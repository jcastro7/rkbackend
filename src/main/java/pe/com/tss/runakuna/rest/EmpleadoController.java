package pe.com.tss.runakuna.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.tss.runakuna.view.model.*;
import pe.com.tss.runakuna.service.EmpleadoService;
import pe.com.tss.runakuna.service.MarcacionEmpleadoService;
import pe.com.tss.runakuna.service.PeriodoEmpleadoService;

import java.util.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/empleado")
public class EmpleadoController extends BaseController{
	
	private static final Logger LOG = LoggerFactory.getLogger(EmpleadoController.class);
    private static String OS = null;

	@Autowired
	EmpleadoService empleadoService;
	
	@Autowired
	MarcacionEmpleadoService marcacionEmpleadoService;

	@Autowired
	PeriodoEmpleadoService periodoEmpleadoService;
	    
    @RequestMapping(value = "/busquedaHorasExtrasEmpleado", method = RequestMethod.POST)
    public @ResponseBody  ResponseEntity<List<HorasExtraViewModel>>  busquedaHorasExtrasEmpleado(@RequestBody HorasExtraEmpleadoFilterViewModel busquedaHorasExtraEmpleadoDto) {

        List<HorasExtraViewModel> result;
        result = empleadoService.busquedaHorasExtrasEmpleado(busquedaHorasExtraEmpleadoDto);
		if(result == null)
			result = new ArrayList<>();

        return new ResponseEntity<>(result, HttpStatus.OK);

    }
    
    @RequestMapping(value = "/registrarHorasExtra", method = RequestMethod.POST)
	public @ResponseBody
    NotificacionViewModel registrarHorasExtra(@RequestBody HorasExtraViewModel horasExtraDto) {

		NotificacionViewModel dto = empleadoService.registrarHorasExtra(horasExtraDto);
		return dto;

	}
    
    @RequestMapping(value = "/eliminarHorasExtraEmpleado", method = RequestMethod.POST)
    public NotificacionViewModel eliminarHorasExtraEmpleado(@RequestBody Long idHorasExtra) {

    	NotificacionViewModel notificacion =  empleadoService.eliminarHorasExtra(idHorasExtra);

        return notificacion;
    }
    
    @RequestMapping(value = "/aprobarHorasExtraEmpleado", method = RequestMethod.POST)
    public @ResponseBody
    NotificacionViewModel aprobarHorasExtraEmpleado(@RequestBody HorasExtraViewModel horasExtraDto) {

    	NotificacionViewModel dto =  empleadoService.aprobarHorasExtra(horasExtraDto);

        return dto;
    }
    
    @RequestMapping(value = "/rechazarHorasExtraEmpleado", method = RequestMethod.POST)
    public @ResponseBody
    NotificacionViewModel rechazarHorasExtraEmpleado(@RequestBody HorasExtraViewModel horasExtraDto) {

    	NotificacionViewModel dto =  empleadoService.rechazarHorasExtra(horasExtraDto);

        return dto;
    }
    
	@RequestMapping(value = "/informacionAdicionalHorasExtras", method = RequestMethod.POST)
	public @ResponseBody
    HorasExtraViewModel informacionAdicionalHorasExtras(@RequestBody EmpleadoViewModel empleado) {
		HorasExtraViewModel result = empleadoService.informacionAdicionalHorasExtras(empleado);
		return result;
	}

	@RequestMapping(value = "/busquedaMarcacionesEmpleado", method = RequestMethod.POST)
	public @ResponseBody  ResponseEntity<List<MarcacionViewModel>>  busquedaMarcacionesEmpleado(@RequestBody MarcacionFilterViewModel busquedaMarcacionDto) {

		List<MarcacionViewModel> result ;
		result = empleadoService.busquedaMarcacionesEmpleado(busquedaMarcacionDto);
		if(result == null)
			result = new ArrayList<>();
		
		return new ResponseEntity<>(result, HttpStatus.OK);

	}
	@RequestMapping(value = "/busquedaEmpleado", method = RequestMethod.POST)
	public @ResponseBody  ResponseEntity<List<EmpleadoResultViewModel>>  busquedaEmpleado(@RequestBody EmpleadoFilterViewModel empleadoFilter) {

		List<EmpleadoResultViewModel> empleadoResult;
		empleadoResult = empleadoService.search(empleadoFilter);
		if(empleadoResult == null)
			empleadoResult = new ArrayList<>();
		
		return new ResponseEntity<>(empleadoResult, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/busquedaRapidaEmpleado", method = RequestMethod.POST)
	public @ResponseBody  ResponseEntity<List<EmpleadoResultViewModel>>  busquedaRapidaEmpleado(@RequestBody EmpleadoQuickFilterViewModel quickFilter) {

		List<EmpleadoResultViewModel> empleadoResult ;
		empleadoResult = empleadoService.quickSearch(quickFilter); 
		if(empleadoResult == null)
			empleadoResult = new ArrayList<>();
		
		return new ResponseEntity<>(empleadoResult, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/busquedaEmpleadoExport", method = RequestMethod.POST)
	public @ResponseBody  ResponseEntity<List<EmpleadoViewModel>>  busquedaEmpleadoExport(@RequestBody EmpleadoFilterViewModel empleadoFilter) {

		List<EmpleadoViewModel> empleadoResult ;
		empleadoResult = empleadoService.searchExport(empleadoFilter);
		if(empleadoResult == null)
			empleadoResult = new ArrayList<>();
		
		return new ResponseEntity<>(empleadoResult, HttpStatus.OK);

	}

	
	@RequestMapping(value = "/busquedaCodigoPermiso", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PermisoEmpleadoViewModel>> obtenerCodigoPermiso(
			@RequestParam(name = "codigo", required = true) String codigo) {
		List<PermisoEmpleadoViewModel> result;
		result = empleadoService.obtenerCodigoPermiso(codigo);
		if (result == null)
			result = new ArrayList<>();
		LOG.info("Msg obtenerCodigoPermiso: " + result);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/registrarEmpleado", method = RequestMethod.POST)
	public @ResponseBody
    NotificacionViewModel registrarEmpleado(@RequestBody EmpleadoViewModel empleado) {
		
		NotificacionViewModel notificacion;

		if(empleado.getIdEmpleado() == null){
			empleado.setEstado("A");
			empleado.setFechaIngreso(new Date());
			notificacion = empleadoService.create(empleado);
			periodoEmpleadoService.registrarPeriodoEmpleado(empleado);
			
		}else{
			notificacion = empleadoService.update(empleado);
		}
		
		return notificacion;
	}


    @RequestMapping(value = "/registrarFotoEmpleado", method = RequestMethod.POST)
    public @ResponseBody
    NotificacionViewModel registrarFotoEmpleado(@RequestBody EmpleadoViewModel empleado) {

        NotificacionViewModel notificacion;
        notificacion = empleadoService.registrarFotoEmpleado(empleado);

        return notificacion;
    }
	
	@RequestMapping(value = "/eliminarEmpleado", method = RequestMethod.POST)
	public @ResponseBody
    NotificacionViewModel eliminarEmpleado(@RequestBody Long idEmpleado) {
		
		NotificacionViewModel notificacion;
		notificacion = empleadoService.delete(idEmpleado);
		
		return notificacion;
	}
	
	@RequestMapping(value = "/registrarDarBajaEmpleado", method = RequestMethod.POST)
	public @ResponseBody
    NotificacionViewModel registrarDarBajaEmpleado(@RequestBody EmpleadoViewModel empleado) {
		
		NotificacionViewModel notificacion;
		
		notificacion = empleadoService.registrarDarBajaEmpleado(empleado);
		
		return notificacion;
	}
		
	@RequestMapping(value = "/obtenerEmpleado", method = RequestMethod.POST)
	public @ResponseBody EmpleadoViewModel verEmpleado(@RequestBody Long idEmpleado) {
		EmpleadoViewModel empleado = empleadoService.findOne(idEmpleado);
		return empleado;
	}
	
	@RequestMapping(value = "/obtenerEmpleadoCabecera", method = RequestMethod.POST)
	public @ResponseBody EmpleadoViewModel obtenerEmpleadoCabecera(@RequestBody Long idEmpleado) {
		EmpleadoViewModel empleado = empleadoService.obtenerEmpleadoCabecera(idEmpleado);
		return empleado;
	}
	
	
	@RequestMapping(value = "/actualizarDatosPersonales", method = RequestMethod.POST)
	public @ResponseBody
    NotificacionViewModel actualizarDatosPersonales(@RequestBody EmpleadoViewModel empleado) {
		
		NotificacionViewModel notificacion;
		
		notificacion = empleadoService.actualizarDatosPersonales(empleado);
		
		return notificacion;
	}
	
	@RequestMapping(value = "/verDocumentos", method = RequestMethod.POST)
	public @ResponseBody List<DocumentoEmpleadoViewModel> verDocumentos(@RequestBody Long idEmpleado) {
		List<DocumentoEmpleadoViewModel> dto = empleadoService.verDocumentos(idEmpleado);
		return dto;
	}
	
	@RequestMapping(value = "/verDocumentosSF", method = RequestMethod.POST)
	public @ResponseBody List<DocumentoEmpleadoViewModel> verDocumentosSF(@RequestBody Long idEmpleado) {
		List<DocumentoEmpleadoViewModel> dto = empleadoService.verDocumentos(idEmpleado);
		List<DocumentoEmpleadoViewModel> filtrado=new ArrayList<DocumentoEmpleadoViewModel>();
		for(DocumentoEmpleadoViewModel de:dto) {
			if(!"FO".equalsIgnoreCase(de.getTipoDocumento())){
				filtrado.add(de);
			}
		}
		return filtrado;
	}
	
	@RequestMapping(value = "/verEducacion", method = RequestMethod.POST)
	public @ResponseBody List<EducacionViewModel> verEducacion(@RequestBody Long idEmpleado) {
		List<EducacionViewModel> dto = empleadoService.verEducacion(idEmpleado);
		return dto;
	}
	
	@RequestMapping(value = "/verExperienciaLaboral", method = RequestMethod.POST)
	public @ResponseBody List<ExperienciaLaboralViewModel> verExperienciaLaboral(@RequestBody Long idEmpleado) {
		List<ExperienciaLaboralViewModel> dto = empleadoService.verExperienciaLaboral(idEmpleado);
		return dto;
	}
	
	@RequestMapping(value = "/verEquipoEntregado", method = RequestMethod.POST)
	public @ResponseBody List<EquipoEntregadoViewModel> verEquipoEntregado(@RequestBody Long idEmpleado) {
		List<EquipoEntregadoViewModel> dto = empleadoService.verEquipoEntregado(idEmpleado);
		return dto;
	}
	
	@RequestMapping(value = "/verDependiente", method = RequestMethod.POST)
	public @ResponseBody List<DependienteViewModel> verDependiente(@RequestBody Long idEmpleado) {
		List<DependienteViewModel> dto = empleadoService.verDependiente(idEmpleado);
		return dto;
	}
	
	@RequestMapping(value = "/verLicencia", method = RequestMethod.POST)
	public @ResponseBody List<LicenciaViewModel> verLicencia(@RequestBody PeriodoEmpleadoViewModel periodoEmpleado) {
		List<LicenciaViewModel> dto = empleadoService.verLicencia(periodoEmpleado);
		return dto;
	}
	
	@RequestMapping(value = "/verHorarioEmpleado", method = RequestMethod.POST)
	public @ResponseBody
	HorarioEmpleadoViewModel verHorarioEmpleado(@RequestBody Long idEmpleado) {
		HorarioEmpleadoViewModel dto = empleadoService.verHorarioEmpleado(idEmpleado);
		return dto;
	}
	
	@RequestMapping(value = "/verHorariosEmpleado", method = RequestMethod.POST)
	public @ResponseBody List<HorarioEmpleadoViewModel> verHorariosEmpleado(@RequestBody Long idEmpleado) {
		List<HorarioEmpleadoViewModel> dto = empleadoService.verHorariosEmpleado(idEmpleado);
		return dto;
	}
	
	@RequestMapping(value = "/verHistoriaLaboral", method = RequestMethod.POST)
	public @ResponseBody List<HistoriaLaboralViewModel> verHistoriaLaboral(@RequestBody Long idEmpleado) {
		List<HistoriaLaboralViewModel> dto;
	    dto = empleadoService.obtenerHistoriaLaboral(idEmpleado);
	    
	    return dto;
	    
	}
	
	@RequestMapping(value = "/verPeriodoEmpleado", method = RequestMethod.POST)
	public @ResponseBody List<PeriodoEmpleadoViewModel> verPeriodoEmpleado(@RequestBody Long idEmpleado) {
		List<PeriodoEmpleadoViewModel> dto;
	    dto = empleadoService.verPeriodoEmpleado(idEmpleado);
	    
	    return dto;
	    
	}
	
	@RequestMapping(value = "/verPermisoEmpleado", method = RequestMethod.POST)
	public @ResponseBody List<PermisoEmpleadoViewModel> verPermisoEmpleado(@RequestBody PeriodoEmpleadoViewModel permisoEmpleado) {
		List<PermisoEmpleadoViewModel> dto;
	    dto = empleadoService.verPermisoEmpleado(permisoEmpleado);
	    
	    return dto;
	    
	}
	
	@RequestMapping(value = "/verVacaciones", method = RequestMethod.POST)
	public @ResponseBody List<VacacionEmpleadoViewModel> verVacaciones(@RequestBody PeriodoEmpleadoViewModel permisoEmpleado) {
		List<VacacionEmpleadoViewModel> dto;
	    dto = empleadoService.verVacacion(permisoEmpleado);
	    
	    return dto;
	    
	}
	
	@RequestMapping(value = "/verMarcaciones", method = RequestMethod.POST)
	public @ResponseBody List<MarcacionViewModel> verMarcaciones(@RequestBody Long idEmpleado) {
		List<MarcacionViewModel> dto;
	    dto = empleadoService.verMarcacion(idEmpleado);
	    
	    return dto;
	    
	}
	    
    @RequestMapping(value = "/historiaLaboral", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<HistoriaLaboralViewModel>> obtenerHistoriaLaboral(@RequestParam(name = "idEmpleado", required = true) Long idEmpleado) {

        List<HistoriaLaboralViewModel> result ;
        result = empleadoService.obtenerHistoriaLaboral(idEmpleado);
        if (result == null)
            result = new ArrayList<>();
        LOG.info("Msg obtenerHistoriaLaboral: " + result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/obtenerEquiposPendientesDevolucion", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EquipoEntregadoViewModel>> obtenerEquiposPendientesDevolucion(
            @RequestParam(name = "idEmpleado", required = true) Long idEmpleado) {

        List<EquipoEntregadoViewModel> result;
        result = empleadoService.obtenerEquiposPendientesDevolucion(idEmpleado);
        if (result == null)
            result = new ArrayList<>();
        LOG.info("Msg obtenerHistoriaLaboral: " + result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/editHistoriaLaboral", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<HistoriaLaboralViewModel>> obtenerIdHistoriaLaboral(
            @RequestParam(name = "idHistorialLaboral", required = true) Long idHistorialLaboral) {

        List<HistoriaLaboralViewModel> result;
        result = empleadoService.obtenerIdHistoriaLaboral(idHistorialLaboral);
        if (result == null)
            result = new ArrayList<>();
        LOG.info("Msg obtenerIdHistoriaLaboral: " + result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/obtenerHistorialLaboralById", method = RequestMethod.POST)
	public @ResponseBody HistorialLaboralViewModel obtenerHistorialLaboralById(@RequestBody Long idHistorialLaboral){
    	HistorialLaboralViewModel result;
			result = empleadoService.obtenerHistorialLaboralById(idHistorialLaboral);
			if(result == null)
				result = new HistorialLaboralViewModel();
			LOG.info("Msg obtenerHistorialLaboralById: " + result);
			return result;
	}


    @RequestMapping(value = "/eliminarPermisoEmpleado", method = RequestMethod.POST)
    public NotificacionViewModel eliminarPermisoEmpleado(@RequestBody PermisoEmpleadoViewModel permisoEmpleadoDto) {

    	NotificacionViewModel notificacion =  empleadoService.eliminarPermisoEmpleado(permisoEmpleadoDto.getIdPermisoEmpleado());

        return notificacion;
    }
    
    @RequestMapping(value = "/obtenerHorarioEmpleadoDiasPorHorarioEmpleado", method = RequestMethod.POST)
    public List<HorarioEmpleadoDiaViewModel> obtenerHorarioEmpleadoDiasPorHorarioEmpleado(@RequestBody HorarioEmpleadoViewModel horarioEmpleadoDto) {
    	List<HorarioEmpleadoDiaViewModel> dto  =  empleadoService.obtenerHorarioEmpleadoDiasPorHorarioEmpleado(horarioEmpleadoDto);
        return dto;
    }
    
    @RequestMapping(value="/countEquiposPendientesDevolucion", method = RequestMethod.POST)
	public @ResponseBody
    NotificacionViewModel countEquiposPendientesDevolucion(@RequestBody EmpleadoViewModel empleadoDto){
	
		NotificacionViewModel dto = empleadoService.countEquiposPendientesDevolucion(empleadoDto);
		return dto;
		
	}
    
    @RequestMapping(value = "/obtenerMarcacionEmpleado", method = RequestMethod.POST)
    public MarcacionViewModel obtenerMarcacionEmpleado(@RequestBody EmpleadoViewModel EmpleadoDto) {
    	MarcacionViewModel dto  =  marcacionEmpleadoService.obtenerMarcacionPorEmpleadoyFechaActual(EmpleadoDto);
        return dto;
    }
    
    @RequestMapping(value = "/obtenerEmpleadoEsPersonalConfianza", method = RequestMethod.POST)
    public EmpleadoViewModel obtenerEmpleadoEsPersonalConfianza(@RequestBody Long idEmpleado) {
    	EmpleadoViewModel dto;
    	dto = empleadoService.obtenerEmpleadoEsPersonalConfianza(idEmpleado);
        return dto;
    }
    
}
