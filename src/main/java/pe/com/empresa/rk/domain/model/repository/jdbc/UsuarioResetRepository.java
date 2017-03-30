package pe.com.empresa.rk.domain.model.repository.jdbc;

import pe.com.empresa.rk.view.model.UsuarioResetViewModel;

public interface UsuarioResetRepository {

	UsuarioResetViewModel buscarUsuarioPorLink(String link);
	
}
