package pe.com.tss.runakuna.service.impl;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.tss.runakuna.domain.model.entities.Calendario;
import pe.com.tss.runakuna.domain.model.entities.Empleado;
import pe.com.tss.runakuna.domain.model.entities.HorarioEmpleado;
import pe.com.tss.runakuna.domain.model.entities.HorarioEmpleadoDia;
import pe.com.tss.runakuna.domain.model.entities.Licencia;
import pe.com.tss.runakuna.domain.model.entities.Marcacion;
import pe.com.tss.runakuna.domain.model.entities.PeriodoEmpleado;
import pe.com.tss.runakuna.domain.model.entities.PermisoEmpleado;
import pe.com.tss.runakuna.domain.model.entities.RegistroMarcacionEmpleado;
import pe.com.tss.runakuna.domain.model.entities.SolicitudCambioMarcacion;
import pe.com.tss.runakuna.domain.model.entities.Vacacion;
import pe.com.tss.runakuna.domain.model.repository.jdbc.EmpleadoRepository;
import pe.com.tss.runakuna.domain.model.repository.jdbc.MarcacionRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.CalendarioJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.EmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.MarcacionJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.RegistroMarcacionEmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.SolicitudCambioMarcacionJpaRepository;
import pe.com.tss.runakuna.enums.EstadoMarcacionEnum;
import pe.com.tss.runakuna.enums.SeverityStatusEnum;
import pe.com.tss.runakuna.exception.BusinessException;
import pe.com.tss.runakuna.service.AlertaService;
import pe.com.tss.runakuna.service.MailService;
import pe.com.tss.runakuna.service.MarcacionEmpleadoService;
import pe.com.tss.runakuna.util.DateUtil;
import pe.com.tss.runakuna.view.model.*;

import static java.util.stream.Collectors.toList;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MarcacionEmpleadoServiceImpl implements MarcacionEmpleadoService {

	@Autowired
	Mapper mapper;
	
	@Autowired
	MarcacionJpaRepository marcacionJpaRepository;
	
	@Autowired
	MarcacionRepository marcacionJdbcRepository;
	
	@Autowired
	EmpleadoJpaRepository empleadoJpaRepository;
	
	@Autowired
	EmpleadoRepository empleadoRepository;
	
	@Autowired
	SolicitudCambioMarcacionJpaRepository solicitudCambioMarcacionJpaRepository;
	
	@Autowired
	RegistroMarcacionEmpleadoJpaRepository registroMarcacionEmpleadoJpaRepository;
	
	@Autowired
	CalendarioJpaRepository calendarioJpaRepository;
	
	@Autowired
	AlertaService alertaService;
	
	@Autowired
	MailService mailService;
	
	@Override
	public MarcacionViewModel obtenerMarcacionPorEmpleadoyFechaActual(EmpleadoViewModel empleado) {
		
		MarcacionViewModel dto = marcacionJdbcRepository.obtenerMarcacionesPorEmpleadoyFechaActual(empleado);
		
		return dto;
	}

	@Override
	public NotificacionViewModel registrarSolicitudCambioMarcacion(SolicitudCambioMarcacionViewModel solicitudCambioMarcacion) {
		
		NotificacionViewModel notificacion = new NotificacionViewModel();
		
		Marcacion marcacion = new Marcacion();
		
		mapper.map(solicitudCambioMarcacion.getMarcacion(), marcacion);
		
		Empleado empleado = empleadoJpaRepository.findOne(solicitudCambioMarcacion.getMarcacion().getIdEmpleado());
		
		marcacion.setEmpleado(empleado);
		
		marcacion.setRecalcular("S");
		
		marcacionJpaRepository.save(marcacion);
		marcacionJpaRepository.flush();
		
		notificacion.setCodigo(1L);
		notificacion.setSeverity("success");
		notificacion.setSummary("Runakuna Success");
		notificacion.setDetail("Se registro Correctamente.");
		return notificacion;
	}
	
	@Override
	public NotificacionViewModel solicitarCambioMarcacion(SolicitudCambioMarcacionViewModel solicitudCambioMarcacion) {
		
		NotificacionViewModel notificacion = new NotificacionViewModel();
		
		SolicitudCambioMarcacion entity = new SolicitudCambioMarcacion();
		
		mapper.map(solicitudCambioMarcacion, entity);
				
		Marcacion marcacion = marcacionJpaRepository.findOne(solicitudCambioMarcacion.getIdMarcacion());
		
		entity.setMarcacion(marcacion);
		entity.setEstado("P");
		
		Empleado atendidoPor = empleadoJpaRepository.findOne(solicitudCambioMarcacion.getIdAtendidoPor());
		entity.setAtendidoPor(atendidoPor);
		
		solicitudCambioMarcacionJpaRepository.save(entity);
		solicitudCambioMarcacionJpaRepository.flush();
		
		//enviar correo
		enviarAlertaMailSolicitarCambioMarcacion(entity);
		
		notificacion.setCodigo(1L);
		notificacion.setSeverity("success");
		notificacion.setSummary("Runakuna Success");
		notificacion.setDetail("Se registro Correctamente.");
		return notificacion;
	}
	
	@Override
	public SolicitudCambioMarcacionViewModel obtenerSolicitudCambio(Long idMarcacion) {
		
		SolicitudCambioMarcacionViewModel dto = new SolicitudCambioMarcacionViewModel();
		
		Marcacion marcacion = marcacionJpaRepository.findOne(idMarcacion);
		
		if(marcacion == null){
			return dto;
		}
		
		MarcacionViewModel marcacionVM = new MarcacionViewModel();
		
		mapper.map(marcacion, marcacionVM);
		
		
		
		marcacionVM.setNombreCompletoEmpleado(marcacion.getEmpleado().getApellidoPaterno()+" "+marcacion.getEmpleado().getApellidoMaterno()+", "+marcacion.getEmpleado().getNombre());
		marcacionVM.setIdEmpleado(marcacion.getEmpleado().getIdEmpleado());
				
		SolicitudCambioMarcacion solicitud = solicitudCambioMarcacionJpaRepository.findByIdMarcacion(idMarcacion, "P");
		
		if(solicitud == null){
			dto.setTieneSolicitudCambio(0);
		}else{
			dto.setIdAtendidoPor(solicitud.getAtendidoPor().getIdEmpleado());
			dto.setTieneSolicitudCambio(1);
			mapper.map(solicitud, dto);
		}
		
		
		
		List<RegistroMarcacionViewModel> registroMarcaciones = new ArrayList<>();
		List<RegistroMarcacionEmpleado> registros = registroMarcacionEmpleadoJpaRepository.findAllByIdEmpleadoAndFecha(marcacion.getEmpleado().getIdEmpleado(), marcacion.getFecha());
		marcacionVM.setRegistrosMarcaciones(new ArrayList<>());
		if(registros != null && registros.size() > 0){
			registroMarcaciones = registros.stream().map(m -> mapper.map(m, RegistroMarcacionViewModel.class)).collect(toList());
		
			marcacionVM.setRegistrosMarcaciones(registroMarcaciones);
		}
		
		dto.setMarcacion(marcacionVM);
		return dto;
	}

	@Override
	public List<MarcacionResultViewModel> quickSearch(QuickFilterViewModel quickFilter) {
		return empleadoRepository.busquedaRapidaMarcacionEmpleado(quickFilter);
	}
	
	@Override
	public List<MarcacionViewModel> getMarcacionesByFiltro(MarcacionFilterViewModel filter) {
		
		List<MarcacionViewModel> marcaciones = marcacionJdbcRepository.getMarcacionesByFiltro(filter);
		
		for (MarcacionViewModel marcacionViewModel : marcaciones) {
			if("Si".equals(marcacionViewModel.getSolicitudCambio())){
			
				List<SolicitudCambioMarcacionViewModel> solicitudes = new ArrayList<>();
				
				SolicitudCambioMarcacion solicitudEntity = solicitudCambioMarcacionJpaRepository.findByIdMarcacion(marcacionViewModel.getIdMarcacion(), "P");
				SolicitudCambioMarcacionViewModel solicitudVM = new SolicitudCambioMarcacionViewModel();
				mapper.map(solicitudEntity, solicitudVM);
				
				solicitudes.add(solicitudVM);
				marcacionViewModel.setSolicitudesCambioMarcacion(solicitudes);
				
			}
			
			List<RegistroMarcacionViewModel> registroMarcaciones = new ArrayList<>();
			List<RegistroMarcacionEmpleado> registros = registroMarcacionEmpleadoJpaRepository.findAllByIdEmpleadoAndFecha(marcacionViewModel.getIdEmpleado(), marcacionViewModel.getFecha());
			marcacionViewModel.setRegistrosMarcaciones(new ArrayList<>());
			if(registros != null && registros.size() > 0){
				registroMarcaciones = registros.stream().map(m -> mapper.map(m, RegistroMarcacionViewModel.class)).collect(toList());
			
				marcacionViewModel.setRegistrosMarcaciones(registroMarcaciones);
			}
		}
		
		return marcaciones;
	}
	
	@Override
	public NotificacionViewModel rechazarSolicitudCambioMarcacion(SolicitudCambioMarcacionViewModel solicitudCambioMarcacion) {
		
		NotificacionViewModel notificacion = new NotificacionViewModel();
		
		SolicitudCambioMarcacion entity = new SolicitudCambioMarcacion();
		
		mapper.map(solicitudCambioMarcacion, entity);
				
		Marcacion marcacion = new Marcacion();
		
		mapper.map(solicitudCambioMarcacion.getMarcacion(), marcacion);
		
		entity.setMarcacion(marcacion);
		
		Empleado atendidoPor = empleadoJpaRepository.findOne(solicitudCambioMarcacion.getIdAtendidoPor());
		entity.setAtendidoPor(atendidoPor);
		
		solicitudCambioMarcacionJpaRepository.save(entity);
		solicitudCambioMarcacionJpaRepository.flush();
		
		enviarAlertaMailCambioEstadoMarcacion(entity,"Rechazado");
		
		notificacion.setCodigo(1L);
		notificacion.setSeverity("success");
		notificacion.setSummary("Runakuna Success");
		notificacion.setDetail("Se rechazo Correctamente.");
		return notificacion;
	}
	
	@Override
	public NotificacionViewModel cancelarSolicitudCambioMarcacion(SolicitudCambioMarcacionViewModel solicitudCambioMarcacion) {NotificacionViewModel notificacion=new NotificacionViewModel();
	
	if (solicitudCambioMarcacionJpaRepository.findOne(solicitudCambioMarcacion.getIdSolicitudCambioMarcacion()) == null) {
		throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", "The record was changed by another user. Please re-enter the screen.");
	}
	
	try {	
		
		solicitudCambioMarcacionJpaRepository.delete(solicitudCambioMarcacion.getIdSolicitudCambioMarcacion());
		solicitudCambioMarcacionJpaRepository.flush();
		
		notificacion.setCodigo(1L);
		notificacion.setSeverity("success");
		notificacion.setSummary("Runakuna Success");
        notificacion.setDetail("Se cancelo la solicitud de cambio de marcacion.");
        
		
	} catch (Exception e) {
		throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", "Restriction is Being used, Can't be deleted");
	}
	
	return notificacion;}
	
	@Override
	public NotificacionViewModel aprobarSolicitudCambioMarcacion(SolicitudCambioMarcacionViewModel solicitudCambioMarcacion) {
		
		NotificacionViewModel notificacion = new NotificacionViewModel();
		
		SolicitudCambioMarcacion entity = new SolicitudCambioMarcacion();
		
		mapper.map(solicitudCambioMarcacion, entity);
				
		Marcacion marcacion = new Marcacion();
		
		mapper.map(solicitudCambioMarcacion.getMarcacion(), marcacion);
		
		entity.setMarcacion(marcacion);
		
		Empleado atendidoPor = empleadoJpaRepository.findOne(solicitudCambioMarcacion.getIdAtendidoPor());
		entity.setAtendidoPor(atendidoPor);
		
		solicitudCambioMarcacionJpaRepository.save(entity);
		solicitudCambioMarcacionJpaRepository.flush();
				
		Empleado empleado = empleadoJpaRepository.findOne(solicitudCambioMarcacion.getMarcacion().getIdEmpleado());
		
		marcacion.setEmpleado(empleado);
		
		marcacion.setRecalcular("S");
		
		marcacionJpaRepository.save(marcacion);
		marcacionJpaRepository.flush();
		
		enviarAlertaMailCambioEstadoMarcacion(entity,"Aprobado");
		
		notificacion.setCodigo(1L);
		notificacion.setSeverity("success");
		notificacion.setSummary("Runakuna Success");
		notificacion.setDetail("Se aprobo Correctamente.");
		return notificacion;
	}
	
	private void enviarAlertaMailSolicitarCambioMarcacion(SolicitudCambioMarcacion entity){
		
		try{
			AlertaViewModel alertaDto=alertaService.obtenerAlerta("ALERTA25");
			
			GeneraMsjeAlertaEmpleadoViewModel msjeAlertaDto=null;
			List<String> correos=new ArrayList<String>();
			correos.add(entity.getAtendidoPor().getEmailInterno());
			
			String nombreEmpleado = entity.getMarcacion().getEmpleado().getApellidoPaterno()+" "+entity.getMarcacion().getEmpleado().getApellidoMaterno()+", "+entity.getMarcacion().getEmpleado().getNombre();
			
			AlertaSubscriptorViewModel jefeInmediato = new AlertaSubscriptorViewModel();
			
			jefeInmediato.setIdEmpleado(entity.getAtendidoPor().getIdEmpleado());
			
			alertaDto.getSubscriptores().add(jefeInmediato);
			
			SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
			for(AlertaSubscriptorViewModel alertaSubscriptorDto:alertaDto.getSubscriptores()) {
				Empleado empl=empleadoJpaRepository.findOne(alertaSubscriptorDto.getIdEmpleado());
				correos.add(empl.getEmailInterno());
				msjeAlertaDto=new GeneraMsjeAlertaEmpleadoViewModel();
				msjeAlertaDto.setIdAlerta(alertaDto.getIdAlerta());
				msjeAlertaDto.setIdEmpleado(empl.getIdEmpleado());
				Map<String,String> parametrosMensaje=new HashMap<String,String>();
				parametrosMensaje.put("Empleado",nombreEmpleado);
				parametrosMensaje.put("Fecha",sdf.format(entity.getMarcacion().getFecha()));
				parametrosMensaje.put("Jefe Inmediato",entity.getAtendidoPor().getApellidoPaterno()+" "+entity.getAtendidoPor().getApellidoMaterno()+", "+entity.getAtendidoPor().getNombre());
				msjeAlertaDto.setParametrosMsje(parametrosMensaje);
				alertaService.generarMensajeAlerta(msjeAlertaDto);
			}
			if(correos.size()>0) {
				
				String asunto=alertaDto.getAsunto().replace("[Empleado]", nombreEmpleado);
				String cuerpo=alertaDto.getCuerpo().replace("[Empleado]", nombreEmpleado);
				cuerpo=cuerpo.replace("[Fecha]", sdf.format(entity.getMarcacion().getFecha()));
				cuerpo=cuerpo.replace("[Jefe Inmediato]", entity.getAtendidoPor().getApellidoPaterno()+" "+entity.getAtendidoPor().getApellidoMaterno()+", "+entity.getAtendidoPor().getNombre());
				mailService.sendEmail(asunto, cuerpo, correos.toArray(new String[correos.size()]));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void enviarAlertaMailCambioEstadoMarcacion(SolicitudCambioMarcacion entity, String estado){
		try{
			AlertaViewModel alertaDto=alertaService.obtenerAlerta("ALERTA26");
			GeneraMsjeAlertaEmpleadoViewModel msjeAlertaDto=new GeneraMsjeAlertaEmpleadoViewModel();
			msjeAlertaDto.setIdAlerta(alertaDto.getIdAlerta());
			msjeAlertaDto.setIdEmpleado(entity.getMarcacion().getEmpleado().getIdEmpleado());
			Map<String,String> parametrosMensaje=new HashMap<String,String>();
			SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
			parametrosMensaje.put("Estado",estado);
			parametrosMensaje.put("Fecha",sdf.format(entity.getMarcacion().getFecha()));
			msjeAlertaDto.setParametrosMsje(parametrosMensaje);
			alertaService.generarMensajeAlerta(msjeAlertaDto);
			
			String cuerpo=alertaDto.getCuerpo().replace("[Estado]", estado);
			cuerpo=cuerpo.replace("[Fecha]", sdf.format(entity.getMarcacion().getFecha()));
			
			String[] correos=new String[1];
			correos[0]=entity.getMarcacion().getEmpleado().getEmailInterno();
			mailService.sendEmail(alertaDto.getAsunto(), cuerpo, correos);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	public void crearMarcacionesDesdeFechaIngreso(EmpleadoViewModel empleado){
		
		try{
			Date fecha = empleado.getFechaIngreso();
			Date fechaActual = DateUtil.truncDay(new Date());
			
			
			
			while(fecha.before(fechaActual) || fecha.equals(fechaActual)){
				
				Integer noLaboral = new Integer(0);
				Calendario calendario = calendarioJpaRepository.getByDate(fecha);
				
				if(calendario != null){
					noLaboral = 1;
				}
				
				Empleado empleadoEntity = empleadoJpaRepository.getByCode(empleado.getCodigo());
					
				Marcacion marcacion = new Marcacion();
				marcacion.setEmpleado(empleadoEntity);
				marcacion.setFecha(fecha);
				marcacion.setEsPersonaDeConfianza(empleado.getEsPersonalDeConfianza());
					
				if(noLaboral.intValue() == 1){
						
						marcacion.setEstado(EstadoMarcacionEnum.SIN_MARCAR.getCode());
						
					}else{
						
						marcacion.setEstado(EstadoMarcacionEnum.INASISTENCIA.getCode());
						
					}
				
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
					
				marcacionJpaRepository.save(marcacion);
				marcacionJpaRepository.flush();
				
				
				fecha = DateUtil.addDays(fecha, 1);
			}
				
			
			
			
					
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
}
