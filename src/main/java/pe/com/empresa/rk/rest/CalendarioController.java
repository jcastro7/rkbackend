package pe.com.empresa.rk.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.empresa.rk.service.CalendarioService;
import pe.com.empresa.rk.view.model.CalendarioFilterViewModel;
import pe.com.empresa.rk.view.model.CalendarioViewModel;
import pe.com.empresa.rk.view.model.NotificacionViewModel;
import pe.com.empresa.rk.view.model.CalendarioResultViewModel;

@RestController
@RequestMapping(value = "/api/calendario")
public class CalendarioController extends BaseController{

	private final Logger LOG = LoggerFactory.getLogger(CalendarioController.class);

	@Autowired
    CalendarioService calendarioService;

	@RequestMapping(value = "/diasNoLaborables", method = RequestMethod.POST)
    public @ResponseBody  ResponseEntity<List<CalendarioResultViewModel>>  busquedaHorasExtrasEmpleadoDashboard(@RequestBody CalendarioFilterViewModel dto) {

        List<CalendarioResultViewModel> result = new ArrayList<>();
        result = calendarioService.searchDiasNoLaborables(dto);
		if(result == null)
			result = new ArrayList<>();

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

	@RequestMapping(value = "/obtenerCalendarioDetalle", method = RequestMethod.POST)
	public @ResponseBody
    CalendarioViewModel obtenerCalendarioDetalle(@RequestBody Long idCalendario){
		CalendarioViewModel result = new CalendarioViewModel();
		result = calendarioService.findOne(idCalendario);
		if(result == null)
			result = new CalendarioViewModel();
		LOG.info("Msg obtenerCalendarioDetalle: " + result);
		return result;
	}

	@RequestMapping(value = "/registrarCalendarioFeriado", method = RequestMethod.POST)
    public @ResponseBody
    NotificacionViewModel registrarCalendarioFeriado(@RequestBody CalendarioViewModel calendarioDto) {
    	NotificacionViewModel dto = new NotificacionViewModel();
    	if(calendarioDto.getIdCalendario()==null){
			dto = calendarioService.create(calendarioDto);
		} else {
			dto = calendarioService.update(calendarioDto);
		}
		return dto;
    }

	@RequestMapping(value = "/eliminarCalendario", method = RequestMethod.POST)
	public NotificacionViewModel eliminarVacacionEmpleado(@RequestBody Long idCalendario) {
		NotificacionViewModel notificacionViewModel = new NotificacionViewModel();
		notificacionViewModel= calendarioService.delete(idCalendario);
		return notificacionViewModel;
	}



}
