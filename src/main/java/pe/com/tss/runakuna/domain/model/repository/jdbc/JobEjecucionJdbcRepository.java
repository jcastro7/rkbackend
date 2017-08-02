package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.support.WhereParams;
import pe.com.tss.runakuna.view.model.JobEjecucionFilterViewModel;
import pe.com.tss.runakuna.view.model.JobEjecucionResultViewModel;
import pe.com.tss.runakuna.view.model.JobEjecucionViewModel;
import pe.com.tss.runakuna.view.model.JobFilterViewModel;
import pe.com.tss.runakuna.view.model.JobResultViewModel;
import pe.com.tss.runakuna.view.model.ProyectoFilterViewModel;
import pe.com.tss.runakuna.view.model.ProyectoQuickFilterViewModel;
import pe.com.tss.runakuna.view.model.ProyectoResultViewModel;
import pe.com.tss.runakuna.view.model.QuickFilterViewModel;

@Repository
public class JobEjecucionJdbcRepository implements JobEjecucionRepository{

	private static final Logger LOGGER = LoggerFactory.getLogger(JobEjecucionJdbcRepository.class);
			
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	JobEjecucionJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private String generarObtenerJobEjecucion(JobEjecucionFilterViewModel dto, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select top 1 je.IdJob,je.IdJobEjecucion,je.Estado,je.ResultadoMensaje ,je.Ejecutado, je.FechaProgramado");
		sql.append(" from JobEjecucion je  ");
		sql.append(" where je.IdJob = ").append(dto.getIdJob());
		sql.append(" order by je.fechaInicio desc ");
		return sql.toString();
	}
	
	private String generarObtenerJobEjecucionPendiente(JobEjecucionFilterViewModel dto, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select top 1 je.IdJob,je.IdJobEjecucion,je.Estado,je.ResultadoMensaje ,je.Ejecutado, je.FechaProgramado ");
		sql.append(" from JobEjecucion je  ");
		sql.append(" where je.IdJob = ").append(dto.getIdJob()).append(" and je.Ejecutado = 0 ");
		sql.append(" order by je.fechaInicio desc ");
		return sql.toString();
	}
	
	private String generarObtenerJobEjecucionEjecutado(JobEjecucionFilterViewModel dto, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select top 1 je.IdJob,je.IdJobEjecucion,je.Estado,je.ResultadoMensaje ,je.Ejecutado, je.FechaProgramado ");
		sql.append(" from JobEjecucion je  ");
		sql.append(" where je.IdJob = ").append(dto.getIdJob()).append(" and je.Ejecutado = 1 ");
		sql.append(" order by je.fechaInicio desc ");
		return sql.toString();
	}

	@Override
	public JobEjecucionResultViewModel obtenerJobEjecucion(JobEjecucionFilterViewModel dto) {
		WhereParams params = new WhereParams();
		JobEjecucionResultViewModel result=new JobEjecucionResultViewModel();
        String sql = generarObtenerJobEjecucion(dto, params);

        List<JobEjecucionResultViewModel> list= jdbcTemplate.query(sql.toString(),params.getParams(),
        		new BeanPropertyRowMapper<JobEjecucionResultViewModel>(JobEjecucionResultViewModel.class));
        if(list!=null && list.size()>0)
        	result=list.get(0);
        return result;
	}
	
	@Override
	public JobEjecucionResultViewModel obtenerJobEjecucionPendiente(JobEjecucionFilterViewModel dto) {
		WhereParams params = new WhereParams();
		JobEjecucionResultViewModel result=new JobEjecucionResultViewModel();
        String sql = generarObtenerJobEjecucionPendiente(dto, params);

        List<JobEjecucionResultViewModel> list= jdbcTemplate.query(sql.toString(),params.getParams(), 
        		new BeanPropertyRowMapper<JobEjecucionResultViewModel>(JobEjecucionResultViewModel.class));
        if(list!=null && list.size()>0)
        	result=list.get(0);
        return result;
	}
	
	@Override
	public JobEjecucionResultViewModel obtenerJobEjecucionEjecutado(JobEjecucionFilterViewModel dto) {
		WhereParams params = new WhereParams();
		JobEjecucionResultViewModel result=new JobEjecucionResultViewModel();
        String sql = generarObtenerJobEjecucionEjecutado(dto, params);

        List<JobEjecucionResultViewModel> list= jdbcTemplate.query(sql.toString(),params.getParams(), 
        		new BeanPropertyRowMapper<JobEjecucionResultViewModel>(JobEjecucionResultViewModel.class));
        if(list!=null && list.size()>0)
        	result=list.get(0);
        return result;
	}

	
	private String generarObtenerJobEjecuciones(JobEjecucionFilterViewModel dto, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select je.IdJob,je.IdJobEjecucion,je.ResultadoMensaje, je.FechaInicio, je.FechaFin, ");
		sql.append(" CASE ");
        sql.append(" WHEN  (je.Estado='F') THEN 'Finalizado' ");
        sql.append(" WHEN  (je.Estado='E') THEN 'Error' ");
        sql.append(" ELSE 'No Ejecutado' ");
		sql.append(" END AS estado ");
		sql.append(" from JobEjecucion je  ");
		sql.append(" WHERE 1=1 ");
		sql.append(params.filter(" and  je.IdJob = :idJob ",dto.getIdJob()));
		sql.append(params.filter(" and  je.FechaInicio >= :fechaInicioDesde ",dto.getFechaInicio()));
		sql.append(params.filter(" and  je.FechaFin <= :fechaInicioHasta ",dto.getFechaFin()));
		sql.append(" order by je.fechaInicio desc ");
		
     	return sql.toString();
	}

	@Override
	public List<JobEjecucionViewModel> obtenerEjecuciones(JobEjecucionFilterViewModel dto) {
		WhereParams params = new WhereParams();
		String sql = generarObtenerJobEjecuciones(dto, params);

        List<JobEjecucionViewModel> list= jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<JobEjecucionViewModel>(JobEjecucionViewModel.class));

        return list;
	}
	
	
	
	

}
