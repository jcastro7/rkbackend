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

import pe.com.tss.runakuna.domain.model.base.AuditingEntity;

@Entity
@Table(name = "PermisoEmpleadoRecuperacion")
@NamedQuery(name = "PermisoEmpleadoRecuperacion.findAll", query = "SELECT e FROM PermisoEmpleadoRecuperacion e")
public class PermisoEmpleadoRecuperacion extends AuditingEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long idPermisoEmpleadoRecuperacion;
	private PermisoEmpleado permisoEmpleado;
	
	private String horaInicio;
	private String horaFin;
	private BigDecimal horas;
    
    private Date fechaRecuperacion;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPermisoEmpleadoRecuperacion")
	public Long getIdPermisoEmpleadoRecuperacion() {
		return idPermisoEmpleadoRecuperacion;
	}

	public void setIdPermisoEmpleadoRecuperacion(Long idPermisoEmpleadoRecuperacion) {
		this.idPermisoEmpleadoRecuperacion = idPermisoEmpleadoRecuperacion;
	}	

	@ManyToOne()
    @JoinColumn(name = "IdPermisoEmpleado")
	public PermisoEmpleado getPermisoEmpleado() {
		return permisoEmpleado;
	}

	public void setPermisoEmpleado(PermisoEmpleado permisoEmpleado) {
		this.permisoEmpleado = permisoEmpleado;
	}

	@Column(name = "HoraInicio")
	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	@Column(name = "HoraFin")
	public String getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

	@Column(name = "Horas")
	public BigDecimal getHoras() {
		return horas;
	}

	public void setHoras(BigDecimal horas) {
		this.horas = horas;
	}

	@Column(name = "FechaRecuperacion")
	public Date getFechaRecuperacion() {
		return fechaRecuperacion;
	}

	public void setFechaRecuperacion(Date fechaRecuperacion) {
		this.fechaRecuperacion = fechaRecuperacion;
	}
    
    
    

}
