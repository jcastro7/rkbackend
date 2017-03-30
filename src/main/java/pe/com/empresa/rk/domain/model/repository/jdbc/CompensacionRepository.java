package pe.com.empresa.rk.domain.model.repository.jdbc;

import java.util.List;

import pe.com.empresa.rk.view.model.CompensacionFilterViewModel;
import pe.com.empresa.rk.view.model.CompensacionViewModel;
import pe.com.empresa.rk.view.model.QuickFilterViewModel;
import pe.com.empresa.rk.view.model.CompensacionResultViewModel;

public interface CompensacionRepository {

	List<CompensacionResultViewModel> SearchByFilter(CompensacionFilterViewModel compensacionFilter);
	
	CompensacionViewModel findById(Long idEmpleadoCompensacion);

	List<CompensacionResultViewModel> busquedaRapidaCompensaciones(QuickFilterViewModel quickFilter);
}
