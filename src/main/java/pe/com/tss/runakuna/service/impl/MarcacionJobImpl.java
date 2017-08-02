package pe.com.tss.runakuna.service.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import pe.com.tss.runakuna.domain.model.entities.Calendario;
import pe.com.tss.runakuna.domain.model.entities.DepartamentoArea;
import pe.com.tss.runakuna.domain.model.entities.Empleado;
import pe.com.tss.runakuna.domain.model.entities.EmpleadoCompensacion;
import pe.com.tss.runakuna.domain.model.entities.HistorialLaboral;
import pe.com.tss.runakuna.domain.model.entities.HorarioEmpleado;
import pe.com.tss.runakuna.domain.model.entities.HorarioEmpleadoDia;
import pe.com.tss.runakuna.domain.model.entities.HorasExtra;
import pe.com.tss.runakuna.domain.model.entities.Licencia;
import pe.com.tss.runakuna.domain.model.entities.Marcacion;
import pe.com.tss.runakuna.domain.model.entities.PeriodoEmpleado;
import pe.com.tss.runakuna.domain.model.entities.PermisoEmpleado;
import pe.com.tss.runakuna.domain.model.entities.PermisoEmpleadoRecuperacion;
import pe.com.tss.runakuna.domain.model.entities.Proyecto;
import pe.com.tss.runakuna.domain.model.entities.RegistroMarcacionEmpleado;
import pe.com.tss.runakuna.domain.model.entities.UnidadDeNegocio;
import pe.com.tss.runakuna.domain.model.entities.Vacacion;
import pe.com.tss.runakuna.domain.model.repository.jdbc.HistoriaLaboralRepository;
import pe.com.tss.runakuna.domain.model.repository.jdbc.MarcacionRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.CalendarioJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.CompensacionJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.DepartamentoAreaJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.EmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.HistorialLaboralJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.HorarioEmpleadoDiaJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.HorarioEmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.HorasExtraJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.LicenciaEmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.MarcacionJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.PeriodoEmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.PermisoEmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.PermisoEmpleadoRecuperacionJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.ProyectoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.RegistroMarcacionEmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.UnidaDeNegocioJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.VacacionJpaRepository;
import pe.com.tss.runakuna.enums.EstadoMarcacionEnum;
import pe.com.tss.runakuna.view.model.AlertaSubscriptorViewModel;
import pe.com.tss.runakuna.view.model.AlertaViewModel;
import pe.com.tss.runakuna.view.model.EmpleadoViewModel;
import pe.com.tss.runakuna.view.model.GeneraMsjeAlertaEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.HistoriaLaboralViewModel;
import pe.com.tss.runakuna.view.model.JobEjecucionResultViewModel;
import pe.com.tss.runakuna.view.model.JobEjecucionViewModel;
import pe.com.tss.runakuna.view.model.MarcacionViewModel;
import pe.com.tss.runakuna.view.model.RegistroMarcacionEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.RegistroMarcacionViewModel;
import pe.com.tss.runakuna.service.AlertaService;
import pe.com.tss.runakuna.service.JobEjecucionService;
import pe.com.tss.runakuna.service.MailService;
import pe.com.tss.runakuna.util.DateUtil;
import pe.com.tss.runakuna.util.StringUtil;

@Service
public class MarcacionJobImpl{
	
	@Autowired
	EmpleadoJpaRepository empleadoJpaRepository;
	
	@Autowired
	RegistroMarcacionEmpleadoJpaRepository registroMarcacionEmpleadoJpaRepository;
	
	@Autowired
	MarcacionJpaRepository marcacionJpaRepository;
	
	@Autowired
	HorarioEmpleadoDiaJpaRepository horarioEmpleadoDiaJpaRepository;
	
	@Autowired
	HorarioEmpleadoJpaRepository horarioEmpleadoJpaRepository;
	
	@Autowired
	PermisoEmpleadoJpaRepository permisoEmpleadoJpaRepository;
	
	@Autowired
	PermisoEmpleadoRecuperacionJpaRepository permisoEmpleadoRecuperacionJpaRepository;
	
	@Autowired
	HorasExtraJpaRepository horasExtraJpaRepository;
	
	@Autowired
	PeriodoEmpleadoJpaRepository periodoEmpleadoJpaRepository;
	
	@Autowired
	VacacionJpaRepository vacacionJpaRepository;
	
	@Autowired
	LicenciaEmpleadoJpaRepository licenciaJpaRepository;
	
	@Autowired
	MarcacionRepository marcacionJdbcRepository;
	
	@Autowired
	DepartamentoAreaJpaRepository departamentoAreaJpaRepository;
	
	@Autowired
	UnidaDeNegocioJpaRepository unidadDeNegocioJpaRepository;
	
	@Autowired
	CompensacionJpaRepository compensacionJpaRepository;
	
	@Autowired
	ProyectoJpaRepository proyectoJpaRepository;
	
	@Autowired
	CalendarioJpaRepository calendarioJpaRepository;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	AlertaService alertaService;
	
	@Autowired
	JobEjecucionService jobEjecucionService;
	
	@Autowired
	Mapper mapper;
	
	@Autowired
	HistoriaLaboralRepository historiaLaboralRepository;
	@Autowired
	HistorialLaboralJpaRepository historiaLaboralJpaRepository;
	
	@Value("${spring.cronFrecuency.JOB_INTE_MARCA}")
    String cron_JOB_INTE_MARCA;
	
	@Value("${spring.cronFrecuency.JOB_INTE_COMP}")
	String cron_JOB_INTE_COMP;
	
	@Value("${spring.cronFrecuency.JOB_CREAR_MARCA}")
	String cron_CREAR_MARCA;
	
	@Value("${spring.cronFrecuency.JOB_MARCA_INCO}")
	String cron_JOB_MARCA_INCO;
	
	@Value("${spring.cronFrecuency.JOB_RECA_MARCA}")
	String cron_JOB_RECA_MARCA;
	
	@Value("${spring.cronFrecuency.JOB_INASISTENCIA}")
	String JOB_INASISTENCIA;
	
	@Value("${spring.cronFrecuency.JOB_TARDANZA_GROUP}")
	String JOB_TARDANZA_GROUP;
	
	@Value("${spring.production.active}")
    private boolean productionActive;

	private void registrarMarcacionesEmpleado(List<RegistroMarcacionEmpleadoViewModel> marcaciones){
		
		for (RegistroMarcacionEmpleadoViewModel registroMarcacionEmpleadoDto : marcaciones) {
			RegistroMarcacionEmpleado entity = new RegistroMarcacionEmpleado();
			
			Empleado empleadoEntity = empleadoJpaRepository.findByCodigoExacto(registroMarcacionEmpleadoDto.getCodigoEmpleado());
			if(empleadoEntity != null){
				
				entity.setFecha(DateUtil.formatoFechaArchivoMarcacion(registroMarcacionEmpleadoDto.getFechaMarcacion()));
				entity.setHora(StringUtil.formatoHoraArchivoMarcacion(registroMarcacionEmpleadoDto.getHoraMarcacion()));
				entity.setCodigoEmpleado(registroMarcacionEmpleadoDto.getCodigoEmpleado());
				entity.setDni(registroMarcacionEmpleadoDto.getDniEmpleado());
				entity.setTipo(registroMarcacionEmpleadoDto.getTipoMarcacion());
				entity.setEmpleado(empleadoEntity);
				entity.setCreador("cron-user");
				entity.setFechaCreacion(new Date());
				entity.setProcesado("N");
				entity.setSensor(registroMarcacionEmpleadoDto.getSensor());
				
				registroMarcacionEmpleadoJpaRepository.save(entity);
				registroMarcacionEmpleadoJpaRepository.flush();
				
			}
		}
	}
	
	//@Scheduled(cron="${spring.cronFrecuency.JOB_INTE_MARCA}")
	public void integrarMarcacionesSistemaAsistencia(){
		GeneraMsjeAlertaEmpleadoViewModel msjeAlertaDto;
		Map<String,String> parametrosMensaje;
		Date inicio=new Date();
		Date fin=null;
		JobEjecucionViewModel jobEjecucionViewModel=null;
		CronExpression ce=null;
		try {
			ce = new CronExpression(cron_JOB_INTE_MARCA);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date nextExecution= ce.getNextValidTimeAfter(inicio);
		JobEjecucionResultViewModel result=jobEjecucionService.obtenerEjecucionJobPendiente("JOB_INTE_MARCA");
		if(result==null ||result.getIdJobEjecucion()==null){
			JobEjecucionViewModel je=new JobEjecucionViewModel();
			je.setEjecutado(0);
			je.setEstado("N");
			je.setFechaProgramado(nextExecution);
			jobEjecucionService.registarJobEjecucionEnBlanco(je, "JOB_INTE_MARCA");
			result=jobEjecucionService.obtenerEjecucionJobPendiente("JOB_INTE_MARCA");
		}
		
		List<RegistroMarcacionViewModel> registros = null;
		
		try{
			if(productionActive){
						
				List<RegistroMarcacionEmpleadoViewModel> marcaciones = marcacionJdbcRepository.obtenerMarcacion();
				
				if(marcaciones!= null && marcaciones.size() > 0){
				
					registrarMarcacionesEmpleado(marcaciones);		
					
					registros = marcacionJdbcRepository.obtenerRegistroMarcacionNoProcesado();
					
					if(registros==null || registros.size()==0){
						return;
					}
					marcacionJdbcRepository.updateRegistroMarcacionAProcesado(registros);
					
					for (RegistroMarcacionViewModel registroMarcacion : registros) {
						
						try {
							Marcacion marcacion = marcacionJpaRepository.obtenerMarcacionPorIdEmpleadoyDate(registroMarcacion.getIdEmpleado(), registroMarcacion.getFecha());
							
							if(marcacion == null){
								boolean estado = crearMarcacionEmpleado(registroMarcacion.getIdEmpleado(),registroMarcacion.getFecha());
								if(!estado){
									continue;
								}
								marcacion = marcacionJpaRepository.obtenerMarcacionPorIdEmpleadoyDate(registroMarcacion.getIdEmpleado(), registroMarcacion.getFecha());
							}
							
							if(marcacion != null){
								
								if(marcacion.getEmpleado().getCategoriaEmpleado().equals("J") || marcacion.getEstado().equals("VA") || marcacion.getEstado().equals("LI")){
									
									continue;
									
								}else if(marcacion.getEmpleado().getCategoriaEmpleado().equals("C")){
									if(marcacion.getHoraIngreso() == null){
										marcacion.setHoraIngreso(registroMarcacion.getHora());
										marcacion.setDemoraEntrada(new BigDecimal(0));
										marcacionJpaRepository.save(marcacion);
										marcacionJpaRepository.flush();
									}
									
								}else if(marcacion.getNoLaboral().intValue() == 1){
									
									if(marcacion.getHoraIngreso() == null){
										marcacion.setHoraIngreso(registroMarcacion.getHora());
										marcacion.setDemoraEntrada(new BigDecimal(0));
									}else if(marcacion.getHoraInicioAlmuerzo() == null){
										marcacion.setHoraInicioAlmuerzo(registroMarcacion.getHora());
									}else if(marcacion.getHoraFinAlmuerzo() == null){	
										marcacion.setHoraFinAlmuerzo(registroMarcacion.getHora());
										marcacion.setDemoraAlmuerzo(new BigDecimal(0));
									}else if(marcacion.getHoraSalida() == null){
										marcacion.setHoraSalida(registroMarcacion.getHora());
										marcacion.setDemoraSalida(new BigDecimal(0));
									}
									
									marcacionJpaRepository.save(marcacion);
									marcacionJpaRepository.flush();
									
								}else{
									
									Calendar c1 = Calendar.getInstance();
									BigDecimal tiempoAlmuerzo = new BigDecimal(1);
									Integer numeroMarcaciones = 0;
									
									HorarioEmpleado horarioEmpleado = horarioEmpleadoJpaRepository.obtenerHorarioPorFechaVigente(marcacion.getFecha(), marcacion.getEmpleado().getIdEmpleado());
									if(horarioEmpleado != null){
									
										HorarioEmpleadoDia horarioEmpleadoDia = horarioEmpleadoDiaJpaRepository.obtenerHorarioDiaPorDiaDeSemana(horarioEmpleado.getIdHorarioEmpleado(), StringUtil.obtenerCodigoDia(c1.get(Calendar.DAY_OF_WEEK)));
									
										if(horarioEmpleadoDia != null){
											
											if(horarioEmpleadoDia.getLaboral().intValue() == 0){
												continue;
											}
											
											tiempoAlmuerzo = new BigDecimal(horarioEmpleadoDia.getTiempoAlmuerzo());
											numeroMarcaciones = horarioEmpleadoDia.getNumeroMarcaciones();
										}else{
											continue;
										}
									}else{
										continue;
									}
									
									if(numeroMarcaciones != null){
										if(numeroMarcaciones.intValue() == 2){
											if(marcacion.getHoraIngreso() == null){
												registrarHoraIngreso(marcacion, registroMarcacion.getHora());
											}else if(marcacion.getHoraSalida() == null){
												registrarHoraSalida(marcacion, registroMarcacion.getHora());
											}else{
												continue;
											}
										}else if(numeroMarcaciones.intValue() == 4){
											if(marcacion.getHoraIngreso() == null){
												
												registrarHoraIngreso(marcacion, registroMarcacion.getHora());
												
											}else if(marcacion.getHoraInicioAlmuerzo() == null){
												
												marcacion.setHoraInicioAlmuerzo(registroMarcacion.getHora());
												
												marcacionJpaRepository.save(marcacion);
												marcacionJpaRepository.flush();
																					
											}else if(marcacion.getHoraFinAlmuerzo() == null){
												
												marcacion.setHoraFinAlmuerzo(registroMarcacion.getHora());
																		
												tiempoAlmuerzo = tiempoAlmuerzo.multiply(new BigDecimal(60));
												
												Date fechaHoraInicio = DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraInicioAlmuerzo());
												Date fechaHoraFin =  DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraFinAlmuerzo());
												
												BigDecimal horaDemoraAlmuerzo = new BigDecimal((fechaHoraFin.getTime()-fechaHoraInicio.getTime())).divide(new BigDecimal((1000*60)),2,0);
												
												if(horaDemoraAlmuerzo.doubleValue() > tiempoAlmuerzo.doubleValue()){
													horaDemoraAlmuerzo = horaDemoraAlmuerzo.subtract(tiempoAlmuerzo);
												}else{
													horaDemoraAlmuerzo = new BigDecimal(0);
												}
												
												marcacion.setDemoraAlmuerzo(horaDemoraAlmuerzo);
												
												marcacionJpaRepository.save(marcacion);
												marcacionJpaRepository.flush();
																					
											}else if(marcacion.getHoraSalida() == null){
												registrarHoraSalida(marcacion, registroMarcacion.getHora());
											}
										}else{
											continue;
										}
									}
								}
												
							}
						} catch (Exception e) {
							e.printStackTrace();
							registroMarcacion.setLog(e.getMessage());
							marcacionJdbcRepository.updateRegistroMarcacionAError(registroMarcacion);
						}
						
					}		
				}
			}
			fin=new Date();
			jobEjecucionViewModel=new JobEjecucionViewModel();
			jobEjecucionViewModel.setIdJobEjecucion(result.getIdJobEjecucion());
			jobEjecucionViewModel.setFechaInicio(inicio);
			jobEjecucionViewModel.setFechaFin(fin);
			jobEjecucionViewModel.setEstado("F");
			jobEjecucionViewModel.setResultadoMensaje("OK");
			jobEjecucionViewModel.setEjecutado(1);
			jobEjecucionViewModel.setFechaProgramado(nextExecution);
		}catch (Exception e) {
			fin=new Date();
			jobEjecucionViewModel=new JobEjecucionViewModel();
			jobEjecucionViewModel.setIdJobEjecucion(result.getIdJobEjecucion());
			jobEjecucionViewModel.setFechaInicio(inicio);
			jobEjecucionViewModel.setFechaFin(fin);
			jobEjecucionViewModel.setEstado("E");
			jobEjecucionViewModel.setResultadoMensaje(e.getMessage());
			jobEjecucionViewModel.setEjecutado(1);
			jobEjecucionViewModel.setFechaProgramado(nextExecution);
						
			e.printStackTrace();
		}
		jobEjecucionService.actualizarJobEjecucion(jobEjecucionViewModel, "JOB_INTE_MARCA");
		JobEjecucionViewModel next=new JobEjecucionViewModel();
		next.setEjecutado(0);
		next.setEstado("N");
		Date nextExecution2= ce.getNextValidTimeAfter(nextExecution);
		next.setFechaProgramado(nextExecution2);
		jobEjecucionService.registarJobEjecucionEnBlanco(next, "JOB_INTE_MARCA");
	}
	
	//@Scheduled(cron="${spring.cronFrecuency.JOB_INTE_COMP}")
	public void integrarCompensaciones(){
		Date inicio=new Date();
		Date fin=null;
		JobEjecucionViewModel jobEjecucionViewModel=null;
		CronExpression ce=null;
		try {
			ce = new CronExpression(cron_JOB_INTE_COMP);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date nextExecution= ce.getNextValidTimeAfter(inicio);
		JobEjecucionResultViewModel result=jobEjecucionService.obtenerEjecucionJobPendiente("JOB_INTE_COMP");
		if(result==null ||result.getIdJobEjecucion()==null){
			JobEjecucionViewModel je=new JobEjecucionViewModel();
			je.setEjecutado(0);
			je.setEstado("N");
			je.setFechaProgramado(nextExecution);
			jobEjecucionService.registarJobEjecucionEnBlanco(je, "JOB_INTE_COMP");
			result=jobEjecucionService.obtenerEjecucionJobPendiente("JOB_INTE_COMP");
		}
		try{
			if(productionActive){
				
				String dateCadena = DateUtil.fmtDt(new Date());
				
				Date today =DateUtil.formatoFechaArchivoMarcacion(dateCadena);
								
				int year = today.getYear();
				String mesComp = StringUtil.autocompleteZeroLeft(String.valueOf(today.getMonth()+1))+"/"+String.valueOf(year+1900);
				
				List<Marcacion> marcaciones = marcacionJpaRepository.obtenerMarcacionesDelDia(today);
				
				for (Marcacion marcacion : marcaciones) {
				
					if(marcacion.getNoLaboral().intValue() == 0){
						
						int vacaciones = 0;
						int tardanzas = 0;
						int inasistencias = 0;
						int licencias = 0;
						
					
						if(marcacion.getEstado().equals(EstadoMarcacionEnum.SIN_MARCAR.getCode()) || marcacion.getEstado().equals(EstadoMarcacionEnum.VACACIONES.getCode()) || marcacion.getEstado().equals(EstadoMarcacionEnum.LICENCIA.getCode())){
							marcacion.setHorasTrabajoPendiente(new BigDecimal(0));
						}
						/*else{
							marcacion.setHorasTrabajoPendiente(marcacion.getHorasTrabajoPendiente().add(marcacion.getHorasExtra()).subtract(marcacion.getHorasPermiso()));
						}*/
						
						if(marcacion.getEstado().equals(EstadoMarcacionEnum.VACACIONES.getCode())){
							vacaciones = 1;
						}else if(marcacion.getEstado().equals(EstadoMarcacionEnum.LICENCIA.getCode())){
							licencias = 1;
						}else if(marcacion.getEstado().equals(EstadoMarcacionEnum.TARDANZA.getCode())){
							tardanzas = 1;
						}else if(marcacion.getEstado().equals(EstadoMarcacionEnum.INASISTENCIA.getCode())){
							inasistencias = 1;
						}
								
						EmpleadoCompensacion compensacion = compensacionJpaRepository.findByMonthAndIdEmpleado(marcacion.getEmpleado().getIdEmpleado(), mesComp);
						
						if(compensacion == null){
							compensacion = new EmpleadoCompensacion();
							compensacion.setEmpleado(marcacion.getEmpleado());
							compensacion.setMes(mesComp);
							compensacion.setTardanzas(String.valueOf(tardanzas));
							compensacion.setHorasTardanzaIngreso(marcacion.getDemoraEntrada().divide(new BigDecimal(60),2,0));
							compensacion.setHorasTardanzaSalida(marcacion.getDemoraSalida().divide(new BigDecimal(60),2,0));
							compensacion.setHorasDemoraAlmuerzo(marcacion.getDemoraAlmuerzo().divide(new BigDecimal(60),2,0));
							compensacion.setHorasTrabajadas(marcacion.getHorasTrabajoReal());
							compensacion.setHorarioHorasTrabajo(marcacion.getHorasTrabajoHorario());
							compensacion.setHorasPendientesTotal(marcacion.getHorasTrabajoPendiente());
							
							compensacion.setInasistencias(inasistencias);
							compensacion.setVacaciones(vacaciones);
							compensacion.setLicencias(licencias);
							
						}else{
							
							tardanzas = tardanzas + Integer.parseInt(compensacion.getTardanzas().trim());
							vacaciones = vacaciones + compensacion.getVacaciones();
							inasistencias = inasistencias + compensacion.getInasistencias();
							licencias = licencias + compensacion.getLicencias();
							
							compensacion.setTardanzas(String.valueOf(tardanzas));
							compensacion.setHorasTardanzaIngreso(compensacion.getHorasTardanzaIngreso().add(marcacion.getDemoraEntrada().divide(new BigDecimal(60),2,0)));
							compensacion.setHorasTardanzaSalida(compensacion.getHorasTardanzaSalida().add(marcacion.getDemoraSalida().divide(new BigDecimal(60),2,0)));
							compensacion.setHorasDemoraAlmuerzo(compensacion.getHorasDemoraAlmuerzo().add(marcacion.getDemoraAlmuerzo().divide(new BigDecimal(60),2,0)));
							compensacion.setHorasTrabajadas(compensacion.getHorasTrabajadas().add(marcacion.getHorasTrabajoReal()));
							compensacion.setHorarioHorasTrabajo(compensacion.getHorarioHorasTrabajo().add(marcacion.getHorasTrabajoHorario()));
							compensacion.setHorasPendientesTotal(compensacion.getHorasPendientesTotal().add(marcacion.getHorasTrabajoPendiente()));
							
							compensacion.setInasistencias(inasistencias);
							compensacion.setVacaciones(vacaciones);
							compensacion.setLicencias(licencias);
						}
						
						compensacionJpaRepository.save(compensacion);
						compensacionJpaRepository.flush();
					}
					
				}
			}
			fin=new Date();
			jobEjecucionViewModel=new JobEjecucionViewModel();
			jobEjecucionViewModel.setIdJobEjecucion(result.getIdJobEjecucion());
			jobEjecucionViewModel.setFechaInicio(inicio);
			jobEjecucionViewModel.setFechaFin(fin);
			jobEjecucionViewModel.setEstado("F");
			jobEjecucionViewModel.setResultadoMensaje("OK");
			jobEjecucionViewModel.setEjecutado(1);
			jobEjecucionViewModel.setFechaProgramado(nextExecution);
		}catch (Exception e) {
			fin=new Date();
			jobEjecucionViewModel=new JobEjecucionViewModel();
			jobEjecucionViewModel.setIdJobEjecucion(result.getIdJobEjecucion());
			jobEjecucionViewModel.setFechaInicio(inicio);
			jobEjecucionViewModel.setFechaFin(fin);
			jobEjecucionViewModel.setEstado("E");
			jobEjecucionViewModel.setResultadoMensaje(e.getMessage());
			jobEjecucionViewModel.setEjecutado(1);
			jobEjecucionViewModel.setFechaProgramado(nextExecution);
		}	
		jobEjecucionService.actualizarJobEjecucion(jobEjecucionViewModel, "JOB_INTE_COMP");
		JobEjecucionViewModel next=new JobEjecucionViewModel();
		next.setEjecutado(0);
		next.setEstado("N");
		Date nextExecution2= ce.getNextValidTimeAfter(nextExecution);
		next.setFechaProgramado(nextExecution2);
		jobEjecucionService.registarJobEjecucionEnBlanco(next, "JOB_INTE_COMP");
	}
	
	//@Scheduled(cron="${spring.cronFrecuency.JOB_CREAR_MARCA}")
	public void crearMarcacionesSistemaAsistencia(){
		Date inicio=new Date();
		Date fin=null;
		JobEjecucionViewModel jobEjecucionViewModel=null;
		CronExpression ce=null;
		try {
			ce = new CronExpression(cron_CREAR_MARCA);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date nextExecution= ce.getNextValidTimeAfter(inicio);
		JobEjecucionResultViewModel result=jobEjecucionService.obtenerEjecucionJobPendiente("JOB_CREAR_MARCA");
		if(result==null ||result.getIdJobEjecucion()==null){
			JobEjecucionViewModel je=new JobEjecucionViewModel();
			je.setEjecutado(0);
			je.setEstado("N");
			je.setFechaProgramado(nextExecution);
			jobEjecucionService.registarJobEjecucionEnBlanco(je, "JOB_CREAR_MARCA");
			result=jobEjecucionService.obtenerEjecucionJobPendiente("JOB_CREAR_MARCA");
		}
		try{
			if(productionActive){
				
				Calendar c1 = Calendar.getInstance();
				
				String diaCodigo = StringUtil.obtenerCodigoDia(c1.get(Calendar.DAY_OF_WEEK));
				
				Integer noLaboral = new Integer(0);
				
				StringBuffer sbEmpleadosHorarios=new StringBuffer();
				List<Empleado> empleados = empleadoJpaRepository.buscarEmpleadosPorEstado("A");
					
				if(empleados != null && empleados.size() > 0){
					
					Date fechaActual = new Date();
					String fechaCadena = DateUtil.fmtDt(fechaActual);
					
					Calendario calendario = calendarioJpaRepository.getByDate(DateUtil.formatoFechaArchivoMarcacion(fechaCadena));
					
					if(calendario != null){
						noLaboral = 1;
					}
					
					for (Empleado empleado : empleados) {
							
						Marcacion marcacionExist = marcacionJpaRepository.obtenerMarcacionPorIdEmpleadoyDate(empleado.getIdEmpleado(), DateUtil.formatoFechaArchivoMarcacion(fechaCadena));
						
						if(marcacionExist == null){
								
							Marcacion marcacion = new Marcacion();
							marcacion.setEmpleado(empleado);
							marcacion.setFecha(DateUtil.formatoFechaArchivoMarcacion(fechaCadena));
							
							if(empleado.getCategoriaEmpleado().equals("J") || empleado.getCategoriaEmpleado().equals("C")){
								marcacion.setEsPersonaDeConfianza(new Integer(1));
							}else{
								marcacion.setEsPersonaDeConfianza(new Integer(0));
							}
							
							if(noLaboral.intValue() == 1){
								
								marcacion.setEstado(EstadoMarcacionEnum.SIN_MARCAR.getCode());
								marcacion.setHorasTrabajoHorario(new BigDecimal(0));
								marcacion.setHorasPermiso(new BigDecimal(0));
								marcacion.setHorasExtra(new BigDecimal(0));
								marcacion.setHorasTrabajoPendiente(new BigDecimal(0));
								marcacion.setHorasTrabajoReal(new BigDecimal(0));
								marcacion.setDemoraEntrada(new BigDecimal(0));
								marcacion.setDemoraAlmuerzo(new BigDecimal(0));
								marcacion.setDemoraSalida(new BigDecimal(0));
								marcacion.setRecalcular("N");
								marcacion.setNoLaboral(noLaboral);
								
							}else{
								
								HorarioEmpleado horarioEmpleado = horarioEmpleadoJpaRepository.obtenerHorarioPorFechaVigente(marcacion.getFecha(), empleado.getIdEmpleado());
								
								if(horarioEmpleado == null){
									
									sbEmpleadosHorarios.append("<tr>").
									append("<td>").append(empleado.getNombre()+" "+empleado.getApellidoPaterno()+" "+empleado.getApellidoMaterno()).append("</td>")
									.append("<td>").append(" No existe horario ").append("</td>").append("</tr>");
									
									continue;
								}
								
								HorarioEmpleadoDia horarioEmpleadoDia = horarioEmpleadoDiaJpaRepository.obtenerHorarioDiaPorDiaDeSemana(horarioEmpleado.getIdHorarioEmpleado(), diaCodigo);
								
								if(horarioEmpleadoDia == null){
				
									sbEmpleadosHorarios.append("<tr>").
									append("<td>").append(empleado.getNombre()+" "+empleado.getApellidoPaterno()+" "+empleado.getApellidoMaterno()).append("</td>")
									.append("<td>").append(" No existe dia para el horario ").append("</td>").append("</tr>");
									
									continue;
								}
								
								marcacion.setHoraIngresoHorario(horarioEmpleadoDia.getEntrada());
								marcacion.setHoraSalidaHorario(horarioEmpleadoDia.getSalida());
								
								Date fechaHoraIngreso =  DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraIngresoHorario());
								Date fechaHoraSalida=  DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraSalidaHorario());
								
								BigDecimal horasTrabajo = new BigDecimal((fechaHoraSalida.getTime()-fechaHoraIngreso.getTime())).divide(new BigDecimal((1000*60*60)),2,0);
								
								horasTrabajo = horasTrabajo.subtract(new BigDecimal(horarioEmpleadoDia.getTiempoAlmuerzo()));
								
								marcacion.setHorasTrabajoHorario(horasTrabajo);
								
								PeriodoEmpleado periodo = periodoEmpleadoJpaRepository.obtenerPeriodoEmpleadoPorFechayEmpleado(marcacion.getFecha(), empleado.getIdEmpleado());
								
								if(periodo == null){
				
									sbEmpleadosHorarios.append("<tr>").
									append("<td>").append(empleado.getNombre()+" "+empleado.getApellidoPaterno()+" "+empleado.getApellidoMaterno()).append("</td>")
									.append("<td>").append(" No existe periodo ").append("</td>").append("</tr>");
									
									continue;
								}
								
								Vacacion vacacion = vacacionJpaRepository.obtenerVacaciones(marcacion.getFecha(), empleado.getIdEmpleado());
								List<Licencia> licencia = licenciaJpaRepository.obtenerLicenciasPorIdEmpleadoYFecha(marcacion.getFecha(), empleado.getIdEmpleado());
								
								boolean flagDiaEntero = false;
								
								List<PermisoEmpleado> permisos = permisoEmpleadoJpaRepository.obtenerPermisoAprobadoPorFechayPeriodoEmpleado(marcacion.getFecha(), marcacion.getEmpleado().getIdEmpleado());
								
								if(permisos != null && permisos.size()>0){
									
									String horarioInicio = "";
									String horarioFin = "";
									
									BigDecimal horasPermiso = new BigDecimal(0);
									
									for (PermisoEmpleado permisoEmpleado : permisos) {
										
										if(permisoEmpleado.getDiaEntero().intValue() == 1){
											flagDiaEntero = true;
											break;
										}
										
										Date fechaHoraIngresoPermiso =  DateUtil.parse(new SimpleDateFormat("HH:mm"),permisoEmpleado.getHoraInicio());
										Date fechaHoraIngresoDia=  DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraIngresoHorario());
										
										Date fechaHoraSalidaPermiso=  DateUtil.parse(new SimpleDateFormat("HH:mm"),permisoEmpleado.getHoraFin());
										Date fechaHoraSalidaDia=  DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraSalidaHorario());
										
										
										if(fechaHoraIngresoDia.equals(fechaHoraIngresoPermiso)){
											horarioInicio = permisoEmpleado.getHoraFin();
										}
										
										if(fechaHoraSalidaDia.equals(fechaHoraSalidaPermiso)){
											horarioFin = permisoEmpleado.getHoraInicio();
										}
										
										horasPermiso = horasPermiso.add(permisoEmpleado.getHoras());
										
									}
									
									if(!horarioInicio.equals("")){
										marcacion.setHoraIngresoHorario(horarioInicio);
									}
									
									if(!horarioFin.equals("")){
										marcacion.setHoraSalidaHorario(horarioFin);
									}
									
									marcacion.setHorasPermiso(horasPermiso);
								
								}else{
									marcacion.setHorasPermiso(new BigDecimal(0));
								}
								
								if(vacacion != null){
									marcacion.setEstado(EstadoMarcacionEnum.VACACIONES.getCode());
									marcacion.setHorasPermiso(new BigDecimal(0));
								}else if(licencia != null && licencia.size()>0){
									marcacion.setEstado(EstadoMarcacionEnum.LICENCIA.getCode());
									marcacion.setHorasPermiso(new BigDecimal(0));
								}else if(flagDiaEntero){
									marcacion.setEstado(EstadoMarcacionEnum.PERMISO_COMPENSACION.getCode());
									marcacion.setHorasPermiso(marcacion.getHorasTrabajoHorario());
								}else if(empleado.getCategoriaEmpleado().equals("J") || empleado.getCategoriaEmpleado().equals("C")){
									marcacion.setEstado(EstadoMarcacionEnum.SIN_MARCAR.getCode());
									marcacion.setHorasPermiso(new BigDecimal(0));
									
								}else{
									marcacion.setEstado(EstadoMarcacionEnum.INASISTENCIA.getCode());
									
								}
								
								marcacion.setHorasTrabajoReal(horasTrabajo);
								marcacion.setHorasExtra(new BigDecimal(0));
								marcacion.setHorasRecuperacion(new BigDecimal(0));
								marcacion.setHorasTrabajoPendiente(new BigDecimal(0));
								marcacion.setDemoraEntrada(new BigDecimal(0));
								marcacion.setDemoraAlmuerzo(new BigDecimal(0));
								marcacion.setDemoraSalida(new BigDecimal(0));
								marcacion.setRecalcular("N");
								marcacion.setNoLaboral(noLaboral);
								
							}
							
							marcacionJpaRepository.save(marcacion);
							marcacionJpaRepository.flush();
								
						}
					}
				}
				
				if(!sbEmpleadosHorarios.equals("")) {
					StringBuffer sb=new StringBuffer();
					sb.append("<table>");
					sb.append(sbEmpleadosHorarios.toString());
					sb.append("</table>");
					AlertaViewModel alertaDto=alertaService.obtenerAlerta("Alerta22");
					String[] correos=new String[alertaDto.getSubscriptores().size()];
					int i=0;
					for(AlertaSubscriptorViewModel alertaSubscriptorDto:alertaDto.getSubscriptores()) {
						Empleado empl=empleadoJpaRepository.findOne(alertaSubscriptorDto.getIdEmpleado());
						correos[i]=empl.getEmailInterno();
						i++;
					}
					String cuerpo=alertaDto.getCuerpo().replace("[ListaEmpleados]", sb.toString());
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					String asunto=alertaDto.getAsunto().replace("[Fecha]", dateFormat.format(new Date()));
					mailService.sendEmail(asunto, cuerpo, correos);
				}
			}
			fin=new Date();
			jobEjecucionViewModel=new JobEjecucionViewModel();
			jobEjecucionViewModel.setIdJobEjecucion(result.getIdJobEjecucion());
			jobEjecucionViewModel.setFechaInicio(inicio);
			jobEjecucionViewModel.setFechaFin(fin);
			jobEjecucionViewModel.setEstado("F");
			jobEjecucionViewModel.setResultadoMensaje("OK");
			jobEjecucionViewModel.setEjecutado(1);
			jobEjecucionViewModel.setFechaProgramado(nextExecution);
		} catch (Exception e) {
			fin=new Date();
			jobEjecucionViewModel=new JobEjecucionViewModel();
			jobEjecucionViewModel.setIdJobEjecucion(result.getIdJobEjecucion());
			jobEjecucionViewModel.setFechaInicio(inicio);
			jobEjecucionViewModel.setFechaFin(fin);
			jobEjecucionViewModel.setEstado("E");
			jobEjecucionViewModel.setResultadoMensaje(e.getMessage());
			jobEjecucionViewModel.setEjecutado(1);
			jobEjecucionViewModel.setFechaProgramado(nextExecution);
			e.printStackTrace();
		}	
		jobEjecucionService.actualizarJobEjecucion(jobEjecucionViewModel, "JOB_CREAR_MARCA");
		JobEjecucionViewModel next=new JobEjecucionViewModel();
		next.setEjecutado(0);
		next.setEstado("N");
		Date nextExecution2= ce.getNextValidTimeAfter(nextExecution);
		next.setFechaProgramado(nextExecution2);
		jobEjecucionService.registarJobEjecucionEnBlanco(next, "JOB_CREAR_MARCA");
	}
	
	//@Scheduled(cron="${spring.cronFrecuency.JOB_MARCA_INCO}")
	public void enviarAlertasMarcacionesIncorrectas(){
		Date inicio=new Date();
		Date fin=null;
		JobEjecucionViewModel jobEjecucionViewModel=null;
		CronExpression ce=null;
		try {
			ce = new CronExpression(cron_JOB_MARCA_INCO);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date nextExecution= ce.getNextValidTimeAfter(inicio);
		JobEjecucionResultViewModel result=jobEjecucionService.obtenerEjecucionJobPendiente("JOB_MARCA_INCO");
		if(result==null ||result.getIdJobEjecucion()==null){
			JobEjecucionViewModel je=new JobEjecucionViewModel();
			je.setEjecutado(0);
			je.setEstado("N");
			je.setFechaProgramado(nextExecution);
			jobEjecucionService.registarJobEjecucionEnBlanco(je, "JOB_MARCA_INCO");
			result=jobEjecucionService.obtenerEjecucionJobPendiente("JOB_MARCA_INCO");
		}
		try{
			if(productionActive){
				
				//validar las horas de marcacion
				
				String fechaCadena = DateUtil.fmtDt(new Date());
				Date fecha = DateUtil.formatoFechaArchivoMarcacion(fechaCadena);
				List<Marcacion> marcacionesIncorrectas = new ArrayList<>();
				List<Marcacion> marcacionesIncorrectasNoDePersonalConfianza = marcacionJpaRepository.obtenerMarcacionIncorrectasYNoEsPersonalDeConfianza(fecha);
				List<Marcacion> marcacionesIncorrectasDePersonalConfianza = marcacionJpaRepository.obtenerMarcacionIncorrectasYEsPersonalDeConfianza(fecha);
				marcacionesIncorrectas.addAll(marcacionesIncorrectasNoDePersonalConfianza);
				marcacionesIncorrectas.addAll(marcacionesIncorrectasDePersonalConfianza);
				AlertaViewModel alertaDto=alertaService.obtenerAlerta("ALERTA23");
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				String asunto=alertaDto.getAsunto().replace("[Fecha]", dateFormat.format(new Date()));
				String cuerpo=alertaDto.getCuerpo().replace("[Fecha]", dateFormat.format(new Date()));
				
				for(Marcacion marcacion :marcacionesIncorrectas) {
					
					boolean sendMail = true;
					PeriodoEmpleado periodo = periodoEmpleadoJpaRepository.obtenerPeriodoEmpleadoPorFechayEmpleado(marcacion.getFecha(), marcacion.getEmpleado().getIdEmpleado());
					Vacacion vacacion = vacacionJpaRepository.obtenerVacaciones(marcacion.getFecha(), marcacion.getEmpleado().getIdEmpleado());
					List<Licencia> licencia = licenciaJpaRepository.obtenerLicenciasPorIdEmpleadoYFecha(marcacion.getFecha(), marcacion.getEmpleado().getIdEmpleado());
					if(vacacion != null){
						marcacion.setEstado(EstadoMarcacionEnum.VACACIONES.getCode());
						sendMail = false;
					}else if(licencia != null && licencia.size()>0){
						marcacion.setEstado(EstadoMarcacionEnum.LICENCIA.getCode());
						sendMail = false;
					}
					
					if(sendMail){
						String correo = marcacion.getEmpleado().getEmailInterno();
						if(correo!=null && !correo.equals("")){
							mailService.sendEmail(asunto, cuerpo, correo);
						}
					}else{
						marcacionJpaRepository.save(marcacion);
						marcacionJpaRepository.flush();
					}
				}
			}
			fin=new Date();
			jobEjecucionViewModel=new JobEjecucionViewModel();
			jobEjecucionViewModel.setIdJobEjecucion(result.getIdJobEjecucion());
			jobEjecucionViewModel.setFechaInicio(inicio);
			jobEjecucionViewModel.setFechaFin(fin);
			jobEjecucionViewModel.setEstado("F");
			jobEjecucionViewModel.setResultadoMensaje("OK");
			jobEjecucionViewModel.setEjecutado(1);
			jobEjecucionViewModel.setFechaProgramado(nextExecution);
		} catch (Exception e) {
			fin=new Date();
			jobEjecucionViewModel=new JobEjecucionViewModel();
			jobEjecucionViewModel.setIdJobEjecucion(result.getIdJobEjecucion());
			jobEjecucionViewModel.setFechaInicio(inicio);
			jobEjecucionViewModel.setFechaFin(fin);
			jobEjecucionViewModel.setEstado("E");
			jobEjecucionViewModel.setResultadoMensaje(e.getMessage());
			jobEjecucionViewModel.setEjecutado(1);
			jobEjecucionViewModel.setFechaProgramado(nextExecution);
		}
		jobEjecucionService.actualizarJobEjecucion(jobEjecucionViewModel, "JOB_MARCA_INCO");
		JobEjecucionViewModel next=new JobEjecucionViewModel();
		next.setEjecutado(0);
		next.setEstado("N");
		Date nextExecution2= ce.getNextValidTimeAfter(nextExecution);
		next.setFechaProgramado(nextExecution2);
		jobEjecucionService.registarJobEjecucionEnBlanco(next, "JOB_MARCA_INCO");
	}
		
	//@Scheduled(cron="${spring.cronFrecuency.JOB_RECA_MARCA}")
	public void recalcularMarcaciones(){
		Date inicio=new Date();
		Date fin=null;
		JobEjecucionViewModel jobEjecucionViewModel=null;
		CronExpression ce=null;
		try {
			ce = new CronExpression(cron_JOB_RECA_MARCA);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date nextExecution= ce.getNextValidTimeAfter(inicio);
		JobEjecucionResultViewModel result=jobEjecucionService.obtenerEjecucionJobPendiente("JOB_RECA_MARCA");
		if(result==null ||result.getIdJobEjecucion()==null){
			JobEjecucionViewModel je=new JobEjecucionViewModel();
			je.setEjecutado(0);
			je.setEstado("N");
			je.setFechaProgramado(nextExecution);
			jobEjecucionService.registarJobEjecucionEnBlanco(je, "JOB_RECA_MARCA");
			result=jobEjecucionService.obtenerEjecucionJobPendiente("JOB_RECA_MARCA");
		}
		try{
			if(productionActive){
				
				List<Marcacion> marcaciones = marcacionJpaRepository.getMarcacionesByEstadoRecalcular("S");
				
				if(marcaciones!=null && marcaciones.size()>0){
					
					marcaciones.forEach(m->{
						m.setRecalcular("P");
						});
					
					marcacionJpaRepository.save(marcaciones);
					marcacionJpaRepository.flush();

				marcaciones = marcacionJpaRepository.getMarcacionesByEstadoRecalcular("P");
				
				Map<Long, Date> mapsEmpleado = new HashMap<>();
				
					for (Marcacion marcacion : marcaciones) {

						try{
							
							if (mapsEmpleado.get(marcacion.getEmpleado().getIdEmpleado()) == null) {
								mapsEmpleado.put(marcacion.getEmpleado().getIdEmpleado(), marcacion.getFecha());
							}
							
							Calendar c1 = DateUtil.buildCal(marcacion.getFecha());
							
							String diaCodigo = StringUtil.obtenerCodigoDia(c1.get(Calendar.DAY_OF_WEEK));
							
							HorarioEmpleado horarioEmpleado = horarioEmpleadoJpaRepository.obtenerHorarioPorFechaVigente(marcacion.getFecha(), marcacion.getEmpleado().getIdEmpleado());
							
							if(horarioEmpleado == null){
								continue;
							}
							
							HorarioEmpleadoDia horarioEmpleadoDia = horarioEmpleadoDiaJpaRepository.obtenerHorarioDiaPorDiaDeSemana(horarioEmpleado.getIdHorarioEmpleado(), diaCodigo);
							
							if(horarioEmpleadoDia == null){
								continue;
							}
							
							marcacion.setHoraIngresoHorario(horarioEmpleadoDia.getEntrada());
							marcacion.setHoraSalidaHorario(horarioEmpleadoDia.getSalida());
							
							Date fechaHoraIngreso =  DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraIngresoHorario());
							Date fechaHoraSalida=  DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraSalidaHorario());
							
							BigDecimal horasTrabajo = new BigDecimal((fechaHoraSalida.getTime()-fechaHoraIngreso.getTime())).divide(new BigDecimal((1000*60*60)),2,0);
							
							horasTrabajo = horasTrabajo.subtract(new BigDecimal(horarioEmpleadoDia.getTiempoAlmuerzo()));
							
							marcacion.setHorasTrabajoHorario(horasTrabajo);
							
							if(marcacion.getNoLaboral().intValue() == 1){
								
								marcacion.setEstado(EstadoMarcacionEnum.SIN_MARCAR.getCode());
								marcacion.setRecalcular("N");
								
							}else{
								
								boolean flagPermNoRec = false;
								boolean flagDiaEntero = false;
								
								List<PermisoEmpleado> permiso = permisoEmpleadoJpaRepository.obtenerPermisoAprobadoPorFechayPeriodoEmpleado(marcacion.getFecha(), marcacion.getEmpleado().getIdEmpleado());

								BigDecimal horasPermisos = new BigDecimal(0);

								if (permiso != null && permiso.size() > 0) {

									String horarioInicio = "";
									String horarioFin = "";

									for (PermisoEmpleado permisoEmp : permiso) {

										Date horaIngresoPerm = DateUtil.parse(new SimpleDateFormat("HH:mm"), permisoEmp.getHoraInicio());
										Date horaIngresoDia = DateUtil.parse(new SimpleDateFormat("HH:mm"), marcacion.getHoraIngresoHorario());

										Date horaSalidaPerm = DateUtil.parse(new SimpleDateFormat("HH:mm"), permisoEmp.getHoraFin());
										Date horaSalidaDia = DateUtil.parse(new SimpleDateFormat("HH:mm"), marcacion.getHoraSalidaHorario());

										horasPermisos = horasPermisos.add(permisoEmp.getHoras());
										
										if(permisoEmp.getDiaEntero().intValue() == 1){
											flagDiaEntero = true;
											horarioInicio = "";
											horarioFin = "";
											break;
										}
										
										if(permisoEmp.getMotivo().equals("N")){
											flagPermNoRec = true;
										}
										
										
										if (horaIngresoDia.equals(horaIngresoPerm)) {
											horarioInicio = permisoEmp.getHoraFin();
										}

										if (horaSalidaDia.equals(horaSalidaPerm)) {
											horarioFin = permisoEmp.getHoraInicio();
										}

									}

									if (!horarioInicio.equals("")) {
										marcacion.setHoraIngresoHorario(horarioInicio);
									}

									if (!horarioFin.equals("")) {
										marcacion.setHoraSalidaHorario(horarioFin);
									}
								}
								marcacion.setHorasPermiso(horasPermisos);

								List<PermisoEmpleadoRecuperacion> permisoRecuperacion = permisoEmpleadoRecuperacionJpaRepository.obtenerPermisosRecuperacionPorIdPeriodoEmpleadoYFecha(marcacion.getEmpleado().getIdEmpleado(), marcacion.getFecha());
								
								BigDecimal horasRecuperacion = new BigDecimal(0);
								
								if (permisoRecuperacion != null && permisoRecuperacion.size() > 0) {

									for (PermisoEmpleadoRecuperacion permisoEmp : permisoRecuperacion) {

										horasRecuperacion = horasRecuperacion.add(permisoEmp.getHoras()); 
												
									}
								}

								marcacion.setHorasRecuperacion(horasRecuperacion);
								
								marcacion.setHorasTrabajoHorario(marcacion.getHorasTrabajoHorario().subtract(marcacion.getHorasPermiso()).add(marcacion.getHorasRecuperacion()));
								
								HorasExtra horasExtra = horasExtraJpaRepository.findOneByIdEmpleadoAndFecha( marcacion.getFecha(), marcacion.getEmpleado().getIdEmpleado());

								if (horasExtra != null) {
									marcacion.setHorasExtra(horasExtra.getHorasExtra());
								}
								
								Vacacion vacacion = vacacionJpaRepository.obtenerVacaciones(marcacion.getFecha(), marcacion.getEmpleado().getIdEmpleado());
								List<Licencia> licencia = licenciaJpaRepository.obtenerLicenciasPorIdEmpleadoYFecha(marcacion.getFecha(), marcacion.getEmpleado().getIdEmpleado());
								
								if (vacacion != null) {
									
									marcacion.setEstado(EstadoMarcacionEnum.VACACIONES.getCode());
									marcacion.setHoraIngreso(null);
									marcacion.setHoraInicioAlmuerzo(null);
									marcacion.setHoraFinAlmuerzo(null);
									marcacion.setHoraSalida(null);
									
									marcacion.setHorasTrabajoHorario(new BigDecimal(0));
									marcacion.setHorasExtra(new BigDecimal(0));
									marcacion.setHorasRecuperacion(new BigDecimal(0));
									marcacion.setHorasTrabajoPendiente(new BigDecimal(0));
									marcacion.setHorasTrabajoReal(new BigDecimal(0));
									marcacion.setDemoraEntrada(new BigDecimal(0));
									marcacion.setDemoraAlmuerzo(new BigDecimal(0));
									marcacion.setDemoraSalida(new BigDecimal(0));
									marcacion.setRecalcular("N");
									
								}else if (licencia != null && licencia.size()>0) {
									
									marcacion.setEstado(EstadoMarcacionEnum.LICENCIA.getCode());
									marcacion.setHoraIngreso(null);
									marcacion.setHoraInicioAlmuerzo(null);
									marcacion.setHoraFinAlmuerzo(null);
									marcacion.setHoraSalida(null);
									
									marcacion.setHorasTrabajoHorario(new BigDecimal(0));
									marcacion.setHorasExtra(new BigDecimal(0));
									marcacion.setHorasRecuperacion(new BigDecimal(0));
									marcacion.setHorasTrabajoPendiente(new BigDecimal(0));
									marcacion.setHorasTrabajoReal(new BigDecimal(0));
									marcacion.setDemoraEntrada(new BigDecimal(0));
									marcacion.setDemoraAlmuerzo(new BigDecimal(0));
									marcacion.setDemoraSalida(new BigDecimal(0));
									marcacion.setRecalcular("N");
									
								}else if(flagDiaEntero){
									marcacion.setEstado(EstadoMarcacionEnum.PERMISO_COMPENSACION.getCode());
									marcacion.setHoraIngreso(null);
									marcacion.setHoraInicioAlmuerzo(null);
									marcacion.setHoraFinAlmuerzo(null);
									marcacion.setHoraSalida(null);
									
									marcacion.setHorasTrabajoHorario(new BigDecimal(0));
									marcacion.setHorasTrabajoReal(new BigDecimal(0));
									marcacion.setDemoraEntrada(new BigDecimal(0));
									marcacion.setDemoraAlmuerzo(new BigDecimal(0));
									marcacion.setDemoraSalida(new BigDecimal(0));
									
									BigDecimal horasPendientes = new BigDecimal(0);
									
									if(marcacion.getHorasExtra().intValue() > 0){
										horasPendientes = marcacion.getHorasExtra().multiply(new BigDecimal(-1));
									}else if(marcacion.getHorasExtra().intValue() <= 0){
										
										BigDecimal horasRealTemp = marcacion.getHorasTrabajoHorario().subtract(marcacion.getHorasTrabajoReal());
										if(horasRealTemp.intValue() < 0){
											horasRealTemp = new BigDecimal(0);
										}
										
										horasPendientes = horasRealTemp.subtract(marcacion.getHorasRecuperacion());
										
										if(!flagPermNoRec){
											horasPendientes = horasPendientes.add(marcacion.getHorasPermiso());
										}
										
									}
									
									marcacion.setHorasTrabajoPendiente(horasPendientes);
									
									marcacion.setRecalcular("N");
								}else if(marcacion.getEmpleado().getCategoriaEmpleado().equals("J")){
									marcacion.setEstado(EstadoMarcacionEnum.SIN_MARCAR.getCode());
									marcacion.setHoraIngreso(null);
									marcacion.setHoraInicioAlmuerzo(null);
									marcacion.setHoraFinAlmuerzo(null);
									marcacion.setHoraSalida(null);
									
									marcacion.setHorasExtra(new BigDecimal(0));
									marcacion.setHorasRecuperacion(new BigDecimal(0));
									marcacion.setHorasTrabajoPendiente(new BigDecimal(0));
									marcacion.setDemoraEntrada(new BigDecimal(0));
									marcacion.setDemoraAlmuerzo(new BigDecimal(0));
									marcacion.setDemoraSalida(new BigDecimal(0));
									marcacion.setRecalcular("N");
									
								}else if(marcacion.getEmpleado().getCategoriaEmpleado().equals("C")){
									marcacion.setHoraInicioAlmuerzo(null);
									marcacion.setHoraFinAlmuerzo(null);
									marcacion.setHoraSalida(null);
									
									marcacion.setHorasRecuperacion(new BigDecimal(0));
									marcacion.setHorasExtra(new BigDecimal(0));
									marcacion.setHorasTrabajoPendiente(new BigDecimal(0));
									marcacion.setDemoraEntrada(new BigDecimal(0));
									marcacion.setDemoraAlmuerzo(new BigDecimal(0));
									marcacion.setDemoraSalida(new BigDecimal(0));
									marcacion.setEstado(EstadoMarcacionEnum.SIN_MARCAR.getCode());
									marcacion.setRecalcular("N");
								
								}else{
									
									marcacion.setEstado(EstadoMarcacionEnum.INASISTENCIA.getCode());
									Integer numeroMarcaciones = horarioEmpleadoDia.getNumeroMarcaciones(); 
									
									if(numeroMarcaciones != null){
										if(numeroMarcaciones.intValue() == 2){
											if (marcacion.getHoraIngreso() != null) {

												marcacion.setEstado(EstadoMarcacionEnum.PUNTUAL.getCode());								
												Date fechaHoraMarcacion = DateUtil.parse(new SimpleDateFormat("HH:mm"), marcacion.getHoraIngreso());
												Date fechaHoraConfig = DateUtil.parse(new SimpleDateFormat("HH:mm"), marcacion.getHoraIngresoHorario());

												Date fechaHoraConfigAdd = DateUtil.addMinutes(fechaHoraConfig, 10);

												if (fechaHoraConfigAdd.before(fechaHoraMarcacion)) {
													marcacion.setEstado(EstadoMarcacionEnum.TARDANZA.getCode());
												
												} else if (fechaHoraConfig.before(fechaHoraMarcacion) && (fechaHoraConfigAdd.after(fechaHoraMarcacion) || fechaHoraConfigAdd.equals(fechaHoraMarcacion))) {
													fechaHoraConfig = fechaHoraMarcacion;
												}

												BigDecimal horaDemoraEntrada = new BigDecimal(
														(fechaHoraMarcacion.getTime() - fechaHoraConfig.getTime())).divide(new BigDecimal((1000 * 60)), 2, 0);

												marcacion.setDemoraEntrada(horaDemoraEntrada);
											}
											
											if(marcacion.getHoraInicioAlmuerzo() != null){
												marcacion.setHoraSalida(marcacion.getHoraInicioAlmuerzo());
												marcacion.setHoraInicioAlmuerzo(null);
											}
											
											if(marcacion.getHoraFinAlmuerzo() != null){
												marcacion.setHoraSalida(marcacion.getHoraFinAlmuerzo());
												marcacion.setHoraFinAlmuerzo(null);
												
											}
											
											if (marcacion.getHoraSalida() != null) {

												Date fechaHoraMarcacion = DateUtil.parse(new SimpleDateFormat("HH:mm"),
														marcacion.getHoraSalida());
												Date fechaHoraConfig = DateUtil.parse(new SimpleDateFormat("HH:mm"),
														marcacion.getHoraSalidaHorario());

												BigDecimal horaDemoraSalida = new BigDecimal(
														(fechaHoraMarcacion.getTime() - fechaHoraConfig.getTime()))
																.divide(new BigDecimal((1000 * 60)), 2, 0);

												marcacion.setDemoraSalida(horaDemoraSalida);

												BigDecimal horascal = marcacion.getHorasTrabajoHorario();

												BigDecimal minutoscal = marcacion.getDemoraEntrada().add(marcacion.getDemoraAlmuerzo());

												minutoscal = minutoscal.subtract(marcacion.getDemoraSalida());

												horascal = horascal.multiply(new BigDecimal(60));

												BigDecimal horasReal = horascal.subtract(minutoscal);

												horasReal = horasReal.divide(new BigDecimal(60), 2, 0);

												marcacion.setHorasTrabajoReal(horasReal);

												BigDecimal horasPendientes = new BigDecimal(0);
											
												if(marcacion.getHorasExtra().intValue() > 0){
													horasPendientes = marcacion.getHorasExtra().multiply(new BigDecimal(-1));
												}else if(marcacion.getHorasExtra().intValue() <= 0){
													
													BigDecimal horasRealTemp = marcacion.getHorasTrabajoHorario().subtract(marcacion.getHorasTrabajoReal());
													if(horasRealTemp.doubleValue() < 0){
														horasRealTemp = new BigDecimal(0);
													}
													
													horasPendientes = horasRealTemp.subtract(marcacion.getHorasRecuperacion());
													
													if(!flagPermNoRec){
														horasPendientes = horasPendientes.add(marcacion.getHorasPermiso());
													}
													
												}
												
												marcacion.setHorasTrabajoPendiente(horasPendientes);
														
											}
											
										}else if(numeroMarcaciones.intValue() == 4){
											if (marcacion.getHoraIngreso() != null) {

												marcacion.setEstado(EstadoMarcacionEnum.PUNTUAL.getCode());								
												Date fechaHoraMarcacion = DateUtil.parse(new SimpleDateFormat("HH:mm"), marcacion.getHoraIngreso());
												Date fechaHoraConfig = DateUtil.parse(new SimpleDateFormat("HH:mm"), marcacion.getHoraIngresoHorario());

												Date fechaHoraConfigAdd = DateUtil.addMinutes(fechaHoraConfig, 10);

												if (fechaHoraConfigAdd.before(fechaHoraMarcacion)) {
													marcacion.setEstado(EstadoMarcacionEnum.TARDANZA.getCode());
												
												} else if (fechaHoraConfig.before(fechaHoraMarcacion) && (fechaHoraConfigAdd.after(fechaHoraMarcacion) || fechaHoraConfigAdd.equals(fechaHoraMarcacion))) {
													fechaHoraConfig = fechaHoraMarcacion;
												}

												BigDecimal horaDemoraEntrada = new BigDecimal(
														(fechaHoraMarcacion.getTime() - fechaHoraConfig.getTime())).divide(new BigDecimal((1000 * 60)), 2, 0);

												marcacion.setDemoraEntrada(horaDemoraEntrada);
											}

											if (marcacion.getHoraFinAlmuerzo() != null) {

												BigDecimal tiempoAlmuerzo = new BigDecimal(1);

												tiempoAlmuerzo = new BigDecimal(horarioEmpleadoDia.getTiempoAlmuerzo());
												
												tiempoAlmuerzo = tiempoAlmuerzo.multiply(new BigDecimal(60));

												Date fechaHoraInicio = DateUtil.parse(new SimpleDateFormat("HH:mm"), marcacion.getHoraInicioAlmuerzo());
												Date fechaHoraFin = DateUtil.parse(new SimpleDateFormat("HH:mm"), marcacion.getHoraFinAlmuerzo());

												BigDecimal horaDemoraAlmuerzo = new BigDecimal((fechaHoraFin.getTime() - fechaHoraInicio.getTime())).divide(new BigDecimal((1000 * 60)), 2, 0);

												if (horaDemoraAlmuerzo.doubleValue() > tiempoAlmuerzo.doubleValue()) {
													horaDemoraAlmuerzo = horaDemoraAlmuerzo.subtract(tiempoAlmuerzo);
												} else {
													horaDemoraAlmuerzo = new BigDecimal(0);
												}

												marcacion.setDemoraAlmuerzo(horaDemoraAlmuerzo);

											}
											
											if (marcacion.getHoraSalida() != null) {

												Date fechaHoraMarcacion = DateUtil.parse(new SimpleDateFormat("HH:mm"),
														marcacion.getHoraSalida());
												Date fechaHoraConfig = DateUtil.parse(new SimpleDateFormat("HH:mm"),
														marcacion.getHoraSalidaHorario());

												BigDecimal horaDemoraSalida = new BigDecimal(
														(fechaHoraMarcacion.getTime() - fechaHoraConfig.getTime()))
																.divide(new BigDecimal((1000 * 60)), 2, 0);

												marcacion.setDemoraSalida(horaDemoraSalida);

												BigDecimal horascal = marcacion.getHorasTrabajoHorario();

												BigDecimal minutoscal = marcacion.getDemoraEntrada().add(marcacion.getDemoraAlmuerzo());

												minutoscal = minutoscal.subtract(marcacion.getDemoraSalida());

												horascal = horascal.multiply(new BigDecimal(60));

												BigDecimal horasReal = horascal.subtract(minutoscal);

												horasReal = horasReal.divide(new BigDecimal(60), 2, 0);

												marcacion.setHorasTrabajoReal(horasReal);

												BigDecimal horasPendientes = new BigDecimal(0);

												if(marcacion.getHorasExtra().intValue() > 0){
													horasPendientes = marcacion.getHorasExtra().multiply(new BigDecimal(-1));
												}else if(marcacion.getHorasExtra().intValue() <= 0){
													
													BigDecimal horasRealTemp = marcacion.getHorasTrabajoHorario().subtract(marcacion.getHorasTrabajoReal());
													if(horasRealTemp.doubleValue() < 0){
														horasRealTemp = new BigDecimal(0);
													}
													
													horasPendientes = horasRealTemp.subtract(marcacion.getHorasRecuperacion());
													
													if(!flagPermNoRec){
														horasPendientes = horasPendientes.add(marcacion.getHorasPermiso());
													}
													
												}
												
												marcacion.setHorasTrabajoPendiente(horasPendientes);
											}
										}
									}
									
									marcacion.setRecalcular("N");
								}
								
							}
							
						}catch(Exception e){
							
							marcacion.setRecalcular("E");
							e.printStackTrace();
						}
						
						marcacionJpaRepository.save(marcacion);
						marcacionJpaRepository.flush();
					}
					
			        mapsEmpleado.forEach((k,v)->{
			        	recalcularCompensacion(k,v);
			        });
				}
			}
			
			fin=new Date();
			jobEjecucionViewModel=new JobEjecucionViewModel();
			jobEjecucionViewModel.setIdJobEjecucion(result.getIdJobEjecucion());
			jobEjecucionViewModel.setFechaInicio(inicio);
			jobEjecucionViewModel.setFechaFin(fin);
			jobEjecucionViewModel.setEstado("F");
			jobEjecucionViewModel.setResultadoMensaje("OK");
			jobEjecucionViewModel.setEjecutado(1);
			jobEjecucionViewModel.setFechaProgramado(nextExecution);
		} catch (Exception e) {
			fin=new Date();
			jobEjecucionViewModel=new JobEjecucionViewModel();
			jobEjecucionViewModel.setIdJobEjecucion(result.getIdJobEjecucion());
			jobEjecucionViewModel.setFechaInicio(inicio);
			jobEjecucionViewModel.setFechaFin(fin);
			jobEjecucionViewModel.setEstado("E");
			jobEjecucionViewModel.setResultadoMensaje(e.getMessage());
			jobEjecucionViewModel.setEjecutado(1);
			jobEjecucionViewModel.setFechaProgramado(nextExecution);
		}	
		
		jobEjecucionService.actualizarJobEjecucion(jobEjecucionViewModel, "JOB_RECA_MARCA");
		JobEjecucionViewModel next=new JobEjecucionViewModel();
		next.setEjecutado(0);
		next.setEstado("N");
		Date nextExecution2= ce.getNextValidTimeAfter(nextExecution);
		next.setFechaProgramado(nextExecution2);
		jobEjecucionService.registarJobEjecucionEnBlanco(next, "JOB_RECA_MARCA");
	}
	
	private void recalcularCompensacion(Long idEmpleado, Date fechaInicioCompensacion){

		String dateCadena = DateUtil.fmtDt(new Date());
		Date today =DateUtil.formatoFechaArchivoMarcacion(dateCadena);
		
		do{
			Integer year = fechaInicioCompensacion.getYear();
			Integer month = fechaInicioCompensacion.getMonth()+1;
			String mesComp = StringUtil.autocompleteZeroLeft(String.valueOf(month))+"/"+String.valueOf(year+1900);
			
			List<Marcacion> marcaciones = marcacionJpaRepository.getAllByIdEmpleadoAndMes(idEmpleado,year+1900,month,today);
			
			int vacaciones = 0;
			int tardanzas = 0;
			int inasistencias = 0;
			int licencias = 0;
			
			BigDecimal horasPendientes = new BigDecimal(0);
			BigDecimal horasTardanzaIngreso = new BigDecimal(0);
			BigDecimal horasTardanzaSalida = new BigDecimal(0);
			BigDecimal horasDemoraAlmuerzo = new BigDecimal(0);
			BigDecimal horasTrabajadas = new BigDecimal(0);
			BigDecimal horarioHorasTrabajo = new BigDecimal(0);
			
			for (Marcacion marcacion : marcaciones) {
							
				if(marcacion.getEstado().equals(EstadoMarcacionEnum.SIN_MARCAR.getCode()) || marcacion.getEstado().equals(EstadoMarcacionEnum.VACACIONES.getCode()) || marcacion.getEstado().equals(EstadoMarcacionEnum.LICENCIA.getCode())){
					marcacion.setHorasTrabajoPendiente(new BigDecimal(0));
				}/*else{
					marcacion.setHorasTrabajoPendiente(marcacion.getHorasTrabajoPendiente().add(marcacion.getHorasExtra()).subtract(marcacion.getHorasPermiso()));
				}*/
				
				if(marcacion.getEstado().equals(EstadoMarcacionEnum.VACACIONES.getCode())){
					vacaciones = vacaciones + 1;
				}else if(marcacion.getEstado().equals(EstadoMarcacionEnum.LICENCIA.getCode())){
					licencias = licencias + 1;
				}else if(marcacion.getEstado().equals(EstadoMarcacionEnum.INASISTENCIA.getCode())){
					inasistencias = inasistencias + 1;
				}else if(marcacion.getEstado().equals(EstadoMarcacionEnum.TARDANZA.getCode())){
					tardanzas = tardanzas + 1;
				}
							
				horasTardanzaIngreso = horasTardanzaIngreso.add(marcacion.getDemoraEntrada().divide(new BigDecimal(60),2,0));
				horasTardanzaSalida = horasTardanzaSalida.add(marcacion.getDemoraSalida().divide(new BigDecimal(60),2,0));
				horasDemoraAlmuerzo = horasDemoraAlmuerzo.add(marcacion.getDemoraAlmuerzo().divide(new BigDecimal(60),2,0));
				horasTrabajadas = horasTrabajadas.add(marcacion.getHorasTrabajoReal());
				horarioHorasTrabajo = horarioHorasTrabajo.add(marcacion.getHorasTrabajoHorario());
				horasPendientes = horasPendientes.add(marcacion.getHorasTrabajoPendiente());
				
			}
			
			EmpleadoCompensacion compensacion = compensacionJpaRepository.findByMonthAndIdEmpleado(idEmpleado, mesComp);
			
			if(compensacion != null){
				
				compensacion.setHorasTardanzaIngreso(horasTardanzaIngreso);
				compensacion.setHorasTardanzaSalida(horasTardanzaSalida);
				compensacion.setHorasDemoraAlmuerzo(horasDemoraAlmuerzo);
				compensacion.setHorasTrabajadas(horasTrabajadas);
				compensacion.setHorarioHorasTrabajo(horarioHorasTrabajo);
				compensacion.setHorasPendientesTotal(horasPendientes);
				
				compensacion.setTardanzas(String.valueOf(tardanzas));
				compensacion.setInasistencias(inasistencias);
				compensacion.setVacaciones(vacaciones);
				compensacion.setLicencias(licencias);
			
				compensacionJpaRepository.save(compensacion);
				compensacionJpaRepository.flush();
			}
			
			fechaInicioCompensacion.setMonth(fechaInicioCompensacion.getMonth()+1);
			
		}while((fechaInicioCompensacion.getMonth()<=today.getMonth()) && (fechaInicioCompensacion.getYear()<=today.getYear()));
		
	}
		
	private boolean crearMarcacionEmpleado(Long idEmpleado, Date fecha)throws Exception{

		Empleado empleado = empleadoJpaRepository.findOne(idEmpleado);
		String fechaCadena = DateUtil.fmtDt(fecha);
		
		if(empleado != null){
			Integer noLaboral = 0;
			Marcacion marcacion = new Marcacion();
			Calendario calendario = calendarioJpaRepository.getByDate(DateUtil.formatoFechaArchivoMarcacion(fechaCadena));
			
			if(calendario != null){
				noLaboral = 1;
			}
			
			if(noLaboral.intValue() == 1){
				marcacion.setEstado(EstadoMarcacionEnum.SIN_MARCAR.getCode());
				marcacion.setEmpleado(empleado);
				marcacion.setFecha(DateUtil.formatoFechaArchivoMarcacion(fechaCadena));
				
				if(empleado.getCategoriaEmpleado().equals("C") || empleado.getCategoriaEmpleado().equals("J")){
					marcacion.setEsPersonaDeConfianza(new Integer(1));
				}else{
					marcacion.setEsPersonaDeConfianza(new Integer(0));
				}
				
				marcacion.setHorasTrabajoHorario(new BigDecimal(0));
				marcacion.setHorasPermiso(new BigDecimal(0));
				marcacion.setHorasExtra(new BigDecimal(0));
				marcacion.setHorasRecuperacion(new BigDecimal(0));
				marcacion.setHorasTrabajoPendiente(new BigDecimal(0));
				marcacion.setHorasTrabajoReal(new BigDecimal(0));
				marcacion.setDemoraEntrada(new BigDecimal(0));
				marcacion.setDemoraAlmuerzo(new BigDecimal(0));
				marcacion.setDemoraSalida(new BigDecimal(0));
				marcacion.setRecalcular("N");
				marcacion.setNoLaboral(noLaboral);
				
			}else{
				StringBuffer sbEmpleadosHorarios=new StringBuffer();
				Calendar c1 = DateUtil.buildCal(fecha);
				String diaCodigo = StringUtil.obtenerCodigoDia(c1.get(Calendar.DAY_OF_WEEK));
				
				marcacion.setEmpleado(empleado);
				marcacion.setFecha(DateUtil.formatoFechaArchivoMarcacion(fechaCadena));
								
				if(empleado.getCategoriaEmpleado().equals("C") || empleado.getCategoriaEmpleado().equals("J")){
					marcacion.setEstado(EstadoMarcacionEnum.SIN_MARCAR.getCode());
					marcacion.setEsPersonaDeConfianza(new Integer(1));
				}else{
					marcacion.setEstado(EstadoMarcacionEnum.INASISTENCIA.getCode());
					marcacion.setEsPersonaDeConfianza(new Integer(0));
				}
				
				
				HorarioEmpleado horarioEmpleado = horarioEmpleadoJpaRepository.obtenerHorarioPorFechaVigente(marcacion.getFecha(), empleado.getIdEmpleado());
				
				if(horarioEmpleado == null){
					
					sbEmpleadosHorarios.append("<tr>").
					append("<td>").append(empleado.getNombre()+" "+empleado.getApellidoPaterno()+" "+empleado.getApellidoMaterno()).append("</td>")
					.append("<td>").append(" No existe horario ").append("</td>").append("</tr>");
					
					return false;
				}
				
				HorarioEmpleadoDia horarioEmpleadoDia = horarioEmpleadoDiaJpaRepository.obtenerHorarioDiaPorDiaDeSemana(horarioEmpleado.getIdHorarioEmpleado(), diaCodigo);
				
				if(horarioEmpleadoDia == null){

					sbEmpleadosHorarios.append("<tr>").
					append("<td>").append(empleado.getNombre()+" "+empleado.getApellidoPaterno()+" "+empleado.getApellidoMaterno()).append("</td>")
					.append("<td>").append(" No existe dia para el horario ").append("</td>").append("</tr>");
					
					return false;
				}
				
				if(horarioEmpleadoDia.getLaboral().intValue() == 1){
					marcacion.setHoraIngresoHorario(horarioEmpleadoDia.getEntrada());
					marcacion.setHoraSalidaHorario(horarioEmpleadoDia.getSalida());
					
					Date fechaHoraIngreso =  DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraIngresoHorario());
					Date fechaHoraSalida=  DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraSalidaHorario());
					
					BigDecimal horasTrabajo = new BigDecimal((fechaHoraSalida.getTime()-fechaHoraIngreso.getTime())).divide(new BigDecimal((1000*60*60)),2,0);
					
					horasTrabajo = horasTrabajo.subtract(new BigDecimal(horarioEmpleadoDia.getTiempoAlmuerzo()));
					
					marcacion.setHorasTrabajoHorario(horasTrabajo);
					
					PeriodoEmpleado periodo = periodoEmpleadoJpaRepository.obtenerPeriodoEmpleadoPorFechayEmpleado(marcacion.getFecha(), empleado.getIdEmpleado());
					
					if(periodo == null){

						sbEmpleadosHorarios.append("<tr>").
						append("<td>").append(empleado.getNombre()+" "+empleado.getApellidoPaterno()+" "+empleado.getApellidoMaterno()).append("</td>")
						.append("<td>").append(" No existe periodo ").append("</td>").append("</tr>");
						
						return false;
					}
					
					List<PermisoEmpleado> permisos = permisoEmpleadoJpaRepository.obtenerPermisoPorFechayPeriodoEmpleado(marcacion.getFecha(), periodo.getIdPeriodoEmpleado());
					
					if(permisos != null && permisos.size()>0){
						
						String horarioInicio = "";
						String horarioFin = "";
						
						BigDecimal horasPermiso = new BigDecimal(0);
						
						for (PermisoEmpleado permisoEmpleado : permisos) {
							
							Date fechaHoraIngresoPermiso =  DateUtil.parse(new SimpleDateFormat("HH:mm"),permisoEmpleado.getHoraInicio());
							Date fechaHoraIngresoDia=  DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraIngresoHorario());
							
							Date fechaHoraSalidaPermiso=  DateUtil.parse(new SimpleDateFormat("HH:mm"),permisoEmpleado.getHoraFin());
							Date fechaHoraSalidaDia=  DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraSalidaHorario());
							
							
							if(fechaHoraIngresoDia.equals(fechaHoraIngresoPermiso)){
								horarioInicio = permisoEmpleado.getHoraFin();
							}
							
							if(fechaHoraSalidaDia.equals(fechaHoraSalidaPermiso)){
								horarioFin = permisoEmpleado.getHoraInicio();
							}
							
							horasPermiso = horasPermiso.add(permisoEmpleado.getHoras());
							
						}
						
						if(!horarioInicio.equals("")){
							marcacion.setHoraIngresoHorario(horarioInicio);
						}
						
						if(!horarioFin.equals("")){
							marcacion.setHoraSalidaHorario(horarioFin);
						}
						
						marcacion.setHorasPermiso(horasPermiso);
					
					}else{
						marcacion.setHorasPermiso(new BigDecimal(0));
					}
					
					Vacacion vacacion = vacacionJpaRepository.obtenerVacaciones(marcacion.getFecha(), empleado.getIdEmpleado());
					List<Licencia> licencia = licenciaJpaRepository.obtenerLicenciasPorIdEmpleadoYFecha(marcacion.getFecha(), empleado.getIdEmpleado());
					
					if(vacacion != null){
						marcacion.setEstado(EstadoMarcacionEnum.VACACIONES.getCode());
					}else if(licencia != null && licencia.size()>0){
						marcacion.setEstado(EstadoMarcacionEnum.LICENCIA.getCode());
					}else if(empleado.getCategoriaEmpleado().equals("J") || empleado.getCategoriaEmpleado().equals("C")){
						marcacion.setEstado(EstadoMarcacionEnum.SIN_MARCAR.getCode());
					}else{
						marcacion.setEstado(EstadoMarcacionEnum.INASISTENCIA.getCode());
					}
					
					marcacion.setHorasExtra(new BigDecimal(0));
					marcacion.setHorasRecuperacion(new BigDecimal(0));
					marcacion.setHorasTrabajoPendiente(new BigDecimal(0));
					marcacion.setHorasTrabajoReal(new BigDecimal(0));
					marcacion.setDemoraEntrada(new BigDecimal(0));
					marcacion.setDemoraAlmuerzo(new BigDecimal(0));
					marcacion.setDemoraSalida(new BigDecimal(0));
					marcacion.setRecalcular("N");
					marcacion.setNoLaboral(noLaboral);
										
				}else{
					marcacion.setEmpleado(empleado);
					marcacion.setFecha(DateUtil.formatoFechaArchivoMarcacion(fechaCadena));
					
					if(empleado.getCategoriaEmpleado().equals("J") || empleado.getCategoriaEmpleado().equals("C")){
						marcacion.setEsPersonaDeConfianza(new Integer(1));
					}else{
						marcacion.setEsPersonaDeConfianza(new Integer(0));
					}
					marcacion.setHoraIngresoHorario(null);
					marcacion.setHoraSalidaHorario(null);
					marcacion.setHorasTrabajoHorario(new BigDecimal(0));
					marcacion.setHorasPermiso(new BigDecimal(0));
					
					marcacion.setHorasRecuperacion(new BigDecimal(0));
					marcacion.setHorasExtra(new BigDecimal(0));
					marcacion.setHorasTrabajoPendiente(new BigDecimal(0));
					marcacion.setHorasTrabajoReal(new BigDecimal(0));
					marcacion.setDemoraEntrada(new BigDecimal(0));
					marcacion.setDemoraAlmuerzo(new BigDecimal(0));
					marcacion.setDemoraSalida(new BigDecimal(0));
					marcacion.setRecalcular("N");
					marcacion.setNoLaboral(noLaboral);
										
				}
			}
			
			marcacionJpaRepository.save(marcacion);
			marcacionJpaRepository.flush();
			
		}
		return true;
		
	}
	
	//@Scheduled(cron="${spring.cronFrecuency.JOB_INASISTENCIA}")
	public void enviarAlertasInasistenciasEmpleado(){
		GeneraMsjeAlertaEmpleadoViewModel msjeAlertaDto;
		Map<String,String> parametrosMensaje;
		Date inicio=new Date();
		Date fin=null;
		JobEjecucionViewModel jobEjecucionViewModel=null;
		CronExpression ce=null;
		try {
			ce = new CronExpression(JOB_INASISTENCIA);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date nextExecution= ce.getNextValidTimeAfter(inicio);
		JobEjecucionResultViewModel result=jobEjecucionService.obtenerEjecucionJobPendiente("JOB_INASISTENCIA");
		if(result==null ||result.getIdJobEjecucion()==null){
			JobEjecucionViewModel je=new JobEjecucionViewModel();
			je.setEjecutado(0);
			je.setEstado("N");
			je.setFechaProgramado(nextExecution);
			jobEjecucionService.registarJobEjecucionEnBlanco(je, "JOB_INASISTENCIA");
			result=jobEjecucionService.obtenerEjecucionJobPendiente("JOB_INASISTENCIA");
		}
				
		try{
			if(productionActive){
						
				Date fechaActual = new Date();
				String fechaCadena = DateUtil.fmtDt(fechaActual);
				
				AlertaViewModel alerta03=alertaService.obtenerAlerta("Alerta03");
				
				if(alerta03 != null){
					if(alerta03.getTipoAlerta().equals("I")){
						
						List<Marcacion> marcaciones = marcacionJpaRepository.getMarcacionInasistentesByDate(DateUtil.formatoFechaArchivoMarcacion(fechaCadena));
						
						if(marcaciones!= null && marcaciones.size() > 0){
						
							for (Marcacion marcacion : marcaciones) {
								
								if(alerta03.getAlertaDashboard().intValue() == 1){
									msjeAlertaDto=new GeneraMsjeAlertaEmpleadoViewModel();
									msjeAlertaDto.setIdAlerta(alerta03.getIdAlerta());
									msjeAlertaDto.setIdEmpleado(marcacion.getEmpleado().getIdEmpleado());
									parametrosMensaje=new HashMap<String,String>();
									parametrosMensaje.put("Fecha", fechaCadena);
									msjeAlertaDto.setParametrosMsje(parametrosMensaje);
									alertaService.generarMensajeAlerta(msjeAlertaDto);
								}
								
								if(alerta03.getCorreoElectronico().intValue() == 1){
									EmpleadoViewModel empleadoViewModel=new EmpleadoViewModel();
									empleadoViewModel.setIdEmpleado(marcacion.getEmpleado().getIdEmpleado());
									
									String asunto=alerta03.getAsunto().replace("[Fecha]", fechaCadena);
									String cuerpo=alerta03.getCuerpo().replace("[Fecha]", fechaCadena);
									
									List<String> correos=new ArrayList<String>();
									String correoEmpleado = marcacion.getEmpleado().getEmailInterno();
									correos.add(correoEmpleado);

									mailService.sendEmail(asunto, cuerpo, correos.toArray(new String[correos.size()]));
								}
							}
						}
					}
				}
				
				AlertaViewModel alerta04=alertaService.obtenerAlerta("Alerta04");
				
				if(alerta04 != null){
					if(alerta04.getTipoAlerta().equals("G")){
						
						if(alerta04.getCorreoElectronico().intValue() == 1){
							
							if(alerta04.getJefeProyecto() != null && alerta04.getJefeProyecto().intValue() == 1){
								
								List<Proyecto> proyectos = proyectoJpaRepository.getAllProyectosActivos();
								
								if(proyectos != null && proyectos.size()>0){
									
									for (Proyecto proyecto : proyectos) {
										List<MarcacionViewModel> marcaciones = marcacionJdbcRepository.getMarcacionesByIdProyecto(DateUtil.formatoFechaArchivoMarcacion(fechaCadena), proyecto.getIdProyecto());
									
										
										StringBuilder sb=new StringBuilder("");
										sb.append("<table>");
										for(MarcacionViewModel marcacionViewModel : marcaciones){
											sb.append("<tr>");
											sb.append("<td>").append(marcacionViewModel.getNombreCompletoEmpleado()).append("</td>");
											sb.append("</tr>");
										}
										sb.append("</table>");
										
										String asunto=alerta04.getAsunto().replace("[Fecha]", fechaCadena);
										asunto=asunto.replace("[Proyecto, Departamento, Unidad de Negocio o Empresa]", proyecto.getNombre());
										
										String cuerpo=alerta04.getCuerpo().replace("[Fecha]", fechaCadena);
										cuerpo=cuerpo.replace("[ListaEmpleados]", sb.toString());
										
										List<String> correos=new ArrayList<String>();
										String emailJefe = proyecto.getJefe().getEmailInterno();
										correos.add(emailJefe);
										
										for(AlertaSubscriptorViewModel alertaSubscriptorDto:alerta04.getSubscriptores()) {
											String emailInterno=alertaSubscriptorDto.getEmailInternoEmpleado();
											correos.add(emailInterno);
										}
										mailService.sendEmail(asunto, cuerpo, correos.toArray(new String[correos.size()]));
									}
								}
							}
						}
					}
				}
						
			}		
				
			fin=new Date();
			jobEjecucionViewModel=new JobEjecucionViewModel();
			jobEjecucionViewModel.setIdJobEjecucion(result.getIdJobEjecucion());
			jobEjecucionViewModel.setFechaInicio(inicio);
			jobEjecucionViewModel.setFechaFin(fin);
			jobEjecucionViewModel.setEstado("F");
			jobEjecucionViewModel.setResultadoMensaje("OK");
			jobEjecucionViewModel.setEjecutado(1);
			jobEjecucionViewModel.setFechaProgramado(nextExecution);
		}catch (Exception e) {
			fin=new Date();
			jobEjecucionViewModel=new JobEjecucionViewModel();
			jobEjecucionViewModel.setIdJobEjecucion(result.getIdJobEjecucion());
			jobEjecucionViewModel.setFechaInicio(inicio);
			jobEjecucionViewModel.setFechaFin(fin);
			jobEjecucionViewModel.setEstado("E");
			jobEjecucionViewModel.setResultadoMensaje(e.getMessage());
			jobEjecucionViewModel.setEjecutado(1);
			jobEjecucionViewModel.setFechaProgramado(nextExecution);
			
			
			e.printStackTrace();
		}
		jobEjecucionService.actualizarJobEjecucion(jobEjecucionViewModel, "JOB_INASISTENCIA");
		JobEjecucionViewModel next=new JobEjecucionViewModel();
		next.setEjecutado(0);
		next.setEstado("N");
		Date nextExecution2= ce.getNextValidTimeAfter(nextExecution);
		next.setFechaProgramado(nextExecution2);
		jobEjecucionService.registarJobEjecucionEnBlanco(next, "JOB_INASISTENCIA");
	}
	
	//@Scheduled(cron="${spring.cronFrecuency.JOB_VALID_ORDEN_MARC}")
	public void validarOrdenMarcaciones(){
		
		if(productionActive){
			
			String fechaCadena = DateUtil.fmtDt(new Date());
			Date fecha = DateUtil.formatoFechaArchivoMarcacion(fechaCadena);
			List<Marcacion> marcaciones = marcacionJpaRepository.obtenerMarcacionesActualizadasDelDia(fecha);
			
			 if(marcaciones != null && marcaciones.size()>0){
				 
				 for (Marcacion marcacion : marcaciones) {
					
					 List<String> horasMarcadas = new ArrayList<>();
					 
					 if(marcacion.getHoraIngreso() != null){
						 horasMarcadas.add(marcacion.getHoraIngreso());
					 }
					 if(marcacion.getHoraInicioAlmuerzo() != null){
						 horasMarcadas.add(marcacion.getHoraInicioAlmuerzo());
					 }
					 if(marcacion.getHoraFinAlmuerzo() != null){
						 horasMarcadas.add(marcacion.getHoraFinAlmuerzo());
					 }
					 if(marcacion.getHoraSalida() != null){
						 horasMarcadas.add(marcacion.getHoraSalida());
					 }
					 
					 if(horasMarcadas.size()>0){
						
						 int cant = horasMarcadas.size();
						 
						 String[] temp = horasMarcadas.toArray(new String[cant]);
						 
						 boolean flagSort = false;
						 
						 for(int i =0; i < (cant-1);i++){
							 
							 for(int j =0; j < (cant-1-i);j++){
								 String hora1 = temp[j];
								 String hora2 = temp[j+1];
								 
								 Date fechaHora1 = DateUtil.parse(new SimpleDateFormat("HH:mm"),hora1);
								 Date fechaHora2 = DateUtil.parse(new SimpleDateFormat("HH:mm"),hora2);
									
								 if(fechaHora1.after(fechaHora2)){
									 temp[j] = hora2;
									 temp[j+1] = hora1;
									 flagSort = true;
								 }
							 }
						 }
						 
						 if(flagSort){
							 
							 if(cant == 4){
								 marcacion.setHoraIngreso(temp[0]);
								 marcacion.setHoraInicioAlmuerzo(temp[1]);
								 marcacion.setHoraFinAlmuerzo(temp[2]);
								 marcacion.setHoraSalida(temp[3]);
							 }
							 else if(cant == 3){
								 marcacion.setHoraIngreso(temp[0]);
								 marcacion.setHoraInicioAlmuerzo(temp[1]);
								 marcacion.setHoraFinAlmuerzo(temp[2]);
							 }
							 else if(cant == 2){
								 marcacion.setHoraIngreso(temp[0]);
								 marcacion.setHoraInicioAlmuerzo(temp[1]);
							 }
							 
							 marcacion.setRecalcular("S");
							 
							 marcacionJpaRepository.save(marcacion);
							 marcacionJpaRepository.flush();
							 
						 }
					 }
				}
				 recalcularMarcaciones();
			 }
		}
	}
	
	//@Scheduled(cron="${spring.cronFrecuency.JOB_TARDANZA_GROUP}")
	public void tardanzaProyectoDepartamentoUnidadNegocioEmpresa(){
		
		Date inicio=new Date();
		Date fin=null;
		JobEjecucionViewModel jobEjecucionViewModel=null;
		CronExpression ce=null;
		try {
			ce = new CronExpression(JOB_TARDANZA_GROUP);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Date nextExecution= ce.getNextValidTimeAfter(inicio);
		JobEjecucionResultViewModel result=jobEjecucionService.obtenerEjecucionJobPendiente("JOB_TARDANZA_GROUP");
		if(result==null ||result.getIdJobEjecucion()==null){
			JobEjecucionViewModel je=new JobEjecucionViewModel();
			je.setEjecutado(0);
			je.setEstado("N");
			je.setFechaProgramado(nextExecution);
			jobEjecucionService.registarJobEjecucionEnBlanco(je, "JOB_TARDANZA_GROUP");
			result=jobEjecucionService.obtenerEjecucionJobPendiente("JOB_TARDANZA_GROUP");
		}
		try{
			if(productionActive){						
					/*Calendar c= Calendar.getInstance();
					c.setTime(new Date());
					c.add(Calendar.DATE, -2);*/
					
					String dateCadena = DateUtil.fmtDt(new Date());
					//String dateCadena = DateUtil.fmtDt(c.getTime());
					
					Date today =DateUtil.formatoFechaArchivoMarcacion(dateCadena);
					
					AlertaViewModel alertaDto02=alertaService.obtenerAlerta("Alerta02");
					if(alertaDto02 != null){
						if(alertaDto02.getTipoNotificacion().equals("G")){
							if(alertaDto02.getCorreoElectronico().intValue() == 1){
								
								if(alertaDto02.getJefeProyecto() != null && alertaDto02.getJefeProyecto() == 1){
									
									List<Proyecto> proyectoEntity = proyectoJpaRepository.getAllProyectosActivos();
									
									if(proyectoEntity != null && proyectoEntity.size() > 0){
										
										for(Proyecto proyecto: proyectoEntity){
											List<MarcacionViewModel> empleadosTardanzaPorProyecto = 
																				marcacionJdbcRepository.getEmpleadosPorTardanzaPorProyecto(today, proyecto.getIdProyecto());
											if(empleadosTardanzaPorProyecto.size()>0){												
												
												StringBuilder sb=new StringBuilder("");
												sb.append("<hr>");
												sb.append("<table>");
												sb.append("<td>").append("Nombre Empleado").append("</td>");
												sb.append("<td>").append("Hora Esperada").append("</td>");
												sb.append("<td>").append("Hora Ingreso").append("</td>");
												sb.append("<td>").append("Demora").append("</td>");
												sb.append("</tr>");												
												for(MarcacionViewModel n:empleadosTardanzaPorProyecto){										
													sb.append("<tr>");
													sb.append("<td>").append(n.getNombreCompletoEmpleado()).append("</td>");
													sb.append("<td>").append(n.getHoraIngresoHorario()).append("</td>");
													sb.append("<td>").append(n.getHoraIngreso()).append("</td>");													
													sb.append("<td>").append(n.getDemoraEntrada()).append("</td>");
													sb.append("</tr>");
												}
												sb.append("</table>");
												
												DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
												String asunto=alertaDto02.getAsunto().replace("[Fecha]", dateFormat.format(new Date()));
												asunto = asunto.replace("[Proyecto, Departamento, Unidad de Negocio o Empresa]", proyecto.getNombre());
												
												String cuerpo=alertaDto02.getCuerpo().replace("[Fecha]", dateFormat.format(new Date()));
												cuerpo= cuerpo.replace("[ListaEmpleados]", sb.toString());
												
												List<String> correos=new ArrayList<String>();
												String emailJefe = proyecto.getJefe().getEmailInterno();
												correos.add(emailJefe);
												
												for(AlertaSubscriptorViewModel alertaSubscriptorDto:alertaDto02.getSubscriptores()) {													
													String emailInterno=alertaSubscriptorDto.getEmailInternoEmpleado();
													correos.add(emailInterno);								
												}												
												
												mailService.sendEmail(asunto, cuerpo, correos.toArray(new String[correos.size()]));
											}
										}
									}
								}
								if(alertaDto02.getJefeDepartamento() != null && alertaDto02.getJefeDepartamento().intValue() == 1){
									List<DepartamentoArea> departamentos = departamentoAreaJpaRepository.getAllDepartamentosActivos();
									
									if(departamentos != null && departamentos.size()>0){
										
										for (DepartamentoArea departamentoArea : departamentos) {
											List<MarcacionViewModel> tardanzasByDepartamentoArea = marcacionJdbcRepository.getTardanzasByIdDepartamentoArea(today, departamentoArea.getIdDepartamentoArea());
										
										 if(tardanzasByDepartamentoArea.size()>0){
											 
											StringBuilder sb=new StringBuilder("");
											sb.append("<hr>");
											sb.append("<table>");
											sb.append("<td>").append("Nombre Empleado").append("</td>");
											sb.append("<td>").append("Hora Esperada").append("</td>");
											sb.append("<td>").append("Hora Ingreso").append("</td>");
											sb.append("<td>").append("Demora").append("</td>");
											sb.append("</tr>");												
											for(MarcacionViewModel n:tardanzasByDepartamentoArea){
												sb.append("<tr>");
												sb.append("<td>").append(n.getNombreCompletoEmpleado()).append("</td>");
												sb.append("<td>").append(n.getHoraIngresoHorario()).append("</td>");
												sb.append("<td>").append(n.getHoraIngreso()).append("</td>");													
												sb.append("<td>").append(n.getDemoraEntrada()).append("</td>");
												sb.append("</tr>");
											}
											sb.append("</table>");
											
											DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
											String asunto=alertaDto02.getAsunto().replace("[Fecha]", dateFormat.format(new Date()));
											asunto=asunto.replace("[Proyecto, Departamento, Unidad de Negocio o Empresa]", departamentoArea.getNombre());
											
											String cuerpo=alertaDto02.getCuerpo().replace("[Fecha]", dateFormat.format(new Date()));
											cuerpo=cuerpo.replace("[ListaEmpleados]", sb.toString());
											
											List<String> correos=new ArrayList<String>();
											String emailJefe = departamentoArea.getEmpleadoJefe().getEmailInterno();
											correos.add(emailJefe);
											
											for(AlertaSubscriptorViewModel alertaSubscriptorDto:alertaDto02.getSubscriptores()) {
												String emailInterno=alertaSubscriptorDto.getEmailInternoEmpleado();
												correos.add(emailInterno);
											}
											mailService.sendEmail(asunto, cuerpo, correos.toArray(new String[correos.size()]));
										 }
										}
									}
								}
								if(alertaDto02.getJefeUnidad() != null && alertaDto02.getJefeUnidad().intValue() == 1){
									
									List<UnidadDeNegocio> unidades = unidadDeNegocioJpaRepository.getAllUnidadDeNegocioActivos();
									
									if(unidades != null && unidades.size()>0){
										
										for (UnidadDeNegocio unidad : unidades) {
											List<MarcacionViewModel> tardanzasByIdUnidadDeNegocio = marcacionJdbcRepository.getTardanzasByIdUnidadDeNegocio(today, unidad.getIdUnidadDeNegocio());
										
											if(tardanzasByIdUnidadDeNegocio.size()>0){
												StringBuilder sb=new StringBuilder("");
												sb.append("<hr>");
												sb.append("<table>");
												sb.append("<td>").append("Nombre Empleado").append("</td>");
												sb.append("<td>").append("Hora Esperada").append("</td>");
												sb.append("<td>").append("Hora Ingreso").append("</td>");
												sb.append("<td>").append("Demora").append("</td>");
												sb.append("</tr>");												
												for(MarcacionViewModel n:tardanzasByIdUnidadDeNegocio){
													sb.append("<tr>");
													sb.append("<td>").append(n.getNombreCompletoEmpleado()).append("</td>");
													sb.append("<td>").append(n.getHoraIngresoHorario()).append("</td>");
													sb.append("<td>").append(n.getHoraIngreso()).append("</td>");													
													sb.append("<td>").append(n.getDemoraEntrada()).append("</td>");
													sb.append("</tr>");
												}
												sb.append("</table>");
												
												DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
												String asunto=alertaDto02.getAsunto().replace("[Fecha]", dateFormat.format(new Date()));
												asunto=asunto.replace("[Proyecto, Departamento, Unidad de Negocio o Empresa]", unidad.getNombre());
												
												String cuerpo=alertaDto02.getCuerpo().replace("[Fecha]", dateFormat.format(new Date()));
												cuerpo=cuerpo.replace("[ListaEmpleados]", sb.toString());
												
												List<String> correos=new ArrayList<String>();
												String emailJefe = unidad.getEmpleadoJefe().getEmailInterno();
												correos.add(emailJefe);
												
												for(AlertaSubscriptorViewModel alertaSubscriptorDto:alertaDto02.getSubscriptores()) {
													String emailInterno=alertaSubscriptorDto.getEmailInternoEmpleado();
													correos.add(emailInterno);
												}
												mailService.sendEmail(asunto, cuerpo, correos.toArray(new String[correos.size()]));
											}
										}
									}
								}
							}
						}
					}
					
					
					
			}
			fin=new Date();
			jobEjecucionViewModel=new JobEjecucionViewModel();
			jobEjecucionViewModel.setIdJobEjecucion(result.getIdJobEjecucion());
			jobEjecucionViewModel.setFechaInicio(inicio);
			jobEjecucionViewModel.setFechaFin(fin);
			jobEjecucionViewModel.setEstado("F");
			jobEjecucionViewModel.setResultadoMensaje("OK");
			jobEjecucionViewModel.setEjecutado(1);
			jobEjecucionViewModel.setFechaProgramado(nextExecution);
		}catch (Exception e) {
			fin=new Date();
			jobEjecucionViewModel=new JobEjecucionViewModel();
			jobEjecucionViewModel.setIdJobEjecucion(result.getIdJobEjecucion());
			jobEjecucionViewModel.setFechaInicio(inicio);
			jobEjecucionViewModel.setFechaFin(fin);
			jobEjecucionViewModel.setEstado("E");
			jobEjecucionViewModel.setResultadoMensaje(e.getMessage());
			jobEjecucionViewModel.setEjecutado(1);
			jobEjecucionViewModel.setFechaProgramado(nextExecution);
			e.printStackTrace();
		}
		jobEjecucionService.actualizarJobEjecucion(jobEjecucionViewModel, "JOB_TARDANZA_GROUP");
		JobEjecucionViewModel next=new JobEjecucionViewModel();
		next.setEjecutado(0);
		next.setEstado("N");
		Date nextExecution2= ce.getNextValidTimeAfter(nextExecution);
		next.setFechaProgramado(nextExecution2);
		jobEjecucionService.registarJobEjecucionEnBlanco(next, "JOB_TARDANZA_GROUP");
		
		
	}

	public void registrarHoraIngreso(Marcacion marcacion, String horaIngreso){
		
		try{
			marcacion.setHoraIngreso(horaIngreso);
			
			marcacion.setEstado(EstadoMarcacionEnum.PUNTUAL.getCode());
			
			if(marcacion.getHoraIngresoHorario() == null){
				marcacionJpaRepository.save(marcacion);
				marcacionJpaRepository.flush();
				return;
			}
			
			Date fechaHoraMarcacion = DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraIngreso());
			Date fechaHoraConfig =  DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraIngresoHorario());
			
			Date fechaHoraConfigAdd = DateUtil.addMinutes(fechaHoraConfig, 10);
				
			boolean msjTardanza = false;
			
			if(fechaHoraConfigAdd.before(fechaHoraMarcacion)){
				marcacion.setEstado(EstadoMarcacionEnum.TARDANZA.getCode());
				msjTardanza = true;
				
			}else if(fechaHoraConfig.before(fechaHoraMarcacion) && (fechaHoraConfigAdd.after(fechaHoraMarcacion) || fechaHoraConfigAdd.equals(fechaHoraMarcacion))){
	
				fechaHoraConfig = fechaHoraMarcacion;
			}
			
			BigDecimal horaDemoraEntrada = new BigDecimal((fechaHoraMarcacion.getTime()-fechaHoraConfig.getTime())).divide(new BigDecimal((1000*60)),2,0);
			
			marcacion.setDemoraEntrada(horaDemoraEntrada);
			
			marcacionJpaRepository.save(marcacion);
			marcacionJpaRepository.flush();
			
			GeneraMsjeAlertaEmpleadoViewModel msjeAlertaDto;
			Map<String,String> parametrosMensaje;
				
		
			if(msjTardanza){
				AlertaViewModel alertaDto=alertaService.obtenerAlerta("Alerta01");
				msjeAlertaDto=new GeneraMsjeAlertaEmpleadoViewModel();
				msjeAlertaDto.setIdAlerta(alertaDto.getIdAlerta());
				msjeAlertaDto.setIdEmpleado(marcacion.getEmpleado().getIdEmpleado());
				parametrosMensaje=new HashMap<String,String>();
				parametrosMensaje.put("Fecha", new SimpleDateFormat("dd/MM/yyyy").format(marcacion.getFecha()));
				BigDecimal demoraTardanza = new BigDecimal((fechaHoraMarcacion.getTime()-fechaHoraConfig.getTime())).divide(new BigDecimal((1000*60)),2,0);
				parametrosMensaje.put("Minutos Tardanza", demoraTardanza.toString());
				parametrosMensaje.put("Hora Entrada",new SimpleDateFormat("HH:mm").format(fechaHoraMarcacion));
				msjeAlertaDto.setParametrosMsje(parametrosMensaje);
				alertaService.generarMensajeAlerta(msjeAlertaDto);

				EmpleadoViewModel empleadoViewModel=new EmpleadoViewModel();
				empleadoViewModel.setIdEmpleado(marcacion.getEmpleado().getIdEmpleado());
				List<String> correos=new ArrayList<String>();
				Empleado empleado=empleadoJpaRepository.findOne(marcacion.getEmpleado().getIdEmpleado());
				String nombre=empleado.getNombre()+" "+empleado.getApellidoPaterno()+" "+empleado.getApellidoMaterno();
				String asunto=alertaDto.getAsunto().replace("[Fecha]", new SimpleDateFormat("dd/MM/yyyy").format(marcacion.getFecha()));
				String cuerpo=alertaDto.getCuerpo().replace("[Fecha]", new SimpleDateFormat("dd/MM/yyyy").format(marcacion.getFecha()));
				cuerpo=cuerpo.replace("[NombreEmpleado]", nombre);
				cuerpo=cuerpo.replace("[Minutos Tardanza]", demoraTardanza.toString());
				cuerpo=cuerpo.replace("[Hora Entrada]", new SimpleDateFormat("HH:mm").format(fechaHoraMarcacion));
				
				Empleado emp=empleadoJpaRepository.findOne(marcacion.getEmpleado().getIdEmpleado());
				correos.add(emp.getEmailInterno());
				mailService.sendEmail(asunto, cuerpo, correos.toArray(new String[correos.size()]));
			}
		}catch(Exception e){
			e.printStackTrace(); 
		}
		
	}

	public void registrarHoraSalida(Marcacion marcacion, String horaSalida){

		try{
			if(marcacion.getHoraSalidaHorario() == null){
				marcacion.setHoraSalida(horaSalida);
				marcacionJpaRepository.save(marcacion);
				marcacionJpaRepository.flush();
				return;
			}
			
			HorasExtra horasExtra = horasExtraJpaRepository.findOneByIdEmpleadoAndFecha(marcacion.getFecha(), marcacion.getEmpleado().getIdEmpleado());
			
			if(horasExtra != null){
				marcacion.setHorasExtra(horasExtra.getHorasExtra());
			}
			
			marcacion.setHoraSalida(horaSalida);
			
			boolean flagPermNoRec = false;
				
			List<PermisoEmpleado> permiso = permisoEmpleadoJpaRepository.obtenerPermisoAprobadoPorFechayPeriodoEmpleado(marcacion.getFecha(), marcacion.getEmpleado().getIdEmpleado());
			
			BigDecimal horasPermisos = new BigDecimal(0);
			
			if(permiso != null && permiso.size() > 0){
				
				String horarioInicio = "";
				String horarioFin = "";
				
				for (PermisoEmpleado permisoEmp : permiso) {
					
					Date horaIngresoPerm =  DateUtil.parse(new SimpleDateFormat("HH:mm"),permisoEmp.getHoraInicio());
					Date horaIngresoDia=  DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraIngresoHorario());
					
					Date horaSalidaPerm=  DateUtil.parse(new SimpleDateFormat("HH:mm"),permisoEmp.getHoraFin());
					Date horaSalidaDia=  DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraSalidaHorario());
					
					horasPermisos = horasPermisos.add(permisoEmp.getHoras());
					
					if(permisoEmp.getDiaEntero().intValue() == 1){
						horarioInicio = "";
						horarioFin = "";
						break;
					}
					
					if(permisoEmp.getMotivo().equals("N")){
						flagPermNoRec = true;
					}
					
					if(horaIngresoDia.equals(horaIngresoPerm)){
						horarioInicio = permisoEmp.getHoraFin();
					}
					
					if(horaSalidaDia.equals(horaSalidaPerm)){
						horarioFin = permisoEmp.getHoraInicio();
					}
									
				}
				
				if(!horarioInicio.equals("")){
					marcacion.setHoraIngresoHorario(horarioInicio);
				}
				
				if(!horarioFin.equals("")){
					marcacion.setHoraSalidaHorario(horarioFin);
				}
				
			}
			
			marcacion.setHorasPermiso(horasPermisos);
			
			List<PermisoEmpleadoRecuperacion> permisoRecuperacion = permisoEmpleadoRecuperacionJpaRepository.obtenerPermisosRecuperacionPorIdPeriodoEmpleadoYFecha(marcacion.getEmpleado().getIdEmpleado(), marcacion.getFecha());
			
			BigDecimal horasRecuperacion = new BigDecimal(0);
						
			if(permisoRecuperacion != null && permisoRecuperacion.size() > 0){
							
				for (PermisoEmpleadoRecuperacion permisoEmp : permisoRecuperacion) {
					horasRecuperacion = horasRecuperacion.add(permisoEmp.getHoras());
				}
				
			}
			
			marcacion.setHorasRecuperacion(horasRecuperacion);
			
			marcacion.setHorasTrabajoHorario(marcacion.getHorasTrabajoHorario().subtract(marcacion.getHorasPermiso()).add(marcacion.getHorasRecuperacion()));
			
			Date fechaHoraMarcacion = DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraSalida());
			Date fechaHoraConfig =  DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraSalidaHorario());
			
			BigDecimal horaDemoraSalida = new BigDecimal((fechaHoraMarcacion.getTime()-fechaHoraConfig.getTime())).divide(new BigDecimal((1000*60)),2,0);
			
			marcacion.setDemoraSalida(horaDemoraSalida);
			
			BigDecimal horascal = marcacion.getHorasTrabajoHorario();
			
			BigDecimal minutoscal = marcacion.getDemoraEntrada().add(marcacion.getDemoraAlmuerzo());
					
			minutoscal = minutoscal.subtract(marcacion.getDemoraSalida());
			
			horascal=horascal.multiply(new BigDecimal(60));
			
			BigDecimal horasReal = horascal.subtract(minutoscal);
			
			horasReal = horasReal.divide(new BigDecimal(60),2,0);
			
			marcacion.setHorasTrabajoReal(horasReal);
			
			BigDecimal horasPendientes =new BigDecimal(0);
			
			if(marcacion.getHorasExtra().intValue() > 0){
				horasPendientes = marcacion.getHorasExtra().multiply(new BigDecimal(-1));
			}else if(marcacion.getHorasExtra().intValue() <= 0){
				
				BigDecimal horasRealTemp = marcacion.getHorasTrabajoHorario().subtract(marcacion.getHorasTrabajoReal());
				if(horasRealTemp.doubleValue() < 0){
					horasRealTemp = new BigDecimal(0);
				}
				
				horasPendientes = horasRealTemp.subtract(marcacion.getHorasRecuperacion());
				
				if(!flagPermNoRec){
					horasPendientes = horasPendientes.add(marcacion.getHorasPermiso());
				}
				
			}
			
			marcacion.setHorasTrabajoPendiente(horasPendientes);
				
			marcacionJpaRepository.save(marcacion);
			marcacionJpaRepository.flush();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
