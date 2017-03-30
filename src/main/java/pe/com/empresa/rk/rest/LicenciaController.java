package pe.com.empresa.rk.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.empresa.rk.service.LicenciaEmpleadoService;
import pe.com.empresa.rk.view.model.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/licenciaEmpleado")
public class LicenciaController extends BaseController{
	
	private final Logger LOG = LoggerFactory.getLogger(LicenciaController.class);

	@Autowired
	LicenciaEmpleadoService licenciaEmpleadoService;
	
	@RequestMapping(value = "/registrarLicencia", method = RequestMethod.POST)
	public @ResponseBody
    NotificacionViewModel registrarLicenciaEmpleado(@RequestBody LicenciaEmpleadoViewModel licenciaEmpleadoDto) {
		NotificacionViewModel dto;
		if(licenciaEmpleadoDto.getIdLicencia()==null){
			dto = licenciaEmpleadoService.create(licenciaEmpleadoDto);
		} else {
			dto = licenciaEmpleadoService.update(licenciaEmpleadoDto);
		}
		return dto;

	}
	
	@RequestMapping(value = "/aprobarLicencia", method = RequestMethod.POST)
	public @ResponseBody
    NotificacionViewModel aprobarLicenciaEmpleado(@RequestBody LicenciaEmpleadoViewModel licenciaEmpleadoDto) {
		NotificacionViewModel dto;
		dto = licenciaEmpleadoService.aprobarLicenciaEmpleado(licenciaEmpleadoDto);
	 	return dto;

	}
	
	@RequestMapping(value = "/validarLicencia", method = RequestMethod.POST)
	public @ResponseBody
    NotificacionViewModel validarLicenciaEmpleado(@RequestBody LicenciaEmpleadoViewModel licenciaEmpleadoDto) {
		NotificacionViewModel dto;
		dto = licenciaEmpleadoService.validarLicenciaEmpleado(licenciaEmpleadoDto);
	 	return dto;

	}
	
	@RequestMapping(value = "/eliminarLicencia", method = RequestMethod.POST)
	public NotificacionViewModel eliminarVacacionEmpleado(@RequestBody Long idLicencia) {
		NotificacionViewModel notificacionViewModel;
		notificacionViewModel= licenciaEmpleadoService.delete(idLicencia);
		return notificacionViewModel;
	}
		
	@RequestMapping(value = "/obtenerTipoLicencia", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<TipoLicenciaViewModel> obtenerTipoLicencia() {
		return licenciaEmpleadoService.obtenerTipoLicencia();
	}
	
	
	@RequestMapping(value = "/obtenerLicencias", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<List<LicenciaEmpleadoResultViewModel>> obtenerLicencias(@RequestBody LicenciaEmpleadoFilterViewModel busquedaLicenciaEmpleadoDto){
		List<LicenciaEmpleadoResultViewModel> result;
		result = licenciaEmpleadoService.search(busquedaLicenciaEmpleadoDto);
		if(result == null)
			result = new ArrayList<>();
		LOG.info("Msg obtenerLicencias: " + result);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/busquedaRapidaLicencias", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<List<LicenciaEmpleadoResultViewModel>> obtenerLicencias(@RequestBody LicenciaEmpleadoQuickFilterViewModel busquedaLicenciaEmpleadoDto){
		List<LicenciaEmpleadoResultViewModel> result;
		result = licenciaEmpleadoService.quickSearch(busquedaLicenciaEmpleadoDto);
		if(result == null)
			result = new ArrayList<>();
		LOG.info("Msg obtenerLicencias: " + result);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/obtenerLicenciaDetalle", method = RequestMethod.POST)
	public @ResponseBody
    LicenciaEmpleadoViewModel obtenerLicencia(@RequestBody Long idLicencia){
		LicenciaEmpleadoViewModel result;
		result = licenciaEmpleadoService.findOne(idLicencia);
		if(result == null)
			result = new LicenciaEmpleadoViewModel();
		LOG.info("Msg obtenerLicencia: " + result);
		return result;
	}
	
	@RequestMapping(value = "/verLicencias", method = RequestMethod.POST)
	public @ResponseBody List<LicenciaViewModel> verLicencias(@RequestBody PeriodoEmpleadoViewModel periodoEmpleado) {
		List<LicenciaViewModel> dto = licenciaEmpleadoService.verLicencias(periodoEmpleado);
		return dto;
	}
	
	@RequestMapping(value = "/actualizarLicencia", method = RequestMethod.POST)
	public @ResponseBody NotificacionViewModel actualizarLicencia(@RequestBody LicenciaViewModel licencia) {
		NotificacionViewModel notificacion;
		notificacion = licenciaEmpleadoService.actualizarLicencia(licencia);
		return notificacion;

	}

}
