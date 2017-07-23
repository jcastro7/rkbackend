package pe.com.tss.runakuna.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import pe.com.tss.runakuna.domain.model.entities.Empleado;
import pe.com.tss.runakuna.domain.model.entities.Marcacion;
import pe.com.tss.runakuna.domain.model.entities.PeriodoEmpleado;
import pe.com.tss.runakuna.domain.model.entities.Vacacion;
import pe.com.tss.runakuna.domain.model.repository.jdbc.PeriodoEmpleadoRepository;
import pe.com.tss.runakuna.domain.model.repository.jdbc.VacacionEmpleadoJdbcRepository;
import pe.com.tss.runakuna.domain.model.repository.jdbc.VacacionEmpleadoRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.EmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.MarcacionJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.PeriodoEmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.VacacionJpaRepository;
import pe.com.tss.runakuna.enums.SeverityStatusEnum;
import pe.com.tss.runakuna.view.model.AlertaSubscriptorViewModel;
import pe.com.tss.runakuna.view.model.AlertaViewModel;
import pe.com.tss.runakuna.view.model.EmpleadoPlanillaResultViewModel;
import pe.com.tss.runakuna.view.model.EmpleadoViewModel;
import pe.com.tss.runakuna.view.model.GeneraMsjeAlertaEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;
import pe.com.tss.runakuna.view.model.PeriodoEmpleadoResultViewModel;
import pe.com.tss.runakuna.view.model.VacacionEmpleadoPlanillaViewModel;
import pe.com.tss.runakuna.view.model.VacacionEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.VacacionFilterViewModel;
import pe.com.tss.runakuna.view.model.VacacionResultViewModel;
import pe.com.tss.runakuna.view.model.VacacionViewModel;
import pe.com.tss.runakuna.view.model.VacacionesEmpleadoFilterViewModel;
import pe.com.tss.runakuna.view.model.VacacionesEmpleadoResultViewModel;
import pe.com.tss.runakuna.exception.BusinessException;
import pe.com.tss.runakuna.gateway.common.ValidationUtils;
import pe.com.tss.runakuna.service.AlertaService;
import pe.com.tss.runakuna.service.MailService;
import pe.com.tss.runakuna.service.VacacionEmpleadoService;
import pe.com.tss.runakuna.util.DateUtil;
import pe.com.tss.runakuna.util.StringUtil;

@Service
public class VacacionEmpleadoServiceImpl implements VacacionEmpleadoService{

	@Autowired
	VacacionEmpleadoRepository vacacionEmpleadoRepository;
	
	@Autowired
	VacacionJpaRepository vacacionJpaRepository;
	
	@Autowired
	EmpleadoJpaRepository empleadoJpaRepository;
	
	@Autowired
	PeriodoEmpleadoJpaRepository periodoEmpleadoJpaRepository;
	
	@Autowired
	PeriodoEmpleadoRepository periodoEmpleadoRepository;
	
	@Autowired
	MarcacionJpaRepository marcacionJpaRepository;
	
	@Autowired
	AlertaService alertaService;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	Mapper mapper;
	
	@Override
	public VacacionEmpleadoViewModel obtenerDiasDisponibles(EmpleadoViewModel empleadoDto) {
		VacacionEmpleadoViewModel dto = new VacacionEmpleadoViewModel();
		
		dto = vacacionEmpleadoRepository.obtenerDiasDisponibles(empleadoDto);
		return dto;
	}
	
	@Override
	public NotificacionViewModel registrarVacaciones(VacacionEmpleadoViewModel vacacionEmpleadoDto) {
		
		NotificacionViewModel notificacion = new NotificacionViewModel();
		
		List<PeriodoEmpleadoResultViewModel> dto = new ArrayList<PeriodoEmpleadoResultViewModel>();
		dto = periodoEmpleadoRepository.obtenerPeriodosConVacacionesDisponibles(vacacionEmpleadoDto.getIdEmpleado());
		
		if(dto != null && dto.size()>0){
			
			List<Vacacion> vacacionesEntity = new ArrayList<>();
			
			for (PeriodoEmpleadoResultViewModel periodoEmpleadoResultViewModel : dto) {
				PeriodoEmpleado periodo = periodoEmpleadoJpaRepository.findOne(periodoEmpleadoResultViewModel.getIdPeriodoEmpleado());
			
				boolean overlapVac = false;
				
				if(periodo.getDiasVacacionesDisponibles() < vacacionEmpleadoDto.getDiasHabiles()){
					
					List<VacacionEmpleadoViewModel> listVacacion = vacacionEmpleadoRepository.allListVacacion(periodo.getIdPeriodoEmpleado());
					
					for(VacacionEmpleadoViewModel next: listVacacion){
						if(next.getFechaInicio()!=null || next.getFechaFin()!=null){
							boolean isTrueInicio = ValidationUtils.isWithinRange(vacacionEmpleadoDto.getFechaInicio(),next.getFechaInicio(),next.getFechaFin());
							boolean isTrueFin 	 = ValidationUtils.isWithinRange(vacacionEmpleadoDto.getFechaFin(),next.getFechaInicio(),next.getFechaFin());
							if(isTrueInicio==true || isTrueFin==true){
								String resultInicio;
								String resultFin;
								SimpleDateFormat formatter;
			
								formatter = new SimpleDateFormat("dd/MM/yyyy", new Locale("es", "pe"));
								resultInicio = formatter.format(next.getFechaInicio());
								resultFin = formatter.format(next.getFechaFin());
			
								notificacion.setCodigo(0L);
								notificacion.setSeverity("error");
								notificacion.setSummary("Runakuna Error");
								notificacion.setDetail("Las fechas ingresadas se cruzan con una o m\u00e1s vacaciones solicitadas.");
								overlapVac = true;
								break;
							}else{
								continue;
							}
						}
					}
					
					if(overlapVac){
						break;
					}
					
					Vacacion entity;
					if(vacacionEmpleadoDto.getIdVacacion() == null)
						entity = new Vacacion();
					else entity = vacacionJpaRepository.findOne(vacacionEmpleadoDto.getIdVacacion());
					
					entity.setPeriodoEmpleado(periodo);
					
					Empleado empleado = empleadoJpaRepository.findOne(vacacionEmpleadoDto.getIdAtendidoPor());
					entity.setAtendidoPor(empleado);
					
					entity.setDiasCalendarios(periodoEmpleadoResultViewModel.getDiasCalendariosDisponibles());
					entity.setDiasHabiles(periodo.getDiasVacacionesDisponibles());
					
					entity.setFechaInicio(vacacionEmpleadoDto.getFechaInicio());
					entity.setFechaFin(DateUtil.addDays(vacacionEmpleadoDto.getFechaInicio(), entity.getDiasCalendarios() - 1));
					
					entity.setRegularizacion(0);
					entity.setIncluidoPlanilla(0);
					entity.setEstado("E");
					entity.setTipo("S");
					
					onDiasHabiles(entity);
					
					vacacionEmpleadoDto.setDiasHabiles(vacacionEmpleadoDto.getDiasHabiles() - entity.getDiasHabiles());
					vacacionEmpleadoDto.setDiasCalendarios(vacacionEmpleadoDto.getDiasCalendarios() - entity.getDiasCalendarios());
					vacacionEmpleadoDto.setFechaInicio(DateUtil.addDays(entity.getFechaFin(), 1));
					
					vacacionesEntity.add(entity);
					
				}else{
					List<VacacionEmpleadoViewModel> listVacacion = vacacionEmpleadoRepository.allListVacacion(periodo.getIdPeriodoEmpleado());
					
					for(VacacionEmpleadoViewModel next: listVacacion){
						if(next.getFechaInicio()!=null || next.getFechaFin()!=null){
							boolean isTrueInicio = ValidationUtils.isWithinRange(vacacionEmpleadoDto.getFechaInicio(),next.getFechaInicio(),next.getFechaFin());
							boolean isTrueFin 	 = ValidationUtils.isWithinRange(vacacionEmpleadoDto.getFechaFin(),next.getFechaInicio(),next.getFechaFin());
							if(isTrueInicio==true || isTrueFin==true){
								String resultInicio;
								String resultFin;
								SimpleDateFormat formatter;
			
								formatter = new SimpleDateFormat("dd/MM/yyyy", new Locale("es", "pe"));
								resultInicio = formatter.format(next.getFechaInicio());
								resultFin = formatter.format(next.getFechaFin());
			
								notificacion.setCodigo(0L);
								notificacion.setSeverity("error");
								notificacion.setSummary("Runakuna Error");
								notificacion.setDetail("Las fechas ingresadas se cruzan con una o m\u00e1s vacaciones solicitadas.");
								overlapVac = true;
								break;
							}else{
								continue;
							}
						}
					}
					
					if(overlapVac){
						break;
					}
					
					Vacacion entity;
					if(vacacionEmpleadoDto.getIdVacacion() == null)
						entity = new Vacacion();
					else entity = vacacionJpaRepository.findOne(vacacionEmpleadoDto.getIdVacacion());
					
					entity.setPeriodoEmpleado(periodo);
					
					Empleado empleado = empleadoJpaRepository.findOne(vacacionEmpleadoDto.getIdAtendidoPor());
					entity.setAtendidoPor(empleado);
					entity.setFechaInicio(vacacionEmpleadoDto.getFechaInicio());
					entity.setFechaFin(vacacionEmpleadoDto.getFechaFin());
					entity.setDiasCalendarios(vacacionEmpleadoDto.getDiasCalendarios());
					
					entity.setDiasHabiles(vacacionEmpleadoDto.getDiasHabiles());
					
					entity.setRegularizacion(0);
					entity.setIncluidoPlanilla(0);
					entity.setEstado("E");
					entity.setTipo("S");
					vacacionesEntity.add(entity);
					
					break;
				}
			
			}
			
			if(vacacionesEntity != null && vacacionesEntity.size()>0){
				
				vacacionJpaRepository.save(vacacionesEntity);
				vacacionJpaRepository.flush();
				
				for (Vacacion vacacion : vacacionesEntity) {
					enviarAlertaMailSolicitarVacaciones(vacacion);
				}
				
				notificacion.setCodigo(1L);
				notificacion.setSeverity("success");
				notificacion.setSummary("Runakuna Success");
				notificacion.setDetail("Se registro correctamente.");
				return notificacion;
				
			}
					
		}else{
			notificacion.setCodigo(0L);
			notificacion.setSeverity("error");
			notificacion.setSummary("Runakuna Error");
			notificacion.setDetail("No es posible registrar, no hay D\u00edas disponibles o Permisos disponibles.");
		}

		return notificacion;
		
	}
	
	@Override
	public NotificacionViewModel actualizarVacaciones(VacacionEmpleadoViewModel vacacionEmpleadoDto) {
		
		NotificacionViewModel notificacion = new NotificacionViewModel();
		
		List<PeriodoEmpleadoResultViewModel> dto = new ArrayList<PeriodoEmpleadoResultViewModel>();
		dto = periodoEmpleadoRepository.obtenerPeriodosConVacacionesDisponibles(vacacionEmpleadoDto.getIdEmpleado());
		
		if(dto != null && dto.size()>0){
			
			List<Vacacion> vacacionesEntity = new ArrayList<>();
			
			for (PeriodoEmpleadoResultViewModel periodoEmpleadoResultViewModel : dto) {
				PeriodoEmpleado periodo = periodoEmpleadoJpaRepository.findOne(periodoEmpleadoResultViewModel.getIdPeriodoEmpleado());
			
				boolean overlapVac = false;
				
				if(periodo.getDiasVacacionesDisponibles() < vacacionEmpleadoDto.getDiasHabiles()){
					
					List<Vacacion> listVacacion = vacacionJpaRepository.allListVacacionExceptionItself(vacacionEmpleadoDto.getIdPeriodoEmpleado(),vacacionEmpleadoDto.getIdVacacion());
					
					for(Vacacion next: listVacacion){
						if(next.getFechaInicio()!=null || next.getFechaFin()!=null){
							boolean isTrueInicio = ValidationUtils.isWithinRange(vacacionEmpleadoDto.getFechaInicio(),next.getFechaInicio(),next.getFechaFin());
							boolean isTrueFin 	 = ValidationUtils.isWithinRange(vacacionEmpleadoDto.getFechaFin(),next.getFechaInicio(),next.getFechaFin());
							if(isTrueInicio==true || isTrueFin==true){
								String resultInicio;
								String resultFin;
								SimpleDateFormat formatter;
			
								formatter = new SimpleDateFormat("dd/MM/yyyy", new Locale("es", "pe"));
								resultInicio = formatter.format(next.getFechaInicio());
								resultFin = formatter.format(next.getFechaFin());
			
								notificacion.setCodigo(0L);
								notificacion.setSeverity("error");
								notificacion.setSummary("Runakuna Error");
								notificacion.setDetail("Las fechas ingresadas se cruzan con una o m\u00e1s vacaciones solicitadas.");
								overlapVac = true;
								break;
							}else{
								continue;
							}
						}
					}
					
					if(overlapVac){
						break;
					}
					
					Vacacion entity;
					entity = vacacionJpaRepository.findOne(vacacionEmpleadoDto.getIdVacacion());
					
					entity.setPeriodoEmpleado(periodo);
					
					Empleado empleado = empleadoJpaRepository.findOne(vacacionEmpleadoDto.getIdAtendidoPor());
					entity.setAtendidoPor(empleado);
					
					entity.setDiasCalendarios(periodoEmpleadoResultViewModel.getDiasCalendariosDisponibles());
					entity.setDiasHabiles(periodo.getDiasVacacionesDisponibles());
					
					entity.setFechaInicio(vacacionEmpleadoDto.getFechaInicio());
					entity.setFechaFin(DateUtil.addDays(vacacionEmpleadoDto.getFechaInicio(), entity.getDiasCalendarios() - 1));
					
					entity.setRegularizacion(0);
					entity.setIncluidoPlanilla(0);
					entity.setEstado(vacacionEmpleadoDto.getEstado());
					entity.setTipo("S");
					
					onDiasHabiles(entity);
					
					vacacionEmpleadoDto.setDiasHabiles(vacacionEmpleadoDto.getDiasHabiles() - entity.getDiasHabiles());
					vacacionEmpleadoDto.setDiasCalendarios(vacacionEmpleadoDto.getDiasCalendarios() - entity.getDiasCalendarios());
					vacacionEmpleadoDto.setFechaInicio(DateUtil.addDays(entity.getFechaFin(), 1));
					
					vacacionesEntity.add(entity);
					
				}else{
					List<Vacacion> listVacacion = vacacionJpaRepository.allListVacacionExceptionItself(vacacionEmpleadoDto.getIdPeriodoEmpleado(),vacacionEmpleadoDto.getIdVacacion());
					
					for(Vacacion next: listVacacion){
						if(next.getFechaInicio()!=null || next.getFechaFin()!=null){
							boolean isTrueInicio = ValidationUtils.isWithinRange(vacacionEmpleadoDto.getFechaInicio(),next.getFechaInicio(),next.getFechaFin());
							boolean isTrueFin 	 = ValidationUtils.isWithinRange(vacacionEmpleadoDto.getFechaFin(),next.getFechaInicio(),next.getFechaFin());
							if(isTrueInicio==true || isTrueFin==true){
								String resultInicio;
								String resultFin;
								SimpleDateFormat formatter;
			
								formatter = new SimpleDateFormat("dd/MM/yyyy", new Locale("es", "pe"));
								resultInicio = formatter.format(next.getFechaInicio());
								resultFin = formatter.format(next.getFechaFin());
			
								notificacion.setCodigo(0L);
								notificacion.setSeverity("error");
								notificacion.setSummary("Runakuna Error");
								notificacion.setDetail("Las fechas ingresadas se cruzan con una o m\u00e1s vacaciones solicitadas.");
								overlapVac = true;
								break;
							}else{
								continue;
							}
						}
					}
					
					if(overlapVac){
						break;
					}
					
					Vacacion entity;
					entity = vacacionJpaRepository.findOne(vacacionEmpleadoDto.getIdVacacion());
					
					entity.setPeriodoEmpleado(periodo);
					
					Empleado empleado = empleadoJpaRepository.findOne(vacacionEmpleadoDto.getIdAtendidoPor());
					entity.setAtendidoPor(empleado);
					entity.setFechaInicio(vacacionEmpleadoDto.getFechaInicio());
					entity.setFechaFin(vacacionEmpleadoDto.getFechaFin());
					entity.setDiasCalendarios(vacacionEmpleadoDto.getDiasCalendarios());
					
					entity.setDiasHabiles(vacacionEmpleadoDto.getDiasHabiles());
					
					entity.setRegularizacion(0);
					entity.setIncluidoPlanilla(0);
					entity.setEstado(vacacionEmpleadoDto.getEstado());
					entity.setTipo("S");
					vacacionesEntity.add(entity);
					
					break;
				}
			
			}
			
			if(vacacionesEntity != null && vacacionesEntity.size()>0){
				
				vacacionJpaRepository.save(vacacionesEntity);
				vacacionJpaRepository.flush();
				
				/*for (Vacacion vacacion : vacacionesEntity) {
					enviarAlertaMailSolicitarVacaciones(vacacion);
				}*/
				
				notificacion.setCodigo(1L);
				notificacion.setSeverity("success");
				notificacion.setSummary("Runakuna Success");
				notificacion.setDetail("Se registro correctamente.");
				return notificacion;
				
			}
					
		}else{
			notificacion.setCodigo(0L);
			notificacion.setSeverity("error");
			notificacion.setSummary("Runakuna Error");
			notificacion.setDetail("No es posible registrar, no hay D\u00edas disponibles o Permisos disponibles.");
		}

		return notificacion;
		
	}

	@Override
	public VacacionEmpleadoViewModel obtenerPeriodo(EmpleadoViewModel empleado) {
		VacacionEmpleadoViewModel dto = new VacacionEmpleadoViewModel();
		
		dto = vacacionEmpleadoRepository.obtenerPeriodo(empleado);
		return dto;
	}

	@Override
	public NotificacionViewModel actualizarVacacionEmpleado(VacacionEmpleadoViewModel vacacionEmpleadoDto) {
		
		NotificacionViewModel notificacion = new NotificacionViewModel();
		
		List<Vacacion> listVacacion = vacacionJpaRepository.allListVacacionExceptionItself(vacacionEmpleadoDto.getIdPeriodoEmpleado(),vacacionEmpleadoDto.getIdVacacion());
			
			
			for(Vacacion next: listVacacion){
				boolean isTrueInicio = ValidationUtils.isWithinRange(vacacionEmpleadoDto.getFechaInicio(),next.getFechaInicio(),next.getFechaFin());
				boolean isTrueFin = ValidationUtils.isWithinRange(vacacionEmpleadoDto.getFechaFin(),next.getFechaInicio(),next.getFechaFin());
				if(isTrueInicio==true || isTrueFin==true){
					String resultInicio,resultFin;
					SimpleDateFormat formatter;

					formatter = new SimpleDateFormat("dd/MM/yyyy", new Locale("es", "pe"));
					resultInicio = formatter.format(next.getFechaInicio());
					resultFin = formatter.format(next.getFechaFin());

					notificacion.setCodigo(0L);
					notificacion.setSeverity("error");
					notificacion.setSummary("Runakuna Error");
					notificacion.setDetail("No es posible registrar, la vacacion se cruza con el codigo: " +
											next.getIdVacacion() +" Desde: "+resultInicio +" Hasta: "+resultFin);
					return notificacion;
				}else{
					continue;
				}
					
			}
		
		Vacacion entity = new Vacacion();
				
		mapper.map(vacacionEmpleadoDto, entity);
		
		PeriodoEmpleado periodoEmpleado = periodoEmpleadoJpaRepository.findOne(vacacionEmpleadoDto.getIdPeriodoEmpleado());
		entity.setPeriodoEmpleado(periodoEmpleado);
		
		Empleado empleado = empleadoJpaRepository.findOne(vacacionEmpleadoDto.getIdAtendidoPor());
		entity.setAtendidoPor(empleado);
		
		entity.setEstado("P");
		
		vacacionJpaRepository.save(entity);
		vacacionJpaRepository.flush();
		
		notificacion.setCodigo(1L);
		notificacion.setSeverity("success");
		notificacion.setSummary("Runakuna Success");
		notificacion.setDetail("Se actualizo correctamente.");
		return notificacion;
		
	}

	@Override
	public NotificacionViewModel enviarVacacionEmpleado(VacacionEmpleadoViewModel vacacionEmpleadoDto) {
		NotificacionViewModel notificacion = new NotificacionViewModel();
		
		Vacacion entity =new Vacacion();
		mapper.map(vacacionEmpleadoDto, entity);
		
		PeriodoEmpleado periodoEmpleado = periodoEmpleadoJpaRepository.findOne(vacacionEmpleadoDto.getIdPeriodoEmpleado());
		entity.setPeriodoEmpleado(periodoEmpleado);
		
		Empleado empleado = empleadoJpaRepository.findOne(vacacionEmpleadoDto.getIdAtendidoPor());
		entity.setAtendidoPor(empleado);
		
		entity.setEstado("E");
		vacacionJpaRepository.save(entity);
		vacacionJpaRepository.flush();
		
		notificacion.setCodigo(1L);
		notificacion.setSeverity("success");
		notificacion.setSummary("Runakuna Success");
		notificacion.setDetail("El registro fue enviado correctamente.");
		return notificacion;
	}

	@Override
	public NotificacionViewModel devolverVacacionEmpleado(VacacionEmpleadoViewModel vacacionEmpleadoDto) {
		NotificacionViewModel notificacion = new NotificacionViewModel();
		Vacacion entity = vacacionJpaRepository.findOne(vacacionEmpleadoDto.getIdVacacion());
		PeriodoEmpleado pe=periodoEmpleadoJpaRepository.findOne(entity.getPeriodoEmpleado().getIdPeriodoEmpleado());
		entity.setEstado("P");
		entity.setComentarioResolucion(vacacionEmpleadoDto.getComentarioResolucion());
		vacacionJpaRepository.save(entity);
		vacacionJpaRepository.flush();
		enviarAlertaMailCambioEstadoVacacion(entity,pe,"Devuelto");
		notificacion.setCodigo(1L);
		notificacion.setSeverity("success");
		notificacion.setSummary("Runakuna Success");
		notificacion.setDetail("El registro fue devuelto correctamente.");
		return notificacion;
	}

	@Override
	public NotificacionViewModel aprobarVacacionEmpleado(VacacionEmpleadoViewModel vacacionEmpleadoDto) {
				
		NotificacionViewModel notificacion = new NotificacionViewModel();
		List<Vacacion> vacacionesEntity = new ArrayList<>();
		
		Vacacion entity =new Vacacion();
		entity =vacacionJpaRepository.findOne(vacacionEmpleadoDto.getIdVacacion());
		
		PeriodoEmpleado pe=periodoEmpleadoJpaRepository.findOne(entity.getPeriodoEmpleado().getIdPeriodoEmpleado());
		
		boolean deleteOriginal = false;
		
		if(pe.getDiasVacacionesDisponibles()<vacacionEmpleadoDto.getDiasHabiles()){
			
			deleteOriginal = true;
			
			List<PeriodoEmpleadoResultViewModel> dto = new ArrayList<PeriodoEmpleadoResultViewModel>();
			dto = periodoEmpleadoRepository.obtenerPeriodosConVacacionesDisponibles(vacacionEmpleadoDto.getIdEmpleado());
			
			if(dto != null && dto.size()>0){
				
				boolean operacionRegistro = false;
				
				for (PeriodoEmpleadoResultViewModel periodoEmpleadoResultViewModel : dto) {
					PeriodoEmpleado periodo = periodoEmpleadoJpaRepository.findOne(periodoEmpleadoResultViewModel.getIdPeriodoEmpleado());
									
					if(periodo.getDiasVacacionesDisponibles() < vacacionEmpleadoDto.getDiasHabiles()){
						Vacacion template = new Vacacion();
						
						template.setPeriodoEmpleado(periodo);
						template.setAtendidoPor(entity.getAtendidoPor());
						template.setRegularizacion(0);
						template.setIncluidoPlanilla(0);
						template.setTipo("S");
						template.setComentarioResolucion(entity.getComentarioResolucion());
						template.setFechaCompra(entity.getFechaCompra());
						template.setCompraAutorizadaPor(entity.getCompraAutorizadaPor());
						template.setMesPlanilla(entity.getMesPlanilla());
						
						template.setDiasHabiles(periodo.getDiasVacacionesDisponibles());
						template.setDiasCalendarios(periodoEmpleadoResultViewModel.getDiasCalendariosDisponibles());
						
						template.setFechaInicio(entity.getFechaInicio());
						template.setFechaFin(DateUtil.addDays(entity.getFechaInicio(), template.getDiasCalendarios()));
						template.setEstado("A");
						
						entity.setFechaInicio(DateUtil.addDays(template.getFechaFin(), 1));
						entity.setDiasHabiles(entity.getDiasHabiles() - template.getDiasHabiles());
						entity.setDiasCalendarios(entity.getDiasCalendarios() - template.getDiasCalendarios());
						
						vacacionesEntity.add(template);
						
					}else{
						
						operacionRegistro = true;
						
						Vacacion template = new Vacacion();

						template.setPeriodoEmpleado(periodo);
						template.setAtendidoPor(entity.getAtendidoPor());
						template.setRegularizacion(0);
						template.setIncluidoPlanilla(0);
						template.setTipo("S");
						template.setComentarioResolucion(entity.getComentarioResolucion());
						template.setFechaCompra(entity.getFechaCompra());
						template.setCompraAutorizadaPor(entity.getCompraAutorizadaPor());
						template.setMesPlanilla(entity.getMesPlanilla());
						
						template.setDiasHabiles(entity.getDiasHabiles());
						template.setDiasCalendarios(entity.getDiasCalendarios());
						
						template.setFechaInicio(entity.getFechaInicio());
						template.setFechaFin(entity.getFechaFin());
						
						template.setEstado("A");
						
						vacacionesEntity.add(template);
						break;
					}
				
				}
				
				if(!operacionRegistro){
					notificacion.setCodigo(0L);
					notificacion.setSeverity("error");
					notificacion.setSummary("Runakuna Error");
					notificacion.setDetail("La solicitud de vacaciones no se puede aprobar porque la cantidad de d\u00edas solicitados exceden a la cantidad de d\u00edas disponibles.");
					return notificacion;
				}
				
					
			}else{
				notificacion.setCodigo(0L);
				notificacion.setSeverity("error");
				notificacion.setSummary("Runakuna Error");
				notificacion.setDetail("La solicitud de vacaciones no se puede aprobar porque la cantidad de d\u00edas solicitados exceden a la cantidad de d\u00edas disponibles.");
				return notificacion;
			}
		}else{
			entity.setEstado("A");
			vacacionesEntity.add(entity);
		}
		
		if(vacacionesEntity != null && vacacionesEntity.size()>0){
			
			vacacionJpaRepository.save(vacacionesEntity);
			vacacionJpaRepository.flush();
			
			Date fechaActual = DateUtil.truncDay(new Date());
			
			for (Vacacion vacacion : vacacionesEntity) {
				
				PeriodoEmpleado periodo = periodoEmpleadoJpaRepository.obtenerPeriodoEmpleadoPorFechayEmpleado(fechaActual, vacacion.getPeriodoEmpleado().getEmpleado().getIdEmpleado());
				periodo.setDiasVacacionesAcumulado(periodo.getDiasVacacionesAcumulado() - vacacion.getDiasHabiles());
				periodoEmpleadoJpaRepository.save(periodo);
				periodoEmpleadoJpaRepository.flush();
				
				PeriodoEmpleado periodoVacacion = periodoEmpleadoJpaRepository.findOne(vacacion.getPeriodoEmpleado().getIdPeriodoEmpleado());
				
				if(periodoVacacion != null){
					periodoVacacion.setDiasVacacionesDisponibles(periodoVacacion.getDiasVacacionesDisponibles() - vacacion.getDiasHabiles());
					periodoEmpleadoJpaRepository.save(periodoVacacion);
					periodoEmpleadoJpaRepository.flush();
				}
				
				enviarAlertaMailCambioEstadoVacacion(entity, vacacion.getPeriodoEmpleado(), "Aprobado");
				
				validarDiasCalendarios(vacacion);
			}
			
			if(vacacionEmpleadoDto.getFechaFin().before(fechaActual) || vacacionEmpleadoDto.getFechaFin().equals(fechaActual)){
				List<Marcacion> marcaciones = marcacionJpaRepository.getMarcacionByIdEmpleadoAndDateRange(vacacionEmpleadoDto.getIdEmpleado(), vacacionEmpleadoDto.getFechaInicio(), vacacionEmpleadoDto.getFechaFin());
				
				if(marcaciones != null && marcaciones.size()>0){
					marcaciones.forEach(m -> m.setRecalcular("S"));
					marcacionJpaRepository.save(marcaciones);
					marcacionJpaRepository.flush();
					
				}
				
			}else if(vacacionEmpleadoDto.getFechaFin().after(fechaActual) || vacacionEmpleadoDto.getFechaInicio().before(fechaActual)){
							
				List<Marcacion> marcaciones = marcacionJpaRepository.getMarcacionByIdEmpleadoAndDateRange(vacacionEmpleadoDto.getIdEmpleado(), vacacionEmpleadoDto.getFechaInicio(),fechaActual);
				
				if(marcaciones != null && marcaciones.size()>0){
					marcaciones.forEach(m -> m.setRecalcular("S"));
					marcacionJpaRepository.save(marcaciones);
					marcacionJpaRepository.flush();
				}
			}
						
		}
		
		notificacion.setCodigo(1L);
		notificacion.setSeverity("success");
		notificacion.setSummary("Runakuna Success");
		notificacion.setDetail("Se ha aprobado correctamente.");
		
		if(deleteOriginal){
			vacacionJpaRepository.delete(entity.getIdVacacion());
			vacacionJpaRepository.flush();
			
			notificacion.setDetail("Se ha creado 2 o mas solicitudes de vacaciones en diferentes periodos, se han aprobado correctamente.");
			
		}
		
		return notificacion;
	}

	@Override
	public NotificacionViewModel rechazarVacacionEmpleado(VacacionEmpleadoViewModel vacacionEmpleadoDto) {
		NotificacionViewModel notificacion = new NotificacionViewModel();
		Vacacion entity =new Vacacion();
		entity =vacacionJpaRepository.findOne(vacacionEmpleadoDto.getIdVacacion());
		PeriodoEmpleado pe=periodoEmpleadoJpaRepository.findOne(entity.getPeriodoEmpleado().getIdPeriodoEmpleado());
		entity.setEstado("R");
		entity.setComentarioResolucion(vacacionEmpleadoDto.getComentarioResolucion());
		vacacionJpaRepository.save(entity);
		vacacionJpaRepository.flush();
		enviarAlertaMailCambioEstadoVacacion(entity,pe,"Rechazado");
		notificacion.setCodigo(1L);
		notificacion.setSeverity("success");
		notificacion.setSummary("Runakuna Success");
		notificacion.setDetail("El registro fue rechazado");
		return notificacion;
	}
	
	private void enviarAlertaMailCambioEstadoVacacion(Vacacion entity,PeriodoEmpleado periodoEntity, String estado){
		
		try{
			AlertaViewModel alertaDto=alertaService.obtenerAlerta("ALERTA10");
			GeneraMsjeAlertaEmpleadoViewModel msjeAlertaDto=new GeneraMsjeAlertaEmpleadoViewModel();
			msjeAlertaDto.setIdAlerta(alertaDto.getIdAlerta());
			msjeAlertaDto.setIdEmpleado(periodoEntity.getEmpleado().getIdEmpleado());
			Map<String,String> parametrosMensaje=new HashMap<String,String>();
			SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
			parametrosMensaje.put("Estado",estado);
			parametrosMensaje.put("Fecha Inicio",sdf.format(entity.getFechaInicio()));
			parametrosMensaje.put("Fecha Fin",sdf.format(entity.getFechaFin()));
			msjeAlertaDto.setParametrosMsje(parametrosMensaje);
			alertaService.generarMensajeAlerta(msjeAlertaDto);
			
			String cuerpo=alertaDto.getCuerpo().replace("[Estado]", estado);
			cuerpo=cuerpo.replace("[Fecha Inicio]", sdf.format(entity.getFechaInicio()));
			cuerpo=cuerpo.replace("[Fecha Fin]", sdf.format(entity.getFechaFin()));
			
			String[] correos=new String[1];
			correos[0]=periodoEntity.getEmpleado().getEmailInterno();
			mailService.sendEmail(alertaDto.getAsunto(), cuerpo, correos);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public List<VacacionesEmpleadoResultViewModel> search(VacacionesEmpleadoFilterViewModel filterViewModel) {
		return null;
	}

	@Override
	public VacacionEmpleadoViewModel findOne(Long id) {
		return null;
	}

	@Override
	public NotificacionViewModel create(VacacionEmpleadoViewModel manteinanceViewModel) {
		return null;
	}

	@Override
	public NotificacionViewModel update(VacacionEmpleadoViewModel manteinanceViewModel) {
		return null;
	}

	@Override
	public NotificacionViewModel delete(Long id) {
		
		NotificacionViewModel notificacion=new NotificacionViewModel();
		if (vacacionJpaRepository.findOne(id) == null) {
			throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", "The record was changed by another user. Please re-enter the screen.");
		}
		try {			
			Vacacion vacacion = vacacionJpaRepository.findOne(id);
			List<Marcacion> marcaciones = marcacionJpaRepository.getMarcacionByIdEmpleadoAndDateRange(vacacion.getPeriodoEmpleado().getEmpleado().getIdEmpleado(), vacacion.getFechaInicio(), vacacion.getFechaFin());
			
			if(vacacion.getEstado().equals("A")){
				Date fechaActual = DateUtil.truncDay(new Date());
				
				PeriodoEmpleado periodo = periodoEmpleadoJpaRepository.obtenerPeriodoEmpleadoPorFechayEmpleado(fechaActual, vacacion.getPeriodoEmpleado().getEmpleado().getIdEmpleado());
				periodo.setDiasVacacionesAcumulado(periodo.getDiasVacacionesAcumulado() + vacacion.getDiasHabiles());
				periodoEmpleadoJpaRepository.save(periodo);
				periodoEmpleadoJpaRepository.flush();
				
				PeriodoEmpleado periodoVacacion = periodoEmpleadoJpaRepository.findOne(vacacion.getPeriodoEmpleado().getIdPeriodoEmpleado());
				
				if(periodoVacacion != null){
					periodoVacacion.setDiasVacacionesDisponibles(periodoVacacion.getDiasVacacionesDisponibles() + vacacion.getDiasHabiles());
					periodoEmpleadoJpaRepository.save(periodoVacacion);
					periodoEmpleadoJpaRepository.flush();
				}
			}
			
			vacacionJpaRepository.delete(id);
			vacacionJpaRepository.flush();
			
			notificacion.setCodigo(1L);
			notificacion.setSeverity("success");
			notificacion.setSummary("Runakuna Success");
            notificacion.setDetail("Registro eliminado correctamente.");
            
            if(marcaciones != null && marcaciones.size()>0 && vacacion.getEstado().equals("A")){
				marcaciones.forEach(m -> m.setRecalcular("S"));
				marcacionJpaRepository.save(marcaciones);
				marcacionJpaRepository.flush();
			}
			
		} catch (Exception e) {
			throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", "Restriction is Being used, Can't be deleted");
		}
		
		return notificacion;
		
	}

	@Override
	public NotificacionViewModel regularizarVacacion(VacacionEmpleadoViewModel vacacionEmpleadoDto) {
		
		NotificacionViewModel notificacion = new NotificacionViewModel();
		PeriodoEmpleado periodoEmpleado = periodoEmpleadoJpaRepository.findOne(vacacionEmpleadoDto.getIdPeriodoEmpleado());
		if(periodoEmpleado.getDiasVacacionesDisponibles() > 0 && periodoEmpleado.getPermisosDisponibles() > 0){
		
			List<VacacionEmpleadoViewModel> listVacacion = vacacionEmpleadoRepository.allListVacacion(vacacionEmpleadoDto.getIdPeriodoEmpleado());
			
			for(VacacionEmpleadoViewModel next: listVacacion){
				boolean isTrueInicio = ValidationUtils.isWithinRange(vacacionEmpleadoDto.getFechaInicio(),next.getFechaInicio(),next.getFechaFin());
				boolean isTrueFin 	 = ValidationUtils.isWithinRange(vacacionEmpleadoDto.getFechaFin(),next.getFechaInicio(),next.getFechaFin());
				if(isTrueInicio==true || isTrueFin==true){
					String resultInicio,resultFin;
					SimpleDateFormat formatter;

					formatter = new SimpleDateFormat("dd/MM/yyyy", new Locale("es", "pe"));
					resultInicio = formatter.format(next.getFechaInicio());
					resultFin = formatter.format(next.getFechaFin());

					notificacion.setCodigo(0L);
					notificacion.setSeverity("error");
					notificacion.setSummary("Runakuna Error");
					notificacion.setDetail("Las fechas ingresadas se cruzan con una o m\u00e1s vacaciones solicitadas.");
					return notificacion;
				}else{
					continue;
				}
					
			}
			Vacacion entity;
			if(vacacionEmpleadoDto.getIdVacacion() == null)
				entity = new Vacacion();
			else entity = vacacionJpaRepository.findOne(vacacionEmpleadoDto.getIdVacacion());
			
			
			entity.setPeriodoEmpleado(periodoEmpleado);
			
			Empleado empleado = empleadoJpaRepository.findOne(vacacionEmpleadoDto.getIdAtendidoPor());
			entity.setAtendidoPor(empleado);
			
			entity.setFechaInicio(vacacionEmpleadoDto.getFechaInicio());
			entity.setFechaFin(vacacionEmpleadoDto.getFechaFin());
			entity.setDiasCalendarios(vacacionEmpleadoDto.getDiasCalendarios());
			entity.setDiasHabiles(vacacionEmpleadoDto.getDiasHabiles());
			entity.setRegularizacion(1);
			if(vacacionEmpleadoDto.getIncluidoPlanilla() == null){
				entity.setIncluidoPlanilla(0);
			}else{
				entity.setIncluidoPlanilla(vacacionEmpleadoDto.getIncluidoPlanilla());
			}
			entity.setEstado("A");
			entity.setTipo("S");
			
			vacacionJpaRepository.save(entity);
			vacacionJpaRepository.flush();
			
			periodoEmpleado.setDiasVacacionesDisponibles(periodoEmpleado.getDiasVacacionesDisponibles()-vacacionEmpleadoDto.getDiasHabiles());
			//periodoEmpleado.setPermisosDisponibles(periodoEmpleado.getPermisosDisponibles()-1);
			periodoEmpleadoJpaRepository.save(periodoEmpleado);
			periodoEmpleadoJpaRepository.flush();
			
			Date fechaActual = DateUtil.truncDay(new Date());
			PeriodoEmpleado periodo = periodoEmpleadoJpaRepository.obtenerPeriodoEmpleadoPorFechayEmpleado(fechaActual, periodoEmpleado.getEmpleado().getIdEmpleado());
			periodo.setDiasVacacionesAcumulado(periodo.getDiasVacacionesAcumulado() - vacacionEmpleadoDto.getDiasHabiles());
			periodoEmpleadoJpaRepository.save(periodo);
			periodoEmpleadoJpaRepository.flush();
			
			validarDiasCalendarios(entity);
			
			notificacion.setCodigo(1L);
			notificacion.setSeverity("success");
			notificacion.setSummary("Runakuna Success");
			notificacion.setDetail("El registro fue aprobado correctamente.");
			return notificacion;
		}else{
			notificacion.setCodigo(0L);
			notificacion.setSeverity("error");
			notificacion.setSummary("Runakuna Error");
			notificacion.setDetail("No es posible registrar, no hay D\u00edas disponibles o Permisos disponibles.");
			return notificacion;
		}
	}
	
	@Override
	public NotificacionViewModel comprarVacacion(VacacionEmpleadoViewModel vacacionEmpleadoDto) {
		
		NotificacionViewModel notificacion = new NotificacionViewModel();
		PeriodoEmpleado periodoEmpleado = periodoEmpleadoJpaRepository.findOne(vacacionEmpleadoDto.getIdPeriodoEmpleado());
		if(periodoEmpleado.getDiasVacacionesDisponibles() > 0 && periodoEmpleado.getPermisosDisponibles() > 0){
		
			Vacacion entity;
			if(vacacionEmpleadoDto.getIdVacacion() == null)
				entity = new Vacacion();
			else entity = vacacionJpaRepository.findOne(vacacionEmpleadoDto.getIdVacacion());
			
			entity.setPeriodoEmpleado(periodoEmpleado);
			
			entity.setDiasCalendarios(vacacionEmpleadoDto.getDiasCalendarios());
			entity.setDiasHabiles(vacacionEmpleadoDto.getDiasHabiles());
			
			Empleado atendidoPor = empleadoJpaRepository.findOne(vacacionEmpleadoDto.getIdAtendidoPor());
			entity.setAtendidoPor(atendidoPor);
			
			Empleado compraAutorizadaPor = empleadoJpaRepository.findOne(vacacionEmpleadoDto.getIdCompraAutorizadaPor());
			entity.setCompraAutorizadaPor(compraAutorizadaPor);
			
			entity.setRegularizacion(0);
			entity.setIncluidoPlanilla(0);
			
			entity.setEstado("A");
			entity.setTipo("C");
			
			vacacionJpaRepository.save(entity);
			vacacionJpaRepository.flush();
			
			periodoEmpleado.setDiasVacacionesDisponibles(periodoEmpleado.getDiasVacacionesDisponibles()-vacacionEmpleadoDto.getDiasHabiles());
			//periodoEmpleado.setPermisosDisponibles(periodoEmpleado.getPermisosDisponibles()-1);
			periodoEmpleadoJpaRepository.save(periodoEmpleado);
			periodoEmpleadoJpaRepository.flush();
			
			Date fechaActual = DateUtil.truncDay(new Date());
			PeriodoEmpleado periodo = periodoEmpleadoJpaRepository.obtenerPeriodoEmpleadoPorFechayEmpleado(fechaActual, periodoEmpleado.getEmpleado().getIdEmpleado());
			periodo.setDiasVacacionesAcumulado(periodo.getDiasVacacionesAcumulado() - vacacionEmpleadoDto.getDiasHabiles());
			periodoEmpleadoJpaRepository.save(periodo);
			periodoEmpleadoJpaRepository.flush();
						
			notificacion.setCodigo(1L);
			notificacion.setSeverity("success");
			notificacion.setSummary("Runakuna Success");
			notificacion.setDetail("El registro fue aprobado correctamente.");
			return notificacion;
		}else{
			notificacion.setCodigo(0L);
			notificacion.setSeverity("error");
			notificacion.setSummary("Runakuna Error");
			notificacion.setDetail("No es posible registrar, no hay D\u00edas disponibles o Permisos disponibles.");
			return notificacion;
		}
	}

	@Override
	public NotificacionViewModel incluirVacacionAPlanilla(Long id) {
		
		NotificacionViewModel notificacion = new NotificacionViewModel();
		
		try {
		
		Vacacion entity = vacacionJpaRepository.findOne(id);
		if(entity == null){
			throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", "The record was changed by another user. Please re-enter the screen.");
		}
		
		entity.setIncluidoPlanilla(1);
		
		vacacionJpaRepository.save(entity);
		vacacionJpaRepository.flush();
		
		notificacion.setCodigo(1L);
		notificacion.setSeverity("success");
		notificacion.setSummary("Runakuna Success");
		notificacion.setDetail("El registro fue incluido a planilla correctamente.");
		
		}catch (Exception e) {
			throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", "Restriction is Being used, Can't be updated");
		}
		
		return notificacion;
	}
	
	private void validarDiasCalendarios(Vacacion vacActual){
		List<Vacacion> vacaciones = vacacionJpaRepository.allListVacacionByIdPeriodoAndAprobado(vacActual.getPeriodoEmpleado().getIdPeriodoEmpleado());
				
		int diasHabiles = 0;
		int diasCalendarios = 0;
		int diasCalFict = 0;
		
		for (Vacacion vacacion : vacaciones) {
			diasHabiles = diasHabiles + vacacion.getDiasHabiles();
			diasCalendarios = diasCalendarios + vacacion.getDiasCalendarios();
			
			vacacion.setIncluidoPlanilla(0);
		}
		
		if(diasHabiles>=5 && diasHabiles<10){
			diasCalFict = 2 - (diasCalendarios - diasHabiles);
			
		}else if(diasHabiles>=10 && diasHabiles<15){
			diasCalFict = 4 - (diasCalendarios - diasHabiles);
			
		}else if(diasHabiles>=15 && diasHabiles<20){
			diasCalFict = 6 - (diasCalendarios - diasHabiles);
		}else if(diasHabiles>=20 && diasHabiles<22){
			diasCalFict = 8 - (diasCalendarios - diasHabiles);
		}else{
			return;
		}
		
		if(diasCalFict > 0){
			
			Calendar c1 = DateUtil.buildCal(vacActual.getFechaFin());
			
			int dayOfWeek = c1.get(Calendar.DAY_OF_WEEK);
			
			dayOfWeek = 7 - dayOfWeek;
			
			do{
				Date fechaIniFic = DateUtil.addDays(vacActual.getFechaFin(), dayOfWeek);
				Date fechaFinFic = DateUtil.addDays(vacActual.getFechaFin(), dayOfWeek + (diasCalFict - 1));
				
				Vacacion vacacionFict = new Vacacion();
				vacacionFict.setPeriodoEmpleado(vacActual.getPeriodoEmpleado());
				vacacionFict.setAtendidoPor(vacActual.getAtendidoPor());
				vacacionFict.setFechaInicio(fechaIniFic);
				vacacionFict.setFechaFin(fechaFinFic);
				
				vacacionFict.setDiasCalendarios(diasCalFict);
				vacacionFict.setDiasHabiles(0);
				vacacionFict.setEstado("A");
				vacacionFict.setTipo("P");
				vacacionFict.setRegularizacion(0);
				vacacionFict.setIncluidoPlanilla(0);
				
				vacaciones.add(vacacionFict);
				
				dayOfWeek = dayOfWeek + 7;
				diasCalFict = diasCalFict -2;
			}while(diasCalFict>0);
			
			vacacionJpaRepository.save(vacaciones);
			vacacionJpaRepository.flush();
			
		}
				
	}
	
	private void enviarAlertaMailSolicitarVacaciones(Vacacion entity){
		
		try{
			AlertaViewModel alertaDto=alertaService.obtenerAlerta("ALERTA07");
			
			GeneraMsjeAlertaEmpleadoViewModel msjeAlertaDto=null;
			List<String> correos=new ArrayList<String>();
			correos.add(entity.getAtendidoPor().getEmailInterno());
			
			String nombreEmpleado = entity.getPeriodoEmpleado().getEmpleado().getApellidoPaterno()+" "+entity.getPeriodoEmpleado().getEmpleado().getApellidoMaterno()+", "+entity.getPeriodoEmpleado().getEmpleado().getNombre();
			
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
				parametrosMensaje.put("Fecha Inicio",sdf.format(entity.getFechaInicio()));
				parametrosMensaje.put("Fecha Fin",sdf.format(entity.getFechaFin()));
				parametrosMensaje.put("Jefe Inmediato",entity.getAtendidoPor().getApellidoPaterno()+" "+entity.getAtendidoPor().getApellidoMaterno()+", "+entity.getAtendidoPor().getNombre());
				msjeAlertaDto.setParametrosMsje(parametrosMensaje);
				alertaService.generarMensajeAlerta(msjeAlertaDto);
			}
			if(correos.size()>0) {
				
				String asunto=alertaDto.getAsunto().replace("[Empleado]", nombreEmpleado);
				String cuerpo=alertaDto.getCuerpo().replace("[Empleado]", nombreEmpleado);
				cuerpo=cuerpo.replace("[Fecha Inicio]", sdf.format(entity.getFechaInicio()));
				cuerpo=cuerpo.replace("[Fecha Fin]", sdf.format(entity.getFechaFin()));
				cuerpo=cuerpo.replace("[Jefe Inmediato]", entity.getAtendidoPor().getApellidoPaterno()+" "+entity.getAtendidoPor().getApellidoMaterno()+", "+entity.getAtendidoPor().getNombre());
				mailService.sendEmail(asunto, cuerpo, correos.toArray(new String[correos.size()]));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public List<VacacionResultViewModel> obtenerBusquedaEmpleadoPlanilla(VacacionFilterViewModel filterViewModel){
		
		return vacacionEmpleadoRepository.generarBusquedaVacacionesPlanilla(filterViewModel);
		
	}
	
	@Override
	public NotificacionViewModel registrarVacacionesEnPanilla(VacacionEmpleadoPlanillaViewModel vacacionEmpleadoPlanilla) {
		
		
		String mes = null;
		NotificacionViewModel dto = new NotificacionViewModel();
		if(vacacionEmpleadoPlanilla.getAnio() !=null && vacacionEmpleadoPlanilla.getMes() != null){
			mes = StringUtil.autocompleteZeroLeft(vacacionEmpleadoPlanilla.getMes().toString())+"/"+String.valueOf(vacacionEmpleadoPlanilla.getAnio());
			
		}
		
		String mesPlanilla = mes;
		
		List<Long> ids = new ArrayList<>();
		
		vacacionEmpleadoPlanilla.getVacacionesEnPlanilla().forEach(m->{
			ids.add(m.getIdVacacion());
			});
		
		List<Vacacion> vacaciones = vacacionJpaRepository.findAll(ids);
		
		if(vacaciones != null && vacaciones.size()>0){
			vacaciones.forEach(m->{
				m.setMesPlanilla(mesPlanilla);
				m.setIncluidoPlanilla(1);
			});
			
			vacacionJpaRepository.save(vacaciones);
			vacacionJpaRepository.flush();
			
			dto.setCodigo(1L);
			dto.setSeverity("success");
			dto.setSummary("Runakuna Success");
			dto.setDetail("Los registros fueron incluido a planilla correctamente.");
			
		}else{
			dto.setCodigo(0L);
			dto.setSeverity("success");
			dto.setSummary("Runakuna Success");
			dto.setDetail("No hay registros.");
		}
		
		return dto;
	}

	@Override
	public Integer obtenerDiasVacacionesPendientes(Long idEmpleado) {
		
		Integer diasPendientes = new Integer(0);
	
		List<Vacacion> vacacionesPendientes = vacacionJpaRepository.allListVacacionByIdEmpleadoAndEnviadoPendiente(idEmpleado);
		
		if(vacacionesPendientes!=null && vacacionesPendientes.size()>0){
		
			for (Vacacion vacacion : vacacionesPendientes) {
				diasPendientes = diasPendientes + vacacion.getDiasHabiles();
			}
						
		}
		
		return diasPendientes;
		
	}
	
	private void onDiasHabiles(Vacacion vac){
		
		Calendar fechaInicial = DateUtil.buildCal(vac.getFechaInicio());
		
		Calendar fechaFinal = DateUtil.buildCal(vac.getFechaFin());
		
		int diffDays= 0;
		while (fechaInicial.before(fechaFinal) || fechaInicial.equals(fechaFinal)) {

			if (fechaInicial.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY && fechaInicial.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
				diffDays++;
			}
		
			fechaInicial.add(Calendar.DATE, 1);

		}
		
		if(diffDays < vac.getDiasHabiles()){
			int diferencia = vac.getDiasHabiles() - diffDays;
			vac.setDiasCalendarios(vac.getDiasCalendarios() + diferencia);
			vac.setFechaFin(DateUtil.addDays(vac.getFechaFin(), diferencia));
		}
		
	}

}
