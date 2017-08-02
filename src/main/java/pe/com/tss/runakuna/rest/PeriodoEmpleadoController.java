package pe.com.tss.runakuna.rest;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.tss.runakuna.service.PeriodoEmpleadoService;
import pe.com.tss.runakuna.view.model.PeriodoEmpleadoFilterViewModel;
import pe.com.tss.runakuna.view.model.PeriodoEmpleadoResultViewModel;

@RestController
@RequestMapping(value = "/api/periodoEmpleado")
public class PeriodoEmpleadoController extends BaseController{
	
	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	PeriodoEmpleadoService periodoEmpleadoService;


	@RequestMapping(value = "/busquedaPeriodoEmpleado", method = RequestMethod.POST)
    public @ResponseBody  ResponseEntity<List<PeriodoEmpleadoResultViewModel>>  busquedaPeriodoEmpleado(@RequestBody PeriodoEmpleadoFilterViewModel busquedaPeriodoEmpleadoDto) {

        List<PeriodoEmpleadoResultViewModel> result;
        result = periodoEmpleadoService.busquedaPeriodoEmpleado(busquedaPeriodoEmpleadoDto);
		if(result == null)
			result = new ArrayList<>();
        return new ResponseEntity<>(result, HttpStatus.OK);

    }
		
	@RequestMapping(value = "/obtenerPeriodosConVacacionesDisponibles", method = RequestMethod.POST)
    public @ResponseBody List<PeriodoEmpleadoResultViewModel> obtenerPeriodosConVacacionesDisponibles(@RequestBody Long idEmpleado) {

        List<PeriodoEmpleadoResultViewModel> result;
        result = periodoEmpleadoService.obtenerPeriodosConVacacionesDisponibles(idEmpleado);
        return result;

    }
	
	@RequestMapping(value = "/busquedaPeriodoDisponibleVacaciones", method = RequestMethod.POST)
    public @ResponseBody List<PeriodoEmpleadoResultViewModel> busquedaPeriodoDisponibleVacaciones(@RequestBody Long idEmpleado) {

        List<PeriodoEmpleadoResultViewModel> result;
        result = periodoEmpleadoService.busquedaPeriodoDisponibleVacaciones(idEmpleado);
        return result;

    }
	
	@RequestMapping(value = "/busquedaTipoLicencia", method = RequestMethod.POST)
	public @ResponseBody  ResponseEntity<List<PeriodoEmpleadoResultViewModel>>  busquedaTipoLicencia(@RequestBody Long idPeriodoEmpleado) {
		
		List<PeriodoEmpleadoResultViewModel> result;
		result = periodoEmpleadoService.busquedaTipoLicencia(idPeriodoEmpleado);
		if(result == null)
			result = new ArrayList<>();
		return new ResponseEntity<>(result, HttpStatus.OK);
		
	}	

}
