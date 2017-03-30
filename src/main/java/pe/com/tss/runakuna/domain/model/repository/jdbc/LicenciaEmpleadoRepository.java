package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.List;

import pe.com.tss.runakuna.view.model.LicenciaEmpleadoFilterViewModel;
import pe.com.tss.runakuna.view.model.LicenciaEmpleadoResultViewModel;
import pe.com.tss.runakuna.view.model.LicenciaEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.LicenciaViewModel;
import pe.com.tss.runakuna.view.model.PeriodoEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.QuickFilterViewModel;
import pe.com.tss.runakuna.view.model.TipoLicenciaViewModel;

public interface LicenciaEmpleadoRepository {

	List<LicenciaEmpleadoResultViewModel> obtenerLicencias(LicenciaEmpleadoFilterViewModel busquedaLicenciaEmpleadoDto);

	List<TipoLicenciaViewModel> getTipoLicencias();

	List<LicenciaEmpleadoResultViewModel> busquedaRapidaLicencias(QuickFilterViewModel quickFilter);
	
	List<LicenciaViewModel> verLicencias(PeriodoEmpleadoViewModel periodoEmpleado);
	
}
