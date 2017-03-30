package pe.com.tss.runakuna.view.model;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.tss.runakuna.json.JsonDateSimpleDeserializer;
import pe.com.tss.runakuna.json.JsonDateSimpleSerializer;

public class HorasExtraViewModel {
	
	private Long idHorasExtra;
	private Long idEmpleado;
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date fecha;
	private String horas;
	private String estado;
	private String nombreEmpleado;
	private String jefeInmediato;
	private String nombreEstado;
	private String estadoString;
	private String nombreJefeInmediato;
	private String motivo;
	private Long idAtendidoPor;
	private String tipo;
	
	/*Información adicional: Solicitar Horas Extras*/ 
	private String horaSalidaHorario;
	private String horaSalidaSolicitado;
	private String horasSemanalesPendientes;
	private String totalHorasExtras;
	private String horasCompensadas;
	private Long idJefeAtendidoPor;
	private String comentarioResolucion;
	
	private BigDecimal horasNoCompensables;
	
	public Long getIdHorasExtra() {
		return idHorasExtra;
	}
	public void setIdHorasExtra(Long idHorasExtra) {
		this.idHorasExtra = idHorasExtra;
	}
	
	public Long getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getHoras() {
		return horas;
	}
	public void setHoras(String horas) {
		this.horas = horas;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}
	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}
	public String getJefeInmediato() {
		return jefeInmediato;
	}
	public void setJefeInmediato(String jefeInmediato) {
		this.jefeInmediato = jefeInmediato;
	}
	public String getNombreEstado() {
		return nombreEstado;
	}
	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}
	public String getEstadoString() {
		return estadoString;
	}
	public void setEstadoString(String estadoString) {
		this.estadoString = estadoString;
	}
	public String getNombreJefeInmediato() {
		return nombreJefeInmediato;
	}
	public void setNombreJefeInmediato(String nombreJefeInmediato) {
		this.nombreJefeInmediato = nombreJefeInmediato;
	}
	
	public String getHoraSalidaHorario() {
		return horaSalidaHorario;
	}
	public void setHoraSalidaHorario(String horaSalidaHorario) {
		this.horaSalidaHorario = horaSalidaHorario;
	}
	public String getHorasSemanalesPendientes() {
		return horasSemanalesPendientes;
	}
	public void setHorasSemanalesPendientes(String horasSemanalesPendientes) {
		this.horasSemanalesPendientes = horasSemanalesPendientes;
	}
	public String getTotalHorasExtras() {
		return totalHorasExtras;
	}
	public void setTotalHorasExtras(String totalHorasExtras) {
		this.totalHorasExtras = totalHorasExtras;
	}
	public String getHoraSalidaSolicitado() {
		return horaSalidaSolicitado;
	}
	public void setHoraSalidaSolicitado(String horaSalidaSolicitado) {
		this.horaSalidaSolicitado = horaSalidaSolicitado;
	}
	
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
	
	public String getHorasCompensadas() {
		return horasCompensadas;
	}
	public void setHorasCompensadas(String horasCompensadas) {
		this.horasCompensadas = horasCompensadas;
	}
	public Long getIdJefeAtendidoPor() {
		return idJefeAtendidoPor;
	}
	public void setIdJefeAtendidoPor(Long idJefeAtendidoPor) {
		this.idJefeAtendidoPor = idJefeAtendidoPor;
	}
	public Long getIdAtendidoPor() {
		return idAtendidoPor;
	}
	public void setIdAtendidoPor(Long idAtendidoPor) {
		this.idAtendidoPor = idAtendidoPor;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getComentarioResolucion() {
		return comentarioResolucion;
	}
	public void setComentarioResolucion(String comentarioResolucion) {
		this.comentarioResolucion = comentarioResolucion;
	}
	public BigDecimal getHorasNoCompensables() {
		return horasNoCompensables;
	}
	public void setHorasNoCompensables(BigDecimal horasNoCompensables) {
		this.horasNoCompensables = horasNoCompensables;
	}
	
}
