package pe.com.tss.runakuna.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.tss.runakuna.view.model.AlertaViewModel;
import pe.com.tss.runakuna.view.model.CalendarioFilterViewModel;
import pe.com.tss.runakuna.view.model.CalendarioResultViewModel;
import pe.com.tss.runakuna.view.model.HorarioDiaViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;
import pe.com.tss.runakuna.view.model.TablaGeneralFilterViewModel;
import pe.com.tss.runakuna.view.model.TablaGeneralResultViewModel;
import pe.com.tss.runakuna.view.model.TablaGeneralViewModel;
import pe.com.tss.runakuna.service.TablaGeneralService;

@RestController
@RequestMapping(value="/api/tablaGeneral")
@CrossOrigin()
public class TablaGeneralController extends BaseController{

	@Autowired
	TablaGeneralService tablaGeneralService;

	private List<TablaGeneralViewModel> resultList = new ArrayList<>();
	private List<HorarioDiaViewModel> resultListDia = new ArrayList<>();

	@RequestMapping(value = "/obtenerTipoDocumento", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralViewModel> obtenerTipoDocumento() {

		resultList = tablaGeneralService.obtenerTiposDocumento();
		return resultList;
	}
	@RequestMapping(value = "/obtenerTipoReporte", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralViewModel> obtenerTipoReporte() {

		resultList = tablaGeneralService.obtenerTipoReporte();
		return resultList;
	}

	@RequestMapping(value = "/obtenerEstadoCivil", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralViewModel> obtenerEstadoCivil() {

		resultList = tablaGeneralService.obtenerEstadosCivil();
		return resultList;
	}

	@RequestMapping(value = "/obtenerGrupoSanguineo", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralViewModel> obtenerGrupoSanguineo() {

		resultList = tablaGeneralService.obtenerGruposSanguineo();
		return resultList;
	}

	@RequestMapping(value = "/obtenerGenero", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralViewModel> obtenerGenero() {

		resultList = tablaGeneralService.obtenerGeneros();
		return resultList;
	}

	@RequestMapping(value = "/obtenerTipoDomicilio", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralViewModel> obtenerTipoDomicilio() {

		resultList = tablaGeneralService.obtenerTiposDomicilio();
		return resultList;
	}

	@RequestMapping(value = "/obtenerRegimenHorario", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralViewModel> obtenerRegimenHorario() {

		resultList = tablaGeneralService.obtenerRegimenHorario();
		return resultList;
	}

	@RequestMapping(value = "/obtenerTipoHorario", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralViewModel> obtenerTipoHorario() {

		resultList = tablaGeneralService.obtenerTipoHorario();
		return resultList;
	}

	@RequestMapping(value = "/obtenerContratoTrabajo", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralViewModel> obtenerContratoTrabajo() {

		resultList = tablaGeneralService.obtenerContratoTrabajo();
		return resultList;
	}

	@RequestMapping(value = "/obtenerTipoTrabajo", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralViewModel> obtenerTipoTrabajo() {

		resultList = tablaGeneralService.obtenerTipoTrabajo();
		return resultList;
	}

	@RequestMapping(value = "/obtenerRegimenLaboral", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralViewModel> obtenerRegimenLaboral() {

		resultList = tablaGeneralService.obtenerRegimenLaboral();
		return resultList;
	}

	@RequestMapping(value = "/obtenerNivelEducacion", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralViewModel> obtenerNivelEducacion() {

		resultList = tablaGeneralService.obtenerNivelEducacion();
		return resultList;
	}

	@RequestMapping(value = "/obtenerDias", method = RequestMethod.GET)
	public @ResponseBody List<HorarioDiaViewModel> obtenerDias() {

		resultListDia = tablaGeneralService.obtenerDias();
		return resultListDia;
	}

	@RequestMapping(value = "/obtenerTipoEquipo", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralViewModel> obtenerTipoEquipo() {

		resultList = tablaGeneralService.obtenerTipoEquipo();
		return resultList;
	}

	@RequestMapping(value = "/obtenerEmpleadoEstados", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralViewModel> obtenerEmpleadoEstado() {

		resultList = tablaGeneralService.obtenerEmpleadoEstado();
		return resultList;
	}

	@RequestMapping(value = "/obtenerEstadoTipoEquipo", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralViewModel> obtenerEstadoTipoEquipo() {

		resultList = tablaGeneralService.obtenerEstadoTipoEquipo();
		return resultList;
	}

	@RequestMapping(value = "/obtenerRelacionContactoEmergencia", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralViewModel> obtenerRelacionContactoEmergencia() {

		resultList = tablaGeneralService.obtenerRelacionContactoEmergencia();
		return resultList;
	}

	@RequestMapping(value = "/obtenerMotivosPermiso", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralViewModel> obtenerMotivosPermiso() {

		resultList = tablaGeneralService.obtenerMotivosPermiso();
		return resultList;
	}

	@RequestMapping(value = "/obtenerPermisoEstados", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralViewModel> obtenerPermisoEstado() {

		resultList = tablaGeneralService.obtenerPermisoEstado();
		return resultList;
	}

	@RequestMapping(value = "/obtenerVacacionesEstados", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralViewModel> obtenerVacacionesEstados() {

		resultList = tablaGeneralService.obtenerVacacionesEstados();
		return resultList;
	}

	@RequestMapping(value = "/obtenerLicenciaEstados", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralViewModel> obtenerLicenciaEstados() {

		resultList = tablaGeneralService.obtenerLicenciaEstados();
		return resultList;
	}

	@RequestMapping(value = "/obtenerMotivoRenuncia", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralViewModel> obtenerMotivoRenuncia() {

		resultList = tablaGeneralService.obtenerMotivoRenuncia();
		return resultList;
	}

	@RequestMapping(value = "/obtenerRelacionDependiente", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralViewModel> obtenerRelacionDependiente() {

		resultList = tablaGeneralService.obtenerRelacionDependiente();
		return resultList;
	}

	@RequestMapping(value = "/obtenerEstados", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralViewModel> obtenerEstados() {

		resultList = tablaGeneralService.obtenerEstados();
		return resultList;
	}

	@RequestMapping(value = "/obtenerEntidadFinanciera", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralViewModel> obtenerEntidadFinanciera() {

		resultList = tablaGeneralService.obtenerEntidadFinanciera();
		return resultList;
	}

	@RequestMapping(value = "/obtenerAFP", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralViewModel> obtenerAFP() {

		resultList = tablaGeneralService.obtenerAFP();
		return resultList;
	}

	@RequestMapping(value = "/obtenerEPS", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralViewModel> obtenerEPS() {

		resultList = tablaGeneralService.obtenerEPS();
		return resultList;
	}

	@RequestMapping(value = "/obtenerTipoNotificacion", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralViewModel> obtenerTipoNotificacion() {

		resultList = tablaGeneralService.obtenerTipoNotificacion();
		return resultList;
	}
	@RequestMapping(value = "/obtenerTipoAlerta", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralViewModel> obtenerTipoAlerta() {

		resultList = tablaGeneralService.obtenerTipoAlerta();
		return resultList;
	}
	@RequestMapping(value = "/obtenerTipoMarcacion", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralViewModel> obtenerTipoMarcacion() {

		resultList = tablaGeneralService.obtenerTipoMarcacion();
		return resultList;
	}

	@RequestMapping(value = "/buscarGrupoTablaGeneral", method = RequestMethod.POST)
	public @ResponseBody List<TablaGeneralViewModel> buscarGrupoTablaGeneral(@RequestBody Long idEmpresa) {

		resultList = tablaGeneralService.buscarGrupoTablaGeneral(idEmpresa);
		return resultList;
	}

	@RequestMapping(value = "/obtenerCodigosTablaGeneral", method = RequestMethod.POST)
    public @ResponseBody  ResponseEntity<List<TablaGeneralResultViewModel>>  obtenerCodigosTablaGeneral(@RequestBody TablaGeneralFilterViewModel dto) {

        List<TablaGeneralResultViewModel> result = new ArrayList<>();
        result = tablaGeneralService.obtenerCodigosTablaGeneral(dto);
		if(result == null)
			result = new ArrayList<>();

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

	@RequestMapping(value = "/obtenerTablaGeneralDetalle", method = RequestMethod.POST)
	public @ResponseBody
	TablaGeneralResultViewModel obtenerTablaGeneralDetalle(@RequestBody Long idTablaGeneral){
		TablaGeneralResultViewModel result = new TablaGeneralResultViewModel();
		result = tablaGeneralService.findOne(idTablaGeneral);
		if(result == null)
			result = new TablaGeneralResultViewModel();
		return result;
	}

	@RequestMapping(value = "/registrarTablaGeneral", method = RequestMethod.POST)
    public @ResponseBody
	NotificacionViewModel registrarTablaGeneral(@RequestBody TablaGeneralResultViewModel tablaGeneralDto) {
    	NotificacionViewModel dto = new NotificacionViewModel();
    	
    	if(tablaGeneralDto.getIdTablaGeneral() == null){
    		dto = tablaGeneralService.create(tablaGeneralDto);
    	}else{
    		dto = tablaGeneralService.update(tablaGeneralDto);
    	}
    	
		return dto;
    }
	
	@RequestMapping(value = "/eliminarTablaGeneral", method = RequestMethod.POST)
	public @ResponseBody	NotificacionViewModel eliminarProyecto(@RequestBody Long idTablaGeneral) {
	 NotificacionViewModel notificacionViewModel = new NotificacionViewModel();	
	 notificacionViewModel = tablaGeneralService.delete(idTablaGeneral);
		return notificacionViewModel;
	}

}
