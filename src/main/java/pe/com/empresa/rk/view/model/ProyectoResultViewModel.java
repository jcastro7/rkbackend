package pe.com.empresa.rk.view.model;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.empresa.rk.json.JsonDateSimpleDeserializer;
import pe.com.empresa.rk.json.JsonDateSimpleSerializer;

public class ProyectoResultViewModel extends ResultViewModel{

	private String nombre;
	private Long idProyecto;
	private String codigo;
	private String estado;
	private String descripcion;
	
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date fechaInicio;
	
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date fechaFin;
	
	private String nombreUnidadDeNegocio;
	private String nombreDepartamentoArea;
	private String nombreJefeProyecto;
	
	private Integer empleados;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(Long idProyecto) {
		this.idProyecto = idProyecto;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	public String getNombreUnidadDeNegocio() {
		return nombreUnidadDeNegocio;
	}

	public void setNombreUnidadDeNegocio(String nombreUnidadDeNegocio) {
		this.nombreUnidadDeNegocio = nombreUnidadDeNegocio;
	}

	public String getNombreDepartamentoArea() {
		return nombreDepartamentoArea;
	}

	public void setNombreDepartamentoArea(String nombreDepartamentoArea) {
		this.nombreDepartamentoArea = nombreDepartamentoArea;
	}

	public String getNombreJefeProyecto() {
		return nombreJefeProyecto;
	}

	public void setNombreJefeProyecto(String nombreJefeProyecto) {
		this.nombreJefeProyecto = nombreJefeProyecto;
	}

	public Integer getEmpleados() {
		return empleados;
	}

	public void setEmpleados(Integer empleados) {
		this.empleados = empleados;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
	
	
	
	
	
}
