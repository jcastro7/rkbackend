package pe.com.tss.runakuna.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.tss.runakuna.domain.model.entities.Accion;
import pe.com.tss.runakuna.domain.model.entities.Autorizacion;
import pe.com.tss.runakuna.domain.model.entities.Empresa;
import pe.com.tss.runakuna.domain.model.entities.Rol;
import pe.com.tss.runakuna.domain.model.repository.jdbc.RolJdbcRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.AccionJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.AutorizacionJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.EmpresaJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.ModuloJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.RolJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.UsuarioRolJpaRepository;
import pe.com.tss.runakuna.enums.SeverityStatusEnum;
import pe.com.tss.runakuna.exception.BusinessException;
import pe.com.tss.runakuna.service.RolService;
import pe.com.tss.runakuna.view.model.AutorizacionFilterViewModel;
import pe.com.tss.runakuna.view.model.ModuloViewModel;
import pe.com.tss.runakuna.view.model.AccionViewModel;
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
	EmpresaJpaRepository empresaJpaRepository;
	
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
		return null;
	}

	@Override
	public NotificacionViewModel update(RolViewModel manteinanceViewModel) {
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
				
				Rol rolEntity = rolJpaRepository.findOne(idRol);
				if(rolEntity.getRolSistema() == 0){
					rolJpaRepository.delete(idRol);
					rolJpaRepository.flush();
					notificacion.setCodigo(1L);
		            notificacion.setSeverity("success");
					notificacion.setSummary("Runakuna Success");
		            notificacion.setDetail("Registro fue eliminado correctamente.");
				}				
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
		notificacion.setSeverity("success");
		notificacion.setSummary("Runakuna Success");
		notificacion.setDetail("Se actualizo el codigo: "+accionViewModel.getIdAutorizacion());
		return notificacion;
	}

	@Override
	public RolViewModel findRolById(Long idRol) {
		
		RolViewModel dto = new RolViewModel();
		List<ModuloViewModel> moduloDto = new ArrayList<>();
		dto = rolJdbcRepository.findRolById(idRol);
		dto.setModulo(new ArrayList<>());
		List<ModuloViewModel> temp= rolJdbcRepository.findAllSubModulosByIdRol(idRol);
		moduloDto=obtenerListaOrdenada(temp);
		//moduloDto = rolJdbcRepository.findAllSubModulosByIdRol(idRol);
		
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
	
	static final Comparator<ModuloViewModel> MODULE_ORDER = 
            new Comparator<ModuloViewModel>() {
    	public int compare(ModuloViewModel e1, ModuloViewModel e2) {
    		return e1.getOrden().compareTo(e2.getOrden());
    	}
    };
    
	private List<ModuloViewModel> obtenerListaOrdenada(List<ModuloViewModel> temp) {
		List<ModuloViewModel> padres=new ArrayList<ModuloViewModel>();
		List<ModuloViewModel> hijos=new ArrayList<ModuloViewModel>();
		List<ModuloViewModel> listaFinal=new ArrayList<ModuloViewModel>();
		for(ModuloViewModel modulo : temp){
			if(modulo.getTieneSubMenu()!=null && modulo.getTieneSubMenu().equals("1")){
				padres.add(modulo);
			}else{
				hijos.add(modulo);
			}
		}
		Collections.sort(padres, MODULE_ORDER);
		for(ModuloViewModel moduloP : padres){
			listaFinal.add(moduloP);
			for(ModuloViewModel moduloH:hijos){
				if(moduloH.getIdParent().longValue()==moduloP.getIdModulo().longValue()){
					listaFinal.add(moduloH);		
				}
			}
		}
		
		return listaFinal;
	}

	@Override
	public RolViewModel findAllSubModulosAccion() {
		RolViewModel dto = new RolViewModel();
		List<ModuloViewModel> moduloDto = new ArrayList<>();
		dto.setModulo(new ArrayList<>());
		List<ModuloViewModel> temp= rolJdbcRepository.findAllSubModulos();
		moduloDto=obtenerListaOrdenada(temp);
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

	@Override
	public NotificacionViewModel crearRolAccion(RolViewModel rolViewModel) {
		NotificacionViewModel notificacionDto=new NotificacionViewModel();
		notificacionDto=crearActualizarRolAccion(rolViewModel);
		return notificacionDto;
	}

	@Override
	public NotificacionViewModel actualizarRolAccion(RolViewModel rolViewModel) {
		NotificacionViewModel notificacionDto=new NotificacionViewModel();
		notificacionDto=crearActualizarRolAccion(rolViewModel);
		return notificacionDto;
	}
	
	private NotificacionViewModel crearActualizarRolAccion(RolViewModel manteinanceViewModel) {
		NotificacionViewModel notificacion = new NotificacionViewModel();
		
		Rol rolEntity = new Rol();
		try {
						
			if(manteinanceViewModel.getIdRol() == null){
				
				List<Rol> finIdRol = rolJpaRepository.findAll();
				boolean isExists = finIdRol.stream().anyMatch(e -> e.getNombre().equals(manteinanceViewModel.getNombre()));
				if(isExists == true){
					notificacion.setCodigo(0L);
					notificacion.setSeverity("error");
					notificacion.setSummary("Runakuna Error");
					notificacion.setDetail("No es posible registrar, el codigo ya existe");
					return notificacion;
				}
				
				mapper.map(manteinanceViewModel, rolEntity);
				Empresa empresa = empresaJpaRepository.findOne(manteinanceViewModel.getIdEmpresa());
				rolEntity.setEmpresa(empresa);
				rolEntity.setRolSistema(0);

				rolEntity.setAutorizacion( new ArrayList<>());
				if(manteinanceViewModel.getModulo()!=null){
					Accion accion = null;
					for(ModuloViewModel moduloDto: manteinanceViewModel.getModulo()){
						if(moduloDto.getAcciones()!=null){							
							for(AccionViewModel accionDto: moduloDto.getAcciones()){
								if(accionDto.getIdAccion()!=null){
									Autorizacion autorizacionEntity = new Autorizacion();
									autorizacionEntity.setRol(rolEntity);
									accion = accionJpaRepository.findOne(accionDto.getIdAccion());
									autorizacionEntity.setAccion(accion);
									if(accionDto.getAutorizado()==null || accionDto.getAutorizado()==0){
										autorizacionEntity.setAutorizado(0);
									}else{
										autorizacionEntity.setAutorizado(accionDto.getAutorizado());
									}
									autorizacionEntity.setCreador(accionDto.getCreadorAutorizacion());
									autorizacionEntity.setFechaCreacion(accionDto.getFechaCreacionAutorizacion());
									autorizacionEntity.setActualizador(accionDto.getActualizadorAutorizacion());
									autorizacionEntity.setFechaActualizacion(accionDto.getFechaActualizacionAutorizacion());
									autorizacionEntity.setRowVersion(accionDto.getRowVersionAutorizacion());
									rolEntity.getAutorizacion().add(autorizacionEntity);
									
								}else{
									continue;
								}
								
							}
						}
					}
				}
				rolJpaRepository.save(rolEntity);
			}else{
//				if(manteinanceViewModel.getRolSistema()!=1){				
//					mapper.map(manteinanceViewModel, rolEntity);
//					Empresa empresa = empresaJpaRepository.findOne(manteinanceViewModel.getIdEmpresa());
//					rolEntity.setEmpresa(empresa);
//					rolJpaRepository.save(rolEntity);
//				}
				if(manteinanceViewModel.getModulo()!=null){
					for(ModuloViewModel moduloDto: manteinanceViewModel.getModulo()){
						if(moduloDto.getAcciones()!=null){
							for(AccionViewModel accionDto: moduloDto.getAcciones()){
								Autorizacion autorizacionEntity = new Autorizacion();
								mapper.map(accionDto, autorizacionEntity);
								if(accionDto.getIdRol()!=null && accionDto.getIdAccion()!=null){
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
				}
			}
							
//				rolJpaRepository.save(rolEntity);
				notificacion.setCodigo(1L);
				notificacion.setSeverity("success");
				notificacion.setSummary("Runakuna Success");
				notificacion.setDetail("Se registro correctamente");
		} catch (Exception e) {
			notificacion.setCodigo(0L);
			notificacion.setSeverity("error");
			notificacion.setSummary("Runakuna Error");
			notificacion.setDetail("No es posible registrar, "+e.getMessage());
			e.printStackTrace();
		}	
		
		return notificacion;
	}
}
