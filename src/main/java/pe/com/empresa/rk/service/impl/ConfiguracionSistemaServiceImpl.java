package pe.com.empresa.rk.service.impl;

import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.empresa.rk.domain.model.repository.jpa.ConfiguracionSistemaJpaRepository;
import pe.com.empresa.rk.domain.model.repository.jpa.EmpresaJpaRepository;
import pe.com.empresa.rk.service.ConfiguracionSistemaService;
import pe.com.empresa.rk.view.model.ConfiguracionSistemaFilterViewModel;
import pe.com.empresa.rk.view.model.ConfiguracionSistemaResultViewModel;
import pe.com.empresa.rk.domain.model.entities.ConfiguracionSistema;
import pe.com.empresa.rk.domain.model.entities.Empresa;
import pe.com.empresa.rk.domain.model.repository.jdbc.ConfiguracionSistemaRepository;
import pe.com.empresa.rk.view.model.ConfiguracionSistemaViewModel;
import pe.com.empresa.rk.view.model.NotificacionViewModel;

@Service
public class ConfiguracionSistemaServiceImpl implements ConfiguracionSistemaService {

	final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	Mapper mapper;
	
	@Autowired
	ConfiguracionSistemaRepository configuracionSistemaRepository;
	
	@Autowired
    ConfiguracionSistemaJpaRepository configuracionSistemaJpaRepository;
	
	@Autowired
    EmpresaJpaRepository empresaJpaRepository;
	
	private NotificacionViewModel crearActualizarConfiguracionSistema(ConfiguracionSistemaViewModel configuracionSistemaDto){
		NotificacionViewModel notificacionDto=new NotificacionViewModel();
		ConfiguracionSistema configuracionSistema=new ConfiguracionSistema();
		
		try{
			mapper.map(configuracionSistemaDto, configuracionSistema);
			Empresa empresa=empresaJpaRepository.findOne(configuracionSistemaDto.getIdEmpresa());
			configuracionSistema.setEmpresa(empresa);
			configuracionSistemaJpaRepository.save(configuracionSistema);
			configuracionSistemaJpaRepository.flush();
			notificacionDto.setCodigo(1L);
			notificacionDto.setSeverity("success");
			notificacionDto.setSummary("Runakuna Success");
			notificacionDto.setDetail("Se actualizo correctamente");
		} catch (Exception e) {
			notificacionDto.setCodigo(0L);
			notificacionDto.setSeverity("error");
			notificacionDto.setSummary("Runakuna Error");
			notificacionDto.setDetail("No es posible actualizar, "+e.getMessage());
		}
		return notificacionDto;
	}

	@Override
	public List<ConfiguracionSistemaResultViewModel> search(ConfiguracionSistemaFilterViewModel filterViewModel) {
		return configuracionSistemaRepository.obtenerConfiguracionesSistema(filterViewModel);
	}
	
	@Override
	public ConfiguracionSistemaViewModel findOne(Long idConfiguracionSistema) {
		ConfiguracionSistemaViewModel dto=new ConfiguracionSistemaViewModel();
		ConfiguracionSistema configuracionSistema=configuracionSistemaJpaRepository.findOne(idConfiguracionSistema);
		mapper.map(configuracionSistema, dto);
		dto.setIdEmpresa(configuracionSistema.getEmpresa().getIdEmpresa());
		return dto;
	}

	@Override
	public NotificacionViewModel create(ConfiguracionSistemaViewModel manteinanceViewModel) {
		NotificacionViewModel notificacionDto=new NotificacionViewModel();
		notificacionDto=crearActualizarConfiguracionSistema(manteinanceViewModel);
		return notificacionDto;
	}

	@Override
	public NotificacionViewModel update(ConfiguracionSistemaViewModel manteinanceViewModel) {
		NotificacionViewModel notificacionDto=new NotificacionViewModel();
		notificacionDto=crearActualizarConfiguracionSistema(manteinanceViewModel);
		return notificacionDto;
	}

	@Override
	public NotificacionViewModel delete(Long idTipoLicencia) {
		
		return null;
	}






	



	



	

	

}
