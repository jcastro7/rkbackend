package pe.com.tss.runakuna.service.impl;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.com.tss.runakuna.domain.model.entities.ConfiguracionSistema;
import pe.com.tss.runakuna.domain.model.entities.Empleado;
import pe.com.tss.runakuna.domain.model.entities.PeriodoEmpleado;
import pe.com.tss.runakuna.domain.model.repository.jpa.*;
import pe.com.tss.runakuna.service.AlertaService;
import pe.com.tss.runakuna.service.MailService;
import pe.com.tss.runakuna.service.PeriodoEmpleadoService;
import pe.com.tss.runakuna.view.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PeriodoJobImpl{


	private static final Logger LOGGER  = LoggerFactory.getLogger(PeriodoJobImpl.class);
	
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
	Mapper mapper;
	
	@Autowired
	PeriodoEmpleadoService periodoEmpleadoService;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	AlertaService alertaService;

	@Scheduled(cron="0 0/5 * 1/1 * ?")
	public void integrarMarcacionesSistemaAsistencia(){
		ConfiguracionSistema cadenaMaxPermi = configuracionSistemaJpaRepository.obtenerConfiguracionSistemaPorCodigo("");
		
		ConfiguracionSistema cadenaMaxVarcacion = configuracionSistemaJpaRepository.obtenerConfiguracionSistemaPorCodigo("'GestionDeTiempo.PermisosPermitidos'");	
		
		
		PeriodoEmpleado periodoEmpleado = new PeriodoEmpleado();
		
		//obtener la lista de empleados vigentes sin periodo futuro
		
	}
	
	@Scheduled(cron="0 50 23 * * *")
	//@Scheduled(cron="0 0/1 * 1/1 * ?")
	@Transactional
	public void crearNuevoPeriodoEmpleado() {
			
		EmpleadoViewModel emp=null;
		EmpleadoViewModel emp1=null;
		PeriodoEmpleadoViewModel pemp=null;
		PeriodoEmpleadoViewModel pemp1=null;
		List<NotificacionEmpleadoViewModel> empleadosPeriodoNuevo=new ArrayList<NotificacionEmpleadoViewModel>();

		
		List<Empleado> empleados=empleadoJpaRepository.buscarEmpleadosPorEstado("A");
		AlertaViewModel alertaDto=alertaService.obtenerAlerta("Alerta13");
		GeneraMsjeAlertaEmpleadoViewModel msjeAlertaDto=null;
		Map<String,String> parametrosMensaje=null;
		for(Empleado empleado :empleados) {
			emp=new EmpleadoViewModel();
			emp.setIdEmpleado(empleado.getIdEmpleado());
			NotificacionEmpleadoViewModel notificacion=periodoEmpleadoService.registrarPeriodoEmpleado(emp);
			if(notificacion.getCodigo().longValue()==1L){
				empleadosPeriodoNuevo.add(notificacion);
				msjeAlertaDto=new GeneraMsjeAlertaEmpleadoViewModel();
				msjeAlertaDto.setIdAlerta(alertaDto.getIdAlerta());
				msjeAlertaDto.setIdEmpleado(emp.getIdEmpleado());
				parametrosMensaje=new HashMap<String,String>();
				parametrosMensaje.put("nombre", notificacion.getEmpleadoDto().getNombre()+" "+notificacion.getEmpleadoDto().getApellidoPaterno()
						+" "+notificacion.getEmpleadoDto().getApellidoMaterno());
				parametrosMensaje.put("periodo", notificacion.getPeriodoEmpleadoDto().getPeriodo());
				msjeAlertaDto.setParametrosMsje(parametrosMensaje);
				alertaService.generarMensajeAlerta(msjeAlertaDto);
			}
		}
		
		if(empleadosPeriodoNuevo.size()>0) {
			String[] correos=new String[alertaDto.getSubscriptores().size()];
			int i=0;
			for(AlertaSubscriptorViewModel alertaSubscriptorDto:alertaDto.getSubscriptores()) {
				Empleado empl=empleadoJpaRepository.findOne(alertaSubscriptorDto.getIdEmpleado());
				correos[i]=empl.getEmailInterno();
				i++;
			}
			StringBuilder sb=new StringBuilder(alertaDto.getCuerpo());
			sb.append("<p>");
			sb.append("<table>");
			for(NotificacionEmpleadoViewModel n:empleadosPeriodoNuevo){
				sb.append("<tr>");
				sb.append("<td>").append(n.getEmpleadoDto().getNombre()+" "+n.getEmpleadoDto().getApellidoPaterno()+" "+n.getEmpleadoDto().getApellidoMaterno()).append("</td>");
				sb.append("<td>").append(n.getEmpleadoDto().getEmailInterno()).append("</td>");
				sb.append("<td>").append(n.getPeriodoEmpleadoDto().getPeriodo()).append("</td>");
				sb.append("</tr>");
			}
			sb.append("</table>");
			mailService.sendEmail(alertaDto.getAsunto(), sb.toString(), correos);
		}
		  
		
	}
	
	@Scheduled(cron="0 59 23 * * *")
	//@Scheduled(cron="0 0/1 * 1/1 * ?")
	@Transactional
	public void actualizaVacacionesAcumuladasPeriodo() {
		List<Empleado> empleados=empleadoJpaRepository.buscarEmpleadosPorEstado("A");
		PeriodoEmpleadoFilterViewModel busquedaPermisoEmpleadoDto= null; 
		List<PeriodoEmpleadoResultViewModel> listaPeriodos=null;
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		try {
			String hoyString=sdf.format(new Date());
			Date hoy=sdf.parse(hoyString);
			PeriodoEmpleado periodoEmpleadoNuevo=new PeriodoEmpleado();
			for(Empleado empleado:empleados) {
				busquedaPermisoEmpleadoDto= new PeriodoEmpleadoFilterViewModel();
				busquedaPermisoEmpleadoDto.setIdEmpleado(empleado.getIdEmpleado());
				listaPeriodos=periodoEmpleadoService.busquedaPeriodoEmpleado(busquedaPermisoEmpleadoDto);
				for(PeriodoEmpleadoResultViewModel periodoEmpleadoResultViewModel:listaPeriodos){
					Date fechaFin=periodoEmpleadoResultViewModel.getFechaFin();
					fechaFin=sdf.parse(sdf.format(fechaFin));
					if(!fechaFin.before(hoy) && !fechaFin.after(hoy)){
						PeriodoEmpleado periodoEmpleadoActual=periodoEmpleadoJpaRepository.findOne(periodoEmpleadoResultViewModel.getIdPeriodoEmpleado());
						int vacacionesAcumuladas=periodoEmpleadoActual.getDiasVacacionesAcumulado();
						List<PeriodoEmpleado> lista=periodoEmpleadoJpaRepository.obtenerUltimoPeriodoEmpleadoVigente(empleado.getIdEmpleado());
						if(lista!=null && !lista.isEmpty()){
							periodoEmpleadoNuevo=lista.get(0);
						}
						periodoEmpleadoActual.setDiasVacacionesAcumulado(0);
						periodoEmpleadoNuevo.setDiasVacacionesAcumulado(periodoEmpleadoNuevo.getDiasVacacionesDisponibles()+vacacionesAcumuladas);
						periodoEmpleadoJpaRepository.save(periodoEmpleadoActual);
						periodoEmpleadoJpaRepository.flush();
						periodoEmpleadoJpaRepository.save(periodoEmpleadoNuevo);
						periodoEmpleadoJpaRepository.flush();
					}
				}
			}
			
			
		} catch (ParseException e) {
			LOGGER.info(e.getMessage(), e);

		} catch (Exception e) {
			LOGGER.info(e.getMessage(), e);

		}
		
	}
	
	
}
