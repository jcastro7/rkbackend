package pe.com.empresa.rk.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.empresa.rk.view.model.AutorizacionFilterViewModel;
import pe.com.empresa.rk.service.RolService;
import pe.com.empresa.rk.view.model.NotificacionViewModel;
import pe.com.empresa.rk.view.model.AccionViewModel;
import pe.com.empresa.rk.view.model.Authorization;
import pe.com.empresa.rk.view.model.RolFilterViewModel;
import pe.com.empresa.rk.view.model.RolResultViewModel;
import pe.com.empresa.rk.view.model.RolViewModel;

@RestController
@RequestMapping(value = "/api/rol")
public class RolController extends BaseController{
	
	@Autowired
	RolService rolService;
	
	@RequestMapping(value = "/obtenerRolResult", method = RequestMethod.POST)
	public @ResponseBody  ResponseEntity<List<RolResultViewModel>>  obtenerRolResult(@RequestBody RolFilterViewModel rolFilterDto) {

		List<RolResultViewModel> result = new ArrayList<>();
		result = rolService.search(rolFilterDto);
		if(result == null)
			result = new ArrayList<>();
		
		return new ResponseEntity<>(result, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/obtenerAutorizacion", method = RequestMethod.POST)
	public @ResponseBody List<Authorization> obtenerAutorizacion(@RequestBody AutorizacionFilterViewModel autorizacion) {

		List<Authorization> result = new ArrayList<>();
		result = rolService.obtenerAutorizaciones(autorizacion);
		return result;

	}
	@RequestMapping(value = "/eliminarRolEmpleado", method = RequestMethod.POST)
    public @ResponseBody NotificacionViewModel eliminarRolEmpleado(@RequestBody Long idRol) {

		NotificacionViewModel dto =new NotificacionViewModel();
		dto =  rolService.delete(idRol);

        return dto;
    }
	
	@RequestMapping(value = "/obtenerRolById", method = RequestMethod.POST)
	public List<RolViewModel> getRolById(@RequestBody Long idRol){
		
		return rolService.getRolById(idRol);
	}
	
	@RequestMapping(value = "/findRolById", method = RequestMethod.POST)
	public @ResponseBody RolViewModel findRolById(@RequestBody Long idRol){
		
		RolViewModel result = new RolViewModel();
		result = rolService.findRolById(idRol);
		
		return result;
	}
	
	@RequestMapping(value = "/findAllSubModulosAccion", method = RequestMethod.POST)
	public @ResponseBody RolViewModel findAllSubModulosAccion(){
		
		RolViewModel result = new RolViewModel();
		result = rolService.findAllSubModulosAccion();
		
		return result;
	}
	
	@RequestMapping(value = "/updateAutorizacion", method = RequestMethod.POST)
	public @ResponseBody
	NotificacionViewModel actualizarAutorizacion(@RequestBody AccionViewModel accionViewModel) {
		
		NotificacionViewModel dto = rolService.updateAutorizacionAccion(accionViewModel);
		return dto;
	}
	
	@RequestMapping(value = "/actualizarRolAccion", method = RequestMethod.POST)
	public @ResponseBody NotificacionViewModel actualizarRolAccion(@RequestBody RolViewModel rolViewModel) {
		
		NotificacionViewModel notificacion = new NotificacionViewModel();
		
		notificacion = rolService.actualizarRolAccion(rolViewModel);
		
		return notificacion;
	}
	

}
