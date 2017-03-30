package pe.com.empresa.rk.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.com.empresa.rk.service.PermisoEmpleadoService;
import pe.com.empresa.rk.view.model.PermisoEmpleadoFilterViewModel;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/autocomplete")
public class AutocompleteController extends BaseController{
	
	@Autowired
	PermisoEmpleadoService permisoEmpleadoService;
	
    @RequestMapping(value = "/autocompleteEmpleado", method = RequestMethod.GET)
	public @ResponseBody List<PermisoEmpleadoFilterViewModel> autocompleteEmpleado(@RequestParam(name="search") String search){
		List<PermisoEmpleadoFilterViewModel> dto = permisoEmpleadoService.autocompleteEmpleado(search);
		return dto;
	}
    
}
