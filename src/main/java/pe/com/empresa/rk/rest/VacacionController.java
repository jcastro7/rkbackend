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

import pe.com.empresa.rk.service.VacacionEmpleadoService;
import pe.com.empresa.rk.service.VacacionService;
import pe.com.empresa.rk.view.model.EmpleadoViewModel;
import pe.com.empresa.rk.view.model.NotificacionViewModel;
import pe.com.empresa.rk.view.model.VacacionEmpleadoViewModel;
import pe.com.empresa.rk.view.model.VacacionFilterViewModel;
import pe.com.empresa.rk.view.model.VacacionQuickFilterViewModel;
import pe.com.empresa.rk.view.model.VacacionResultViewModel;
import pe.com.empresa.rk.view.model.VacacionViewModel;

@RestController
@RequestMapping(value = "/api/vacacionEmpleado")
public class VacacionController extends BaseController {
	
	@Autowired
	VacacionEmpleadoService vacacionEmpleadoService;
	
	@Autowired
	VacacionService vacacionService;
	
	
	@RequestMapping(value = "/obtenerDiasDisponibles", method = RequestMethod.POST)
	public @ResponseBody
	VacacionEmpleadoViewModel obtenerDiasDisponibles(@RequestBody EmpleadoViewModel empleado) {
		VacacionEmpleadoViewModel dto = vacacionEmpleadoService.obtenerDiasDisponibles(empleado);
		return dto;
	}
	
	@RequestMapping(value = "/obtenerPeriodo", method = RequestMethod.POST)
	public @ResponseBody
	VacacionEmpleadoViewModel obtenerPeriodo(@RequestBody EmpleadoViewModel empleado) {
		VacacionEmpleadoViewModel dto = vacacionEmpleadoService.obtenerPeriodo(empleado);
		return dto;
	}
	
	@RequestMapping(value = "/registrarVacaciones", method = RequestMethod.POST)
	public @ResponseBody
    NotificacionViewModel registrarVacaciones(@RequestBody VacacionEmpleadoViewModel vacacionEmpleadoDto) {

		NotificacionViewModel dto = vacacionEmpleadoService.registrarVacaciones(vacacionEmpleadoDto);
		return dto;

	}
	
	@RequestMapping(value = "/regularizarVacacion", method = RequestMethod.POST)
	public @ResponseBody
    NotificacionViewModel regularizarVacacion(@RequestBody VacacionEmpleadoViewModel vacacionEmpleadoDto) {

		NotificacionViewModel dto = vacacionEmpleadoService.regularizarVacacion(vacacionEmpleadoDto);
		return dto;

	}
	
	@RequestMapping(value = "/actualizarVacacionEmpleado", method = RequestMethod.POST)
	public @ResponseBody
    NotificacionViewModel actualizarVacacionEmpleado(@RequestBody VacacionEmpleadoViewModel vacacionEmpleadoDto) {
		
		NotificacionViewModel dto = vacacionEmpleadoService.actualizarVacacionEmpleado(vacacionEmpleadoDto);
		return dto;
	}
	@RequestMapping(value = "/enviarVacacionEmpleado", method = RequestMethod.POST)
	public @ResponseBody
    NotificacionViewModel enviarVacacionEmpleado(@RequestBody VacacionEmpleadoViewModel vacacionEmpleadoDto) {
		
		NotificacionViewModel dto = vacacionEmpleadoService.enviarVacacionEmpleado(vacacionEmpleadoDto);
		return dto;
	}
	@RequestMapping(value = "/eliminarVacacionEmpleado", method = RequestMethod.POST)
	public NotificacionViewModel eliminarVacacionEmpleado(@RequestBody VacacionEmpleadoViewModel vacacionEmpleadoDto) {
		
		NotificacionViewModel notificacion = vacacionEmpleadoService.eliminarVacacionEmpleado(vacacionEmpleadoDto.getIdVacacion());
		return notificacion;
	}
	@RequestMapping(value = "/devolverVacacionEmpleado", method = RequestMethod.POST)
	public @ResponseBody
    NotificacionViewModel devolverVacacionEmpleado(@RequestBody VacacionEmpleadoViewModel vacacionEmpleadoDto) {
		
		NotificacionViewModel dto = vacacionEmpleadoService.devolverVacacionEmpleado(vacacionEmpleadoDto);
		return dto;
	}
	@RequestMapping(value = "/aprobarVacacionEmpleado", method = RequestMethod.POST)
	public @ResponseBody
    NotificacionViewModel aprobarVacacionEmpleado(@RequestBody VacacionEmpleadoViewModel vacacionEmpleadoDto) {
		
		NotificacionViewModel dto = vacacionEmpleadoService.aprobarVacacionEmpleado(vacacionEmpleadoDto);
		return dto;
	}
	@RequestMapping(value = "/rechazarVacacionEmpleado", method = RequestMethod.POST)
	public @ResponseBody
    NotificacionViewModel rechazarVacacionEmpleado(@RequestBody VacacionEmpleadoViewModel vacacionEmpleadoDto) {
		
		NotificacionViewModel dto = vacacionEmpleadoService.rechazarVacacionEmpleado(vacacionEmpleadoDto);
		return dto;
	}
	
	@RequestMapping(value = "/busquedaVacacionesEmpleado", method = RequestMethod.POST)
    public @ResponseBody  ResponseEntity<List<VacacionResultViewModel>>  busquedaVacacionesEmpleado(@RequestBody VacacionFilterViewModel busquedaVacacionesEmpleadoDto) {

        List<VacacionResultViewModel> result = new ArrayList<>();
        result = vacacionService.search(busquedaVacacionesEmpleadoDto);
		if(result == null)
			result = new ArrayList<>();

        return new ResponseEntity<>(result, HttpStatus.OK);

    }
	
	@RequestMapping(value = "/busquedaRapidaVacacionesEmpleado", method = RequestMethod.POST)
    public @ResponseBody  ResponseEntity<List<VacacionResultViewModel>>  busquedaRapidaVacacionesEmpleado(@RequestBody VacacionQuickFilterViewModel busquedaVacacionesEmpleadoDto) {

        List<VacacionResultViewModel> result = new ArrayList<>();
        result = vacacionService.quickSearch(busquedaVacacionesEmpleadoDto);
		if(result == null)
			result = new ArrayList<>();

        return new ResponseEntity<>(result, HttpStatus.OK);

    }
	
	@RequestMapping(value = "/obtenerVacacionesEmpleadoDetalle", method = RequestMethod.POST)
	VacacionViewModel obtenerVacacionesEmpleadoDetalle(@RequestBody Long idLicencia){
		VacacionViewModel result = new VacacionViewModel();
		result = vacacionService.findOne(idLicencia);
		if(result == null)
			result = new VacacionViewModel();
		
		return result;
	}	

}
