package pe.com.empresa.rk.domain.model.repository.jdbc;

import java.util.List;

import pe.com.empresa.rk.view.model.EmpleadoViewModel;
import pe.com.empresa.rk.view.model.PeriodoEmpleadoFilterViewModel;
import pe.com.empresa.rk.view.model.PeriodoEmpleadoResultViewModel;
import pe.com.empresa.rk.view.model.PeriodoEmpleadoViewModel;

public interface PeriodoEmpleadoRepository {
	
	PeriodoEmpleadoViewModel obtenerPeriodoEmpleadoActual(EmpleadoViewModel empleado);

	List<PeriodoEmpleadoResultViewModel> busquedaPeriodoEmpleado(PeriodoEmpleadoFilterViewModel busquedaPermisoEmpleadoDto);
}
