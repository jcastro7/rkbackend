package pe.com.tss.runakuna.service;

import java.util.List;

import pe.com.tss.runakuna.view.model.NotificacionViewModel;
import pe.com.tss.runakuna.view.model.QuickFilterViewModel;
import pe.com.tss.runakuna.view.model.RolResultViewModel;
import pe.com.tss.runakuna.view.model.UsuarioFilterViewModel;
import pe.com.tss.runakuna.view.model.UsuarioResultViewModel;
import pe.com.tss.runakuna.view.model.UsuarioViewModel;
import pe.com.tss.runakuna.view.model.UsuarioViewModelAutoComplete;

public interface UsuarioService extends MaintenanceService<UsuarioFilterViewModel, UsuarioResultViewModel, UsuarioViewModel, NotificacionViewModel, Long>,
QuickSearchService<UsuarioResultViewModel, QuickFilterViewModel>{

	UsuarioViewModel cargarUsuarioPorcuentaUsuario(Long idUsuario);

	List<RolResultViewModel> cargarComboRol();

	UsuarioViewModelAutoComplete getInfoAutoCompleteEmpleado(Long idEmpleado);
}
