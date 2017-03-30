package pe.com.empresa.rk.view.model;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.empresa.rk.json.JsonDateSimpleDeserializer;
import pe.com.empresa.rk.json.JsonDateSimpleSerializer;

public class ProyectoFilterViewModel extends FilterViewModel {

	private Long idProyecto;
	private String nombre;
	private String nombreDepartamento;
	private String nombreUnidadDeNegocio;
	private String estado;
	
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date fechaInicio;
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date fechaInicioDesde;
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date fechaInicioHasta;
	
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date fechaFin;
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date fechaFinDesde;
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date fechaFinHasta;
	
	private String codigo;
	private String descripcion;
	private String nombreJefeProyecto;
	private String nombreJefeProyectoReemplazo;
	
	private Long idUnidadDeNegocio;
	private Long idDepartamentoArea;
	private Long idJefeProyecto;
	private Long idJefeProyectoReemplazo;
	private Integer jefeNoDisponible;
	private String cliente;
	
	
	
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
	public String getNombreDepartamento() {
		return nombreDepartamento;
	}
	public void setNombreDepartamento(String nombreDepartamento) {
		this.nombreDepartamento = nombreDepartamento;
	}
	public String getNombreUnidadDeNegocio() {
		return nombreUnidadDeNegocio;
	}
	public void setNombreUnidadDeNegocio(String nombreUnidadDeNegocio) {
		this.nombreUnidadDeNegocio = nombreUnidadDeNegocio;
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
	public Long getIdUnidadDeNegocio() {
		return idUnidadDeNegocio;
	}
	public void setIdUnidadDeNegocio(Long idUnidadDeNegocio) {
		this.idUnidadDeNegocio = idUnidadDeNegocio;
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
	public Date getFechaInicioDesde() {
		return fechaInicioDesde;
	}
	public void setFechaInicioDesde(Date fechaInicioDesde) {
		this.fechaInicioDesde = fechaInicioDesde;
	}
	public Date getFechaInicioHasta() {
		return fechaInicioHasta;
	}
	public void setFechaInicioHasta(Date fechaInicioHasta) {
		this.fechaInicioHasta = fechaInicioHasta;
	}
	public Date getFechaFinDesde() {
		return fechaFinDesde;
	}
	public void setFechaFinDesde(Date fechaFinDesde) {
		this.fechaFinDesde = fechaFinDesde;
	}
	public Date getFechaFinHasta() {
		return fechaFinHasta;
	}
	public void setFechaFinHasta(Date fechaFinHasta) {
		this.fechaFinHasta = fechaFinHasta;
	}
	
	
}
