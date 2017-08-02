package pe.com.tss.runakuna.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.com.tss.runakuna.service.HorarioEmpleadoService;
import pe.com.tss.runakuna.view.model.HorarioEmpleadoResultViewModel;
import pe.com.tss.runakuna.view.model.HorarioEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;

@RestController
@RequestMapping(value = "/api/horarioEmpleado")
public class HorarioEmpleadoController extends BaseController{
	
	@Autowired
	HorarioEmpleadoService horarioEmpleadoService;

	@RequestMapping(value = "/obtenerHorariosEmpleado", method = RequestMethod.POST)
	public @ResponseBody List<HorarioEmpleadoResultViewModel> obtenerHorariosEmpleado(@RequestBody Long idEmpleado) {
		List<HorarioEmpleadoResultViewModel> dto = horarioEmpleadoService.obtenerBusquedaHorariosEmpleado(idEmpleado);
		return dto;
	}
	
	@RequestMapping(value = "/verHorariosEmpleado", method = RequestMethod.POST)
	public @ResponseBody List<HorarioEmpleadoViewModel> verHorariosEmpleado(@RequestBody Long idEmpleado) {
		List<HorarioEmpleadoViewModel> dto = horarioEmpleadoService.verHorariosEmpleado(idEmpleado);
		return dto;
	}
	
	@RequestMapping(value = "/registrarHorarioEmpleado", method = RequestMethod.POST)
	public @ResponseBody NotificacionViewModel registrarHorarioEmpleado(@RequestBody HorarioEmpleadoViewModel horarioEmpleado) {
		
		NotificacionViewModel notificacion = new NotificacionViewModel(); 
		
		if(horarioEmpleado.getIdHorarioEmpleado() ==null)
			notificacion = horarioEmpleadoService.create(horarioEmpleado);
		else
			notificacion = horarioEmpleadoService.update(horarioEmpleado);
		
		return notificacion;
	}
	
	@RequestMapping(value = "/obtenerHorarioEmpleado", method = RequestMethod.POST)
    public HorarioEmpleadoViewModel obtenerHorarioEmpleado(@RequestBody Long idHorarioEmpleado) {
    	HorarioEmpleadoViewModel horarioEmpleado  =  horarioEmpleadoService.findOne(idHorarioEmpleado);
        return horarioEmpleado;
    }
	
	@RequestMapping(value = "/eliminarHorarioEmpleado", method = RequestMethod.POST)
	public @ResponseBody
    NotificacionViewModel eliminarHorarioEmpleado(@RequestBody Long idHorarioEmpleado) {

		NotificacionViewModel notificacion = new NotificacionViewModel();
		notificacion = horarioEmpleadoService.delete(idHorarioEmpleado);
		return notificacion;

	}
	
	
	
}