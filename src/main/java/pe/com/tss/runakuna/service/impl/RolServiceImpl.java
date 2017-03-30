package pe.com.tss.runakuna.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.tss.runakuna.domain.model.entities.Accion;
import pe.com.tss.runakuna.domain.model.entities.Alerta;
import pe.com.tss.runakuna.domain.model.entities.Autorizacion;
import pe.com.tss.runakuna.domain.model.entities.Empleado;
import pe.com.tss.runakuna.domain.model.entities.Modulo;
import pe.com.tss.runakuna.domain.model.entities.PeriodoEmpleado;
import pe.com.tss.runakuna.domain.model.entities.PermisoEmpleado;
import pe.com.tss.runakuna.domain.model.entities.Rol;
import pe.com.tss.runakuna.domain.model.repository.jdbc.RolJdbcRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.AccionJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.AutorizacionJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.ModuloJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.RolJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.UsuarioRolJpaRepository;
import pe.com.tss.runakuna.enums.SeverityStatusEnum;
import pe.com.tss.runakuna.enums.UserStatusEnum;
import pe.com.tss.runakuna.exception.BusinessException;
import pe.com.tss.runakuna.exception.GenericRestException;
import pe.com.tss.runakuna.service.RolService;
import pe.com.tss.runakuna.view.model.AutorizacionFilterViewModel;
import pe.com.tss.runakuna.view.model.ModuloViewModel;
import pe.com.tss.runakuna.view.model.AccionViewModel;
import pe.com.tss.runakuna.view.model.AlertaSubscriptorViewModel;
import pe.com.tss.runakuna.view.model.Authorization;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;
import pe.com.tss.runakuna.view.model.RolFilterViewModel;
import pe.com.tss.runakuna.view.model.RolResultViewModel;
import pe.com.tss.runakuna.view.model.RolViewModel;

@Service
public class RolServiceImpl implements RolService{

	@Autowired
	RolJdbcRepository rolJdbcRepository;
	
	@Autowired
	RolJpaRepository rolJpaRepository;
	
	@Autowired
	UsuarioRolJpaRepository usuarioRolJpaRepository;
	
	@Autowired
	ModuloJpaRepository moduloJpaRepository;
	
	@Autowired
	AccionJpaRepository accionJpaRepository;
	
	@Autowired
	AutorizacionJpaRepository autorizacionJpaRepository;
	
	@Autowired
	Mapper mapper;
	
	@Override
	public List<RolResultViewModel> search(RolFilterViewModel filterViewModel) {
		return rolJdbcRepository.searchRolResult(filterViewModel);
	}

	@Override
	public RolViewModel findOne(Long id) {

		
		
		return null;
	}

	@Override
	public NotificacionViewModel create(RolViewModel manteinanceViewModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NotificacionViewModel update(RolViewModel manteinanceViewModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NotificacionViewModel delete(Long idRol) {
		NotificacionViewModel notificacion=new NotificacionViewModel();
		if(rolJpaRepository.findOne(idRol) == null){
			throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", "The record was changed by another user. Please re-enter the screen.");
		}
		try {
			if(usuarioRolJpaRepository.findOne(idRol) == null){
				rolJpaRepository.deleteByIdRol(idRol,0);
				rolJpaRepository.flush();
				notificacion.setCodigo(1L);
	            notificacion.setSeverity(SeverityStatusEnum.SUCCESS.getCode());
				notificacion.setSummary("Runakuna Success");
	            notificacion.setDetail("Registro fue eliminado correctamente.");
			}			
		} catch (Exception e) {
			throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", "Restriction is Being used, Can't be deleted");
		}
		return notificacion;
	}

	@Override
	public List<Authorization> obtenerAutorizaciones(AutorizacionFilterViewModel filter) {
		List<Authorization> result = new ArrayList<>();
		result = rolJdbcRepository.obtenerAutorizaciones(filter);
		return result;
	}

	@Override
	public List<RolViewModel> getRolById(Long idRol) {
		List<RolViewModel> allowedMods = rolJdbcRepository.getRolById(idRol);
        List<RolViewModel> distintc = new ArrayList<>(new LinkedHashSet<>(allowedMods));
        List<RolViewModel> result = groupByModuloPadre(distintc);
        return result;
	}
	protected List<RolViewModel> groupByModuloPadre(List<RolViewModel> padres){
//        List<RolViewModel> rolViewModel = new ArrayList<>();
//        if (padres.size() == 0) {
//            return rolViewModel;
//        }
//        
//        int idx = 0;
//        RolViewModel padre = padres.get(idx);
//        while(idx < padres.size()){
//        	Long idModulo = padre.getIdModulo();
//        	Long idRol = padre.getIdRol();
//        	
//        	RolViewModel rolDto = new RolViewModel();
//        	rolDto.setNombre(padre.getNombre());
//        	rolDto.setDescripcion(padre.getDescripcion());
//        	rolDto.setNombreModulo(padre.getNombreModulo());
//        	List<AccionViewModel> acciones = new ArrayList<>();
//        	do {
//        		acciones = rolJdbcRepository.findAccionByIdModulo(idModulo,idRol);
//        		
//                if (idx++ == padres.size()-1){
//                    break;
//                }
//                padre = padres.get(idx);
//            } while (idModulo.equals(padre.getIdModulo()));
//        	rolDto.setAccion(acciones);
//        	rolViewModel.add(rolDto);
//        }
//
//        return rolViewModel;
		return null;
    }

	@Override
	public NotificacionViewModel updateAutorizacionAccion(AccionViewModel accionViewModel) {


		NotificacionViewModel notificacion = new NotificacionViewModel();
		
		Autorizacion entity = autorizacionJpaRepository.findOne(accionViewModel.getIdAutorizacion());
		
		entity.setAutorizado(accionViewModel.getAutorizado());
		
		Rol rolEntity = rolJpaRepository.findOne(entity.getRol().getIdRol());
		entity.setRol(rolEntity);

		Accion accion = accionJpaRepository.findOne(entity.getAccion().getIdAccion());
		entity.setAccion(accion);
		
		autorizacionJpaRepository.save(entity);
		
		notificacion.setCodigo(1L);
		notificacion.setDetail("Se actualizo el codigo: "+accionViewModel.getIdAutorizacion());
		return notificacion;
	}

	@Override
	public RolViewModel findRolById(Long idRol) {
		
		RolViewModel dto = new RolViewModel();
		List<ModuloViewModel> moduloDto = new ArrayList<>();
		dto = rolJdbcRepository.findRolById(idRol);
		dto.setModulo(new ArrayList<>());
		
		moduloDto = rolJdbcRepository.findAllSubModulosByIdRol(idRol);
		
		if(moduloDto!=null && moduloDto.size()>0){
			for(ModuloViewModel modulo : moduloDto){
				modulo.setAcciones(new ArrayList<>());
				
				List<AccionViewModel> acciones = new ArrayList<>();
				acciones = rolJdbcRepository.findAccionByIdModulo(modulo.getIdModulo(), idRol);
				modulo.setAcciones(acciones);
			}
		}
		dto.setModulo(moduloDto);
		
		return dto;
	}

	@Override
	public NotificacionViewModel actualizarRolAccion(RolViewModel manteinanceViewModel) {

		NotificacionViewModel notificacion = new NotificacionViewModel();
		Autorizacion autorizacionEntity = new Autorizacion();
		try {
			
			if(manteinanceViewModel.getModulo()!=null){
				for(ModuloViewModel moduloDto: manteinanceViewModel.getModulo()){
					if(moduloDto.getAcciones()!=null){
						for(AccionViewModel accionDto: moduloDto.getAcciones()){
							mapper.map(accionDto, autorizacionEntity);
							Rol rol = rolJpaRepository.findOne(accionDto.getIdRol());
							autorizacionEntity.setRol(rol);
							Accion accion = accionJpaRepository.findOne(accionDto.getIdAccion());
							autorizacionEntity.setAccion(accion);
							autorizacionEntity.setCreador(accionDto.getCreadorAutorizacion());
							autorizacionEntity.setFechaCreacion(accionDto.getFechaCreacionAutorizacion());
							autorizacionEntity.setActualizador(accionDto.getActualizadorAutorizacion());
							autorizacionEntity.setFechaActualizacion(accionDto.getFechaActualizacionAutorizacion());
							autorizacionEntity.setRowVersion(accionDto.getRowVersionAutorizacion());

							autorizacionJpaRepository.save(autorizacionEntity);
						}
					}
				}
			}			
			
			autorizacionJpaRepository.flush();
			notificacion.setCodigo(1L);
			notificacion.setDetail("Se actualizo el codigo: "+manteinanceViewModel.getIdRol());
		} catch (Exception e) {
			notificacion.setCodigo(0L);
			notificacion.setDetail("No es posible registrar, "+e.getMessage());
		}	
		
		return notificacion;
	}

	@Override
	public RolViewModel findAllSubModulosAccion() {
		RolViewModel dto = new RolViewModel();
		List<ModuloViewModel> moduloDto = new ArrayList<>();
//		dto = rolJdbcRepository.findRolById();
		dto.setModulo(new ArrayList<>());
		
		moduloDto = rolJdbcRepository.findAllSubModulos();
		
		if(moduloDto!=null && moduloDto.size()>0){
			for(ModuloViewModel modulo : moduloDto){
				modulo.setAcciones(new ArrayList<>());
				
				List<AccionViewModel> acciones = new ArrayList<>();
				acciones = rolJdbcRepository.findAllAccionByIdModulo(modulo.getIdModulo());
				modulo.setAcciones(acciones);
			}
		}
		dto.setModulo(moduloDto);
		
		return dto;
	}
}
