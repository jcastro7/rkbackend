package pe.com.empresa.rk.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.empresa.rk.service.HoraExtraService;
import pe.com.empresa.rk.view.model.EmpleadoViewModel;
import pe.com.empresa.rk.view.model.HorasExtraEmpleadoFilterViewModel;
import pe.com.empresa.rk.view.model.HorasExtraEmpleadoQuickFilterViewModel;
import pe.com.empresa.rk.view.model.HorasExtraEmpleadoResultViewModel;
import pe.com.empresa.rk.view.model.HorasExtraViewModel;
import pe.com.empresa.rk.view.model.NotificacionViewModel;
import pe.com.empresa.rk.view.model.PeriodoEmpleadoViewModel;

@RestController
@RequestMapping(value = "/api/horasExtra")
public class HorasExtraController extends BaseController{
	
	@Autowired
	HoraExtraService horaExtraService;
	
	
	   @RequestMapping(value = "/obtenerHorasExtrasEmpleado", method = RequestMethod.POST)
	    public @ResponseBody  ResponseEntity<List<HorasExtraEmpleadoResultViewModel>>
	   busquedaHorasExtrasEmpleado(@RequestBody HorasExtraEmpleadoFilterViewModel busquedaHorasExtraEmpleadoDto) {

	        List<HorasExtraEmpleadoResultViewModel> result = new ArrayList<>();
	        result = horaExtraService.search(busquedaHorasExtraEmpleadoDto);
			if(result == null)
				result = new ArrayList<>();

	        return new ResponseEntity<>(result, HttpStatus.OK);

	    }
	   
	   @RequestMapping(value = "/busquedaRapidaHorasExtrasEmpleado", method = RequestMethod.POST)
	    public @ResponseBody  ResponseEntity<List<HorasExtraEmpleadoResultViewModel>>	busquedaRapidaHorasExtrasEmpleado
	    (@RequestBody HorasExtraEmpleadoQuickFilterViewModel busquedaHorasExtraEmpleadoDto) {

	        List<HorasExtraEmpleadoResultViewModel> result = new ArrayList<>();
	        result = horaExtraService.quickSearch(busquedaHorasExtraEmpleadoDto);
			if(result == null)
				result = new ArrayList<>();

	        return new ResponseEntity<>(result, HttpStatus.OK);

	    }
	   
	   @RequestMapping(value = "/verHorasExtras", method = RequestMethod.POST)
		public @ResponseBody List<HorasExtraViewModel> verHorasExtras(@RequestBody PeriodoEmpleadoViewModel permisoEmpleado) {
			List<HorasExtraViewModel> dto = new ArrayList<>();
		    dto = horaExtraService.verHorasExtras(permisoEmpleado);
		    
		    return dto;
		    
		}
	    
	    @RequestMapping(value = "/registrarHorasExtra", method = RequestMethod.POST)
		public @ResponseBody
	    NotificacionViewModel registrarHorasExtra(@RequestBody HorasExtraViewModel horasExtraDto) {

			NotificacionViewModel dto = horaExtraService.create(horasExtraDto);
			return dto;

		}
	    
	    
	    @RequestMapping(value = "/actualizarHoraExtraEmpleado", method = RequestMethod.POST)
		public @ResponseBody
	    NotificacionViewModel actualizarHoraExtraEmpleado(@RequestBody HorasExtraViewModel horasExtraDto) {

			NotificacionViewModel dto = horaExtraService.update(horasExtraDto);
			return dto;

		}
	    
	    @RequestMapping(value = "/recuperarHoras", method = RequestMethod.POST)
		public @ResponseBody
	    NotificacionViewModel recuperarHoras(@RequestBody HorasExtraViewModel horasExtraDto) {

			NotificacionViewModel dto = horaExtraService.registrarRecuperarHoras(horasExtraDto);
			return dto;

		}
	    
	    @RequestMapping(value = "/eliminarHorasExtraEmpleado", method = RequestMethod.POST)
	    public NotificacionViewModel eliminarHorasExtraEmpleado(@RequestBody Long idHorasExtra) {
	    	NotificacionViewModel notificacion=new NotificacionViewModel();
	        notificacion=  horaExtraService.delete(idHorasExtra);
	        return notificacion;

	        
	    }
	    
	    @RequestMapping(value = "/aprobarHorasExtraEmpleado", method = RequestMethod.POST)
	    public @ResponseBody
	    NotificacionViewModel aprobarHorasExtraEmpleado(@RequestBody HorasExtraViewModel horasExtraDto) {

	    	NotificacionViewModel dto =  horaExtraService.aprobarHorasExtra(horasExtraDto);

	        return dto;
	    }
	    
	    @RequestMapping(value = "/rechazarHorasExtraEmpleado", method = RequestMethod.POST)
	    public @ResponseBody
	    NotificacionViewModel rechazarHorasExtraEmpleado(@RequestBody HorasExtraViewModel horasExtraDto) {

	    	NotificacionViewModel dto =  horaExtraService.rechazarHorasExtra(horasExtraDto);

	        return dto;
	    }
	    
		@RequestMapping(value = "/informacionAdicionalHorasExtras", method = RequestMethod.POST)
		public @ResponseBody
	    HorasExtraViewModel informacionAdicionalHorasExtras(@RequestBody EmpleadoViewModel empleado) {
			HorasExtraViewModel result = horaExtraService.informacionAdicionalHorasExtras(empleado);
			return result;
		}
		
		 @RequestMapping(value = "/obtenerHorasExtrasEmpleadoDetalle", method = RequestMethod.POST)
			public @ResponseBody
		 HorasExtraViewModel obtenerCargoDetalle(@RequestBody Long idHorasExtra){
			 HorasExtraViewModel result = new HorasExtraViewModel();
				result = horaExtraService.findOne(idHorasExtra);
				if(result == null)
					result = new HorasExtraViewModel();
				
				return result;
		}
		 
		@RequestMapping(value = "/obtenerHorasSemanalesPendientes", method = RequestMethod.POST)
		public @ResponseBody	HorasExtraViewModel obtenerHorasSemanalesPendientes(@RequestBody Long idEmpleado) {
				HorasExtraViewModel result = horaExtraService.obtenerHorasSemanalesPendientes(idEmpleado);
				return result;
		}

}
