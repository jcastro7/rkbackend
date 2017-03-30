package pe.com.tss.runakuna.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.tss.runakuna.domain.model.entities.Contrato;
import pe.com.tss.runakuna.domain.model.entities.Empleado;
import pe.com.tss.runakuna.domain.model.entities.Moneda;
import pe.com.tss.runakuna.domain.model.entities.TablaGeneral;
import pe.com.tss.runakuna.domain.model.repository.jdbc.ContratoRepository;
import pe.com.tss.runakuna.domain.model.repository.jdbc.HistoriaLaboralRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.ContratoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.EmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.MonedaJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.TablaGeneralJpaRepository;
import pe.com.tss.runakuna.enums.SeverityStatusEnum;
import pe.com.tss.runakuna.exception.BusinessException;
import pe.com.tss.runakuna.view.model.ContratoFilterViewModel;
import pe.com.tss.runakuna.view.model.ContratoResultViewModel;
import pe.com.tss.runakuna.view.model.ContratoViewModel;
import pe.com.tss.runakuna.view.model.EmpleadoViewModel;
import pe.com.tss.runakuna.view.model.HistoriaLaboralViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;
import pe.com.tss.runakuna.service.ContratoService;

@Service
public class ContratoServiceImpl implements ContratoService{

	final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	ContratoJpaRepository contratoJpaRepository;
	
	@Autowired
	EmpleadoJpaRepository empleadoJpaRepository;
	
	@Autowired
	MonedaJpaRepository monedaJpaRepository;
	
	@Autowired
	TablaGeneralJpaRepository tablaGeneralJpaRepository;
	
	@Autowired
	ContratoRepository contratoJdbcRepository;
	
	@Autowired
	HistoriaLaboralRepository historiaLaboralRepository;
	
	@Autowired
	Mapper mapper;


	@Override
	public List<ContratoViewModel> obtenerContratosPorEmpleado(Long idEmpleado) {
		List<ContratoViewModel> contratos = contratoJdbcRepository.obtenerContratosPorEmpleado(idEmpleado);
		return contratos;
	}
	
	@Override
	public List<ContratoResultViewModel> busquedaContratosPorEmpleado(Long idEmpleado) {
		List<ContratoResultViewModel> contratos = contratoJdbcRepository.busquedaContratosPorEmpleado(idEmpleado);
		return contratos;
	}
	
	@Override
	public NotificacionViewModel aprobar(ContratoViewModel dto) {
		NotificacionViewModel notificacion = new NotificacionViewModel();
		
		Contrato entity = new Contrato();
		
		mapper.map(dto, entity);
		
		Empleado empleadoEntity = empleadoJpaRepository.findOne(dto.getIdEmpleado());
		
		entity.setEmpleado(empleadoEntity);
		
		Moneda monedaEntity = monedaJpaRepository.findOne(dto.getIdMoneda());
		entity.setMoneda(monedaEntity);
		
		contratoJpaRepository.save(entity);
		contratoJpaRepository.flush();
		
		notificacion.setCodigo(1L);
		notificacion.setDetail("Se registro Correctamente");
		return notificacion;
	}

	@Override
	public List<ContratoResultViewModel> search(ContratoFilterViewModel filterViewModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContratoViewModel findOne(Long id) {
		ContratoViewModel dto = new ContratoViewModel();
		Contrato entity = new Contrato();
		
		entity = contratoJpaRepository.findOne(id);
		EmpleadoViewModel empleado =new EmpleadoViewModel();
		empleado.setIdEmpleado(entity.getEmpleado().getIdEmpleado());
		
		mapper.map(entity, dto);
		
		dto.setIdEmpleado(entity.getEmpleado().getIdEmpleado());
		dto.setIdMoneda(entity.getMoneda().getIdMoneda());
		
		dto.setNombreCompletoEmpleado(entity.getEmpleado().getNombre()+" "+entity.getEmpleado().getApellidoPaterno()+" "+entity.getEmpleado().getApellidoMaterno());
		
		TablaGeneral estado = tablaGeneralJpaRepository.findByGrupoAndCodigo("Contrato.Estado", entity.getEstado());
		
		dto.setEstadoString(estado.getNombre());
		
		TablaGeneral contrato = tablaGeneralJpaRepository.findByGrupoAndCodigo("Empleado.ContratoTrabajo", entity.getTipoContrato());
		
		dto.setTipoContratoString(contrato.getNombre());
		

		return dto;
	}

	@Override
	public NotificacionViewModel create(ContratoViewModel manteinanceViewModel) {
		
		NotificacionViewModel notificacion = new NotificacionViewModel();
		notificacion = createOrUpdateContrato(manteinanceViewModel);
		return notificacion;
	}

	@Override
	public NotificacionViewModel update(ContratoViewModel manteinanceViewModel) {
		NotificacionViewModel notificacion = new NotificacionViewModel();
		notificacion = createOrUpdateContrato(manteinanceViewModel);
		return notificacion;
	}

	@Override
	public NotificacionViewModel delete(Long id) {
		
		NotificacionViewModel notificacion=new NotificacionViewModel();
		if (contratoJpaRepository.findOne(id) == null) {
			throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", "The record was changed by another user. Please re-enter the screen.");
		}
		try {
			contratoJpaRepository.delete(id);
			contratoJpaRepository.flush();
			
			notificacion.setCodigo(1L);
            notificacion.setSeverity(SeverityStatusEnum.SUCCESS.getCode());
			notificacion.setSummary("Runakuna Success");
            notificacion.setDetail("Registro fue eliminado correctamente.");			
			
		} catch (Exception e) {
			throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", "Restriction is Being used, Can't be deleted");
		}
		return notificacion;
	}
	
	private NotificacionViewModel createOrUpdateContrato(ContratoViewModel manteinanceViewModel){
		
		NotificacionViewModel notificacion = new NotificacionViewModel();
		
		Contrato entity = new Contrato();
		
		mapper.map(manteinanceViewModel, entity);
		
		Empleado empleadoEntity = empleadoJpaRepository.findOne(manteinanceViewModel.getIdEmpleado());
		
		entity.setTipoContrato(manteinanceViewModel.getTipoContrato());
		
		entity.setEmpleado(empleadoEntity);
		
		Moneda monedaEntity = monedaJpaRepository.findOne(manteinanceViewModel.getIdMoneda());
		entity.setMoneda(monedaEntity);
		
		contratoJpaRepository.save(entity);
		contratoJpaRepository.flush();
		
		notificacion.setCodigo(1L);
		notificacion.setDetail("Se registro Correctamente");
		return notificacion;
	}
	
	@Override
	public HistoriaLaboralViewModel obtenerHistorialLaboralActualPorEmpleado(Long idEmpleado) {
		
		HistoriaLaboralViewModel result = new HistoriaLaboralViewModel();
		List<HistoriaLaboralViewModel> dto = new ArrayList<>();
		
		dto = historiaLaboralRepository.obtenerHistoriasLaboralesPorEmpleadoContrato(idEmpleado);
		if(dto != null && dto.size()>0){
			result = dto.get(0);
		}
		
		return result;
	}

}
