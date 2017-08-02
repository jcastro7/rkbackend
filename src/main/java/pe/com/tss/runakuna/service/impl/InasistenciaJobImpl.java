package pe.com.tss.runakuna.service.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import pe.com.tss.runakuna.domain.model.entities.DepartamentoArea;
import pe.com.tss.runakuna.domain.model.entities.Empleado;
import pe.com.tss.runakuna.domain.model.entities.EmpleadoCompensacion;
import pe.com.tss.runakuna.domain.model.entities.HistorialLaboral;
import pe.com.tss.runakuna.domain.model.entities.HorarioEmpleado;
import pe.com.tss.runakuna.domain.model.entities.HorarioEmpleadoDia;
import pe.com.tss.runakuna.domain.model.entities.HorasExtra;
import pe.com.tss.runakuna.domain.model.entities.Licencia;
import pe.com.tss.runakuna.domain.model.entities.Marcacion;
import pe.com.tss.runakuna.domain.model.entities.PeriodoEmpleado;
import pe.com.tss.runakuna.domain.model.entities.PermisoEmpleado;
import pe.com.tss.runakuna.domain.model.entities.Proyecto;
import pe.com.tss.runakuna.domain.model.entities.RegistroMarcacionEmpleado;
import pe.com.tss.runakuna.domain.model.entities.UnidadDeNegocio;
import pe.com.tss.runakuna.domain.model.entities.Vacacion;
import pe.com.tss.runakuna.domain.model.repository.jdbc.HistoriaLaboralRepository;
import pe.com.tss.runakuna.domain.model.repository.jdbc.MarcacionRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.CompensacionJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.DepartamentoAreaJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.EmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.HistorialLaboralJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.HorarioEmpleadoDiaJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.HorarioEmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.HorasExtraJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.LicenciaEmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.MarcacionJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.PeriodoEmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.PermisoEmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.ProyectoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.RegistroMarcacionEmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.UnidaDeNegocioJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.VacacionJpaRepository;
import pe.com.tss.runakuna.view.model.AlertaSubscriptorViewModel;
import pe.com.tss.runakuna.view.model.AlertaViewModel;
import pe.com.tss.runakuna.view.model.EmpleadoViewModel;
import pe.com.tss.runakuna.view.model.GeneraMsjeAlertaEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.HistoriaLaboralViewModel;
import pe.com.tss.runakuna.view.model.JobEjecucionResultViewModel;
import pe.com.tss.runakuna.view.model.JobEjecucionViewModel;
import pe.com.tss.runakuna.view.model.MarcacionViewModel;
import pe.com.tss.runakuna.view.model.RegistroMarcacionEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.RegistroMarcacionViewModel;
import pe.com.tss.runakuna.service.AlertaService;
import pe.com.tss.runakuna.service.JobEjecucionService;
import pe.com.tss.runakuna.service.MailService;
import pe.com.tss.runakuna.util.DateUtil;
import pe.com.tss.runakuna.util.StringUtil;

@Service
public class InasistenciaJobImpl{
			
	@Autowired
	MarcacionJpaRepository marcacionJpaRepository;
	
	@Autowired
	MarcacionRepository marcacionJdbcRepository;
	
	@Autowired
	ProyectoJpaRepository proyectoJpaRepository;
	
	@Autowired
	DepartamentoAreaJpaRepository departamentoAreaJpaRepository;
	
	@Autowired
	UnidaDeNegocioJpaRepository unidadDeNegocioJpaRepository;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	AlertaService alertaService;
	
	@Autowired
	JobEjecucionService jobEjecucionService;
	
	@Autowired
	Mapper mapper;
	
	
	@Value("${spring.cronFrecuency.JOB_INASISTENCIA}")
	String JOB_INASISTENCIA;
	
	@Value("${spring.production.active}")
    private boolean productionActive;
	

	//@Scheduled(cron="${spring.cronFrecuency.JOB_INASISTENCIA}")
	public void enviarAlertasInasistenciasEmpleado(){
		GeneraMsjeAlertaEmpleadoViewModel msjeAlertaDto;
		Map<String,String> parametrosMensaje;
		Date inicio=new Date();
		Date fin=null;
		JobEjecucionViewModel jobEjecucionViewModel=null;
		CronExpression ce=null;
		try {
			ce = new CronExpression(JOB_INASISTENCIA);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date nextExecution= ce.getNextValidTimeAfter(inicio);
		JobEjecucionResultViewModel result=jobEjecucionService.obtenerEjecucionJobPendiente("JOB_INASISTENCIA");
		if(result==null ||result.getIdJobEjecucion()==null){
			JobEjecucionViewModel je=new JobEjecucionViewModel();
			je.setEjecutado(0);
			je.setEstado("N");
			je.setFechaProgramado(nextExecution);
			jobEjecucionService.registarJobEjecucionEnBlanco(je, "JOB_INASISTENCIA");
			result=jobEjecucionService.obtenerEjecucionJobPendiente("JOB_INASISTENCIA");
		}
				
		try{
			if(productionActive){
						
				Date fechaActual = new Date();
				String fechaCadena = DateUtil.fmtDt(fechaActual);
				
				AlertaViewModel alerta03=alertaService.obtenerAlerta("Alerta03");
				
				if(alerta03 != null){
					if(alerta03.getTipoNotificacion().equals("I")){
						
						List<Marcacion> marcaciones = marcacionJpaRepository.getMarcacionInasistentesByDate(DateUtil.formatoFechaArchivoMarcacion(fechaCadena));
						
						if(marcaciones!= null && marcaciones.size() > 0){
						
							for (Marcacion marcacion : marcaciones) {
								
								if(alerta03.getAlertaDashboard().intValue() == 1){
									msjeAlertaDto=new GeneraMsjeAlertaEmpleadoViewModel();
									msjeAlertaDto.setIdAlerta(alerta03.getIdAlerta());
									msjeAlertaDto.setIdEmpleado(marcacion.getEmpleado().getIdEmpleado());
									parametrosMensaje=new HashMap<String,String>();
									parametrosMensaje.put("Fecha", fechaCadena);
									msjeAlertaDto.setParametrosMsje(parametrosMensaje);
									alertaService.generarMensajeAlerta(msjeAlertaDto);
								}
								
								if(alerta03.getCorreoElectronico().intValue() == 1){
									EmpleadoViewModel empleadoViewModel=new EmpleadoViewModel();
									empleadoViewModel.setIdEmpleado(marcacion.getEmpleado().getIdEmpleado());
									
									String asunto=alerta03.getAsunto().replace("[Fecha]", fechaCadena);
									String cuerpo=alerta03.getCuerpo().replace("[Fecha]", fechaCadena);
									
									List<String> correos=new ArrayList<String>();
									String correoEmpleado = marcacion.getEmpleado().getEmailInterno();
									correos.add(correoEmpleado);

									mailService.sendEmail(asunto, cuerpo, correos.toArray(new String[correos.size()]));
								}
							}
						}
					}
				}
				
				AlertaViewModel alerta04=alertaService.obtenerAlerta("Alerta04");
				
				if(alerta04 != null){
					if(alerta04.getTipoNotificacion().equals("G")){
						
						if(alerta04.getCorreoElectronico().intValue() == 1){
							
							if(alerta04.getJefeProyecto() != null && alerta04.getJefeProyecto().intValue() == 1){
								
								List<Proyecto> proyectos = proyectoJpaRepository.getAllProyectosActivos();
								
								if(proyectos != null && proyectos.size()>0){
									
									for (Proyecto proyecto : proyectos) {
										List<MarcacionViewModel> marcaciones = marcacionJdbcRepository.getMarcacionesByIdProyecto(DateUtil.formatoFechaArchivoMarcacion(fechaCadena), proyecto.getIdProyecto());
									
										
										StringBuilder sb=new StringBuilder("");
										sb.append("<table>");
										for(MarcacionViewModel marcacionViewModel : marcaciones){
											sb.append("<tr>");
											sb.append("<td>").append(marcacionViewModel.getNombreCompletoEmpleado()).append("</td>");
											sb.append("</tr>");
										}
										sb.append("</table>");
										
										String asunto=alerta04.getAsunto().replace("[Fecha]", fechaCadena);
										asunto=asunto.replace("[Proyecto, Departamento, Unidad de Negocio o Empresa]", proyecto.getNombre());
										
										String cuerpo=alerta04.getCuerpo().replace("[Fecha]", fechaCadena);
										cuerpo=cuerpo.replace("[ListaEmpleados]", sb.toString());
										
										List<String> correos=new ArrayList<String>();
										String emailJefe = proyecto.getJefe().getEmailInterno();
										correos.add(emailJefe);
										
										for(AlertaSubscriptorViewModel alertaSubscriptorDto:alerta04.getSubscriptores()) {
											String emailInterno=alertaSubscriptorDto.getEmailInternoEmpleado();
											correos.add(emailInterno);
										}
										mailService.sendEmail(asunto, cuerpo, correos.toArray(new String[correos.size()]));
									}
								}
							}
							
							if(alerta04.getJefeDepartamento() != null && alerta04.getJefeDepartamento().intValue() == 1){
								
								List<DepartamentoArea> departamentos = departamentoAreaJpaRepository.getAllDepartamentosActivos();
								
								if(departamentos != null && departamentos.size()>0){
									
									for (DepartamentoArea departamentoArea : departamentos) {
										List<MarcacionViewModel> marcaciones = marcacionJdbcRepository.getMarcacionesByIdDepartamentoArea(DateUtil.formatoFechaArchivoMarcacion(fechaCadena), departamentoArea.getIdDepartamentoArea());
									
										
										StringBuilder sb=new StringBuilder("");
										sb.append("<table>");
										for(MarcacionViewModel marcacionViewModel : marcaciones){
											sb.append("<tr>");
											sb.append("<td>").append(marcacionViewModel.getNombreCompletoEmpleado()).append("</td>");
											sb.append("</tr>");
										}
										sb.append("</table>");
										
										String asunto=alerta04.getAsunto().replace("[Fecha]", fechaCadena);
										asunto=asunto.replace("[Proyecto, Departamento, Unidad de Negocio o Empresa]", departamentoArea.getNombre());
										
										String cuerpo=alerta04.getCuerpo().replace("[Fecha]", fechaCadena);
										cuerpo=cuerpo.replace("[ListaEmpleados]", sb.toString());
										
										List<String> correos=new ArrayList<String>();
										String emailJefe = departamentoArea.getEmpleadoJefe().getEmailInterno();
										correos.add(emailJefe);
										
										for(AlertaSubscriptorViewModel alertaSubscriptorDto:alerta04.getSubscriptores()) {
											String emailInterno=alertaSubscriptorDto.getEmailInternoEmpleado();
											correos.add(emailInterno);
										}
										mailService.sendEmail(asunto, cuerpo, correos.toArray(new String[correos.size()]));
									}
								}
							}
							
							if(alerta04.getJefeUnidad() != null && alerta04.getJefeUnidad().intValue() == 1){
								
								List<UnidadDeNegocio> unidades = unidadDeNegocioJpaRepository.getAllUnidadDeNegocioActivos();
								
								if(unidades != null && unidades.size()>0){
									
									for (UnidadDeNegocio unidad : unidades) {
										List<MarcacionViewModel> marcaciones = marcacionJdbcRepository.getMarcacionesByIdUnidadDeNegocio(DateUtil.formatoFechaArchivoMarcacion(fechaCadena), unidad.getIdUnidadDeNegocio());
									
										
										StringBuilder sb=new StringBuilder("");
										sb.append("<table>");
										for(MarcacionViewModel marcacionViewModel : marcaciones){
											sb.append("<tr>");
											sb.append("<td>").append(marcacionViewModel.getNombreCompletoEmpleado()).append("</td>");
											sb.append("</tr>");
										}
										sb.append("</table>");
										
										String asunto=alerta04.getAsunto().replace("[Fecha]", fechaCadena);
										asunto=asunto.replace("[Proyecto, Departamento, Unidad de Negocio o Empresa]", unidad.getNombre());
										
										String cuerpo=alerta04.getCuerpo().replace("[Fecha]", fechaCadena);
										cuerpo=cuerpo.replace("[ListaEmpleados]", sb.toString());
										
										List<String> correos=new ArrayList<String>();
										String emailJefe = unidad.getEmpleadoJefe().getEmailInterno();
										correos.add(emailJefe);
										
										for(AlertaSubscriptorViewModel alertaSubscriptorDto:alerta04.getSubscriptores()) {
											String emailInterno=alertaSubscriptorDto.getEmailInternoEmpleado();
											correos.add(emailInterno);
										}
										mailService.sendEmail(asunto, cuerpo, correos.toArray(new String[correos.size()]));
									}
								}
							}
						}
					}
				}
						
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
		}catch (Exception e) {
			fin=new Date();
			jobEjecucionViewModel=new JobEjecucionViewModel();
			jobEjecucionViewModel.setIdJobEjecucion(result.getIdJobEjecucion());
			jobEjecucionViewModel.setFechaInicio(inicio);
			jobEjecucionViewModel.setFechaFin(fin);
			jobEjecucionViewModel.setEstado("E");
			jobEjecucionViewModel.setResultadoMensaje(e.getMessage());
			jobEjecucionViewModel.setEjecutado(1);
			jobEjecucionViewModel.setFechaProgramado(nextExecution);
			
			
			e.printStackTrace();
		}
		jobEjecucionService.actualizarJobEjecucion(jobEjecucionViewModel, "JOB_INASISTENCIA");
		JobEjecucionViewModel next=new JobEjecucionViewModel();
		next.setEjecutado(0);
		next.setEstado("N");
		Date nextExecution2= ce.getNextValidTimeAfter(nextExecution);
		next.setFechaProgramado(nextExecution2);
		jobEjecucionService.registarJobEjecucionEnBlanco(next, "JOB_INASISTENCIA");
	}
		
}
