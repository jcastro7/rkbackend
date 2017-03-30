package pe.com.empresa.rk.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.empresa.rk.domain.model.repository.jpa.CompensacionJpaRepository;
import pe.com.empresa.rk.domain.model.repository.jpa.LicenciaEmpleadoJpaRepository;
import pe.com.empresa.rk.view.model.GeneraMsjeAlertaEmpleadoViewModel;
import pe.com.empresa.rk.domain.model.entities.Empleado;
import pe.com.empresa.rk.domain.model.entities.EmpleadoCompensacion;
import pe.com.empresa.rk.domain.model.entities.HorarioEmpleado;
import pe.com.empresa.rk.domain.model.entities.HorarioEmpleadoDia;
import pe.com.empresa.rk.domain.model.entities.HorasExtra;
import pe.com.empresa.rk.domain.model.entities.Licencia;
import pe.com.empresa.rk.domain.model.entities.Marcacion;
import pe.com.empresa.rk.domain.model.entities.PeriodoEmpleado;
import pe.com.empresa.rk.domain.model.entities.PermisoEmpleado;
import pe.com.empresa.rk.domain.model.entities.RegistroMarcacionEmpleado;
import pe.com.empresa.rk.domain.model.entities.Vacacion;
import pe.com.empresa.rk.domain.model.repository.jdbc.MarcacionRepository;
import pe.com.empresa.rk.domain.model.repository.jpa.EmpleadoJpaRepository;
import pe.com.empresa.rk.domain.model.repository.jpa.HorarioEmpleadoDiaJpaRepository;
import pe.com.empresa.rk.domain.model.repository.jpa.HorarioEmpleadoJpaRepository;
import pe.com.empresa.rk.domain.model.repository.jpa.HorasExtraJpaRepository;
import pe.com.empresa.rk.domain.model.repository.jpa.MarcacionJpaRepository;
import pe.com.empresa.rk.domain.model.repository.jpa.PeriodoEmpleadoJpaRepository;
import pe.com.empresa.rk.domain.model.repository.jpa.PermisoEmpleadoJpaRepository;
import pe.com.empresa.rk.domain.model.repository.jpa.RegistroMarcacionEmpleadoJpaRepository;
import pe.com.empresa.rk.domain.model.repository.jpa.VacacionJpaRepository;
import pe.com.empresa.rk.view.model.AlertaViewModel;
import pe.com.empresa.rk.view.model.RegistroMarcacionEmpleadoViewModel;
import pe.com.empresa.rk.view.model.RegistroMarcacionViewModel;
import pe.com.empresa.rk.service.AlertaService;
import pe.com.empresa.rk.service.MailService;
import pe.com.empresa.rk.util.DateUtil;
import pe.com.empresa.rk.util.StringUtil;

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
    CompensacionJpaRepository compensacionJpaRepository;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	AlertaService alertaService;
	
	@Autowired
	Mapper mapper;
	
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
				
				registroMarcacionEmpleadoJpaRepository.save(entity);
				registroMarcacionEmpleadoJpaRepository.flush();
				
			}
		}
	}
	
	@Scheduled(cron="0 0/5 * 1/1 * ?")
	public void integrarMarcacionesSistemaAsistencia(){
		GeneraMsjeAlertaEmpleadoViewModel msjeAlertaDto;
		Map<String,String> parametrosMensaje;
		if(productionActive){
					
			List<RegistroMarcacionEmpleadoViewModel> marcaciones = marcacionJdbcRepository.obtenerMarcacion();
			
			if(marcaciones!= null && marcaciones.size() > 0){
				
				registrarMarcacionesEmpleado(marcaciones);		
				
				List<RegistroMarcacionViewModel> registros = marcacionJdbcRepository.obtenerRegistroMarcacionNoProcesado();
				
				marcacionJdbcRepository.updateRegistroMarcacionAProcesado(registros);
				
				for (RegistroMarcacionViewModel registroMarcacion : registros) {
					
					Marcacion marcacion = marcacionJpaRepository.obtenerMarcacionPorIdEmpleadoyDate(registroMarcacion.getIdEmpleado(), registroMarcacion.getFecha());
					if(marcacion != null){
						
						if(marcacion.getHoraIngreso() == null){
							
							marcacion.setHoraIngreso(registroMarcacion.getHora());
							marcacion.setInasistencia(0);
							
							Date fechaHoraMarcacion = DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraIngreso());
							Date fechaHoraConfig =  DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraIngresoHorario());
							
							Date fechaHoraConfigAdd = DateUtil.addMinutes(fechaHoraConfig, 10);
								
							if(fechaHoraConfigAdd.before(fechaHoraMarcacion)){
								marcacion.setTardanza(1);
								fechaHoraConfig = DateUtil.addMinutes(fechaHoraConfig, 10);
								
								AlertaViewModel alertaDto=alertaService.obtenerAlerta("Alerta01");
								msjeAlertaDto=new GeneraMsjeAlertaEmpleadoViewModel();
								msjeAlertaDto.setIdAlerta(alertaDto.getIdAlerta());
								msjeAlertaDto.setIdEmpleado(registroMarcacion.getIdEmpleado());
								parametrosMensaje=new HashMap<String,String>();
								parametrosMensaje.put("Fecha", new SimpleDateFormat("dd/MM/yyyy").format(registroMarcacion.getFecha()));
								BigDecimal horaDemoraEntrada = new BigDecimal((fechaHoraMarcacion.getTime()-fechaHoraConfig.getTime())).divide(new BigDecimal((1000*60)),2);
								parametrosMensaje.put("Minutos Tardanza", horaDemoraEntrada.toString());
								msjeAlertaDto.setParametrosMsje(parametrosMensaje);
								alertaService.generarMensajeAlerta(msjeAlertaDto);
								
								
								
							}else if(fechaHoraConfig.before(fechaHoraMarcacion) &&  fechaHoraConfigAdd.after(fechaHoraMarcacion)){
								fechaHoraConfig = fechaHoraMarcacion;
							}
							
							BigDecimal horaDemoraEntrada = new BigDecimal((fechaHoraMarcacion.getTime()-fechaHoraConfig.getTime())).divide(new BigDecimal((1000*60)),2);
							
							marcacion.setDemoraEntrada(horaDemoraEntrada);
							
							marcacionJpaRepository.save(marcacion);
							marcacionJpaRepository.flush();
						}else if(marcacion.getHoraInicioAlmuerzo() == null){
							
							marcacion.setHoraInicioAlmuerzo(registroMarcacion.getHora());
							
							marcacionJpaRepository.save(marcacion);
							marcacionJpaRepository.flush();
						}else if(marcacion.getHoraFinAlmuerzo() == null){
							
							marcacion.setHoraFinAlmuerzo(registroMarcacion.getHora());
							
							Calendar c1 = Calendar.getInstance();
							BigDecimal tiempoAlmuerzo = new BigDecimal(1);
							
							HorarioEmpleado horarioEmpleado = horarioEmpleadoJpaRepository.obtenerHorarioPorFechaVigente(marcacion.getFecha(), marcacion.getEmpleado().getIdEmpleado());
							if(horarioEmpleado == null){
							
								HorarioEmpleadoDia horarioEmpleadoDia = horarioEmpleadoDiaJpaRepository.obtenerHorarioDiaPorDiaDeSemana(horarioEmpleado.getIdHorarioEmpleado(), StringUtil.obtenerCodigoDia(c1.get(Calendar.DAY_OF_WEEK)));
							
								if(horarioEmpleadoDia == null){
									tiempoAlmuerzo = new BigDecimal(horarioEmpleadoDia.getTiempoAlmuerzo());
								}
							}
														
							tiempoAlmuerzo = tiempoAlmuerzo.multiply(new BigDecimal(60));
							
							Date fechaHoraInicio = DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraInicioAlmuerzo());
							Date fechaHoraFin =  DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraFinAlmuerzo());
							
							BigDecimal horaDemoraAlmuerzo = new BigDecimal((fechaHoraFin.getTime()-fechaHoraInicio.getTime())).divide(new BigDecimal((1000*60)),2);
							
							if(horaDemoraAlmuerzo.doubleValue() > tiempoAlmuerzo.doubleValue()){
								horaDemoraAlmuerzo = horaDemoraAlmuerzo.subtract(tiempoAlmuerzo);
							}else{
								horaDemoraAlmuerzo = new BigDecimal(0);
							}
							
							marcacion.setDemoraAlmuerzo(horaDemoraAlmuerzo);
							
							marcacionJpaRepository.save(marcacion);
							marcacionJpaRepository.flush();
						}else if(marcacion.getHoraSalida() == null){
							
							HorasExtra horasExtra = horasExtraJpaRepository.findOneByIdEmpleadoAndFecha(marcacion.getFecha(), marcacion.getEmpleado().getIdEmpleado());
							
							if(horasExtra != null){
								marcacion.setHorasExtra(new BigDecimal(horasExtra.getHoras()));
							}
							
							marcacion.setHoraSalida(registroMarcacion.getHora());
						
							Date fechaHoraMarcacion = DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraSalida());
							Date fechaHoraConfig =  DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraSalidaHorario());
							
							BigDecimal horaDemoraSalida = new BigDecimal((fechaHoraMarcacion.getTime()-fechaHoraConfig.getTime())).divide(new BigDecimal((1000*60)),2);
							
							marcacion.setDemoraSalida(horaDemoraSalida);
							
							BigDecimal horascal = marcacion.getHorasTrabajoHorario();
							
							BigDecimal minutoscal = marcacion.getDemoraEntrada().add(marcacion.getDemoraAlmuerzo());
									
							minutoscal = minutoscal.subtract(marcacion.getDemoraSalida());
							
							horascal=horascal.multiply(new BigDecimal(60));
							
							BigDecimal horasReal = horascal.subtract(minutoscal);
							
							horasReal = horasReal.divide(new BigDecimal(60),2);
							
							marcacion.setHorasTrabajoReal(horasReal);
							
							PeriodoEmpleado periodo = periodoEmpleadoJpaRepository.obtenerPeriodoEmpleadoPorFechayEmpleado(marcacion.getFecha(), marcacion.getEmpleado().getIdEmpleado());
							
							PermisoEmpleado permiso = permisoEmpleadoJpaRepository.obtenerPermisoSinCompensar(marcacion.getFecha(), periodo.getIdPeriodoEmpleado());
							
							if(permiso == null){
								permiso = new PermisoEmpleado();
								permiso.setHoras(new BigDecimal(0));
							}
							
							PermisoEmpleado permisoRecuperacion = permisoEmpleadoJpaRepository.obtenerPermisoPersonalRecuperacion(marcacion.getFecha(), periodo.getIdPeriodoEmpleado());
							if(permisoRecuperacion == null){
								permisoRecuperacion = new PermisoEmpleado();
								permisoRecuperacion.setHoras(new BigDecimal(0));
							}
							
							BigDecimal horasPendientes = marcacion.getHorasTrabajoHorario().subtract(marcacion.getHorasTrabajoReal()).subtract(permiso.getHoras()).add(permisoRecuperacion.getHoras());
							
							if(horasPendientes.doubleValue() >= 0){
								marcacion.setHorasTrabajoPendiente(horasPendientes);
							}else if(horasPendientes.doubleValue() < 0){
								marcacion.setHorasTrabajoPendiente(marcacion.getHorasExtra().multiply(new BigDecimal(-1)));
							}else{
								marcacion.setHorasTrabajoPendiente(new BigDecimal(0));
							}
							
							//marcacion.setHorasTrabajoPendiente(marcacion.getHorasTrabajoHorario().subtract(marcacion.getHorasTrabajoReal()));
							
							marcacionJpaRepository.save(marcacion);
							marcacionJpaRepository.flush();
							
						}					
					}
				}		
			}
		}
	}
	
	@Scheduled(cron="0 30 23 1/1 * ?")
	public void integrarCompensaciones(){
		if(productionActive){
			
			String dateCadena = DateUtil.fmtDt(new Date());
			
			Date today =DateUtil.formatoFechaArchivoMarcacion(dateCadena);
			
			today.setHours(today.getHours()-72);
			
			int year = today.getYear();
			String mesComp = StringUtil.autocompleteZeroLeft(String.valueOf(today.getMonth()+1))+"/"+String.valueOf(year+1900);
			
			List<Marcacion> marcaciones = marcacionJpaRepository.obtenerMarcacionesDelDia(today);
			
			for (Marcacion marcacion : marcaciones) {
			
				if(marcacion.getInasistencia() == 1 && marcacion.getEsPersonalDeConfianza() == 1){
					marcacion.setHorasTrabajoPendiente(marcacion.getHorasTrabajoHorario().add(marcacion.getHorasExtra()).subtract(marcacion.getHorasPermiso()));
				}
				
				int vacaciones = Integer.parseInt(String.valueOf(marcacion.getVacaciones()[0]));
				
				if(marcacion.getEsPersonalDeConfianza() == 1){
					marcacion.setHorasTrabajoPendiente(marcacion.getHorasTrabajoHorario().add(marcacion.getHorasExtra()).subtract(marcacion.getHorasPermiso()));
				}
				
				EmpleadoCompensacion compensacion = compensacionJpaRepository.findByMonthAndIdEmpleado(marcacion.getEmpleado().getIdEmpleado(), mesComp);
				
				if(compensacion == null){
					compensacion = new EmpleadoCompensacion();
					compensacion.setEmpleado(marcacion.getEmpleado());
					compensacion.setMes(mesComp);
					compensacion.setTardanzas(String.valueOf(marcacion.getTardanza()));
					compensacion.setHorasTardanzaIngreso(marcacion.getDemoraEntrada());
					compensacion.setHorasTardanzaSalida(marcacion.getDemoraSalida());
					compensacion.setHorasDemoraAlmuerzo(marcacion.getDemoraAlmuerzo());
					compensacion.setHorasTrabajadas(marcacion.getHorasTrabajoReal());
					compensacion.setHorarioHorasTrabajo(marcacion.getHorasTrabajoHorario());
					compensacion.setHorasPendientesTotal(marcacion.getHorasTrabajoPendiente());
					compensacion.setInasistencias(marcacion.getInasistencia());

					compensacion.setVacaciones(vacaciones);
					compensacion.setLicencias(Integer.parseInt(String.valueOf(marcacion.getLicencia()[0])));
					
				}else{
					
					int tardanzas = Integer.parseInt(compensacion.getTardanzas());
					tardanzas = tardanzas + marcacion.getTardanza();
					compensacion.setTardanzas(String.valueOf(tardanzas));
					compensacion.setHorasTardanzaIngreso(compensacion.getHorasTardanzaIngreso().add(marcacion.getDemoraEntrada()));
					compensacion.setHorasTardanzaSalida(compensacion.getHorasTardanzaSalida().add(marcacion.getDemoraSalida()));
					compensacion.setHorasDemoraAlmuerzo(compensacion.getHorasDemoraAlmuerzo().add(marcacion.getDemoraAlmuerzo()));
					compensacion.setHorasTrabajadas(compensacion.getHorasTrabajadas().add(marcacion.getHorasTrabajoReal()));
					compensacion.setHorarioHorasTrabajo(compensacion.getHorarioHorasTrabajo().add(marcacion.getHorasTrabajoHorario()));
					compensacion.setHorasPendientesTotal(compensacion.getHorasPendientesTotal().add(marcacion.getHorasTrabajoPendiente()));
					compensacion.setInasistencias(compensacion.getInasistencias() + marcacion.getInasistencia());
				
					vacaciones = vacaciones + compensacion.getVacaciones();
					
					compensacion.setVacaciones(vacaciones);
					
					int licencias = Integer.parseInt(String.valueOf(marcacion.getLicencia()[0]));
					licencias = licencias + compensacion.getLicencias();
					compensacion.setLicencias(licencias);
				}
				
				compensacionJpaRepository.save(compensacion);
				compensacionJpaRepository.flush();
			}
		}
	}
	
	@Scheduled(cron="0 30 0 1/1 * ?")
	public void crearMarcacionesSistemaAsistencia(){
		if(productionActive){
			Calendar c1 = Calendar.getInstance();
			
			
			List<Empleado> empleados = empleadoJpaRepository.buscarEmpleadosPorEstado("A");
			
			if(empleados != null && empleados.size() > 0){
			
				Date fechaActual = new Date();
				String fechaCadena = DateUtil.fmtDt(fechaActual);
				
				for (Empleado empleado : empleados) {
					
					Marcacion marcacionExist = marcacionJpaRepository.obtenerMarcacionPorIdEmpleadoyDate(empleado.getIdEmpleado(), DateUtil.formatoFechaArchivoMarcacion(fechaCadena));
					
					if(marcacionExist == null){
						
						Marcacion marcacion = new Marcacion();
						marcacion.setEmpleado(empleado);
						marcacion.setFecha(DateUtil.formatoFechaArchivoMarcacion(fechaCadena));
						marcacion.setEsPersonalDeConfianza(empleado.getEsPersonalDeConfianza());
						
						if(marcacion.getEsPersonalDeConfianza() == 1){
							marcacion.setInasistencia(0);
						}else{
							marcacion.setInasistencia(1);
						}
						
						marcacion.setTardanza(0);
						
						HorarioEmpleado horarioEmpleado = horarioEmpleadoJpaRepository.obtenerHorarioPorFechaVigente(marcacion.getFecha(), empleado.getIdEmpleado());
						
						
						if(horarioEmpleado == null){
							
							String correo = empleado.getEmailInterno();
							if(correo!=null && !correo.equals("")){
								mailService.sendEmail("Configuracion del Sistema", "Configurar El horario del empleado", correo);
							}
							
							continue;
						}
						
						HorarioEmpleadoDia horarioEmpleadoDia = horarioEmpleadoDiaJpaRepository.obtenerHorarioDiaPorDiaDeSemana(horarioEmpleado.getIdHorarioEmpleado(), StringUtil.obtenerCodigoDia(c1.get(Calendar.DAY_OF_WEEK)));
						
						if(horarioEmpleadoDia == null){
		
							String correo = empleado.getEmailInterno();
							if(correo!=null && !correo.equals("")){
								mailService.sendEmail("Configuracion del Sistema", "Configurar El horario dia del empleado", correo);
							}
							
							continue;
						}
						
						marcacion.setHoraIngresoHorario(horarioEmpleadoDia.getEntrada());
						marcacion.setHoraSalidaHorario(horarioEmpleadoDia.getSalida());
						
						PeriodoEmpleado periodo = periodoEmpleadoJpaRepository.obtenerPeriodoEmpleadoPorFechayEmpleado(marcacion.getFecha(), empleado.getIdEmpleado());
						
						if(periodo == null){
		
							String correo = empleado.getEmailInterno();
							if(correo!=null && !correo.equals("")){
								mailService.sendEmail("Configuracion del Sistema", "Configurar El periodo del empleado", correo);
							}
							
							continue;
						}
						
						PermisoEmpleado permisoEmpleadoEntity = permisoEmpleadoJpaRepository.obtenerPermisoPorFechayPeriodoEmpleado(marcacion.getFecha(), periodo.getIdPeriodoEmpleado());
						
						if(permisoEmpleadoEntity != null){
							
							Date fechaHoraIngresoPermiso =  DateUtil.parse(new SimpleDateFormat("HH:mm"),permisoEmpleadoEntity.getHoraInicio());
							Date fechaHoraIngresoDia=  DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraIngresoHorario());
							
							Date fechaHoraSalidaPermiso=  DateUtil.parse(new SimpleDateFormat("HH:mm"),permisoEmpleadoEntity.getHoraFin());
							Date fechaHoraSalidaDia=  DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraSalidaHorario());
							
							if(fechaHoraIngresoDia.equals(fechaHoraIngresoPermiso)){
								marcacion.setHoraIngresoHorario(permisoEmpleadoEntity.getHoraFin());
							}
							
							if(fechaHoraSalidaDia.equals(fechaHoraSalidaPermiso)){
								marcacion.setHoraSalidaHorario(permisoEmpleadoEntity.getHoraInicio());
							}
							
							marcacion.setHorasPermiso(permisoEmpleadoEntity.getHoras());
						
						}else{
							marcacion.setHorasPermiso(new BigDecimal(0));
						}
						
						Date fechaHoraIngreso =  DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraIngresoHorario());
						Date fechaHoraSalida=  DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraSalidaHorario());
						
						BigDecimal horasTrabajo = new BigDecimal((fechaHoraSalida.getTime()-fechaHoraIngreso.getTime())).divide(new BigDecimal((1000*60*60)),2);
						
						horasTrabajo = horasTrabajo.subtract(new BigDecimal(1));
						
						marcacion.setHorasTrabajoHorario(horasTrabajo);
						
						Vacacion vacacion = vacacionJpaRepository.obtenerVacaciones(marcacion.getFecha(), periodo.getIdPeriodoEmpleado());
						if(vacacion != null){
							marcacion.setVacaciones(new byte[]{1});
							marcacion.setInasistencia(0);
						}else{
							marcacion.setVacaciones(new byte[]{0});
						}
						
						Licencia licencia = licenciaJpaRepository.obtenerLicencia(marcacion.getFecha(), empleado.getIdEmpleado());
						
						if(licencia != null){
							marcacion.setLicencia(new byte[]{1});
							marcacion.setInasistencia(0);
						}else{
							marcacion.setLicencia(new byte[]{0});
						}
						
						marcacion.setHorasExtra(new BigDecimal(0));
						marcacion.setHorasTrabajoPendiente(new BigDecimal(0));
						marcacion.setHorasTrabajoReal(new BigDecimal(0));
						marcacion.setDemoraEntrada(new BigDecimal(0));
						marcacion.setDemoraAlmuerzo(new BigDecimal(0));
						marcacion.setDemoraSalida(new BigDecimal(0));
						
						marcacionJpaRepository.save(marcacion);
						
					}
				}
			}
		}
		
	}
	
	@Scheduled(cron="0 45 23 1/1 * ?")
	@Transactional
	public void enviarAlertasMarcacionesIncorrectas(){
		
		if(productionActive){
		
			String fechaCadena = DateUtil.fmtDt(new Date());
			
			Date fecha = DateUtil.formatoFechaArchivoMarcacion(fechaCadena);
			
			List<Marcacion> marcacionesIncorrectas = new ArrayList<>();
			
			List<Marcacion> marcacionesIncorrectasNoDePersonalConfianza = marcacionJpaRepository.obtenerMarcacionIncorrectasYNoEsPersonalDeConfianza(fecha);
			
			List<Marcacion> marcacionesIncorrectasDePersonalConfianza = marcacionJpaRepository.obtenerMarcacionIncorrectasYEsPersonalDeConfianza(fecha);
			
			marcacionesIncorrectas.addAll(marcacionesIncorrectasNoDePersonalConfianza);
			marcacionesIncorrectas.addAll(marcacionesIncorrectasDePersonalConfianza);
			
			AlertaViewModel alertaDto=alertaService.obtenerAlerta("AlertaMA");
			
			GeneraMsjeAlertaEmpleadoViewModel msjeAlertaDto;
			Map<String,String> parametrosMensaje;
			
			for(Marcacion marcacion :marcacionesIncorrectas) {
				
				msjeAlertaDto=new GeneraMsjeAlertaEmpleadoViewModel();
				msjeAlertaDto.setIdAlerta(alertaDto.getIdAlerta());
				msjeAlertaDto.setIdEmpleado(marcacion.getEmpleado().getIdEmpleado());
				parametrosMensaje=new HashMap<String,String>();
				parametrosMensaje.put("nombre", marcacion.getEmpleado().getNombre()+" "+marcacion.getEmpleado().getApellidoPaterno()
						+" "+marcacion.getEmpleado().getApellidoMaterno());
				parametrosMensaje.put("fecha", fechaCadena);
				msjeAlertaDto.setParametrosMsje(parametrosMensaje);
				alertaService.generarMensajeAlerta(msjeAlertaDto);
				
			}
			
			for(Marcacion marcacion :marcacionesIncorrectas) {
				
				StringBuilder sb=new StringBuilder(alertaDto.getCuerpo());
				
				String correo = marcacion.getEmpleado().getEmailInterno();
				if(correo!=null && !correo.equals("")){
					mailService.sendEmail(alertaDto.getAsunto(), sb.toString(), correo);
				}
			}
		}
	}
	
}
