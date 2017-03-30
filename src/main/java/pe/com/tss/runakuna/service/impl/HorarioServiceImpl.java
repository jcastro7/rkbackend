package pe.com.tss.runakuna.service.impl;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.tss.runakuna.domain.model.entities.*;
import pe.com.tss.runakuna.domain.model.repository.jdbc.EmpleadoRepository;
import pe.com.tss.runakuna.domain.model.repository.jdbc.HorarioRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.*;
import pe.com.tss.runakuna.enums.SeverityStatusEnum;
import pe.com.tss.runakuna.exception.BusinessException;
import pe.com.tss.runakuna.exception.GenericRestException;
import pe.com.tss.runakuna.service.HorarioService;
import pe.com.tss.runakuna.view.model.HorarioFilterViewModel;
import pe.com.tss.runakuna.view.model.HorarioResultViewModel;
import pe.com.tss.runakuna.view.model.HorarioDiaViewModel;
import pe.com.tss.runakuna.view.model.HorarioViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class HorarioServiceImpl implements HorarioService {

	@Autowired
	Mapper mapper;
	
	@Autowired
	HorarioRepository horarioJdbcRepository;
	
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
	
	@Autowired EmpleadoRepository	empleadoJdbcRepository;

	private static final Logger log = LoggerFactory.getLogger(HorarioServiceImpl.class);
		
	@Override
	public List<HorarioViewModel> obtenerHorariosPorTipoHorario(HorarioViewModel horarioDto) {
		
		return horarioJdbcRepository.obtenerHorariosPorTipoHorario(horarioDto);
	}
	
	@Override
	public HorarioViewModel obtenerHorarioPorTipoHorarioPorDefecto(HorarioViewModel horarioDto) {
		
		HorarioViewModel dto = horarioJdbcRepository.obtenerHorariosPorTipoHorarioyPorDefecto(horarioDto);
		if(dto!=null){
			List<HorarioDiaViewModel> horarioDias = horarioJdbcRepository.obtenerHorarioDiaPorHorario(dto.getIdHorario());
			dto.setHorarioDias(horarioDias);
		}
		
		return dto;
	}
	
	@Override
	public List<HorarioDiaViewModel> obtenerHorarioDiaPorHorario(Long idHorario) {
		
		List<HorarioDiaViewModel> horarioDias = horarioJdbcRepository.obtenerHorarioDiaPorHorario(idHorario);
			
		return horarioDias;
	}
		
	@Override
	public HorarioViewModel obtenerHorarioPorIdHorario(HorarioViewModel horarioDto) {
		HorarioViewModel dto = new HorarioViewModel();
		Horario entity;
		entity = horarioJpaRepository.findOne(horarioDto.getIdHorario());
		
		if(entity!= null){
			mapper.map(entity, dto);
			
			if(entity.getProyecto() != null)
				dto.setIdProyecto(entity.getProyecto().getIdProyecto());
		}
		
		return dto;
	}
	
	@Override
	public List<HorarioViewModel> obtenerHorarios() {
		
		List<HorarioViewModel> dto = new ArrayList<>();
		
		List<Horario> horarioDias = horarioJpaRepository.findAll();
		
		if(horarioDias!= null){
			dto = horarioDias.stream().map(m -> mapper.map(m, HorarioViewModel.class)).collect(toList());
		}
		return dto;
	}
	
	@Override
	public List<HorarioResultViewModel> search(HorarioFilterViewModel filterViewModel) {
		List<HorarioResultViewModel> result=  horarioJdbcRepository.busquedaHorario(filterViewModel);
		return result;
	}

	@Override
	public HorarioViewModel findOne(Long id) {
		HorarioViewModel manteinanceViewModel = new HorarioViewModel();
		Horario entity;
		entity = horarioJpaRepository.findOne(id);
		
		if(entity!= null){
			mapper.map(entity, manteinanceViewModel);
			
			if(entity.getProyecto() != null)
				manteinanceViewModel.setIdProyecto(entity.getProyecto().getIdProyecto());
		}
		BigDecimal horasSemanalesCubiertas=empleadoJdbcRepository.obtenerHorasSemanalesCubiertas(id);
		horasSemanalesCubiertas=horasSemanalesCubiertas.setScale(2, RoundingMode.HALF_UP);
		manteinanceViewModel.setHorasCubiertas(horasSemanalesCubiertas.toPlainString());
		
		return manteinanceViewModel;
	}

	@Override
	@Transactional
	public NotificacionViewModel create(HorarioViewModel manteinanceViewModel) {
		List<Horario> horariosExistentes = horarioJpaRepository.validarHorarioPorNombre(manteinanceViewModel.getNombre());
		
		if(horariosExistentes != null && horariosExistentes.size()>0){
			throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", "Ya existe horario con el nombre "+manteinanceViewModel.getNombre()+".");
		}

		return createOrUpdateHorario(manteinanceViewModel);
	}

	@Override
	@Transactional
	public NotificacionViewModel update(HorarioViewModel manteinanceViewModel) {
		return createOrUpdateHorario(manteinanceViewModel);
	}

	@Override
	public NotificacionViewModel delete(Long id) {
		
		NotificacionViewModel notificacion=new NotificacionViewModel();
		if (horarioJpaRepository.findOne(id) == null) {
			throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", "The record was changed by another user. Please re-enter the screen.");
		}
		try {
			horarioJpaRepository.delete(id);
			horarioJpaRepository.flush();
			notificacion.setCodigo(1L);
			notificacion.setSeverity(SeverityStatusEnum.SUCCESS.getCode());
			notificacion.setSummary("Runakuna Success");
			notificacion.setDetail("Registro fue eliminado correctamente.");
		} catch (Exception e) {
			throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", "Restriction is Being used, Can't be deleted");
		}
		return notificacion;
	}
	
	private NotificacionViewModel createOrUpdateHorario(HorarioViewModel horario){
		
		NotificacionViewModel notificacion = new NotificacionViewModel();
		Horario entity = new Horario();
		
		Empresa empresa = empresaJpaRepository.findOne(horario.getIdEmpresa());
		
		try{
			mapper.map(horario, entity);
			
			if(horario.getPorDefecto().intValue() == 1){
				if(horario.getIdProyecto() != null){
					horarioJpaRepository.updatePorDefectoPorProyecto(horario.getIdEmpresa(), horario.getIdProyecto());
				}else{
					horarioJpaRepository.updatePorDefectoPorEmpresa(horario.getIdEmpresa());
				}
			}
			
			entity.setEmpresa(empresa);
			
			if(horario.getIdProyecto() != null){
				Proyecto proyectoEntity = proyectoJpaRepository.findOne(horario.getIdProyecto());
				entity.setProyecto(proyectoEntity);
			}
		
			entity.setHorariosDias(new ArrayList<>());
				
			for (HorarioDiaViewModel horarioDiaDto : horario.getHorarioDias()) {
					
				HorarioDia horarioDiaEntity = new HorarioDia();
				mapper.map(horarioDiaDto, horarioDiaEntity);
				horarioDiaEntity.setHorario(entity);
				entity.getHorariosDias().add(horarioDiaEntity);
				
			}
			
			horarioJpaRepository.save(entity);
			horarioJpaRepository.flush();
			
			notificacion.setCodigo(1L);
			notificacion.setSeverity("success");
			notificacion.setSummary("Runakuna Success");
			notificacion.setDetail("Se actualizo Correctamente.");
		
		} catch (Exception e) {
			throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error","No es posible actualizar, "+e.getMessage());
		}
		return notificacion;
	}

}
