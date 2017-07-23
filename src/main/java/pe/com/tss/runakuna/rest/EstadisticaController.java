package pe.com.tss.runakuna.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.tss.runakuna.service.DashboardService;
import pe.com.tss.runakuna.service.MarcadorService;
import pe.com.tss.runakuna.service.ProyectoService;
import pe.com.tss.runakuna.view.model.MarcacionesMensualesFilterViewModel;
import pe.com.tss.runakuna.view.model.MarcacionesMensualesViewModel;
import pe.com.tss.runakuna.view.model.MarcadorFilterViewModel;
import pe.com.tss.runakuna.view.model.MarcadorResultViewModel;
import pe.com.tss.runakuna.view.model.MarcadorViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;
import pe.com.tss.runakuna.view.model.ProyectoFilterViewModel;
import pe.com.tss.runakuna.view.model.ProyectoQuickFilterViewModel;
import pe.com.tss.runakuna.view.model.ProyectoResultViewModel;
import pe.com.tss.runakuna.view.model.ProyectoViewModel;

@CrossOrigin
@RestController
@RequestMapping("/api/estadistica")
public class EstadisticaController extends BaseController {
	
	@Autowired
	DashboardService dashboardService;
	
	private final Logger LOG = LoggerFactory.getLogger(EstadisticaController.class);
	
	
	 
	 @RequestMapping(value = "/obtenerMarcacionesMensuales", method = RequestMethod.POST)
	    public @ResponseBody ResponseEntity<List<MarcacionesMensualesViewModel>> obtenerMarcacionesMensuales(@RequestBody MarcacionesMensualesFilterViewModel dto){
	    	List<MarcacionesMensualesViewModel> result = new ArrayList<MarcacionesMensualesViewModel>();
			result = dashboardService.obtenerMarcacionesMensuales(dto);
			if(result == null)
				result = new ArrayList<>();
			return new ResponseEntity<>(result, HttpStatus.OK);
	}
	 
	
	
	 
}
