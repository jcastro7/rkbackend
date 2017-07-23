package pe.com.tss.runakuna.service;

import java.util.List;

import pe.com.tss.runakuna.view.model.HorarioEmpleadoFilterViewModel;
import pe.com.tss.runakuna.view.model.HorarioEmpleadoResultViewModel;
import pe.com.tss.runakuna.view.model.HorarioEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;

public interface HorarioEmpleadoService extends MaintenanceService<HorarioEmpleadoFilterViewModel, HorarioEmpleadoResultViewModel, HorarioEmpleadoViewModel, NotificacionViewModel, Long> {

	List<HorarioEmpleadoViewModel> verHorariosEmpleado(Long idEmpleado);
	List<HorarioEmpleadoResultViewModel> obtenerBusquedaHorariosEmpleado(Long idEmpleado);
	
}
