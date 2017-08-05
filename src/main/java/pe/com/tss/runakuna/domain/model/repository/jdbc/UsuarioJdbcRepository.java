package pe.com.tss.runakuna.domain.model.repository.jdbc;

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

import pe.com.tss.runakuna.support.WhereParams;
import pe.com.tss.runakuna.view.model.CargoResultViewModel;
import pe.com.tss.runakuna.view.model.CurrentUser;
import pe.com.tss.runakuna.view.model.HorarioViewModel;
import pe.com.tss.runakuna.view.model.PieChartDataResultViewModel;
import pe.com.tss.runakuna.view.model.QuickFilterViewModel;
import pe.com.tss.runakuna.view.model.RolResultViewModel;
import pe.com.tss.runakuna.view.model.UsuarioFilterViewModel;
import pe.com.tss.runakuna.view.model.UsuarioQuickFilterViewModel;
import pe.com.tss.runakuna.view.model.UsuarioResultViewModel;
import pe.com.tss.runakuna.view.model.UsuarioViewModel;
import pe.com.tss.runakuna.view.model.UsuarioViewModelAutoComplete;

@Repository
public class UsuarioJdbcRepository implements UsuarioRepository{

	
	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioJdbcRepository.class);

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    UsuarioJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private static final String SEARCH_USER =
            "select * from Usuario where IdUsuario = :idUsuario";
    
    private static final String SEARCH_USER_BY_MAIL =
            "select * from Usuario where Email = :email";
    
    private static final String CARGAR_COMBO_ROL =
    		"select * from Rol";

    
	@Transactional(readOnly = true)
    @Override
	public UsuarioViewModel buscarUsuarioPorcuentaUsuario(Long idUsuario) {
		LOGGER.info("Buscando usuarios para la autenticacion ", idUsuario);

        Map<String, Long> queryParams = new HashMap<>();
        queryParams.put("idUsuario", idUsuario);

        List<UsuarioViewModel> searchResults = jdbcTemplate.query(SEARCH_USER,
                queryParams,
                new BeanPropertyRowMapper<>(UsuarioViewModel.class)
        );

        LOGGER.info("Found usuario {}", searchResults);

        if (searchResults.size() > 0) {
            return searchResults.get(0);
        }

        return null;
	}
	
	@Override
	public UsuarioViewModel buscarUsuarioPorEmail(String email) {
		LOGGER.info("Buscando usuarios para la autenticacion ", email);

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("email", email);

        List<UsuarioViewModel> searchResults = jdbcTemplate.query(SEARCH_USER_BY_MAIL,
                queryParams,
                new BeanPropertyRowMapper<>(UsuarioViewModel.class)
        );

        LOGGER.info("Found usuario {}", searchResults);

        if (searchResults.size() > 0) {
            return searchResults.get(0);
        }

        return null;
	}

	@Override
	public List<UsuarioResultViewModel> obtenerUsuarios(UsuarioFilterViewModel filterViewModel) {
		WhereParams params = new WhereParams();
        String sql = generarObtenerUsuarios(filterViewModel, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<UsuarioResultViewModel>(UsuarioResultViewModel.class));

	}

	private String generarObtenerUsuarios(UsuarioFilterViewModel filterViewModel, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT ");
		sql.append(" u.IdUsuario AS idUsuario, ");
		sql.append(" CASE WHEN (u.IdEmpleado IS NOT NULL) THEN 'SI' ELSE 'NO' END AS esEmpleado, ");
		sql.append(" u.Nombre AS nombre, ");
		sql.append(" u.ApellidoPaterno AS apellidoPaterno, ");
		sql.append(" u.ApellidoMaterno AS apellidoMaterno, ");
		sql.append(" u.CuentaUsuario AS cuentaUsuario, ");
		sql.append(" CASE WHEN (u.Estado='A')  THEN 'Activo' ELSE CASE WHEN (u.Estado='I') THEN 'Inactivo' ELSE CASE WHEN (u.Estado='B') THEN 'Bloqueado' END END END AS estado, ");
		sql.append(" u.Email AS email ");
		
		
        sql.append(" FROM Usuario u ");
        
        
        sql.append(" WHERE 1=1 ");
        sql.append(params.filter(" AND u.CuentaUsuario = :cuentaUsuario ",filterViewModel.getCuentaUsuario()));
        sql.append(params.filter(" AND u.Email = :email ",filterViewModel.getEmail()));
        sql.append(params.filter(" AND UPPER(u.Nombre) LIKE  UPPER ('%'+ :nombre +'%') ",filterViewModel.getNombre()));
        sql.append(params.filter(" AND UPPER(u.ApellidoPaterno) LIKE  UPPER ('%'+ :apellidoPaterno +'%') ",filterViewModel.getApellidoPaterno()));
        sql.append(params.filter(" AND UPPER(u.ApellidoMaterno) LIKE  UPPER ('%'+ :apellidoMaterno +'%') ",filterViewModel.getApellidoMaterno()));
        sql.append(params.filter(" AND u.IdEmpleado = :idEmpleado ",filterViewModel.getIdEmpleado()));
        sql.append(params.filter(" AND u.Estado = :estado ",filterViewModel.getEstado()));
        
        return sql.toString();
	}

	@Override
	public List<RolResultViewModel> cargarComboRol() {
		WhereParams params = new WhereParams();
        String sql = cargarComboRolQuery(params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<>(RolResultViewModel.class));
	}

	private String cargarComboRolQuery(WhereParams params) {
		StringBuilder sql = new StringBuilder();
        sql.append(" select DISTINCT r.IdRol as idRol, ");
        sql.append(" r.Nombre as nombre, ");
        sql.append(" r.Descripcion as descripcion, ");
        sql.append(" r.Estado as estado, ");
        sql.append(" r.RolSistema as rolSistema, ");
        sql.append(" r.RowVersion as rowVersion, ");
        sql.append(" r.Creador as creador, ");
        sql.append(" r.FechaCreacion as fechaCreacion, ");
        sql.append(" r.Actualizador as actualizador, ");
        sql.append(" r.FechaActualizacion as fechaActualizacion, ");
        sql.append(" ur.PorDefecto as porDefecto ");        
        sql.append(" From Rol r ") ;
        sql.append(" LEFT JOIN UsuarioRol ur ON ur.IdRol=r.IdRol ");
        sql.append(" Where 1=1 ");
        return sql.toString();
	}

	@Override
	public List<UsuarioResultViewModel> busquedaRapidaUsuarios(QuickFilterViewModel quickFilter) {
		WhereParams params = new WhereParams();
        String sql = generarBusquedaRapidaUsuarios((UsuarioQuickFilterViewModel)quickFilter, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<UsuarioResultViewModel>(UsuarioResultViewModel.class));
	}
	
	private String generarBusquedaRapidaUsuarios(UsuarioQuickFilterViewModel filterViewModel, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT ");
		sql.append(" u.IdUsuario AS idUsuario, ");
		sql.append(" CASE WHEN (u.IdEmpleado IS NOT NULL) THEN 'SI' ELSE 'NO' END AS esEmpleado, ");
		sql.append(" u.Nombre AS nombre, ");
		sql.append(" u.ApellidoPaterno AS apellidoPaterno, ");
		sql.append(" u.ApellidoMaterno AS apellidoMaterno, ");
		sql.append(" u.CuentaUsuario AS cuentaUsuario, ");
		sql.append(" CASE WHEN (u.Estado='A')  THEN 'Activo' ELSE CASE WHEN (u.Estado='I') THEN 'Inactivo' ELSE CASE WHEN (u.Estado='B') THEN 'Bloqueado' END END END AS estado, ");
		sql.append(" u.Email AS email ");
		
		sql.append(" FROM Usuario u ");

        sql.append(" WHERE 1=1 ");

        sql.append(params.filter(" AND UPPER(u.CuentaUsuario) LIKE UPPER(CONCAT('%', :cuentaUsuario, '%'))",filterViewModel.getValue()));
        sql.append(params.filter(" OR UPPER(u.Nombre) LIKE UPPER(CONCAT('%', :nombre, '%'))",filterViewModel.getValue()));
        sql.append(params.filter(" OR UPPER(u.ApellidoPaterno) LIKE UPPER(CONCAT('%', :apellidoPaterno, '%'))",filterViewModel.getValue()));
        sql.append(params.filter(" OR UPPER(u.ApellidoMaterno) LIKE UPPER(CONCAT('%', :apellidoMaterno, '%'))",filterViewModel.getValue()));
        
        
        return sql.toString();
	}

	@Override
	public UsuarioViewModelAutoComplete getInfoAutoCompleteEmpleado(Long idEmpleado) {
		WhereParams params = new WhereParams();
        String sql = getInfoAutoCompleteEmpleadoQuery(idEmpleado, params);

        return jdbcTemplate.queryForObject(sql, params.getParams(), 
        		new BeanPropertyRowMapper<UsuarioViewModelAutoComplete>(UsuarioViewModelAutoComplete.class));
	}

	private String getInfoAutoCompleteEmpleadoQuery(Long idEmpleado, WhereParams params) {
		StringBuilder sql = new StringBuilder();
        sql.append(" select u.Nombre AS nombre, ");
        sql.append(" u.ApellidoPaterno AS apellidoPaterno, ");
        sql.append(" u.ApellidoMaterno AS apellidoMaterno, ");
        sql.append(" u.EmailInterno AS email ");
        sql.append(" FROM Empleado u ");
        sql.append(" WHERE 1=1 ");
        sql.append(params.filter(" AND u.IdEmpleado = :idEmpleado ",idEmpleado));
        
        return sql.toString();
	}

}
