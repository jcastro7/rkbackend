package pe.com.empresa.rk.domain.model.repository.jdbc;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.empresa.rk.support.WhereParams;
import pe.com.empresa.rk.view.model.TablaGeneralResultViewModel;
import pe.com.empresa.rk.view.model.TablaGeneralFilterViewModel;
import pe.com.empresa.rk.view.model.TablaGeneralViewModel;

@Repository
public class TablaGeneralJdbcRepository {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
    DataSource dataSource;

    private NamedParameterJdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

	public List<TablaGeneralResultViewModel> obtenerCodigosTablaGeneral(TablaGeneralFilterViewModel dto) {

		WhereParams params = new WhereParams();
        String sql = obtenerCodigosTablaGeneralQuery(dto, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<>(TablaGeneralResultViewModel.class));
	}

	private String obtenerCodigosTablaGeneralQuery(TablaGeneralFilterViewModel dto, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT c.IdTablaGeneral AS idTablaGeneral, ");
		sql.append(" c.Codigo AS codigo, ");
		sql.append(" c.Nombre AS nombre, ");
		sql.append(" c.Grupo AS grupo, ");
		sql.append(" case when (c.Estado ='A') then 'Activo' else 'Inactivo' end AS estado, ");
		sql.append(" c.Creador as creador, ");
        sql.append(" c.RowVersion as rowVersion, ");
        sql.append(" c.FechaCreacion as fechaCreacion, ");
        sql.append(" c.Actualizador as actualizador, ");
        sql.append(" c.FechaActualizacion as fechaActualizacion ");
		sql.append(" FROM TablaGeneral c ");
		sql.append(" LEFT JOIN Empresa e ON c.IdEmpresa=e.IdEmpresa ");
		sql.append(" WHERE 1=1 ");
		sql.append(params.filter(" AND c.Grupo = :grupo ", dto.getGrupo()));
		sql.append(params.filter(" AND UPPER(C.Nombre) LIKE  UPPER ('%'+ :nombre +'%') ", dto.getNombre()));

		return sql.toString();
	}

	public List<TablaGeneralViewModel> buscarGrupoTablaGeneral() {
		WhereParams params = new WhereParams();
        String sql = buscarGrupoTablaGeneralQuery(params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<>(TablaGeneralViewModel.class));
	}

	private String buscarGrupoTablaGeneralQuery(WhereParams params) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT distinct c.Grupo AS grupo ");
		sql.append(" FROM TablaGeneral c ");
		sql.append(" WHERE 1=1 ");

		return sql.toString();
	}



}
