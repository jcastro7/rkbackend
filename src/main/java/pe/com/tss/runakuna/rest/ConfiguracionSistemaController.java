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
import pe.com.tss.runakuna.service.ConfiguracionSistemaService;
import pe.com.tss.runakuna.service.TipoLicenciaService;
import pe.com.tss.runakuna.view.model.CargoFilterViewModel;
import pe.com.tss.runakuna.view.model.CargoResultViewModel;
import pe.com.tss.runakuna.view.model.CargoViewModel;
import pe.com.tss.runakuna.view.model.ConfiguracionSistemaFilterViewModel;
import pe.com.tss.runakuna.view.model.ConfiguracionSistemaResultViewModel;
import pe.com.tss.runakuna.view.model.ConfiguracionSistemaViewModel;
import pe.com.tss.runakuna.view.model.HistoriaLaboralViewModel;
import pe.com.tss.runakuna.view.model.HorarioViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;
import pe.com.tss.runakuna.view.model.TipoLicenciaFilterViewModel;
import pe.com.tss.runakuna.view.model.TipoLicenciaResultViewModel;
import pe.com.tss.runakuna.view.model.TipoLicenciaViewModel;


@RestController
@RequestMapping("/api/configuracionSistema")
public class ConfiguracionSistemaController extends BaseController{
	
	@Autowired
	ConfiguracionSistemaService configuracionSistemaService;
	
	private final Logger LOG = LoggerFactory.getLogger(ConfiguracionSistemaController.class);
	
	/**
	 * OACP
	 */
	
	@RequestMapping(value = "/registrarConfiguracionSistema", method = RequestMethod.POST)
    public @ResponseBody
	NotificacionViewModel registrarConfiguracionSistema(@RequestBody ConfiguracionSistemaViewModel configuracionSistemaDto) {
    	NotificacionViewModel dto = new NotificacionViewModel();
    	if(configuracionSistemaDto.getIdConfiguracionSistema()==null){
			dto = configuracionSistemaService.create(configuracionSistemaDto);
		} else {
			dto = configuracionSistemaService.update(configuracionSistemaDto);
		}
		return dto;
    }
	
	 @RequestMapping(value = "/obtenerConfiguracionSistemaDetalle", method = RequestMethod.POST)
		public @ResponseBody
		ConfiguracionSistemaViewModel obtenerConfiguracionSistemaDetalle(@RequestBody Long idConfiguracionSistema){
		 ConfiguracionSistemaViewModel result = new ConfiguracionSistemaViewModel();
			result = configuracionSistemaService.findOne(idConfiguracionSistema);
			if(result == null)
				result = new ConfiguracionSistemaViewModel();
			return result;
	}
	 
	 @RequestMapping(value = "/obtenerConfiguracionesSistema", method = RequestMethod.POST)
	    public @ResponseBody ResponseEntity<List<ConfiguracionSistemaResultViewModel>> obtenerConfiguracionesSistema(@RequestBody ConfiguracionSistemaFilterViewModel dto){
	    	List<ConfiguracionSistemaResultViewModel> result = new ArrayList<ConfiguracionSistemaResultViewModel>();
			result = configuracionSistemaService.search(dto);
			if(result == null)
				result = new ArrayList<>();
			return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	 

}
