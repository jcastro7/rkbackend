package pe.com.empresa.rk.view.model;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.empresa.rk.json.JsonFlagIntegerDeserializer;
import pe.com.empresa.rk.json.JsonFlagIntegerSerializer;

public class RolViewModel extends AuditingEntityViewModel{
	
	private Long idRol;
	private String nombre;
	private String descripcion;
	@JsonSerialize(using=JsonFlagIntegerSerializer.class)
	@JsonDeserialize(using=JsonFlagIntegerDeserializer.class)
	private Integer rolSistema;
	private String estado;
	private List<ModuloViewModel> modulo;
//	private Long idModulo;
//	private String nombreModulo;
//	private List<AccionViewModel> accion;
	
//	public String getNombreModulo() {
//		return nombreModulo;
//	}
//	public void setNombreModulo(String nombreModulo) {
//		this.nombreModulo = nombreModulo;
//	}
//	
//	public Long getIdModulo() {
//		return idModulo;
//	}
//	public void setIdModulo(Long idModulo) {
//		this.idModulo = idModulo;
//	}
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
	
//	public List<AccionViewModel> getAccion() {
//		return accion;
//	}
//	public void setAccion(List<AccionViewModel> accion) {
//		this.accion = accion;
//	}
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
	
	
	
	

}
