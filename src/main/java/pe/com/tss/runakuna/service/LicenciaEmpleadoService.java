package pe.com.tss.runakuna.service;

import java.util.List;

import pe.com.tss.runakuna.view.model.LicenciaEmpleadoFilterViewModel;
import pe.com.tss.runakuna.view.model.LicenciaEmpleadoResultViewModel;
import pe.com.tss.runakuna.view.model.LicenciaEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.LicenciaViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;
import pe.com.tss.runakuna.view.model.PeriodoEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.QuickFilterViewModel;
import pe.com.tss.runakuna.view.model.TipoLicenciaViewModel;


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
