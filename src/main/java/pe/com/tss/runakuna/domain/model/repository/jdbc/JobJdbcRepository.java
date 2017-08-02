package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.domain.model.entities.Job;
import pe.com.tss.runakuna.support.WhereParams;
import pe.com.tss.runakuna.view.model.JobFilterViewModel;
import pe.com.tss.runakuna.view.model.JobResultViewModel;
import pe.com.tss.runakuna.view.model.JobViewModel;
import pe.com.tss.runakuna.view.model.ProyectoFilterViewModel;
import pe.com.tss.runakuna.view.model.ProyectoQuickFilterViewModel;
import pe.com.tss.runakuna.view.model.ProyectoResultViewModel;
import pe.com.tss.runakuna.view.model.QuickFilterViewModel;

@Repository
public class JobJdbcRepository implements JobRepository{

	private static final Logger LOGGER = LoggerFactory.getLogger(JobJdbcRepository.class);
			
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	JobJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<JobResultViewModel> obtenerJobs(JobFilterViewModel dto) {
		WhereParams params = new WhereParams();
        String sql = generarObtenerJobs(dto, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<JobResultViewModel>(JobResultViewModel.class));

	}

	private String generarObtenerJobs(JobFilterViewModel dto, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT ");
		sql.append(" P.IdJob AS idJob, ");
		sql.append(" P.Codigo AS codigo, ");
		sql.append(" P.Descripcion AS descripcion, ");
		sql.append(" CASE WHEN (P.Estado='A') THEN 'Activo' WHEN (P.Estado='I') THEN 'Inactivo' ELSE '' END AS estado ");
		sql.append(" FROM Job P ");
        
        sql.append(" WHERE 1=1 ");
        sql.append(params.filter(" AND UPPER(P.Codigo) LIKE  UPPER ('%'+ :codigo +'%') ",dto.getCodigo()));
        sql.append(params.filter(" AND UPPER(P.Descripcion) LIKE  UPPER ('%'+ :nombtre +'%') ",dto.getDescripcion()));
        
        
        
		return sql.toString();
	}

	@Override
	public JobViewModel findByCodigo(String codigoJob) {
		WhereParams params = new WhereParams();
        String sql = generarObtenerJobByCodigo(codigoJob, params);

        return jdbcTemplate.queryForObject(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<JobViewModel>(JobViewModel.class));

	}
	
	private String generarObtenerJobByCodigo(String codigo, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT ");
		sql.append(" P.IdJob AS idJob, ");
		sql.append(" P.Codigo AS codigo, ");
		sql.append(" P.Descripcion AS descripcion, ");
		sql.append(" P.Estado AS estado, ");
		sql.append(" P.UltimaEjecucion AS ultimaEjecucion ");
		sql.append(" FROM Job P ");
        sql.append(" WHERE 1=1 ");
        sql.append(params.filter(" AND UPPER(P.Codigo) = UPPER(:codigo)  ",codigo));
        
        return sql.toString();
	}
	
	

}
