package pe.com.tss.runakuna.service.impl;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.tss.runakuna.domain.model.entities.Marcacion;
import pe.com.tss.runakuna.domain.model.entities.SolicitudCambioMarcacion;
import pe.com.tss.runakuna.domain.model.repository.jdbc.EmpleadoRepository;
import pe.com.tss.runakuna.domain.model.repository.jdbc.MarcacionRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.EmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.MarcacionJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.SolicitudCambioMarcacionJpaRepository;
import pe.com.tss.runakuna.service.MarcacionEmpleadoService;
import pe.com.tss.runakuna.view.model.*;

import java.util.List;

@Service
public class MarcacionEmpleadoServiceImpl implements MarcacionEmpleadoService {

	@Autowired
	Mapper mapper;
	
	@Autowired
	MarcacionJpaRepository marcacionJpaRepository;
	
	@Autowired
	MarcacionRepository marcacionJdbcRepository;
	
	@Autowired
	EmpleadoJpaRepository empleadoJpaRepository;
	
	@Autowired
	EmpleadoRepository empleadoRepository;
	
	@Autowired
	SolicitudCambioMarcacionJpaRepository solicitudCambioMarcacionJpaRepository;
	

	private static final Logger log = LoggerFactory.getLogger(MarcacionEmpleadoServiceImpl.class);

	@Override
	public MarcacionViewModel obtenerMarcacionPorEmpleadoyFechaActual(EmpleadoViewModel empleado) {
		
		MarcacionViewModel dto = marcacionJdbcRepository.obtenerMarcacionesPorEmpleadoyFechaActual(empleado);
		
		return dto;
	}

	@Override
	public NotificacionViewModel registrarSolicitudCambioMarcacion(SolicitudCambioMarcacionViewModel solicitudCambioMarcacion) {
		
		NotificacionViewModel notificacion = new NotificacionViewModel();
		
		SolicitudCambioMarcacion entity = new SolicitudCambioMarcacion();
		
		if(solicitudCambioMarcacion.getIdSolicitudCambioMarcacion()!=null){
			entity = solicitudCambioMarcacionJpaRepository.findOne(solicitudCambioMarcacion.getIdSolicitudCambioMarcacion());
		}
		
		Marcacion marcacion = marcacionJpaRepository.findOne(solicitudCambioMarcacion.getIdMarcacion());
		
		//Empleado empleado = empleadoJpaRepository.findOne(solicitudCambioMarcacion.getIdAtendidoPor());
		
		mapper.map(solicitudCambioMarcacion, entity);
		
		entity.setMarcacion(marcacion);
		//entity.setEmpleado(empleado);
		
		solicitudCambioMarcacionJpaRepository.save(entity);
		solicitudCambioMarcacionJpaRepository.flush();
		
		notificacion.setCodigo(1L);
		notificacion.setDetail("Se registro Correctamente.");
		return notificacion;
	}
	
	@Override
	public SolicitudCambioMarcacionViewModel obtenerSolicitudCambio(Long idMarcacion) {
		
		SolicitudCambioMarcacionViewModel dto = new SolicitudCambioMarcacionViewModel();
		
		MarcacionViewModel marcacion = marcacionJdbcRepository.findById(idMarcacion);
		
		if(marcacion == null){
			return dto;
		}
		
		SolicitudCambioMarcacion solicitud = solicitudCambioMarcacionJpaRepository.findByIdMarcacion(marcacion.getIdMarcacion());
		
		if(solicitud == null){
			dto.setTieneSolicitudCambio(0);
		}else{
			dto.setTieneSolicitudCambio(1);
			mapper.map(solicitud, dto);
		}
		
		dto.setMarcacion(marcacion);
		return dto;
	}

	@Override
	public List<MarcacionViewModel> quickSearch(QuickFilterViewModel quickFilter) {
		return empleadoRepository.busquedaRapidaMarcacionEmpleado(quickFilter);
	}
	
	@Override
	public List<MarcacionViewModel> getMarcacionesByFiltro(MarcacionFilterViewModel filter) {
		return marcacionJdbcRepository.getMarcacionesByFiltro(filter);
	}
	
	
}
