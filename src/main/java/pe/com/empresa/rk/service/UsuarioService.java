package pe.com.empresa.rk.service;

import java.util.List;

import pe.com.empresa.rk.view.model.NotificacionViewModel;
import pe.com.empresa.rk.view.model.QuickFilterViewModel;
import pe.com.empresa.rk.view.model.RolResultViewModel;
import pe.com.empresa.rk.view.model.UsuarioFilterViewModel;
import pe.com.empresa.rk.view.model.UsuarioResultViewModel;
import pe.com.empresa.rk.view.model.UsuarioViewModel;

public interface UsuarioService extends MaintenanceService<UsuarioFilterViewModel, UsuarioResultViewModel, UsuarioViewModel, NotificacionViewModel, Long>,
QuickSearchService<UsuarioResultViewModel, QuickFilterViewModel>{

	UsuarioViewModel cargarUsuarioPorcuentaUsuario(Long idUsuario);

	List<RolResultViewModel> cargarComboRol();
}
