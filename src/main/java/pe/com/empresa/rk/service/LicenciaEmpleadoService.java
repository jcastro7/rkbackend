package pe.com.empresa.rk.service;

import java.util.List;

import pe.com.empresa.rk.view.model.LicenciaEmpleadoFilterViewModel;
import pe.com.empresa.rk.view.model.LicenciaEmpleadoResultViewModel;
import pe.com.empresa.rk.view.model.LicenciaEmpleadoViewModel;
import pe.com.empresa.rk.view.model.LicenciaViewModel;
import pe.com.empresa.rk.view.model.NotificacionViewModel;
import pe.com.empresa.rk.view.model.PeriodoEmpleadoViewModel;
import pe.com.empresa.rk.view.model.QuickFilterViewModel;
import pe.com.empresa.rk.view.model.TipoLicenciaViewModel;


public interface LicenciaEmpleadoService extends MaintenanceService<LicenciaEmpleadoFilterViewModel, LicenciaEmpleadoResultViewModel,LicenciaEmpleadoViewModel, NotificacionViewModel, Long>,
QuickSearchService<LicenciaEmpleadoResultViewModel, QuickFilterViewModel>{

	//NotificacionViewModel registrarLicenciaEmpleado(LicenciaEmpleadoViewModel licenciaEmpleadoDto);
	//NotificacionViewModel actualizarLicenciaEmpleado(LicenciaEmpleadoViewModel licenciaEmpleadoDto);
	NotificacionViewModel aprobarLicenciaEmpleado(LicenciaEmpleadoViewModel licenciaEmpleadoDto);
	NotificacionViewModel validarLicenciaEmpleado(LicenciaEmpleadoViewModel licenciaEmpleadoDto);
	//Long eliminarLicenciaEmpleado(Long idLicencia);
	//List<LicenciaEmpleadoViewModel> obtenerLicencias(LicenciaEmpleadoFilterViewModel busquedaLicenciaEmpleadoDto);
	//LicenciaEmpleadoViewModel obtenerLicencia(Long idLicencia);
	List<TipoLicenciaViewModel> obtenerTipoLicencia();
	
	List<LicenciaViewModel> verLicencias(PeriodoEmpleadoViewModel periodoEmpleado);
	
	NotificacionViewModel actualizarLicencia(LicenciaViewModel licencia);
	
}
