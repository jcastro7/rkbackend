package pe.com.tss.runakuna.view.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.tss.runakuna.json.JsonFlagIntegerDeserializer;
import pe.com.tss.runakuna.json.JsonFlagIntegerSerializer;

public class TipoLicenciaViewModel extends AuditingEntityViewModel{
	
	private Long idTipoLicencia;
	private String codigo;
	private String nombre;
	
	private Integer limiteMensual;
	
	private Integer limiteAnual;
	private String estado;
	
	@JsonSerialize(using=JsonFlagIntegerSerializer.class)
	@JsonDeserialize(using=JsonFlagIntegerDeserializer.class)
	private Integer activaParaESS;
	
	public Long getIdTipoLicencia() {
		return idTipoLicencia;
	}
	public void setIdTipoLicencia(Long idTipoLicencia) {
		this.idTipoLicencia = idTipoLicencia;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getLimiteMensual() {
		return limiteMensual;
	}
	public void setLimiteMensual(Integer limiteMensual) {
		this.limiteMensual = limiteMensual;
	}
	public Integer getLimiteAnual() {
		return limiteAnual;
	}
	public void setLimiteAnual(Integer limiteAnual) {
		this.limiteAnual = limiteAnual;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Integer getActivaParaESS() {
		return activaParaESS;
	}
	public void setActivaParaESS(Integer activaParaESS) {
		this.activaParaESS = activaParaESS;
	}
	
	
	
}
