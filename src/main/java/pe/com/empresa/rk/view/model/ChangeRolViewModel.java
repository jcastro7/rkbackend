package pe.com.empresa.rk.view.model;

import java.util.List;

public class ChangeRolViewModel {
	
	CurrentUser currentUser;
	List<ModuloViewModel> modulos;
	
	public CurrentUser getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(CurrentUser currentUser) {
		this.currentUser = currentUser;
	}
	public List<ModuloViewModel> getModulos() {
		return modulos;
	}
	public void setModulos(List<ModuloViewModel> modulos) {
		this.modulos = modulos;
	}
	
	
	

}
