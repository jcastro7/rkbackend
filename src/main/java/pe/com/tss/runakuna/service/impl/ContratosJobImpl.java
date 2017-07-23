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
import pe.com.tss.runakuna.domain.model.entities.Contrato;
import pe.com.tss.runakuna.domain.model.entities.Empleado;
import pe.com.tss.runakuna.domain.model.entities.PeriodoEmpleado;
import pe.com.tss.runakuna.domain.model.entities.PeriodoEmpleadoTipoLicencia;
import pe.com.tss.runakuna.domain.model.entities.TipoLicencia;
import pe.com.tss.runakuna.domain.model.repository.jdbc.HistoriaLaboralRepository;
import pe.com.tss.runakuna.domain.model.repository.jdbc.PeriodoEmpleadoJdbcRepository;
import pe.com.tss.runakuna.domain.model.repository.jdbc.PeriodoEmpleadoRepository;
import pe.com.tss.runakuna.domain.model.repository.jdbc.PermisoEmpleadoJdbcRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.ConfiguracionSistemaJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.ContratoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.EmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.HorarioEmpleadoDiaJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.HorarioEmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.MarcacionJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.PeriodoEmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.PeriodoEmpleadoTipoLicenciaJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.PermisoEmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.RegistroMarcacionEmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.TipoLicenciaJpaRepository;
import pe.com.tss.runakuna.service.AlertaService;
import pe.com.tss.runakuna.service.JobEjecucionService;
import pe.com.tss.runakuna.service.MailService;
import pe.com.tss.runakuna.service.PeriodoEmpleadoService;
import pe.com.tss.runakuna.view.model.AlertaSubscriptorViewModel;
import pe.com.tss.runakuna.view.model.AlertaViewModel;
import pe.com.tss.runakuna.view.model.EmpleadoViewModel;
import pe.com.tss.runakuna.view.model.GeneraMsjeAlertaEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.HistoriaLaboralViewModel;
import pe.com.tss.runakuna.view.model.JobEjecucionResultViewModel;
import pe.com.tss.runakuna.view.model.JobEjecucionViewModel;
import pe.com.tss.runakuna.view.model.NotificacionEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.PeriodoEmpleadoFilterViewModel;
import pe.com.tss.runakuna.view.model.PeriodoEmpleadoResultViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ContratosJobImpl{


	private static final Logger LOGGER  = LoggerFactory.getLogger(ContratosJobImpl.class);
	
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
	HistoriaLaboralRepository historiaLaboralRepository;
	
	@Autowired
	ConfiguracionSistemaJpaRepository configuracionSistemaJpaRepository;
	
	@Autowired
	PeriodoEmpleadoJpaRepository periodoEmpleadoJpaRepository;
	
	@Autowired
	PeriodoEmpleadoTipoLicenciaJpaRepository periodoEmpleadoTipoLicenciaJpaRepository;
	
	@Autowired
	Mapper mapper;
	
	@Autowired
	PeriodoEmpleadoService periodoEmpleadoService;
	
	@Autowired
	PeriodoEmpleadoRepository periodoEmpleadoRepository;
	
	@Autowired
	ContratoJpaRepository contratoJpaRepository;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	AlertaService alertaService;
	
	@Autowired
	JobEjecucionService jobEjecucionService;
	
	@Value("${spring.cronFrecuency.JOB_VENC_CONTR}")
    String cron_JOB_VENC_CONTR;
	
	
	//@Scheduled(cron="${spring.cronFrecuency.JOB_VENC_CONTR}")
	//@Scheduled(cron="0 0/1 * 1/1 * ?")
	@Transactional
	public void obtenerEmpleadosExcedenTipoLicencia() {
		
		Date inicio=new Date();
		Date fin=null;
		JobEjecucionViewModel jobEjecucionViewModel=null;
		CronExpression ce=null;
		try {
			ce = new CronExpression(cron_JOB_VENC_CONTR);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date nextExecution= ce.getNextValidTimeAfter(inicio);
		JobEjecucionResultViewModel result=jobEjecucionService.obtenerEjecucionJobPendiente("JOB_VENC_CONTR");
		if(result==null ||result.getIdJobEjecucion()==null){
			JobEjecucionViewModel je=new JobEjecucionViewModel();
			je.setEjecutado(0);
			je.setEstado("N");
			je.setFechaProgramado(nextExecution);
			jobEjecucionService.registarJobEjecucionEnBlanco(je, "JOB_VENC_CONTR");
			result=jobEjecucionService.obtenerEjecucionJobPendiente("JOB_VENC_CONTR");
			
		}
		try{
			int contratos=0;
			StringBuilder sb=new StringBuilder("");
			sb.append("<table>");
			ConfiguracionSistema maxDiasPreviosVencimientoContrato = configuracionSistemaJpaRepository.obtenerConfiguracionSistemaPorCodigo("Contrato.MaxDiasPreviosVencimientoContrato");
			SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
			List<Empleado> empleados=empleadoJpaRepository.buscarEmpleadosPorEstado("A");
			AlertaViewModel alertaDto=alertaService.obtenerAlerta("ALERTA05");
			List<String> correosJefes=new ArrayList<String>();
			for(Empleado empleado :empleados) {
				Contrato contrato=contratoJpaRepository.buscarContratoVigentePorEmpleado(new Date(), empleado.getIdEmpleado());
				if(contrato!=null && contrato.getFechaFin()!=null){
					Calendar c=Calendar.getInstance();
					c.setTime(new Date());
					c.add(Calendar.DAY_OF_MONTH,Integer.parseInt(maxDiasPreviosVencimientoContrato.getValor()));
					Date d=sdf.parse(sdf.format(c.getTime()));
					if(d.after(contrato.getFechaFin())){
						sb.append("<tr>");
						sb.append("<td>").append(empleado.getNombre()+" "+empleado.getApellidoPaterno()+" "+empleado.getApellidoMaterno()).append("</td>");
						sb.append("<td>").append(contrato.getFechaFin()).append("</td>");
						sb.append("</tr>");
						contratos++;
					}
					List<HistoriaLaboralViewModel> list=historiaLaboralRepository.obtenerHistoriasLaboralesPorEmpleado(empleado.getIdEmpleado());
					for(HistoriaLaboralViewModel historia:list){
						Empleado jefe=empleadoJpaRepository.findOne(historia.getIdJefeInmediato());
						correosJefes.add(jefe.getEmailInterno());
					}
				}
			}
			if(contratos>0){
				String[] correos=new String[alertaDto.getSubscriptores().size()+correosJefes.size()];
				int i=0;
				for(AlertaSubscriptorViewModel alertaSubscriptorDto:alertaDto.getSubscriptores()) {
					Empleado empl=empleadoJpaRepository.findOne(alertaSubscriptorDto.getIdEmpleado());
					correos[i]=empl.getEmailInterno();
					i++;
				}
				for(String correoJefe:correosJefes) {
					correos[i]=correoJefe;
					i++;
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
		jobEjecucionService.actualizarJobEjecucion(jobEjecucionViewModel, "JOB_VENC_CONTR");
		JobEjecucionViewModel next=new JobEjecucionViewModel();
		next.setEjecutado(0);
		next.setEstado("N");
		Date nextExecution2= ce.getNextValidTimeAfter(nextExecution);
		next.setFechaProgramado(nextExecution2);
		jobEjecucionService.registarJobEjecucionEnBlanco(next, "JOB_VENC_CONTR");
	}
	
	
	
}
