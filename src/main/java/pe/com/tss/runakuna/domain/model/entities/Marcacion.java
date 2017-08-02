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
@Table(name = "Marcacion")
@NamedQuery(name = "Marcacion.findAll", query = "SELECT m FROM Marcacion m")
public class Marcacion extends AuditingEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long idMarcacion;
	private Date fecha;
	private String horaIngreso;
	private String horaInicioAlmuerzo;
	private String horaFinAlmuerzo;
	private String horaSalida;
	private String horaIngresoHorario;
	private String horaSalidaHorario;
	//private Integer inasistencia;
	//private Integer tardanza;
	
	private BigDecimal horasTrabajoHorario;
	private BigDecimal demoraEntrada;
	private BigDecimal demoraAlmuerzo;
	private BigDecimal demoraSalida;
	
	private BigDecimal horasTrabajoReal;
	
	private BigDecimal horasPermiso;
	
	private BigDecimal horasExtra;
	
	private BigDecimal horasTrabajoPendiente;
	
	private Empleado empleado;
	
	//private Integer vacaciones;
	
	//private Integer licencia;
	
	private Integer esPersonaDeConfianza;
	
	private String recalcular;
	
	private String estado;
	
	private Integer noLaboral;
	
	private BigDecimal horasRecuperacion;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdMarcacion")
	public Long getIdMarcacion() {
		return idMarcacion;
	}
	
	public void setIdMarcacion(Long idMarcacion) {
		this.idMarcacion = idMarcacion;
	}
	
	@Column(name = "Fecha")
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Column(name = "HoraIngreso")
	public String getHoraIngreso() {
		return horaIngreso;
	}

	public void setHoraIngreso(String horaIngreso) {
		this.horaIngreso = horaIngreso;
	}

	@Column(name = "HoraInicioAlmuerzo")
	public String getHoraInicioAlmuerzo() {
		return horaInicioAlmuerzo;
	}

	public void setHoraInicioAlmuerzo(String horaInicioAlmuerzo) {
		this.horaInicioAlmuerzo = horaInicioAlmuerzo;
	}

	@Column(name = "HoraFinAlmuerzo")
	public String getHoraFinAlmuerzo() {
		return horaFinAlmuerzo;
	}

	public void setHoraFinAlmuerzo(String horaFinAlmuerzo) {
		this.horaFinAlmuerzo = horaFinAlmuerzo;
	}

	@Column(name = "HoraSalida")
	public String getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}

	@Column(name = "HoraIngresoHorario")
	public String getHoraIngresoHorario() {
		return horaIngresoHorario;
	}

	public void setHoraIngresoHorario(String horaIngresoHorario) {
		this.horaIngresoHorario = horaIngresoHorario;
	}

	@Column(name = "HoraSalidaHorario")
	public String getHoraSalidaHorario() {
		return horaSalidaHorario;
	}

	public void setHoraSalidaHorario(String horaSalidaHorario) {
		this.horaSalidaHorario = horaSalidaHorario;
	}

	/*@Column(name = "Inasistencia")
	public Integer getInasistencia() {
		return inasistencia;
	}

	public void setInasistencia(Integer inasistencia) {
		this.inasistencia = inasistencia;
	}*/

	/*@Column(name = "Tardanza")
	public Integer getTardanza() {
		return tardanza;
	}

	public void setTardanza(Integer tardanza) {
		this.tardanza = tardanza;
	}*/
	
	@Column(name = "HorasTrabajoHorario")
	public BigDecimal getHorasTrabajoHorario() {
		return horasTrabajoHorario;
	}

	public void setHorasTrabajoHorario(BigDecimal horasTrabajoHorario) {
		this.horasTrabajoHorario = horasTrabajoHorario;
	}

	@Column(name = "DemoraEntrada")
	public BigDecimal getDemoraEntrada() {
		return demoraEntrada;
	}

	public void setDemoraEntrada(BigDecimal demoraEntrada) {
		this.demoraEntrada = demoraEntrada;
	}

	@Column(name = "DemoraAlmuerzo")
	public BigDecimal getDemoraAlmuerzo() {
		return demoraAlmuerzo;
	}

	public void setDemoraAlmuerzo(BigDecimal demoraAlmuerzo) {
		this.demoraAlmuerzo = demoraAlmuerzo;
	}

	@Column(name = "DemoraSalida")
	public BigDecimal getDemoraSalida() {
		return demoraSalida;
	}

	public void setDemoraSalida(BigDecimal demoraSalida) {
		this.demoraSalida = demoraSalida;
	}

	@Column(name = "HorasTrabajoReal")
	public BigDecimal getHorasTrabajoReal() {
		return horasTrabajoReal;
	}

	public void setHorasTrabajoReal(BigDecimal horasTrabajoReal) {
		this.horasTrabajoReal = horasTrabajoReal;
	}

	@Column(name = "HorasPermiso")
	public BigDecimal getHorasPermiso() {
		return horasPermiso;
	}

	public void setHorasPermiso(BigDecimal horasPermiso) {
		this.horasPermiso = horasPermiso;
	}

	@Column(name = "HorasExtra")
	public BigDecimal getHorasExtra() {
		return horasExtra;
	}

	public void setHorasExtra(BigDecimal horasExtra) {
		this.horasExtra = horasExtra;
	}

	@Column(name = "HorasTrabajoPendiente")
	public BigDecimal getHorasTrabajoPendiente() {
		return horasTrabajoPendiente;
	}

	public void setHorasTrabajoPendiente(BigDecimal horasTrabajoPendiente) {
		this.horasTrabajoPendiente = horasTrabajoPendiente;
	}

	@ManyToOne()
    @JoinColumn(name = "IdEmpleado")
	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	/*@Column(name = "Vacaciones")
	public Integer getVacaciones() {
		return vacaciones;
	}

	public void setVacaciones(Integer vacaciones) {
		this.vacaciones = vacaciones;
	}
	
	@Column(name = "Licencia")
	public Integer getLicencia() {
		return licencia;
	}

	public void setLicencia(Integer licencia) {
		this.licencia = licencia;
	}*/
	
	@Column(name = "EsPersonaDeConfianza")
	public Integer getEsPersonaDeConfianza() {
		return esPersonaDeConfianza;
	}
	
	public void setEsPersonaDeConfianza(Integer esPersonaDeConfianza) {
		this.esPersonaDeConfianza = esPersonaDeConfianza;
	}

	@Column(name = "Recalcular")
	public String getRecalcular() {
		return recalcular;
	}

	public void setRecalcular(String recalcular) {
		this.recalcular = recalcular;
	}

	@Column(name = "Estado")
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Column(name = "NoLaboral")
	public Integer getNoLaboral() {
		return noLaboral;
	}

	public void setNoLaboral(Integer noLaboral) {
		this.noLaboral = noLaboral;
	}
	
	@Column(name = "HorasRecuperacion")
	public BigDecimal getHorasRecuperacion() {
		return horasRecuperacion;
	}

	public void setHorasRecuperacion(BigDecimal horasRecuperacion) {
		this.horasRecuperacion = horasRecuperacion;
	}
		
}
