package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.com.tss.runakuna.util.DateUtil;
import pe.com.tss.runakuna.view.model.UsuarioResetViewModel;
import pe.com.tss.runakuna.view.model.UsuarioViewModel;

@Repository
public class UsuarioResetJdbcRepository implements UsuarioResetRepository{

	
	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioResetJdbcRepository.class);

    private final NamedParameterJdbcTemplate jdbcTemplate;
    
    private String getdate;

    @Autowired
    UsuarioResetJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        
        Date date = new Date();
		getdate = DateUtil.format(new SimpleDateFormat("yyyy-MM-dd"), date);
    }
    
    @Transactional(readOnly = true)
    @Override
	public UsuarioResetViewModel buscarUsuarioPorLink(String link) {
		LOGGER.info("Buscando usuarios para resetear clave ", link);

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("link", link);
        
        StringBuffer sql =new StringBuffer();
        sql.append(" select u.IdUsuarioReset, u.IdUsuario, u.Estado, u.FechaInicio, u.FechaFin, u.Enlace, ");
        sql.append(" u.Creador as creador, ");
        sql.append(" u.RowVersion as rowVersion, ");
        sql.append(" u.FechaCreacion as fechaCreacion, ");
        sql.append(" u.Actualizador as actualizador, ");
        sql.append(" u.FechaActualizacion as fechaActualizacion ");
        sql.append("from UsuarioReset u where u.Enlace = :link and u.Estado= 'A' and (u.FechaInicio<='"+getdate+"' and u.FechaFin>='"+getdate+"')");
        

        List<UsuarioResetViewModel > searchResults = jdbcTemplate.query(sql.toString(),
                queryParams,
                new BeanPropertyRowMapper<>(UsuarioResetViewModel.class)
        );

        LOGGER.info("Found usuario {}", searchResults);

        if (searchResults.size() > 0) {
            return searchResults.get(0);
        }

        return null;
	}

}
