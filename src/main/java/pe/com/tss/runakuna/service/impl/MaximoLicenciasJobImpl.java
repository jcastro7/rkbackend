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
import pe.com.tss.runakuna.domain.model.entities.Empleado;
import pe.com.tss.runakuna.domain.model.entities.PeriodoEmpleado;
import pe.com.tss.runakuna.domain.model.entities.PeriodoEmpleadoTipoLicencia;
import pe.com.tss.runakuna.domain.model.entities.TipoLicencia;
import pe.com.tss.runakuna.domain.model.repository.jdbc.PeriodoEmpleadoJdbcRepository;
import pe.com.tss.runakuna.domain.model.repository.jdbc.PeriodoEmpleadoRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.ConfiguracionSistemaJpaRepository;
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
import pe.com.tss.runakuna.view.model.JobEjecucionResultViewModel;
import pe.com.tss.runakuna.view.model.JobEjecucionViewModel;
import pe.com.tss.runakuna.view.model.NotificacionEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.PeriodoEmpleadoFilterViewModel;
import pe.com.tss.runakuna.view.model.PeriodoEmpleadoResultViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MaximoLicenciasJobImpl{


	private static final Logger LOGGER  = LoggerFactory.getLogger(MaximoLicenciasJobImpl.class);
	
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
	PeriodoEmpleadoTipoLicenciaJpaRepository periodoEmpleadoTipoLicenciaJpaRepository;
	
	@Autowired
	Mapper mapper;
	
	@Autowired
	PeriodoEmpleadoService periodoEmpleadoService;
	
	@Autowired
	PeriodoEmpleadoRepository periodoEmpleadoRepository;
	
	@Autowired
	TipoLicenciaJpaRepository tipoLicenciaJpaRepository;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	AlertaService alertaService;
	
	@Autowired
	JobEjecucionService jobEjecucionService;
	
	@Value("${spring.cronFrecuency.JOB_MAX_LIC}")
    String cron_JOB_MAX_LIC;
	
	
	//@Scheduled(cron="${spring.cronFrecuency.JOB_MAX_LIC}")
	//@Scheduled(cron="0 0/1 * 1/1 * ?")
	@Transactional
	public void obtenerEmpleadosExcedenTipoLicencia() {
		
		Date inicio=new Date();
		Date fin=null;
		JobEjecucionViewModel jobEjecucionViewModel=null;
		CronExpression ce=null;
		try {
			ce = new CronExpression(cron_JOB_MAX_LIC);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date nextExecution= ce.getNextValidTimeAfter(inicio);
		JobEjecucionResultViewModel result=jobEjecucionService.obtenerEjecucionJobPendiente("JOB_MAX_LIC");
		if(result==null ||result.getIdJobEjecucion()==null){
			JobEjecucionViewModel je=new JobEjecucionViewModel();
			je.setEjecutado(0);
			je.setEstado("N");
			je.setFechaProgramado(nextExecution);
			jobEjecucionService.registarJobEjecucionEnBlanco(je, "JOB_MAX_LIC");
			result=jobEjecucionService.obtenerEjecucionJobPendiente("JOB_MAX_LIC");
			
		}
		try{
			List<Empleado> empleados=empleadoJpaRepository.buscarEmpleadosPorEstado("A");
			AlertaViewModel alertaDto=alertaService.obtenerAlerta("ALERTA12");
			for(Empleado empleado :empleados) {
				List<PeriodoEmpleado> periodos=periodoEmpleadoJpaRepository.obtenerPeriodosPorEmpleado(empleado.getIdEmpleado());
				for(PeriodoEmpleado periodo:periodos){
					List<PeriodoEmpleadoTipoLicencia> listPeriodoEmpleadoTipoLicencia=periodoEmpleadoTipoLicenciaJpaRepository.
							findOneByIdPeriodo(periodo.getIdPeriodoEmpleado() );
					for(PeriodoEmpleadoTipoLicencia periodoEmpleadoTipoLicencia:listPeriodoEmpleadoTipoLicencia){
					TipoLicencia tipoLicencia=periodoEmpleadoTipoLicencia.getTipoLicencia();
						if(periodoEmpleadoTipoLicencia.getDiasLicencia()>tipoLicencia.getLimiteAnual()){
							GeneraMsjeAlertaEmpleadoViewModel msjeAlertaDto=new GeneraMsjeAlertaEmpleadoViewModel();
							msjeAlertaDto.setIdAlerta(alertaDto.getIdAlerta());
							msjeAlertaDto.setIdEmpleado(empleado.getIdEmpleado());
							Map<String,String> parametrosMensaje=new HashMap<String,String>();
							parametrosMensaje.put("Dias de Licencia",periodoEmpleadoTipoLicencia.getDiasLicencia().toString());
							parametrosMensaje.put("Tipo Licencia",tipoLicencia.getNombre());
							msjeAlertaDto.setParametrosMsje(parametrosMensaje);
							alertaService.generarMensajeAlerta(msjeAlertaDto);
							String[] correos=new String[alertaDto.getSubscriptores().size()];//Correo de los subscriptores
							//String[] correos=new String[1];
							int i=0;
							//correos[0]="oscar.castillo@tss.com.pe";
							for(AlertaSubscriptorViewModel alertaSubscriptorDto:alertaDto.getSubscriptores()) {
								Empleado empl=empleadoJpaRepository.findOne(alertaSubscriptorDto.getIdEmpleado());
								correos[i]=empl.getEmailInterno();
								i++;
							}
							String cuerpo=alertaDto.getCuerpo().replace("[Empleado]", empleado.getApellidoPaterno()+" "+empleado.getApellidoMaterno()+" "+empleado.getNombre());
							cuerpo=cuerpo.replace("[Dias de Licencia]", periodoEmpleadoTipoLicencia.getDiasLicencia().toString());
							cuerpo=cuerpo.replace("[Tipo Licencia]", tipoLicencia.getNombre());
							cuerpo=cuerpo.replace("[Periodo]", periodo.getPeriodo());
							mailService.sendEmail(alertaDto.getAsunto(), cuerpo, correos);
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
		jobEjecucionService.actualizarJobEjecucion(jobEjecucionViewModel, "JOB_MAX_LIC");
		JobEjecucionViewModel next=new JobEjecucionViewModel();
		next.setEjecutado(0);
		next.setEstado("N");
		Date nextExecution2= ce.getNextValidTimeAfter(nextExecution);
		next.setFechaProgramado(nextExecution2);
		jobEjecucionService.registarJobEjecucionEnBlanco(next, "JOB_MAX_LIC");
	}
	
	
	
}
