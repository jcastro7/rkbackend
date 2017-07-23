package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.support.WhereParams;
import pe.com.tss.runakuna.view.model.CargoFilterViewModel;
import pe.com.tss.runakuna.view.model.CargoResultViewModel;
import pe.com.tss.runakuna.view.model.CargoViewModel;
import pe.com.tss.runakuna.view.model.CentroCostoFilterViewModel;
import pe.com.tss.runakuna.view.model.CentroCostoResultViewModel;
import pe.com.tss.runakuna.view.model.HorarioViewModel;
import pe.com.tss.runakuna.view.model.TipoLicenciaResultViewModel;

@Repository
public class CentroCostoJdbcRepository implements CentroCostoRepository{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CentroCostoJdbcRepository.class);
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	CentroCostoJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private static final String SEARCH_ALL_CARGOS = "select * from Cargo";
	private static final String SEARCH_NOMBRE_HORARIO = "select * from Horario";

	@Override
	public List<CentroCostoResultViewModel> search(CentroCostoFilterViewModel dto) {
		WhereParams params = new WhereParams();
        String sql = generarObtenerCentroCosto(dto, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<CentroCostoResultViewModel>(CentroCostoResultViewModel.class));

	}

	private String generarObtenerCentroCosto(CentroCostoFilterViewModel filterViewModel, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ");
		sql.append(" c.IdCentroCosto AS idCentroCosto, ");
		sql.append(" u.Nombre AS nombreUnidadDeNegocio, ");
		sql.append(" d.Nombre AS nombreDepartamentoArea, ");
		sql.append(" CASE WHEN (c.Estado='A')  THEN 'Activo' ELSE 'Inactivo' END AS estado, ");
		sql.append(" c.Nombre AS nombre, ");
		sql.append(" c.Descripcion AS descripcion ");
		sql.append(" FROM CentroCosto c ");
		sql.append(" LEFT JOIN UnidadDeNegocio u ON c.IdUnidadDeNegocio=u.IdUnidadDeNegocio ");
		sql.append(" LEFT JOIN DepartamentoArea d ON c.IdDepartamentoArea=d.IdDepartamentoArea ");
        sql.append(" WHERE 1=1 ");
        sql.append(params.filter(" AND UPPER(c.Nombre) LIKE  UPPER ('%'+ :nombre +'%') ",filterViewModel.getNombre()));
        sql.append(params.filter(" AND c.Estado = :estado ",filterViewModel.getEstado()));
        sql.append(params.filter(" AND c.idUnidadDeNegocio = :idUnidadDeNegocio ",filterViewModel.getIdUnidadDeNegocio()));
        sql.append(params.filter(" AND c.idDepartamentoArea = :idDepartamentoArea ",filterViewModel.getIdDepartamentoArea()));
        
        return sql.toString();
	}
	
	

}
