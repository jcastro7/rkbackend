package pe.com.tss.runakuna.view.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.tss.runakuna.json.JsonFlagIntegerDeserializer;
import pe.com.tss.runakuna.json.JsonFlagIntegerSerializer;

public class RolResultViewModel extends AuditingEntityViewModel{
	
	private Long idRol;
	private String nombre;
	private String descripcion;
	private String estado;
	
	@JsonSerialize(using=JsonFlagIntegerSerializer.class)
	@JsonDeserialize(using=JsonFlagIntegerDeserializer.class)
	private Integer rolSistema;
	private Integer porDefecto;
		
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
	public Integer getPorDefecto() {
		return porDefecto;
	}
	public void setPorDefecto(Integer porDefecto) {
		this.porDefecto = porDefecto;
	}
	
	

}
