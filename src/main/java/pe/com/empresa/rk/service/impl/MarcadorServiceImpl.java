package pe.com.empresa.rk.service.impl;

import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.empresa.rk.domain.model.entities.Empresa;
import pe.com.empresa.rk.domain.model.entities.Marcador;
import pe.com.empresa.rk.domain.model.repository.jdbc.MarcadorRepository;
import pe.com.empresa.rk.domain.model.repository.jpa.EmpresaJpaRepository;
import pe.com.empresa.rk.domain.model.repository.jpa.MarcadorJpaRepository;
import pe.com.empresa.rk.enums.SeverityStatusEnum;
import pe.com.empresa.rk.exception.BusinessException;
import pe.com.empresa.rk.service.MarcadorService;
import pe.com.empresa.rk.view.model.MarcadorFilterViewModel;
import pe.com.empresa.rk.view.model.MarcadorResultViewModel;
import pe.com.empresa.rk.view.model.MarcadorViewModel;
import pe.com.empresa.rk.view.model.NotificacionViewModel;

/**
 * Created by josediaz on 25/11/2016.
 */
@Service
public class MarcadorServiceImpl implements MarcadorService {

	private static final Logger LOGGER  = LoggerFactory.getLogger(MarcadorServiceImpl.class);

    @Autowired
    private MarcadorRepository marcadorRepository;
    
    @Autowired
    private MarcadorJpaRepository marcadorJpaRepository;
    
    @Autowired
    private EmpresaJpaRepository empresaJpaRepository;
    
    @Autowired
    private Mapper mapper;
    
    

	@Override
	public List<MarcadorResultViewModel> search(MarcadorFilterViewModel filterViewModel) {
		// TODO Auto-generated method stub
		return marcadorRepository.buscarMarcador(filterViewModel);
	}

	@Override
	public MarcadorViewModel findOne(Long id) {
		MarcadorViewModel marcadorViewModel=new MarcadorViewModel();
		Marcador marcador= marcadorJpaRepository.findOne(id);
		mapper.map(marcador, marcadorViewModel);
		
		return marcadorViewModel;
	}

	@Override
	public NotificacionViewModel create(MarcadorViewModel manteinanceViewModel) {
		NotificacionViewModel notificacionDto;
		notificacionDto=crearActualizarMarcador(manteinanceViewModel);
		return notificacionDto;
	}

	private NotificacionViewModel crearActualizarMarcador(MarcadorViewModel marcadorViewModel) {
		NotificacionViewModel notificacionDto=new NotificacionViewModel();
		Marcador marcador=new Marcador();
		try{
			mapper.map(marcadorViewModel, marcador);
			Empresa empresa=empresaJpaRepository.findOne(marcadorViewModel.getIdEmpresa());
			marcador.setEmpresa(empresa);
			
			marcadorJpaRepository.save(marcador);
			marcadorJpaRepository.flush();
			notificacionDto.setCodigo(1L);
	        notificacionDto.setSeverity("success");
	        notificacionDto.setSummary("Informativo");
	        notificacionDto.setDetail("Se registro correctamente");
		} catch (Exception e) {
			LOGGER.info(e.getMessage(), e);
			notificacionDto.setCodigo(0L);
			notificacionDto.setDetail("No es posible registrar, "+e.getMessage());
		}
		
		return notificacionDto;
	}

	@Override
	public NotificacionViewModel update(MarcadorViewModel manteinanceViewModel) {
		NotificacionViewModel notificacionDto;
		notificacionDto=crearActualizarMarcador(manteinanceViewModel);
		return notificacionDto;
	}

	@Override
	public NotificacionViewModel delete(Long id) {
		NotificacionViewModel notificacion = new NotificacionViewModel();
        if (marcadorJpaRepository.findOne(id) == null) {
            throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", "The record was changed by another user. Please re-enter the screen.");
        }
        try {
        	marcadorJpaRepository.delete(id);
        	marcadorJpaRepository.flush();
            notificacion.setCodigo(1L);
            notificacion.setSeverity(SeverityStatusEnum.SUCCESS.getCode());
			notificacion.setSummary("Runakuna Success");
            notificacion.setDetail("Registro fue eliminado correctamente.");
        } catch (Exception e) {
			LOGGER.info(e.getMessage(), e);
        	throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", "Restriction is Being used, Can't be deleted");
        }

        return notificacion;
	}

	

	
    


	
}
