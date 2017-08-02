package pe.com.tss.runakuna.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.tss.runakuna.service.ProyectoService;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;
import pe.com.tss.runakuna.view.model.ProyectoFilterViewModel;
import pe.com.tss.runakuna.view.model.ProyectoQuickFilterViewModel;
import pe.com.tss.runakuna.view.model.ProyectoResultViewModel;
import pe.com.tss.runakuna.view.model.ProyectoViewModel;

@CrossOrigin
@RestController
@RequestMapping("/api/proyecto")
public class ProyectoController extends BaseController {
	
	@Autowired
	ProyectoService proyectoService;
	
	private final Logger LOG = LoggerFactory.getLogger(ProyectoController.class);
	
	@RequestMapping(value = "/registrarProyecto", method = RequestMethod.POST)
    public @ResponseBody
    NotificacionViewModel registrarProyecto(@RequestBody ProyectoViewModel proyectoDto) {
    	NotificacionViewModel dto = new NotificacionViewModel();
    	if(proyectoDto.getIdProyecto()==null){
			dto = proyectoService.create(proyectoDto);
		} else {
			dto = proyectoService.update(proyectoDto);
		}
		return dto;
    }
	
	 @RequestMapping(value = "/obtenerProyectoDetalle", method = RequestMethod.POST)
		public @ResponseBody ProyectoViewModel obtenerProyectoDetalle(@RequestBody Long idProyecto){
		 	ProyectoViewModel result = new ProyectoViewModel();
			result = proyectoService.findOne(idProyecto);
			if(result == null)
				result = new ProyectoViewModel();
			return result;
	}
	 
	 @RequestMapping(value = "/obtenerProyectos", method = RequestMethod.POST)
	    public @ResponseBody ResponseEntity<List<ProyectoResultViewModel>> obtenerProyectos(@RequestBody ProyectoFilterViewModel dto){
	    	List<ProyectoResultViewModel> result = new ArrayList<ProyectoResultViewModel>();
			result = proyectoService.search(dto);
			if(result == null)
				result = new ArrayList<>();
			return new ResponseEntity<>(result, HttpStatus.OK);
	}
	 
	 @RequestMapping(value = "/busquedaRapidaProyectos", method = RequestMethod.POST)
	    public @ResponseBody ResponseEntity<List<ProyectoResultViewModel>> busquedaRapidaProyectos(@RequestBody ProyectoQuickFilterViewModel dto){
	    	List<ProyectoResultViewModel> result = new ArrayList<ProyectoResultViewModel>();
			result = proyectoService.quickSearch(dto);
			if(result == null)
				result = new ArrayList<>();
			return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	 @RequestMapping(value = "/eliminarProyecto", method = RequestMethod.POST)
		public @ResponseBody	NotificacionViewModel eliminarProyecto(@RequestBody Long idProyecto) {
		 NotificacionViewModel notificacionViewModel = new NotificacionViewModel();	
		 notificacionViewModel = proyectoService.delete(idProyecto);
			return notificacionViewModel;
		}

}
