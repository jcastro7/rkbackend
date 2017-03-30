package pe.com.empresa.rk.view.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.empresa.rk.json.JsonFlagIntegerDeserializer;
import pe.com.empresa.rk.json.JsonFlagIntegerSerializer;

public class RolResultViewModel extends AuditingEntityViewModel{
	
	private Long idRol;
	private String nombre;
	private String descripcion;
	private String estado;
	
	@JsonSerialize(using=JsonFlagIntegerSerializer.class)
	@JsonDeserialize(using=JsonFlagIntegerDeserializer.class)
	private Integer rolSistema;
		
	public Long getIdRol() {
		return idRol;
	}
	public void setIdRol(Long idRol) {
		this.idRol = idRol;
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getRolSistema() {
		return rolSistema;
	}
	public void setRolSistema(Integer rolSistema) {
		this.rolSistema = rolSistema;
	}
	
	

}
