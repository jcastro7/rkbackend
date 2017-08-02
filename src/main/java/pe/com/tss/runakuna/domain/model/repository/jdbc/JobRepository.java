package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.List;

import pe.com.tss.runakuna.domain.model.entities.Job;
import pe.com.tss.runakuna.view.model.JobFilterViewModel;
import pe.com.tss.runakuna.view.model.JobResultViewModel;
import pe.com.tss.runakuna.view.model.JobViewModel;

public interface JobRepository {

	List<JobResultViewModel> obtenerJobs(JobFilterViewModel dto);
	JobViewModel findByCodigo(String codigoJob);	

	
}
