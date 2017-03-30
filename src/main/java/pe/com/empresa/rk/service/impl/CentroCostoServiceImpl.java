package pe.com.empresa.rk.service.impl;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.empresa.rk.domain.model.entities.DepartamentoArea;
import pe.com.empresa.rk.domain.model.repository.jdbc.CentroCostoRepository;
import pe.com.empresa.rk.domain.model.repository.jpa.DepartamentoAreaJpaRepository;
import pe.com.empresa.rk.domain.model.repository.jpa.EmpresaJpaRepository;
import pe.com.empresa.rk.domain.model.repository.jpa.UnidaDeNegocioJpaRepository;
import pe.com.empresa.rk.enums.SeverityStatusEnum;
import pe.com.empresa.rk.service.CentroCostoService;
import pe.com.empresa.rk.view.model.CentroCostoResultViewModel;
import pe.com.empresa.rk.domain.model.entities.CentroCosto;
import pe.com.empresa.rk.domain.model.entities.Empresa;
import pe.com.empresa.rk.domain.model.entities.Proyecto;
import pe.com.empresa.rk.domain.model.entities.UnidadDeNegocio;
import pe.com.empresa.rk.domain.model.repository.jpa.CentroCostoJpaRepository;
import pe.com.empresa.rk.domain.model.repository.jpa.ProyectoJpaRepository;
import pe.com.empresa.rk.exception.BusinessException;
import pe.com.empresa.rk.view.model.CentroCostoFilterViewModel;
import pe.com.empresa.rk.view.model.CentroCostoViewModel;
import pe.com.empresa.rk.view.model.NotificacionViewModel;

@Service
public class CentroCostoServiceImpl implements CentroCostoService {

	@Autowired
    Mapper mapper;
	
	@Autowired
	CentroCostoJpaRepository centroCostoJpaRepository;
	@Autowired
    CentroCostoRepository centroCostoRepository;
	@Autowired
    EmpresaJpaRepository empresaJpaRepository;
	@Autowired
    UnidaDeNegocioJpaRepository unidaDeNegocioJpaRepository;
	@Autowired
    DepartamentoAreaJpaRepository departamentoAreaJpaRepository;
	@Autowired
	ProyectoJpaRepository  proyectoJpaRepository;

	@Override
	public List<CentroCostoViewModel> obtenerCentrosCosto() {
		List<CentroCosto> entities;
		List<CentroCostoViewModel> items;
		
		entities = centroCostoJpaRepository.findAll();
		
		items = entities.stream().map(m -> mapper.map(m, CentroCostoViewModel.class)).collect(toList());
		
		return items;
	}

	@Override
	public List<CentroCostoResultViewModel> search(CentroCostoFilterViewModel filterViewModel) {
		// TODO Auto-generated method stub
		return centroCostoRepository.search(filterViewModel);
	}

	@Override
	public CentroCostoViewModel findOne(Long id) {
		CentroCosto centroCosto=centroCostoJpaRepository.findOne(id);
		CentroCostoViewModel centroCostoViewModel=new CentroCostoViewModel();
		mapper.map(centroCosto, centroCostoViewModel);
		centroCostoViewModel.setIdEmpresa(centroCosto.getEmpresa().getIdEmpresa());
		if(centroCosto.getUnidadDeNegocio()!=null)
			centroCostoViewModel.setIdUnidadDeNegocio(centroCosto.getUnidadDeNegocio().getIdUnidadDeNegocio());
		if(centroCosto.getDepartamentoArea()!=null)
			centroCostoViewModel.setIdDepartamentoArea(centroCosto.getDepartamentoArea().getIdDepartamentoArea());
		if(centroCosto.getProyecto()!=null)
			centroCostoViewModel.setIdProyecto(centroCosto.getProyecto().getIdProyecto());
		
		return centroCostoViewModel;
	}

	@Override
	public NotificacionViewModel create(CentroCostoViewModel manteinanceViewModel) {
		NotificacionViewModel notificacionDto=new NotificacionViewModel();
		notificacionDto=crearActualizarCentroCosto(manteinanceViewModel);
		return notificacionDto;
	}

	private NotificacionViewModel crearActualizarCentroCosto(CentroCostoViewModel centroCostoDto) {
		NotificacionViewModel notificacionDto=new NotificacionViewModel();
		CentroCosto centroCosto=new CentroCosto();
		
		try{
			mapper.map(centroCostoDto, centroCosto);
			Empresa empresa=empresaJpaRepository.findOne(centroCostoDto.getIdEmpresa());
			UnidadDeNegocio unidadDeNegocio=null;
			DepartamentoArea departamentoArea=null;
			Proyecto proyecto=null;
			if(centroCostoDto.getIdUnidadDeNegocio()!=null)
				unidadDeNegocio=unidaDeNegocioJpaRepository.findOne(centroCostoDto.getIdUnidadDeNegocio());
			if(centroCostoDto.getIdDepartamentoArea()!=null)
				departamentoArea=departamentoAreaJpaRepository.findOne(centroCostoDto.getIdDepartamentoArea());
			if(centroCostoDto.getIdProyecto()!=null)
				proyecto=proyectoJpaRepository.findOne(centroCostoDto.getIdProyecto());
			centroCosto.setEmpresa(empresa);
			centroCosto.setUnidadDeNegocio(unidadDeNegocio);
			centroCosto.setDepartamentoArea(departamentoArea);
			centroCosto.setProyecto(proyecto);
			
			centroCostoJpaRepository.save(centroCosto);
			centroCostoJpaRepository.flush();
			notificacionDto.setCodigo(1L);
			notificacionDto.setSeverity("success");
			notificacionDto.setSummary("Runakuna Success");
			notificacionDto.setDetail("Se registro correctamente");
		} catch (Exception e) {
			notificacionDto.setCodigo(0L);
			notificacionDto.setSeverity("error");
			notificacionDto.setSummary("Runakuna Error");
			notificacionDto.setDetail("No es posible registrar, "+e.getMessage());
		}
		return notificacionDto;
	}

	@Override
	public NotificacionViewModel update(CentroCostoViewModel manteinanceViewModel) {
		NotificacionViewModel notificacionDto=new NotificacionViewModel();
		notificacionDto=crearActualizarCentroCosto(manteinanceViewModel);
		return notificacionDto;
	}

	@Override
	public NotificacionViewModel delete(Long id) {
		NotificacionViewModel notificacion=new NotificacionViewModel();
		if (centroCostoJpaRepository.findOne(id) == null) {
			throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", "The record was changed by another user. Please re-enter the screen.");
		}
		try {
			centroCostoJpaRepository.delete(id);
			centroCostoJpaRepository.flush();
			
			notificacion.setCodigo(1L);
            notificacion.setSeverity(SeverityStatusEnum.SUCCESS.getCode());
			notificacion.setSummary("Runakuna Success");
            notificacion.setDetail("Registro fue eliminado correctamente.");
			
		} catch (Exception e) {
			throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", "Restriction is Being used, Can't be deleted");
		}
		return notificacion;
	}



}
