package pe.com.empresa.rk.domain.model.repository.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.empresa.rk.support.WhereParams;
import pe.com.empresa.rk.view.model.HorasExtraEmpleadoResultViewModel;
import pe.com.empresa.rk.view.model.HorasExtraViewModel;
import pe.com.empresa.rk.view.model.ModuloFilterViewModel;
import pe.com.empresa.rk.view.model.QuickFilterViewModel;
import pe.com.empresa.rk.view.model.HorasExtraEmpleadoFilterViewModel;
import pe.com.empresa.rk.view.model.HorasExtraEmpleadoQuickFilterViewModel;
import pe.com.empresa.rk.view.model.ModuloResultViewModel;
import pe.com.empresa.rk.view.model.PeriodoEmpleadoViewModel;

import java.util.List;

/**
 * Created by josediaz on 25/11/2016.
 */
@Repository
public class HoraExtraJdbcRepository implements HoraExtraRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(HoraExtraJdbcRepository.class);

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    HoraExtraJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
	public List<ModuloResultViewModel> buscarModulos(ModuloFilterViewModel filterViewModel) {
		WhereParams params = new WhereParams();
        String sql = generarBuscarModulos(filterViewModel, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<ModuloResultViewModel>(ModuloResultViewModel.class));

	}

	private String generarBuscarModulos(ModuloFilterViewModel filterViewModel, WhereParams params) {
		StringBuffer sql=new StringBuffer();
		sql.append(" select m.IdModulo, "); 
		sql.append("  case when (m.IdParent is null) then '' else  (select mh.codigo from Modulo mh where mh.IdModulo=m.IdParent)"); 
		sql.append("   end  as codigoPadre , ");
		sql.append("  m.Codigo as codigo, m.Nombre as nombre ,m.Orden as orden,m.EtiquetaMenu as etiquetaMenu, ");
		sql.append("  case when (m.Visible=1) then 'Visible' else 'No Visible' end as visible ");
		sql.append("  from Modulo m ");
		sql.append(" WHERE 1=1 ");
		sql.append(params.filter(" AND UPPER(m.Codigo) LIKE  UPPER ('%'+ :codigo +'%') ",filterViewModel.getCodigo()));
		sql.append(params.filter(" AND UPPER(m.Nombre) LIKE  UPPER ('%'+ :nombre +'%') ",filterViewModel.getNombre()));
		sql.append(params.filter(" AND m.Visible = :visible ",filterViewModel.getVisible()));
	     
		return sql.toString();
	}


	@Override
	public List<HorasExtraEmpleadoResultViewModel> busquedaRapidaHorasExtrasEmpleado(QuickFilterViewModel quickFilter) {
		WhereParams params = new WhereParams();
        String sql = generarBusquedaRapidaHoraExtra((HorasExtraEmpleadoQuickFilterViewModel)quickFilter, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<HorasExtraEmpleadoResultViewModel>(HorasExtraEmpleadoResultViewModel.class));
	}

	private String generarBusquedaRapidaHoraExtra(HorasExtraEmpleadoQuickFilterViewModel quickFilter,WhereParams params) {
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

        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = he.IdEmpleado AND ((HISTORIAL.FechaInicio < getdate() AND HISTORIAL.FechaFin > getDate()) OR (HISTORIAL.FechaInicio < getdate() AND HISTORIAL.FechaFin IS NULL)) ");

        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");

        sql.append("  LEFT JOIN Empleado ep on PROY.IdJefe = ep.IdEmpleado ");
		sql.append("  LEFT JOIN Empleado eda on DEP.IdJefe = eda.IdEmpleado ");
		sql.append("  LEFT JOIN Empleado eun on UN.IdJefe = eun.IdEmpleado ");

        sql.append(" where 1=1 ");
        sql.append(params.filter(" AND UPPER(EMPLEADO.Nombre) LIKE UPPER('%' + :value + '%') ", quickFilter.getValue()));
        sql.append(params.filter(" OR UPPER(EMPLEADO.ApellidoPaterno) LIKE UPPER('%' + :value + '%') ", quickFilter.getValue()));
        sql.append(params.filter(" OR UPPER(EMPLEADO.ApellidoMaterno) LIKE UPPER('%' + :value + '%') ", quickFilter.getValue()));
        sql.append(params.filterDateDesde_US(" AND he.Fecha " , quickFilter.getFechaInicio()));
        sql.append(params.filterDateHasta_US(" AND he.Fecha ", quickFilter.getFechaFin()));
        sql.append(params.filter(" AND he.IdAtendidoPor = :idJefeEmpleado ", quickFilter.getIdJefeInmediato()));
        
        return sql.toString();
	}
	
	@Override
	public List<HorasExtraViewModel> verHorasExtras(PeriodoEmpleadoViewModel periodoEmpleado) {
		WhereParams params = new WhereParams();
        String sql = generarVerHorasExtras(periodoEmpleado, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<HorasExtraViewModel>(HorasExtraViewModel.class));

	}
	
	private String generarVerHorasExtras(PeriodoEmpleadoViewModel filter,WhereParams params) {
		StringBuilder sql = new StringBuilder();
        sql.append(" SELECT HORAEXTRA.IdHorasExtra AS idHorasExtra, ");
        sql.append(" HORAEXTRA.IdEmpleado AS idEmpleado, ");
        sql.append(" HORAEXTRA.IdAtendidoPor AS idAtendidoPor, ");
       
        sql.append(" CONCAT(ATENDIDOPOR.ApellidoPaterno, ' ', ATENDIDOPOR.ApellidoMaterno, ', ', ATENDIDOPOR.Nombre) as jefeInmediato, ");
        sql.append(" HORAEXTRA.Fecha as fecha, ");
        sql.append(" HORAEXTRA.Horas as horas, ");
        
        sql.append(" HORAEXTRA.ComentarioResolucion as comentarioResolucion, ");
        sql.append(" HORAEXTRA.HorasNoCompensables as horasNoCompensables, ");
        
        sql.append(" HORAEXTRA.HoraSalidaHorario as horaSalidaHorario,  ");
        sql.append(" HORAEXTRA.HoraSalidaSolicitado as horaSalidaSolicitado , ");
        sql.append(" HORAEXTRA.Motivo as motivo,  ");
        sql.append(" HORAEXTRA.Tipo as tipo,  ");
        sql.append(" HORAEXTRA.Estado as estado,  ");
        sql.append(" ESTADO.Nombre as nombreEstado,  ");
        
        sql.append(" HORAEXTRA.Creador AS creador, ");
        sql.append(" HORAEXTRA.Actualizador AS actualizador, ");
        sql.append(" HORAEXTRA.FechaCreacion AS fechaCreacion, ");
        sql.append(" HORAEXTRA.FechaActualizacion AS fechaActualizacion,  ");
        sql.append(" HORAEXTRA.RowVersion AS rowVersion  ");

        sql.append(" FROM HorasExtra HORAEXTRA  ");

        sql.append(" LEFT JOIN Empleado ATENDIDOPOR ON ATENDIDOPOR.IdEmpleado = HORAEXTRA.IdAtendidoPor  ");
        sql.append(" LEFT JOIN PeriodoEmpleado PERIODO ON PERIODO.IdEmpleado = HORAEXTRA.IdEmpleado ");
        sql.append(" LEFT JOIN TablaGeneral ESTADO ON HORAEXTRA.Estado=ESTADO.Codigo and ESTADO.GRUPO='HorasExtra.Estado'");
        
        sql.append(" WHERE HORAEXTRA.Fecha >= PERIODO.FechaInicio  ");
        sql.append(" AND HORAEXTRA.Fecha <= PERIODO.FechaFin ");
        
        sql.append(params.filter(" AND PERIODO.IdPeriodoEmpleado = :idPeriodoEmpleado ", filter.getIdPeriodoEmpleado()));
        sql.append(params.filter(" AND HORAEXTRA.IdEmpleado = :idEmpleado ", filter.getIdEmpleado()));
        
        
        return sql.toString();
	}
	
	@Override
	public List<HorasExtraViewModel> buscarHorasExtrasPorMesCompensacion(HorasExtraEmpleadoFilterViewModel filterViewModel) {
		WhereParams params = new WhereParams();
        String sql = generarBuscarHorasExtrasPorMesCompensacion(filterViewModel, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<HorasExtraViewModel>(HorasExtraViewModel.class));

	}
	
	private String generarBuscarHorasExtrasPorMesCompensacion(HorasExtraEmpleadoFilterViewModel filter,WhereParams params) {
		StringBuilder sql = new StringBuilder();
        sql.append(" SELECT HORAEXTRA.IdHorasExtra AS idHorasExtra, ");
        sql.append(" CONCAT(ATENDIDOPOR.ApellidoPaterno, ' ', ATENDIDOPOR.ApellidoMaterno, ', ', ATENDIDOPOR.Nombre) as jefeInmediato, ");
        sql.append(" HORAEXTRA.Fecha as fecha, ");
        sql.append(" HORAEXTRA.Horas as horas, ");
        
        sql.append(" HORAEXTRA.HoraSalidaHorario as horaSalidaHorario, ");
        sql.append(" HORAEXTRA.HoraSalidaSolicitado as horaSalidaSolicitado ");

        sql.append(" from HorasExtra HORAEXTRA ") ;

        sql.append(" LEFT JOIN Empleado ATENDIDOPOR ON ATENDIDOPOR.IdEmpleado = HORAEXTRA.IdAtendidoPor ");
        
        sql.append(" where 1=1 ");
        sql.append(params.filter(" AND HORAEXTRA.IdEmpleado = :idEmpleado ", filter.getIdEmpleado()));
        sql.append(params.filter(" AND MONTH(HORAEXTRA.Fecha) = :mes ", filter.getMes()));
        sql.append(params.filter(" AND Year(HORAEXTRA.Fecha) = :anio ", filter.getAnio()));
        
        
        return sql.toString();
	}


}
