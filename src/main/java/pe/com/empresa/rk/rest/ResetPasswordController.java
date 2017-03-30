package pe.com.empresa.rk.rest;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.empresa.rk.domain.model.entities.ConfiguracionSistema;
import pe.com.empresa.rk.domain.model.repository.jpa.ConfiguracionSistemaJpaRepository;
import pe.com.empresa.rk.service.MailService;
import pe.com.empresa.rk.service.UsuarioResetService;
import pe.com.empresa.rk.service.UsuarioService;
import pe.com.empresa.rk.view.model.NotificacionViewModel;
import pe.com.empresa.rk.view.model.UsuarioResetViewModel;



@RestController
@RequestMapping(value = "/reset")
public class ResetPasswordController extends BaseController{

	private final Logger LOG = LoggerFactory.getLogger(ResetPasswordController.class);
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	UsuarioResetService usuarioResetService;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	ConfiguracionSistemaJpaRepository configuracionSistemaJpaRepository;
	
	@Value("${spring.urlpasswordreset.ipfrontend}")
    private String urlpasswordreset;

	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
	public @ResponseBody UsuarioResetViewModel sendMailPasswordRecovery(@RequestBody UsuarioResetViewModel usuario){

		StringBuffer link=new StringBuffer(urlpasswordreset);
		link.append("?id=").append(UUID.randomUUID());
		String enlace=link.toString();
		
		usuario.setEnlace(enlace);
		usuario.setFechaInicio(new Date());
		Calendar calendar =Calendar.getInstance();
		calendar.setTime(usuario.getFechaInicio());
		ConfiguracionSistema validDaysOfLInk = configuracionSistemaJpaRepository.obtenerConfiguracionSistemaPorCodigo("Login.ResetPassword");
		calendar.add(Calendar.DAY_OF_YEAR, Integer.parseInt(validDaysOfLInk.getValor()));
		usuario.setFechaFin(calendar.getTime());
		usuario.setEstado("A");
		
		NotificacionViewModel notificacion=usuarioResetService.create(usuario);
		
		if(notificacion!=null && notificacion.getCodigo().equals(1L)){
			mailService.sendEmail("Recupero de Contraseña", "Estimado.<p> Para recuperar su contraseña de click en el siguiente link: <p>"+enlace,
					usuario.getUserName());
		}
		
		return usuario;
		
	}
	
	@RequestMapping(value = "/validateLink", method = RequestMethod.POST)
	public @ResponseBody NotificacionViewModel validateLink(@RequestBody UsuarioResetViewModel usuario){
		NotificacionViewModel notificacion=new NotificacionViewModel();
		
		UsuarioResetViewModel usuarioReset =usuarioResetService.validateLink(usuario.getEnlace());
		if(usuarioReset!=null && usuarioReset.getIdUsuario()!=null) {
			notificacion.setCodigo(1L);
			notificacion.setDetail("Link valido");
		} else {
			notificacion.setCodigo(0L);
			notificacion.setDetail("Link no valido");
		}
		
		return notificacion;
		
	}
	
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public @ResponseBody NotificacionViewModel resetPassword(@RequestBody UsuarioResetViewModel usuario,HttpServletRequest request){
		NotificacionViewModel notificacion=new NotificacionViewModel();
		 notificacion =usuarioResetService.update(usuario);
		return notificacion;
		
	}
}
