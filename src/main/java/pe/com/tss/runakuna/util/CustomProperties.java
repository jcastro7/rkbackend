package pe.com.tss.runakuna.util;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class CustomProperties extends Properties{
	
	private final Logger logger = LoggerFactory.getLogger(CustomProperties.class);
	private static final long serialVersionUID = 1L;

	public CustomProperties(DataSource dataSource)
	{
		super();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource); 
		List<Map<String, Object>> l = jdbcTemplate.queryForList("select param_cd, param_value from sys_param");

		for(Map<String, Object> m: l)
		{
			setProperty((m.get("PARAM_CD")).toString(), (m.get("PARAM_VALUE")).toString());
		}
	}

}
