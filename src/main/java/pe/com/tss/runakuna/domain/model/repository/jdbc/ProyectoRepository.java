package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.List;

import pe.com.tss.runakuna.view.model.ProyectoFilterViewModel;
import pe.com.tss.runakuna.view.model.ProyectoResultViewModel;
import pe.com.tss.runakuna.view.model.QuickFilterViewModel;

public interface ProyectoRepository {

	List<ProyectoResultViewModel> obtenerProyectos(ProyectoFilterViewModel dto);
	List<ProyectoResultViewModel> busquedaRapidaProyectos(QuickFilterViewModel dto);

	
}
