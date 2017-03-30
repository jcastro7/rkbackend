package pe.com.tss.runakuna.rest;

import net.sf.jasperreports.engine.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.com.tss.runakuna.exception.GenericRestException;
import pe.com.tss.runakuna.service.EmpleadoService;
import pe.com.tss.runakuna.service.MarcacionEmpleadoService;
import pe.com.tss.runakuna.service.PermisoEmpleadoService;
import pe.com.tss.runakuna.util.*;
import pe.com.tss.runakuna.view.model.*;
import pe.com.tss.runakuna.view.model.importxls.ExcelImporter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping(value = "/utility")
public class UtilityController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UtilityController.class);
    private static String OS = null;

    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    MarcacionEmpleadoService marcacionEmpleadoService;

    @Autowired
    PermisoEmpleadoService permisoEmpleadoService;

    @Autowired
    private ResourceLoader resourceLoader;

    @RequestMapping(value = "/exportarEmpleados", method = RequestMethod.POST)
    @ResponseBody
    public void exportarEmpleados(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String templateFileName = "excel/Empleados.xlsx";

        downloadTemplate(request, response, templateFileName);
    }

    @RequestMapping(value = "/exportarMarcaciones", method = RequestMethod.POST)
    @ResponseBody
    public void exportarMarcaciones(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String templateFileName = "excel/Marcaciones.xlsx";

        downloadTemplateMarcaciones(request, response, templateFileName);
    }


    @RequestMapping(value = "/cargarArchivoDocumento", method = RequestMethod.POST)
    public @ResponseBody
    FileViewModel cargarArchivoDocumento(@RequestParam List<MultipartFile> files, HttpServletResponse response) {

        FileViewModel dto = new FileViewModel();

        String cadena = "";
        MultipartFile file = files.get(0);

        try {

            cadena = Base64.getEncoder().encodeToString(file.getBytes());

            dto.setContent(cadena);
            dto.setName(file.getOriginalFilename());
            dto.setContentType(file.getContentType());

        } catch (IOException e) {
            LOGGER.info(e.getMessage(), e);
            e.printStackTrace();
        }

        return dto;
    }

    private void createDocExcelEmpty(HttpServletResponse response, String templateFileName) throws IOException {

        List<EmpleadoViewModel> pageableResult = new ArrayList<>();
        generateExcel(response, templateFileName, pageableResult);

    }


    @RequestMapping(value = "/descargarTemplateEmpleados", method = RequestMethod.GET)
    @ResponseBody
    public void downloadTemplateFormatProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String templateFileName = "excel/EmpleadosDownloadFormat.xlsx";

        createDocExcelEmpty(response, templateFileName);

    }


    @RequestMapping(value = "/descargarArchivoDocumento", method = RequestMethod.POST)
    @ResponseBody
    public void descargarArchivoDocumento(HttpServletRequest request, HttpServletResponse response) throws IOException {


        String tipoArchivo = request.getParameter("tipoArchivo");
        String contenidoArchivo = request.getParameter("contenidoArchivo");
        String nombreArchivo = request.getParameter("nombreArchivo");

        byte[] contenido = Base64.getDecoder().decode(contenidoArchivo);

        response.setContentType(tipoArchivo);
        response.setHeader("Content-disposition", "attachment;filename=" + nombreArchivo);
        response.getOutputStream().write(contenido);

    }

    @RequestMapping(value = "/eliminarArchivoDocumento", produces = "application/json")
    public void eliminarArchivoDocumento(@RequestParam("fileNames") MultipartFile files,
                                         HttpServletResponse response) {


    }

    @RequestMapping(value = "/templateEmpleadosProcess", method = RequestMethod.GET)
    @ResponseBody
    public void downloadTemplateDrayListProcess(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String docname = ("undefined".equalsIgnoreCase(request.getParameter("docname"))) ? null
                : request.getParameter("docname");

        String templateFileName = System.getProperty("java.io.tmpdir", null) + ((isWindows()) ? "" : "/") + docname; // fileTemp


        TemplateExcelExporter report = new TemplateExcelExporter();

        report.exportX(templateFileName);

        report.writeExcelToResponse(response, "Empleados Process");

    }


    private static synchronized String getOsName() {
        if (OS == null) {
            OS = System.getProperty("os.name");
        }
        return OS;
    }

    private static boolean isWindows() {
        return getOsName().startsWith("Windows");
    }


    @RequestMapping(value = "importarArchivoEmpleados", produces = Constants.JSON)
    @ResponseBody
    public ResponseEntity<JsonResultViewModel> uploadFile(
            @RequestParam("files") MultipartFile uploadfile
    ) {

        try {

            JsonResultViewModel resultFront = new JsonResultViewModel();

            List<EmpleadoViewModel> messageList = new ArrayList<>();


            List<EmpleadoViewModel> baseLaneList = parseEmpleadosFromExcel(resultFront,
                    uploadfile.getBytes());

            List<EmpleadoViewModel> empleadoListSend = new ArrayList<>();
            List<String> empleadoList = new ArrayList<>();

            for (EmpleadoViewModel empleadoDto : baseLaneList) {
                if (empleadoDto.hasError()) {
                    empleadoListSend.add(empleadoDto);
                } else {
                    if (!empleadoList.contains(empleadoDto.getCodigo())) {

                        empleadoListSend.add(empleadoDto);

                        empleadoList.add(empleadoDto.getCodigo());

                    } else {
                        empleadoDto
                                .addErrorStatus("Codigo ya repetido en el archivo.");

                        messageList.add(empleadoDto);
                    }
                }

            }

            ResponseEntity<List<EmpleadoViewModel>> responseEntity = this.procesarMasivamenteEmpleados(empleadoListSend);

            List<EmpleadoViewModel> contextResponse = new ArrayList<>();

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                contextResponse = responseEntity.getBody();

            }

            messageList.addAll(contextResponse);
            Collections.sort(messageList, comparador);

            String docName = CreateFileExportResultEmpleado(messageList);

            resultFront.setFileDocName(docName);

            if (!messageList.stream()
                    .filter(p -> p.getStatusProcessDtoList().stream()
                            .filter(e -> "error".equalsIgnoreCase(e.getStatus())).collect(Collectors.toList())
                            .size() > 0)
                    .collect(Collectors.toList()).isEmpty()) {
                resultFront.setDescription("Archivo cargado con errores");
                resultFront.setStatus("error");
                return new ResponseEntity<>(resultFront, HttpStatus.ACCEPTED);
            }

            resultFront.setDescription("Archivo cargado correctamente");

            return new ResponseEntity<>(resultFront, HttpStatus.OK);


        } catch (Exception e) {
            String msg = (e.getMessage() == null) ? "Error " : e.getMessage();
            LOGGER.error(Constants.ERROR + msg, e);

            throw new GenericRestException("ERR_001", "No se pudo realizar el import de empleados");
        }

    }


    public String CreateFileExportResultEmpleado(List<EmpleadoViewModel> messageList)
            throws IOException {

        String templateFileName = "excel/EmpleadosProcess.xlsx";

        TemplateExcelExporter report = new TemplateExcelExporter();

        loadRegistroEmpleadoProcess(templateFileName, report, messageList);

        String fileExcelDoc = "EmpleadoProcess" + System.currentTimeMillis() + "";// +".xlsx";

        File file = File.createTempFile(fileExcelDoc, ".xlsx");

        report.writeExcelToFile(file);
        return file.getName();
    }


    private void loadRegistroEmpleadoProcess(String templateFileName, TemplateExcelExporter report,
                                             List<EmpleadoViewModel> empleadosDtos) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:" + templateFileName);

        report.exportH(resource.getInputStream());

        int index = 0;
        for (EmpleadoViewModel item : empleadosDtos) {
            index++;
            report.addRow(index);

            report.addColumnValue(0, item.getEmpleadoOrden());
            if (item.getCodigo() != null) {
                report.addColumnValue(1, item.getCodigo());
            }
            report.addColumnValue(2, item.getStatusProcessDtoList().get(0).getStatus());

            StringBuilder bld = new StringBuilder();
            for (StatusProcessViewModel status : item.getStatusProcessDtoList()) {
                if (!"Procesado".equalsIgnoreCase(status.getStatus())) {
                    bld.append(status.getMessage()).append(". ").append("\n");
                }
            }

            report.addColumnValue(3, bld.toString());

        }
    }


    private Comparator<EmpleadoViewModel> comparador = new Comparator<EmpleadoViewModel>() {
        public int compare(EmpleadoViewModel a, EmpleadoViewModel b) {
            int resultado = Double.compare((double) a.getEmpleadoOrden(),
                    (double) b.getEmpleadoOrden());
            if (resultado != 0) {
                return resultado;
            }
            return resultado;
        }
    };


    @RequestMapping(value = "/procesarMasivamenteEmpleados", method = RequestMethod.POST)
    public ResponseEntity<List<EmpleadoViewModel>> procesarMasivamenteEmpleados(@RequestBody List<EmpleadoViewModel> dtos) {

        List<EmpleadoViewModel> empleadoDtoList = new ArrayList<>();
        try {


            empleadoDtoList = empleadoService.procesarMasivamenteEmpleados(dtos);
        } catch (Exception e) {
            LOGGER.debug("ERROR", e);
            String msg = ((e.getMessage() == null) ? "" : e.getMessage());

            throw new GenericRestException("ERR_001", msg);
        }


        return new ResponseEntity<>(empleadoDtoList, HttpStatus.OK);

    }


    private List<EmpleadoViewModel> parseEmpleadosFromExcel(JsonResultViewModel resultFront, byte[] bytes) throws Exception {


        List<EmpleadoViewModel> result = new ArrayList<>();

        File excel = FileUtils.createTempFile(".xls");
        FileUtils.writeToFile(excel, bytes);
        FileInputStream myInput = new FileInputStream(excel);
        Workbook myWorkBook = new XSSFWorkbook(myInput);
        Sheet mySheet = myWorkBook.getSheetAt(0);

        int numRow = mySheet.getLastRowNum();

        if (numRow > 10000) {
            EmpleadoViewModel empleadoDtoSendError = new EmpleadoViewModel();
            empleadoDtoSendError.addErrorStatus("Excel excede los 10,000 registros.");
            result.add(empleadoDtoSendError);
        } else {

            if (numRow == 0) {
                result.clear();
                EmpleadoViewModel empleadoDtoSendError = new EmpleadoViewModel();
                empleadoDtoSendError.addErrorStatus("Excel esta vacío");
                result.add(empleadoDtoSendError);

            } else {
                for (int i = 1; i < numRow + 1; i++) {
                    Row row = (Row) mySheet.getRow(i);
                    EmpleadoViewModel empleadoDto;

                    empleadoDto = poblarLineaDesdeTemplate(row);
                    empleadoDto.setEmpleadoOrden(i);

                    String validate = validateExcelEmpty(numRow + 1, empleadoDto);

                    if ("EMPTY EXCEL".equalsIgnoreCase(validate)) {
                        result.clear();
                        EmpleadoViewModel empleadoDtoSendError = new EmpleadoViewModel();
                        empleadoDtoSendError.addErrorStatus("Excel esta vacío");
                        result.add(empleadoDtoSendError);
                        break;
                    }
                    if ("EMPTY ROW".equalsIgnoreCase(validate)) {
                        continue;
                    }


                    if (empleadoDto.getErrorList().size() > 0) {
                        for (EmpleadoMessageRsl empleadoMessageRsl : empleadoDto.getErrorList()) {
                            empleadoDto.addErrorStatus(empleadoMessageRsl.getMessage());
                        }
                    }

                    result.add(empleadoDto);
                }


                if (result.isEmpty()) {
                    result.clear();
                    EmpleadoViewModel empleadoDtoSendError = new EmpleadoViewModel();
                    empleadoDtoSendError.addErrorStatus("Excel esta vacío");
                    result.add(empleadoDtoSendError);
                }
            }
        }


        return result;

    }


    private String validateExcelEmpty(int i, EmpleadoViewModel empleadoDto) {


        if (StringUtils.isEmpty((empleadoDto.getCodigo() == null) ? "" : empleadoDto.getCodigo().trim())
                && StringUtils.isEmpty((empleadoDto.getApellidoPaterno() == null) ? "" : empleadoDto.getApellidoPaterno().trim())
                && StringUtils.isEmpty((empleadoDto.getApellidoMaterno() == null) ? "" : empleadoDto.getApellidoMaterno().trim())
                && StringUtils.isEmpty((empleadoDto.getTipoDocumento() == null) ? "" : empleadoDto.getTipoDocumento().trim())
                && StringUtils.isEmpty((empleadoDto.getNumeroDocumento() == null) ? "" : empleadoDto.getNumeroDocumento().trim())
                && StringUtils.isEmpty((empleadoDto.getGenero() == null) ? "" : empleadoDto.getGenero().trim())
                && StringUtils.isEmpty((empleadoDto.getEstadoCivil() == null) ? "" : empleadoDto.getEstadoCivil().trim())
                && StringUtils.isEmpty((empleadoDto.getGrupoSangre() == null) ? "" : empleadoDto.getGrupoSangre().trim())
                && empleadoDto.getDiscapacitado() == null
                && empleadoDto.getFechaNacimiento() == null
                && StringUtils.isEmpty((empleadoDto.getPaisNacimiento() == null) ? "" : empleadoDto.getPaisNacimiento().trim())) {

            if (i <= 2) {

                return "EMPTY EXCEL";
            } else {

                return "EMPTY ROW";
            }
        }
        return "OK";
    }

    private EmpleadoViewModel poblarLineaDesdeTemplate(Row row) {

        EmpleadoViewModel result = new EmpleadoViewModel();

        ExcelImporter excelImporter = new ExcelImporter(row, result);
        result.setCodigo(excelImporter.build(ExcelTemplate.CODIGO).title("Codigo").readStr());
        result.setNombre(excelImporter.build(ExcelTemplate.NOMBRES).title("Nombres").readStr());
        result.setApellidoPaterno(excelImporter.build(ExcelTemplate.APELLIDO_PATERNO).title("Apellido Paterno").readStr());
        result.setApellidoMaterno(excelImporter.build(ExcelTemplate.APELLIDO_MATERNO).title("Apellido Materno").readStr());
        result.setTipoDocumentoString(excelImporter.build(ExcelTemplate.TIPO_DOCUMENTO).title("Tipo Documento").readStr());

        result.setNumeroDocumento(excelImporter.build(ExcelTemplate.NUMERO_DOCUMENTO).title("Numero Documento").readStr());
        result.setGenero(excelImporter.build(ExcelTemplate.GENERO).title("Genero").readStr());
        result.setEstadoCivil(excelImporter.build(ExcelTemplate.ESTADO_CIVIL).title("Estado Civil").readStr());
        result.setGrupoSangre(excelImporter.build(ExcelTemplate.GRUPO_SANGUINEO).title("Grupo Sanguineo").readStr());
        result.setDiscapacitado(excelImporter.build(ExcelTemplate.DISCAPACITADO).title("Discapacitado").readNumberLong().intValue());
        result.setFechaNacimiento(excelImporter.build(ExcelTemplate.FECHA_NACIMIENTO).title("Fecha Nacimiento").readDate("dd/MM/yyyy"));

        result.setPaisNacimiento(excelImporter.build(ExcelTemplate.PAIS).title("Pais").readStr());
        result.setEmailInterno(excelImporter.build(ExcelTemplate.CORREO_ELECTRONICO_INTERNO).title("Correo Electronico Interno").readStr());

        result.setTelefonoInterno(excelImporter.build(ExcelTemplate.TELEFONO_INTERNO).title("Telefono Interno").readStr());
        result.setAnexoInterno(excelImporter.build(ExcelTemplate.ANEXO_INTERNO).title("Anexo Interno").readStr());

        result.setCentroCostoString(excelImporter.build(ExcelTemplate.CENTRO_COSTO).title("Centro de Costo").readStr());

        result.setContratoTrabajo(excelImporter.build(ExcelTemplate.CONTRATO_TRABAJO).title("Contrato Trabajo").readStr());
        result.setTipoTrabajador(excelImporter.build(ExcelTemplate.TIPO_TRABAJO).title("Tipo Trabajo").readStr());
        result.setRegimenHorario(excelImporter.build(ExcelTemplate.REGIMEN_HORARIO).title("Regimen Horario").readStr());
        result.setRegimenLaboral(excelImporter.build(ExcelTemplate.REGIMEN_LABORAL).title("Regimen Laboral").readStr());
        result.setEsPersonalDeConfianza(excelImporter.build(ExcelTemplate.PERSONAL_CONFIANZA).title("Personal de Confianza").readNumberLong().intValue());

        result.setDireccionDomicilio(excelImporter.build(ExcelTemplate.DIRECCION).title("Direccion").readStr());
        result.setTipoDomicilioString(excelImporter.build(ExcelTemplate.TIPO_DOMICILIO).title("Tipo de Domicilio").readStr());
        result.setDistritoDomicilio(excelImporter.build(ExcelTemplate.DISTRITO).title("Distrito").readStr());
        result.setTelefonoCasa(excelImporter.build(ExcelTemplate.TELEFONO_CASA).title("Telefono de Casa").readStr());

        result.setEmailPersonal(excelImporter.build(ExcelTemplate.CORREO_ELECTRONICO).title("Correo Electronico").readStr());
        result.setEmailPersonal(excelImporter.build(ExcelTemplate.CORREO_ELECTRONICO).title("Correo Electronico").readStr());
        result.setRelacionContactoEmergencia(excelImporter.build(ExcelTemplate.RELACION_CONTACTO).title("Relacion Contacto").readStr());
        result.setNombreContactoEmergencia(excelImporter.build(ExcelTemplate.NOMBRE_CONTACTO).title("Nombre Completo Contacto").readStr());
        result.setTelefonoContactoEmergencia(excelImporter.build(ExcelTemplate.TELEFONO_CONTACTO).title("Telefono Contacto").readStr());
        result.setEmailContactoEmergencia(excelImporter.build(ExcelTemplate.CORREO_ELECTRONICO_CONTACTO).title("Correo Electronico Contacto").readStr());

        result.setEstado(excelImporter.build(ExcelTemplate.ESTADO).title("Estado").readStr());
        return result;
    }


    @RequestMapping(value = "/eliminarArchivoEmpleados", produces = "application/json")
    public void eliminarArchivoEmpleados(@RequestParam("fileNames") MultipartFile files,
                                         HttpServletResponse response) {


    }


    private void downloadTemplate(HttpServletRequest request, HttpServletResponse response, String templateFileName) throws IOException {

        EmpleadoFilterViewModel empleadoFilter = adapterFilterEmpleado(request);

        createDocExcel(response, empleadoFilter, templateFileName);

    }


    private EmpleadoFilterViewModel adapterFilterEmpleado(HttpServletRequest request) {

        EmpleadoFilterViewModel busquedaEmpleadoDto = new EmpleadoFilterViewModel();

        String nombres = (request.getParameter("nombres").equalsIgnoreCase(Constants.NULL)) ? null : request.getParameter("nombres");
        String apellidoPaterno = (request.getParameter("apellidoPaterno").equalsIgnoreCase(Constants.NULL)) ? null : request.getParameter("apellidoPaterno");
        String apellidoMaterno = (request.getParameter("apellidoMaterno").equalsIgnoreCase(Constants.NULL)) ? null : request.getParameter("apellidoMaterno");
        String codigo = (request.getParameter("codigo").equalsIgnoreCase(Constants.NULL)) ? null : request.getParameter("codigo");
        String tipoDocumento = (request.getParameter("tipoDocumento").equalsIgnoreCase(Constants.NULL)) ? null : request.getParameter("tipoDocumento");
        String numeroDocumento = (request.getParameter("numeroDocumento").equalsIgnoreCase(Constants.NULL)) ? null : request.getParameter("numeroDocumento");

        String unidadNegocio = (request.getParameter("unidadNegocio").equalsIgnoreCase(Constants.NULL)) ? null : request.getParameter("unidadNegocio");
        String departamento = (request.getParameter("departamento").equalsIgnoreCase(Constants.NULL)) ? null : request.getParameter("departamento");
        String proyecto = (request.getParameter("proyecto").equalsIgnoreCase(Constants.NULL)) ? null : request.getParameter("proyecto");

        String jefeInmediato = (request.getParameter("jefeInmediato").equalsIgnoreCase(Constants.NULL)) ? null : request.getParameter("jefeInmediato");
        String centroCosto = (request.getParameter("centroCosto").equalsIgnoreCase(Constants.NULL)) ? null : request.getParameter("centroCosto");

        String correoElectronico = (request.getParameter("correoElectronico").equalsIgnoreCase(Constants.NULL)) ? null : request.getParameter("correoElectronico");
        String estado = (request.getParameter("estado").equalsIgnoreCase(Constants.NULL)) ? null : request.getParameter("estado");


        busquedaEmpleadoDto.setNombres(nombres);
        busquedaEmpleadoDto.setApePaterno(apellidoPaterno);
        busquedaEmpleadoDto.setApeMaterno(apellidoMaterno);
        busquedaEmpleadoDto.setCodigo(codigo);
        busquedaEmpleadoDto.setTipoDocumento(tipoDocumento);
        busquedaEmpleadoDto.setNumeroDocumento(numeroDocumento);
        if (unidadNegocio != null && !"".equals(unidadNegocio.trim())) {
            busquedaEmpleadoDto.setUnidadNegocio(new Long(unidadNegocio));
        }
        if (departamento != null && !"".equals(departamento.trim())) {
            busquedaEmpleadoDto.setDepartamento(new Long(departamento));
        }
        if (proyecto != null && !"".equals(proyecto.trim())) {
            busquedaEmpleadoDto.setProyecto(new Long(proyecto));
        }

        busquedaEmpleadoDto.setJefeInmediato(jefeInmediato);
        busquedaEmpleadoDto.setCentroCosto(centroCosto);
        busquedaEmpleadoDto.setCorreoElectronico(correoElectronico);
        busquedaEmpleadoDto.setEstado(estado);

        return busquedaEmpleadoDto;
    }

    @RequestMapping(value = "/busquedaEmpleadoExport", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<List<EmpleadoViewModel>> busquedaEmpleadoExport(@RequestBody EmpleadoFilterViewModel empleadoFilter) {

        List<EmpleadoViewModel> empleadoResult = empleadoService.searchExport(empleadoFilter);
        if (empleadoResult == null)
            empleadoResult = new ArrayList<>();

        return new ResponseEntity<>(empleadoResult, HttpStatus.OK);

    }

    private void createDocExcel(HttpServletResponse response, EmpleadoFilterViewModel empleadoFilter, String templateFileName) throws IOException {


        ResponseEntity<List<EmpleadoViewModel>> responseEntity = busquedaEmpleadoExport(empleadoFilter);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            List<EmpleadoViewModel> pageableResult = responseEntity.getBody();
            generateExcel(response, templateFileName, pageableResult);
        }
    }

    private void generateExcel(HttpServletResponse response, String templateFileName, List<EmpleadoViewModel> pageableResult) throws IOException {

        TemplateExcelExporter report = new TemplateExcelExporter();

        loadRegistrosEmpleados(templateFileName, report, pageableResult);

        report.writeExcelToResponse(response, "Reporte de Empleados");
    }

    private void loadRegistrosEmpleados(String templateFileName, TemplateExcelExporter report, List<EmpleadoViewModel> empleadosResult) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:" + templateFileName);

        report.exportH(resource.getInputStream());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int index = 0;
        if (empleadosResult != null) {
            for (EmpleadoViewModel item : empleadosResult) {
                index++;
                report.addRow(index);
                report.addColumnValue(0, item.getCodigo());
                report.addColumnValue(1, item.getNombre());
                report.addColumnValue(2, item.getApellidoPaterno());
                report.addColumnValue(3, item.getApellidoMaterno());
                report.addColumnValue(4, item.getTipoDocumento());
                report.addColumnValue(5, item.getNumeroDocumento());
                report.addColumnValue(6, item.getGenero());
                report.addColumnValue(7, item.getEstadoCivil());
                report.addColumnValue(8, item.getGrupoSangre());
                report.addColumnValue(9, item.getDiscapacitadoString());
                report.addColumnValue(10, sdf.format(item.getFechaNacimiento()));
                report.addColumnValue(11, item.getPaisNacimiento());
                report.addColumnValue(12, item.getEmailInterno());
                report.addColumnValue(13, item.getTelefonoInterno());
                report.addColumnValue(14, item.getAnexoInterno());
                report.addColumnValue(15, item.getCentroCostoString());
                report.addColumnValue(16, item.getContratoTrabajoString());
                report.addColumnValue(17, item.getTipoTrabajadorString());
                report.addColumnValue(18, item.getRegimenHorarioString());
                report.addColumnValue(19, item.getRegimenLaboralString());
                report.addColumnValue(20, item.getEsPersonalDeConfianzaString());
                report.addColumnValue(21, item.getDireccionDomicilio());
                report.addColumnValue(22, item.getTipoDomicilioString());
                report.addColumnValue(23, item.getDistritoDomicilio());
                report.addColumnValue(24, item.getTelefonoCasa());
                report.addColumnValue(25, item.getEmailPersonal());
                report.addColumnValue(26, item.getRelacionContactoEmergenciaString());
                report.addColumnValue(27, item.getNombreContactoEmergencia());
                report.addColumnValue(28, item.getTelefonoContactoEmergencia());
                report.addColumnValue(29, item.getEmailContactoEmergencia());
                report.addColumnValue(30, item.getEstado());


                // report.addColumnValue(13, item.getEstado());
            }
        }
    }

    private void downloadTemplateMarcaciones(HttpServletRequest request, HttpServletResponse response, String templateFileName) throws IOException {

        MarcacionFilterViewModel busquedaMarcacionDto = adapterFilterMarcacion(request);

        createDocExcelMarcacion(response, busquedaMarcacionDto, templateFileName);

    }

    private MarcacionFilterViewModel adapterFilterMarcacion(HttpServletRequest request) {

        MarcacionFilterViewModel busquedaMarcacionDto = new MarcacionFilterViewModel();

        String idEmpleado = (request.getParameter("idEmpleado").equalsIgnoreCase(Constants.NULL) || "".equals(request.getParameter("idEmpleado")))? null : request.getParameter("idEmpleado");
        String desde = (request.getParameter("desde").equalsIgnoreCase(Constants.NULL)) ? null : request.getParameter("desde");
        String hasta = (request.getParameter("hasta").equalsIgnoreCase(Constants.NULL)) ? null : request.getParameter("hasta");

        String unidadNegocio = (request.getParameter("unidadNegocio").equalsIgnoreCase(Constants.NULL) || "".equals(request.getParameter("unidadNegocio"))) ? null : request.getParameter("unidadNegocio");
        String departamento = (request.getParameter("departamento").equalsIgnoreCase(Constants.NULL) || "".equals(request.getParameter("departamento"))) ? null : request.getParameter("departamento");
        String proyecto = (request.getParameter("proyecto").equalsIgnoreCase(Constants.NULL) || "".equals(request.getParameter("proyecto"))) ? null : request.getParameter("proyecto");

        String idJefeInmediato = (request.getParameter("idJefeInmediato").equalsIgnoreCase(Constants.NULL) || "".equals(request.getParameter("idJefeInmediato"))) ? null : request.getParameter("idJefeInmediato");


        busquedaMarcacionDto.setUnidadNegocio(unidadNegocio == null ? null : new Long(unidadNegocio));
        busquedaMarcacionDto.setDepartamento(departamento == null ? null : new Long(departamento));
        busquedaMarcacionDto.setProyecto(proyecto == null ? null : new Long(proyecto));
        busquedaMarcacionDto.setIdJefeInmediato(idJefeInmediato == null ? null : new Long(idJefeInmediato));
        busquedaMarcacionDto.setIdEmpleado(idEmpleado == null ? null : new Long(idEmpleado));

        busquedaMarcacionDto.setDesde(DateUtil.formatoFechaArchivoMarcacion(desde));
        busquedaMarcacionDto.setHasta(DateUtil.formatoFechaArchivoMarcacion(hasta));

        return busquedaMarcacionDto;
    }

    @RequestMapping(value = "/busquedaMarcacionesEmpleado", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<List<MarcacionViewModel>> busquedaMarcacionesEmpleado(@RequestBody MarcacionFilterViewModel busquedaMarcacionDto) {

        List<MarcacionViewModel> result;
        result = empleadoService.busquedaMarcacionesEmpleado(busquedaMarcacionDto);
        if (result == null)
            result = new ArrayList<>();

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    private void createDocExcelMarcacion(HttpServletResponse response, MarcacionFilterViewModel busquedaMarcacionDto, String templateFileName) throws IOException {


        ResponseEntity<List<MarcacionViewModel>> responseEntity = busquedaMarcacionesEmpleado(busquedaMarcacionDto);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            List<MarcacionViewModel> pageableResult = responseEntity.getBody();
            generateExcelMarcacion(response, templateFileName, pageableResult);
        }
    }

    private void generateExcelMarcacion(HttpServletResponse response, String templateFileName, List<MarcacionViewModel> pageableResult) throws IOException {

        TemplateExcelExporter report = new TemplateExcelExporter();

        loadRegistrosMarcaciones(templateFileName, report, pageableResult);

        report.writeExcelToResponse(response, "Reporte de Marcaciones");
    }

    private void loadRegistrosMarcaciones(String templateFileName, TemplateExcelExporter report, List<MarcacionViewModel> marcacionDtos) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:" + templateFileName);

        report.exportH(resource.getInputStream());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int index = 0;
        if (marcacionDtos != null) {
            for (MarcacionViewModel item : marcacionDtos) {
                index++;
                report.addRow(index);
                report.addColumnValue(0, sdf.format(item.getFecha()));
                report.addColumnValue(1, item.getCodigoEmpleado());
                report.addColumnValue(2, item.getNombreCompletoEmpleado());

                report.addColumnValue(3, item.getHoraIngreso());
                report.addColumnValue(4, item.getHoraInicioAlmuerzo());
                report.addColumnValue(5, item.getHoraFinAlmuerzo());
                report.addColumnValue(6, item.getHoraSalida());
                report.addColumnValue(7, item.getHoraIngresoHorario());
                report.addColumnValue(8, item.getHoraSalidaHorario());

                report.addColumnValue(9, item.getDemoraEntrada());
                report.addColumnValue(10, item.getDemoraAlmuerzo());
                report.addColumnValue(11, item.getDemoraSalida());
                report.addColumnValue(12, item.getTardanza());
                report.addColumnValue(13, item.getSolicitudCambio());
            }
        }
    }

    @RequestMapping(value = "/descargarContrato", method = RequestMethod.POST)
    @ResponseBody
    public void descargarContrato(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            String nombreReporteGenerado = "Contrato";

            String nombreCompleto = request.getParameter("nombre");
            String domicilio = request.getParameter("domicilio");
            String cargo = request.getParameter("cargo");
            String dni = request.getParameter("dni");
            String sueldo = request.getParameter("sueldo");

            String fechaInicio = request.getParameter("fechaInicio");
            String fechaFin = request.getParameter("fechaFin");
            String plazo = request.getParameter("plazo");

            String sueldoEnLetras = StringUtil.convertNumberToLetter(sueldo);

            Map<String, Object> reportParameters = new HashMap<>();
            reportParameters.put("Nombre", StringUtil.toCamelCase(nombreCompleto));
            reportParameters.put("Domicilio", domicilio);
            reportParameters.put("Cargo", cargo);
            reportParameters.put("Dni", dni);
            reportParameters.put("Sueldo", new BigDecimal(sueldo));
            reportParameters.put("SueldoEnLetras", sueldoEnLetras);
            reportParameters.put("FechaInicio", fechaInicio);
            reportParameters.put("FechaFin", fechaFin);
            reportParameters.put("Plazo", plazo);
            reportParameters.put("FechaContrato", DateUtil.fmtDt(new Date()));

            JasperReport jasperReport;
            JasperPrint jasperPrint;

            Resource resource = resourceLoader.getResource("classpath:" + "static/reporte/contrato.jrxml");

            jasperReport = JasperCompileManager.compileReport(resource.getInputStream());

            jasperPrint = JasperFillManager.fillReport(jasperReport, reportParameters, new JREmptyDataSource());

            response.setContentType("application/x-pdf");
            response.setHeader("Content-Disposition", "inline; filename=" + nombreReporteGenerado + ".pdf");

            //opcion 1
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

            //opcion 2
            /*ByteArrayOutputStream baos = new ByteArrayOutputStream();
			JRPdfExporter exporter = new JRPdfExporter();
			
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
			
			exporter.exportReport();
			
			ServletOutputStream outStream = response.getOutputStream();
			
			baos.writeTo(outStream);
			outStream.flush();
			outStream.close();*/

            //byte[] ba = JasperRunManager.runReportToPdf(jasperReport, reportParameters);


            //response.getOutputStream().write(ba);


        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException(e.getMessage(), e);
        }

    }


}
