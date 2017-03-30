package pe.com.empresa.rk.view.model;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.empresa.rk.json.JsonFlagIntegerDeserializer;
import pe.com.empresa.rk.json.JsonFlagIntegerSerializer;

public class AccionViewModel extends AuditingEntityViewModel {
	
	private static final long serialVersionUID = 1L;
	
	private Long idAccion;
	private Long idModulo;
	private String nombre;
	private String etiqueta;
	private String tipoAccion;
	private Integer autorizacionDefecto;
	private String autorizacionDefectoString;
	private Integer editable;
	private String editableString;
	private Long idRol;
	private Long idAutorizacion;
	private Long rowVersionAutorizacion;
	private String actualizadorAutorizacion;
	private Date fechaActualizacionAutorizacion;
	private Date fechaCreacionAutorizacion;
	private String creadorAutorizacion;
	@JsonSerialize(using=JsonFlagIntegerSerializer.class)
	@JsonDeserialize(using=JsonFlagIntegerDeserializer.class)
	private Integer autorizado;
	
	public Long getIdAccion() {
		return idAccion;
	}
	public void setIdAccion(Long idAccion) {
		this.idAccion = idAccion;
	}
	public Long getIdModulo() {
		return idModulo;
	}
	public void setIdModulo(Long idModulo) {
		this.idModulo = idModulo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEtiqueta() {
		return etiqueta;
	}
	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}
	public String getTipoAccion() {
		return tipoAccion;
	}
	public void setTipoAccion(String tipoAccion) {
		this.tipoAccion = tipoAccion;
	}
	public Integer getAutorizacionDefecto() {
		return autorizacionDefecto;
	}
	public void setAutorizacionDefecto(Integer autorizacionDefecto) {
		this.autorizacionDefecto = autorizacionDefecto;
	}
	public Integer getEditable() {
		return editable;
	}
	public void setEditable(Integer editable) {
		this.editable = editable;
	}
	public String getAutorizacionDefectoString() {
		return autorizacionDefectoString;
	}
	public void setAutorizacionDefectoString(String autorizacionDefectoString) {
		this.autorizacionDefectoString = autorizacionDefectoString;
	}
	public String getEditableString() {
		return editableString;
	}
	public void setEditableString(String editableString) {
		this.editableString = editableString;
	}
	public Integer getAutorizado() {
		return autorizado;
	}
	public void setAutorizado(Integer autorizado) {
		this.autorizado = autorizado;
	}
	public Long getIdAutorizacion() {
		return idAutorizacion;
	}
	public void setIdAutorizacion(Long idAutorizacion) {
		this.idAutorizacion = idAutorizacion;
	}
	public Long getIdRol() {
		return idRol;
	}
	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}
	public Long getRowVersionAutorizacion() {
		return rowVersionAutorizacion;
	}
	public void setRowVersionAutorizacion(Long rowVersionAutorizacion) {
		this.rowVersionAutorizacion = rowVersionAutorizacion;
	}
	public String getActualizadorAutorizacion() {
		return actualizadorAutorizacion;
	}
	public void setActualizadorAutorizacion(String actualizadorAutorizacion) {
		this.actualizadorAutorizacion = actualizadorAutorizacion;
	}
	public Date getFechaActualizacionAutorizacion() {
		return fechaActualizacionAutorizacion;
	}
	public void setFechaActualizacionAutorizacion(Date fechaActualizacionAutorizacion) {
		this.fechaActualizacionAutorizacion = fechaActualizacionAutorizacion;
	}
	public Date getFechaCreacionAutorizacion() {
		return fechaCreacionAutorizacion;
	}
	public void setFechaCreacionAutorizacion(Date fechaCreacionAutorizacion) {
		this.fechaCreacionAutorizacion = fechaCreacionAutorizacion;
	}
	public String getCreadorAutorizacion() {
		return creadorAutorizacion;
	}
	public void setCreadorAutorizacion(String creadorAutorizacion) {
		this.creadorAutorizacion = creadorAutorizacion;
	}


	
	
}
