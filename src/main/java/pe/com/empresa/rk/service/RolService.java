package pe.com.empresa.rk.service;

import java.util.List;

import pe.com.empresa.rk.view.model.AutorizacionFilterViewModel;
import pe.com.empresa.rk.view.model.AccionViewModel;
import pe.com.empresa.rk.view.model.Authorization;
import pe.com.empresa.rk.view.model.NotificacionViewModel;
import pe.com.empresa.rk.view.model.RolFilterViewModel;
import pe.com.empresa.rk.view.model.RolResultViewModel;
import pe.com.empresa.rk.view.model.RolViewModel;

public interface RolService extends MaintenanceService<RolFilterViewModel, RolResultViewModel, RolViewModel, NotificacionViewModel, Long>{

	List<Authorization> obtenerAutorizaciones(AutorizacionFilterViewModel filter);

	List<RolViewModel> getRolById(Long idRol);

	NotificacionViewModel updateAutorizacionAccion(AccionViewModel accionViewModel);

	RolViewModel findRolById(Long idRol);

	NotificacionViewModel actualizarRolAccion(RolViewModel rolViewModel);

	RolViewModel findAllSubModulosAccion();
	
}
