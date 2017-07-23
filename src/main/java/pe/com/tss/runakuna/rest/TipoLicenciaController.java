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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.tss.runakuna.domain.model.repository.jpa.HistorialLaboralJpaRepository;
import pe.com.tss.runakuna.service.CargoService;
import pe.com.tss.runakuna.service.TipoLicenciaService;
import pe.com.tss.runakuna.view.model.CargoFilterViewModel;
import pe.com.tss.runakuna.view.model.CargoResultViewModel;
import pe.com.tss.runakuna.view.model.CargoViewModel;
import pe.com.tss.runakuna.view.model.HistoriaLaboralViewModel;
import pe.com.tss.runakuna.view.model.HorarioViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;
import pe.com.tss.runakuna.view.model.TipoLicenciaFilterViewModel;
import pe.com.tss.runakuna.view.model.TipoLicenciaResultViewModel;
import pe.com.tss.runakuna.view.model.TipoLicenciaViewModel;


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
			return result;
	}
	 
	 @RequestMapping(value = "/obtenerTiposLicencias", method = RequestMethod.POST)
	    public @ResponseBody ResponseEntity<List<TipoLicenciaResultViewModel>> obtenerLicencias(@RequestBody TipoLicenciaFilterViewModel dto){
	    	List<TipoLicenciaResultViewModel> result = new ArrayList<TipoLicenciaResultViewModel>();
			result = tipoLicenciaService.search(dto);
			if(result == null)
				result = new ArrayList<>();
			return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	 @RequestMapping(value = "/eliminarTipoLicencia", method = RequestMethod.POST)
		public NotificacionViewModel eliminarLicencia(@RequestBody Long idLicencia) {
		 NotificacionViewModel notificacionViewModel = new NotificacionViewModel();
		 notificacionViewModel = tipoLicenciaService.delete(idLicencia);
			return  notificacionViewModel;
		}
	 
	 
	 

}
