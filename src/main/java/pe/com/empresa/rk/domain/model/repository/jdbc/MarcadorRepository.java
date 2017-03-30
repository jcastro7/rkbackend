package pe.com.empresa.rk.domain.model.repository.jdbc;

import java.util.List;

import pe.com.empresa.rk.view.model.MarcadorFilterViewModel;
import pe.com.empresa.rk.view.model.MarcadorResultViewModel;

/**
 * Created by josediaz on 25/11/2016.
 */
public interface MarcadorRepository {

    

	public List<MarcadorResultViewModel> buscarMarcador(MarcadorFilterViewModel filterViewModel);

//	public List<RolUsuarioPadreViewModel> getRolPorUsuario(String cuentaUsuario);
}
