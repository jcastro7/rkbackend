package pe.com.empresa.rk.view.model;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.empresa.rk.json.JsonDateSimpleDeserializer;
import pe.com.empresa.rk.json.JsonDateSimpleSerializer;


public class MarcacionDashboardFilterViewModel extends FilterViewModel {

	private Long idEmpleado;
	private Long unidadNegocio;
    private Long departamento;
    private Long proyecto;
    
    @JsonSerialize(using=JsonDateSimpleSerializer.class)
    @JsonDeserialize(using=JsonDateSimpleDeserializer.class)
    private Date fecha;
    
    private String tipoMarcacion;
    
	public Long getUnidadNegocio() {
		return unidadNegocio;
	}
	public void setUnidadNegocio(Long unidadNegocio) {
		this.unidadNegocio = unidadNegocio;
	}
	public Long getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Long departamento) {
		this.departamento = departamento;
	}
	public Long getProyecto() {
		return proyecto;
	}
	public void setProyecto(Long proyecto) {
		this.proyecto = proyecto;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Long getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public String getTipoMarcacion() {
		return tipoMarcacion;
	}
	public void setTipoMarcacion(String tipoMarcacion) {
		this.tipoMarcacion = tipoMarcacion;
	}
	
	
    
    
}
