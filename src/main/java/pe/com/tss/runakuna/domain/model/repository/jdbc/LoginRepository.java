package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.List;

import pe.com.tss.runakuna.view.model.AssignedRole;
import pe.com.tss.runakuna.view.model.ChangeRolViewModel;
import pe.com.tss.runakuna.view.model.CurrentUser;
import pe.com.tss.runakuna.view.model.ModuloViewModel;

public interface LoginRepository {

	CurrentUser authenticate(String cuentaUsuario);
	List<AssignedRole> getAssignedRolesByUser(Long idUsuario);
	Integer validateModuleActionName(Long idUsuario, String moduleCode, String actionName);
	void updateDefaultRole(String account, String roleCode);
	
}
