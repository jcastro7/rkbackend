package pe.com.empresa.rk.view.model;

public class RolUsuarioPadreViewModel extends CurrentUser {
	
	private String rolesNames;
	
	public RolUsuarioPadreViewModel(){
		
	}
//
//	public RolUsuarioPadreViewModel(String rolesNames, Long idEmpleado, String rolName) {
//		super(idEmpleado, rolName);
//		this.rolesNames = rolesNames;
//	}

	public String getRolesNames() {
		return rolesNames;
	}

	public void setRolesNames(String rolesNames) {
		this.rolesNames = rolesNames;
	}

	

}
