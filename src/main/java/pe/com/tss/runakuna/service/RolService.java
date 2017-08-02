package pe.com.tss.runakuna.service;

import java.util.List;

import pe.com.tss.runakuna.view.model.AutorizacionFilterViewModel;
import pe.com.tss.runakuna.view.model.AccionViewModel;
import pe.com.tss.runakuna.view.model.Authorization;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;
import pe.com.tss.runakuna.view.model.RolFilterViewModel;
import pe.com.tss.runakuna.view.model.RolResultViewModel;
import pe.com.tss.runakuna.view.model.RolViewModel;

public interface RolService extends MaintenanceService<RolFilterViewModel, RolResultViewModel, RolViewModel, NotificacionViewModel, Long>{

	List<Authorization> obtenerAutorizaciones(AutorizacionFilterViewModel filter);

	List<RolViewModel> getRolById(Long idRol);

	NotificacionViewModel updateAutorizacionAccion(AccionViewModel accionViewModel);

	RolViewModel findRolById(Long idRol);

	RolViewModel findAllSubModulosAccion();

	NotificacionViewModel crearRolAccion(RolViewModel rolViewModel);

	NotificacionViewModel actualizarRolAccion(RolViewModel rolViewModel);
	
}
