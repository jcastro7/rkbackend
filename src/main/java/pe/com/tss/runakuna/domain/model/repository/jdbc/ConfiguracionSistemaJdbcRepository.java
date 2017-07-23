package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.support.WhereParams;
import pe.com.tss.runakuna.view.model.ConfiguracionSistemaFilterViewModel;
import pe.com.tss.runakuna.view.model.ConfiguracionSistemaResultViewModel;
import pe.com.tss.runakuna.view.model.TipoLicenciaResultViewModel;

@Repository
public class ConfiguracionSistemaJdbcRepository implements ConfiguracionSistemaRepository{
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	ConfiguracionSistemaJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<ConfiguracionSistemaResultViewModel> obtenerConfiguracionesSistema(
			ConfiguracionSistemaFilterViewModel filterViewModel) {
		WhereParams params = new WhereParams();
        String sql = generarObtenerConfiguracionesSistema(filterViewModel, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<ConfiguracionSistemaResultViewModel>(ConfiguracionSistemaResultViewModel.class));
	}

	private String generarObtenerConfiguracionesSistema(ConfiguracionSistemaFilterViewModel filterViewModel,
			WhereParams params) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT ");
		sql.append(" c.IdConfiguracionSistema AS idConfiguracionSistema, ");
		sql.append(" c.Codigo AS codigo, ");
		sql.append(" c.Descripcion AS descripcion, ");
		sql.append(" c.Valor AS valor ");
		sql.append(" FROM ConfiguracionSistema c ");
        sql.append(" WHERE 1=1 ");
        sql.append(params.filter(" AND UPPER(c.Codigo) LIKE  UPPER ('%'+ :codigo +'%') ",filterViewModel.getCodigo()));
        sql.append(params.filter(" AND UPPER(c.Descripcion) LIKE  UPPER ('%'+ :descripcion +'%') ",filterViewModel.getDescripcion()));
        
        
        return sql.toString();

	}

	@Override
	public ConfiguracionSistemaResultViewModel obtenerConfiguracionesSistemaContratoIdenfinido(
			ConfiguracionSistemaFilterViewModel filterViewModel) {
		WhereParams params = new WhereParams();
        String sql = generarObtenerConfiguracionesSistema(filterViewModel, params);

        return jdbcTemplate.queryForObject(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<ConfiguracionSistemaResultViewModel>(ConfiguracionSistemaResultViewModel.class));
	}

	    
	
}
