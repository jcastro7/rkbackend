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
import pe.com.empresa.rk.view.model.AccionViewModel;
import pe.com.empresa.rk.view.model.AutorizacionFilterViewModel;
import pe.com.empresa.rk.view.model.ModuloViewModel;
import pe.com.empresa.rk.view.model.RolResultViewModel;
import pe.com.empresa.rk.view.model.Authorization;
import pe.com.empresa.rk.view.model.RolFilterViewModel;
import pe.com.empresa.rk.view.model.RolViewModel;

@Repository
public class RolJdbcRepository {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
    DataSource dataSource;

    private NamedParameterJdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    
	public List<RolResultViewModel> searchRolResult(RolFilterViewModel filterViewModel) {
		WhereParams params = new WhereParams();
        String sql = searchRolResult(filterViewModel, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<>(RolResultViewModel.class));
	}
	
	public List<Authorization> obtenerAutorizaciones(AutorizacionFilterViewModel filterViewModel) {
		WhereParams params = new WhereParams();
        String sql = searchObtenerAutorizacion(filterViewModel, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<>(Authorization.class));
	}


	private String searchRolResult(RolFilterViewModel filterViewModel, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT r.IdRol AS idRol,");
		sql.append("  r.Nombre AS nombre, ");
		sql.append("  r.RolSistema AS rolSistema, ");
		sql.append("  r.Descripcion AS descripcion, ");		
		sql.append(" CASE ");   
        sql.append(" WHEN  (r.Estado='A') THEN 'Activo' ");
        sql.append(" WHEN  (r.Estado='I') THEN 'Inactivo' ");
        sql.append(" ELSE 'No Asociado' ");
		sql.append(" END AS estado ");
		
		sql.append("  FROM Rol r ");
		sql.append("  WHERE 1 = 1 ");
//		sql.append(params.filter("  AND r.Descripcion = :descripcion ", filterViewModel.getDescripcion()));
//		sql.append(params.filter("  AND r.Nombre = :nombre ", filterViewModel.getNombre()));
		sql.append(params.filter("  AND UPPER(r.Nombre) LIKE  UPPER ('%'+ :nombre +'%') ", filterViewModel.getNombre()));
		sql.append(params.filter("  AND r.Estado = :estado ", filterViewModel.getEstado()));//UPPER(C.Nombre) LIKE  UPPER ('%'+ :nombre +'%')
		sql.append(params.filter("  AND UPPER(r.Descripcion) LIKE  UPPER ('%'+ :descripcion +'%') ", filterViewModel.getEstado()));
		
		return sql.toString();
	}
	
	
	private String searchObtenerAutorizacion(AutorizacionFilterViewModel filterViewModel, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT A.Nombre as actionName, ");

		sql.append("  convert( P.Autorizado, SIGNED) AS authorized");
		sql.append(" FROM Modulo M  ");
		sql.append("  LEFT JOIN Accion A ON A.IdModulo = M.IdModulo ");
		sql.append("  LEFT JOIN Autorizacion P ON P.IdAccion = A.IdAccion ");
		sql.append("  LEFT JOIN Rol R ON R.IdRol = P.IdRol ");
		sql.append("  LEFT JOIN UsuarioRol U ON U.IdRol = R.IdRol ");
		
		sql.append("  WHERE 1 = 1 AND U.PorDefecto = 1 ");
		sql.append(params.filter("  AND M.Codigo = :codigoModulo", filterViewModel.getCodigoModulo()));
		sql.append(params.filter("  AND U.IdUsuario = :idUsuario", filterViewModel.getIdUsuario()));
//		sql.append("  GROUP BY M.IdModulo,A.Nombre ");
		
		return sql.toString();
	}

	public List<RolViewModel> getRolById(Long idRol) {
		WhereParams params = new WhereParams();
        String sql = obtenerRolById(idRol, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<RolViewModel>(RolViewModel.class));
	}

	private String obtenerRolById(Long idRol, WhereParams params) {
		StringBuffer sql=new StringBuffer();
		
		sql.append(" SELECT DISTINCT M.Nombre AS nombreModulo, ");
		sql.append(" R.Nombre AS nombre, ");
		sql.append(" R.Descripcion AS descripcion, ");
		sql.append(" M.IdModulo AS idModulo, ");
		sql.append(" R.IdRol AS idRol ");
		sql.append(" FROM MODULO M ");
		sql.append(" LEFT JOIN Accion A ON M.IdModulo = A.IdModulo ");
		sql.append(" LEFT JOIN Autorizacion P ON P.IdAccion = A.IdAccion ");
		sql.append(" LEFT JOIN Rol R ON R.IdRol = P.IdRol ");
		sql.append(" WHERE 1 = 1 ");
		sql.append(params.filter(" AND R.IdRol = :idRol ",idRol));
		sql.append(" ORDER BY M.Nombre ");
	     
		return sql.toString();
	}

	public List<AccionViewModel> findAccionByIdModulo(Long idModulo, Long idRol) {
		WhereParams params = new WhereParams();
        String sql = findAccionByIdModulo(idModulo,idRol, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<AccionViewModel>(AccionViewModel.class));
	}
	
	public List<AccionViewModel> findAllAccionByIdModulo(Long idModulo) {
		WhereParams params = new WhereParams();
        String sql = findAllAccionByIdModuloQuery(idModulo, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<AccionViewModel>(AccionViewModel.class));
	}

	private String findAccionByIdModulo(Long idModulo, Long idRol, WhereParams params) {
		StringBuffer sql=new StringBuffer();
		
		sql.append(" SELECT DISTINCT A.Nombre AS nombre, ");
		sql.append(" A.TipoAccion AS tipoAccion, ");
		sql.append(" R.IdRol AS idRol, ");
		sql.append(" A.IdAccion AS idAccion, ");
		sql.append(" P.IdAutorizacion AS idAutorizacion, ");
		sql.append(" P.RowVersion AS rowVersionAutorizacion, ");
		sql.append(" P.Actualizador AS actualizadorAutorizacion, ");
		sql.append(" P.FechaActualizacion AS fechaActualizacionAutorizacion, ");
		sql.append(" P.FechaCreacion AS fechaCreacionAutorizacion, ");
		sql.append(" P.Creador AS creadorAutorizacion, ");
		sql.append(" P.Autorizado AS autorizado ");
		sql.append(" FROM MODULO M ");
		sql.append(" LEFT JOIN Accion A ON M.IdModulo = A.IdModulo ");
		sql.append(" LEFT JOIN Autorizacion P ON P.IdAccion = A.IdAccion ");
		sql.append(" LEFT JOIN Rol R ON R.IdRol = P.IdRol ");
		sql.append(" WHERE 1 = 1 ");
		sql.append(params.filter(" AND R.IdRol = :idRol ",idRol));
		sql.append(params.filter(" AND M.IdModulo = :idModulo ",idModulo));
		sql.append(" ORDER BY A.Nombre ");
		return sql.toString();
	}
	
	private String findAllAccionByIdModuloQuery(Long idModulo, WhereParams params) {
		StringBuffer sql=new StringBuffer();
		
		sql.append(" SELECT DISTINCT A.Nombre AS nombre, ");
		sql.append(" A.TipoAccion AS tipoAccion, ");
		sql.append(" A.IdAccion AS idAccion ");
//		sql.append(" P.RowVersion AS rowVersionAutorizacion, ");
//		sql.append(" P.Actualizador AS actualizadorAutorizacion, ");
//		sql.append(" P.FechaActualizacion AS fechaActualizacionAutorizacion, ");
//		sql.append(" P.FechaCreacion AS fechaCreacionAutorizacion, ");
//		sql.append(" P.Creador AS creadorAutorizacion, ");
//		sql.append(" P.Autorizado AS autorizado ");
		sql.append(" FROM MODULO M ");
		sql.append(" LEFT JOIN Accion A ON M.IdModulo = A.IdModulo ");
		sql.append(" LEFT JOIN Autorizacion P ON P.IdAccion = A.IdAccion ");
		sql.append(" LEFT JOIN Rol R ON R.IdRol = P.IdRol ");
		sql.append(" WHERE 1 = 1 ");
		sql.append(params.filter(" AND M.IdModulo = :idModulo ",idModulo));
		sql.append(" GROUP BY M.IdModulo,A.Nombre,A.TipoAccion,A.IdAccion ");
		sql.append(" ORDER BY A.Nombre ");
		return sql.toString();
	}

	public RolViewModel findRolById(Long idRol) {
		WhereParams params = new WhereParams();
        String sql = findRolByIdQuery(idRol, params);

        return jdbcTemplate.queryForObject(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<RolViewModel>(RolViewModel.class));
	}

	private String findRolByIdQuery(Long idRol, WhereParams params) {
		StringBuffer sql=new StringBuffer();
		
		sql.append(" SELECT DISTINCT R.IdRol AS idRol, ");
		sql.append(" R.Nombre AS nombre, ");
		sql.append(" R.Descripcion AS descripcion, ");
		sql.append(" R.RolSistema AS rolSistema, ");
		sql.append(" R.Estado AS estado ");
		sql.append(" FROM Rol R ");
		sql.append(" WHERE 1 = 1 ");
		sql.append(params.filter(" AND R.IdRol = :idRol ",idRol));
	     
		return sql.toString();
	}

	public List<ModuloViewModel> findAllSubModulosByIdRol(Long idRol) {
		WhereParams params = new WhereParams();
        String sql = findAllSubModulosByIdRolQuery(idRol, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<ModuloViewModel>(ModuloViewModel.class));
	}
	public List<ModuloViewModel> findAllSubModulos() {
		WhereParams params = new WhereParams();
        String sql = findAllSubModulosQuery(params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<ModuloViewModel>(ModuloViewModel.class));
	}

	private String findAllSubModulosByIdRolQuery(Long idRol, WhereParams params) {

		StringBuffer sql=new StringBuffer();
		
		sql.append(" SELECT DISTINCT M.Nombre AS nombre, ");
		sql.append(" M.IdModulo AS idModulo ");
		sql.append(" FROM MODULO M ");
		sql.append(" LEFT JOIN Accion A ON M.IdModulo = A.IdModulo ");
		sql.append(" LEFT JOIN Autorizacion P ON P.IdAccion = A.IdAccion ");
		sql.append(" LEFT JOIN Rol R ON R.IdRol = P.IdRol ");
		sql.append(" WHERE 1 = 1 ");
		sql.append(params.filter(" AND R.IdRol = :idRol ",idRol));
		sql.append(" ORDER BY M.Nombre ");
	     
		return sql.toString();
	}
	
	private String findAllSubModulosQuery(WhereParams params) {

		StringBuffer sql=new StringBuffer();
		
		sql.append(" SELECT DISTINCT M.Nombre AS nombre, ");
		sql.append(" M.IdModulo AS idModulo ");
		sql.append(" FROM MODULO M ");
		sql.append(" LEFT JOIN Accion A ON M.IdModulo = A.IdModulo ");
		sql.append(" LEFT JOIN Autorizacion P ON P.IdAccion = A.IdAccion ");
		sql.append(" LEFT JOIN Rol R ON R.IdRol = P.IdRol ");
		sql.append(" WHERE 1 = 1 ");
		sql.append(" ORDER BY M.Nombre ");
	     
		return sql.toString();
	}
	
	  
    
	

}

