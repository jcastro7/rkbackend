package pe.com.tss.runakuna.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.tss.runakuna.domain.model.entities.Alerta;
import pe.com.tss.runakuna.domain.model.entities.AlertaEmpleado;
import pe.com.tss.runakuna.domain.model.entities.AlertaSubscriptor;
import pe.com.tss.runakuna.domain.model.entities.Empleado;
import pe.com.tss.runakuna.domain.model.entities.Empresa;
import pe.com.tss.runakuna.domain.model.repository.jdbc.AlertaJdbcRepository;
import pe.com.tss.runakuna.domain.model.repository.jdbc.AlertaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.AlertaEmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.AlertaJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.AlertaSubscriptorJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.EmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.EmpresaJpaRepository;
import pe.com.tss.runakuna.view.model.AlertaViewModel;
import pe.com.tss.runakuna.view.model.AlertaEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.AlertaFilterViewModel;
import pe.com.tss.runakuna.view.model.AlertaResultViewModel;
import pe.com.tss.runakuna.view.model.AlertaSubscriptorViewModel;
import pe.com.tss.runakuna.view.model.GeneraMsjeAlertaEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;
import pe.com.tss.runakuna.service.AlertaService;

/**
 * Created by josediaz on 14/12/2016.
 */
@Service
public class AlertaServiceImpl implements AlertaService {

    @Autowired
    private AlertaRepository alertaRepository;
    
    @Autowired
    private AlertaJpaRepository alertaJpaRepository;
    
    @Autowired
    private AlertaJdbcRepository alertaJdbcRepository;
    
    @Autowired
    private EmpresaJpaRepository empresaJpaRepository;
    
    @Autowired
	Mapper mapper;
    
	@Autowired
	private EmpleadoJpaRepository empleadoJpaRepository;
	
	@Autowired
	private AlertaSubscriptorJpaRepository alertaSubscriptorJpaRepository;
	
	@Autowired
	private AlertaEmpleadoJpaRepository alertaEmpleadoJpaRepository;
    
    @Override
    public AlertaViewModel obtenerAlerta(String codigo) {
    	AlertaViewModel alertaDto= alertaRepository.obtenerAlerta(codigo);
    	alertaDto.setSubscriptores(new ArrayList<AlertaSubscriptorViewModel>());
    	Alerta alerta=alertaJpaRepository.findOne(alertaDto.getIdAlerta());
    	if(alerta.getSubscriptores()!=null ){
	    	for(AlertaSubscriptor alertaSubscriptor:alerta.getSubscriptores()){
				AlertaSubscriptorViewModel alertaSubscriptorDto=new AlertaSubscriptorViewModel();
				mapper.map(alertaSubscriptor, alertaSubscriptorDto);
				alertaSubscriptorDto.setIdAlerta(alertaDto.getIdAlerta());
				alertaSubscriptorDto.setIdEmpleado(alertaSubscriptor.getEmpleado().getIdEmpleado());
				alertaDto.getSubscriptores().add(alertaSubscriptorDto);
			}
    	}
    	return alertaDto;
    }
    

	
	private NotificacionViewModel crearActualizarAlerta(AlertaViewModel alertaDto){
		NotificacionViewModel notificacionDto=new NotificacionViewModel();
		Alerta entity=new Alerta();
		Empresa empresa=empresaJpaRepository.findOne(alertaDto.getIdEmpresa());
		try{
			mapper.map(alertaDto, entity);
			entity.setEmpresa(empresa);
			entity.setSubscriptores(new ArrayList<AlertaSubscriptor>());
			entity.setEstado(alertaDto.getCodigoEstado());
			for(AlertaSubscriptorViewModel subscriptorDto: alertaDto.getSubscriptores()) {
				AlertaSubscriptor alertaSubscriptor=new AlertaSubscriptor();
				mapper.map(subscriptorDto, alertaSubscriptor);
				alertaSubscriptor.setAlerta(entity);
				alertaSubscriptor.setEmpleado(empleadoJpaRepository.findOne(subscriptorDto.getIdEmpleado()));
				entity.getSubscriptores().add(alertaSubscriptor);
			}
			alertaJpaRepository.save(entity);
			alertaJpaRepository.flush();
			notificacionDto.setCodigo(1L);
			notificacionDto.setSeverity("success");
			notificacionDto.setSummary("Runakuna Success");
			notificacionDto.setDetail("Se actualizo correctamente");
		} catch (Exception e) {
			notificacionDto.setCodigo(0L);
			notificacionDto.setSeverity("error");
			notificacionDto.setSummary("Runakuna Error");
			notificacionDto.setDetail("No es posible actualizar, "+e.getMessage());
			e.printStackTrace();
		}
		
		return notificacionDto;
	}
	

	@Override
	public List<AlertaEmpleadoViewModel> obtenerMensajeAlerta(Long idEmpleado) {
		List<AlertaEmpleadoViewModel> lista=alertaJdbcRepository.obtenerAlertaEmpleado(idEmpleado);
		return lista;
	}
	@Override
	public NotificacionViewModel generarMensajeAlerta(GeneraMsjeAlertaEmpleadoViewModel msjeAlertaDto) {
		Alerta alerta=alertaJpaRepository.findOne(msjeAlertaDto.getIdAlerta());
		Empleado empleado= empleadoJpaRepository.findOne(msjeAlertaDto.getIdEmpleado());
		NotificacionViewModel notificacionDto=new NotificacionViewModel();
		try{
			String cuerpoAlerta=alerta.getAlerta();
			
			Map<String, String> map =msjeAlertaDto.getParametrosMsje();
			for (Map.Entry<String, String> entry : map.entrySet())
			{
			    String marca="["+entry.getKey()+"]";
			    cuerpoAlerta=cuerpoAlerta.replace(marca, entry.getValue());
			}
			AlertaEmpleado alertaEmpleado=new AlertaEmpleado();
			alertaEmpleado.setAlerta(alerta);
			alertaEmpleado.setEmpleado(empleado);
			alertaEmpleado.setEstado("P");
			alertaEmpleado.setMensaje(cuerpoAlerta);
			alertaEmpleadoJpaRepository.save(alertaEmpleado);
			alertaEmpleadoJpaRepository.flush();
			
			notificacionDto.setCodigo(1L);
			notificacionDto.setSeverity("success");
			notificacionDto.setSummary("Runakuna Success");
			notificacionDto.setDetail("Se genero mensaje de alerta correctamente");
		} catch (Exception e) {
			notificacionDto.setCodigo(0L);
			notificacionDto.setSeverity("error");
			notificacionDto.setSummary("Runakuna Error");
			notificacionDto.setDetail("No se pudo generar mensaje de alerta "+e.getMessage());
		}
		
		
		return notificacionDto;
	}


	@Override
	public List<AlertaResultViewModel> search(AlertaFilterViewModel filterViewModel) {
		return alertaJdbcRepository.obtenerAlertas(filterViewModel);
	}

	@Override
	public AlertaViewModel findOne(Long id) {
		AlertaViewModel dto=new AlertaViewModel();
		dto.setSubscriptores(new ArrayList<>());
		Alerta alerta=alertaJpaRepository.findOne(id);
		mapper.map(alerta, dto);
		dto.setIdEmpresa(alerta.getEmpresa().getIdEmpresa());
		dto.setCodigoEstado(alerta.getEstado());
		dto.setCodigoTipoAlerta(alerta.getTipoAlerta());
		dto.setCodigoTipoNotificacion(alerta.getTipoNotificacion());
		List<AlertaSubscriptorViewModel> subscriptores=	alertaJdbcRepository.obtenerSubscriptoresAlertas(dto);
		dto.setSubscriptores(subscriptores);
		return dto;
	}

	@Override
	public NotificacionViewModel create(AlertaViewModel manteinanceViewModel) {
		NotificacionViewModel notificacionDto=new NotificacionViewModel();
		notificacionDto= crearActualizarAlerta(manteinanceViewModel);
		return notificacionDto;
	}

	@Override
	public NotificacionViewModel update(AlertaViewModel manteinanceViewModel) {
		NotificacionViewModel notificacionDto=new NotificacionViewModel();
		notificacionDto= crearActualizarAlerta(manteinanceViewModel);
		return notificacionDto;
	}

	@Override
	public NotificacionViewModel delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
