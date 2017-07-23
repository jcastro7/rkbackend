package pe.com.tss.runakuna.service.impl;


import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pe.com.tss.runakuna.domain.model.entities.DocumentoEmpleado;
import pe.com.tss.runakuna.domain.model.entities.Empleado;
import pe.com.tss.runakuna.domain.model.entities.Empresa;
import pe.com.tss.runakuna.domain.model.entities.Rol;
import pe.com.tss.runakuna.domain.model.entities.Usuario;
import pe.com.tss.runakuna.domain.model.entities.UsuarioRol;
import pe.com.tss.runakuna.domain.model.repository.jdbc.UsuarioJdbcRepository;
import pe.com.tss.runakuna.domain.model.repository.jdbc.UsuarioRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.EmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.EmpresaJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.RolJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.UsuarioJpaRepository;
import pe.com.tss.runakuna.enums.SeverityStatusEnum;
import pe.com.tss.runakuna.exception.BusinessException;
import pe.com.tss.runakuna.view.model.DocumentoEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;
import pe.com.tss.runakuna.view.model.QuickFilterViewModel;
import pe.com.tss.runakuna.view.model.RolResultViewModel;
import pe.com.tss.runakuna.view.model.UsuarioFilterViewModel;
import pe.com.tss.runakuna.view.model.UsuarioResultViewModel;
import pe.com.tss.runakuna.view.model.UsuarioRolViewModel;
import pe.com.tss.runakuna.view.model.UsuarioViewModel;
import pe.com.tss.runakuna.view.model.UsuarioViewModelAutoComplete;
import pe.com.tss.runakuna.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	UsuarioJpaRepository usuarioJpaRepository;
	
	@Autowired
	EmpresaJpaRepository empresaJpaRepository;
	
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
		return usuarioRepository.obtenerUsuarios(filterViewModel);
	}

	@Override
	public UsuarioViewModel findOne(Long id) {
		UsuarioViewModel usuarioViewModel=new UsuarioViewModel();
		Usuario usuario=usuarioJpaRepository.findOne(id);
		mapper.map(usuario, usuarioViewModel);
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
			
			mapper.map(usuarioViewModel, usuario);
			
			Empresa empresa = empresaJpaRepository.findOne(usuarioViewModel.getIdEmpresa());
			usuario.setEmpresa(empresa);
			
			usuario.setPassword("$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm");
			usuario.setRoles(new ArrayList<UsuarioRol>());
			if (usuarioViewModel.getUsuarioRol()!=null) {
				
				ArrayList<Integer> rolPorDefecto = new ArrayList<>();
				
				for(UsuarioRolViewModel usuarioRolViewModel:usuarioViewModel.getUsuarioRol()) {
					UsuarioRol usuarioRol=new UsuarioRol();
					mapper.map(usuarioRolViewModel, usuarioRol);
					usuarioRol.setUsuario(usuario);
					
					rolPorDefecto.add(usuarioRolViewModel.getPorDefecto());					
					
					usuarioRol.setRol(rolJpaRepository.findOne(usuarioRolViewModel.getIdRol()));
					usuario.getRoles().add(usuarioRol);
				}
				boolean valueByDefaultIsZero = rolPorDefecto.contains(1);
				if(valueByDefaultIsZero == false){
					notificacionDto.setCodigo(0L);
					notificacionDto.setSeverity("error");
					notificacionDto.setSummary("Runakuna Error");
					notificacionDto.setDetail("Seleccione un rol Por Defecto ");
					return notificacionDto;	
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
			notificacion.setSeverity("success");
			notificacion.setSummary("Runakuna Success");
            notificacion.setDetail("Registro fue eliminado correctamente.");
			
		} catch (Exception e) {
			throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", "Restriction is Being used, Can't be deleted");
		}
		return notificacion;
	}

	@Override
	public List<RolResultViewModel> cargarComboRol() {
		
		List<Rol> allRolEntity = rolJpaRepository.findAll();
		List<RolResultViewModel> rolViewModel = new ArrayList<RolResultViewModel>();
		
		if(allRolEntity != null)
			rolViewModel = allRolEntity.stream().map(m -> mapper.map(m, RolResultViewModel.class)).collect(toList());

		return rolViewModel;
//		return usuarioRepository.cargarComboRol();
	}

	@Override
	public List<UsuarioResultViewModel> quickSearch(QuickFilterViewModel quickFilter) {
		return usuarioRepository.busquedaRapidaUsuarios(quickFilter);
	}

	@Override
	public UsuarioViewModelAutoComplete getInfoAutoCompleteEmpleado(Long idEmpleado) {
		return usuarioRepository.getInfoAutoCompleteEmpleado(idEmpleado);
	}

}
