package pe.com.empresa.rk.view.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.empresa.rk.json.JsonFlagIntegerDeserializer;
import pe.com.empresa.rk.json.JsonFlagIntegerSerializer;

public class DepartamentoAreaViewModel extends AuditingEntityViewModel {
	
	private Long idDepartamentoArea;
	private Long idUnidadDeNegocio;
	private String nombre;
	private String estado;
	
	private String nombreEstado;
	
	private Long idJefe;
	private Long idJefeReemplazo;
	
	@JsonSerialize(using=JsonFlagIntegerSerializer.class)
	@JsonDeserialize(using=JsonFlagIntegerDeserializer.class)
	private Integer jefeNoDisponible;
	
	private String jefe;
	private String jefeReemplazo;
	
	public Long getIdDepartamentoArea() {
		return idDepartamentoArea;
	}
	public void setIdDepartamentoArea(Long idDepartamentoArea) {
		this.idDepartamentoArea = idDepartamentoArea;
	}
	
	public Long getIdUnidadDeNegocio() {
		return idUnidadDeNegocio;
	}
	public void setIdUnidadDeNegocio(Long idUnidadDeNegocio) {
		this.idUnidadDeNegocio = idUnidadDeNegocio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getNombreEstado() {
		return nombreEstado;
	}
	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}
	public Long getIdJefe() {
		return idJefe;
	}
	public void setIdJefe(Long idJefe) {
		this.idJefe = idJefe;
	}
	public Long getIdJefeReemplazo() {
		return idJefeReemplazo;
	}
	public void setIdJefeReemplazo(Long idJefeReemplazo) {
		this.idJefeReemplazo = idJefeReemplazo;
	}
	public Integer getJefeNoDisponible() {
		return jefeNoDisponible;
	}
	public void setJefeNoDisponible(Integer jefeNoDisponible) {
		this.jefeNoDisponible = jefeNoDisponible;
	}
	public String getJefe() {
		return jefe;
	}
	public void setJefe(String jefe) {
		this.jefe = jefe;
	}
	public String getJefeReemplazo() {
		return jefeReemplazo;
	}
	public void setJefeReemplazo(String jefeReemplazo) {
		this.jefeReemplazo = jefeReemplazo;
	}
	
}
