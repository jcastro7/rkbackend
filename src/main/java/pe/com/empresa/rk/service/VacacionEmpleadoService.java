package pe.com.empresa.rk.service;

import pe.com.empresa.rk.view.model.EmpleadoViewModel;
import pe.com.empresa.rk.view.model.NotificacionViewModel;
import pe.com.empresa.rk.view.model.VacacionEmpleadoViewModel;
import pe.com.empresa.rk.view.model.VacacionesEmpleadoFilterViewModel;
import pe.com.empresa.rk.view.model.VacacionesEmpleadoResultViewModel;

public interface VacacionEmpleadoService extends MaintenanceService<VacacionesEmpleadoFilterViewModel, VacacionesEmpleadoResultViewModel, VacacionEmpleadoViewModel, NotificacionViewModel, Long> {
	
	VacacionEmpleadoViewModel obtenerDiasDisponibles(EmpleadoViewModel empleadoDto);

	NotificacionViewModel registrarVacaciones(VacacionEmpleadoViewModel vacacionEmpleadoDto);

	VacacionEmpleadoViewModel obtenerPeriodo(EmpleadoViewModel empleado);

	NotificacionViewModel actualizarVacacionEmpleado(VacacionEmpleadoViewModel vacacionEmpleadoDto);

	NotificacionViewModel enviarVacacionEmpleado(VacacionEmpleadoViewModel vacacionEmpleadoDto);

	NotificacionViewModel eliminarVacacionEmpleado(Long idVacacion);

	NotificacionViewModel devolverVacacionEmpleado(VacacionEmpleadoViewModel vacacionEmpleadoDto);

	NotificacionViewModel aprobarVacacionEmpleado(VacacionEmpleadoViewModel vacacionEmpleadoDto);

	NotificacionViewModel rechazarVacacionEmpleado(VacacionEmpleadoViewModel vacacionEmpleadoDto);

	NotificacionViewModel regularizarVacacion(VacacionEmpleadoViewModel vacacionEmpleadoDto);

}
