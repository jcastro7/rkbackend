package pe.com.empresa.rk.domain.model.repository.jdbc;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.empresa.rk.support.WhereParams;
import pe.com.empresa.rk.view.model.CargoComboViewModel;
import pe.com.empresa.rk.view.model.DepartamentoAreaViewModel;
import pe.com.empresa.rk.view.model.MonedaViewModel;
import pe.com.empresa.rk.view.model.ProyectoComboViewModel;
import pe.com.empresa.rk.view.model.TablaGeneralViewModel;
import pe.com.empresa.rk.view.model.TipoLicenciaViewModel;
import pe.com.empresa.rk.view.model.UnidadDeNegocioViewModel;

@Repository
public class LocalStorageJdbcRepository implements LocalStorageRepository{
	
private final NamedParameterJdbcTemplate jdbcTemplate;
    
    @Autowired
    DataSource dataSource;

    @Autowired
    LocalStorageJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    
	public List<UnidadDeNegocioViewModel> searchUnidadDeNegocio() {
		WhereParams params = new WhereParams();
        String sql = searchUnidadDeNegocioJdbc();

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<UnidadDeNegocioViewModel>(UnidadDeNegocioViewModel.class));
	}

	private String searchUnidadDeNegocioJdbc() {
		StringBuilder sql = new StringBuilder();
		
        sql.append(" SELECT A.IdUnidadDeNegocio AS idUnidadDeNegocio, ");
        sql.append(" A.Nombre AS nombre ");
        sql.append(" FROM UnidadDeNegocio A ");
        sql.append(" WHERE 1=1 ");
        sql.append(" AND A.Estado = 'A' ");        
        return sql.toString();
	}

	public List<DepartamentoAreaViewModel> searchDepartamentoArea() {
		WhereParams params = new WhereParams();
        String sql = searchDepartamentoAreaJdbc();

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<DepartamentoAreaViewModel>(DepartamentoAreaViewModel.class));
	}

	private String searchDepartamentoAreaJdbc() {
		StringBuilder sql = new StringBuilder();
		
        sql.append(" SELECT A.IdDepartamentoArea AS idDepartamentoArea, ");
        sql.append(" A.IdUnidadDeNegocio AS idUnidadDeNegocio, ");
        sql.append(" A.Nombre AS nombre ");
        sql.append(" FROM DepartamentoArea A ");
        sql.append(" WHERE 1=1 ");
        sql.append(" AND A.Estado = 'A' ");
        
        return sql.toString();
	}

	public List<ProyectoComboViewModel> searchProyecto() {
		WhereParams params = new WhereParams();
        String sql = searchProyectoJdbc();

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<ProyectoComboViewModel>(ProyectoComboViewModel.class));
	}

	private String searchProyectoJdbc() {
		StringBuilder sql = new StringBuilder();
		
        sql.append(" SELECT A.IdProyecto AS idProyecto, ");
        sql.append(" A.IdDepartamentoArea AS idDepartamentoArea, ");
        sql.append(" A.Nombre AS nombre ");
        sql.append(" FROM Proyecto A ");
        sql.append(" WHERE 1=1 ");
        sql.append(" AND A.Estado = 'A' ");
        
        return sql.toString();
	}

	public List<TablaGeneralViewModel> searchTablaGeneral() {
		WhereParams params = new WhereParams();
        String sql = searchTablaGeneralJdbc();

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<TablaGeneralViewModel>(TablaGeneralViewModel.class));
	}

	private String searchTablaGeneralJdbc() {
		StringBuilder sql = new StringBuilder();
		
        sql.append(" SELECT A.Codigo AS codigo, ");
        sql.append(" A.Nombre AS nombre, ");
        sql.append(" A.Grupo AS grupo ");
        sql.append(" FROM TablaGeneral A ");
        sql.append(" WHERE 1=1 ");
        sql.append(" AND A.Estado = 'A' ");
        
        return sql.toString();
	}


	public List<TipoLicenciaViewModel> searchTipoLicencia() {
		WhereParams params = new WhereParams();
        String sql = searchTipoLicenciaJdbc();

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<TipoLicenciaViewModel>(TipoLicenciaViewModel.class));
	}


	private String searchTipoLicenciaJdbc() {
		StringBuilder sql = new StringBuilder();
		
        sql.append(" SELECT A.Codigo AS codigo, ");
        sql.append(" A.IdTipoLicencia AS idTipoLicencia, ");
        sql.append(" A.Nombre AS nombre ");
        sql.append(" FROM TipoLicencia A ");
        sql.append(" WHERE 1=1 ");
        sql.append(" AND A.Estado = 'A' ");
        
        return sql.toString();
	}


	public List<MonedaViewModel> searchMoneda() {
		WhereParams params = new WhereParams();
        String sql = searchMonedaJdbc();

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<MonedaViewModel>(MonedaViewModel.class));
	}


	private String searchMonedaJdbc() {
		StringBuilder sql = new StringBuilder();
		
        sql.append(" SELECT A.IdMoneda AS idMoneda, ");
        sql.append(" A.Nombre AS nombre ");
        sql.append(" FROM Moneda A ");
        sql.append(" WHERE 1=1 ");
        
        return sql.toString();
	}


	public List<CargoComboViewModel> searchCargo() {
		WhereParams params = new WhereParams();
        String sql = searchCargoJdbc();

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<CargoComboViewModel>(CargoComboViewModel.class));
	}


	private String searchCargoJdbc() {
		StringBuilder sql = new StringBuilder();
		
        sql.append(" SELECT A.IdCargo AS idCargo, ");
        sql.append(" A.Nombre AS nombre ");
        sql.append(" FROM Cargo A ");
        sql.append(" WHERE 1=1 ");
        sql.append(" AND A.Estado = 'A' ");
        
        return sql.toString();
	}
	

}
