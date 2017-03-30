package pe.com.empresa.rk.service.impl;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.empresa.rk.domain.model.repository.jpa.ProvinciaJpaRepository;
import pe.com.empresa.rk.domain.model.entities.Distrito;
import pe.com.empresa.rk.domain.model.entities.Provincia;
import pe.com.empresa.rk.domain.model.repository.jpa.DistritoJpaRepository;
import pe.com.empresa.rk.view.model.DistritoViewModel;
import pe.com.empresa.rk.service.DistritoService;

@Service
public class DistritoServiceImpl implements DistritoService{

	@Autowired
    Mapper mapper;
	
	@Autowired
    ProvinciaJpaRepository provinciaJpaRepository;
	

	@Autowired
	DistritoJpaRepository distritoJpaRepository;
	
	

	@Override
	public List<DistritoViewModel> obtenerDistritosPorProvincia(String codigoProvincia) {
		
		Provincia provincia = provinciaJpaRepository.findByCodigo(codigoProvincia);
		
		List<Distrito> entities;
		List<DistritoViewModel> items;
		
		entities = distritoJpaRepository.findByProvincia(provincia.getIdProvincia());
		
		items = entities.stream().map(m -> mapper.map(m, DistritoViewModel.class)).collect(toList());
		
		return items;
	}



}
