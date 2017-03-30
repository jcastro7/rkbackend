package pe.com.tss.runakuna.service;

import java.util.List;

import pe.com.tss.runakuna.view.model.HorarioDiaViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;
import pe.com.tss.runakuna.view.model.TablaGeneralFilterViewModel;
import pe.com.tss.runakuna.view.model.TablaGeneralResultViewModel;
import pe.com.tss.runakuna.view.model.TablaGeneralViewModel;

public interface TablaGeneralService {

	List<TablaGeneralViewModel> obtenerTiposDocumento();
	List<TablaGeneralViewModel> obtenerEstadosCivil();
	List<TablaGeneralViewModel> obtenerGruposSanguineo();
	List<TablaGeneralViewModel> obtenerGeneros();
	List<TablaGeneralViewModel> obtenerTiposDomicilio();
	List<TablaGeneralViewModel> obtenerRegimenHorario();
	List<TablaGeneralViewModel> obtenerTipoHorario();
	List<TablaGeneralViewModel> obtenerContratoTrabajo();
	List<TablaGeneralViewModel> obtenerTipoTrabajo();
	List<TablaGeneralViewModel> obtenerRegimenLaboral();
	List<TablaGeneralViewModel> obtenerNivelEducacion();
	List<HorarioDiaViewModel> obtenerDias();
	List<TablaGeneralViewModel> obtenerTipoEquipo();
	List<TablaGeneralViewModel> obtenerEmpleadoEstado();
	List<TablaGeneralViewModel> obtenerEstadoTipoEquipo();
	List<TablaGeneralViewModel> obtenerRelacionContactoEmergencia();
	List<TablaGeneralViewModel> obtenerMotivosPermiso();
	List<TablaGeneralViewModel> obtenerPermisoEstado();
	List<TablaGeneralViewModel> obtenerRelacionDependiente();
	List<TablaGeneralViewModel> obtenerVacacionesEstados();
	List<TablaGeneralViewModel> obtenerEstados();
	List<TablaGeneralViewModel> obtenerMotivoRenuncia();

	List<TablaGeneralViewModel> obtenerEntidadFinanciera();
	List<TablaGeneralViewModel> obtenerAFP() ;
	List<TablaGeneralViewModel> obtenerEPS();
	List<TablaGeneralViewModel> obtenerLicenciaEstados();
	List<TablaGeneralViewModel> obtenerTipoReporte();
	List<TablaGeneralViewModel> obtenerTipoNotificacion();
	List<TablaGeneralViewModel> obtenerTipoAlerta();
	List<TablaGeneralViewModel> obtenerTipoMarcacion();
	List<TablaGeneralViewModel> buscarGrupoTablaGeneral();
	List<TablaGeneralResultViewModel> obtenerCodigosTablaGeneral(TablaGeneralFilterViewModel dto);
	TablaGeneralResultViewModel findOne(Long idTablaGeneral);
	NotificacionViewModel update(TablaGeneralResultViewModel tablaGeneralDto);

}
