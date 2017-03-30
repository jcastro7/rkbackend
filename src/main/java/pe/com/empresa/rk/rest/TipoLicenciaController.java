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

import pe.com.empresa.rk.view.model.TipoLicenciaFilterViewModel;
import pe.com.empresa.rk.view.model.TipoLicenciaViewModel;
import pe.com.empresa.rk.service.TipoLicenciaService;
import pe.com.empresa.rk.view.model.NotificacionViewModel;
import pe.com.empresa.rk.view.model.TipoLicenciaResultViewModel;


@RestController
@RequestMapping("/api/tipoLicencia")
public class TipoLicenciaController extends BaseController{
	
	@Autowired
	TipoLicenciaService tipoLicenciaService;
	
	private final Logger LOG = LoggerFactory.getLogger(TipoLicenciaController.class);
	
	/**
	 * OACP
	 */
	
	@RequestMapping(value = "/registrarTipoLicencia", method = RequestMethod.POST)
    public @ResponseBody
	NotificacionViewModel registrarLicencia(@RequestBody TipoLicenciaViewModel tipoLicenciaDto) {
    	NotificacionViewModel dto = new NotificacionViewModel();
    	if(tipoLicenciaDto.getIdTipoLicencia()==null){
			dto = tipoLicenciaService.create(tipoLicenciaDto);
		} else {
			dto = tipoLicenciaService.update(tipoLicenciaDto);
		}
		return dto;
    }
	
	 @RequestMapping(value = "/obtenerTipoLicenciaDetalle", method = RequestMethod.POST)
		public @ResponseBody
		TipoLicenciaViewModel obtenerLicenciaDetalle(@RequestBody Long idLicencia){
	    	TipoLicenciaViewModel result = new TipoLicenciaViewModel();
			result = tipoLicenciaService.findOne(idLicencia);
			if(result == null)
				result = new TipoLicenciaViewModel();
			LOG.info("Msg obtenerCargoDetalle: " + result);
			return result;
	}
	 
	 @RequestMapping(value = "/obtenerTiposLicencias", method = RequestMethod.POST)
	    public @ResponseBody ResponseEntity<List<TipoLicenciaResultViewModel>> obtenerLicencias(@RequestBody TipoLicenciaFilterViewModel dto){
	    	List<TipoLicenciaResultViewModel> result = new ArrayList<TipoLicenciaResultViewModel>();
			result = tipoLicenciaService.search(dto);
			if(result == null)
				result = new ArrayList<>();
			LOG.info("Msg obtenerCargos: " + result);
			return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	 @RequestMapping(value = "/eliminarTipoLicencia", method = RequestMethod.POST)
		public NotificacionViewModel eliminarLicencia(@RequestBody Long idLicencia) {
		 NotificacionViewModel notificacionViewModel = new NotificacionViewModel();
		 notificacionViewModel = tipoLicenciaService.delete(idLicencia);
			return  notificacionViewModel;
		}
	 
	 
	 

}
