package pe.com.tss.runakuna.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.tss.runakuna.view.model.DepartamentoViewModel;
import pe.com.tss.runakuna.view.model.DistritoViewModel;
import pe.com.tss.runakuna.view.model.PaisViewModel;
import pe.com.tss.runakuna.view.model.ProvinciaViewModel;
import pe.com.tss.runakuna.service.DepartamentoService;
import pe.com.tss.runakuna.service.DistritoService;
import pe.com.tss.runakuna.service.PaisService;
import pe.com.tss.runakuna.service.ProvinciaService;

@RestController
@RequestMapping(value="/api/pais")
@CrossOrigin()
public class PaisController extends BaseController{

	@Autowired
	PaisService paisService;
	
	@Autowired
	DepartamentoService departamentoService;
	
	@Autowired
	ProvinciaService provinciaService;
	
	@Autowired
	DistritoService distritoService;
	
	private List<PaisViewModel> resultList = new ArrayList<>();

	@RequestMapping(value = "/obtenerPaises", method = RequestMethod.GET)
	public @ResponseBody List<PaisViewModel> obtenerPaises() {
		
		resultList = paisService.obtenerPaises();
		return resultList;
	}
	
	@RequestMapping(value = "/obtenerDepartamentos", method = RequestMethod.GET)
	public @ResponseBody List<DepartamentoViewModel> obtenerDepartamentos(@RequestParam(name="idPais") Long idPais) {
		List<DepartamentoViewModel> result = null;
		result = departamentoService.obtenerDepartamentosPorPais(idPais);
		if(result == null)
			result = new ArrayList<>();
		return result;
	}
	
	@RequestMapping(value = "/obtenerProvincias", method = RequestMethod.GET)
	public @ResponseBody List<ProvinciaViewModel> obtenerProvincias(@RequestParam(name="idDpto")  Long idDpto) {
		List<ProvinciaViewModel> result = null;
		result = provinciaService.obtenerProvinciasPorDepartamento(idDpto);
		if(result == null)
			result = new ArrayList<>();
		return result;
	}
	
	@RequestMapping(value = "/obtenerDistritos", method = RequestMethod.GET)
	public @ResponseBody List<DistritoViewModel> obtenerDistritos(@RequestParam(name="idProvincia") Long idProvincia) {
		List<DistritoViewModel> result = null;
		result = distritoService.obtenerDistritosPorProvincia(idProvincia);
		if(result == null)
			result = new ArrayList<>();
		return result;
	}
	
	
	
}
