package pe.com.tss.runakuna.service;

import java.util.List;

import pe.com.tss.runakuna.view.model.AssignedRole;
import pe.com.tss.runakuna.view.model.ChangeRolViewModel;
import pe.com.tss.runakuna.view.model.CurrentUser;
import pe.com.tss.runakuna.view.model.ModuloViewModel;

public interface LoginService {

	CurrentUser authenticate(String cuentaUsuario);

	Boolean validateModuleActionName(Long idUsuario, String moduleCode, String actionName);

	ChangeRolViewModel updateDefaultRole(String account, String roleCode);
	
}
