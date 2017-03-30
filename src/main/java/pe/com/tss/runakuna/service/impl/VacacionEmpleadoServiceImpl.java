package pe.com.tss.runakuna.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.tss.runakuna.domain.model.entities.Empleado;
import pe.com.tss.runakuna.domain.model.entities.PeriodoEmpleado;
import pe.com.tss.runakuna.domain.model.entities.Vacacion;
import pe.com.tss.runakuna.domain.model.repository.jdbc.VacacionEmpleadoRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.EmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.PeriodoEmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.VacacionJpaRepository;
import pe.com.tss.runakuna.enums.SeverityStatusEnum;
import pe.com.tss.runakuna.view.model.EmpleadoViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;
import pe.com.tss.runakuna.view.model.VacacionEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.VacacionesEmpleadoFilterViewModel;
import pe.com.tss.runakuna.view.model.VacacionesEmpleadoResultViewModel;
import pe.com.tss.runakuna.exception.BusinessException;
import pe.com.tss.runakuna.exception.GenericRestException;
import pe.com.tss.runakuna.gateway.common.ValidationUtils;
import pe.com.tss.runakuna.service.VacacionEmpleadoService;

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
					notificacion.setDetail("No es posible registrar, la vacacion se cruza con el codigo: " +
											next.getIdVacacion() +" Desde: "+resultInicio +" Hasta: "+resultFin);
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
			entity.setEmpleado(empleado);
			
			entity.setFechaInicio(vacacionEmpleadoDto.getFechaInicio());
			entity.setFechaFin(vacacionEmpleadoDto.getFechaFin());
			entity.setDiasCalendarios(vacacionEmpleadoDto.getDiasCalendarios());
			entity.setDiasHabiles(vacacionEmpleadoDto.getDiasHabiles());
			entity.setRegularizacion(0);
			entity.setEstado("E");
			
			vacacionJpaRepository.save(entity);
			vacacionJpaRepository.flush();
			
			//periodoEmpleado.setDiasVacacionesDisponibles(periodoEmpleado.getDiasVacacionesDisponibles()-vacacionEmpleadoDto.getDiasHabiles());
			periodoEmpleado.setDiasVacacionesDisponibles(periodoEmpleado.getDiasVacacionesDisponibles());
			periodoEmpleado.setPermisosDisponibles(periodoEmpleado.getPermisosDisponibles()-1);
			periodoEmpleadoJpaRepository.save(periodoEmpleado);
			periodoEmpleadoJpaRepository.flush();
			
			notificacion.setCodigo(1L);
			notificacion.setSeverity("success");
			notificacion.setSummary("Runakuna Success");
			notificacion.setDetail("Se registro correctamente.");
			return notificacion;
		}else{
			notificacion.setCodigo(0L);
			notificacion.setSeverity("error");
			notificacion.setSummary("Runakuna Error");
			notificacion.setDetail("No es posible registrar, no hay Días disponibles o Permisos disponibles");
			return notificacion;
		}
		
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
		entity.setEmpleado(empleado);
		
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
		entity.setEmpleado(empleado);
		
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
	public NotificacionViewModel eliminarVacacionEmpleado(Long idVacacion) {
		NotificacionViewModel notificacion=new NotificacionViewModel();
		if (vacacionJpaRepository.findOne(idVacacion) == null) {
			throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", "The record was changed by another user. Please re-enter the screen.");
		}
		try {
			vacacionJpaRepository.delete(idVacacion);
			vacacionJpaRepository.flush();
			
			notificacion.setCodigo(1L);
            notificacion.setSeverity(SeverityStatusEnum.SUCCESS.getCode());
			notificacion.setSummary("Runakuna Success");
            notificacion.setDetail("Registro eliminado correctamente.");
			
		} catch (Exception e) {
			throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", "Restriction is Being used, Can't be deleted");
		}
		
		return notificacion;
		
	}

	@Override
	public NotificacionViewModel devolverVacacionEmpleado(VacacionEmpleadoViewModel vacacionEmpleadoDto) {
		NotificacionViewModel notificacion = new NotificacionViewModel();
		Vacacion entity = vacacionJpaRepository.findOne(vacacionEmpleadoDto.getIdVacacion());
		entity.setEstado("P");
		entity.setComentarioResolucion(vacacionEmpleadoDto.getComentarioResolucion());
		vacacionJpaRepository.save(entity);
		vacacionJpaRepository.flush();
		
		notificacion.setCodigo(1L);
		notificacion.setSeverity("success");
		notificacion.setSummary("Runakuna Success");
		notificacion.setDetail("El registro fue devuelto correctamente.");
		return notificacion;
	}

	@Override
	public NotificacionViewModel aprobarVacacionEmpleado(VacacionEmpleadoViewModel vacacionEmpleadoDto) {
		NotificacionViewModel notificacion = new NotificacionViewModel();
		Vacacion entity =new Vacacion();
		//mapper.map(vacacionEmpleadoDto, entity);
		entity =vacacionJpaRepository.findOne(vacacionEmpleadoDto.getIdVacacion());
		PeriodoEmpleado pe=periodoEmpleadoJpaRepository.findOne(entity.getPeriodoEmpleado().getIdPeriodoEmpleado());
		//entity.setPeriodoEmpleado(pe);
		entity.setEstado("A");
		//Empleado e= empleadoJpaRepository.findOne(vacacionEmpleadoDto.getIdAtendidoPor());
		//entity.setEmpleado(e);
		pe.setDiasVacacionesDisponibles(pe.getDiasVacacionesDisponibles()-vacacionEmpleadoDto.getDiasCalendarios());
		pe.setDiasVacacionesAcumulado(pe.getDiasVacacionesAcumulado()-vacacionEmpleadoDto.getDiasCalendarios());
		vacacionJpaRepository.save(entity);
		vacacionJpaRepository.flush();
		periodoEmpleadoJpaRepository.save(pe);
		periodoEmpleadoJpaRepository.flush();
		notificacion.setCodigo(1L);
		notificacion.setSeverity("success");
		notificacion.setSummary("Runakuna Success");
		notificacion.setDetail("El registro fue aprobado correctamente.");
		return notificacion;
	}

	@Override
	public NotificacionViewModel rechazarVacacionEmpleado(VacacionEmpleadoViewModel vacacionEmpleadoDto) {
		NotificacionViewModel notificacion = new NotificacionViewModel();
		Vacacion entity =new Vacacion();
		//mapper.map(vacacionEmpleadoDto, entity);
		entity =vacacionJpaRepository.findOne(vacacionEmpleadoDto.getIdVacacion());
		//PeriodoEmpleado pe=periodoEmpleadoJpaRepository.findOne(vacacionEmpleadoDto.getIdPeriodoEmpleado());
		entity.setEstado("R");
		//entity.setPeriodoEmpleado(pe);
		//Empleado e= empleadoJpaRepository.findOne(vacacionEmpleadoDto.getIdAtendidoPor());
		//entity.setEmpleado(e);
		entity.setComentarioResolucion(vacacionEmpleadoDto.getComentarioResolucion());
		vacacionJpaRepository.save(entity);
		vacacionJpaRepository.flush();
		notificacion.setCodigo(1L);
		notificacion.setSeverity("success");
		notificacion.setSummary("Runakuna Success");
		notificacion.setDetail("El registro fue rechazado");
		return notificacion;
	}

	@Override
	public List<VacacionesEmpleadoResultViewModel> search(VacacionesEmpleadoFilterViewModel filterViewModel) {
		
		return null;
	}

	@Override
	public VacacionEmpleadoViewModel findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NotificacionViewModel create(VacacionEmpleadoViewModel manteinanceViewModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NotificacionViewModel update(VacacionEmpleadoViewModel manteinanceViewModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NotificacionViewModel delete(Long id) {
		// TODO Auto-generated method stub
		return null;
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
					notificacion.setDetail("No es posible registrar, la vacacion se cruza con el codigo: " +
											next.getIdVacacion() +" Desde: "+resultInicio +" Hasta: "+resultFin);
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
			entity.setEmpleado(empleado);
			
			entity.setFechaInicio(vacacionEmpleadoDto.getFechaInicio());
			entity.setFechaFin(vacacionEmpleadoDto.getFechaFin());
			entity.setDiasCalendarios(vacacionEmpleadoDto.getDiasCalendarios());
			entity.setDiasHabiles(vacacionEmpleadoDto.getDiasHabiles());
			entity.setRegularizacion(1);
			entity.setEstado("A");
			
			vacacionJpaRepository.save(entity);
			vacacionJpaRepository.flush();
			
			//periodoEmpleado.setDiasVacacionesDisponibles(periodoEmpleado.getDiasVacacionesDisponibles()-vacacionEmpleadoDto.getDiasHabiles());
			periodoEmpleado.setDiasVacacionesDisponibles(periodoEmpleado.getDiasVacacionesDisponibles()-vacacionEmpleadoDto.getDiasCalendarios());
			periodoEmpleado.setDiasVacacionesAcumulado(periodoEmpleado.getDiasVacacionesAcumulado()-vacacionEmpleadoDto.getDiasCalendarios());
			periodoEmpleado.setPermisosDisponibles(periodoEmpleado.getPermisosDisponibles()-1);
			periodoEmpleadoJpaRepository.save(periodoEmpleado);
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
			notificacion.setDetail("No es posible registrar, no hay Dias disponibles o Permisos disponibles");
			return notificacion;
		}
	}

}
