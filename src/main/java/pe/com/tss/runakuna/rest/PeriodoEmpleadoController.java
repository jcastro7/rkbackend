package pe.com.tss.runakuna.rest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.tss.runakuna.service.PeriodoEmpleadoService;
import pe.com.tss.runakuna.util.Constants;
import pe.com.tss.runakuna.util.DateUtil;
import pe.com.tss.runakuna.util.TemplateExcelExporter;
import pe.com.tss.runakuna.view.model.MarcacionFilterViewModel;
import pe.com.tss.runakuna.view.model.MarcacionViewModel;
import pe.com.tss.runakuna.view.model.PeriodoEmpleadoFilterViewModel;
import pe.com.tss.runakuna.view.model.PeriodoEmpleadoResultViewModel;
import pe.com.tss.runakuna.view.model.PermisoEmpleadoFilterViewModel;
import pe.com.tss.runakuna.view.model.PermisoEmpleadoResultViewModel;

@RestController
@RequestMapping(value = "/api/periodoEmpleado")
public class PeriodoEmpleadoController extends BaseController{
	
	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	PeriodoEmpleadoService periodoEmpleadoService;


	@RequestMapping(value = "/busquedaPeriodoEmpleado", method = RequestMethod.POST)
    public @ResponseBody  ResponseEntity<List<PeriodoEmpleadoResultViewModel>>  busquedaPeriodoEmpleado(@RequestBody PeriodoEmpleadoFilterViewModel busquedaPeriodoEmpleadoDto) {

        List<PeriodoEmpleadoResultViewModel> result = new ArrayList<PeriodoEmpleadoResultViewModel>();
        result = periodoEmpleadoService.busquedaPeriodoEmpleado(busquedaPeriodoEmpleadoDto);
		if(result == null)
			result = new ArrayList<>();
        return new ResponseEntity<>(result, HttpStatus.OK);

    }
	
	@RequestMapping(value = "/exportarPeriodoEmpleado", method = RequestMethod.POST)
	@ResponseBody
	public void exportarPeriodoEmpleado(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String templateFileName = "excel/PeriodoEmpleados.xlsx";
		downloadTemplatePeriodoEmpleado(request,response,templateFileName);
	}
	
	private void downloadTemplatePeriodoEmpleado(HttpServletRequest request, HttpServletResponse response, String templateFileName) throws IOException {

		PeriodoEmpleadoFilterViewModel busquedaMarcacionDto = adapterFilterPeriodoEmpleado(request);
        createDocExcelPeriodoEmpleado(response, busquedaMarcacionDto, templateFileName);

    }
	
	private PeriodoEmpleadoFilterViewModel adapterFilterPeriodoEmpleado(HttpServletRequest request) {

		PeriodoEmpleadoFilterViewModel busquedaPeriodoEmpleadoDto = new PeriodoEmpleadoFilterViewModel();

        String idEmpleado = (request.getParameter("idEmpleado").equalsIgnoreCase(Constants.NULL) || request.getParameter("idEmpleado").equals("")) ? null : request.getParameter("idEmpleado");
        
        String unidadNegocio = (request.getParameter("unidadNegocio").equalsIgnoreCase(Constants.NULL) || request.getParameter("unidadNegocio").equals("")) ? null : request.getParameter("unidadNegocio");
        String departamento = (request.getParameter("departamento").equalsIgnoreCase(Constants.NULL) || request.getParameter("departamento").equals("")) ? null : request.getParameter("departamento");
        String proyecto = (request.getParameter("proyecto").equalsIgnoreCase(Constants.NULL) || request.getParameter("proyecto").equals("")) ? null : request.getParameter("proyecto");

        String idJefeInmediato = (request.getParameter("idJefeInmediato").equalsIgnoreCase(Constants.NULL) || request.getParameter("idJefeInmediato").equals("")) ? null : request.getParameter("idJefeInmediato");

        String vigente = request.getParameter("vigente");
        
        busquedaPeriodoEmpleadoDto.setUnidadNegocio(unidadNegocio == null ? null : new Long(unidadNegocio));
        busquedaPeriodoEmpleadoDto.setDepartamento(departamento == null ? null : new Long(departamento));
        busquedaPeriodoEmpleadoDto.setProyecto(proyecto == null ? null : new Long(proyecto));
        busquedaPeriodoEmpleadoDto.setIdJefeInmediato(idJefeInmediato == null ? null : new Long(idJefeInmediato));
        busquedaPeriodoEmpleadoDto.setIdEmpleado(idEmpleado == null ? null : new Long(idEmpleado));
        busquedaPeriodoEmpleadoDto.setVigente(vigente == null ? null : new Boolean(vigente));

        return busquedaPeriodoEmpleadoDto;
    }
	
	private void createDocExcelPeriodoEmpleado(HttpServletResponse response, PeriodoEmpleadoFilterViewModel busquedaPeriodoEmpleadoDto, String templateFileName) throws IOException {

        ResponseEntity<List<PeriodoEmpleadoResultViewModel>> responseEntity = busquedaPeriodoEmpleado(busquedaPeriodoEmpleadoDto);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            List<PeriodoEmpleadoResultViewModel> pageableResult = responseEntity.getBody();
            generateExcelPeriodoEmpleado(response, templateFileName, pageableResult);
        }
    }
	
	private void generateExcelPeriodoEmpleado(HttpServletResponse response, String templateFileName, List<PeriodoEmpleadoResultViewModel> pageableResult) throws IOException {

        TemplateExcelExporter report = new TemplateExcelExporter();

        loadRegistrosPeriodoEmpleado(templateFileName, report, pageableResult);

        report.writeExcelToResponse(response, "Reporte de Marcaciones");
    }

	private void loadRegistrosPeriodoEmpleado(String templateFileName, TemplateExcelExporter report, List<PeriodoEmpleadoResultViewModel> periodoEmpleadoDto) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:" + templateFileName);

        report.exportH(resource.getInputStream());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int index = 0;
        if (periodoEmpleadoDto != null) {
            for (PeriodoEmpleadoResultViewModel item : periodoEmpleadoDto) {
                index++;
                report.addRow(index);
                
                report.addColumnValue(0, item.getIdPeriodoEmpleado());
                report.addColumnValue(1, item.getNombreEmpleado());               
                report.addColumnValue(2, sdf.format(item.getFechaInicio()));
                report.addColumnValue(3, sdf.format(item.getFechaFin()));
                report.addColumnValue(4, item.getMaxDiasVacaciones());
                report.addColumnValue(5, item.getDiasVacacionesDisponibles());
                report.addColumnValue(6, item.getMaxPermisos());
                report.addColumnValue(7, item.getPermisosDisponibles());
            }
        }
    }	

}
