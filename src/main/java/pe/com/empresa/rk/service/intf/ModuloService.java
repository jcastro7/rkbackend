package pe.com.empresa.rk.service.intf;

import java.util.List;

import pe.com.empresa.rk.service.MaintenanceService;
import pe.com.empresa.rk.view.model.ModuloViewModel;
import pe.com.empresa.rk.view.model.ModuloFilterViewModel;
import pe.com.empresa.rk.view.model.ModuloResultViewModel;
import pe.com.empresa.rk.view.model.NotificacionViewModel;

/**
 * Created by josediaz on 25/11/2016.
 */
public interface ModuloService extends MaintenanceService<ModuloFilterViewModel, ModuloResultViewModel, ModuloViewModel, NotificacionViewModel, Long> {

    public List<ModuloViewModel> getModulosPermitidosPorUsuario(String cuentaUsuario) ;

}
