package pe.com.tss.runakuna.view.model;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.tss.runakuna.json.JsonDateSimpleDeserializer;
import pe.com.tss.runakuna.json.JsonDateSimpleSerializer;

/**
 * Created by josediaz on 14/12/2016.
 */
public class BandaSalarialViewModel extends AuditingEntityViewModel {

	private Long idBandaSalarial;
	private Long idCargo;
	private Long idMoneda;
	private BigDecimal limiteSuperior;
	private BigDecimal limiteMedio;
	private BigDecimal limiteInferior;
	private String nombreMoneda;
	
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date inicioVigencia;
	
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date finVigencia;
	
	public Long getIdBandaSalarial() {
		return idBandaSalarial;
	}
	public void setIdBandaSalarial(Long idBandaSalarial) {
		this.idBandaSalarial = idBandaSalarial;
	}
	public Long getIdCargo() {
		return idCargo;
	}
	public void setIdCargo(Long idCargo) {
		this.idCargo = idCargo;
	}
	public Long getIdMoneda() {
		return idMoneda;
	}
	public void setIdMoneda(Long idMoneda) {
		this.idMoneda = idMoneda;
	}
	public BigDecimal getLimiteSuperior() {
		return limiteSuperior;
	}
	public void setLimiteSuperior(BigDecimal limiteSuperior) {
		this.limiteSuperior = limiteSuperior;
	}
	public BigDecimal getLimiteMedio() {
		return limiteMedio;
	}
	public void setLimiteMedio(BigDecimal limiteMedio) {
		this.limiteMedio = limiteMedio;
	}
	public BigDecimal getLimiteInferior() {
		return limiteInferior;
	}
	public void setLimiteInferior(BigDecimal limiteInferior) {
		this.limiteInferior = limiteInferior;
	}
	public Date getInicioVigencia() {
		return inicioVigencia;
	}
	public void setInicioVigencia(Date inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}
	public Date getFinVigencia() {
		return finVigencia;
	}
	public void setFinVigencia(Date finVigencia) {
		this.finVigencia = finVigencia;
	}
	public String getNombreMoneda() {
		return nombreMoneda;
	}
	public void setNombreMoneda(String nombreMoneda) {
		this.nombreMoneda = nombreMoneda;
	}
	
    
}
