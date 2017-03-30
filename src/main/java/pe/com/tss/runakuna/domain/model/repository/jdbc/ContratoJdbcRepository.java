package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.view.model.ContratoResultViewModel;
import pe.com.tss.runakuna.view.model.ContratoViewModel;
import pe.com.tss.runakuna.view.model.EmpleadoViewModel;
import pe.com.tss.runakuna.support.WhereParams;

@Repository
public class ContratoJdbcRepository implements ContratoRepository{
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	ContratoJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<ContratoViewModel> obtenerContratosPorEmpleado(Long idEmpleado) {
		
		WhereParams params = new WhereParams();
		
		String sql = generarVerContratos(idEmpleado, params);

	    return jdbcTemplate.query(sql.toString(),
	            params.getParams(), new BeanPropertyRowMapper<ContratoViewModel>(ContratoViewModel.class));
	}
	
	@Override
	public List<ContratoResultViewModel> busquedaContratosPorEmpleado(Long idEmpleado) {
		
		WhereParams params = new WhereParams();
		
		String sql = generarBusquedaContratosPorEmpleado(idEmpleado, params);

	    return jdbcTemplate.query(sql.toString(),
	            params.getParams(), new BeanPropertyRowMapper<ContratoResultViewModel>(ContratoResultViewModel.class));
	}
	
	private String generarVerContratos(Long idEmpleado, WhereParams params) {

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT  ");
        sql.append(" C.IdContrato AS idContrato, ");
        sql.append(" C.FechaInicio AS fechaInicio, ");
        sql.append(" C.FechaFin AS fechaFin, ");
        sql.append(" C.Duracion AS duracion, ");
        sql.append(" C.Cargo AS cargo, ");
        sql.append(" C.Salario AS salario, ");
        sql.append(" C.Estado AS estado, ");
        sql.append(" ESTADOCONTRATO.Nombre AS estadoString,");
        sql.append(" TIPOCONTRATO.Nombre as tipoContratoString, ");
        sql.append(" C.Creador AS creador, ");
        sql.append(" C.Actualizador AS actualizador, ");
        sql.append(" C.FechaCreacion AS fechaCreacion, ");
        sql.append(" C.FechaActualizacion AS fechaActualizacion,  ");
        sql.append(" C.RowVersion AS rowVersion  ");
 
        sql.append(" FROM Contrato C ");
        sql.append(" LEFT JOIN TablaGeneral ESTADOCONTRATO ON C.Estado = ESTADOCONTRATO.Codigo AND ESTADOCONTRATO.Grupo = 'Contrato.Estado' ");
        sql.append(" LEFT JOIN TablaGeneral TIPOCONTRATO ON C.TipoContrato = TIPOCONTRATO.Codigo AND TIPOCONTRATO.Grupo = 'Empleado.ContratoTrabajo' ");
        sql.append(" where 1=1");
        
        sql.append(params.filter(" AND C.IdEmpleado = :idEmpleado ", idEmpleado));
        sql.append(" ORDER BY C.FechaInicio DESC");
        return sql.toString();
    }    
	
	
	private String generarBusquedaContratosPorEmpleado(Long idEmpleado, WhereParams params) {

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT  ");
        sql.append(" C.IdContrato AS idContrato, ");
        sql.append(" C.FechaInicio AS fechaInicio, ");
        sql.append(" C.FechaFin AS fechaFin, ");
        sql.append(" C.Duracion AS duracion, ");
        sql.append(" C.Cargo AS cargo, ");
        sql.append(" C.Salario AS salario, ");
        
        sql.append(" H.Salario as salario, ");
        sql.append(" M.Codigo as monedaCodigo, ");
        sql.append(" M.Nombre as monedaString, ");
        
        sql.append(" E.ContratoTrabajo AS tipoContrato, ");
        sql.append(" TIPOCONTRATO.Nombre as tipoContratoString, ");
        sql.append(" ESTADOCONTRATO.Nombre AS estado ");
        sql.append(" FROM Contrato C ");
        sql.append(" LEFT JOIN TablaGeneral ESTADOCONTRATO ON C.Estado = ESTADOCONTRATO.Codigo AND ESTADOCONTRATO.Grupo = 'Contrato.Estado' ");
        sql.append(" LEFT JOIN TablaGeneral TIPOCONTRATO ON C.TipoContrato = TIPOCONTRATO.Codigo AND TIPOCONTRATO.Grupo = 'Empleado.ContratoTrabajo' ");
        sql.append(" LEFT JOIN EMPLEADO E on C.IdEmpleado=E.IdEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral H on C.IdEmpleado=H.IdEmpleado and (H.FechaInicio<=C.FechaInicio and (H.FechaFin is null or H.FechaFin>=C.FechaFin)) ");
        sql.append(" LEFT JOIN Moneda M on H.IdMoneda=M.IdMoneda ");
        sql.append(" WHERE 1=1");
        
        sql.append(params.filter(" AND C.IdEmpleado = :idEmpleado ", idEmpleado));
        
        sql.append(" ORDER BY C.FechaInicio DESC ");
        
        return sql.toString();
    }
	
}
