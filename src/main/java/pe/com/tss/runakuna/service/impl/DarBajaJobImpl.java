package pe.com.tss.runakuna.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.tss.runakuna.domain.model.entities.Empleado;
import pe.com.tss.runakuna.domain.model.repository.jpa.EmpleadoJpaRepository;
import pe.com.tss.runakuna.view.model.AlertaSubscriptorViewModel;
import pe.com.tss.runakuna.view.model.AlertaViewModel;
import pe.com.tss.runakuna.view.model.EmpleadoViewModel;
import pe.com.tss.runakuna.view.model.JobEjecucionResultViewModel;
import pe.com.tss.runakuna.view.model.JobEjecucionViewModel;
import pe.com.tss.runakuna.json.JsonDateSimpleDeserializer;
import pe.com.tss.runakuna.json.JsonDateSimpleSerializer;
import pe.com.tss.runakuna.service.AlertaService;
import pe.com.tss.runakuna.service.JobEjecucionService;
import pe.com.tss.runakuna.service.MailService;
import pe.com.tss.runakuna.support.WhereParams;
import pe.com.tss.runakuna.util.DateUtil;

@Service
public class DarBajaJobImpl {
	
	@Autowired
	EmpleadoJpaRepository empleadoJpaRepository;
	
	@Autowired
	DataSource dataSource;

	private NamedParameterJdbcTemplate jdbcTemplate;
	
	private String getdate;

	@PostConstruct
	public void init() {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		
		Date date = new Date();
		getdate = DateUtil.format(new SimpleDateFormat("yyyy-MM-dd"), date);
	}
	
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	Date todayDate;
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	Date daysAgo;
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	Date dateBefore1Day;
	
	@Autowired
	JobEjecucionService jobEjecucionService;
	
	@Autowired
	AlertaService alertaService;
	
	@Autowired
	MailService mailService;
	/*
	 * 0 0 12 * * ?	Fire at 12pm (noon) every day
	 * 0 0 12 1/1 * ? *
	 * 0 0 12 ? * MON-FRI *
	 * 0 15 10 ? * *	Fire at 10:15am every day
	 * 
	 * 
	 */
	@Value("${spring.cronFrecuency.JOB_BAJ_EMPL}")
    String cron_JOB_BAJ_EMPL;
	
	//@Scheduled(cron="${spring.cronFrecuency.JOB_BAJ_EMPL}")
	public void darBajaEmpleado(){
		
		todayDate = new Date();
		Date inicio=new Date();
		Date fin=null;
		JobEjecucionViewModel jobEjecucionViewModel=null;
		AlertaViewModel alertaDto=alertaService.obtenerAlerta("Alerta20");
		CronExpression ce=null;
		try {
			ce = new CronExpression(cron_JOB_BAJ_EMPL);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date nextExecution= ce.getNextValidTimeAfter(inicio);
		JobEjecucionResultViewModel result=jobEjecucionService.obtenerEjecucionJobPendiente("JOB_BAJ_EMPL");
		if(result==null ||result.getIdJobEjecucion()==null){
			JobEjecucionViewModel je=new JobEjecucionViewModel();
			je.setEjecutado(0);
			je.setEstado("N");
			je.setFechaProgramado(nextExecution);
			jobEjecucionService.registarJobEjecucionEnBlanco(je, "JOB_BAJ_EMPL");
			result=jobEjecucionService.obtenerEjecucionJobPendiente("JOB_BAJ_EMPL");
		}
		try {
			List<EmpleadoViewModel> listFechaCese = getEmpleadosCesados();
			if(listFechaCese!= null){
				for(EmpleadoViewModel next: listFechaCese){
					Empleado entity = new Empleado();
					entity = empleadoJpaRepository.findOne(next.getIdEmpleado());
					entity.setEstado("I");
					entity = empleadoJpaRepository.save(entity);
					empleadoJpaRepository.flush();
				}
				if(listFechaCese.size()>0) {
					String[] correos=new String[alertaDto.getSubscriptores().size()];
					int i=0;
					for(AlertaSubscriptorViewModel alertaSubscriptorDto:alertaDto.getSubscriptores()) {
						Empleado empl=empleadoJpaRepository.findOne(alertaSubscriptorDto.getIdEmpleado());
						correos[i]=empl.getEmailInterno();
						i++;
					}
					StringBuilder sb=new StringBuilder("");
					sb.append("<table>");
					for(EmpleadoViewModel n:listFechaCese){
						sb.append("<tr>");
						sb.append("<td>").append(n.getNombre()+" "+n.getApellidoPaterno()+" "+n.getApellidoMaterno()).append("</td>");
						sb.append("<td>").append(n.getEmailInterno()).append("</td>");
						sb.append("</tr>");
					}
					sb.append("</table>");
					String cuerpo=alertaDto.getCuerpo().replace("[ListaEmpleados]", sb.toString());
					mailService.sendEmail(alertaDto.getAsunto(), cuerpo, correos);
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
		jobEjecucionService.actualizarJobEjecucion(jobEjecucionViewModel, "JOB_BAJ_EMPL");
		JobEjecucionViewModel next=new JobEjecucionViewModel();
		next.setEjecutado(0);
		next.setEstado("N");
		Date nextExecution2= ce.getNextValidTimeAfter(nextExecution);
		next.setFechaProgramado(nextExecution2);
		jobEjecucionService.registarJobEjecucionEnBlanco(next, "JOB_BAJ_EMPL");
	}
	
	private List<EmpleadoViewModel> getEmpleadosCesados() {
		WhereParams params = new WhereParams();
        String sql = getConsultaEmpleadosCesados();

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<>(EmpleadoViewModel.class));
	}

	private String getConsultaEmpleadosCesados() {
		StringBuilder sql = new StringBuilder();
        sql.append(" SELECT * FROM Empleado he WHERE he.Estado = 'A' ");
        sql.append(" AND he.FechaCese < '"+getdate+"'"  );
        return sql.toString();
	}
	
	//@Scheduled(cron="0 0/1 * 1/1 * ?")
	/*public void darBajaEmpleado2(){
		String[] correos=new String[1];
		correos[0]="oscar.castillo@tss.com.pe";
		mailService.sendEmail("Hola", "Hola", correos);
	}*/

}
