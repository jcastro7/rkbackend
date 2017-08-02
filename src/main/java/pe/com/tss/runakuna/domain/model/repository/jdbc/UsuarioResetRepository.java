package pe.com.tss.runakuna.domain.model.repository.jdbc;

import pe.com.tss.runakuna.view.model.UsuarioResetViewModel;

public interface UsuarioResetRepository {

	UsuarioResetViewModel buscarUsuarioPorLink(String link);
	
}
