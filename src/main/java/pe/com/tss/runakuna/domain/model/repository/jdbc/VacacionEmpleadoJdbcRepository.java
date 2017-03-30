package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.support.WhereParams;
import pe.com.tss.runakuna.view.model.EmpleadoViewModel;
import pe.com.tss.runakuna.view.model.QuickFilterViewModel;
import pe.com.tss.runakuna.view.model.VacacionEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.VacacionFilterViewModel;
import pe.com.tss.runakuna.view.model.VacacionQuickFilterViewModel;
import pe.com.tss.runakuna.view.model.VacacionResultViewModel;

@Repository
public class VacacionEmpleadoJdbcRepository implements VacacionEmpleadoRepository{

	private static final Logger LOGGER = LoggerFactory.getLogger(VacacionEmpleadoJdbcRepository.class);

	@Autowired
	DataSource dataSource;
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	

	@PostConstruct
	public void init() {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Override
	public VacacionEmpleadoViewModel obtenerDiasDisponibles(EmpleadoViewModel empleadoDto) {
		WhereParams params = new WhereParams();
		String sql = obtenerDiasDisponibles(empleadoDto, params);
		
		VacacionEmpleadoViewModel result=new VacacionEmpleadoViewModel();
		
		List<VacacionEmpleadoViewModel> listVacacionEmpleado=jdbcTemplate.query(sql, params.getParams(),
				new BeanPropertyRowMapper<VacacionEmpleadoViewModel>(VacacionEmpleadoViewModel.class));
		
		if(listVacacionEmpleado!=null && listVacacionEmpleado.size()>0) {
			result=listVacacionEmpleado.get(0);
		}
		return result;
	}

	private String obtenerDiasDisponibles(EmpleadoViewModel empleadoDto, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT ");
		   
		sql.append("  pe.IdPeriodoEmpleado AS idPeriodoEmpleado, ");
		sql.append("  pe.DiasVacacionesDisponibles AS diasVacacionesDisponibles ");
		sql.append("  FROM ");
		sql.append("  PeriodoEmpleado pe");
		//sql.append("  WHERE (pe.FechaInicio<= getdate() AND pe.FechaFin>= getdate()) ");
		sql.append(" WHERE ");
		sql.append("   pe.IdEmpleado="+empleadoDto.getIdEmpleado()+" order by pe.FechaInicio desc");

				
		return sql.toString();
	}

	@Override
	public VacacionEmpleadoViewModel obtenerPeriodo(EmpleadoViewModel empleado) {
		WhereParams params = new WhereParams();
		String sql = obtenerPeriodo(empleado, params);
		
		VacacionEmpleadoViewModel result=new VacacionEmpleadoViewModel();
		
		List<VacacionEmpleadoViewModel> listHistoriaLaboral=jdbcTemplate.query(sql, params.getParams(),
				new BeanPropertyRowMapper<VacacionEmpleadoViewModel>(VacacionEmpleadoViewModel.class));
		
		if(listHistoriaLaboral!=null && listHistoriaLaboral.size()>0) {
			result=listHistoriaLaboral.get(0);
		}
		
		return result;
	}

	private String obtenerPeriodo(EmpleadoViewModel empleado, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT ");
		sql.append("  pe.IdPeriodoEmpleado AS idPeriodoEmpleado, ");
		sql.append("  pe.Periodo AS periodo ");
		sql.append("  FROM ");
		sql.append("  PeriodoEmpleado pe");
		//sql.append("  WHERE (pe.FechaInicio<= getdate() AND pe.FechaFin>= getdate()) ");
		sql.append(" WHERE ");
		sql.append(" DiasVacacionesDisponibles>0 ");
		sql.append("  AND pe.IdEmpleado="+empleado.getIdEmpleado() +" order by pe.FechaInicio asc");

				
		return sql.toString();
	}

	@Override
	public Long obtenerCantidadVacaciones(Long idPeriodoEmpleado) {
		WhereParams params = new WhereParams();
		String sql = obtenerCantidadVacaciones(idPeriodoEmpleado, params);
		return calculateTotalRows(sql, params);
	}
	
	private long calculateTotalRows(String queryBase, WhereParams params) {
        String query = "SELECT COUNT(1) FROM (" + queryBase + ") X";
        return jdbcTemplate.queryForObject(query, params.getParams(), Long.class);
    }

	private String obtenerCantidadVacaciones(Long idPeriodoEmpleado, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT IdVacacion AS idVacacion,");
		sql.append("  IdPeriodoEmpleado AS idPeriodoEmpleado ");
		sql.append("  FROM Vacacion");
		sql.append("  WHERE 1 = 1 ");
		sql.append("  AND IdPeriodoEmpleado="+idPeriodoEmpleado);
		
		return sql.toString();
	}

	@Override
	public VacacionEmpleadoViewModel obtenerPrimeraVacacion(Long idPeriodoEmpleado) {
		WhereParams params = new WhereParams();
		String sql = obtenerPrimeraVacacion(idPeriodoEmpleado, params);
		
		return jdbcTemplate.queryForObject(sql, params.getParams(),
				new BeanPropertyRowMapper<VacacionEmpleadoViewModel>(VacacionEmpleadoViewModel.class));
	}

	private String obtenerPrimeraVacacion(Long idPeriodoEmpleado, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT IdVacacion AS idVacacion,");
		sql.append("  IdPeriodoEmpleado AS idPeriodoEmpleado ");
		sql.append("  FROM (select IdVacacion,IdPeriodoEmpleado, row_number() over(partition by IdPeriodoEmpleado order by FechaInicio asc) as rn from Vacacion) as T");
		sql.append("  WHERE rn = 1 ");
		sql.append("  AND IdPeriodoEmpleado="+idPeriodoEmpleado);
				
		
		return sql.toString();
	}

	@Override
	public VacacionEmpleadoViewModel obtenerUltimaVacacion(Long idPeriodoEmpleado) {
		WhereParams params = new WhereParams();
		String sql = obtenerUltimaVacacion(idPeriodoEmpleado, params);
		
		return jdbcTemplate.queryForObject(sql, params.getParams(),
				new BeanPropertyRowMapper<VacacionEmpleadoViewModel>(VacacionEmpleadoViewModel.class));
	}

	private String obtenerUltimaVacacion(Long idPeriodoEmpleado, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT IdVacacion AS idVacacion,");
		sql.append("  IdPeriodoEmpleado AS idPeriodoEmpleado ");
		sql.append("  FROM (select IdVacacion,IdPeriodoEmpleado, row_number() over(partition by IdPeriodoEmpleado order by FechaInicio desc) as rn from Vacacion) as T");
		sql.append("  WHERE rn = 1 ");
		sql.append("  AND IdPeriodoEmpleado="+idPeriodoEmpleado);
				
		
		return sql.toString();
	}

	@Override
	public List<VacacionEmpleadoViewModel> allListVacacion(Long idPeriodoEmpleado) {
		WhereParams params = new WhereParams();
		String sql = allListVacacion(idPeriodoEmpleado, params);
		
		return jdbcTemplate.query(sql, params.getParams(),
				new BeanPropertyRowMapper<VacacionEmpleadoViewModel>(VacacionEmpleadoViewModel.class));
	}

	private String allListVacacion(Long idPeriodoEmpleado, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from Vacacion  ");
		sql.append("  WHERE 1 = 1 AND Estado != 'R' ");
		sql.append("  AND IdPeriodoEmpleado="+idPeriodoEmpleado);
		return sql.toString();
	}

	@Override
	public List<VacacionResultViewModel> busquedaVacacionesEmpleado(
			VacacionFilterViewModel filterViewModel) {
		WhereParams params = new WhereParams();
        String sql = generarBusquedaVacacionesEmpleado(filterViewModel, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<>(VacacionResultViewModel.class));
        
	}

	private String generarBusquedaVacacionesEmpleado(VacacionFilterViewModel filterViewModel,
			WhereParams params) {
		StringBuilder sql = new StringBuilder();
        sql.append(" SELECT distinct va.IdVacacion AS idVacacion, ");
        sql.append(" EMPLEADO.IdEmpleado AS idEmpleado, ");
        sql.append(" va.IdPeriodoEmpleado AS idPeriodoEmpleado, ");
        sql.append(" CONCAT(EMPLEADO.ApellidoPaterno, ' ', EMPLEADO.ApellidoMaterno, ', ', EMPLEADO.Nombre) as nombreEmpleado, ");
        sql.append(" va.FechaInicio as fechaInicio, ");
        sql.append(" va.FechaFin as fechaFin, ");
        sql.append(" va.DiasCalendarios as diasCalendarios, ") ;
        sql.append(" va.DiasHabiles as diasHabiles, ") ;
        sql.append(" CONCAT(ATENDIDO.ApellidoPaterno, ' ', ATENDIDO.ApellidoMaterno, ', ', ATENDIDO.Nombre) as nombreJefeInmediato, ");
        sql.append(" ESTADO.Nombre AS estado, ");
        sql.append(" va.IdAtendidoPor as idAtendidoPor ");
        sql.append(" from Vacacion va ") ;
        sql.append(" LEFT JOIN TablaGeneral ESTADO ON va.Estado=ESTADO.Codigo and ESTADO.GRUPO='Vacaciones.Estado'");
        sql.append(" LEFT JOIN PeriodoEmpleado PERIODO_EMPLEADO ON va.IdPeriodoEmpleado = PERIODO_EMPLEADO.IdPeriodoEmpleado ");
        sql.append(" LEFT JOIN Empleado EMPLEADO ON PERIODO_EMPLEADO.IdEmpleado = EMPLEADO.IdEmpleado ");
        
        sql.append(" LEFT JOIN Empleado ATENDIDO ON va.IdAtendidoPor = ATENDIDO.IdEmpleado ");
        
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMPLEADO.IdEmpleado AND ((HISTORIAL.FechaInicio < getdate() AND HISTORIAL.FechaFin > getDate()) AND (HISTORIAL.FechaInicio < getdate() AND HISTORIAL.FechaFin IS NULL)) ");
        
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        
        sql.append("  LEFT JOIN Empleado ep on PROY.IdJefe = ep.IdEmpleado ");
		sql.append("  LEFT JOIN Empleado eda on DEP.IdJefe = eda.IdEmpleado ");
		sql.append("  LEFT JOIN Empleado eun on UN.IdJefe = eun.IdEmpleado ");
                
        sql.append(" where 1=1 ");
        sql.append(params.filter(" AND va.Estado = :estado ", filterViewModel.getEstado()));
        sql.append(params.filterDateDesde_US(" AND va.FechaInicio  " , filterViewModel.getFechaInicio()));
        sql.append(params.filterDateHasta_US(" AND va.FechaFin  ", filterViewModel.getFechaFin()));
        sql.append(params.filter(" AND UN.IdUnidadDeNegocio = :unidadNegocio ", filterViewModel.getUnidadNegocio()));
        sql.append(params.filter(" AND DEP.IdDepartamentoArea = :departamento ", filterViewModel.getDepartamento()));
        sql.append(params.filter(" AND PROY.IdProyecto = :proyecto ", filterViewModel.getProyecto()));
        sql.append(params.filter(" AND va.IdVacacion = :idVacacion ", filterViewModel.getIdVacacion()));
        sql.append(params.filter(" AND EMPLEADO.IdEmpleado = :idEmpleado ", filterViewModel.getIdEmpleado()));
        
        sql.append(params.filter(" AND ( "+
        		" (HISTORIAL.IdProyecto IS NOT NULL AND PROY.IdJefe = :idJefeInmediato)  OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NOT NULL AND DEP.IdJefe  = :idJefeInmediato)"+ 
        		" OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NULL AND HISTORIAL.IdUnidadDeNegocio IS NOT NULL AND UN.IdJefe = :idJefeInmediato) ) ", filterViewModel.getIdJefeInmediato()));
        
        sql.append(params.filter(" AND [dbo].[GetJefeInmediato] (EMPLEADO.IdEmpleado,HISTORIAL.IdHistorialLaboral) = :idJefe",filterViewModel.getIdJefe()));
        
        return sql.toString();
	}

	@Override
	public List<VacacionResultViewModel> busquedaRapidaVacacionesEmpleado(QuickFilterViewModel quickFilter) {
		WhereParams params = new WhereParams();
        String sql = generarBusquedaRapidaVacacionesEmpleado((VacacionQuickFilterViewModel)quickFilter, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<>(VacacionResultViewModel.class));
	}

	private String generarBusquedaRapidaVacacionesEmpleado(VacacionQuickFilterViewModel quickFilter,
			WhereParams params) {
		StringBuilder sql = new StringBuilder();
        sql.append(" SELECT distinct va.IdVacacion AS idVacacion, ");
        sql.append(" EMPLEADO.IdEmpleado AS idEmpleado, ");
        sql.append(" va.IdPeriodoEmpleado AS idPeriodoEmpleado, ");
        sql.append(" CONCAT(EMPLEADO.ApellidoPaterno, ' ', EMPLEADO.ApellidoMaterno, ', ', EMPLEADO.Nombre) as nombreEmpleado, ");
        sql.append(" va.FechaInicio as fechaInicio, ");
        sql.append(" va.FechaFin as fechaFin, ");
        sql.append(" va.DiasCalendarios as diasCalendarios, ") ;
        sql.append(" va.DiasHabiles as diasHabiles, ") ;
        sql.append(" CONCAT(ATENDIDO.ApellidoPaterno, ' ', ATENDIDO.ApellidoMaterno, ', ', ATENDIDO.Nombre) as nombreJefeInmediato, ");
        sql.append(" ESTADO.Nombre AS estado ");
        sql.append(" from Vacacion va ") ;
        sql.append(" LEFT JOIN TablaGeneral ESTADO ON va.Estado=ESTADO.Codigo and ESTADO.GRUPO='Vacaciones.Estado'");
        sql.append(" LEFT JOIN PeriodoEmpleado PERIODO_EMPLEADO ON va.IdPeriodoEmpleado = PERIODO_EMPLEADO.IdPeriodoEmpleado ");
        sql.append(" LEFT JOIN Empleado EMPLEADO ON PERIODO_EMPLEADO.IdEmpleado = EMPLEADO.IdEmpleado ");
        
        sql.append(" LEFT JOIN Empleado ATENDIDO ON va.IdAtendidoPor = ATENDIDO.IdEmpleado ");
        
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMPLEADO.IdEmpleado AND ((HISTORIAL.FechaInicio < getdate() AND HISTORIAL.FechaFin > getDate()) OR (HISTORIAL.FechaInicio < getdate() AND HISTORIAL.FechaFin IS NULL)) ");
        
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        
        sql.append("  LEFT JOIN Empleado ep on PROY.IdJefe = ep.IdEmpleado ");
		sql.append("  LEFT JOIN Empleado eda on DEP.IdJefe = eda.IdEmpleado ");
		sql.append("  LEFT JOIN Empleado eun on UN.IdJefe = eun.IdEmpleado ");
                
        sql.append(" where 1=1 ");
        sql.append(params.filterDateDesde_US(" AND va.FechaInicio " , quickFilter.getFechaInicio()));
        sql.append(params.filterDateHasta_US(" AND va.FechaFin ", quickFilter.getFechaFin()));
        sql.append(params.filter(" AND UPPER(EMPLEADO.Nombre) LIKE UPPER('%' + :value + '%') ", quickFilter.getValue()));
        sql.append(params.filter(" OR UPPER(EMPLEADO.ApellidoPaterno) LIKE UPPER('%' + :value + '%') ", quickFilter.getValue()));
        sql.append(params.filter(" OR UPPER(EMPLEADO.ApellidoMaterno) LIKE UPPER('%' + :value + '%') ", quickFilter.getValue()));
        
        sql.append(params.filter(" AND ( "+
        		" (HISTORIAL.IdProyecto IS NOT NULL AND PROY.IdJefe = :idJefeInmediato)  OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NOT NULL AND DEP.IdJefe  = :idJefeInmediato)"+ 
        		" OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NULL AND HISTORIAL.IdUnidadDeNegocio IS NOT NULL AND UN.IdJefe = :idJefeInmediato) ) ", quickFilter.getIdJefeInmediato()));

        sql.append(params.filter(" AND [dbo].[GetJefeInmediato] (EMPLEADO.IdEmpleado,HISTORIAL.IdHistorialLaboral) = :idEmpleado",quickFilter.getIdEmpleado()));
        return sql.toString();
	}

}
