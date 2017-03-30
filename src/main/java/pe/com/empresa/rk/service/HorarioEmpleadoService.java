package pe.com.empresa.rk.service;

import java.util.List;

import pe.com.empresa.rk.view.model.HorarioEmpleadoFilterViewModel;
import pe.com.empresa.rk.view.model.HorarioEmpleadoResultViewModel;
import pe.com.empresa.rk.view.model.HorarioEmpleadoViewModel;
import pe.com.empresa.rk.view.model.NotificacionViewModel;

public interface HorarioEmpleadoService extends MaintenanceService<HorarioEmpleadoFilterViewModel, HorarioEmpleadoResultViewModel, HorarioEmpleadoViewModel, NotificacionViewModel, Long> {

	List<HorarioEmpleadoViewModel> verHorariosEmpleado(Long idEmpleado);
	List<HorarioEmpleadoResultViewModel> obtenerBusquedaHorariosEmpleado(Long idEmpleado);
	
}
