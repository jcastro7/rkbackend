package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.List;

import pe.com.tss.runakuna.view.model.ConfiguracionSistemaFilterViewModel;
import pe.com.tss.runakuna.view.model.ConfiguracionSistemaResultViewModel;

public interface ConfiguracionSistemaRepository {

	List<ConfiguracionSistemaResultViewModel> obtenerConfiguracionesSistema(
			ConfiguracionSistemaFilterViewModel filterViewModel);

	
}
