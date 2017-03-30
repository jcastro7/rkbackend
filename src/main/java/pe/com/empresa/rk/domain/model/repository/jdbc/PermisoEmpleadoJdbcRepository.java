package pe.com.empresa.rk.domain.model.repository.jdbc;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.empresa.rk.support.WhereParams;
import pe.com.empresa.rk.view.model.PermisoEmpleadoQuickFilterViewModel;
import pe.com.empresa.rk.view.model.PermisoEmpleadoResultViewModel;
import pe.com.empresa.rk.view.model.QuickFilterViewModel;
import pe.com.empresa.rk.view.model.PermisoEmpleadoFilterViewModel;
import pe.com.empresa.rk.view.model.PermisoEmpleadoViewModel;

@Repository
public class PermisoEmpleadoJdbcRepository {
	
	@Autowired
    DataSource dataSource;

    private NamedParameterJdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

	public List<PermisoEmpleadoResultViewModel> busquedaPermisoEmpleado(PermisoEmpleadoFilterViewModel filterViewModel) {
		WhereParams params = new WhereParams();
        String sql = generarBusquedaPermisoEmpleado(filterViewModel, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<PermisoEmpleadoResultViewModel>(PermisoEmpleadoResultViewModel.class));
	}
	
	public List<PermisoEmpleadoViewModel> buscarPermisoEmpleadoPorMesCompensacion(PermisoEmpleadoFilterViewModel filterViewModel) {
		WhereParams params = new WhereParams();
        String sql = generarBuscarPermisoEmpleadoPorMesCompensacion(filterViewModel, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<PermisoEmpleadoViewModel>(PermisoEmpleadoViewModel.class));
	}
	
	private String generarBuscarPermisoEmpleadoPorMesCompensacion(PermisoEmpleadoFilterViewModel filter, WhereParams params) {
		StringBuilder sql = new StringBuilder();
        sql.append(" select PERMISO.IdPermisoEmpleado as idPermisoEmpleado, ");
        sql.append(" CONCAT(ATENDIDOPOR.ApellidoPaterno, ' ', ATENDIDOPOR.ApellidoMaterno, ', ', ATENDIDOPOR.Nombre) as jefeInmediato, ");
        sql.append(" PERMISO.Fecha as fecha, ") ;
        sql.append(" PERMISO.HoraInicio as horaInicio, ") ;
        sql.append(" PERMISO.HoraFin as horaFin, ") ;
        sql.append(" PERMISO.Horas as horas ") ;
        sql.append(" From PermisoEmpleado PERMISO ") ;
        sql.append(" LEFT JOIN PeriodoEmpleado PERIODO ON PERMISO.IdPeriodoEmpleado = PERIODO.IdPeriodoEmpleado ");
        sql.append(" LEFT JOIN Empleado ATENDIDOPOR ON PERMISO.IdAtendidoPor = ATENDIDOPOR.IdEmpleado ");
        
        sql.append(" Where 1=1 ");
        sql.append(params.filter(" AND PERIODO.IdEmpleado = :idEmpleado ", filter.getIdEmpleado()));
        sql.append(params.filter(" AND MONTH(PERMISO.Fecha) = :mes ", filter.getMes()));
        sql.append(params.filter(" AND Year(PERMISO.Fecha) = :anio ", filter.getAnio()));
        
        return sql.toString();
	}

	private String generarBusquedaPermisoEmpleado(PermisoEmpleadoFilterViewModel busquedaPermisoEmpleadoDto, WhereParams params) {
		StringBuilder sql = new StringBuilder();
        sql.append(" select distinct pe.IdPermisoEmpleado as idPermisoEmpleado, ");
        sql.append(" CONCAT(EMPLEADO.ApellidoPaterno, ' ', EMPLEADO.ApellidoMaterno, ', ', EMPLEADO.Nombre) as nombreEmpleado, ");
        sql.append(" MOTIVO.nombre as motivo, ");
        sql.append(" pe.IdAtendidoPor AS idAtendidoPor, ");
        sql.append(" pe.Razon as razon, ") ;
        sql.append(" pe.Fecha as fecha, ") ;
        sql.append(" pe.HoraInicio as horaInicio, ") ;
        sql.append(" pe.HoraFin as horaFin, ") ;
        sql.append(" usu.IdUsuario as idUsuario, ") ;
        sql.append(" pe.Horas as horas, ") ;
        sql.append("  CASE ");   
		sql.append("  WHEN (HISTORIAL.IdProyecto IS NOT NULL) THEN ep.ApellidoPaterno +' '+ep.ApellidoMaterno +', '+ep.Nombre "); 
		sql.append("  WHEN (HISTORIAL.IdProyecto IS NULL AND HISTORIAL.IdDepartamentoArea IS NOT NULL) THEN eda.ApellidoPaterno +' '+eda.ApellidoMaterno +', '+eda.Nombre "); 
		sql.append("  WHEN (HISTORIAL.IdProyecto IS NULL AND HISTORIAL.IdDepartamentoArea IS NULL AND HISTORIAL.IdUnidadDeNegocio IS NOT NULL) THEN eun.ApellidoPaterno +' '+eun.ApellidoMaterno +', '+eun.Nombre "); 
		sql.append("  ELSE 'No Asociado'  ");
		sql.append("  END AS nombreJefeInmediato, ");
        sql.append(" ESTADO.Nombre AS estado ");
        sql.append(" From PermisoEmpleado pe ") ;
        sql.append(" LEFT JOIN TablaGeneral ESTADO ON pe.Estado=ESTADO.Codigo and ESTADO.GRUPO='Permiso.Estado'");
        sql.append(" LEFT JOIN TablaGeneral MOTIVO ON pe.MOTIVO=MOTIVO.Codigo and MOTIVO.GRUPO='Permiso.Motivo'");
        sql.append(" LEFT JOIN PeriodoEmpleado PERIODO_EMPLEADO ON pe.IdPeriodoEmpleado = PERIODO_EMPLEADO.IdPeriodoEmpleado ");
        sql.append(" LEFT JOIN Empleado EMPLEADO ON PERIODO_EMPLEADO.IdEmpleado = EMPLEADO.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMPLEADO.IdEmpleado AND ((HISTORIAL.FechaInicio < getdate() AND HISTORIAL.FechaFin > getDate()) AND (HISTORIAL.FechaInicio < getdate() AND HISTORIAL.FechaFin IS NULL)) ");
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append(" LEFT JOIN Empleado ep on PROY.IdJefe = ep.IdEmpleado ");
		sql.append(" LEFT JOIN Empleado eda on DEP.IdJefe = eda.IdEmpleado ");
		sql.append(" LEFT JOIN Empleado eun on UN.IdJefe = eun.IdEmpleado ");
		sql.append(" LEFT JOIN Usuario usu on eun.IdEmpleado = usu.IdEmpleado ");
        sql.append(" Where 1=1 ");
        sql.append(params.filter(" AND pe.Estado = :estado ", busquedaPermisoEmpleadoDto.getEstado()));
        sql.append(params.filterDateDesde_US(" AND pe.Fecha  " , busquedaPermisoEmpleadoDto.getDesde()));
        sql.append(params.filterDateHasta_US(" AND pe.Fecha  ", busquedaPermisoEmpleadoDto.getHasta()));
        sql.append(params.filter(" AND UN.IdUnidadDeNegocio = :unidadNegocio ", busquedaPermisoEmpleadoDto.getUnidadNegocio()));
        sql.append(params.filter(" AND DEP.IdDepartamentoArea = :departamento ", busquedaPermisoEmpleadoDto.getDepartamento()));
        sql.append(params.filter(" AND PROY.IdProyecto = :proyecto ", busquedaPermisoEmpleadoDto.getProyecto()));
        sql.append(params.filter(" AND pe.IdPermisoEmpleado = :codigoPermiso ", busquedaPermisoEmpleadoDto.getCodigoPermiso()));
        sql.append(params.filter(" AND EMPLEADO.IdEmpleado = :idEmpleado ", busquedaPermisoEmpleadoDto.getIdEmpleado()));
        sql.append(params.filter(" AND ( "+
        		" (HISTORIAL.IdProyecto IS NOT NULL AND PROY.IdJefe = :idJefeInmediato)  OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NOT NULL AND DEP.IdJefe  = :idJefeInmediato)"+ 
        		" OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NULL AND HISTORIAL.IdUnidadDeNegocio IS NOT NULL AND UN.IdJefe = :idJefeInmediato) ) ", busquedaPermisoEmpleadoDto.getIdJefeInmediato()));
        
        sql.append(params.filter(" AND [dbo].[GetJefeInmediato] (EMPLEADO.IdEmpleado,HISTORIAL.IdHistorialLaboral) = :idJefe",busquedaPermisoEmpleadoDto.getIdJefe()));
        
        return sql.toString();
	}
	
	private String generarBusquedaRapidaPermisoEmpleado(PermisoEmpleadoQuickFilterViewModel busquedaPermisoEmpleadoDto, WhereParams params) {
		StringBuilder sql = new StringBuilder();
        sql.append(" select distinct pe.IdPermisoEmpleado as idPermisoEmpleado, ");
        sql.append(" CONCAT(EMPLEADO.ApellidoPaterno, ' ', EMPLEADO.ApellidoMaterno, ', ', EMPLEADO.Nombre) as nombreEmpleado, ");
        sql.append(" MOTIVO.nombre as motivo, ");
        sql.append(" pe.IdAtendidoPor AS idAtendidoPor, ");
        sql.append(" pe.Razon as razon, ") ;
        sql.append(" pe.Fecha as fecha, ") ;
        sql.append(" pe.HoraInicio as horaInicio, ") ;
        sql.append(" pe.HoraFin as horaFin, ") ;
        sql.append(" usu.IdUsuario as idUsuario, ") ;
        sql.append(" pe.Horas as horas, ") ;
        sql.append("  CASE ");   
		sql.append("  WHEN (HISTORIAL.IdProyecto IS NOT NULL) THEN ep.ApellidoPaterno +' '+ep.ApellidoMaterno +', '+ep.Nombre "); 
		sql.append("  WHEN (HISTORIAL.IdProyecto IS NULL AND HISTORIAL.IdDepartamentoArea IS NOT NULL) THEN eda.ApellidoPaterno +' '+eda.ApellidoMaterno +', '+eda.Nombre "); 
		sql.append("  WHEN (HISTORIAL.IdProyecto IS NULL AND HISTORIAL.IdDepartamentoArea IS NULL AND HISTORIAL.IdUnidadDeNegocio IS NOT NULL) THEN eun.ApellidoPaterno +' '+eun.ApellidoMaterno +', '+eun.Nombre "); 
		sql.append("  ELSE 'No Asociado'  ");
		sql.append("  END AS nombreJefeInmediato, ");
        sql.append(" CASE ");   
        sql.append(" WHEN  (pe.Estado='P') THEN 'Pendiente' ");
        sql.append(" WHEN  (pe.Estado='E') THEN 'Enviado' ");
        sql.append(" WHEN  (pe.Estado='A') THEN 'Aprobado' ");
        sql.append(" WHEN  (pe.Estado='R') THEN 'Rechazado' ");
        sql.append(" ELSE 'No Asociado' ");
		sql.append(" END AS estado ");
        sql.append(" From PermisoEmpleado pe ") ;
        sql.append(" LEFT JOIN TablaGeneral ESTADO ON pe.Estado=ESTADO.Codigo and ESTADO.GRUPO='Permiso.Estado'");
        sql.append(" LEFT JOIN TablaGeneral MOTIVO ON pe.MOTIVO=MOTIVO.Codigo and MOTIVO.GRUPO='Permiso.Motivo'");
        sql.append(" LEFT JOIN PeriodoEmpleado PERIODO_EMPLEADO ON pe.IdPeriodoEmpleado = PERIODO_EMPLEADO.IdPeriodoEmpleado ");
        sql.append(" LEFT JOIN Empleado EMPLEADO ON PERIODO_EMPLEADO.IdEmpleado = EMPLEADO.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMPLEADO.IdEmpleado AND ((HISTORIAL.FechaInicio < getdate() AND HISTORIAL.FechaFin > getDate()) OR (HISTORIAL.FechaInicio < getdate() AND HISTORIAL.FechaFin IS NULL)) ");
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append(" LEFT JOIN Empleado ep on PROY.IdJefe = ep.IdEmpleado ");
		sql.append(" LEFT JOIN Empleado eda on DEP.IdJefe = eda.IdEmpleado ");
		sql.append(" LEFT JOIN Empleado eun on UN.IdJefe = eun.IdEmpleado ");
        sql.append(" LEFT JOIN Usuario usu on eun.IdEmpleado = usu.IdEmpleado ");
        sql.append(" Where 1=1 ");
        
        sql.append(params.filter(" AND UPPER(EMPLEADO.Nombre) LIKE UPPER('%' + :value + '%') ", busquedaPermisoEmpleadoDto.getValue()));
        sql.append(params.filter(" OR UPPER(EMPLEADO.ApellidoPaterno) LIKE UPPER('%' + :value + '%') ", busquedaPermisoEmpleadoDto.getValue()));
        sql.append(params.filter(" OR UPPER(EMPLEADO.ApellidoMaterno) LIKE UPPER('%' + :value + '%') ", busquedaPermisoEmpleadoDto.getValue()));
        sql.append(params.filterDateDesde_US(" AND pe.Fecha ", busquedaPermisoEmpleadoDto.getDesde()));
        sql.append(params.filterDateHasta_US(" AND pe.Fecha ", busquedaPermisoEmpleadoDto.getHasta()));
        sql.append(params.filter(" AND ( "+
        		" (HISTORIAL.IdProyecto IS NOT NULL AND PROY.IdJefe = :idJefeInmediato)  OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NOT NULL AND DEP.IdJefe  = :idJefeInmediato)"+ 
        		" OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NULL AND HISTORIAL.IdUnidadDeNegocio IS NOT NULL AND UN.IdJefe = :idJefeInmediato) ) ", busquedaPermisoEmpleadoDto.getIdJefeInmediato()));
        sql.append(params.filter(" AND [dbo].[GetJefeInmediato] (EMPLEADO.IdEmpleado,HISTORIAL.IdHistorialLaboral) = :idJefe",busquedaPermisoEmpleadoDto.getIdEmpleado()));
        
        return sql.toString();
	}

	public List<PermisoEmpleadoResultViewModel> busquedaRapidaPermisoEmpleado(QuickFilterViewModel quickFilter) {
		WhereParams params = new WhereParams();
        String sql = generarBusquedaRapidaPermisoEmpleado((PermisoEmpleadoQuickFilterViewModel) quickFilter, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<PermisoEmpleadoResultViewModel>(PermisoEmpleadoResultViewModel.class));
	}

}
