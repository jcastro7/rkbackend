package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.List;

import pe.com.tss.runakuna.view.model.CompensacionFilterViewModel;
import pe.com.tss.runakuna.view.model.CompensacionResultViewModel;
import pe.com.tss.runakuna.view.model.CompensacionViewModel;
import pe.com.tss.runakuna.view.model.QuickFilterViewModel;

public interface CompensacionRepository {

	List<CompensacionResultViewModel> SearchByFilter(CompensacionFilterViewModel compensacionFilter);
	
	CompensacionViewModel findById(Long idEmpleadoCompensacion);

	List<CompensacionResultViewModel> busquedaRapidaCompensaciones(QuickFilterViewModel quickFilter);
}
