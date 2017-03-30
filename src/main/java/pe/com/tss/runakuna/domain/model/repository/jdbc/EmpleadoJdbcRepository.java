package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.math.BigDecimal;
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
import pe.com.tss.runakuna.view.model.DependienteViewModel;
import pe.com.tss.runakuna.view.model.EducacionViewModel;
import pe.com.tss.runakuna.view.model.EmpleadoFilterViewModel;
import pe.com.tss.runakuna.view.model.EmpleadoResultViewModel;
import pe.com.tss.runakuna.view.model.EmpleadoViewModel;
import pe.com.tss.runakuna.view.model.EquipoEntregadoViewModel;
import pe.com.tss.runakuna.view.model.HorarioDiaViewModel;
import pe.com.tss.runakuna.view.model.HorarioEmpleadoDiaViewModel;
import pe.com.tss.runakuna.view.model.HorarioEmpleadoResultViewModel;
import pe.com.tss.runakuna.view.model.HorarioEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.HorasExtraEmpleadoFilterViewModel;
import pe.com.tss.runakuna.view.model.HorasExtraEmpleadoResultViewModel;
import pe.com.tss.runakuna.view.model.HorasExtraViewModel;
import pe.com.tss.runakuna.view.model.LicenciaViewModel;
import pe.com.tss.runakuna.view.model.MarcacionFilterViewModel;
import pe.com.tss.runakuna.view.model.MarcacionQuickFilterViewModel;
import pe.com.tss.runakuna.view.model.MarcacionViewModel;
import pe.com.tss.runakuna.view.model.PeriodoEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.PermisoEmpleadoFilterViewModel;
import pe.com.tss.runakuna.view.model.PermisoEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.QuickFilterViewModel;
import pe.com.tss.runakuna.view.model.VacacionEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.VacacionesEmpleadoFilterViewModel;

/**
 * Created by josediaz on 2/11/2016.
 */

@Repository
public class EmpleadoJdbcRepository implements EmpleadoRepository {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    DataSource dataSource;

    private NamedParameterJdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<EmpleadoResultViewModel> busquedaEmpleado(EmpleadoFilterViewModel busquedaEmpleadoDto) {

        WhereParams params = new WhereParams();
        String sql = generarBusquedaEmpleado(busquedaEmpleadoDto, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<EmpleadoResultViewModel>(EmpleadoResultViewModel.class));
    }
    
    @Override
    public List<EmpleadoResultViewModel> busquedaRapidaEmpleado(QuickFilterViewModel quickFilter) {

        WhereParams params = new WhereParams();
        String sql = generarBusquedaEmpleadoQuickSearch(quickFilter, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<EmpleadoResultViewModel>(EmpleadoResultViewModel.class));
    }

    @Override
    public List<EmpleadoViewModel> busquedaEmpleadoExport(EmpleadoFilterViewModel busquedaEmpleadoDto) {

        WhereParams params = new WhereParams();
        String sql = generarBusquedaEmpleadoExport(busquedaEmpleadoDto, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<EmpleadoViewModel>(EmpleadoViewModel.class));
    }

    @Override
    public EmpleadoViewModel verEmpleado(Long idEmpleado) {

        WhereParams params = new WhereParams();
        String sql = generarVerEmpleado(idEmpleado, params);

        return jdbcTemplate.queryForObject(sql,
                params.getParams(), new BeanPropertyRowMapper<EmpleadoViewModel>(EmpleadoViewModel.class));
    }

    @Override
    public List<EducacionViewModel> verEducacion(Long idEmpleado) {

        WhereParams params = new WhereParams();
        String sql = generarVerEducacion(idEmpleado, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<EducacionViewModel>(EducacionViewModel.class));
    }

	@Override
	public List<EquipoEntregadoViewModel> verEquipoEntregado(Long idEmpleado) {
		WhereParams params = new WhereParams();
        String sql = generarVerEquipoEntregado(idEmpleado, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<EquipoEntregadoViewModel>(EquipoEntregadoViewModel.class));
	}

	@Override
	public List<DependienteViewModel> verDependiente(Long idEmpleado) {
		WhereParams params = new WhereParams();
        String sql = generarVerDependiente(idEmpleado, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<DependienteViewModel>(DependienteViewModel.class));
	}

	@Override
	public List<LicenciaViewModel> verLicencia(PeriodoEmpleadoViewModel periodoEmpleado) {
		WhereParams params = new WhereParams();
        String sql = generarVerLicencia(periodoEmpleado, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<LicenciaViewModel>(LicenciaViewModel.class));
	}

	@Override
	public HorarioEmpleadoViewModel verHorarioEmpleado(Long idEmpleado) {
		WhereParams params = new WhereParams();

		HorarioEmpleadoViewModel horario = null;

        String sql = generarVerHorarioEmpleado(idEmpleado, params);

        List<HorarioEmpleadoViewModel> list = jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<HorarioEmpleadoViewModel>(HorarioEmpleadoViewModel.class));

        if(list!=null && list.size() > 0){
        	horario = list.get(0);
        }

        return horario;

	}

	@Override
	public List<HorarioEmpleadoViewModel> verHorariosEmpleado(Long idEmpleado) {
		WhereParams params = new WhereParams();
        String sql = generarVerHorariosEmpleado(idEmpleado, params);

        List<HorarioEmpleadoViewModel> horarios = jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<HorarioEmpleadoViewModel>(HorarioEmpleadoViewModel.class));

        return horarios;

	}

	@Override
	public List<HorarioEmpleadoResultViewModel> busquedaHorariosEmpleado(Long idEmpleado) {
		WhereParams params = new WhereParams();
        String sql = generarVerHorariosEmpleado(idEmpleado, params);

        List<HorarioEmpleadoResultViewModel> horarios = jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<HorarioEmpleadoResultViewModel>(HorarioEmpleadoResultViewModel.class));

        return horarios;

	}

	@Override
	public List<PeriodoEmpleadoViewModel> verPeriodoEmpleado(Long idEmpleado) {
		WhereParams params = new WhereParams();
        String sql = generarVerPermisosPermitidos(idEmpleado, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<PeriodoEmpleadoViewModel>(PeriodoEmpleadoViewModel.class));

    }

	@Override
	public List<HorarioEmpleadoDiaViewModel> verHorarioEmpleadoDia(HorarioEmpleadoViewModel horarioEmpleadoDto) {
		WhereParams params = new WhereParams();
        String sql = generarVerHorarioEmpleadoDia(horarioEmpleadoDto, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<HorarioEmpleadoDiaViewModel>(HorarioEmpleadoDiaViewModel.class));

	}

	@Override
	public List<PermisoEmpleadoViewModel> verPermisoEmpleado(PeriodoEmpleadoViewModel periodoEmpleado) {
		WhereParams params = new WhereParams();
        String sql = generarVerPermisosEmpleado(periodoEmpleado, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<PermisoEmpleadoViewModel>(PermisoEmpleadoViewModel.class));

	}

	@Override
	public List<VacacionEmpleadoViewModel> verVacaciones(PeriodoEmpleadoViewModel periodoEmpleado) {
		WhereParams params = new WhereParams();
        String sql = generarVerVacaciones(periodoEmpleado, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<VacacionEmpleadoViewModel>(VacacionEmpleadoViewModel.class));

	}

	@Override
	public List<MarcacionViewModel> verMarcaciones(Long idEmpleado) {
		WhereParams params = new WhereParams();
        String sql = generarVerMarcaciones(idEmpleado, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<MarcacionViewModel>(MarcacionViewModel.class));

	}


	public List<VacacionEmpleadoViewModel> busquedaVacacionesEmpleado(VacacionesEmpleadoFilterViewModel busquedaVacacionesEmpleadoDto) {
		WhereParams params = new WhereParams();
        String sql = generarBusquedaVacacionesEmpleado(busquedaVacacionesEmpleadoDto, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<>(VacacionEmpleadoViewModel.class));
	}
	
	@Override
	public BigDecimal obtenerHorasSemanalesLunesViernes(Long idHorario) {
		WhereParams params = new WhereParams();
        String sql = generarHorasSemanalesLunesViernes(idHorario, params);

        return  (BigDecimal)jdbcTemplate.queryForObject(sql, params.getParams(), BigDecimal.class);

	}
	
	@Override
	public BigDecimal obtenerHorasSemanalesCubiertas(Long idHorario) {
		WhereParams params = new WhereParams();
        String sql = generarHorasSemanalesCubiertas(idHorario, params);

        return  (BigDecimal)jdbcTemplate.queryForObject(sql.toString(), params.getParams(), BigDecimal.class);

	}
	
	@Override	
	public HorarioEmpleadoDiaViewModel obtenerHorarioDiaPorDia(Long idHorario, String dia) {
		WhereParams params = new WhereParams();
        String sql = generarHoraDiaPorDia(idHorario, dia, params);

        return  jdbcTemplate.queryForObject(sql, params.getParams(), new BeanPropertyRowMapper<>(HorarioEmpleadoDiaViewModel.class));

	}

    private String generarBusquedaVacacionesEmpleado(VacacionesEmpleadoFilterViewModel busquedaVacacionesEmpleadoDto, WhereParams params) {

    	StringBuilder sql = new StringBuilder();
        sql.append(" SELECT distinct va.IdVacacion AS idVacacion, ");
        sql.append(" EMPLEADO.IdEmpleado AS idEmpleado, ");
        sql.append(" va.IdPeriodoEmpleado AS idPeriodoEmpleado, ");
        sql.append(" CONCAT(EMPLEADO.ApellidoPaterno, ' ', EMPLEADO.ApellidoMaterno, ', ', EMPLEADO.Nombre) as nombreEmpleado, ");
        sql.append(" va.FechaInicio as fechaInicio, ");
        sql.append(" va.FechaFin as fechaFin, ");
        sql.append(" va.DiasCalendarios as diasCalendarios, ") ;
        sql.append(" va.DiasHabiles as diasHabiles, ") ;
        sql.append(" CONCAT(ATENDIDO.ApellidoPaterno, ' ', ATENDIDO.ApellidoMaterno, ', ', ATENDIDO.Nombre) as nombreJefeInmediato, ");
        sql.append(" va.Estado AS estado, ");
        sql.append(" ESTADO.Nombre AS estadoString ") ;
        sql.append(" from Vacacion va ") ;
        sql.append(" LEFT JOIN TablaGeneral ESTADO ON va.Estado=ESTADO.Codigo and ESTADO.GRUPO='Permiso.Estado'");
        sql.append(" LEFT JOIN PeriodoEmpleado PERIODO_EMPLEADO ON va.IdPeriodoEmpleado = PERIODO_EMPLEADO.IdPeriodoEmpleado ");
        sql.append(" LEFT JOIN Empleado EMPLEADO ON PERIODO_EMPLEADO.IdEmpleado = EMPLEADO.IdEmpleado ");

        sql.append(" LEFT JOIN Empleado ATENDIDO ON va.IdAtendidoPor = ATENDIDO.IdEmpleado ");

        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMPLEADO.IdEmpleado AND ((HISTORIAL.FechaInicio < getdate() AND HISTORIAL.FechaFin > getDate()) OR (HISTORIAL.FechaInicio < getdate() AND HISTORIAL.FechaFin IS NULL)) ");

        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");

        sql.append("  LEFT JOIN Empleado ep on PROY.IdJefe = ep.IdEmpleado ");
		sql.append("  LEFT JOIN Empleado eda on DEP.IdJefe = eda.IdEmpleado ");
		sql.append("  LEFT JOIN Empleado eun on UN.IdJefe = eun.IdEmpleado ");

        sql.append(" where 1=1 ");
        sql.append(params.filter(" AND va.Estado = :estado ", busquedaVacacionesEmpleadoDto.getEstado()));
        sql.append(params.filterDateDesde_US(" AND va.FechaInicio  " , busquedaVacacionesEmpleadoDto.getFechaInicio()));
        sql.append(params.filterDateHasta_US(" AND va.FechaFin  ", busquedaVacacionesEmpleadoDto.getFechaFin()));
        sql.append(params.filter(" AND UN.IdUnidadDeNegocio = :unidadNegocio ", busquedaVacacionesEmpleadoDto.getIdUnidadNegocio()));
        sql.append(params.filter(" AND DEP.IdDepartamentoArea = :departamento ", busquedaVacacionesEmpleadoDto.getIdDepartamentoArea()));
        sql.append(params.filter(" AND PROY.IdProyecto = :proyecto ", busquedaVacacionesEmpleadoDto.getIdProyecto()));
        sql.append(params.filter(" AND va.IdPermisoEmpleado = :codigoPermiso ", busquedaVacacionesEmpleadoDto.getCodigoPermiso()));

        sql.append(params.filter(" AND EMPLEADO.IdEmpleado = :idEmpleado ", busquedaVacacionesEmpleadoDto.getIdEmpleado()));

        sql.append(params.filter(" AND va.IdAtendidoPor = :idEmpleado ", busquedaVacacionesEmpleadoDto.getIdJefeInmediato()));

        /*sql.append(params.filter(" AND ( "+
        		" (HISTORIAL.IdProyecto IS NOT NULL AND PROY.IdJefe = :idJefeInmediato)  OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NOT NULL AND DEP.IdJefe  = :idJefeInmediato)"+
        		" OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NULL AND HISTORIAL.IdUnidadDeNegocio IS NOT NULL AND UN.IdJefe = :idJefeInmediato) ) ", busquedaVacacionesEmpleadoDto.getIdJefeInmediato()));
        */
        return sql.toString();
	}

	@Override
    public List<MarcacionViewModel> busquedaMarcacionEmpleado(MarcacionFilterViewModel busquedaMarcacionDto) {
        WhereParams params = new WhereParams();
        String sql = generarVerBusquedaMarcacionesEmpleado(busquedaMarcacionDto, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<>(MarcacionViewModel.class));
    }

    @Override
    public List<PermisoEmpleadoFilterViewModel> autocompleteEmpleado(String search) {
        WhereParams params = new WhereParams();
        String sql = generarAutocompleteEmpleado(search, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<>(PermisoEmpleadoFilterViewModel.class));
    }

    private String generarAutocompleteEmpleado(String search, WhereParams params) {

        StringBuilder sql = new StringBuilder();
        sql.append(" select ");
        sql.append(" EMPLEADO.IdEmpleado as idEmpleado, ");
        sql.append(" CONCAT(EMPLEADO.ApellidoPaterno, ' ', EMPLEADO.ApellidoMaterno, ', ', EMPLEADO.Nombre) as nombreEmpleado ");
        sql.append(" from  Empleado EMPLEADO ");

        sql.append(" where (CONCAT(EMPLEADO.ApellidoPaterno, ' ', EMPLEADO.ApellidoMaterno, ', ', EMPLEADO.Nombre)) like '%"+search+"%' ");


        return sql.toString();
    }

	private String generarVerPermisosPermitidos(Long idEmpleado, WhereParams params) {

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append(" pe.IdPeriodoEmpleado AS idPeriodoEmpleado, ");
        sql.append(" pe.Periodo AS periodo, ");
        sql.append(" pe.FechaInicio AS fechaInicio, ");
        sql.append(" pe.FechaFin AS fechaFin, ");
        
        sql.append(" pe.DiasVacacionesDisponibles AS diasVacacionesDisponibles, ");
        sql.append(" pe.PermisosDisponibles AS permisosDisponibles, ");
        sql.append(" pe.MaxPermisos AS permisosPermitidos, ");
        sql.append(" (SELECT count(*) FROM PermisoEmpleado LEFT JOIN PeriodoEmpleado ON PeriodoEmpleado.IdPeriodoEmpleado = pe.IdPeriodoEmpleado) AS permisosUsados ");
        sql.append(" FROM ");
        sql.append(" PeriodoEmpleado pe ");
        sql.append(" where pe.IdEmpleado = "+idEmpleado);
        sql.append(" order by pe.IdPeriodoEmpleado desc ");

        return sql.toString();
    }

	private String generarVerPermisosEmpleado(PeriodoEmpleadoViewModel periodoEmpleado, WhereParams params) {

		StringBuilder sql = new StringBuilder();
        sql.append(" SELECT  ");
        sql.append(" PE.IdPermisoEmpleado AS idPermisoEmpleado, ");
        sql.append(" PE.IdPeriodoEmpleado AS idPeriodoEmpleado, ");
        sql.append(" PERM.Nombre AS nombreMotivo, ");
        sql.append(" PERIODO.Periodo AS periodo, ");
        sql.append(" PE.Motivo AS motivo, ");
        sql.append(" PE.Razon AS razon, ");
        sql.append(" PE.Fecha AS fecha, ");
        sql.append(" PE.HoraInicio AS horaInicio, ");
        sql.append(" PE.HoraFin AS horaFin, ");
        sql.append(" PE.FechaRecuperacion AS fechaRecuperacion, ");
        sql.append(" PE.HoraInicioRecuperacion AS horaInicioRecuperacion, ");
        sql.append(" PE.HoraFinRecuperacion AS horaFinRecuperacion, ");
        sql.append(" PE.Horas AS horas, ");
        sql.append(" PE.Estado AS estado, ");
        sql.append(" ESTPERM.Nombre AS nombreEstado, ");

        /*sql.append("  CASE ");
		sql.append("  WHEN (h.IdProyecto IS NOT NULL) THEN ep.Nombre+' '+ep.ApellidoPaterno +' '+ep.ApellidoMaterno ");
		sql.append("  WHEN (h.IdProyecto IS NULL AND h.IdDepartamentoArea IS NOT NULL) THEN eda.Nombre+' '+eda.ApellidoPaterno +' '+eda.ApellidoMaterno ");
		sql.append("  WHEN (h.IdProyecto IS NULL AND h.IdDepartamentoArea IS NULL AND h.IdUnidadDeNegocio IS NOT NULL) THEN eun.Nombre+' '+eun.ApellidoPaterno +' '+eun.ApellidoMaterno ");
		sql.append("  ELSE ' '  ");
		sql.append("  END AS jefeInmediato, ");*/

        sql.append("  (EMP.ApellidoPaterno +' '+EMP.ApellidoMaterno+', '+EMP.Nombre) AS jefeInmediato, ");
		sql.append("  PE.IdAtendidoPor AS idAtendidoPor ");

        sql.append(" FROM PermisoEmpleado PE ");
        sql.append(" LEFT JOIN PeriodoEmpleado PERIODO ON PE.IdPeriodoEmpleado = PERIODO.IdPeriodoEmpleado ");
        sql.append(" LEFT JOIN Empleado EMP ON EMP.IdEmpleado = PE.IdAtendidoPor ");
        sql.append(" LEFT JOIN TablaGeneral PERM ON PERM.Codigo=PE.Motivo AND PERM.Grupo = 'Permiso.Motivo' ");
        sql.append(" LEFT JOIN TablaGeneral ESTPERM ON ESTPERM.Codigo=PE.Estado AND ESTPERM.Grupo = 'Permiso.Estado' ");

        sql.append(" LEFT JOIN HistorialLaboral h ON h.IdEmpleado = EMP.IdEmpleado AND (h.FechaInicio<=getdate() AND h.FechaFin>=getdate()) ");

        sql.append("  LEFT JOIN Proyecto p on p.IdProyecto = h.IdProyecto ");
		sql.append("  LEFT JOIN Empleado ep on p.IdJefe = ep.IdEmpleado ");
		sql.append("  LEFT JOIN DepartamentoArea da on da.IdDepartamentoArea = h.IdDepartamentoArea ");
		sql.append("  LEFT JOIN Empleado eda on da.IdJefe = eda.IdEmpleado ");
		sql.append("  LEFT JOIN UnidadDeNegocio un ON un.IdUnidadDeNegocio = h.IdUnidadDeNegocio ");
		sql.append("  LEFT JOIN Empleado eun on un.IdJefe = eun.IdEmpleado ");

        sql.append(" where PERIODO.IdEmpleado = "+periodoEmpleado.getIdEmpleado());
        sql.append(params.filter(" AND PE.IdPeriodoEmpleado = :idPeriodoEmpleado ", periodoEmpleado.getIdPeriodoEmpleado()));
        sql.append(" order by PE.IdPermisoEmpleado desc ");

        return sql.toString();
    }

	private String generarVerVacaciones(PeriodoEmpleadoViewModel periodoEmpleado, WhereParams params) {

		StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append(" VAC.IdVacacion AS idVacacion, ");
        sql.append(" PERIODO.IdPeriodoEmpleado AS idPeriodoEmpleado, ");
        sql.append(" PERIODO.Periodo AS periodo, ");
        sql.append(" EMP.IdEmpleado AS idEmpleado, ");
        sql.append(" ATENDIDO.IdEmpleado AS idAtendidoPor, ");
        sql.append(" VAC.FechaInicio AS fechaInicio, ");
        sql.append(" VAC.FechaFin AS fechaFin, ");
        sql.append(" ESTVAC.Nombre AS nombreEstado, ");
        sql.append(" VAC.Estado AS Estado, ");

        sql.append(" VAC.DiasCalendarios AS diasCalendarios, ");
        sql.append(" VAC.DiasHabiles AS diasHabiles, ");

        sql.append("  (ATENDIDO.ApellidoPaterno +' '+ATENDIDO.ApellidoMaterno+', '+ATENDIDO.Nombre) AS jefeInmediato, ");
        sql.append(" PERIODO.DiasVacacionesDisponibles AS diasVacacionesDisponibles, "); 
        sql.append(" VAC.Creador AS creador, ");
        sql.append(" VAC.Actualizador AS actualizador, ");
        sql.append(" VAC.FechaCreacion AS fechaCreacion, ");
        sql.append(" VAC.FechaActualizacion AS fechaActualizacion, ");
        sql.append(" VAC.RowVersion AS rowVersion  ");

        sql.append(" FROM Vacacion VAC ");
        sql.append(" LEFT JOIN Empleado ATENDIDO ON ATENDIDO.IdEmpleado = VAC.IdAtendidoPor ");
        sql.append(" LEFT JOIN PeriodoEmpleado PERIODO ON PERIODO.IdPeriodoEmpleado = VAC.IdPeriodoEmpleado ");
        sql.append(" LEFT JOIN Empleado EMP ON EMP.IdEmpleado = PERIODO.IdEmpleado ");
        sql.append(" LEFT JOIN TablaGeneral ESTVAC ON ESTVAC.Codigo=VAC.Estado AND ESTVAC.Grupo = 'Vacaciones.Estado' ");

        sql.append(" LEFT JOIN HistorialLaboral h ON h.IdEmpleado = EMP.IdEmpleado AND (h.FechaInicio<=getdate() AND h.FechaFin>=getdate()) ");


        sql.append(" where PERIODO.IdEmpleado = "+periodoEmpleado.getIdEmpleado());
        sql.append(params.filter(" AND VAC.IdPeriodoEmpleado = :idPeriodoEmpleado ", periodoEmpleado.getIdPeriodoEmpleado()));
        sql.append(" order by VAC.IdVacacion  desc ");

        return sql.toString();
    }

	private String generarVerMarcaciones(Long idEmpleado, WhereParams params) {

		StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append(" MAC.IdMarcacion AS idMarcacion, ");
        sql.append(" MAC.IdEmpleado AS idEmpleado, ");
        sql.append(" MAC.Fecha AS fecha, ");
        sql.append(" MAC.HoraIngreso AS horaIngreso, ");
        sql.append(" MAC.HoraInicioAlmuerzo AS horaInicioAlmuerzo, ");
        sql.append(" MAC.HoraFinAlmuerzo AS horaFinAlmuerzo, ");
        sql.append(" MAC.HoraSalida AS horaSalida, ");
        sql.append(" EMP.Nombre+' '+EMP.ApellidoPaterno+' '+EMP.ApellidoMaterno AS nombreCompletoEmpleado, ");
        sql.append(" CASE ");
        sql.append(" WHEN (MAC.Tardanza =1) THEN 'Si' ");
        sql.append(" WHEN (MAC.Tardanza =0) THEN 'No' ");
        sql.append(" ELSE 'No' ");
        sql.append(" END AS tardanza, ");
        
        
        sql.append(" CASE ");
        sql.append(" WHEN (MAC.EsPersonaDeConfianza =1) THEN 'Sin Marcar' ");
        sql.append(" WHEN (MAC.Inasistencia =1 AND MAC.EsPersonaDeConfianza =0) THEN 'Inasistencia' ");
        sql.append(" WHEN (MAC.Vacaciones =1) THEN 'Vacaciones' ");
        sql.append(" WHEN (MAC.Licencia =1) THEN 'Licencia' ");
        sql.append(" WHEN (MAC.Tardanza =1) THEN 'Tardanza' ");
        sql.append(" ELSE 'Puntual' ");
        sql.append(" END AS estado, ");
        
        sql.append(" 'No' AS solicitudCambio ");


        sql.append(" FROM Marcacion MAC  ");
        sql.append(" LEFT JOIN Empleado EMP ON EMP.IdEmpleado = MAC.IdEmpleado  ");
        sql.append(" where  MAC.IdEmpleado = "+idEmpleado);

        return sql.toString();
    }

    private String generarVerHorarioEmpleado(Long idEmpleado, WhereParams params) {

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT  ");
        sql.append(" he.IdHorarioEmpleado AS idHorarioEmpleado, ");
        sql.append(" he.HorasSemanal AS horasSemanal, ");
        sql.append(" h.HorasSemanal AS horasSemanalHorario, ");
        sql.append(" h.Nombre AS nombreHorario, ");
        sql.append(" TIPOHORARIO.Nombre AS nombreTipoHorario, ");
        sql.append(" he.InicioVigencia AS inicioVigencia, ");
        sql.append(" he.IdHorario AS idHorario, ");
        sql.append(" he.FinVigencia AS finVigencia ");

        sql.append(" FROM HorarioEmpleado he ");
        sql.append(" LEFT JOIN Horario h ON h.IdHorario = he.IdHorario ");
        sql.append(" LEFT JOIN TablaGeneral TIPOHORARIO ON he.TipoHorario= TIPOHORARIO.Codigo AND TIPOHORARIO.Grupo = 'Horario.TipoHorario' ");
        sql.append(" where ((he.InicioVigencia<=getDate() and he.FinVigencia is not null and he.FinVigencia>=getDate()) or (he.InicioVigencia<=getDate() and he.FinVigencia is null) ) ");
        sql.append(" and he.IdEmpleado = "+idEmpleado);


        return sql.toString();
    }

    private String generarVerHorariosEmpleado(Long idEmpleado, WhereParams params) {

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT  ");
        sql.append(" he.IdHorarioEmpleado AS idHorarioEmpleado, ");
        sql.append(" he.IdHorario AS idHorario, ");
        sql.append(" he.TipoHorario AS tipoHorario, ");
        sql.append(" he.HorasSemanal AS horasSemanal, ");
        sql.append(" h.HorasSemanal AS horasSemanalHorario, ");
        sql.append(" h.Nombre AS nombreHorario, ");
        sql.append(" TIPOHORARIO.Nombre AS nombreTipoHorario, ");
        sql.append(" he.InicioVigencia AS inicioVigencia, ");
        sql.append(" he.FinVigencia AS finVigencia, ");
        
        sql.append(" CASE ");
        sql.append(" WHEN getdate() < he.InicioVigencia THEN 'Futuro' ");
        sql.append(" WHEN he.FinVigencia < getdate() THEN 'Pasado' ");
        sql.append(" ELSE 'Vigente' ");
        sql.append(" END AS estadoHorario, ");
        
        sql.append(" (SELECT count(*) FROM HorarioEmpleadoDia hde WHERE hde.IdHorarioEmpleado=he.IdHorarioEmpleado AND Laboral = 1) AS diasLaboral, ");

        sql.append(" he.Creador AS creador, ");
        sql.append(" he.Actualizador AS actualizador, ");
        sql.append(" he.FechaCreacion AS fechaCreacion, ");
        sql.append(" he.FechaActualizacion AS fechaActualizacion,  ");
        sql.append(" he.RowVersion AS rowVersion  ");

        sql.append(" FROM HorarioEmpleado he ");
        sql.append(" LEFT JOIN Horario h ON h.IdHorario = he.IdHorario ");
        sql.append(" LEFT JOIN TablaGeneral TIPOHORARIO ON he.TipoHorario= TIPOHORARIO.Codigo AND TIPOHORARIO.Grupo = 'Horario.TipoHorario' ");
        sql.append(" WHERE he.IdEmpleado = "+idEmpleado);


        return sql.toString();
    }

    private String generarVerHorarioEmpleadoDia(HorarioEmpleadoViewModel horarioEmpleadoDto, WhereParams params) {

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT  ");
        sql.append(" hed.IdHorarioEmpleadoDia AS idHorarioEmpleadoDia, ");
        sql.append(" hed.laboral AS laboral, ");
        sql.append(" hed.Entrada AS entrada, ");
        sql.append(" hed.DiaSemana AS diaSemana, ");
        sql.append(" hed.Salida AS salida, ");
        sql.append(" hed.TiempoAlmuerzo AS tiempoAlmuerzo, ");
        sql.append(" DIASEMANA.Nombre AS nombreDiaSemana, ");

        sql.append(" hed.Creador AS creador, ");
        sql.append(" hed.Actualizador AS actualizador, ");
        sql.append(" hed.FechaCreacion AS fechaCreacion, ");
        sql.append(" hed.FechaActualizacion AS fechaActualizacion,  ");
        sql.append(" hed.RowVersion AS rowVersion  ");

        sql.append(" FROM HorarioEmpleadoDia hed ");
        sql.append(" LEFT JOIN TablaGeneral DIASEMANA ON hed.DiaSemana= DIASEMANA.Codigo AND DIASEMANA.Grupo = 'Dia' ");

        sql.append(" where 1=1");
        sql.append(params.filter(" AND hed.IdHorarioEmpleado = :idHorarioEmpleado ", horarioEmpleadoDto.getIdHorarioEmpleado()));

        return sql.toString();
    }

    private String generarVerBusquedaMarcacionesEmpleado(MarcacionFilterViewModel busquedaMarcacionDto, WhereParams params){
		StringBuilder sql = new StringBuilder();

        sql.append(" SELECT distinct ");
        sql.append(" MAR.IdMarcacion AS idMarcacion, ");
        sql.append(" MAR.Fecha AS fecha, ");
        sql.append(" MAR.HoraIngreso AS horaIngreso, ");
        sql.append(" MAR.HoraInicioAlmuerzo AS horaInicioAlmuerzo, ");
        sql.append(" MAR.HoraFinAlmuerzo AS horaFinAlmuerzo, ");
        sql.append(" MAR.HoraSalida AS horaSalida, ");

        sql.append(" MAR.DemoraEntrada AS demoraEntrada, ");
        sql.append(" MAR.DemoraAlmuerzo AS demoraAlmuerzo, ");
        sql.append(" MAR.DemoraSalida AS demoraSalida, ");

        sql.append(" CASE ");
        sql.append(" WHEN (MAR.EsPersonaDeConfianza =1) THEN 'Si' ");
        sql.append(" WHEN (MAR.EsPersonaDeConfianza =0) THEN 'No' ");
        sql.append(" ELSE 'No' ");
        sql.append(" END AS esPersonaDeConfianza, ");
        sql.append(" CASE ");
        sql.append(" WHEN (MAR.Inasistencia =1) THEN 'Si' ");
        sql.append(" WHEN (MAR.Inasistencia =0) THEN 'No' ");
        sql.append(" ELSE 'No' ");
        sql.append(" END AS inasistencia, ");
        
        sql.append(" CASE ");
        sql.append(" WHEN (MAR.EsPersonaDeConfianza =1) THEN 'Sin Marcar' ");
        sql.append(" WHEN (MAR.Inasistencia =1 AND MAR.EsPersonaDeConfianza =0) THEN 'Inasistencia' ");
        sql.append(" WHEN (MAR.Vacaciones =1) THEN 'Vacaciones' ");
        sql.append(" WHEN (MAR.Licencia =1) THEN 'Licencia' ");
        sql.append(" WHEN (MAR.Tardanza =1) THEN 'Tardanza' ");
        sql.append(" ELSE 'Puntual' ");
        sql.append(" END AS estado, ");
        
        sql.append(" CASE ");
        sql.append(" WHEN (SELECT count(*) FROM SolicitudCambioMarcacion SOL WHERE SOL.IdMarcacion = MAR.IdMarcacion)=0 THEN 'No' ");
        sql.append(" ELSE 'Si' ");
        sql.append(" END AS solicitudCambio, ");

        sql.append(" MAR.HoraIngresoHorario AS horaIngresoHorario, ");
        sql.append(" MAR.HoraSalidaHorario AS horaSalidaHorario, ");

        sql.append(" (EMP.ApellidoPaterno+' '+EMP.ApellidoMaterno+', '+EMP.Nombre) AS nombreCompletoEmpleado, ");

        sql.append(" EMP.Nombre AS nombreEmpleado, ");
        sql.append(" EMP.ApellidoPaterno AS apelPaternoEmpleado, ");
        sql.append(" EMP.ApellidoMaterno AS apelMaternoEmpleado, ");
        sql.append(" EMP.Codigo AS codigoEmpleado, ");
        sql.append(" MAR.HorasTrabajoReal AS horasTrabajoReal, ");
        sql.append(" MAR.HorasPermiso AS horasPermiso, ");
        sql.append(" MAR.HorasExtra AS horasExtra, ");
        sql.append(" MAR.HorasTrabajoPendiente AS horasTrabajoPendiente ");
        

        sql.append(" FROM Marcacion MAR ");
        sql.append(" LEFT JOIN Empleado EMP ON EMP.IdEmpleado = MAR.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMP.IdEmpleado AND (HISTORIAL.FechaInicio <= MAR.Fecha AND ( HISTORIAL.FechaFin IS NULL OR HISTORIAL.FechaFin>= MAR.Fecha) )  ");

        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append(" LEFT JOIN SolicitudCambioMarcacion soli ON soli.IdMarcacion = MAR.IdMarcacion ");
        sql.append(" WHERE 1=1 ");

        sql.append(params.filter(" AND EMP.IdEmpleado = :idEmpleado ", busquedaMarcacionDto.getIdEmpleado()));

        sql.append(params.filter(" AND UN.IdUnidadDeNegocio = :unidadNegocio ", busquedaMarcacionDto.getUnidadNegocio()));
        sql.append(params.filter(" AND DEP.IdDepartamentoArea = :departamento ", busquedaMarcacionDto.getDepartamento()));
        sql.append(params.filter(" AND PROY.IdProyecto = :proyecto ", busquedaMarcacionDto.getProyecto()));
        
        if(busquedaMarcacionDto.getSolicitudCambio() == 1){
        	sql.append(" AND soli.IdMarcacion = MAR.IdMarcacion ");
        }
        sql.append(params.filterDateDesde_US(" AND MAR.Fecha  " , busquedaMarcacionDto.getDesde()));
        sql.append(params.filterDateHasta_US(" AND MAR.Fecha  ", busquedaMarcacionDto.getHasta()));

        sql.append(params.filter(" AND ( "+
        		" (HISTORIAL.IdProyecto IS NOT NULL AND PROY.IdJefe = :idJefeInmediato)  OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NOT NULL AND DEP.IdJefe  = :idJefeInmediato)"+
        		" OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NULL AND HISTORIAL.IdUnidadDeNegocio IS NOT NULL AND UN.IdJefe = :idJefeInmediato) ) ", busquedaMarcacionDto.getIdJefeInmediato()));

        sql.append(" ORDER BY MAR.Fecha DESC, nombreCompletoEmpleado ");
        
		return sql.toString();
	}


    private String generarBusquedaEmpleado(EmpleadoFilterViewModel busquedaEmpleadoDto, WhereParams params) {

        StringBuilder sql = new StringBuilder();
        sql.append(" select distinct e.IdEmpleado, e.Nombre as nombre,  ");
        sql.append(" e.ApellidoPaterno as apellidoPaterno, e.ApellidoMaterno as apellidoMaterno,  ");
        sql.append(" TIPODOCUMENTO.Nombre as tipoDocumento, ");
        sql.append(" e.Codigo as codigo,  ");
        sql.append(" e.NumeroDocumento as numeroDocumento,  ");
        sql.append(" e.EmailInterno as emailInterno, e.Codigo as codigo,  ");

        sql.append(" ESTADO.Nombre as estado, ");

        sql.append(" GENERO.Nombre as genero, ");
        sql.append(" ESTADOCIVIL.Nombre as estadoCivil, ");
        sql.append(" GRUPOSANGRE.Nombre as grupoSangre, ");
        sql.append(" e.FechaNacimiento as fechaNacimiento ,");
        sql.append(" PAIS.Nombre as paisNacimiento, ");

        sql.append(" CASE");
        sql.append(" WHEN Discapacitado = 1 THEN 'Si'");
        sql.append(" WHEN Discapacitado = 0 THEN 'No'");
        sql.append(" ELSE 'No'");
        sql.append(" END AS discapacitado ");


        sql.append(" from Empleado e");
        sql.append(" LEFT JOIN Pais PAIS ON e.PaisNacimiento=PAIS.Codigo ");
        //sql.append(" LEFT JOIN TablaGeneral DISCAPACITADO ON e.Discapacitado=DISCAPACITADO.Codigo and DISCAPACITADO.GRUPO='Empleado.Discapacitado'");
        sql.append(" LEFT JOIN TablaGeneral GRUPOSANGRE ON e.GrupoSangre=GRUPOSANGRE.Codigo and GRUPOSANGRE.GRUPO='Empleado.GrupoSanguineo'");
        sql.append(" LEFT JOIN TablaGeneral GENERO ON e.Genero=GENERO.Codigo and GENERO.GRUPO='Empleado.Generico'");
        sql.append(" LEFT JOIN TablaGeneral ESTADOCIVIL ON e.EstadoCivil=ESTADOCIVIL.Codigo and ESTADOCIVIL.GRUPO='Empleado.EstadoCivil'");
        sql.append(" LEFT JOIN TablaGeneral TIPODOCUMENTO ON e.TipoDocumento=TIPODOCUMENTO.Codigo and TIPODOCUMENTO.GRUPO='Empleado.TipoDocumento'");
        sql.append(" LEFT JOIN TablaGeneral ESTADO ON e.Estado=ESTADO.Codigo and ESTADO.GRUPO='Empleado.Estado'");
        sql.append(" LEFT JOIN CentroCosto CENTRO ON CENTRO.IdCentroCosto = e.IdCentroCosto");

        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = e.IdEmpleado AND (HISTORIAL.FechaInicio < getdate()  AND (HISTORIAL.FechaFin IS NULL OR  HISTORIAL.FechaFin > getDate() )) ");

        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");


        sql.append(" where 1=1");
        sql.append(params.filter(" AND UPPER(e.Nombre) LIKE UPPER('%' + :nombres + '%') ", busquedaEmpleadoDto.getNombres()));
        sql.append(params.filter(" AND UPPER(e.ApellidoPaterno) LIKE UPPER('%' + :apellidoPaterno + '%') ", busquedaEmpleadoDto.getApePaterno()));
        sql.append(params.filter(" AND UPPER(e.ApellidoMaterno) LIKE UPPER('%' + :apellidoMaterno + '%') ", busquedaEmpleadoDto.getApeMaterno()));
        sql.append(params.filter(" AND UPPER(e.EmailInterno) LIKE UPPER('%' + :correoElectronico + '%') ", busquedaEmpleadoDto.getCorreoElectronico()));
        sql.append(params.filter(" AND e.Codigo = :codigo ", busquedaEmpleadoDto.getCodigo()));
        sql.append(params.filter(" AND e.TipoDocumento = :tipoDocumento ", busquedaEmpleadoDto.getTipoDocumento()));
        sql.append(params.filter(" AND e.NumeroDocumento = :numeroDocumento ", busquedaEmpleadoDto.getNumeroDocumento()));

        sql.append(params.filter(" AND UN.IdUnidadDeNegocio = :unidadNegocio ", busquedaEmpleadoDto.getUnidadNegocio()));
        sql.append(params.filter(" AND DEP.IdDepartamentoArea = :departamento ", busquedaEmpleadoDto.getDepartamento()));
        sql.append(params.filter(" AND PROY.IdProyecto = :proyecto ", busquedaEmpleadoDto.getProyecto()));

        //TODO Unidad de Negocio, Proyecto, Departamento?
        //TODO Jefe?
        sql.append(params.filter(" AND e.IdCentroCosto = :centroCosto ", busquedaEmpleadoDto.getCentroCosto()));
        sql.append(params.filter(" AND UPPER(e.EmailInterno) LIKE UPPER('%' + :correoElectronico + '%') ", busquedaEmpleadoDto.getCorreoElectronico()));
        sql.append(params.filter(" AND e.Estado = :estado ", busquedaEmpleadoDto.getEstado()));
        sql.append(params.filter(" AND e.FechaIngreso >= :fechaIngresoDesde ", busquedaEmpleadoDto.getFechaIngresoDesde()));
        sql.append(params.filter(" AND e.FechaIngreso <= :fechaIngresoHasta ", busquedaEmpleadoDto.getFechaIngresoHasta()));
        sql.append(params.filter(" AND e.FechaCese IS NOT NULL AND e.FechaCese >= :fechaCeseDesde ", busquedaEmpleadoDto.getFechaCeseDesde()));
        sql.append(params.filter(" AND e.FechaCese IS NOT NULL AND e.FechaCese <= :fechaCeseHasta ", busquedaEmpleadoDto.getFechaCeseHasta()));

        sql.append(params.filter(" AND ( "+
        		" (PROY.IdJefe = :idJefeInmediato)  OR (DEP.IdJefe  = :idJefeInmediato)"+
        		" OR (UN.IdJefe = :idJefeInmediato) ) ", busquedaEmpleadoDto.getIdJefeInmediato()));
        sql.append(params.filter(" AND [dbo].[GetJefeInmediato] (e.IdEmpleado,HISTORIAL.IdHistorialLaboral) = :idEmpleado",busquedaEmpleadoDto.getIdEmpleado()));
        return sql.toString();
    }

    private String generarBusquedaEmpleadoExport(EmpleadoFilterViewModel busquedaEmpleadoDto, WhereParams params) {

        StringBuilder sql = new StringBuilder();
        sql.append(" select distinct e.IdEmpleado, e.Nombre as nombre,  ");
        sql.append(" e.ApellidoPaterno as apellidoPaterno, e.ApellidoMaterno as apellidoMaterno,  ");
        sql.append(" TIPODOCUMENTO.Nombre as tipoDocumento, ");
        sql.append(" e.Codigo as codigo,  ");
        sql.append(" e.NumeroDocumento as numeroDocumento,  ");
        sql.append(" e.EmailInterno as emailInterno, e.Codigo as codigo,  ");

        sql.append(" ESTADO.Nombre as estado, ");

        sql.append(" GENERO.Nombre as genero, ");
        sql.append(" ESTADOCIVIL.Nombre as estadoCivil, ");
        sql.append(" GRUPOSANGRE.Nombre as grupoSangre, ");
        sql.append(" e.FechaNacimiento as fechaNacimiento ,");
        sql.append(" PAIS.Nombre as paisNacimiento, ");

        sql.append(" CASE");
        sql.append(" WHEN Discapacitado = 1 THEN 'Si'");
        sql.append(" WHEN Discapacitado = 0 THEN 'No'");
        sql.append(" ELSE 'No'");
        sql.append(" END AS discapacitadoString, ");
        sql.append(" e.TelefonoInterno as telefonoInterno , e.AnexoInterno as anexoInterno, CENTRO.Nombre as centroCostoString, ");
        sql.append(" CONTRATOTRABAJO.Nombre AS contratoTrabajoString, ");
        sql.append(" TIPOTRABAJO.Nombre AS tipoTrabajadorString, ");
        sql.append(" REGIMENHORARIO.Nombre AS regimenHorarioString, ");
        sql.append(" REGIMENLABORAL.Nombre AS regimenLaboralString, ");
        sql.append(" CASE");
        sql.append(" WHEN e.EsPersonalDeConfianza = 1 THEN 'Si'");
        sql.append(" WHEN e.EsPersonalDeConfianza = 0 THEN 'No'");
        sql.append(" ELSE 'No'");
        sql.append(" END AS esPersonalDeConfianzaString, ");
        sql.append(" e.DireccionDomicilio AS direccionDomicilio, ");
        sql.append(" TIPODOMICILIO.Nombre AS tipoDomicilioString, ");
        sql.append(" e.DistritoDomicilio AS distritoDomicilio, ");
        sql.append(" e.TelefonoCasa AS telefonoCasa, ");
        sql.append(" e.TelefonoCelular AS telefonoCelular, ");
        sql.append(" e.EmailPersonal AS emailPersonal, ");
        sql.append(" e.NombreContactoEmergencia AS nombreContactoEmergencia, ");
        sql.append(" e.TelefonoContactoEmergencia AS telefonoContactoEmergencia, ");
        sql.append(" e.EmailContactoEmergencia AS emailContactoEmergencia, ");
        sql.append(" RELACION.Nombre AS relacionContactoEmergenciaString ");

        sql.append(" from Empleado e");
        sql.append(" LEFT JOIN Pais PAIS ON e.PaisNacimiento=PAIS.Codigo ");
        //sql.append(" LEFT JOIN TablaGeneral DISCAPACITADO ON e.Discapacitado=DISCAPACITADO.Codigo and DISCAPACITADO.GRUPO='Empleado.Discapacitado'");
        sql.append(" LEFT JOIN TablaGeneral GRUPOSANGRE ON e.GrupoSangre=GRUPOSANGRE.Codigo and GRUPOSANGRE.GRUPO='Empleado.GrupoSanguineo'");
        sql.append(" LEFT JOIN TablaGeneral GENERO ON e.Genero=GENERO.Codigo and GENERO.GRUPO='Empleado.Generico'");
        sql.append(" LEFT JOIN TablaGeneral ESTADOCIVIL ON e.EstadoCivil=ESTADOCIVIL.Codigo and ESTADOCIVIL.GRUPO='Empleado.EstadoCivil'");
        sql.append(" LEFT JOIN TablaGeneral TIPODOCUMENTO ON e.TipoDocumento=TIPODOCUMENTO.Codigo and TIPODOCUMENTO.GRUPO='Empleado.TipoDocumento'");
        sql.append(" LEFT JOIN TablaGeneral ESTADO ON e.Estado=ESTADO.Codigo and ESTADO.GRUPO='Empleado.Estado'");
        sql.append(" LEFT JOIN TablaGeneral CONTRATOTRABAJO ON e.ContratoTrabajo=CONTRATOTRABAJO.Codigo and CONTRATOTRABAJO.GRUPO='Empleado.ContratoTrabajo' ");
        sql.append(" LEFT JOIN TablaGeneral TIPOTRABAJO ON e.TipoTrabajador=TIPOTRABAJO.Codigo and TIPOTRABAJO.GRUPO='Empleado.TipoTrabajo' ");
        sql.append(" LEFT JOIN TablaGeneral REGIMENHORARIO ON e.RegimenHorario=REGIMENHORARIO.Codigo and REGIMENHORARIO.GRUPO='Empleado.RegimenHorario' ");
        sql.append(" LEFT JOIN TablaGeneral REGIMENLABORAL ON e.RegimenLaboral=REGIMENLABORAL.Codigo and REGIMENLABORAL.GRUPO='Empleado.RegimenLaboral' ");
        sql.append(" LEFT JOIN TablaGeneral TIPODOMICILIO ON e.TipoDomicilio=TIPODOMICILIO.Codigo and TIPODOMICILIO.GRUPO='Empleado.TipoDomicilio' ");
        sql.append(" LEFT JOIN TablaGeneral RELACION ON e.RelacionContactoEmergencia=RELACION.Codigo and RELACION.GRUPO='Empleado.RelacionContacto' ");

        sql.append(" LEFT JOIN CentroCosto CENTRO ON CENTRO.IdCentroCosto = e.IdCentroCosto");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = e.IdEmpleado AND ((HISTORIAL.FechaInicio < getdate() AND HISTORIAL.FechaFin > getDate()) OR (HISTORIAL.FechaInicio < getdate() AND HISTORIAL.FechaFin IS NULL)) ");

        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");


        sql.append(" where 1=1");
        sql.append(params.filter(" AND UPPER(e.Nombre) LIKE UPPER('%' + :nombres + '%') ", busquedaEmpleadoDto.getNombres()));
        sql.append(params.filter(" AND UPPER(e.ApellidoPaterno) LIKE UPPER('%' + :apellidoPaterno + '%') ", busquedaEmpleadoDto.getApePaterno()));
        sql.append(params.filter(" AND UPPER(e.ApellidoMaterno) LIKE UPPER('%' + :apellidoMaterno + '%') ", busquedaEmpleadoDto.getApeMaterno()));
        sql.append(params.filter(" AND UPPER(e.EmailInterno) LIKE UPPER('%' + :correoElectronico + '%') ", busquedaEmpleadoDto.getCorreoElectronico()));
        sql.append(params.filter(" AND e.Codigo = :codigo ", busquedaEmpleadoDto.getCodigo()));
        sql.append(params.filter(" AND e.TipoDocumento = :tipoDocumento ", busquedaEmpleadoDto.getTipoDocumento()));
        sql.append(params.filter(" AND e.NumeroDocumento = :numeroDocumento ", busquedaEmpleadoDto.getNumeroDocumento()));

        sql.append(params.filter(" AND UN.IdUnidadDeNegocio = :unidadNegocio ", busquedaEmpleadoDto.getUnidadNegocio()));
        sql.append(params.filter(" AND DEP.IdDepartamentoArea = :departamento ", busquedaEmpleadoDto.getDepartamento()));
        sql.append(params.filter(" AND PROY.IdProyecto = :proyecto ", busquedaEmpleadoDto.getProyecto()));

        //TODO Unidad de Negocio, Proyecto, Departamento?
        //TODO Jefe?
        sql.append(params.filter(" AND e.IdCentroCosto = :centroCosto ", busquedaEmpleadoDto.getCentroCosto()));
        sql.append(params.filter(" AND UPPER(e.EmailInterno) LIKE UPPER('%' + :correoElectronico + '%') ", busquedaEmpleadoDto.getCorreoElectronico()));
        sql.append(params.filter(" AND e.Estado = :estado ", busquedaEmpleadoDto.getEstado()));

        sql.append(params.filter(" AND ( "+
        		" (HISTORIAL.IdProyecto IS NOT NULL AND PROY.IdJefe = :idJefeInmediato)  OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NOT NULL AND DEP.IdJefe  = :idJefeInmediato)"+
        		" OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NULL AND HISTORIAL.IdUnidadDeNegocio IS NOT NULL AND UN.IdJefe = :idJefeInmediato) ) ", busquedaEmpleadoDto.getIdJefeInmediato()));

        return sql.toString();
    }

    private String generarVerEmpleado(Long idEmpleado, WhereParams params) {

        StringBuilder sql = new StringBuilder();
        sql.append(" select e.IdEmpleado as idEmpleado, ");
        sql.append(" e.Nombre as nombre, ");
        sql.append(" e.ApellidoPaterno as apellidoPaterno, ");
        sql.append(" e.ApellidoMaterno as apellidoMaterno, ");
        
        sql.append(" CONCAT(e.Nombre,' ',e.ApellidoPaterno, ' ', e.ApellidoMaterno) as nombreCompletoEmpleado, ");
        
        sql.append(" e.TipoDocumento as tipoDocumento, ");
        sql.append(" TIPODOCUMENTO.Nombre as tipoDocumentoString, ");
        sql.append(" e.NumeroDocumento as numeroDocumento, ");
        sql.append(" e.Genero as genero, ");
        sql.append(" GENERO.Nombre as generoString, ");
        sql.append(" e.EstadoCivil as estadoCivil, ");
        sql.append(" ESTADOCIVIL.Nombre as estadoCivilString, ");
        sql.append(" e.GrupoSangre as grupoSangre, ");
        sql.append(" GRUPOSANGRE.Nombre as grupoSangreString, ");
        sql.append(" e.Discapacitado as discapacitado, ");
        sql.append(" e.FechaNacimiento as fechaNacimiento, ");
        sql.append(" e.PaisNacimiento as paisNacimiento, ");
        sql.append(" PAIS.Nombre AS paisNacimientoString, ");
        sql.append(" e.DepartamentoNacimiento as departamentoNacimiento, ");
        sql.append(" DEP.Nombre AS departamentoNacimientoString, ");
        sql.append(" e.ProvinciaNacimiento as provinciaNacimiento, ");
        sql.append(" PROV.Nombre AS provinciaNacimientoString, ");
        sql.append(" e.DistritoNacimiento AS distritoNacimiento, ");
        sql.append(" e.Codigo as codigo, ");
        sql.append(" e.FechaIngreso as fechaIngreso, ");
        sql.append(" e.EmailInterno as emailInterno, ");
        sql.append(" e.TelefonoInterno AS telefonoInterno, ");
        sql.append(" e.AnexoInterno as anexoInterno, ");
        sql.append(" e.IdCentroCosto as idCentroCosto, ");
        sql.append(" CENTRO.Nombre as centroCostoString, ");
        sql.append(" e.ContratoTrabajo as contratoTrabajo, ");
        sql.append(" CONTRATOTRABAJO.Nombre AS contratoTrabajoString, ");
        sql.append(" e.TipoTrabajador as tipoTrabajador, ");
        sql.append(" TIPOTRABAJO.Nombre AS tipoTrabajadorString, ");
        sql.append(" e.RegimenHorario as regimenHorario, ");
        sql.append(" REGIMENHORARIO.Nombre AS regimenHorarioString, ");
        sql.append(" e.RegimenLaboral as regimenLaboral, ");
        sql.append(" REGIMENLABORAL.Nombre AS regimenLaboralString, ");
        sql.append(" e.TipoDomicilio as tipoDomicilio, ");
        sql.append(" TIPODOMICILIO.Nombre AS tipoDomicilioString, ");
        sql.append(" e.PaisDomicilio as paisDomicilio, ");
        sql.append(" PAIS.Nombre AS paisDomicilioString, ");
        sql.append(" e.DepartamentoDomicilio as departamentoDomicilio, ");
        sql.append(" DEP.Nombre AS departamentoDomicilioString, ");
        sql.append(" e.ProvinciaDomicilio as provinciaDomicilio, ");
        sql.append(" PROV.Nombre AS provinciaDomicilioString, ");
        sql.append(" e.DistritoDomicilio AS distritoDomicilio, ");
        sql.append(" e.RelacionContactoEmergencia AS relacionContactoEmergencia, ");
        sql.append(" RELACION.Nombre AS relacionContactoEmergenciaString, ");
        sql.append(" ESTADO.Nombre as estadoString, ");
        sql.append(" e.Estado as estado, ");

        sql.append(" e.TieneEPS as tieneEps, ");
        sql.append(" e.EntidadBancariaSueldo as entidadBancariaSueldo, ");
        sql.append(" e.AFP as afp, ");
        sql.append(" e.CTS as cts, ");
        sql.append(" e.EPS as eps, ");

        sql.append(" ENTIDADBANCARIA.Nombre as entidadBancariaSueldoString, ");
        sql.append(" AFP.Nombre as afpString, ");
        sql.append(" CTS.Nombre as ctsString, ");
        sql.append(" EPS.Nombre as epsString, ");

        sql.append(" e.DireccionDomicilio AS direccionDomicilio, ");
        sql.append(" e.TelefonoCasa AS telefonoCasa, ");
        sql.append(" e.TelefonoCelular AS telefonoCelular, ");
        sql.append(" e.TelefonoAdicional AS telefonoAdicional, ");
        sql.append(" e.EmailPersonal AS emailPersonal, ");
        sql.append(" e.NombreContactoEmergencia AS nombreContactoEmergencia, ");
        sql.append(" e.TelefonoContactoEmergencia AS telefonoContactoEmergencia, ");
        sql.append(" e.EmailContactoEmergencia AS emailContactoEmergencia, ");
        sql.append(" e.Discapacitado AS discapacitado, ");
        sql.append(" e.EsPersonalDeConfianza AS esPersonalDeConfianza, ");

        sql.append(" e.Creador AS creador, ");
        sql.append(" e.Actualizador AS actualizador, ");
        sql.append(" e.FechaCreacion AS fechaCreacion, ");
        sql.append(" e.FechaActualizacion AS fechaActualizacion,  ");
        sql.append(" e.RowVersion AS rowVersion  ");

        sql.append(" from Empleado e  ");

        sql.append(" lEFT JOIN Pais PAIS ON e.PaisNacimiento= PAIS.Codigo ");
        sql.append(" LEFT JOIN Departamento DEP ON e.DepartamentoNacimiento= DEP.Codigo ");
        sql.append(" LEFT JOIN Provincia PROV ON e.ProvinciaNacimiento= PROV.Codigo ");
        sql.append(" LEFT JOIN CentroCosto CENTRO ON CENTRO.IdCentroCosto = e.IdCentroCosto ");
        sql.append(" lEFT JOIN Pais PAISDOM ON e.PaisDomicilio= PAISDOM.Codigo ");
        sql.append(" LEFT JOIN Departamento DEPDOM ON e.DepartamentoDomicilio= DEPDOM.Codigo ");
        sql.append(" LEFT JOIN Provincia PROVDOM ON e.ProvinciaDomicilio= PROVDOM.Codigo ");
        sql.append(" LEFT JOIN TablaGeneral GRUPOSANGRE ON e.GrupoSangre=GRUPOSANGRE.Codigo and GRUPOSANGRE.GRUPO='Empleado.GrupoSanguineo' ");
        sql.append(" LEFT JOIN TablaGeneral GENERO ON e.Genero=GENERO.Codigo and GENERO.GRUPO='Empleado.Generico' ");
        sql.append(" LEFT JOIN TablaGeneral ESTADOCIVIL ON e.EstadoCivil=ESTADOCIVIL.Codigo and ESTADOCIVIL.GRUPO='Empleado.EstadoCivil' ");
        sql.append(" LEFT JOIN TablaGeneral TIPODOCUMENTO ON e.TipoDocumento=TIPODOCUMENTO.Codigo and TIPODOCUMENTO.GRUPO='Empleado.TipoDocumento' ");
        sql.append(" LEFT JOIN TablaGeneral CONTRATOTRABAJO ON e.ContratoTrabajo=CONTRATOTRABAJO.Codigo and CONTRATOTRABAJO.GRUPO='Empleado.ContratoTrabajo' ");
        sql.append(" LEFT JOIN TablaGeneral TIPOTRABAJO ON e.TipoTrabajador=TIPOTRABAJO.Codigo and TIPOTRABAJO.GRUPO='Empleado.TipoTrabajo' ");
        sql.append(" LEFT JOIN TablaGeneral REGIMENHORARIO ON e.RegimenHorario=REGIMENHORARIO.Codigo and REGIMENHORARIO.GRUPO='Empleado.RegimenHorario' ");
        sql.append(" LEFT JOIN TablaGeneral REGIMENLABORAL ON e.RegimenLaboral=REGIMENLABORAL.Codigo and REGIMENLABORAL.GRUPO='Empleado.RegimenLaboral' ");
        sql.append(" LEFT JOIN TablaGeneral TIPODOMICILIO ON e.TipoDomicilio=TIPODOMICILIO.Codigo and TIPODOMICILIO.GRUPO='Empleado.TipoDomicilio' ");
        sql.append(" LEFT JOIN TablaGeneral RELACION ON e.RelacionContactoEmergencia=RELACION.Codigo and RELACION.GRUPO='Empleado.RelacionContacto' ");

        sql.append(" LEFT JOIN TablaGeneral CTS ON e.CTS=CTS.Codigo and CTS.GRUPO='Entidad Financiera' ");
        sql.append(" LEFT JOIN TablaGeneral AFP ON e.AFP=AFP.Codigo and AFP.GRUPO='AFP' ");
        sql.append(" LEFT JOIN TablaGeneral EPS ON e.EPS=EPS.Codigo and EPS.GRUPO='EPS' ");
        sql.append(" LEFT JOIN TablaGeneral ENTIDADBANCARIA ON e.EntidadBancariaSueldo=ENTIDADBANCARIA.Codigo and ENTIDADBANCARIA.GRUPO='Entidad Financiera' ");

        sql.append(" LEFT JOIN TablaGeneral ESTADO ON e.Estado=ESTADO.Codigo and ESTADO.GRUPO='Empleado.Estado' ");

        sql.append(" where 1=1");
        sql.append(params.filter(" AND e.IdEmpleado = :idEmpleado ", idEmpleado));

        return sql.toString();
    }

    private String generarVerEducacion(Long idEmpleado, WhereParams params) {

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append(" e.IdEducacion AS idEducacion, ");
        sql.append(" e.Institucion AS institucion, ");
        sql.append(" e.FechaInicio AS fechaInicio, ");
        sql.append(" e.FechaFin AS fechaFin, ");
        sql.append(" e.Titulo AS titulo, ");
        sql.append(" e.Descripcion AS descripcion, ");
        sql.append(" e.NivelEducacion AS nivelEducacion, ");
        sql.append(" NIVELEDU.Nombre AS nombreNivelEducacion, ");

        sql.append(" e.Creador AS creador, ");
        sql.append(" e.Actualizador AS actualizador, ");
        sql.append(" e.FechaCreacion AS fechaCreacion, ");
        sql.append(" e.FechaActualizacion AS fechaActualizacion,  ");
        sql.append(" e.RowVersion AS rowVersion  ");

        sql.append(" FROM Educacion e ");
        sql.append(" LEFT JOIN TablaGeneral NIVELEDU ON e.NivelEducacion = NIVELEDU.Codigo AND NIVELEDU.Grupo = 'Empleado.NivelEducacion' ");


        sql.append(" where 1=1");
        sql.append(params.filter(" AND e.IdEmpleado = :idEmpleado ", idEmpleado));

        return sql.toString();
    }

    private String generarVerEquipoEntregado(Long idEmpleado, WhereParams params) {

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT e.Descripcion AS descripcion, ");
        sql.append(" e.IdEquipoEntregado AS idEquipoEntregado, ");
        sql.append(" e.FechaEntrega AS fechaEntrega, ");
        sql.append(" e.FechaDevolucion AS fechaDevolucion, ");
        sql.append(" e.TipoEquipo AS tipoEquipo, ");
        sql.append(" TIPOEQUIPO.Nombre AS nombreTipoEquipo, ");
        sql.append(" e.Estado AS estado, ");
        sql.append(" ESTADO.Nombre AS nombreEstado, ");

        sql.append(" e.Creador AS creador, ");
        sql.append(" e.Actualizador AS actualizador, ");
        sql.append(" e.FechaCreacion AS fechaCreacion, ");
        sql.append(" e.FechaActualizacion AS fechaActualizacion,  ");
        sql.append(" e.RowVersion AS rowVersion  ");

        sql.append(" FROM EquipoEntregado e ");
        sql.append(" LEFT JOIN TablaGeneral TIPOEQUIPO ON TIPOEQUIPO.Codigo=e.TipoEquipo AND TIPOEQUIPO.Grupo = 'EquiposEntregados.TipoEquipo' ");
        sql.append(" LEFT JOIN TablaGeneral ESTADO ON ESTADO.Codigo=e.Estado AND ESTADO.Grupo = 'EquiposEntregados.Estado' ");

        sql.append(" where 1=1");
        sql.append(params.filter(" AND e.IdEmpleado = :idEmpleado ", idEmpleado));

        return sql.toString();
    }

    private String generarVerDependiente(Long idEmpleado, WhereParams params) {

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append("d.IdDependiente AS idDependiente, ");
        sql.append("d.Nombre AS nombre, ");
        sql.append("d.ApellidoPaterno AS apellidoPaterno, ");
        sql.append("d.ApellidoMaterno AS apellidoMaterno, ");
        sql.append("RELACION.Nombre AS nombreRelacion, ");
        sql.append("d.Relacion AS relacion, ");
        sql.append("d.TipoDocumento AS tipoDocumento, ");
        sql.append("TIPODOC.Nombre AS nombreTipoDocumento, ");
        sql.append("d.NumeroDocumento AS numeroDocumento, ");
        sql.append("d.FechaNacimiento AS fechaNacimiento, ");

        sql.append(" d.Creador AS creador, ");
        sql.append(" d.Actualizador AS actualizador, ");
        sql.append(" d.FechaCreacion AS fechaCreacion, ");
        sql.append(" d.FechaActualizacion AS fechaActualizacion,  ");
        sql.append(" d.RowVersion AS rowVersion  ");

        sql.append("FROM Dependiente d ");

        sql.append("LEFT JOIN TablaGeneral RELACION ON RELACION.Codigo=d.Relacion AND RELACION.Grupo = 'Empleado.RelacionDependiente' ");
        sql.append("LEFT JOIN TablaGeneral TIPODOC ON TIPODOC.Codigo=d.TipoDocumento AND TIPODOC.Grupo = 'Empleado.TipoDocumento' ");

        sql.append(" where 1=1");
        sql.append(params.filter(" AND d.IdEmpleado = :idEmpleado ", idEmpleado));

        return sql.toString();
    }

    private String generarVerLicencia(PeriodoEmpleadoViewModel periodoEmpleado, WhereParams params) {

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT  distinct");
        sql.append(" l.IdLicencia AS IdLicencia, ");
        sql.append(" TIPOLIC.Nombre AS nombreMotivo, ");
        sql.append(" l.Comentario AS comentario, ");
        sql.append(" l.FechaInicio AS fechaInicio, ");
        sql.append(" l.FechaFin AS fechaFin, ");
        sql.append(" l.Dias AS dias, ");

        sql.append(" l.Creador AS creador, ");
        sql.append(" l.Actualizador AS actualizador, ");
        sql.append(" l.FechaCreacion AS fechaCreacion, ");
        sql.append(" l.FechaActualizacion AS fechaActualizacion,  ");
        sql.append(" l.RowVersion AS rowVersion  ");

        sql.append(" FROM Licencia l ");
        sql.append(" LEFT JOIN Empleado ON Empleado.IdEmpleado = l.IdEmpleado ");
        sql.append(" LEFT JOIN PeriodoEmpleado ON PeriodoEmpleado.IdEmpleado = Empleado.IdEmpleado ");
        sql.append(" LEFT JOIN TipoLicencia TIPOLIC ON l.IdTipoLicencia=TIPOLIC.IdTipoLicencia ");

        sql.append(" where l.IdEmpleado = "+periodoEmpleado.getIdEmpleado());

        if(periodoEmpleado.getIdPeriodoEmpleado() != null){
        	sql.append(" AND PeriodoEmpleado.IdPeriodoEmpleado ="+periodoEmpleado.getIdPeriodoEmpleado());
        	sql.append(" AND ( ");
        	sql.append(" (  ");
        	sql.append(" l.FechaInicio<=PeriodoEmpleado.FechaFin ");
        	sql.append(" AND l.FechaInicio>=PeriodoEmpleado.FechaInicio ");
        	sql.append(" ) ");
        	sql.append(" OR ");
        	sql.append(" ( ");
        	sql.append(" l.FechaFin<=PeriodoEmpleado.FechaFin ");
        	sql.append(" AND l.FechaFin>=PeriodoEmpleado.FechaInicio");
        	sql.append(" ) ");
        	sql.append(" ) ");

        }


        return sql.toString();
    }

	public List<HorasExtraEmpleadoResultViewModel> busquedaHorasExtrasEmpleado(
			HorasExtraEmpleadoFilterViewModel busquedaHorasExtraEmpleadoDto) {
		WhereParams params = new WhereParams();
        String sql = generarBusquedaHorasExtrasEmpleado(busquedaHorasExtraEmpleadoDto, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<>(HorasExtraEmpleadoResultViewModel.class));
	}

	private String generarBusquedaHorasExtrasEmpleado(HorasExtraEmpleadoFilterViewModel busquedaHorasExtraEmpleadoDto,
			WhereParams params) {
		StringBuilder sql = new StringBuilder();
        sql.append(" SELECT distinct he.IdHorasExtra AS idHorasExtra, ");
        sql.append(" EMPLEADO.IdEmpleado AS idEmpleado, ");
        sql.append(" he.Motivo AS motivo, ");
        sql.append(" he.HoraSalidaSolicitado AS horaSalidaSolicitado, ");
        sql.append(" he.HoraSalidaHorario AS horaSalidaHorario, ");
        sql.append(" CONCAT(ATENDIDO.ApellidoPaterno, ' ', ATENDIDO.ApellidoMaterno, ', ', ATENDIDO.Nombre) as nombreJefeInmediato, ");
        sql.append(" CONCAT(EMPLEADO.ApellidoPaterno, ' ', EMPLEADO.ApellidoMaterno, ', ', EMPLEADO.Nombre) as nombreEmpleado, ");
        sql.append(" he.Fecha as fecha, ");
        sql.append(" he.Horas as horas, ");

        sql.append(" ESTADO.Nombre AS estado ");

        sql.append(" from HorasExtra he ") ;

        sql.append(" LEFT JOIN TablaGeneral ESTADO ON he.Estado=ESTADO.Codigo and ESTADO.GRUPO='Permiso.Estado'");
        sql.append(" LEFT JOIN Empleado EMPLEADO ON he.IdEmpleado = EMPLEADO.IdEmpleado ");

        sql.append(" LEFT JOIN Empleado ATENDIDO ON he.IdAtendidoPor = ATENDIDO.IdEmpleado ");

        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = he.IdEmpleado AND ((HISTORIAL.FechaInicio < getdate() AND HISTORIAL.FechaFin > getDate()) AND (HISTORIAL.FechaInicio < getdate() AND HISTORIAL.FechaFin IS NULL)) ");

        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");

        sql.append("  LEFT JOIN Empleado ep on PROY.IdJefe = ep.IdEmpleado ");
		sql.append("  LEFT JOIN Empleado eda on DEP.IdJefe = eda.IdEmpleado ");
		sql.append("  LEFT JOIN Empleado eun on UN.IdJefe = eun.IdEmpleado ");

        sql.append(" where 1=1 ");
        sql.append(params.filter(" AND he.Estado = :estado ", busquedaHorasExtraEmpleadoDto.getEstado()));
        sql.append(params.filterDateDesde_US(" AND he.Fecha " , busquedaHorasExtraEmpleadoDto.getFechaInicio()));
        sql.append(params.filterDateHasta_US(" AND he.Fecha ", busquedaHorasExtraEmpleadoDto.getFechaFin()));
        sql.append(params.filter(" AND UN.IdUnidadDeNegocio = :unidadNegocio ", busquedaHorasExtraEmpleadoDto.getUnidadNegocio()));
        sql.append(params.filter(" AND DEP.IdDepartamentoArea = :departamento ", busquedaHorasExtraEmpleadoDto.getDepartamento()));
        sql.append(params.filter(" AND PROY.IdProyecto = :proyecto ", busquedaHorasExtraEmpleadoDto.getProyecto()));

        sql.append(params.filter(" AND he.IdHorasExtra = :idHorasExtra ", busquedaHorasExtraEmpleadoDto.getIdHorasExtra()));

        sql.append(params.filter(" AND EMPLEADO.IdEmpleado = :idEmpleado ", busquedaHorasExtraEmpleadoDto.getIdEmpleado()));

        sql.append(params.filter(" AND he.IdAtendidoPor = :idJefeEmpleado ", busquedaHorasExtraEmpleadoDto.getIdJefeInmediato()));

        /*sql.append(params.filter(" AND ( "+
        		" (HISTORIAL.IdProyecto IS NOT NULL AND PROY.IdJefe = :idJefeInmediato)  OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NOT NULL AND DEP.IdJefe  = :idJefeInmediato)"+
        		" OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NULL AND HISTORIAL.IdUnidadDeNegocio IS NOT NULL AND UN.IdJefe = :idJefeInmediato) ) ", busquedaHorasExtraEmpleadoDto.getIdJefeInmediato()));*/

        return sql.toString();
	}

	public HorasExtraViewModel informacionAdicionalHorasExtras(EmpleadoViewModel empleado) {
		WhereParams params = new WhereParams();
		String sql = obtenerInformacionAdicionalHorasExtras(empleado, params);

		return jdbcTemplate.queryForObject(sql, params.getParams(),
				new BeanPropertyRowMapper<HorasExtraViewModel>(HorasExtraViewModel.class));
	}

	private String obtenerInformacionAdicionalHorasExtras(EmpleadoViewModel empleado, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT TOP 1 ");
		return sql.toString();
	}

	public HorasExtraViewModel getHorarioEmpleadoDia(Long idEmpleado, Integer dayOfWeek) {
		WhereParams params = new WhereParams();
		String dayOfWeekVal = "";
		if(dayOfWeek==1){
			dayOfWeekVal = "DO";
		}else if(dayOfWeek==2){
			dayOfWeekVal = "LU";
		}else if(dayOfWeek==3){
			dayOfWeekVal = "MA";
		}else if(dayOfWeek==4){
			dayOfWeekVal = "MI";
		}else if(dayOfWeek==5){
			dayOfWeekVal = "JU";
		}else if(dayOfWeek==6){
			dayOfWeekVal = "VI";
		}else if(dayOfWeek==7){
			dayOfWeekVal = "SA";
		}

		String sql = getHorarioEmpleadoDia(idEmpleado, dayOfWeekVal, params);

		return jdbcTemplate.queryForObject(sql, params.getParams(),
				new BeanPropertyRowMapper<HorasExtraViewModel>(HorasExtraViewModel.class));
	}

	private String getHorarioEmpleadoDia(Long idEmpleado,String dayOfWeekVal, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select hed.Salida AS horaSalidaHorario");
		sql.append(" FROM HorarioEmpleado he  ");
		sql.append(" LEFT JOIN HorarioEmpleadoDia hed on hed.IdHorarioEmpleado = he.IdHorarioEmpleado ");
		sql.append(" WHERE 1 = 1 ");
		sql.append(" AND (he.InicioVigencia<= getdate() AND he.FinVigencia>= getdate()) ");
		sql.append(" AND he.IdEmpleado="+idEmpleado);
		sql.append(" AND hed.DiaSemana='"+dayOfWeekVal+"'");
		return sql.toString();
	}

	public List<HorasExtraViewModel> listHorasExtraEmpleado(Long idEmpleado) {
		WhereParams params = new WhereParams();
		String sql = getListHorasExtraEmpleado(idEmpleado);

		return jdbcTemplate.query(sql, params.getParams(),
				new BeanPropertyRowMapper<HorasExtraViewModel>(HorasExtraViewModel.class));
	}

	private String getListHorasExtraEmpleado(Long idEmpleado) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select h.Fecha as fecha, ");
		sql.append(" h.IdHorasExtra as idHorasExtra, ");
		sql.append(" h.HoraSalidaHorario as horaSalidaHorario, ");
		sql.append(" h.HoraSalidaSolicitado as horaSalidaSolicitado ");
		sql.append(" FROM HorasExtra h ");
		sql.append(" WHERE 1 = 1 AND h.Estado != 'R' ");
		sql.append(" AND h.IdEmpleado ="+idEmpleado);
		return sql.toString();
	}

	public List<EquipoEntregadoViewModel> obtenerEquiposPendientesDevolucion(Long idEmpleado) {
		WhereParams params = new WhereParams();
		String sql = getEquiposPendientesDevolucion(idEmpleado, params);

		return jdbcTemplate.query(sql, params.getParams(),
				new BeanPropertyRowMapper<EquipoEntregadoViewModel>(EquipoEntregadoViewModel.class));
	}

	private String getEquiposPendientesDevolucion(Long idEmpleado, WhereParams params) {
		StringBuilder sql = new StringBuilder();

		   sql.append("  select TIPO.Nombre tipoEquipo, \n");
		   sql.append("  ee.Descripcion descripcion, \n");
		   sql.append("  ee.FechaEntrega fechaEntrega, \n");
		   sql.append("  ee.FechaDevolucion fechaDevolucion,  \n");
		   sql.append("  ESTADO.Nombre estado \n");

		   sql.append("  From EquipoEntregado ee \n");
		   sql.append("  LEFT JOIN TablaGeneral ESTADO ON ee.Estado = ESTADO.Codigo	AND ESTADO.GRUPO = 'EquiposEntregados.Estado' \n");
		   sql.append("  LEFT JOIN TablaGeneral TIPO ON ee.TipoEquipo = TIPO.Codigo	AND TIPO.GRUPO = 'EquiposEntregados.TipoEquipo'  \n");
		   sql.append("  WHERE 1 = 1 \n");
		   sql.append(params.filter(" AND ee.IdEmpleado =:idEmpleado ", idEmpleado));



		return sql.toString();
	}

	public Long countEquiposPendientesDevolucion(Long idEmpleado) {
		WhereParams params = new WhereParams();
		String sql = obtenerEquiposPendientesDevolucion(idEmpleado, params);
		return calculateTotalRows(sql, params);
	}

	private String obtenerEquiposPendientesDevolucion(Long idEmpleado, WhereParams params) {
		StringBuilder sql = new StringBuilder();

		   sql.append("  select TIPO.Nombre tipoEquipo, \n");
		   sql.append("  ee.Descripcion descripcion, \n");
		   sql.append("  ee.FechaEntrega fechaEntrega, \n");
		   sql.append("  ee.FechaDevolucion fechaDevolucion,  \n");
		   sql.append("  ESTADO.Nombre estado \n");

		   sql.append("  From EquipoEntregado ee \n");
		   sql.append("  LEFT JOIN TablaGeneral ESTADO ON ee.Estado = ESTADO.Codigo	AND ESTADO.GRUPO = 'EquiposEntregados.Estado' \n");
		   sql.append("  LEFT JOIN TablaGeneral TIPO ON ee.TipoEquipo = TIPO.Codigo	AND TIPO.GRUPO = 'EquiposEntregados.TipoEquipo'  \n");
		   sql.append("  WHERE 1 = 1 \n");
		   sql.append("  AND ESTADO.Codigo = 'EN' \n");
		   sql.append(params.filter(" AND ee.IdEmpleado =:idEmpleado ", idEmpleado));



		return sql.toString();
	}

	private long calculateTotalRows(String queryBase, WhereParams params) {
        String query = "SELECT COUNT(1) FROM (" + queryBase + ") X";
        return jdbcTemplate.queryForObject(query, params.getParams(), Long.class);
    }

	/**
	 * Busqueda Marcacion Dashboard
	 * @param busquedaMarcacionDto
	 * @return
	 */
	public List<MarcacionViewModel> busquedaMarcacionesDashboard(MarcacionFilterViewModel busquedaMarcacionDto) {
		WhereParams params = new WhereParams();
        String sql = generarVerBusquedaMarcacionesDashboard(busquedaMarcacionDto, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<>(MarcacionViewModel.class));
	}

	private String generarVerBusquedaMarcacionesDashboard(MarcacionFilterViewModel busquedaMarcacionDto, WhereParams params){
		StringBuilder sql = new StringBuilder();

        sql.append(" SELECT distinct ");
        sql.append(" MAR.IdMarcacion AS idMarcacion, ");
        sql.append(" MAR.Fecha AS fecha, ");
        sql.append(" MAR.HoraIngreso AS horaIngreso, ");
        sql.append(" MAR.HoraInicioAlmuerzo AS horaInicioAlmuerzo, ");
        sql.append(" MAR.HoraFinAlmuerzo AS horaFinAlmuerzo, ");
        sql.append(" MAR.HoraSalida AS horaSalida, ");

        sql.append(" MAR.DemoraEntrada AS demoraEntrada, ");
        sql.append(" MAR.DemoraAlmuerzo AS demoraAlmuerzo, ");
        sql.append(" MAR.DemoraSalida AS demoraSalida, ");

        sql.append(" CASE ");
        sql.append(" WHEN (MAR.Tardanza =1) THEN 'Si' ");
        sql.append(" WHEN (MAR.Tardanza =0) THEN 'No' ");
        sql.append(" ELSE 'No' ");
        sql.append(" END AS tardanza, ");
        sql.append(" CASE ");
        sql.append(" WHEN (MAR.Inasistencia =1) THEN 'Si' ");
        sql.append(" WHEN (MAR.Inasistencia =0) THEN 'No' ");
        sql.append(" ELSE 'No' ");
        sql.append(" END AS inasistencia, ");
        sql.append(" 'No' AS solicitudCambio, ");

        sql.append(" MAR.HoraIngresoHorario AS horaIngresoHorario, ");
        sql.append(" MAR.HoraSalidaHorario AS horaSalidaHorario, ");

        sql.append(" EMP.Nombre+' '+EMP.ApellidoPaterno+' '+EMP.ApellidoMaterno AS nombreCompletoEmpleado, ");

        sql.append(" EMP.Nombre AS nombreEmpleado, ");
        sql.append(" EMP.ApellidoPaterno AS apelPaternoEmpleado, ");
        sql.append(" EMP.ApellidoMaterno AS apelMaternoEmpleado, ");
        sql.append(" EMP.Codigo AS codigoEmpleado ");
        sql.append(" FROM Marcacion MAR ");
        sql.append(" LEFT JOIN Empleado EMP ON EMP.IdEmpleado = MAR.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMP.IdEmpleado AND (HISTORIAL.FechaInicio < MAR.Fecha AND HISTORIAL.FechaFin > MAR.Fecha) ");

        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append(" WHERE 1=1 ");

        sql.append(params.filter(" AND UN.IdUnidadDeNegocio = :unidadNegocio ", busquedaMarcacionDto.getUnidadNegocio()));
        sql.append(params.filter(" AND DEP.IdDepartamentoArea = :departamento ", busquedaMarcacionDto.getDepartamento()));
        sql.append(params.filter(" AND PROY.IdProyecto = :proyecto ", busquedaMarcacionDto.getProyecto()));

        sql.append(params.filter(" AND MAR.Fecha = :fecha " , busquedaMarcacionDto.getFecha()));


		return sql.toString();
	}

	public EmpleadoViewModel findOneAccessJwtToken(Long idEmpleado) {
		WhereParams params = new WhereParams();
        String sql = findOneAccessJwtTokenQuery(idEmpleado, params);

        return jdbcTemplate.queryForObject(sql,
                params.getParams(), new BeanPropertyRowMapper<EmpleadoViewModel>(EmpleadoViewModel.class));
	}

	private String findOneAccessJwtTokenQuery(Long idEmpleado, WhereParams params) {
		StringBuilder sql = new StringBuilder();
        sql.append(" select e.IdEmpleado as idEmpleado, ");
        sql.append(" e.Nombre as nombre, ");
        sql.append(" e.ApellidoPaterno as apellidoPaterno, ");
        sql.append(" e.ApellidoMaterno as apellidoMaterno ");
        sql.append(" from Empleado e  ");
        sql.append(" where 1=1");
        sql.append(params.filter(" AND e.IdEmpleado = :idEmpleado ", idEmpleado));

        return sql.toString();
	}

	private String generarBusquedaEmpleadoQuickSearch(QuickFilterViewModel quickFilter, WhereParams params) {

        StringBuilder sql = new StringBuilder();
        sql.append(" select distinct e.IdEmpleado, e.Nombre as nombre,  ");
        sql.append(" e.ApellidoPaterno as apellidoPaterno, e.ApellidoMaterno as apellidoMaterno,  ");
        sql.append(" TIPODOCUMENTO.Nombre as tipoDocumento, ");
        sql.append(" e.Codigo as codigo,  ");
        sql.append(" e.NumeroDocumento as numeroDocumento,  ");
        sql.append(" e.EmailInterno as emailInterno, e.Codigo as codigo,  ");

        sql.append(" ESTADO.Nombre as estado, ");
        
        sql.append(" GENERO.Nombre as genero, ");
        sql.append(" ESTADOCIVIL.Nombre as estadoCivil, ");
        sql.append(" GRUPOSANGRE.Nombre as grupoSangre, ");
        sql.append(" e.FechaNacimiento as fechaNacimiento ,");
        sql.append(" PAIS.Nombre as paisNacimiento, ");
        
        sql.append(" CASE");
        sql.append(" WHEN Discapacitado = 1 THEN 'Si'");
        sql.append(" WHEN Discapacitado = 0 THEN 'No'");
        sql.append(" ELSE 'No'");
        sql.append(" END AS discapacitado ");
        
        
        sql.append(" from Empleado e");
        sql.append(" LEFT JOIN Pais PAIS ON e.PaisNacimiento=PAIS.Codigo ");
        //sql.append(" LEFT JOIN TablaGeneral DISCAPACITADO ON e.Discapacitado=DISCAPACITADO.Codigo and DISCAPACITADO.GRUPO='Empleado.Discapacitado'");
        sql.append(" LEFT JOIN TablaGeneral GRUPOSANGRE ON e.GrupoSangre=GRUPOSANGRE.Codigo and GRUPOSANGRE.GRUPO='Empleado.GrupoSanguineo'");
        sql.append(" LEFT JOIN TablaGeneral GENERO ON e.Genero=GENERO.Codigo and GENERO.GRUPO='Empleado.Generico'");
        sql.append(" LEFT JOIN TablaGeneral ESTADOCIVIL ON e.EstadoCivil=ESTADOCIVIL.Codigo and ESTADOCIVIL.GRUPO='Empleado.EstadoCivil'");
        sql.append(" LEFT JOIN TablaGeneral TIPODOCUMENTO ON e.TipoDocumento=TIPODOCUMENTO.Codigo and TIPODOCUMENTO.GRUPO='Empleado.TipoDocumento'");
        sql.append(" LEFT JOIN TablaGeneral ESTADO ON e.Estado=ESTADO.Codigo and ESTADO.GRUPO='Empleado.Estado'");
        sql.append(" LEFT JOIN CentroCosto CENTRO ON CENTRO.IdCentroCosto = e.IdCentroCosto");
        
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = e.IdEmpleado AND ((HISTORIAL.FechaInicio < getdate() AND HISTORIAL.FechaFin > getDate()) OR (HISTORIAL.FechaInicio < getdate() AND HISTORIAL.FechaFin IS NULL)) ");
        
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        
        
        sql.append(" where 1=1");
        sql.append(params.filter(" AND (UPPER(e.Nombre) LIKE UPPER('%' + :value + '%') ", quickFilter.getValue()));
        sql.append(params.filter(" OR UPPER(e.ApellidoPaterno) LIKE UPPER('%' + :value + '%') ", quickFilter.getValue()));
        sql.append(params.filter(" OR UPPER(e.ApellidoMaterno) LIKE UPPER('%' + :value + '%')) ", quickFilter.getValue()));
        sql.append(params.filter(" AND [dbo].[GetJefeInmediato] (e.IdEmpleado,HISTORIAL.IdHistorialLaboral) = :idEmpleado",quickFilter.getIdEmpleado()));                

        return sql.toString();
    }

	@Override
	public List<MarcacionViewModel> busquedaRapidaMarcacionEmpleado(QuickFilterViewModel quickFilter) {
		WhereParams params = new WhereParams();
        String sql = generarBusquedaRapidaMarcacionesEmpleado((MarcacionQuickFilterViewModel)quickFilter, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<>(MarcacionViewModel.class));
	}

	private String generarBusquedaRapidaMarcacionesEmpleado(MarcacionQuickFilterViewModel quickFilter, WhereParams params) {
		StringBuilder sql = new StringBuilder();

        sql.append(" SELECT distinct ");
        sql.append(" MAR.IdMarcacion AS idMarcacion, ");
        sql.append(" MAR.Fecha AS fecha, ");
        sql.append(" MAR.HoraIngreso AS horaIngreso, ");
        sql.append(" MAR.HoraInicioAlmuerzo AS horaInicioAlmuerzo, ");
        sql.append(" MAR.HoraFinAlmuerzo AS horaFinAlmuerzo, ");
        sql.append(" MAR.HoraSalida AS horaSalida, ");

        sql.append(" MAR.DemoraEntrada AS demoraEntrada, ");
        sql.append(" MAR.DemoraAlmuerzo AS demoraAlmuerzo, ");
        sql.append(" MAR.DemoraSalida AS demoraSalida, ");

        sql.append(" CASE ");
        sql.append(" WHEN (MAR.EsPersonaDeConfianza =1) THEN 'Si' ");
        sql.append(" WHEN (MAR.EsPersonaDeConfianza =0) THEN 'No' ");
        sql.append(" ELSE 'No' ");
        sql.append(" END AS esPersonaDeConfianza, ");
        sql.append(" CASE ");
        sql.append(" WHEN (MAR.Inasistencia =1) THEN 'Si' ");
        sql.append(" WHEN (MAR.Inasistencia =0) THEN 'No' ");
        sql.append(" ELSE 'No' ");
        sql.append(" END AS inasistencia, ");
        
        sql.append(" CASE ");
        sql.append(" WHEN (SELECT count(*) FROM SolicitudCambioMarcacion SOL WHERE SOL.IdMarcacion = MAR.IdMarcacion)=0 THEN 'No' ");
        sql.append(" ELSE 'Si' ");
        sql.append(" END AS solicitudCambio, ");

        sql.append(" CASE ");
        sql.append(" WHEN (MAR.EsPersonaDeConfianza =1) THEN 'Sin Marcar' ");
        sql.append(" WHEN (MAR.Inasistencia =1 AND MAR.EsPersonaDeConfianza =0) THEN 'Inasistencia' ");
        sql.append(" WHEN (MAR.Vacaciones =1) THEN 'Vacaciones' ");
        sql.append(" WHEN (MAR.Licencia =1) THEN 'Licencia' ");
        sql.append(" WHEN (MAR.Tardanza =1) THEN 'Tardanza' ");
        sql.append(" ELSE 'Puntual' ");
        sql.append(" END AS estado, ");
        
        sql.append(" MAR.HoraIngresoHorario AS horaIngresoHorario, ");
        sql.append(" MAR.HoraSalidaHorario AS horaSalidaHorario, ");

        sql.append(" (EMP.ApellidoPaterno+' '+EMP.ApellidoMaterno+', '+EMP.Nombre) AS nombreCompletoEmpleado, ");

        sql.append(" EMP.Nombre AS nombreEmpleado, ");
        sql.append(" EMP.ApellidoPaterno AS apelPaternoEmpleado, ");
        sql.append(" EMP.ApellidoMaterno AS apelMaternoEmpleado, ");
        sql.append(" EMP.Codigo AS codigoEmpleado, ");

        //sql.append(" PROY.Nombre AS nombreProyecto, ");
        //sql.append(" DEP.Nombre AS nombreDepartamento, ");
        //sql.append(" UN.Nombre AS nombreUnidadNegocio ");
        sql.append(" MAR.HorasTrabajoReal AS horasTrabajoReal, ");
        sql.append(" MAR.HorasPermiso AS horasPermiso, ");
        sql.append(" MAR.HorasExtra AS horasExtra, ");
        sql.append(" MAR.HorasTrabajoPendiente AS horasTrabajoPendiente ");

        sql.append(" FROM Marcacion MAR ");
        sql.append(" LEFT JOIN Empleado EMP ON EMP.IdEmpleado = MAR.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMP.IdEmpleado AND (HISTORIAL.FechaInicio < MAR.Fecha AND HISTORIAL.FechaFin > MAR.Fecha) ");

        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append(" WHERE 1=1 ");

        sql.append(params.filter(" AND UPPER(EMP.Nombre) LIKE UPPER('%' + :value + '%') ", quickFilter.getValue()));
        sql.append(params.filter(" OR UPPER(EMP.ApellidoPaterno) LIKE UPPER('%' + :value + '%') ", quickFilter.getValue()));
        sql.append(params.filter(" OR UPPER(EMP.ApellidoMaterno) LIKE UPPER('%' + :value + '%') ", quickFilter.getValue()));
        sql.append(params.filterDateDesde_US(" AND MAR.Fecha " , quickFilter.getFechaDesde()));
        sql.append(params.filterDateHasta_US(" AND MAR.Fecha ", quickFilter.getFechaHasta()));

        sql.append(" ORDER BY MAR.Fecha DESC, nombreCompletoEmpleado ");

		return sql.toString();
	}
	
	private String generarHorasSemanalesLunesViernes(Long idHorarioEmpleado, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select sum((DATEDIFF(minute,Entrada,Salida)/60.0)-TiempoAlmuerzo)as horasSemanales from horarioEmpleadoDia where Laboral=1 ");
		sql.append(params.filter(" and idHorarioEmpleado=:idHorarioEmpleado ",idHorarioEmpleado));
		return sql.toString();
	}
	
	private String generarHorasSemanalesCubiertas(Long idHorario, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select sum((DATEDIFF(minute,Entrada,Salida)/60.0)-TiempoAlmuerzo)as horasSemanales from horarioDia where Laboral=1 ");
		sql.append(params.filter(" and idHorario=:idHorario ",idHorario));
		return sql.toString();
	}
	
	private String generarHoraDiaPorDia(Long idHorarioEmpleado, String dia,WhereParams params) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select idHorarioEmpleadoDia, idHorarioEmpleado, DiaSemana, Laboral,Entrada, Salida, TiempoAlmuerzo from horarioEmpleadoDia where Laboral=1 ");
		sql.append(params.filter(" and idHorarioEmpleado=:idHorarioEmpleado ",idHorarioEmpleado));
		sql.append(params.filter(" and DiaSemana=:diaSemana ",dia));
		return sql.toString();
	}

	 @Override
	 public EmpleadoViewModel obtenerEmpleadoCabecera(Long idEmpleado) {

	        WhereParams params = new WhereParams();
	        String sql = generarObtenerEmpleadoCabecera(idEmpleado, params);

	        return jdbcTemplate.queryForObject(sql,
	                params.getParams(), new BeanPropertyRowMapper<EmpleadoViewModel>(EmpleadoViewModel.class));
	 }
	 
	 private String generarObtenerEmpleadoCabecera(Long idEmpleado, WhereParams params) {

	        StringBuilder sql = new StringBuilder();
	        sql.append(" select e.IdEmpleado as idEmpleado, ");
	        sql.append(" e.Nombre as nombre, ");
	        sql.append(" e.ApellidoPaterno as apellidoPaterno, ");
	        sql.append(" e.ApellidoMaterno as apellidoMaterno, ");
	        
	        sql.append(" CONCAT(e.Nombre,' ',e.ApellidoPaterno, ' ', e.ApellidoMaterno) as nombreCompletoEmpleado ");
	        sql.append(" from Empleado e  ");

	        sql.append(" where 1=1");
	        sql.append(params.filter(" AND e.IdEmpleado = :idEmpleado ", idEmpleado));

	        return sql.toString();
	    }
	

}
