package pe.com.tss.runakuna.service.impl;

import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.tss.runakuna.domain.model.entities.DepartamentoArea;
import pe.com.tss.runakuna.domain.model.entities.Empleado;
import pe.com.tss.runakuna.domain.model.entities.Proyecto;
import pe.com.tss.runakuna.domain.model.repository.jdbc.ProyectoRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.DepartamentoAreaJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.EmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.EmpresaJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.MonedaJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.ProyectoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.ProyectoRepositoryJpa;
import pe.com.tss.runakuna.domain.model.repository.jpa.UnidaDeNegocioJpaRepository;
import pe.com.tss.runakuna.enums.SeverityStatusEnum;
import pe.com.tss.runakuna.view.model.ProyectoFilterViewModel;
import pe.com.tss.runakuna.view.model.ProyectoResultViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;
import pe.com.tss.runakuna.view.model.ProyectoViewModel;
import pe.com.tss.runakuna.view.model.QuickFilterViewModel;
import pe.com.tss.runakuna.exception.BusinessException;
import pe.com.tss.runakuna.exception.GenericRestException;
import pe.com.tss.runakuna.service.ProyectoService;

@Service
public class ProyectoServiceImpl implements ProyectoService {

    final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    Mapper mapper;

    @Autowired
    EmpleadoJpaRepository empleadoJpaRepository;

    @Autowired
    ProyectoRepositoryJpa proyectoRepositoryJpa;

    @Autowired
    MonedaJpaRepository monedaJpaRepository;

    @Autowired
    UnidaDeNegocioJpaRepository unidaDeNegocioJpaRepository;

    @Autowired
    DepartamentoAreaJpaRepository departamentoAreaJpaRepository;

    @Autowired
    EmpresaJpaRepository empresaJpaRepository;

    @Autowired
    ProyectoJpaRepository proyectoJpaRepository;

    @Autowired
    ProyectoRepository proyectoRepository;


    @Override
    public List<ProyectoResultViewModel> search(ProyectoFilterViewModel proyectoFilterViewModel) {
        return proyectoRepository.obtenerProyectos(proyectoFilterViewModel);
    }

    @Override
    public ProyectoViewModel findOne(Long id) {
        Proyecto proyecto = proyectoRepositoryJpa.findOne(id);
        ProyectoViewModel dto = new ProyectoViewModel();
        mapper.map(proyecto, dto);
        if (proyecto.getDepartamentoArea() != null) {
            dto.setIdDepartamentoArea(proyecto.getDepartamentoArea().getIdDepartamentoArea());
            if (proyecto.getDepartamentoArea().getUnidadDeNegocio() != null) {
                dto.setIdUnidadDeNegocio(proyecto.getDepartamentoArea().getUnidadDeNegocio().getIdUnidadDeNegocio());
            }
        }
        if (proyecto.getJefe() != null) {
            dto.setIdJefeProyecto(proyecto.getJefe().getIdEmpleado());
            Empleado jefe = empleadoJpaRepository.findOne(proyecto.getJefe().getIdEmpleado());
            dto.setNombreJefeProyecto(jefe.getApellidoPaterno() + " " + jefe.getApellidoMaterno() +", " +jefe.getNombre());
        }
        if (proyecto.getJefeReemplazo() != null) {
            dto.setIdJefeProyectoReemplazo(proyecto.getJefeReemplazo().getIdEmpleado());
            Empleado jefeReemplazo = empleadoJpaRepository.findOne(proyecto.getJefeReemplazo().getIdEmpleado());
            dto.setNombreJefeProyectoReemplazo(jefeReemplazo.getApellidoPaterno() + " " + jefeReemplazo.getApellidoMaterno() +", " +jefeReemplazo.getNombre());
        }
        return dto;
    }

    @Override
    public NotificacionViewModel create(ProyectoViewModel manteinanceViewModel) {
        NotificacionViewModel notificacionDto = new NotificacionViewModel();
        notificacionDto = crearActualizarProyecto(manteinanceViewModel);
        return notificacionDto;
    }

    @Override
    public NotificacionViewModel update(ProyectoViewModel manteinanceViewModel) {
        NotificacionViewModel notificacionDto = new NotificacionViewModel();
        notificacionDto = crearActualizarProyecto(manteinanceViewModel);
        return notificacionDto;
    }

    @Override
    public NotificacionViewModel delete(Long id) {
        NotificacionViewModel notificacion = new NotificacionViewModel();
        if (proyectoJpaRepository.findOne(id) == null) {
            throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", "The record was changed by another user. Please re-enter the screen.");
        }
        try {
            proyectoJpaRepository.delete(id);
            proyectoJpaRepository.flush();
            notificacion.setCodigo(1L);
            notificacion.setSeverity(SeverityStatusEnum.SUCCESS.getCode());
			notificacion.setSummary("Runakuna Success");
            notificacion.setDetail("Registro fue eliminado correctamente.");
        } catch (Exception e) {
        	throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", "Restriction is Being used, Can't be deleted");
        }

        return notificacion;
    }

    private NotificacionViewModel crearActualizarProyecto(ProyectoViewModel proyectoDto) {
        NotificacionViewModel notificacionDto = new NotificacionViewModel();
        DepartamentoArea departamentoArea = null;
        Empleado jefeProyecto = null;
        Empleado jefeProyectoReemplazo = null;
        Proyecto proyecto = new Proyecto();

        if (proyectoDto.getIdJefeProyecto() != null)
            jefeProyecto = empleadoJpaRepository.findOne(proyectoDto.getIdJefeProyecto());
        if (proyectoDto.getIdJefeProyectoReemplazo() != null)
            jefeProyectoReemplazo = empleadoJpaRepository.findOne(proyectoDto.getIdJefeProyectoReemplazo());
        if (proyectoDto.getIdDepartamentoArea() != null)
            departamentoArea = departamentoAreaJpaRepository.findOne(proyectoDto.getIdDepartamentoArea());

        mapper.map(proyectoDto, proyecto);
        proyecto.setJefe(jefeProyecto);
        proyecto.setJefeReemplazo(jefeProyectoReemplazo);
        proyecto.setDepartamentoArea(departamentoArea);
        proyectoRepositoryJpa.save(proyecto);
        proyectoRepositoryJpa.flush();
        notificacionDto.setCodigo(1L);
        notificacionDto.setSeverity("success");
        notificacionDto.setSummary("Informativo");
        notificacionDto.setDetail("Se registro correctamente");

        return notificacionDto;
    }

    @Override
    public List<ProyectoResultViewModel> quickSearch(QuickFilterViewModel quickFilter) {
        return proyectoRepository.busquedaRapidaProyectos(quickFilter);
    }


}
