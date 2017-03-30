package pe.com.empresa.rk.rest;

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

import pe.com.empresa.rk.service.CargoService;
import pe.com.empresa.rk.view.model.CargoResultViewModel;
import pe.com.empresa.rk.view.model.CargoViewModel;
import pe.com.empresa.rk.view.model.HorarioViewModel;
import pe.com.empresa.rk.domain.model.repository.jpa.HistorialLaboralJpaRepository;
import pe.com.empresa.rk.view.model.CargoFilterViewModel;
import pe.com.empresa.rk.view.model.CargoQuickFilterViewModel;
import pe.com.empresa.rk.view.model.HistoriaLaboralViewModel;
import pe.com.empresa.rk.view.model.HistorialLaboralViewModel;
import pe.com.empresa.rk.view.model.NotificacionViewModel;

@CrossOrigin
@RestController
@RequestMapping("/api/cargo")
public class CargoController extends BaseController{
	
	@Autowired
    CargoService cargoService;
	
	@Autowired
	HistorialLaboralJpaRepository historialLaboralJpaRepository;
	
	private final Logger LOG = LoggerFactory.getLogger(CargoController.class);
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<String> create(@RequestBody CargoViewModel dto) {

		String dataCargoProcess = cargoService.save(dto);

		return   new ResponseEntity<String>( dataCargoProcess , HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/findAll", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<CargoViewModel> departamentos() {

		return cargoService.getCargo();
	}
	
	@RequestMapping(value = "/nombreHorario", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<HorarioViewModel> obtenerNombreHorario() {

		return cargoService.getObtenerNombreHorario();
	}
	
	@RequestMapping(value="/actualizarCargo", method = RequestMethod.POST)
	public @ResponseBody
	NotificacionViewModel actualizarCargo(@RequestBody HistorialLaboralViewModel historialLaboraldto){
		
		NotificacionViewModel dto = cargoService.actualizarCargo(historialLaboraldto);
		
		return dto;
		
	}
	
	@RequestMapping(value="/crearCargo", method = RequestMethod.POST)
		public @ResponseBody
	NotificacionViewModel crearCargo(@RequestBody HistoriaLaboralViewModel historiaLaboralDto){
		
		NotificacionViewModel dto = cargoService.crearCargo(historiaLaboralDto);
		return dto;
		
	}
	
	@RequestMapping(value = "/eliminarCargo/{idHistorialLaboral}")
	public @ResponseBody NotificacionViewModel eliminarCargo(@PathVariable Long idHistorialLaboral) {
		NotificacionViewModel dto = cargoService.eliminarCargo(idHistorialLaboral);

		return dto;
	}
	
	/**
	 * OACP
	 */
	
	@RequestMapping(value = "/registrarCargo", method = RequestMethod.POST)
    public @ResponseBody
	NotificacionViewModel registrarCargo(@RequestBody CargoViewModel cargoDto) {
    	NotificacionViewModel dto = new NotificacionViewModel();
    	if(cargoDto.getIdCargo()==null){
			dto = cargoService.create(cargoDto);
		} else {
			dto = cargoService.update(cargoDto);
		}
		return dto;
    }
	
	@RequestMapping(value = "/obtenerCargoDetalle", method = RequestMethod.POST)
	public @ResponseBody CargoViewModel obtenerCargoDetalle(@RequestBody Long idCargo){
	    	CargoViewModel result = new CargoViewModel();
			result = cargoService.findOne(idCargo);
			if(result == null)
				result = new CargoViewModel();
			LOG.info("Msg obtenerCargoDetalle: " + result);
			return result;
	}
	 
	 @RequestMapping(value = "/obtenerCargos", method = RequestMethod.POST)
	    public @ResponseBody ResponseEntity<List<CargoResultViewModel>> obtenerCargos(@RequestBody CargoFilterViewModel dto){
	    	List<CargoResultViewModel> result = new ArrayList<CargoResultViewModel>();
			result = cargoService.search(dto);
			if(result == null)
				result = new ArrayList<>();
			LOG.info("Msg obtenerCargos: " + result);
			return new ResponseEntity<>(result, HttpStatus.OK);
	}
	 
	 @RequestMapping(value = "/busquedaRapidaCargos", method = RequestMethod.POST)
	    public @ResponseBody ResponseEntity<List<CargoResultViewModel>> busquedaRapidaCargos(@RequestBody CargoQuickFilterViewModel dto){
	    	List<CargoResultViewModel> result = new ArrayList<CargoResultViewModel>();
			result = cargoService.quickSearch(dto);
			if(result == null)
				result = new ArrayList<>();
			LOG.info("Msg obtenerCargos: " + result);
			return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	 @RequestMapping(value = "/eliminarCargo", method = RequestMethod.POST)
		public NotificacionViewModel eliminarCargoBanda(@RequestBody Long idCargo) {
		 NotificacionViewModel notificacionViewModel = new NotificacionViewModel();
		 notificacionViewModel = cargoService.delete(idCargo);
			return  notificacionViewModel;
		}

}
