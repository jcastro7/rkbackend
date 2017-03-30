package pe.com.empresa.rk.service;

import java.util.List;

import pe.com.empresa.rk.view.model.EmpleadoViewModel;
import pe.com.empresa.rk.view.model.NotificacionEmpleadoViewModel;
import pe.com.empresa.rk.view.model.PeriodoEmpleadoFilterViewModel;
import pe.com.empresa.rk.view.model.PeriodoEmpleadoResultViewModel;

public interface PeriodoEmpleadoService {
	NotificacionEmpleadoViewModel registrarPeriodoEmpleado(EmpleadoViewModel empleadoDto);

	List<PeriodoEmpleadoResultViewModel> busquedaPeriodoEmpleado(
	PeriodoEmpleadoFilterViewModel busquedaPermisoEmpleadoDto);

}
