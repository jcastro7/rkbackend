package pe.com.tss.runakuna.view.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.tss.runakuna.json.JsonDateSimpleDeserializer;
import pe.com.tss.runakuna.json.JsonDateSimpleSerializer;
import pe.com.tss.runakuna.json.JsonFlagIntegerDeserializer;
import pe.com.tss.runakuna.json.JsonFlagIntegerSerializer;

public class LicenciaViewModel extends AuditingEntityViewModel {

	private Long idLicencia;
	private String motivo;
	private String comentario;
	
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date fechaInicio;
	
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date fechaFin;
	
	private Integer dias;
	
	private String nombreMotivo;
	
	private Long idEmpleado;
	private Long idAtendidoPor;
	private String nombreEstado;
	private String estado;
	
	@JsonSerialize(using=JsonFlagIntegerSerializer.class)
	@JsonDeserialize(using=JsonFlagIntegerDeserializer.class)
	private Integer diaEntero;
	
	private String nombreDiaEntero;
	
	private Long idTipoLicencia;
	private String nombreTipoLicencia;
	private String jefeInmediato;
	
	private String periodo;
	
	private List<DocumentoEmpleadoViewModel> documentos;

	public Long getIdLicencia() {
		return idLicencia;
	}

	public void setIdLicencia(Long idLicencia) {
		this.idLicencia = idLicencia;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Integer getDias() {
		return dias;
	}

	public void setDias(Integer dias) {
		this.dias = dias;
	}

	public String getNombreMotivo() {
		return nombreMotivo;
	}

	public void setNombreMotivo(String nombreMotivo) {
		this.nombreMotivo = nombreMotivo;
	}
	
	public List<DocumentoEmpleadoViewModel> getDocumentos() {
		return documentos;
	}
	public void setDocumentos(List<DocumentoEmpleadoViewModel> documentos) {
		this.documentos = documentos;
	}

	public Long getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public Long getIdAtendidoPor() {
		return idAtendidoPor;
	}

	public void setIdAtendidoPor(Long idAtendidoPor) {
		this.idAtendidoPor = idAtendidoPor;
	}

	public String getNombreEstado() {
		return nombreEstado;
	}

	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}

	public Integer getDiaEntero() {
		return diaEntero;
	}

	public void setDiaEntero(Integer diaEntero) {
		this.diaEntero = diaEntero;
	}

	public Long getIdTipoLicencia() {
		return idTipoLicencia;
	}

	public void setIdTipoLicencia(Long idTipoLicencia) {
		this.idTipoLicencia = idTipoLicencia;
	}

	public String getNombreTipoLicencia() {
		return nombreTipoLicencia;
	}

	public void setNombreTipoLicencia(String nombreTipoLicencia) {
		this.nombreTipoLicencia = nombreTipoLicencia;
	}

	public String getJefeInmediato() {
		return jefeInmediato;
	}

	public void setJefeInmediato(String jefeInmediato) {
		this.jefeInmediato = jefeInmediato;
	}

	public String getNombreDiaEntero() {
		return nombreDiaEntero;
	}

	public void setNombreDiaEntero(String nombreDiaEntero) {
		this.nombreDiaEntero = nombreDiaEntero;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
