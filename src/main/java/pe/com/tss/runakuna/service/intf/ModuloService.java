package pe.com.tss.runakuna.service.intf;

import java.util.List;

import pe.com.tss.runakuna.service.MaintenanceService;
import pe.com.tss.runakuna.view.model.ModuloFilterViewModel;
import pe.com.tss.runakuna.view.model.ModuloResultViewModel;
import pe.com.tss.runakuna.view.model.ModuloViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;

/**
 * Created by josediaz on 25/11/2016.
 */
public interface ModuloService extends MaintenanceService<ModuloFilterViewModel, ModuloResultViewModel, ModuloViewModel, NotificacionViewModel, Long> {

    public List<ModuloViewModel> getModulosPermitidosPorUsuario(String cuentaUsuario) ;

}
