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
import pe.com.empresa.rk.view.model.EmpleadoViewModel;
import pe.com.empresa.rk.view.model.PeriodoEmpleadoFilterViewModel;
import pe.com.empresa.rk.view.model.PeriodoEmpleadoResultViewModel;
import pe.com.empresa.rk.view.model.PeriodoEmpleadoViewModel;

@Repository
public class PeriodoEmpleadoJdbcRepository implements PeriodoEmpleadoRepository{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CargoJdbcRepository.class);

	@Autowired
	DataSource dataSource;
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	

	@PostConstruct
	public void init() {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
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
		sql.append("  WHERE (pe.FechaInicio<= now() AND pe.FechaFin>= now()) ");
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
		sql.append("  SELECT ");		   
		sql.append("  PE.IdPeriodoEmpleado AS idPeriodoEmpleado, ");
		sql.append(" CONCAT(EMPLEADO.ApellidoPaterno, ' ', EMPLEADO.ApellidoMaterno, ', ', EMPLEADO.Nombre) as nombreEmpleado, ");
		sql.append("  PE.FechaInicio AS fechaInicio, ");
		sql.append("  PE.FechaFin AS fechaFin, ");
		sql.append("  PE.MaxDiasVacaciones AS maxDiasVacaciones, ");
		sql.append("  PE.DiasVacacionesDisponibles AS diasVacacionesDisponibles, ");
		sql.append("  PE.MaxPermisos AS maxPermisos, ");
		sql.append("  PE.PermisosDisponibles AS permisosDisponibles ");
		sql.append("  FROM PeriodoEmpleado PE");
		sql.append(" LEFT JOIN Empleado EMPLEADO ON PE.IdEmpleado = EMPLEADO.IdEmpleado ");
		sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMPLEADO.IdEmpleado AND ((HISTORIAL.FechaInicio < now() AND HISTORIAL.FechaFin > now()) OR (HISTORIAL.FechaInicio < now() AND HISTORIAL.FechaFin IS NULL)) ");
		sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
		sql.append(" WHERE 1 = 1");
		if(busquedaPeriodoEmpleadoDto.isVigente() == true){
			sql.append("  AND PE.FechaInicio <= now() and PE.FechaFin >= now() ");
		}
	    sql.append(params.filter(" AND UN.IdUnidadDeNegocio = :unidadNegocio ", busquedaPeriodoEmpleadoDto.getUnidadNegocio()));
	    sql.append(params.filter(" AND DEP.IdDepartamentoArea = :departamento ", busquedaPeriodoEmpleadoDto.getDepartamento()));
	    sql.append(params.filter(" AND PROY.IdProyecto = :proyecto ", busquedaPeriodoEmpleadoDto.getProyecto()));
	    sql.append(params.filter(" AND EMPLEADO.IdEmpleado = :idEmpleado ", busquedaPeriodoEmpleadoDto.getIdEmpleado()));
	    sql.append(params.filter(" AND ( "+
        		" (HISTORIAL.IdProyecto IS NOT NULL AND PROY.IdJefe = :idJefeInmediato)  OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NOT NULL AND DEP.IdJefe  = :idJefeInmediato)"+ 
        		" OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NULL AND HISTORIAL.IdUnidadDeNegocio IS NOT NULL AND UN.IdJefe = :idJefeInmediato) ) ", busquedaPeriodoEmpleadoDto.getIdJefeInmediato()));    
		sql.append(" order by pe.FechaInicio asc ");

				
		return sql.toString();
	}

}
