package pe.com.tss.runakuna.rest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.tss.runakuna.service.JobService;
import pe.com.tss.runakuna.service.impl.VerificarMarcadoresImpl;
import pe.com.tss.runakuna.util.SpringContextUtil;
import pe.com.tss.runakuna.view.model.JobEjecucionFilterViewModel;
import pe.com.tss.runakuna.view.model.JobEjecucionViewModel;
import pe.com.tss.runakuna.view.model.JobExecuteManuallyViewModel;
import pe.com.tss.runakuna.view.model.JobResultViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;


@CrossOrigin
@RestController
@RequestMapping("/api/job")
public class JobController extends BaseController {

	@Autowired
	JobService jobService;
	
	@Autowired
    ApplicationContext applicationContext;
	
	private final Logger LOG = LoggerFactory.getLogger(JobController.class);

	@RequestMapping(value = "/obtenerJobDetalle", method = RequestMethod.POST)

	public @ResponseBody ResponseEntity<List<JobEjecucionViewModel>> obtenerJobEjecuciones(@RequestBody JobEjecucionFilterViewModel dto){
		List<JobEjecucionViewModel> result = new ArrayList<JobEjecucionViewModel>();
		result = jobService.obtenerJobEjecuciones(dto);
		if(result == null)
			result = new ArrayList<JobEjecucionViewModel>();
		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@RequestMapping(value = "/obtenerJobs", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<List<JobResultViewModel>> obtenerJobs() {
		List<JobResultViewModel> result = new ArrayList<JobResultViewModel>();
		result = jobService.getListaJobStatus();
		if (result == null)
			result = new ArrayList<>();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/executeManuallyJob", method = RequestMethod.POST)
	public @ResponseBody NotificacionViewModel executeManuallyJob(@RequestBody JobExecuteManuallyViewModel jobExecute) {
		NotificacionViewModel notificacion = new NotificacionViewModel();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					sendExecuteManuallyJob(jobExecute.getNombreClase(), jobExecute.getMetodo());
					notificacion.setCodigo(1L);
					notificacion.setSeverity("success");
					notificacion.setSummary("Runakuna Success");
					notificacion.setDetail("El job termino de ejecutarse.");
				} catch (Exception e) {
					LOG.error(":::Something went wrong when executing the job manually", e);
					notificacion.setCodigo(0L);
					notificacion.setSeverity("error");
					notificacion.setSummary("Runakuna Error");
					notificacion.setDetail("Something went wrong when executing the job manually");
				}finally {
				}
				
			}			
		}).start();
		
		return notificacion;

	}
	
	private void sendExecuteManuallyJob(String nombreClase, String metodo) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, InstantiationException {
		
		Class clazz = Class.forName(nombreClase);
//		clazz.getDeclaredMethod(metodo).invoke(clazz.newInstance(), null);
		Method method = clazz.getMethod(metodo);
		Object bean = applicationContext.getBean(clazz);
		method.invoke(bean);
	}


}
