package pe.com.tss.runakuna.rest;

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

import pe.com.tss.runakuna.service.VacacionEmpleadoService;
import pe.com.tss.runakuna.service.VacacionService;
import pe.com.tss.runakuna.view.model.EmpleadoPlanillaResultViewModel;
import pe.com.tss.runakuna.view.model.EmpleadoViewModel;
import pe.com.tss.runakuna.view.model.LicenciaEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;
import pe.com.tss.runakuna.view.model.VacacionEmpleadoPlanillaViewModel;
import pe.com.tss.runakuna.view.model.VacacionEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.VacacionFilterViewModel;
import pe.com.tss.runakuna.view.model.VacacionPendienteResultViewModel;
import pe.com.tss.runakuna.view.model.VacacionQuickFilterViewModel;
import pe.com.tss.runakuna.view.model.VacacionResultViewModel;
import pe.com.tss.runakuna.view.model.VacacionViewModel;
import pe.com.tss.runakuna.view.model.VacacionesEmpleadoFilterViewModel;
import pe.com.tss.runakuna.view.model.VacacionesEmpleadoResultViewModel;

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
	
	@RequestMapping(value = "/actualizarVacaciones", method = RequestMethod.POST)
	public @ResponseBody
    NotificacionViewModel actualizarVacaciones(@RequestBody VacacionEmpleadoViewModel vacacionEmpleadoDto) {

		NotificacionViewModel dto = vacacionEmpleadoService.actualizarVacaciones(vacacionEmpleadoDto);
		return dto;

	}
	
	@RequestMapping(value = "/regularizarVacacion", method = RequestMethod.POST)
	public @ResponseBody
    NotificacionViewModel regularizarVacacion(@RequestBody VacacionEmpleadoViewModel vacacionEmpleadoDto) {

		NotificacionViewModel dto = vacacionEmpleadoService.regularizarVacacion(vacacionEmpleadoDto);
		return dto;

	}
	
	@RequestMapping(value = "/comprarVacacion", method = RequestMethod.POST)
	public @ResponseBody
    NotificacionViewModel comprarVacacion(@RequestBody VacacionEmpleadoViewModel vacacionEmpleadoDto) {

		NotificacionViewModel dto = vacacionEmpleadoService.comprarVacacion(vacacionEmpleadoDto);
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
		
		NotificacionViewModel notificacion = vacacionEmpleadoService.delete(vacacionEmpleadoDto.getIdVacacion());
		return notificacion;
	}
	@RequestMapping(value = "/incluirVacacionEmpleadoAPlanilla", method = RequestMethod.POST)
	public NotificacionViewModel incluirVacacionAPlanilla(@RequestBody VacacionEmpleadoViewModel vacacionEmpleadoDto) {
		
		NotificacionViewModel notificacion = vacacionEmpleadoService.incluirVacacionAPlanilla(vacacionEmpleadoDto.getIdVacacion());
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
	VacacionViewModel obtenerVacacionesEmpleadoDetalle(@RequestBody Long idVacacion){
		VacacionViewModel result = new VacacionViewModel();
		result = vacacionService.findOne(idVacacion);
		if(result == null)
			result = new VacacionViewModel();
		
		return result;
	}	

	@RequestMapping(value = "/busquedaVacacionesPendientesEmpleado", method = RequestMethod.POST)
    public @ResponseBody  ResponseEntity<List<VacacionPendienteResultViewModel>>  busquedaVacacionesPendientesEmpleado(@RequestBody VacacionFilterViewModel busquedaVacacionesEmpleadoDto) {
        List<VacacionPendienteResultViewModel> result = new ArrayList<>();
        result = vacacionService.searchVacacionesPendientes(busquedaVacacionesEmpleadoDto);
		if(result == null)
			result = new ArrayList<>();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/obtenerBusquedaEmpleadoPlanilla", method = RequestMethod.POST)
	public @ResponseBody List<VacacionResultViewModel> obtenerBusquedaEmpleadoPlanilla(@RequestBody VacacionFilterViewModel busquedaVacacionesEmpleadoDto){
	
		return vacacionEmpleadoService.obtenerBusquedaEmpleadoPlanilla(busquedaVacacionesEmpleadoDto);
	}
	
	@RequestMapping(value = "/registrarVacacionesEnPanilla", method = RequestMethod.POST)
	public @ResponseBody
    NotificacionViewModel registrarVacacionesEnPanilla(@RequestBody VacacionEmpleadoPlanillaViewModel vacacionEmpleadoPlanilla) {
		
		NotificacionViewModel dto = vacacionEmpleadoService.registrarVacacionesEnPanilla(vacacionEmpleadoPlanilla);
		return dto;
	}
	
	
	@RequestMapping(value = "/obtenerDiasVacacionesPendientes", method = RequestMethod.POST)
	public @ResponseBody
    Integer obtenerDiasVacacionesPendientes(@RequestBody Long idEmpleado) {
		
		Integer diasPendientes = vacacionEmpleadoService.obtenerDiasVacacionesPendientes(idEmpleado);
		return diasPendientes;
	}
	
}
