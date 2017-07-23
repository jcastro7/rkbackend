package pe.com.tss.runakuna.domain.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name = "EmpleadoCompensacion")
@NamedQuery(name = "EmpleadoCompensacion.findAll", query = "SELECT o FROM EmpleadoCompensacion o")
public class EmpleadoCompensacion extends AuditingEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long idEmpleadoCompensacion;
	private Empleado empleado;
	
	private BigDecimal horasTardanzaIngreso;
	private BigDecimal horasTardanzaSalida;
	private BigDecimal horasDemoraAlmuerzo;
	private BigDecimal horasTrabajadas;
	
	private BigDecimal horasPendientesTotal;
	private BigDecimal horasPendientesMesActual;
	private BigDecimal horasPendientesHastaMesAnterior;
	private String tardanzas;
	private BigDecimal horarioHorasTrabajo;
	private Integer vacaciones;
	private Integer licencias;
	private Integer inasistencias;
	
	private String mes;
	
	public EmpleadoCompensacion() { }

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdEmpleadoCompensacion")
	public Long getIdEmpleadoCompensacion() {
		return idEmpleadoCompensacion;
	}

	public void setIdEmpleadoCompensacion(Long idEmpleadoCompensacion) {
		this.idEmpleadoCompensacion = idEmpleadoCompensacion;
	}

	@ManyToOne
    @JoinColumn(name = "IdEmpleado")
	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	
	@Column(name = "HorasPendientesTotal")
	public BigDecimal getHorasPendientesTotal() {
		return horasPendientesTotal;
	}

	public void setHorasPendientesTotal(BigDecimal horasPendientesTotal) {
		this.horasPendientesTotal = horasPendientesTotal;
	}

	@Column(name = "HorasPendientesMesActual")
	public BigDecimal getHorasPendientesMesActual() {
		return horasPendientesMesActual;
	}

	public void setHorasPendientesMesActual(BigDecimal horasPendientesMesActual) {
		this.horasPendientesMesActual = horasPendientesMesActual;
	}

	@Column(name = "HorasPendientesHastaMesAnterior")
	public BigDecimal getHorasPendientesHastaMesAnterior() {
		return horasPendientesHastaMesAnterior;
	}

	public void setHorasPendientesHastaMesAnterior(BigDecimal horasPendientesHastaMesAnterior) {
		this.horasPendientesHastaMesAnterior = horasPendientesHastaMesAnterior;
	}

	@Column(name = "HorasTardanzaIngreso")
	public BigDecimal getHorasTardanzaIngreso() {
		return horasTardanzaIngreso;
	}

	public void setHorasTardanzaIngreso(BigDecimal horasTardanzaIngreso) {
		this.horasTardanzaIngreso = horasTardanzaIngreso;
	}

	@Column(name = "HorasTardanzaSalida")
	public BigDecimal getHorasTardanzaSalida() {
		return horasTardanzaSalida;
	}

	public void setHorasTardanzaSalida(BigDecimal horasTardanzaSalida) {
		this.horasTardanzaSalida = horasTardanzaSalida;
	}

	@Column(name = "HorasDemoraAlmuerzo")
	public BigDecimal getHorasDemoraAlmuerzo() {
		return horasDemoraAlmuerzo;
	}

	public void setHorasDemoraAlmuerzo(BigDecimal horasDemoraAlmuerzo) {
		this.horasDemoraAlmuerzo = horasDemoraAlmuerzo;
	}

	@Column(name = "HorasTrabajadas")
	public BigDecimal getHorasTrabajadas() {
		return horasTrabajadas;
	}

	public void setHorasTrabajadas(BigDecimal horasTrabajadas) {
		this.horasTrabajadas = horasTrabajadas;
	}

	@Column(name = "Tardanzas")
	public String getTardanzas() {
		return tardanzas;
	}

	public void setTardanzas(String tardanzas) {
		this.tardanzas = tardanzas;
	}
	@Column(name = "HorarioHorasTrabajo")
	public BigDecimal getHorarioHorasTrabajo() {
		return horarioHorasTrabajo;
	}

	public void setHorarioHorasTrabajo(BigDecimal horarioHorasTrabajo) {
		this.horarioHorasTrabajo = horarioHorasTrabajo;
	}

	@Column(name = "Vacaciones")
	public Integer getVacaciones() {
		return vacaciones;
	}

	public void setVacaciones(Integer vacaciones) {
		this.vacaciones = vacaciones;
	}

	@Column(name = "Licencias")
	public Integer getLicencias() {
		return licencias;
	}

	public void setLicencias(Integer licencias) {
		this.licencias = licencias;
	}

	@Column(name = "Inasistencias")
	public Integer getInasistencias() {
		return inasistencias;
	}

	public void setInasistencias(Integer inasistencias) {
		this.inasistencias = inasistencias;
	}

	@Column(name = "Mes")
	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

}
