package pe.com.tss.runakuna.domain.model.entities;

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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.tss.runakuna.domain.model.base.AuditingEntity;
import pe.com.tss.runakuna.json.JsonDateSimpleDeserializer;
import pe.com.tss.runakuna.json.JsonDateSimpleSerializer;

@Entity
@Table(name = "Contrato")
@NamedQuery(name = "Contrato.findAll", query = "SELECT c FROM Contrato c")
public class Contrato extends AuditingEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long idContrato;
	private Date fechaInicio;
	private Date fechaFin;
	private String duracion;
	private String cargo;
	private BigDecimal salario;
	private String direccion;
	private String tipoDocumento;
	private String numeroDocumento;
	private String estado;
	private String tipoContrato;
	private Empleado empleado;
	
	private Moneda moneda;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdContrato")
	public Long getIdContrato() {
		return idContrato;
	}
	
	public void setIdContrato(Long idContrato) {
		this.idContrato = idContrato;
	}
	
	@Column(name = "FechaInicio")
	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	@Column(name = "FechaFin")
	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Column(name = "Duracion")
	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	@Column(name = "Cargo")
	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	@Column(name = "Salario")
	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	@Column(name = "Direccion")
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Column(name = "TipoDocumento")
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	@Column(name = "NumeroDocumento")
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	@Column(name = "Estado")
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@ManyToOne()
    @JoinColumn(name = "IdEmpleado")
	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	
	@ManyToOne()
    @JoinColumn(name = "IdMoneda")
	public Moneda getMoneda() {
		return moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}
	@Column(name = "TipoContrato")
	public String getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}
		
}
