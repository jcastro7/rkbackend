package pe.com.tss.runakuna.service;

import pe.com.tss.runakuna.view.model.JobEjecucionFilterViewModel;
import pe.com.tss.runakuna.view.model.JobEjecucionResultViewModel;
import pe.com.tss.runakuna.view.model.JobEjecucionViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;

public interface JobEjecucionService extends MaintenanceService<JobEjecucionFilterViewModel, JobEjecucionResultViewModel, JobEjecucionViewModel, NotificacionViewModel, Long> 
{
	NotificacionViewModel registarJobEjecucion(JobEjecucionViewModel jobEjecucionViewModel, String codigoJob);
	NotificacionViewModel registarJobEjecucionEnBlanco(JobEjecucionViewModel jobEjecucionViewModel, String codigoJob);
	JobEjecucionResultViewModel obtenerEjecucionJob (String codigo);
	JobEjecucionResultViewModel obtenerEjecucionJobPendiente (String codigo);
	NotificacionViewModel actualizarJobEjecucion(JobEjecucionViewModel jobEjecucionViewModel, String string);

}
