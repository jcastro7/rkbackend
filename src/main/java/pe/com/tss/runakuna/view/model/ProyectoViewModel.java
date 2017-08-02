package pe.com.tss.runakuna.view.model;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.tss.runakuna.json.JsonDateSimpleDeserializer;
import pe.com.tss.runakuna.json.JsonDateSimpleSerializer;

import pe.com.tss.runakuna.json.JsonFlagIntegerDeserializer;
import pe.com.tss.runakuna.json.JsonFlagIntegerSerializer;

public class ProyectoViewModel extends AuditingEntityViewModel{

	private Long idProyecto;
	private String nombre;
	private String estado;
	
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date fechaInicio;
	
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date fechaFin;
	
	private String codigo;
	private String descripcion;
	
	
	private Long idDepartamentoArea;
	private Long idJefeProyecto;
	private Long idJefeProyectoReemplazo;
	
	@JsonSerialize(using=JsonFlagIntegerSerializer.class)
	@JsonDeserialize(using=JsonFlagIntegerDeserializer.class)
	private Integer jefeNoDisponible;
	
	private String cliente;
	
	private Long idUnidadDeNegocio;
	
	private String nombreJefeProyecto;
	private String nombreJefeProyectoReemplazo;
	
	public Long getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(Long idProyecto) {
		this.idProyecto = idProyecto;
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
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Long getIdDepartamentoArea() {
		return idDepartamentoArea;
	}
	public void setIdDepartamentoArea(Long idDepartamentoArea) {
		this.idDepartamentoArea = idDepartamentoArea;
	}
	public Long getIdJefeProyecto() {
		return idJefeProyecto;
	}
	public void setIdJefeProyecto(Long idJefeProyecto) {
		this.idJefeProyecto = idJefeProyecto;
	}
	public Long getIdJefeProyectoReemplazo() {
		return idJefeProyectoReemplazo;
	}
	public void setIdJefeProyectoReemplazo(Long idJefeProyectoReemplazo) {
		this.idJefeProyectoReemplazo = idJefeProyectoReemplazo;
	}
	public Integer getJefeNoDisponible() {
		return jefeNoDisponible;
	}
	public void setJefeNoDisponible(Integer jefeNoDisponible) {
		this.jefeNoDisponible = jefeNoDisponible;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public Long getIdUnidadDeNegocio() {
		return idUnidadDeNegocio;
	}
	public void setIdUnidadDeNegocio(Long idUnidadDeNegocio) {
		this.idUnidadDeNegocio = idUnidadDeNegocio;
	}
	public String getNombreJefeProyecto() {
		return nombreJefeProyecto;
	}
	public void setNombreJefeProyecto(String nombreJefeProyecto) {
		this.nombreJefeProyecto = nombreJefeProyecto;
	}
	public String getNombreJefeProyectoReemplazo() {
		return nombreJefeProyectoReemplazo;
	}
	public void setNombreJefeProyectoReemplazo(String nombreJefeProyectoReemplazo) {
		this.nombreJefeProyectoReemplazo = nombreJefeProyectoReemplazo;
	}
	
	
	
	
}
