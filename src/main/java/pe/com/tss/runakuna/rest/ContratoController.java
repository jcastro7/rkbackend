package pe.com.tss.runakuna.rest;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.tss.runakuna.view.model.ContratoResultViewModel;
import pe.com.tss.runakuna.view.model.ContratoViewModel;
import pe.com.tss.runakuna.view.model.EmpleadoViewModel;
import pe.com.tss.runakuna.view.model.HistoriaLaboralViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;

import pe.com.tss.runakuna.service.ContratoService;
import pe.com.tss.runakuna.util.DateUtil;
import pe.com.tss.runakuna.util.StringUtil;

@RestController
@RequestMapping(value = "/api/contrato")
public class ContratoController extends BaseController{
	
	@Autowired
	ContratoService contratoService;

	@Autowired
	private ResourceLoader resourceLoader;


	@RequestMapping(value = "/registrarContrato", method = RequestMethod.POST)
	public @ResponseBody
    NotificacionViewModel registrarContrato(@RequestBody ContratoViewModel contrato) {

		NotificacionViewModel notificacion = new NotificacionViewModel();
		
		
		if(contrato.getIdContrato() == null){
			contrato.setEstado("P");
			notificacion = contratoService.create(contrato);
			
		}else{
			notificacion = contratoService.update(contrato);
		}
		
		return notificacion;
		
	}
	
	@RequestMapping(value = "/obtenerContrato", method = RequestMethod.POST)
	public @ResponseBody
	ContratoViewModel obtenerContrato(@RequestBody Long idContrato) {

		ContratoViewModel result = new ContratoViewModel();
		
		result = contratoService.findOne(idContrato);
		return result;

	}
	
	@RequestMapping(value = "/obtenerContratosPorEmpleado", method = RequestMethod.POST)
	public @ResponseBody List<ContratoViewModel> obtenerContratosPorEmpleado(@RequestBody Long idEmpleado) {

		List<ContratoViewModel> result = new ArrayList<>();
		
		result = contratoService.obtenerContratosPorEmpleado(idEmpleado);
		return result;

	}
	
	@RequestMapping(value = "/busquedaContratosPorEmpleado", method = RequestMethod.POST)
	public @ResponseBody List<ContratoResultViewModel> busquedaContratosPorEmpleado(@RequestBody Long idEmpleado) {

		List<ContratoResultViewModel> result = new ArrayList<>();
		
		result = contratoService.busquedaContratosPorEmpleado(idEmpleado);
		return result;

	}
	
	@RequestMapping(value = "/aprobarContrato", method = RequestMethod.POST)
	public @ResponseBody
    NotificacionViewModel aprobarContrato(@RequestBody ContratoViewModel contrato) {

		NotificacionViewModel notificacion = new NotificacionViewModel();
		contrato.setEstado("T");
		notificacion = contratoService.aprobar(contrato);
		return notificacion;

	}
	
	@RequestMapping(value = "/eliminarContrato", method = RequestMethod.POST)
	public @ResponseBody
    NotificacionViewModel eliminarContrato(@RequestBody Long idContrato) {

		NotificacionViewModel notificacion = new NotificacionViewModel();
		notificacion = contratoService.delete(idContrato);
		return notificacion;

	}
	
	@RequestMapping(value = "/obtenerHistorialLaboralActualPorEmpleado", method = RequestMethod.POST)
	public @ResponseBody HistoriaLaboralViewModel obtenerHistorialLaboralActualPorEmpleado(@RequestBody Long idEmpleado) {
		HistoriaLaboralViewModel dto = contratoService.obtenerHistorialLaboralActualPorEmpleado(idEmpleado);
		return dto;
	}
			
}