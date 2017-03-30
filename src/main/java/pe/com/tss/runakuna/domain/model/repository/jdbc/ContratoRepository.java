package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.List;

import pe.com.tss.runakuna.view.model.ContratoResultViewModel;
import pe.com.tss.runakuna.view.model.ContratoViewModel;

public interface ContratoRepository {

	List<ContratoViewModel> obtenerContratosPorEmpleado(Long idEmpleado);
	List<ContratoResultViewModel> busquedaContratosPorEmpleado(Long idEmpleado);
}
