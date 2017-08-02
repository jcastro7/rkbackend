package pe.com.tss.runakuna.view.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.tss.runakuna.domain.model.entities.PermisoEmpleadoRecuperacion;
import pe.com.tss.runakuna.json.JsonDateSimpleDeserializer;
import pe.com.tss.runakuna.json.JsonDateSimpleSerializer;
import pe.com.tss.runakuna.json.JsonFlagIntegerDeserializer;
import pe.com.tss.runakuna.json.JsonFlagIntegerSerializer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class PermisoEmpleadoViewModel extends AuditingEntityViewModel{

	private Long idPermisoEmpleado;
	private Long idEmpleado;
	private String motivo;
	private String nombreMotivo;
	private String razon;
	private String jefeInmediato;
	private String periodo;
	
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date fecha;
	
	private String horaInicio;
	private String horaFin;
	private String estado;
	private String nombreEstado;
	private BigDecimal horas;
	
    private String codigo;
    private Long idPeriodoEmpleado;
    private Long idAtendidoPor;
    
//    @JsonSerialize(using=JsonDateSimpleSerializer.class)
//	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
//    private Date fechaRecuperacion;
//    
//    private String horaInicioRecuperacion;
//    private String horaFinRecuperacion;
    private String estadoString;
    
    private PeriodoEmpleadoViewModel periodoEmpleado;

	private String nombreEmpleado;
	
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date fechaPermiso;
	
	private String comentarioResolucion;
	
	private Integer diasCalendariosDisponibles;
	@JsonSerialize(using=JsonFlagIntegerSerializer.class)
	@JsonDeserialize(using=JsonFlagIntegerDeserializer.class)
	private Integer diaEntero;
	
	private List<PermisoEmpleadoRecuperacionViewModel> permisoEmpleadoRecuperacion;
	
	public Long getIdPermisoEmpleado() {
		return idPermisoEmpleado;
	}
	public void setIdPermisoEmpleado(Long idPermisoEmpleado) {
		this.idPermisoEmpleado = idPermisoEmpleado;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getNombreMotivo() {
		return nombreMotivo;
	}
	public void setNombreMotivo(String nombreMotivo) {
		this.nombreMotivo = nombreMotivo;
	}
	public String getRazon() {
		return razon;
	}
	public void setRazon(String razon) {
		this.razon = razon;
	}
	public String getJefeInmediato() {
		return jefeInmediato;
	}
	public void setJefeInmediato(String jefeInmediato) {
		this.jefeInmediato = jefeInmediato;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public String getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getNombreEstado() {
		return nombreEstado;
	}
	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}
	public BigDecimal getHoras() {
		return horas;
	}
	public void setHoras(BigDecimal horas) {
		this.horas = horas;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Long getIdPeriodoEmpleado() {
		return idPeriodoEmpleado;
	}
	public void setIdPeriodoEmpleado(Long idPeriodoEmpleado) {
		this.idPeriodoEmpleado = idPeriodoEmpleado;
	}
	public Long getIdAtendidoPor() {
		return idAtendidoPor;
	}
	public void setIdAtendidoPor(Long idAtendidoPor) {
		this.idAtendidoPor = idAtendidoPor;
	}
//	public Date getFechaRecuperacion() {
//		return fechaRecuperacion;
//	}
//	public void setFechaRecuperacion(Date fechaRecuperacion) {
//		this.fechaRecuperacion = fechaRecuperacion;
//	}
//	public String getHoraInicioRecuperacion() {
//		return horaInicioRecuperacion;
//	}
//	public void setHoraInicioRecuperacion(String horaInicioRecuperacion) {
//		this.horaInicioRecuperacion = horaInicioRecuperacion;
//	}
//	public String getHoraFinRecuperacion() {
//		return horaFinRecuperacion;
//	}
//	public void setHoraFinRecuperacion(String horaFinRecuperacion) {
//		this.horaFinRecuperacion = horaFinRecuperacion;
//	}
	public String getEstadoString() {
		return estadoString;
	}
	public void setEstadoString(String estadoString) {
		this.estadoString = estadoString;
	}
	public PeriodoEmpleadoViewModel getPeriodoEmpleado() {
		return periodoEmpleado;
	}
	public void setPeriodoEmpleado(PeriodoEmpleadoViewModel periodoEmpleado) {
		this.periodoEmpleado = periodoEmpleado;
	}

	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}
	public Long getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public Date getFechaPermiso() {
		return fechaPermiso;
	}
	public void setFechaPermiso(Date fechaPermiso) {
		this.fechaPermiso = fechaPermiso;
	}
	public String getComentarioResolucion() {
		return comentarioResolucion;
	}
	public void setComentarioResolucion(String comentarioResolucion) {
		this.comentarioResolucion = comentarioResolucion;
	}
	public Integer getDiasCalendariosDisponibles() {
		return diasCalendariosDisponibles;
	}
	public void setDiasCalendariosDisponibles(Integer diasCalendariosDisponibles) {
		this.diasCalendariosDisponibles = diasCalendariosDisponibles;
	}
	public Integer getDiaEntero() {
		return diaEntero;
	}
	public void setDiaEntero(Integer diaEntero) {
		this.diaEntero = diaEntero;
	}
	public List<PermisoEmpleadoRecuperacionViewModel> getPermisoEmpleadoRecuperacion() {
		return permisoEmpleadoRecuperacion;
	}
	public void setPermisoEmpleadoRecuperacion(List<PermisoEmpleadoRecuperacionViewModel> permisoEmpleadoRecuperacion) {
		this.permisoEmpleadoRecuperacion = permisoEmpleadoRecuperacion;
	}
		
	
}