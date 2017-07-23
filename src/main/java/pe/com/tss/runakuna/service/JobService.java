package pe.com.tss.runakuna.service;

import java.util.List;


import pe.com.tss.runakuna.view.model.JobEjecucionFilterViewModel;
import pe.com.tss.runakuna.view.model.JobEjecucionViewModel;
import pe.com.tss.runakuna.view.model.JobExecuteManuallyViewModel;

import pe.com.tss.runakuna.view.model.JobFilterViewModel;
import pe.com.tss.runakuna.view.model.JobResultViewModel;
import pe.com.tss.runakuna.view.model.JobViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;

public interface JobService extends MaintenanceService<JobFilterViewModel, JobResultViewModel, JobViewModel, NotificacionViewModel, Long> 
{

	List<JobResultViewModel> getListaJobStatus();


	List<JobEjecucionViewModel> obtenerJobEjecuciones(JobEjecucionFilterViewModel filter);


	/*List<ProyectoViewModel> getProyecto();

	NotificacionViewModel registrarProyecto(ProyectoViewModel proyectoDto);

	NotificacionViewModel actualizarProyecto(ProyectoViewModel proyectoDto);

	ProyectoViewModel obtenerProyectoDetalle(Long idProyecto);

	List<ProyectoViewModel> obtenerProyectos(ProyectoFilterViewModel dto);

	Long eliminarProyecto(Long idProyecto);*/
}
