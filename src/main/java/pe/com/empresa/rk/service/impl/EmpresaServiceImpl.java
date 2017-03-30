package pe.com.empresa.rk.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.empresa.rk.domain.model.entities.DepartamentoArea;
import pe.com.empresa.rk.domain.model.entities.UnidadDeNegocio;
import pe.com.empresa.rk.domain.model.repository.jpa.EmpresaJpaRepository;
import pe.com.empresa.rk.view.model.DepartamentoAreaViewModel;
import pe.com.empresa.rk.view.model.EmpresaResultViewModel;
import pe.com.empresa.rk.view.model.EmpresaViewModel;
import pe.com.empresa.rk.view.model.UnidadDeNegocioViewModel;
import pe.com.empresa.rk.domain.model.entities.Empresa;
import pe.com.empresa.rk.domain.model.repository.jdbc.EmpresaRepository;
import pe.com.empresa.rk.service.EmpresaService;
import pe.com.empresa.rk.view.model.EmpresaFilterViewModel;
import pe.com.empresa.rk.view.model.NotificacionViewModel;

@Service
public class EmpresaServiceImpl implements EmpresaService {

	@Autowired
	Mapper mapper;
	
	@Autowired
    EmpresaJpaRepository empresaJpaRepository;
		
	@Autowired
	EmpresaRepository empresaRepository;
	

	@Override
	public EmpresaViewModel findOne(Long idEmpresa) {
		EmpresaViewModel dto = new EmpresaViewModel();
		
		List<UnidadDeNegocioViewModel> unidades = new ArrayList<>();
		
		dto = empresaRepository.findEmpresaById(idEmpresa);

		dto.setUnidadesDeNegocio(new ArrayList<>());
		
		unidades = empresaRepository.findAllUnidadDeNegocioByEmpresa(idEmpresa);
		
		if(unidades!=null && unidades.size()>0){
		
			for (UnidadDeNegocioViewModel unidadDeNegocio : unidades) {
				unidadDeNegocio.setDepartamentosArea(new ArrayList<>());
				
				List<DepartamentoAreaViewModel> departamentos = new ArrayList<>();
				
				departamentos = empresaRepository.findAllDepartamentoAreaByUnidadNegocio(unidadDeNegocio.getIdUnidadDeNegocio());
				
				unidadDeNegocio.setDepartamentosArea(departamentos);
			}
			
		}
		
		dto.setUnidadesDeNegocio(unidades);
		
		
		return dto;
	}


	@Override
	public List<EmpresaResultViewModel> search(EmpresaFilterViewModel filterViewModel) {
		List<EmpresaResultViewModel> result = new ArrayList<>();
		
		result = empresaRepository.finAllEmpresaByFilterSearch(filterViewModel);
		
		return result;
	}



	@Override
	public NotificacionViewModel create(EmpresaViewModel manteinanceViewModel) {
		
		NotificacionViewModel notificacion = new NotificacionViewModel();
		
		Empresa entity = empresaJpaRepository.findEmpresaByCode(manteinanceViewModel.getCodigo());
		
		if(entity != null){
			notificacion.setCodigo(0l);
			notificacion.setDetail("El codigo ya existe.");
			return notificacion;
		}
		
		notificacion = createOrUpdateEmpresa(manteinanceViewModel);
		
		return notificacion;
	}


	@Override
	public NotificacionViewModel update(EmpresaViewModel manteinanceViewModel) {
		
		NotificacionViewModel notificacion = new NotificacionViewModel();
		
		notificacion = createOrUpdateEmpresa(manteinanceViewModel);
		
		return notificacion;
	}


	@Override
	public NotificacionViewModel delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private NotificacionViewModel createOrUpdateEmpresa(EmpresaViewModel manteinanceViewModel){
		
		NotificacionViewModel notificacion = new NotificacionViewModel();
		
		Empresa entity = new Empresa();
		
		mapper.map(manteinanceViewModel, entity);
		
		entity.setUnidadesDeNegocio(new ArrayList<>());
		
		for (UnidadDeNegocioViewModel unidadNegocioDto : manteinanceViewModel.getUnidadesDeNegocio()) {
				
			UnidadDeNegocio undNegocioEntity = new UnidadDeNegocio();
			mapper.map(unidadNegocioDto, undNegocioEntity);
			undNegocioEntity.setEmpresa(entity);
			entity.getUnidadesDeNegocio().add(undNegocioEntity);
			
			
			undNegocioEntity.setDepartamentosArea(new ArrayList<>());
			for(DepartamentoAreaViewModel departamentoAreaDto : unidadNegocioDto.getDepartamentosArea()){
				DepartamentoArea depAreaEntity = new DepartamentoArea();
				mapper.map(departamentoAreaDto, depAreaEntity);
				depAreaEntity.setUnidadDeNegocio(undNegocioEntity);
				undNegocioEntity.getDepartamentosArea().add(depAreaEntity);
			}
		}
		
		empresaJpaRepository.save(entity);
		empresaJpaRepository.flush();
		
		notificacion.setCodigo(1l);
		notificacion.setDetail("Se registro correctamente");
		
		return notificacion;
	}
	
}
