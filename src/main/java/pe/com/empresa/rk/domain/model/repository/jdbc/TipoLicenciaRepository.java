package pe.com.empresa.rk.domain.model.repository.jdbc;

import java.util.List;

import pe.com.empresa.rk.view.model.LicenciaViewModel;
import pe.com.empresa.rk.view.model.TipoLicenciaFilterViewModel;
import pe.com.empresa.rk.view.model.TipoLicenciaResultViewModel;

public interface TipoLicenciaRepository {

	 List<TipoLicenciaResultViewModel> obtenerTipoLicencias(TipoLicenciaFilterViewModel filterViewModel);

	List<LicenciaViewModel> obtenerLicenciaByTipoLicencia(Long idTipoLicencia);

	void eliminarTipoLicencia(Long idTipoLicencia); 

	
	
}
