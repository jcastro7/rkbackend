package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.List;

import pe.com.tss.runakuna.view.model.ModuloFilterViewModel;
import pe.com.tss.runakuna.view.model.ModuloResultViewModel;
import pe.com.tss.runakuna.view.model.ModuloViewModel;
import pe.com.tss.runakuna.view.model.RolUsuarioPadreViewModel;

/**
 * Created by josediaz on 25/11/2016.
 */
public interface ModuloRepository {

    public List<ModuloViewModel> getModulosPermitidosPorUsuario(String cuentaUsuario);

	public List<ModuloResultViewModel> buscarModulos(ModuloFilterViewModel filterViewModel);

//	public List<RolUsuarioPadreViewModel> getRolPorUsuario(String cuentaUsuario);
}
