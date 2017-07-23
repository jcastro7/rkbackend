package pe.com.tss.runakuna.domain.model.repository.jdbc;


import java.math.BigDecimal;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.domain.model.entities.Marcacion;
import pe.com.tss.runakuna.domain.model.repository.jpa.MarcacionJpaRepository;
import pe.com.tss.runakuna.support.WhereParams;
import pe.com.tss.runakuna.util.DateUtil;
import pe.com.tss.runakuna.view.model.HorasExtraEmpleadoResultViewModel;
import pe.com.tss.runakuna.view.model.IndicadorJefeResultViewModel;
import pe.com.tss.runakuna.view.model.IndicadorRRHHResultViewModel;
import pe.com.tss.runakuna.view.model.IndicadorVacacionesResultViewModel;
import pe.com.tss.runakuna.view.model.LicenciaEmpleadoResultViewModel;
import pe.com.tss.runakuna.view.model.MarcacionDashboardEmpleadoFilterViewModel;
import pe.com.tss.runakuna.view.model.MarcacionDashboardFilterViewModel;
import pe.com.tss.runakuna.view.model.MarcacionDashboardResultViewModel;
import pe.com.tss.runakuna.view.model.MarcacionResultViewModel;
import pe.com.tss.runakuna.view.model.MarcacionesMensualesFilterViewModel;
import pe.com.tss.runakuna.view.model.MarcacionesMensualesViewModel;
import pe.com.tss.runakuna.view.model.PermisoEmpleadoResultViewModel;
import pe.com.tss.runakuna.view.model.PieChartDataResultViewModel;
import pe.com.tss.runakuna.view.model.VacacionResultViewModel;

@Repository
public class DashboardJdbcRepository {

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
    
    @Autowired
    MarcacionJpaRepository marcacionJpaRepository;

    /**
	 * Busqueda Marcacion Dashboard RRHH
	 * @param filterViewModel
	 * @return
	 */
	public List<MarcacionDashboardResultViewModel> busquedaMarcacionesDashboardRRHH(MarcacionDashboardFilterViewModel filterViewModel) {
		WhereParams params = new WhereParams();
		if(filterViewModel.getTipoMarcacion().equals("0") || filterViewModel.getTipoMarcacion().equals("1") || filterViewModel.getTipoMarcacion().equals("2") || filterViewModel.getTipoMarcacion().equals("3")){
			String sql = generarVerBusquedaMarcacionesDashboardRRHHTipoMarcacion(filterViewModel, params);
			return jdbcTemplate.query(sql,
	                params.getParams(), new BeanPropertyRowMapper<>(MarcacionDashboardResultViewModel.class));
		}

        String sql = generarVerBusquedaMarcacionesDashboardRRHH(filterViewModel, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<>(MarcacionDashboardResultViewModel.class));
	}
	
	public List<MarcacionDashboardResultViewModel> busquedaMarcacionesDashboardRRHHAll(MarcacionDashboardFilterViewModel filterViewModel) {
		WhereParams params = new WhereParams();

        String sql = generarVerBusquedaMarcacionesDashboardRRHH(filterViewModel, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<>(MarcacionDashboardResultViewModel.class));
	}

	private String generarVerBusquedaMarcacionesDashboardRRHHTipoMarcacion(
			MarcacionDashboardFilterViewModel filterViewModel, WhereParams params) {
		StringBuilder sql = new StringBuilder();

        sql.append(" SELECT distinct ");
        sql.append(" MAR.Fecha AS fecha, ");
        sql.append(" MAR.HoraIngreso AS horaIngreso, ");
        sql.append(" MAR.HoraInicioAlmuerzo AS horaInicioAlmuerzo, ");
        sql.append(" MAR.HoraFinAlmuerzo AS horaFinAlmuerzo, ");
        sql.append(" MAR.HoraSalida AS horaSalida, ");

        sql.append(" MAR.DemoraEntrada AS demoraEntrada, ");
        sql.append(" MAR.DemoraAlmuerzo AS demoraAlmuerzo, ");
        sql.append(" MAR.DemoraSalida AS demoraSalida, ");
        sql.append(" MAR.HoraIngresoHorario AS horaIngresoHorario, ");
        sql.append(" MAR.HoraSalidaHorario AS horaSalidaHorario, ");
                
        sql.append(" ESTMAR.Nombre AS estadoMarcacion, ");
        
        sql.append(" EMP.ApellidoPaterno+' '+EMP.ApellidoMaterno+', '+EMP.Nombre AS nombreCompletoEmpleado, ");
        sql.append(" EMP.Codigo AS codigoEmpleado ");
        sql.append(" FROM Marcacion MAR ");
        sql.append(" LEFT JOIN Empleado EMP ON EMP.IdEmpleado = MAR.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMP.IdEmpleado ");

        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append(" LEFT JOIN TablaGeneral ESTMAR ON MAR.Estado= ESTMAR.Codigo AND ESTMAR.Grupo = 'Marcaciones.Estado' ");
        
        sql.append(" WHERE 1=1 ");

        sql.append(params.filter(" AND UN.IdUnidadDeNegocio = :unidadNegocio ", filterViewModel.getUnidadNegocio()));
        sql.append(params.filter(" AND DEP.IdDepartamentoArea = :departamento ", filterViewModel.getDepartamento()));
        sql.append(params.filter(" AND PROY.IdProyecto = :proyecto ", filterViewModel.getProyecto()));

        sql.append(params.filter(" AND MAR.Fecha = :fecha" , filterViewModel.getFecha()));
        //CLICL PIECHART RRHH
        if("0".equals(filterViewModel.getTipoMarcacion())){
        	// 0 = Nro. De Empleados que marcaron a tiempo',
        	sql.append(" AND MAR.DemoraEntrada <= 0 AND MAR.HoraIngreso IS NOT NULL AND MAR.EsPersonaDeConfianza = 0 ");
        }
        if("1".equals(filterViewModel.getTipoMarcacion())){
        	// 1 = 'Nro. De Empleados que marcaron fuera de tiempo'
        	sql.append(" AND MAR.HoraIngreso is NOT NULL AND MAR.DemoraEntrada > 0 AND MAR.EsPersonaDeConfianza = 0 ");
        }
        if("2".equals(filterViewModel.getTipoMarcacion())){
        	// 2 = 'Nro. De Empleados sin marcaciones';
        	sql.append(" AND MAR.HoraIngreso IS NULL AND MAR.EsPersonaDeConfianza = 0 ");
        }
        if("3".equals(filterViewModel.getTipoMarcacion())){
        	// 3 = 'Nro. De Personal Confianza';
        	sql.append(" AND MAR.EsPersonaDeConfianza = 1 ");
        }
        sql.append(" Order by nombreCompletoEmpleado asc,MAR.Fecha desc ");

		return sql.toString();
	}

	private String generarVerBusquedaMarcacionesDashboardRRHH(MarcacionDashboardFilterViewModel busquedaMarcacionDto,WhereParams params) {
		StringBuilder sql = new StringBuilder();

        sql.append(" SELECT distinct ");
        sql.append(" MAR.Fecha AS fecha, ");
        sql.append(" MAR.HoraIngreso AS horaIngreso, ");
        sql.append(" MAR.HoraInicioAlmuerzo AS horaInicioAlmuerzo, ");
        sql.append(" MAR.HoraFinAlmuerzo AS horaFinAlmuerzo, ");
        sql.append(" MAR.HoraSalida AS horaSalida, ");

        sql.append(" MAR.DemoraEntrada AS demoraEntrada, ");
               
        sql.append(" MAR.DemoraAlmuerzo AS demoraAlmuerzo, ");
        sql.append(" MAR.DemoraSalida AS demoraSalida, ");
        sql.append(" MAR.HoraIngresoHorario AS horaIngresoHorario, ");
        sql.append(" MAR.HoraSalidaHorario AS horaSalidaHorario, ");
        
        sql.append(" ESTMAR.Nombre AS estadoMarcacion, ");

        sql.append(" EMP.ApellidoPaterno+' '+EMP.ApellidoMaterno+', '+EMP.Nombre AS nombreCompletoEmpleado, ");
        sql.append(" EMP.Codigo AS codigoEmpleado ");
        sql.append(" FROM Marcacion MAR ");
        sql.append(" LEFT JOIN Empleado EMP ON EMP.IdEmpleado = MAR.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMP.IdEmpleado ");

        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        
        sql.append(" LEFT JOIN TablaGeneral ESTMAR ON MAR.Estado= ESTMAR.Codigo AND ESTMAR.Grupo = 'Marcaciones.Estado' ");
        sql.append(" WHERE 1=1 ");

        sql.append(params.filter(" AND UN.IdUnidadDeNegocio = :unidadNegocio ", busquedaMarcacionDto.getUnidadNegocio()));	
        sql.append(params.filter(" AND DEP.IdDepartamentoArea = :departamento ", busquedaMarcacionDto.getDepartamento()));
        sql.append(params.filter(" AND PROY.IdProyecto = :proyecto ", busquedaMarcacionDto.getProyecto()));

        sql.append(params.filter(" AND MAR.Fecha = :fecha" , busquedaMarcacionDto.getFecha()));
        sql.append(" Order by nombreCompletoEmpleado asc,MAR.Fecha desc ");

		return sql.toString();
	}

	public List<PieChartDataResultViewModel> busquedaPieChartDashboardRHHH(MarcacionDashboardFilterViewModel busquedaMarcacionDto) {
		WhereParams params = new WhereParams();
        String sql = busquedaPieChartDashboardRRHH(busquedaMarcacionDto, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<>(PieChartDataResultViewModel.class));
	}

	private String busquedaPieChartDashboardRRHH(MarcacionDashboardFilterViewModel busquedaMarcacionDto,WhereParams params) {
		StringBuilder sql = new StringBuilder();

		sql.append(" Select ");
		sql.append(" distinct nroEmpleadosMarcacionFueraTiempo = (select count(DISTINCT(prr.IdEmpleado)) ");
		sql.append(" FROM Marcacion prr LEFT JOIN Empleado EMP ON EMP.IdEmpleado = prr.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMP.IdEmpleado ");
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append(" WHERE 1=1 ");
        sql.append(params.filter(" AND UN.IdUnidadDeNegocio = :unidadNegocio ", busquedaMarcacionDto.getUnidadNegocio()));
        sql.append(params.filter(" AND DEP.IdDepartamentoArea = :departamento ", busquedaMarcacionDto.getDepartamento()));
        sql.append(params.filter(" AND PROY.IdProyecto = :proyecto ", busquedaMarcacionDto.getProyecto()));
        sql.append(" AND prr.HoraIngreso is NOT NULL ");
        sql.append(" AND prr.DemoraEntrada > 0 ");
        sql.append(" AND prr.EsPersonaDeConfianza = 0 ");
		sql.append(params.filter(" AND prr.Fecha = :fecha ", busquedaMarcacionDto.getFecha()));


		sql.append(" ),nroEmpleadosMarcacionTiempo = (select count(DISTINCT(prr.IdEmpleado)) ");
		sql.append(" FROM Marcacion prr LEFT JOIN Empleado EMP ON EMP.IdEmpleado = prr.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMP.IdEmpleado ");
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append(" WHERE 1=1 ");
		sql.append(params.filter(" AND UN.IdUnidadDeNegocio = :unidadNegocio ", busquedaMarcacionDto.getUnidadNegocio()));
        sql.append(params.filter(" AND DEP.IdDepartamentoArea = :departamento ", busquedaMarcacionDto.getDepartamento()));
		sql.append(params.filter(" AND PROY.IdProyecto = :proyecto ", busquedaMarcacionDto.getProyecto()));
		sql.append(" AND prr.DemoraEntrada <= 0 AND prr.HoraIngreso IS NOT NULL AND prr.EsPersonaDeConfianza = 0 ");
        sql.append(params.filter(" AND prr.Fecha = :fecha ", busquedaMarcacionDto.getFecha()));
        
        sql.append(" ),nroPersonalConfianza = (select count(DISTINCT(prr.IdEmpleado)) ");
		sql.append(" FROM Marcacion prr LEFT JOIN Empleado EMP ON EMP.IdEmpleado = prr.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMP.IdEmpleado ");
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append(" WHERE 1=1 ");
		sql.append(params.filter(" AND UN.IdUnidadDeNegocio = :unidadNegocio ", busquedaMarcacionDto.getUnidadNegocio()));
        sql.append(params.filter(" AND DEP.IdDepartamentoArea = :departamento ", busquedaMarcacionDto.getDepartamento()));
		sql.append(params.filter(" AND PROY.IdProyecto = :proyecto ", busquedaMarcacionDto.getProyecto()));
		sql.append(" AND prr.EsPersonaDeConfianza = 1 ");
        sql.append(params.filter(" AND prr.Fecha = :fecha ", busquedaMarcacionDto.getFecha()));

        sql.append(" ),nroEmpleadosSinMarcacion = (select count(DISTINCT(prr.IdEmpleado)) ");
        sql.append(" FROM Marcacion prr LEFT JOIN Empleado EMP ON EMP.IdEmpleado = prr.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMP.IdEmpleado ");
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append(" WHERE 1=1 ");
        sql.append(params.filter(" AND UN.IdUnidadDeNegocio = :unidadNegocio ", busquedaMarcacionDto.getUnidadNegocio()));
        sql.append(params.filter(" AND DEP.IdDepartamentoArea = :departamento ", busquedaMarcacionDto.getDepartamento()));
        sql.append(params.filter(" AND PROY.IdProyecto = :proyecto ", busquedaMarcacionDto.getProyecto()));
        sql.append(" AND prr.HoraIngreso IS NULL ");
        sql.append(" AND prr.EsPersonaDeConfianza = 0 ");
        sql.append(params.filter(" AND prr.Fecha = :fecha ", busquedaMarcacionDto.getFecha()));
        sql.append(" ) ");

		return sql.toString();
	}

	public List<PermisoEmpleadoResultViewModel> searchPermisoDashboardJefe(MarcacionDashboardFilterViewModel busquedaMarcacionDto) {
		WhereParams params = new WhereParams();
        String sql = searchPermisoDashboardJefeQuery(busquedaMarcacionDto, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<PermisoEmpleadoResultViewModel>(PermisoEmpleadoResultViewModel.class));
	}

	private String searchPermisoDashboardJefeQuery(MarcacionDashboardFilterViewModel busquedaPermisoEmpleadoDto,
			WhereParams params) {
		StringBuilder sql = new StringBuilder();
        sql.append(" select distinct pe.IdPermisoEmpleado as idPermisoEmpleado, ");
        sql.append(" CONCAT(EMPLEADO.ApellidoPaterno, ' ', EMPLEADO.ApellidoMaterno, ', ', EMPLEADO.Nombre) as nombreEmpleado, ");
        sql.append(" MOTIVO.nombre as motivo, ");
        sql.append(" pe.Fecha as fecha, ") ;
        sql.append(" pe.HoraInicio as horaInicio, ") ;
        sql.append(" pe.HoraFin as horaFin, ") ;
        sql.append(" pe.Horas as horas, ") ;
        sql.append(" CASE ");
        sql.append(" WHEN  (pe.Estado='P') THEN 'Pendiente' ");
        sql.append(" WHEN  (pe.Estado='E') THEN 'Enviado' ");
        sql.append(" WHEN  (pe.Estado='A') THEN 'Aprobado' ");
        sql.append(" WHEN  (pe.Estado='R') THEN 'Rechazao' ");
        sql.append(" ELSE 'No Asociado' ");
		sql.append(" END AS estado ");
        sql.append(" From PermisoEmpleado pe ") ;
        sql.append(" LEFT JOIN TablaGeneral ESTADO ON pe.Estado=ESTADO.Codigo and ESTADO.GRUPO='Permiso.Estado'");
        sql.append(" LEFT JOIN TablaGeneral MOTIVO ON pe.MOTIVO=MOTIVO.Codigo and MOTIVO.GRUPO='Permiso.Tipo'");
        sql.append(" LEFT JOIN PeriodoEmpleado PERIODO_EMPLEADO ON pe.IdPeriodoEmpleado = PERIODO_EMPLEADO.IdPeriodoEmpleado ");
        sql.append(" LEFT JOIN Empleado EMPLEADO ON PERIODO_EMPLEADO.IdEmpleado = EMPLEADO.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMPLEADO.IdEmpleado AND (HISTORIAL.FechaInicio < '"+getdate+"' OR HISTORIAL.FechaFin > '"+getdate+"') ");
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append(" LEFT JOIN Empleado ep on PROY.IdJefe = ep.IdEmpleado ");
		sql.append(" LEFT JOIN Empleado eda on DEP.IdJefe = eda.IdEmpleado ");
		sql.append(" LEFT JOIN Empleado eun on UN.IdJefe = eun.IdEmpleado ");
        sql.append(" Where 1=1 ");
        sql.append(params.filter(" AND pe.IdAtendidoPor = :idEmpleado ",busquedaPermisoEmpleadoDto.getIdEmpleado()));
//        sql.append(" AND MONTH(pe.Fecha) = MONTH('"+getdate+"') AND YEAR(pe.Fecha) = YEAR('"+getdate+"') ");
        sql.append(" AND pe.Estado='E' ");
        sql.append(" ORDER BY nombreEmpleado");

        return sql.toString();
	}

	public List<VacacionResultViewModel> searchVacacionesDashboardJefe(
			MarcacionDashboardFilterViewModel busquedaVacacionesEmpleadoDto) {
		WhereParams params = new WhereParams();
        String sql = searchVacacionesDashboardJefeQuery(busquedaVacacionesEmpleadoDto, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<>(VacacionResultViewModel.class));
	}

	private String searchVacacionesDashboardJefeQuery(MarcacionDashboardFilterViewModel filterViewModel,
			WhereParams params) {
		StringBuilder sql = new StringBuilder();
        sql.append(" SELECT distinct va.IdVacacion AS idVacacion, ");
        sql.append(" EMPLEADO.IdEmpleado AS idEmpleado, ");
        sql.append(" CONCAT(EMPLEADO.ApellidoPaterno, ' ', EMPLEADO.ApellidoMaterno, ', ', EMPLEADO.Nombre) as nombreEmpleado, ");
        sql.append(" va.FechaInicio as fechaInicio, ");
        sql.append(" va.FechaFin as fechaFin, ");
        sql.append(" va.DiasCalendarios as diasCalendarios, ") ;
        sql.append(" va.DiasHabiles as diasHabiles, ") ;
        sql.append(" CASE ");
        sql.append(" WHEN  (va.Estado='P') THEN 'Pendiente' ");
        sql.append(" WHEN  (va.Estado='E') THEN 'Enviado' ");
        sql.append(" WHEN  (va.Estado='A') THEN 'Aprobado' ");
        sql.append(" WHEN  (va.Estado='R') THEN 'Rechazado' ");
        sql.append(" ELSE 'No Asociado' ");
		sql.append(" END AS estado ");
        sql.append(" from Vacacion va ") ;
        sql.append(" LEFT JOIN TablaGeneral ESTADO ON va.Estado=ESTADO.Codigo and ESTADO.GRUPO='Vacaciones.Estado'");
        sql.append(" LEFT JOIN PeriodoEmpleado PERIODO_EMPLEADO ON va.IdPeriodoEmpleado = PERIODO_EMPLEADO.IdPeriodoEmpleado ");
        sql.append(" LEFT JOIN Empleado EMPLEADO ON PERIODO_EMPLEADO.IdEmpleado = EMPLEADO.IdEmpleado ");
        sql.append(" LEFT JOIN Empleado ATENDIDO ON va.IdAtendidoPor = ATENDIDO.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMPLEADO.IdEmpleado AND ((HISTORIAL.FechaInicio < '"+getdate+"' AND HISTORIAL.FechaFin > '"+getdate+"') OR (HISTORIAL.FechaInicio < '"+getdate+"' AND HISTORIAL.FechaFin IS NULL)) ");
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append("  LEFT JOIN Empleado ep on PROY.IdJefe = ep.IdEmpleado ");
		sql.append("  LEFT JOIN Empleado eda on DEP.IdJefe = eda.IdEmpleado ");
		sql.append("  LEFT JOIN Empleado eun on UN.IdJefe = eun.IdEmpleado ");
        sql.append(" where 1=1 ");
        sql.append(params.filter(" AND va.IdAtendidoPor = :idEmpleado ",filterViewModel.getIdEmpleado()));
//        sql.append(" AND MONTH(va.FechaCreacion) = MONTH('"+getdate+"') and YEAR(va.FechaCreacion) = YEAR('"+getdate+"') ");
        sql.append(" AND va.Estado='E' ");
//        sql.append(params.filter(" AND ( "+
//        		" (HISTORIAL.IdProyecto IS NOT NULL AND PROY.IdJefe = :idJefeInmediato)  OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NOT NULL AND DEP.IdJefe  = :idJefeInmediato)"+
//        		" OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NULL AND HISTORIAL.IdUnidadDeNegocio IS NOT NULL AND UN.IdJefe = :idJefeInmediato) ) ", filterViewModel.getIdEmpleado()));
        sql.append(" ORDER BY nombreEmpleado");

        return sql.toString();
	}

	public List<HorasExtraEmpleadoResultViewModel> searchHorasExtrasDashboardJefe(
			MarcacionDashboardFilterViewModel marcacionDashboardEmpleadoDto) {
		WhereParams params = new WhereParams();
        String sql = searchHorasExtrasDashboardJefeQuery(marcacionDashboardEmpleadoDto, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<>(HorasExtraEmpleadoResultViewModel.class));
	}
	
	public List<MarcacionResultViewModel> searchMarcacionesDashboardJefe(
			MarcacionDashboardFilterViewModel marcacionDashboardEmpleadoDto) {
		WhereParams params = new WhereParams();
        String sql = searchMarcacionesDashboardJefeQuery(marcacionDashboardEmpleadoDto, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<>(MarcacionResultViewModel.class));
	}
	
	public List<LicenciaEmpleadoResultViewModel> searchLicenciasDashboardJefe(
			MarcacionDashboardFilterViewModel marcacionDashboardEmpleadoDto) {
		WhereParams params = new WhereParams();
        String sql = searchLicenciasDashboardJefeQuery(marcacionDashboardEmpleadoDto, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<>(LicenciaEmpleadoResultViewModel.class));
	}

	private String searchHorasExtrasDashboardJefeQuery(MarcacionDashboardFilterViewModel filterViewModel, WhereParams params) {

		StringBuilder sql = new StringBuilder();
        sql.append(" SELECT distinct he.IdHorasExtra AS idHorasExtra, ");
        sql.append(" he.HoraSalidaSolicitado AS horaSalidaSolicitado, ");
        sql.append(" he.HoraInicioSolicitado AS horaInicioSolicitado, ");
        sql.append(" CONCAT(EMPLEADO.ApellidoPaterno, ' ', EMPLEADO.ApellidoMaterno, ', ', EMPLEADO.Nombre) as nombreEmpleado, ");
        sql.append(" he.Fecha as fecha, ");
        sql.append(" he.HorasExtra as horasExtra, ");
        sql.append(" he.HorasSolicitadas as horasSolicitadas, ");
        sql.append(" he.HorasNoCompensables AS horasNoCompensables, ");
        sql.append(" ESTADO.Nombre AS estado ");
        sql.append(" from HorasExtra he ") ;
        sql.append(" LEFT JOIN TablaGeneral ESTADO ON he.Estado=ESTADO.Codigo and ESTADO.GRUPO='HorasExtra.Estado'");
        sql.append(" LEFT JOIN Empleado EMPLEADO ON he.IdEmpleado = EMPLEADO.IdEmpleado ");
        sql.append(" LEFT JOIN Empleado ATENDIDO ON he.IdAtendidoPor = ATENDIDO.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = he.IdEmpleado AND ((HISTORIAL.FechaInicio < '"+getdate+"' AND HISTORIAL.FechaFin > '"+getdate+"') OR (HISTORIAL.FechaInicio < '"+getdate+"' AND HISTORIAL.FechaFin IS NULL)) ");
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append("  LEFT JOIN Empleado ep on PROY.IdJefe = ep.IdEmpleado ");
		sql.append("  LEFT JOIN Empleado eda on DEP.IdJefe = eda.IdEmpleado ");
		sql.append("  LEFT JOIN Empleado eun on UN.IdJefe = eun.IdEmpleado ");
        sql.append(" where 1=1 ");
        sql.append(params.filter(" AND he.IdAtendidoPor = :idEmpleado ",filterViewModel.getIdEmpleado()));
//        sql.append(" AND MONTH(he.Fecha) = MONTH('"+getdate+"') AND YEAR(he.Fecha) = YEAR('"+getdate+"') ");
        sql.append(" AND he.Estado='E' ");
        sql.append(" ORDER BY nombreEmpleado");

        return sql.toString();

	}
	
	private String searchMarcacionesDashboardJefeQuery(MarcacionDashboardFilterViewModel filterViewModel, WhereParams params) {

		StringBuilder sql = new StringBuilder();

        sql.append(" SELECT distinct ");
        sql.append(" MAR.IdMarcacion AS idMarcacion, ");
        sql.append(" MAR.Fecha AS fecha, ");
        sql.append(" MAR.HoraIngreso AS horaIngreso, ");
        sql.append(" MAR.HoraInicioAlmuerzo AS horaInicioAlmuerzo, ");
        sql.append(" MAR.HoraFinAlmuerzo AS horaFinAlmuerzo, ");
        sql.append(" MAR.HoraSalida AS horaSalida, ");

        sql.append(" ESTMAR.Nombre AS estado, ");
        sql.append(" CASE ");
        sql.append(" WHEN (SELECT count(*) FROM SolicitudCambioMarcacion SOL WHERE SOL.Estado='P' AND SOL.IdMarcacion = MAR.IdMarcacion)>0 THEN 'Si' ");
        sql.append(" ELSE 'No' ");
        sql.append(" END AS solicitudCambio, ");

        sql.append(" (EMP.ApellidoPaterno+' '+EMP.ApellidoMaterno+', '+EMP.Nombre) AS nombreCompletoEmpleado ");

        sql.append(" FROM Marcacion MAR ");
        sql.append(" LEFT JOIN Empleado EMP ON EMP.IdEmpleado = MAR.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMP.IdEmpleado AND (HISTORIAL.FechaInicio <= MAR.Fecha AND ( HISTORIAL.FechaFin IS NULL OR HISTORIAL.FechaFin>= MAR.Fecha) )  ");
        sql.append(" LEFT JOIN SolicitudCambioMarcacion soli ON soli.IdMarcacion = MAR.IdMarcacion");
        sql.append(" LEFT JOIN TablaGeneral ESTMAR ON MAR.Estado= ESTMAR.Codigo AND ESTMAR.Grupo = 'Marcaciones.Estado' ");
        
        sql.append(" WHERE soli.Estado = 'P'");
        sql.append(params.filter(" AND [dbo].[GetJefeInmediato] (EMP.IdEmpleado,HISTORIAL.IdHistorialLaboral) = :idEmpleado",filterViewModel.getIdEmpleado()));
        
        sql.append(" ORDER BY MAR.Fecha DESC, nombreCompletoEmpleado ");
        
		return sql.toString();

	}
	
	private String searchLicenciasDashboardJefeQuery(MarcacionDashboardFilterViewModel filterViewModel, WhereParams params) {

		StringBuilder sql = new StringBuilder();
		
		sql.append(" select distinct li.IdLicencia as idLicencia,  ");
		sql.append(" li.Comentario as comentario, " );
		sql.append(" li.DiaEntero as diaEntero, ");
		sql.append(" li.HoraInicio as horaInicio, ");
		sql.append(" li.HoraFin as horaFin, ");
		sql.append(" tl.IdTipoLicencia as idTipoLicencia,  ");
		sql.append(" tl.Nombre as nombreTipoLicencia,  ");
		sql.append(" e.IdEmpleado as idEmpleado, ");
		sql.append(" CONCAT(e.ApellidoPaterno, ' ', e.ApellidoMaterno, ', ', e.Nombre) as nombreEmpleado, ");
		
        sql.append(" CASE");
        sql.append(" WHEN DiaEntero = 1 THEN 'Si'");
        sql.append(" ELSE 'No'");
        sql.append(" END AS nombreDiaEntero, ");
        sql.append(" ATENDIDOPOR.ApellidoPaterno +' '+ATENDIDOPOR.ApellidoMaterno +', '+ATENDIDOPOR.Nombre AS jefeInmediato, ");

        sql.append(" li.FechaInicio as fechaInicio,  ");
        sql.append(" li.FechaFin as fechaFin,   ");
        sql.append(" li.Dias as dias ,   ");
        sql.append(" ESTADO.Nombre as estado ");
        
        sql.append(" FROM Licencia li");
        sql.append(" LEFT JOIN TipoLicencia tl ON li.idTipoLicencia=tl.idTipoLicencia ");
        sql.append(" LEFT JOIN Empleado e ON e.idEmpleado=li.idEmpleado ");
        sql.append(" LEFT JOIN TablaGeneral ESTADO ON li.Estado=ESTADO.Codigo AND ESTADO.Grupo = 'Licencia.Estado' ");
        sql.append(" LEFT JOIN Empleado ATENDIDOPOR ON ATENDIDOPOR.IdEmpleado = li.IdAtendidoPor ");
        sql.append(" WHERE li.Estado in ('P','E') ");
        sql.append(params.filter(" AND li.IdAtendidoPor = :idEmpleado ", filterViewModel.getIdEmpleado()));
        
       
        sql.append(" ORDER BY li.FechaInicio DESC ");
        
		return sql.toString();

	}

	private String generarVerBusquedaGridMarcacionesDashboardJefe(MarcacionDashboardFilterViewModel busquedaMarcacionDto,
			WhereParams params) {
		StringBuilder sql = new StringBuilder();

        sql.append(" SELECT distinct ");
        sql.append(" MAR.Fecha AS fecha, ");
        sql.append(" MAR.HoraIngreso AS horaIngreso, ");
        sql.append(" MAR.HoraInicioAlmuerzo AS horaInicioAlmuerzo, ");
        sql.append(" MAR.HoraFinAlmuerzo AS horaFinAlmuerzo, ");
        sql.append(" MAR.HoraSalida AS horaSalida, ");

        sql.append(" MAR.DemoraEntrada AS demoraEntrada, ");
        sql.append(" MAR.DemoraAlmuerzo AS demoraAlmuerzo, ");
        sql.append(" MAR.DemoraSalida AS demoraSalida, ");
        sql.append(" MAR.HoraIngresoHorario AS horaIngresoHorario, ");
        sql.append(" MAR.HoraSalidaHorario AS horaSalidaHorario, ");
                
        sql.append(" ESTMAR.Nombre AS estadoMarcacion, ");

        sql.append(" EMP.ApellidoPaterno+' '+EMP.ApellidoMaterno+', '+EMP.Nombre AS nombreCompletoEmpleado, ");
        sql.append(" EMP.Codigo AS codigoEmpleado ");
        sql.append(" FROM Marcacion MAR ");
        sql.append(" LEFT JOIN Empleado EMP ON EMP.IdEmpleado = MAR.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMP.IdEmpleado ");

        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append(" LEFT JOIN TablaGeneral ESTMAR ON MAR.Estado= ESTMAR.Codigo AND ESTMAR.Grupo = 'Marcaciones.Estado' ");
        sql.append(" WHERE 1=1 ");
        sql.append(params.filter(" AND UN.IdUnidadDeNegocio = :unidadNegocio ", busquedaMarcacionDto.getUnidadNegocio()));
        sql.append(params.filter(" AND DEP.IdDepartamentoArea = :departamento ", busquedaMarcacionDto.getDepartamento()));
        sql.append(params.filter(" AND PROY.IdProyecto = :proyecto ", busquedaMarcacionDto.getProyecto()));
        sql.append(params.filter(" AND MAR.Fecha = :fecha" , busquedaMarcacionDto.getFecha()));
        sql.append(" AND MAR.Fecha >= HISTORIAL.FechaInicio AND (HISTORIAL.fechaFin IS NULL OR MAR.Fecha<= HISTORIAL.fechaFin) ");
        sql.append(params.filter(" AND ( "+
        		" (HISTORIAL.IdProyecto IS NOT NULL AND PROY.IdJefe = :idJefeInmediato)  OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NOT NULL AND DEP.IdJefe  = :idJefeInmediato)"+
        		" OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NULL AND HISTORIAL.IdUnidadDeNegocio IS NOT NULL AND UN.IdJefe = :idJefeInmediato) ) ", busquedaMarcacionDto.getIdEmpleado()));
        sql.append(" Order by nombreCompletoEmpleado asc,MAR.Fecha desc ");

		return sql.toString();
	}

	public List<MarcacionDashboardResultViewModel> busquedaMarcacionesDashboardJefe(
			MarcacionDashboardFilterViewModel busquedaMarcacionDto) {
		WhereParams params = new WhereParams();
		if("0".equals(busquedaMarcacionDto.getTipoMarcacion()) || "1".equals(busquedaMarcacionDto.getTipoMarcacion())
                    || "2".equals(busquedaMarcacionDto.getTipoMarcacion()) || "3".equals(busquedaMarcacionDto.getTipoMarcacion())){
			String sql = generarVerBusquedaGridMarcacionesDashboardJefeTipoMarcacion(busquedaMarcacionDto, params);
			return jdbcTemplate.query(sql,
	                params.getParams(), new BeanPropertyRowMapper<>(MarcacionDashboardResultViewModel.class));
		}
        String sql = generarVerBusquedaGridMarcacionesDashboardJefe(busquedaMarcacionDto, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<>(MarcacionDashboardResultViewModel.class));
	}

	private String generarVerBusquedaGridMarcacionesDashboardJefeTipoMarcacion(
			MarcacionDashboardFilterViewModel busquedaMarcacionDto, WhereParams params) {
		StringBuilder sql = new StringBuilder();

        sql.append(" SELECT distinct ");
        sql.append(" MAR.Fecha AS fecha, ");
        sql.append(" MAR.HoraIngreso AS horaIngreso, ");
        sql.append(" MAR.HoraInicioAlmuerzo AS horaInicioAlmuerzo, ");
        sql.append(" MAR.HoraFinAlmuerzo AS horaFinAlmuerzo, ");
        sql.append(" MAR.HoraSalida AS horaSalida, ");

        sql.append(" MAR.DemoraEntrada AS demoraEntrada, ");
        sql.append(" MAR.DemoraAlmuerzo AS demoraAlmuerzo, ");
        sql.append(" MAR.DemoraSalida AS demoraSalida, ");
        sql.append(" MAR.HoraIngresoHorario AS horaIngresoHorario, ");
        sql.append(" MAR.HoraSalidaHorario AS horaSalidaHorario, ");
                
        sql.append(" ESTMAR.Nombre AS estadoMarcacion, ");

        sql.append(" EMP.ApellidoPaterno+' '+EMP.ApellidoMaterno+', '+EMP.Nombre AS nombreCompletoEmpleado, ");
        sql.append(" EMP.Codigo AS codigoEmpleado ");
        sql.append(" FROM Marcacion MAR ");
        sql.append(" LEFT JOIN Empleado EMP ON EMP.IdEmpleado = MAR.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMP.IdEmpleado ");

        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append(" LEFT JOIN TablaGeneral ESTMAR ON MAR.Estado= ESTMAR.Codigo AND ESTMAR.Grupo = 'Marcaciones.Estado' ");
        sql.append(" WHERE 1=1 ");
        sql.append(params.filter(" AND (UN.IdJefe = :idJefe OR DEP.IdJefe = :idJefe OR PROY.IdJefe = :idJefe )",busquedaMarcacionDto.getIdEmpleado()));
        sql.append(params.filter(" AND UN.IdUnidadDeNegocio = :unidadNegocio ", busquedaMarcacionDto.getUnidadNegocio()));
        sql.append(params.filter(" AND DEP.IdDepartamentoArea = :departamento ", busquedaMarcacionDto.getDepartamento()));
        if(busquedaMarcacionDto.getDepartamento()!=null){
        	sql.append(params.filter(" AND DEP.IdJefe = :idJefe ",busquedaMarcacionDto.getIdEmpleado()));
        }
        sql.append(params.filter(" AND PROY.IdProyecto = :proyecto ", busquedaMarcacionDto.getProyecto()));
        if(busquedaMarcacionDto.getProyecto()!=null){
        	sql.append(params.filter(" AND PROY.IdJefe = :idJefe ",busquedaMarcacionDto.getIdEmpleado()));
        }
        sql.append(params.filter(" AND MAR.Fecha = :fecha" , busquedaMarcacionDto.getFecha()));

	    //CLICL PIECHART jefe
        if(busquedaMarcacionDto.getTipoMarcacion().equals("0")){
        	// 0 = Nro. De Empleados que marcaron a tiempo',
        	sql.append(" AND MAR.DemoraEntrada <= 0 AND MAR.HoraIngreso IS NOT NULL AND MAR.EsPersonaDeConfianza = 0 ");
        }
        if(busquedaMarcacionDto.getTipoMarcacion().equals("1")){
        	// 1 = 'Nro. De Empleados que marcaron fuera de tiempo'
        	sql.append(" AND MAR.HoraIngreso is NOT NULL AND MAR.DemoraEntrada > 0 AND MAR.EsPersonaDeConfianza = 0 ");
        }
        if(busquedaMarcacionDto.getTipoMarcacion().equals("2")){
        	// 2 = 'Nro. De Empleados sin marcaciones';
        	sql.append(" AND MAR.HoraIngreso IS NULL AND MAR.EsPersonaDeConfianza = 0 ");
        }
        if(busquedaMarcacionDto.getTipoMarcacion().equals("3")){
        	// 3 = 'Nro. Personal de Confianza';
        	sql.append(" AND MAR.EsPersonaDeConfianza = 1 ");
        }

        sql.append(" Order by nombreCompletoEmpleado asc,MAR.Fecha desc ");

		return sql.toString();
	}

	public List<MarcacionDashboardResultViewModel> busquedaMarcacionesDashboardEmpleado(
			MarcacionDashboardEmpleadoFilterViewModel filterViewModel) {
		WhereParams params = new WhereParams();
		/*
	        0 = Nro. De Empleados que marcaron sin tardanza',
	        1 = 'Nro. De Empleados que marcaron con tardanza',
	        2 = 'Nro. De Empleados sin marcacion'];
        */
		if("0".equals(filterViewModel.getTipoMarcacion()) || "1".equals(filterViewModel.getTipoMarcacion())
                || "2".equals(filterViewModel.getTipoMarcacion())){
			String sql = generarVerBusquedaMarcacionesDashboardEmpleadoTipoMarcacion(filterViewModel, params);
			return jdbcTemplate.query(sql,
	                params.getParams(), new BeanPropertyRowMapper<>(MarcacionDashboardResultViewModel.class));
		}
        String sql = generarVerBusquedaMarcacionesDashboardEmpleado(filterViewModel, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<>(MarcacionDashboardResultViewModel.class));
	}

	private String generarVerBusquedaMarcacionesDashboardEmpleado(MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto,
			WhereParams params) {
		StringBuilder sql = new StringBuilder();

        sql.append(" SELECT distinct ");
        sql.append(" MAR.Fecha AS fecha, ");
        sql.append(" MAR.HoraIngreso AS horaIngreso, ");
        sql.append(" MAR.HoraInicioAlmuerzo AS horaInicioAlmuerzo, ");
        sql.append(" MAR.HoraFinAlmuerzo AS horaFinAlmuerzo, ");
        sql.append(" MAR.HoraSalida AS horaSalida, ");

        sql.append(" MAR.DemoraEntrada AS demoraEntrada, ");
        sql.append(" MAR.DemoraAlmuerzo AS demoraAlmuerzo, ");
        sql.append(" MAR.DemoraSalida AS demoraSalida, ");
        sql.append(" MAR.HoraIngresoHorario AS horaIngresoHorario, ");
        sql.append(" MAR.HoraSalidaHorario AS horaSalidaHorario, ");
        
        sql.append(" ESTMAR.Nombre AS estadoMarcacion, ");
        
        sql.append(" EMP.ApellidoPaterno+' '+EMP.ApellidoMaterno+', '+EMP.Nombre AS nombreCompletoEmpleado, ");

        sql.append(" EMP.Codigo AS codigoEmpleado ");
        sql.append(" FROM Marcacion MAR ");
        sql.append(" LEFT JOIN Empleado EMP ON EMP.IdEmpleado = MAR.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMP.IdEmpleado ");

        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append(" LEFT JOIN TablaGeneral ESTMAR ON MAR.Estado= ESTMAR.Codigo AND ESTMAR.Grupo = 'Marcaciones.Estado' ");
        sql.append(" WHERE 1=1 ");
        sql.append(params.filter(" AND EMP.IdEmpleado = :idEmpleado ", busquedaMarcacionDto.getIdEmpleado()));
        sql.append(params.filterDateDesde_US(" AND MAR.Fecha " , busquedaMarcacionDto.getDesde()));
        sql.append(params.filterDateHasta_US(" AND MAR.Fecha " , busquedaMarcacionDto.getHasta()));
        sql.append(" Order by MAR.Fecha desc ");

		return sql.toString();
	}

	private String generarVerBusquedaMarcacionesDashboardEmpleadoTipoMarcacion(MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		//IMPLEMNENTAR LOGICA CHART CLICK

        sql.append(" SELECT distinct ");
        sql.append(" MAR.Fecha AS fecha, ");
        sql.append(" MAR.HoraIngreso AS horaIngreso, ");
        sql.append(" MAR.HoraInicioAlmuerzo AS horaInicioAlmuerzo, ");
        sql.append(" MAR.HoraFinAlmuerzo AS horaFinAlmuerzo, ");
        sql.append(" MAR.HoraSalida AS horaSalida, ");

        sql.append(" MAR.DemoraEntrada AS demoraEntrada, ");
        sql.append(" MAR.DemoraAlmuerzo AS demoraAlmuerzo, ");
        sql.append(" MAR.DemoraSalida AS demoraSalida, ");
        sql.append(" MAR.HoraIngresoHorario AS horaIngresoHorario, ");
        sql.append(" MAR.HoraSalidaHorario AS horaSalidaHorario, ");
        
        sql.append(" ESTMAR.Nombre AS estadoMarcacion, ");

        sql.append(" EMP.ApellidoPaterno+' '+EMP.ApellidoMaterno+', '+EMP.Nombre AS nombreCompletoEmpleado, ");

        sql.append(" EMP.Codigo AS codigoEmpleado ");
        sql.append(" FROM Marcacion MAR ");
        sql.append(" LEFT JOIN Empleado EMP ON EMP.IdEmpleado = MAR.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMP.IdEmpleado ");

        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append(" LEFT JOIN TablaGeneral ESTMAR ON MAR.Estado= ESTMAR.Codigo AND ESTMAR.Grupo = 'Marcaciones.Estado' ");
        sql.append(" WHERE 1=1 ");
        sql.append(params.filter(" AND EMP.IdEmpleado = :idEmpleado ", busquedaMarcacionDto.getIdEmpleado()));
        if("0".equals(busquedaMarcacionDto.getTipoMarcacion())){
        	// 0 = Nro. De Empleados que marcaron a tiempo',
        	sql.append(" AND MAR.Estado != 'TA' AND MAR.HoraIngreso is NOT NULL ");
        }
        if("1".equals(busquedaMarcacionDto.getTipoMarcacion())){
        	// 1 = 'Nro. De Empleados que marcaron fuera de tiempo'
        	sql.append(" AND MAR.Estado = 'TA' AND MAR.HoraIngreso is NOT NULL ");
        }
        if("2".equals(busquedaMarcacionDto.getTipoMarcacion())){
        	// 2 = 'Nro. De Empleados sin marcaciones';
        	sql.append(" AND MAR.HoraIngreso IS NULL AND MAR.Estado = 'IN' OR MAR.Estado = 'VA' OR MAR.Estado = 'LI' ");
        }

        sql.append(" Order by MAR.Fecha desc ");

		return sql.toString();
	}

	public List<PermisoEmpleadoResultViewModel> searchPermisoDashboardEmpleado(
			MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto) {
		WhereParams params = new WhereParams();
        String sql = getPermisoEmpleadoDashboardEmpleadoQuery(busquedaMarcacionDto, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<PermisoEmpleadoResultViewModel>(PermisoEmpleadoResultViewModel.class));
	}

	private String getPermisoEmpleadoDashboardEmpleadoQuery(
			MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto, WhereParams params) {

		StringBuilder sql = new StringBuilder();
        sql.append(" select distinct pe.IdPermisoEmpleado as idPermisoEmpleado, ");
        sql.append(" CONCAT(EMPLEADO.ApellidoPaterno, ' ', EMPLEADO.ApellidoMaterno, ', ', EMPLEADO.Nombre) as nombreEmpleado, ");
        sql.append(" MOTIVO.nombre as motivo, ");
        sql.append(" pe.Fecha as fecha, ") ;
        sql.append(" pe.HoraInicio as horaInicio, ") ;
        sql.append(" pe.HoraFin as horaFin, ") ;
        sql.append(" pe.Horas as horas, ") ;
        sql.append("  CASE ");
        sql.append("  WHEN (pe.IdAtendidoPor IS NOT NULL) THEN JEFE.ApellidoPaterno + ' ' + JEFE.ApellidoMaterno + ', ' + JEFE.Nombre ");
        sql.append("  ELSE 'No Asociado'  ");
		sql.append("  END AS nombreJefeInmediato, ");
        sql.append(" CASE ");
        sql.append(" WHEN  (pe.Estado='P') THEN 'Pendiente' ");
        sql.append(" WHEN  (pe.Estado='E') THEN 'Enviado' ");
        sql.append(" WHEN  (pe.Estado='A') THEN 'Aprobado' ");
        sql.append(" WHEN  (pe.Estado='R') THEN 'Rechazao' ");
        sql.append(" ELSE 'No Asociado' ");
		sql.append(" END AS estado ");
        sql.append(" From PermisoEmpleado pe ") ;
        sql.append(" LEFT JOIN TablaGeneral ESTADO ON pe.Estado=ESTADO.Codigo and ESTADO.GRUPO='Permiso.Estado'");
        sql.append(" LEFT JOIN TablaGeneral MOTIVO ON pe.MOTIVO=MOTIVO.Codigo and MOTIVO.GRUPO='Permiso.Tipo'");
        sql.append(" LEFT JOIN PeriodoEmpleado PERIODO_EMPLEADO ON pe.IdPeriodoEmpleado = PERIODO_EMPLEADO.IdPeriodoEmpleado ");
        sql.append(" LEFT JOIN Empleado EMPLEADO ON PERIODO_EMPLEADO.IdEmpleado = EMPLEADO.IdEmpleado ");
        sql.append(" LEFT JOIN EMPLEADO JEFE ON JEFE.IdEmpleado = pe.IdAtendidoPor ");
        sql.append(" Where 1=1 ");
        sql.append(" AND pe.Estado IN ('P','E') ");
        sql.append(params.filter(" AND EMPLEADO.IdEmpleado = :idEmpleado ", busquedaMarcacionDto.getIdEmpleado()));
        sql.append(" ORDER BY nombreEmpleado");

        return sql.toString();

	}

	public List<VacacionResultViewModel> searchVacacionesEmpleadoDashboardEmpleado(
			MarcacionDashboardEmpleadoFilterViewModel marcacionDashboardEmpleadoDto) {
		WhereParams params = new WhereParams();
        String sql = generarBusquedaVacacionesDashboardEmpleadoQuery(marcacionDashboardEmpleadoDto, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<>(VacacionResultViewModel.class));
	}

	private String generarBusquedaVacacionesDashboardEmpleadoQuery(
			MarcacionDashboardEmpleadoFilterViewModel marcacionDashboardEmpleadoDto, WhereParams params) {
		StringBuilder sql = new StringBuilder();
        sql.append(" SELECT distinct va.IdVacacion AS idVacacion, ");
        sql.append(" CONCAT(EMPLEADO.ApellidoPaterno, ' ', EMPLEADO.ApellidoMaterno, ', ', EMPLEADO.Nombre) as nombreEmpleado, ");
        sql.append(" va.FechaInicio as fechaInicio, ");
        sql.append(" va.FechaFin as fechaFin, ");
        sql.append(" va.DiasCalendarios as diasCalendarios, ") ;
        sql.append(" va.DiasHabiles as diasHabiles, ") ;
        sql.append(" CONCAT(ATENDIDO.ApellidoPaterno, ' ', ATENDIDO.ApellidoMaterno, ', ', ATENDIDO.Nombre) as nombreJefeInmediato, ");
        sql.append(" CASE ");
        sql.append(" WHEN  (va.Estado='P') THEN 'Pendiente' ");
        sql.append(" WHEN  (va.Estado='E') THEN 'Enviado' ");
        sql.append(" WHEN  (va.Estado='A') THEN 'Aprobado' ");
        sql.append(" WHEN  (va.Estado='R') THEN 'Rechazado' ");
        sql.append(" ELSE 'No Asociado' ");
		sql.append(" END AS estado ");
        sql.append(" from Vacacion va ") ;
        sql.append(" LEFT JOIN TablaGeneral ESTADO ON va.Estado=ESTADO.Codigo and ESTADO.GRUPO='Permiso.Estado'");
        sql.append(" LEFT JOIN PeriodoEmpleado PERIODO_EMPLEADO ON va.IdPeriodoEmpleado = PERIODO_EMPLEADO.IdPeriodoEmpleado ");
        sql.append(" LEFT JOIN Empleado EMPLEADO ON PERIODO_EMPLEADO.IdEmpleado = EMPLEADO.IdEmpleado ");

        sql.append(" LEFT JOIN Empleado ATENDIDO ON va.IdAtendidoPor = ATENDIDO.IdEmpleado ");

        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMPLEADO.IdEmpleado AND ((HISTORIAL.FechaInicio < '"+getdate+"' AND HISTORIAL.FechaFin > '"+getdate+"') OR (HISTORIAL.FechaInicio < '"+getdate+"' AND HISTORIAL.FechaFin IS NULL)) ");

        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");

        sql.append("  LEFT JOIN Empleado ep on PROY.IdJefe = ep.IdEmpleado ");
		sql.append("  LEFT JOIN Empleado eda on DEP.IdJefe = eda.IdEmpleado ");
		sql.append("  LEFT JOIN Empleado eun on UN.IdJefe = eun.IdEmpleado ");

        sql.append(" where 1=1 ");
        sql.append(" AND va.Estado IN ('P','E') ");
        sql.append(" AND MONTH(va.FechaCreacion) = MONTH('"+getdate+"') and YEAR(va.FechaCreacion) = YEAR('"+getdate+"') ");
        sql.append(params.filter(" AND EMPLEADO.IdEmpleado = :idEmpleado ", marcacionDashboardEmpleadoDto.getIdEmpleado()));
        sql.append(" ORDER BY nombreEmpleado");
        return sql.toString();
	}

	public List<HorasExtraEmpleadoResultViewModel> busquedaHorasExtrasDashboardEmpledo(
			MarcacionDashboardEmpleadoFilterViewModel marcacionDashboardEmpleadoDto) {
		WhereParams params = new WhereParams();
        String sql = searchHorasExtrasDashboardEmpleadoQuery(marcacionDashboardEmpleadoDto, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<>(HorasExtraEmpleadoResultViewModel.class));
	}

	private String searchHorasExtrasDashboardEmpleadoQuery(MarcacionDashboardEmpleadoFilterViewModel marcacionDashboardEmpleadoDto, WhereParams params) {

		StringBuilder sql = new StringBuilder();
        sql.append(" SELECT distinct he.IdHorasExtra AS idHorasExtra, ");
        sql.append(" he.HoraSalidaSolicitado AS horaSalidaSolicitado, ");
        sql.append(" he.HoraInicioSolicitado AS horaInicioSolicitado, ");
        sql.append(" he.HorasNoCompensables AS horasNoCompensables, ");
        sql.append(" CONCAT(ATENDIDO.ApellidoPaterno, ' ', ATENDIDO.ApellidoMaterno, ', ', ATENDIDO.Nombre) as nombreJefeInmediato, ");
        sql.append(" CONCAT(EMPLEADO.ApellidoPaterno, ' ', EMPLEADO.ApellidoMaterno, ', ', EMPLEADO.Nombre) as nombreEmpleado, ");
        sql.append(" he.Fecha as fecha, ");
        sql.append(" he.HorasExtra as horasExtra, ");
        sql.append(" he.HorasSolicitadas as horasSolicitadas, ");
        sql.append(" ESTADO.Nombre AS estado ");
        sql.append(" from HorasExtra he ") ;
        sql.append(" LEFT JOIN TablaGeneral ESTADO ON he.Estado=ESTADO.Codigo and ESTADO.GRUPO='HorasExtra.Estado'");
        sql.append(" LEFT JOIN Empleado EMPLEADO ON he.IdEmpleado = EMPLEADO.IdEmpleado ");
        sql.append(" LEFT JOIN Empleado ATENDIDO ON he.IdAtendidoPor = ATENDIDO.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = he.IdEmpleado AND ((HISTORIAL.FechaInicio < '"+getdate+"' AND HISTORIAL.FechaFin > '"+getdate+"') OR (HISTORIAL.FechaInicio < '"+getdate+"' AND HISTORIAL.FechaFin IS NULL)) ");
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append(" where 1=1 ");
        sql.append(" AND he.Estado IN ('P','E') ");
        sql.append(" AND MONTH(he.Fecha) = MONTH('"+getdate+"') AND YEAR(he.Fecha) = YEAR('"+getdate+"') ");
        sql.append(params.filter(" AND EMPLEADO.IdEmpleado = :idEmpleado ", marcacionDashboardEmpleadoDto.getIdEmpleado()));
        sql.append(" ORDER BY nombreEmpleado");
        return sql.toString();
	}

	public List<PieChartDataResultViewModel> busquedaPieChartDashboardEmpleado(
			MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto) {
		WhereParams params = new WhereParams();
        String sql = busquedaPieChartDashboardEmpleadoQuery(busquedaMarcacionDto, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<>(PieChartDataResultViewModel.class));
	}

	private String busquedaPieChartDashboardEmpleadoQuery(
			MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto, WhereParams params) {
	  StringBuilder sql = new StringBuilder();

	  sql.append(" Select ");
	  sql.append(" distinct nroEmpleadosMarcacionFueraTiempo = (select count(prr.Estado) FROM Marcacion prr LEFT JOIN Empleado EMP ON EMP.IdEmpleado = prr.IdEmpleado ");
	  sql.append(" where prr.Estado = 'TA' AND prr.HoraIngreso is NOT NULL ");
	  sql.append(params.filterDateDesde_US(" AND prr.Fecha " , busquedaMarcacionDto.getDesde()));
      sql.append(params.filterDateHasta_US(" AND prr.Fecha " , busquedaMarcacionDto.getHasta()));
      sql.append(params.filter(" AND EMP.IdEmpleado = :idEmpleado ", busquedaMarcacionDto.getIdEmpleado()));

      sql.append(" ),nroEmpleadosMarcacionTiempo = (select count(prr.Estado) FROM Marcacion prr LEFT JOIN Empleado EMP ON EMP.IdEmpleado = prr.IdEmpleado ");
      sql.append(" where prr.Estado = 'TA' AND prr.HoraIngreso is NOT NULL ");
      sql.append(params.filterDateDesde_US(" AND prr.Fecha " , busquedaMarcacionDto.getDesde()));
      sql.append(params.filterDateHasta_US(" AND prr.Fecha " , busquedaMarcacionDto.getHasta()));
      sql.append(params.filter(" AND EMP.IdEmpleado = :idEmpleado ", busquedaMarcacionDto.getIdEmpleado()));

      sql.append(" ),nroEmpleadosSinMarcacion = (select count(prr.Estado) FROM Marcacion prr LEFT JOIN Empleado EMP ON EMP.IdEmpleado = prr.IdEmpleado ");
      sql.append(" where prr.HoraIngreso IS NULL AND (prr.Estado = 'IN' OR prr.Estado = 'VA' OR prr.Estado = 'LI') ");
      sql.append(params.filterDateDesde_US(" AND prr.Fecha " , busquedaMarcacionDto.getDesde()));
      sql.append(params.filterDateHasta_US(" AND prr.Fecha " , busquedaMarcacionDto.getHasta()));
      sql.append(params.filter(" AND EMP.IdEmpleado = :idEmpleado ", busquedaMarcacionDto.getIdEmpleado()));

      sql.append(" ) ");


		return sql.toString();
	}



	public List<PieChartDataResultViewModel> busquedaPieChartDashboardJefe(
			MarcacionDashboardFilterViewModel busquedaMarcacionDto) {
		WhereParams params = new WhereParams();
        String sql = busquedaPieChartDashboardJefe(busquedaMarcacionDto, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<>(PieChartDataResultViewModel.class));
	}

	private String busquedaPieChartDashboardJefe(MarcacionDashboardFilterViewModel busquedaMarcacionDto,
			WhereParams params) {
		StringBuilder sql = new StringBuilder();

		sql.append(" Select ");
		sql.append(" distinct nroEmpleadosMarcacionFueraTiempo = (select count(DISTINCT(prr.IdEmpleado)) ");
		sql.append(" FROM Marcacion prr LEFT JOIN Empleado EMP ON EMP.IdEmpleado = prr.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMP.IdEmpleado ");
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append(" WHERE 1=1 ");
        sql.append(" AND prr.EsPersonaDeConfianza = 0 ");
        sql.append(params.filter(" AND UN.IdUnidadDeNegocio = :unidadNegocio ", busquedaMarcacionDto.getUnidadNegocio()));
        sql.append(params.filter(" AND DEP.IdDepartamentoArea = :departamento ", busquedaMarcacionDto.getDepartamento()));
        sql.append(params.filter(" AND PROY.IdProyecto = :proyecto ", busquedaMarcacionDto.getProyecto()));
        sql.append(" AND prr.HoraIngreso is NOT NULL ");
        sql.append(" AND prr.DemoraEntrada > 0 ");
        sql.append(" AND prr.EsPersonaDeConfianza = 0 ");
		sql.append(params.filter(" AND prr.Fecha = :fecha ", busquedaMarcacionDto.getFecha()));
		sql.append(params.filter(" AND ( "+
        		" (HISTORIAL.IdProyecto IS NOT NULL AND PROY.IdJefe = :idJefeInmediato)  OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NOT NULL AND DEP.IdJefe  = :idJefeInmediato)"+
        		" OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NULL AND HISTORIAL.IdUnidadDeNegocio IS NOT NULL AND UN.IdJefe = :idJefeInmediato) ) ", busquedaMarcacionDto.getIdEmpleado()));

		sql.append(" ),nroEmpleadosMarcacionTiempo = (select count(distinct EMP.IdEmpleado) ");
		sql.append(" FROM Marcacion prr LEFT JOIN Empleado EMP ON EMP.IdEmpleado = prr.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMP.IdEmpleado ");
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append(" WHERE 1=1 ");
        sql.append(params.filter(" AND UN.IdUnidadDeNegocio = :unidadNegocio ", busquedaMarcacionDto.getUnidadNegocio()));
        sql.append(params.filter(" AND DEP.IdDepartamentoArea = :departamento ", busquedaMarcacionDto.getDepartamento()));
        sql.append(params.filter(" AND PROY.IdProyecto = :proyecto ", busquedaMarcacionDto.getProyecto()));
        sql.append(" AND prr.DemoraEntrada <= 0 AND prr.HoraIngreso IS NOT NULL AND prr.EsPersonaDeConfianza = 0 ");
        sql.append(params.filter(" AND prr.Fecha = :fecha ", busquedaMarcacionDto.getFecha()));
        sql.append(params.filter(" AND ( "+
        		" (HISTORIAL.IdProyecto IS NOT NULL AND PROY.IdJefe = :idJefeInmediato)  OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NOT NULL AND DEP.IdJefe  = :idJefeInmediato)"+
        		" OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NULL AND HISTORIAL.IdUnidadDeNegocio IS NOT NULL AND UN.IdJefe = :idJefeInmediato) ) ", busquedaMarcacionDto.getIdEmpleado()));

        sql.append(" ),nroPersonalConfianza = (select count(DISTINCT(prr.IdEmpleado)) ");
		sql.append(" FROM Marcacion prr LEFT JOIN Empleado EMP ON EMP.IdEmpleado = prr.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMP.IdEmpleado ");
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append(" WHERE 1=1 ");
		sql.append(params.filter(" AND UN.IdUnidadDeNegocio = :unidadNegocio ", busquedaMarcacionDto.getUnidadNegocio()));
        sql.append(params.filter(" AND DEP.IdDepartamentoArea = :departamento ", busquedaMarcacionDto.getDepartamento()));
		sql.append(params.filter(" AND PROY.IdProyecto = :proyecto ", busquedaMarcacionDto.getProyecto()));
		sql.append(" AND prr.EsPersonaDeConfianza = 1 ");
        sql.append(params.filter(" AND prr.Fecha = :fecha ", busquedaMarcacionDto.getFecha()));        
        sql.append(params.filter(" AND ( "+
        		" (HISTORIAL.IdProyecto IS NOT NULL AND PROY.IdJefe = :idJefeInmediato)  OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NOT NULL AND DEP.IdJefe  = :idJefeInmediato)"+
        		" OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NULL AND HISTORIAL.IdUnidadDeNegocio IS NOT NULL AND UN.IdJefe = :idJefeInmediato) ) ", busquedaMarcacionDto.getIdEmpleado()));
        
        
        sql.append(" ),nroEmpleadosSinMarcacion = (select count(DISTINCT(prr.IdEmpleado)) ");
        sql.append(" FROM Marcacion prr LEFT JOIN Empleado EMP ON EMP.IdEmpleado = prr.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMP.IdEmpleado ");
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append(" WHERE 1=1 ");
        sql.append(params.filter(" AND (UN.IdJefe = :idJefe OR DEP.IdJefe = :idJefe OR PROY.IdJefe = :idJefe )",busquedaMarcacionDto.getIdEmpleado()));
        sql.append(params.filter(" AND UN.IdUnidadDeNegocio = :unidadNegocio ", busquedaMarcacionDto.getUnidadNegocio()));
        sql.append(params.filter(" AND DEP.IdDepartamentoArea = :departamento ", busquedaMarcacionDto.getDepartamento()));
        sql.append(params.filter(" AND PROY.IdProyecto = :proyecto ", busquedaMarcacionDto.getProyecto()));
        sql.append(" AND prr.HoraIngreso IS NULL ");
        sql.append(" AND prr.EsPersonaDeConfianza = 0 ");
        sql.append(params.filter(" AND prr.Fecha = :fecha ", busquedaMarcacionDto.getFecha()));
        sql.append(params.filter(" AND ( "+
        		" (HISTORIAL.IdProyecto IS NOT NULL AND PROY.IdJefe = :idJefeInmediato)  OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NOT NULL AND DEP.IdJefe  = :idJefeInmediato)"+
        		" OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NULL AND HISTORIAL.IdUnidadDeNegocio IS NOT NULL AND UN.IdJefe = :idJefeInmediato) ) ", busquedaMarcacionDto.getIdEmpleado()));
        sql.append(" ) FROM Marcacion MAR ");
		return sql.toString();
	}

	public List<PermisoEmpleadoResultViewModel> searchPermisoDashboardRRHH(
			MarcacionDashboardFilterViewModel busquedaMarcacionDto) {
		WhereParams params = new WhereParams();
        String sql = searchPermisoDashboardRRHHQuery(busquedaMarcacionDto, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<PermisoEmpleadoResultViewModel>(PermisoEmpleadoResultViewModel.class));
	}

	private String searchPermisoDashboardRRHHQuery(MarcacionDashboardFilterViewModel busquedaMarcacionDto,
			WhereParams params) {
		StringBuilder sql = new StringBuilder();
        sql.append(" select distinct pe.IdPermisoEmpleado as idPermisoEmpleado, ");
        sql.append(" CONCAT(EMPLEADO.ApellidoPaterno, ' ', EMPLEADO.ApellidoMaterno, ', ', EMPLEADO.Nombre) as nombreEmpleado, ");
        sql.append(" MOTIVO.nombre as motivo, ");
        sql.append(" pe.Razon as razon, ") ;
        sql.append(" pe.Fecha as fecha, ") ;
        sql.append(" pe.HoraInicio as horaInicio, ") ;
        sql.append(" pe.HoraFin as horaFin, ") ;
        sql.append(" pe.Horas as horas, ") ;
        sql.append(" CASE ");
        sql.append(" WHEN  (pe.Estado='P') THEN 'Pendiente' ");
        sql.append(" WHEN  (pe.Estado='E') THEN 'Enviado' ");
        sql.append(" WHEN  (pe.Estado='A') THEN 'Aprobado' ");
        sql.append(" WHEN  (pe.Estado='R') THEN 'Rechazado' ");
        sql.append(" ELSE 'No Asociado' ");
		sql.append(" END AS estado ");
        sql.append(" From PermisoEmpleado pe ") ;
        sql.append(" LEFT JOIN TablaGeneral ESTADO ON pe.Estado=ESTADO.Codigo and ESTADO.GRUPO='Permiso.Estado'");
        sql.append(" LEFT JOIN TablaGeneral MOTIVO ON pe.MOTIVO=MOTIVO.Codigo and MOTIVO.GRUPO='Permiso.Tipo'");
        sql.append(" LEFT JOIN PeriodoEmpleado PERIODO_EMPLEADO ON pe.IdPeriodoEmpleado = PERIODO_EMPLEADO.IdPeriodoEmpleado ");
        sql.append(" LEFT JOIN Empleado EMPLEADO ON PERIODO_EMPLEADO.IdEmpleado = EMPLEADO.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMPLEADO.IdEmpleado AND ((HISTORIAL.FechaInicio < '"+getdate+"' AND HISTORIAL.FechaFin > '"+getdate+"') OR (HISTORIAL.FechaInicio < '"+getdate+"' AND HISTORIAL.FechaFin IS NULL)) ");
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append(" Where 1=1 ");
        sql.append(" AND pe.Estado in ('P','E') ");
        sql.append(" ORDER BY nombreEmpleado");
        return sql.toString();
	}

	public List<PieChartDataResultViewModel> buscarEmpleadoIndicadorDashBoardXmes(MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto) {
		WhereParams params = new WhereParams();
        String sql = buscarEmpleadoIndicadorDashBoardXmesQuery(busquedaMarcacionDto, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<>(PieChartDataResultViewModel.class));
	}

	private String buscarEmpleadoIndicadorDashBoardXmesQuery(MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		
		String dateCadena = DateUtil.fmtDt(new Date());		
		Date fechaActual =DateUtil.formatoFechaHoy(dateCadena);

		  sql.append(" Select ");
		  sql.append(" distinct nroEmpleadosMarcacionFueraTiempo = (select count(prr.Estado) FROM Marcacion prr LEFT JOIN Empleado EMP ON EMP.IdEmpleado = prr.IdEmpleado ");
		  sql.append(" where prr.Estado = 'TA' AND prr.HoraIngreso is NOT NULL ");
		  sql.append(" AND MONTH(prr.Fecha) = MONTH('"+getdate+"') AND YEAR(prr.Fecha) = YEAR('"+getdate+"') ");
	      sql.append(params.filter(" AND EMP.IdEmpleado = :idEmpleado ", busquedaMarcacionDto.getIdEmpleado()));

	      sql.append(" ),nroEmpleadosMarcacionTiempo = (select count(prr.Estado) FROM Marcacion prr ");
	      sql.append(" where prr.Estado != 'TA' AND prr.HoraIngreso is NOT NULL ");
	      sql.append(params.filter(" AND prr.Fecha = :fecha ",fechaActual));
	      sql.append(params.filter(" AND prr.IdEmpleado = :idEmpleado ", busquedaMarcacionDto.getIdEmpleado()));
	      
	      sql.append(" ),indicadorMarcacion = (select count(prr.IdMarcacion) FROM Marcacion prr LEFT JOIN Empleado EMP ON EMP.IdEmpleado = prr.IdEmpleado ");
	      sql.append(" WHERE EMP.CategoriaEmpleado = 'C' AND prr.HoraIngreso IS NOT NULL ");
	      sql.append(params.filter(" AND prr.Fecha = :fecha ",fechaActual));
	      sql.append(params.filter(" AND EMP.IdEmpleado = :idEmpleado ", busquedaMarcacionDto.getIdEmpleado()));

	      sql.append(" ),nroEmpleadosSinMarcacion = (select count(prr.Estado) FROM Marcacion prr LEFT JOIN Empleado EMP ON EMP.IdEmpleado = prr.IdEmpleado ");
	      sql.append(" where prr.HoraIngreso IS NULL AND (prr.Estado = 'IN' OR prr.Estado = 'VA' OR prr.Estado = 'LI') ");
	      sql.append(" AND MONTH(prr.Fecha) = MONTH('"+getdate+"') AND YEAR(prr.Fecha) = YEAR('"+getdate+"') ");
	      sql.append(params.filter(" AND EMP.IdEmpleado = :idEmpleado ", busquedaMarcacionDto.getIdEmpleado()));

	      sql.append(" ),countIsVacacion = (SELECT count(prr.Estado) FROM Marcacion prr LEFT JOIN Empleado EMP ON EMP.IdEmpleado = prr.IdEmpleado ");
	      sql.append(" WHERE 1=1 AND prr.Estado = 'VA' AND prr.HoraIngreso IS NULL ");
	      sql.append(params.filter(" AND prr.Fecha =:fechaActual ", fechaActual));
	      sql.append(params.filter(" AND EMP.IdEmpleado = :idEmpleado ", busquedaMarcacionDto.getIdEmpleado()));

	      sql.append(" ),countIsLicencia = (SELECT count(prr.Estado) FROM Marcacion prr LEFT JOIN Empleado EMP ON EMP.IdEmpleado = prr.IdEmpleado ");
	      sql.append(" WHERE 1=1 AND prr.Estado = 'LI' AND prr.HoraIngreso IS NULL ");
	      sql.append(params.filter(" AND prr.Fecha =:fechaActual ", fechaActual));
	      sql.append(params.filter(" AND EMP.IdEmpleado = :idEmpleado ", busquedaMarcacionDto.getIdEmpleado()));
	      
	      sql.append(" ),countIsInasistencia = (SELECT count(prr.IdMarcacion) FROM Marcacion prr LEFT JOIN Empleado EMP ON EMP.IdEmpleado = prr.IdEmpleado ");
	      sql.append(" WHERE 1=1 AND prr.Estado = 'IN' AND prr.HoraIngreso IS NULL ");
	      sql.append(params.filter(" AND prr.Fecha =:fechaActual ", fechaActual));
	      sql.append(params.filter(" AND EMP.IdEmpleado = :idEmpleado ", busquedaMarcacionDto.getIdEmpleado()));

	      sql.append(" ),countInasistenciaXmes = (SELECT count(prr.Estado) FROM Marcacion prr LEFT JOIN Empleado EMP ON EMP.IdEmpleado = prr.IdEmpleado ");
	      sql.append(" WHERE 1=1 AND prr.Estado = 'IN' AND prr.HoraIngreso IS NULL ");
	      sql.append(" AND MONTH(prr.Fecha) = MONTH('"+getdate+"') AND YEAR(prr.Fecha) = YEAR('"+getdate+"') ");
	      sql.append(params.filter(" AND EMP.IdEmpleado = :idEmpleado ", busquedaMarcacionDto.getIdEmpleado()));

	      sql.append(" ),countSinMarcacion = (SELECT count(prr.IdMarcacion) FROM Marcacion prr LEFT JOIN Empleado EMP ON EMP.IdEmpleado = prr.IdEmpleado ");
	      sql.append(" WHERE 1=1 AND prr.Estado = 'SM' AND prr.HoraIngreso IS NULL ");
	      sql.append(params.filter(" AND prr.Fecha =:fechaActual ", fechaActual));
	      sql.append(params.filter(" AND EMP.IdEmpleado = :idEmpleado ", busquedaMarcacionDto.getIdEmpleado()));

	      sql.append(" ),horasPendientesCompensar = (SELECT e.HorasPendientesTotal FROM EmpleadoCompensacion e ");
	      sql.append(" WHERE 1=1 ");
	      sql.append(params.filter(" AND e.IdEmpleado = :idEmpleado ", busquedaMarcacionDto.getIdEmpleado()));
	      sql.append(" AND e.Mes = '0'+cast(month('"+getdate+"') as varchar)+cast('/' as varchar)+cast(year('"+getdate+"') as varchar)+'' ");

	      sql.append(" ),isPersonalConfianza = (SELECT ");
	      sql.append(" CASE ");
	      sql.append(" WHEN (e.CategoriaEmpleado = 'J' OR e.CategoriaEmpleado = 'C') THEN 1 ");
	      sql.append(" ELSE 0");
	      sql.append(" END ");
	      sql.append(" FROM Empleado e ");
	      sql.append(" WHERE 1=1 ");
	      sql.append(params.filter(" AND e.IdEmpleado = :idEmpleado ", busquedaMarcacionDto.getIdEmpleado()));
	      sql.append(" ) ");


		  return sql.toString();
	}

	public Long minutosTardanciaxDia(MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto) {

		String fechaCadena = DateUtil.fmtDt(new Date());

        Date currentDate = DateUtil.formatoFechaArchivoMarcacion(fechaCadena);

		Marcacion marcacionEntity = marcacionJpaRepository.obtenerMarcacionPorIdEmpleadoyDate(busquedaMarcacionDto.getIdEmpleado(), currentDate);

		if(marcacionEntity!=null){
			BigDecimal minutosTardancia = marcacionEntity.getDemoraEntrada();
			return minutosTardancia.longValue();
		}

		return 0L;

	}

	public Long minutosTardanciaxMes(MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto) {

    	return null;
	}

	public List<PieChartDataResultViewModel> listMinutosTardanciaxMes(MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto) {
		WhereParams params = new WhereParams();
        String sql = listMinutosTardanciaxMesQuery(busquedaMarcacionDto, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<PieChartDataResultViewModel>(PieChartDataResultViewModel.class));
	}

	private String listMinutosTardanciaxMesQuery(MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto,
			WhereParams params) {
		StringBuilder sql = new StringBuilder();
        sql.append(" select pe.DemoraEntrada as minutosTardanzaXmes");
        sql.append(" From Marcacion pe ") ;
        sql.append(" Where 1=1 ");
        sql.append(" AND pe.DemoraEntrada > 0 ");
        sql.append(" AND MONTH(pe.Fecha) = MONTH('"+getdate+"') AND YEAR(pe.Fecha) = YEAR('"+getdate+"') ");
        sql.append(params.filter(" AND pe.IdEmpleado = :idEmpleado ", busquedaMarcacionDto.getIdEmpleado()));
        return sql.toString();
	}

	public List<IndicadorRRHHResultViewModel> buscarIndicadorRRHHDashBoard(MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto) {
		WhereParams params = new WhereParams();
        String sql = buscarIndicadorRRHHDashBoardQuery(busquedaMarcacionDto, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<>(IndicadorRRHHResultViewModel.class));
	}

	private String buscarIndicadorRRHHDashBoardQuery(MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto,
			WhereParams params) {
		  StringBuilder sql = new StringBuilder();

	      Date fechaActual = new Date();

		  sql.append(" Select ");
		  sql.append(" distinct countEmpleadoVacacionHoy = (select count(DISTINCT(prr.IdVacacion)) FROM Vacacion prr where 1=1 ");
		  sql.append(params.filterDate_US_Beteen(" AND ", fechaActual));
		  sql.append(" BETWEEN prr.FechaInicio and prr.FechaFin ");
		  sql.append(" AND prr.Estado = 'A' ");

		  //MODIFICAR ESTO
	      sql.append(" ),countTardanzasPromedioAlDiaxMes = (select count(DISTINCT(prr.IdMarcacion)) FROM Marcacion prr LEFT JOIN Empleado EMP ON EMP.IdEmpleado = prr.IdEmpleado ");
	      sql.append(" where prr.Estado = 'TA' AND prr.HoraIngreso is NOT NULL ");
	      sql.append(" AND MONTH(prr.Fecha) = MONTH('"+getdate+"') AND YEAR(prr.Fecha) = YEAR('"+getdate+"') ");

	      sql.append(" ),countBirthdayByMonth = (select count(DISTINCT(e.IdEmpleado)) FROM Empleado e WHERE MONTH(e.FechaNacimiento) = MONTH('"+getdate+"') AND e.Estado = 'A' ");

	      sql.append(" ),countAltasLastYear = (select count(DISTINCT(e.IdEmpleado)) from Empleado e where 1=1 AND e.Estado = 'A' AND e.FechaIngreso BETWEEN DATEADD(year, -1, '"+getdate+"') AND '"+getdate+"' ");

	      sql.append(" ),countEmpleadoEmpresa = (select count(DISTINCT(e.IdEmpleado)) from Empleado e where 1=1 AND e.Estado = 'A' ");

	      sql.append(" ),countContratoxVencer = (select count(DISTINCT(e.IdContrato)) from Contrato e where MONTH(e.FechaFin) = MONTH('"+getdate+"') ");

	      sql.append(" ),countEmpleadoLicenciaByDay = (select count(DISTINCT(e.IdLicencia)) from Licencia e where 1=1 ");
	      sql.append(params.filterDate_US_Beteen(" AND ", fechaActual));
	      sql.append(" BETWEEN e.FechaInicio and e.FechaFin AND e.Estado = 'A' ");
	      //sql.append(" AND e.Estado = 'V' ");
	      
	      sql.append(" ),countInasistenciasxMes = (select count(DISTINCT(prr.IdMarcacion)) FROM Marcacion prr LEFT JOIN Empleado EMP ON EMP.IdEmpleado = prr.IdEmpleado ");
	      sql.append(" where 1=1 AND prr.HoraIngreso IS NULL AND (prr.Estado = 'IN') ");
	      sql.append(" AND MONTH(prr.Fecha) = MONTH('"+getdate+"') AND YEAR(prr.Fecha) = YEAR('"+getdate+"') ");

	      sql.append(" ),countLicenciaxMes = (select count(DISTINCT(prr.IdMarcacion)) from Marcacion prr ");
	      sql.append(" where 1=1 AND prr.HoraIngreso IS NULL AND (prr.Estado = 'LI') ");
	      sql.append(" AND MONTH(prr.Fecha) = MONTH('"+getdate+"') AND YEAR(prr.Fecha) = YEAR('"+getdate+"') ");

	      sql.append(" ),countBajasLastYear = (select count(DISTINCT(e.IdEmpleado)) from Empleado e where 1=1 AND e.Estado = 'I' AND e.FechaCese BETWEEN DATEADD(year, -1, '"+getdate+"') AND '"+getdate+"' ");
	      sql.append(" ) ");

		return sql.toString();
	}

	public List<VacacionResultViewModel> searchVacacionesDashboardRRHH(MarcacionDashboardFilterViewModel marcacionDashboardEmpleadoDto) {
		WhereParams params = new WhereParams();
        String sql = searchVacacionesDashboardRRHHQuery(marcacionDashboardEmpleadoDto, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<>(VacacionResultViewModel.class));
	}

	private String searchVacacionesDashboardRRHHQuery(MarcacionDashboardFilterViewModel marcacionDashboardEmpleadoDto,
			WhereParams params) {
		StringBuilder sql = new StringBuilder();
        sql.append(" SELECT distinct va.IdVacacion AS idVacacion, ");
        sql.append(" CONCAT(EMPLEADO.ApellidoPaterno, ' ', EMPLEADO.ApellidoMaterno, ', ', EMPLEADO.Nombre) as nombreEmpleado, ");
        sql.append(" va.FechaInicio as fechaInicio, ");
        sql.append(" va.FechaFin as fechaFin, ");
        sql.append(" va.DiasCalendarios as diasCalendarios, ") ;
        sql.append(" va.DiasHabiles as diasHabiles, ") ;
        sql.append(" CASE ");
        sql.append(" WHEN  (va.Estado='P') THEN 'Pendiente' ");
        sql.append(" WHEN  (va.Estado='E') THEN 'Enviado' ");
        sql.append(" WHEN  (va.Estado='A') THEN 'Aprobado' ");
        sql.append(" WHEN  (va.Estado='R') THEN 'Rechazado' ");
        sql.append(" ELSE 'No Asociado' ");
		sql.append(" END AS estado ");
        sql.append(" from Vacacion va ") ;
        sql.append(" LEFT JOIN TablaGeneral ESTADO ON va.Estado=ESTADO.Codigo and ESTADO.GRUPO='Vacaciones.Estado'");
        sql.append(" LEFT JOIN PeriodoEmpleado PERIODO_EMPLEADO ON va.IdPeriodoEmpleado = PERIODO_EMPLEADO.IdPeriodoEmpleado ");
        sql.append(" LEFT JOIN Empleado EMPLEADO ON PERIODO_EMPLEADO.IdEmpleado = EMPLEADO.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMPLEADO.IdEmpleado AND ((HISTORIAL.FechaInicio < '"+getdate+"' AND HISTORIAL.FechaFin > '"+getdate+"') OR (HISTORIAL.FechaInicio < '"+getdate+"' AND HISTORIAL.FechaFin IS NULL)) ");
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append(" where 1=1 ");
        sql.append(" AND va.Estado in ('P','E') ");
        sql.append(" ORDER BY nombreEmpleado");

        return sql.toString();
	}

	public List<HorasExtraEmpleadoResultViewModel> searchHorasExtrasDashboardRRHH(MarcacionDashboardFilterViewModel marcacionDashboardEmpleadoDto) {
		WhereParams params = new WhereParams();
        String sql = searchHorasExtrasDashboardRRHHQuery(marcacionDashboardEmpleadoDto, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<>(HorasExtraEmpleadoResultViewModel.class));
	}
	
	public List<LicenciaEmpleadoResultViewModel> searchLicenciasDashboardRRHH(MarcacionDashboardFilterViewModel marcacionDashboardEmpleadoDto) {
		WhereParams params = new WhereParams();
        String sql = searchLicenciasDashboardRRHHQuery(marcacionDashboardEmpleadoDto, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<>(LicenciaEmpleadoResultViewModel.class));
	}
	
	private String searchLicenciasDashboardRRHHQuery(MarcacionDashboardFilterViewModel filterViewModel, WhereParams params) {

		StringBuilder sql = new StringBuilder();
		
		sql.append(" select distinct li.IdLicencia as idLicencia,  ");
		sql.append(" li.Comentario as comentario, " );
		sql.append(" li.DiaEntero as diaEntero, ");
		sql.append(" li.HoraInicio as horaInicio, ");
		sql.append(" li.HoraFin as horaFin, ");
		sql.append(" tl.IdTipoLicencia as idTipoLicencia,  ");
		sql.append(" tl.Nombre as nombreTipoLicencia,  ");
		sql.append(" e.IdEmpleado as idEmpleado, ");
		sql.append(" CONCAT(e.ApellidoPaterno, ' ', e.ApellidoMaterno, ', ', e.Nombre) as nombreEmpleado, ");
		
        sql.append(" CASE");
        sql.append(" WHEN DiaEntero = 1 THEN 'Si'");
        sql.append(" ELSE 'No'");
        sql.append(" END AS nombreDiaEntero, ");
        sql.append(" ATENDIDOPOR.ApellidoPaterno +' '+ATENDIDOPOR.ApellidoMaterno +', '+ATENDIDOPOR.Nombre AS jefeInmediato, ");

        sql.append(" li.FechaInicio as fechaInicio,  ");
        sql.append(" li.FechaFin as fechaFin,   ");
        sql.append(" li.Dias as dias ,   ");
        sql.append(" ESTADO.Nombre as estado ");
        
        sql.append(" FROM Licencia li");
        sql.append(" LEFT JOIN TipoLicencia tl ON li.idTipoLicencia=tl.idTipoLicencia ");
        sql.append(" LEFT JOIN Empleado e ON e.idEmpleado=li.idEmpleado ");
        sql.append(" LEFT JOIN TablaGeneral ESTADO ON e.Estado=ESTADO.Codigo AND ESTADO.Grupo = 'Licencia.Estado' ");
        sql.append(" LEFT JOIN Empleado ATENDIDOPOR ON ATENDIDOPOR.IdEmpleado = li.IdAtendidoPor ");
        sql.append(" WHERE li.Estado in ('P','E') ");
               
        sql.append(" ORDER BY li.FechaInicio DESC ");
        
		return sql.toString();

	}

	private String searchHorasExtrasDashboardRRHHQuery(MarcacionDashboardFilterViewModel marcacionDashboardEmpleadoDto,
			WhereParams params) {

		StringBuilder sql = new StringBuilder();
        sql.append(" SELECT distinct he.IdHorasExtra AS idHorasExtra, ");
        sql.append(" CONCAT(EMPLEADO.ApellidoPaterno, ' ', EMPLEADO.ApellidoMaterno, ', ', EMPLEADO.Nombre) as nombreEmpleado, ");
        sql.append(" he.Fecha as fecha, ");
        sql.append(" he.HoraSalidaSolicitado AS horaSalidaSolicitado, ");
        sql.append(" he.HoraInicioSolicitado AS horaInicioSolicitado, ");
        sql.append(" he.HorasNoCompensables AS horasNoCompensables, ");
        sql.append(" he.HorasExtra as horasExtra, ");
        sql.append(" he.HorasSolicitadas as horasSolicitadas, ");
        sql.append(" ESTADO.Nombre AS estado ");
        sql.append(" from HorasExtra he ") ;
        sql.append(" LEFT JOIN TablaGeneral ESTADO ON he.Estado=ESTADO.Codigo and ESTADO.GRUPO='HorasExtra.Estado'");
        sql.append(" LEFT JOIN Empleado EMPLEADO ON he.IdEmpleado = EMPLEADO.IdEmpleado ");
        sql.append(" LEFT JOIN Empleado ATENDIDO ON he.IdAtendidoPor = ATENDIDO.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = he.IdEmpleado AND ((HISTORIAL.FechaInicio < '"+getdate+"' AND HISTORIAL.FechaFin > '"+getdate+"') OR (HISTORIAL.FechaInicio < '"+getdate+"' AND HISTORIAL.FechaFin IS NULL)) ");
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append(" where 1=1 ");
        sql.append(" AND he.Estado in ('P','E') ");
        sql.append(" ORDER BY nombreEmpleado");
        return sql.toString();
	}

	public List<MarcacionesMensualesViewModel> obtenerMarcacionesMensuales(MarcacionesMensualesFilterViewModel dto) {
		 SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("spuMarcacionesMensualesEmpleado")
				 .returningResultSet("MarcacionesMensuales",new BeanPropertyRowMapper<MarcacionesMensualesViewModel>(MarcacionesMensualesViewModel.class));
		 
         SqlParameterSource in = new MapSqlParameterSource().addValue("anio", dto.getAnio(),Types.VARCHAR)
        		 											.addValue("mes", dto.getMes(),Types.VARCHAR)
        		 											.addValue("idEmpleado", dto.getIdEmpleado(),Types.NUMERIC);
         Map<String, Object> out = jdbcCall.execute(in);
         List<MarcacionesMensualesViewModel> lista = (List<MarcacionesMensualesViewModel>)out.get("MarcacionesMensuales");
         
	     return lista;

	}

	public List<MarcacionDashboardResultViewModel> busquedaMarcacionesDashboardJefeAll(
			MarcacionDashboardFilterViewModel busquedaMarcacionDto) {
		WhereParams params = new WhereParams();
        String sql = generarVerBusquedaGridMarcacionesDashboardJefe(busquedaMarcacionDto, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<>(MarcacionDashboardResultViewModel.class));
	}

	public List<IndicadorRRHHResultViewModel> listMinutosTardanciaxMesRRHH(
			MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto) {
		WhereParams params = new WhereParams();
        String sql = listMinutosTardanciaxMesRRHHQuery(busquedaMarcacionDto, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<IndicadorRRHHResultViewModel>(IndicadorRRHHResultViewModel.class));
	}

	private String listMinutosTardanciaxMesRRHHQuery(MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto,
			WhereParams params) {
		
		StringBuilder sql = new StringBuilder();
        sql.append(" select pe.DemoraEntrada as countMinutosTardanzaXmes");
        sql.append(" From Marcacion pe ") ;
        sql.append(" Where 1=1 ");
        sql.append(" AND MONTH(pe.Fecha) = MONTH('"+getdate+"') AND YEAR(pe.Fecha) = YEAR('"+getdate+"') ");
        sql.append(" AND pe.Estado = 'TA' ");
        return sql.toString();
	}

	public List<IndicadorJefeResultViewModel> buscarIndicadorJefeDashBoard(MarcacionDashboardEmpleadoFilterViewModel filterViewModel) {
		WhereParams params = new WhereParams();
        String sql = buscarIndicadorJefeDashBoardQuery(filterViewModel, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<>(IndicadorJefeResultViewModel.class));
	}

	private String buscarIndicadorJefeDashBoardQuery(MarcacionDashboardEmpleadoFilterViewModel filterViewModel,WhereParams params) {
		StringBuilder sql = new StringBuilder();

	      Date fechaActual = new Date();

	      sql.append(" Select countEmpleadoVacacionHoy = (select count(DISTINCT(prr.IdVacacion)) FROM Vacacion prr where 1=1 ");
		  sql.append(params.filterDate_US_Beteen(" AND ", fechaActual));
		  sql.append(" BETWEEN prr.FechaInicio and prr.FechaFin ");
		  sql.append(params.filter(" AND prr.IdAtendidoPor = :idAtendidoPor ", filterViewModel.getIdEmpleado()));
		  sql.append(" AND prr.Estado = 'A' ");
		  
	      sql.append(" ),countTardanzasPromedioAlDiaxMes = (select count(DISTINCT(prr.IdMarcacion)) FROM Marcacion prr ");	      
	      sql.append(" LEFT JOIN Empleado EMP ON EMP.IdEmpleado = prr.IdEmpleado ");
	      sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMP.IdEmpleado ");
	      sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
	      sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
	      sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");	      
	      sql.append(" where prr.Estado = 'TA' AND prr.HoraIngreso is NOT NULL ");
	      sql.append(" AND MONTH(prr.Fecha) = MONTH('"+getdate+"') AND YEAR(prr.Fecha) = YEAR('"+getdate+"') ");
	      sql.append(params.filter(" AND ( "+
	        		" (HISTORIAL.IdProyecto IS NOT NULL AND PROY.IdJefe = :idEmpleado) "+
	        		" OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NOT NULL AND DEP.IdJefe  = :idEmpleado) "+
	        		" OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NULL AND HISTORIAL.IdUnidadDeNegocio IS NOT NULL "+
	        		" AND UN.IdJefe = :idEmpleado) ) ", filterViewModel.getIdEmpleado()));
	      sql.append(params.filter(" AND [dbo].[GetJefeInmediato] (EMP.IdEmpleado,HISTORIAL.IdHistorialLaboral) = :idEmpleado",filterViewModel.getIdEmpleado()));

	      sql.append(" ),countBirthdayByMonth = (select count(DISTINCT(EMP.IdEmpleado)) FROM Empleado EMP ");
	      sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMP.IdEmpleado ");
	      sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
	      sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
	      sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
	      sql.append(" WHERE MONTH(EMP.FechaNacimiento) = MONTH('"+getdate+"') ");
	      sql.append(" AND ((HISTORIAL.FechaInicio<='"+getdate+"' and HISTORIAL.FechaFin>='"+getdate+"') or (HISTORIAL.FechaInicio<='"+getdate+"' and HISTORIAL.FechaFin is null)) ");
	      sql.append(" AND dbo.GetJefeInmediato(EMP.IdEmpleado,HISTORIAL.IdHistorialLaboral) = "+ filterViewModel.getIdEmpleado());
	      sql.append(" AND EMP.Estado = 'A' ");
	     /* sql.append(params.filter(" AND ( "+
	        		" (HISTORIAL.IdProyecto IS NOT NULL AND PROY.IdJefe = :idJefeInmediato) "+
	        		" OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NOT NULL AND DEP.IdJefe  = :idJefeInmediato) "+
	        		" OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NULL AND HISTORIAL.IdUnidadDeNegocio IS NOT NULL "+
	        		" AND UN.IdJefe = :idJefeInmediato) ) ", filterViewModel.getIdEmpleado()));
	      */
	      sql.append(" ),countEmpleadoLicenciaByDay = (select count(DISTINCT(e.IdLicencia)) from Licencia e where 1=1 ");
	      sql.append(params.filterDate_US_Beteen(" AND ", fechaActual));
	      sql.append(" BETWEEN e.FechaInicio and e.FechaFin ");
	      sql.append(params.filter(" AND e.IdAtendidoPor = :idAtendidoPor ", filterViewModel.getIdEmpleado()));
	      sql.append(" AND e.Estado = 'V' ");
	      
	      sql.append(" ),countInasistenciasxMes = (select count(DISTINCT(prr.IdMarcacion)) FROM Marcacion prr ");
	      sql.append(" LEFT JOIN Empleado EMP ON EMP.IdEmpleado = prr.IdEmpleado ");
	      sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMP.IdEmpleado ");
	      sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
	      sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
	      sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
	      sql.append(" where 1=1 AND prr.HoraIngreso IS NULL AND (prr.Estado = 'IN') ");
	      sql.append(" AND MONTH(prr.Fecha) = MONTH('"+getdate+"') AND YEAR(prr.Fecha) = YEAR('"+getdate+"') ");
	      sql.append(params.filter(" AND ( "+
	        		" (HISTORIAL.IdProyecto IS NOT NULL AND PROY.IdJefe = :idEmpleado) "+
	        		" OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NOT NULL AND DEP.IdJefe  = :idEmpleado) "+
	        		" OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NULL AND HISTORIAL.IdUnidadDeNegocio IS NOT NULL "+
	        		" AND UN.IdJefe = :idEmpleado) ) ", filterViewModel.getIdEmpleado()));

	      sql.append(" ),countLicenciaxMes = (select count(DISTINCT(prr.IdMarcacion)) from Marcacion prr ");
	      sql.append(" LEFT JOIN Empleado EMP ON EMP.IdEmpleado = prr.IdEmpleado ");
	      sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMP.IdEmpleado ");
	      sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
	      sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
	      sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
	      sql.append(" where 1=1 AND prr.HoraIngreso IS NULL AND (prr.Estado = 'LI') ");
	      sql.append(" AND MONTH(prr.Fecha) = MONTH('"+getdate+"') AND YEAR(prr.Fecha) = YEAR('"+getdate+"') ");
	      sql.append(params.filter(" AND ( "+
	        		" (HISTORIAL.IdProyecto IS NOT NULL AND PROY.IdJefe = :idEmpleado) "+
	        		" OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NOT NULL AND DEP.IdJefe  = :idEmpleado) "+
	        		" OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NULL AND HISTORIAL.IdUnidadDeNegocio IS NOT NULL "+
	        		" AND UN.IdJefe = :idEmpleado) ) ", filterViewModel.getIdEmpleado()));
	      
	      sql.append(" ) ");

		return sql.toString();
	}

	public List<IndicadorVacacionesResultViewModel> busquedaIndicadorVacacionesDashboard(
			MarcacionDashboardFilterViewModel filterViewModel) {
		
		WhereParams params = new WhereParams();
        String sql = busquedaIndicadorVacacionesDashboardQuery(filterViewModel, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<>(IndicadorVacacionesResultViewModel.class));
	}

	private String busquedaIndicadorVacacionesDashboardQuery(MarcacionDashboardFilterViewModel filterViewModel,WhereParams params) {
		
		StringBuilder sql = new StringBuilder();
        sql.append(" SELECT DISTINCT (p.IdEmpleado),CONCAT(emp.ApellidoPaterno, ' ', emp.ApellidoMaterno, ', ', emp.Nombre) as nombreEmpleado, ");
        sql.append(" p.Periodo as periodo, p.DiasVacacionesAcumulado as diasHabiles, ");
        sql.append(" CASE ");
        sql.append(" WHEN (p.DiasVacacionesAcumulado<=0) THEN 'verde' ");
        sql.append(" WHEN (p.DiasVacacionesAcumulado>0 AND p.DiasVacacionesAcumulado < 23 AND DATEDIFF(day,'"+getdate+"',p.FechaFin)>30 ) THEN 'verde' ");
        sql.append(" WHEN (p.DiasVacacionesAcumulado>0 AND p.DiasVacacionesAcumulado < 23 AND DATEDIFF(day,'"+getdate+"',p.FechaFin)<=30) THEN 'amarillo' ");
        sql.append(" WHEN (p.DiasVacacionesAcumulado>22 AND p.DiasVacacionesAcumulado < 34 ) THEN 'amarillo' ");
        sql.append(" WHEN (p.DiasVacacionesAcumulado>33 ) THEN 'rojo' ");
        sql.append(" ELSE 'No identificado' END as semaforo ");
        sql.append(" from PeriodoEmpleado p ");
        sql.append(" LEFT JOIN Empleado emp ON p.IdEmpleado = emp.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = p.IdEmpleado AND ((HISTORIAL.FechaInicio < '"+getdate+"' AND HISTORIAL.FechaFin > '"+getdate+"') OR (HISTORIAL.FechaInicio < '"+getdate+"' AND HISTORIAL.FechaFin IS NULL)) ");
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append(" where '"+getdate+"' BETWEEN p.FechaInicio and p.FechaFin AND emp.Estado = 'A' ");
        sql.append(params.filter(" AND ( "+
        		" (HISTORIAL.IdProyecto IS NOT NULL AND PROY.IdJefe = :idJefeInmediato)  OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NOT NULL AND DEP.IdJefe  = :idJefeInmediato)"+
        		" OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NULL AND HISTORIAL.IdUnidadDeNegocio IS NOT NULL AND UN.IdJefe = :idJefeInmediato) ) ", filterViewModel.getIdEmpleado()));
        sql.append(params.filter(" AND [dbo].[GetJefeInmediato] (emp.IdEmpleado,HISTORIAL.IdHistorialLaboral) = :idJefe",filterViewModel.getIdEmpleado()));
        sql.append(" ORDER BY p.DiasVacacionesAcumulado DESC ");
        return sql.toString();
	}

}
