package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.List;

import pe.com.tss.runakuna.view.model.JobEjecucionFilterViewModel;
import pe.com.tss.runakuna.view.model.JobEjecucionResultViewModel;
import pe.com.tss.runakuna.view.model.JobEjecucionViewModel;

public interface JobEjecucionRepository {

	JobEjecucionResultViewModel obtenerJobEjecucion(JobEjecucionFilterViewModel dto);
	JobEjecucionResultViewModel obtenerJobEjecucionPendiente(JobEjecucionFilterViewModel dto);
	JobEjecucionResultViewModel obtenerJobEjecucionEjecutado(JobEjecucionFilterViewModel dto);
	List<JobEjecucionViewModel> obtenerEjecuciones(JobEjecucionFilterViewModel dto);

	
	

	
}
