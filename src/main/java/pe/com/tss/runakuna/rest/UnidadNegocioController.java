package pe.com.tss.runakuna.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.tss.runakuna.view.model.CargoViewModel;
import pe.com.tss.runakuna.view.model.DepartamentoAreaViewModel;
import pe.com.tss.runakuna.view.model.ProyectoViewModel;
import pe.com.tss.runakuna.view.model.UndNegocioViewModel;
import pe.com.tss.runakuna.service.DepartamentoService;
import pe.com.tss.runakuna.service.UndNegocioService;

@CrossOrigin
@RestController
@RequestMapping("/api/undNegocio")
public class UnidadNegocioController extends BaseController{
	
	private final Logger LOG = LoggerFactory.getLogger(UnidadNegocioController.class);
	
	@Autowired
	UndNegocioService undNegocioService;
	
	@Autowired
	DepartamentoService departamentoService;
	
	@RequestMapping(value = "/obtenerUndNegocio", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<UndNegocioViewModel> getUndNegocio(){
		return undNegocioService.getUndNegocio();
	}
	
	@RequestMapping(value = "/obtenerDepaArea", 
							 method = RequestMethod.GET, 
							 produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DepartamentoAreaViewModel>> obtenerDepartamentos(
									@RequestParam(name="idUnidadDeNegocio", required = true)  Long idUnidadDeNegocio){
		List<DepartamentoAreaViewModel> result = new ArrayList<>();
		result = departamentoService.obtenerDepaAreaPorUndNegocio(idUnidadDeNegocio);
		if(result == null)
			result = new ArrayList<>();
		LOG.info("Msg obtenerDepartamentos: " + result);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/obtenerProyecto", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ProyectoViewModel> obtenerProyectos(@RequestParam(name="idDepartamentoArea")  Long idDepartamentoArea){
		List<ProyectoViewModel> result = null;
		result = departamentoService.obtenerProyPorDepaArea(idDepartamentoArea);
		if(result == null)
			result = new ArrayList<>();
		LOG.info("Msg obtenerProyectos: " + result);
		return result;
	}
	
	@RequestMapping(value = "/obtenerCargo", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<CargoViewModel> obtenerCargos(@RequestParam(name="idProyecto")  Long idProyecto){
		List<CargoViewModel> result = null;
		/*result = departamentoService.obtenerCargoPorProy(idProyecto);
		if(result == null)
			result = new ArrayList<>();
		LOG.info("Msg obtenerCargoPorProy: " + result);*/
		return result;
	}
	
	@RequestMapping(value = "/obtenerListCargos", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<CargoViewModel> getListCargos(){
		return undNegocioService.getListCargos();
	}
	
	
	@RequestMapping(value = "/obtenerProyectos", method = RequestMethod.GET)
	public @ResponseBody List<ProyectoViewModel> obtenerProyectoss(){
		List<ProyectoViewModel> result = null;
		result = departamentoService.obtenerProyectos();
		if(result == null)
			result = new ArrayList<>();
		LOG.info("Msg obtenerProyectos: " + result);
		return result;
	}

}
