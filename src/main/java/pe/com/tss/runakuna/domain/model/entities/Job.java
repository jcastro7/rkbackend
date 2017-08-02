package pe.com.tss.runakuna.domain.model.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import pe.com.tss.runakuna.domain.model.base.AuditingEntity;
import pe.com.tss.runakuna.view.model.AuditingEntityViewModel;

@Entity
@Table(name = "Job")
@NamedQuery(name = "Job.findAll", query = "SELECT e FROM Job e")
public class Job extends AuditingEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Long idJob;
	
	private String codigo;
	private String descripcion;
	private Date ultimaEjecucion;
	private String estado;
	private String nombreClase;
    private String metodo;
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idJob")
	public Long getIdJob() {
		return idJob;
	}
	public void setIdJob(Long idJob) {
		this.idJob = idJob;
	}
	
		
	@Column(name="Codigo")
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	@Column(name="Descripcion")
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Column(name="UltimaEjecucion")
	public Date getUltimaEjecucion() {
		return ultimaEjecucion;
	}
	public void setUltimaEjecucion(Date ultimaEjecucion) {
		this.ultimaEjecucion = ultimaEjecucion;
	}
	
	@Column(name="Estado")
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	@Column(name="NombreClase")
	public String getNombreClase() {
		return nombreClase;
	}
	public void setNombreClase(String nombreClase) {
		this.nombreClase = nombreClase;
	}
	@Column(name="Metodo")
	public String getMetodo() {
		return metodo;
	}
	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}
	
	
	
	
	
	
}
