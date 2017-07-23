package pe.com.tss.runakuna.service.impl;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.tss.runakuna.domain.model.entities.ConfiguracionSistema;
import pe.com.tss.runakuna.domain.model.entities.Empleado;
import pe.com.tss.runakuna.domain.model.entities.PeriodoEmpleado;
import pe.com.tss.runakuna.domain.model.repository.jpa.ConfiguracionSistemaJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.EmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.HorarioEmpleadoDiaJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.HorarioEmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.MarcacionJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.PeriodoEmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.PermisoEmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.RegistroMarcacionEmpleadoJpaRepository;
import pe.com.tss.runakuna.service.AlertaService;
import pe.com.tss.runakuna.service.JobEjecucionService;
import pe.com.tss.runakuna.service.MailService;
import pe.com.tss.runakuna.service.PeriodoEmpleadoService;
import pe.com.tss.runakuna.view.model.AlertaSubscriptorViewModel;
import pe.com.tss.runakuna.view.model.AlertaViewModel;
import pe.com.tss.runakuna.view.model.EmpleadoViewModel;
import pe.com.tss.runakuna.view.model.JobEjecucionResultViewModel;
import pe.com.tss.runakuna.view.model.JobEjecucionViewModel;
import pe.com.tss.runakuna.view.model.NotificacionEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.PeriodoEmpleadoFilterViewModel;
import pe.com.tss.runakuna.view.model.PeriodoEmpleadoResultViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PeriodoJobImpl{


	private static final Logger LOGGER  = LoggerFactory.getLogger(PeriodoJobImpl.class);
	
	@Autowired
	EmpleadoJpaRepository empleadoJpaRepository;
	
	@Autowired
	RegistroMarcacionEmpleadoJpaRepository registroMarcacionEmpleadoJpaRepository;
	
	@Autowired
	MarcacionJpaRepository marcacionJpaRepository;
	
	@Autowired
	HorarioEmpleadoDiaJpaRepository horarioEmpleadoDiaJpaRepository;
	
	@Autowired
	HorarioEmpleadoJpaRepository horarioEmpleadoJpaRepository;
	
	@Autowired
	PermisoEmpleadoJpaRepository permisoEmpleadoJpaRepository;
	
	@Autowired
	ConfiguracionSistemaJpaRepository configuracionSistemaJpaRepository;
	
	@Autowired
	PeriodoEmpleadoJpaRepository periodoEmpleadoJpaRepository;
	
	@Autowired
	Mapper mapper;
	
	@Autowired
	PeriodoEmpleadoService periodoEmpleadoService;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	AlertaService alertaService;
	
	@Autowired
	JobEjecucionService jobEjecucionService;
	
	@Value("${spring.cronFrecuency.JOB_CRE_PERI}")
    String cron_JOB_CRE_PERI;
	
	@Value("${spring.cronFrecuency.JOB_ACT_VACA}")
    String cron_JOB_ACT_VACA;

	
	//@Scheduled(cron="${spring.cronFrecuency.JOB_CRE_PERI}")
	@Transactional
	public void crearNuevoPeriodoEmpleado() {
		EmpleadoViewModel emp=null;
		List<NotificacionEmpleadoViewModel> empleadosPeriodoNuevo=new ArrayList<NotificacionEmpleadoViewModel>();
		Date inicio=new Date();
		Date fin=null;
		JobEjecucionViewModel jobEjecucionViewModel=null;
		CronExpression ce=null;
		try {
			ce = new CronExpression(cron_JOB_CRE_PERI);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date nextExecution= ce.getNextValidTimeAfter(inicio);
		JobEjecucionResultViewModel result=jobEjecucionService.obtenerEjecucionJobPendiente("JOB_CRE_PERI");
		if(result==null ||result.getIdJobEjecucion()==null){
			JobEjecucionViewModel je=new JobEjecucionViewModel();
			je.setEjecutado(0);
			je.setEstado("N");
			je.setFechaProgramado(nextExecution);
			jobEjecucionService.registarJobEjecucionEnBlanco(je, "JOB_CRE_PERI");
			result=jobEjecucionService.obtenerEjecucionJobPendiente("JOB_CRE_PERI");
		}
		try{
			List<Empleado> empleados=empleadoJpaRepository.buscarEmpleadosPorEstado("A");
			AlertaViewModel alertaDto=alertaService.obtenerAlerta("Alerta13");
			for(Empleado empleado :empleados) {
				emp=new EmpleadoViewModel();
				emp.setIdEmpleado(empleado.getIdEmpleado());
				NotificacionEmpleadoViewModel notificacion=periodoEmpleadoService.registrarPeriodoEmpleado(emp);
				if(notificacion.getCodigo().longValue()==1L){
					empleadosPeriodoNuevo.add(notificacion);
				}
			}
			
			if(empleadosPeriodoNuevo.size()>0) {
				String[] correos=new String[alertaDto.getSubscriptores().size()];
				int i=0;
				for(AlertaSubscriptorViewModel alertaSubscriptorDto:alertaDto.getSubscriptores()) {
					Empleado empl=empleadoJpaRepository.findOne(alertaSubscriptorDto.getIdEmpleado());
					correos[i]=empl.getEmailInterno();
					i++;
				}
				StringBuilder sb=new StringBuilder("");
				sb.append("<table>");
				for(NotificacionEmpleadoViewModel n:empleadosPeriodoNuevo){
					sb.append("<tr>");
					sb.append("<td>").append(n.getEmpleadoDto().getNombre()+" "+n.getEmpleadoDto().getApellidoPaterno()+" "+n.getEmpleadoDto().getApellidoMaterno()).append("</td>");
					sb.append("<td>").append(n.getEmpleadoDto().getEmailInterno()).append("</td>");
					sb.append("<td>").append(n.getPeriodoEmpleadoDto().getPeriodo()).append("</td>");
					sb.append("</tr>");
				}
				sb.append("</table>");
				String cuerpo=alertaDto.getCuerpo().replace("[ListaEmpleados]", sb.toString());
				mailService.sendEmail(alertaDto.getAsunto(), cuerpo, correos);
			}
			fin=new Date();
			jobEjecucionViewModel=new JobEjecucionViewModel();
			jobEjecucionViewModel.setIdJobEjecucion(result.getIdJobEjecucion());
			jobEjecucionViewModel.setFechaInicio(inicio);
			jobEjecucionViewModel.setFechaFin(fin);
			jobEjecucionViewModel.setEstado("F");
			jobEjecucionViewModel.setResultadoMensaje("OK");
			jobEjecucionViewModel.setEjecutado(1);
			jobEjecucionViewModel.setFechaProgramado(nextExecution);
			
		} catch (Exception e) {
			fin=new Date();
			jobEjecucionViewModel=new JobEjecucionViewModel();
			jobEjecucionViewModel.setIdJobEjecucion(result.getIdJobEjecucion());
			jobEjecucionViewModel.setFechaInicio(inicio);
			jobEjecucionViewModel.setFechaFin(fin);
			jobEjecucionViewModel.setEstado("E");
			jobEjecucionViewModel.setResultadoMensaje(e.getMessage());
			jobEjecucionViewModel.setEjecutado(1);
			jobEjecucionViewModel.setFechaProgramado(nextExecution);
		}
		jobEjecucionService.actualizarJobEjecucion(jobEjecucionViewModel, "JOB_CRE_PERI");
		JobEjecucionViewModel next=new JobEjecucionViewModel();
		next.setEjecutado(0);
		next.setEstado("N");
		Date nextExecution2= ce.getNextValidTimeAfter(nextExecution);
		next.setFechaProgramado(nextExecution2);
		jobEjecucionService.registarJobEjecucionEnBlanco(next, "JOB_CRE_PERI");
	}
	
	//@Scheduled(cron="${spring.cronFrecuency.JOB_ACT_VACA}")
	@Transactional
	public void actualizaVacacionesAcumuladasPeriodo() {
		List<Empleado> empleados=empleadoJpaRepository.buscarEmpleadosPorEstado("A");
		AlertaViewModel alertaDto=alertaService.obtenerAlerta("Alerta19");
		PeriodoEmpleadoFilterViewModel busquedaPermisoEmpleadoDto= null; 
		List<PeriodoEmpleadoResultViewModel> listaPeriodos=null;
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		Date inicio=new Date();
		Date fin=null;
		JobEjecucionViewModel jobEjecucionViewModel=null;
		List<Empleado> empleadosActualizados=new  ArrayList<Empleado>();
		CronExpression ce=null;
		try {
			ce = new CronExpression(cron_JOB_ACT_VACA);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date nextExecution= ce.getNextValidTimeAfter(inicio);
		JobEjecucionResultViewModel result=jobEjecucionService.obtenerEjecucionJobPendiente("JOB_ACT_VACA");
		if(result==null ||result.getIdJobEjecucion()==null){
			JobEjecucionViewModel je=new JobEjecucionViewModel();
			je.setEjecutado(0);
			je.setEstado("N");
			je.setFechaProgramado(nextExecution);
			jobEjecucionService.registarJobEjecucionEnBlanco(je, "JOB_ACT_VACA");
			result=jobEjecucionService.obtenerEjecucionJobPendiente("JOB_ACT_VACA");
		}
		try {
			String hoyString=sdf.format(new Date());
			Date hoy=sdf.parse(hoyString);
			
			ConfiguracionSistema cadenaMaxVacacion = configuracionSistemaJpaRepository.obtenerConfiguracionSistemaPorCodigo("GestionDeTiempo.Vacaciones");
			
			Integer cantidadMaxVacacion = Integer.parseInt(cadenaMaxVacacion.getValor());
			
			PeriodoEmpleado periodoEmpleadoNuevo=new PeriodoEmpleado();
			for(Empleado empleado:empleados) {
				busquedaPermisoEmpleadoDto= new PeriodoEmpleadoFilterViewModel();
				busquedaPermisoEmpleadoDto.setIdEmpleado(empleado.getIdEmpleado());
				listaPeriodos=periodoEmpleadoService.busquedaPeriodoEmpleado(busquedaPermisoEmpleadoDto);
				for(PeriodoEmpleadoResultViewModel periodoEmpleadoResultViewModel:listaPeriodos){
					Date fechaFin=periodoEmpleadoResultViewModel.getFechaFin();
					fechaFin=sdf.parse(sdf.format(fechaFin));
					if(!fechaFin.before(hoy) && !fechaFin.after(hoy)){
						PeriodoEmpleado periodoEmpleadoActual=periodoEmpleadoJpaRepository.findOne(periodoEmpleadoResultViewModel.getIdPeriodoEmpleado());
						int vacacionesAcumuladas=periodoEmpleadoActual.getDiasVacacionesAcumulado();
						List<PeriodoEmpleado> lista=periodoEmpleadoJpaRepository.obtenerUltimoPeriodoEmpleadoVigente(empleado.getIdEmpleado());
						if(lista!=null && !lista.isEmpty()){
							periodoEmpleadoNuevo=lista.get(0);
						}
						periodoEmpleadoActual.setDiasVacacionesAcumulado(0);
						periodoEmpleadoNuevo.setDiasVacacionesAcumulado(cantidadMaxVacacion+vacacionesAcumuladas);
						periodoEmpleadoJpaRepository.save(periodoEmpleadoActual);
						periodoEmpleadoJpaRepository.flush();
						periodoEmpleadoJpaRepository.save(periodoEmpleadoNuevo);
						periodoEmpleadoJpaRepository.flush();
						empleadosActualizados.add(periodoEmpleadoActual.getEmpleado());
					}
				}
			}
			
			if(empleadosActualizados.size()>0) {
				String[] correos=new String[alertaDto.getSubscriptores().size()];
				int i=0;
				for(AlertaSubscriptorViewModel alertaSubscriptorDto:alertaDto.getSubscriptores()) {
					Empleado empl=empleadoJpaRepository.findOne(alertaSubscriptorDto.getIdEmpleado());
					correos[i]=empl.getEmailInterno();
					i++;
				}
				StringBuilder sb=new StringBuilder("");
				sb.append("<table>");
				for(Empleado n:empleadosActualizados){
					sb.append("<tr>");
					sb.append("<td>").append(n.getNombre()+" "+n.getApellidoPaterno()+" "+n.getApellidoMaterno()).append("</td>");
					sb.append("<td>").append(n.getEmailInterno()).append("</td>");
					//sb.append("<td>").append(n.getPeriodoEmpleadoDto().getPeriodo()).append("</td>");
					sb.append("</tr>");
				}
				sb.append("</table>");
				String cuerpo=alertaDto.getCuerpo().replace("[ListaEmpleados]", sb.toString());
				mailService.sendEmail(alertaDto.getAsunto(), cuerpo, correos);
			}
			
			fin=new Date();
			jobEjecucionViewModel=new JobEjecucionViewModel();
			jobEjecucionViewModel.setIdJobEjecucion(result.getIdJobEjecucion());
			jobEjecucionViewModel.setFechaInicio(inicio);
			jobEjecucionViewModel.setFechaFin(fin);
			jobEjecucionViewModel.setEstado("F");
			jobEjecucionViewModel.setResultadoMensaje("OK");
			jobEjecucionViewModel.setEjecutado(1);
			jobEjecucionViewModel.setFechaProgramado(nextExecution);
		} catch (Exception e) {
			fin=new Date();
			jobEjecucionViewModel=new JobEjecucionViewModel();
			jobEjecucionViewModel.setIdJobEjecucion(result.getIdJobEjecucion());
			jobEjecucionViewModel.setFechaInicio(inicio);
			jobEjecucionViewModel.setFechaFin(fin);
			jobEjecucionViewModel.setEstado("E");
			jobEjecucionViewModel.setResultadoMensaje(e.getMessage());
			jobEjecucionViewModel.setEjecutado(1);
			jobEjecucionViewModel.setFechaProgramado(nextExecution);
			LOGGER.info(e.getMessage(), e);

		}
		jobEjecucionService.actualizarJobEjecucion(jobEjecucionViewModel, "JOB_ACT_VACA");
		JobEjecucionViewModel next=new JobEjecucionViewModel();
		next.setEjecutado(0);
		next.setEstado("N");
		Date nextExecution2= ce.getNextValidTimeAfter(nextExecution);
		next.setFechaProgramado(nextExecution2);
		jobEjecucionService.registarJobEjecucionEnBlanco(next, "JOB_ACT_VACA");	
	}
	
	
}
