package pe.com.tss.runakuna.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.tss.runakuna.domain.model.entities.Empleado;
import pe.com.tss.runakuna.domain.model.repository.jdbc.MarcacionJdbcRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.EmpleadoJpaRepository;
import pe.com.tss.runakuna.view.model.ReporteMarcacionFilterViewModel;
import pe.com.tss.runakuna.view.model.AlertaViewModel;
import pe.com.tss.runakuna.view.model.GenerarReporteMarcacionAcumuladaViewModel;
import pe.com.tss.runakuna.view.model.GenerarReporteMarcacionViewModel;
import pe.com.tss.runakuna.view.model.JobEjecucionResultViewModel;
import pe.com.tss.runakuna.view.model.JobEjecucionViewModel;
import pe.com.tss.runakuna.view.model.ReporteMarcacionViewModel;
import pe.com.tss.runakuna.view.model.ReporteMarcacionSubscriptorViewModel;
import pe.com.tss.runakuna.service.AlertaService;
import pe.com.tss.runakuna.service.JobEjecucionService;
import pe.com.tss.runakuna.service.MailService;
import pe.com.tss.runakuna.service.ReporteMarcacionService;
import pe.com.tss.runakuna.util.DateUtil;
import pe.com.tss.runakuna.util.StringUtil;
import pe.com.tss.runakuna.util.TemplateExcelExporter;

@Service
public class ReporteMarcacionJobImpl {
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Autowired
    ReporteMarcacionService reporteMarcacionService;
	
	@Autowired
	MarcacionJdbcRepository marcacionJdbcRepository;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	EmpleadoJpaRepository empleadoJpaRepository;
	
	@Autowired
	AlertaService alertaService;
	
	@Autowired
	JobEjecucionService jobEjecucionService;
	
	@Value("${spring.cronFrecuency.JOB_REP_MARCA}")
    String cron_JOB_REP_MARCA;
	
	@Value("${spring.cronFrecuency.JOB_REP_MARCA_ACU}")
    String cron_JOB_REP_MARCA_ACU;
	
	@Value("${spring.production.active}")
    private boolean productionActive;

	//@Scheduled(cron="${spring.cronFrecuency.JOB_REP_MARCA}")
	@Transactional
	public void crearReporteMarcacion() {
		
		if(productionActive){
			String templateFileName = "excel/ReporteMarcaciones.xlsx";
			Date inicio=new Date();
			Date fin=null;
			JobEjecucionViewModel jobEjecucionViewModel=null;
			CronExpression ce=null;
			try {
				ce = new CronExpression(cron_JOB_REP_MARCA);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Date nextExecution= ce.getNextValidTimeAfter(inicio);
			JobEjecucionResultViewModel result=jobEjecucionService.obtenerEjecucionJobPendiente("JOB_REP_MARCA");
			if(result==null ||result.getIdJobEjecucion()==null){
				JobEjecucionViewModel je=new JobEjecucionViewModel();
				je.setEjecutado(0);
				je.setEstado("N");
				je.setFechaProgramado(nextExecution);
				jobEjecucionService.registarJobEjecucionEnBlanco(je, "JOB_REP_MARCA");
				result=jobEjecucionService.obtenerEjecucionJobPendiente("JOB_REP_MARCA");
			}
			try {
				downloadTemplateReporteMarcaciones(templateFileName);
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
			jobEjecucionService.actualizarJobEjecucion(jobEjecucionViewModel, "JOB_REP_MARCA");
			JobEjecucionViewModel next=new JobEjecucionViewModel();
			next.setEjecutado(0);
			next.setEstado("N");
			Date nextExecution2= ce.getNextValidTimeAfter(nextExecution);
			next.setFechaProgramado(nextExecution2);
			jobEjecucionService.registarJobEjecucionEnBlanco(next, "JOB_REP_MARCA");
		}
		
	}
	
	//@Scheduled(cron="${spring.cronFrecuency.JOB_REP_MARCA_ACU}")
	@Transactional
	public void crearReporteMarcacionAcumulada() {
		
		if(productionActive){
			String templateFileName = "excel/ReporteMarcacionesAcumuladas.xlsx";
			Date inicio=new Date();
			Date fin=null;
			JobEjecucionViewModel jobEjecucionViewModel=null;
			CronExpression ce=null;
			try {
				ce = new CronExpression(cron_JOB_REP_MARCA_ACU);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Date nextExecution= ce.getNextValidTimeAfter(inicio);
			JobEjecucionResultViewModel result=jobEjecucionService.obtenerEjecucionJobPendiente("JOB_REP_MARCA_ACU");
			if(result==null ||result.getIdJobEjecucion()==null){
				JobEjecucionViewModel je=new JobEjecucionViewModel();
				je.setEjecutado(0);
				je.setEstado("N");
				je.setFechaProgramado(nextExecution);
				jobEjecucionService.registarJobEjecucionEnBlanco(je, "JOB_REP_MARCA_ACU");
				result=jobEjecucionService.obtenerEjecucionJobPendiente("JOB_REP_MARCA_ACU");
			}
			try {
				downloadTemplateReporteMarcacionesAcumuladas(templateFileName);
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
			jobEjecucionService.actualizarJobEjecucion(jobEjecucionViewModel, "JOB_REP_MARCA_ACU");
			JobEjecucionViewModel next=new JobEjecucionViewModel();
			next.setEjecutado(0);
			next.setEstado("N");
			Date nextExecution2= ce.getNextValidTimeAfter(nextExecution);
			next.setFechaProgramado(nextExecution2);
			jobEjecucionService.registarJobEjecucionEnBlanco(next, "JOB_REP_MARCA_ACU");
		}
		
	}
	
	 private void downloadTemplateReporteMarcaciones(String templateFileName) throws IOException {
		 		 
		 ReporteMarcacionFilterViewModel dto=new ReporteMarcacionFilterViewModel();
		 dto.setEstado("A");
		 dto.setTipoReporte("D");
		 List<ReporteMarcacionViewModel> listaParametros=new ArrayList<ReporteMarcacionViewModel>();
		 listaParametros= reporteMarcacionService.obtenerReportesJob(dto);
		 List<GenerarReporteMarcacionViewModel> listaReportes=new ArrayList<GenerarReporteMarcacionViewModel>();
		 int index=1;
		 for(ReporteMarcacionViewModel reporteMarcacionDto:listaParametros){
			 listaReportes=marcacionJdbcRepository.obtenerMarcacionesSegunParametros(reporteMarcacionDto);
			 
			 if(listaReportes != null && listaReportes.size()>0){
				 generateExcelReporteMarcacion(listaReportes, templateFileName, index,reporteMarcacionDto);	 
			 }
			 
			 index++;
		 }

	 }
	 
	 private void downloadTemplateReporteMarcacionesAcumuladas(String templateFileName) throws IOException {
		 ReporteMarcacionFilterViewModel dto=new ReporteMarcacionFilterViewModel();
		 dto.setEstado("A");
		 dto.setTipoReporte("M");
		 List<ReporteMarcacionViewModel> listaParametros=new ArrayList<ReporteMarcacionViewModel>();
		 listaParametros= reporteMarcacionService.obtenerReportesJob(dto);
		 List<GenerarReporteMarcacionAcumuladaViewModel> listaReportes=new ArrayList<GenerarReporteMarcacionAcumuladaViewModel>();
		 
		 int index=1;
		 for(ReporteMarcacionViewModel reporteMarcacionDto:listaParametros){
			 listaReportes=marcacionJdbcRepository.obtenerMarcacionesAcumuladasSegunParametros(reporteMarcacionDto);
			 if(listaReportes != null && listaReportes.size()>0){
				 generateExcelReporteMarcacionAcumulada(listaReportes, templateFileName, index,reporteMarcacionDto);
			 }
			 
			 index++;
		 }
		 
		
	}
	 
	 private void generateExcelReporteMarcacion(List<GenerarReporteMarcacionViewModel> listaReportes,
				String templateFileName, int index, ReporteMarcacionViewModel reporteMarcacionDto) {
			 
			 try {
				TemplateExcelExporter report = new TemplateExcelExporter(); 
				loadRegistrosMarcaciones(templateFileName, report, listaReportes);
				Resource resource = resourceLoader.getResource("classpath:" );
				File miexcel=new File(resource.getURL().getPath()+"\\excel\\reporteMarcacion_"+index+".xlsx");
				miexcel.createNewFile();
				File fileReport=report.writeExcelToFile(miexcel);
				File[] filesReport=new File[1];
				filesReport[0]=fileReport;
				AlertaViewModel alertaDto=alertaService.obtenerAlerta("Alerta18");
				String fechaReporte = new SimpleDateFormat("dd/MM/yyyy").format(DateUtil.addDays(new Date(), -1));
				String asunto=alertaDto.getAsunto().replace("[Fecha del Reporte]", fechaReporte);
				String cuerpo=alertaDto.getCuerpo().replace("[Fecha del Reporte]", fechaReporte);
				enviarMailReporte(asunto,cuerpo,reporteMarcacionDto,filesReport);
			} catch (IOException e) {
				e.printStackTrace();
			}
			 
	 }
	 
	 private void generateExcelReporteMarcacionAcumulada(List<GenerarReporteMarcacionAcumuladaViewModel> listaReportes,
				String templateFileName, int index, ReporteMarcacionViewModel reporteMarcacionDto) {
			 
			 try {
				TemplateExcelExporter report = new TemplateExcelExporter(); 
				loadRegistrosMarcacionesAcumuladas(templateFileName, report, listaReportes);
				Resource resource = resourceLoader.getResource("classpath:" );
				File miexcel=new File(resource.getURL().getPath()+"\\excel\\reporteMarcacionAcumulada_"+index+".xlsx");
				miexcel.createNewFile();
				File fileReport=report.writeExcelToFile(miexcel);
				File[] filesReport=new File[1];
				filesReport[0]=fileReport;
				AlertaViewModel alertaDto=alertaService.obtenerAlerta("Alerta21");
				String fechaReporte = new SimpleDateFormat("MM/yyyy").format(new Date());
				String asunto=alertaDto.getAsunto().replace("[Fecha]", fechaReporte);
				String cuerpo=alertaDto.getCuerpo().replace("[Mes]", fechaReporte);
				/*enviarMailReporte("Reporte de Marcacion Acumulada","<p>Buen dia<br> Se adjunta excel con el resumen de las marcaciones acumuladas al dia de hoy. <br>",
						reporteMarcacionDto,filesReport);*/
				enviarMailReporte(asunto,cuerpo,reporteMarcacionDto,filesReport);
			} catch (IOException e) {
				e.printStackTrace();
			}
			 
		}

	 private void enviarMailReporte(String subject, String msje, ReporteMarcacionViewModel reporteMarcacionDto, File[] filesReport) {
			ReporteMarcacionViewModel dto=reporteMarcacionService.obtenerReporteMarcacionDetalle(reporteMarcacionDto.getIdReporteMarcacion());
			String[] correos=null;
			List<String> correosList=new ArrayList<String>();
			
			for(ReporteMarcacionSubscriptorViewModel reporteMarcacionSubscriptorDto:dto.getSubscriptores()) {
				Empleado empl=empleadoJpaRepository.findOne(reporteMarcacionSubscriptorDto.getIdEmpleado());
				if (empl.getEmailInterno()!=null && !empl.getEmailInterno().equals("")) {
					correosList.add(empl.getEmailInterno());
				}
			}
			correos=new String[correosList.size()];
			correos=correosList.toArray(correos);
			mailService.sendEmailWithAttachments(subject, msje, correos, filesReport);
		}

		private void loadRegistrosMarcacionesAcumuladas(String templateFileName, TemplateExcelExporter report,
				List<GenerarReporteMarcacionAcumuladaViewModel> listaReportes) throws IOException {
			Resource resource = resourceLoader.getResource("classpath:" + templateFileName);
	        report.exportH(resource.getInputStream());
	        //armarCabecera(report);
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	        
	        int index = 0;
	        if (listaReportes != null) {
	            for (GenerarReporteMarcacionAcumuladaViewModel item : listaReportes) {
	                index++;
	                report.addRow(index);
	                CellStyle   style = report.getWb().createCellStyle();
	                XSSFFont font = (XSSFFont) report.getWb().createFont();
	                font.setFontHeightInPoints((short) 10);
	                style.setFont(font);
	                
	                report.addColumnValue(0,  sdf.format(new Date()),style);
	                report.addColumnValue(1, item.getNombreCompletoEmpleado(),style);
	                report.addColumnValue(2, item.getUnidadNegocio(),style);
	                report.addColumnValue(3, item.getDepartamentoArea(),style);
	                report.addColumnValue(4, item.getProyecto(),style);
	               
	                report.addColumnValue(5, item.getHorasPendientesTotal(),style);
	                report.addColumnValue(6, item.getHorasPendientesMesActual(),style);
	                report.addColumnValue(7, item.getHorasPendientesHastaMesAnterior(),style);
	                report.addColumnValue(8, item.getTardanzas(),style);
	                report.addColumnValue(9, item.getHorasTrabajadas(),style);
	                report.addColumnValue(10, item.getHorarioHorasTrabajo(),style);
	                report.addColumnValue(11, item.getVacaciones(),style);
	                report.addColumnValue(12, item.getLicencias(),style);
	                report.addColumnValue(13, item.getInasistencias(),style);
	                
	            }
	       }
			
		}
		
		 
		private void loadRegistrosMarcaciones(String templateFileName, TemplateExcelExporter report, List<GenerarReporteMarcacionViewModel> listaReportes) throws IOException {
		        Resource resource = resourceLoader.getResource("classpath:" + templateFileName);
		        report.exportH(resource.getInputStream());
		        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		        
		        int index = 0;
		        if (listaReportes != null) {
		        	
		            for (GenerarReporteMarcacionViewModel item : listaReportes) {
		                index++;
		                report.addRow(index);
		                
		                CellStyle   style = report.getWb().createCellStyle();
		                XSSFFont font = (XSSFFont) report.getWb().createFont();
		                font.setFontHeightInPoints((short) 10);
		                style.setFont(font);
		                
		                report.addColumnValue(0,  sdf.format(item.getFecha()),style);
		                report.addColumnValue(1, item.getNombreCompletoEmpleado(),style);
		                report.addColumnValue(2, item.getUnidadNegocio(),style);
		                report.addColumnValue(3, item.getDepartamentoArea(),style);
		                report.addColumnValue(4, item.getProyecto(),style);
		               
		                report.addColumnValue(5, item.getHoraIngresoHorario(),style);
		                report.addColumnValue(6, item.getHoraIngreso(),style);
		                report.addColumnValue(7,"NO",style);
		                report.addColumnValue(8, item.getDemoraEntrada(),style);
		                if(item.getDemoraEntrada().doubleValue()>0){
		                	report.addColumnValue(7,"SI",style);
		                }
		                
		                report.addColumnValue(9, item.getHoraInicioAlmuerzo(),style);
		                report.addColumnValue(10, item.getHoraFinAlmuerzo(),style);
		                report.addColumnValue(11,"NO",style);
		                report.addColumnValue(12, item.getDemoraAlmuerzo(),style);
		                if(item.getDemoraAlmuerzo().doubleValue()>0){
		                	report.addColumnValue(11,"SI",style);
		                }

		                report.addColumnValue(13, item.getHoraSalidaHorario(),style);
		                report.addColumnValue(14, item.getHoraSalida(),style);
		                report.addColumnValue(15, item.getHorasTrabajoHorario(),style);
		                report.addColumnValue(16, item.getHorasTrabajoReal(),style);
		                report.addColumnValue(17, item.getDemoraSalida(),style);
		                
		                
		                //report.addColumnValue(14, item.getTardanza());
		                
		                
		                report.addColumnValue(18, item.getVacaciones(),style);
		                report.addColumnValue(19, item.getLicencia(),style);
		                report.addColumnValue(20, item.getInasistencia(),style);
		                
		            }
		       }
		 }
	 
	 
}
