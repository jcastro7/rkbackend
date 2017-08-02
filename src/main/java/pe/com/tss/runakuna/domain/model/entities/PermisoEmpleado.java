package pe.com.tss.runakuna.domain.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

@Entity
@Table(name = "PermisoEmpleado")
@NamedQuery(name = "PermisoEmpleado.findAll", query = "SELECT e FROM PermisoEmpleado e")
public class PermisoEmpleado extends AuditingEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idPermisoEmpleado;
	private String motivo;
	private String razon;
	private Date fecha;
	
	private String horaInicio;
	private String horaFin;
	private String estado;
	private BigDecimal horas;
    
    private PeriodoEmpleado periodoEmpleado;
    
	private Empleado atendidoPor;
	
	private String comentarioResolucion;
	private Integer diaEntero;
	
	private List<PermisoEmpleadoRecuperacion> permisoEmpleadoRecuperacion;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPermisoEmpleado")
	public Long getIdPermisoEmpleado() {
		return idPermisoEmpleado;
	}

	public void setIdPermisoEmpleado(Long idPermisoEmpleado) {
		this.idPermisoEmpleado = idPermisoEmpleado;
	}
	
	@Column(name = "Motivo")
	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	@Column(name = "Razon")
	public String getRazon() {
		return razon;
	}

	public void setRazon(String razon) {
		this.razon = razon;
	}

	@Column(name = "Fecha")
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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

	@Column(name = "Estado")
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Column(name = "Horas")
	public BigDecimal getHoras() {
		return horas;
	}

	public void setHoras(BigDecimal horas) {
		this.horas = horas;
	}

	@ManyToOne()
    @JoinColumn(name = "IdPeriodoEmpleado")
	public PeriodoEmpleado getPeriodoEmpleado() {
		return periodoEmpleado;
	}

	public void setPeriodoEmpleado(PeriodoEmpleado periodoEmpleado) {
		this.periodoEmpleado = periodoEmpleado;
	}

	@ManyToOne()
    @JoinColumn(name = "IdAtendidoPor")
	public Empleado getAtendidoPor() {
		return atendidoPor;
	}

	public void setAtendidoPor(Empleado atendidoPor) {
		this.atendidoPor = atendidoPor;
	}
	@Column(name = "ComentarioResolucion")
	public String getComentarioResolucion() {
		return comentarioResolucion;
	}

	public void setComentarioResolucion(String comentarioResolucion) {
		this.comentarioResolucion = comentarioResolucion;
	}

	@Column(name = "DiaEntero")
	public Integer getDiaEntero() {
		return diaEntero;
	}

	public void setDiaEntero(Integer diaEntero) {
		this.diaEntero = diaEntero;
	}

	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},orphanRemoval=true, mappedBy = "permisoEmpleado")
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.DELETE,
			org.hibernate.annotations.CascadeType.MERGE,
			org.hibernate.annotations.CascadeType.PERSIST
	})
	public List<PermisoEmpleadoRecuperacion> getPermisoEmpleadoRecuperacion() {
		return permisoEmpleadoRecuperacion;
	}

	public void setPermisoEmpleadoRecuperacion(List<PermisoEmpleadoRecuperacion> permisoEmpleadoRecuperacion) {
		this.permisoEmpleadoRecuperacion = permisoEmpleadoRecuperacion;
	}
	
	
	
	
}