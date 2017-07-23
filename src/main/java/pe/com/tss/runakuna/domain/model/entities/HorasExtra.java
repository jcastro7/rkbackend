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
@Table(name = "HorasExtra")
@NamedQuery(name = "HorasExtra.findAll", query = "SELECT e FROM HorasExtra e")
public class HorasExtra extends AuditingEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long idHorasExtra;
	private Empleado empleado;
	private Date fecha;
	//private String horaSalidaHorario;
	private Empleado atendidoPor;
	private String horaSalidaSolicitado;
	//private Double horas;
	private String motivo;
	private String estado;
	private Double horasCompensadas;
	private String comentarioResolucion;
	private String tipo;
	private BigDecimal horasNoCompensables;
	private Integer estoyDeAcuerdo;
	private String acuerdoTexto;
	
	private String horaInicioSolicitado;
	private BigDecimal horasSolicitadas;
	private BigDecimal horasExtra;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdHorasExtra")
	public Long getIdHorasExtra() {
		return idHorasExtra;
	}
	public void setIdHorasExtra(Long idHorasExtra) {
		this.idHorasExtra = idHorasExtra;
	}
	
	@ManyToOne()
    @JoinColumn(name = "IdEmpleado")
	public Empleado getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	
	@Column(name = "Fecha")
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
		
	@ManyToOne()
    @JoinColumn(name = "IdAtendidoPor")
	public Empleado getAtendidoPor() {
		return atendidoPor;
	}
	public void setAtendidoPor(Empleado atendidoPor) {
		this.atendidoPor = atendidoPor;
	}
	
	@Column(name = "HoraSalidaSolicitado")
	public String getHoraSalidaSolicitado() {
		return horaSalidaSolicitado;
	}
	public void setHoraSalidaSolicitado(String horaSalidaSolicitado) {
		this.horaSalidaSolicitado = horaSalidaSolicitado;
	}
		
	@Column(name = "Motivo")
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
	@Column(name = "Estado")
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	@Column(name = "HorasCompensadas")
	public Double getHorasCompensadas() {
		return horasCompensadas;
	}
	public void setHorasCompensadas(Double horasCompensadas) {
		this.horasCompensadas = horasCompensadas;
	}
	
	@Column(name = "ComentarioResolucion")
	public String getComentarioResolucion() {
		return comentarioResolucion;
	}
	public void setComentarioResolucion(String comentarioResolucion) {
		this.comentarioResolucion = comentarioResolucion;
	}
	
	@Column(name = "Tipo")
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	@Column(name = "HorasNoCompensables")
	public BigDecimal getHorasNoCompensables() {
		return horasNoCompensables;
	}
	public void setHorasNoCompensables(BigDecimal horasNoCompensables) {
		this.horasNoCompensables = horasNoCompensables;
	}
	@Column(name = "EstoyDeAcuerdo")
	public Integer getEstoyDeAcuerdo() {
		return estoyDeAcuerdo;
	}
	public void setEstoyDeAcuerdo(Integer estoyDeAcuerdo) {
		this.estoyDeAcuerdo = estoyDeAcuerdo;
	}
	@Column(name = "AcuerdoTexto")
	public String getAcuerdoTexto() {
		return acuerdoTexto;
	}
	public void setAcuerdoTexto(String acuerdoTexto) {
		this.acuerdoTexto = acuerdoTexto;
	}
	@Column(name = "HoraInicioSolicitado")
	public String getHoraInicioSolicitado() {
		return horaInicioSolicitado;
	}
	public void setHoraInicioSolicitado(String horaInicioSolicitado) {
		this.horaInicioSolicitado = horaInicioSolicitado;
	}
	@Column(name = "HorasSolicitadas")
	public BigDecimal getHorasSolicitadas() {
		return horasSolicitadas;
	}
	public void setHorasSolicitadas(BigDecimal horasSolicitadas) {
		this.horasSolicitadas = horasSolicitadas;
	}
	@Column(name = "HorasExtra")
	public BigDecimal getHorasExtra() {
		return horasExtra;
	}
	public void setHorasExtra(BigDecimal horasExtra) {
		this.horasExtra = horasExtra;
	}

}
