package pe.com.tss.runakuna.view.model;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.tss.runakuna.json.JsonDateSimpleDeserializer;
import pe.com.tss.runakuna.json.JsonDateSimpleSerializer;
import pe.com.tss.runakuna.json.JsonFlagIntegerDeserializer;
import pe.com.tss.runakuna.json.JsonFlagIntegerSerializer;

public class ContratoFilterViewModel {

	private Long idContrato;
	private Long idEmpleado;
	
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date fechaInicio;
	
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date fechaFin;
		
	private String estado;
	private String tipoContrato;
	
//	@JsonSerialize(using=JsonFlagIntegerSerializer.class)
//	@JsonDeserialize(using=JsonFlagIntegerDeserializer.class)
	private boolean soloVigente;
	
//	@JsonSerialize(using=JsonFlagIntegerSerializer.class)
//	@JsonDeserialize(using=JsonFlagIntegerDeserializer.class)
	private boolean soloPorVencer;

	public Long getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(Long idContrato) {
		this.idContrato = idContrato;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Long getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
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

	public String getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	public boolean isSoloVigente() {
		return soloVigente;
	}

	public void setSoloVigente(boolean soloVigente) {
		this.soloVigente = soloVigente;
	}

	public boolean isSoloPorVencer() {
		return soloPorVencer;
	}

	public void setSoloPorVencer(boolean soloPorVencer) {
		this.soloPorVencer = soloPorVencer;
	}
	
	
	
}
