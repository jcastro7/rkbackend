package pe.com.tss.runakuna.service.impl;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.tss.runakuna.domain.model.entities.*;
import pe.com.tss.runakuna.domain.model.repository.jdbc.LicenciaEmpleadoRepository;
import pe.com.tss.runakuna.domain.model.repository.jdbc.PeriodoEmpleadoRepository;
import pe.com.tss.runakuna.domain.model.repository.jdbc.PeriodoEmpleadoTipoLicenciaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.*;
import pe.com.tss.runakuna.enums.SeverityStatusEnum;
import pe.com.tss.runakuna.exception.BusinessException;
import pe.com.tss.runakuna.service.LicenciaEmpleadoService;
import pe.com.tss.runakuna.view.model.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class LicenciaEmpleadoServiceImpl implements LicenciaEmpleadoService{

	private static final Logger LOGGER  = LoggerFactory.getLogger(LicenciaEmpleadoServiceImpl.class);
	
	@Autowired
	EmpleadoJpaRepository empleadoJpaRepository;
	
	@Autowired
	LicenciaEmpleadoRepository licenciaEmpleadoRepository;
	
	@Autowired
	LicenciaEmpleadoJpaRepository licenciaEmpleadoJpaRepository;
	
	@Autowired
	TipoLicenciaJpaRepository tipoLicenciaJpaRepository;
	
	@Autowired
	DocumentoEmpleadoJpaRepository documentoEmpleadoJpaRepository;
	
	@Autowired
	Mapper mapper;
	
	@Autowired
	PeriodoEmpleadoRepository periodoEmpleadoRepository;
	
	@Autowired
	PeriodoEmpleadoJpaRepository  periodoEmpleadoJpaRepository;
	
	@Autowired
	PeriodoEmpleadoTipoLicenciaRepository  periodoEmpleadoTipoLicenciaRepository;
	
	@Autowired
	PeriodoEmpleadoTipoLicenciaJpaRepository periodoEmpleadoTipoLicenciaJpaRepository;
	
	private NotificacionViewModel crearActualizarLicenciaEmpleado(LicenciaEmpleadoViewModel licenciaEmpleadoDto) {
		NotificacionViewModel notificacion = new NotificacionViewModel();
		

		Licencia entity=new Licencia();
		Empleado empleado=empleadoJpaRepository.findOne(licenciaEmpleadoDto.getIdEmpleado());
		TipoLicencia tipoLicencia=tipoLicenciaJpaRepository.findOne(licenciaEmpleadoDto.getIdTipoLicencia());
		try{
			mapper.map(licenciaEmpleadoDto, entity);
			entity.setEmpleado(empleado);
			entity.setTipoLicencia(tipoLicencia);
			entity.setDocumentosEmpleado(new ArrayList<>());
			if(licenciaEmpleadoDto.getDocumentos()!=null) {
				for (DocumentoEmpleadoViewModel documento : licenciaEmpleadoDto.getDocumentos()){
					DocumentoEmpleado documentoEntity=new DocumentoEmpleado();
					mapper.map(documento, documentoEntity );
					documentoEntity.setLicencia(entity);
					documentoEntity.setEmpleado(empleado);
					entity.getDocumentosEmpleado().add(documentoEntity);
				}
			}

			if(entity.getIdLicencia()==null){
				entity.setEstado("E");
			} else {
				entity.setEstado(licenciaEmpleadoDto.getEstado());
			}
			
			Empleado atendidoPor=empleadoJpaRepository.findOne(licenciaEmpleadoDto.getIdAtendidoPor());
			entity.setAtendidoPor(atendidoPor);
			
			licenciaEmpleadoJpaRepository.save(entity);
			licenciaEmpleadoJpaRepository.flush();
			notificacion.setCodigo(1L);
			notificacion.setSeverity("success");
			notificacion.setSummary("Runakuna Success");
			notificacion.setDetail("Se registro correctamente.");
		} catch(Exception e) {
			LOGGER.info(e.getMessage(), e);
			notificacion.setCodigo(0L);
			notificacion.setSeverity("error");
			notificacion.setSummary("Runakuna Error");
			notificacion.setDetail("No es posible registrar, "+e.getMessage());
		}
		return notificacion;
	}

	@Override
	public NotificacionViewModel aprobarLicenciaEmpleado(LicenciaEmpleadoViewModel licenciaEmpleadoDto) {
		licenciaEmpleadoDto.setEstado("A");
		NotificacionViewModel notificacionDto = new NotificacionViewModel();
		try{
			notificacionDto=update(licenciaEmpleadoDto);
			notificacionDto.setCodigo(1L);
			notificacionDto.setDetail("Se aprobo correctamente");
		} catch (Exception e) {
			LOGGER.info(e.getMessage(), e);
			notificacionDto.setCodigo(0L);
			notificacionDto.setDetail("No se pudo aprobar "+e.getMessage());
		}
		
		return notificacionDto;
	}
	
	@Override
	public NotificacionViewModel validarLicenciaEmpleado(LicenciaEmpleadoViewModel licenciaEmpleadoDto) {
		licenciaEmpleadoDto.setEstado("V");
		NotificacionViewModel notificacionDto=new NotificacionViewModel();
		try{
			notificacionDto=update(licenciaEmpleadoDto);
			Empleado empleado=empleadoJpaRepository.findOne(licenciaEmpleadoDto.getIdEmpleado());
			EmpleadoViewModel empleadoDto=new EmpleadoViewModel();
			empleadoDto.setIdEmpleado(empleado.getIdEmpleado());
			PeriodoEmpleadoViewModel periodoEmpleadoDto=	periodoEmpleadoRepository.obtenerPeriodoEmpleadoActual(empleadoDto);
			
			PeriodoEmpleadoTipoLicenciaViewModel periodoEmpleadoTipoLicenciaDto=new PeriodoEmpleadoTipoLicenciaViewModel();
			periodoEmpleadoTipoLicenciaDto.setIdPeriodoEmpleado(periodoEmpleadoDto.getIdPeriodoEmpleado());
			periodoEmpleadoTipoLicenciaDto.setIdTipoLicencia(licenciaEmpleadoDto.getIdTipoLicencia());
			List<PeriodoEmpleadoTipoLicenciaViewModel> periodoEmpleadoTipoLicenciaResultDto = periodoEmpleadoTipoLicenciaRepository.obtenerDiasPorPeriodoEmpleadoTipoLicencia(periodoEmpleadoTipoLicenciaDto);
			if(periodoEmpleadoTipoLicenciaResultDto!=null && periodoEmpleadoTipoLicenciaResultDto.size()>0 &&
					periodoEmpleadoTipoLicenciaResultDto.get(0).getDias().intValue()>=0){
				PeriodoEmpleadoTipoLicencia entity=periodoEmpleadoTipoLicenciaJpaRepository.findOne(periodoEmpleadoTipoLicenciaResultDto.get(0).getIdPeriodoEmpleadoTipoLicencia());
				entity.setDiasLicencia(entity.getDiasLicencia()+licenciaEmpleadoDto.getDias());
				periodoEmpleadoTipoLicenciaJpaRepository.save(entity);
			} else{
				PeriodoEmpleadoTipoLicencia entity=new PeriodoEmpleadoTipoLicencia();
				PeriodoEmpleado periodoEmpleado=periodoEmpleadoJpaRepository.findOne(periodoEmpleadoDto.getIdPeriodoEmpleado());
				TipoLicencia tipoLicencia=tipoLicenciaJpaRepository.findOne(licenciaEmpleadoDto.getIdTipoLicencia());
				entity.setDiasLicencia(licenciaEmpleadoDto.getDias());
				entity.setTipoLicencia(tipoLicencia);
				entity.setPeriodoEmpleado(periodoEmpleado);
				periodoEmpleadoTipoLicenciaJpaRepository.save(entity);
			}
			notificacionDto.setCodigo(1L);
			notificacionDto.setSeverity("success");
			notificacionDto.setSummary("Runakuna Success");
			notificacionDto.setDetail("Se aprobo correctamente");
			
		} catch (Exception e) {
			LOGGER.info(e.getMessage(), e);
			notificacionDto.setCodigo(0L);
			notificacionDto.setSeverity("error");
			notificacionDto.setSummary("Runakuna Error");
			notificacionDto.setDetail("No se pudo aprobar "+e.getMessage());
		}
		
		return notificacionDto;
	}

	@Override
	public List<TipoLicenciaViewModel> obtenerTipoLicencia() {
		return licenciaEmpleadoRepository.getTipoLicencias();
	}
	@Override
	public List<LicenciaEmpleadoResultViewModel> search(LicenciaEmpleadoFilterViewModel filterViewModel) {
		
		return licenciaEmpleadoRepository.obtenerLicencias(filterViewModel);
	}
	@Override
	public LicenciaEmpleadoViewModel findOne(Long idLicencia) {
		LicenciaEmpleadoViewModel dto=new LicenciaEmpleadoViewModel();
		dto.setDocumentos(new ArrayList<>());
		Licencia licencia=licenciaEmpleadoJpaRepository.findOne(idLicencia);
		mapper.map(licencia, dto);
		dto.setIdEmpleado(licencia.getEmpleado().getIdEmpleado());
		dto.setIdAtendidoPor(licencia.getAtendidoPor().getIdEmpleado());
		dto.setNombreEmpleado(licencia.getEmpleado().getApellidoPaterno()+" "+licencia.getEmpleado().getApellidoMaterno()+" "+licencia.getEmpleado().getNombre());
		dto.setIdTipoLicencia(licencia.getTipoLicencia().getIdTipoLicencia());
		for(DocumentoEmpleado documentoEmpleado:licencia.getDocumentosEmpleado()){
			DocumentoEmpleadoViewModel docEmpdto=new DocumentoEmpleadoViewModel();
			mapper.map(documentoEmpleado, docEmpdto);
			dto.getDocumentos().add(docEmpdto);
		}
		
		return dto;
	}
	@Override
	public NotificacionViewModel create(LicenciaEmpleadoViewModel manteinanceViewModel) {
		NotificacionViewModel notificacion;
		notificacion=crearActualizarLicenciaEmpleado(manteinanceViewModel);
		return notificacion;
	}
	@Override
	public NotificacionViewModel update(LicenciaEmpleadoViewModel manteinanceViewModel) {
		NotificacionViewModel notificacion;
		notificacion=crearActualizarLicenciaEmpleado(manteinanceViewModel);
		return notificacion;
	}
	@Override
	public NotificacionViewModel delete(Long idLicencia) {
		NotificacionViewModel notificacion = new NotificacionViewModel();
		if (licenciaEmpleadoJpaRepository.findOne(idLicencia) == null) {
			throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", "The record was changed by another user. Please re-enter the screen.");
		}
		try {
			licenciaEmpleadoJpaRepository.delete(idLicencia);
			licenciaEmpleadoJpaRepository.flush();
			
			notificacion.setCodigo(1L);
			notificacion.setSeverity(SeverityStatusEnum.SUCCESS.getCode());
			notificacion.setSummary("Runakuna Success");
			notificacion.setDetail("Registro eliminado correctamente.");
			
		} catch (Exception e) {
			throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", "Restriction is Being used, Can't be deleted");
		
		}
		return notificacion;
	}
	
	@Override
	public List<LicenciaEmpleadoResultViewModel> quickSearch(QuickFilterViewModel quickFilter) {
		return licenciaEmpleadoRepository.busquedaRapidaLicencias(quickFilter);
	}
	
	@Override
	public List<LicenciaViewModel> verLicencias(PeriodoEmpleadoViewModel periodoEmpleado) {
		List<LicenciaViewModel> dto;
		dto = licenciaEmpleadoRepository.verLicencias(periodoEmpleado);

		for (LicenciaViewModel licenciaViewModel : dto) {
			List<DocumentoEmpleadoViewModel> lincenciasDocDto;
			List<DocumentoEmpleado> licenciasDoc = documentoEmpleadoJpaRepository.findAllOnlyLicencias(licenciaViewModel.getIdEmpleado(),licenciaViewModel.getIdLicencia());
			lincenciasDocDto = licenciasDoc.stream().map(m -> mapper.map(m, DocumentoEmpleadoViewModel.class)).collect(toList());
			licenciaViewModel.setDocumentos(lincenciasDocDto);
			
		}
		
		return dto;
	}
	
	@Override
	public NotificacionViewModel actualizarLicencia(LicenciaViewModel licencia) {
		NotificacionViewModel notificacion = new NotificacionViewModel();

		try{
			
			Licencia entity=new Licencia();
			
			mapper.map(licencia, entity);
			
			Empleado empleado=empleadoJpaRepository.findOne(licencia.getIdEmpleado());
			Empleado atendidoPor=empleadoJpaRepository.findOne(licencia.getIdAtendidoPor());
			TipoLicencia tipoLicencia=tipoLicenciaJpaRepository.findOne(licencia.getIdTipoLicencia());
			
			entity.setEmpleado(empleado);
			entity.setTipoLicencia(tipoLicencia);
			entity.setAtendidoPor(atendidoPor);
			
			entity.setDocumentosEmpleado(new ArrayList<>());
			
			if(licencia.getDocumentos() !=null){
				for (DocumentoEmpleadoViewModel documento : licencia.getDocumentos()) {

					DocumentoEmpleado documentoEntity = new DocumentoEmpleado();
					mapper.map(documento, documentoEntity);
					documentoEntity.setLicencia(entity);
					entity.getDocumentosEmpleado().add(documentoEntity);
				}
			}

			licenciaEmpleadoJpaRepository.save(entity);
			licenciaEmpleadoJpaRepository.flush();
			
			notificacion.setCodigo(1L);
			notificacion.setDetail("Se registro correctamente");
		} catch(Exception e) {
			LOGGER.info(e.getMessage(), e);

			notificacion.setCodigo(0L);
			notificacion.setDetail("No es posible registrar, "+e.getMessage());
		}
		return notificacion;
	}

}
