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

import pe.com.tss.runakuna.service.MarcadorService;
import pe.com.tss.runakuna.service.ProyectoService;
import pe.com.tss.runakuna.view.model.MarcadorFilterViewModel;
import pe.com.tss.runakuna.view.model.MarcadorResultViewModel;
import pe.com.tss.runakuna.view.model.MarcadorViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;
import pe.com.tss.runakuna.view.model.ProyectoFilterViewModel;
import pe.com.tss.runakuna.view.model.ProyectoQuickFilterViewModel;
import pe.com.tss.runakuna.view.model.ProyectoResultViewModel;
import pe.com.tss.runakuna.view.model.ProyectoViewModel;

@CrossOrigin
@RestController
@RequestMapping("/api/marcador")
public class MarcadorController extends BaseController {
	
	@Autowired
	MarcadorService marcadorService;
	
	private final Logger LOG = LoggerFactory.getLogger(MarcadorController.class);
	
	@RequestMapping(value = "/registrarMarcador", method = RequestMethod.POST)
    public @ResponseBody
    NotificacionViewModel registrarMarcador(@RequestBody MarcadorViewModel marcadorDto) {
    	NotificacionViewModel dto;
    	if(marcadorDto.getIdMarcador()==null){
			dto = marcadorService.create(marcadorDto);
		} else {
			dto = marcadorService.update(marcadorDto);
		}
		return dto;
    }
	
	 @RequestMapping(value = "/obtenerMarcador", method = RequestMethod.POST)
		public @ResponseBody MarcadorViewModel obtenerMarcador(@RequestBody Long idMarcador){
		 	MarcadorViewModel result;
			result = marcadorService.findOne(idMarcador);
			if(result == null)
				result = new MarcadorViewModel();
			LOG.info("Msg obtenerMarcador: " + result);
			return result;
	}
	 
	 @RequestMapping(value = "/obtenerMarcadores", method = RequestMethod.POST)
	    public @ResponseBody ResponseEntity<List<MarcadorResultViewModel>> obtenerMarcadores(@RequestBody MarcadorFilterViewModel dto){
	    	List<MarcadorResultViewModel> result;
			result = marcadorService.search(dto);
			if(result == null)
				result = new ArrayList<>();
			LOG.info("Msg obtenerMarcadores: " + result);
			return new ResponseEntity<>(result, HttpStatus.OK);
	}
	 
	
	 @RequestMapping(value = "/eliminarMarcador", method = RequestMethod.POST)
		public @ResponseBody	NotificacionViewModel eliminarMarcador(@RequestBody Long idMarcador) {
		 NotificacionViewModel notificacionViewModel;
		 notificacionViewModel = marcadorService.delete(idMarcador);
			return notificacionViewModel;
		}
	 
}
