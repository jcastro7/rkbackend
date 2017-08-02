package pe.com.tss.runakuna.domain.model.repository.jdbc;

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
import pe.com.tss.runakuna.view.model.CalendarioFilterViewModel;
import pe.com.tss.runakuna.view.model.CalendarioResultViewModel;
import pe.com.tss.runakuna.view.model.PieChartDataResultViewModel;

@Repository
public class CalendarioJdbcRepository {

	private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    DataSource dataSource;

    private NamedParameterJdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

	public List<CalendarioResultViewModel> searchDiasNoLaborables(CalendarioFilterViewModel dto) {
		WhereParams params = new WhereParams();
        String sql = searchDiasNoLaborablesQuery(dto, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<>(CalendarioResultViewModel.class));
	}

	private String searchDiasNoLaborablesQuery(CalendarioFilterViewModel dto, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT c.IdCalendario AS idCalendario, ");
		sql.append(" c.Fecha AS fecha, ");
		sql.append(" c.Descripcion AS descripcion, ");
		sql.append(" c.Nombre AS nombre, ");
		sql.append(" c.DiaCompleto AS diaCompleto, ");
		sql.append(" c.HoraInicio AS horaInicio, ");
		sql.append(" c.HoraFin AS horaFin ");
		sql.append(" FROM Calendario c ");
		sql.append(" WHERE 1=1 ");
		if(dto.getFecha() != null){
			sql.append(params.filter(" AND c.Fecha = :fecha", dto.getFecha()));
		}else{
			if(dto.getFecha() == null && (dto.getMes() != 0 && dto.getYear()!=0)){
				sql.append(" AND month(fecha)= "+"'"+dto.getMes()+"'");
				sql.append(" AND year(fecha)= "+"'"+dto.getYear()+"'");
//				sql.append("AND convert(date,[Fecha]) = "+"'"+fechaConcat+"'");
//				sql.append(params.filter("AND cast([c.fecha] as date) = '02-17-2017'", dto.getDia()));
			}else{
				if(dto.getYear() == 0){
					dto.setYear(2017L);
				}
				sql.append(" AND year(fecha)= "+"'"+dto.getYear()+"'");
			}
		}

		sql.append(" ORDER BY c.Fecha ASC ");


		return sql.toString();
	}
}
