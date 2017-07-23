package pe.com.tss.runakuna.view.model;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.tss.runakuna.json.JsonDateSimpleDeserializer;
import pe.com.tss.runakuna.json.JsonDateSimpleSerializer;
import pe.com.tss.runakuna.json.JsonFlagIntegerDeserializer;
import pe.com.tss.runakuna.json.JsonFlagIntegerSerializer;

public class VacacionResultViewModel extends ResultViewModel {

	private Long idVacacion;
	private String estado;
	private String nombreEmpleado;
	
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date fechaInicio;
	
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date fechaFin;
	
	private Integer diasCalendarios;
	private Integer diasHabiles;
	private String nombreJefeInmediato;
	
	
	private String codigoPermiso;
	private Long idAtendidoPor;
	
	private String nombreIncluidoPlanilla;
	private String periodo;
	
	private Date desde;
	private Date hasta;
	
	private String tipo;
	
	@JsonSerialize(using=JsonFlagIntegerSerializer.class)
	@JsonDeserialize(using=JsonFlagIntegerDeserializer.class)
	private Integer incluidoPlanilla;
	
	private String mesPlanilla;
	
	public Long getIdVacacion() {
		return idVacacion;
	}
	public void setIdVacacion(Long idVacacion) {
		this.idVacacion = idVacacion;
	}
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}
	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
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
	public Integer getDiasCalendarios() {
		return diasCalendarios;
	}
	public void setDiasCalendarios(Integer diasCalendarios) {
		this.diasCalendarios = diasCalendarios;
	}
	public Integer getDiasHabiles() {
		return diasHabiles;
	}
	public void setDiasHabiles(Integer diasHabiles) {
		this.diasHabiles = diasHabiles;
	}
	public String getNombreJefeInmediato() {
		return nombreJefeInmediato;
	}
	public void setNombreJefeInmediato(String nombreJefeInmediato) {
		this.nombreJefeInmediato = nombreJefeInmediato;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCodigoPermiso() {
		return codigoPermiso;
	}
	public void setCodigoPermiso(String codigoPermiso) {
		this.codigoPermiso = codigoPermiso;
	}
	public Long getIdAtendidoPor() {
		return idAtendidoPor;
	}
	public void setIdAtendidoPor(Long idAtendidoPor) {
		this.idAtendidoPor = idAtendidoPor;
	}
	public String getNombreIncluidoPlanilla() {
		return nombreIncluidoPlanilla;
	}
	public void setNombreIncluidoPlanilla(String nombreIncluidoPlanilla) {
		this.nombreIncluidoPlanilla = nombreIncluidoPlanilla;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public Date getDesde() {
		return desde;
	}
	public void setDesde(Date desde) {
		this.desde = desde;
	}
	public Date getHasta() {
		return hasta;
	}
	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}

	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Integer getIncluidoPlanilla() {
		return incluidoPlanilla;
	}
	public void setIncluidoPlanilla(Integer incluidoPlanilla) {
		this.incluidoPlanilla = incluidoPlanilla;
	}
	public String getMesPlanilla() {
		return mesPlanilla;
	}
	public void setMesPlanilla(String mesPlanilla) {
		this.mesPlanilla = mesPlanilla;
	}
	
}
