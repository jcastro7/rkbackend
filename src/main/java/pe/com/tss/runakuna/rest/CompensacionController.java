package pe.com.tss.runakuna.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.tss.runakuna.service.CompensacionService;
import pe.com.tss.runakuna.view.model.CompensacionDetalleViewModel;
import pe.com.tss.runakuna.view.model.CompensacionFilterViewModel;
import pe.com.tss.runakuna.view.model.CompensacionQuickFilterViewModel;
import pe.com.tss.runakuna.view.model.CompensacionResultViewModel;
import pe.com.tss.runakuna.view.model.CompensacionViewModel;

@RestController
@RequestMapping(value = "/api/compensacion")
public class CompensacionController extends BaseController{
	
	@Autowired
	CompensacionService compensacionService;


	
	@RequestMapping(value = "/obtenerCompensaciones", method = RequestMethod.POST)
	public @ResponseBody List<CompensacionResultViewModel> obtenerCompensaciones(@RequestBody CompensacionFilterViewModel compensacionFilter) {

		List<CompensacionResultViewModel> result = compensacionService.search(compensacionFilter);
		return result;

	}
	
	@RequestMapping(value = "/busquedaRapidaCompensaciones", method = RequestMethod.POST)
	public @ResponseBody List<CompensacionResultViewModel> busquedaRapidaCompensaciones(@RequestBody CompensacionQuickFilterViewModel compensacionFilter) {

		List<CompensacionResultViewModel> result = compensacionService.quickSearch(compensacionFilter);
		return result;

	}

	
	@RequestMapping(value = "/obtenerCompensacion", method = RequestMethod.POST)
	public @ResponseBody CompensacionViewModel obtenerCompensacion(@RequestBody Long idEmpleadoCompensacion) {

		CompensacionViewModel result = compensacionService.findOne(idEmpleadoCompensacion);

		return result;
	}
	
	@RequestMapping(value = "/obtenerCompensacionDetalle", method = RequestMethod.POST)
	public @ResponseBody CompensacionDetalleViewModel obtenerCompensacionDetalle(@RequestBody Long idEmpleadoCompensacion) {

		CompensacionDetalleViewModel result = compensacionService.findDetalle(idEmpleadoCompensacion);

		return result;
	}
	
	
	
	
	
		
}