package pe.com.tss.runakuna.rest;

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

import pe.com.tss.runakuna.view.model.PermisoEmpleadoFilterViewModel;
import pe.com.tss.runakuna.view.model.PermisoEmpleadoQuickFilterViewModel;
import pe.com.tss.runakuna.view.model.PermisoEmpleadoResultViewModel;
import pe.com.tss.runakuna.view.model.EmpleadoCompensacionViewModel;
import pe.com.tss.runakuna.view.model.EmpleadoViewModel;
import pe.com.tss.runakuna.view.model.HistoriaLaboralViewModel;
import pe.com.tss.runakuna.view.model.HorarioEmpleadoDiaViewModel;
import pe.com.tss.runakuna.view.model.LicenciaEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;
import pe.com.tss.runakuna.view.model.PeriodoEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.PermisoEmpleadoViewModel;
import pe.com.tss.runakuna.service.PermisoEmpleadoService;

@RestController
@RequestMapping(value = "/api/permisoEmpleado")
public class PermisoEmpleadoController extends BaseController{
	
	@Autowired
	PermisoEmpleadoService permisoEmpleadoService;

	@RequestMapping(value = "/verPermisoEmpleado", method = RequestMethod.POST)
	public @ResponseBody List<PermisoEmpleadoViewModel> verPermisoEmpleado(@RequestBody PeriodoEmpleadoViewModel permisoEmpleado) {
		List<PermisoEmpleadoViewModel> dto;
	    dto = permisoEmpleadoService.verPermisoEmpleado(permisoEmpleado);
	    
	    return dto;
	    
	}
	
	
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
	
	@RequestMapping(value = "/guardarPendientePermisoEmpleado", method = RequestMethod.POST)
	public @ResponseBody
	NotificacionViewModel guardarPendientePermisoEmpleado(@RequestBody PermisoEmpleadoViewModel permisoEmpleado) {
		
		NotificacionViewModel dto = permisoEmpleadoService.guardarPendientePermisoEmpleado(permisoEmpleado);
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
	
	@RequestMapping(value = "/obtenerHorasPorCompensarPorEmpleado", method = RequestMethod.POST)
	public @ResponseBody EmpleadoCompensacionViewModel obtenerHorasPorCompensarPorEmpleado(@RequestBody Long idEmpleado) {
		EmpleadoCompensacionViewModel dto = permisoEmpleadoService.obtenerHorasPorCompensarPorEmpleado(idEmpleado);
		return dto;
	}
	
	@RequestMapping(value = "/eliminarPermisoEmpleado", method = RequestMethod.POST)
    public NotificacionViewModel eliminarPermisoEmpleado(@RequestBody PermisoEmpleadoViewModel permisoEmpleadoDto) {

    	NotificacionViewModel notificacion =  permisoEmpleadoService.delete(permisoEmpleadoDto.getIdPermisoEmpleado());

        return notificacion;
    }
	
	@RequestMapping(value = "/obtenerHorarioEmpleadoDia", method = RequestMethod.POST)
	public HorarioEmpleadoDiaViewModel obtenerHorarioEmpleadoDia(@RequestBody PermisoEmpleadoViewModel permisoEmpleadoDto) {
		
		HorarioEmpleadoDiaViewModel horarioEmpleadoDia =  permisoEmpleadoService.obtenerHorarioEmpleadoDia(permisoEmpleadoDto);
		
		return horarioEmpleadoDia;
	}
	
	
}