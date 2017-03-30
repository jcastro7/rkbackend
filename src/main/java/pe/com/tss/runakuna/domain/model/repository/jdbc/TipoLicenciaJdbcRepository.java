package pe.com.tss.runakuna.domain.model.repository.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import pe.com.tss.runakuna.support.WhereParams;
import pe.com.tss.runakuna.view.model.LicenciaViewModel;
import pe.com.tss.runakuna.view.model.TipoLicenciaFilterViewModel;
import pe.com.tss.runakuna.view.model.TipoLicenciaResultViewModel;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;

@Repository
public class TipoLicenciaJdbcRepository implements TipoLicenciaRepository{



    private static final Logger LOGGER = LoggerFactory.getLogger(TipoLicenciaJdbcRepository.class);

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    TipoLicenciaJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired

    DataSource dataSource;


    @Override
    public List<TipoLicenciaResultViewModel> obtenerTipoLicencias(TipoLicenciaFilterViewModel filterViewModel) {
        WhereParams params = new WhereParams();
        String sql = generarObtenerCargos(filterViewModel, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<TipoLicenciaResultViewModel>(TipoLicenciaResultViewModel.class));

    }

    @Override
    public List<LicenciaViewModel> obtenerLicenciaByTipoLicencia(Long idTipoLicencia) {
        WhereParams params = new WhereParams();
        String sql = generarObtenerLicencia(idTipoLicencia, params);


        return jdbcTemplate.query(sql,params.getParams(),new BeanPropertyRowMapper<LicenciaViewModel>(LicenciaViewModel.class));

    }



    private String generarObtenerCargos(TipoLicenciaFilterViewModel filterViewModel, WhereParams params) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append(" t.IdTipoLicencia AS idTipoLicencia, ");
        sql.append(" t.Codigo AS codigo, ");
        sql.append(" t.Nombre AS nombre, ");
        sql.append(" CASE WHEN (t.Estado='A')  THEN 'Activo' ELSE 'Inactivo' END AS estado, ");
        sql.append(" t.LimiteMensual AS limiteMensual, ");
        sql.append(" t.limiteAnual AS limiteAnual ");
        sql.append(" FROM TipoLicencia t ");
        sql.append(" WHERE 1=1 ");
        sql.append(params.filter(" AND UPPER(t.Codigo) LIKE  UPPER ('%'+ :codigo +'%') ",filterViewModel.getCodigo()));
        sql.append(params.filter(" AND UPPER(t.Nombre) LIKE  UPPER ('%'+ :nombre +'%') ",filterViewModel.getNombre()));
        sql.append(params.filter(" AND t.Estado = :estado ",filterViewModel.getEstado()));

        return sql.toString();
    }


    private String generarObtenerLicencia(Long idTipoLicencia, WhereParams params) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT 1 ");
        sql.append(" FROM Licencia t ");
        sql.append(" WHERE t.idTipoLicencia = ").append(idTipoLicencia);
        return sql.toString();
    }

    @Override
    public void eliminarTipoLicencia(Long idTipoLicencia) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("spuEliminarTipoLicencia");

        SqlParameterSource in = new MapSqlParameterSource().addValue("idTipoLicencia", idTipoLicencia,Types.NUMERIC);


        jdbcCall.execute(in);



    }

}