package pe.com.tss.runakuna.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.tss.runakuna.domain.model.entities.Job;
import pe.com.tss.runakuna.domain.model.repository.jdbc.JobEjecucionRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.EmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.EmpresaJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.JobJpaRepository;
import pe.com.tss.runakuna.service.JobService;
import pe.com.tss.runakuna.view.model.JobEjecucionFilterViewModel;
import pe.com.tss.runakuna.view.model.JobEjecucionResultViewModel;

import pe.com.tss.runakuna.view.model.JobEjecucionViewModel;

import pe.com.tss.runakuna.view.model.JobFilterViewModel;
import pe.com.tss.runakuna.view.model.JobResultViewModel;
import pe.com.tss.runakuna.view.model.JobViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;

@Service
public class JobServiceImpl implements JobService {

    final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    Mapper mapper;

    @Autowired
    EmpleadoJpaRepository empleadoJpaRepository;

    @Autowired
    JobJpaRepository jobJpaRepository;

    @Autowired
    EmpresaJpaRepository empresaJpaRepository;
    
    @Autowired
    JobEjecucionRepository jobEjecucionRepository;
  


    @Override
    public List<JobResultViewModel> search(JobFilterViewModel jobFilterViewModel) {
        return null;
    }

    @Override
    public JobViewModel findOne(Long id) {
        Job job = jobJpaRepository.findOne(id);
        JobViewModel dto = new JobViewModel();
        mapper.map(job, dto);
        
        return dto;
    }

    @Override
    public NotificacionViewModel create(JobViewModel manteinanceViewModel) {
        
        return null;
    }

    @Override
    public NotificacionViewModel update(JobViewModel manteinanceViewModel) {
        
        return null;
    }

    @Override
    public NotificacionViewModel delete(Long id) {
       
       return null;
    }

	@Override
	public List<JobResultViewModel> getListaJobStatus() {
		List<Job> jobs=jobJpaRepository.findAll();
		JobEjecucionFilterViewModel filter=new JobEjecucionFilterViewModel();
		List<JobResultViewModel>lista=new ArrayList<JobResultViewModel>();
		JobResultViewModel jobResultViewModel=new JobResultViewModel();
		for(Job job:jobs) {
			jobResultViewModel=new JobResultViewModel();
			filter=new JobEjecucionFilterViewModel();
			filter.setIdJob(job.getIdJob());
			JobEjecucionResultViewModel result=jobEjecucionRepository.obtenerJobEjecucionPendiente(filter);
			Date hoy=new Date();
			if(result!=null){
				if(result.getFechaProgramado()!=null && result.getFechaProgramado().before(hoy)){
					jobResultViewModel.setEstadoEjecucion("N");
				} else {
					result=jobEjecucionRepository.obtenerJobEjecucionEjecutado(filter);
					jobResultViewModel.setEstadoEjecucion(result.getEstado());
				}
			} else {
				result=jobEjecucionRepository.obtenerJobEjecucionEjecutado(filter);
				jobResultViewModel.setEstadoEjecucion(result.getEstado());
			}
			jobResultViewModel.setIdJob(job.getIdJob());
			jobResultViewModel.setCodigo(job.getCodigo());
			jobResultViewModel.setDescripcion(job.getDescripcion());
			if(job.getEstado()!=null && job.getEstado().equals("A")) {
				jobResultViewModel.setEstado("Activo");
			} else {
				jobResultViewModel.setEstado("Inactivo");
			}
			SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			jobResultViewModel.setFechaUltimaEjecucion(sdf.format(job.getUltimaEjecucion()));
			jobResultViewModel.setNombreClase(job.getNombreClase());
			jobResultViewModel.setMetodo(job.getMetodo());
			lista.add(jobResultViewModel);
		}	
		return lista;
	}

	@Override

	public List<JobEjecucionViewModel> obtenerJobEjecuciones(JobEjecucionFilterViewModel filter) {
		List<JobEjecucionViewModel> result=new ArrayList<JobEjecucionViewModel>();
		result=jobEjecucionRepository.obtenerEjecuciones(filter);
		return result;
	}



}
