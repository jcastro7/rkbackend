package pe.com.empresa.rk.service;

import pe.com.empresa.rk.view.model.NotificacionViewModel;
import pe.com.empresa.rk.view.model.UsuarioResetViewModel;

public interface UsuarioResetService extends MaintenanceService<Object, Object, UsuarioResetViewModel, NotificacionViewModel, Long>{

	UsuarioResetViewModel validateLink(String enlace);

	
}
