package pe.com.empresa.rk.domain.model.repository.jdbc;

import java.util.List;

import pe.com.empresa.rk.view.model.DepartamentoViewModel;

public interface DepartamentoRepository {

	List<DepartamentoViewModel> findDepartamentos();
}
