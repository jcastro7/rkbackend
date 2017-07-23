package pe.com.tss.runakuna.service;

import pe.com.tss.runakuna.view.model.NotificacionViewModel;
import pe.com.tss.runakuna.view.model.ProyectoFilterViewModel;
import pe.com.tss.runakuna.view.model.ProyectoResultViewModel;
import pe.com.tss.runakuna.view.model.ProyectoViewModel;
import pe.com.tss.runakuna.view.model.QuickFilterViewModel;

public interface ProyectoService extends MaintenanceService<ProyectoFilterViewModel, ProyectoResultViewModel, ProyectoViewModel, NotificacionViewModel, Long> 
, QuickSearchService<ProyectoResultViewModel, QuickFilterViewModel>{

	/*List<ProyectoViewModel> getProyecto();

	NotificacionViewModel registrarProyecto(ProyectoViewModel proyectoDto);

	NotificacionViewModel actualizarProyecto(ProyectoViewModel proyectoDto);

	ProyectoViewModel obtenerProyectoDetalle(Long idProyecto);

	List<ProyectoViewModel> obtenerProyectos(ProyectoFilterViewModel dto);

	Long eliminarProyecto(Long idProyecto);*/
}
