package pe.com.tss.runakuna.service.impl;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.tss.runakuna.domain.model.entities.*;
import pe.com.tss.runakuna.domain.model.repository.jdbc.EmpleadoRepository;
import pe.com.tss.runakuna.domain.model.repository.jdbc.HorarioEmpleadoRepository;
import pe.com.tss.runakuna.domain.model.repository.jdbc.HorarioJdbcRepository;
import pe.com.tss.runakuna.domain.model.repository.jdbc.MarcacionJdbcRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.*;
import pe.com.tss.runakuna.enums.SeverityStatusEnum;
import pe.com.tss.runakuna.exception.BusinessException;
import pe.com.tss.runakuna.service.HorarioEmpleadoService;
import pe.com.tss.runakuna.util.DateUtil;
import pe.com.tss.runakuna.util.StringUtil;
import pe.com.tss.runakuna.view.model.HorarioEmpleadoDiaViewModel;
import pe.com.tss.runakuna.view.model.HorarioEmpleadoFilterViewModel;
import pe.com.tss.runakuna.view.model.HorarioEmpleadoResultViewModel;
import pe.com.tss.runakuna.view.model.HorarioEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;

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
	MarcacionJdbcRepository marcacionJdbcRepository;
	
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
				throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", "la fecha del horario debe ser mayor a la ultima fecha de horario del empleado registrado.");
			}
			
			lastHorario.setFinVigencia(DateUtil.addDays(manteinanceViewModel.getInicioVigencia(), -1));
			horarioEmpleadoJpaRepository.save(lastHorario);
			horarioEmpleadoJpaRepository.flush();
			
		}
		
		notificacion = createOrUpdateHorarioEmpleado(manteinanceViewModel);
		
		recalcularMarcaciones(manteinanceViewModel);
		return notificacion;
	}
	
	@Override
	public NotificacionViewModel update(HorarioEmpleadoViewModel manteinanceViewModel) {
		
		NotificacionViewModel notificacion = new NotificacionViewModel();
		notificacion = createOrUpdateHorarioEmpleado(manteinanceViewModel);
		recalcularMarcaciones(manteinanceViewModel);
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
			notificacion.setSeverity("success");
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
            notificacion.setSeverity(SeverityStatusEnum.SUCCESS.getCode());
			notificacion.setSummary("Runakuna Success");
            notificacion.setDetail("Se registro Correctamente");			

			
		} catch (Exception e) {
			notificacion.setCodigo(0L);
			notificacion.setSeverity(SeverityStatusEnum.ERROR.getCode());
			notificacion.setSummary("Runakuna Error");
			notificacion.setDetail("No es posible actualizar, "+e.getMessage());
			e.printStackTrace();
		}
		return notificacion;
	}
	
	private void recalcularMarcaciones(HorarioEmpleadoViewModel horarioEmpleado){
		
		Date fechaActual = DateUtil.formatoFechaArchivoMarcacion(DateUtil.fmtDt(new Date()));
		Date fechaInicioVigencia = horarioEmpleado.getInicioVigencia();
		
		Date fechaFinVigencia = horarioEmpleado.getFinVigencia();
		
		if(fechaInicioVigencia.before(fechaActual) || fechaInicioVigencia.equals(fechaActual)){
			
			if(horarioEmpleado.getHorariosEmpleadoDia() != null && horarioEmpleado.getHorariosEmpleadoDia().size() > 0){
				
				for (HorarioEmpleadoDiaViewModel horarioEmpleadoDia : horarioEmpleado.getHorariosEmpleadoDia()) {
					List<Marcacion> marcaciones = null;
					if(fechaFinVigencia!=null){
						marcaciones = marcacionJpaRepository.getMarcacionRecalcularWithinRangeDate(horarioEmpleado.getIdEmpleado(), fechaInicioVigencia, fechaFinVigencia, StringUtil.getDayNumberToCode(horarioEmpleadoDia.getDiaSemana()));
					}else{
						marcaciones = marcacionJpaRepository.getMarcacionRecalcular(horarioEmpleado.getIdEmpleado(), fechaInicioVigencia, StringUtil.getDayNumberToCode(horarioEmpleadoDia.getDiaSemana()));
					}
					
					if(marcaciones!= null && marcaciones.size()>0){
						marcaciones.forEach(m->{
							m.setRecalcular("S");
							m.setHoraIngresoHorario(horarioEmpleadoDia.getEntrada());
							m.setHoraSalidaHorario(horarioEmpleadoDia.getSalida());
							});
						
						marcacionJpaRepository.save(marcaciones);
						marcacionJpaRepository.flush();
					}
				}
			}
		}
	}
	
}
