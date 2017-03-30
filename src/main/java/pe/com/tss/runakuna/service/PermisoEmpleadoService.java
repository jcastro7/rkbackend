package pe.com.tss.runakuna.service;

import java.util.List;

import pe.com.tss.runakuna.view.model.PermisoEmpleadoFilterViewModel;
import pe.com.tss.runakuna.view.model.PermisoEmpleadoResultViewModel;
import pe.com.tss.runakuna.view.model.EmpleadoViewModel;
import pe.com.tss.runakuna.view.model.HistoriaLaboralViewModel;
import pe.com.tss.runakuna.view.model.LicenciaEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;
import pe.com.tss.runakuna.view.model.PeriodoEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.PermisoEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.QuickFilterViewModel;

public interface PermisoEmpleadoService extends MaintenanceService<PermisoEmpleadoFilterViewModel, PermisoEmpleadoResultViewModel, PermisoEmpleadoViewModel,NotificacionViewModel, Long>,
QuickSearchService<PermisoEmpleadoResultViewModel, QuickFilterViewModel>{

	NotificacionViewModel registrarPermisoEmpleado(PermisoEmpleadoViewModel permisoEmpleado);
	
	NotificacionViewModel actualizarPermisoEmpleado(PermisoEmpleadoViewModel permisoEmpleado);
	
	NotificacionViewModel enviarPermisoEmpleado(PermisoEmpleadoViewModel permisoEmpleado);
	
	NotificacionViewModel devolverPermisoEmpleado(PermisoEmpleadoViewModel permisoEmpleado);
	
	NotificacionViewModel aprobarPermisoEmpleado(PermisoEmpleadoViewModel permisoEmpleado);
	
	NotificacionViewModel rechazarPermisoEmpleado(PermisoEmpleadoViewModel permisoEmpleado);
	
	HistoriaLaboralViewModel obtenerHistoriaLaboralActual(EmpleadoViewModel empleadoDto);
	
	List<HistoriaLaboralViewModel> obtenerHistoriasLaboralesActualPorEmpleado(EmpleadoViewModel empleadoDto);
	
	PeriodoEmpleadoViewModel obtenerPeriodoEmpleadoActual(EmpleadoViewModel empleadoDto);
	
	List<PermisoEmpleadoFilterViewModel> autocompleteEmpleado(String search);

	HistoriaLaboralViewModel obtenerHistoriaLaboralLicencia(LicenciaEmpleadoViewModel licenciaEmpleadoD);
	
	List<HistoriaLaboralViewModel> obtenerHistoriasLaboralesPorEmpleado(Long idEmpleado);
	
}
