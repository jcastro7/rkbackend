package pe.com.tss.runakuna.domain.model.repository.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.view.model.HorarioFilterViewModel;
import pe.com.tss.runakuna.view.model.HorarioResultViewModel;
import pe.com.tss.runakuna.view.model.HorarioDiaViewModel;
import pe.com.tss.runakuna.view.model.HorarioViewModel;
import pe.com.tss.runakuna.support.WhereParams;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

@Repository
public class HorarioJdbcRepository implements HorarioRepository {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    DataSource dataSource;

    private NamedParameterJdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

	@Override
	public List<HorarioResultViewModel> busquedaHorario(HorarioFilterViewModel busquedaHorarioDto) {
		WhereParams params = new WhereParams();
        String sql = generarBusquedaHorario(busquedaHorarioDto, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<HorarioResultViewModel>(HorarioResultViewModel.class));
	}
	
	@Override
	public List<HorarioViewModel> obtenerHorariosPorTipoHorario(HorarioViewModel horarioDto) {
		WhereParams params = new WhereParams();
        String sql = generarHorarioPorTipoHorario(horarioDto, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<HorarioViewModel>(HorarioViewModel.class));
	}	
	
	@Override
	public HorarioViewModel obtenerHorariosPorTipoHorarioyPorDefecto(HorarioViewModel horarioDto) {
		WhereParams params = new WhereParams();
        String sql = generarHorarioPorTipoHorarioyPorDefecto(horarioDto, params);

        HorarioViewModel horario = null;
        
        List<HorarioViewModel> horarios = jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<HorarioViewModel>(HorarioViewModel.class));
        
        if(horarios!=null && horarios.size()>0){
        	horario = horarios.get(0);
        }
        
        return horario;
        
	}

	@Override
	public List<HorarioDiaViewModel> obtenerHorarioDiaPorHorario(Long idHorario) {
		WhereParams params = new WhereParams();
        String sql = generarHorarioDiaPorHorario(idHorario, params);

        return jdbcTemplate.query(sql,
                params.getParams(), new BeanPropertyRowMapper<HorarioDiaViewModel>(HorarioDiaViewModel.class));
	}
	
    private String generarBusquedaHorario(HorarioFilterViewModel busquedaHorariooDto, WhereParams params) {
		
    	StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append(" HOR.IdHorario AS idHorario, ");
        sql.append(" TIPOHORARIO.Nombre AS nombreTipoHorario, ");
        sql.append(" ESTADO.Nombre AS nombreEstado, ");
        sql.append(" PROY.Nombre AS nombreProyecto, ");
        sql.append(" HOR.Nombre AS nombre, ");
        sql.append(" HOR.HorasSemanal AS horasSemanal, ");
        sql.append(" 5 AS diasSemana, ");

        sql.append(" CASE  ");
        sql.append(" WHEN HOR.PorDefecto = 1 THEN 'Si' ");
        sql.append(" WHEN HOR.PorDefecto = 0 THEN 'No' ");
        sql.append(" ELSE ' ' ");
        sql.append(" END AS nombrePorDefecto  ");

        sql.append(" FROM Horario HOR ");
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HOR.IdProyecto ");
        sql.append(" LEFT JOIN TablaGeneral TIPOHORARIO ON HOR.TipoHorario= TIPOHORARIO.Codigo AND TIPOHORARIO.Grupo = 'Horario.TipoHorario' ");
        sql.append(" LEFT JOIN TablaGeneral ESTADO ON HOR.Estado= ESTADO.Codigo AND ESTADO.Grupo = 'Estado' ");
                
        sql.append(" where 1=1 ");
        sql.append(params.filter(" AND PROY.IdProyecto = :proyecto ", busquedaHorariooDto.getProyecto()));
        sql.append(params.filter(" AND HOR.PorDefecto = :porDefecto ", busquedaHorariooDto.getPorDefecto()));
        sql.append(params.filter(" AND HOR.TipoHorario = :tipoHorario ", busquedaHorariooDto.getTipoHorario()));
        sql.append(params.filter(" AND HOR.Estado = :estado ", busquedaHorariooDto.getEstado()));
        
        return sql.toString();
	}
    
    private String generarHorarioPorTipoHorario(HorarioViewModel horarioDto, WhereParams params) {
		
    	StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append(" HOR.IdHorario AS idHorario, ");
        sql.append(" HOR.Nombre AS nombre ");

        sql.append(" FROM Horario HOR ");
                
        sql.append(" WHERE 1=1 ");
        
        if("EM".equals(horarioDto.getTipoHorario())){
        	 sql.append(" AND HOR.IdProyecto IS NULL ");
        }else if("PR".equals(horarioDto.getTipoHorario())){
        	sql.append(" AND HOR.IdProyecto= "+horarioDto.getIdProyecto());
        }
        
        
        
        return sql.toString();
	}
    
    private String generarHorarioPorTipoHorarioyPorDefecto(HorarioViewModel horarioDto, WhereParams params) {
		
    	StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append(" HOR.IdHorario AS idHorario, ");
        sql.append(" HOR.Nombre AS nombre, ");
        sql.append(" HOR.HorasSemanal AS horasSemanal ");

        sql.append(" FROM Horario HOR ");
                
        sql.append(" WHERE 1=1 ");
        
        if("EM".equals(horarioDto.getTipoHorario())){
        	 sql.append(" AND HOR.IdProyecto IS NULL ");
        	 sql.append(" AND HOR.PorDefecto =1 ");
        }
        
        if("PR".equals(horarioDto.getTipoHorario())){
        	sql.append(" AND HOR.IdProyecto= "+horarioDto.getIdProyecto());
       	 	sql.append(" AND HOR.PorDefecto =1 ");
       }
       
        
        return sql.toString();
	}
    
    private String generarHorarioDiaPorHorario(Long idHorario, WhereParams params) {

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT   ");
        sql.append(" HORDIA.IdHorarioDia AS idHorarioDia, ");
        sql.append(" HORDIA.DiaSemana AS diaSemana, ");
        sql.append(" DIASEM.Nombre AS nombreDiaSemana, ");
        sql.append(" HORDIA.Entrada AS entrada, ");
        sql.append(" HORDIA.Salida AS salida, ");
        sql.append(" HORDIA.TiempoAlmuerzo AS tiempoAlmuerzo, ");
        sql.append(" HORDIA.Laboral AS laboral,  ");
        sql.append(" HORDIA.NumeroMarcaciones AS numeroMarcaciones,  ");
        
        sql.append(" HORDIA.Creador AS creador, ");
        sql.append(" HORDIA.Actualizador AS actualizador, ");
        sql.append(" HORDIA.FechaCreacion AS fechaCreacion, ");
        sql.append(" HORDIA.FechaActualizacion AS fechaActualizacion,  ");
        sql.append(" HORDIA.RowVersion AS rowVersion  ");
        
        sql.append(" FROM HorarioDia HORDIA ");
        sql.append(" LEFT JOIN TablaGeneral DIASEM ON DIASEM.Codigo=HORDIA.DiaSemana AND DIASEM.Grupo='Dia' ");
        sql.append(" WHERE HORDIA.IdHorario="+idHorario);

        return sql.toString();
    }

}