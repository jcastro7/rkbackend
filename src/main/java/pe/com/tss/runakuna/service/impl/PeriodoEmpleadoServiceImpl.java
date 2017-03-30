package pe.com.tss.runakuna.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.tss.runakuna.domain.model.entities.*;
import pe.com.tss.runakuna.domain.model.repository.jdbc.PeriodoEmpleadoRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.*;
import pe.com.tss.runakuna.service.PeriodoEmpleadoService;
import pe.com.tss.runakuna.view.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class PeriodoEmpleadoServiceImpl  implements PeriodoEmpleadoService{

	@Autowired
	ConfiguracionSistemaJpaRepository configuracionSistemaJpaRepository;
	
	@Autowired
	PeriodoEmpleadoJpaRepository periodoEmpleadoJpaRepository;
	
	@Autowired
	EmpleadoJpaRepository empleadoJpaRepository;
	
	@Autowired
	PeriodoEmpleadoRepository periodoEmpleadoRepository;
	
	@Autowired
	TipoLicenciaJpaRepository tipoLicenciaJpaRepository;
	
	@Autowired
	PeriodoEmpleadoTipoLicenciaJpaRepository  periodoEmpleadoTipoLicenciaJpaRepository;
	
	@Override
	public NotificacionEmpleadoViewModel registrarPeriodoEmpleado(EmpleadoViewModel empleadoDto) {
		NotificacionEmpleadoViewModel notificacionEmpleadoDto=new NotificacionEmpleadoViewModel();
		
		ConfiguracionSistema cadenaDiasCrearPeriodoEmpleado = configuracionSistemaJpaRepository.obtenerConfiguracionSistemaPorCodigo("GestionDeTiempo.PeriodoEmpleado");
		String diasCrearPeriodoEmpleado=cadenaDiasCrearPeriodoEmpleado.getValor();
		PeriodoEmpleado periodoEmpleado=periodoEmpleadoJpaRepository.obtenerPeriodoEmpleadoPorFechayEmpleado(new Date(), empleadoDto.getIdEmpleado());
		if(periodoEmpleado==null) {
			List<PeriodoEmpleado> lista=periodoEmpleadoJpaRepository.obtenerUltimoPeriodoEmpleadoVigente(empleadoDto.getIdEmpleado());
			if(lista!=null && lista.size()>0){
				periodoEmpleado=lista.get(0);
			}
		} 
		PeriodoEmpleado periodoEmpleadoNuevo=null;
		try {
			
				if(periodoEmpleado==null) {
					periodoEmpleadoNuevo=crearPeriodoEmpleado(empleadoDto,null);
					notificacionEmpleadoDto.setCodigo(1L);
					notificacionEmpleadoDto.setDetail("Se crea un nuevo periodoEmpleado");
					EmpleadoViewModel empDto=new EmpleadoViewModel();
					empDto.setNombre(periodoEmpleadoNuevo.getEmpleado().getNombre());
					empDto.setApellidoPaterno(periodoEmpleadoNuevo.getEmpleado().getApellidoPaterno());
					empDto.setApellidoMaterno(periodoEmpleadoNuevo.getEmpleado().getApellidoMaterno());
					empDto.setEmailInterno(periodoEmpleadoNuevo.getEmpleado().getEmailInterno());
					PeriodoEmpleadoViewModel periodoCreadoDto=new PeriodoEmpleadoViewModel();
					periodoCreadoDto.setPeriodo(periodoEmpleadoNuevo.getPeriodo());
					notificacionEmpleadoDto.setEmpleadoDto(empDto);
					notificacionEmpleadoDto.setPeriodoEmpleadoDto(periodoCreadoDto);
					
				} else if(verificarPeriodoEmpleadoNuevo(empleadoDto,diasCrearPeriodoEmpleado)){
					
					notificacionEmpleadoDto.setCodigo(2L);
					notificacionEmpleadoDto.setDetail("Ya tiene periodoEmpleado creado y activo");
					
				} else {
					Date fechaFinPeriodo=periodoEmpleado.getFechaFin();
					Calendar calendar=Calendar.getInstance();
					calendar.setTime(fechaFinPeriodo);
					calendar.add(Calendar.DAY_OF_YEAR, -1*(Integer.parseInt(diasCrearPeriodoEmpleado)));
					Calendar hoy=Calendar.getInstance();
					hoy.setTime(new Date());
					if(!hoy.before(calendar.getTime())) {
						Calendar calendarFechaInicioPeriodo=Calendar.getInstance();
						calendarFechaInicioPeriodo.setTime(fechaFinPeriodo);
						calendarFechaInicioPeriodo.add(Calendar.DAY_OF_YEAR, 1);
						 periodoEmpleadoNuevo=crearPeriodoEmpleado(empleadoDto,calendarFechaInicioPeriodo.getTime());
						EmpleadoViewModel empDto=new EmpleadoViewModel();
						empDto.setNombre(periodoEmpleadoNuevo.getEmpleado().getNombre());
						empDto.setApellidoPaterno(periodoEmpleadoNuevo.getEmpleado().getApellidoPaterno());
						empDto.setApellidoMaterno(periodoEmpleadoNuevo.getEmpleado().getApellidoMaterno());
						empDto.setEmailInterno(periodoEmpleadoNuevo.getEmpleado().getEmailInterno());
						PeriodoEmpleadoViewModel periodoCreadoDto=new PeriodoEmpleadoViewModel();
						periodoCreadoDto.setPeriodo(periodoEmpleadoNuevo.getPeriodo());
						notificacionEmpleadoDto.setPeriodoEmpleadoDto(periodoCreadoDto);
						notificacionEmpleadoDto.setEmpleadoDto(empDto);
						notificacionEmpleadoDto.setCodigo(1L);
						notificacionEmpleadoDto.setDetail("Se genero un nuevo periodoEmpleado, a partir de un periodo existente");
					}
				}
			
			if(periodoEmpleadoNuevo!=null) {
				crearTiposLicenciaPorPeriodo(periodoEmpleadoNuevo);
			}
		} catch (Exception e) {
			notificacionEmpleadoDto.setCodigo(0L);
			notificacionEmpleadoDto.setDetail("No es posible crear periodoEmpleado, "+e.getMessage());
		}
		
		return notificacionEmpleadoDto;
	}
	
	private boolean verificarPeriodoEmpleadoNuevo(EmpleadoViewModel empleadoDto, String diasCrearPeriodoEmpleado) {
		Boolean result=false;
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(new Date());
		if(Integer.parseInt(diasCrearPeriodoEmpleado)==30) {
			calendar.add(Calendar.MONTH, 1);
		} else{
			calendar.add(Calendar.DAY_OF_YEAR, Integer.parseInt(diasCrearPeriodoEmpleado));
		}
		
		Date fecha=calendar.getTime();
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		try {
			fecha=sdf.parse(sdf.format(fecha));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		PeriodoEmpleado periodoEmpleado=periodoEmpleadoJpaRepository.obtenerPeriodoEmpleadoPorFechayEmpleado(fecha, empleadoDto.getIdEmpleado());
		if(periodoEmpleado!=null) {
			result=true;
		}
		return result;
	}

	private PeriodoEmpleado crearPeriodoEmpleado(EmpleadoViewModel empleadoDto, Date fechaInicioPeriodo) {
		ConfiguracionSistema cadenaMaxVacacion = configuracionSistemaJpaRepository.obtenerConfiguracionSistemaPorCodigo("GestionDeTiempo.Vacaciones");
		ConfiguracionSistema cadenaMaxPermi = configuracionSistemaJpaRepository.obtenerConfiguracionSistemaPorCodigo("GestionDeTiempo.PermisosPermitidos");
		
		PeriodoEmpleado periodoEmpleado=new PeriodoEmpleado();
		Empleado empleado=empleadoJpaRepository.findOne(empleadoDto.getIdEmpleado());
		periodoEmpleado.setEmpleado(empleado);
		Date fechaInicio=empleado.getFechaIngreso();
		Calendar calendar=Calendar.getInstance();
		if(fechaInicioPeriodo==null)
			calendar.setTime(fechaInicio);
		else
			calendar.setTime(fechaInicioPeriodo);
		calendar.add(Calendar.MONTH, 12);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		Date fechaFin =calendar.getTime();
		if(fechaInicioPeriodo==null)
			periodoEmpleado.setFechaInicio(fechaInicio);
		else
			periodoEmpleado.setFechaInicio(fechaInicioPeriodo);
		periodoEmpleado.setFechaFin(fechaFin);
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		String fechaInicioCadena=sdf.format(periodoEmpleado.getFechaInicio());
		String fechaFinCadena=sdf.format(periodoEmpleado.getFechaFin());
		periodoEmpleado.setPeriodo(fechaInicioCadena+"-"+fechaFinCadena);
		periodoEmpleado.setMaxDiasVacaciones(Integer.parseInt(cadenaMaxVacacion.getValor()));
		periodoEmpleado.setDiasVacacionesDisponibles(Integer.parseInt(cadenaMaxVacacion.getValor()));
		periodoEmpleado.setMaxPermisos(Integer.parseInt(cadenaMaxPermi.getValor()));
		periodoEmpleado.setPermisosDisponibles(Integer.parseInt(cadenaMaxPermi.getValor()));
		periodoEmpleado.setDiasVacacionesAcumulado(0);
		periodoEmpleadoJpaRepository.save(periodoEmpleado);
		return periodoEmpleado;
		
	}

	@Override
	public List<PeriodoEmpleadoResultViewModel> busquedaPeriodoEmpleado(
			PeriodoEmpleadoFilterViewModel busquedaPeriodoEmpleadoDto) {
		List<PeriodoEmpleadoResultViewModel> dto = new ArrayList<PeriodoEmpleadoResultViewModel>();
		
		dto = periodoEmpleadoRepository.busquedaPeriodoEmpleado(busquedaPeriodoEmpleadoDto);
		return dto;
	}
	
	private void crearTiposLicenciaPorPeriodo(PeriodoEmpleado periodoEmpleadoNuevo) {
		List<TipoLicencia> tiposLicencias=tipoLicenciaJpaRepository.findTipoLicencia();
		PeriodoEmpleadoTipoLicencia periodoEmpleadoTipoLicencia;
		for(TipoLicencia tipoLicencia:tiposLicencias) {
			periodoEmpleadoTipoLicencia=new PeriodoEmpleadoTipoLicencia();
			periodoEmpleadoTipoLicencia.setDiasLicencia(0);
			periodoEmpleadoTipoLicencia.setPeriodoEmpleado(periodoEmpleadoNuevo);
			periodoEmpleadoTipoLicencia.setTipoLicencia(tipoLicencia);
			periodoEmpleadoTipoLicenciaJpaRepository.save(periodoEmpleadoTipoLicencia);
		}
		
	}

	
	
}
