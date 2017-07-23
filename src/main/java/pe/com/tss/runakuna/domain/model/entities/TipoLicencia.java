package pe.com.tss.runakuna.domain.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.tss.runakuna.domain.model.base.AuditingEntity;
import pe.com.tss.runakuna.json.JsonFlagIntegerDeserializer;
import pe.com.tss.runakuna.json.JsonFlagIntegerSerializer;

@Entity
@Table(name = "TipoLicencia")
@NamedQuery(name = "TipoLicencia.findAll", query = "SELECT l FROM TipoLicencia l")
public class TipoLicencia extends AuditingEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idTipoLicencia;
	private String codigo;
	private String nombre;
	private Integer limiteMensual;
	private Integer limiteAnual;
	private Integer activaParaESS;
	private String estado;
	
	private Integer visibleEmpleado;
	private Integer considerarLicenciaAlEnviar;
	private Integer eliminable;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTipoLicencia")
	public Long getIdTipoLicencia() {
		return idTipoLicencia;
	}

	public void setIdTipoLicencia(Long idTipoLicencia) {
		this.idTipoLicencia = idTipoLicencia;
	}
	
	@Column(name = "ActivaParaESS")
	public Integer getActivaParaESS() {
		return activaParaESS;
	}

	public void setActivaParaESS(Integer activaParaESS) {
		this.activaParaESS = activaParaESS;
	}
	
	@Column(name = "Codigo")
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Column(name = "Nombre")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "LimiteMensual")
	public Integer getLimiteMensual() {
		return limiteMensual;
	}

	public void setLimiteMensual(Integer limiteMensual) {
		this.limiteMensual = limiteMensual;
	}

	@Column(name = "LimiteAnual")
	public Integer getLimiteAnual() {
		return limiteAnual;
	}

	public void setLimiteAnual(Integer limiteAnual) {
		this.limiteAnual = limiteAnual;
	}

	@Column(name = "Estado")
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Column(name = "VisibleEmpleado")
	public Integer getVisibleEmpleado() {
		return visibleEmpleado;
	}

	public void setVisibleEmpleado(Integer visibleEmpleado) {
		this.visibleEmpleado = visibleEmpleado;
	}

	@Column(name = "ConsiderarLicenciaAlEnviar")
	public Integer getConsiderarLicenciaAlEnviar() {
		return considerarLicenciaAlEnviar;
	}

	public void setConsiderarLicenciaAlEnviar(Integer considerarLicenciaAlEnviar) {
		this.considerarLicenciaAlEnviar = considerarLicenciaAlEnviar;
	}

	@Column(name = "Eliminable")
	public Integer getEliminable() {
		return eliminable;
	}

	public void setEliminable(Integer eliminable) {
		this.eliminable = eliminable;
	}
	
}
