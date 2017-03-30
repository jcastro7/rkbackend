package pe.com.tss.runakuna.service.impl;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.tss.runakuna.domain.model.entities.*;
import pe.com.tss.runakuna.domain.model.repository.jdbc.EmpleadoRepository;
import pe.com.tss.runakuna.domain.model.repository.jdbc.HorarioEmpleadoRepository;
import pe.com.tss.runakuna.domain.model.repository.jdbc.HorarioJdbcRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.*;
import pe.com.tss.runakuna.enums.SeverityStatusEnum;
import pe.com.tss.runakuna.exception.BusinessException;
import pe.com.tss.runakuna.service.HorarioEmpleadoService;
import pe.com.tss.runakuna.util.DateUtil;
import pe.com.tss.runakuna.view.model.DocumentoEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.EmpleadoViewModel;
import pe.com.tss.runakuna.view.model.HorarioEmpleadoDiaViewModel;
import pe.com.tss.runakuna.view.model.HorarioEmpleadoFilterViewModel;
import pe.com.tss.runakuna.view.model.HorarioEmpleadoResultViewModel;
import pe.com.tss.runakuna.view.model.HorarioEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;

import static java.util.stream.Collectors.toList;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HorarioEmpleadoServiceImpl implements HorarioEmpleadoService {

	@Autowired
	Mapper mapper;
	
	@Autowired
	HorarioEmpleadoRepository horarioEmpleadoJdbcRepository;
	
	@Autowired
	EmpleadoJpaRepository empleadoJpaRepository;
	
	@Autowired
	EmpleadoRepository empleadoJdbcRepository;
	
	@Autowired
	HorarioJdbcRepository horarioJdbcRepository;
	
	@Autowired
	EmpresaJpaRepository empresaJpaRepository;
	
	@Autowired
	ProyectoJpaRepository proyectoJpaRepository;
	
	@Autowired
	HorarioJpaRepository horarioJpaRepository;
	
	@Autowired
	HorarioDiaJpaRepository horarioDiaJpaRepository;
	
	@Autowired
	HorarioEmpleadoJpaRepository horarioEmpleadoJpaRepository;

	@Autowired
    HorarioEmpleadoDiaJpaRepository horarioEmpleadoDiaJpaRepository;
	
	@Autowired
	MarcacionJpaRepository marcacionJpaRepository;	
	
	@Autowired
	RegistroMarcacionEmpleadoJpaRepository registroMarcacionEmpleadoJpaRepository;
	
	@Override
	public  List<HorarioEmpleadoViewModel> verHorariosEmpleado(Long idEmpleado) {
		List<HorarioEmpleadoViewModel> horarios = new ArrayList<>();
		horarios = empleadoJdbcRepository.verHorariosEmpleado(idEmpleado);
		
		return horarios;
	}
	
	@Override
	public  List<HorarioEmpleadoResultViewModel> obtenerBusquedaHorariosEmpleado(Long idEmpleado) {
		List<HorarioEmpleadoResultViewModel> horarios = new ArrayList<>();
		horarios = empleadoJdbcRepository.busquedaHorariosEmpleado(idEmpleado);
		
		return horarios;
	}
	
	@Override
	public List<HorarioEmpleadoResultViewModel> search(HorarioEmpleadoFilterViewModel filterViewModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HorarioEmpleadoViewModel findOne(Long id) {
		
		HorarioEmpleadoViewModel horarioEmpleado = new HorarioEmpleadoViewModel();
		horarioEmpleado = horarioEmpleadoJdbcRepository.findOneById(id);
		
		List<HorarioEmpleadoDiaViewModel> horariosEmpleadoDia = new ArrayList<>();
		horariosEmpleadoDia = horarioEmpleadoJdbcRepository.findHorarioEmpleadoDiaByIdHorarioEmpleado(id);
		
		horarioEmpleado.setHorariosEmpleadoDia(horariosEmpleadoDia);
		
		return horarioEmpleado;
	}

	@Override
	public NotificacionViewModel create(HorarioEmpleadoViewModel manteinanceViewModel) {
		
		NotificacionViewModel notificacion = new NotificacionViewModel();
		
		List<HorarioEmpleado> horariosExistentes = horarioEmpleadoJpaRepository.obtenerHorariosEmpleado(manteinanceViewModel.getIdEmpleado());
		
		if(horariosExistentes!=null && horariosExistentes.size()>0){
			HorarioEmpleado lastHorario = horarioEmpleadoJpaRepository.obtenerUltimoHorarioEmpleado(manteinanceViewModel.getInicioVigencia(), manteinanceViewModel.getIdEmpleado());
			
			if(lastHorario == null){
				notificacion.setCodigo(0L);
				notificacion.setDetail("la fecha del horario debe ser mayor a la ultima fecha de horario del empleado registrado.");
				return notificacion;
			}
			
			lastHorario.setFinVigencia(DateUtil.addDays(manteinanceViewModel.getInicioVigencia(), -1));
			horarioEmpleadoJpaRepository.save(lastHorario);
			horarioEmpleadoJpaRepository.flush();
			
		}
		
		notificacion = createOrUpdateHorarioEmpleado(manteinanceViewModel);
		
		//recalcularMarcaciones(manteinanceViewModel);
		
		return notificacion;
	}
	
	@Override
	public NotificacionViewModel update(HorarioEmpleadoViewModel manteinanceViewModel) {
		
		NotificacionViewModel notificacion = new NotificacionViewModel();
		notificacion = createOrUpdateHorarioEmpleado(manteinanceViewModel);
		return notificacion;
	}

	@Override
	public NotificacionViewModel delete(Long id) {
		NotificacionViewModel notificacion=new NotificacionViewModel();
		
		try {
			
			HorarioEmpleado horarioEmpleado = horarioEmpleadoJpaRepository.findOne(id);
			if (horarioEmpleado == null) {
				throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", "The record was changed by another user. Please re-enter the screen.");
			}
			
			List<HorarioEmpleado> horariosPrev = horarioEmpleadoJpaRepository.findPrevHorarioEmpleado(horarioEmpleado.getEmpleado().getIdEmpleado(), horarioEmpleado.getInicioVigencia());
			
			if(horariosPrev!=null && horariosPrev.size()>0){
				HorarioEmpleado horarioPrev = horariosPrev.get(0);
				horarioPrev.setFinVigencia(horarioEmpleado.getFinVigencia());
				horarioEmpleadoJpaRepository.save(horarioPrev);
				horarioEmpleadoJpaRepository.flush();
			}
				
			
			horarioEmpleadoJpaRepository.delete(id);
			horarioEmpleadoJpaRepository.flush();

			notificacion.setCodigo(1L);
            notificacion.setSeverity(SeverityStatusEnum.SUCCESS.getCode());
			notificacion.setSummary("Runakuna Success");
            notificacion.setDetail("Registro fue eliminado correctamente.");
			
		} catch (Exception e) {
			throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", "Restriction is Being used, Can't be deleted");
		}
		return notificacion;
	}
	
	@Transactional
	private NotificacionViewModel createOrUpdateHorarioEmpleado(HorarioEmpleadoViewModel horarioEmpleado) {
		
		NotificacionViewModel notificacion = new NotificacionViewModel();
		
		HorarioEmpleado entity = new HorarioEmpleado();
		
		mapper.map(horarioEmpleado, entity);
		
		Empleado empleadoEntity = empleadoJpaRepository.findOne(horarioEmpleado.getIdEmpleado());
		
		try{
			entity.setEmpleado(empleadoEntity);
			
			if(horarioEmpleado.getIdHorario() != null){
				Horario horarioEntity = horarioJpaRepository.findOne(horarioEmpleado.getIdHorario());
				entity.setHorario(horarioEntity);
			}
					
			entity.setHorarioEmpleadoDias(new ArrayList<>());
			
			for (HorarioEmpleadoDiaViewModel horarioEmpleadoDiaDto : horarioEmpleado.getHorariosEmpleadoDia()) {
					
				HorarioEmpleadoDia horarioEmpleadoDiaEntity = new HorarioEmpleadoDia();
					
				mapper.map(horarioEmpleadoDiaDto, horarioEmpleadoDiaEntity);
					
				horarioEmpleadoDiaEntity.setHorarioEmpleado(entity);
				entity.getHorarioEmpleadoDias().add(horarioEmpleadoDiaEntity);
			}
			
			horarioEmpleadoJpaRepository.save(entity);
			horarioEmpleadoJpaRepository.flush();
			
			notificacion.setCodigo(1L);
			notificacion.setDetail("Se registro Correctamente");
			
		} catch (Exception e) {
			
			notificacion.setCodigo(0L);
			notificacion.setDetail("No es posible actualizar, "+e.getMessage());
		}
		return notificacion;
	}
	
	private void recalcularMarcaciones(HorarioEmpleadoViewModel manteinanceViewModel){
		Date fechaActual = new Date();
		Date fechaInicioVigencia = manteinanceViewModel.getInicioVigencia();
		
		if(fechaInicioVigencia.after(fechaActual) || fechaInicioVigencia.equals(fechaActual)){
			return;
		}
		
		do{
			Marcacion marcacion = marcacionJpaRepository.obtenerMarcacionPorIdEmpleadoyDate(manteinanceViewModel.getIdEmpleado(), fechaInicioVigencia);
			if(marcacion !=null){
				
				List<RegistroMarcacionEmpleado> marcaciones = registroMarcacionEmpleadoJpaRepository.findByIdEmpleadoAndFecha(manteinanceViewModel.getIdEmpleado(), fechaInicioVigencia, "O");
				
				for (RegistroMarcacionEmpleado registroMarcacionEmpleado : marcaciones) {
					
					if(marcacion.getHoraIngreso() == null){
						
						marcacion.setHoraIngreso(registroMarcacionEmpleado.getHora());
						marcacion.setInasistencia(0);
						
						Date fechaHoraMarcacion = DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraIngreso());
						Date fechaHoraConfig =  DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraIngresoHorario());
							
						fechaHoraConfig = DateUtil.addMinutes(fechaHoraConfig, 10);
							
						if(fechaHoraConfig.before(fechaHoraMarcacion)){
							marcacion.setTardanza(1);
						}
						
						BigDecimal horaDemoraEntrada = new BigDecimal((fechaHoraMarcacion.getTime()-fechaHoraConfig.getTime())).divide(new BigDecimal((1000*60)),2);
						
						marcacion.setDemoraEntrada(horaDemoraEntrada);
						
						marcacionJpaRepository.save(marcacion);
						
					}else if(marcacion.getHoraInicioAlmuerzo() == null){
						
						marcacion.setHoraInicioAlmuerzo(registroMarcacionEmpleado.getHora());
						
						marcacionJpaRepository.save(marcacion);
						
					}else if(marcacion.getHoraFinAlmuerzo() == null){
						
						marcacion.setHoraFinAlmuerzo(registroMarcacionEmpleado.getHora());
						
						Date fechaHoraInicio = DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraInicioAlmuerzo());
						Date fechaHoraFin =  DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraFinAlmuerzo());
						
						BigDecimal horaDemoraAlmuerzo = new BigDecimal((fechaHoraFin.getTime()-fechaHoraInicio.getTime())).divide(new BigDecimal((1000*60)),2);
						
						marcacion.setDemoraAlmuerzo(horaDemoraAlmuerzo);
						
						marcacionJpaRepository.save(marcacion);
						
					}else if(marcacion.getHoraSalida() == null){
						
						marcacion.setHoraSalida(registroMarcacionEmpleado.getHora());
					
						Date fechaHoraMarcacion = DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraSalida());
						Date fechaHoraConfig =  DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraSalidaHorario());
						
						BigDecimal horaDemoraSalida = new BigDecimal((fechaHoraMarcacion.getTime()-fechaHoraConfig.getTime())).divide(new BigDecimal((1000*60)),2);
						
						marcacion.setDemoraSalida(horaDemoraSalida);
						
						BigDecimal horascal = marcacion.getHorasTrabajoHorario().subtract(marcacion.getHorasPermiso());
						horascal=horascal.add(marcacion.getHorasExtra());
							
						BigDecimal minutoscal = marcacion.getDemoraEntrada().add(marcacion.getDemoraAlmuerzo());
								
						minutoscal = minutoscal.subtract(marcacion.getDemoraSalida());
						
						horascal=horascal.multiply(new BigDecimal(60));
						
						BigDecimal horasReal = horascal.subtract(minutoscal);
						
						horasReal = horasReal.divide(new BigDecimal(60),2);
						
						marcacion.setHorasTrabajoReal(horasReal);
						
						marcacion.setHorasTrabajoPendiente(marcacion.getHorasTrabajoHorario().subtract(marcacion.getHorasTrabajoReal()));
						
						marcacionJpaRepository.save(marcacion);
					}
				}
			
			}
			
			fechaInicioVigencia = DateUtil.addDays(fechaInicioVigencia, 1);
		}while(fechaInicioVigencia.before(fechaActual));
		
	}
	
}
