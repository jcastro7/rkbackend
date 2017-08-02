package pe.com.tss.runakuna.service.impl;

import org.quartz.CronExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pe.com.tss.runakuna.domain.model.entities.Empleado;
import pe.com.tss.runakuna.domain.model.entities.Marcador;
import pe.com.tss.runakuna.domain.model.repository.jdbc.MarcacionJdbcRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.EmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.MarcadorJpaRepository;
import pe.com.tss.runakuna.service.AlertaService;
import pe.com.tss.runakuna.service.JobEjecucionService;
import pe.com.tss.runakuna.service.MailService;
import pe.com.tss.runakuna.service.VerificarMarcadores;
import pe.com.tss.runakuna.view.model.AlertaSubscriptorViewModel;
import pe.com.tss.runakuna.view.model.AlertaViewModel;
import pe.com.tss.runakuna.view.model.GeneraMsjeAlertaEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.JobEjecucionResultViewModel;
import pe.com.tss.runakuna.view.model.JobEjecucionViewModel;

import javax.sql.DataSource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by josediaz on 24/03/2017.
 */

@Service
public class VerificarMarcadoresImpl implements VerificarMarcadores {


    private static final Logger LOGGER = LoggerFactory.getLogger(VerificarMarcadoresImpl.class);

    @Autowired
    DataSource dataSource;

    @Autowired
    MarcadorJpaRepository marcadorJpaRepository;


    @Autowired
    MarcacionJdbcRepository marcacionJdbcRepository;

    @Autowired
    MailService mailService;

    @Autowired
    AlertaService alertaService;

    @Autowired
    EmpleadoJpaRepository empleadoJpaRepository;

    @Autowired
	JobEjecucionService jobEjecucionService;
    
    @Value("${spring.cronFrecuency.JOB_VERI_SENS}")
    String cron_JOB_VERI_SENS;
    
    //@Scheduled(cron="${spring.cronFrecuency.JOB_VERI_SENS}")
    @Override
    public void verificar() {

        List<Marcador> marcadores = marcadorJpaRepository.findAll();
        AlertaViewModel alertaDto = alertaService.obtenerAlerta("Alerta17");
        GeneraMsjeAlertaEmpleadoViewModel msjeAlertaDto;
        Date inicio=new Date();
		Date fin=null;
		JobEjecucionViewModel jobEjecucionViewModel=null;
		CronExpression ce=null;
		try {
			ce = new CronExpression(cron_JOB_VERI_SENS);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date nextExecution= ce.getNextValidTimeAfter(inicio);
		JobEjecucionResultViewModel result=jobEjecucionService.obtenerEjecucionJobPendiente("JOB_VERI_SENS");
		if(result==null ||result.getIdJobEjecucion()==null){
			JobEjecucionViewModel je=new JobEjecucionViewModel();
			je.setEjecutado(0);
			je.setEstado("N");
			je.setFechaProgramado(nextExecution);
			jobEjecucionService.registarJobEjecucionEnBlanco(je, "JOB_VERI_SENS");
			result=jobEjecucionService.obtenerEjecucionJobPendiente("JOB_VERI_SENS");
		}

		try{
	        for (Marcador marcador : marcadores) {
	            int numeroMarcaciones = marcacionJdbcRepository.countMarcacionesPorCodigo(marcador.getCodigo(), new Date());
	            int numeroAlertasPorMarcador = marcacionJdbcRepository.countAlertasEmpleadoPorMarcador(marcador.getCodigo());
	            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	            if (numeroMarcaciones == 0 && numeroAlertasPorMarcador == 0) {
	                msjeAlertaDto = new GeneraMsjeAlertaEmpleadoViewModel();
	                msjeAlertaDto.setIdAlerta(alertaDto.getIdAlerta());
	                msjeAlertaDto.setSensor(marcador.getCodigo());
	                Map<String, String> parametrosMensaje = new HashMap<>();
	                parametrosMensaje.put("Codigo", marcador.getCodigo());
                    parametrosMensaje.put("Nombre", marcador.getNombre());
                    parametrosMensaje.put("Fecha", dateFormat.format(new Date()));
	                for (AlertaSubscriptorViewModel alertaSubscriptorDto : alertaDto.getSubscriptores()) {
	                    Empleado empl = empleadoJpaRepository.findOne(alertaSubscriptorDto.getIdEmpleado());
	                    msjeAlertaDto.setIdEmpleado(empl.getIdEmpleado());
	                    msjeAlertaDto.setParametrosMsje(parametrosMensaje);
	                    alertaService.generarMensajeAlerta(msjeAlertaDto);
	                }
	                try {
                        sendAlertaToSubscriptores(parametrosMensaje, alertaDto);
                    } catch (Exception ex) {
                        LOGGER.info(ex.getMessage(), ex);
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
			e.printStackTrace();
		}  
		jobEjecucionService.actualizarJobEjecucion(jobEjecucionViewModel, "JOB_VERI_SENS");
		JobEjecucionViewModel next=new JobEjecucionViewModel();
		next.setEjecutado(0);
		next.setEstado("N");
		Date nextExecution2= ce.getNextValidTimeAfter(nextExecution);
		next.setFechaProgramado(nextExecution2);
		jobEjecucionService.registarJobEjecucionEnBlanco(next, "JOB_VERI_SENS");
    }


    private void sendAlertaToSubscriptores(Map<String, String> parametrosMensaje, AlertaViewModel alertaDto) {

    	String cuerpo=alertaDto.getCuerpo() ;
		cuerpo=cuerpo.replace("[Codigo]", (String)parametrosMensaje.get("Codigo"));
        cuerpo=cuerpo.replace("[Nombre]", (String)parametrosMensaje.get("Nombre"));
        cuerpo=cuerpo.replace("[Fecha]", parametrosMensaje.get("Fecha"));
        
    	StringBuilder sb = new StringBuilder();
        sb.append("<p>");
        sb.append("<table>");
        sb.append("<tr>");
        sb.append("<td>").append(cuerpo).append("</td>");
        sb.append("</tr>");
        sb.append("</table>");
        
        String asunto=alertaDto.getAsunto();
        asunto=asunto.replace("[Codigo]", (String)parametrosMensaje.get("Codigo"));
        asunto=asunto.replace("[Nombre]", (String)parametrosMensaje.get("Nombre"));
        
		
				
        mailService.sendEmail(asunto, cuerpo, getCorreos(alertaDto.getSubscriptores()));
    }

    /*private String getCuerpoMensaje(String cuerpoMensaje, Map<String, String> parametrosMensaje) {

        for (Map.Entry<String, String> entry : parametrosMensaje.entrySet()) {
            cuerpoMensaje = cuerpoMensaje.replace("[" + entry.getKey() + "]", entry.getValue());
        }
        return cuerpoMensaje;
    }*/

    private String[] getCorreos(List<AlertaSubscriptorViewModel> subscriptores) {
        String[] correos = new String[subscriptores.size()];
        int i = 0;
        for (AlertaSubscriptorViewModel alertaSubscriptorDto : subscriptores) {
            Empleado empl = empleadoJpaRepository.findOne(alertaSubscriptorDto.getIdEmpleado());
            correos[i] = empl.getEmailInterno();
            i++;
        }
        return correos;
    }
}

