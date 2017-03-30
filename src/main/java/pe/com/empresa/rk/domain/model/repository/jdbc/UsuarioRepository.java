package pe.com.empresa.rk.domain.model.repository.jdbc;

import java.util.List;

import pe.com.empresa.rk.view.model.QuickFilterViewModel;
import pe.com.empresa.rk.view.model.UsuarioFilterViewModel;
import pe.com.empresa.rk.view.model.UsuarioResultViewModel;
import pe.com.empresa.rk.view.model.RolResultViewModel;
import pe.com.empresa.rk.view.model.UsuarioViewModel;

public interface UsuarioRepository {

	UsuarioViewModel buscarUsuarioPorcuentaUsuario(Long idUsuario);
	UsuarioViewModel buscarUsuarioPorEmail(String email);
	List<UsuarioResultViewModel> obtenerUsuarios(UsuarioFilterViewModel filterViewModel);
	List<RolResultViewModel> cargarComboRol();
	List<UsuarioResultViewModel> busquedaRapidaUsuarios(QuickFilterViewModel quickFilter);
	
}
