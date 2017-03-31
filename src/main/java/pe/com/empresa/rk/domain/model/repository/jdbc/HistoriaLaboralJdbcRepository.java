package pe.com.empresa.rk.domain.model.repository.jdbc;

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

import pe.com.empresa.rk.support.WhereParams;
import pe.com.empresa.rk.view.model.EmpleadoViewModel;
import pe.com.empresa.rk.view.model.HistoriaLaboralViewModel;
import pe.com.empresa.rk.view.model.LicenciaEmpleadoViewModel;

@Repository
public class HistoriaLaboralJdbcRepository implements HistoriaLaboralRepository{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CargoJdbcRepository.class);

	@Autowired
	DataSource dataSource;
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@PostConstruct
	public void init() {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	private static final String SEARCH_ALL_CARGOS = "select * from Cargo";
	
	@Override
	public List<HistoriaLaboralViewModel> obtenerHistoriaLaboral(Long idEmpleado) {

        WhereParams params = new WhereParams();
		String sql = buscarHistoriaLaboralPorIdEmpleado(idEmpleado, params);
		
		return jdbcTemplate.query(sql, params.getParams(),
				new BeanPropertyRowMapper<HistoriaLaboralViewModel>(HistoriaLaboralViewModel.class));
	}

	private String buscarHistoriaLaboralPorIdEmpleado(Long idEmpleado, WhereParams params) {

		StringBuilder sql = new StringBuilder();
		
		   sql.append("  select h.IdHistorialLaboral idHistorialLaboral, \n");
		   sql.append("  un.Nombre unidadNegocio, \n");
		   sql.append("  da.Nombre departamentoArea, \n");
		   sql.append("  p.Nombre proyecto,  \n");
		   sql.append("  c.Nombre cargo, \n");
		   sql.append("  CASE ");
		   sql.append("  WHEN (h.IdProyecto IS NOT NULL) THEN ep.Nombre+' '+ep.ApellidoPaterno +' '+ep.ApellidoMaterno ");
		   sql.append("  WHEN (h.IdProyecto IS NULL AND h.IdDepartamentoArea IS NOT NULL) THEN eda.Nombre+' '+eda.ApellidoPaterno +' '+eda.ApellidoMaterno ");
		   sql.append("  WHEN (h.IdProyecto IS NULL AND h.IdDepartamentoArea IS NULL AND h.IdUnidadDeNegocio IS NOT NULL) THEN eun.Nombre+' '+eun.ApellidoPaterno +' '+eun.ApellidoMaterno ");
		   sql.append("  ELSE ' '  ");
		   sql.append("  END AS jefeInmediato, ");
		   sql.append("  h.FechaInicio fechaInicio,  \n");
		   sql.append("  h.FechaFin fechaFin \n");
		   sql.append("  From HistorialLaboral h \n");
		   sql.append("  left join UnidadDeNegocio un on un.IdUnidadDeNegocio = h.IdUnidadDeNegocio \n");
		   sql.append("  left join DepartamentoArea da on da.IdDepartamentoArea = h.IdDepartamentoArea  \n");
		   sql.append("  left join Proyecto p on p.IdProyecto = h.IdProyecto \n");
		   sql.append("  left join Cargo c on c.IdCargo = h.IdCargo \n");
		   sql.append("  left join Moneda n on n.IdMoneda = h.IdMoneda \n");
		   sql.append("  left join Empleado ep on p.IdJefe = ep.IdEmpleado \n");
		   sql.append("  LEFT JOIN Empleado eda on da.IdJefe = eda.IdEmpleado \n");
		   sql.append("  LEFT JOIN Empleado eun on un.IdJefe = eun.IdEmpleado \n");
		   sql.append("  WHERE 1 = 1 \n");
		   sql.append(params.filter(" AND h.IdEmpleado =:idEmpleado ", idEmpleado));
		   sql.append("  ORDER BY h.FechaInicio desc");
				
		return sql.toString();
	}

	@Override
	public List<HistoriaLaboralViewModel> obtenerIdHistoriaLaboral(Long idHistorialLaboral) {
		WhereParams params = new WhereParams();
		String sql = buscarHistoriaLaboralPorIdHistoriaLaboral(idHistorialLaboral, params);
		
		return jdbcTemplate.query(sql, params.getParams(),
				new BeanPropertyRowMapper<HistoriaLaboralViewModel>(HistoriaLaboralViewModel.class));
	}
	
	private String buscarHistoriaLaboralPorIdHistoriaLaboral(Long idHistorialLaboral, WhereParams params) {

		StringBuilder sql = new StringBuilder();
		
		   sql.append("  select h.IdHistorialLaboral idHistorialLaboral, \n");
		   sql.append("  un.Nombre unidadNegocio, \n");
		   sql.append("  da.Nombre departamentoArea, \n");
		   sql.append("  p.Nombre proyecto,  \n");
		   sql.append("  c.Nombre cargo, \n");
		   sql.append("  h.Salario salario,  \n");
		   sql.append("  h.Descripcion descripcion,  \n");
		   sql.append("  h.FechaInicio desdeFecha,  \n");
		   sql.append("  h.FechaFin hastaFecha \n");
		   sql.append("  From UnidadDeNegocio un \n");
		   sql.append("  inner join Cargo c on un.IdUnidadDeNegocio = c.IdUnidadDeNegocio \n");
		   sql.append("  inner join HistorialLaboral h on c.IdCargo = h.IdCargo  \n");
		   sql.append("  inner join Proyecto p on p.IdProyecto = h.IdProyecto \n");
		   sql.append("  inner join Empleado e on e.IdEmpleado = h.IdEmpleado \n");
		   sql.append("  inner join DepartamentoArea da on da.IdDepartamentoArea = p.IdDepartamentoArea \n");
		   sql.append("  WHERE 1 = 1 \n");
		   sql.append(params.filter(" AND h.IdHistorialLaboral =:idHistorialLaboral ", idHistorialLaboral));
		
		
		
		return sql.toString();
	}

	@Override
	public HistoriaLaboralViewModel obtenerHistoriaLaboralActual(EmpleadoViewModel empleado) {
		WhereParams params = new WhereParams();
		String sql = generarHistoriaLaboralActual(empleado, params);
		HistoriaLaboralViewModel result=new HistoriaLaboralViewModel();
		
		List<HistoriaLaboralViewModel> listHistoriaLaboral=jdbcTemplate.query(sql, params.getParams(),
				new BeanPropertyRowMapper<HistoriaLaboralViewModel>(HistoriaLaboralViewModel.class));
		
		if(listHistoriaLaboral!=null && listHistoriaLaboral.size()>0) {
			result=listHistoriaLaboral.get(0);
		}
		
		return result;
	}
	
	@Override
	public HistoriaLaboralViewModel obtenerHistoriaLaboralVigenteFecha(EmpleadoViewModel empleado, Date fecha) {
		WhereParams params = new WhereParams();
		String sql = generarHistoriaLaboralVigenteFecha(empleado, fecha,params);
		HistoriaLaboralViewModel result=new HistoriaLaboralViewModel();
		
		List<HistoriaLaboralViewModel> listHistoriaLaboral=jdbcTemplate.query(sql, params.getParams(),
				new BeanPropertyRowMapper<HistoriaLaboralViewModel>(HistoriaLaboralViewModel.class));
		
		if(listHistoriaLaboral!=null && listHistoriaLaboral.size()>0) {
			result=listHistoriaLaboral.get(0);
		}
		
		return result;
	}
	
	@Override
	public List<HistoriaLaboralViewModel> obtenerHistoriasLaboralesActualPorEmpleado(EmpleadoViewModel empleado) {
		WhereParams params = new WhereParams();
		String sql = generarHistoriasLaboralesActualPorEmpleado(empleado, params);
		
		return jdbcTemplate.query(sql, params.getParams(),
				new BeanPropertyRowMapper<HistoriaLaboralViewModel>(HistoriaLaboralViewModel.class));
	}
	
	@Override
	public List<HistoriaLaboralViewModel> obtenerHistoriasLaboralesPorEmpleado(Long idEmpleado) {
		WhereParams params = new WhereParams();
		String sql = generarHistoriasLaboralesPorEmpleado(idEmpleado, params);
		
		return jdbcTemplate.query(sql, params.getParams(),
				new BeanPropertyRowMapper<HistoriaLaboralViewModel>(HistoriaLaboralViewModel.class));
	}
	
	@Override
	public List<HistoriaLaboralViewModel> obtenerHistoriasLaboralesPorEmpleadoContrato(Long idEmpleado) {
		WhereParams params = new WhereParams();
		String sql = generarHistoriasLaboralesPorEmpleadoContrato(idEmpleado, params);
		
		return jdbcTemplate.query(sql, params.getParams(),
				new BeanPropertyRowMapper<HistoriaLaboralViewModel>(HistoriaLaboralViewModel.class));
	}
	
	private String generarHistoriaLaboralActual(EmpleadoViewModel empleado, WhereParams params) {

		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT TOP 1 ");
		sql.append("  h.IdHistorialLaboral AS idHistorialLaboral, ");
		sql.append("  h.FechaInicio AS fechaInicio, ");
		sql.append("  h.FechaFin AS fechaFin, ");
		sql.append("  h.IdEmpleado AS idEmpleado, ");
		sql.append("  h.IdProyecto as idProyecto, ");
		
		sql.append("  p.Nombre AS proyecto, ");
		sql.append("  un.Nombre AS unidadNegocio, ");
		sql.append("  da.Nombre as departamentoArea, ");
		
		sql.append("  CASE ");   
		sql.append("  WHEN (h.IdProyecto IS NOT NULL) THEN ep.Nombre+' '+ep.ApellidoPaterno +' '+ep.ApellidoMaterno "); 
		sql.append("  WHEN (h.IdProyecto IS NULL AND h.IdDepartamentoArea IS NOT NULL) THEN eda.Nombre+' '+eda.ApellidoPaterno +' '+eda.ApellidoMaterno "); 
		sql.append("  WHEN (h.IdProyecto IS NULL AND h.IdDepartamentoArea IS NULL AND h.IdUnidadDeNegocio IS NOT NULL) THEN eun.Nombre+' '+eun.ApellidoPaterno +' '+eun.ApellidoMaterno "); 
		sql.append("  ELSE 'No Asociado'  ");
		sql.append("  END AS jefeInmediato ");
		sql.append("  FROM ");
		sql.append("  HistorialLaboral h ");
		sql.append("  LEFT JOIN Proyecto p on p.IdProyecto = h.IdProyecto ");
		sql.append("  LEFT JOIN Empleado ep on p.IdJefe = ep.IdEmpleado ");
		sql.append("  LEFT JOIN DepartamentoArea da on da.IdDepartamentoArea = h.IdDepartamentoArea ");
		sql.append("  LEFT JOIN Empleado eda on da.IdJefe = eda.IdEmpleado ");
		sql.append("  LEFT JOIN UnidadDeNegocio un ON un.IdUnidadDeNegocio = h.IdUnidadDeNegocio ");
		sql.append("  LEFT JOIN Empleado eun on un.IdJefe = eun.IdEmpleado ");
		sql.append("  WHERE ( (h.FechaInicio<=now()  AND h.FechaFin IS NULL) OR (h.FechaInicio<= now() AND h.FechaFin IS NOT NULL AND h.FechaFin>= now()) ) ");
		sql.append("  AND h.IdEmpleado="+empleado.getIdEmpleado());
				
		
		return sql.toString();
	}
	
	private String generarHistoriaLaboralVigenteFecha(EmpleadoViewModel empleado, Date fecha ,WhereParams params) {

		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT TOP 1 ");
		sql.append("  h.IdHistorialLaboral AS idHistorialLaboral, ");
		sql.append("  h.FechaInicio AS fechaInicio, ");
		sql.append("  h.FechaFin AS fechaFin, ");
		sql.append("  h.IdEmpleado AS idEmpleado, ");
		sql.append("  h.IdProyecto as idProyecto, ");
		sql.append("  h.IdMoneda as idMoneda, ");
		sql.append("  h.Salario as salario, ");
		
		sql.append("  p.Nombre AS proyecto, ");
		sql.append("  un.Nombre AS unidadNegocio, ");
		sql.append("  da.Nombre as departamentoArea, ");
		
		sql.append("  CASE ");   
		sql.append("  WHEN (h.IdProyecto IS NOT NULL) THEN ep.Nombre+' '+ep.ApellidoPaterno +' '+ep.ApellidoMaterno "); 
		sql.append("  WHEN (h.IdProyecto IS NULL AND h.IdDepartamentoArea IS NOT NULL) THEN eda.Nombre+' '+eda.ApellidoPaterno +' '+eda.ApellidoMaterno "); 
		sql.append("  WHEN (h.IdProyecto IS NULL AND h.IdDepartamentoArea IS NULL AND h.IdUnidadDeNegocio IS NOT NULL) THEN eun.Nombre+' '+eun.ApellidoPaterno +' '+eun.ApellidoMaterno "); 
		sql.append("  ELSE 'No Asociado'  ");
		sql.append("  END AS jefeInmediato ");
		sql.append("  FROM ");
		sql.append("  HistorialLaboral h ");
		sql.append("  LEFT JOIN Proyecto p on p.IdProyecto = h.IdProyecto ");
		sql.append("  LEFT JOIN Empleado ep on p.IdJefe = ep.IdEmpleado ");
		sql.append("  LEFT JOIN DepartamentoArea da on da.IdDepartamentoArea = h.IdDepartamentoArea ");
		sql.append("  LEFT JOIN Empleado eda on da.IdJefe = eda.IdEmpleado ");
		sql.append("  LEFT JOIN UnidadDeNegocio un ON un.IdUnidadDeNegocio = h.IdUnidadDeNegocio ");
		sql.append("  LEFT JOIN Empleado eun on un.IdJefe = eun.IdEmpleado ");
		sql.append("  WHERE  ");
		sql.append(params.filter("   ( (h.FechaInicio<= :fecha AND h.FechaFin IS NULL) OR (h.FechaInicio<= :fecha AND h.FechaFin IS NOT NULL AND h.FechaFin>= :fecha) ) ",fecha));
		sql.append("  AND h.IdEmpleado="+empleado.getIdEmpleado());
				
		
		return sql.toString();
	}
	
	private String generarHistoriasLaboralesActualPorEmpleado(EmpleadoViewModel empleado, WhereParams params) {

		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT DISTINCT ");
		sql.append("  h.IdEmpleado AS idEmpleado, ");
		
		sql.append("  CASE ");   
		sql.append("  WHEN (h.IdProyecto IS NOT NULL) THEN p.IdJefe "); 
		sql.append("  WHEN (h.IdProyecto IS NULL AND h.IdDepartamentoArea IS NOT NULL) THEN da.IdJefe "); 
		sql.append("  WHEN (h.IdProyecto IS NULL AND h.IdDepartamentoArea IS NULL AND h.IdUnidadDeNegocio IS NOT NULL) THEN un.IdJefe "); 
		sql.append("  ELSE null  ");
		sql.append("  END AS idJefeInmediato, ");
		
		sql.append("  CASE ");   
		sql.append("  WHEN (h.IdProyecto IS NOT NULL) THEN ep.Nombre+' '+ep.ApellidoPaterno +' '+ep.ApellidoMaterno "); 
		sql.append("  WHEN (h.IdProyecto IS NULL AND h.IdDepartamentoArea IS NOT NULL) THEN eda.Nombre+' '+eda.ApellidoPaterno +' '+eda.ApellidoMaterno "); 
		sql.append("  WHEN (h.IdProyecto IS NULL AND h.IdDepartamentoArea IS NULL AND h.IdUnidadDeNegocio IS NOT NULL) THEN eun.Nombre+' '+eun.ApellidoPaterno +' '+eun.ApellidoMaterno "); 
		sql.append("  ELSE 'No Asociado'  ");
		sql.append("  END AS jefeInmediato ");
		sql.append("  FROM ");
		sql.append("  HistorialLaboral h ");
		sql.append("  LEFT JOIN Proyecto p on p.IdProyecto = h.IdProyecto ");
		sql.append("  LEFT JOIN Empleado ep on p.IdJefe = ep.IdEmpleado ");
		sql.append("  LEFT JOIN DepartamentoArea da on da.IdDepartamentoArea = h.IdDepartamentoArea ");
		sql.append("  LEFT JOIN Empleado eda on da.IdJefe = eda.IdEmpleado ");
		sql.append("  LEFT JOIN UnidadDeNegocio un ON un.IdUnidadDeNegocio = h.IdUnidadDeNegocio ");
		sql.append("  LEFT JOIN Empleado eun on un.IdJefe = eun.IdEmpleado ");
		   
		sql.append("  WHERE ( (h.FechaInicio<= now() AND h.FechaFin IS NULL) OR (h.FechaInicio<= now() AND h.FechaFin IS NOT NULL AND h.FechaFin>= now()) ) ");
		sql.append("  AND h.IdEmpleado="+empleado.getIdEmpleado());
				
		
		return sql.toString();
	}
	
	private String generarHistoriasLaboralesPorEmpleado(Long idEmpleado, WhereParams params) {

		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT DISTINCT ");
		sql.append("  h.IdEmpleado AS idEmpleado, ");
		sql.append("  [dbo].[GetJefeInmediato] (h.IdEmpleado,h.IdHistorialLaboral) AS idJefeInmediato, ");
		sql.append("  ep.Nombre+' '+ep.ApellidoPaterno +' '+ep.ApellidoMaterno AS jefeInmediato ");
		sql.append("  FROM ");
		sql.append("  HistorialLaboral h ");
		sql.append("  LEFT JOIN Empleado ep on ep.IdEmpleado = [dbo].[GetJefeInmediato] (h.IdEmpleado,h.IdHistorialLaboral) ");
		   
		sql.append("  WHERE ( (h.FechaInicio<= now() AND h.FechaFin IS NULL) OR (h.FechaInicio<= now() AND h.FechaFin IS NOT NULL AND h.FechaFin>= now()) ) ");
		sql.append(params.filter("  AND h.IdEmpleado = :idEmpleado ",idEmpleado));
				
		
		return sql.toString();
	}

	private String generarHistoriasLaboralesPorEmpleadoContrato(Long idEmpleado, WhereParams params) {

		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT ");
		sql.append("  h.IdEmpleado AS idEmpleado, ");

		sql.append("  h.IdHistorialLaboral AS idHistorialLaboral, ");
		sql.append("  h.FechaInicio AS fechaInicio, ");
		sql.append("  h.FechaFin AS fechaFin, ");
		sql.append("  h.IdEmpleado AS idEmpleado, ");
		sql.append("  h.IdMoneda as idMoneda, ");
		sql.append("  h.Salario as salario ");
		
		sql.append("  FROM ");
		sql.append("  HistorialLaboral h ");
		
		sql.append("  WHERE h.FechaInicio<= now() AND (h.FechaFin IS NULL OR h.FechaFin>= now()) ");
		sql.append("  AND h.IdEmpleado="+idEmpleado);
				
		
		return sql.toString();
	}
	
	@Override
	public HistoriaLaboralViewModel obtenerUltimoCargo(Long idEmpleado) {
		WhereParams params = new WhereParams();
		String sql = obtenerUltimoCargo(idEmpleado, params);
		
		return jdbcTemplate.queryForObject(sql, params.getParams(),
				new BeanPropertyRowMapper<HistoriaLaboralViewModel>(HistoriaLaboralViewModel.class));
	}
	
	private String obtenerUltimoCargo(Long idEmpleado, WhereParams params) {

		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT IdHistorialLaboral AS idHistorialLaboral,");
		sql.append("  IdEmpleado AS IdEmpleado ");
		sql.append("  FROM (select IdHistorialLaboral,IdEmpleado, row_number() over(partition by IdEmpleado order by FechaInicio desc) as rn from HistorialLaboral) as T");
		sql.append("  WHERE rn = 1 ");
		sql.append("  AND IdEmpleado="+idEmpleado);
				
		
		return sql.toString();
	}

	@Override
	public Long obtenerCantidadCargos(Long idEmpleado) {
		WhereParams params = new WhereParams();
		String sql = obtenerCantidadCargos(idEmpleado, params);
		return calculateTotalRows(sql, params);
	}

	private String obtenerCantidadCargos(Long idEmpleado, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT IdHistorialLaboral AS idHistorialLaboral,");
		sql.append("  IdEmpleado AS IdEmpleado ");
		sql.append("  FROM HistorialLaboral");
		sql.append("  WHERE 1 = 1 ");
		sql.append("  AND IdEmpleado="+idEmpleado);
		
		return sql.toString();
	}
	
	private long calculateTotalRows(String queryBase, WhereParams params) {
        String query = "SELECT COUNT(1) FROM (" + queryBase + ") X";
        return jdbcTemplate.queryForObject(query, params.getParams(), Long.class);
    }

	@Override
	public HistoriaLaboralViewModel obtenerPrimerCargo(Long idEmpleado) {
		WhereParams params = new WhereParams();
		String sql = obtenerPrimerCargo(idEmpleado, params);
		
		return jdbcTemplate.queryForObject(sql, params.getParams(),
				new BeanPropertyRowMapper<HistoriaLaboralViewModel>(HistoriaLaboralViewModel.class));
	}
	
	private String obtenerPrimerCargo(Long idEmpleado, WhereParams params) {

		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT IdHistorialLaboral AS idHistorialLaboral,");
		sql.append("  IdEmpleado AS IdEmpleado ");
		sql.append("  FROM (select IdHistorialLaboral,IdEmpleado, row_number() over(partition by IdEmpleado order by FechaInicio asc) as rn from HistorialLaboral) as T");
		sql.append("  WHERE rn = 1 ");
		sql.append("  AND IdEmpleado="+idEmpleado);
				
		
		return sql.toString();
	}

	@Override
	public HistoriaLaboralViewModel obtenerHistoriaLaboralLicencia(LicenciaEmpleadoViewModel licenciaEmpleadoD) {
		WhereParams params = new WhereParams();
		String sql = generarHistoriaLaboralLicencia(licenciaEmpleadoD, params);
		
//		return jdbcTemplate.queryForObject(sql, params.getParams(),
//				new BeanPropertyRowMapper<HistoriaLaboralDto>(HistoriaLaboralDto.class));
		List<HistoriaLaboralViewModel> searchResults = jdbcTemplate.query(sql,params.getParams(), new BeanPropertyRowMapper<HistoriaLaboralViewModel>(HistoriaLaboralViewModel.class));

        LOGGER.info("Found module {}", searchResults);

        if (searchResults.size() > 0) {
            return searchResults.get(0);
        }

        return null;
	}

	private String generarHistoriaLaboralLicencia(LicenciaEmpleadoViewModel licenciaEmpleadoD, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT TOP 1 ");
		sql.append("  h.IdHistorialLaboral AS idHistorialLaboral, ");
		sql.append("  h.FechaInicio AS fechaInicio, ");
		sql.append("  h.FechaFin AS fechaFin, ");
		sql.append("  h.IdEmpleado AS idEmpleado, ");
		sql.append("  h.IdProyecto as idProyecto, ");
		
		sql.append("  p.Nombre AS proyecto, ");
		sql.append("  un.Nombre AS unidadNegocio, ");
		sql.append("  da.Nombre as departamentoArea, ");
		
		sql.append("  CASE ");   
		sql.append("  WHEN (h.IdProyecto IS NOT NULL) THEN ep.Nombre+' '+ep.ApellidoPaterno +' '+ep.ApellidoMaterno "); 
		sql.append("  WHEN (h.IdProyecto IS NULL AND h.IdDepartamentoArea IS NOT NULL) THEN eda.Nombre+' '+eda.ApellidoPaterno +' '+eda.ApellidoMaterno "); 
		sql.append("  WHEN (h.IdProyecto IS NULL AND h.IdDepartamentoArea IS NULL AND h.IdUnidadDeNegocio IS NOT NULL) THEN eun.Nombre+' '+eun.ApellidoPaterno +' '+eun.ApellidoMaterno "); 
		sql.append("  ELSE 'No Asociado'  ");
		sql.append("  END AS jefeInmediato ");
		sql.append("  FROM ");
		sql.append("  HistorialLaboral h ");
		sql.append("  LEFT JOIN Proyecto p on p.IdProyecto = h.IdProyecto ");
		sql.append("  LEFT JOIN Empleado ep on p.IdJefe = ep.IdEmpleado ");
		sql.append("  LEFT JOIN DepartamentoArea da on da.IdDepartamentoArea = h.IdDepartamentoArea ");
		sql.append("  LEFT JOIN Empleado eda on da.IdJefe = eda.IdEmpleado ");
		sql.append("  LEFT JOIN UnidadDeNegocio un ON un.IdUnidadDeNegocio = h.IdUnidadDeNegocio ");
		sql.append("  LEFT JOIN Empleado eun on un.IdJefe = eun.IdEmpleado ");
		   
		sql.append("  WHERE (h.FechaInicio<= now() AND h.FechaFin>= now()) ");
		sql.append("  AND h.IdEmpleado="+licenciaEmpleadoD.getIdEmpleado());
				
		
		return sql.toString();
	}

}
