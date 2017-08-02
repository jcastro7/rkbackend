package pe.com.tss.runakuna.service;

import java.util.List;

import pe.com.tss.runakuna.view.model.EmpleadoViewModel;
import pe.com.tss.runakuna.view.model.MarcacionFilterViewModel;
import pe.com.tss.runakuna.view.model.MarcacionResultViewModel;
import pe.com.tss.runakuna.view.model.MarcacionViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;
import pe.com.tss.runakuna.view.model.QuickFilterViewModel;
import pe.com.tss.runakuna.view.model.SolicitudCambioMarcacionViewModel;

public interface MarcacionEmpleadoService extends QuickSearchService<MarcacionResultViewModel, QuickFilterViewModel> {

	MarcacionViewModel obtenerMarcacionPorEmpleadoyFechaActual(EmpleadoViewModel empleado);
	
	NotificacionViewModel registrarSolicitudCambioMarcacion(SolicitudCambioMarcacionViewModel solicitudCambioMarcacion);
	
	NotificacionViewModel aprobarSolicitudCambioMarcacion(SolicitudCambioMarcacionViewModel solicitudCambioMarcacion);
	
	NotificacionViewModel rechazarSolicitudCambioMarcacion(SolicitudCambioMarcacionViewModel solicitudCambioMarcacion);
	
	NotificacionViewModel cancelarSolicitudCambioMarcacion(SolicitudCambioMarcacionViewModel solicitudCambioMarcacion);
	
	SolicitudCambioMarcacionViewModel obtenerSolicitudCambio(Long idMarcacion) ;
	
	List<MarcacionViewModel> getMarcacionesByFiltro(MarcacionFilterViewModel filter);
	
	NotificacionViewModel solicitarCambioMarcacion(SolicitudCambioMarcacionViewModel solicitudCambioMarcacion);
	
	void crearMarcacionesDesdeFechaIngreso(EmpleadoViewModel empleado);
		
}
