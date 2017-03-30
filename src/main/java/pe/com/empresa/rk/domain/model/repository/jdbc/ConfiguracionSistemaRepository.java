package pe.com.empresa.rk.domain.model.repository.jdbc;

import java.util.List;

import pe.com.empresa.rk.view.model.ConfiguracionSistemaFilterViewModel;
import pe.com.empresa.rk.view.model.ConfiguracionSistemaResultViewModel;

public interface ConfiguracionSistemaRepository {

	List<ConfiguracionSistemaResultViewModel> obtenerConfiguracionesSistema(
			ConfiguracionSistemaFilterViewModel filterViewModel);

	
}
