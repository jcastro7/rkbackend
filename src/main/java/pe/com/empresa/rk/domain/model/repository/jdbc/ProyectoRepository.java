package pe.com.empresa.rk.domain.model.repository.jdbc;

import java.util.List;

import pe.com.empresa.rk.view.model.ProyectoFilterViewModel;
import pe.com.empresa.rk.view.model.ProyectoResultViewModel;
import pe.com.empresa.rk.view.model.QuickFilterViewModel;

public interface ProyectoRepository {

	List<ProyectoResultViewModel> obtenerProyectos(ProyectoFilterViewModel dto);
	List<ProyectoResultViewModel> busquedaRapidaProyectos(QuickFilterViewModel dto);

	
}
