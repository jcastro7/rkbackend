package pe.com.tss.runakuna.service;

import pe.com.tss.runakuna.view.model.NotificacionViewModel;
import pe.com.tss.runakuna.view.model.UsuarioResetViewModel;

public interface UsuarioResetService extends MaintenanceService<Object, Object, UsuarioResetViewModel, NotificacionViewModel, Long>{

	UsuarioResetViewModel validateLink(String enlace);

	
}
