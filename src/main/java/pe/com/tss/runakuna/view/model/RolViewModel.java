package pe.com.tss.runakuna.view.model;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.tss.runakuna.json.JsonFlagIntegerDeserializer;
import pe.com.tss.runakuna.json.JsonFlagIntegerSerializer;

public class RolViewModel extends AuditingEntityViewModel{
	
	private Long idRol;
	private String nombre;
	private String descripcion;
	@JsonSerialize(using=JsonFlagIntegerSerializer.class)
	@JsonDeserialize(using=JsonFlagIntegerDeserializer.class)
	private Integer rolSistema;
	
	@JsonSerialize(using=JsonFlagIntegerSerializer.class)
	@JsonDeserialize(using=JsonFlagIntegerDeserializer.class)
	private Integer editable;
	
	private String estado;
	private List<ModuloViewModel> modulo;
	private Long idEmpresa;
	
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
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<ModuloViewModel> getModulo() {
		return modulo;
	}
	public void setModulo(List<ModuloViewModel> modulo) {
		this.modulo = modulo;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Integer getRolSistema() {
		return rolSistema;
	}
	public void setRolSistema(Integer rolSistema) {
		this.rolSistema = rolSistema;
	}
	public Integer getEditable() {
		return editable;
	}
	public void setEditable(Integer editable) {
		this.editable = editable;
	}
	public Long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	
	
	
}
