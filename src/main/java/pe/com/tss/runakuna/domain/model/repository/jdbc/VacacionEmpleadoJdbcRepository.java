package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.support.ExtendedBeanPropertyRowMapper;
import pe.com.tss.runakuna.support.WhereParams;
import pe.com.tss.runakuna.util.DateUtil;
import pe.com.tss.runakuna.util.StringUtil;
import pe.com.tss.runakuna.view.model.EmpleadoPlanillaResultViewModel;
import pe.com.tss.runakuna.view.model.EmpleadoViewModel;
import pe.com.tss.runakuna.view.model.QuickFilterViewModel;
import pe.com.tss.runakuna.view.model.VacacionEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.VacacionFilterViewModel;
import pe.com.tss.runakuna.view.model.VacacionPendienteResultViewModel;
import pe.com.tss.runakuna.view.model.VacacionQuickFilterViewModel;
import pe.com.tss.runakuna.view.model.VacacionResultViewModel;
import pe.com.tss.runakuna.view.model.VacacionViewModel;

@Repository
public class VacacionEmpleadoJdbcRepository implements VacacionEmpleadoRepository{

	private static final Logger LOGGER = LoggerFactory.getLogger(VacacionEmpleadoJdbcRepository.class);

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
		sql.append("  pe.DiasVacacionesAcumulado AS diasVacacionesAcumulado, ");
		sql.append("  pe.DiasVacacionesDisponibles AS diasVacacionesDisponibles ");
		sql.append("  FROM ");
		sql.append("  PeriodoEmpleado pe");
		sql.append("  WHERE pe.IdEmpleado="+empleadoDto.getIdEmpleado());
		sql.append("  AND '"+getdate+"' BETWEEN pe.FechaInicio and pe.FechaFin ");

				
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
		sql.append("  pe.IdPeriodoEmpleado AS idPeriodoEmpleado, pe.DiasVacacionesDisponibles as  diasVacacionesDisponibles, ");
		sql.append("  pe.Periodo AS periodo ");
		sql.append("  FROM ");
		sql.append("  PeriodoEmpleado pe");
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
		sql.append("  WHERE 1 = 1 AND Estado in ('A','E','P') ");
		sql.append("  AND Tipo != 'C' ");
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
		
        sql.append(" SELECT va.IdVacacion AS idVacacion, ");
        sql.append(" EMPLEADO.IdEmpleado AS idEmpleado, ");
        sql.append(" va.IdPeriodoEmpleado AS idPeriodoEmpleado, ");
        sql.append(" CONCAT(EMPLEADO.ApellidoPaterno, ' ', EMPLEADO.ApellidoMaterno, ', ', EMPLEADO.Nombre) as nombreEmpleado, ");
        sql.append(" va.FechaInicio as fechaInicio, ");
        sql.append(" va.FechaFin as fechaFin, ");
        sql.append(" va.FechaInicio as desde, ");
        sql.append(" va.FechaFin as hasta, ");
        sql.append(" va.DiasCalendarios as diasCalendarios, ") ;
        sql.append(" va.DiasHabiles as diasHabiles, ") ;
        sql.append(" CONCAT(ATENDIDO.ApellidoPaterno, ' ', ATENDIDO.ApellidoMaterno, ', ', ATENDIDO.Nombre) as nombreJefeInmediato, ");
        sql.append(" ESTADO.Nombre AS estado, ");
        sql.append(" TIPO.Nombre AS tipo, ");
        sql.append(" CASE");
        sql.append(" WHEN IncluidoPlanilla = 1 THEN 'Si'");
        sql.append(" WHEN IncluidoPlanilla = 0 THEN 'No'");
        sql.append(" ELSE 'No'");
        sql.append(" END AS nombreIncluidoPlanilla, ");
        sql.append(" PERIODO_EMPLEADO.Periodo as periodo, ");
        sql.append(" va.IdAtendidoPor as idAtendidoPor ");
        sql.append(" from Vacacion va ") ;
        sql.append(" LEFT JOIN TablaGeneral ESTADO ON va.Estado=ESTADO.Codigo and ESTADO.GRUPO='Vacaciones.Estado'");
        sql.append(" LEFT JOIN TablaGeneral TIPO ON va.Tipo=TIPO.Codigo and TIPO.GRUPO='Vacaciones.Tipo'");
        sql.append(" LEFT JOIN PeriodoEmpleado PERIODO_EMPLEADO ON va.IdPeriodoEmpleado = PERIODO_EMPLEADO.IdPeriodoEmpleado ");
        sql.append(" LEFT JOIN Empleado EMPLEADO ON PERIODO_EMPLEADO.IdEmpleado = EMPLEADO.IdEmpleado ");
        
        sql.append(" LEFT JOIN Empleado ATENDIDO ON va.IdAtendidoPor = ATENDIDO.IdEmpleado ");
     
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMPLEADO.IdEmpleado AND ((HISTORIAL.FechaInicio < '"+getdate+"' AND HISTORIAL.FechaFin > '"+getdate+"') AND (HISTORIAL.FechaInicio < '"+getdate+"' AND HISTORIAL.FechaFin IS NULL)) ");
        
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
        sql.append(params.filter(" AND va.IncluidoPlanilla = :incluidoPlanilla ", filterViewModel.getIncluidoPlanilla()));
        sql.append(params.filter(" AND EMPLEADO.IdEmpleado = :idEmpleado ", filterViewModel.getIdEmpleado()));
        
        sql.append(params.filter(" AND va.IdAtendidoPor = :idJefeInmediato ", filterViewModel.getIdJefeInmediato()));
        
        sql.append(" ORDER BY va.FechaInicio DESC, nombreEmpleado ");
        
        return sql.toString();
	}
	
	private String generarBusquedaVacacionesPendientesEmpleado(VacacionFilterViewModel filterViewModel,
			WhereParams params) {
		StringBuilder sql = new StringBuilder();
        sql.append(" select em.codigo, em.nombre as nombreEmpleado, em.apellidopaterno as apellidoPaterno, em.apellidomaterno as apellidoMaterno, pe.periodo, pe.maxdiasvacaciones as maxDiasVacacionesPeriodo,  ");
        sql.append(" pe.diasvacacionesdisponibles as diasHabilesVacacionesDisponibles, ");
        sql.append(" 30 - isnull(sum(va.diascalendarios),0) as diasCalendarioVacacionesDisponibles,  ");
        sql.append(" isnull(sum(va.diashabiles),0) as diasHabilesVacacionesUsadas, ");
        sql.append(" isnull(sum(va.diascalendarios),0) as diasCalendarioVacacionesUsadas ");
        sql.append(" from periodoempleado pe ");
        sql.append(" inner join empleado em on pe.idempleado = em.idempleado ") ;
        sql.append(" left join vacacion va on pe.idperiodoempleado = va.idperiodoempleado AND va.Estado = 'A' ") ;
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EM.IdEmpleado AND (HISTORIAL.FechaInicio < '"+getdate+"' AND (HISTORIAL.FechaInicio < '"+getdate+"' AND HISTORIAL.FechaFin IS NULL)) ");
        
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        
        sql.append(" where 1=1 ");
        sql.append(" AND pe.diasvacacionesdisponibles > 0 ");
        sql.append(" AND pe.Fechafin < '"+getdate+"'");
        
        sql.append(params.filter(" AND em.IdEmpleado = :idEmpleado ", filterViewModel.getIdEmpleado()));
        
        sql.append(params.filter(" AND UN.IdUnidadDeNegocio = :unidadNegocio ", filterViewModel.getUnidadNegocio()));
        sql.append(params.filter(" AND DEP.IdDepartamentoArea = :departamento ", filterViewModel.getDepartamento()));
        sql.append(params.filter(" AND PROY.IdProyecto = :proyecto ", filterViewModel.getProyecto()));

        sql.append(params.filter(" AND [dbo].[GetJefeInmediato] (em.IdEmpleado,HISTORIAL.IdHistorialLaboral) = :idJefeInmediato ", filterViewModel.getIdJefeInmediato()));

        sql.append(" group by em.codigo, em.nombre, em.apellidopaterno, em.apellidomaterno, pe.periodo, pe.maxdiasvacaciones, pe.diasvacacionesdisponibles ");
        sql.append(" order by em.apellidopaterno, em.apellidomaterno, em.nombre ");
        
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
        sql.append(" SELECT va.IdVacacion AS idVacacion, ");
        sql.append(" EMPLEADO.IdEmpleado AS idEmpleado, ");
        sql.append(" va.IdPeriodoEmpleado AS idPeriodoEmpleado, ");
        sql.append(" CONCAT(EMPLEADO.ApellidoPaterno, ' ', EMPLEADO.ApellidoMaterno, ', ', EMPLEADO.Nombre) as nombreEmpleado, ");
        sql.append(" va.FechaInicio as fechaInicio, ");
        sql.append(" va.FechaFin as fechaFin, ");
        sql.append(" va.FechaInicio as desde, ");
        sql.append(" va.FechaFin as hasta, ");
        sql.append(" va.DiasCalendarios as diasCalendarios, ") ;
        sql.append(" va.DiasHabiles as diasHabiles, ") ;
        sql.append(" CONCAT(ATENDIDO.ApellidoPaterno, ' ', ATENDIDO.ApellidoMaterno, ', ', ATENDIDO.Nombre) as nombreJefeInmediato, ");
        sql.append(" CASE");
        sql.append(" WHEN va.IncluidoPlanilla = 1 THEN 'Si'");
        sql.append(" WHEN va.IncluidoPlanilla = 0 THEN 'No'");
        sql.append(" ELSE 'No'");
        sql.append(" END AS nombreIncluidoPlanilla, ");
        sql.append(" PERIODO_EMPLEADO.Periodo as periodo, ");
        sql.append(" ESTADO.Nombre AS estado, ");
        sql.append(" TIPO.Nombre AS tipo ");
        sql.append(" from Vacacion va ") ;
        sql.append(" LEFT JOIN TablaGeneral ESTADO ON va.Estado=ESTADO.Codigo and ESTADO.GRUPO='Vacaciones.Estado'");
        sql.append(" LEFT JOIN TablaGeneral TIPO ON va.Tipo=TIPO.Codigo and TIPO.GRUPO='Vacaciones.Tipo'");
        sql.append(" LEFT JOIN PeriodoEmpleado PERIODO_EMPLEADO ON va.IdPeriodoEmpleado = PERIODO_EMPLEADO.IdPeriodoEmpleado ");
        sql.append(" LEFT JOIN Empleado EMPLEADO ON PERIODO_EMPLEADO.IdEmpleado = EMPLEADO.IdEmpleado ");
        
        sql.append(" LEFT JOIN Empleado ATENDIDO ON va.IdAtendidoPor = ATENDIDO.IdEmpleado ");
        
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMPLEADO.IdEmpleado AND ((HISTORIAL.FechaInicio < '"+getdate+"' AND HISTORIAL.FechaFin > '"+getdate+"') OR (HISTORIAL.FechaInicio < '"+getdate+"' AND HISTORIAL.FechaFin IS NULL)) ");
        
        sql.append(" where 1=1 ");
        sql.append(params.filterDateDesde_US(" AND va.FechaInicio " , quickFilter.getFechaInicio()));
        sql.append(params.filterDateHasta_US(" AND va.FechaFin ", quickFilter.getFechaFin()));
        sql.append(params.filter(" AND va.IdAtendidoPor = :idEmpleado ", quickFilter.getIdEmpleado()));
        sql.append(params.filter(" AND (UPPER(EMPLEADO.Nombre) LIKE UPPER('%' + :value + '%') ", quickFilter.getValue()));
        sql.append(params.filter(" OR UPPER(EMPLEADO.ApellidoPaterno) LIKE UPPER('%' + :value + '%') ", quickFilter.getValue()));
        sql.append(params.filter(" OR UPPER(EMPLEADO.ApellidoMaterno) LIKE UPPER('%' + :value + '%')) ", quickFilter.getValue()));
       
        sql.append(" ORDER BY va.FechaInicio DESC, nombreEmpleado ");
        
        return sql.toString();
	}

	@Override
	public List<VacacionPendienteResultViewModel> busquedaVacacionesPendientesEmpleado(
			VacacionFilterViewModel filterViewModel) {
		WhereParams params = new WhereParams();
		String sql = generarBusquedaVacacionesPendientesEmpleado(filterViewModel, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<>(VacacionPendienteResultViewModel.class));

	}
	
	@Override
	public List<EmpleadoPlanillaResultViewModel> obtenerBusquedaEmpleadoPlanilla() {
		
		WhereParams params = new WhereParams();
		String sql = obtenerBusquedaEmpleadoPlanillaQuery(params);
		
		return jdbcTemplate.query(sql, params.getParams(),
				new ResultSetExtractor<List<EmpleadoPlanillaResultViewModel>>() {
						
			public List<EmpleadoPlanillaResultViewModel> extractData(ResultSet rs) throws SQLException {
		        Hashtable<Integer,EmpleadoPlanillaResultViewModel> empleados = new Hashtable<Integer,EmpleadoPlanillaResultViewModel>();
		        
		        while(rs.next()) {
		            Integer idEmpleado = rs.getInt("idEmpleado");
		            EmpleadoPlanillaResultViewModel empleadoPlanilla = empleados.get(idEmpleado);
		            if (empleadoPlanilla == null) {
		            	empleadoPlanilla = new ExtendedBeanPropertyRowMapper<EmpleadoPlanillaResultViewModel>(EmpleadoPlanillaResultViewModel.class).mapRow(rs, rs.getRow());
		            	empleados.put(idEmpleado,empleadoPlanilla);
		            }
		            VacacionEmpleadoViewModel item = new ExtendedBeanPropertyRowMapper<VacacionEmpleadoViewModel>(VacacionEmpleadoViewModel.class).mapRow(rs, rs.getRow());
		            empleadoPlanilla.getVacacionesEmpleado().add(item);
		        }
		        return new ArrayList<EmpleadoPlanillaResultViewModel>(empleados.values());
		    
			}
			
		});
		
	}
		
	private String obtenerBusquedaEmpleadoPlanillaQuery(WhereParams params){
		StringBuilder sql = new StringBuilder();

		sql.append("  SELECT ");
		sql.append("  EMP.Nombre AS nombre,");
		sql.append("  EMP.ApellidoPaterno AS apellidoPaterno,");
		sql.append("  EMP.ApellidoMaterno AS apellidoMaterno,");
		sql.append("  EMP.IdEmpleado AS idEmpleado,");
		sql.append("  PER.Periodo AS periodo,");
		sql.append("  VAC.FechaInicio AS fechaInicio,");
		sql.append("  VAC.FechaFin AS fechaFin,");
		sql.append("  VAC.Tipo AS tipo, ");
		sql.append("  CASE ");
		sql.append("  WHEN VAC.IncluidoPlanilla = 1 THEN 1 ");
		sql.append("  ELSE 0 ");
		sql.append("  END AS incluidoPlanilla ");

		sql.append("  FROM Vacacion VAC ");
		sql.append("  INNER JOIN PeriodoEmpleado PER ON PER.IdPeriodoEmpleado = VAC.IdPeriodoEmpleado ");
		sql.append("  INNER JOIN Empleado EMP ON EMP.IdEmpleado = PER.IdEmpleado ");

		sql.append("  WHERE VAC.IncluidoPlanilla = 0 ");
		sql.append("  ORDER BY EMP.ApellidoPaterno, EMP.ApellidoMaterno, EMP.Nombre ");
		
		
		return sql.toString();
	}
	
	

	@Override
	public List<VacacionResultViewModel> generarBusquedaVacacionesPlanilla(VacacionFilterViewModel filterViewModel) {
		
		WhereParams params = new WhereParams();
		String sql = generarBusquedaVacacionesPlanillaQuery(filterViewModel, params);
		
		 return jdbcTemplate.query(sql.toString(),
	                params.getParams(), new BeanPropertyRowMapper<>(VacacionResultViewModel.class));
	}
	
	private String generarBusquedaVacacionesPlanillaQuery(VacacionFilterViewModel filterViewModel,
			WhereParams params) {
		StringBuilder sql = new StringBuilder();
		
        sql.append(" SELECT va.IdVacacion AS idVacacion, ");
        sql.append(" EMPLEADO.IdEmpleado AS idEmpleado, ");
        sql.append(" va.IdPeriodoEmpleado AS idPeriodoEmpleado, ");
        sql.append(" CONCAT(EMPLEADO.ApellidoPaterno, ' ', EMPLEADO.ApellidoMaterno, ', ', EMPLEADO.Nombre) as nombreEmpleado, ");
        sql.append(" va.FechaInicio as fechaInicio, ");
        sql.append(" va.FechaFin as fechaFin, ");
        sql.append(" va.FechaInicio as desde, ");
        sql.append(" va.FechaFin as hasta, ");
        sql.append(" va.DiasCalendarios as diasCalendarios, ") ;
        sql.append(" va.DiasHabiles as diasHabiles, ") ;
        sql.append(" CONCAT(ATENDIDO.ApellidoPaterno, ' ', ATENDIDO.ApellidoMaterno, ', ', ATENDIDO.Nombre) as nombreJefeInmediato, ");
        sql.append(" ESTADO.Nombre AS estado, ");
        sql.append(" TIPO.Nombre AS tipo, ");
        sql.append(" CASE");
        sql.append(" WHEN va.IncluidoPlanilla = 1 THEN 'Si'");
        sql.append(" WHEN va.IncluidoPlanilla = 0 THEN 'No'");
        sql.append(" ELSE 'No'");
        sql.append(" END AS nombreIncluidoPlanilla, ");
        
        sql.append(" va.IncluidoPlanilla AS incluidoPlanilla, ");
        
        sql.append(" va.MesPlanilla AS mesPlanilla, ");
        
        sql.append(" PERIODO_EMPLEADO.Periodo as periodo, ");
        sql.append(" va.IdAtendidoPor as idAtendidoPor ");
        sql.append(" from Vacacion va ") ;
        sql.append(" LEFT JOIN TablaGeneral ESTADO ON va.Estado=ESTADO.Codigo and ESTADO.GRUPO='Vacaciones.Estado'");
        sql.append(" LEFT JOIN TablaGeneral TIPO ON va.Tipo=TIPO.Codigo and TIPO.GRUPO='Vacaciones.Tipo'");
        sql.append(" LEFT JOIN PeriodoEmpleado PERIODO_EMPLEADO ON va.IdPeriodoEmpleado = PERIODO_EMPLEADO.IdPeriodoEmpleado ");
        sql.append(" LEFT JOIN Empleado EMPLEADO ON PERIODO_EMPLEADO.IdEmpleado = EMPLEADO.IdEmpleado ");
        
        sql.append(" LEFT JOIN Empleado ATENDIDO ON va.IdAtendidoPor = ATENDIDO.IdEmpleado ");
     
        sql.append(" WHERE va.Estado = 'A' " );
        sql.append(params.filter(" AND va.IncluidoPlanilla = :incluidoPlanilla ", filterViewModel.getIncluidoPlanilla()));
        sql.append(params.filter(" AND EMPLEADO.IdEmpleado = :idEmpleado ", filterViewModel.getIdEmpleado()));
        
        if(filterViewModel.getAnio() !=null && filterViewModel.getMes() != null){
        	
        	sql.append(" AND va.MesPlanilla = '"+StringUtil.autocompleteZeroLeft(filterViewModel.getMes().toString())+"/"+String.valueOf(filterViewModel.getAnio())+"'");
        }
        
		if(filterViewModel.getAnio() ==null && filterViewModel.getMes() != null){
		        	
        	sql.append(" AND va.MesPlanilla like '"+StringUtil.autocompleteZeroLeft(filterViewModel.getMes().toString())+"/%'");
        }

		if(filterViewModel.getAnio() !=null && filterViewModel.getMes() == null){
			
			sql.append(" AND va.MesPlanilla like '%/"+String.valueOf(filterViewModel.getAnio())+"'");
		}
          	
        sql.append(" ORDER BY nombreEmpleado , va.FechaInicio DESC ");
        
        return sql.toString();
	}

}
