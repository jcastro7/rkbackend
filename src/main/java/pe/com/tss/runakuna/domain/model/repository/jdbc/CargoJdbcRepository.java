package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.support.WhereParams;
import pe.com.tss.runakuna.view.model.CargoComboViewModel;
import pe.com.tss.runakuna.view.model.CargoFilterViewModel;
import pe.com.tss.runakuna.view.model.CargoQuickFilterViewModel;
import pe.com.tss.runakuna.view.model.CargoResultViewModel;
import pe.com.tss.runakuna.view.model.CargoViewModel;
import pe.com.tss.runakuna.view.model.HorarioViewModel;
import pe.com.tss.runakuna.view.model.ProyectoFilterViewModel;
import pe.com.tss.runakuna.view.model.QuickFilterViewModel;

@Repository
public class CargoJdbcRepository implements CargoRepository{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CargoJdbcRepository.class);
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	CargoJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private static final String SEARCH_ALL_CARGOS = "select * from Cargo";
	private static final String SEARCH_NOMBRE_HORARIO = "select * from Horario";
	
	@Override
	public List<CargoViewModel> findCargo() {
		List<CargoViewModel> searchResults = jdbcTemplate.query(SEARCH_ALL_CARGOS,
                new BeanPropertyRowMapper<>(CargoViewModel.class)
        );
        LOGGER.info("Found {} cargos", searchResults.size());

        return searchResults;
	}

	@Override
	public List<HorarioViewModel> obtenerNombreHorario() {
		List<HorarioViewModel> searchResults = jdbcTemplate.query(SEARCH_NOMBRE_HORARIO,
                new BeanPropertyRowMapper<>(HorarioViewModel.class)
        );
        LOGGER.info("Found {} nombre Horario", searchResults.size());

        return searchResults;
	}

	@Override
	public List<CargoResultViewModel> obtenerCargos(CargoFilterViewModel dto) {
		WhereParams params = new WhereParams();
        String sql = generarObtenerCargos(dto, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<CargoResultViewModel>(CargoResultViewModel.class));

	}

	private String generarObtenerCargos(CargoFilterViewModel dto, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT ");
		sql.append(" C.IdCargo AS idCargo, ");
		sql.append(" C.Nombre AS nombre, ");
		sql.append(" DEP.Nombre AS nombreDepartamento, ");
        sql.append(" UN.Nombre AS nombreUnidadNegocio, ");
        sql.append(" PROY.Nombre AS nombreProyecto, ");
        sql.append(" S.Nombre AS nombreCargoSuperior, ");
        sql.append(" CASE WHEN  (C.Estado='A') THEN 'Activo' ELSE 'Inactivo' END as estado ");
        //sql.append(" ESTADO.Nombre AS nombreEstado ");
        sql.append(" FROM Cargo C ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = C.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = C.IdUnidadDeNegocio ");
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = C.IdProyecto ");
        //sql.append(" LEFT JOIN TablaGeneral ESTADO ON C.estado = ESTADO.Codigo AND ESTADO.Grupo = 'Estado' ");
        sql.append(" LEFT JOIN Cargo S ON C.IdSuperior = S.IdCargo ");
        sql.append(" WHERE 1=1 ");
        sql.append(params.filter(" AND C.IdDepartamentoArea = :idDepartamentoArea ",dto.getIdDepartamentoArea()));
        sql.append(params.filter(" AND C.IdUnidadDeNegocio = :idUnidadDeNegocio ",dto.getIdUnidadDeNegocio()));
        sql.append(params.filter(" AND C.IdProyecto = :idProyecto ",dto.getIdProyecto()));
        sql.append(params.filter(" AND C.Estado = :estado ",dto.getEstado()));
        sql.append(params.filter(" AND UPPER(C.Nombre) LIKE  UPPER ('%'+ :nombre +'%') ",dto.getNombre()));
        
        
		return sql.toString();
	}

	@Override
	public List<CargoResultViewModel> busquedaRapidaCargos(QuickFilterViewModel quickFilter) {
		WhereParams params = new WhereParams();
        String sql = generarBusquedaRapidaCargos((CargoQuickFilterViewModel)quickFilter, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<CargoResultViewModel>(CargoResultViewModel.class));
	}

	private String generarBusquedaRapidaCargos(CargoQuickFilterViewModel quickFilter, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ");
		sql.append(" C.IdCargo AS idCargo, ");
		sql.append(" C.Nombre AS nombre, ");
		sql.append(" DEP.Nombre AS nombreDepartamento, ");
        sql.append(" UN.Nombre AS nombreUnidadNegocio, ");
        sql.append(" PROY.Nombre AS nombreProyecto, ");
        sql.append(" S.Nombre AS nombreCargoSuperior, ");
        sql.append(" CASE WHEN  (C.Estado='A') THEN 'Activo' ELSE 'Inactivo' END as estado ");
        //sql.append(" ESTADO.Nombre AS nombreEstado ");
        sql.append(" FROM Cargo C ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = C.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = C.IdUnidadDeNegocio ");
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = C.IdProyecto ");
        //sql.append(" LEFT JOIN TablaGeneral ESTADO ON C.estado = ESTADO.Codigo AND ESTADO.Grupo = 'Estado' ");
        sql.append(" LEFT JOIN Cargo S ON C.IdSuperior = S.IdCargo ");
        sql.append(" WHERE 1=1 ");
        sql.append(params.filter(" AND UPPER(UN.Nombre) LIKE  UPPER ('%'+ :value +'%') ",quickFilter.getValue()));
        sql.append(params.filter(" OR UPPER(DEP.Nombre) LIKE  UPPER ('%'+ :value +'%') ",quickFilter.getValue()));
        sql.append(params.filter(" OR UPPER(PROY.Nombre) LIKE  UPPER ('%'+ :value +'%') ",quickFilter.getValue()));
        
        
		return sql.toString();
	}

	@Override
	public List<CargoComboViewModel> obtenerCargoComboHistorialLaboral(ProyectoFilterViewModel filter) {
		WhereParams params = new WhereParams();
        String sql = generarCargoComboHistorialLaboralQuery(filter, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<CargoComboViewModel>(CargoComboViewModel.class));
	}
	
	private String generarCargoComboHistorialLaboralQuery(ProyectoFilterViewModel filter, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ");
		sql.append(" C.IdCargo AS idCargo, ");
		sql.append(" C.Nombre AS nombre ");
        sql.append(" FROM Cargo C ");
       
        sql.append(" WHERE 1=1 ");
        sql.append(params.filter(" AND C.IdUnidadDeNegocio=:idUnidadDeNegocio ",filter.getIdUnidadDeNegocio()));
        sql.append(params.filter(" AND C.IdDepartamentoArea=:idDepartamentoArea ",filter.getIdDepartamentoArea()));
           
		return sql.toString();
	}

}
