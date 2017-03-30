package pe.com.empresa.rk.domain.model.repository.jdbc;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import pe.com.empresa.rk.support.WhereParams;
import pe.com.empresa.rk.view.model.AlertaFilterViewModel;
import pe.com.empresa.rk.view.model.AlertaSubscriptorViewModel;
import pe.com.empresa.rk.view.model.AlertaEmpleadoViewModel;
import pe.com.empresa.rk.view.model.AlertaResultViewModel;
import pe.com.empresa.rk.view.model.AlertaViewModel;

/**
 * Created by josediaz on 14/12/2016.
 */
@Repository
public class AlertaJdbcRepository implements AlertaRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlertaJdbcRepository.class);

    private final NamedParameterJdbcTemplate jdbcTemplate;
    
    @Autowired
    DataSource dataSource;

    @Autowired
    AlertaJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public AlertaViewModel obtenerAlerta(String codigo) {
        WhereParams params = new WhereParams();
        String sql = generarAlerta(codigo, params);

        return jdbcTemplate.queryForObject(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<AlertaViewModel>(AlertaViewModel.class));
    }

    private String generarAlerta(String codigo, WhereParams params) {

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append(" A.IdAlerta AS idAlerta, ");
        sql.append(" A.IdEmpresa AS idEmpresa, ");
        sql.append(" A.Codigo as codigo, ");
        sql.append(" A.TipoAlerta as tipoAlerta, ");
        sql.append(" A.TipoNotificacion as tipoNotificacion, ");
        sql.append(" A.Asunto as asunto, ");
        sql.append(" A.Cuerpo as cuerpo, ");
        sql.append(" A.Alerta as alerta, ");
        sql.append(" A.JefeProyecto as jefeProyecto, ");
        sql.append(" A.JefeDepartamento as jefeDepartamento, ");
        sql.append(" A.JefeUnidad as jefeUnidad, ");
        sql.append(" A.JefeEmpresa as jefeEmpresa, ");
        sql.append(" A.Alerta as alerta, ");
        sql.append(" A.Agrupamiento as agrupamiento, ");
        sql.append(" A.Estado as estado ");
        sql.append(" FROM Alerta A ");

        sql.append(" WHERE 1=1 ");

        sql.append(params.filter(" AND A.Codigo = :codigo ",codigo));

        return sql.toString();
    }


	@Override
	public List<AlertaResultViewModel> obtenerAlertas(AlertaFilterViewModel dto) {
		WhereParams params = new WhereParams();
        String sql = generarBusquedaAlerta(dto, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<AlertaResultViewModel>(AlertaResultViewModel.class));
	}


	private String generarBusquedaAlerta(AlertaFilterViewModel dto, WhereParams params) {
		StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append(" A.IdAlerta AS idAlerta, ");
        sql.append(" A.Codigo as codigo, ");
        sql.append(" TipoAlerta.Nombre as tipoAlerta, ");
        sql.append(" TipoNotificacion.Nombre as tipoNotificacion, ");
        sql.append(" CASE WHEN  (A.Estado='A') THEN 'Activo' ELSE 'Inactivo' END as estado ");
        sql.append(" FROM Alerta A ");
        sql.append(" LEFT JOIN TablaGeneral TipoAlerta ON A.TipoAlerta=TipoAlerta.Codigo and TipoAlerta.GRUPO='Alerta.Tipo'");
        sql.append(" LEFT JOIN TablaGeneral TipoNotificacion ON A.TipoNotificacion=TipoNotificacion.Codigo and TipoNotificacion.GRUPO='Alerta.TipoNotificacion'");

        sql.append(" WHERE 1=1 ");
        sql.append(params.filter(" AND A.TipoNotificacion = :codigoTipoNotificacion ",dto.getTipoNotificacion()));
        sql.append(params.filter(" AND A.Estado = :estado ",dto.getEstado()));
        
        return sql.toString();
	}


	@Override
	public List<AlertaEmpleadoViewModel> obtenerAlertaEmpleado(Long idEmpleado) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("spuAlertasEmpleadosPendientes")
	    		   //.withCatalogName("dbo")
	    		   .returningResultSet("MensajeAlerta",new BeanPropertyRowMapper<AlertaEmpleadoViewModel>(AlertaEmpleadoViewModel.class))
	    		   /*.declareParameters(new SqlParameter("idAlerta", Types.NUMERIC),
	    				              new SqlParameter("idEmpleado", Types.NUMERIC))*/
	    		   ;
		  SqlParameterSource in = new MapSqlParameterSource().addValue("idEmpleado", idEmpleado,Types.NUMERIC);
		  
	       Map<String, Object> out = jdbcCall.execute(in);
	       List<AlertaEmpleadoViewModel> lista = (List<AlertaEmpleadoViewModel>)out.get("MensajeAlerta");
	             
	       return lista;
	}


	public List<AlertaSubscriptorViewModel> obtenerSubscriptoresAlertas(AlertaViewModel dto) {
		WhereParams params = new WhereParams();
        String sql = generarBusquedaSubscriptoresAlertas(dto, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<AlertaSubscriptorViewModel>(AlertaSubscriptorViewModel.class));
	}


	private String generarBusquedaSubscriptoresAlertas(AlertaViewModel dto, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		
        sql.append(" SELECT distinct A.IdAlerta AS idAlerta, ");
        sql.append(" EMP.Nombre+' '+EMP.ApellidoPaterno+' '+EMP.ApellidoMaterno AS nombreEmpleado, ");
        sql.append(" RMS.IdAlertaSubscriptor as idAlertaSubscriptor, ");
        sql.append(" RMS.Creador as creador, ");
        sql.append(" RMS.RowVersion as rowVersion, ");
        sql.append(" RMS.FechaCreacion as fechaCreacion, ");
        sql.append(" RMS.Actualizador as actualizador, ");
        sql.append(" RMS.FechaActualizacion as fechaActualizacion, ");
        sql.append(" RMS.IdEmpleado AS idEmpleado ");
        sql.append(" FROM AlertaSubscriptor RMS ");
        sql.append(" LEFT JOIN Alerta A ON A.IdAlerta=RMS.IdAlerta ");
        sql.append(" LEFT JOIN Empleado EMP ON RMS.IdEmpleado = EMP.IdEmpleado ");
        sql.append(" WHERE 1=1 ");
        sql.append(params.filter(" AND A.IdAlerta = :idAlerta ",dto.getIdAlerta()));
        
        return sql.toString();
	}
}
