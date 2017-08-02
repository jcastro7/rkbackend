package pe.com.tss.runakuna.view.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.tss.runakuna.json.JsonFlagIntegerDeserializer;
import pe.com.tss.runakuna.json.JsonFlagIntegerSerializer;

public class UsuarioRolViewModel extends AuditingEntityViewModel{

	private Long idUsuarioRol;
	private Long idUsuario;
	private Long idRol;
	private String nombre;
	private String descripcion;
	
	@JsonSerialize(using=JsonFlagIntegerSerializer.class)
	@JsonDeserialize(using=JsonFlagIntegerDeserializer.class)
	private Integer porDefecto;
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Long getIdUsuarioRol() {
		return idUsuarioRol;
	}
	public void setIdUsuarioRol(Long idUsuarioRol) {
		this.idUsuarioRol = idUsuarioRol;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
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
	public Integer getPorDefecto() {
		return porDefecto;
	}
	public void setPorDefecto(Integer porDefecto) {
		this.porDefecto = porDefecto;
	}
	
	
	
}
