package pe.com.empresa.rk.service.impl;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.empresa.rk.domain.model.entities.Departamento;
import pe.com.empresa.rk.domain.model.entities.Provincia;
import pe.com.empresa.rk.domain.model.repository.jpa.DepartamentoJpaRepository;
import pe.com.empresa.rk.domain.model.repository.jpa.ProvinciaJpaRepository;
import pe.com.empresa.rk.service.ProvinciaService;
import pe.com.empresa.rk.view.model.ProvinciaViewModel;

@Service
public class ProvinciaServiceImpl implements ProvinciaService {

	@Autowired
    Mapper mapper;
	
	@Autowired
    DepartamentoJpaRepository departamentoJpaRepository;
	
	@Autowired
    ProvinciaJpaRepository provinciaJpaRepository;
	
	@Override
	public List<ProvinciaViewModel> obtenerProvinciasPorDepartamento(String codigoDepartamento) {
		Departamento dpto = departamentoJpaRepository.findByCodigo(codigoDepartamento);
		
		List<Provincia> entities;
		List<ProvinciaViewModel> items;
		
		entities = provinciaJpaRepository.findByDepartamento(dpto.getIdDepartamento());
		
		items = entities.stream().map(m -> mapper.map(m, ProvinciaViewModel.class)).collect(toList());
		
		return items;
	
	}



}
