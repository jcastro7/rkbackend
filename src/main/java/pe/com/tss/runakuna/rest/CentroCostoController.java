package pe.com.tss.runakuna.rest;

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

import pe.com.tss.runakuna.service.CentroCostoService;
import pe.com.tss.runakuna.view.model.CentroCostoFilterViewModel;
import pe.com.tss.runakuna.view.model.CentroCostoResultViewModel;
import pe.com.tss.runakuna.view.model.CentroCostoViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;

@RestController
@RequestMapping(value="/api/centroCosto")
public class CentroCostoController extends BaseController{

	@Autowired
	CentroCostoService centroCostoService;
	private final Logger LOG = LoggerFactory.getLogger(TipoLicenciaController.class);
		
	@RequestMapping(value = "/obtenerCentrosCosto", method = RequestMethod.GET)
	public @ResponseBody List<CentroCostoViewModel> obtenerDepartamentos() {
		List<CentroCostoViewModel> result = null;
		result = centroCostoService.obtenerCentrosCosto();
		if(result == null)
			result = new ArrayList<>();
		return result;
	}
	
	@RequestMapping(value = "/registrarCentroCosto", method = RequestMethod.POST)
    public @ResponseBody
	NotificacionViewModel registrarCentroCosto(@RequestBody CentroCostoViewModel centroCostoDto) {
    	NotificacionViewModel dto = new NotificacionViewModel();
    	if(centroCostoDto.getIdCentroCosto()==null){
			dto = centroCostoService.create(centroCostoDto);
		} else {
			dto = centroCostoService.update(centroCostoDto);
		}
		return dto;
    }
	
	 @RequestMapping(value = "/obtenerCentroCostoDetalle", method = RequestMethod.POST)
		public @ResponseBody
		CentroCostoViewModel obtenerCentroCostoDetalle(@RequestBody Long idCentroCosto){
	    	CentroCostoViewModel result = new CentroCostoViewModel();
			result = centroCostoService.findOne(idCentroCosto);
			if(result == null)
				result = new CentroCostoViewModel();
			return result;
	}
	 
	 @RequestMapping(value = "/buscarCentrosCostos", method = RequestMethod.POST)
	    public @ResponseBody ResponseEntity<List<CentroCostoResultViewModel>> obtenerLicencias(@RequestBody CentroCostoFilterViewModel dto){
	    	List<CentroCostoResultViewModel> result = new ArrayList<CentroCostoResultViewModel>();
			result = centroCostoService.search(dto);
			if(result == null)
				result = new ArrayList<>();
			return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	 @RequestMapping(value = "/eliminarCentroCosto", method = RequestMethod.POST)
		public NotificacionViewModel eliminarCentroCosto(@RequestBody Long idCentroCosto) {
		 NotificacionViewModel notificacionViewModel = new NotificacionViewModel();
		 notificacionViewModel = centroCostoService.delete(idCentroCosto);
			return  notificacionViewModel;
		}
	
}
