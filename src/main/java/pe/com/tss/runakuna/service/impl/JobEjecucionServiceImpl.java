package pe.com.tss.runakuna.service.impl;

import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.tss.runakuna.domain.model.entities.Job;
import pe.com.tss.runakuna.domain.model.entities.JobEjecucion;
import pe.com.tss.runakuna.domain.model.repository.jdbc.JobEjecucionRepository;
import pe.com.tss.runakuna.domain.model.repository.jdbc.JobRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.EmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.JobEjecucionJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.JobJpaRepository;
import pe.com.tss.runakuna.enums.SeverityStatusEnum;
import pe.com.tss.runakuna.exception.BusinessException;
import pe.com.tss.runakuna.service.JobEjecucionService;
import pe.com.tss.runakuna.view.model.JobEjecucionFilterViewModel;
import pe.com.tss.runakuna.view.model.JobEjecucionResultViewModel;
import pe.com.tss.runakuna.view.model.JobEjecucionViewModel;
import pe.com.tss.runakuna.view.model.JobViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;

@Service
public class JobEjecucionServiceImpl implements JobEjecucionService {

    final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    Mapper mapper;

    @Autowired
    EmpleadoJpaRepository empleadoJpaRepository;

    @Autowired
    JobJpaRepository jobJpaRepository;
    
    @Autowired
    JobRepository jobRepository;

    @Autowired
    JobEjecucionJpaRepository jobEjecucionJpaRepository;
    
    @Autowired
    JobEjecucionRepository jobEjecucionRepository;
  

    @Override
    public NotificacionViewModel delete(Long id) {
       
       return null;
    }

	@Override
	public List<JobEjecucionResultViewModel> search(JobEjecucionFilterViewModel filterViewModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JobEjecucionViewModel findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NotificacionViewModel create(JobEjecucionViewModel manteinanceViewModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NotificacionViewModel update(JobEjecucionViewModel manteinanceViewModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NotificacionViewModel registarJobEjecucion(JobEjecucionViewModel jobEjecucionViewModel, String codigoJob) {
		NotificacionViewModel notificacionDto=new NotificacionViewModel();
		 try {
			JobViewModel jobViewModel= jobRepository.findByCodigo(codigoJob);
			JobEjecucion jobEjecucion=new JobEjecucion();
			mapper.map(jobEjecucionViewModel, jobEjecucion);
			Job job=jobJpaRepository.findOne(jobViewModel.getIdJob());
			jobEjecucion.setJob(job);
			jobEjecucion.setEjecutado(1);
			jobEjecucionJpaRepository.save(jobEjecucion);
			job.setUltimaEjecucion(jobEjecucion.getFechaInicio());
			jobJpaRepository.save(job);
			notificacionDto.setCodigo(1L);
	        notificacionDto.setSeverity("success");
	        notificacionDto.setSummary("Informativo");
	        notificacionDto.setDetail("Se registro correctamente");
		 } catch (Exception e) {
			 	notificacionDto.setCodigo(0L);
		        notificacionDto.setSeverity("error");
		        notificacionDto.setSummary("Error");
		        notificacionDto.setDetail("No se registro correctamente");
	        	throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", e.getMessage());
	     }
		
		return notificacionDto;
	}
	
	@Override
	public JobEjecucionResultViewModel obtenerEjecucionJob (String codigoJob) {
		JobViewModel jobViewModel= jobRepository.findByCodigo(codigoJob);
		JobEjecucionFilterViewModel filter=new JobEjecucionFilterViewModel();
		filter.setIdJob(jobViewModel.getIdJob());
		JobEjecucionResultViewModel result=jobEjecucionRepository.obtenerJobEjecucion(filter);
		return result;
		
	}
	@Override
	public JobEjecucionResultViewModel obtenerEjecucionJobPendiente (String codigoJob) {
		JobViewModel jobViewModel= jobRepository.findByCodigo(codigoJob);
		JobEjecucionFilterViewModel filter=new JobEjecucionFilterViewModel();
		filter.setIdJob(jobViewModel.getIdJob());
		JobEjecucionResultViewModel result=jobEjecucionRepository.obtenerJobEjecucionPendiente(filter);
		return result;
		
	}

	@Override
	public NotificacionViewModel registarJobEjecucionEnBlanco(JobEjecucionViewModel jobEjecucionViewModel,
			String codigoJob) {
		NotificacionViewModel notificacionDto=new NotificacionViewModel();
		 try {
			JobViewModel jobViewModel= jobRepository.findByCodigo(codigoJob);
			JobEjecucion jobEjecucion=new JobEjecucion();
			mapper.map(jobEjecucionViewModel, jobEjecucion);
			Job job=jobJpaRepository.findOne(jobViewModel.getIdJob());
			jobEjecucion.setJob(job);
			jobEjecucion.setEjecutado(0);
			jobEjecucionJpaRepository.save(jobEjecucion);
			//job.setUltimaEjecucion(jobEjecucion.getFechaInicio());
			//jobJpaRepository.save(job);
			notificacionDto.setCodigo(1L);
	        notificacionDto.setSeverity("success");
	        notificacionDto.setSummary("Informativo");
	        notificacionDto.setDetail("Se registro correctamente");
		 } catch (Exception e) {
			 	notificacionDto.setCodigo(0L);
		        notificacionDto.setSeverity("error");
		        notificacionDto.setSummary("Error");
		        notificacionDto.setDetail("No se registro correctamente");
	        	throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", e.getMessage());
	     }
		
		return notificacionDto;
	}

	@Override
	public NotificacionViewModel actualizarJobEjecucion(JobEjecucionViewModel jobEjecucionViewModel, String codigoJob) {
		NotificacionViewModel notificacionDto=new NotificacionViewModel();
		 try {
			JobViewModel jobViewModel= jobRepository.findByCodigo(codigoJob);
			JobEjecucion jobEjecucion=new JobEjecucion();
			jobEjecucion=jobEjecucionJpaRepository.findOne(jobEjecucionViewModel.getIdJobEjecucion());
			Job job=jobJpaRepository.findOne(jobViewModel.getIdJob());
			jobEjecucion.setJob(job);
			jobEjecucion.setEjecutado(1);
			jobEjecucion.setFechaInicio(jobEjecucionViewModel.getFechaInicio());
			jobEjecucion.setFechaFin(jobEjecucionViewModel.getFechaFin());
			jobEjecucion.setEstado(jobEjecucionViewModel.getEstado());
			jobEjecucion.setResultadoMensaje(jobEjecucionViewModel.getResultadoMensaje());
			jobEjecucionJpaRepository.save(jobEjecucion);
			job.setUltimaEjecucion(jobEjecucion.getFechaInicio());
			jobJpaRepository.save(job);
			notificacionDto.setCodigo(1L);
	        notificacionDto.setSeverity("success");
	        notificacionDto.setSummary("Informativo");
	        notificacionDto.setDetail("Se registro correctamente");
		 } catch (Exception e) {
			 	notificacionDto.setCodigo(0L);
		        notificacionDto.setSeverity("error");
		        notificacionDto.setSummary("Error");
		        notificacionDto.setDetail("No se registro correctamente");
	        	throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error", e.getMessage());
	     }
		 return notificacionDto;
	}

	

   

    


}
