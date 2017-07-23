package pe.com.tss.runakuna.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.tss.runakuna.domain.model.entities.DepartamentoArea;
import pe.com.tss.runakuna.domain.model.entities.Empleado;
import pe.com.tss.runakuna.domain.model.entities.Empresa;
import pe.com.tss.runakuna.domain.model.entities.UnidadDeNegocio;
import pe.com.tss.runakuna.domain.model.repository.jdbc.EmpresaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.EmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.EmpresaJpaRepository;
import pe.com.tss.runakuna.service.EmpresaService;
import pe.com.tss.runakuna.view.model.DepartamentoAreaViewModel;
import pe.com.tss.runakuna.view.model.EmpresaFilterViewModel;
import pe.com.tss.runakuna.view.model.EmpresaResultViewModel;
import pe.com.tss.runakuna.view.model.EmpresaViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;
import pe.com.tss.runakuna.view.model.UnidadDeNegocioViewModel;

@Service
public class EmpresaServiceImpl implements EmpresaService {

	@Autowired
	Mapper mapper;
	
	@Autowired
	EmpresaJpaRepository empresaJpaRepository;
		
	@Autowired
	EmpresaRepository empresaRepository;
	
	@Autowired
	EmpleadoJpaRepository empleadoJpaRepository;
	

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
		
		try{
			Empresa entity = new Empresa();
		
			mapper.map(manteinanceViewModel, entity);
		
			Empleado empleadoEntity = empleadoJpaRepository.findOne(manteinanceViewModel.getIdJefe());
			entity.setEmpleado(empleadoEntity);
			
			if(manteinanceViewModel.getIdJefeReemplazo()!=null){
				Empleado jefeReemplazoEntity = empleadoJpaRepository.findOne(manteinanceViewModel.getIdJefeReemplazo());
				entity.setEmpleadoJefeReemplazo(jefeReemplazoEntity);	
			}else{
				entity.setEmpleadoJefeReemplazo(null);	
			}			
			
			entity.setUnidadesDeNegocio(new ArrayList<>());
			
			for (UnidadDeNegocioViewModel unidadNegocioDto : manteinanceViewModel.getUnidadesDeNegocio()) {
					
				UnidadDeNegocio undNegocioEntity = new UnidadDeNegocio();
				mapper.map(unidadNegocioDto, undNegocioEntity);
				undNegocioEntity.setEmpresa(entity);
				entity.getUnidadesDeNegocio().add(undNegocioEntity);
				
					Empleado jefeUnidadDeNegocio = empleadoJpaRepository.findOne(unidadNegocioDto.getIdJefe());
					undNegocioEntity.setEmpleadoJefe(jefeUnidadDeNegocio);
					
					if(unidadNegocioDto.getIdJefeReemplazo()!=null){
						Empleado jefeReemplazoUnidadDeNegocio = empleadoJpaRepository.findOne(unidadNegocioDto.getIdJefeReemplazo());
						undNegocioEntity.setEmpleadoJefeReemplazo(jefeReemplazoUnidadDeNegocio);	
					}else{
						undNegocioEntity.setEmpleadoJefeReemplazo(null);
					}
				
				undNegocioEntity.setDepartamentosArea(new ArrayList<>());
				for(DepartamentoAreaViewModel departamentoAreaDto : unidadNegocioDto.getDepartamentosArea()){
					DepartamentoArea depAreaEntity = new DepartamentoArea();
					mapper.map(departamentoAreaDto, depAreaEntity);
					depAreaEntity.setUnidadDeNegocio(undNegocioEntity);
						Empleado jefeDepartamentoArea = empleadoJpaRepository.findOne(departamentoAreaDto.getIdJefe());
						depAreaEntity.setEmpleadoJefe(jefeDepartamentoArea);
						
						if(unidadNegocioDto.getIdJefeReemplazo()!=null){
							Empleado jefeReemplazoDepartamentoArea = empleadoJpaRepository.findOne(departamentoAreaDto.getIdJefeReemplazo());
							depAreaEntity.setEmpleadoJefeReemplazo(jefeReemplazoDepartamentoArea);	
						}else{
							depAreaEntity.setEmpleadoJefeReemplazo(null);
						}
					undNegocioEntity.getDepartamentosArea().add(depAreaEntity);
				}
			}
			
			empresaJpaRepository.save(entity);
			empresaJpaRepository.flush();
			
			notificacion.setCodigo(1L);
			notificacion.setSeverity("success");
			notificacion.setSummary("Runakuna Success");
			notificacion.setDetail("Se registro correctamente");
		} catch (Exception e) {
			notificacion.setCodigo(0L);
			notificacion.setSeverity("error");
			notificacion.setSummary("Runakuna Error");
			notificacion.setDetail("No se registro la empresa.");
			e.printStackTrace();
		}
		
		return notificacion;
	}
	
}
