package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.List;

import pe.com.tss.runakuna.view.model.LicenciaViewModel;
import pe.com.tss.runakuna.view.model.TipoLicenciaFilterViewModel;
import pe.com.tss.runakuna.view.model.TipoLicenciaResultViewModel;

public interface TipoLicenciaRepository {

	 List<TipoLicenciaResultViewModel> obtenerTipoLicencias(TipoLicenciaFilterViewModel filterViewModel);

	List<LicenciaViewModel> obtenerLicenciaByTipoLicencia(Long idTipoLicencia);

	void eliminarTipoLicencia(Long idTipoLicencia); 

	
	
}
