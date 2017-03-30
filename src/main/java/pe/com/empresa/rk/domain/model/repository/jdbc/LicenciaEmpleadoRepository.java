package pe.com.empresa.rk.domain.model.repository.jdbc;

import java.util.List;

import pe.com.empresa.rk.view.model.LicenciaEmpleadoFilterViewModel;
import pe.com.empresa.rk.view.model.LicenciaEmpleadoResultViewModel;
import pe.com.empresa.rk.view.model.LicenciaViewModel;
import pe.com.empresa.rk.view.model.PeriodoEmpleadoViewModel;
import pe.com.empresa.rk.view.model.QuickFilterViewModel;
import pe.com.empresa.rk.view.model.TipoLicenciaViewModel;

public interface LicenciaEmpleadoRepository {

	List<LicenciaEmpleadoResultViewModel> obtenerLicencias(LicenciaEmpleadoFilterViewModel busquedaLicenciaEmpleadoDto);

	List<TipoLicenciaViewModel> getTipoLicencias();

	List<LicenciaEmpleadoResultViewModel> busquedaRapidaLicencias(QuickFilterViewModel quickFilter);
	
	List<LicenciaViewModel> verLicencias(PeriodoEmpleadoViewModel periodoEmpleado);
	
}
