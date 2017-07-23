package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.support.WhereParams;
import pe.com.tss.runakuna.util.DateUtil;
import pe.com.tss.runakuna.view.model.AccionViewModel;
import pe.com.tss.runakuna.view.model.HorarioEmpleadoDiaViewModel;
import pe.com.tss.runakuna.view.model.PeriodoEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.PermisoEmpleadoFilterViewModel;
import pe.com.tss.runakuna.view.model.PermisoEmpleadoQuickFilterViewModel;
import pe.com.tss.runakuna.view.model.PermisoEmpleadoRecuperacionViewModel;
import pe.com.tss.runakuna.view.model.PermisoEmpleadoResultViewModel;
import pe.com.tss.runakuna.view.model.PermisoEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.QuickFilterViewModel;

@Repository
public class PermisoEmpleadoJdbcRepository {
	
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
        sql.append(" select pe.IdPermisoEmpleado as idPermisoEmpleado, ");
        sql.append(" CONCAT(EMPLEADO.ApellidoPaterno, ' ', EMPLEADO.ApellidoMaterno, ', ', EMPLEADO.Nombre) as nombreEmpleado, ");
        sql.append(" MOTIVO.nombre as motivo, ");
        sql.append(" pe.IdAtendidoPor AS idAtendidoPor, ");
        sql.append(" pe.Razon as razon, ") ;
        sql.append(" pe.Fecha as fecha, ") ;
        sql.append(" pe.HoraInicio as horaInicio, ") ;
        sql.append(" pe.HoraFin as horaFin, ") ;
        sql.append(" usu.IdUsuario as idUsuario, ") ;
        sql.append(" pe.Horas as horas, ") ;
        sql.append("  ATENDIDOPOR.ApellidoPaterno +' '+ATENDIDOPOR.ApellidoMaterno +', '+ATENDIDOPOR.Nombre AS nombreJefeInmediato, ");
        sql.append(" ESTADO.Nombre AS estado ");
        sql.append(" From PermisoEmpleado pe ") ;
        sql.append(" LEFT JOIN Empleado ATENDIDOPOR ON ATENDIDOPOR.IdEmpleado = pe.IdAtendidoPor ");
        sql.append(" LEFT JOIN TablaGeneral ESTADO ON pe.Estado=ESTADO.Codigo and ESTADO.GRUPO='Permiso.Estado'");
        sql.append(" LEFT JOIN TablaGeneral MOTIVO ON pe.MOTIVO=MOTIVO.Codigo and MOTIVO.GRUPO='Permiso.Tipo'");
        sql.append(" LEFT JOIN PeriodoEmpleado PERIODO_EMPLEADO ON pe.IdPeriodoEmpleado = PERIODO_EMPLEADO.IdPeriodoEmpleado ");
        sql.append(" LEFT JOIN Empleado EMPLEADO ON PERIODO_EMPLEADO.IdEmpleado = EMPLEADO.IdEmpleado ");
        
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMPLEADO.IdEmpleado AND ((HISTORIAL.FechaInicio < '"+getdate+"' AND HISTORIAL.FechaFin > '"+getdate+"') AND (HISTORIAL.FechaInicio < '"+getdate+"' AND HISTORIAL.FechaFin IS NULL)) ");
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append(" LEFT JOIN Empleado ep on PROY.IdJefe = ep.IdEmpleado ");
		sql.append(" LEFT JOIN Empleado eda on DEP.IdJefe = eda.IdEmpleado ");
		sql.append(" LEFT JOIN Empleado eun on UN.IdJefe = eun.IdEmpleado ");
		sql.append(" LEFT JOIN Usuario usu on eun.IdEmpleado = usu.IdEmpleado ");
		
        sql.append(" Where 1=1 ");
        sql.append(params.filter(" AND pe.Estado = :estado ", busquedaPermisoEmpleadoDto.getEstado()));
        sql.append(params.filter(" AND pe.Motivo = :tipoPermiso ", busquedaPermisoEmpleadoDto.getTipoPermiso()));
        sql.append(params.filterDateDesde_US(" AND pe.Fecha  " , busquedaPermisoEmpleadoDto.getDesde()));
        sql.append(params.filterDateHasta_US(" AND pe.Fecha  ", busquedaPermisoEmpleadoDto.getHasta()));
        sql.append(params.filter(" AND UN.IdUnidadDeNegocio = :unidadNegocio ", busquedaPermisoEmpleadoDto.getUnidadNegocio()));
        sql.append(params.filter(" AND DEP.IdDepartamentoArea = :departamento ", busquedaPermisoEmpleadoDto.getDepartamento()));
        sql.append(params.filter(" AND PROY.IdProyecto = :proyecto ", busquedaPermisoEmpleadoDto.getProyecto()));
        sql.append(params.filter(" AND pe.IdPermisoEmpleado = :codigoPermiso ", busquedaPermisoEmpleadoDto.getCodigoPermiso()));
        sql.append(params.filter(" AND EMPLEADO.IdEmpleado = :idEmpleado ", busquedaPermisoEmpleadoDto.getIdEmpleado()));
        sql.append(params.filter(" AND pe.IdAtendidoPor = :idJefeInmediato ", busquedaPermisoEmpleadoDto.getIdJefeInmediato()));
        sql.append(" ORDER BY pe.Fecha DESC, EMPLEADO.Nombre DESC ");
        
        return sql.toString();
	}
	
	private String generarBusquedaRapidaPermisoEmpleado(PermisoEmpleadoQuickFilterViewModel busquedaPermisoEmpleadoDto, WhereParams params) {
		StringBuilder sql = new StringBuilder();
        sql.append(" select pe.IdPermisoEmpleado as idPermisoEmpleado, ");
        sql.append(" CONCAT(EMPLEADO.ApellidoPaterno, ' ', EMPLEADO.ApellidoMaterno, ', ', EMPLEADO.Nombre) as nombreEmpleado, ");
        sql.append(" MOTIVO.nombre as motivo, ");
        sql.append(" pe.IdAtendidoPor AS idAtendidoPor, ");
        sql.append(" pe.Razon as razon, ") ;
        sql.append(" pe.Fecha as fecha, ") ;
        sql.append(" pe.HoraInicio as horaInicio, ") ;
        sql.append(" pe.HoraFin as horaFin, ") ;
        sql.append(" usu.IdUsuario as idUsuario, ") ;
        sql.append(" pe.Horas as horas, ") ;
        sql.append("  ATENDIDOPOR.ApellidoPaterno +' '+ATENDIDOPOR.ApellidoMaterno +', '+ATENDIDOPOR.Nombre AS nombreJefeInmediato, ");
        sql.append(" CASE ");   
        sql.append(" WHEN  (pe.Estado='P') THEN 'Pendiente' ");
        sql.append(" WHEN  (pe.Estado='E') THEN 'Enviado' ");
        sql.append(" WHEN  (pe.Estado='A') THEN 'Aprobado' ");
        sql.append(" WHEN  (pe.Estado='R') THEN 'Rechazado' ");
        sql.append(" ELSE 'No Asociado' ");
		sql.append(" END AS estado ");
        sql.append(" From PermisoEmpleado pe ") ;
        sql.append(" LEFT JOIN Empleado ATENDIDOPOR ON ATENDIDOPOR.IdEmpleado = pe.IdAtendidoPor ");
        sql.append(" LEFT JOIN TablaGeneral ESTADO ON pe.Estado=ESTADO.Codigo and ESTADO.GRUPO='Permiso.Estado'");
        sql.append(" LEFT JOIN TablaGeneral MOTIVO ON pe.MOTIVO=MOTIVO.Codigo and MOTIVO.GRUPO='Permiso.Tipo'");
        sql.append(" LEFT JOIN PeriodoEmpleado PERIODO_EMPLEADO ON pe.IdPeriodoEmpleado = PERIODO_EMPLEADO.IdPeriodoEmpleado ");
        sql.append(" LEFT JOIN Empleado EMPLEADO ON PERIODO_EMPLEADO.IdEmpleado = EMPLEADO.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMPLEADO.IdEmpleado AND ((HISTORIAL.FechaInicio < '"+getdate+"' AND HISTORIAL.FechaFin > '"+getdate+"') AND (HISTORIAL.FechaInicio < '"+getdate+"' AND HISTORIAL.FechaFin IS NULL)) ");
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
        sql.append(params.filter(" AND pe.IdAtendidoPor = :idEmpleado ", busquedaPermisoEmpleadoDto.getIdEmpleado()));
        sql.append(" ORDER BY pe.Fecha DESC, EMPLEADO.Nombre DESC ");
//        sql.append(params.filter(" AND ( "+
//        		" (HISTORIAL.IdProyecto IS NOT NULL AND PROY.IdJefe = :idJefeInmediato)  OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NOT NULL AND DEP.IdJefe  = :idJefeInmediato)"+ 
//        		" OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NULL AND HISTORIAL.IdUnidadDeNegocio IS NOT NULL AND UN.IdJefe = :idJefeInmediato) ) ", busquedaPermisoEmpleadoDto.getIdJefeInmediato()));
//        sql.append(params.filter(" AND [dbo].[GetJefeInmediato] (EMPLEADO.IdEmpleado,HISTORIAL.IdHistorialLaboral) = :idJefe",busquedaPermisoEmpleadoDto.getIdEmpleado()));
        
        return sql.toString();
	}

	public List<PermisoEmpleadoResultViewModel> busquedaRapidaPermisoEmpleado(QuickFilterViewModel quickFilter) {
		WhereParams params = new WhereParams();
        String sql = generarBusquedaRapidaPermisoEmpleado((PermisoEmpleadoQuickFilterViewModel) quickFilter, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<PermisoEmpleadoResultViewModel>(PermisoEmpleadoResultViewModel.class));
	}

	public List<PermisoEmpleadoViewModel> verPermisoEmpleado(PeriodoEmpleadoViewModel periodoEmpleado) {
		WhereParams params = new WhereParams();
        String sql = generarVerPermisosEmpleado(periodoEmpleado, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<PermisoEmpleadoViewModel>(PermisoEmpleadoViewModel.class));

	}
	
	private String generarVerPermisosEmpleado(PeriodoEmpleadoViewModel periodoEmpleado, WhereParams params) {

		StringBuilder sql = new StringBuilder();
        sql.append(" SELECT DISTINCT ");
        sql.append(" PE.IdPermisoEmpleado AS idPermisoEmpleado, ");
        sql.append(" PE.IdPeriodoEmpleado AS idPeriodoEmpleado, ");
        sql.append(" PERM.Nombre AS nombreMotivo, ");
        sql.append(" PERIODO.Periodo AS periodo, ");
        sql.append(" PE.Motivo AS motivo, ");
        sql.append(" PE.Razon AS razon, ");
        sql.append(" PE.Fecha AS fecha, ");
        sql.append(" PE.HoraInicio AS horaInicio, ");
        sql.append(" PE.HoraFin AS horaFin, ");
        sql.append(" PE.RowVersion AS rowVersion, ");
        sql.append(" PE.Creador AS creador, ");
        sql.append(" PE.FechaCreacion AS fechaCreacion, ");
        sql.append(" PE.FechaActualizacion AS fechaActualizacion, ");
        sql.append(" PE.Actualizador AS actualizador, ");
        sql.append(" PE.Horas AS horas, ");
        sql.append(" PE.DiaEntero AS diaEntero, ");
        sql.append(" PE.Estado AS estado, ");
        sql.append(" ESTPERM.Nombre AS nombreEstado, ");
        sql.append(" PE.ComentarioResolucion AS comentarioResolucion, ");

        sql.append("  (EMP.ApellidoPaterno +' '+EMP.ApellidoMaterno+', '+EMP.Nombre) AS jefeInmediato, ");
		sql.append("  PE.IdAtendidoPor AS idAtendidoPor ");

        sql.append(" FROM PermisoEmpleado PE ");
//        sql.append(" LEFT JOIN PermisoEmpleadoRecuperacion pere on PE.IdPermisoEmpleado = pere.IdPermisoEmpleado ");
        sql.append(" LEFT JOIN PeriodoEmpleado PERIODO ON PE.IdPeriodoEmpleado = PERIODO.IdPeriodoEmpleado ");
        sql.append(" LEFT JOIN Empleado EMP ON EMP.IdEmpleado = PE.IdAtendidoPor ");
        sql.append(" LEFT JOIN TablaGeneral PERM ON PERM.Codigo=PE.Motivo AND PERM.Grupo = 'Permiso.Tipo' ");
        sql.append(" LEFT JOIN TablaGeneral ESTPERM ON ESTPERM.Codigo=PE.Estado AND ESTPERM.Grupo = 'Permiso.Estado' ");

        sql.append(" LEFT JOIN HistorialLaboral h ON h.IdEmpleado = EMP.IdEmpleado AND (h.FechaInicio<='"+getdate+"' AND h.FechaFin>='"+getdate+"') ");

        sql.append("  LEFT JOIN Proyecto p on p.IdProyecto = h.IdProyecto ");
		sql.append("  LEFT JOIN Empleado ep on p.IdJefe = ep.IdEmpleado ");
		sql.append("  LEFT JOIN DepartamentoArea da on da.IdDepartamentoArea = h.IdDepartamentoArea ");
		sql.append("  LEFT JOIN Empleado eda on da.IdJefe = eda.IdEmpleado ");
		sql.append("  LEFT JOIN UnidadDeNegocio un ON un.IdUnidadDeNegocio = h.IdUnidadDeNegocio ");
		sql.append("  LEFT JOIN Empleado eun on un.IdJefe = eun.IdEmpleado ");

        sql.append(" where PERIODO.IdEmpleado = "+periodoEmpleado.getIdEmpleado());
        sql.append(params.filter(" AND PE.IdPeriodoEmpleado = :idPeriodoEmpleado ", periodoEmpleado.getIdPeriodoEmpleado()));
        sql.append(" order by PE.Fecha desc ");

        return sql.toString();
    }

	public HorarioEmpleadoDiaViewModel obtenerHorarioEmpleadoDia(Long idHorarioEmpleado, String obtenerCodigoDia) {
		WhereParams params = new WhereParams();
        String sql = obtenerHorarioEmpleadoDiaQuery(idHorarioEmpleado,obtenerCodigoDia, params);

        return jdbcTemplate.queryForObject(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<HorarioEmpleadoDiaViewModel>(HorarioEmpleadoDiaViewModel.class));
	}

	private String obtenerHorarioEmpleadoDiaQuery(Long idHorarioEmpleado, String obtenerCodigoDia, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select h.Entrada as entrada, ");
		sql.append(" h.Salida as salida, ");
		sql.append(" h.TiempoAlmuerzo as tiempoAlmuerzo ");
		sql.append(" FROM HorarioEmpleadoDia h ");
		sql.append(" WHERE 1 = 1 ");
		sql.append(params.filter(" AND h.IdHorarioEmpleado =:idHorarioEmpleado ",idHorarioEmpleado));
		sql.append(params.filter(" AND h.diaSemana =:diaSemana ",obtenerCodigoDia));
		return sql.toString();
	}

	public List<PermisoEmpleadoRecuperacionViewModel> findPermisoRecuperacionByIdPermisoEmpleado(Long idPermisoEmpleado) {
		WhereParams params = new WhereParams();
        String sql = findPermisoRecuperacionByIdPermisoEmpleadoQuery(idPermisoEmpleado, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<PermisoEmpleadoRecuperacionViewModel>(PermisoEmpleadoRecuperacionViewModel.class));
	}

	private String findPermisoRecuperacionByIdPermisoEmpleadoQuery(Long idPermisoEmpleado, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select h.IdPermisoEmpleadoRecuperacion as idPermisoEmpleadoRecuperacion, ");
		sql.append(" h.IdPermisoEmpleado as idPermisoEmpleado, ");
		sql.append(" h.FechaRecuperacion as fechaRecuperacion, ");
		sql.append(" h.HoraInicio as horaInicio, ");
		sql.append(" h.HoraFin as horaFin, ");
		sql.append(" h.RowVersion AS rowVersion, ");
        sql.append(" h.Creador AS creador, ");
        sql.append(" h.FechaCreacion AS fechaCreacion, ");
        sql.append(" h.FechaActualizacion AS fechaActualizacion, ");
        sql.append(" h.Actualizador AS actualizador, ");
		sql.append(" h.Horas as horas ");
		sql.append(" FROM PermisoEmpleadoRecuperacion h ");
		sql.append(" WHERE 1 = 1 ");
		sql.append(params.filter(" AND h.IdPermisoEmpleado =:idPermisoEmpleado ",idPermisoEmpleado));
		return sql.toString();
	}
	
}
