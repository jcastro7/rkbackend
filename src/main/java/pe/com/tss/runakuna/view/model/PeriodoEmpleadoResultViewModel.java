package pe.com.tss.runakuna.view.model;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.tss.runakuna.json.JsonDateSimpleDeserializer;
import pe.com.tss.runakuna.json.JsonDateSimpleSerializer;

public class PeriodoEmpleadoResultViewModel extends ResultViewModel{
	
	private Long idPeriodoEmpleado;
	private String nombreEmpleado;
	
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date fechaInicio;
	
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date fechaFin;
	
	private Integer maxDiasVacaciones;
	private Integer diasVacacionesDisponibles;
	private Integer maxPermisos;
	private Integer permisosDisponibles;
	
	public Long getIdPeriodoEmpleado() {
		return idPeriodoEmpleado;
	}
	public void setIdPeriodoEmpleado(Long idPeriodoEmpleado) {
		this.idPeriodoEmpleado = idPeriodoEmpleado;
	}
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}
	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Integer getMaxDiasVacaciones() {
		return maxDiasVacaciones;
	}
	public void setMaxDiasVacaciones(Integer maxDiasVacaciones) {
		this.maxDiasVacaciones = maxDiasVacaciones;
	}
	public Integer getDiasVacacionesDisponibles() {
		return diasVacacionesDisponibles;
	}
	public void setDiasVacacionesDisponibles(Integer diasVacacionesDisponibles) {
		this.diasVacacionesDisponibles = diasVacacionesDisponibles;
	}
	public Integer getMaxPermisos() {
		return maxPermisos;
	}
	public void setMaxPermisos(Integer maxPermisos) {
		this.maxPermisos = maxPermisos;
	}
	public Integer getPermisosDisponibles() {
		return permisosDisponibles;
	}
	public void setPermisosDisponibles(Integer permisosDisponibles) {
		this.permisosDisponibles = permisosDisponibles;
	}
	
	

}
