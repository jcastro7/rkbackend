package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.view.model.EmpleadoViewModel;
import pe.com.tss.runakuna.view.model.HistoriaLaboralViewModel;
import pe.com.tss.runakuna.view.model.PeriodoEmpleadoFilterViewModel;
import pe.com.tss.runakuna.view.model.PeriodoEmpleadoResultViewModel;
import pe.com.tss.runakuna.view.model.PeriodoEmpleadoViewModel;
import pe.com.tss.runakuna.support.WhereParams;
import pe.com.tss.runakuna.util.DateUtil;

@Repository
public class PeriodoEmpleadoJdbcRepository implements PeriodoEmpleadoRepository{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CargoJdbcRepository.class);

	@Autowired
	DataSource dataSource;
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	private String getdate;

	@PostConstruct
	public void init() {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		
		Date date = new Date();
		getdate = DateUtil.format(new SimpleDateFormat("yyyy-MM-dd"), date);
	}
	
	@Override
	public PeriodoEmpleadoViewModel obtenerPeriodoEmpleadoActual(EmpleadoViewModel empleado) {
		WhereParams params = new WhereParams();
		String sql = generarPeriodoEmpleadoActual(empleado, params);
		PeriodoEmpleadoViewModel result=new PeriodoEmpleadoViewModel();
		List<PeriodoEmpleadoViewModel> listaPeriodoEmpleado= jdbcTemplate.query(sql, params.getParams(),
				new BeanPropertyRowMapper<PeriodoEmpleadoViewModel>(PeriodoEmpleadoViewModel.class));
		if(listaPeriodoEmpleado!=null && listaPeriodoEmpleado.size()>0) {
			result=listaPeriodoEmpleado.get(0);
		}
			
		return result;
	}
	
	private String generarPeriodoEmpleadoActual(EmpleadoViewModel empleado, WhereParams params) {

		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT ");
		   
		sql.append("  pe.IdPeriodoEmpleado AS idPeriodoEmpleado, ");
		sql.append("  pe.FechaInicio AS fechaInicio, ");
		sql.append("  pe.FechaFin AS fechaFin, ");
		sql.append("  pe.Periodo AS periodo ");
		sql.append("  FROM ");
		sql.append("  PeriodoEmpleado pe ");
		sql.append("  WHERE (pe.FechaInicio<= '"+getdate+"' AND pe.FechaFin>= '"+getdate+"') ");
		sql.append("  AND pe.IdEmpleado="+empleado.getIdEmpleado());
				
		return sql.toString();
	}

	@Override
	public List<PeriodoEmpleadoResultViewModel> busquedaPeriodoEmpleado(PeriodoEmpleadoFilterViewModel busquedaPeriodoEmpleadoDto) {
		WhereParams params = new WhereParams();
		String sql = busquedaPeriodoEmpleadoQuery(busquedaPeriodoEmpleadoDto, params);
		
		return jdbcTemplate.query(sql, params.getParams(),
				new BeanPropertyRowMapper<PeriodoEmpleadoResultViewModel>(PeriodoEmpleadoResultViewModel.class));
	}

	private String busquedaPeriodoEmpleadoQuery(PeriodoEmpleadoFilterViewModel busquedaPeriodoEmpleadoDto, WhereParams params) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT DISTINCT ");		   
		sql.append("  PE.IdEmpleado AS IdEmpleado,PE.IdPeriodoEmpleado AS idPeriodoEmpleado, EMPLEADO.ApellidoPaterno, EMPLEADO.ApellidoMaterno, EMPLEADO.Nombre, ");
		sql.append(" CONCAT(EMPLEADO.ApellidoPaterno, ' ', EMPLEADO.ApellidoMaterno, ', ', EMPLEADO.Nombre) as nombreEmpleado, ");
		sql.append("  PE.Periodo AS periodo, ");
		sql.append("  PE.FechaInicio AS fechaInicio, ");
		sql.append("  PE.FechaFin AS fechaFin, ");
		sql.append("  PE.MaxDiasVacaciones AS maxDiasVacaciones, ");
		sql.append("  PE.DiasVacacionesDisponibles AS diasVacacionesDisponibles, ");
		sql.append("  PE.DiasVacacionesAcumulado AS diasVacacionesAcumulado, ");
		sql.append("  PE.MaxPermisos AS maxPermisos, ");
		sql.append("  PE.PermisosDisponibles AS permisosDisponibles, ");
		sql.append("  EMPLEADO.Codigo AS codigoEmpleado ");
		sql.append("  FROM PeriodoEmpleado PE");
		sql.append(" LEFT JOIN Empleado EMPLEADO ON PE.IdEmpleado = EMPLEADO.IdEmpleado ");
		sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMPLEADO.IdEmpleado AND ((HISTORIAL.FechaInicio < '"+getdate+"' AND HISTORIAL.FechaFin > '"+getdate+"') OR (HISTORIAL.FechaInicio < '"+getdate+"' AND HISTORIAL.FechaFin IS NULL)) ");
		sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
		sql.append(" WHERE 1 = 1");
		if(busquedaPeriodoEmpleadoDto.isVigente() == true){
			sql.append("  AND PE.FechaInicio <= '"+getdate+"' and PE.FechaFin >= '"+getdate+"' ");
		}
	    sql.append(params.filter(" AND UN.IdUnidadDeNegocio = :unidadNegocio ", busquedaPeriodoEmpleadoDto.getUnidadNegocio()));
	    sql.append(params.filter(" AND DEP.IdDepartamentoArea = :departamento ", busquedaPeriodoEmpleadoDto.getDepartamento()));
	    sql.append(params.filter(" AND PROY.IdProyecto = :proyecto ", busquedaPeriodoEmpleadoDto.getProyecto()));
	    sql.append(params.filter(" AND EMPLEADO.IdEmpleado = :idEmpleado ", busquedaPeriodoEmpleadoDto.getIdEmpleado()));
	    sql.append(params.filter(" AND ( "+
        		" (HISTORIAL.IdProyecto IS NOT NULL AND PROY.IdJefe = :idJefeInmediato)  OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NOT NULL AND DEP.IdJefe  = :idJefeInmediato)"+ 
        		" OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NULL AND HISTORIAL.IdUnidadDeNegocio IS NOT NULL AND UN.IdJefe = :idJefeInmediato) ) ", busquedaPeriodoEmpleadoDto.getIdJefeInmediato()));    
		sql.append(" order by EMPLEADO.ApellidoPaterno, EMPLEADO.ApellidoMaterno, EMPLEADO.Nombre, PE.FechaInicio desc ");

				
		return sql.toString();
	}

	@Override
	public Long obtenerIdUltimoPeriodo(Long idEmpleado) {
		WhereParams params = new WhereParams();
		String sql = busquedaIdUltimoPeriodo(idEmpleado, params);
		
		return jdbcTemplate.queryForObject(sql, params.getParams(),Long.class);
	}

	private String busquedaIdUltimoPeriodo(Long idEmpleado, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select max(IdPeriodoEmpleado) from PeriodoEmpleado where IdEmpleado= ").append(idEmpleado);
		return sql.toString();
	}

	@Override
	public List<PeriodoEmpleadoResultViewModel> obtenerPeriodosConVacacionesDisponibles(Long idEmpleado) {
		WhereParams params = new WhereParams();
		String sql = obtenerPeriodosConVacacionesDisponiblesQuery(idEmpleado, params);
		
		return jdbcTemplate.query(sql, params.getParams(),
				new BeanPropertyRowMapper<PeriodoEmpleadoResultViewModel>(PeriodoEmpleadoResultViewModel.class));
	}
	
	private String obtenerPeriodosConVacacionesDisponiblesQuery(Long idEmpleado, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ");
		
		sql.append(" (30 - isnull( ");
		sql.append(" (SELECT sum(VA.DiasCalendarios) FROM Vacacion VA WHERE VA.IdPeriodoEmpleado = PE.IdPeriodoEmpleado AND VA.Estado = 'A') ");
		sql.append(" ,0)) AS diasCalendariosDisponibles, ");
		sql.append("  	pe.IdPeriodoEmpleado AS idPeriodoEmpleado, ");
		sql.append("  	pe.DiasVacacionesDisponibles AS  diasVacacionesDisponibles, ");
		sql.append("  	pe.IdEmpleado AS idEmpleado, ");
		sql.append("  	pe.Periodo AS periodo ");
		
		sql.append(" FROM ");
		sql.append("  	PeriodoEmpleado pe");
		sql.append(" WHERE ");
		sql.append(" 	DiasVacacionesDisponibles>0 ");
		sql.append(" AND pe.IdEmpleado="+idEmpleado);
		sql.append(" ORDER BY pe.FechaInicio asc");

				
		return sql.toString();
	}
	
	@Override
	public List<PeriodoEmpleadoResultViewModel> obtenerPeriodosConVacacionesDisponiblesMayor15Dias(Long idEmpleado) {
		WhereParams params = new WhereParams();
		String sql = obtenerPeriodosConVacacionesDisponiblesMayor15DiasQuery(idEmpleado, params);
		
		return jdbcTemplate.query(sql, params.getParams(),
				new BeanPropertyRowMapper<PeriodoEmpleadoResultViewModel>(PeriodoEmpleadoResultViewModel.class));
	}
	
	private String obtenerPeriodosConVacacionesDisponiblesMayor15DiasQuery(Long idEmpleado, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ");
		
		sql.append(" (30 - isnull( ");
		sql.append(" (SELECT sum(VA.DiasCalendarios) FROM Vacacion VA WHERE VA.IdPeriodoEmpleado = PE.IdPeriodoEmpleado AND VA.Estado = 'A') ");
		sql.append(" ,0)) AS diasCalendariosDisponibles, ");
		sql.append("  	pe.IdPeriodoEmpleado AS idPeriodoEmpleado, pe.DiasVacacionesDisponibles AS  diasVacacionesDisponibles, ");
		sql.append("  	pe.IdEmpleado AS idEmpleado, ");
		sql.append("  	pe.Periodo AS periodo ");
		sql.append(" FROM ");
		sql.append("  	PeriodoEmpleado pe");
		sql.append(" WHERE ");
		sql.append(" 	DiasVacacionesDisponibles>=15 ");
		sql.append(" AND pe.IdEmpleado="+idEmpleado);
		sql.append(" ORDER BY pe.FechaInicio asc");

				
		return sql.toString();
	}

	@Override
	public List<PeriodoEmpleadoResultViewModel> busquedaPeriodoById(Long idPeriodoEmpleado) {
		WhereParams params = new WhereParams();
		String sql = busquedaPeriodoByIdQuery(idPeriodoEmpleado, params);
		
		return jdbcTemplate.query(sql, params.getParams(),
				new BeanPropertyRowMapper<PeriodoEmpleadoResultViewModel>(PeriodoEmpleadoResultViewModel.class));
	}

	private String busquedaPeriodoByIdQuery(Long idPeriodoEmpleado, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT ");
		sql.append("  pe.IdPeriodoEmpleado AS idPeriodoEmpleado ");
		sql.append("  FROM ");
		sql.append("  PeriodoEmpleado pe ");
		sql.append("  WHERE pe.IdPeriodoEmpleado="+idPeriodoEmpleado);
				
		return sql.toString();
	}

}
