package pe.com.tss.runakuna.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import pe.com.tss.runakuna.domain.model.entities.DepartamentoArea;
import pe.com.tss.runakuna.domain.model.entities.Proyecto;
import pe.com.tss.runakuna.domain.model.entities.UnidadDeNegocio;
import pe.com.tss.runakuna.domain.model.repository.jdbc.ConfiguracionSistemaRepository;
import pe.com.tss.runakuna.domain.model.repository.jdbc.EmpleadoJdbcRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.ProyectoJpaRepository;
import pe.com.tss.runakuna.service.AlertaService;
import pe.com.tss.runakuna.service.JobEjecucionService;
import pe.com.tss.runakuna.service.MailService;
import pe.com.tss.runakuna.util.DateUtil;
import pe.com.tss.runakuna.view.model.AlertaSubscriptorViewModel;
import pe.com.tss.runakuna.view.model.AlertaViewModel;
import pe.com.tss.runakuna.view.model.ConfiguracionSistemaFilterViewModel;
import pe.com.tss.runakuna.view.model.ConfiguracionSistemaResultViewModel;
import pe.com.tss.runakuna.view.model.EmpleadoResultViewModel;
import pe.com.tss.runakuna.view.model.JobEjecucionResultViewModel;
import pe.com.tss.runakuna.view.model.JobEjecucionViewModel;
import pe.com.tss.runakuna.view.model.MarcacionViewModel;

@Service
public class AlertaJobImpl {
	
	@Autowired
	MailService mailService;
	
	@Autowired
	AlertaService alertaService;
	
	@Autowired
	JobEjecucionService jobEjecucionService;
	
	@Autowired
	ProyectoJpaRepository proyectoJpaRepository;
	
	@Autowired
	EmpleadoJdbcRepository empleadoJdbcRepository;
	
	@Autowired
	ConfiguracionSistemaRepository configuracionSistemaRepository;
	
	@Value("${spring.cronFrecuency.JOB_ANTIGUEDAD_EMPLE}")
	String JOB_ANTIGUEDAD_EMPLE;
	
	@Value("${spring.production.active}")
    private boolean productionActive;
	
	//@Scheduled(cron="${spring.cronFrecuency.JOB_ANTIGUEDAD_EMPLE}")
	public void tardanzaProyectoDepartamentoUnidadNegocioEmpresa(){
		
		Date inicio=new Date();
		Date fin=null;
		JobEjecucionViewModel jobEjecucionViewModel=null;
		CronExpression ce=null;
		try {
			ce = new CronExpression(JOB_ANTIGUEDAD_EMPLE);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Date nextExecution= ce.getNextValidTimeAfter(inicio);
		JobEjecucionResultViewModel result=jobEjecucionService.obtenerEjecucionJobPendiente("JOB_ANTIGUEDAD_EMPLE");
		if(result==null ||result.getIdJobEjecucion()==null){
			JobEjecucionViewModel je=new JobEjecucionViewModel();
			je.setEjecutado(0);
			je.setEstado("N");
			je.setFechaProgramado(nextExecution);
			jobEjecucionService.registarJobEjecucionEnBlanco(je, "JOB_ANTIGUEDAD_EMPLE");
			result=jobEjecucionService.obtenerEjecucionJobPendiente("JOB_ANTIGUEDAD_EMPLE");
		}
		try{
			if(productionActive){						
					
					String dateCadena = DateUtil.fmtDt(new Date());
					
					Date today =DateUtil.formatoFechaArchivoMarcacion(dateCadena);
					
					AlertaViewModel alertaDto27=alertaService.obtenerAlerta("Alerta27");
					
					ConfiguracionSistemaFilterViewModel filterConfiguracionSistema = new ConfiguracionSistemaFilterViewModel();
					filterConfiguracionSistema.setCodigo("Empleado.ContratoIndefinido");					
					ConfiguracionSistemaResultViewModel configSistema = configuracionSistemaRepository.obtenerConfiguracionesSistemaContratoIdenfinido(filterConfiguracionSistema);
					if(alertaDto27 != null){
						if(alertaDto27.getTipoNotificacion().equals("I")){
							if(alertaDto27.getCorreoElectronico().intValue() == 1){
								List<EmpleadoResultViewModel> alertaAntiguedadxMesEmpleado = empleadoJdbcRepository.busquedaAlertaxMesAntiguedadEmpleado(alertaDto27.getUmbralAdvertencia(),configSistema.getValor());
								
								if(alertaAntiguedadxMesEmpleado != null && alertaAntiguedadxMesEmpleado.size() > 0){
									StringBuilder sb=new StringBuilder("");
									sb.append("<hr>");
									sb.append("<table>");
									sb.append("<td>").append("Nombre Empleado").append("</td>");
									sb.append("<td>").append("Fecha Ingreso").append("</td>");
									sb.append("<td>").append("Mensaje").append("</td>");
									sb.append("</tr>");												
									for(EmpleadoResultViewModel n:alertaAntiguedadxMesEmpleado){
										sb.append("<tr>");
										sb.append("<td>").append(n.getNombre()+", "+n.getApellidoPaterno()+" "+n.getApellidoMaterno()).append("</td>");
										sb.append("<td>").append(n.getFechaIngreso()).append("</td>");
										sb.append("<td>").append("El empleado est\u00e1 a "+alertaDto27.getUmbralAdvertencia()+" d\u00edas de cumplir "+configSistema.getValor()+" a\u00f1os en la empresa").append("</td>");
										sb.append("</tr>");
									}
									sb.append("</table>");
									
									DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
									String asunto=alertaDto27.getAsunto().replace("[Fecha]", dateFormat.format(new Date()));
									
									String cuerpo=alertaDto27.getCuerpo().replace("[Fecha]", dateFormat.format(new Date()));
									cuerpo= cuerpo.replace("[ListaEmpleados]", sb.toString());
									cuerpo = cuerpo.replace("[Year]", configSistema.getValor());
									
									List<String> correos=new ArrayList<String>();
									
									for(AlertaSubscriptorViewModel alertaSubscriptorDto:alertaDto27.getSubscriptores()) {													
										String emailInterno=alertaSubscriptorDto.getEmailInternoEmpleado();
										correos.add(emailInterno);								
									}												
									
									mailService.sendEmail(asunto, cuerpo, correos.toArray(new String[correos.size()]));
								}
								
								List<EmpleadoResultViewModel> alertaAntiguedadxDiaEmpleado = empleadoJdbcRepository.busquedaAlertaxDiaAntiguedadEmpleado(alertaDto27.getUmbralCritico(),configSistema.getValor());
								
								if(alertaAntiguedadxDiaEmpleado != null && alertaAntiguedadxDiaEmpleado.size() > 0){
									StringBuilder sb=new StringBuilder("");
									sb.append("<hr>");
									sb.append("<table>");
									sb.append("<td>").append("Nombre Empleado").append("</td>");
									sb.append("<td>").append("Fecha Ingreso").append("</td>");
									sb.append("<td>").append("Mensaje").append("</td>");
									sb.append("</tr>");												
									for(EmpleadoResultViewModel n:alertaAntiguedadxDiaEmpleado){
										sb.append("<tr>");
										sb.append("<td>").append(n.getNombre()+", "+n.getApellidoPaterno()+" "+n.getApellidoMaterno()).append("</td>");
										sb.append("<td>").append(n.getFechaIngreso()).append("</td>");
										sb.append("<td>").append("El empleado est\u00e1 a "+alertaDto27.getUmbralCritico()+" d\u00edas de cumplir "+configSistema.getValor()+" a\u00f1os en la empresa").append("</td>");
										sb.append("</tr>");
									}
									sb.append("</table>");
									
									DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
									String asunto=alertaDto27.getAsunto().replace("[Fecha]", dateFormat.format(new Date()));
									
									String cuerpo=alertaDto27.getCuerpo().replace("[Fecha]", dateFormat.format(new Date()));
									cuerpo= cuerpo.replace("[ListaEmpleados]", sb.toString());
									cuerpo = cuerpo.replace("[Year]", configSistema.getValor());
									
									List<String> correos=new ArrayList<String>();
									
									for(AlertaSubscriptorViewModel alertaSubscriptorDto:alertaDto27.getSubscriptores()) {													
										String emailInterno=alertaSubscriptorDto.getEmailInternoEmpleado();
										correos.add(emailInterno);								
									}												
									
									mailService.sendEmail(asunto, cuerpo, correos.toArray(new String[correos.size()]));
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
		jobEjecucionService.actualizarJobEjecucion(jobEjecucionViewModel, "JOB_TARDANZA_GROUP");
		JobEjecucionViewModel next=new JobEjecucionViewModel();
		next.setEjecutado(0);
		next.setEstado("N");
		Date nextExecution2= ce.getNextValidTimeAfter(nextExecution);
		next.setFechaProgramado(nextExecution2);
		jobEjecucionService.registarJobEjecucionEnBlanco(next, "JOB_TARDANZA_GROUP");
		
		
	}

}
