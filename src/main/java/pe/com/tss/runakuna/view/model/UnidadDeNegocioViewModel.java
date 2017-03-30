package pe.com.tss.runakuna.view.model;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.tss.runakuna.json.JsonFlagIntegerDeserializer;
import pe.com.tss.runakuna.json.JsonFlagIntegerSerializer;

public class UnidadDeNegocioViewModel extends AuditingEntityViewModel{
	
	private Long idUnidadDeNegocio;
	private Long idEmpresa;
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
	
	private List<DepartamentoAreaViewModel> departamentosArea;
	
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
	public Long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public List<DepartamentoAreaViewModel> getDepartamentosArea() {
		return departamentosArea;
	}
	public void setDepartamentosArea(List<DepartamentoAreaViewModel> departamentosArea) {
		this.departamentosArea = departamentosArea;
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
