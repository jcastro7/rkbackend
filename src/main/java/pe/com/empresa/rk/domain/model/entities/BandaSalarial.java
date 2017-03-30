package pe.com.empresa.rk.domain.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import pe.com.empresa.rk.domain.model.base.AuditingEntity;

@Entity
@Table(name = "BandaSalarial")
@NamedQuery(name = "BandaSalarial.findAll", query = "SELECT e FROM BandaSalarial e")
public class BandaSalarial extends AuditingEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long idBandaSalarial;
	private Cargo cargo;
	private Moneda moneda;
	private BigDecimal limiteSuperior;
	private BigDecimal limiteMedio;
	private BigDecimal limiteInferior;
	private Date inicioVigencia;
	private Date finVigencia;
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdBandaSalarial")
	public Long getIdBandaSalarial() {
		return idBandaSalarial;
	}
	public void setIdBandaSalarial(Long idBandaSalarial) {
		this.idBandaSalarial = idBandaSalarial;
	}
	
	@ManyToOne()
    @JoinColumn(name = "IdCargo")
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
	@ManyToOne()
    @JoinColumn(name = "IdMoneda")
	public Moneda getMoneda() {
		return moneda;
	}
	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}
	
	@Column(name= "LimiteSuperior")
	public BigDecimal getLimiteSuperior() {
		return limiteSuperior;
	}
	public void setLimiteSuperior(BigDecimal limiteSuperior) {
		this.limiteSuperior = limiteSuperior;
	}
	
	@Column(name= "LimiteMedio")
	public BigDecimal getLimiteMedio() {
		return limiteMedio;
	}
	public void setLimiteMedio(BigDecimal limiteMedio) {
		this.limiteMedio = limiteMedio;
	}
	
	@Column(name= "LimiteInferior")
	public BigDecimal getLimiteInferior() {
		return limiteInferior;
	}
	public void setLimiteInferior(BigDecimal limiteInferior) {
		this.limiteInferior = limiteInferior;
	}
	
	@Column(name= "InicioVigencia")
	public Date getInicioVigencia() {
		return inicioVigencia;
	}
	public void setInicioVigencia(Date inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}
	
	@Column(name= "FinVigencia")
	public Date getFinVigencia() {
		return finVigencia;
	}
	public void setFinVigencia(Date finVigencia) {
		this.finVigencia = finVigencia;
	}
	
	
	
		      

}
