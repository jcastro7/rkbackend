package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.view.model.ContratoFilterViewModel;
import pe.com.tss.runakuna.view.model.ContratoResultViewModel;
import pe.com.tss.runakuna.view.model.ContratoViewModel;
import pe.com.tss.runakuna.support.WhereParams;
import pe.com.tss.runakuna.util.DateUtil;

@Repository
public class ContratoJdbcRepository implements ContratoRepository{
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	private String getdate;
	
	@Autowired
	ContratoJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		
		Date date = new Date();
		getdate = DateUtil.format(new SimpleDateFormat("yyyy-MM-dd"), date);
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

	@Override
	public List<ContratoResultViewModel> busquedaContratos(ContratoFilterViewModel filterViewModel) {
		WhereParams params = new WhereParams();
		
		String sql = busquedaContratosQuery(filterViewModel, params);

	    return jdbcTemplate.query(sql.toString(),
	            params.getParams(), new BeanPropertyRowMapper<ContratoResultViewModel>(ContratoResultViewModel.class));
	}

	private String busquedaContratosQuery(ContratoFilterViewModel filterViewModel, WhereParams params) {
		StringBuilder sql = new StringBuilder();
        sql.append(" SELECT  ");
        sql.append(" EMP.ApellidoPaterno+' '+EMP.ApellidoMaterno+', '+EMP.Nombre AS nombreCompletoEmpleado, ");
        sql.append(" C.Cargo AS cargo, ");
        sql.append(" EMP.ContratoTrabajo AS tipoContrato, ");
        sql.append(" C.FechaInicio AS fechaInicio, ");
        sql.append(" C.FechaFin AS fechaFin, ");
        sql.append(" ESTADOCONTRATO.Nombre AS estado ");
        sql.append(" FROM Contrato C ");
        sql.append(" LEFT JOIN Empleado EMP ON EMP.IdEmpleado = C.IdEmpleado ");
        sql.append(" LEFT JOIN TablaGeneral ESTADOCONTRATO ON C.Estado = ESTADOCONTRATO.Codigo AND ESTADOCONTRATO.Grupo = 'Contrato.Estado' ");
        sql.append(" LEFT JOIN TablaGeneral TIPOCONTRATO ON C.TipoContrato = TIPOCONTRATO.Codigo AND TIPOCONTRATO.Grupo = 'Empleado.ContratoTrabajo' ");
        sql.append(" LEFT JOIN HistorialLaboral H on C.IdEmpleado=H.IdEmpleado and (H.FechaInicio<=C.FechaInicio and (H.FechaFin is null AND H.FechaFin>=C.FechaFin)) ");
        sql.append(" WHERE 1=1");
        
        sql.append(params.filter(" AND C.IdEmpleado = :idEmpleado ", filterViewModel.getIdEmpleado()));
        sql.append(params.filter(" AND C.FechaInicio >= :fechaInicio ", filterViewModel.getFechaInicio()));
        sql.append(params.filter(" AND C.FechaFin <= :fechaFin ", filterViewModel.getFechaFin()));
        sql.append(params.filter(" AND TIPOCONTRATO.Codigo = :tipoContrato ", filterViewModel.getTipoContrato()));
        
        if(filterViewModel.isSoloVigente()){
        	sql.append(" AND '"+getdate+"' BETWEEN c.FechaInicio and c.FechaFin ");
        }
        if(filterViewModel.isSoloPorVencer()){
        	sql.append(" AND '"+getdate+"' BETWEEN DATEADD(day,-7,c.FechaFin) AND c.FechaFin ");
        }
        
        sql.append(" ORDER BY C.FechaInicio DESC ");
        
        return sql.toString();
	}
	
}
