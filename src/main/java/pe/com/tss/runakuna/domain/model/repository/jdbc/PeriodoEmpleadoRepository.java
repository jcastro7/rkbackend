package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.List;

import pe.com.tss.runakuna.view.model.EmpleadoViewModel;
import pe.com.tss.runakuna.view.model.PeriodoEmpleadoFilterViewModel;
import pe.com.tss.runakuna.view.model.PeriodoEmpleadoResultViewModel;
import pe.com.tss.runakuna.view.model.PeriodoEmpleadoViewModel;

public interface PeriodoEmpleadoRepository {
	
	PeriodoEmpleadoViewModel obtenerPeriodoEmpleadoActual(EmpleadoViewModel empleado);

	List<PeriodoEmpleadoResultViewModel> busquedaPeriodoEmpleado(PeriodoEmpleadoFilterViewModel busquedaPermisoEmpleadoDto);

	Long obtenerIdUltimoPeriodo(Long idEmpleado);
	
	List<PeriodoEmpleadoResultViewModel> obtenerPeriodosConVacacionesDisponiblesMayor15Dias(Long idEmpleado);
	
	List<PeriodoEmpleadoResultViewModel> obtenerPeriodosConVacacionesDisponibles(Long idEmpleado);

	List<PeriodoEmpleadoResultViewModel> busquedaPeriodoById(Long idPeriodoEmpleado);
}
