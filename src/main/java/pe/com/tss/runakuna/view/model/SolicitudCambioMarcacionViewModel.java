package pe.com.tss.runakuna.view.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.tss.runakuna.json.JsonByteFlagDeserializer;
import pe.com.tss.runakuna.json.JsonByteFlagSerializer;

import pe.com.tss.runakuna.json.JsonFlagIntegerDeserializer;
import pe.com.tss.runakuna.json.JsonFlagIntegerSerializer;

public class SolicitudCambioMarcacionViewModel extends AuditingEntityViewModel{

	private Long idSolicitudCambioMarcacion;
	
	private Long idAtendidoPor;
	
	private Long idMarcacion;
	
	@JsonSerialize(using=JsonByteFlagSerializer.class)
	@JsonDeserialize(using=JsonByteFlagDeserializer.class)
	private byte[] cambiarIngreso;
	
	@JsonSerialize(using=JsonByteFlagSerializer.class)
	@JsonDeserialize(using=JsonByteFlagDeserializer.class)
	private byte[] cambiarInicioAlmuerzo;
	
	@JsonSerialize(using=JsonByteFlagSerializer.class)
	@JsonDeserialize(using=JsonByteFlagDeserializer.class)
	private byte[] cambiarFinAlmuerzo;
	
	@JsonSerialize(using=JsonByteFlagSerializer.class)
	@JsonDeserialize(using=JsonByteFlagDeserializer.class)
	private byte[] cambiarSalida;
	
	private String horaIngreso;
	private String horaInicioAlmuerzo;
	private String horaFinAlmuerzo;
	private String horaSalida;
	
	private String razonCambioHoraIngreso;
	private String razonCambioHoraInicioAlmuerzo;
	private String razonCambioHoraFinAlmuerzo;
	private String razonCambioHoraSalida;
	private String estado;
	
	@JsonSerialize(using=JsonFlagIntegerSerializer.class)
	@JsonDeserialize(using=JsonFlagIntegerDeserializer.class)
	private Integer tieneSolicitudCambio;
	
	private MarcacionViewModel marcacion;

	public Long getIdSolicitudCambioMarcacion() {
		return idSolicitudCambioMarcacion;
	}

	public void setIdSolicitudCambioMarcacion(Long idSolicitudCambioMarcacion) {
		this.idSolicitudCambioMarcacion = idSolicitudCambioMarcacion;
	}
	
	public Long getIdAtendidoPor() {
		return idAtendidoPor;
	}

	public void setIdAtendidoPor(Long idAtendidoPor) {
		this.idAtendidoPor = idAtendidoPor;
	}
	
	public Long getIdMarcacion() {
		return idMarcacion;
	}

	public void setIdMarcacion(Long idMarcacion) {
		this.idMarcacion = idMarcacion;
	}

	public byte[] getCambiarIngreso() {
		return cambiarIngreso;
	}

	public void setCambiarIngreso(byte[] cambiarIngreso) {
		this.cambiarIngreso = cambiarIngreso;
	}

	public byte[] getCambiarInicioAlmuerzo() {
		return cambiarInicioAlmuerzo;
	}

	public void setCambiarInicioAlmuerzo(byte[] cambiarInicioAlmuerzo) {
		this.cambiarInicioAlmuerzo = cambiarInicioAlmuerzo;
	}

	public byte[] getCambiarFinAlmuerzo() {
		return cambiarFinAlmuerzo;
	}

	public void setCambiarFinAlmuerzo(byte[] cambiarFinAlmuerzo) {
		this.cambiarFinAlmuerzo = cambiarFinAlmuerzo;
	}

	public byte[] getCambiarSalida() {
		return cambiarSalida;
	}

	public void setCambiarSalida(byte[] cambiarSalida) {
		this.cambiarSalida = cambiarSalida;
	}

	public String getHoraIngreso() {
		return horaIngreso;
	}

	public void setHoraIngreso(String horaIngreso) {
		this.horaIngreso = horaIngreso;
	}

	public String getHoraInicioAlmuerzo() {
		return horaInicioAlmuerzo;
	}

	public void setHoraInicioAlmuerzo(String horaInicioAlmuerzo) {
		this.horaInicioAlmuerzo = horaInicioAlmuerzo;
	}

	public String getHoraFinAlmuerzo() {
		return horaFinAlmuerzo;
	}

	public void setHoraFinAlmuerzo(String horaFinAlmuerzo) {
		this.horaFinAlmuerzo = horaFinAlmuerzo;
	}

	public String getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}

	public String getRazonCambioHoraIngreso() {
		return razonCambioHoraIngreso;
	}

	public void setRazonCambioHoraIngreso(String razonCambioHoraIngreso) {
		this.razonCambioHoraIngreso = razonCambioHoraIngreso;
	}

	public String getRazonCambioHoraInicioAlmuerzo() {
		return razonCambioHoraInicioAlmuerzo;
	}

	public void setRazonCambioHoraInicioAlmuerzo(String razonCambioHoraInicioAlmuerzo) {
		this.razonCambioHoraInicioAlmuerzo = razonCambioHoraInicioAlmuerzo;
	}

	public String getRazonCambioHoraFinAlmuerzo() {
		return razonCambioHoraFinAlmuerzo;
	}

	public void setRazonCambioHoraFinAlmuerzo(String razonCambioHoraFinAlmuerzo) {
		this.razonCambioHoraFinAlmuerzo = razonCambioHoraFinAlmuerzo;
	}

	public String getRazonCambioHoraSalida() {
		return razonCambioHoraSalida;
	}

	public void setRazonCambioHoraSalida(String razonCambioHoraSalida) {
		this.razonCambioHoraSalida = razonCambioHoraSalida;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public MarcacionViewModel getMarcacion() {
		return marcacion;
	}

	public void setMarcacion(MarcacionViewModel marcacion) {
		this.marcacion = marcacion;
	}

	public Integer getTieneSolicitudCambio() {
		return tieneSolicitudCambio;
	}

	public void setTieneSolicitudCambio(Integer tieneSolicitudCambio) {
		this.tieneSolicitudCambio = tieneSolicitudCambio;
	}
	
}