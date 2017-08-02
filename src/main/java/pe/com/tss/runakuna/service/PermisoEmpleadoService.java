package pe.com.tss.runakuna.service;

import java.util.List;

import pe.com.tss.runakuna.view.model.PermisoEmpleadoFilterViewModel;
import pe.com.tss.runakuna.view.model.PermisoEmpleadoResultViewModel;
import pe.com.tss.runakuna.view.model.EmpleadoCompensacionViewModel;
import pe.com.tss.runakuna.view.model.EmpleadoViewModel;
import pe.com.tss.runakuna.view.model.HistoriaLaboralViewModel;
import pe.com.tss.runakuna.view.model.HorarioEmpleadoDiaViewModel;
import pe.com.tss.runakuna.view.model.LicenciaEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;
import pe.com.tss.runakuna.view.model.PeriodoEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.PermisoEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.QuickFilterViewModel;

public interface PermisoEmpleadoService extends MaintenanceService<PermisoEmpleadoFilterViewModel, PermisoEmpleadoResultViewModel, PermisoEmpleadoViewModel,NotificacionViewModel, Long>,
QuickSearchService<PermisoEmpleadoResultViewModel, QuickFilterViewModel>{

	List<PermisoEmpleadoViewModel> verPermisoEmpleado(PeriodoEmpleadoViewModel periodoEmpleado);
	
	NotificacionViewModel registrarPermisoEmpleado(PermisoEmpleadoViewModel permisoEmpleado);
	
	NotificacionViewModel actualizarPermisoEmpleado(PermisoEmpleadoViewModel permisoEmpleado);
	
	NotificacionViewModel guardarPendientePermisoEmpleado(PermisoEmpleadoViewModel permisoEmpleado);
	
	NotificacionViewModel enviarPermisoEmpleado(PermisoEmpleadoViewModel permisoEmpleado);
	
	NotificacionViewModel devolverPermisoEmpleado(PermisoEmpleadoViewModel permisoEmpleado);
	
	NotificacionViewModel aprobarPermisoEmpleado(PermisoEmpleadoViewModel permisoEmpleado);
	
	NotificacionViewModel rechazarPermisoEmpleado(PermisoEmpleadoViewModel permisoEmpleado);
	
	HistoriaLaboralViewModel obtenerHistoriaLaboralActual(EmpleadoViewModel empleadoDto);
	
	List<HistoriaLaboralViewModel> obtenerHistoriasLaboralesActualPorEmpleado(EmpleadoViewModel empleadoDto);
	
	PeriodoEmpleadoViewModel obtenerPeriodoEmpleadoActual(EmpleadoViewModel empleadoDto);
	
	List<PermisoEmpleadoFilterViewModel> autocompleteEmpleado(String search);
	List<PermisoEmpleadoFilterViewModel> autocompleteJefe(String search);
	List<PermisoEmpleadoFilterViewModel> autocompleteEmpleadoConJefe(String search, Long idJefe);

	HistoriaLaboralViewModel obtenerHistoriaLaboralLicencia(LicenciaEmpleadoViewModel licenciaEmpleadoD);
	
	List<HistoriaLaboralViewModel> obtenerHistoriasLaboralesPorEmpleado(Long idEmpleado);

	EmpleadoCompensacionViewModel obtenerHorasPorCompensarPorEmpleado(Long idEmpleado);

	HorarioEmpleadoDiaViewModel obtenerHorarioEmpleadoDia(PermisoEmpleadoViewModel permisoEmpleadoDto);
	
}
