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
import pe.com.empresa.rk.view.model.LicenciaEmpleadoQuickFilterViewModel;
import pe.com.empresa.rk.view.model.LicenciaEmpleadoResultViewModel;
import pe.com.empresa.rk.view.model.QuickFilterViewModel;
import pe.com.empresa.rk.view.model.TipoLicenciaViewModel;
import pe.com.empresa.rk.view.model.LicenciaEmpleadoFilterViewModel;
import pe.com.empresa.rk.view.model.LicenciaViewModel;
import pe.com.empresa.rk.view.model.PeriodoEmpleadoViewModel;

@Repository
public class LicenciaEmpleadoJdbcRepository implements LicenciaEmpleadoRepository{

	private static final Logger LOGGER = LoggerFactory.getLogger(LicenciaEmpleadoJdbcRepository.class);
	
	private static final String SEARCH_ALL_TIPO_LICENCIA = "select * from TipoLicencia";

	@Autowired
	DataSource dataSource;
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	

	@PostConstruct
	public void init() {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}


	@Override
	public List<LicenciaEmpleadoResultViewModel> obtenerLicencias(LicenciaEmpleadoFilterViewModel busquedaLicenciaEmpleadoDto) {
		WhereParams params = new WhereParams();
        String sql = generarObtenerLicencias(busquedaLicenciaEmpleadoDto, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<LicenciaEmpleadoResultViewModel>(LicenciaEmpleadoResultViewModel.class));
	}


	private String generarObtenerLicencias(LicenciaEmpleadoFilterViewModel busquedaLicenciaEmpleadoDto,
			WhereParams params) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select li.IdLicencia as idLicencia, li.Comentario as comentario, li.DiaEntero as diaEntero, li.HoraInicio as horaInicio, li.HoraFin as horaFin,tl.IdTipoLicencia as idTipoLicencia, tl.Nombre as nombreTipoLicencia, e.IdEmpleado as idEmpleado, ");
		sql.append(" CONCAT(e.ApellidoPaterno, ' ', e.ApellidoMaterno, ', ', e.Nombre) as nombreEmpleado, ");
		sql.append(" PROY.Nombre as nombreProyecto,  ");
		sql.append(" DEP.Nombre as nombreDepartamento, ");
        sql.append(" UN.Nombre as nombreUnidadNegocio, ");

        sql.append(" CASE");
        sql.append(" WHEN DiaEntero = 1 THEN 'Si'");
        sql.append(" WHEN DiaEntero = 0 THEN 'No'");
        sql.append(" ELSE 'No'");
        sql.append(" END AS nombreDiaEntero, ");
        sql.append(" EMP.ApellidoPaterno +' '+EMP.ApellidoMaterno +', '+EMP.Nombre AS jefeInmediato, ");

        sql.append(" li.FechaInicio as fechaInicio, li.FechaFin as fechaFin, li.Dias as dias , CASE WHEN (li.Estado='P') THEN 'Pendiente'  WHEN (li.Estado='E') THEN 'Enviado' WHEN (li.Estado='A') THEN 'Aprobado' WHEN (li.Estado='V') THEN 'Validado' "
        		+ " ELSE 'Denegado' END as estado");
        
        sql.append(" from Licencia li");
        sql.append(" LEFT JOIN TipoLicencia tl ON li.idTipoLicencia=tl.idTipoLicencia ");
        sql.append(" LEFT JOIN Empleado e ON e.idEmpleado=li.idEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = e.IdEmpleado  AND ((HISTORIAL.FechaInicio<=getdate() AND HISTORIAL.FechaFin>=getdate()) AND (HISTORIAL.FechaInicio<=getdate() AND HISTORIAL.FechaFin IS NULL)) ");
        sql.append(" LEFT JOIN PeriodoEmpleado periodo ON periodo.IdEmpleado = e.IdEmpleado  AND (periodo.FechaInicio<=getdate() AND periodo.FechaFin>=getdate()) ");
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = PROY.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = DEP.IdUnidadDeNegocio ");
        sql.append(" LEFT JOIN Empleado EMP ON EMP.IdEmpleado = li.IdAtendidoPor ");
        sql.append(" where 1=1 ");
        sql.append(params.filterDateDesde_US(" AND li.FechaInicio = : fechaInicio " , busquedaLicenciaEmpleadoDto.getFechaInicio()));
        sql.append(params.filterDateHasta_US(" AND li.FechaFin = :fechaFin ", busquedaLicenciaEmpleadoDto.getFechaFin()));
        sql.append(params.filter(" AND li.idTipoLicencia = :idTipoLicencia ", busquedaLicenciaEmpleadoDto.getIdTipoLicencia()));
        sql.append(params.filter(" AND li.estado= :estado ", busquedaLicenciaEmpleadoDto.getEstado()));
        
        sql.append(params.filter(" AND UN.IdUnidadDeNegocio = :idUnidadNegocio ", busquedaLicenciaEmpleadoDto.getIdUnidadDeNegocio()));
        sql.append(params.filter(" AND DEP.IdDepartamentoArea = :idDepartamentoArea ", busquedaLicenciaEmpleadoDto.getIdDepartamentoArea()));
        sql.append(params.filter(" AND PROY.IdProyecto = :idProyecto ", busquedaLicenciaEmpleadoDto.getIdProyecto()));
        sql.append(params.filter(" AND e.IdEmpleado = :idEmpleado ", busquedaLicenciaEmpleadoDto.getIdEmpleado()));
        sql.append(params.filter(" AND periodo.IdEmpleado = :idEmpleado ", busquedaLicenciaEmpleadoDto.getIdEmpleado()));
        sql.append(params.filter(" AND li.IdEmpleado = :idEmpleado ", busquedaLicenciaEmpleadoDto.getIdEmpleado()));
//        sql.append(params.filter(" AND HISTORIAL.IdEmpleado = :idEmpleado ", busquedaLicenciaEmpleadoDto.getIdEmpleado()));
        sql.append(params.filter(" AND PROY.IdJefe = :idJefe ", busquedaLicenciaEmpleadoDto.getIdJefeInmediato()));
        
        /*sql.append(params.filter(" AND ( "+
        		" (HISTORIAL.IdProyecto IS NOT NULL AND PROY.IdJefe = :idJefe)  OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NOT NULL AND DEP.IdJefe  = :idJefe)"+ 
        		" OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NULL AND HISTORIAL.IdUnidadDeNegocio IS NOT NULL AND UN.IdJefe = :idJefe) ) ", busquedaLicenciaEmpleadoDto.getIdJefe()));
        		*/
        
       
        
        
		return sql.toString();
	}


	@Override
	public List<TipoLicenciaViewModel> getTipoLicencias() {
		
		List<TipoLicenciaViewModel> result = jdbcTemplate.query(SEARCH_ALL_TIPO_LICENCIA,
                new BeanPropertyRowMapper<>(TipoLicenciaViewModel.class)
        );
        LOGGER.info("Found {} list TipoLicencia", result.size());

        return result;
	}


	@Override
	public List<LicenciaEmpleadoResultViewModel> busquedaRapidaLicencias(QuickFilterViewModel quickFilter) {
		WhereParams params = new WhereParams();
        String sql = generarBusquedaRapidaLicencias((LicenciaEmpleadoQuickFilterViewModel)quickFilter, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<LicenciaEmpleadoResultViewModel>(LicenciaEmpleadoResultViewModel.class));

	}
	
	private String generarBusquedaRapidaLicencias(LicenciaEmpleadoQuickFilterViewModel quickFilter,
			WhereParams params) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select li.IdLicencia as idLicencia, li.Comentario as comentario, li.DiaEntero as diaEntero, li.HoraInicio as horaInicio, li.HoraFin as horaFin,tl.IdTipoLicencia as idTipoLicencia, tl.Nombre as nombreTipoLicencia, e.IdEmpleado as idEmpleado, ");
		sql.append(" CONCAT(e.ApellidoPaterno, ' ', e.ApellidoMaterno, ', ', e.Nombre) as nombreEmpleado, ");
		sql.append(" PROY.Nombre as nombreProyecto,  ");
		sql.append(" DEP.Nombre as nombreDepartamento, ");
        sql.append(" UN.Nombre as nombreUnidadNegocio, ");
        sql.append(" li.FechaInicio as fechaInicio, li.FechaFin as fechaFin, li.Dias as dias , CASE WHEN (li.Estado='P') THEN 'Pendiente' "
        		+ " ELSE 'Aprobado' END as estado");
        
        sql.append(" from Licencia li");
        sql.append(" LEFT JOIN TipoLicencia tl ON li.idTipoLicencia=tl.idTipoLicencia ");
        sql.append(" LEFT JOIN Empleado e ON e.idEmpleado=li.idEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = e.IdEmpleado  AND ((HISTORIAL.FechaInicio<=getdate() AND HISTORIAL.FechaFin>=getdate()) OR (HISTORIAL.FechaInicio<=getdate() AND HISTORIAL.FechaFin IS NULL)) ");
        sql.append(" LEFT JOIN PeriodoEmpleado periodo ON periodo.IdEmpleado = e.IdEmpleado  AND (periodo.FechaInicio<=getdate() AND periodo.FechaFin>=getdate()) ");
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = PROY.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = DEP.IdUnidadDeNegocio ");
        sql.append(" where 1=1 ");
        sql.append(params.filterDateDesde_US(" AND li.FechaInicio " , quickFilter.getFechaInicio()));
        sql.append(params.filterDateHasta_US(" AND li.FechaFin ", quickFilter.getFechaFin()));
        
        sql.append(params.filter(" AND UPPER(e.Nombre) LIKE UPPER('%' + :value + '%') ", quickFilter.getValue()));
        sql.append(params.filter(" OR UPPER(e.ApellidoPaterno) LIKE UPPER('%' + :value + '%') ", quickFilter.getValue()));
        sql.append(params.filter(" OR UPPER(e.ApellidoMaterno) LIKE UPPER('%' + :value + '%') ", quickFilter.getValue()));
        
		return sql.toString();
	}
	
	@Override
	public List<LicenciaViewModel> verLicencias(PeriodoEmpleadoViewModel periodoEmpleado) {
		WhereParams params = new WhereParams();
        String sql = generarVerLicencias(periodoEmpleado, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<LicenciaViewModel>(LicenciaViewModel.class));
	}
	
	 private String generarVerLicencias(PeriodoEmpleadoViewModel periodoEmpleado, WhereParams params) {

	        StringBuilder sql = new StringBuilder();
	        sql.append(" SELECT ");
	        sql.append(" LICENCIA.IdLicencia AS idLicencia, ");
	        sql.append(" LICENCIA.IdEmpleado AS idEmpleado, ");
	        sql.append(" LICENCIA.IdAtendidoPor AS idAtendidoPor, ");
	        
	        sql.append(" LICENCIA.IdTipoLicencia AS idTipoLicencia, ");
	        sql.append(" TIPOLICENCIA.Nombre AS nombreTipoLicencia, ");
	        
	        sql.append(" CONCAT(ATENDIDOPOR.ApellidoPaterno, ' ', ATENDIDOPOR.ApellidoMaterno, ', ', ATENDIDOPOR.Nombre) as jefeInmediato, ");
	        sql.append(" LICENCIA.Comentario AS comentario, ");
	        sql.append(" LICENCIA.FechaInicio AS fechaInicio, ");
	        sql.append(" LICENCIA.FechaFin AS fechaFin, ");
	        sql.append(" LICENCIA.Dias AS dias, ");
	        sql.append(" LICENCIA.Estado AS estado, ");
	        sql.append(" ESTADO.Nombre as nombreEstado,  ");
	        sql.append(" LICENCIA.DiaEntero AS diaEntero, ");
	        
	        sql.append(" PERIODO.Periodo AS periodo, ");
	        
	        sql.append(" CASE ");
	        sql.append(" WHEN LICENCIA.DiaEntero = 1 THEN 'Si' ");
	        sql.append(" WHEN LICENCIA.DiaEntero = 0 THEN 'No' ");
	        sql.append(" END AS nombreDiaEntero, ");

	        sql.append(" LICENCIA.Creador AS creador, ");
	        sql.append(" LICENCIA.Actualizador AS actualizador, ");
	        sql.append(" LICENCIA.FechaCreacion AS fechaCreacion, ");
	        sql.append(" LICENCIA.FechaActualizacion AS fechaActualizacion,  ");
	        sql.append(" LICENCIA.RowVersion AS rowVersion  ");

	        sql.append(" FROM Licencia LICENCIA ");
	        sql.append(" LEFT JOIN Empleado ATENDIDOPOR ON ATENDIDOPOR.IdEmpleado = LICENCIA.IdAtendidoPor ");
	        sql.append(" LEFT JOIN PeriodoEmpleado PERIODO ON PERIODO.IdEmpleado = LICENCIA.IdEmpleado ");
	        sql.append(" LEFT JOIN TipoLicencia TIPOLICENCIA ON LICENCIA.IdTipoLicencia=TIPOLICENCIA.IdTipoLicencia ");
	        sql.append(" LEFT JOIN TablaGeneral ESTADO ON LICENCIA.Estado=ESTADO.Codigo and ESTADO.GRUPO='Licencia.Estado'");

	        sql.append(" where LICENCIA.IdEmpleado = "+periodoEmpleado.getIdEmpleado());

	        if(periodoEmpleado.getIdPeriodoEmpleado() != null){
	        	sql.append(" AND PERIODO.IdPeriodoEmpleado ="+periodoEmpleado.getIdPeriodoEmpleado());
	        	sql.append(" AND ( ");
	        	sql.append(" (  ");
	        	//sql.append(" l.FechaInicio<=PeriodoEmpleado.FechaFin ");
	        	sql.append(" LICENCIA.FechaInicio>=PERIODO.FechaInicio ");
	        	//sql.append(" ) ");
	        	//sql.append(" OR ");
	        	//sql.append(" ( ");
	        	sql.append(" AND LICENCIA.FechaFin<=PERIODO.FechaFin ");
	        	//sql.append(" AND l.FechaFin>=PeriodoEmpleado.FechaInicio");
	        	sql.append(" ) ");
	        	sql.append(" ) ");

	        }


	        return sql.toString();
	    }

}
