package pe.com.tss.runakuna.service.impl;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.tss.runakuna.domain.model.entities.Pais;
import pe.com.tss.runakuna.domain.model.repository.jpa.PaisJpaRepository;
import pe.com.tss.runakuna.view.model.PaisViewModel;
import pe.com.tss.runakuna.service.PaisService;

@Service
public class PaisServiceImpl implements PaisService{

	@Autowired
    Mapper mapper;
	
	@Autowired
	PaisJpaRepository paisJpaRepository;
	
	@Override
	public List<PaisViewModel> obtenerPaises() {
		List<Pais> entities;
		List<PaisViewModel> items;
		
		entities = paisJpaRepository.findAll();
		
		items = entities.stream().map(m -> mapper.map(m, PaisViewModel.class)).collect(toList());
		
		return items;
	}

}
