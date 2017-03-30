package pe.com.empresa.rk.service;

import java.util.List;

import pe.com.empresa.rk.view.model.PermisoEmpleadoFilterViewModel;
import pe.com.empresa.rk.view.model.PermisoEmpleadoResultViewModel;
import pe.com.empresa.rk.view.model.EmpleadoViewModel;
import pe.com.empresa.rk.view.model.HistoriaLaboralViewModel;
import pe.com.empresa.rk.view.model.LicenciaEmpleadoViewModel;
import pe.com.empresa.rk.view.model.NotificacionViewModel;
import pe.com.empresa.rk.view.model.PeriodoEmpleadoViewModel;
import pe.com.empresa.rk.view.model.PermisoEmpleadoViewModel;
import pe.com.empresa.rk.view.model.QuickFilterViewModel;

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
