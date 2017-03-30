package pe.com.empresa.rk.domain.model.repository.jdbc;

import java.util.List;

import pe.com.empresa.rk.view.model.AssignedRole;
import pe.com.empresa.rk.view.model.CurrentUser;

public interface LoginRepository {

	CurrentUser authenticate(String cuentaUsuario);
	List<AssignedRole> getAssignedRolesByUser(Long idUsuario);
	Integer validateModuleActionName(Long idUsuario, String moduleCode, String actionName);
	void updateDefaultRole(String account, String roleCode);
	
}
