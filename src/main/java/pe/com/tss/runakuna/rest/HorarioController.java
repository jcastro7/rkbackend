package pe.com.tss.runakuna.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.tss.runakuna.service.HorarioService;
import pe.com.tss.runakuna.view.model.HorarioDiaViewModel;
import pe.com.tss.runakuna.view.model.HorarioFilterViewModel;
import pe.com.tss.runakuna.view.model.HorarioResultViewModel;
import pe.com.tss.runakuna.view.model.HorarioViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;

import java.util.*;

@RestController
@RequestMapping(value = "/api/horario")
public class HorarioController extends BaseController{
	
	private static final Logger LOG = LoggerFactory.getLogger(HorarioController.class);

	@Autowired
	HorarioService horarioService;

	@RequestMapping(value = "/busquedaHorario", method = RequestMethod.POST)
	public @ResponseBody  ResponseEntity<List<HorarioResultViewModel>>  busquedaEmpleado(@RequestBody HorarioFilterViewModel horarioFilter) {

		List<HorarioResultViewModel> result = new ArrayList<>();
		result = horarioService.search(horarioFilter);
		
		if(result == null)
			result = new ArrayList<>();
		
		return new ResponseEntity<>(result, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/obtenerHorariosPorTipoHorario", method = RequestMethod.POST)
	public @ResponseBody  List<HorarioViewModel>  obtenerHorariosPorTipoHorario(@RequestBody HorarioViewModel horario) {

		List<HorarioViewModel> result = new ArrayList<>();
		result = horarioService.obtenerHorariosPorTipoHorario(horario);
		return result;

	}
	
	@RequestMapping(value = "/obtenerHorarioPorTipoHorarioPorDefecto", method = RequestMethod.POST)
	public @ResponseBody
	HorarioViewModel obtenerHorarioPorTipoHorarioPorDefecto(@RequestBody HorarioViewModel horario) {
		HorarioViewModel result = new HorarioViewModel();
		result = horarioService.obtenerHorarioPorTipoHorarioPorDefecto(horario);
		return result;

	}
	
	@RequestMapping(value = "/obtenerHorarioDiaPorHorario", method = RequestMethod.POST)
	public @ResponseBody List<HorarioDiaViewModel> obtenerHorarioDiaPorHorario(@RequestBody Long idHorario) {
		List<HorarioDiaViewModel> result = new ArrayList<>();
		result = horarioService.obtenerHorarioDiaPorHorario(idHorario);
		return result;

	}
	
	@RequestMapping(value = "/registrarHorario", method = RequestMethod.POST)
	public @ResponseBody NotificacionViewModel registrarHorario(@RequestBody HorarioViewModel horario) {

		NotificacionViewModel dto = new NotificacionViewModel();
		if(horario.getIdHorario() == null){
			dto = horarioService.create(horario);
		}else{
			dto = horarioService.update(horario);
		}
		
		return dto;
	}
	
	@RequestMapping(value = "/obtenerHorario", method = RequestMethod.POST)
	public @ResponseBody HorarioViewModel obtenerHorario(@RequestBody Long idHorario) {
		HorarioViewModel result = new HorarioViewModel();
		result = horarioService.findOne(idHorario);
		return result;

	}
	
	@RequestMapping(value = "/eliminarHorario", method = RequestMethod.POST)
	public @ResponseBody  NotificacionViewModel  eliminarHorario(@RequestBody Long idHorario) {

		NotificacionViewModel viewModel =  horarioService.delete(idHorario);
		return viewModel;
	}
	
	@RequestMapping(value = "/obtenerHorarios", method = RequestMethod.POST)
	public @ResponseBody List<HorarioViewModel> obtenerHorarios() {
		List<HorarioViewModel> result = new ArrayList<>();
		result = horarioService.obtenerHorarios();
		return result;

	}
	
}