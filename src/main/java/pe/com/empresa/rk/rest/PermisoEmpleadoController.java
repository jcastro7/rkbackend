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

import pe.com.empresa.rk.view.model.PermisoEmpleadoQuickFilterViewModel;
import pe.com.empresa.rk.view.model.PermisoEmpleadoResultViewModel;
import pe.com.empresa.rk.view.model.PermisoEmpleadoFilterViewModel;
import pe.com.empresa.rk.view.model.EmpleadoViewModel;
import pe.com.empresa.rk.view.model.HistoriaLaboralViewModel;
import pe.com.empresa.rk.view.model.LicenciaEmpleadoViewModel;
import pe.com.empresa.rk.view.model.NotificacionViewModel;
import pe.com.empresa.rk.view.model.PeriodoEmpleadoViewModel;
import pe.com.empresa.rk.view.model.PermisoEmpleadoViewModel;
import pe.com.empresa.rk.service.PermisoEmpleadoService;

@RestController
@RequestMapping(value = "/api/permisoEmpleado")
public class PermisoEmpleadoController extends BaseController{
	
	@Autowired
	PermisoEmpleadoService permisoEmpleadoService;

	@RequestMapping(value = "/registrarPermisoEmpleado", method = RequestMethod.POST)
	public @ResponseBody
	NotificacionViewModel registrarPermisoEmpleado(@RequestBody PermisoEmpleadoViewModel permisoEmpleado) {
		
		NotificacionViewModel dto = permisoEmpleadoService.registrarPermisoEmpleado(permisoEmpleado);
		return dto;
	}
	
	@RequestMapping(value = "/actualizarPermisoEmpleado", method = RequestMethod.POST)
	public @ResponseBody
	NotificacionViewModel actualizarPermisoEmpleado(@RequestBody PermisoEmpleadoViewModel permisoEmpleado) {
		
		NotificacionViewModel dto = permisoEmpleadoService.actualizarPermisoEmpleado(permisoEmpleado);
		return dto;
	}
	
	@RequestMapping(value = "/enviarPermisoEmpleado", method = RequestMethod.POST)
	public @ResponseBody
	NotificacionViewModel enviarPermisoEmpleado(@RequestBody PermisoEmpleadoViewModel permisoEmpleado) {
		
		NotificacionViewModel dto = permisoEmpleadoService.enviarPermisoEmpleado(permisoEmpleado);
		return dto;
	}
	
	@RequestMapping(value = "/devolverPermisoEmpleado", method = RequestMethod.POST)
	public @ResponseBody
	NotificacionViewModel devolverPermisoEmpleado(@RequestBody PermisoEmpleadoViewModel permisoEmpleado) {
		
		NotificacionViewModel dto = permisoEmpleadoService.devolverPermisoEmpleado(permisoEmpleado);
		return dto;
	}
	
	@RequestMapping(value = "/aprobarPermisoEmpleado", method = RequestMethod.POST)
	public @ResponseBody
	NotificacionViewModel aprobarPermisoEmpleado(@RequestBody PermisoEmpleadoViewModel permisoEmpleado) {
		
		NotificacionViewModel dto = permisoEmpleadoService.aprobarPermisoEmpleado(permisoEmpleado);
		return dto;
	}
	
	@RequestMapping(value = "/rechazarPermisoEmpleado", method = RequestMethod.POST)
	public @ResponseBody
	NotificacionViewModel rechazarPermisoEmpleado(@RequestBody PermisoEmpleadoViewModel permisoEmpleado) {
		
		NotificacionViewModel dto = permisoEmpleadoService.rechazarPermisoEmpleado(permisoEmpleado);
		return dto;
	}
	
	@RequestMapping(value = "/obtenerHistoriaLaboralActual", method = RequestMethod.POST)
	public @ResponseBody
    HistoriaLaboralViewModel obtenerHistoriaLaboralActual(@RequestBody EmpleadoViewModel empleado) {
		HistoriaLaboralViewModel dto = permisoEmpleadoService.obtenerHistoriaLaboralActual(empleado);
		return dto;
	}
	
	@RequestMapping(value = "/obtenerHistoriaLaboralLicencia", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<HistoriaLaboralViewModel> obtenerHistoriaLaboralLicencia(@RequestBody LicenciaEmpleadoViewModel licenciaEmpleadoD) {
		HistoriaLaboralViewModel dto = permisoEmpleadoService.obtenerHistoriaLaboralLicencia(licenciaEmpleadoD);
		if(dto == null)
			dto = new HistoriaLaboralViewModel();
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	@RequestMapping(value = "/obtenerHistoriasLaboralesActualPorEmpleado", method = RequestMethod.POST)
	public @ResponseBody List<HistoriaLaboralViewModel> obtenerHistoriasLaboralesActualPorEmpleado(@RequestBody EmpleadoViewModel empleado) {
		List<HistoriaLaboralViewModel> dto = permisoEmpleadoService.obtenerHistoriasLaboralesActualPorEmpleado(empleado);
		return dto;
	}
	
	@RequestMapping(value = "/obtenerPeriodoEmpleadoActual", method = RequestMethod.POST)
	public @ResponseBody
	PeriodoEmpleadoViewModel obtenerPeriodoEmpleadoActual(@RequestBody EmpleadoViewModel empleado) {
		PeriodoEmpleadoViewModel dto = permisoEmpleadoService.obtenerPeriodoEmpleadoActual(empleado);
		return dto;
	}
	
	@RequestMapping(value = "/obtenerPermisoEmpleadoDetalle", method = RequestMethod.POST)
	public @ResponseBody
	PermisoEmpleadoViewModel obtenerPermisoEmpleadoDetalle(@RequestBody Long idPermisoEmpleado){
		PermisoEmpleadoViewModel result = new PermisoEmpleadoViewModel();
		result = permisoEmpleadoService.findOne(idPermisoEmpleado);
		if(result == null)
			result = new PermisoEmpleadoViewModel();
		return result;
	}
	
	@RequestMapping(value = "/busquedaPermisoEmpleado", method = RequestMethod.POST)
    public @ResponseBody  ResponseEntity<List<PermisoEmpleadoResultViewModel>>  busquedaPermisoEmpleado(@RequestBody PermisoEmpleadoFilterViewModel busquedaPermisoEmpleadoDto) {

        List<PermisoEmpleadoResultViewModel> result = new ArrayList<PermisoEmpleadoResultViewModel>();
        result = permisoEmpleadoService.search(busquedaPermisoEmpleadoDto);
		if(result == null)
			result = new ArrayList<>();
        return new ResponseEntity<>(result, HttpStatus.OK);

    }
	
	@RequestMapping(value = "/busquedaRapidaPermisoEmpleado", method = RequestMethod.POST)
    public @ResponseBody  ResponseEntity<List<PermisoEmpleadoResultViewModel>>  busquedaRapidaPermisoEmpleado(@RequestBody PermisoEmpleadoQuickFilterViewModel busquedaRapidaPermisoEmpleadoDto) {

        List<PermisoEmpleadoResultViewModel> result = new ArrayList<PermisoEmpleadoResultViewModel>();
        result = permisoEmpleadoService.quickSearch(busquedaRapidaPermisoEmpleadoDto);
		if(result == null)
			result = new ArrayList<>();
        return new ResponseEntity<>(result, HttpStatus.OK);

    }
	
	@RequestMapping(value = "/obtenerHistoriasLaboralesPorEmpleado", method = RequestMethod.POST)
	public @ResponseBody List<HistoriaLaboralViewModel> obtenerHistoriasLaboralesPorEmpleado(@RequestBody Long idEmpleado) {
		List<HistoriaLaboralViewModel> dto = permisoEmpleadoService.obtenerHistoriasLaboralesPorEmpleado(idEmpleado);
		return dto;
	}
	
}