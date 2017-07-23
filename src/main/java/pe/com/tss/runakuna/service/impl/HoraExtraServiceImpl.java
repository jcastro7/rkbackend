package pe.com.tss.runakuna.service.impl;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.tss.runakuna.domain.model.entities.Empleado;
import pe.com.tss.runakuna.domain.model.entities.HorarioEmpleado;
import pe.com.tss.runakuna.domain.model.entities.HorasExtra;
import pe.com.tss.runakuna.domain.model.repository.jdbc.EmpleadoJdbcRepository;
import pe.com.tss.runakuna.domain.model.repository.jdbc.HoraExtraRepository;
import pe.com.tss.runakuna.domain.model.repository.jdbc.MarcacionRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.EmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.HorarioEmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.HorasExtraJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.MarcacionJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.PeriodoEmpleadoJpaRepository;
import pe.com.tss.runakuna.enums.SeverityStatusEnum;
import pe.com.tss.runakuna.exception.BusinessException;
import pe.com.tss.runakuna.gateway.common.ValidationUtils;
import pe.com.tss.runakuna.service.AlertaService;
import pe.com.tss.runakuna.service.HoraExtraService;
import pe.com.tss.runakuna.service.MailService;
import pe.com.tss.runakuna.util.DateUtil;
import pe.com.tss.runakuna.util.StringUtil;
import pe.com.tss.runakuna.view.model.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	@Autowired
	MarcacionJpaRepository MarcacionJpaRepository;
	
	@Autowired
	HorarioEmpleadoJpaRepository horarioEmpleadoJpaRepository;
	
	@Autowired
	AlertaService alertaService;
	
	@Autowired
	MailService mailService;

	@Override
	public List<HorasExtraEmpleadoResultViewModel> search(HorasExtraEmpleadoFilterViewModel filterViewModel) {
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
			
		try{
			List<HorasExtraViewModel> listHorasExtra = empleadoJdbcRepository.listHorasExtraEmpleado(manteinanceViewModel.getIdEmpleado());
	
			for(HorasExtraViewModel next: listHorasExtra){
				if(manteinanceViewModel.getFecha().equals(next.getFecha())){
					boolean isWithin = ValidationUtils.isWithinRangeHours(manteinanceViewModel.getHoraSalidaSolicitado(), 
																	next.getHoraInicioSolicitado(), next.getHoraSalidaSolicitado());
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
			
			entity.setEstado("P");
			entity.setTipo(manteinanceViewModel.getTipo());
			
			horasExtraJpaRepository.save(entity);
			horasExtraJpaRepository.flush();
			
			enviarAlertaMailSolicitarHorasExtra(entity);
			
			notificacion.setCodigo(1L);
			notificacion.setSeverity("success");
			notificacion.setSummary("Runakuna Success");
			notificacion.setDetail("Se registro correctamente.");
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
	public NotificacionViewModel update(HorasExtraViewModel manteinanceViewModel) {
		NotificacionViewModel notificacion = new NotificacionViewModel();
		
		try{
			HorasExtra entity = new HorasExtra();
			
			mapper.map(manteinanceViewModel, entity);
			
			Empleado empleado = empleadoJpaRepository.findOne(manteinanceViewModel.getIdEmpleado());
			entity.setEmpleado(empleado);
			
			Empleado empleadoJefe = empleadoJpaRepository.findOne(manteinanceViewModel.getIdAtendidoPor());
			entity.setAtendidoPor(empleadoJefe);
			
			//entity.setHoras(Double.parseDouble(manteinanceViewModel.getHoras()));
			
			horasExtraJpaRepository.save(entity);
			horasExtraJpaRepository.flush();
			
			notificacion.setCodigo(1L);
			notificacion.setSeverity("success");
			notificacion.setSummary("Runakuna Success");
			notificacion.setDetail("Se registro correctamente");
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
	public NotificacionViewModel delete(Long idHorasExtra) {
		
		NotificacionViewModel notificacion = new NotificacionViewModel();
		
		if (horasExtraJpaRepository.findOne(idHorasExtra) == null) {
			throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", "The record was changed by another user. Please re-enter the screen.");
        }
        try {
        	horasExtraJpaRepository.delete(idHorasExtra);
        	horasExtraJpaRepository.flush();
        	
        	notificacion.setCodigo(1L);
    		notificacion.setSeverity("success");
    		notificacion.setSummary("Runakuna Success");
    		notificacion.setDetail("Registro fue eliminado correctamente.");
        	
        } catch (Exception e) {
        	throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", "Restriction is Being used, Can't be deleted");
        }

        return notificacion;
	}

	@Override
	public NotificacionViewModel aprobarHorasExtra(HorasExtraViewModel horasExtraDto) {
		NotificacionViewModel notificacion = new NotificacionViewModel();
		HorasExtra entity = horasExtraJpaRepository.findOne(horasExtraDto.getIdHorasExtra());
		entity.setEstado("A");
		horasExtraJpaRepository.save(entity);
		horasExtraJpaRepository.flush();
		
		enviarAlertaMailCambioEstadoHorasExtras(entity, "Aprobado");
		
		//recalcular
		recalcularHorasExtrasPendientes(entity);
		
		notificacion.setCodigo(1L);
		notificacion.setSeverity("success");
		notificacion.setSummary("Runakuna Success");
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
		horasExtraJpaRepository.flush();
		
		enviarAlertaMailCambioEstadoHorasExtras(entity, "Rechazado");
		
		notificacion.setCodigo(1L);
		notificacion.setSeverity("success");
		notificacion.setSummary("Runakuna Success");
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
				return horaExtraRepository.busquedaRapidaHorasExtrasEmpleado(quickFilter);
	}
	
	@Override
	public NotificacionViewModel registrarRecuperarHoras(HorasExtraViewModel manteinanceViewModel) {
		NotificacionViewModel notificacion = new NotificacionViewModel();
		
		try{
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
			//entity.setHoras(Double.parseDouble(manteinanceViewModel.getHoras()));
			
			horasExtraJpaRepository.save(entity);
			horasExtraJpaRepository.flush();
			
			notificacion.setCodigo(1L);
			notificacion.setSeverity("success");
			notificacion.setSummary("Runakuna Success");
			notificacion.setDetail("Se registro correctamente");
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
	public List<HorasExtraViewModel> verHorasExtras(PeriodoEmpleadoViewModel periodoEmpleado){
		return horaExtraRepository.verHorasExtras(periodoEmpleado);
	}

	@Override
	public HorasExtraViewModel obtenerHorasSemanalesPendientes(Long idEmpleado) {
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

	private void enviarAlertaMailSolicitarHorasExtra(HorasExtra entity){
		
		try{
			AlertaViewModel alertaDto=alertaService.obtenerAlerta("ALERTA08");
			
			GeneraMsjeAlertaEmpleadoViewModel msjeAlertaDto=null;
			List<String> correos=new ArrayList<String>();
			correos.add(entity.getAtendidoPor().getEmailInterno());
			
			String nombreEmpleado = entity.getEmpleado().getApellidoPaterno()+" "+entity.getEmpleado().getApellidoMaterno()+", "+entity.getEmpleado().getNombre();
			
			SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
			
			AlertaSubscriptorViewModel jefeInmediato = new AlertaSubscriptorViewModel();
			
			jefeInmediato.setIdEmpleado(entity.getAtendidoPor().getIdEmpleado());
			
			alertaDto.getSubscriptores().add(jefeInmediato);
			
			for(AlertaSubscriptorViewModel alertaSubscriptorDto:alertaDto.getSubscriptores()) {
				Empleado empl=empleadoJpaRepository.findOne(alertaSubscriptorDto.getIdEmpleado());
				correos.add(empl.getEmailInterno());
				msjeAlertaDto=new GeneraMsjeAlertaEmpleadoViewModel();
				msjeAlertaDto.setIdAlerta(alertaDto.getIdAlerta());
				msjeAlertaDto.setIdEmpleado(empl.getIdEmpleado());
				Map<String,String> parametrosMensaje=new HashMap<String,String>();
				parametrosMensaje.put("Empleado",nombreEmpleado);
				parametrosMensaje.put("Fecha",sdf.format(entity.getFecha()));
				parametrosMensaje.put("Hora Inicio",entity.getHoraInicioSolicitado());
				parametrosMensaje.put("Hora Fin",entity.getHoraSalidaSolicitado());
				parametrosMensaje.put("Jefe Inmediato",entity.getAtendidoPor().getApellidoPaterno()+" "+entity.getAtendidoPor().getApellidoMaterno()+", "+entity.getAtendidoPor().getNombre());
				msjeAlertaDto.setParametrosMsje(parametrosMensaje);
				alertaService.generarMensajeAlerta(msjeAlertaDto);
			}
			
			if(correos.size()>0) {
				
				String asunto=alertaDto.getAsunto().replace("[Empleado]", nombreEmpleado);
				String cuerpo=alertaDto.getCuerpo().replace("[Empleado]", nombreEmpleado);
				cuerpo=cuerpo.replace("[Fecha]", sdf.format(entity.getFecha()));
				cuerpo=cuerpo.replace("[Hora Inicio]", entity.getHoraInicioSolicitado());
				cuerpo=cuerpo.replace("[Hora Fin]", entity.getHoraSalidaSolicitado());
				cuerpo=cuerpo.replace("[Jefe Inmediato]", entity.getAtendidoPor().getApellidoPaterno()+" "+entity.getAtendidoPor().getApellidoMaterno()+", "+entity.getAtendidoPor().getNombre());
				mailService.sendEmail(asunto, cuerpo, correos.toArray(new String[correos.size()]));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void enviarAlertaMailCambioEstadoHorasExtras(HorasExtra entity, String estado){
		
		try{
			AlertaViewModel alertaDto=alertaService.obtenerAlerta("ALERTA11");
			GeneraMsjeAlertaEmpleadoViewModel msjeAlertaDto=new GeneraMsjeAlertaEmpleadoViewModel();
			msjeAlertaDto.setIdAlerta(alertaDto.getIdAlerta());
			msjeAlertaDto.setIdEmpleado(entity.getEmpleado().getIdEmpleado());
			
			Map<String,String> parametrosMensaje=new HashMap<String,String>();
			SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
			parametrosMensaje.put("Estado",estado);
			parametrosMensaje.put("Fecha",sdf.format(entity.getFecha()));
			parametrosMensaje.put("Hora Inicio",entity.getHoraInicioSolicitado());
			parametrosMensaje.put("Hora Fin",entity.getHoraSalidaSolicitado());
			msjeAlertaDto.setParametrosMsje(parametrosMensaje);
			alertaService.generarMensajeAlerta(msjeAlertaDto);
			
			String cuerpo=alertaDto.getCuerpo().replace("[Estado]", estado);
			cuerpo=cuerpo.replace("[Fecha]", sdf.format(entity.getFecha()));
			cuerpo=cuerpo.replace("[Hora Inicio]", entity.getHoraInicioSolicitado());
			cuerpo=cuerpo.replace("[Hora Fin]", entity.getHoraSalidaSolicitado());
			
			String[] correos=new String[1];
			correos[0]=entity.getEmpleado().getEmailInterno();
			mailService.sendEmail(alertaDto.getAsunto(), cuerpo, correos);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	public HorasExtraViewModel obtenerHorasSemanalPendiente(HorasExtraViewModel filter) {
		
		EmpleadoViewModel empleadoDto=new EmpleadoViewModel();
		HorasExtraViewModel horasExtraViewModel=new HorasExtraViewModel();
		empleadoDto.setIdEmpleado(filter.getIdEmpleado());
		
		int diaSemana=DateUtil.getDayOfWeek(filter.getFecha());
		
		HorarioEmpleado horarioEmpleado = horarioEmpleadoJpaRepository.findOneByIdEmpleadoAndDate(filter.getFecha(),filter.getIdEmpleado());
		
		String dia= StringUtil.obtenerCodigoDia(diaSemana+1);
		
		HorarioEmpleadoDiaViewModel horarioDiaViewModel=empleadoJdbcRepository.obtenerHorarioDiaPorDia(horarioEmpleado.getIdHorarioEmpleado(), dia);
		
		BigDecimal horasSemanalesTeoricas=empleadoJdbcRepository.obtenerHorasSemanalesLunesViernes(horarioEmpleado.getIdHorarioEmpleado());
		BigDecimal horasSemanales= new BigDecimal(horarioEmpleado.getHorasSemanal());
		horasExtraViewModel.setHoraSalidaHorario(horarioDiaViewModel.getSalida());
		BigDecimal horasExtraTrabajadas=new BigDecimal(0);
		if(diaSemana==1){
			horasExtraViewModel.setHorasSemanalesPendientes(horasSemanales.subtract(horasSemanalesTeoricas).setScale(2, RoundingMode.HALF_UP).toPlainString());
		} else {
			
			for(int i=1;i<diaSemana;i++) {
				
				Date fechaCalculada=DateUtil.addDays(filter.getFecha(), -1*i);
				
				List<HorasExtra> horasExtraAll = horasExtraJpaRepository.findAllByIdEmpleadoAndFecha(fechaCalculada, filter.getIdEmpleado());
				
				if(horasExtraAll!= null && horasExtraAll.size() > 0){
				
					for (HorasExtra horasExtra : horasExtraAll) {
						horasExtraTrabajadas=horasExtraTrabajadas.add(horasExtra.getHorasSolicitadas());
					}
				}
			}
			horasExtraTrabajadas=horasExtraTrabajadas.setScale(2, RoundingMode.HALF_UP);
			horasExtraViewModel.setHorasSemanalesPendientes(horasSemanales.subtract(horasSemanalesTeoricas).subtract(horasExtraTrabajadas).
					setScale(2, RoundingMode.HALF_UP).toPlainString());
		}
		
		return horasExtraViewModel;
	}
	
	private void recalcularHorasExtrasPendientes(HorasExtra horasExtra){
		
		try{
			int diaSemana=DateUtil.getDayOfWeek(horasExtra.getFecha());
			
			if(diaSemana == 0){
				diaSemana = 7;
			}
			
			Date fechaInicio = DateUtil.addDays(horasExtra.getFecha(), -1*(diaSemana-1));
			
			Date fechaFin = DateUtil.addDays(horasExtra.getFecha(), (7-diaSemana));
			
			List<HorasExtra> horasExtrasPendientes = horasExtraJpaRepository.findAllByIdEmpleadoAndRangeFecha(fechaInicio, fechaFin, horasExtra.getEmpleado().getIdEmpleado());
			if(horasExtrasPendientes != null && horasExtrasPendientes.size() > 0){
				HorasExtraViewModel template = new HorasExtraViewModel();
				template.setIdEmpleado(horasExtrasPendientes.get(0).getEmpleado().getIdEmpleado());
				template.setFecha(horasExtrasPendientes.get(0).getFecha());
				
				HorasExtraViewModel result = obtenerHorasSemanalPendiente(template);
				 
				for(HorasExtra horaEx : horasExtrasPendientes){
					horaEx.setHorasNoCompensables(new BigDecimal(result.getHorasSemanalesPendientes()).setScale(2, RoundingMode.HALF_UP));
					
					BigDecimal horaTotalExtra = horaEx.getHorasSolicitadas().subtract(horaEx.getHorasNoCompensables()).setScale(2, RoundingMode.HALF_UP);

					if(horaTotalExtra.doubleValue() <= 0){
						horaTotalExtra = new BigDecimal(0);
					}
					
					horaEx.setHorasExtra(horaTotalExtra);
				}
				
				horasExtraJpaRepository.save(horasExtrasPendientes);
				horasExtraJpaRepository.flush();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
