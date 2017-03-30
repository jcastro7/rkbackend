package pe.com.empresa.rk.domain.model.repository.jdbc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.empresa.rk.view.model.DepartamentoViewModel;

@Repository
public class DepartamentoJdbcRepository implements DepartamentoRepository{

	private static final Logger LOGGER = LoggerFactory.getLogger(DepartamentoJdbcRepository.class);
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	DepartamentoJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	private static final String SEARCH_ALL_DEPARMENTOS = "select * from Departamento";
	
	@Override
	public List<DepartamentoViewModel> findDepartamentos() {

        List<DepartamentoViewModel> searchResults = jdbcTemplate.query(SEARCH_ALL_DEPARMENTOS,
                new BeanPropertyRowMapper<>(DepartamentoViewModel.class)
        );
        LOGGER.info("Found {} departamentos", searchResults.size());

        return searchResults;
	}

}
