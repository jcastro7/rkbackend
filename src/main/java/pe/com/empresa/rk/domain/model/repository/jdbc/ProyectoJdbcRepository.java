package pe.com.empresa.rk.domain.model.repository.jdbc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.empresa.rk.support.WhereParams;
import pe.com.empresa.rk.view.model.ProyectoResultViewModel;
import pe.com.empresa.rk.view.model.QuickFilterViewModel;
import pe.com.empresa.rk.view.model.ProyectoFilterViewModel;
import pe.com.empresa.rk.view.model.ProyectoQuickFilterViewModel;

@Repository
public class ProyectoJdbcRepository implements ProyectoRepository{

	private static final Logger LOGGER = LoggerFactory.getLogger(ProyectoJdbcRepository.class);
			
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	private static final String SEARCH_ALL_MONEDAS = "select * from Proyecto";
	
	@Autowired
	ProyectoJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<ProyectoResultViewModel> obtenerProyectos(ProyectoFilterViewModel dto) {
		WhereParams params = new WhereParams();
        String sql = generarObtenerProyectos(dto, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<ProyectoResultViewModel>(ProyectoResultViewModel.class));

	}

	private String generarObtenerProyectos(ProyectoFilterViewModel dto, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT ");
		sql.append(" P.IdProyecto AS idProyecto, ");
		sql.append(" P.Nombre AS nombre, ");
		sql.append(" P.Codigo AS codigo, ");
		sql.append(" P.Descripcion AS descripcion, ");
		sql.append(" P.FechaInicio AS fechaInicio, ");
		sql.append(" P.FechaFin AS fechaFin, ");
		sql.append(" DEP.Nombre AS nombreDepartamentoArea, ");
		sql.append(" EMP.Nombre+' '+EMP.ApellidoPaterno+' '+EMP.ApellidoMaterno AS nombreJefeProyecto, ");
		sql.append(" UN.Nombre AS nombreUnidadDeNegocio, ");
		sql.append(" CASE WHEN (P.Estado='A') THEN 'Activo' WHEN (P.Estado='C') THEN 'Cerrado' ELSE 'Propuesto' END AS estado ,");
		
		sql.append(" empleados= (select count (distinct h.IdEmpleado) as empleados from HistorialLaboral h where h.IdProyecto=P.IdProyecto ) ");
        //sql.append(" inner join DepartamentoArea d on (d.IdDepartamentoArea=pr.IdDepartamentoArea) ");
        //sql.append(" inner join UnidadDeNegocio u on (d.IdUnidadDeNegocio=u.IdUnidadDeNegocio) ");
        //sql.append(" inner join HistorialLaboral h on ");
        //sql.append(" (h.IdUnidadDeNegocio=u.IdUnidadDeNegocio and h.IdDepartamentoArea=d.IdDepartamentoArea "); 
        //sql.append(" and h.IdProyecto=pr.IdProyecto and ( ");
        //sql.append(" (GETDATE()>=h.FechaInicio and GETDATE()<=h.FechaFin) or (GETDATE()>=h.FechaInicio and h.FechaFin is null)) ");
        //sql.append(" ))");
        
        
        sql.append(" FROM Proyecto P ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = P.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = DEP.IdUnidadDeNegocio ");
        sql.append(" LEFT JOIN Empleado EMP ON P.IdJefe = EMP.IdEmpleado ");
        
        sql.append(" WHERE 1=1 ");
        sql.append(params.filter(" AND P.IdDepartamentoArea = :idDepartamentoArea ",dto.getIdDepartamentoArea()));
        sql.append(params.filter(" AND P.Estado = :estado ",dto.getEstado()));
        sql.append(params.filter(" AND UN.IdUnidadDeNegocio = :idUnidadDeNegocio ",dto.getIdUnidadDeNegocio()));
        sql.append(params.filter(" AND P.IdJefe = :idJefeProyecto ",dto.getIdJefeProyecto()));
        sql.append(params.filter(" AND UPPER(P.Nombre) LIKE  UPPER ('%'+ :nombre +'%') ",dto.getNombre()));
        sql.append(params.filter(" AND P.FechaInicio >= :fechaInicioDesde ",dto.getFechaInicioDesde()));
        sql.append(params.filter(" AND P.FechaInicio <= :fechaInicioHasta ",dto.getFechaInicioHasta()));
        sql.append(params.filter(" AND (P.FechaFin IS NOT NULL AND P.FechaFin >= :fechaFinDesde) ",dto.getFechaFinDesde()));
        sql.append(params.filter(" AND (P.FechaFin IS NOT NULL AND P.FechaFin <= :fechaFinHasta) ",dto.getFechaFinHasta()));
        
        
		return sql.toString();
	}
	
	private String generarBusquedaRapidaProyectos(ProyectoQuickFilterViewModel dto, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT ");
		sql.append(" P.IdProyecto AS idProyecto, ");
		sql.append(" P.Nombre AS nombre, ");
		sql.append(" P.Codigo AS codigo, ");
		sql.append(" P.Descripcion AS descripcion, ");
		sql.append(" P.FechaInicio AS fechaInicio, ");
		sql.append(" P.FechaFin AS fechaFin, ");
		sql.append(" DEP.Nombre AS nombreDepartamentoArea, ");
		sql.append(" EMP.Nombre+' '+EMP.ApellidoPaterno+' '+EMP.ApellidoMaterno AS nombreJefeProyecto, ");
		sql.append(" UN.Nombre AS nombreUnidadDeNegocio, ");
		sql.append(" CASE WHEN (P.Estado='A') THEN 'Activo' WHEN (P.Estado='C') THEN 'Cerrado' ELSE 'Propuesto' END AS estado ,");
		sql.append(" empleados= (select count (distinct h.IdEmpleado) as empleados from Proyecto pr ");
        sql.append(" inner join DepartamentoArea d on (d.IdDepartamentoArea=pr.IdDepartamentoArea) ");
        sql.append(" inner join UnidadDeNegocio u on (d.IdUnidadDeNegocio=u.IdUnidadDeNegocio) ");
        sql.append(" inner join HistorialLaboral h on ");
        sql.append(" (h.IdUnidadDeNegocio=u.IdUnidadDeNegocio and h.IdDepartamentoArea=d.IdDepartamentoArea "); 
        sql.append(" and h.IdProyecto=pr.IdProyecto and ( ");
        sql.append(" (GETDATE()>=h.FechaInicio and GETDATE()<=h.FechaFin) or (GETDATE()>=h.FechaInicio and h.FechaFin is null)) ");
        sql.append(" ))");
        sql.append(" FROM Proyecto P ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = P.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = DEP.IdUnidadDeNegocio ");
        sql.append(" LEFT JOIN Empleado EMP ON P.IdJefe = EMP.IdEmpleado ");
        
        sql.append(" WHERE 1=1 ");
        sql.append(params.filter(" AND UPPER(DEP.Nombre) LIKE UPPER('%' + :value + '%') ", dto.getValue()));
        sql.append(params.filter(" OR UPPER(UN.Nombre) LIKE UPPER('%' + :value + '%') ", dto.getValue()));
        
        
        
        
        
		return sql.toString();
	}

	@Override
	public List<ProyectoResultViewModel> busquedaRapidaProyectos(QuickFilterViewModel dto) {
		WhereParams params = new WhereParams();
        String sql = generarBusquedaRapidaProyectos((ProyectoQuickFilterViewModel) dto, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<ProyectoResultViewModel>(ProyectoResultViewModel.class));

	}
	
	

}
