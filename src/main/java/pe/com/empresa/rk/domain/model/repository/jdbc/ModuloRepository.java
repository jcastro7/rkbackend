package pe.com.empresa.rk.domain.model.repository.jdbc;

import java.util.List;

import pe.com.empresa.rk.view.model.ModuloFilterViewModel;
import pe.com.empresa.rk.view.model.ModuloResultViewModel;
import pe.com.empresa.rk.view.model.ModuloViewModel;

/**
 * Created by josediaz on 25/11/2016.
 */
public interface ModuloRepository {

    public List<ModuloViewModel> getModulosPermitidosPorUsuario(String cuentaUsuario);

	public List<ModuloResultViewModel> buscarModulos(ModuloFilterViewModel filterViewModel);

//	public List<RolUsuarioPadreViewModel> getRolPorUsuario(String cuentaUsuario);
}
