package pe.com.empresa.rk.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.empresa.rk.service.ContratoService;
import pe.com.empresa.rk.view.model.ContratoResultViewModel;
import pe.com.empresa.rk.view.model.ContratoViewModel;
import pe.com.empresa.rk.view.model.HistoriaLaboralViewModel;
import pe.com.empresa.rk.view.model.NotificacionViewModel;

@RestController
@RequestMapping(value = "/api/contrato")
public class ContratoController extends BaseController{
	
	@Autowired
    ContratoService contratoService;

	@Autowired
	private ResourceLoader resourceLoader;


	@RequestMapping(value = "/registrarContrato", method = RequestMethod.POST)
	public @ResponseBody
    NotificacionViewModel registrarContrato(@RequestBody ContratoViewModel contrato) {

		NotificacionViewModel notificacion = new NotificacionViewModel();
		
		
		if(contrato.getIdContrato() == null){
			contrato.setEstado("P");
			notificacion = contratoService.create(contrato);
			
		}else{
			notificacion = contratoService.update(contrato);
		}
		
		return notificacion;
		
	}
	
	@RequestMapping(value = "/obtenerContrato", method = RequestMethod.POST)
	public @ResponseBody
	ContratoViewModel obtenerContrato(@RequestBody Long idContrato) {

		ContratoViewModel result = new ContratoViewModel();
		
		result = contratoService.findOne(idContrato);
		return result;

	}
	
	@RequestMapping(value = "/obtenerContratosPorEmpleado", method = RequestMethod.POST)
	public @ResponseBody List<ContratoViewModel> obtenerContratosPorEmpleado(@RequestBody Long idEmpleado) {

		List<ContratoViewModel> result = new ArrayList<>();
		
		result = contratoService.obtenerContratosPorEmpleado(idEmpleado);
		return result;

	}
	
	@RequestMapping(value = "/busquedaContratosPorEmpleado", method = RequestMethod.POST)
	public @ResponseBody List<ContratoResultViewModel> busquedaContratosPorEmpleado(@RequestBody Long idEmpleado) {

		List<ContratoResultViewModel> result = new ArrayList<>();
		
		result = contratoService.busquedaContratosPorEmpleado(idEmpleado);
		return result;

	}
	
	@RequestMapping(value = "/aprobarContrato", method = RequestMethod.POST)
	public @ResponseBody
    NotificacionViewModel aprobarContrato(@RequestBody ContratoViewModel contrato) {

		NotificacionViewModel notificacion = new NotificacionViewModel();
		contrato.setEstado("T");
		notificacion = contratoService.aprobar(contrato);
		return notificacion;

	}
	
	@RequestMapping(value = "/eliminarContrato", method = RequestMethod.POST)
	public @ResponseBody
    NotificacionViewModel eliminarContrato(@RequestBody Long idContrato) {

		NotificacionViewModel notificacion = new NotificacionViewModel();
		notificacion = contratoService.delete(idContrato);
		return notificacion;

	}
	
	@RequestMapping(value = "/obtenerHistorialLaboralActualPorEmpleado", method = RequestMethod.POST)
	public @ResponseBody
    HistoriaLaboralViewModel obtenerHistorialLaboralActualPorEmpleado(@RequestBody Long idEmpleado) {
		HistoriaLaboralViewModel dto = contratoService.obtenerHistorialLaboralActualPorEmpleado(idEmpleado);
		return dto;
	}
			
}