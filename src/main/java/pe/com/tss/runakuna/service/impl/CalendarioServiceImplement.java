package pe.com.tss.runakuna.service.impl;

import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.tss.runakuna.domain.model.entities.Calendario;
import pe.com.tss.runakuna.domain.model.repository.jdbc.CalendarioJdbcRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.CalendarioRepositoryJpa;
import pe.com.tss.runakuna.enums.SeverityStatusEnum;
import pe.com.tss.runakuna.exception.BusinessException;
import pe.com.tss.runakuna.exception.GenericRestException;
import pe.com.tss.runakuna.service.CalendarioService;
import pe.com.tss.runakuna.view.model.CalendarioFilterViewModel;
import pe.com.tss.runakuna.view.model.CalendarioResultViewModel;
import pe.com.tss.runakuna.view.model.CalendarioViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;

@Service
public class CalendarioServiceImplement implements CalendarioService {

	@Autowired
	Mapper mapper;

	@Autowired
	CalendarioJdbcRepository calendarioJdbcRepository;

	@Autowired
	CalendarioRepositoryJpa calendarioRepositoryJpa;

	@Override
	public List<CalendarioResultViewModel> searchDiasNoLaborables(CalendarioFilterViewModel dto) {
		return calendarioJdbcRepository.searchDiasNoLaborables(dto);
	}

	@Override
	public CalendarioViewModel findOne(Long idCalendario) {

		Calendario calendario = calendarioRepositoryJpa.findOne(idCalendario);
		CalendarioViewModel dto = new CalendarioViewModel();
		mapper.map(calendario,dto);

		return dto;
	}

	@Override
	public NotificacionViewModel create(CalendarioViewModel calendarioDto) {
		NotificacionViewModel notificacionDto=new NotificacionViewModel();
		notificacionDto=crearActualizarCalendarioFeriado(calendarioDto);
		return notificacionDto;
	}

	@Override
	public NotificacionViewModel update(CalendarioViewModel calendarioDto) {
		NotificacionViewModel notificacionDto=new NotificacionViewModel();
		notificacionDto=crearActualizarCalendarioFeriado(calendarioDto);
		return notificacionDto;
	}

	private NotificacionViewModel crearActualizarCalendarioFeriado(CalendarioViewModel calendarioDto) {
		NotificacionViewModel notificacionDto=new NotificacionViewModel();

		Calendario calendario = new Calendario();
		try {
			mapper.map(calendarioDto, calendario);

			calendarioRepositoryJpa.save(calendario);
			calendarioRepositoryJpa.flush();
			notificacionDto.setCodigo(1L);
			notificacionDto.setSeverity("success");
			notificacionDto.setSummary("Runakuna Success");
			notificacionDto.setDetail("Se registro correctamente");
		} catch (Exception e) {
			notificacionDto.setCodigo(0L);
			notificacionDto.setSeverity("error");
			notificacionDto.setSummary("Runakuna Error");
			notificacionDto.setDetail("No es posible registrar, "+e.getMessage());
		}



		return notificacionDto;
	}

	@Override
	public NotificacionViewModel delete(Long idCalendario) {
		NotificacionViewModel notificacion = new NotificacionViewModel();
		if (calendarioRepositoryJpa.findOne(idCalendario) == null) {
			throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", "The record was changed by another user. Please re-enter the screen.");
		}
		try {
			calendarioRepositoryJpa.delete(idCalendario);
			calendarioRepositoryJpa.flush();
			notificacion.setCodigo(1L);
			notificacion.setSeverity(SeverityStatusEnum.SUCCESS.getCode());
			notificacion.setSummary("Runakuna Success");
			notificacion.setDetail("Registro fue eliminado correctamente.");
		} catch (Exception e) {
			throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", "Restriction is Being used, Can't be deleted");
		}
		return notificacion;
	}

}
