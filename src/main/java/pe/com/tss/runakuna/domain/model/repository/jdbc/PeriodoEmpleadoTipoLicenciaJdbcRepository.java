package pe.com.tss.runakuna.domain.model.repository.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.com.tss.runakuna.support.WhereParams;
import pe.com.tss.runakuna.view.model.PeriodoEmpleadoTipoLicenciaViewModel;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

@Repository

public class PeriodoEmpleadoTipoLicenciaJdbcRepository implements PeriodoEmpleadoTipoLicenciaRepository{


    private static final Logger LOGGER = LoggerFactory.getLogger(PeriodoEmpleadoTipoLicenciaRepository.class);

    @Autowired
    DataSource dataSource;

    private NamedParameterJdbcTemplate jdbcTemplate;


    @PostConstruct
    public void init() {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    @Override
    public List<PeriodoEmpleadoTipoLicenciaViewModel> obtenerDiasPorPeriodoEmpleadoTipoLicencia(
            PeriodoEmpleadoTipoLicenciaViewModel periodoEmpleadoTipoLicenciaDto) {
        WhereParams params = new WhereParams();
        String sql = generarDiasPeriodoEmpleadoTipoLicencia(periodoEmpleadoTipoLicenciaDto, params);

        return jdbcTemplate.query(sql, params.getParams(),
                new BeanPropertyRowMapper<PeriodoEmpleadoTipoLicenciaViewModel>(PeriodoEmpleadoTipoLicenciaViewModel.class));
    }

    @Override
    public BigDecimal obtenerDiasLicenciaPorTipo(Long idTipoLicencia) {
        WhereParams params = new WhereParams();
        String sql = generarObtenerDiasLicenciaPorTipo(idTipoLicencia, params);

        return  (BigDecimal)jdbcTemplate.queryForObject(sql, params.getParams(), BigDecimal.class);

    }



    private String generarObtenerDiasLicenciaPorTipo(Long idTipoLicencia, WhereParams params) {
        StringBuilder sql = new StringBuilder();
        sql.append("  select sum(DiasLicencia) as dias from PeriodoEmpleadoTipoLicencia ");
        sql.append("  where IdTipoLicencia = "+idTipoLicencia);

        return sql.toString();
    }



    private String generarDiasPeriodoEmpleadoTipoLicencia(PeriodoEmpleadoTipoLicenciaViewModel periodoEmpleadoTipoLicenciaDto, WhereParams params) {
        StringBuilder sql = new StringBuilder();
        sql.append("  SELECT pe.IdPeriodoEmpleadoTipoLicencia as idPeriodoEmpleadoTipoLicencia, ");

        sql.append("  CASE WHEN (pe.DiasLicencia IS NULL) THEN 0 ELSE pe.DiasLicencia END AS dias ");
        sql.append("  FROM ");
        sql.append("  PeriodoEmpleadoTipoLicencia pe ");
        sql.append("  WHERE pe.IdPeriodoEmpleado= "+periodoEmpleadoTipoLicenciaDto.getIdPeriodoEmpleado());
        sql.append("  AND pe.IdTipoLicencia= "+periodoEmpleadoTipoLicenciaDto.getIdTipoLicencia());

        return sql.toString();
    }






}
