package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.List;

import pe.com.tss.runakuna.view.model.EmpleadoViewModel;
import pe.com.tss.runakuna.view.model.QuickFilterViewModel;
import pe.com.tss.runakuna.view.model.VacacionEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.VacacionFilterViewModel;
import pe.com.tss.runakuna.view.model.VacacionResultViewModel;

public interface VacacionEmpleadoRepository {

	VacacionEmpleadoViewModel obtenerDiasDisponibles(EmpleadoViewModel empleadoDto);

	VacacionEmpleadoViewModel obtenerPeriodo(EmpleadoViewModel empleado);

	Long obtenerCantidadVacaciones(Long idPeriodoEmpleado);

	VacacionEmpleadoViewModel obtenerPrimeraVacacion(Long idPeriodoEmpleado);

	VacacionEmpleadoViewModel obtenerUltimaVacacion(Long idPeriodoEmpleado);

	List<VacacionEmpleadoViewModel> allListVacacion(Long idPeriodoEmpleado);
	
	
	List<VacacionResultViewModel> busquedaVacacionesEmpleado(
			VacacionFilterViewModel filterViewModel);

	List<VacacionResultViewModel> busquedaRapidaVacacionesEmpleado(QuickFilterViewModel quickFilter);

}
