package pe.com.empresa.rk.service;

import pe.com.empresa.rk.view.model.ChangeRolViewModel;
import pe.com.empresa.rk.view.model.CurrentUser;

public interface LoginService {

	CurrentUser authenticate(String cuentaUsuario);

	Boolean validateModuleActionName(Long idUsuario, String moduleCode, String actionName);

	ChangeRolViewModel updateDefaultRole(String account, String roleCode);
	
}
