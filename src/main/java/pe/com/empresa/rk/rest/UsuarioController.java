package pe.com.empresa.rk.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.empresa.rk.service.UsuarioService;
import pe.com.empresa.rk.view.model.UsuarioFilterViewModel;
import pe.com.empresa.rk.view.model.UsuarioQuickFilterViewModel;
import pe.com.empresa.rk.view.model.UsuarioResultViewModel;
import pe.com.empresa.rk.view.model.NotificacionViewModel;
import pe.com.empresa.rk.view.model.RolResultViewModel;
import pe.com.empresa.rk.view.model.UsuarioViewModel;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class UsuarioController extends BaseController{
	
	@Autowired
    UsuarioService usuarioService;
	
	private final Logger LOG = LoggerFactory.getLogger(UsuarioController.class);
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public LoginResponse login(@RequestBody final UsuarioViewModel login){
		return null;
		
	}
	
	@RequestMapping(value = "/buscarUsuarioPorCuentaUsuario", method = RequestMethod.GET, produces = "application/json")
    public UsuarioViewModel buscarUsuarioPorCuentaUsuario(@RequestParam Long idUsuario) {
        return usuarioService.cargarUsuarioPorcuentaUsuario(idUsuario);
    }
	
	@SuppressWarnings("unused")
    private static class LoginResponse {
        public String token;

        public LoginResponse(final String token) {
            this.token = token;
        }
    }
	

	
	@RequestMapping(value = "/registrarUsuario", method = RequestMethod.POST)
    public @ResponseBody
	NotificacionViewModel registrarUsuario(@RequestBody UsuarioViewModel usuarioDto) {
    	NotificacionViewModel dto = new NotificacionViewModel();
    	if(usuarioDto.getIdUsuario()==null){
			dto = usuarioService.create(usuarioDto);
		} else {
			dto = usuarioService.update(usuarioDto);
		}
		return dto;
    }
	
	@RequestMapping(value = "/obtenerUsuarios", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<List<UsuarioResultViewModel>> obtenerUsuarios(@RequestBody UsuarioFilterViewModel dto){
    	List<UsuarioResultViewModel> result = new ArrayList<UsuarioResultViewModel>();
		result = usuarioService.search(dto);
		if(result == null)
			result = new ArrayList<>();
		LOG.info("Msg obtenerUsuarios: " + result);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/busquedaRapidaUsuarios", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<List<UsuarioResultViewModel>> busquedaRapidaUsuarios(@RequestBody UsuarioQuickFilterViewModel dto){
    	List<UsuarioResultViewModel> result = new ArrayList<UsuarioResultViewModel>();
		result = usuarioService.quickSearch(dto);
		if(result == null)
			result = new ArrayList<>();
		LOG.info("Msg obtenerUsuarios: " + result);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/obtenerUsuarioDetalle", method = RequestMethod.POST)
	public @ResponseBody UsuarioViewModel obtenerUsuarioDetalle(@RequestBody Long idUsuario){
    	UsuarioViewModel result = new UsuarioViewModel();
		result = usuarioService.findOne(idUsuario);
		if(result == null)
			result = new UsuarioViewModel();
		LOG.info("Msg obtenerUsuarioDetalle: " + result);
		return result;
	}
	
	@RequestMapping(value = "/eliminarUsuario", method = RequestMethod.POST)
	public NotificacionViewModel eliminarUsuario(@RequestBody Long idUsuario) {
		NotificacionViewModel notificacionViewModel = new NotificacionViewModel();
		notificacionViewModel = usuarioService.delete(idUsuario);
		return  notificacionViewModel;
	}
	
	@RequestMapping(value = "/cargarComboRol", method = RequestMethod.GET)
	public List<RolResultViewModel> cargarComboRol() {

		return usuarioService.cargarComboRol();
	}

}
