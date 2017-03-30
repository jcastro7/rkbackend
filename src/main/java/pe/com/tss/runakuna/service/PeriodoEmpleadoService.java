package pe.com.tss.runakuna.service;

import java.util.List;

import pe.com.tss.runakuna.view.model.EmpleadoViewModel;
import pe.com.tss.runakuna.view.model.NotificacionEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.PeriodoEmpleadoFilterViewModel;
import pe.com.tss.runakuna.view.model.PeriodoEmpleadoResultViewModel;

public interface PeriodoEmpleadoService {
	NotificacionEmpleadoViewModel registrarPeriodoEmpleado(EmpleadoViewModel empleadoDto);

	List<PeriodoEmpleadoResultViewModel> busquedaPeriodoEmpleado(
	PeriodoEmpleadoFilterViewModel busquedaPermisoEmpleadoDto);

}
