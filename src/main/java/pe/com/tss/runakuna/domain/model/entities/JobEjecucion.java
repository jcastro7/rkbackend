package pe.com.tss.runakuna.domain.model.entities;

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

import pe.com.tss.runakuna.domain.model.base.AuditingEntity;

@Entity
@Table(name = "JobEjecucion")
@NamedQuery(name = "JobEjecucion.findAll", query = "SELECT e FROM JobEjecucion e")
public class JobEjecucion extends AuditingEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Long idJobEjecucion;
	private Job job;
	private Date fechaInicio;
	private Date fechaFin;
	private String estado;
	private String resultadoMensaje;
	private Integer ejecutado;
	private Date fechaProgramado;
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idJobEjecucion")
	public Long getIdJobEjecucion() {
		return idJobEjecucion;
	}
	public void setIdJobEjecucion(Long idJobEjecucion) {
		this.idJobEjecucion = idJobEjecucion;
	}
	
	@ManyToOne
    @JoinColumn(name = "IdJob")
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	
	
	@Column(name="Estado")
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	@Column(name="FechaInicio")
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	@Column(name="FechaFin")
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	@Column(name="ResultadoMensaje")
	public String getResultadoMensaje() {
		return resultadoMensaje;
	}
	public void setResultadoMensaje(String resultadoMensaje) {
		this.resultadoMensaje = resultadoMensaje;
	}
	@Column(name="Ejecutado")
	public Integer getEjecutado() {
		return ejecutado;
	}
	public void setEjecutado(Integer ejecutado) {
		this.ejecutado = ejecutado;
	}
	@Column(name="FechaProgramado")
	public Date getFechaProgramado() {
		return fechaProgramado;
	}
	public void setFechaProgramado(Date fechaProgramado) {
		this.fechaProgramado = fechaProgramado;
	}
	
	
	
	
	
	
}
