package pe.com.empresa.rk.domain.model.repository.jdbc;

import java.util.List;

import pe.com.empresa.rk.view.model.QuickFilterViewModel;
import pe.com.empresa.rk.view.model.VacacionEmpleadoViewModel;
import pe.com.empresa.rk.view.model.VacacionResultViewModel;
import pe.com.empresa.rk.view.model.EmpleadoViewModel;
import pe.com.empresa.rk.view.model.VacacionFilterViewModel;

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
