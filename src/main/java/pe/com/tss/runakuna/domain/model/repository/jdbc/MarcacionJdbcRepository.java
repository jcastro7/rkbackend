package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.view.model.EmpleadoViewModel;
import pe.com.tss.runakuna.view.model.GenerarReporteMarcacionAcumuladaViewModel;
import pe.com.tss.runakuna.view.model.GenerarReporteMarcacionViewModel;
import pe.com.tss.runakuna.view.model.MarcacionFilterViewModel;
import pe.com.tss.runakuna.view.model.MarcacionViewModel;
import pe.com.tss.runakuna.view.model.NotificacionTardanzaViewModel;
import pe.com.tss.runakuna.view.model.RegistroMarcacionEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.RegistroMarcacionViewModel;
import pe.com.tss.runakuna.view.model.ReporteMarcacionViewModel;
import pe.com.tss.runakuna.support.WhereParams;
import pe.com.tss.runakuna.util.DateUtil;

import static java.util.stream.Collectors.joining;

@Repository
public class MarcacionJdbcRepository implements MarcacionRepository {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("marcacionDataSource") 
    DataSource marcacionDataSource;
    
    @Autowired
    DataSource dataSource;

    private JdbcTemplate marcacionJdbcTemplate;
    
    private NamedParameterJdbcTemplate jdbcTemplate;
    
    private String getdate;

    @PostConstruct
    public void init() {
    	marcacionJdbcTemplate = new JdbcTemplate(marcacionDataSource);
    	marcacionJdbcTemplate.setResultsMapCaseInsensitive(true);
    	
    	jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    	
    	Date date = new Date();
		getdate = DateUtil.format(new SimpleDateFormat("yyyy-MM-dd"), date);
    }

    @Override
    public  List<RegistroMarcacionEmpleadoViewModel> obtenerMarcacion() {

       SimpleJdbcCall jdbcCall = new SimpleJdbcCall(marcacionJdbcTemplate).withProcedureName("spuMarcacionesPendientes")
    		   .withCatalogName("dbo").
    		   returningResultSet("Marcaciones",new BeanPropertyRowMapper<RegistroMarcacionEmpleadoViewModel>(RegistroMarcacionEmpleadoViewModel.class));
       
       Map<String, Object> out = jdbcCall.execute(new HashMap<String, Object>(0));
              
       List<RegistroMarcacionEmpleadoViewModel> lista = (List)out.get("Marcaciones");
             
       return lista;
       
    }
    
    @Override
    public MarcacionViewModel obtenerMarcacionesPorEmpleadoyFechaActual(EmpleadoViewModel empleado) {
        WhereParams params = new WhereParams();
        String sql = generarObtenerMarcacionesPorEmpleadoyFechaActual(empleado, params);


        List<MarcacionViewModel> _found = jdbcTemplate.query(sql,params.getParams(),  new BeanPropertyRowMapper<>(MarcacionViewModel.class));

        if(_found == null || _found.isEmpty())
            return null;
        else return _found.get(0);

    }
    
    @Override
    public MarcacionViewModel obtenerMarcacionesPorEmpleadoyFecha(EmpleadoViewModel empleado, Date fecha) {
        WhereParams params = new WhereParams();
        String sql = generarObtenerMarcacionesPorEmpleadoyFecha(empleado, fecha,params);



        List<MarcacionViewModel> _found = jdbcTemplate.query(sql,params.getParams(),  new BeanPropertyRowMapper<>(MarcacionViewModel.class));

        if(_found == null || _found.isEmpty())
            return null;
        else return _found.get(0);

    }
    
    private String generarObtenerMarcacionesPorEmpleadoyFechaActual(EmpleadoViewModel empleado, WhereParams params) {

    	StringBuilder sql = new StringBuilder();
    	
    	String formateFechaActual = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
    	
    	sql.append(" SELECT ");   
        sql.append(" MAR.IdMarcacion AS idMarcacion, ");  
        sql.append(" MAR.Fecha AS fecha, ");  
        sql.append(" MAR.HoraIngreso AS horaIngreso, ");  
        sql.append(" MAR.HoraInicioAlmuerzo AS horaInicioAlmuerzo, ");  
        sql.append(" MAR.HoraFinAlmuerzo AS horaFinAlmuerzo, ");  
        sql.append(" MAR.HoraSalida AS horaSalida, ");  
                
        
        sql.append(" MAR.HoraIngresoHorario AS horaIngresoHorario, ");  
        sql.append(" MAR.HoraSalidaHorario AS horaSalidaHorario, ");  
        
        sql.append(" EMP.Nombre+' '+EMP.ApellidoPaterno+' '+EMP.ApellidoMaterno AS nombreCompletoEmpleado, ");
        
        sql.append(" EMP.Nombre AS nombreEmpleado, ");  
        sql.append(" EMP.ApellidoPaterno AS apelPaternoEmpleado, ");  
        sql.append(" EMP.ApellidoMaterno AS apelMaternoEmpleado, ");  
        sql.append(" EMP.Codigo AS codigoEmpleado ");  

        sql.append(" FROM Marcacion MAR ");  
        sql.append(" LEFT JOIN Empleado EMP ON EMP.IdEmpleado = MAR.IdEmpleado ");  
        
        sql.append(" WHERE 1=1 ");
        
        sql.append(params.filter(" AND EMP.IdEmpleado = :idEmpleado ", empleado.getIdEmpleado()));
        
        sql.append(params.filterDate_US(" AND MAR.Fecha  " , DateUtil.parse(new SimpleDateFormat("MM/dd/yyyy"), formateFechaActual)));
     
		return sql.toString();
    }
    
    private String generarObtenerMarcacionesPorEmpleadoyFecha(EmpleadoViewModel empleado, Date fecha, WhereParams params) {

    	StringBuilder sql = new StringBuilder();
    	String formateFecha= new SimpleDateFormat("MM/dd/yyyy").format(fecha);
    	sql.append(" SELECT ");   
        sql.append(" MAR.IdMarcacion AS idMarcacion, ");  
        sql.append(" MAR.Fecha AS fecha, ");  
        sql.append(" MAR.HoraIngreso AS horaIngreso, ");  
        sql.append(" MAR.HoraInicioAlmuerzo AS horaInicioAlmuerzo, ");  
        sql.append(" MAR.HoraFinAlmuerzo AS horaFinAlmuerzo, ");  
        sql.append(" MAR.HoraSalida AS horaSalida, ");  
        sql.append(" MAR.HoraIngresoHorario AS horaIngresoHorario, ");  
        sql.append(" MAR.HoraSalidaHorario AS horaSalidaHorario, ");  
        sql.append(" MAR.HorasTrabajoReal AS horasTrabajoReal, ");
        sql.append(" MAR.HorasTrabajoHorario AS horasTrabajoHorario, ");
        sql.append(" CASE WHEN(MAR.Estado='IN') THEN 'SI' ELSE 'NO' END AS inasistencia, ");
        
        sql.append(" EMP.Nombre+' '+EMP.ApellidoPaterno+' '+EMP.ApellidoMaterno AS nombreCompletoEmpleado, ");
        sql.append(" EMP.Nombre AS nombreEmpleado, ");  
        sql.append(" EMP.ApellidoPaterno AS apelPaternoEmpleado, ");  
        sql.append(" EMP.ApellidoMaterno AS apelMaternoEmpleado, ");  
        sql.append(" EMP.Codigo AS codigoEmpleado ");  
        sql.append(" FROM Marcacion MAR ");  
        sql.append(" LEFT JOIN Empleado EMP ON EMP.IdEmpleado = MAR.IdEmpleado ");  
        sql.append(" WHERE 1=1 ");
        sql.append(params.filter(" AND EMP.IdEmpleado = :idEmpleado ", empleado.getIdEmpleado()));
        sql.append(params.filterDate_US(" AND MAR.Fecha  " , DateUtil.parse(new SimpleDateFormat("MM/dd/yyyy"), formateFecha)));
     	return sql.toString();
    }
    
    @Override
    public List<GenerarReporteMarcacionViewModel> obtenerMarcacionesSegunParametros(ReporteMarcacionViewModel reporteMarcacionDto) {
        WhereParams params = new WhereParams();
        String sql = generarMarcacionesSegunParametros(reporteMarcacionDto, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<>(GenerarReporteMarcacionViewModel.class));
    }
    
    @Override
    public List<GenerarReporteMarcacionAcumuladaViewModel> obtenerMarcacionesAcumuladasSegunParametros(ReporteMarcacionViewModel reporteMarcacionDto) {
        WhereParams params = new WhereParams();
        String sql = generarMarcacionesAcumuladasSegunParametros(reporteMarcacionDto, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<>(GenerarReporteMarcacionAcumuladaViewModel.class));
    }

    
    private String generarMarcacionesAcumuladasSegunParametros(ReporteMarcacionViewModel reporteMarcacionDto, WhereParams params) {
    	
    	Date fechaReporte = new Date();
    	
    	int diasAnterior = -1;
    	
    	Calendar c1 = DateUtil.buildCal(fechaReporte);
			
 		int diaSemana = c1.get(Calendar.DAY_OF_WEEK);
 		
 		if(diaSemana == 2){
 			diasAnterior = -3;
 		}
    	
 		String mesActual = new SimpleDateFormat("MM/yyyy").format(DateUtil.addDays(fechaReporte, diasAnterior));
    	
    	StringBuilder sql = new StringBuilder();
    	sql.append(" SELECT distinct ");   
        sql.append(" EC.IdEmpleadoCompensacion AS idEmpleadoCompensacion, ");  
        sql.append(" EC.IdEmpleado AS idEmpleado, ");
        sql.append(" EC.HorasPendientesTotal AS horasPendientesTotal, ");
        sql.append(" EC.HorasPendientesMesActual AS horasPendientesMesActual, ");
        sql.append(" EC.HorasPendientesHastaMesAnterior AS horasPendientesHastaMesAnterior, ");
        
        sql.append(" EC.HorasTardanzaIngreso AS horasTardanzaIngreso, ");
        sql.append(" EC.HorasTardanzaSalida AS horasTardanzaSalida, ");
        sql.append(" EC.HorasDemoraAlmuerzo AS horasDemoraAlmuerzo, ");
        sql.append(" EC.HorasTrabajadas AS horasTrabajadas, ");
        
        sql.append(" CASE WHEN(EC.Tardanzas>=1) THEN 'SI' ELSE 'NO' END AS tardanzas, ");
        sql.append(" EC.HorarioHorasTrabajo AS horarioHorasTrabajo, ");
        sql.append(" CASE WHEN(EC.Vacaciones>=1) THEN 'SI' ELSE 'NO' END AS vacaciones, ");
        sql.append(" CASE WHEN(EC.Licencias>=1) THEN 'SI' ELSE 'NO' END AS licencias, ");
        sql.append(" CASE WHEN(EC.Inasistencias>=1) THEN 'SI' ELSE 'NO' END AS inasistencias, ");
        
        sql.append(" PROY.Nombre AS proyecto, ");
        sql.append(" DEP.Nombre AS departamentoArea, ");
        sql.append(" UN.Nombre AS unidadNegocio, ");
        
        sql.append(" EMP.Nombre+' '+EMP.ApellidoPaterno+' '+EMP.ApellidoMaterno AS nombreCompletoEmpleado, ");
        sql.append(" EMP.Nombre AS nombreEmpleado, ");  
        sql.append(" EMP.ApellidoPaterno AS apelPaternoEmpleado, ");  
        sql.append(" EMP.ApellidoMaterno AS apelMaternoEmpleado, ");  
        sql.append(" EMP.Codigo AS codigoEmpleado ");  
        
        sql.append(" FROM EmpleadoCompensacion EC ");  
        sql.append(" LEFT JOIN Empleado EMP ON EMP.IdEmpleado = EC.IdEmpleado ");  
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMP.IdEmpleado  "
        		+ "AND ((HISTORIAL.FechaInicio<='"+getdate+"' AND HISTORIAL.FechaFin>='"+getdate+"') OR (HISTORIAL.FechaInicio<='"+getdate+"' AND HISTORIAL.FechaFin IS NULL)) ");
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append(" WHERE 1=1 ");
        sql.append(params.filter(" AND EC.Mes = :mesActual ", mesActual));
        sql.append(params.filter(" AND EMP.IdEmpresa = :idEmpresa ", reporteMarcacionDto.getIdEmpresa()));
        sql.append(params.filter(" AND HISTORIAL.IdUnidadDeNegocio = :idUnidadNegocio ", reporteMarcacionDto.getIdUnidadDeNegocio()));
        sql.append(params.filter(" AND HISTORIAL.IdDepartamentoArea = :idDepartamentoArea ", reporteMarcacionDto.getIdDepartamentoArea()));
        sql.append(params.filter(" AND HISTORIAL.IdProyecto = :idProyecto ", reporteMarcacionDto.getIdProyecto()));
        sql.append(params.filter(" AND ( "+
        		" (HISTORIAL.IdProyecto IS NOT NULL AND PROY.IdJefe = :idJefeProyecto)  OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NOT NULL AND DEP.IdJefe  = :idJefeProyecto)"+ 
        		" OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NULL AND HISTORIAL.IdUnidadDeNegocio IS NOT NULL AND UN.IdJefe = :idJefeProyecto) ) ", reporteMarcacionDto.getIdJefeProyecto()));
        
    	return sql.toString();
    }
    
    private String generarMarcacionesSegunParametros(ReporteMarcacionViewModel reporteMarcacionDto, WhereParams params) {
    	
    	Date fechaReporte = new Date();
    	
    	int diasAnterior = -1;
    	
    	Calendar c1 = DateUtil.buildCal(fechaReporte);
			
 		int diaSemana = c1.get(Calendar.DAY_OF_WEEK);
 		
 		if(diaSemana == 2){
 			diasAnterior = -3;
 		}
    	
 		String formateFechaActual = new SimpleDateFormat("MM/dd/yyyy").format(DateUtil.addDays(fechaReporte, diasAnterior));
    	
    	StringBuilder sql = new StringBuilder();
    	sql.append(" SELECT ");   
        sql.append(" MAR.IdMarcacion AS idMarcacion, ");  
        sql.append(" MAR.IdEmpleado AS idEmpleado, ");
        sql.append(" MAR.Fecha AS fecha, ");  
        sql.append(" MAR.HoraIngreso AS horaIngreso, ");  
        sql.append(" MAR.HoraInicioAlmuerzo AS horaInicioAlmuerzo, ");  
        sql.append(" MAR.HoraFinAlmuerzo AS horaFinAlmuerzo, ");  
        sql.append(" MAR.HoraSalida AS horaSalida, ");  
        sql.append(" MAR.HoraIngresoHorario AS horaIngresoHorario, ");  
        sql.append(" MAR.HoraSalidaHorario AS horaSalidaHorario, ");  
        sql.append(" CASE WHEN(MAR.Estado='TA') THEN 'SI' ELSE 'NO' END AS tardanza, ");
        sql.append(" MAR.DemoraEntrada AS demoraEntrada, ");
        sql.append(" MAR.DemoraSalida AS demoraSalida, ");
        sql.append(" MAR.DemoraAlmuerzo AS demoraAlmuerzo, ");
        sql.append(" MAR.HorasTrabajoReal AS horasTrabajoReal, ");
        sql.append(" MAR.HorasTrabajoHorario AS horasTrabajoHorario, ");
        sql.append(" CASE WHEN(MAR.Estado='IN') THEN 'SI' ELSE 'NO' END AS inasistencia, ");
        sql.append(" CASE WHEN(MAR.Estado='VA') THEN 'SI' ELSE 'NO' END AS vacaciones, ");
        sql.append(" CASE WHEN(MAR.Estado='LI') THEN 'SI' ELSE 'NO' END AS licencia, ");
        
        sql.append(" PROY.Nombre AS proyecto, ");
        
        sql.append(" DEP.Nombre AS departamentoArea, ");
        
        sql.append(" UN.Nombre AS unidadNegocio, ");
        
        
        sql.append(" EMP.Nombre+' '+EMP.ApellidoPaterno+' '+EMP.ApellidoMaterno AS nombreCompletoEmpleado, ");
        sql.append(" EMP.Nombre AS nombreEmpleado, ");  
        sql.append(" EMP.ApellidoPaterno AS apelPaternoEmpleado, ");  
        sql.append(" EMP.ApellidoMaterno AS apelMaternoEmpleado, ");  
        sql.append(" EMP.Codigo AS codigoEmpleado ");  
        
        sql.append(" FROM Marcacion MAR ");  
        sql.append(" LEFT JOIN Empleado EMP ON EMP.IdEmpleado = MAR.IdEmpleado ");  
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMP.IdEmpleado  AND ((HISTORIAL.FechaInicio<='"+getdate+"' AND HISTORIAL.FechaFin>='"+getdate+"') OR (HISTORIAL.FechaInicio<='"+getdate+"' AND HISTORIAL.FechaFin IS NULL)) ");
        
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        
        sql.append(" WHERE 1=1 ");
        
        sql.append(params.filter(" AND EMP.IdEmpresa = :idEmpresa ", reporteMarcacionDto.getIdEmpresa()));
        sql.append(params.filter(" AND HISTORIAL.IdUnidadDeNegocio = :idUnidadNegocio ", reporteMarcacionDto.getIdUnidadDeNegocio()));
        sql.append(params.filter(" AND HISTORIAL.IdDepartamentoArea = :idDepartamentoArea ", reporteMarcacionDto.getIdDepartamentoArea()));
        sql.append(params.filter(" AND HISTORIAL.IdProyecto = :idProyecto ", reporteMarcacionDto.getIdProyecto()));
        sql.append(params.filter(" AND ( "+
        		" (HISTORIAL.IdProyecto IS NOT NULL AND PROY.IdJefe = :idJefeProyecto)  OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NOT NULL AND DEP.IdJefe  = :idJefeProyecto)"+ 
        		" OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NULL AND HISTORIAL.IdUnidadDeNegocio IS NOT NULL AND UN.IdJefe = :idJefeProyecto) ) ", reporteMarcacionDto.getIdJefeProyecto()));
        
        sql.append(params.filterDate_US(" AND MAR.Fecha  " , DateUtil.parse(new SimpleDateFormat("MM/dd/yyyy"), formateFechaActual)));

        
    	return sql.toString();
    }

    @Override
    public  List<RegistroMarcacionViewModel> obtenerRegistroMarcacionNoProcesado() {

    	WhereParams params = new WhereParams();
        String sql = obtenerRegistroMarcacionNoProcesadoSql(params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<>(RegistroMarcacionViewModel.class));
       
    }
    
    @Override
    public void updateRegistroMarcacionAProcesado(List<RegistroMarcacionViewModel> registros) {
    	
    	String ids = registros.stream().map((e) -> String.valueOf(e.getIdRegistroMarcacionEmpleado())).collect(joining(", "));
    	
    	Map<String, Object> queryParams = new HashMap<>();
    	
        StringBuilder sql = new StringBuilder();
        sql.append(" update  ");
        sql.append("  RegistroMarcacionEmpleado ");
        sql.append("  SET Procesado = 'Y' ");
        sql.append("  WHERE IdRegistroMarcacionEmpleado in ( ");
        sql.append(ids);
        sql.append(")");
    	 int rowsUpdate = jdbcTemplate.update(sql.toString(), queryParams);
       
    }
    
    @Override
    public void updateRegistroMarcacionAError(RegistroMarcacionViewModel registro) {
    	
    	WhereParams params = new WhereParams();
    	
        StringBuilder sql = new StringBuilder();
        sql.append(" update  ");
        sql.append("  RegistroMarcacionEmpleado ");
        sql.append("  SET Procesado = 'E' ");
        sql.append("  WHERE IdRegistroMarcacionEmpleado = ");
        sql.append(registro.getIdRegistroMarcacionEmpleado());
        sql.append(params.filter(" AND Log = :log ", registro.getLog()));
        
    	 int rowsUpdate = jdbcTemplate.update(sql.toString(), params.getParams());
       
    }
    
    @Override
    public void updateRegistroMarcacionANoProcesado(List<RegistroMarcacionViewModel> registros) {
    	
    	String ids = registros.stream().map((e) -> String.valueOf(e.getIdRegistroMarcacionEmpleado())).collect(joining(", "));
    	
    	Map<String, Object> queryParams = new HashMap<>();
    	
        StringBuilder sql = new StringBuilder();
        sql.append(" update  ");
        sql.append("  RegistroMarcacionEmpleado ");
        sql.append("  SET Procesado = 'N' ");
        sql.append("  WHERE IdRegistroMarcacionEmpleado in ( ");
        sql.append(ids);
        sql.append(")");
    	 int rowsUpdate = jdbcTemplate.update(sql.toString(), queryParams);
       
    }
    
    @Override
    public void updateMarcacionRecalcular(Long idEmpleado, Date fechaInicio, String horaIngresoHorario, String horaSalidaHorario, int dayNumber) {
    	
    	WhereParams params = new WhereParams();
    	
        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE  ");
        sql.append("  Marcacion ");
        sql.append(" SET HoraIngresoHorario = "+horaIngresoHorario);
        sql.append("  ,HoraSalidaHorario = "+horaSalidaHorario);
        sql.append("  ,Recalcular = 'Y' ");
        sql.append(" WHERE IdEmpleado = "+idEmpleado);
        sql.append(" AND DATEPART(dw, Fecha) = "+dayNumber);
        sql.append(params.filterDateDesde_US(" AND Fecha ", fechaInicio));
        
    	 int rowsUpdate = jdbcTemplate.update(sql.toString(), params.getParams());
       
    }
    
    private String obtenerRegistroMarcacionNoProcesadoSql(WhereParams params) {

    	StringBuilder sql = new StringBuilder();
    	
    	sql.append(" SELECT ");   
        sql.append(" MAR.IdRegistroMarcacionEmpleado AS idRegistroMarcacionEmpleado, ");  
        sql.append(" MAR.IdEmpleado AS idEmpleado, ");  
        sql.append(" MAR.CodigoEmpleado AS codigoEmpleado, ");  
        sql.append(" MAR.DNI AS dni, ");  
        sql.append(" MAR.Fecha AS fecha, ");  
        sql.append(" MAR.Hora AS hora, ");  
                
        
        sql.append(" MAR.Tipo AS tipo, ");  
        sql.append(" MAR.Procesado AS procesado ");  
        
        sql.append(" FROM RegistroMarcacionEmpleado MAR ");  
        sql.append(" WHERE  MAR.Procesado = 'N' and MAR.Tipo = 'O' ");
        sql.append(" ORDER BY MAR.IdEmpleado, MAR.Fecha, MAR.Hora");
        
		return sql.toString();
    }
    
    @Override
    public MarcacionViewModel findById(Long idMarcacion) {

       WhereParams params = new WhereParams();
       String sql = findByIdSql(params, idMarcacion);

       return jdbcTemplate.queryForObject(sql,
               params.getParams(), new BeanPropertyRowMapper<>(MarcacionViewModel.class));
       
    }
    
    private String findByIdSql(WhereParams params, Long idMarcacion) {

    	StringBuilder sql = new StringBuilder();
    	
    	sql.append(" SELECT ");   
    	sql.append(" MAR.IdMarcacion AS idMarcacion, ");  
        sql.append(" MAR.Fecha AS fecha, ");  
        sql.append(" MAR.HoraIngreso AS horaIngreso, ");  
        sql.append(" MAR.HoraInicioAlmuerzo AS horaInicioAlmuerzo, ");  
        sql.append(" MAR.HoraFinAlmuerzo AS horaFinAlmuerzo, ");  
        sql.append(" MAR.HoraSalida AS horaSalida, ");   
        sql.append(" (EMP.ApellidoPaterno+' '+EMP.ApellidoMaterno+', '+EMP.Nombre) AS nombreCompletoEmpleado, ");
        
        sql.append(" MAR.Creador AS creador, ");
        sql.append(" MAR.Actualizador AS actualizador, ");
        sql.append(" MAR.FechaCreacion AS fechaCreacion, ");
        sql.append(" MAR.FechaActualizacion AS fechaActualizacion,  ");
        sql.append(" MAR.RowVersion AS rowVersion  ");
        
        sql.append(" FROM Marcacion MAR ");  
        sql.append(" LEFT JOIN Empleado EMP ON EMP.IdEmpleado = MAR.IdEmpleado "); 
        
        sql.append(" WHERE 1=1 ");
        
        sql.append(params.filter(" AND MAR.IdMarcacion = :idMarcacion ", idMarcacion));
        
		return sql.toString();
    }
    
    @Override
    public List<MarcacionViewModel> getMarcacionesByFiltro(MarcacionFilterViewModel filter){
    	WhereParams params = new WhereParams();
        String sql = getMarcacionesByFiltroQuery(filter, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<MarcacionViewModel>(MarcacionViewModel.class));
    }

    @Override
    public int countMarcacionesPorCodigo(String codigo, Date fecha) {
        WhereParams params = new WhereParams();

        StringBuilder sql = new StringBuilder();

        sql.append("select count(*) from RegistroMarcacionEmpleado " );
        sql.append("where " );
        sql.append(params.filter("CAST(Fecha AS DATE) = CAST(:fecha AS DATE) ",fecha));
        sql.append(" and ");
        sql.append(params.filter("sensor = :sensor ", codigo));
        return jdbcTemplate.queryForObject(sql.toString(), params.getParams(), Integer.class);
    }

    @Override
    public int countAlertasEmpleadoPorMarcador(String codigo) {
        WhereParams params = new WhereParams();
        StringBuilder sql = new StringBuilder();
        sql.append("select count(*) from AlertaEmpleado ");
        sql.append("where CONVERT(CHAR(5), FechaCreacion,114) between '00:00' and '23:59' " );
        sql.append("and FechaCreacion >= DATEADD(day, -1, '"+getdate+"')  ");
        sql.append(" and ");
        sql.append(params.filter("sensor = :sensor ", codigo));
        return jdbcTemplate.queryForObject(sql.toString(), params.getParams(), Integer.class);
    }


    private String getMarcacionesByFiltroQuery(MarcacionFilterViewModel filter, WhereParams params) {

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
               
        sql.append(" ESTMAR.Nombre AS estado, ");
        
        sql.append(" CASE ");
        sql.append(" WHEN (SELECT count(*) FROM SolicitudCambioMarcacion SOL WHERE SOL.Estado='P' AND SOL.IdMarcacion = MAC.IdMarcacion)=0 THEN 'No' ");
        sql.append(" ELSE 'Si' ");
        sql.append(" END AS solicitudCambio ");
        
        sql.append(" FROM Marcacion MAC  ");
        sql.append(" LEFT JOIN Empleado EMP ON EMP.IdEmpleado = MAC.IdEmpleado  ");
        sql.append(" LEFT JOIN TablaGeneral ESTMAR ON MAC.Estado= ESTMAR.Codigo AND ESTMAR.Grupo = 'Marcaciones.Estado' ");
        sql.append(" WHERE 1=1 ");
        sql.append(" AND  MAC.IdEmpleado = "+filter.getIdEmpleado());
        sql.append(params.filterDateDesde_US(" AND MAC.Fecha " , filter.getDesde()));
        sql.append(params.filterDateHasta_US(" AND MAC.Fecha ", filter.getHasta()));

        return sql.toString();
    }
    
    @Override
    public List<MarcacionViewModel> getMarcacionesByIdProyecto(Date fecha, Long idProyecto){
    	WhereParams params = new WhereParams();
        String sql = getMarcacionesByIdProyectoQuery(fecha, idProyecto, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<MarcacionViewModel>(MarcacionViewModel.class));
    }

    private String getMarcacionesByIdProyectoQuery(Date fecha, Long idProyecto, WhereParams params) {

		StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append(" MAC.IdMarcacion AS idMarcacion, ");
        sql.append(" MAC.IdEmpleado AS idEmpleado, ");
        sql.append(" EMP.Nombre+' '+EMP.ApellidoPaterno+' '+EMP.ApellidoMaterno AS nombreCompletoEmpleado ");
        
        sql.append(" FROM Marcacion MAC  ");
        sql.append(" INNER JOIN Empleado EMP ON EMP.IdEmpleado = MAC.IdEmpleado  ");
        sql.append(" LEFT JOIN HistorialLaboral HISTLAB ON HISTLAB.IdEmpleado = EMP.IdEmpleado ");

        sql.append(" WHERE MAC.Fecha>= HISTLAB.fechaInicio AND (HISTLAB.fechaFin IS NULL OR MAC.Fecha<= HISTLAB.fechaFin) ");
        sql.append(" AND MAC.Inasistencia = 1 ");
        sql.append(params.filter(" AND HISTLAB.IdProyecto =:idProyecto ",idProyecto));
        sql.append(params.filter(" AND MAC.Fecha =:fecha ",fecha));

        return sql.toString();
    }
    
    @Override
    public List<MarcacionViewModel> getMarcacionesByIdDepartamentoArea(Date fecha, Long idDepArea){
    	WhereParams params = new WhereParams();
        String sql = getMarcacionesByIdDepartamentoAreaQuery(fecha, idDepArea, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<MarcacionViewModel>(MarcacionViewModel.class));
    }

    private String getMarcacionesByIdDepartamentoAreaQuery(Date fecha, Long idDepArea, WhereParams params) {

		StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append(" MAC.IdMarcacion AS idMarcacion, ");
        sql.append(" MAC.IdEmpleado AS idEmpleado, ");
        sql.append(" EMP.Nombre+' '+EMP.ApellidoPaterno+' '+EMP.ApellidoMaterno AS nombreCompletoEmpleado ");
        
        sql.append(" FROM Marcacion MAC  ");
        sql.append(" INNER JOIN Empleado EMP ON EMP.IdEmpleado = MAC.IdEmpleado  ");
        sql.append(" LEFT JOIN HistorialLaboral HISTLAB ON HISTLAB.IdEmpleado = EMP.IdEmpleado ");

        sql.append(" WHERE MAC.Fecha>= HISTLAB.fechaInicio AND (HISTLAB.fechaFin IS NULL OR MAC.Fecha<= HISTLAB.fechaFin) ");
        sql.append(" AND MAC.Inasistencia = 1 ");
        sql.append(params.filter(" AND HISTLAB.IdDepartamentoArea =:idDepArea ",idDepArea));
        sql.append(params.filter(" AND MAC.Fecha =:fecha ",fecha));

        return sql.toString();
    }
    
    @Override
    public List<MarcacionViewModel> getMarcacionesByIdUnidadDeNegocio(Date fecha, Long idUnidad){
    	WhereParams params = new WhereParams();
        String sql = getMarcacionesByIdUnidadDeNegocioQuery(fecha, idUnidad, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<MarcacionViewModel>(MarcacionViewModel.class));
    }

    private String getMarcacionesByIdUnidadDeNegocioQuery(Date fecha, Long idUnidad, WhereParams params) {

		StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append(" MAC.IdMarcacion AS idMarcacion, ");
        sql.append(" MAC.IdEmpleado AS idEmpleado, ");
        sql.append(" EMP.Nombre+' '+EMP.ApellidoPaterno+' '+EMP.ApellidoMaterno AS nombreCompletoEmpleado ");
        
        sql.append(" FROM Marcacion MAC  ");
        sql.append(" INNER JOIN Empleado EMP ON EMP.IdEmpleado = MAC.IdEmpleado  ");
        sql.append(" LEFT JOIN HistorialLaboral HISTLAB ON HISTLAB.IdEmpleado = EMP.IdEmpleado ");

        sql.append(" WHERE MAC.Fecha>= HISTLAB.fechaInicio AND (HISTLAB.fechaFin IS NULL OR MAC.Fecha<= HISTLAB.fechaFin) ");
        sql.append(" AND MAC.Inasistencia = 1 ");
        sql.append(params.filter(" AND HISTLAB.IdUnidadDeNegocio =:idUnidad ",idUnidad));
        sql.append(params.filter(" AND MAC.Fecha =:fecha ",fecha));

        return sql.toString();
    }

	@Override
	public List<NotificacionTardanzaViewModel> getEmpleadosPorTardanzaPorUnidadDeNegocio(Date today, Long idEmpleado,Long idUnidadDeNegocio) {
		WhereParams params = new WhereParams();
        String sql = getEmpleadosPorTardanzaPorUnidadDeNegocioQuery(today,idEmpleado,idUnidadDeNegocio,params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<NotificacionTardanzaViewModel>(NotificacionTardanzaViewModel.class));
	}
	
	private String getEmpleadosPorTardanzaPorUnidadDeNegocioQuery(Date today, Long idEmpleado, Long idUnidadDeNegocio, WhereParams params) {
		StringBuilder sql = new StringBuilder();

        sql.append(" SELECT ");
        sql.append(" MAR.IdEmpleado AS idEmpleado, ");
        sql.append(" MAR.HoraIngreso AS marcacion, ");
        sql.append(" MAR.HoraIngresoHorario AS horario, ");
        sql.append(" MAR.DemoraEntrada AS tardanza, ");
        sql.append(" EMP.ApellidoPaterno+' '+EMP.ApellidoMaterno+', '+EMP.Nombre AS nombreCompleto, ");
        
        sql.append("  CASE ");   
		sql.append("  WHEN (HISTORIAL.IdProyecto IS NOT NULL) THEN eun.ApellidoPaterno +' '+eun.ApellidoMaterno +', '+eun.Nombre "); 
		sql.append("  WHEN (HISTORIAL.IdProyecto IS NULL AND HISTORIAL.IdDepartamentoArea IS NOT NULL) THEN eun.ApellidoPaterno +' '+eun.ApellidoMaterno +', '+eun.Nombre "); 
		sql.append("  WHEN (HISTORIAL.IdProyecto IS NULL AND HISTORIAL.IdDepartamentoArea IS NULL AND HISTORIAL.IdUnidadDeNegocio IS NOT NULL) THEN eun.ApellidoPaterno +' '+eun.ApellidoMaterno +', '+eun.Nombre "); 
		sql.append("  ELSE 'No Asociado'  ");
		sql.append("  END AS nombreJefe ");
        
        sql.append(" FROM Marcacion MAR ");
        sql.append(" LEFT JOIN Empleado EMP ON EMP.IdEmpleado = MAR.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMP.IdEmpleado ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        sql.append(" LEFT JOIN Empleado eun ON UN.IdJefe = eun.IdEmpleado ");
        sql.append(" WHERE 1=1 ");

        sql.append(params.filter(" AND UN.IdUnidadDeNegocio = :unidadNegocio ", idUnidadDeNegocio));	

        sql.append(params.filter(" AND MAR.Fecha = :fecha", today));
        sql.append(" AND MAR.DemoraEntrada > 0 ");
        sql.append(" AND MAR.EsPersonaDeConfianza = 0 ");
        sql.append(" Order by MAR.Fecha desc ");

		return sql.toString();
	}

	@Override
	public List<MarcacionViewModel> getEmpleadosPorTardanzaPorProyecto(Date today,Long idProyecto) {
		WhereParams params = new WhereParams();
        String sql = getEmpleadosPorTardanzaPorProyectoQuery(today,idProyecto,params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<MarcacionViewModel>(MarcacionViewModel.class));
	}
	
	private String getEmpleadosPorTardanzaPorProyectoQuery(Date today, Long idProyecto,WhereParams params) {
		StringBuilder sql = new StringBuilder();

        sql.append(" SELECT DISTINCT");
        sql.append(" MAC.IdMarcacion AS idMarcacion, ");
        sql.append(" MAC.IdEmpleado AS idEmpleado, ");
        
        sql.append(" MAC.HoraIngresoHorario AS horaIngresoHorario, ");
        sql.append(" MAC.HoraIngreso AS horaIngreso, ");
        sql.append(" MAC.DemoraEntrada AS demoraEntrada, ");
        
        sql.append(" EMP.Nombre+' '+EMP.ApellidoPaterno+' '+EMP.ApellidoMaterno AS nombreCompletoEmpleado ");
        
        sql.append(" FROM Marcacion MAC  ");
        sql.append(" INNER JOIN Empleado EMP ON EMP.IdEmpleado = MAC.IdEmpleado  ");
        sql.append(" LEFT JOIN HistorialLaboral HISTLAB ON HISTLAB.IdEmpleado = EMP.IdEmpleado ");

        sql.append(" WHERE MAC.Fecha>= HISTLAB.fechaInicio AND (HISTLAB.fechaFin IS NULL OR MAC.Fecha<= HISTLAB.fechaFin) ");
        sql.append(params.filter(" AND HISTLAB.IdProyecto =:idProyecto ",idProyecto));
        sql.append(params.filter(" AND MAC.Fecha =:fecha ",today));
        sql.append(" AND MAC.DemoraEntrada > 0 ");
        sql.append(" AND MAC.EsPersonaDeConfianza = 0 ");
        sql.append(" ORDER BY EMP.Nombre ");

		return sql.toString();
	}

	@Override
	public List<MarcacionViewModel> getTardanzasByIdDepartamentoArea(Date today, Long idDepartamentoArea) {
		WhereParams params = new WhereParams();
        String sql = getTardanzasByIdDepartamentoAreaQuery(today, idDepartamentoArea, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<MarcacionViewModel>(MarcacionViewModel.class));
	}
	
	private String getTardanzasByIdDepartamentoAreaQuery(Date fecha, Long idDepartamentoArea, WhereParams params) {

		StringBuilder sql = new StringBuilder();
        sql.append(" SELECT DISTINCT ");
        sql.append(" MAC.IdMarcacion AS idMarcacion, ");
        sql.append(" MAC.IdEmpleado AS idEmpleado, ");
        
        sql.append(" MAC.HoraIngresoHorario AS horaIngresoHorario, ");
        sql.append(" MAC.HoraIngreso AS horaIngreso, ");
        sql.append(" MAC.DemoraEntrada AS demoraEntrada, ");
        
        sql.append(" EMP.Nombre+' '+EMP.ApellidoPaterno+' '+EMP.ApellidoMaterno AS nombreCompletoEmpleado ");
        
        sql.append(" FROM Marcacion MAC  ");
        sql.append(" INNER JOIN Empleado EMP ON EMP.IdEmpleado = MAC.IdEmpleado  ");
        sql.append(" LEFT JOIN HistorialLaboral HISTLAB ON HISTLAB.IdEmpleado = EMP.IdEmpleado ");

        sql.append(" WHERE MAC.Fecha>= HISTLAB.fechaInicio AND (HISTLAB.fechaFin IS NULL OR MAC.Fecha<= HISTLAB.fechaFin) ");
        sql.append(" AND MAC.DemoraEntrada > 0 ");
        sql.append(" AND MAC.EsPersonaDeConfianza = 0 ");
        sql.append(params.filter(" AND HISTLAB.IdDepartamentoArea =:idDepArea ",idDepartamentoArea));
        sql.append(params.filter(" AND MAC.Fecha =:fecha ",fecha));
        sql.append(" ORDER BY EMP.Nombre ");

        return sql.toString();
    }

	@Override
	public List<MarcacionViewModel> getTardanzasByIdUnidadDeNegocio(Date today, Long idUnidadDeNegocio) {
		WhereParams params = new WhereParams();
        String sql = getTardanzasByIdUnidadDeNegocioQuery(today, idUnidadDeNegocio, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<MarcacionViewModel>(MarcacionViewModel.class));
	}

	private String getTardanzasByIdUnidadDeNegocioQuery(Date fecha, Long idUnidadDeNegocio, WhereParams params) {
		StringBuilder sql = new StringBuilder();
        sql.append(" SELECT DISTINCT ");
        sql.append(" MAC.IdMarcacion AS idMarcacion, ");
        sql.append(" MAC.IdEmpleado AS idEmpleado, ");
        
        sql.append(" MAC.HoraIngresoHorario AS horaIngresoHorario, ");
        sql.append(" MAC.HoraIngreso AS horaIngreso, ");
        sql.append(" MAC.DemoraEntrada AS demoraEntrada, ");
        
        sql.append(" EMP.Nombre+' '+EMP.ApellidoPaterno+' '+EMP.ApellidoMaterno AS nombreCompletoEmpleado ");
        
        sql.append(" FROM Marcacion MAC  ");
        sql.append(" INNER JOIN Empleado EMP ON EMP.IdEmpleado = MAC.IdEmpleado  ");
        sql.append(" LEFT JOIN HistorialLaboral HISTLAB ON HISTLAB.IdEmpleado = EMP.IdEmpleado ");

        sql.append(" WHERE MAC.Fecha>= HISTLAB.fechaInicio AND (HISTLAB.fechaFin IS NULL OR MAC.Fecha<= HISTLAB.fechaFin) ");
        sql.append(" AND MAC.DemoraEntrada > 0 ");
        sql.append(" AND MAC.EsPersonaDeConfianza = 0 ");
        sql.append(params.filter(" AND HISTLAB.IdUnidadDeNegocio =:idUnidad ",idUnidadDeNegocio));
        sql.append(params.filter(" AND MAC.Fecha =:fecha ",fecha));
        sql.append(" ORDER BY EMP.Nombre ");

        return sql.toString();
	}

}