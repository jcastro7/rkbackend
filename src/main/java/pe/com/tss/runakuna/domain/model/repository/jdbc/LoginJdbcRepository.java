package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.service.LoginService;
import pe.com.tss.runakuna.support.WhereParams;
import pe.com.tss.runakuna.view.model.AssignedRole;
import pe.com.tss.runakuna.view.model.CurrentUser;

@Repository
public class LoginJdbcRepository implements LoginRepository{

	@Autowired
    DataSource dataSource;
	
	private  NamedParameterJdbcTemplate jdbcTemplate;
	private  JdbcTemplate jdbcTemplateSp;

    @Autowired
    LoginJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
    	this.jdbcTemplate = jdbcTemplate;
    }
    
    @PostConstruct
    public void init() {
    	this.jdbcTemplateSp = new JdbcTemplate(dataSource);
    	this.jdbcTemplateSp.setResultsMapCaseInsensitive(true);
    }
    
    
    private static final String SEARCH_ROLES_AVAILABLE_PER_USER = "select \n" +
            "  r.Nombre AS \"rolName\",\n" +
            "  u.IdUsuario AS \"idUsuario\",\n" +
            "  u.IdEmpleado AS \"idEmpleado\" \n" +
            " from Usuario u \n" +
            " inner join UsuarioRol ur on u.IdUsuario = ur.IdUsuario\n" +
            " where 1=1\n" +
            " and u.cuentaUsuario = :cuentaUsuario";
    
	@Override
	public CurrentUser authenticate(String cuentaUsuario) {

		WhereParams params = new WhereParams();
        String sql = encontrarCurrentUser(cuentaUsuario, params);

        return jdbcTemplate.queryForObject(sql, params.getParams(), 
        		new BeanPropertyRowMapper<CurrentUser>(CurrentUser.class));
	}

	private String encontrarCurrentUser(String cuentaUsuario, WhereParams params) {
		StringBuilder sql = new StringBuilder();
        sql.append(" select u.IdEmpleado AS idEmpleado, ");
        sql.append(" u.IdUsuario AS idUsuario, ");
        sql.append(" u.CuentaUsuario AS cuentaUsuario, ");
        sql.append(" u.ApellidoPaterno+' '+u.ApellidoMaterno+', '+u.Nombre AS nombreCompleto, ");
        sql.append(" u.Email AS email, ");
        sql.append(" u.IdEmpresa AS idEmpresa");
        sql.append(" FROM Usuario u ");
        sql.append(" WHERE 1=1 ");
        sql.append(params.filter(" AND u.cuentaUsuario = :cuentaUsuario ",cuentaUsuario));
        
        return sql.toString();
	}

	@Override
	public List<AssignedRole> getAssignedRolesByUser(Long idUsuario) {
		WhereParams params = new WhereParams();
        String sql = generarGetAssignedRolesByUser(idUsuario, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<AssignedRole>(AssignedRole.class));
	}

	private String generarGetAssignedRolesByUser(Long idUsuario, WhereParams params) {
		StringBuilder sql = new StringBuilder();
        
        sql.append(" select r.Nombre as roleName, r.Descripcion as roleDescription, case when (x.IdRol is NULL) then 0 else 1 end as assigned, ");
        sql.append(" case when isnull(x.PorDefecto,0) = 0 THEN 0 else x.PorDefecto end as roleDefault ");
        sql.append(" from Rol r ");
        sql.append(" left join  ");
        sql.append(" (select IdRol,PorDefecto from UsuarioRol ur where ");
        sql.append(params.filter(" ur.IdUsuario= :idUsuario) x on r.IdRol=x.IdRol ",idUsuario));
        
        sql.append(" WHERE x.IdRol IS NOT null");

        return sql.toString();
	}

	@Override
	public Integer validateModuleActionName(Long idUsuario, String moduleCode, String actionName) {
		WhereParams params = new WhereParams();
        String sql = getValidateModuleActionName(idUsuario, moduleCode, actionName, params);

        return jdbcTemplate.queryForObject(sql, params.getParams(), 
        		Integer.class);
	}

	private String getValidateModuleActionName(Long idUsuario, String moduleCode, String actionName,
			WhereParams params) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select max(cast(au.Autorizado as INT)) as autorizado from Autorizacion au ");
		sql.append(" inner join Accion a on a.IdAccion=au.IdAccion ");
		sql.append(" inner join Modulo m on a.IdModulo=m.IdModulo ");
		sql.append(" where IdRol in ( ");
		sql.append(params.filter(" select IdRol from UsuarioRol where IdUsuario = :idUsuario ",idUsuario));
		sql.append(params.filter(" ) and m.Codigo= :moduleCode ",moduleCode));
		sql.append(params.filter(" and a.Nombre= :actionName ",actionName));
		return sql.toString();
	}
	
	@Override
	public void updateDefaultRole(String account, String roleCode) {

	       SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplateSp).withProcedureName("spuUsuarioRolUpdateDefault");
	       
	       SqlParameterSource inn = new MapSqlParameterSource().addValue("account", account,Types.VARCHAR).addValue("roleName", roleCode,Types.VARCHAR);
	       
	       jdbcCall.execute(inn);
	   
	}


}
