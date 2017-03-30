package pe.com.empresa.rk.service.impl;


import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pe.com.empresa.rk.domain.model.repository.jdbc.UsuarioRepository;
import pe.com.empresa.rk.domain.model.repository.jpa.UsuarioJpaRepository;
import pe.com.empresa.rk.domain.model.repository.jpa.UsuarioResetJpaRepository;
import pe.com.empresa.rk.service.UsuarioResetService;
import pe.com.empresa.rk.domain.model.entities.Usuario;
import pe.com.empresa.rk.domain.model.entities.UsuarioReset;
import pe.com.empresa.rk.domain.model.repository.jdbc.UsuarioResetRepository;
import pe.com.empresa.rk.view.model.NotificacionViewModel;
import pe.com.empresa.rk.view.model.UsuarioResetViewModel;
import pe.com.empresa.rk.view.model.UsuarioViewModel;

@Service
public class UsuarioResetServiceImpl implements UsuarioResetService {

	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
    UsuarioRepository usuarioRepository;
	
	@Autowired
    UsuarioJpaRepository usuarioJpaRepository;
	
	@Autowired
    UsuarioResetJpaRepository usuarioResetJpaRepository;
	
	@Autowired
	UsuarioResetRepository  usuarioResetRepository;
	
	@Autowired
	Mapper mapper;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	 
	@Override
	public List<Object> search(Object filterViewModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioResetViewModel findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NotificacionViewModel create(UsuarioResetViewModel manteinanceViewModel) {
		NotificacionViewModel notificacionDto=new NotificacionViewModel();
		notificacionDto=crearUsuarioReset(manteinanceViewModel);
		return notificacionDto;
	}

	private NotificacionViewModel crearUsuarioReset(UsuarioResetViewModel manteinanceViewModel) {
		NotificacionViewModel notificacionDto=new NotificacionViewModel();
		UsuarioViewModel usuario= usuarioRepository.buscarUsuarioPorEmail(manteinanceViewModel.getUserName());
		Usuario usuarioModel=usuarioJpaRepository.findOne(usuario.getIdUsuario());
		UsuarioReset usuarioReset=new UsuarioReset();
		try{
			mapper.map(manteinanceViewModel,usuarioReset);
			if(usuario.getIdUsuario()!=null)
				usuarioReset.setUsuario(usuarioModel);
			
			usuarioResetJpaRepository.save(usuarioReset);
			usuarioResetJpaRepository.flush();
			notificacionDto.setCodigo(1L);
			notificacionDto.setDetail("Se registro correctamente");
			
		} catch (Exception e) {
			notificacionDto.setCodigo(0L);
			notificacionDto.setDetail("No es posible registrar, "+e.getMessage());
		}
		
		return notificacionDto;
	}

	@Override
	public NotificacionViewModel update(UsuarioResetViewModel manteinanceViewModel) {
		NotificacionViewModel notificacionDto=new NotificacionViewModel();
		//notificacionDto=crearActualizarUsuarioReset(manteinanceViewModel);
	try{
			UsuarioResetViewModel usuarioResetViewModel =usuarioResetRepository.buscarUsuarioPorLink(manteinanceViewModel.getEnlace());
			UsuarioReset usuarioReset=new UsuarioReset();
			mapper.map(usuarioResetViewModel, usuarioReset);
			usuarioReset.setEstado("I");
			Usuario usuario=usuarioJpaRepository.findOne(usuarioResetViewModel.getIdUsuario());
			usuarioReset.setUsuario(usuario);
			//TODO falta encriptar
			usuario.setPassword(passwordEncoder.encode(manteinanceViewModel.getPassword()));
			usuarioResetJpaRepository.save(usuarioReset);
			usuarioResetJpaRepository.flush();
			usuarioJpaRepository.save(usuario);
			usuarioJpaRepository.flush();
			notificacionDto.setCodigo(1L);
			notificacionDto.setDetail("Se realizo el cambio de contraseña correctamente");
		}	catch (Exception e) {
			notificacionDto.setCodigo(0L);
			notificacionDto.setDetail("No se pudo realizar el cambio de contraseña, "+e.getMessage());
		}
		
		return notificacionDto;
	}

	@Override
	public NotificacionViewModel delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioResetViewModel validateLink(String enlace) {
		UsuarioResetViewModel usuarioResetViewModel =usuarioResetRepository.buscarUsuarioPorLink(enlace);
		return usuarioResetViewModel;
	}

}
