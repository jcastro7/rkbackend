package pe.com.empresa.rk.service;

import pe.com.empresa.rk.view.model.ProyectoResultViewModel;
import pe.com.empresa.rk.view.model.QuickFilterViewModel;
import pe.com.empresa.rk.view.model.NotificacionViewModel;
import pe.com.empresa.rk.view.model.ProyectoFilterViewModel;
import pe.com.empresa.rk.view.model.ProyectoViewModel;

public interface ProyectoService extends MaintenanceService<ProyectoFilterViewModel, ProyectoResultViewModel, ProyectoViewModel, NotificacionViewModel, Long>
, QuickSearchService<ProyectoResultViewModel, QuickFilterViewModel>{

	/*List<ProyectoViewModel> getProyecto();

	NotificacionViewModel registrarProyecto(ProyectoViewModel proyectoDto);

	NotificacionViewModel actualizarProyecto(ProyectoViewModel proyectoDto);

	ProyectoViewModel obtenerProyectoDetalle(Long idProyecto);

	List<ProyectoViewModel> obtenerProyectos(ProyectoFilterViewModel dto);

	Long eliminarProyecto(Long idProyecto);*/
}
