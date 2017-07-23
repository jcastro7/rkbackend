package pe.com.tss.runakuna.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.tss.runakuna.domain.model.entities.HorarioEmpleado;
import pe.com.tss.runakuna.domain.model.entities.HorarioEmpleadoDia;
import pe.com.tss.runakuna.domain.model.entities.Marcacion;
import pe.com.tss.runakuna.domain.model.entities.Empleado;
import pe.com.tss.runakuna.domain.model.entities.PeriodoEmpleado;
import pe.com.tss.runakuna.domain.model.entities.PermisoEmpleado;
import pe.com.tss.runakuna.domain.model.entities.PermisoEmpleadoRecuperacion;
import pe.com.tss.runakuna.domain.model.repository.jdbc.EmpleadoRepository;
import pe.com.tss.runakuna.domain.model.repository.jdbc.HistoriaLaboralRepository;
import pe.com.tss.runakuna.domain.model.repository.jdbc.PeriodoEmpleadoRepository;
import pe.com.tss.runakuna.domain.model.repository.jdbc.PermisoEmpleadoJdbcRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.EmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.HorarioEmpleadoDiaJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.HorarioEmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.MarcacionJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.PeriodoEmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.PermisoEmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.PermisoEmpleadoRecuperacionJpaRepository;
import pe.com.tss.runakuna.enums.SeverityStatusEnum;
import pe.com.tss.runakuna.exception.BusinessException;
import pe.com.tss.runakuna.view.model.PermisoEmpleadoFilterViewModel;
import pe.com.tss.runakuna.view.model.PermisoEmpleadoRecuperacionViewModel;
import pe.com.tss.runakuna.view.model.PermisoEmpleadoResultViewModel;
import pe.com.tss.runakuna.view.model.AlertaSubscriptorViewModel;
import pe.com.tss.runakuna.view.model.AlertaViewModel;
import pe.com.tss.runakuna.view.model.EmpleadoCompensacionViewModel;
import pe.com.tss.runakuna.view.model.EmpleadoViewModel;
import pe.com.tss.runakuna.view.model.GeneraMsjeAlertaEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.HistoriaLaboralViewModel;
import pe.com.tss.runakuna.view.model.HorarioEmpleadoDiaViewModel;
import pe.com.tss.runakuna.view.model.LicenciaEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;
import pe.com.tss.runakuna.view.model.PeriodoEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.PermisoEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.QuickFilterViewModel;
import pe.com.tss.runakuna.service.AlertaService;
import pe.com.tss.runakuna.service.MailService;
import pe.com.tss.runakuna.service.PermisoEmpleadoService;
import pe.com.tss.runakuna.util.DateUtil;
import pe.com.tss.runakuna.util.StringUtil;


@Service
public class PermisoEmpleadoServiceImpl implements PermisoEmpleadoService {

	private static final Logger LOGGER  = LoggerFactory.getLogger(PermisoEmpleadoServiceImpl.class);

	@Autowired
	PermisoEmpleadoJpaRepository permisoEmpleadoJpaRepository;
	
	@Autowired
	PermisoEmpleadoRecuperacionJpaRepository permisoEmpleadoRecuperacionJpaRepository;
	
	@Autowired
	PermisoEmpleadoJdbcRepository permisoEmpleadoJdbcRepository;
	
	@Autowired
	HistoriaLaboralRepository historiaLaboralRepository;
	
	@Autowired
	PeriodoEmpleadoRepository periodoEmpleadoRepository;
	
	@Autowired
	PeriodoEmpleadoJpaRepository periodoEmpleadoJpaRepository;
	
	@Autowired
	EmpleadoJpaRepository empleadoJpaRepository;
	
	@Autowired
	EmpleadoRepository empleadoRepository;
	
	@Autowired
	HorarioEmpleadoJpaRepository horarioEmpleadoJpaRepository;
	
	@Autowired
	HorarioEmpleadoDiaJpaRepository horarioEmpleadoDiaJpaRepository;
	
	@Autowired
	MarcacionJpaRepository marcacionJpaRepository;

	@Autowired
	Mapper mapper;
	
	@Autowired
	AlertaService alertaService;
	
	@Autowired
	MailService mailService;
	
	@Override
	public List<PermisoEmpleadoViewModel> verPermisoEmpleado(PeriodoEmpleadoViewModel periodoEmpleado) {
		List<PermisoEmpleadoViewModel> dto = new ArrayList<>();
		dto = permisoEmpleadoJdbcRepository.verPermisoEmpleado(periodoEmpleado);
		if(dto!= null && dto.size()>0){
			for(PermisoEmpleadoViewModel next: dto){
				next.setPermisoEmpleadoRecuperacion(new ArrayList<>());
				
				List<PermisoEmpleadoRecuperacionViewModel> permisoEmpleadoRecuperacionEntity = new ArrayList<>();
				permisoEmpleadoRecuperacionEntity = permisoEmpleadoJdbcRepository.findPermisoRecuperacionByIdPermisoEmpleado(next.getIdPermisoEmpleado());
				next.setPermisoEmpleadoRecuperacion(permisoEmpleadoRecuperacionEntity);
			}
		}
		

		return dto;
	}
	
	@Override
	public NotificacionViewModel enviarPermisoEmpleado(PermisoEmpleadoViewModel permisoEmpleado) {

		NotificacionViewModel notificacion = new NotificacionViewModel();
		PermisoEmpleado entity = permisoEmpleadoJpaRepository.findOne(permisoEmpleado.getIdPermisoEmpleado());

		entity.setEstado("E");
		permisoEmpleadoJpaRepository.save(entity);
		permisoEmpleadoJpaRepository.flush();
		
		notificacion.setCodigo(1L);
		notificacion.setSeverity("success");
		notificacion.setSummary("Runakuna Success");
		notificacion.setDetail("El registro fue enviado correctamente.");
		return notificacion;
	}
	
	@Override
	public NotificacionViewModel devolverPermisoEmpleado(PermisoEmpleadoViewModel permisoEmpleado) {
		NotificacionViewModel notificacion = new NotificacionViewModel();
		PermisoEmpleado entity = permisoEmpleadoJpaRepository.findOne(permisoEmpleado.getIdPermisoEmpleado());
		PeriodoEmpleado periodoEntity = periodoEmpleadoJpaRepository.findOne(entity.getPeriodoEmpleado().getIdPeriodoEmpleado());
		entity.setEstado("P");
		entity.setComentarioResolucion(permisoEmpleado.getComentarioResolucion());
		permisoEmpleadoJpaRepository.save(entity);
		permisoEmpleadoJpaRepository.flush();
		enviarAlertaMailCambioEstadoPermiso(entity, periodoEntity, "Devuelto");
		notificacion.setCodigo(1L);
		notificacion.setSeverity("success");
		notificacion.setSummary("Runakuna Success");
		notificacion.setDetail("El registro fue devuelto correctamente");
		return notificacion;
	}
	
	@Override
	public NotificacionViewModel aprobarPermisoEmpleado(PermisoEmpleadoViewModel permisoEmpleado) {
		NotificacionViewModel notificacion = new NotificacionViewModel();
		PermisoEmpleado entity = permisoEmpleadoJpaRepository.findOne(permisoEmpleado.getIdPermisoEmpleado());
		PeriodoEmpleado periodoEntity = periodoEmpleadoJpaRepository.findOne(entity.getPeriodoEmpleado().getIdPeriodoEmpleado());
		periodoEntity.setPermisosDisponibles(periodoEntity.getPermisosDisponibles() - 1);
		
		periodoEmpleadoJpaRepository.save(periodoEntity);
		periodoEmpleadoJpaRepository.flush();
		
		entity.setEstado("A");
		permisoEmpleadoJpaRepository.save(entity);
		permisoEmpleadoJpaRepository.flush();
		
		enviarAlertaMailCambioEstadoPermiso(entity, periodoEntity, "Aprobado");
		
		Marcacion marcacion = marcacionJpaRepository.obtenerMarcacionPorIdEmpleadoyDate(periodoEntity.getEmpleado().getIdEmpleado(), entity.getFecha());
    	
        if(marcacion != null){
        	marcacion.setRecalcular("S");
        	marcacionJpaRepository.save(marcacion);
        	marcacionJpaRepository.flush();
        }
        
        if(entity.getMotivo().equals("P")){
        	
        	if(entity.getPermisoEmpleadoRecuperacion() != null && entity.getPermisoEmpleadoRecuperacion().size() > 0){
        		
        		for (PermisoEmpleadoRecuperacion permRecup : entity.getPermisoEmpleadoRecuperacion()) {
        			Marcacion marcacionRecuperacion = marcacionJpaRepository.obtenerMarcacionPorIdEmpleadoyDate(periodoEntity.getEmpleado().getIdEmpleado(), permRecup.getFechaRecuperacion());
                	
                    if(marcacionRecuperacion != null){
                    	marcacionRecuperacion.setRecalcular("S");
                    	marcacionJpaRepository.save(marcacionRecuperacion);
                    	marcacionJpaRepository.flush();
                    }
				}
        	}
        	
        }
        
		notificacion.setCodigo(1L);
		notificacion.setSeverity("success");
		notificacion.setSummary("Runakuna Success");
		notificacion.setDetail("El registro fue aprobado correctamente.");
		return notificacion;
	}
	
	@Override
	public NotificacionViewModel rechazarPermisoEmpleado(PermisoEmpleadoViewModel permisoEmpleado) {
		NotificacionViewModel notificacion = new NotificacionViewModel();
		PermisoEmpleado entity = permisoEmpleadoJpaRepository.findOne(permisoEmpleado.getIdPermisoEmpleado());
		PeriodoEmpleado periodoEntity = periodoEmpleadoJpaRepository.findOne(entity.getPeriodoEmpleado().getIdPeriodoEmpleado());
		entity.setEstado("R");
		entity.setComentarioResolucion(permisoEmpleado.getComentarioResolucion());
		permisoEmpleadoJpaRepository.save(entity);
		permisoEmpleadoJpaRepository.flush();
		enviarAlertaMailCambioEstadoPermiso(entity, periodoEntity, "Rechazado");
		notificacion.setCodigo(1L);
		notificacion.setSeverity("success");
		notificacion.setSummary("Runakuna Success");
		notificacion.setDetail("El registro fue rechazado correctamente.");
		return notificacion;
	}
	
	
	private void enviarAlertaMailCambioEstadoPermiso(PermisoEmpleado entity,PeriodoEmpleado periodoEntity, String estado){
		
		try{
			AlertaViewModel alertaDto=alertaService.obtenerAlerta("ALERTA09");
			GeneraMsjeAlertaEmpleadoViewModel msjeAlertaDto=new GeneraMsjeAlertaEmpleadoViewModel();
			msjeAlertaDto.setIdAlerta(alertaDto.getIdAlerta());
			msjeAlertaDto.setIdEmpleado(periodoEntity.getEmpleado().getIdEmpleado());
			Map<String,String> parametrosMensaje=new HashMap<String,String>();
			SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
			parametrosMensaje.put("Estado",estado);
			parametrosMensaje.put("Fecha",sdf.format(entity.getFecha()));
			parametrosMensaje.put("Hora Inicio",entity.getHoraInicio());
			parametrosMensaje.put("Hora Fin",entity.getHoraFin());
			msjeAlertaDto.setParametrosMsje(parametrosMensaje);
			alertaService.generarMensajeAlerta(msjeAlertaDto);
			
			String cuerpo=alertaDto.getCuerpo().replace("[Estado]", estado);
			cuerpo=cuerpo.replace("[Fecha]", sdf.format(entity.getFecha()));
			cuerpo=cuerpo.replace("[Hora Inicio]", entity.getHoraInicio());
			cuerpo=cuerpo.replace("[Hora Fin]", entity.getHoraFin());
			String[] correos=new String[1];
			correos[0]=periodoEntity.getEmpleado().getEmailInterno();
			mailService.sendEmail(alertaDto.getAsunto(), cuerpo, correos);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	@Override
	public NotificacionViewModel actualizarPermisoEmpleado(PermisoEmpleadoViewModel permisoEmpleado) {

		NotificacionViewModel notificacion = new NotificacionViewModel();
		
		try{
			PermisoEmpleado entity = new PermisoEmpleado();
	
			mapper.map(permisoEmpleado, entity);
			
			PeriodoEmpleado periodoEntity = periodoEmpleadoJpaRepository.findOne(permisoEmpleado.getIdPeriodoEmpleado());
			
			entity.setPermisoEmpleadoRecuperacion(new ArrayList<>());
			if(permisoEmpleado.getMotivo().equals("P")){
				if(permisoEmpleado.getPermisoEmpleadoRecuperacion().size()>0){
					for(PermisoEmpleadoRecuperacionViewModel next: permisoEmpleado.getPermisoEmpleadoRecuperacion()){
						if(next.getIdPermisoEmpleadoRecuperacion()!=null){
							Calendar c1 = Calendar.getInstance();
							PermisoEmpleadoRecuperacion per = new PermisoEmpleadoRecuperacion();
							
							HorarioEmpleado horarioEmpleado = horarioEmpleadoJpaRepository.obtenerHorarioPorFechaVigente(permisoEmpleado.getFecha(), periodoEntity.getEmpleado().getIdEmpleado());
							HorarioEmpleadoDia horarioEmpleadoDia = horarioEmpleadoDiaJpaRepository.obtenerHorarioDiaPorDiaDeSemana(horarioEmpleado.getIdHorarioEmpleado(), StringUtil.obtenerCodigoDia(c1.get(Calendar.DAY_OF_WEEK)));
							Date fechaHoraIngresoDia=  DateUtil.parse(new SimpleDateFormat("HH:mm"),horarioEmpleadoDia.getEntrada());
							Date fechaHoraSalidaDia=  DateUtil.parse(new SimpleDateFormat("HH:mm"),horarioEmpleadoDia.getSalida());
							Date fechaHoraIngresoRecupPermiso =  DateUtil.parse(new SimpleDateFormat("HH:mm"),next.getHoraInicio());
							Date fechaHoraSalidaRecupPermiso=  DateUtil.parse(new SimpleDateFormat("HH:mm"),next.getHoraFin());
							if(fechaHoraIngresoRecupPermiso.after(fechaHoraIngresoDia) && fechaHoraIngresoRecupPermiso.before(fechaHoraSalidaDia)){
								throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error","La hora de inicio de recuperacion del permiso debe ser fuera de rango al horario del dia que solicita el permiso.");
							}
							if(fechaHoraSalidaRecupPermiso.after(fechaHoraIngresoDia) && fechaHoraSalidaRecupPermiso.before(fechaHoraSalidaDia)){
								throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error","La hora de fin de recuperacion del permiso debe ser fuera de rango al horario del dia que solicita el permiso.");
							}						
							
							mapper.map(next,per);
							per.setPermisoEmpleado(entity);
							entity.getPermisoEmpleadoRecuperacion().add(per);
						}					
					}
				}
				
			}
			entity.setPeriodoEmpleado(periodoEntity);
	
			Empleado empleado = empleadoJpaRepository.findOne(permisoEmpleado.getIdAtendidoPor());
			entity.setAtendidoPor(empleado);
			entity.setEstado("E");
			
			permisoEmpleadoJpaRepository.save(entity);
			permisoEmpleadoJpaRepository.flush();
			
			notificacion.setCodigo(1L);
			notificacion.setSeverity("success");
			notificacion.setSummary("Runakuna Success");
			notificacion.setDetail("Se actualizo el codigo: "+permisoEmpleado.getIdPermisoEmpleado());
			return notificacion;
		} catch (Exception e) {
			notificacion.setCodigo(0L);
			notificacion.setSeverity("error");
			notificacion.setSummary("Runakuna Error");
			notificacion.setDetail("No es posible registrar, "+e.getMessage());
			e.printStackTrace();
		}
		return notificacion;
	}
	
	@Override
	public NotificacionViewModel guardarPendientePermisoEmpleado(PermisoEmpleadoViewModel permisoEmpleado) {

		NotificacionViewModel notificacion = new NotificacionViewModel();
		
		try{
			PermisoEmpleado entity = new PermisoEmpleado();
	
			mapper.map(permisoEmpleado, entity);
			
			PeriodoEmpleado periodoEntity = periodoEmpleadoJpaRepository.findOne(permisoEmpleado.getIdPeriodoEmpleado());
			
			entity.setPermisoEmpleadoRecuperacion(new ArrayList<>());
			if(permisoEmpleado.getMotivo().equals("P")){
				if(permisoEmpleado.getPermisoEmpleadoRecuperacion().size()>0){
					for(PermisoEmpleadoRecuperacionViewModel next: permisoEmpleado.getPermisoEmpleadoRecuperacion()){
						if(next.getIdPermisoEmpleadoRecuperacion()!=null){
							Calendar c1 = Calendar.getInstance();
							PermisoEmpleadoRecuperacion per = new PermisoEmpleadoRecuperacion();
							
							HorarioEmpleado horarioEmpleado = horarioEmpleadoJpaRepository.obtenerHorarioPorFechaVigente(permisoEmpleado.getFecha(), periodoEntity.getEmpleado().getIdEmpleado());
							HorarioEmpleadoDia horarioEmpleadoDia = horarioEmpleadoDiaJpaRepository.obtenerHorarioDiaPorDiaDeSemana(horarioEmpleado.getIdHorarioEmpleado(), StringUtil.obtenerCodigoDia(c1.get(Calendar.DAY_OF_WEEK)));
							Date fechaHoraIngresoDia=  DateUtil.parse(new SimpleDateFormat("HH:mm"),horarioEmpleadoDia.getEntrada());
							Date fechaHoraSalidaDia=  DateUtil.parse(new SimpleDateFormat("HH:mm"),horarioEmpleadoDia.getSalida());
							Date fechaHoraIngresoRecupPermiso =  DateUtil.parse(new SimpleDateFormat("HH:mm"),next.getHoraInicio());
							Date fechaHoraSalidaRecupPermiso=  DateUtil.parse(new SimpleDateFormat("HH:mm"),next.getHoraFin());
							if(fechaHoraIngresoRecupPermiso.after(fechaHoraIngresoDia) && fechaHoraIngresoRecupPermiso.before(fechaHoraSalidaDia)){
								throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error","La hora de inicio de recuperacion del permiso debe ser fuera de rango al horario del dia que solicita el permiso.");
							}
							if(fechaHoraSalidaRecupPermiso.after(fechaHoraIngresoDia) && fechaHoraSalidaRecupPermiso.before(fechaHoraSalidaDia)){
								throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error","La hora de fin de recuperacion del permiso debe ser fuera de rango al horario del dia que solicita el permiso.");
							}						
							
							mapper.map(next,per);
							per.setPermisoEmpleado(entity);
							entity.getPermisoEmpleadoRecuperacion().add(per);
						}					
					}
				}
				
			}
			entity.setPeriodoEmpleado(periodoEntity);
	
			Empleado empleado = empleadoJpaRepository.findOne(permisoEmpleado.getIdAtendidoPor());
			entity.setAtendidoPor(empleado);
			entity.setEstado("P");
			
			permisoEmpleadoJpaRepository.save(entity);
			permisoEmpleadoJpaRepository.flush();
			
			notificacion.setCodigo(1L);
			notificacion.setSeverity("success");
			notificacion.setSummary("Runakuna Success");
			notificacion.setDetail("Se actualizo el codigo: "+permisoEmpleado.getIdPermisoEmpleado());
			return notificacion;
		} catch (Exception e) {
			notificacion.setCodigo(0L);
			notificacion.setSeverity("error");
			notificacion.setSummary("Runakuna Error");
			notificacion.setDetail("No es posible registrar, "+e.getMessage());
			e.printStackTrace();
		}
		return notificacion;
	}
	
	@Override
	public NotificacionViewModel registrarPermisoEmpleado(PermisoEmpleadoViewModel permisoEmpleado) {
		NotificacionViewModel notificacion = new NotificacionViewModel();
		
			Calendar c1 = Calendar.getInstance();
		
			List<PermisoEmpleado> lista = permisoEmpleadoJpaRepository.obtenerPermisoPorFechayPeriodoEmpleado(permisoEmpleado.getFecha(), permisoEmpleado.getIdPeriodoEmpleado());
			
			if(lista != null && lista.size() >0){
				
				boolean validacion = false;
				String[] horaIniSolicitado = permisoEmpleado.getHoraInicio().split(":");
				String[] horaFinSolicitado = permisoEmpleado.getHoraFin().split(":");
				
				Date fechaIniSolicitado = DateUtil.formatoFechaArchivoMarcacion(DateUtil.fmtDt(permisoEmpleado.getFecha()));
				Date fechaFinSolicitado = DateUtil.formatoFechaArchivoMarcacion(DateUtil.fmtDt(permisoEmpleado.getFecha()));
				
				fechaIniSolicitado.setHours(Integer.parseInt(horaIniSolicitado[0]));
				fechaIniSolicitado.setMinutes(Integer.parseInt(horaIniSolicitado[1]));
				
				fechaFinSolicitado.setHours(Integer.parseInt(horaFinSolicitado[0]));
				fechaFinSolicitado.setMinutes(Integer.parseInt(horaFinSolicitado[1]));
				
				for (PermisoEmpleado permisoEntity : lista) {
					
					String[] horaIniPermiso = permisoEntity.getHoraInicio().split(":");
					String[] horaFinPermiso = permisoEntity.getHoraFin().split(":");
					
					Date fechaIniPermiso = DateUtil.formatoFechaArchivoMarcacion(DateUtil.fmtDt(permisoEntity.getFecha()));
					Date fechaFinPermiso = DateUtil.formatoFechaArchivoMarcacion(DateUtil.fmtDt(permisoEntity.getFecha()));
					
					fechaIniPermiso.setHours(Integer.parseInt(horaIniPermiso[0]));
					fechaIniPermiso.setMinutes(Integer.parseInt(horaIniPermiso[1]));
					
					fechaFinPermiso.setHours(Integer.parseInt(horaFinPermiso[0]));
					fechaFinPermiso.setMinutes(Integer.parseInt(horaFinPermiso[1]));
					
					if((fechaIniPermiso.equals(fechaIniSolicitado) || fechaFinPermiso.equals(fechaFinSolicitado))){
						validacion = true;
						break;
					}
					
					if((fechaIniPermiso.after(fechaIniSolicitado) && fechaFinPermiso.before(fechaFinSolicitado))){
						validacion = true;
						break;
					}
					
					if((fechaIniSolicitado.after(fechaIniPermiso) && fechaFinSolicitado.before(fechaFinPermiso))){
						validacion = true;
						break;
					}
					
					if((fechaIniPermiso.after(fechaIniSolicitado) && fechaFinPermiso.before(fechaFinSolicitado))){
						validacion = true;
						break;
					}
					
					if((fechaIniSolicitado.after(fechaIniPermiso) && fechaIniSolicitado.before(fechaFinPermiso)) || (fechaFinSolicitado.after(fechaIniPermiso) && fechaFinSolicitado.before(fechaFinPermiso))){
						validacion = true;
						break;
					}
					
				}
				
				if(validacion){
					throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error","El Permiso ya existe o existe cruce de horas del permiso.");	
				}
			}
			
			HorarioEmpleado horarioEmpleado = horarioEmpleadoJpaRepository.obtenerHorarioPorFechaVigente(permisoEmpleado.getFecha(), permisoEmpleado.getPeriodoEmpleado().getIdEmpleado());
			
			if(horarioEmpleado == null){
				throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error","No tiene configuracion de Horario Empleado.");
			}
			
			c1.setTime(permisoEmpleado.getFecha());
			
			HorarioEmpleadoDia horarioEmpleadoDia = horarioEmpleadoDiaJpaRepository.obtenerHorarioDiaPorDiaDeSemana(horarioEmpleado.getIdHorarioEmpleado(), StringUtil.obtenerCodigoDia(c1.get(Calendar.DAY_OF_WEEK)));
			
			if(horarioEmpleadoDia == null){
				throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error","No tiene configuracion de Horario Empleado Dia.");
			}
			
			Date fechaHoraIngresoPermiso =  DateUtil.parse(new SimpleDateFormat("HH:mm"),permisoEmpleado.getHoraInicio());
			Date fechaHoraSalidaPermiso=  DateUtil.parse(new SimpleDateFormat("HH:mm"),permisoEmpleado.getHoraFin());
			
			Date fechaHoraIngresoDia=  DateUtil.parse(new SimpleDateFormat("HH:mm"),horarioEmpleadoDia.getEntrada());
			Date fechaHoraSalidaDia=  DateUtil.parse(new SimpleDateFormat("HH:mm"),horarioEmpleadoDia.getSalida());
			
			if(fechaHoraIngresoPermiso.before(fechaHoraIngresoDia)){
				throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error","La hora del inicio del permiso debe ser posterior a la hora de entrada del dia que solicita el permiso.");
			}
			
			if(fechaHoraSalidaPermiso.after(fechaHoraSalidaDia)){
				throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error","La hora del fin del permiso debe ser inferior a la la hora de salida del dia que solicita el permiso.");
			}
			
			PermisoEmpleado entity = new PermisoEmpleado();
			mapper.map(permisoEmpleado, entity);
			entity.setPermisoEmpleadoRecuperacion(new ArrayList<>());
			
			if(permisoEmpleado.getMotivo().equals("P")){
				if(permisoEmpleado.getPermisoEmpleadoRecuperacion().size()>0){
					for(PermisoEmpleadoRecuperacionViewModel next: permisoEmpleado.getPermisoEmpleadoRecuperacion()){
						
						Date fechaHoraIngresoRecupPermiso =  DateUtil.parse(new SimpleDateFormat("HH:mm"),next.getHoraInicio());
						Date fechaHoraSalidaRecupPermiso=  DateUtil.parse(new SimpleDateFormat("HH:mm"),next.getHoraFin());
										
						if(fechaHoraIngresoRecupPermiso.after(fechaHoraIngresoDia) && fechaHoraIngresoRecupPermiso.before(fechaHoraSalidaDia)){
							throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error","La hora de inicio de recuperacion del permiso debe ser fuera de rango al horario del dia que solicita el permiso.");
						}
						
						if(fechaHoraSalidaRecupPermiso.after(fechaHoraIngresoDia) && fechaHoraSalidaRecupPermiso.before(fechaHoraSalidaDia)){
							throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error","La hora de fin de recuperacion del permiso debe ser fuera de rango al horario del dia que solicita el permiso.");
						}
						
						PermisoEmpleadoRecuperacion per = new PermisoEmpleadoRecuperacion();
						mapper.map(next,per);
						per.setPermisoEmpleado(entity);
						entity.getPermisoEmpleadoRecuperacion().add(per);
					}
				}
				
			}
			
			
			
			PeriodoEmpleado periodoEntity = periodoEmpleadoJpaRepository.findOne(permisoEmpleado.getPeriodoEmpleado().getIdPeriodoEmpleado());
			
			Empleado empleadoEntity = empleadoJpaRepository.findOne(permisoEmpleado.getIdAtendidoPor());
			
			entity.setPeriodoEmpleado(periodoEntity);
			entity.setAtendidoPor(empleadoEntity);
			entity.setEstado("E");
			
			permisoEmpleadoJpaRepository.save(entity);
			permisoEmpleadoJpaRepository.flush();
			enviarMailSolicitarPermiso(entity);
			notificacion.setCodigo(1L);
			notificacion.setSeverity("success");
			notificacion.setSummary("Runakuna Success");
			notificacion.setDetail("Se registro correctamente.");
		/*} catch (Exception e) {
			LOGGER.info(e.getMessage(), e);
			notificacion.setCodigo(0L);
			notificacion.setSeverity("error");
			notificacion.setSummary("Runakuna Error");
			notificacion.setDetail("No es posible registrar, "+e.getMessage());
			e.printStackTrace();
		}*/
		return notificacion;
	}

	private void enviarMailSolicitarPermiso(PermisoEmpleado entity) {
		
		try{
			AlertaViewModel alertaDto=alertaService.obtenerAlerta("ALERTA06");
			SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
			String[] correos=new String[alertaDto.getSubscriptores().size()+1];
			Long[] ids=new Long[alertaDto.getSubscriptores().size()+1];
			int i=0;
			for(AlertaSubscriptorViewModel alertaSubscriptorDto:alertaDto.getSubscriptores()) {
				Empleado empl=empleadoJpaRepository.findOne(alertaSubscriptorDto.getIdEmpleado());
				correos[i]=empl.getEmailInterno();
				ids[i]=empl.getIdEmpleado();
				i++;
			}
			
			correos[i] = entity.getAtendidoPor().getEmailInterno();
			ids[i] = entity.getAtendidoPor().getIdEmpleado();
			
			GeneraMsjeAlertaEmpleadoViewModel msjeAlertaDto=new GeneraMsjeAlertaEmpleadoViewModel();
			for(int j=0;j<correos.length;j++) {
				msjeAlertaDto=new GeneraMsjeAlertaEmpleadoViewModel();
				msjeAlertaDto.setIdAlerta(alertaDto.getIdAlerta());
				msjeAlertaDto.setIdEmpleado(ids[j]);
				Map<String,String> parametrosMensaje=new HashMap<String,String>();
				
				parametrosMensaje.put("Empleado",entity.getPeriodoEmpleado().getEmpleado().getApellidoPaterno()+" "+entity.getPeriodoEmpleado().getEmpleado().getApellidoMaterno()+", "+entity.getPeriodoEmpleado().getEmpleado().getNombre());
				parametrosMensaje.put("Fecha",sdf.format(entity.getFecha()));
				parametrosMensaje.put("Hora Inicio",entity.getHoraInicio());
				parametrosMensaje.put("Hora Fin",entity.getHoraFin());
				parametrosMensaje.put("Jefe Inmediato",entity.getAtendidoPor().getApellidoPaterno()+" "+entity.getAtendidoPor().getApellidoMaterno()+", "+entity.getAtendidoPor().getNombre());
				msjeAlertaDto.setParametrosMsje(parametrosMensaje);
				alertaService.generarMensajeAlerta(msjeAlertaDto);
			}
			
			
			if(correos.length>0){
				String cuerpo=alertaDto.getCuerpo().replace("[Empleado]", entity.getPeriodoEmpleado().getEmpleado().getApellidoPaterno()+" "+entity.getPeriodoEmpleado().getEmpleado().getApellidoMaterno()+", "+entity.getPeriodoEmpleado().getEmpleado().getNombre());
				cuerpo=cuerpo.replace("[Fecha]", sdf.format(entity.getFecha()));
				cuerpo=cuerpo.replace("[Hora Inicio]", entity.getHoraInicio());
				cuerpo=cuerpo.replace("[Hora Fin]", entity.getHoraFin());
				cuerpo=cuerpo.replace("[Jefe Inmediato]", entity.getAtendidoPor().getApellidoPaterno()+" "+entity.getAtendidoPor().getApellidoMaterno()+", "+entity.getAtendidoPor().getNombre());
				mailService.sendEmail(alertaDto.getAsunto(), cuerpo, correos);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
				
	}

	@Override
	public HistoriaLaboralViewModel obtenerHistoriaLaboralActual(EmpleadoViewModel empleadoDto) {
		HistoriaLaboralViewModel dto = new HistoriaLaboralViewModel();
		
		dto = historiaLaboralRepository.obtenerHistoriaLaboralActual(empleadoDto);
		return dto;
	}
	
	@Override
	public List<HistoriaLaboralViewModel> obtenerHistoriasLaboralesActualPorEmpleado(EmpleadoViewModel empleadoDto) {
		List<HistoriaLaboralViewModel> dto = new ArrayList<>();
		
		dto = historiaLaboralRepository.obtenerHistoriasLaboralesActualPorEmpleado(empleadoDto);
		return dto;
	}

	@Override
	public PeriodoEmpleadoViewModel obtenerPeriodoEmpleadoActual(EmpleadoViewModel empleadoDto) {
		PeriodoEmpleadoViewModel dto = new PeriodoEmpleadoViewModel();
		
		dto = periodoEmpleadoRepository.obtenerPeriodoEmpleadoActual(empleadoDto);
		return dto;
	}
	
	@Override
	public  List<PermisoEmpleadoFilterViewModel> autocompleteEmpleado(String search) {
		List<PermisoEmpleadoFilterViewModel> dto = new ArrayList<>();
		
		dto = empleadoRepository.autocompleteEmpleado(search);
		return dto;
	}
	
	@Override
	public  List<PermisoEmpleadoFilterViewModel> autocompleteJefe(String search) {
		List<PermisoEmpleadoFilterViewModel> dto = new ArrayList<>();
		
		dto = empleadoRepository.autocompleteJefe(search);
		return dto;
	}
	
	@Override
	public  List<PermisoEmpleadoFilterViewModel> autocompleteEmpleadoConJefe(String search, Long idJefe) {
		List<PermisoEmpleadoFilterViewModel> dto = new ArrayList<>();
		
		dto = empleadoRepository.autocompleteEmpleadoConJefe(search,idJefe);
		return dto;
	}

	@Override
	public HistoriaLaboralViewModel obtenerHistoriaLaboralLicencia(LicenciaEmpleadoViewModel licenciaEmpleadoD) {
		HistoriaLaboralViewModel dto = new HistoriaLaboralViewModel();
		
		dto = historiaLaboralRepository.obtenerHistoriaLaboralLicencia(licenciaEmpleadoD);
		return dto;
	}

	@Override
	public List<PermisoEmpleadoResultViewModel> search(PermisoEmpleadoFilterViewModel filterViewModel) {
		return permisoEmpleadoJdbcRepository.busquedaPermisoEmpleado(filterViewModel);
	}

	@Override
	public PermisoEmpleadoViewModel findOne(Long id) {
		
		PermisoEmpleadoViewModel dto=new PermisoEmpleadoViewModel();
		
		PermisoEmpleado permisoEmpleado=permisoEmpleadoJpaRepository.findOne(id);
		mapper.map(permisoEmpleado, dto);
		
		PeriodoEmpleado periodoEmpleado = periodoEmpleadoJpaRepository.findOne(permisoEmpleado.getPeriodoEmpleado().getIdPeriodoEmpleado());
		dto.setIdEmpleado(periodoEmpleado.getEmpleado().getIdEmpleado());
		
		String estado=permisoEmpleado.getEstado();
		String estadoDescripcion="";
		switch (estado) {
			case "P" :estadoDescripcion="Pendiente"; break;
			case "E" :estadoDescripcion="Enviado"; break;
			case "A" :estadoDescripcion="Aprobado"; break;
			case "R" :estadoDescripcion="Rechazado"; break;
			default: estadoDescripcion=""; break;
		}
		dto.setEstado(estado);
		dto.setEstadoString(estadoDescripcion);
		if(permisoEmpleado.getAtendidoPor()!=null && dto.getEstadoString()=="Enviado"){
			dto.setIdAtendidoPor(permisoEmpleado.getAtendidoPor().getIdEmpleado());
		}
		
		dto.setIdAtendidoPor(permisoEmpleado.getAtendidoPor().getIdEmpleado());
		
		dto.setNombreEmpleado(periodoEmpleado.getEmpleado().getApellidoPaterno()+" "+periodoEmpleado.getEmpleado().getApellidoMaterno()+", "+periodoEmpleado.getEmpleado().getNombre());
		
		dto.setPermisoEmpleadoRecuperacion(new ArrayList<>());
		if(permisoEmpleado.getMotivo().equals("P")){
			List<PermisoEmpleadoRecuperacion> permisoEmpleadoRecuperacion = permisoEmpleadoRecuperacionJpaRepository.obtenerPermisosRecuperacionPorIdPermiso(permisoEmpleado.getIdPermisoEmpleado());
			for(PermisoEmpleadoRecuperacion next: permisoEmpleadoRecuperacion){				
				
				PermisoEmpleadoRecuperacionViewModel per = new PermisoEmpleadoRecuperacionViewModel();
				mapper.map(next,per);
				dto.getPermisoEmpleadoRecuperacion().add(per);
			}
		}
		
		
		return dto;
	}

	@Override
	public NotificacionViewModel create(PermisoEmpleadoViewModel manteinanceViewModel) {
		return null;
	}

	@Override
	public NotificacionViewModel update(PermisoEmpleadoViewModel manteinanceViewModel) {
		return null;
	}

	@Override
	public NotificacionViewModel delete(Long id) {
		
    	NotificacionViewModel notificacion=new NotificacionViewModel();
        if (permisoEmpleadoJpaRepository.findOne(id) == null) {
        	throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", "The record was changed by another user. Please re-enter the screen.");
        }
        try {
        	
        	PermisoEmpleado permiso = permisoEmpleadoJpaRepository.findOne(id);
        	Marcacion marcacion = marcacionJpaRepository.obtenerMarcacionPorIdEmpleadoyDate(permiso.getPeriodoEmpleado().getEmpleado().getIdEmpleado(), permiso.getFecha());
        	
            permisoEmpleadoJpaRepository.delete(id);
            permisoEmpleadoJpaRepository.flush();
            
            Date fechaActual = DateUtil.truncDay(new Date());
			
			PeriodoEmpleado periodo = periodoEmpleadoJpaRepository.obtenerPeriodoEmpleadoPorFechayEmpleado(fechaActual, permiso.getPeriodoEmpleado().getEmpleado().getIdEmpleado());
			periodo.setPermisosDisponibles(periodo.getPermisosDisponibles() + 1);
			periodoEmpleadoJpaRepository.save(periodo);
			periodoEmpleadoJpaRepository.flush();
            
            notificacion.setCodigo(1L);
            notificacion.setSeverity("success");
			notificacion.setSummary("Runakuna Success");
            notificacion.setDetail("Registro eliminado correctamente.");
            
            if(marcacion != null && permiso.getEstado().equals("A")){
            	marcacion.setRecalcular("S");
            	marcacionJpaRepository.save(marcacion);
            	marcacionJpaRepository.flush();
            }
            
        } catch (Exception e) {
        	throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", "Restriction is Being used, Can't be deleted");
        }

        return notificacion;
    }
	
	@Override
	public List<HistoriaLaboralViewModel> obtenerHistoriasLaboralesPorEmpleado(Long idEmpleado) {
		List<HistoriaLaboralViewModel> dto = new ArrayList<>();
		
		dto = historiaLaboralRepository.obtenerHistoriasLaboralesPorEmpleado(idEmpleado);
		return dto;
	}

	@Override
	public List<PermisoEmpleadoResultViewModel> quickSearch(QuickFilterViewModel quickFilter) {
		return permisoEmpleadoJdbcRepository.busquedaRapidaPermisoEmpleado(quickFilter);
	}

	@Override
	public EmpleadoCompensacionViewModel obtenerHorasPorCompensarPorEmpleado(Long idEmpleado) {
		
		EmpleadoCompensacionViewModel dto = historiaLaboralRepository.obtenerHorasPorCompensarPorEmpleado(idEmpleado);
		return dto;
	}

	@Override
	public HorarioEmpleadoDiaViewModel obtenerHorarioEmpleadoDia(PermisoEmpleadoViewModel permisoEmpleadoDto) {
		HorarioEmpleadoDiaViewModel entity = new HorarioEmpleadoDiaViewModel();
		Calendar c1 = Calendar.getInstance();
		BigDecimal tiempoAlmuerzo = new BigDecimal(1);
		
		String dateCadena = DateUtil.fmtDt(new Date());
		Date today =DateUtil.formatoFechaArchivoMarcacion(dateCadena);
		
		HorarioEmpleado horarioEmpleado = horarioEmpleadoJpaRepository.obtenerHorarioPorFechaVigente(permisoEmpleadoDto.getFecha(), permisoEmpleadoDto.getIdEmpleado());
		
		entity = permisoEmpleadoJdbcRepository.obtenerHorarioEmpleadoDia(horarioEmpleado.getIdHorarioEmpleado(), StringUtil.obtenerCodigoDia(c1.get(Calendar.DAY_OF_WEEK)));
		
		return entity;
	}

	

}
