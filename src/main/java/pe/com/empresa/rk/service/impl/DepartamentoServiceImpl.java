package pe.com.empresa.rk.service.impl;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.empresa.rk.domain.model.entities.Departamento;
import pe.com.empresa.rk.domain.model.entities.DepartamentoArea;
import pe.com.empresa.rk.domain.model.entities.Pais;
import pe.com.empresa.rk.domain.model.entities.UnidadDeNegocio;
import pe.com.empresa.rk.service.DepartamentoService;
import pe.com.empresa.rk.view.model.CargoViewModel;
import pe.com.empresa.rk.view.model.DepartamentoAreaViewModel;
import pe.com.empresa.rk.view.model.DepartamentoViewModel;
import pe.com.empresa.rk.domain.model.entities.Cargo;
import pe.com.empresa.rk.domain.model.entities.Proyecto;
import pe.com.empresa.rk.domain.model.repository.jpa.CargoJpaRepository;
import pe.com.empresa.rk.domain.model.repository.jpa.DepartamentoAreaJpaRepository;
import pe.com.empresa.rk.domain.model.repository.jpa.DepartamentoJpaRepository;
import pe.com.empresa.rk.domain.model.repository.jpa.PaisJpaRepository;
import pe.com.empresa.rk.domain.model.repository.jpa.ProyectoJpaRepository;
import pe.com.empresa.rk.domain.model.repository.jpa.UndNegocioJpaRepository;
import pe.com.empresa.rk.view.model.ProyectoViewModel;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

	final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
    Mapper mapper;
	
	@Autowired
    UndNegocioJpaRepository undNegocioJpaRepository;
	
	@Autowired
    PaisJpaRepository paisJpaRepository;
	
	@Autowired
    DepartamentoJpaRepository departamentoJpaRepository;
	
	@Autowired
    DepartamentoAreaJpaRepository departamentoAreaJpaRepository;
	
	@Autowired
	ProyectoJpaRepository proyectoJpaRepository;
	
	@Autowired
    CargoJpaRepository cargoJpaRepository;

//	@Override
//	public List<DepartamentoDto> getDepartamentos() {
//		return departamentoRepository.findDepartamentos();
//	}

	@Override
	public List<DepartamentoViewModel> obtenerDepartamentosPorPais(String codigoPais) {
		Pais pais = paisJpaRepository.findByCodigo(codigoPais);
		
		List<Departamento> entities;
		List<DepartamentoViewModel> items;
		
		entities = departamentoJpaRepository.findByPais(pais.getIdPais());
		
		items = entities.stream().map(m -> mapper.map(m, DepartamentoViewModel.class)).collect(toList());
		
		return items;
	
	}

	@Override
	public List<DepartamentoAreaViewModel> obtenerDepaAreaPorUndNegocio(Long idUnidadDeNegocio) {
		UnidadDeNegocio undNegocio = undNegocioJpaRepository.findByCodigo(idUnidadDeNegocio);
		
		List<DepartamentoArea> entities;
		List<DepartamentoAreaViewModel> items;
		
		entities = departamentoAreaJpaRepository.findByUndNegocio(undNegocio.getIdUnidadDeNegocio());
		items = entities.stream().map(m -> mapper.map(m, DepartamentoAreaViewModel.class)).collect(toList());
		return items;
	}

	@Override
	public List<ProyectoViewModel> obtenerProyPorDepaArea(Long idDepartamentoArea) {
		DepartamentoArea depaArea = departamentoAreaJpaRepository.findByCodigo(idDepartamentoArea);
		
		List<Proyecto> entities;
		List<ProyectoViewModel> items;
		
		entities = proyectoJpaRepository.findByProyPorDepartamentoArea(depaArea.getIdDepartamentoArea());
		items = entities.stream().map(m -> mapper.map(m, ProyectoViewModel.class)).collect(toList());
		return items;
	}

	@Override
	public List<CargoViewModel> obtenerCargoPorProy(Long idProyecto) {
		
		Proyecto proyecto = proyectoJpaRepository.findByIdProyecto(idProyecto);
		
		List<Cargo> entities;
		List<CargoViewModel> items=null;
		
		entities = cargoJpaRepository.findByCargoPorProyecto(proyecto.getDepartamentoArea().getIdDepartamentoArea());
		items = entities.stream().map(m -> mapper.map(m, CargoViewModel.class)).collect(toList());
		return items;
	}

	@Override
	public List<CargoViewModel> getListCargos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<ProyectoViewModel> obtenerProyectos() {
		
		
		List<Proyecto> entities;
		List<ProyectoViewModel> items;
		
		entities = proyectoJpaRepository.findAll();
		
		items = entities.stream().map(m -> mapper.map(m, ProyectoViewModel.class)).collect(toList());
		return items;
	}

}
