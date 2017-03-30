package pe.com.tss.runakuna.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.icu.impl.StringRange;

import pe.com.tss.runakuna.domain.model.entities.*;
import pe.com.tss.runakuna.domain.model.repository.jdbc.EmpleadoJdbcRepository;
import pe.com.tss.runakuna.domain.model.repository.jdbc.EmpleadoRepository;
import pe.com.tss.runakuna.domain.model.repository.jdbc.HistoriaLaboralRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.*;
import pe.com.tss.runakuna.enums.SeverityStatusEnum;
import pe.com.tss.runakuna.exception.BusinessException;
import pe.com.tss.runakuna.exception.GenericRestException;
import pe.com.tss.runakuna.gateway.common.ValidationUtils;
import pe.com.tss.runakuna.rest.EmpleadoController;
import pe.com.tss.runakuna.service.EmpleadoService;
import pe.com.tss.runakuna.util.StringUtil;
import pe.com.tss.runakuna.view.model.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

	@Autowired
	EmpleadoJpaRepository empleadoJpaRepository;

	@Autowired
	DocumentoEmpleadoJpaRepository documentoEmpleadoJpaRepository;

	@Autowired
	EducacionJpaRepository educacionJpaRepository;

	@Autowired
	ExperienciaLaboralJpaRepository experienciaLaboralJpaRepository;

	@Autowired
	EquipoEntregadoJpaRepository equipoEntregadoJpaRepository;

	@Autowired
	DependienteJpaRepository dependienteJpaRepository;

	@Autowired
	EmpresaJpaRepository empresaJpaRepository;

	@Autowired
	CentroCostoJpaRepository centroCostoJpaRepository;

	@Autowired
	HistoriaLaboralRepository historiaLaboralRepository;

    @Autowired
    TablaGeneralJpaRepository tablaGeneralJpaRepository;


	@Autowired
	PeriodoEmpleadoJpaRepository periodoEmpleadoJpaRepository;

	@Autowired
	Mapper mapper;

	@Autowired
	EmpleadoJdbcRepository empleadoJdbcRepository;

    @Autowired
    PermisoEmpleadoJpaRepository permisoEmpleadoJpaRepository;

    @Autowired
    HorarioEmpleadoJpaRepository horarioEmpleadoJpaRepository;

    @Autowired
    HorasExtraJpaRepository horasExtraJpaRepository;

    @Autowired
    HorarioJpaRepository horarioJpaRepository;

    @Autowired
    HorarioEmpleadoDiaJpaRepository horarioEmpleadoDiaJpaRepository;
    
    @Autowired
    HistorialLaboralJpaRepository historialLaboralJpaRepository;

	private static final Logger log = LoggerFactory.getLogger(EmpleadoController.class);

	@Override
	public NotificacionViewModel registrarDarBajaEmpleado(EmpleadoViewModel empleado) {
		NotificacionViewModel notificacion = new NotificacionViewModel();
		Empleado entity = new Empleado();

		mapper.map(empleado, entity);

		entity.setDocumentosEmpleado(new ArrayList<>());

		if(empleado.getDocumentos() !=null){
			for (DocumentoEmpleadoViewModel documento : empleado.getDocumentos()) {

				DocumentoEmpleado documentoEntity = new DocumentoEmpleado();

				mapper.map(documento, documentoEntity);

				documentoEntity.setEmpleado(entity);
				entity.getDocumentosEmpleado().add(documentoEntity);
			}
		}

		Empresa empresa = empresaJpaRepository.findOne(empleado.getIdEmpresa());
		CentroCosto centroCosto = centroCostoJpaRepository.findOne(empleado.getIdCentroCosto());

		entity.setEmpresa(empresa);
		entity.setCentroCosto(centroCosto);

		entity = empleadoJpaRepository.save(entity);
		empleadoJpaRepository.flush();

		notificacion.setCodigo(1L);
		notificacion.setDetail("Se registro Correctamente");
		return notificacion;
	}


	@Override
	@Transactional
	public NotificacionViewModel actualizarDatosPersonales(EmpleadoViewModel empleado) {

		NotificacionViewModel notificacion = new NotificacionViewModel();
		Empleado entity = new Empleado();

		mapper.map(empleado, entity);
		
		if(empleado.getDocumentos()==null){
			empleado.setDocumentos(new ArrayList<>());
		}
		
		if(empleado.getFotoPerfil() != null){
			empleado.getDocumentos().add(empleado.getFotoPerfil());
			
		}
		
		entity.setDocumentosEmpleado(new ArrayList<>());

		if(empleado.getDocumentos() != null){
			for (DocumentoEmpleadoViewModel documento : empleado.getDocumentos()) {

				DocumentoEmpleado documentoEntity = new DocumentoEmpleado();
				mapper.map(documento, documentoEntity);
				documentoEntity.setEmpleado(entity);
				entity.getDocumentosEmpleado().add(documentoEntity);
			}
		}
		
		entity.setExperienciasLaboralesEmpleado(new ArrayList<>());

		if(empleado.getExperienciasLaborales() !=null){
			for (ExperienciaLaboralViewModel experienciaLaboral : empleado.getExperienciasLaborales()) {
				ExperienciaLaboral experienciaLaboralEntity = new ExperienciaLaboral();

				mapper.map(experienciaLaboral, experienciaLaboralEntity);
				experienciaLaboralEntity.setEmpleado(entity);

				entity.getExperienciasLaboralesEmpleado().add(experienciaLaboralEntity);
			}
		}

		entity.setDependientesEmpleado(new ArrayList<>());

		if(empleado.getDependientes() !=null){
			for (DependienteViewModel dependiente : empleado.getDependientes()) {
				Dependiente dependienteEntity = new Dependiente();

				mapper.map(dependiente,dependienteEntity);
				dependienteEntity.setEmpleado(entity);

				entity.getDependientesEmpleado().add(dependienteEntity);
			}
		}
		
		entity.setEducacionesEmpleado(new ArrayList<>());

		if(empleado.getEducaciones() !=null){
			for (EducacionViewModel educacion : empleado.getEducaciones()) {
				Educacion educacionEntity = new Educacion();

				mapper.map(educacion,educacionEntity);
				educacionEntity.setEmpleado(entity);

				entity.getEducacionesEmpleado().add(educacionEntity);
			}
		}
		
		entity.setEquiposEntregadoEmpleado(new ArrayList<>());

		if(empleado.getEquiposEntregados() !=null){
			for (EquipoEntregadoViewModel equipos : empleado.getEquiposEntregados()) {
				EquipoEntregado equipoEntity = new EquipoEntregado();

				mapper.map(equipos,equipoEntity);
				equipoEntity.setEmpleado(entity);

				entity.getEquiposEntregadoEmpleado().add(equipoEntity);
			}
		}

		Empresa empresa = empresaJpaRepository.findOne(empleado.getIdEmpresa());
		CentroCosto centroCosto = centroCostoJpaRepository.findOne(empleado.getIdCentroCosto());

		entity.setEmpresa(empresa);
		entity.setCentroCosto(centroCosto);

		entity = empleadoJpaRepository.save(entity);
		empleadoJpaRepository.flush();

		notificacion.setCodigo(1L);
		notificacion.setSeverity("success");
		notificacion.setSummary("Runakuna Success");
		notificacion.setDetail("Se actualizo los datos personales Correctamente.");
		return notificacion;
	}

	@Override
	public List<HistoriaLaboralViewModel> obtenerHistoriaLaboral(Long idEmpleado) {
		// TODO Auto-generated method stub
		return historiaLaboralRepository.obtenerHistoriaLaboral(idEmpleado);
	}

	@Override
	public List<HistoriaLaboralViewModel> obtenerIdHistoriaLaboral(Long idHistorialLaboral) {
		// TODO Auto-generated method stub
		return historiaLaboralRepository.obtenerIdHistoriaLaboral(idHistorialLaboral);
	}

	@Override
	public List<EmpleadoResultViewModel> busquedaEmpleado(EmpleadoFilterViewModel busquedaEmpleadoDto) {
		return empleadoJdbcRepository.busquedaEmpleado(busquedaEmpleadoDto);
	}

	@Override
	public List<MarcacionViewModel> busquedaMarcacionesEmpleado(MarcacionFilterViewModel busquedaMarcacionDto) {
		return empleadoJdbcRepository.busquedaMarcacionEmpleado(busquedaMarcacionDto);
	}

	@Override
	public List<DocumentoEmpleadoViewModel> verDocumentos(Long idEmpleado) {

		List<DocumentoEmpleado> entity;
		List<DocumentoEmpleadoViewModel> dto = new ArrayList<>();
		entity = documentoEmpleadoJpaRepository.findAllOnlyPersonal(idEmpleado);
		if(entity != null)
			dto = entity.stream().map(m -> mapper.map(m, DocumentoEmpleadoViewModel.class)).collect(toList());

		return dto;
	}

	@Override
	public List<EducacionViewModel> verEducacion(Long idEmpleado) {

		List<EducacionViewModel> dto = new ArrayList<>();
		dto = empleadoJdbcRepository.verEducacion(idEmpleado);
		return dto;
	}

	@Override
	public List<ExperienciaLaboralViewModel> verExperienciaLaboral(Long idEmpleado) {

		List<ExperienciaLaboral> entity;
		List<ExperienciaLaboralViewModel> dto = new ArrayList<>();
		entity = experienciaLaboralJpaRepository.buscarExperienciaLaboralPorEmpleado(idEmpleado);
		if(entity != null)
			dto = entity.stream().map(m -> mapper.map(m, ExperienciaLaboralViewModel.class)).collect(toList());

		return dto;
	}

	@Override
	public List<EquipoEntregadoViewModel> verEquipoEntregado(Long idEmpleado) {

		List<EquipoEntregadoViewModel> dto = new ArrayList<>();
		dto = empleadoJdbcRepository.verEquipoEntregado(idEmpleado);
		return dto;
	}

	@Override
	public List<DependienteViewModel> verDependiente(Long idEmpleado) {

		List<DependienteViewModel> dto = new ArrayList<>();
		dto = empleadoJdbcRepository.verDependiente(idEmpleado);
		return dto;
	}

	@Override
	public List<LicenciaViewModel> verLicencia(PeriodoEmpleadoViewModel periodoEmpleado) {

		List<LicenciaViewModel> dto = new ArrayList<>();
		if(periodoEmpleado.getIdPeriodoEmpleado() != null){

			PeriodoEmpleado entity = periodoEmpleadoJpaRepository.findOne(periodoEmpleado.getIdPeriodoEmpleado());
			if(entity != null){
				periodoEmpleado.setFechaInicio(entity.getFechaInicio());
				periodoEmpleado.setFechaFin(entity.getFechaFin());
			}else{
				periodoEmpleado.setIdPeriodoEmpleado(null);
			}
		}
		dto = empleadoJdbcRepository.verLicencia(periodoEmpleado);



		return dto;
	}

    @Override
    public List<EmpleadoViewModel> procesarMasivamenteEmpleados(List<EmpleadoViewModel> dtos) {

        try {

            ListEmpleadosNuevosYAntiguosViewModel listEmpleadosNuevosYAntiguos = validarSiEmpleadosExisten(dtos);

            validarInfoDeEmpleados(listEmpleadosNuevosYAntiguos.getEmpleadosAntiguos(), true);

            processUpdateEmpleado(listEmpleadosNuevosYAntiguos.getEmpleadosAntiguos());


            validarInfoDeEmpleados(listEmpleadosNuevosYAntiguos.getEmpleadosNuevos(), false);


            processSaveEmpleado(listEmpleadosNuevosYAntiguos.getEmpleadosNuevos());

            return dtos;
        } catch (Exception e) {
            log.debug("ERROR", e);
            String msg = e.getMessage() == null ? "" : e.getMessage();

            throw new GenericRestException("ERR_001", msg);
        }
    }

    private void processSaveEmpleado(List<EmpleadoViewModel> dtos) {

        try {

            for(EmpleadoViewModel empleadoItem :dtos){
                if(empleadoItem.getStatusProcessDtoList().size()==0){


                    Long  idEmpleado = processDataSaveEmpleado(empleadoItem);

                    empleadoItem.setIdEmpleado(idEmpleado);
                    StatusProcessViewModel statusProcessDto = new StatusProcessViewModel();
                    statusProcessDto.setStatus("Processed");
                    statusProcessDto.setMessage("");
                    empleadoItem.getStatusProcessDtoList().add(statusProcessDto);
                    log.info("Processed ");


                }

            }
        }catch (Exception e){
            log.debug("ERROR", e);

            String msg = e.getMessage()==null?"":e.getMessage();

            throw new GenericRestException("ERR_001",msg);
        }



    }

    private Long processDataSaveEmpleado(EmpleadoViewModel dto) {


        try {
            log.info("Creando Empleado");
            Empleado empleado = new Empleado();
            empleado.setCodigo(dto.getCodigo());
            empleado.setNombre(dto.getNombre());
            empleado.setApellidoMaterno(dto.getApellidoMaterno());
            empleado.setApellidoPaterno(dto.getApellidoPaterno());
            empleado.setTipoDocumento(dto.getTipoDocumento());
            empleado.setNumeroDocumento(dto.getNumeroDocumento());
            empleado.setGenero(dto.getGenero());
            empleado.setEstadoCivil(dto.getEstadoCivil());
            empleado.setGrupoSangre(dto.getGrupoSangre());
            empleado.setDiscapacitado(dto.getDiscapacitado());
            empleado.setFechaNacimiento(dto.getFechaNacimiento());
            empleado.setPaisNacimiento(dto.getPaisNacimiento());
            empleado.setEmailInterno(dto.getEmailInterno());
            empleado.setTelefonoInterno(dto.getTelefonoInterno());
            empleado.setAnexoInterno(dto.getAnexoInterno());
            empleado.setCentroCosto(dto.getCentroCosto());
            empleado.setContratoTrabajo(dto.getContratoTrabajo());
            empleado.setTipoTrabajador(dto.getTipoTrabajador());
            empleado.setRegimenHorario(dto.getRegimenHorario());
            empleado.setRegimenLaboral(dto.getRegimenLaboral());
            empleado.setEsPersonalDeConfianza(dto.getEsPersonalDeConfianza());
            empleado.setDireccionDomicilio(dto.getDireccionDomicilio());
            empleado.setTipoDomicilio(dto.getTipoDomicilio());
            empleado.setDistritoDomicilio(dto.getDistritoDomicilio());
            empleado.setTelefonoCasa(dto.getTelefonoCasa());
            empleado.setEmailPersonal(dto.getEmailPersonal());
            empleado.setRelacionContactoEmergencia(dto.getRelacionContactoEmergencia());
            empleado.setNombreContactoEmergencia(dto.getNombreContactoEmergencia());
            empleado.setTelefonoContactoEmergencia(dto.getTelefonoContactoEmergencia());
            empleado.setEmailContactoEmergencia(dto.getEmailContactoEmergencia());
            empleado.setEstado(dto.getEstado());

            //TODO: HARDCODE por ahora
            empleado.setEmpresa(empresaJpaRepository.findOne(4L));
            empleado.setFechaIngreso(new Date());
            empleado.setTelefonoCelular("");

            empleadoJpaRepository.save(empleado);
            empleadoJpaRepository.flush();

            log.info("Empleado creado [id:" + empleado.getIdEmpleado() + ", codigo: " + empleado.getCodigo() + "]");

            return empleado.getIdEmpleado();

        } catch (Exception e) {
            log.debug("ERROR", e);
            String msg = ((e.getMessage() == null) ? "" : e.getMessage());

            throw new GenericRestException("ERR_001", msg);
        }
    }

    @Override
    public Long processDataUpdateEmpleado(EmpleadoViewModel dto) throws Exception {
        //TODO deberia usar un mapper
        Empleado empleado = empleadoJpaRepository.findByCodigoExacto(dto.getCodigo());
        empleado.setNombre(dto.getNombre());
        empleado.setApellidoMaterno(dto.getApellidoMaterno());
        empleado.setApellidoPaterno(dto.getApellidoPaterno());
        empleado.setTipoDocumento(dto.getTipoDocumento());
        empleado.setNumeroDocumento(dto.getNumeroDocumento());
        empleado.setGenero(dto.getGenero());
        empleado.setEstadoCivil(dto.getEstadoCivil());
        empleado.setGrupoSangre(dto.getGrupoSangre());
        empleado.setDiscapacitado(dto.getDiscapacitado());
        empleado.setFechaNacimiento(dto.getFechaNacimiento());
        empleado.setPaisNacimiento(dto.getPaisNacimiento());
        empleado.setEmailInterno(dto.getEmailInterno());
        empleado.setTelefonoInterno(dto.getTelefonoInterno());
        empleado.setAnexoInterno(dto.getAnexoInterno());
        //TODO Centro de costo
        empleado.setContratoTrabajo(dto.getContratoTrabajo());
        empleado.setTipoTrabajador(dto.getTipoTrabajador());
        empleado.setRegimenHorario(dto.getRegimenHorario());
        empleado.setRegimenLaboral(dto.getRegimenLaboral());
        empleado.setEsPersonalDeConfianza(dto.getEsPersonalDeConfianza());
        empleado.setDireccionDomicilio(dto.getDireccionDomicilio());
        empleado.setTipoDomicilio(dto.getTipoDomicilio());
        empleado.setDistritoDomicilio(dto.getDistritoDomicilio());
        empleado.setTelefonoCasa(dto.getTelefonoCasa());
        empleado.setEmailPersonal(dto.getEmailPersonal());
        empleado.setRelacionContactoEmergencia(dto.getRelacionContactoEmergencia());
        empleado.setNombreContactoEmergencia(dto.getNombreContactoEmergencia());
        empleado.setTelefonoContactoEmergencia(dto.getTelefonoContactoEmergencia());
        empleado.setEmailContactoEmergencia(dto.getEmailContactoEmergencia());
        empleado.setEstado(dto.getEstado());

        try {
            empleadoJpaRepository.save(empleado);
            empleadoJpaRepository.flush();
            log.info("Empleado actualizado [id:" + empleado.getIdEmpleado() + ", codigo: " + empleado.getCodigo() + "]");

            return empleado.getIdEmpleado();

        } catch (Exception e) {
            log.debug("ERROR", e);
            String msg = ((e.getMessage() == null) ? "" : e.getMessage());
            if (e instanceof GenericRestException) {
                throw new GenericRestException("ERR_001", msg);
            } else if (e instanceof ObjectOptimisticLockingFailureException) {
                throw new GenericRestException("ERROR",
                        "The record was changed by another user. Please re-enter the screen.");
            } else if (e instanceof EmptyResultDataAccessException) {
                throw new GenericRestException("ERROR",
                        "The record was changed by another user. Please re-enter the screen.");
            } else {
                throw new GenericRestException("ERR_001", msg);
            }

        }


    }

//    @Override
//    public List<PermisoEmpleadoViewModel> busquedaPermisoEmpleado(PermisoEmpleadoFilterViewModel busquedaPermisoEmpleadoDto) {
//        return empleadoJdbcRepository.busquedaPermisoEmpleado(busquedaPermisoEmpleadoDto);
//    }

    private void processUpdateEmpleado(List<EmpleadoViewModel> dtos) {

        try {
            for (EmpleadoViewModel empleadoItem : dtos) {


                if (empleadoItem.getStatusProcessDtoList().size() == 0) {

                    Long empleadoId = this.processDataUpdateEmpleado(empleadoItem);

                    empleadoItem.setIdEmpleado(empleadoId);
                    StatusProcessViewModel statusProcessDto = new StatusProcessViewModel();
                    statusProcessDto.setStatus("Processed");
                    statusProcessDto.setMessage("");
                    empleadoItem.getStatusProcessDtoList().add(statusProcessDto);
                    log.info("Processed ");

                }

            }

        } catch (Exception e) {
            log.debug("ERROR", e);
            String msg = e.getMessage() == null ? "" : e.getMessage();

            throw new GenericRestException("ERR_001", msg);
        }


    }


    private void validarInfoDeEmpleados(List<EmpleadoViewModel> dtos, boolean isEdit) {

        for (EmpleadoViewModel empleadoItem : dtos) {

            List<StatusProcessViewModel> statusProcessListExistInfo = validateDataMasiveEmpleado(empleadoItem,false);
            if(statusProcessListExistInfo.size()>0){
                empleadoItem.getStatusProcessDtoList().addAll(statusProcessListExistInfo);
            }

        }
    }

    private List<StatusProcessViewModel> validateDataMasiveEmpleado(EmpleadoViewModel empleadoItem, boolean b) {

        List<StatusProcessViewModel> statusProcessDtos = new ArrayList<>();
        StatusProcessViewModel statusProcessDto = new StatusProcessViewModel();

        if (!StringUtils.isBlank(empleadoItem.getTipoDocumentoString())) {
            //TODO ver si no se pone en duro y se usa constante para Empleado.TipoDocumento
            TablaGeneral tablaTipoDocumento = tablaGeneralJpaRepository.findByGrupoAndNombre("Empleado.TipoDocumento", empleadoItem.getTipoDocumentoString());
            empleadoItem.setTipoDocumento(tablaTipoDocumento.getCodigo());
        }

        if (!StringUtils.isBlank(empleadoItem.getTipoDomicilioString())) {
            //TODO ver si no se pone en duro y se usa constante para Empleado.TipoDocumento
            TablaGeneral tablaTipoDomicilio = tablaGeneralJpaRepository.findByGrupoAndNombre("Empleado.TipoDomicilio", empleadoItem.getTipoDomicilioString());
            empleadoItem.setTipoDomicilio(tablaTipoDomicilio.getCodigo());
        }


        if (!StringUtils.isBlank(empleadoItem.getCentroCostoString())) {
            CentroCosto centroCosto = centroCostoJpaRepository.findByNombreExacto(empleadoItem.getCentroCostoString());
            if(centroCosto!=null)
                empleadoItem.setCentroCosto(centroCosto);
            else{
                statusProcessDtos.add(new StatusProcessViewModel("Error", "Centro de Costo no existe"));
            }
        }

        return statusProcessDtos;

    }

    private ListEmpleadosNuevosYAntiguosViewModel validarSiEmpleadosExisten(List<EmpleadoViewModel> dto) {
        ListEmpleadosNuevosYAntiguosViewModel contenedor = new ListEmpleadosNuevosYAntiguosViewModel();
        for (EmpleadoViewModel empleadoItem : dto) {
            Empleado empleadoEncontrado = empleadoJpaRepository.findByCodigoExacto(empleadoItem.getCodigo());
            if (empleadoEncontrado != null) {
                contenedor.getEmpleadosAntiguos().add(empleadoItem);
            }else{
                contenedor.getEmpleadosNuevos().add(empleadoItem);
            }
        }
        return contenedor;
    }

	@Override
	public HorarioEmpleadoViewModel verHorarioEmpleado(Long idEmpleado) {
		HorarioEmpleadoViewModel dto = new HorarioEmpleadoViewModel();
		dto = empleadoJdbcRepository.verHorarioEmpleado(idEmpleado);
		if(dto != null){
			List<HorarioEmpleadoDiaViewModel> horarioEmpleadoDiaDto = empleadoJdbcRepository.verHorarioEmpleadoDia(dto);
			dto.setHorariosEmpleadoDia(horarioEmpleadoDiaDto);
		}
		return dto;
	}

	@Override
	public List<HorarioEmpleadoDiaViewModel> obtenerHorarioEmpleadoDiasPorHorarioEmpleado(HorarioEmpleadoViewModel horarioEmpleadoDto) {

		List<HorarioEmpleadoDiaViewModel> dto = empleadoJdbcRepository.verHorarioEmpleadoDia(horarioEmpleadoDto);

		return dto;
	}

	@Override
	public List<PeriodoEmpleadoViewModel> verPeriodoEmpleado(Long idEmpleado) {
		List<PeriodoEmpleadoViewModel> dto = new ArrayList<>();
		dto = empleadoJdbcRepository.verPeriodoEmpleado(idEmpleado);

		return dto;
	}

	@Override
	public List<PermisoEmpleadoViewModel> verPermisoEmpleado(PeriodoEmpleadoViewModel periodoEmpleado) {
		List<PermisoEmpleadoViewModel> dto = new ArrayList<>();
		dto = empleadoJdbcRepository.verPermisoEmpleado(periodoEmpleado);

		return dto;
	}

	@Override
	public List<VacacionEmpleadoViewModel> verVacacion(PeriodoEmpleadoViewModel periodoEmpleado) {
		List<VacacionEmpleadoViewModel> dto = new ArrayList<>();
		dto = empleadoJdbcRepository.verVacaciones(periodoEmpleado);

		return dto;
	}

	@Override
	public List<MarcacionViewModel> verMarcacion(Long idEmpleado) {
		List<MarcacionViewModel> dto = new ArrayList<>();
		dto = empleadoJdbcRepository.verMarcaciones(idEmpleado);

		return dto;
	}

    @Override
    public NotificacionViewModel eliminarPermisoEmpleado(Long idPermisoEmpleado) {
    	NotificacionViewModel notificacion=new NotificacionViewModel();
        if (permisoEmpleadoJpaRepository.findOne(idPermisoEmpleado) == null) {
        	throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", "The record was changed by another user. Please re-enter the screen.");
        }
        try {
            permisoEmpleadoJpaRepository.delete(idPermisoEmpleado);
            permisoEmpleadoJpaRepository.flush();
            
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
	public List<PermisoEmpleadoViewModel> obtenerCodigoPermiso(String codigo) {
		List<PermisoEmpleado> entityPermisoEmpleado = permisoEmpleadoJpaRepository.findByCodigo(codigo);

		List<PermisoEmpleadoViewModel> items;

		items = entityPermisoEmpleado.stream().map(m -> mapper.map(m, PermisoEmpleadoViewModel.class)).collect(toList());
		return items;
	}

	@Override
	public List<VacacionEmpleadoViewModel> busquedaVacacionesEmpleado(
			VacacionesEmpleadoFilterViewModel busquedaVacacionesEmpleadoDto) {
		return empleadoJdbcRepository.busquedaVacacionesEmpleado(busquedaVacacionesEmpleadoDto);
	}

	@Override
	public List<HorasExtraViewModel> busquedaHorasExtrasEmpleado(
			HorasExtraEmpleadoFilterViewModel busquedaHorasExtraEmpleadoDto) {
		// TODO Auto-generated method stub
		//return empleadoJdbcRepository.busquedaHorasExtrasEmpleado(busquedaHorasExtraEmpleadoDto);
		return null;
	}

	@Override
	public HorasExtraViewModel informacionAdicionalHorasExtras(EmpleadoViewModel empleado) {

		HorasExtraViewModel dto = new HorasExtraViewModel();
		Integer dayOfWeek = getDayOfWeek(empleado.getFechaIngreso());
		dto = empleadoJdbcRepository.getHorarioEmpleadoDia(empleado.getIdEmpleado(), dayOfWeek);

		return dto;
	}
	public static int getDayOfWeek(Date aDate) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(aDate);
	    return cal.get(Calendar.DAY_OF_WEEK);
	}

	@Override
	public NotificacionViewModel registrarHorasExtra(HorasExtraViewModel horasExtraDto) {
		NotificacionViewModel notificacion = new NotificacionViewModel();

		List<HorasExtraViewModel> listHorasExtra = empleadoJdbcRepository.listHorasExtraEmpleado(horasExtraDto.getIdEmpleado());

		for(HorasExtraViewModel next: listHorasExtra){
			if(horasExtraDto.getFecha().equals(next.getFecha())){
				boolean isWithin = ValidationUtils.isWithinRangeHours(horasExtraDto.getHoraSalidaSolicitado(),
																next.getHoraSalidaHorario(), next.getHoraSalidaSolicitado());
				if(horasExtraDto.getHoraSalidaSolicitado().equals(next.getHoraSalidaSolicitado()) || isWithin==true){
					notificacion.setCodigo(0L);
					notificacion.setDetail("No es posible registrar, la hora extra se cruza con el codigo: " +
											next.getIdHorasExtra() +" Desde: "+next.getHoraSalidaHorario() +" Hasta: "+next.getHoraSalidaSolicitado());
					return notificacion;
				}

			}

		}

		HorasExtra entity;
		if(horasExtraDto.getIdHorasExtra() == null)
			entity = new HorasExtra();
		else entity = horasExtraJpaRepository.findOne(horasExtraDto.getIdHorasExtra());

		Empleado empleado = empleadoJpaRepository.findOne(horasExtraDto.getIdEmpleado());
		entity.setEmpleado(empleado);

		entity.setFecha(horasExtraDto.getFecha());
		entity.setHoraSalidaHorario(horasExtraDto.getHoraSalidaHorario());
		Empleado empleadoJefe = empleadoJpaRepository.findOne(horasExtraDto.getIdAtendidoPor());
		entity.setAtendidoPor(empleadoJefe);
		entity.setHoraSalidaSolicitado(horasExtraDto.getHoraSalidaSolicitado());
		if(horasExtraDto.getHoras()!=null && !horasExtraDto.getHoras().equals(""))
			entity.setHoras(Double.parseDouble(horasExtraDto.getHoras()));
		entity.setMotivo(horasExtraDto.getMotivo());
		entity.setEstado("P");
		entity.setTipo("RH");
//		entity.setEstado("E");
//		entity.setHorasCompensadas(Double.parseDouble(horasExtraDto.getHorasCompensadas()));

		horasExtraJpaRepository.save(entity);

		notificacion.setCodigo(1L);
		notificacion.setDetail("Se registro correctamente");
		return notificacion;
	}

	@Override
	public List<EquipoEntregadoViewModel> obtenerEquiposPendientesDevolucion(Long idEmpleado) {
		// TODO Auto-generated method stub
		return empleadoJdbcRepository.obtenerEquiposPendientesDevolucion(idEmpleado);
	}

	@Override
	public NotificacionViewModel countEquiposPendientesDevolucion(EmpleadoViewModel empleadoDto) {
		// TODO Auto-generated method stub
		NotificacionViewModel notificacion = new NotificacionViewModel();
		Long totalCount = empleadoJdbcRepository.countEquiposPendientesDevolucion(empleadoDto.getIdEmpleado());
		if(totalCount > 0){
			notificacion.setCodigo(1L);
			notificacion.setDetail("Hay equipos pendientes de devoluciï¿½n");
		}
		return notificacion;

	}

	@Override
	public  List<HorarioEmpleadoViewModel> verHorariosEmpleado(Long idEmpleado) {
		List<HorarioEmpleadoViewModel> horarios = new ArrayList<>();
		horarios = empleadoJdbcRepository.verHorariosEmpleado(idEmpleado);

		return horarios;
	}

	private boolean horarioEmpleadoDiaToDelete(List<HorarioEmpleadoDiaViewModel> horarioEmpleadoDias, Long idHorarioEmpleadoDia) {
        boolean delete = true;
        for (HorarioEmpleadoDiaViewModel item : horarioEmpleadoDias) {
            if (item.getIdHorarioEmpleadoDia().intValue() == idHorarioEmpleadoDia.intValue()) {
                delete = false;
                break;
            }
        }
        return delete;
    }

	@Override
	public NotificacionViewModel eliminarHorasExtra(Long idHorasExtra) {
		
		NotificacionViewModel notificacion = new NotificacionViewModel();
		
		if (horasExtraJpaRepository.findOne(idHorasExtra) == null) {
			throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", "The record was changed by another user. Please re-enter the screen.");
        }
        try {
        	horasExtraJpaRepository.delete(idHorasExtra);
        	horasExtraJpaRepository.flush();
        	
        	notificacion.setCodigo(1L);
    		notificacion.setSeverity("success");
    		notificacion.setSummary("Runakuna Success");
    		notificacion.setDetail("Registro fue eliminado correctamente.");
        	
        } catch (Exception e) {
        	throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", "Restriction is Being used, Can't be deleted");
        }

        return notificacion;
	}

	@Override
	public NotificacionViewModel rechazarHorasExtra(HorasExtraViewModel horasExtraDto) {
		NotificacionViewModel notificacion = new NotificacionViewModel();
		HorasExtra entity = horasExtraJpaRepository.findOne(horasExtraDto.getIdHorasExtra());
		entity.setEstado("R");
		entity.setComentarioResolucion(horasExtraDto.getComentarioResolucion());
		horasExtraJpaRepository.save(entity);
		horasExtraJpaRepository.flush();
		
		notificacion.setCodigo(1L);
		notificacion.setSeverity("success");
		notificacion.setSummary("Runakuna Success");
		notificacion.setDetail("El registro fue rechazado correctamente");
		return notificacion;
	}

	@Override
	public NotificacionViewModel aprobarHorasExtra(HorasExtraViewModel horasExtraDto) {
		NotificacionViewModel notificacion = new NotificacionViewModel();
		HorasExtra entity = horasExtraJpaRepository.findOne(horasExtraDto.getIdHorasExtra());
		entity.setEstado("A");
		horasExtraJpaRepository.save(entity);
		horasExtraJpaRepository.flush();

		notificacion.setCodigo(1L);
		notificacion.setSeverity("success");
		notificacion.setSummary("Runakuna Success");
		notificacion.setDetail("El registro fue aprobado correctamente");
		return notificacion;
	}

	@Override
	public List<EmpleadoResultViewModel> search(EmpleadoFilterViewModel filterViewModel) {
		return empleadoJdbcRepository.busquedaEmpleado(filterViewModel);
	}

	@Override
	public List<EmpleadoViewModel> searchExport(EmpleadoFilterViewModel filterViewModel) {
		return empleadoJdbcRepository.busquedaEmpleadoExport(filterViewModel);
	}

	@Override
	public EmpleadoViewModel findOne(Long id) {
		EmpleadoViewModel empleado;
		empleado = empleadoJdbcRepository.verEmpleado(id);
		
		empleado.setNombreCompletoEmpleado(StringUtil.toCamelCase(empleado.getNombreCompletoEmpleado()));
		
		DocumentoEmpleado fotoPerfil = documentoEmpleadoJpaRepository.findOneOnlyFotoPerfil(id);
		
		if(fotoPerfil != null){
			DocumentoEmpleadoViewModel fotoPerfilDto = new DocumentoEmpleadoViewModel();
			mapper.map(fotoPerfil, fotoPerfilDto);
			
			empleado.setFotoPerfil(fotoPerfilDto);
			
		}
		
		return empleado;
	}

	@Override
	public NotificacionViewModel create(EmpleadoViewModel empleado) {

		NotificacionViewModel notificacion = new NotificacionViewModel();

		List<Empleado> empleados = empleadoJpaRepository.BuscarEmpleadoPorCodigo(empleado.getCodigo());

		if(empleados != null && empleados.size() >0){
			notificacion.setCodigo(0L);
			notificacion.setDetail("El codigo del empleado ya existe.");
			return notificacion;
		}
		
		notificacion = createOrUpdateEmpleado(empleado);

		return notificacion;

	}

	@Override
	public NotificacionViewModel update(EmpleadoViewModel empleado) {

		NotificacionViewModel notificacion = new NotificacionViewModel();
		
		notificacion = createOrUpdateEmpleado(empleado);
		return notificacion;

	}

	@Override
	public NotificacionViewModel delete(Long id) {
		NotificacionViewModel notificacion=new NotificacionViewModel();
		if (empleadoJpaRepository.findOne(id) == null) {
			notificacion.setCodigo(0L);
			notificacion.setDetail("The record was changed by another user. Please re-enter the screen.");
		}
		try {
			empleadoJpaRepository.delete(id);
			empleadoJpaRepository.flush();
			notificacion.setCodigo(1L);
			notificacion.setSeverity("success");
			notificacion.setSummary("Runakuna Success");
			notificacion.setDetail("Se elimino el registro correctamente");
		} catch (Exception e) {
			notificacion.setCodigo(0L);
			notificacion.setSeverity("error");
			notificacion.setSummary("Runakuna Error");
			notificacion.setDetail("No se pudo eliminar el registro.");
			e.printStackTrace();
		}
		return notificacion;
	}

	@Transactional
	private NotificacionViewModel createOrUpdateEmpleado(EmpleadoViewModel empleado){

		NotificacionViewModel notificacion = new NotificacionViewModel();

		if(empleado.getDocumentos() ==null){
			empleado.setDocumentos(new ArrayList<>());
		}
		
		if(empleado.getFotoPerfil() != null){
			empleado.getDocumentos().add(empleado.getFotoPerfil());
		}
		
		Empleado entity = new Empleado();

		mapper.map(empleado, entity);

		entity.setDocumentosEmpleado(new ArrayList<>());

		if(empleado.getDocumentos() !=null){
			for (DocumentoEmpleadoViewModel documento : empleado.getDocumentos()) {

				DocumentoEmpleado documentoEntity = new DocumentoEmpleado();
				mapper.map(documento, documentoEntity);
				documentoEntity.setEmpleado(entity);
				entity.getDocumentosEmpleado().add(documentoEntity);
			}
		}

		entity.setEducacionesEmpleado(new ArrayList<>());

		if(empleado.getEducaciones() !=null){
			for (EducacionViewModel educacion : empleado.getEducaciones()) {
				Educacion educacionEntity= new Educacion();
				mapper.map(educacion, educacionEntity);
				educacionEntity.setEmpleado(entity);
				entity.getEducacionesEmpleado().add(educacionEntity);
			}
		}

		entity.setExperienciasLaboralesEmpleado(new ArrayList<>());

		if(empleado.getExperienciasLaborales() !=null){
			for (ExperienciaLaboralViewModel experienciaLaboral : empleado.getExperienciasLaborales()) {
				ExperienciaLaboral experienciaLaboralEntity = new ExperienciaLaboral();
				mapper.map(experienciaLaboral, experienciaLaboralEntity);
				experienciaLaboralEntity.setEmpleado(entity);
				entity.getExperienciasLaboralesEmpleado().add(experienciaLaboralEntity);
			}
		}

		entity.setEquiposEntregadoEmpleado(new ArrayList<>());

		if(empleado.getEquiposEntregados() !=null){
			for (EquipoEntregadoViewModel equipoEntregado : empleado.getEquiposEntregados()) {
				EquipoEntregado equipoEntregadoEntity = new EquipoEntregado();
				mapper.map(equipoEntregado, equipoEntregadoEntity);
				equipoEntregadoEntity.setEmpleado(entity);
				entity.getEquiposEntregadoEmpleado().add(equipoEntregadoEntity);
			}
		}

		entity.setDependientesEmpleado(new ArrayList<>());

		if(empleado.getDependientes() !=null){
			for (DependienteViewModel dependiente : empleado.getDependientes()) {
				Dependiente dependienteEntity = new Dependiente();

				mapper.map(dependiente,dependienteEntity);
				dependienteEntity.setEmpleado(entity);

				entity.getDependientesEmpleado().add(dependienteEntity);
			}
		}

		Empresa empresa = empresaJpaRepository.findOne(empleado.getIdEmpresa());
		CentroCosto centroCosto = centroCostoJpaRepository.findOne(empleado.getIdCentroCosto());

		entity.setEmpresa(empresa);
		entity.setCentroCosto(centroCosto);

		entity = empleadoJpaRepository.save(entity);
		empleadoJpaRepository.flush();

		notificacion.setCodigo(1L);
		notificacion.setSeverity("success");
		notificacion.setSummary("Runakuna Success");
		notificacion.setDetail("Se registro Correctamente.");
		return notificacion;
	}


	@Override
	public EmpleadoViewModel findOneAccessJwtToken(Long idEmpleado) {
		EmpleadoViewModel empleado;
		empleado = empleadoJdbcRepository.findOneAccessJwtToken(idEmpleado);
		return empleado;
	}


	@Override
	public List<EmpleadoResultViewModel> quickSearch(QuickFilterViewModel quickFilter) {
		return empleadoJdbcRepository.busquedaRapidaEmpleado(quickFilter);
	}


	@Override
	public HistorialLaboralViewModel obtenerHistorialLaboralById(Long idHistorialLaboral) {
		HistorialLaboralViewModel dto = new HistorialLaboralViewModel();
		HistorialLaboral entity = historialLaboralJpaRepository.findOne(idHistorialLaboral);
		mapper.map(entity, dto);
		if(entity.getEmpleado().getIdEmpleado()!=null){
			dto.setIdEmpleado(entity.getEmpleado().getIdEmpleado());
		}
		if(entity.getProyecto() != null){
			dto.setIdProyecto(entity.getProyecto().getIdProyecto());
		}
		
		if(entity.getCargo()!=null && entity.getCargo().getIdCargo()!=null){
			dto.setIdCargo(entity.getCargo().getIdCargo());
		}
		
		if(entity.getMoneda()!=null && entity.getMoneda().getIdMoneda()!=null){
			dto.setIdMoneda(entity.getMoneda().getIdMoneda());
		}
		
		if(entity.getUnidadDeNegocio()!=null && entity.getUnidadDeNegocio().getIdUnidadDeNegocio()!=null){
			dto.setIdUnidadDeNegocio(entity.getUnidadDeNegocio().getIdUnidadDeNegocio());
		}
		
		if(entity.getDepartamentoArea()!=null && entity.getDepartamentoArea().getIdDepartamentoArea()!=null){
			dto.setIdDepartamentoArea(entity.getDepartamentoArea().getIdDepartamentoArea());
		}
				
		return dto;
	}

	@Override
	public NotificacionViewModel registrarFotoEmpleado(EmpleadoViewModel empleado) {

		NotificacionViewModel notificacion = new NotificacionViewModel();



		DocumentoEmpleado oneOnlyFotoPerfil = documentoEmpleadoJpaRepository.findOneOnlyFotoPerfil(empleado.getIdEmpleado());

		if(oneOnlyFotoPerfil!=null){
			oneOnlyFotoPerfil.setContenidoArchivo(empleado.getFotoPerfil().getContenidoArchivo());
			oneOnlyFotoPerfil.setTipoDocumento(empleado.getFotoPerfil().getTipoDocumento());
			oneOnlyFotoPerfil.setNombre(empleado.getFotoPerfil().getNombre());
			oneOnlyFotoPerfil.setNombreArchivo(empleado.getFotoPerfil().getNombreArchivo());
			oneOnlyFotoPerfil.setTipoArchivo(empleado.getFotoPerfil().getTipoArchivo());
			documentoEmpleadoJpaRepository.save(oneOnlyFotoPerfil);
		}else{
			//nuevo
			DocumentoEmpleado documentoEmpleado = new DocumentoEmpleado();
			documentoEmpleado.setEmpleado(empleadoJpaRepository.findOne(empleado.getIdEmpleado()));
			documentoEmpleado.setTipoDocumento(empleado.getFotoPerfil().getTipoDocumento());
			documentoEmpleado.setNombre(empleado.getFotoPerfil().getNombre());
			documentoEmpleado.setNombreArchivo(empleado.getFotoPerfil().getNombreArchivo());
			documentoEmpleado.setTipoArchivo(empleado.getFotoPerfil().getTipoArchivo());
			documentoEmpleado.setContenidoArchivo(empleado.getFotoPerfil().getContenidoArchivo());
			documentoEmpleadoJpaRepository.save(documentoEmpleado);
		}

		documentoEmpleadoJpaRepository.flush();

		notificacion.setCodigo(1L);
		notificacion.setSeverity("success");
		notificacion.setSummary("Runakuna Success");
		notificacion.setDetail("Se Modifico la foto satisfactoriamente.");
		return notificacion;
	}
	
	@Override
	public EmpleadoViewModel obtenerEmpleadoEsPersonalConfianza(Long idEmpleado) {
		Empleado empleado = empleadoJpaRepository.findOne(idEmpleado);
		EmpleadoViewModel dto = new EmpleadoViewModel();		
		dto.setIdEmpleado(empleado.getIdEmpleado());
		dto.setEsPersonalDeConfianza(empleado.getEsPersonalDeConfianza());
		return dto;
		
	}
	
	@Override
	public EmpleadoViewModel obtenerEmpleadoCabecera(Long id) {
		EmpleadoViewModel empleado;
		empleado = empleadoJdbcRepository.obtenerEmpleadoCabecera(id);
		
		empleado.setNombreCompletoEmpleado(StringUtil.toCamelCase(empleado.getNombreCompletoEmpleado()));
		
		DocumentoEmpleado fotoPerfil = documentoEmpleadoJpaRepository.findOneOnlyFotoPerfil(id);
		
		if(fotoPerfil != null){
			DocumentoEmpleadoViewModel fotoPerfilDto = new DocumentoEmpleadoViewModel();
			mapper.map(fotoPerfil, fotoPerfilDto);
			
			empleado.setFotoPerfil(fotoPerfilDto);
			
		}
		
		return empleado;
	}
	
}

