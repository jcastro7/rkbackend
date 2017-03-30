package pe.com.empresa.rk.domain.model.repository.jdbc;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.empresa.rk.support.WhereParams;
import pe.com.empresa.rk.view.model.ReporteMarcacionFilterViewModel;
import pe.com.empresa.rk.view.model.ReporteMarcacionResultViewModel;
import pe.com.empresa.rk.view.model.ReporteMarcacionSubscriptorViewModel;
import pe.com.empresa.rk.view.model.ReporteMarcacionViewModel;

/**
 * Created by josediaz on 14/12/2016.
 */
@Repository
public class ReporteMarcacionJdbcRepository implements ReporteMarcacionRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReporteMarcacionJdbcRepository.class);

    private final NamedParameterJdbcTemplate jdbcTemplate;
    
    @Autowired
    DataSource dataSource;

    @Autowired
    ReporteMarcacionJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
	public List<ReporteMarcacionResultViewModel> obtenerReportes(ReporteMarcacionFilterViewModel dto) {
		WhereParams params = new WhereParams();
        String sql = generarBusquedaReporteMarcacion(dto, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<ReporteMarcacionResultViewModel>(ReporteMarcacionResultViewModel.class));
	}


	private String generarBusquedaReporteMarcacion(ReporteMarcacionFilterViewModel dto, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		
		if(dto.getTipoReporte().equals("D")){
			dto.setReporteDiario(1);
		}else if (dto.getTipoReporte().equals("M")) {
			dto.setReporteAcumulado(1);
		}
		
        sql.append(" SELECT distinct ");
        sql.append(" A.IdReporteMarcacion AS idReporteMarcacion, ");
        sql.append(" PROY.Nombre AS nombreProyecto, ");
        sql.append(" DEP.Nombre AS nombreDepartamento, ");
        sql.append(" UN.Nombre AS nombreUnidadNegocio, ");
        sql.append(" EMP.Nombre+' '+EMP.ApellidoPaterno+' '+EMP.ApellidoMaterno AS jefeInmediato, ");
        sql.append(" CASE WHEN  (A.Estado='A') THEN 'Activo' ELSE 'Inactivo' END as estado ");
        sql.append(" FROM ReporteMarcacion A ");
        sql.append(" LEFT JOIN ReporteMarcacionSubscriptor RMS ON A.idReporteMarcacion=RMS.idReporteMarcacion ");
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = A.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = A.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = A.IdUnidadDeNegocio ");
        sql.append(" LEFT JOIN Empleado EMP ON A.IdJefe = EMP.IdEmpleado ");
        sql.append(" WHERE 1=1 ");
        sql.append(params.filter(" AND A.Estado = :estado ",dto.getEstado()));
//        sql.append(params.filter(" AND A.TipoReporte = :tipoReporte ",dto.getTipoReporte()));
        sql.append(params.filter(" AND A.ReporteDiario = :reporteDiario ",dto.getReporteDiario()));
        sql.append(params.filter(" AND A.ReporteAcumulado = :reporteAcumulado ",dto.getReporteAcumulado()));
        sql.append(params.filter(" AND RMS.idEmpleado = :idEmpleado ",dto.getIdEmpleado()));
        
        return sql.toString();
	}

	public List<ReporteMarcacionSubscriptorViewModel> obtenerSubscriptores(ReporteMarcacionViewModel dto) {
		WhereParams params = new WhereParams();
        String sql = generarBusquedaSubscriptores(dto, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<ReporteMarcacionSubscriptorViewModel>(ReporteMarcacionSubscriptorViewModel.class));
		
	}

	private String generarBusquedaSubscriptores(ReporteMarcacionViewModel dto, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		
        sql.append(" SELECT distinct A.IdReporteMarcacion AS idReporteMarcacion, ");
        sql.append(" EMP.Nombre+' '+EMP.ApellidoPaterno+' '+EMP.ApellidoMaterno AS nombreEmpleado, ");
        sql.append(" RMS.RowVersion as rowVersion, ");
        sql.append(" RMS.FechaCreacion as fechaCreacion, ");
        sql.append(" RMS.Actualizador as actualizador, ");
        sql.append(" RMS.FechaActualizacion as fechaActualizacion, ");
        sql.append(" RMS.IdEmpleado AS idEmpleado ");
        sql.append(" FROM ReporteMarcacion A ");
        sql.append(" LEFT JOIN ReporteMarcacionSubscriptor RMS ON A.idReporteMarcacion=RMS.idReporteMarcacion ");
        sql.append(" LEFT JOIN Empleado EMP ON RMS.IdEmpleado = EMP.IdEmpleado ");
        sql.append(" WHERE 1=1 ");
        sql.append(params.filter(" AND A.IdReporteMarcacion = :idReporteMarcacion ",dto.getIdReporteMarcacion()));
        
        return sql.toString();
	}
	
	@Override
	public List<ReporteMarcacionViewModel> obtenerReportesJob(ReporteMarcacionFilterViewModel reporteMarcacionDto) {
		WhereParams params = new WhereParams();
        String sql = generarBusquedaReporteMarcacionJob(reporteMarcacionDto, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<ReporteMarcacionViewModel>(ReporteMarcacionViewModel.class));
	}
	
	private String generarBusquedaReporteMarcacionJob(ReporteMarcacionFilterViewModel dto, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		
		if(dto.getTipoReporte().equals("D")){
			dto.setReporteDiario(1);
		}else if (dto.getTipoReporte().equals("M")) {
			dto.setReporteAcumulado(1);
		}
		
        sql.append(" SELECT distinct ");
        sql.append(" A.IdReporteMarcacion AS idReporteMarcacion, ");
        sql.append(" A.IdEmpresa AS idEmpresa, ");
        sql.append(" A.IdUnidadDeNegocio AS idUnidadDeNegocio, ");
        sql.append(" PROY.IdProyecto AS idproyecto, ");
        sql.append(" DEP.IdDepartamentoArea AS idDepartamentoArea, ");
        sql.append(" PROY.Nombre AS nombreProyecto, ");
        sql.append(" DEP.Nombre AS nombreDepartamento, ");
        sql.append(" UN.Nombre AS nombreUnidadNegocio, ");
        sql.append(" A.IdJefe AS idJefeProyecto, ");
        sql.append(" A.ReporteDiario AS reporteDiario, ");
        sql.append(" A.ReporteAcumulado AS reporteAcumulado, ");
        sql.append(" EMP.Nombre+' '+EMP.ApellidoPaterno+' '+EMP.ApellidoMaterno AS jefeInmediato, ");
        sql.append(" A.estado AS codigo,");
        sql.append(" CASE WHEN  (A.Estado='A') THEN 'Activo' ELSE 'Inactivo' END as estado ");
        sql.append(" FROM ReporteMarcacion A ");
        sql.append(" LEFT JOIN ReporteMarcacionSubscriptor RMS ON A.idReporteMarcacion=RMS.idReporteMarcacion ");
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = A.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = A.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = A.IdUnidadDeNegocio ");
        sql.append(" LEFT JOIN Empleado EMP ON A.IdJefe = EMP.IdEmpleado ");
        sql.append(" WHERE 1=1 ");
        sql.append(params.filter(" AND A.Estado = :estado ",dto.getEstado()));
        sql.append(params.filter(" AND A.ReporteDiario = :reporteDiario ",dto.getReporteDiario()));
        sql.append(params.filter(" AND A.ReporteAcumulado = :reporteAcumulado ",dto.getReporteAcumulado()));
        sql.append(params.filter(" AND RMS.idEmpleado = :idEmpleado ",dto.getIdEmpleado()));
        
        return sql.toString();
	}

	
}
