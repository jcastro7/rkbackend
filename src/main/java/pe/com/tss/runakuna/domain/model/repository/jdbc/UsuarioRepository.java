package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.List;

import pe.com.tss.runakuna.view.model.QuickFilterViewModel;
import pe.com.tss.runakuna.view.model.RolResultViewModel;
import pe.com.tss.runakuna.view.model.UsuarioFilterViewModel;
import pe.com.tss.runakuna.view.model.UsuarioResultViewModel;
import pe.com.tss.runakuna.view.model.UsuarioViewModel;
import pe.com.tss.runakuna.view.model.UsuarioViewModelAutoComplete;

public interface UsuarioRepository {

	UsuarioViewModel buscarUsuarioPorcuentaUsuario(Long idUsuario);
	UsuarioViewModel buscarUsuarioPorEmail(String email);
	List<UsuarioResultViewModel> obtenerUsuarios(UsuarioFilterViewModel filterViewModel);
	List<RolResultViewModel> cargarComboRol();
	List<UsuarioResultViewModel> busquedaRapidaUsuarios(QuickFilterViewModel quickFilter);
	UsuarioViewModelAutoComplete getInfoAutoCompleteEmpleado(Long idEmpleado);
	
}
