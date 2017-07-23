package pe.com.tss.runakuna.service;

import java.util.List;

import pe.com.tss.runakuna.view.model.EmpleadoPlanillaResultViewModel;
import pe.com.tss.runakuna.view.model.EmpleadoViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;
import pe.com.tss.runakuna.view.model.VacacionEmpleadoPlanillaViewModel;
import pe.com.tss.runakuna.view.model.VacacionEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.VacacionFilterViewModel;
import pe.com.tss.runakuna.view.model.VacacionResultViewModel;
import pe.com.tss.runakuna.view.model.VacacionesEmpleadoFilterViewModel;
import pe.com.tss.runakuna.view.model.VacacionesEmpleadoResultViewModel;

public interface VacacionEmpleadoService extends MaintenanceService<VacacionesEmpleadoFilterViewModel, VacacionesEmpleadoResultViewModel, VacacionEmpleadoViewModel, NotificacionViewModel, Long> {
	
	VacacionEmpleadoViewModel obtenerDiasDisponibles(EmpleadoViewModel empleadoDto);

	NotificacionViewModel registrarVacaciones(VacacionEmpleadoViewModel vacacionEmpleadoDto);
	
	NotificacionViewModel actualizarVacaciones(VacacionEmpleadoViewModel vacacionEmpleadoDto);

	VacacionEmpleadoViewModel obtenerPeriodo(EmpleadoViewModel empleado);

	NotificacionViewModel actualizarVacacionEmpleado(VacacionEmpleadoViewModel vacacionEmpleadoDto);

	NotificacionViewModel enviarVacacionEmpleado(VacacionEmpleadoViewModel vacacionEmpleadoDto);

	NotificacionViewModel devolverVacacionEmpleado(VacacionEmpleadoViewModel vacacionEmpleadoDto);

	NotificacionViewModel aprobarVacacionEmpleado(VacacionEmpleadoViewModel vacacionEmpleadoDto);

	NotificacionViewModel rechazarVacacionEmpleado(VacacionEmpleadoViewModel vacacionEmpleadoDto);

	NotificacionViewModel regularizarVacacion(VacacionEmpleadoViewModel vacacionEmpleadoDto);
	
	NotificacionViewModel comprarVacacion(VacacionEmpleadoViewModel vacacionEmpleadoDto);
	
	NotificacionViewModel incluirVacacionAPlanilla(Long id);
	
	List<VacacionResultViewModel> obtenerBusquedaEmpleadoPlanilla(VacacionFilterViewModel filterViewModel);
	
	NotificacionViewModel registrarVacacionesEnPanilla(VacacionEmpleadoPlanillaViewModel vacacionEmpleadoPlanilla);
	
	Integer obtenerDiasVacacionesPendientes(Long idEmpleado);

}
