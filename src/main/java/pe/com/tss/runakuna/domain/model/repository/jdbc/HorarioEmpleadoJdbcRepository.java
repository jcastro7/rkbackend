package pe.com.tss.runakuna.domain.model.repository.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.view.model.ContratoViewModel;
import pe.com.tss.runakuna.view.model.HorarioEmpleadoDiaViewModel;
import pe.com.tss.runakuna.view.model.HorarioEmpleadoViewModel;
import pe.com.tss.runakuna.support.ExtendedBeanPropertyRowMapper;
import pe.com.tss.runakuna.support.WhereParams;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

@Repository
public class HorarioEmpleadoJdbcRepository implements HorarioEmpleadoRepository {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    DataSource dataSource;

    private NamedParameterJdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    
    
    @Override
	public HorarioEmpleadoViewModel findOneById(Long idHorarioEmpleado) {
    	HorarioEmpleadoViewModel horarioEmpleadoVM = new HorarioEmpleadoViewModel();
    	
    	WhereParams params = new WhereParams();
        String sql = findOneByIdSql(idHorarioEmpleado, params);
    	
    	horarioEmpleadoVM =  jdbcTemplate.queryForObject(sql.toString(),
	            params.getParams(), new BeanPropertyRowMapper<HorarioEmpleadoViewModel>(HorarioEmpleadoViewModel.class));
    	return horarioEmpleadoVM;
    }
    
    @Override
   	public List<HorarioEmpleadoDiaViewModel> findHorarioEmpleadoDiaByIdHorarioEmpleado(Long idHorarioEmpleado) {
    	List<HorarioEmpleadoDiaViewModel> horariosEmpleadoDia = new ArrayList<>();
       	
       	WhereParams params = new WhereParams();
           String sql = findHorarioEmpleadoDiaByIdHorarioEmpleadoSql(idHorarioEmpleado, params);
       	
           horariosEmpleadoDia =  jdbcTemplate.query(sql.toString(),
   	            params.getParams(), new BeanPropertyRowMapper<HorarioEmpleadoDiaViewModel>(HorarioEmpleadoDiaViewModel.class));
       	return horariosEmpleadoDia;
       }
    
	@Override
	public HorarioEmpleadoViewModel ObtenerHorarioEmpleado(Long idHorarioEmpleado) {
		
		HorarioEmpleadoViewModel horarioEmpleadoVM = new HorarioEmpleadoViewModel();
		
		WhereParams params = new WhereParams();
        String sql = generarVerHorarioEmpleado(idHorarioEmpleado, params);

        List<HorarioEmpleadoViewModel> horariosEmpleado = jdbcTemplate.query(sql, params.getParams(),
				new ResultSetExtractor<List<HorarioEmpleadoViewModel>>() {
				
        	@Override
			public List<HorarioEmpleadoViewModel> extractData(ResultSet rs) throws SQLException {
		        Hashtable<Integer,HorarioEmpleadoViewModel> horariosEmpleadoDia = new Hashtable<Integer,HorarioEmpleadoViewModel>();
		        
		        while(rs.next()) {
		            Integer idHorarioEmpleado = rs.getInt("idHorarioEmpleado");
		            HorarioEmpleadoViewModel horarioEmpleado = horariosEmpleadoDia.get(idHorarioEmpleado);
		            if (horarioEmpleado == null) {
		            	horarioEmpleado = new ExtendedBeanPropertyRowMapper<HorarioEmpleadoViewModel>(HorarioEmpleadoViewModel.class).mapRow(rs, rs.getRow());
		            	horariosEmpleadoDia.put(idHorarioEmpleado,horarioEmpleado);
		            }
		            HorarioEmpleadoDiaViewModel item = new ExtendedBeanPropertyRowMapper<HorarioEmpleadoDiaViewModel>(HorarioEmpleadoDiaViewModel.class).mapRow(rs, rs.getRow());
		            horarioEmpleado.getHorariosEmpleadoDia().add(item);
		        }
		        return new ArrayList<HorarioEmpleadoViewModel>(horariosEmpleadoDia.values());
		    
			}
			
		});
        if(horariosEmpleado!=null && horariosEmpleado.size()>0){
        	horarioEmpleadoVM = horariosEmpleado.get(0);
        }
        	return horarioEmpleadoVM;
	}

	private String findOneByIdSql(Long idHorarioEmpleado, WhereParams params) {

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT  ");
        sql.append(" he.IdHorarioEmpleado AS idHorarioEmpleado, ");
        sql.append(" he.IdHorario AS idHorario, ");
        sql.append(" he.TipoHorario AS tipoHorario, ");
        sql.append(" he.HorasSemanal AS horasSemanal, ");
        sql.append(" he.InicioVigencia AS inicioVigencia, ");
        sql.append(" he.FinVigencia AS finVigencia, ");
        
        sql.append(" he.Creador AS creador, ");
        sql.append(" he.Actualizador AS actualizador, ");
        sql.append(" he.FechaCreacion AS fechaCreacion, ");
        sql.append(" he.FechaActualizacion AS fechaActualizacion,  ");
        sql.append(" he.RowVersion AS rowVersion  ");
               
        sql.append(" FROM HorarioEmpleado he ");

        sql.append(" where 1=1");
        sql.append(params.filter(" AND he.IdHorarioEmpleado = :idHorarioEmpleado ", idHorarioEmpleado));

        return sql.toString();
    }
	
	private String findHorarioEmpleadoDiaByIdHorarioEmpleadoSql(Long idHorarioEmpleado, WhereParams params) {

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT  ");
        sql.append(" hed.IdHorarioEmpleado AS \"idHorarioEmpleado\", ");
        sql.append(" hed.IdHorarioEmpleadoDia AS \"idHorarioEmpleadoDia\", ");
        sql.append(" hed.laboral AS \"laboral\", ");
        sql.append(" hed.Entrada AS \"entrada\", ");
        sql.append(" hed.DiaSemana AS \"diaSemana\", ");
        sql.append(" hed.Salida AS \"salida\", ");
        sql.append(" hed.NumeroMarcaciones AS \"numeroMarcaciones\", ");
        sql.append(" hed.TiempoAlmuerzo AS \"tiempoAlmuerzo\", ");
        sql.append(" DIASEMANA.Nombre AS \"nombreDiaSemana\", ");
        sql.append(" DIASEMANA.Nombre AS \"nombreDiaSemana\", ");
        
        sql.append(" hed.Creador AS \"creador\", ");
        sql.append(" hed.Actualizador AS \"actualizador\", ");
        sql.append(" hed.FechaCreacion AS \"fechaCreacion\", ");
        sql.append(" hed.FechaActualizacion AS \"fechaActualizacion\",  ");
        sql.append(" hed.RowVersion AS \"rowVersion\"  ");
        
        sql.append(" FROM HorarioEmpleadoDia hed ");
        sql.append(" LEFT JOIN TablaGeneral DIASEMANA ON hed.DiaSemana= DIASEMANA.Codigo AND DIASEMANA.Grupo = 'Dia' ");

        sql.append(" where 1=1");
        sql.append(params.filter(" AND hed.IdHorarioEmpleado = :idHorarioEmpleado ", idHorarioEmpleado));

        return sql.toString();
    }
	
	private String generarVerHorarioEmpleado(Long idHorarioEmpleado, WhereParams params) {

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT  ");
        
        sql.append(" he.IdHorario AS \"idHorario\", ");
        sql.append(" he.TipoHorario AS \"tipoHorario\", ");
        sql.append(" he.HorasSemanal AS \"horasSemanal\", ");
        sql.append(" he.InicioVigencia AS \"inicioVigencia\", ");
        sql.append(" he.FinVigencia AS \"finVigencia\", ");
        
        sql.append(" he.Creador AS \"creador\", ");
        sql.append(" he.Actualizador AS \"actualizador\", ");
        sql.append(" he.FechaCreacion AS \"fechaCreacion\", ");
        sql.append(" he.FechaActualizacion AS \"fechaActualizacion\",  ");
        sql.append(" he.RowVersion AS \"rowVersion\",  ");
        sql.append(" he.IdHorarioEmpleado AS \"idHorarioEmpleado\", ");
        
        sql.append(" hed.IdHorarioEmpleadoDia AS \"idHorarioEmpleadoDia\", ");
        sql.append(" hed.laboral AS \"laboral\", ");
        sql.append(" hed.Entrada AS \"entrada\", ");
        sql.append(" hed.DiaSemana AS \"diaSemana\", ");
        sql.append(" hed.Salida AS \"salida\", ");
        sql.append(" hed.TiempoAlmuerzo AS \"tiempoAlmuerzo\", ");
        sql.append(" DIASEMANA.Nombre AS \"nombreDiaSemana\", ");
        
        sql.append(" hed.Creador AS \"creador\", ");
        sql.append(" hed.Actualizador AS \"actualizador\", ");
        sql.append(" hed.FechaCreacion AS \"fechaCreacion\", ");
        sql.append(" hed.FechaActualizacion AS \"fechaActualizacion\",  ");
        sql.append(" hed.RowVersion AS \"rowVersion\"  ");
        
        sql.append(" FROM HorarioEmpleado he ");
        sql.append(" LEFT JOIN HorarioEmpleadoDia hed ON he.idHorarioEmpleado = hed.idHorarioEmpleado ");
        sql.append(" LEFT JOIN TablaGeneral DIASEMANA ON hed.DiaSemana= DIASEMANA.Codigo AND DIASEMANA.Grupo = 'Dia' ");

        sql.append(" where 1=1");
        sql.append(params.filter(" AND he.IdHorarioEmpleado = :idHorarioEmpleado ", idHorarioEmpleado));

        return sql.toString();
    }
    
}