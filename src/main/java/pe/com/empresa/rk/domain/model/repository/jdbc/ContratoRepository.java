package pe.com.empresa.rk.domain.model.repository.jdbc;

import java.util.List;

import pe.com.empresa.rk.view.model.ContratoResultViewModel;
import pe.com.empresa.rk.view.model.ContratoViewModel;

public interface ContratoRepository {

	List<ContratoViewModel> obtenerContratosPorEmpleado(Long idEmpleado);
	List<ContratoResultViewModel> busquedaContratosPorEmpleado(Long idEmpleado);
}
