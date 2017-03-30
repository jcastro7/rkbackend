package pe.com.tss.runakuna.service.impl;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import pe.com.tss.runakuna.domain.model.entities.DocumentoEmpleado;
import pe.com.tss.runakuna.domain.model.entities.Empleado;
import pe.com.tss.runakuna.domain.model.repository.jdbc.LoginRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.DocumentoEmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.EmpleadoJpaRepository;
import pe.com.tss.runakuna.service.LoginService;
import pe.com.tss.runakuna.service.intf.ModuloService;
import pe.com.tss.runakuna.view.model.AssignedRole;
import pe.com.tss.runakuna.view.model.ChangeRolViewModel;
import pe.com.tss.runakuna.view.model.CurrentUser;
import pe.com.tss.runakuna.view.model.ModuloViewModel;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginRepository loginRepository;
	@Autowired
	DocumentoEmpleadoJpaRepository documentoEmpleadoJpaRepository;

	@Autowired
	EmpleadoJpaRepository empleadoJpaRepository;
	

    @Autowired
    private ModuloService moduloService;


	@Value("${version}")
	private String version;

	@Value("${build}")
	private String build;

	@Value("${timestamp}")
	private String timestamp;

	@Value("${revision}")
	private String revision;

	@Override
	public CurrentUser authenticate(String cuentaUsuario) {

		CurrentUser currentUser = loginRepository.authenticate(cuentaUsuario);

		if(currentUser.getIdEmpleado()!=null) {

			Empleado empleado = empleadoJpaRepository.findOne(currentUser.getIdEmpleado());

			if(empleado.getGenero()!=null){
				currentUser.setGenero(empleado.getGenero());
			}

			DocumentoEmpleado fotoPerfil = documentoEmpleadoJpaRepository.findOneOnlyFotoPerfil(currentUser.getIdEmpleado());

			if(fotoPerfil!=null)
				currentUser.setFoto(fotoPerfil.getContenidoArchivo());
		}

		if(currentUser!=null && currentUser.getIdUsuario()!=null) {
			List<AssignedRole> assignedRoles=loginRepository.getAssignedRolesByUser(currentUser.getIdUsuario());
			currentUser.setAssignedRoles(assignedRoles);
		}


		currentUser.setBuild(build);
		currentUser.setRevision(revision);
		currentUser.setTimestamp(timestamp);


		return currentUser;
	}

	@Override
	public Boolean validateModuleActionName(Long idUsuario, String moduleCode, String actionName) {
		Integer validate=loginRepository.validateModuleActionName(idUsuario, moduleCode, actionName);
		return (validate==1)?true:false;
	}
	
	@Override
	public ChangeRolViewModel updateDefaultRole(String account, String roleCode){
		   
		   ChangeRolViewModel changeRolViewModel = new ChangeRolViewModel();

		   loginRepository.updateDefaultRole(account, roleCode);
	       
	       changeRolViewModel.setCurrentUser(authenticate(account));
	       changeRolViewModel.setModulos(moduloService.getModulosPermitidosPorUsuario(account));	              
	       return changeRolViewModel;
	}

}
