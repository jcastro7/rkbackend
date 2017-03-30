package pe.com.empresa.rk.view.model;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.empresa.rk.json.JsonDateSimpleDeserializer;
import pe.com.empresa.rk.json.JsonDateSimpleSerializer;

public class LicenciaEmpleadoResultViewModel extends ResultViewModel {

	
	private Long idLicencia;
	private String nombreEmpleado;	
	
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date fechaInicio;
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date fechaFin;
	private String estado;
	private String nombreTipoLicencia;
	private Integer dias;
	private Integer diaEntero;
	private String jefeInmediato;
	private String nombreDiaEntero;
	
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
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getNombreTipoLicencia() {
		return nombreTipoLicencia;
	}
	public void setNombreTipoLicencia(String nombreTipoLicencia) {
		this.nombreTipoLicencia = nombreTipoLicencia;
	}
	public Integer getDias() {
		return dias;
	}
	public void setDias(Integer dias) {
		this.dias = dias;
	}
	public Integer getDiaEntero() {
		return diaEntero;
	}
	public void setDiaEntero(Integer diaEntero) {
		this.diaEntero = diaEntero;
	}
	public Long getIdLicencia() {
		return idLicencia;
	}
	public void setIdLicencia(Long idLicencia) {
		this.idLicencia = idLicencia;
	}
	public String getJefeInmediato() {
		return jefeInmediato;
	}
	public void setJefeInmediato(String jefeInmediato) {
		this.jefeInmediato = jefeInmediato;
	}
	public String getNombreDiaEntero() {
		return nombreDiaEntero;
	}
	public void setNombreDiaEntero(String nombreDiaEntero) {
		this.nombreDiaEntero = nombreDiaEntero;
	}
	
	
	
	
	

}

