package pe.com.tss.runakuna.service;

import java.util.List;

import pe.com.tss.runakuna.view.model.EmpleadoFilterViewModel;
import pe.com.tss.runakuna.view.model.EmpleadoQuickFilterViewModel;
import pe.com.tss.runakuna.view.model.EmpleadoResultViewModel;
import pe.com.tss.runakuna.view.model.HorasExtraEmpleadoFilterViewModel;
import pe.com.tss.runakuna.view.model.MarcacionFilterViewModel;
import pe.com.tss.runakuna.view.model.MarcacionResultViewModel;
import pe.com.tss.runakuna.view.model.PermisoEmpleadoFilterViewModel;
import pe.com.tss.runakuna.view.model.PermisoEmpleadoResultViewModel;
import pe.com.tss.runakuna.view.model.VacacionesEmpleadoFilterViewModel;
import pe.com.tss.runakuna.view.model.DependienteViewModel;
import pe.com.tss.runakuna.view.model.DocumentoEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.EducacionViewModel;
import pe.com.tss.runakuna.view.model.EmpleadoCabeceraViewModel;
import pe.com.tss.runakuna.view.model.EmpleadoDirectorioResultViewModel;
import pe.com.tss.runakuna.view.model.EmpleadoViewModel;
import pe.com.tss.runakuna.view.model.EquipoEntregadoViewModel;
import pe.com.tss.runakuna.view.model.ExperienciaLaboralViewModel;
import pe.com.tss.runakuna.view.model.HistoriaLaboralViewModel;
import pe.com.tss.runakuna.view.model.HistorialLaboralViewModel;
import pe.com.tss.runakuna.view.model.HorarioEmpleadoDiaViewModel;
import pe.com.tss.runakuna.view.model.HorarioEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.HorarioFilterViewModel;
import pe.com.tss.runakuna.view.model.HorarioResultViewModel;
import pe.com.tss.runakuna.view.model.HorarioViewModel;
import pe.com.tss.runakuna.view.model.HorasExtraViewModel;
import pe.com.tss.runakuna.view.model.LicenciaViewModel;
import pe.com.tss.runakuna.view.model.MarcacionViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;
import pe.com.tss.runakuna.view.model.PeriodoEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.PermisoEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.QuickFilterViewModel;
import pe.com.tss.runakuna.view.model.VacacionViewModel;
import pe.com.tss.runakuna.view.model.VacacionEmpleadoViewModel;

public interface EmpleadoService extends MaintenanceService<EmpleadoFilterViewModel,EmpleadoResultViewModel,EmpleadoViewModel,NotificacionViewModel, Long>,
QuickSearchService<EmpleadoResultViewModel, QuickFilterViewModel>{

	NotificacionViewModel actualizarDatosPersonales(EmpleadoViewModel empleado);
	List<HistoriaLaboralViewModel> obtenerHistoriaLaboral(Long idEmpleado);
	List<HistoriaLaboralViewModel> obtenerIdHistoriaLaboral(Long idHistorialLaboral);
	List<EmpleadoResultViewModel> busquedaEmpleado(EmpleadoFilterViewModel busquedaEmpleadoDto);
	List<MarcacionResultViewModel> busquedaMarcacionesEmpleado(MarcacionFilterViewModel busquedaMarcacionDto);
	List<DocumentoEmpleadoViewModel> verDocumentos(Long idEmpleado);
	List<EducacionViewModel> verEducacion(Long idEmpleado);
	List<ExperienciaLaboralViewModel> verExperienciaLaboral(Long idEmpleado);
	List<EquipoEntregadoViewModel> verEquipoEntregado(Long idEmpleado);
	List<DependienteViewModel> verDependiente(Long idEmpleado);
	List<LicenciaViewModel> verLicencia(PeriodoEmpleadoViewModel periodoEmpleado);
	List<EmpleadoViewModel> procesarMasivamenteEmpleados(List<EmpleadoViewModel> dtos);
	Long processDataUpdateEmpleado(EmpleadoViewModel dto)  throws Exception;
	HorarioEmpleadoViewModel verHorarioEmpleado(Long idEmpleado);
	List<HorarioEmpleadoDiaViewModel> obtenerHorarioEmpleadoDiasPorHorarioEmpleado(HorarioEmpleadoViewModel horarioEmpleadoDto);
	List<PeriodoEmpleadoViewModel> verPeriodoEmpleado(Long idEmpleado);
	
	List<VacacionEmpleadoViewModel> verVacacion(PeriodoEmpleadoViewModel periodoEmpleado);
    List<PermisoEmpleadoViewModel> obtenerCodigoPermiso(String codigo);
	List<VacacionEmpleadoViewModel> busquedaVacacionesEmpleado(VacacionesEmpleadoFilterViewModel busquedaVacacionesEmpleadoDto);
	List<MarcacionViewModel> verMarcacion(Long idEmpleado);
	List<HorasExtraViewModel> busquedaHorasExtrasEmpleado(HorasExtraEmpleadoFilterViewModel busquedaHorasExtraEmpleadoDto);
	HorasExtraViewModel informacionAdicionalHorasExtras(EmpleadoViewModel empleado);
	NotificacionViewModel registrarHorasExtra(HorasExtraViewModel horasExtraDto);
	List<EquipoEntregadoViewModel> obtenerEquiposPendientesDevolucion(Long idEmpleado);
	NotificacionViewModel countEquiposPendientesDevolucion(EmpleadoViewModel empleadoDto);
	List<HorarioEmpleadoViewModel> verHorariosEmpleado(Long idEmpleado);
	NotificacionViewModel registrarDarBajaEmpleado(EmpleadoViewModel empleado);
	NotificacionViewModel rechazarHorasExtra(HorasExtraViewModel horasExtraDto);
	NotificacionViewModel aprobarHorasExtra(HorasExtraViewModel horasExtraDto);
	List<EmpleadoViewModel> searchExport(EmpleadoFilterViewModel filterViewModel);
	
	List<EmpleadoViewModel> searchExportBusquedaRapida(QuickFilterViewModel quickFilter);
	
	EmpleadoViewModel findOneAccessJwtToken(Long idEmpleado);
	HistorialLaboralViewModel obtenerHistorialLaboralById(Long idHistorialLaboral);

	NotificacionViewModel registrarFotoEmpleado(EmpleadoViewModel empleado);
	
	EmpleadoViewModel obtenerEmpleadoEsPersonalConfianza(Long idEmpleado);
	
	EmpleadoCabeceraViewModel obtenerEmpleadoCabecera(Long id);
	
	EmpleadoViewModel obtenerEmpleadoPorCodigo(EmpleadoViewModel empleadoVM);
	List<EmpleadoDirectorioResultViewModel> busquedaDirectorioEmpleado(QuickFilterViewModel busquedaEmpleadoDto);
}
