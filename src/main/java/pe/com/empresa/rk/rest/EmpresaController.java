package pe.com.empresa.rk.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.empresa.rk.view.model.EmpresaResultViewModel;
import pe.com.empresa.rk.view.model.EmpresaViewModel;
import pe.com.empresa.rk.view.model.EmpresaFilterViewModel;
import pe.com.empresa.rk.view.model.NotificacionViewModel;

import pe.com.empresa.rk.service.EmpresaService;

@RestController
@RequestMapping(value = "/api/empresa")
public class EmpresaController extends BaseController{
	
	@Autowired
	EmpresaService empresaService;
	
	@RequestMapping(value = "/obtenerEmpresa", method = RequestMethod.POST)
	public @ResponseBody
    EmpresaViewModel obtenerEmpresa(@RequestBody Long idEmpresa) {

		EmpresaViewModel result = new EmpresaViewModel();
		
		result = empresaService.findOne(idEmpresa);
		
		return result;

	}
	
	@RequestMapping(value = "/registrarEmpresa", method = RequestMethod.POST)
	public @ResponseBody NotificacionViewModel registrarEmpresa(@RequestBody EmpresaViewModel empresa) {

		NotificacionViewModel notificacion = new NotificacionViewModel();
		
		if(empresa.getIdEmpresa()==null){
			notificacion = empresaService.create(empresa);
		}else{
			notificacion = empresaService.update(empresa);
		}
		return notificacion;

	}
	
	@RequestMapping(value = "/obtenerEmpresas", method = RequestMethod.POST)
	public @ResponseBody List<EmpresaResultViewModel> obtenerEmpresas(@RequestBody EmpresaFilterViewModel empresa) {

		List<EmpresaResultViewModel> result = new ArrayList<>();
		
		result = empresaService.search(empresa);
		
		return result;

	}
	
}