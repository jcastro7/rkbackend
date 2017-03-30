package pe.com.empresa.rk.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pe.com.empresa.rk.domain.model.entities.Empleado;
import pe.com.empresa.rk.domain.model.entities.Rol;
import pe.com.empresa.rk.domain.model.entities.Usuario;
import pe.com.empresa.rk.domain.model.repository.jdbc.UsuarioRepository;
import pe.com.empresa.rk.domain.model.repository.jpa.EmpleadoJpaRepository;
import pe.com.empresa.rk.domain.model.repository.jpa.RolJpaRepository;
import pe.com.empresa.rk.domain.model.repository.jpa.UsuarioJpaRepository;
import pe.com.empresa.rk.enums.SeverityStatusEnum;
import pe.com.empresa.rk.exception.BusinessException;
import pe.com.empresa.rk.service.UsuarioService;
import pe.com.empresa.rk.domain.model.entities.UsuarioRol;
import pe.com.empresa.rk.view.model.NotificacionViewModel;
import pe.com.empresa.rk.view.model.QuickFilterViewModel;
import pe.com.empresa.rk.view.model.RolResultViewModel;
import pe.com.empresa.rk.view.model.UsuarioFilterViewModel;
import pe.com.empresa.rk.view.model.UsuarioResultViewModel;
import pe.com.empresa.rk.view.model.UsuarioRolViewModel;
import pe.com.empresa.rk.view.model.UsuarioViewModel;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
    UsuarioRepository usuarioRepository;
	
	@Autowired
    UsuarioJpaRepository usuarioJpaRepository;
	
	@Autowired
	Mapper mapper;
	
	@Autowired
    RolJpaRepository rolJpaRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
    private EmpleadoJpaRepository empleadoJpaRepository;
	 
	@Override
	public UsuarioViewModel cargarUsuarioPorcuentaUsuario(Long idUsuario) {

		UsuarioViewModel result = usuarioRepository.buscarUsuarioPorcuentaUsuario(idUsuario);
        

        return result;
	}

	@Override
	public List<UsuarioResultViewModel> search(UsuarioFilterViewModel filterViewModel) {
		// TODO Auto-generated method stub
		return usuarioRepository.obtenerUsuarios(filterViewModel);
	}

	@Override
	public UsuarioViewModel findOne(Long id) {
		UsuarioViewModel usuarioViewModel=new UsuarioViewModel();
		Usuario usuario=usuarioJpaRepository.findOne(id);
//		UserStatusEnum estadoEnum=UserStatusEnum.findByCode(usuario.getEstado().getCode());
		mapper.map(usuario, usuarioViewModel);
//		usuarioViewModel.setEstado(estadoEnum.getCode());
		if(usuario.getIdEmpleado()!=null){
			Empleado empleado=empleadoJpaRepository.findOne(usuario.getIdEmpleado());
			usuarioViewModel.setNombreEmpleado(empleado.getNombre()+" "+empleado.getApellidoPaterno()+" "+ empleado.getApellidoMaterno());
		}
		usuarioViewModel.setUsuarioRol(new ArrayList<UsuarioRolViewModel>());
		if (usuarioViewModel.getUsuarioRol()!=null) {
			for(UsuarioRol usuarioRol:usuario.getRoles()) {
				UsuarioRolViewModel urolViewModel=new UsuarioRolViewModel();
				mapper.map(usuarioRol, urolViewModel);
				Rol rol = rolJpaRepository.findOne(usuarioRol.getRol().getIdRol());
				urolViewModel.setIdUsuario(usuarioRol.getUsuario().getIdUsuario());
				urolViewModel.setIdRol(usuarioRol.getRol().getIdRol());
				urolViewModel.setDescripcion(rol.getDescripcion());
				usuarioViewModel.getUsuarioRol().add(urolViewModel);
			}
		}
		List<Rol> allRol = rolJpaRepository.findAll();
		List<RolResultViewModel> rolViewModel = new ArrayList<RolResultViewModel>();
		mapper.map(allRol, rolViewModel);
		usuarioViewModel.setAllRol(rolViewModel);
		
		return usuarioViewModel;
	}

	@Override
	public NotificacionViewModel create(UsuarioViewModel manteinanceViewModel) {
		NotificacionViewModel notificacionDto=new NotificacionViewModel();
		notificacionDto=crearActualizarUsuario(manteinanceViewModel);
		return notificacionDto;
	}

	private NotificacionViewModel crearActualizarUsuario(UsuarioViewModel usuarioViewModel) {
		NotificacionViewModel notificacionDto=new NotificacionViewModel();
		Usuario usuario=new Usuario();
		try {
//			UserStatusEnum estadoEnum=UserStatusEnum.findByCode(usuarioViewModel.getEstado());
//			usuarioViewModel.setEstado(estadoEnum.getLabel());			
			mapper.map(usuarioViewModel, usuario);
			usuario.setPassword("$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm");
			usuario.setRoles(new ArrayList<UsuarioRol>());
			if (usuarioViewModel.getUsuarioRol()!=null) {
				for(UsuarioRolViewModel usuarioRolViewModel:usuarioViewModel.getUsuarioRol()) {
					UsuarioRol usuarioRol=new UsuarioRol();
					mapper.map(usuarioRolViewModel, usuarioRol);
					usuarioRol.setUsuario(usuario);
					usuarioRol.setRol(rolJpaRepository.findOne(usuarioRolViewModel.getIdRol()));
					usuario.getRoles().add(usuarioRol);
				}
			}
			usuarioJpaRepository.save(usuario);
			usuarioJpaRepository.flush();
			notificacionDto.setCodigo(1L);
			notificacionDto.setSeverity("success");
			notificacionDto.setSummary("Runakuna Success");
			notificacionDto.setDetail("Se registro correctamente");
		} catch (Exception e) {
			notificacionDto.setCodigo(0L);
			notificacionDto.setSeverity("error");
			notificacionDto.setSummary("Runakuna Error");
			notificacionDto.setDetail("No es posible registrar, "+e.getMessage());
		}
		return notificacionDto;
	}

	@Override
	public NotificacionViewModel update(UsuarioViewModel manteinanceViewModel) {
		NotificacionViewModel notificacionDto=new NotificacionViewModel();
		notificacionDto=crearActualizarUsuario(manteinanceViewModel);
		return notificacionDto;
	}

	@Override
	public NotificacionViewModel delete(Long id) {
		NotificacionViewModel notificacion=new NotificacionViewModel();
		Usuario usuario = usuarioJpaRepository.findOne(id);
		if (usuario == null) {
			throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", "The record was changed by another user. Please re-enter the screen.");
		}
		try {
			usuarioJpaRepository.delete(id);
			usuarioJpaRepository.flush();
			
			notificacion.setCodigo(1L);
            notificacion.setSeverity(SeverityStatusEnum.SUCCESS.getCode());
			notificacion.setSummary("Runakuna Success");
            notificacion.setDetail("Registro fue eliminado correctamente.");
			
		} catch (Exception e) {
			throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", "Restriction is Being used, Can't be deleted");
		}
		return notificacion;
	}

	@Override
	public List<RolResultViewModel> cargarComboRol() {
		// TODO Auto-generated method stub
		
		List<Rol> allRol = rolJpaRepository.findAll();
		List<RolResultViewModel> rolViewModel = new ArrayList<RolResultViewModel>();
		mapper.map(allRol, rolViewModel);
		
		return rolViewModel;
		
//		return usuarioRepository.cargarComboRol();
	}

	@Override
	public List<UsuarioResultViewModel> quickSearch(QuickFilterViewModel quickFilter) {
		return usuarioRepository.busquedaRapidaUsuarios(quickFilter);
	}

}
