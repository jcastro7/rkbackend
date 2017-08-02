package pe.com.tss.runakuna.rest;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.tss.runakuna.view.model.ContratoResultViewModel;
import pe.com.tss.runakuna.view.model.ContratoViewModel;
import pe.com.tss.runakuna.view.model.EmpresaFilterViewModel;
import pe.com.tss.runakuna.view.model.EmpresaResultViewModel;
import pe.com.tss.runakuna.view.model.EmpresaViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;

import pe.com.tss.runakuna.service.ContratoService;
import pe.com.tss.runakuna.service.EmpresaService;

@RestController
@RequestMapping(value = "/api/empresa")
public class EmpresaController extends BaseController{
	
	@Autowired
	EmpresaService empresaService;
	
	@RequestMapping(value = "/obtenerEmpresa", method = RequestMethod.POST)
	public @ResponseBody EmpresaViewModel obtenerEmpresa(@RequestBody Long idEmpresa) {

		EmpresaViewModel result = new EmpresaViewModel();
		
		result = empresaService.findOne(idEmpresa);
		
		return result;

	}
	
	@RequestMapping(value = "/registrarEmpresa", method = RequestMethod.POST)
	public @ResponseBody NotificacionViewModel registrarEmpresa(@RequestBody EmpresaViewModel empresa) {

		NotificacionViewModel notificacion = new NotificacionViewModel();
		
		if(empresa.getIdEmpresa()==null){
			notificacion = empresaService.create(empresa);
		}else{
			notificacion = empresaService.update(empresa);
		}
		return notificacion;

	}
	
	@RequestMapping(value = "/obtenerEmpresas", method = RequestMethod.POST)
	public @ResponseBody List<EmpresaResultViewModel> obtenerEmpresas(@RequestBody EmpresaFilterViewModel empresa) {

		List<EmpresaResultViewModel> result = new ArrayList<>();
		
		result = empresaService.search(empresa);
		
		return result;

	}
	
}