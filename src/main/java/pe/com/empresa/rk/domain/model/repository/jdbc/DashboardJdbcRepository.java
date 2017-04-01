package pe.com.empresa.rk.domain.model.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.com.empresa.rk.domain.model.entities.Marcacion;
import pe.com.empresa.rk.domain.model.repository.jpa.MarcacionJpaRepository;
import pe.com.empresa.rk.support.WhereParams;
import pe.com.empresa.rk.util.DateUtil;
import pe.com.empresa.rk.view.model.*;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class DashboardJdbcRepository {
	

    @Autowired
    DataSource dataSource;

    private NamedParameterJdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
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
		if(filterViewModel.getTipoMarcacion().equals("0") || filterViewModel.getTipoMarcacion().equals("1") || filterViewModel.getTipoMarcacion().equals("2")){
			String sql = generarVerBusquedaMarcacionesDashboardRRHHTipoMarcacion(filterViewModel, params);
			return jdbcTemplate.query(sql,
	                params.getParams(), new BeanPropertyRowMapper<>(MarcacionDashboardResultViewModel.class));
		}

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

        sql.append(" EMP.ApellidoPaterno+' '+EMP.ApellidoMaterno+', '+EMP.Nombre AS nombreCompletoEmpleado, ");
        sql.append(" EMP.Codigo AS codigoEmpleado ");
        sql.append(" FROM Marcacion MAR ");
        sql.append(" LEFT JOIN Empleado EMP ON EMP.IdEmpleado = MAR.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMP.IdEmpleado ");

        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append(" WHERE 1=1 ");

        sql.append(params.filter(" AND UN.IdUnidadDeNegocio = :unidadNegocio ", filterViewModel.getUnidadNegocio()));
        sql.append(params.filter(" AND DEP.IdDepartamentoArea = :departamento ", filterViewModel.getDepartamento()));
        sql.append(params.filter(" AND PROY.IdProyecto = :proyecto ", filterViewModel.getProyecto()));

        sql.append(params.filter(" AND MAR.Fecha = :fecha" , filterViewModel.getFecha()));
        //CLICL PIECHART RRHH
        if("0".equals(filterViewModel.getTipoMarcacion())){
        	// 0 = Nro. De Empleados que marcaron a tiempo',
        	sql.append(" AND MAR.Tardanza = 0 AND MAR.HoraIngreso is NOT NULL ");
        }
        if("1".equals(filterViewModel.getTipoMarcacion())){
        	// 1 = 'Nro. De Empleados que marcaron fuera de tiempo'
        	sql.append(" AND MAR.Tardanza = 1 AND MAR.HoraIngreso is NOT NULL ");
        }
        if("2".equals(filterViewModel.getTipoMarcacion())){
        	// 2 = 'Nro. De Empleados sin marcaciones';
        	sql.append(" AND MAR.HoraIngreso IS NULL ");
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

        sql.append(" EMP.ApellidoPaterno+' '+EMP.ApellidoMaterno+', '+EMP.Nombre AS nombreCompletoEmpleado, ");
        sql.append(" EMP.Codigo AS codigoEmpleado ");
        sql.append(" FROM Marcacion MAR ");
        sql.append(" LEFT JOIN Empleado EMP ON EMP.IdEmpleado = MAR.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMP.IdEmpleado ");

        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append(" WHERE 1=1 ");

        sql.append(params.filter(" AND UN.IdUnidadDeNegocio = :unidadNegocio ", busquedaMarcacionDto.getUnidadNegocio()));
        sql.append(params.filter(" AND DEP.IdDepartamentoArea = :departamento ", busquedaMarcacionDto.getDepartamento()));
        sql.append(params.filter(" AND PROY.IdProyecto = :proyecto ", busquedaMarcacionDto.getProyecto()));

        sql.append(params.filter(" AND MAR.Fecha = :fecha" , busquedaMarcacionDto.getFecha()));
        sql.append(" Order by nombreCompletoEmpleado asc,MAR.Fecha desc ");

		return sql.toString();
	}

	public List<PieChartDataResultViewModel> busquedaPieChartDashboardRHHH(MarcacionDashboardFilterViewModel busquedaMarcacionDto) {
		//TODO revisar luego este query
	    //WhereParams params = new WhereParams();
        //String sql = busquedaPieChartDashboardRRHH(busquedaMarcacionDto, params);

        //return jdbcTemplate.query(sql,
          //      params.getParams(), new BeanPropertyRowMapper<>(PieChartDataResultViewModel.class));
        return new ArrayList<>();
	}

	private String busquedaPieChartDashboardRRHH(MarcacionDashboardFilterViewModel busquedaMarcacionDto,WhereParams params) {
		StringBuilder sql = new StringBuilder();

		sql.append(" Select ");
		sql.append(" distinct nroEmpleadosMarcacionFueraTiempo = (select count(prr.Tardanza) ");
		sql.append(" FROM Marcacion prr LEFT JOIN Empleado EMP ON EMP.IdEmpleado = prr.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMP.IdEmpleado ");
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append(" WHERE 1=1 ");
        sql.append(params.filter(" AND UN.IdUnidadDeNegocio = :unidadNegocio ", busquedaMarcacionDto.getUnidadNegocio()));
        sql.append(params.filter(" AND DEP.IdDepartamentoArea = :departamento ", busquedaMarcacionDto.getDepartamento()));
        sql.append(params.filter(" AND PROY.IdProyecto = :proyecto ", busquedaMarcacionDto.getProyecto()));
        sql.append(" AND prr.Tardanza = 1 AND prr.HoraIngreso is NOT NULL ");
		sql.append(params.filter(" AND prr.Fecha = :fecha ", busquedaMarcacionDto.getFecha()));


		sql.append(" ),nroEmpleadosMarcacionTiempo = (select count(prr.Tardanza) ");
		sql.append(" FROM Marcacion prr LEFT JOIN Empleado EMP ON EMP.IdEmpleado = prr.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMP.IdEmpleado ");
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append(" WHERE 1=1 ");
		sql.append(params.filter(" AND UN.IdUnidadDeNegocio = :unidadNegocio ", busquedaMarcacionDto.getUnidadNegocio()));
        sql.append(params.filter(" AND DEP.IdDepartamentoArea = :departamento ", busquedaMarcacionDto.getDepartamento()));
		sql.append(params.filter(" AND PROY.IdProyecto = :proyecto ", busquedaMarcacionDto.getProyecto()));
		sql.append(" AND prr.Tardanza = 0 AND prr.HoraIngreso is NOT NULL ");
        sql.append(params.filter(" AND prr.Fecha = :fecha ", busquedaMarcacionDto.getFecha()));

        sql.append(" ),nroEmpleadosSinMarcacion = (select count(prr.Inasistencia) ");
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
        sql.append(params.filter(" AND prr.Fecha = :fecha ", busquedaMarcacionDto.getFecha()));
        sql.append(" ) ");

		return sql.toString();
	}

	public List<PermisoEmpleadoResultViewModel> searchPermisoDashboardJefe(
			MarcacionDashboardFilterViewModel busquedaMarcacionDto) {
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
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMPLEADO.IdEmpleado AND (HISTORIAL.FechaInicio < NOW()" +
                " OR HISTORIAL.FechaFin > NOW()) ");
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append(" LEFT JOIN Empleado ep on PROY.IdJefe = ep.IdEmpleado ");
		sql.append(" LEFT JOIN Empleado eda on DEP.IdJefe = eda.IdEmpleado ");
		sql.append(" LEFT JOIN Empleado eun on UN.IdJefe = eun.IdEmpleado ");
        sql.append(" Where 1=1 ");
        sql.append(params.filter(" AND (UN.IdJefe = :idJefe OR DEP.IdJefe = :idJefe OR PROY.IdJefe = :idJefe )",busquedaPermisoEmpleadoDto.getIdEmpleado()));
        sql.append(" AND MONTH(pe.Fecha) = MONTH(NOW()) AND YEAR(pe.Fecha) = YEAR(NOW()) ");
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
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMPLEADO.IdEmpleado AND ((HISTORIAL.FechaInicio < NOW() AND HISTORIAL.FechaFin > NOW()) OR (HISTORIAL.FechaInicio < NOW() AND HISTORIAL.FechaFin IS NULL)) ");
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append("  LEFT JOIN Empleado ep on PROY.IdJefe = ep.IdEmpleado ");
		sql.append("  LEFT JOIN Empleado eda on DEP.IdJefe = eda.IdEmpleado ");
		sql.append("  LEFT JOIN Empleado eun on UN.IdJefe = eun.IdEmpleado ");
        sql.append(" where 1=1 ");
        sql.append(" AND MONTH(va.FechaCreacion) = MONTH(NOW()) and YEAR(va.FechaCreacion) = YEAR(NOW()) ");
        sql.append(params.filter(" AND ( "+
        		" (HISTORIAL.IdProyecto IS NOT NULL AND PROY.IdJefe = :idJefeInmediato)  OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NOT NULL AND DEP.IdJefe  = :idJefeInmediato)"+
        		" OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NULL AND HISTORIAL.IdUnidadDeNegocio IS NOT NULL AND UN.IdJefe = :idJefeInmediato) ) ", filterViewModel.getIdEmpleado()));
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

	private String searchHorasExtrasDashboardJefeQuery(MarcacionDashboardFilterViewModel filterViewModel, WhereParams params) {

		StringBuilder sql = new StringBuilder();
        sql.append(" SELECT distinct he.IdHorasExtra AS idHorasExtra, ");
        sql.append(" he.HoraSalidaSolicitado AS horaSalidaSolicitado, ");
        sql.append(" he.HoraSalidaHorario AS horaSalidaHorario, ");
        sql.append(" CONCAT(EMPLEADO.ApellidoPaterno, ' ', EMPLEADO.ApellidoMaterno, ', ', EMPLEADO.Nombre) as nombreEmpleado, ");
        sql.append(" he.Fecha as fecha, ");
        sql.append(" he.Horas as horas, ");
        sql.append(" he.HorasNoCompensables AS horasNoCompensables, ");
        sql.append(" ESTADO.Nombre AS estado ");
        sql.append(" from HorasExtra he ") ;
        sql.append(" LEFT JOIN TablaGeneral ESTADO ON he.Estado=ESTADO.Codigo and ESTADO.GRUPO='HorasExtra.Estado'");
        sql.append(" LEFT JOIN Empleado EMPLEADO ON he.IdEmpleado = EMPLEADO.IdEmpleado ");
        sql.append(" LEFT JOIN Empleado ATENDIDO ON he.IdAtendidoPor = ATENDIDO.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = he.IdEmpleado AND ((HISTORIAL.FechaInicio < NOW() AND HISTORIAL.FechaFin > NOW()) OR (HISTORIAL.FechaInicio < NOW() AND HISTORIAL.FechaFin IS NULL)) ");
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append("  LEFT JOIN Empleado ep on PROY.IdJefe = ep.IdEmpleado ");
		sql.append("  LEFT JOIN Empleado eda on DEP.IdJefe = eda.IdEmpleado ");
		sql.append("  LEFT JOIN Empleado eun on UN.IdJefe = eun.IdEmpleado ");
        sql.append(" where 1=1 ");
        sql.append(params.filter(" AND (UN.IdJefe = :idJefe OR DEP.IdJefe = :idJefe OR PROY.IdJefe = :idJefe )",filterViewModel.getIdEmpleado()));
        sql.append(" AND MONTH(he.Fecha) = MONTH(NOW()) AND YEAR(he.Fecha) = YEAR(NOW()) ");
        sql.append(" ORDER BY nombreEmpleado");

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

        sql.append(" EMP.ApellidoPaterno+' '+EMP.ApellidoMaterno+', '+EMP.Nombre AS nombreCompletoEmpleado, ");
        sql.append(" EMP.Codigo AS codigoEmpleado ");
        sql.append(" FROM Marcacion MAR ");
        sql.append(" LEFT JOIN Empleado EMP ON EMP.IdEmpleado = MAR.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMP.IdEmpleado ");

        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append(" WHERE 1=1 ");
        sql.append(params.filter(" AND UN.IdUnidadDeNegocio = :unidadNegocio ", busquedaMarcacionDto.getUnidadNegocio()));
        sql.append(params.filter(" AND DEP.IdDepartamentoArea = :departamento ", busquedaMarcacionDto.getDepartamento()));
        sql.append(params.filter(" AND PROY.IdProyecto = :proyecto ", busquedaMarcacionDto.getProyecto()));
        sql.append(params.filter(" AND MAR.Fecha = :fecha" , busquedaMarcacionDto.getFecha()));
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
                    || "2".equals(busquedaMarcacionDto.getTipoMarcacion())){
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

        sql.append(" EMP.ApellidoPaterno+' '+EMP.ApellidoMaterno+', '+EMP.Nombre AS nombreCompletoEmpleado, ");
        sql.append(" EMP.Codigo AS codigoEmpleado ");
        sql.append(" FROM Marcacion MAR ");
        sql.append(" LEFT JOIN Empleado EMP ON EMP.IdEmpleado = MAR.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMP.IdEmpleado ");

        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
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
        	sql.append(" AND MAR.Tardanza = 0 AND MAR.HoraIngreso is NOT NULL ");
        }
        if(busquedaMarcacionDto.getTipoMarcacion().equals("1")){
        	// 1 = 'Nro. De Empleados que marcaron fuera de tiempo'
        	sql.append(" AND MAR.Tardanza = 1 AND MAR.HoraIngreso is NOT NULL ");
        }
        if(busquedaMarcacionDto.getTipoMarcacion().equals("2")){
        	// 2 = 'Nro. De Empleados sin marcaciones';
        	sql.append(" AND MAR.HoraIngreso IS NULL ");
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

        sql.append(" EMP.ApellidoPaterno+' '+EMP.ApellidoMaterno+', '+EMP.Nombre AS nombreCompletoEmpleado, ");

        sql.append(" EMP.Codigo AS codigoEmpleado ");
        sql.append(" FROM Marcacion MAR ");
        sql.append(" LEFT JOIN Empleado EMP ON EMP.IdEmpleado = MAR.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMP.IdEmpleado ");

        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
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

        sql.append(" EMP.ApellidoPaterno+' '+EMP.ApellidoMaterno+', '+EMP.Nombre AS nombreCompletoEmpleado, ");

        sql.append(" EMP.Codigo AS codigoEmpleado ");
        sql.append(" FROM Marcacion MAR ");
        sql.append(" LEFT JOIN Empleado EMP ON EMP.IdEmpleado = MAR.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMP.IdEmpleado ");

        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append(" WHERE 1=1 ");
        sql.append(params.filter(" AND EMP.IdEmpleado = :idEmpleado ", busquedaMarcacionDto.getIdEmpleado()));
        if("0".equals(busquedaMarcacionDto.getTipoMarcacion())){
        	// 0 = Nro. De Empleados que marcaron a tiempo',
        	sql.append(" AND MAR.Tardanza = 0 AND MAR.HoraIngreso is NOT NULL ");
        }
        if("1".equals(busquedaMarcacionDto.getTipoMarcacion())){
        	// 1 = 'Nro. De Empleados que marcaron fuera de tiempo'
        	sql.append(" AND MAR.Tardanza = 1 AND MAR.HoraIngreso is NOT NULL ");
        }
        if("2".equals(busquedaMarcacionDto.getTipoMarcacion())){
        	// 2 = 'Nro. De Empleados sin marcaciones';
        	sql.append(" AND MAR.HoraIngreso IS NULL AND MAR.Inasistencia = 1 OR MAR.Vacaciones = 1 OR MAR.Licencia = 1 ");
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
		sql.append("  WHEN (HISTORIAL.IdProyecto IS NOT NULL) THEN ep.ApellidoPaterno +' '+ep.ApellidoMaterno +', '+ep.Nombre ");
		sql.append("  WHEN (HISTORIAL.IdProyecto IS NULL AND HISTORIAL.IdDepartamentoArea IS NOT NULL) THEN eda.ApellidoPaterno +' '+eda.ApellidoMaterno +', '+eda.Nombre ");
		sql.append("  WHEN (HISTORIAL.IdProyecto IS NULL AND HISTORIAL.IdDepartamentoArea IS NULL AND HISTORIAL.IdUnidadDeNegocio IS NOT NULL) THEN eun.ApellidoPaterno +' '+eun.ApellidoMaterno +', '+eun.Nombre ");
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
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMPLEADO.IdEmpleado AND ((HISTORIAL.FechaInicio < NOW() AND HISTORIAL.FechaFin > NOW()) OR (HISTORIAL.FechaInicio < NOW() AND HISTORIAL.FechaFin IS NULL)) ");
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append(" LEFT JOIN Empleado ep on PROY.IdJefe = ep.IdEmpleado ");
		sql.append(" LEFT JOIN Empleado eda on DEP.IdJefe = eda.IdEmpleado ");
		sql.append(" LEFT JOIN Empleado eun on UN.IdJefe = eun.IdEmpleado ");
        sql.append(" Where 1=1 ");
        sql.append(" AND MONTH(pe.Fecha) = MONTH(NOW()) AND YEAR(pe.Fecha) = YEAR(NOW()) ");
        sql.append(params.filter(" AND EMPLEADO.IdEmpleado = :idEmpleado ", busquedaMarcacionDto.getIdEmpleado()));
        sql.append(" ORDER BY nombreEmpleado");

        return sql.toString();

	}

	public List<VacacionResultViewModel> searchVacacionesEmpleadoDashboardEmpleado(
			MarcacionDashboardEmpleadoFilterViewModel marcacionDashboardEmpleadoDto) {
		// TODO Auto-generated method stub
		WhereParams params = new WhereParams();
        String sql = generarBusquedaVacacionesDashboardEmpleadoQuery(marcacionDashboardEmpleadoDto, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<>(VacacionResultViewModel.class));
	}

	private String generarBusquedaVacacionesDashboardEmpleadoQuery(
			MarcacionDashboardEmpleadoFilterViewModel marcacionDashboardEmpleadoDto, WhereParams params) {
		// TODO Auto-generated method stub
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

        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMPLEADO.IdEmpleado AND ((HISTORIAL.FechaInicio < NOW() AND HISTORIAL.FechaFin > NOW()) OR (HISTORIAL.FechaInicio < NOW() AND HISTORIAL.FechaFin IS NULL)) ");

        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");

        sql.append("  LEFT JOIN Empleado ep on PROY.IdJefe = ep.IdEmpleado ");
		sql.append("  LEFT JOIN Empleado eda on DEP.IdJefe = eda.IdEmpleado ");
		sql.append("  LEFT JOIN Empleado eun on UN.IdJefe = eun.IdEmpleado ");

        sql.append(" where 1=1 ");
        sql.append(" AND MONTH(va.FechaCreacion) = MONTH(NOW()) and YEAR(va.FechaCreacion) = YEAR(NOW()) ");
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
        sql.append(" he.HoraSalidaHorario AS horaSalidaHorario, ");
        sql.append(" he.HorasNoCompensables AS horasNoCompensables, ");
        sql.append(" CONCAT(ATENDIDO.ApellidoPaterno, ' ', ATENDIDO.ApellidoMaterno, ', ', ATENDIDO.Nombre) as nombreJefeInmediato, ");
        sql.append(" CONCAT(EMPLEADO.ApellidoPaterno, ' ', EMPLEADO.ApellidoMaterno, ', ', EMPLEADO.Nombre) as nombreEmpleado, ");
        sql.append(" he.Fecha as fecha, ");
        sql.append(" he.Horas as horas, ");
        sql.append(" ESTADO.Nombre AS estado ");
        sql.append(" from HorasExtra he ") ;
        sql.append(" LEFT JOIN TablaGeneral ESTADO ON he.Estado=ESTADO.Codigo and ESTADO.GRUPO='HorasExtra.Estado'");
        sql.append(" LEFT JOIN Empleado EMPLEADO ON he.IdEmpleado = EMPLEADO.IdEmpleado ");
        sql.append(" LEFT JOIN Empleado ATENDIDO ON he.IdAtendidoPor = ATENDIDO.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = he.IdEmpleado AND ((HISTORIAL.FechaInicio < NOW() AND HISTORIAL.FechaFin > NOW()) OR (HISTORIAL.FechaInicio < NOW() AND HISTORIAL.FechaFin IS NULL)) ");
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append(" where 1=1 ");
        sql.append(" AND MONTH(he.Fecha) = MONTH(NOW()) AND YEAR(he.Fecha) = YEAR(NOW()) ");
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
	  sql.append(" distinct nroEmpleadosMarcacionFueraTiempo = (select count(prr.Tardanza) FROM Marcacion prr LEFT JOIN Empleado EMP ON EMP.IdEmpleado = prr.IdEmpleado ");
	  sql.append(" where prr.Tardanza = 1 AND prr.HoraIngreso is NOT NULL ");
	  sql.append(params.filterDateDesde_US(" AND prr.Fecha " , busquedaMarcacionDto.getDesde()));
      sql.append(params.filterDateHasta_US(" AND prr.Fecha " , busquedaMarcacionDto.getHasta()));
      sql.append(params.filter(" AND EMP.IdEmpleado = :idEmpleado ", busquedaMarcacionDto.getIdEmpleado()));

      sql.append(" ),nroEmpleadosMarcacionTiempo = (select count(prr.Tardanza) FROM Marcacion prr LEFT JOIN Empleado EMP ON EMP.IdEmpleado = prr.IdEmpleado ");
      sql.append(" where prr.Tardanza = 0 AND prr.HoraIngreso is NOT NULL ");
      sql.append(params.filterDateDesde_US(" AND prr.Fecha " , busquedaMarcacionDto.getDesde()));
      sql.append(params.filterDateHasta_US(" AND prr.Fecha " , busquedaMarcacionDto.getHasta()));
      sql.append(params.filter(" AND EMP.IdEmpleado = :idEmpleado ", busquedaMarcacionDto.getIdEmpleado()));

      sql.append(" ),nroEmpleadosSinMarcacion = (select count(prr.Inasistencia) FROM Marcacion prr LEFT JOIN Empleado EMP ON EMP.IdEmpleado = prr.IdEmpleado ");
      sql.append(" where prr.HoraIngreso IS NULL AND (prr.Inasistencia = 1 OR prr.Vacaciones = 1 OR prr.Licencia = 1) ");
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
		sql.append(" distinct nroEmpleadosMarcacionFueraTiempo = (select count(prr.Tardanza) ");
		sql.append(" FROM Marcacion prr LEFT JOIN Empleado EMP ON EMP.IdEmpleado = prr.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMP.IdEmpleado ");
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append(" WHERE 1=1 ");
        sql.append(params.filter(" AND UN.IdUnidadDeNegocio = :unidadNegocio ", busquedaMarcacionDto.getUnidadNegocio()));
        sql.append(params.filter(" AND DEP.IdDepartamentoArea = :departamento ", busquedaMarcacionDto.getDepartamento()));
        sql.append(params.filter(" AND PROY.IdProyecto = :proyecto ", busquedaMarcacionDto.getProyecto()));
        sql.append(" AND prr.Tardanza = 1 AND prr.HoraIngreso IS NOT NULL ");
		sql.append(params.filter(" AND prr.Fecha = :fecha ", busquedaMarcacionDto.getFecha()));
		sql.append(params.filter(" AND ( "+
        		" (HISTORIAL.IdProyecto IS NOT NULL AND PROY.IdJefe = :idJefeInmediato)  OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NOT NULL AND DEP.IdJefe  = :idJefeInmediato)"+
        		" OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NULL AND HISTORIAL.IdUnidadDeNegocio IS NOT NULL AND UN.IdJefe = :idJefeInmediato) ) ", busquedaMarcacionDto.getIdEmpleado()));


		sql.append(" ),nroEmpleadosMarcacionTiempo = (select count(prr.Tardanza) ");
        sql.append(" FROM Marcacion prr LEFT JOIN Empleado EMP ON EMP.IdEmpleado = prr.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMP.IdEmpleado ");
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append(" WHERE 1=1 ");
        sql.append(params.filter(" AND UN.IdUnidadDeNegocio = :unidadNegocio ", busquedaMarcacionDto.getUnidadNegocio()));
        sql.append(params.filter(" AND DEP.IdDepartamentoArea = :departamento ", busquedaMarcacionDto.getDepartamento()));
        sql.append(params.filter(" AND PROY.IdProyecto = :proyecto ", busquedaMarcacionDto.getProyecto()));
        sql.append(" AND prr.Tardanza = 0 AND prr.HoraIngreso is NOT NULL ");
        sql.append(params.filter(" AND prr.Fecha = :fecha ", busquedaMarcacionDto.getFecha()));
        sql.append(params.filter(" AND ( "+
        		" (HISTORIAL.IdProyecto IS NOT NULL AND PROY.IdJefe = :idJefeInmediato)  OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NOT NULL AND DEP.IdJefe  = :idJefeInmediato)"+
        		" OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NULL AND HISTORIAL.IdUnidadDeNegocio IS NOT NULL AND UN.IdJefe = :idJefeInmediato) ) ", busquedaMarcacionDto.getIdEmpleado()));


        sql.append(" ),nroEmpleadosSinMarcacion = (select count(prr.Inasistencia) ");
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
        sql.append(" WHEN  (pe.Estado='R') THEN 'Rechazao' ");
        sql.append(" ELSE 'No Asociado' ");
		sql.append(" END AS estado ");
        sql.append(" From PermisoEmpleado pe ") ;
        sql.append(" LEFT JOIN TablaGeneral ESTADO ON pe.Estado=ESTADO.Codigo and ESTADO.GRUPO='Permiso.Estado'");
        sql.append(" LEFT JOIN TablaGeneral MOTIVO ON pe.MOTIVO=MOTIVO.Codigo and MOTIVO.GRUPO='Permiso.Tipo'");
        sql.append(" LEFT JOIN PeriodoEmpleado PERIODO_EMPLEADO ON pe.IdPeriodoEmpleado = PERIODO_EMPLEADO.IdPeriodoEmpleado ");
        sql.append(" LEFT JOIN Empleado EMPLEADO ON PERIODO_EMPLEADO.IdEmpleado = EMPLEADO.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMPLEADO.IdEmpleado AND ((HISTORIAL.FechaInicio < NOW() AND HISTORIAL.FechaFin > NOW()) OR (HISTORIAL.FechaInicio < NOW() AND HISTORIAL.FechaFin IS NULL)) ");
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append(" Where 1=1 ");
        sql.append(" AND MONTH(pe.Fecha) = MONTH(NOW()) AND YEAR(pe.Fecha) = YEAR(NOW()) ");
        sql.append(" ORDER BY nombreEmpleado");
        return sql.toString();
	}

	public List<PieChartDataResultViewModel> buscarEmpleadoIndicadorDashBoardXmes(MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto) {
		WhereParams params = new WhereParams();
        String sql = buscarEmpleadoIndicadorDashBoardXmesQuery(busquedaMarcacionDto, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<>(PieChartDataResultViewModel.class));
	}

	private String buscarEmpleadoIndicadorDashBoardXmesQuery(
			MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		Date fechaActual = new Date();

		  sql.append(" Select ");
		  sql.append(" distinct nroEmpleadosMarcacionFueraTiempo = (select count(prr.Tardanza) FROM Marcacion prr LEFT JOIN Empleado EMP ON EMP.IdEmpleado = prr.IdEmpleado ");
		  sql.append(" where prr.Tardanza = 1 AND prr.HoraIngreso is NOT NULL ");
		  sql.append(" AND MONTH(prr.Fecha) = MONTH(NOW()) AND YEAR(prr.Fecha) = YEAR(NOW()) ");
	      sql.append(params.filter(" AND EMP.IdEmpleado = :idEmpleado ", busquedaMarcacionDto.getIdEmpleado()));

	      sql.append(" ),nroEmpleadosMarcacionTiempo = (select count(prr.Tardanza) FROM Marcacion prr LEFT JOIN Empleado EMP ON EMP.IdEmpleado = prr.IdEmpleado ");
	      sql.append(" where prr.Tardanza = 0 AND prr.HoraIngreso is NOT NULL ");
	      sql.append(" AND prr.Fecha = NOW() ");
	      sql.append(params.filter(" AND EMP.IdEmpleado = :idEmpleado ", busquedaMarcacionDto.getIdEmpleado()));

	      sql.append(" ),nroEmpleadosSinMarcacion = (select count(prr.Inasistencia) FROM Marcacion prr LEFT JOIN Empleado EMP ON EMP.IdEmpleado = prr.IdEmpleado ");
	      sql.append(" where prr.HoraIngreso IS NULL AND (prr.Inasistencia = 1 OR prr.Vacaciones = 1 OR prr.Licencia = 1) ");
	      sql.append(" AND MONTH(prr.Fecha) = MONTH(NOW()) AND YEAR(prr.Fecha) = YEAR(NOW()) ");
	      sql.append(params.filter(" AND EMP.IdEmpleado = :idEmpleado ", busquedaMarcacionDto.getIdEmpleado()));

	      sql.append(" ),countIsVacacion = (SELECT count(prr.Vacaciones) FROM Marcacion prr LEFT JOIN Empleado EMP ON EMP.IdEmpleado = prr.IdEmpleado ");
	      sql.append(" WHERE 1=1 AND prr.Vacaciones = 1 AND prr.HoraIngreso IS NULL ");
	      sql.append(params.filter(" AND prr.Fecha =:fechaActual ", fechaActual));
	      sql.append(params.filter(" AND EMP.IdEmpleado = :idEmpleado ", busquedaMarcacionDto.getIdEmpleado()));

	      sql.append(" ),countIsLicencia = (SELECT count(prr.Licencia) FROM Marcacion prr LEFT JOIN Empleado EMP ON EMP.IdEmpleado = prr.IdEmpleado ");
	      sql.append(" WHERE 1=1 AND prr.Licencia = 1 AND prr.HoraIngreso IS NULL ");
	      sql.append(params.filter(" AND prr.Fecha =:fechaActual ", fechaActual));
	      sql.append(params.filter(" AND EMP.IdEmpleado = :idEmpleado ", busquedaMarcacionDto.getIdEmpleado()));

	      sql.append(" ),countIsInasistencia = (SELECT count(prr.Inasistencia) FROM Marcacion prr LEFT JOIN Empleado EMP ON EMP.IdEmpleado = prr.IdEmpleado ");
	      sql.append(" WHERE 1=1 AND prr.Inasistencia = 1 AND prr.EsPersonaDeConfianza = 0 AND prr.HoraIngreso IS NULL ");
	      sql.append(params.filter(" AND prr.Fecha =:fechaActual ", fechaActual));
	      sql.append(params.filter(" AND EMP.IdEmpleado = :idEmpleado ", busquedaMarcacionDto.getIdEmpleado()));

	      sql.append(" ),countSinMarcacion = (SELECT count(prr.IdMarcacion) FROM Marcacion prr LEFT JOIN Empleado EMP ON EMP.IdEmpleado = prr.IdEmpleado ");
	      sql.append(" WHERE 1=1 AND prr.EsPersonaDeConfianza = 1 AND prr.HoraIngreso IS NULL ");
	      sql.append(params.filter(" AND prr.Fecha =:fechaActual ", fechaActual));
	      sql.append(params.filter(" AND EMP.IdEmpleado = :idEmpleado ", busquedaMarcacionDto.getIdEmpleado()));
	      //compensacion
	      sql.append(" ),horasPendientesCompensar = (SELECT e.HorasPendientesTotal FROM EmpleadoCompensacion e ");
	      sql.append(" WHERE 1=1 ");
	      sql.append(params.filter(" AND e.IdEmpleado = :idEmpleado ", busquedaMarcacionDto.getIdEmpleado()));
	      //isPersonalConfianza
	      sql.append(" ),isPersonalConfianza = (SELECT e.EsPersonalDeConfianza FROM Empleado e ");
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
        sql.append(" AND MONTH(pe.Fecha) = MONTH(NOW()) AND YEAR(pe.Fecha) = YEAR(NOW()) ");
        sql.append(params.filter(" AND pe.IdEmpleado = :idEmpleado ", busquedaMarcacionDto.getIdEmpleado()));
        return sql.toString();
	}

	public List<IndicadorRRHHResultViewModel> buscarIndicadorRRHHDashBoard(MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto) {
		//TODO Revisar luego este query
	    //WhereParams params = new WhereParams();
        //String sql = buscarIndicadorRRHHDashBoardQuery(busquedaMarcacionDto, params);

        //return jdbcTemplate.query(sql,
        //        params.getParams(), new BeanPropertyRowMapper<>(IndicadorRRHHResultViewModel.class));
        return new ArrayList<>();
	}

	private String buscarIndicadorRRHHDashBoardQuery(MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto,
			WhereParams params) {
		  StringBuilder sql = new StringBuilder();

	      Date fechaActual = new Date();

		  sql.append(" Select ");
		  sql.append(" distinct countEmpleadoVacacionHoy = (select count(prr.IdVacacion) FROM Vacacion prr where 1=1 ");
		  sql.append(params.filterDate_US_Beteen(" AND ", fechaActual));
		  sql.append(" BETWEEN prr.FechaInicio and prr.FechaFin ");

		  //MODIFICAR ESTO
	      sql.append(" ),countTardanzasPromedioAlDiaxMes = (select count(prr.Tardanza) FROM Marcacion prr LEFT JOIN Empleado EMP ON EMP.IdEmpleado = prr.IdEmpleado ");
	      sql.append(" where prr.Tardanza = 1 AND prr.HoraIngreso is NOT NULL ");
	      sql.append(" AND MONTH(prr.Fecha) = MONTH(NOW()) AND YEAR(prr.Fecha) = YEAR(NOW()) ");

	      sql.append(" ),countBirthdayByMonth = (select count(e.IdEmpleado) FROM Empleado e WHERE MONTH(e.FechaNacimiento) = MONTH(NOW()) ");

	      sql.append(" ),countAltasLastYear = (select count(e.IdEmpleado) from Empleado e where 1=1 AND e.Estado = 'A' AND YEAR(e.FechaIngreso) > YEAR(NOW())-2 ");

	      sql.append(" ),countEmpleadoEmpresa = (select count(e.IdEmpleado) from Empleado e where 1=1 AND e.Estado = 'A' ");

	      sql.append(" ),countContratoxVencer = (select count(e.IdContrato) from Contrato e where MONTH(e.FechaFin) = MONTH(NOW()) ");

	      sql.append(" ),countEmpleadoLicenciaByDay = (select count(e.IdLicencia) from Licencia e where 1=1 ");
	      sql.append(params.filterDate_US_Beteen(" AND ", fechaActual));
	      sql.append(" BETWEEN e.FechaInicio and e.FechaFin ");

	      sql.append(" ),countInasistenciasxMes = (select count(prr.Inasistencia) FROM Marcacion prr LEFT JOIN Empleado EMP ON EMP.IdEmpleado = prr.IdEmpleado ");
	      sql.append(" where 1=1 AND prr.HoraIngreso IS NULL AND (prr.Inasistencia = 1) ");
	      sql.append(" AND MONTH(prr.Fecha) = MONTH(NOW()) AND YEAR(prr.Fecha) = YEAR(NOW()) ");

	      sql.append(" ),countLicenciaxMes = (select count(prr.IdMarcacion) from Marcacion prr ");
	      sql.append(" where 1=1 AND prr.HoraIngreso IS NULL AND (prr.Licencia = 1) ");
	      sql.append(" AND MONTH(prr.Fecha) = MONTH(NOW()) AND YEAR(prr.Fecha) = YEAR(NOW()) ");

	      sql.append(" ),countBajasLastYear = (select count(e.IdEmpleado) from Empleado e where 1=1 AND e.Estado = 'I' AND YEAR(e.FechaCese) > YEAR(NOW())-2 ");
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
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMPLEADO.IdEmpleado AND ((HISTORIAL.FechaInicio < NOW() AND HISTORIAL.FechaFin > NOW()) OR (HISTORIAL.FechaInicio < NOW() AND HISTORIAL.FechaFin IS NULL)) ");
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append(" where 1=1 ");
        sql.append(" AND MONTH(va.FechaCreacion) = MONTH(NOW()) and YEAR(va.FechaCreacion) = YEAR(NOW()) ");
        sql.append(" ORDER BY nombreEmpleado");

        return sql.toString();
	}

	public List<HorasExtraEmpleadoResultViewModel> searchHorasExtrasDashboardRRHH(MarcacionDashboardFilterViewModel marcacionDashboardEmpleadoDto) {
		WhereParams params = new WhereParams();
        String sql = searchHorasExtrasDashboardRRHHQuery(marcacionDashboardEmpleadoDto, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<>(HorasExtraEmpleadoResultViewModel.class));
	}

	private String searchHorasExtrasDashboardRRHHQuery(MarcacionDashboardFilterViewModel marcacionDashboardEmpleadoDto,
			WhereParams params) {

		StringBuilder sql = new StringBuilder();
        sql.append(" SELECT distinct he.IdHorasExtra AS idHorasExtra, ");
        sql.append(" CONCAT(EMPLEADO.ApellidoPaterno, ' ', EMPLEADO.ApellidoMaterno, ', ', EMPLEADO.Nombre) as nombreEmpleado, ");
        sql.append(" he.Fecha as fecha, ");
        sql.append(" he.HoraSalidaSolicitado AS horaSalidaSolicitado, ");
        sql.append(" he.HoraSalidaHorario AS horaSalidaHorario, ");
        sql.append(" he.HorasNoCompensables AS horasNoCompensables, ");
        sql.append(" he.Horas as horas, ");
        sql.append(" ESTADO.Nombre AS estado ");
        sql.append(" from HorasExtra he ") ;
        sql.append(" LEFT JOIN TablaGeneral ESTADO ON he.Estado=ESTADO.Codigo and ESTADO.GRUPO='HorasExtra.Estado'");
        sql.append(" LEFT JOIN Empleado EMPLEADO ON he.IdEmpleado = EMPLEADO.IdEmpleado ");
        sql.append(" LEFT JOIN Empleado ATENDIDO ON he.IdAtendidoPor = ATENDIDO.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = he.IdEmpleado AND " +
                "((HISTORIAL.FechaInicio < NOW() AND HISTORIAL.FechaFin > NOW()) " +
                "OR (HISTORIAL.FechaInicio < NOW() AND HISTORIAL.FechaFin IS NULL)) ");
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append(" where 1=1 ");
        sql.append(" AND MONTH(he.Fecha) = MONTH(NOW()) AND YEAR(he.Fecha) = YEAR(NOW()) ");
        sql.append(" ORDER BY nombreEmpleado");
        return sql.toString();
	}

}
