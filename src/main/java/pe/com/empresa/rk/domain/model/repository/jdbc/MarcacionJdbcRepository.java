package pe.com.empresa.rk.domain.model.repository.jdbc;

import java.text.SimpleDateFormat;
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
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import pe.com.empresa.rk.support.WhereParams;
import pe.com.empresa.rk.util.DateUtil;
import pe.com.empresa.rk.view.model.GenerarReporteMarcacionViewModel;
import pe.com.empresa.rk.view.model.MarcacionViewModel;
import pe.com.empresa.rk.view.model.EmpleadoViewModel;
import pe.com.empresa.rk.view.model.GenerarReporteMarcacionAcumuladaViewModel;
import pe.com.empresa.rk.view.model.MarcacionFilterViewModel;
import pe.com.empresa.rk.view.model.RegistroMarcacionEmpleadoViewModel;
import pe.com.empresa.rk.view.model.RegistroMarcacionViewModel;
import pe.com.empresa.rk.view.model.ReporteMarcacionViewModel;

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
    

    @PostConstruct
    public void init() {
    	marcacionJdbcTemplate = new JdbcTemplate(marcacionDataSource);
    	marcacionJdbcTemplate.setResultsMapCaseInsensitive(true);
    	
    	jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
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
        
        //sql.append(" PROY.Nombre AS nombreProyecto, ");  
        //sql.append(" DEP.Nombre AS nombreDepartamento, ");  
        //sql.append(" UN.Nombre AS nombreUnidadNegocio ");  

        sql.append(" FROM Marcacion MAR ");  
        sql.append(" LEFT JOIN Empleado EMP ON EMP.IdEmpleado = MAR.IdEmpleado ");  
        //sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMP.IdEmpleado AND ((HISTORIAL.FechaInicio < MAR.Fecha AND HISTORIAL.FechaFin IS NULL) OR (HISTORIAL.FechaInicio < MAR.Fecha AND HISTORIAL.FechaFin IS NOT NULL AND HISTORIAL.FechaFin > MAR.Fecha))  ");  

        //sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");  
        //sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");  
        //sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");  
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
        sql.append(" CASE WHEN(MAR.Inasistencia=1) THEN 'SI' ELSE 'NO' END AS inasistencia, ");
        
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
    	
    	String formateFechaActual = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
    	
    	StringBuilder sql = new StringBuilder();
    	sql.append(" SELECT ");   
        sql.append(" EC.IdEmpleadoCompensacion AS idEmpleadoCompensacion, ");  
        sql.append(" EC.IdEmpleado AS idEmpleado, ");
          
        /*sql.append(" MAR.HoraIngreso AS horaIngreso, ");  
        sql.append(" MAR.HoraInicioAlmuerzo AS horaInicioAlmuerzo, ");  
        sql.append(" MAR.HoraFinAlmuerzo AS horaFinAlmuerzo, ");  
        sql.append(" MAR.HoraSalida AS horaSalida, ");  
        sql.append(" MAR.HoraIngresoHorario AS horaIngresoHorario, ");  
        sql.append(" MAR.HoraSalidaHorario AS horaSalidaHorario, ");  
        sql.append(" MAR.Tardanza AS tardanza, ");*/
        sql.append(" EC.HorasPendientesTotal AS horasPendientesTotal, ");
        sql.append(" EC.HorasPendientesMesActual AS horasPendientesMesActual, ");
        sql.append(" EC.HorasPendientesHastaMesAnterior AS horasPendientesHastaMesAnterior, ");
        
        sql.append(" EC.HorasTardanzaIngreso AS horasTardanzaIngreso, ");
        sql.append(" EC.HorasTardanzaSalida AS horasTardanzaSalida, ");
        sql.append(" EC.HorasDemoraAlmuerzo AS horasDemoraAlmuerzo, ");
        sql.append(" EC.HorasTrabajadas AS horasTrabajadas, ");
        
        sql.append(" CASE WHEN(EC.Tardanzas=1) THEN 'SI' ELSE 'NO' END AS tardanzas, ");
        sql.append(" EC.HorarioHorasTrabajo AS horarioHorasTrabajo, ");
        sql.append(" CASE WHEN(EC.Vacaciones=1) THEN 'SI' ELSE 'NO' END AS vacaciones, ");
        sql.append(" CASE WHEN(EC.Licencias=1) THEN 'SI' ELSE 'NO' END AS licencias, ");
        sql.append(" CASE WHEN(EC.Inasistencias=1) THEN 'SI' ELSE 'NO' END AS inasistencias, ");
        
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
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMP.IdEmpleado  AND ((HISTORIAL.FechaInicio<=now() AND HISTORIAL.FechaFin>=now()) OR (HISTORIAL.FechaInicio<=now() AND HISTORIAL.FechaFin IS NULL)) ");
        
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
        
      

    	return sql.toString();
    }
    
    private String generarMarcacionesSegunParametros(ReporteMarcacionViewModel reporteMarcacionDto, WhereParams params) {
    	
    	String formateFechaActual = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
    	
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
        sql.append(" CASE WHEN(MAR.Tardanza=1) THEN 'SI' ELSE 'NO' END AS tardanza, ");
        sql.append(" MAR.DemoraEntrada AS demoraEntrada, ");
        sql.append(" MAR.DemoraSalida AS demoraSalida, ");
        sql.append(" MAR.DemoraAlmuerzo AS demoraAlmuerzo, ");
        sql.append(" MAR.HorasTrabajoReal AS horasTrabajoReal, ");
        sql.append(" MAR.HorasTrabajoHorario AS horasTrabajoHorario, ");
        sql.append(" CASE WHEN(MAR.Inasistencia=1) THEN 'SI' ELSE 'NO' END AS inasistencia, ");
        sql.append(" CASE WHEN(MAR.Vacaciones=1) THEN 'SI' ELSE 'NO' END AS vacaciones, ");
        sql.append(" CASE WHEN(MAR.Licencia=1) THEN 'SI' ELSE 'NO' END AS licencia, ");
        
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
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMP.IdEmpleado  AND ((HISTORIAL.FechaInicio<=now() AND HISTORIAL.FechaFin>=now()) OR (HISTORIAL.FechaInicio<=now() AND HISTORIAL.FechaFin IS NULL)) ");
        
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
        sql.append(" WHERE  MAR.Procesado = 'N' and MAR.Tipo = 'O'");
        
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
        sql.append(" (EMP.ApellidoPaterno+' '+EMP.ApellidoMaterno+', '+EMP.Nombre) AS nombreCompletoEmpleado ");
        
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
    public int countMarcacionesPorCodigo(String codigo) {
        WhereParams params = new WhereParams();
        String query = "select count(*) from RegistroMarcacionEmpleado \n" +
                "where CONVERT(CHAR(5), FechaCreacion, " + codigo + ") between '00:00' and '09:30' " +
                "and FechaCreacion >= DATEADD(day, -1, now()) and Sensor=103";
        return jdbcTemplate.queryForObject(query, params.getParams(), Integer.class);
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
        
        sql.append(" CASE ");
        sql.append(" WHEN (SELECT count(*) FROM SolicitudCambioMarcacion SOL WHERE SOL.IdMarcacion = MAC.IdMarcacion)=0 THEN 'No' ");
        sql.append(" ELSE 'Si' ");
        sql.append(" END AS solicitudCambio ");
        
        sql.append(" FROM Marcacion MAC  ");
        sql.append(" LEFT JOIN Empleado EMP ON EMP.IdEmpleado = MAC.IdEmpleado  ");
        sql.append(" WHERE 1=1 ");
        sql.append(" AND  MAC.IdEmpleado = "+filter.getIdEmpleado());
        sql.append(params.filterDateDesde_US(" AND MAC.Fecha " , filter.getDesde()));
        sql.append(params.filterDateHasta_US(" AND MAC.Fecha ", filter.getHasta()));

        return sql.toString();
    }

    

}