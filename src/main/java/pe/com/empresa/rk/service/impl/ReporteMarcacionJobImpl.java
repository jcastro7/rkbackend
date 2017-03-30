package pe.com.empresa.rk.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.empresa.rk.domain.model.repository.jpa.EmpleadoJpaRepository;
import pe.com.empresa.rk.view.model.GenerarReporteMarcacionViewModel;
import pe.com.empresa.rk.domain.model.entities.Empleado;
import pe.com.empresa.rk.domain.model.repository.jdbc.MarcacionJdbcRepository;
import pe.com.empresa.rk.view.model.ReporteMarcacionFilterViewModel;
import pe.com.empresa.rk.view.model.GenerarReporteMarcacionAcumuladaViewModel;
import pe.com.empresa.rk.view.model.ReporteMarcacionViewModel;
import pe.com.empresa.rk.view.model.ReporteMarcacionSubscriptorViewModel;
import pe.com.empresa.rk.service.MailService;
import pe.com.empresa.rk.service.ReporteMarcacionService;
import pe.com.empresa.rk.util.TemplateExcelExporter;

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

	//@Scheduled(cron="0 0/1 * 1/1 * ?")
	@Scheduled(cron="0 0 7 * * *")
	@Transactional
	public void crearReporteMarcacion() {
		String templateFileName = "excel/ReporteMarcaciones.xlsx";
		try {
			downloadTemplateReporteMarcaciones(templateFileName);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	@Scheduled(cron="0 0 6 1 * ?")
	@Transactional
	public void crearReporteMarcacionAcumulada() {
		String templateFileName = "excel/ReporteMarcacionesAcumuladas.xlsx";
		try {
			downloadTemplateReporteMarcacionesAcumuladas(templateFileName);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	 private void downloadTemplateReporteMarcaciones(String templateFileName) throws IOException {
		 ReporteMarcacionFilterViewModel dto=new ReporteMarcacionFilterViewModel();
		 dto.setEstado("A");
		 dto.setTipoReporte("D");
		 //dto.setReporteDiario(1);
		 List<ReporteMarcacionViewModel> listaParametros=new ArrayList<ReporteMarcacionViewModel>();
		 listaParametros= reporteMarcacionService.obtenerReportesJob(dto);
		 List<GenerarReporteMarcacionViewModel> listaReportes=new ArrayList<GenerarReporteMarcacionViewModel>();
		 int index=1;
		 for(ReporteMarcacionViewModel reporteMarcacionDto:listaParametros){
			 listaReportes=marcacionJdbcRepository.obtenerMarcacionesSegunParametros(reporteMarcacionDto);
			 generateExcelReporteMarcacion(listaReportes, templateFileName, index,reporteMarcacionDto);
			 index++;
		 }

	 }
	 
	 private void downloadTemplateReporteMarcacionesAcumuladas(String templateFileName) throws IOException {
		 ReporteMarcacionFilterViewModel dto=new ReporteMarcacionFilterViewModel();
		 dto.setEstado("A");
		 dto.setTipoReporte("M");
		 //dto.setReporteAcumulado(1);
		 List<ReporteMarcacionViewModel> listaParametros=new ArrayList<ReporteMarcacionViewModel>();
		 listaParametros= reporteMarcacionService.obtenerReportesJob(dto);
		 List<GenerarReporteMarcacionAcumuladaViewModel> listaReportes=new ArrayList<GenerarReporteMarcacionAcumuladaViewModel>();
		 
		 int index=1;
		 for(ReporteMarcacionViewModel reporteMarcacionDto:listaParametros){
			 listaReportes=marcacionJdbcRepository.obtenerMarcacionesAcumuladasSegunParametros(reporteMarcacionDto);
			 generateExcelReporteMarcacionAcumulada(listaReportes, templateFileName, index,reporteMarcacionDto);
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
				enviarMailReporte("Reporte de Marcacion Diaria","<p>Buen dia<br> Se adjunta excel con el resumen de la marcacion de empleados del dia de hoy. <br>",
						reporteMarcacionDto,filesReport);
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
				enviarMailReporte("Reporte de Marcacion Acumulada","<p>Buen dia<br> Se adjunta excel con el resumen de las marcaciones acumuladas al dia de hoy. <br>",
						reporteMarcacionDto,filesReport);
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
	                report.addColumnValue(0,  sdf.format(new Date()));
	                report.addColumnValue(1, item.getNombreCompletoEmpleado());
	                report.addColumnValue(2, item.getUnidadNegocio());
	                report.addColumnValue(3, item.getDepartamentoArea());
	                report.addColumnValue(4, item.getProyecto());
	               
	                report.addColumnValue(5, item.getHorasPendientesTotal());
	                report.addColumnValue(6, item.getHorasPendientesMesActual());
	                report.addColumnValue(7, item.getHorasPendientesHastaMesAnterior());
	                report.addColumnValue(8, item.getTardanzas());
	                report.addColumnValue(9, item.getHorasTrabajadas());
	                report.addColumnValue(10, item.getHorarioHorasTrabajo());
	                report.addColumnValue(11, item.getVacaciones());
	                report.addColumnValue(12, item.getLicencias());
	                report.addColumnValue(13, item.getInasistencias());
	                
	            }
	       }
			
		}
		
		 
		private void loadRegistrosMarcaciones(String templateFileName, TemplateExcelExporter report, List<GenerarReporteMarcacionViewModel> listaReportes) throws IOException {
		        Resource resource = resourceLoader.getResource("classpath:" + templateFileName);
		        report.exportH(resource.getInputStream());
		        //armarCabecera(report);
		        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		        
		        int index = 0;
		        if (listaReportes != null) {
		            for (GenerarReporteMarcacionViewModel item : listaReportes) {
		                index++;
		                report.addRow(index);
		                report.addColumnValue(0,  sdf.format(item.getFecha()));
		                report.addColumnValue(1, item.getNombreCompletoEmpleado());
		                report.addColumnValue(2, item.getUnidadNegocio());
		                report.addColumnValue(3, item.getDepartamentoArea());
		                report.addColumnValue(4, item.getProyecto());
		               
		                report.addColumnValue(5, item.getHoraIngreso());
		                report.addColumnValue(6, item.getHoraInicioAlmuerzo());
		                report.addColumnValue(7, item.getHoraFinAlmuerzo());
		                report.addColumnValue(8, item.getHoraSalida());
		                report.addColumnValue(9, item.getHoraIngresoHorario());
		                report.addColumnValue(10, item.getHoraSalidaHorario());

		                report.addColumnValue(11, item.getDemoraEntrada());
		                report.addColumnValue(12, item.getDemoraAlmuerzo());
		                report.addColumnValue(13, item.getDemoraSalida());
		                report.addColumnValue(14, item.getTardanza());
		                report.addColumnValue(15, item.getHorasTrabajoReal());
		                report.addColumnValue(16, item.getHorasTrabajoHorario());
		                report.addColumnValue(17, item.getVacaciones());
		                report.addColumnValue(18, item.getLicencia());
		                report.addColumnValue(19, item.getInasistencia());
		                
		            }
		       }
		 }
	 
	 
}
