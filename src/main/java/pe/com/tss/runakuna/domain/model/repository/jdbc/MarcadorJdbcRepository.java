package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.support.WhereParams;
import pe.com.tss.runakuna.view.model.MarcadorFilterViewModel;
import pe.com.tss.runakuna.view.model.MarcadorResultViewModel;

@Repository
public class MarcadorJdbcRepository implements MarcadorRepository {

    private final Logger log = LoggerFactory.getLogger(getClass());

    
    
    @Autowired
    DataSource dataSource;

    private NamedParameterJdbcTemplate jdbcTemplate;
    

    @PostConstruct
    public void init() {
    	
    	
    	
    	jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


	@Override
	public List<MarcadorResultViewModel> buscarMarcador(MarcadorFilterViewModel filterViewModel) {
		WhereParams params = new WhereParams();
        String sql = generarObtenerMarcadores(filterViewModel, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<MarcadorResultViewModel>(MarcadorResultViewModel.class));

	}
	
	private String generarObtenerMarcadores(MarcadorFilterViewModel filterViewModel,
			WhereParams params) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT ");
		sql.append(" m.IdMarcador AS idMarcador, ");
		sql.append(" m.Codigo AS codigo, ");
		sql.append(" m.Nombre AS nombre, ");
		sql.append(" m.Descripcion AS descripcion ");
		sql.append(" FROM Marcador m ");
        sql.append(" WHERE 1=1 ");
        sql.append(params.filter(" AND UPPER(m.Codigo) LIKE  UPPER ('%'+ :codigo +'%') ",filterViewModel.getCodigo()));
        sql.append(params.filter(" AND UPPER(m.Nombre) LIKE  UPPER ('%'+ :nombre +'%') ",filterViewModel.getNombre()));
        
        
        return sql.toString();

	}

    

    

}