package pe.com.tss.runakuna.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.tss.runakuna.service.intf.ModuloService;
import pe.com.tss.runakuna.view.model.ModuloFilterViewModel;
import pe.com.tss.runakuna.view.model.ModuloResultViewModel;
import pe.com.tss.runakuna.view.model.ModuloViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by josediaz on 25/11/2016.
 */
@RestController
@RequestMapping("/api/modulo")
public class ModuloController extends BaseController{


    @Autowired
    private ModuloService moduloService;



    private final Logger LOG = LoggerFactory.getLogger(ModuloController.class);
    
    @RequestMapping(value="/modulosPermitidos", method= RequestMethod.GET, produces="application/json")
    public List<ModuloViewModel> allowedModules(@RequestParam("cuentaUsuario") String cuentaUsuario) {

        return moduloService.getModulosPermitidosPorUsuario(cuentaUsuario);
    }



    
    @RequestMapping(value = "/registrarModulo", method = RequestMethod.POST)
    public @ResponseBody
	NotificacionViewModel registrarModulo(@RequestBody ModuloViewModel moduloViewModel) {


    	NotificacionViewModel dto = new NotificacionViewModel();
    	if(moduloViewModel.getIdModulo()==null){
			dto = moduloService.create(moduloViewModel);
		} else {
			dto = moduloService.update(moduloViewModel);
		}
		return dto;
    }
	
	 @RequestMapping(value = "/obtenerModuloDetalle", method = RequestMethod.POST)
		public @ResponseBody ModuloViewModel obtenerModuloDetalle(@RequestBody Long idModulo){


		 ModuloViewModel result = new ModuloViewModel();
			result = moduloService.findOne(idModulo);
			if(result == null)
				result = new ModuloViewModel();
			LOG.info("Msg obtenerModuloDetalle: " + result);
			return result;
	}
	 
	 @RequestMapping(value = "/obtenerModulos", method = RequestMethod.POST)
	    public @ResponseBody ResponseEntity<List<ModuloResultViewModel>> obtenerModulos(@RequestBody ModuloFilterViewModel dto){
	    	List<ModuloResultViewModel> result = new ArrayList<ModuloResultViewModel>();
			result = moduloService.search(dto);
			if(result == null)
				result = new ArrayList<>();
			LOG.info("Msg obtenerModulos: " + result);
			return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
