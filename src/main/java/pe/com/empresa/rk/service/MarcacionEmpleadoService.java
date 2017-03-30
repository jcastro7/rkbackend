package pe.com.empresa.rk.service;

import java.util.List;

import pe.com.empresa.rk.view.model.EmpleadoViewModel;
import pe.com.empresa.rk.view.model.MarcacionViewModel;
import pe.com.empresa.rk.view.model.NotificacionViewModel;
import pe.com.empresa.rk.view.model.QuickFilterViewModel;
import pe.com.empresa.rk.view.model.MarcacionFilterViewModel;
import pe.com.empresa.rk.view.model.SolicitudCambioMarcacionViewModel;

public interface MarcacionEmpleadoService extends QuickSearchService<MarcacionViewModel, QuickFilterViewModel> {

	MarcacionViewModel obtenerMarcacionPorEmpleadoyFechaActual(EmpleadoViewModel empleado);
	
	NotificacionViewModel registrarSolicitudCambioMarcacion(SolicitudCambioMarcacionViewModel solicitudCambioMarcacion);
	
	SolicitudCambioMarcacionViewModel obtenerSolicitudCambio(Long idMarcacion) ;
	
	List<MarcacionViewModel> getMarcacionesByFiltro(MarcacionFilterViewModel filter);
	
}
