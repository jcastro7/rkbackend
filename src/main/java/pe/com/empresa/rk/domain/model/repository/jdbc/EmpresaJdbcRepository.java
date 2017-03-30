package pe.com.empresa.rk.domain.model.repository.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.empresa.rk.support.WhereParams;
import pe.com.empresa.rk.view.model.DepartamentoAreaViewModel;
import pe.com.empresa.rk.view.model.EmpresaResultViewModel;
import pe.com.empresa.rk.view.model.EmpresaViewModel;
import pe.com.empresa.rk.view.model.UnidadDeNegocioViewModel;
import pe.com.empresa.rk.view.model.EmpresaFilterViewModel;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

/**
 * Created by josediaz on 2/11/2016.
 */

@Repository
public class EmpresaJdbcRepository implements EmpresaRepository {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    DataSource dataSource;

    private NamedParameterJdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
	public EmpresaViewModel findEmpresaById(Long idEmpresa) {
    	WhereParams params = new WhereParams();
        String sql = findEmpresaByIdQuery(idEmpresa, params);

        return jdbcTemplate.queryForObject(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<EmpresaViewModel>(EmpresaViewModel.class));
	}

    @Override
	public List<UnidadDeNegocioViewModel> findAllUnidadDeNegocioByEmpresa(Long idEmpresa) {
    	WhereParams params = new WhereParams();
        String sql = findAllUnidadDeNegocioByEmpresaQuery(idEmpresa, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<UnidadDeNegocioViewModel>(UnidadDeNegocioViewModel.class));
	}

	@Override
	public List<DepartamentoAreaViewModel> findAllDepartamentoAreaByUnidadNegocio(Long idDepartamentoArea) {
		WhereParams params = new WhereParams();
        String sql = findAllDepartamentoAreaByUnidadNegocioQuery(idDepartamentoArea, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<DepartamentoAreaViewModel>(DepartamentoAreaViewModel.class));
	}
	
	@Override
	public List<EmpresaResultViewModel> finAllEmpresaByFilterSearch(EmpresaFilterViewModel empresaFilter) {
		WhereParams params = new WhereParams();
        String sql = finAllEmpresaByFilterSearchQuery(empresaFilter, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<EmpresaResultViewModel>(EmpresaResultViewModel.class));
	}
    
    private String findEmpresaByIdQuery(Long idEmpresa, WhereParams params) {
		
    	StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append(" EMP.IdEmpresa AS idEmpresa, ");
        sql.append(" EMP.Codigo AS codigo, ");
        sql.append(" EMP.RazonSocial AS razonSocial, ");
        sql.append(" EMP.RUC AS ruc, ");
        sql.append(" EST.Nombre AS nombreEstado, ");
        sql.append(" EMP.Estado AS estado, ");
        sql.append(" EMP.IdJefe AS idJefe, ");
        sql.append(" EMP.IdJefeReemplazo AS idJefeReemplazo, ");
        sql.append(" EMP.JefeNoDisponible AS jefeNoDisponible, ");
        sql.append(" JEFE.ApellidoPaterno+' '+JEFE.ApellidoMaterno+', '+JEFE.Nombre AS jefe, ");
        sql.append(" JEFEREEMP.ApellidoPaterno+' '+JEFEREEMP.ApellidoMaterno+', '+JEFEREEMP.Nombre AS jefeReemplazo, ");
        
        sql.append(" EMP.Creador AS creador, ");
        sql.append(" EMP.Actualizador AS actualizador, ");
        sql.append(" EMP.FechaCreacion AS fechaCreacion, ");
        sql.append(" EMP.FechaActualizacion AS fechaActualizacion,  ");
        sql.append(" EMP.RowVersion AS rowVersion  ");
        
        sql.append(" FROM Empresa EMP ");
        sql.append(" LEFT JOIN TablaGeneral EST ON EST.Codigo = EMP.Estado AND EST.Grupo = 'Estado' ");
        sql.append(" LEFT JOIN Empleado JEFE ON IdEmpleado = EMP.IdJefe ");
        sql.append(" LEFT JOIN Empleado JEFEREEMP ON JEFEREEMP.IdEmpleado = EMP.IdJefeReemplazo ");
                
        sql.append(" WHERE EMP.IdEmpresa = "+idEmpresa);
       
        return sql.toString();
	}
    
    private String findAllUnidadDeNegocioByEmpresaQuery(Long idEmpresa, WhereParams params) {
		
    	StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        
        sql.append(" UND.IdUnidadDeNegocio AS idUnidadDeNegocio, ");
        sql.append(" UND.IdEmpresa AS idEmpresa, ");
        sql.append(" UND.Nombre AS nombre, ");
        
        sql.append(" EST.Nombre AS nombreEstado, ");
        sql.append(" UND.Estado AS estado, ");
        sql.append(" UND.IdJefe AS idJefe, ");
        sql.append(" UND.IdJefeReemplazo AS idJefeReemplazo, ");
        sql.append(" UND.JefeNoDisponible AS jefeNoDisponible, ");
        sql.append(" JEFE.ApellidoPaterno+' '+JEFE.ApellidoMaterno+', '+JEFE.Nombre AS jefe, ");
        sql.append(" JEFEREEMP.ApellidoPaterno+' '+JEFEREEMP.ApellidoMaterno+', '+JEFEREEMP.Nombre AS jefeReemplazo, ");
        
        sql.append(" UND.Creador AS creador, ");
        sql.append(" UND.Actualizador AS actualizador, ");
        sql.append(" UND.FechaCreacion AS fechaCreacion, ");
        sql.append(" UND.FechaActualizacion AS fechaActualizacion,  ");
        sql.append(" UND.RowVersion AS rowVersion  ");
        
        sql.append(" FROM UnidadDeNegocio UND ");
        sql.append(" LEFT JOIN TablaGeneral EST ON EST.Codigo = UND.Estado AND EST.Grupo = 'Estado' ");
        sql.append(" LEFT JOIN Empleado JEFE ON JEFE.IdEmpleado = UND.IdJefe ");
        sql.append(" LEFT JOIN Empleado JEFEREEMP ON JEFEREEMP.IdEmpleado = UND.IdJefeReemplazo ");
                
        sql.append(" WHERE UND.IdEmpresa = "+idEmpresa);
       
        return sql.toString();
	}
    
    private String findAllDepartamentoAreaByUnidadNegocioQuery(Long idUnidadNegocio, WhereParams params) {
		
    	StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        
        sql.append(" DEPA.IdDepartamentoArea AS idDepartamentoArea, ");
        sql.append(" DEPA.IdUnidadDeNegocio AS idUnidadDeNegocio, ");
        sql.append(" DEPA.Nombre AS nombre, ");
        
        sql.append(" EST.Nombre AS nombreEstado, ");
        sql.append(" DEPA.Estado AS estado, ");
        sql.append(" DEPA.IdJefe AS idJefe, ");
        sql.append(" DEPA.IdJefeReemplazo AS idJefeReemplazo, ");
        sql.append(" DEPA.JefeNoDisponible AS jefeNoDisponible, ");
        sql.append(" JEFE.ApellidoPaterno+' '+JEFE.ApellidoMaterno+', '+JEFE.Nombre AS jefe, ");
        sql.append(" JEFEREEMP.ApellidoPaterno+' '+JEFEREEMP.ApellidoMaterno+', '+JEFEREEMP.Nombre AS jefeReemplazo, ");
        
        sql.append(" DEPA.Creador AS creador, ");
        sql.append(" DEPA.Actualizador AS actualizador, ");
        sql.append(" DEPA.FechaCreacion AS fechaCreacion, ");
        sql.append(" DEPA.FechaActualizacion AS fechaActualizacion,  ");
        sql.append(" DEPA.RowVersion AS rowVersion  ");
        
        sql.append(" FROM DepartamentoArea DEPA ");
        sql.append(" LEFT JOIN TablaGeneral EST ON EST.Codigo = DEPA.Estado AND EST.Grupo = 'Estado' ");
        sql.append(" LEFT JOIN Empleado JEFE ON JEFE.IdEmpleado = DEPA.IdJefe ");
        sql.append(" LEFT JOIN Empleado JEFEREEMP ON JEFEREEMP.IdEmpleado = DEPA.IdJefeReemplazo ");
                
        sql.append(" WHERE DEPA.IdUnidadDeNegocio = "+idUnidadNegocio);
       
        return sql.toString();
	}
    
    private String finAllEmpresaByFilterSearchQuery(EmpresaFilterViewModel empresaFilter, WhereParams params) {StringBuilder sql = new StringBuilder();
    sql.append(" SELECT ");
    sql.append(" EMP.IdEmpresa AS idEmpresa, ");
    sql.append(" EMP.Codigo AS codigo, ");
    sql.append(" EMP.RazonSocial AS razonSocial, ");
    sql.append(" EMP.RUC AS ruc, ");
    sql.append(" EST.Nombre AS estado, ");
    sql.append(" EMP.IdJefe AS idJefe, ");
    sql.append(" EMP.IdJefeReemplazo AS idJefeReemplazo, ");
    sql.append(" EMP.JefeNoDisponible AS jefeNoDisponible, ");
    sql.append(" JEFE.ApellidoPaterno+' '+JEFE.ApellidoMaterno+', '+JEFE.Nombre AS jefe, ");
    sql.append(" JEFEREEMP.ApellidoPaterno+' '+JEFEREEMP.ApellidoMaterno+', '+JEFEREEMP.Nombre AS jefeReemplazo ");
        
    sql.append(" FROM Empresa EMP ");
    sql.append(" LEFT JOIN TablaGeneral EST ON EST.Codigo = EMP.Estado AND EST.Grupo = 'Estado' ");
    sql.append(" LEFT JOIN Empleado JEFE ON IdEmpleado = EMP.IdJefe ");
    sql.append(" LEFT JOIN Empleado JEFEREEMP ON JEFEREEMP.IdEmpleado = EMP.IdJefeReemplazo ");
            
    sql.append(" WHERE 1=1 ");
    
    sql.append(params.filter(" AND EMP.IdJefe = :idJefe ", empresaFilter.getIdJefe()));
    sql.append(params.filter(" AND EMP.Estado = :estado ", empresaFilter.getEstado()));
    sql.append(params.filter(" AND UPPER(EMP.Codigo) LIKE UPPER('%' + :codigo + '%') ", empresaFilter.getCodigo()));
    sql.append(params.filter(" AND UPPER(EMP.RazonSocial) LIKE UPPER('%' + :razonSocial + '%') ", empresaFilter.getRazonSocial()));
    sql.append(params.filter(" AND UPPER(EMP.ruc) LIKE UPPER('%' + :ruc + '%') ", empresaFilter.getRuc()));
   
    return sql.toString();}
	
}