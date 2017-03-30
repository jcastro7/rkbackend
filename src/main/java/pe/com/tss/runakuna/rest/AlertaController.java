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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.tss.runakuna.service.AlertaService;
import pe.com.tss.runakuna.view.model.AlertaEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.AlertaFilterViewModel;
import pe.com.tss.runakuna.view.model.AlertaResultViewModel;
import pe.com.tss.runakuna.view.model.AlertaViewModel;
import pe.com.tss.runakuna.view.model.GeneraMsjeAlertaEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;

/**
 * Created by josediaz on 14/12/2016.
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/api/alerta")
public class AlertaController extends BaseController{

	private final Logger LOG = LoggerFactory.getLogger(AlertaController.class);
	
    @Autowired
    AlertaService alertaService;

    @RequestMapping(value = "/alertaByCodigo", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public AlertaViewModel getAlertaByCodigo(@RequestParam(name="codigo")  String codigo) {

        return alertaService.obtenerAlerta(codigo);
    }
    
    @RequestMapping(value = "/registrarAlerta", method = RequestMethod.POST)
    public @ResponseBody
	NotificacionViewModel registrarAlerta(@RequestBody AlertaViewModel alertaDto) {
    	NotificacionViewModel dto = new NotificacionViewModel();
    	if(alertaDto.getIdAlerta()==null){
			dto = alertaService.create(alertaDto);
		} else {
			dto = alertaService.update(alertaDto);
		}
		return dto;
    }
    
    @RequestMapping(value = "/obtenerAlertas", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<List<AlertaResultViewModel>> obtenerAlertas(@RequestBody AlertaFilterViewModel dto){
    	List<AlertaResultViewModel> result = new ArrayList<AlertaResultViewModel>();
		result = alertaService.search(dto);
		if(result == null)
			result = new ArrayList<>();
		LOG.info("Msg obtenerAlertas: " + result);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
    
    @RequestMapping(value = "/obtenerAlertaDetalle", method = RequestMethod.POST)
	public @ResponseBody
	AlertaViewModel obtenerLicencia(@RequestBody Long idAlerta){
    	AlertaViewModel result = new AlertaViewModel();
		result = alertaService.findOne(idAlerta);
		if(result == null)
			result = new AlertaViewModel();
		LOG.info("Msg obtenerAlerta: " + result);
		return result;
	}
    
    @RequestMapping(value = "/obtenerMensajeAlerta", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<List<AlertaEmpleadoViewModel>> obtenerMensajeAlerta(@RequestBody AlertaEmpleadoViewModel dto){
    	List<AlertaEmpleadoViewModel> result = new ArrayList<AlertaEmpleadoViewModel>();
		result = alertaService.obtenerMensajeAlerta(dto.getIdEmpleado());
		if(result == null)
			result = new ArrayList<AlertaEmpleadoViewModel>();
		LOG.info("Msg obtenerAlerta: " + result);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
    
    @RequestMapping(value = "/generarMensajeAlerta", method = RequestMethod.POST)
    public @ResponseBody
	NotificacionViewModel generarMensajeAlerta(@RequestBody GeneraMsjeAlertaEmpleadoViewModel msjeAlertaDto) {
    	NotificacionViewModel dto = new NotificacionViewModel();
    	dto=alertaService.generarMensajeAlerta(msjeAlertaDto);
		return dto;
    }
    /*@RequestMapping(value = "/obtenerAlertaDetalle", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<List<AlertaViewModel>> obtenerSubscriptoresAlertas(@RequestBody AlertaViewModel dto){
    	List<AlertaViewModel> result = new ArrayList<AlertaViewModel>();
		result = alertaService.obtenerSubscriptoresAlertas(dto);
		if(result == null)
			result = new ArrayList<>();
		LOG.info("Msg obtenerSubscriptoresAlertas: " + result);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}*/

}
