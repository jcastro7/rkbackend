package pe.com.empresa.rk.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.empresa.rk.view.model.MarcacionQuickFilterViewModel;
import pe.com.empresa.rk.view.model.MarcacionViewModel;
import pe.com.empresa.rk.service.MarcacionEmpleadoService;
import pe.com.empresa.rk.view.model.MarcacionFilterViewModel;
import pe.com.empresa.rk.view.model.NotificacionViewModel;
import pe.com.empresa.rk.view.model.SolicitudCambioMarcacionViewModel;

@RestController
@RequestMapping(value = "/api/marcacion")
public class MarcacionController extends BaseController{
	
	private static final Logger LOG = LoggerFactory.getLogger(MarcacionController.class);

	@Autowired
	MarcacionEmpleadoService marcacionEmpleadoService;
	
	@RequestMapping(value = "/registrarCorreccionMarcacion", method = RequestMethod.POST)
	public @ResponseBody
	NotificacionViewModel registrarCorreccionMarcacion(@RequestBody SolicitudCambioMarcacionViewModel solicitudCambioMarcacion) {

		NotificacionViewModel notificacion;
		solicitudCambioMarcacion.setEstado("P");
		notificacion = marcacionEmpleadoService.registrarSolicitudCambioMarcacion(solicitudCambioMarcacion);
		return notificacion;

	}
	
	@RequestMapping(value = "/obtenerSolicitudCambio", method = RequestMethod.POST)
	public @ResponseBody
	SolicitudCambioMarcacionViewModel obtenerSolicitudCambio(@RequestBody Long idMarcacion) {

		SolicitudCambioMarcacionViewModel solicitud ;
		solicitud = marcacionEmpleadoService.obtenerSolicitudCambio(idMarcacion);
		return solicitud;

	}
	
	@RequestMapping(value = "/aprobarSolicitud", method = RequestMethod.POST)
	public @ResponseBody
	NotificacionViewModel aprobarSolicitud(@RequestBody SolicitudCambioMarcacionViewModel solicitudCambioMarcacion) {

		NotificacionViewModel notificacion ;
		solicitudCambioMarcacion.setEstado("A");
		notificacion = marcacionEmpleadoService.registrarSolicitudCambioMarcacion(solicitudCambioMarcacion);
		return notificacion;

	}
	
	@RequestMapping(value = "/rechazarSolicitud", method = RequestMethod.POST)
	public @ResponseBody
	NotificacionViewModel rechazarSolicitud(@RequestBody SolicitudCambioMarcacionViewModel solicitudCambioMarcacion) {

		NotificacionViewModel notificacion ;
		solicitudCambioMarcacion.setEstado("R");
		notificacion = marcacionEmpleadoService.registrarSolicitudCambioMarcacion(solicitudCambioMarcacion);
		return notificacion;

	}
	
	@RequestMapping(value = "/busquedaRapidaMarcacionesEmpleado", method = RequestMethod.POST)
	public @ResponseBody  ResponseEntity<List<MarcacionViewModel>>  busquedaRapidaMarcacionesEmpleado(@RequestBody MarcacionQuickFilterViewModel quickFilter) {

		List<MarcacionViewModel> result;
		result = marcacionEmpleadoService.quickSearch(quickFilter);
		if(result == null)
			result = new ArrayList<>();
		
		return new ResponseEntity<>(result, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/getMarcacionesByFiltro", method = RequestMethod.POST)
	public @ResponseBody List<MarcacionViewModel> getMarcacionesByFiltro(@RequestBody MarcacionFilterViewModel filter) {
		List<MarcacionViewModel> dto;
	    dto = marcacionEmpleadoService.getMarcacionesByFiltro(filter);
	    
	    return dto;
	    
	}

		
}