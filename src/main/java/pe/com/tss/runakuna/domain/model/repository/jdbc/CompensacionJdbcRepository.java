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
import pe.com.tss.runakuna.view.model.CompensacionFilterViewModel;
import pe.com.tss.runakuna.view.model.CompensacionQuickFilterViewModel;
import pe.com.tss.runakuna.view.model.CompensacionResultViewModel;
import pe.com.tss.runakuna.view.model.CompensacionViewModel;
import pe.com.tss.runakuna.view.model.QuickFilterViewModel;

/**
 * Created by josediaz on 2/11/2016.
 */

@Repository
public class CompensacionJdbcRepository implements CompensacionRepository {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    DataSource dataSource;

    private NamedParameterJdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
	public List<CompensacionResultViewModel> SearchByFilter(CompensacionFilterViewModel compensacionFilter) {
    	WhereParams params = new WhereParams();
        
    	String sql = searchByFilterQuery(compensacionFilter, params);
        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<CompensacionResultViewModel>(CompensacionResultViewModel.class));
	}
    
	@Override
	public CompensacionViewModel findById(Long idEmpleadoCompensacion) {
		WhereParams params = new WhereParams();
        
    	String sql = findByIdQuery(idEmpleadoCompensacion, params);
        return jdbcTemplate.queryForObject(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<CompensacionViewModel>(CompensacionViewModel.class));
	}
    
    private String searchByFilterQuery(CompensacionFilterViewModel compensacionFilter, WhereParams params) {
		
    	StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append(" EMPCOM.IdEmpleadoCompensacion AS idEmpleadoCompensacion, ");
        sql.append(" (EMP.ApellidoPaterno+' '+EMP.ApellidoMaterno+', '+EMP.Nombre) AS empleado, ");
        
        sql.append(" EMPCOM.Mes AS mes, ");

        sql.append(" EMPCOM.HorasTardanzaIngreso AS horasTardanzaIngreso, ");
        sql.append(" EMPCOM.HorasTardanzaSalida AS horasTardanzaSalida, ");

        sql.append(" EMPCOM.HorasDemoraAlmuerzo AS horasDemoraAlmuerzo, ");

        sql.append(" EMPCOM.HorasTrabajadas AS horasTrabajadas, ");
        sql.append(" EMPCOM.HorasPendientesTotal AS horasPendientesTotal ");

        sql.append(" FROM EmpleadoCompensacion EMPCOM ");
        sql.append(" LEFT JOIN Empleado EMP ON EMP.IdEmpleado = EMPCOM.IdEmpleado ");
             
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMP.IdEmpleado AND ((HISTORIAL.FechaInicio < getdate() AND HISTORIAL.FechaFin > getDate()) OR (HISTORIAL.FechaInicio < getdate() AND HISTORIAL.FechaFin IS NULL)) ");
        
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        
        sql.append(" where 1=1");
        
        sql.append(params.filter(" AND UN.IdUnidadDeNegocio = :idUnidadDeNegocio ", compensacionFilter.getIdUnidadDeNegocio()));
        sql.append(params.filter(" AND DEP.IdDepartamentoArea = :idDepartamentoArea ", compensacionFilter.getIdDepartamentoArea()));
        sql.append(params.filter(" AND PROY.IdProyecto = :idProyecto ", compensacionFilter.getIdProyecto()));
        
        sql.append(params.filter(" AND EMP.IdEmpleado = :idEmpleado ", compensacionFilter.getIdEmpleado()));
        
        sql.append(params.filter(" AND [dbo].[GetJefeInmediato] (EMP.IdEmpleado,HISTORIAL.IdHistorialLaboral) = :idJefe",compensacionFilter.getIdJefe()));
        
        return sql.toString();
	}
    
    private String findByIdQuery(Long idEmpleadoCompensacion, WhereParams params) {
		
    	StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append(" EMPCOM.IdEmpleadoCompensacion AS idEmpleadoCompensacion, ");
        
        sql.append(" EMPCOM.IdEmpleado AS idEmpleado, ");
        
        sql.append(" EMPCOM.Mes AS mes, ");

        sql.append(" EMPCOM.HorasTardanzaIngreso AS horasTardanzaIngreso, ");
        sql.append(" EMPCOM.HorasTardanzaSalida AS horasTardanzaSalida, ");

        sql.append(" EMPCOM.HorasDemoraAlmuerzo AS horasDemoraAlmuerzo, ");

        sql.append(" EMPCOM.HorasTrabajadas AS horasTrabajadas, ");
        sql.append(" EMPCOM.HorasPendientesTotal AS horasPendientesTotal, ");
        sql.append(" EMPCOM.HorarioHorasTrabajo AS horarioHorasTrabajo, ");
        
        sql.append(" EMPCOM.Inasistencias AS inasistencias, ");
        sql.append(" EMPCOM.Licencias AS licencias, ");
        sql.append(" EMPCOM.Vacaciones AS vacaciones, ");
        
        sql.append(" EMPCOM.HorasAdicionales AS horasAdicionales, ");
        sql.append(" EMPCOM.HorasCompensadas AS horasCompensadas ");

         
        sql.append(" FROM EmpleadoCompensacion EMPCOM ");
  
        sql.append(" where 1=1");
        
        sql.append(params.filter(" AND EMPCOM.IdEmpleadoCompensacion = :idEmpleadoCompensacion ", idEmpleadoCompensacion));
        
        return sql.toString();
	}

	@Override
	public List<CompensacionResultViewModel> busquedaRapidaCompensaciones(QuickFilterViewModel quickFilter) {
		WhereParams params = new WhereParams();
        
    	String sql = generarBusquedaRapidaCompensaciones((CompensacionQuickFilterViewModel)quickFilter, params);
        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<CompensacionResultViewModel>(CompensacionResultViewModel.class));
	}
	
	private String generarBusquedaRapidaCompensaciones(CompensacionQuickFilterViewModel quickFilter, WhereParams params) {
		
    	StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append(" EMPCOM.IdEmpleadoCompensacion AS idEmpleadoCompensacion, ");
        sql.append(" (EMP.ApellidoPaterno+' '+EMP.ApellidoMaterno+', '+EMP.Nombre) AS empleado, ");
        
        sql.append(" EMPCOM.Mes AS mes, ");

        sql.append(" EMPCOM.HorasTardanzaIngreso AS horasTardanzaIngreso, ");
        sql.append(" EMPCOM.HorasTardanzaSalida AS horasTardanzaSalida, ");

        sql.append(" EMPCOM.HorasDemoraAlmuerzo AS horasDemoraAlmuerzo, ");

        sql.append(" EMPCOM.HorasTrabajadas AS horasTrabajadas, ");
        sql.append(" EMPCOM.HorasPendientesMesActual AS horasPendientesMesActual ");

         
        sql.append(" FROM EmpleadoCompensacion EMPCOM ");
        sql.append(" LEFT JOIN Empleado EMP ON EMP.IdEmpleado = EMPCOM.IdEmpleado ");
             
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMP.IdEmpleado AND ((HISTORIAL.FechaInicio < getdate() AND HISTORIAL.FechaFin > getDate()) OR (HISTORIAL.FechaInicio < getdate() AND HISTORIAL.FechaFin IS NULL)) ");
        
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        
        sql.append(" where 1=1");
        sql.append(params.filter(" AND UPPER(EMP.Nombre) LIKE UPPER('%' + :value + '%') ", quickFilter.getValue()));
        sql.append(params.filter(" OR UPPER(EMP.ApellidoPaterno) LIKE UPPER('%' + :value + '%') ", quickFilter.getValue()));
        sql.append(params.filter(" OR UPPER(EMP.ApellidoMaterno) LIKE UPPER('%' + :value + '%') ", quickFilter.getValue()));        
        
        sql.append(params.filter(" AND [dbo].[GetJefeInmediato] (EMP.IdEmpleado,HISTORIAL.IdHistorialLaboral) = :idJefe",quickFilter.getIdJefe()));
        
        return sql.toString();
	}
    
}