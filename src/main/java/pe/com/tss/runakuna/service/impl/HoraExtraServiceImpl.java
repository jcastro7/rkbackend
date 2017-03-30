package pe.com.tss.runakuna.service.impl;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.tss.runakuna.domain.model.entities.Empleado;
import pe.com.tss.runakuna.domain.model.entities.HorasExtra;
import pe.com.tss.runakuna.domain.model.repository.jdbc.EmpleadoJdbcRepository;
import pe.com.tss.runakuna.domain.model.repository.jdbc.HoraExtraRepository;
import pe.com.tss.runakuna.domain.model.repository.jdbc.MarcacionRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.EmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.HorasExtraJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.PeriodoEmpleadoJpaRepository;
import pe.com.tss.runakuna.exception.GenericRestException;
import pe.com.tss.runakuna.gateway.common.ValidationUtils;
import pe.com.tss.runakuna.service.HoraExtraService;
import pe.com.tss.runakuna.util.DateUtil;
import pe.com.tss.runakuna.util.StringUtil;
import pe.com.tss.runakuna.view.model.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class HoraExtraServiceImpl implements HoraExtraService{

	
	@Autowired
	EmpleadoJpaRepository empleadoJpaRepository;
	
	
	@Autowired
	PeriodoEmpleadoJpaRepository periodoEmpleadoJpaRepository;
	
	@Autowired
	EmpleadoJdbcRepository empleadoJdbcRepository;
	
	@Autowired
	Mapper mapper;
	
	@Autowired
	HorasExtraJpaRepository horasExtraJpaRepository;
	
	@Autowired
	HoraExtraRepository horaExtraRepository;
	
	@Autowired
	MarcacionRepository marcacionRepository;

	@Override
	public List<HorasExtraEmpleadoResultViewModel> search(HorasExtraEmpleadoFilterViewModel filterViewModel) {
		// TODO Auto-generated method stub
		return empleadoJdbcRepository.busquedaHorasExtrasEmpleado(filterViewModel);
	}

	@Override
	public HorasExtraViewModel findOne(Long id) {
		HorasExtraViewModel horasExtraViewModel=new HorasExtraViewModel();
		HorasExtra horasExtra=horasExtraJpaRepository.findOne(id);
		mapper.map(horasExtra, horasExtraViewModel);
		horasExtraViewModel.setIdEmpleado(horasExtra.getEmpleado().getIdEmpleado());
		horasExtraViewModel.setIdJefeAtendidoPor(horasExtra.getAtendidoPor().getIdEmpleado());
		horasExtraViewModel.setIdAtendidoPor(horasExtra.getAtendidoPor().getIdEmpleado());
		return horasExtraViewModel;
	}

	@Override
	public NotificacionViewModel create(HorasExtraViewModel manteinanceViewModel) {
		
		NotificacionViewModel notificacion = new NotificacionViewModel();
				
		List<HorasExtraViewModel> listHorasExtra = empleadoJdbcRepository.listHorasExtraEmpleado(manteinanceViewModel.getIdEmpleado());

		for(HorasExtraViewModel next: listHorasExtra){
			if(manteinanceViewModel.getFecha().equals(next.getFecha())){
				boolean isWithin = ValidationUtils.isWithinRangeHours(manteinanceViewModel.getHoraSalidaSolicitado(), 
																next.getHoraSalidaHorario(), next.getHoraSalidaSolicitado());
				if(manteinanceViewModel.getHoraSalidaSolicitado().equals(next.getHoraSalidaSolicitado()) || isWithin==true){
					notificacion.setCodigo(0L);
					notificacion.setSeverity("error");
					notificacion.setSummary("Runakuna Error");
					notificacion.setDetail("No es posible registrar, la hora extra se cruza con el codigo: " +
											next.getIdHorasExtra() +" Desde: "+next.getHoraSalidaHorario() +" Hasta: "+next.getHoraSalidaSolicitado());
					return notificacion;
				}
				
			}
			
		}
		
		HorasExtra entity = new HorasExtra();
		mapper.map(manteinanceViewModel, entity);
		
		Empleado empleado = empleadoJpaRepository.findOne(manteinanceViewModel.getIdEmpleado());
		entity.setEmpleado(empleado);
		
		Empleado empleadoJefe = empleadoJpaRepository.findOne(manteinanceViewModel.getIdAtendidoPor());
		entity.setAtendidoPor(empleadoJefe);
		
		entity.setHoras(Double.parseDouble(manteinanceViewModel.getHoras()));
		entity.setEstado("P");
		//entity.setTipo("RH");
		entity.setTipo(manteinanceViewModel.getTipo());
		
		horasExtraJpaRepository.save(entity);
		horasExtraJpaRepository.flush();
		
		notificacion.setCodigo(1L);
		notificacion.setSeverity("success");
		notificacion.setSummary("Runakuna Success");
		notificacion.setDetail("Se registro correctamente.");
				
		return notificacion;
	}

	@Override
	public NotificacionViewModel update(HorasExtraViewModel manteinanceViewModel) {
		NotificacionViewModel notificacion = new NotificacionViewModel();
		
		HorasExtra entity = new HorasExtra();
		
		mapper.map(manteinanceViewModel, entity);
		
		Empleado empleado = empleadoJpaRepository.findOne(manteinanceViewModel.getIdEmpleado());
		entity.setEmpleado(empleado);
		
		Empleado empleadoJefe = empleadoJpaRepository.findOne(manteinanceViewModel.getIdAtendidoPor());
		entity.setAtendidoPor(empleadoJefe);
		
		entity.setHoras(Double.parseDouble(manteinanceViewModel.getHoras()));
		
		horasExtraJpaRepository.save(entity);
		horasExtraJpaRepository.flush();
		
		notificacion.setCodigo(1L);
		notificacion.setDetail("Se registro correctamente");
		return notificacion;
	}

	@Override
	public NotificacionViewModel delete(Long idHorasExtra) {
		NotificacionViewModel notificacion=new NotificacionViewModel();
		if (horasExtraJpaRepository.findOne(idHorasExtra) == null) {
            throw new GenericRestException("ERROR", "The record was changed by another user. Please re-enter the screen.");
        }
        try {
        	horasExtraJpaRepository.delete(idHorasExtra);
        	horasExtraJpaRepository.flush();
        	notificacion.setCodigo(1L);
        	notificacion.setDetail("Se elimino registro correctamente");
        } catch (Exception e) {
        	notificacion.setCodigo(0L);
        	notificacion.setDetail("No se pudo eliminar registro correctamente");
            throw new GenericRestException("ERR_001", "Restriction is Being used, Can't be deleted");
        }
        return notificacion;
	}

	@Override
	public NotificacionViewModel aprobarHorasExtra(HorasExtraViewModel horasExtraDto) {
		NotificacionViewModel notificacion = new NotificacionViewModel();
		HorasExtra entity = horasExtraJpaRepository.findOne(horasExtraDto.getIdHorasExtra());
		entity.setEstado("A");
		horasExtraJpaRepository.save(entity);
		
		notificacion.setCodigo(1L);
		notificacion.setDetail("El registro fue aprobado correctamente");
		return notificacion;
	}

	@Override
	public NotificacionViewModel rechazarHorasExtra(HorasExtraViewModel horasExtraDto) {
		NotificacionViewModel notificacion = new NotificacionViewModel();
		HorasExtra entity = horasExtraJpaRepository.findOne(horasExtraDto.getIdHorasExtra());
		entity.setEstado("R");
		entity.setComentarioResolucion(horasExtraDto.getComentarioResolucion());
		horasExtraJpaRepository.save(entity);
		
		notificacion.setCodigo(1L);
		notificacion.setDetail("El registro fue rechazado correctamente");
		return notificacion;
	}

	@Override
	public HorasExtraViewModel informacionAdicionalHorasExtras(EmpleadoViewModel empleado) {
		HorasExtraViewModel dto = new HorasExtraViewModel();
		Integer dayOfWeek = getDayOfWeek(empleado.getFechaIngreso());
		dto = empleadoJdbcRepository.getHorarioEmpleadoDia(empleado.getIdEmpleado(), dayOfWeek);
		
		return dto;
	}
	
	public static int getDayOfWeek(Date aDate) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(aDate);
	    return cal.get(Calendar.DAY_OF_WEEK);
	}

	@Override
	public List<HorasExtraEmpleadoResultViewModel> quickSearch(QuickFilterViewModel quickFilter) {
		// TODO Auto-generated method stub
				return horaExtraRepository.busquedaRapidaHorasExtrasEmpleado(quickFilter);
	}
	
	@Override
	public NotificacionViewModel registrarRecuperarHoras(HorasExtraViewModel manteinanceViewModel) {
		NotificacionViewModel notificacion = new NotificacionViewModel();
		
		List<HorasExtraViewModel> listHorasExtra = empleadoJdbcRepository.listHorasExtraEmpleado(manteinanceViewModel.getIdEmpleado());

		for(HorasExtraViewModel next: listHorasExtra){
			if(manteinanceViewModel.getFecha().equals(next.getFecha())){
				boolean isWithin = ValidationUtils.isWithinRangeHours(manteinanceViewModel.getHoraSalidaSolicitado(), 
																next.getHoraSalidaHorario(), next.getHoraSalidaSolicitado());
				if(manteinanceViewModel.getHoraSalidaSolicitado().equals(next.getHoraSalidaSolicitado()) || isWithin==true){
					notificacion.setCodigo(0L);
					notificacion.setDetail("No es posible registrar, la hora extra se cruza con el codigo: " +
											next.getIdHorasExtra() +" Desde: "+next.getHoraSalidaHorario() +" Hasta: "+next.getHoraSalidaSolicitado());
					return notificacion;
				}
				
			}
			
		}
		
		HorasExtra entity = new HorasExtra();
		
		mapper.map(manteinanceViewModel, entity);
		
		Empleado empleado = empleadoJpaRepository.findOne(manteinanceViewModel.getIdEmpleado());
		entity.setEmpleado(empleado);
		
		Empleado antendido = empleadoJpaRepository.findOne(manteinanceViewModel.getIdAtendidoPor());
		entity.setAtendidoPor(antendido);
		entity.setHoras(Double.parseDouble(manteinanceViewModel.getHoras()));
		
		horasExtraJpaRepository.save(entity);
		horasExtraJpaRepository.flush();
		
		notificacion.setCodigo(1L);
		notificacion.setDetail("Se registro correctamente");
		return notificacion;
	}
	
	@Override
	public List<HorasExtraViewModel> verHorasExtras(PeriodoEmpleadoViewModel periodoEmpleado){
		return horaExtraRepository.verHorasExtras(periodoEmpleado);
	}

	@Override
	public HorasExtraViewModel obtenerHorasSemanalesPendientes(Long idEmpleado) {
		// TODO Auto-generated method stub
		EmpleadoViewModel empleadoDto=new EmpleadoViewModel();
		HorasExtraViewModel horasExtraViewModel=new HorasExtraViewModel();
		empleadoDto.setIdEmpleado(idEmpleado);
		MarcacionViewModel marcacionDto=marcacionRepository.obtenerMarcacionesPorEmpleadoyFechaActual(empleadoDto);
		int diaSemana=DateUtil.getDayOfWeek(new Date());
		HorarioEmpleadoViewModel horarioEmpleadoViewModel =empleadoJdbcRepository.verHorarioEmpleado(idEmpleado);
		
		String dia= StringUtil.obtenerCodigoDia(diaSemana+1);
		
		HorarioEmpleadoDiaViewModel horarioDiaViewModel=empleadoJdbcRepository.obtenerHorarioDiaPorDia(horarioEmpleadoViewModel.getIdHorarioEmpleado(), dia);
		BigDecimal horasSemanalesTeoricas=empleadoJdbcRepository.obtenerHorasSemanalesLunesViernes(horarioEmpleadoViewModel.getIdHorarioEmpleado());
		BigDecimal horasSemanales= new BigDecimal(horarioEmpleadoViewModel.getHorasSemanal());
		horasExtraViewModel.setHoraSalidaHorario(horarioDiaViewModel.getSalida());
		BigDecimal horasExtraTrabajadas=new BigDecimal(0);
		if(diaSemana==1){
			horasExtraViewModel.setHorasSemanalesPendientes(horasSemanales.subtract(horasSemanalesTeoricas).setScale(2, RoundingMode.HALF_UP).toPlainString());
		} else {
			int diaMarcacion=DateUtil.getDayOfWeek(marcacionDto.getFecha());
			
			for(int i=1;i<diaMarcacion;i++) {
				Date fechaMarcacion=DateUtil.addDays(marcacionDto.getFecha(), -1*i);
				MarcacionViewModel marcacionDiariaDto=marcacionRepository.obtenerMarcacionesPorEmpleadoyFecha(empleadoDto,fechaMarcacion);
				
				if(marcacionDiariaDto!= null && "NO".equals(marcacionDiariaDto.getInasistencia())){
				
					if(marcacionDiariaDto.getHorasTrabajoHorario()!=null && marcacionDiariaDto.getHorasTrabajoReal()!=null &&
							marcacionDiariaDto.getHorasTrabajoHorario().subtract(marcacionDiariaDto.getHorasTrabajoReal()).doubleValue()<0) {
						horasExtraTrabajadas=horasExtraTrabajadas.add(marcacionDiariaDto.getHorasTrabajoHorario().subtract(marcacionDiariaDto.getHorasTrabajoReal()));
					}
				}
			}
			horasExtraTrabajadas=horasExtraTrabajadas.setScale(2, RoundingMode.HALF_UP);
			horasExtraViewModel.setHorasSemanalesPendientes(horasSemanales.subtract(horasSemanalesTeoricas).add(horasExtraTrabajadas).
					setScale(2, RoundingMode.HALF_UP).toPlainString());
		}
		
		return horasExtraViewModel;
	}

	

}
