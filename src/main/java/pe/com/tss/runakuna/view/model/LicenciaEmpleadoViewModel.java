package pe.com.tss.runakuna.view.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.tss.runakuna.json.JsonDateSimpleDeserializer;
import pe.com.tss.runakuna.json.JsonDateSimpleSerializer;
import pe.com.tss.runakuna.json.JsonFlagIntegerDeserializer;
import pe.com.tss.runakuna.json.JsonFlagIntegerSerializer;

public class LicenciaEmpleadoViewModel extends AuditingEntityViewModel {

	private Long idLicencia;
	private Long idEmpleado;
	private String comentario;
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date fechaInicio;
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date fechaFin;
	private Integer dias;
	private String estado;
	private String nombreEmpleado;
	private String nombreProyecto;
	private String nombreDepartamento;
	private String nombreUnidadNegocio;
	private String nombreTipoLicencia;
	private Long idTipoLicencia;
	private List<DocumentoEmpleadoViewModel> documentos;
	@JsonSerialize(using=JsonFlagIntegerSerializer.class)
	@JsonDeserialize(using=JsonFlagIntegerDeserializer.class)
	private Integer diaEntero;
	private String horaInicio;
	private String horaFin;
	private Long idAtendidoPor;
	private String comentarioResolucion;
	
	
	public Long getIdTipoLicencia() {
		return idTipoLicencia;
	}
	public void setIdTipoLicencia(Long idTipoLicencia) {
		this.idTipoLicencia = idTipoLicencia;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getComentario() {
		return comentario;
	}
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}
	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}
	public String getNombreProyecto() {
		return nombreProyecto;
	}
	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}
	public String getNombreDepartamento() {
		return nombreDepartamento;
	}
	public void setNombreDepartamento(String nombreDepartamento) {
		this.nombreDepartamento = nombreDepartamento;
	}
	public String getNombreUnidadNegocio() {
		return nombreUnidadNegocio;
	}
	public void setNombreUnidadNegocio(String nombreUnidadNegocio) {
		this.nombreUnidadNegocio = nombreUnidadNegocio;
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
	
	public Long getIdLicencia() {
		return idLicencia;
	}
	public void setIdLicencia(Long idLicencia) {
		this.idLicencia = idLicencia;
	}
	public Long getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public List<DocumentoEmpleadoViewModel> getDocumentos() {
		return documentos;
	}
	public void setDocumentos(List<DocumentoEmpleadoViewModel> documentos) {
		this.documentos = documentos;
	}
	public Integer getDiaEntero() {
		return diaEntero;
	}
	public void setDiaEntero(Integer diaEntero) {
		this.diaEntero = diaEntero;
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
	public String getNombreTipoLicencia() {
		return nombreTipoLicencia;
	}
	public void setNombreTipoLicencia(String nombreTipoLicencia) {
		this.nombreTipoLicencia = nombreTipoLicencia;
	}
	public Long getIdAtendidoPor() {
		return idAtendidoPor;
	}
	public void setIdAtendidoPor(Long idAtendidoPor) {
		this.idAtendidoPor = idAtendidoPor;
	}
	public String getComentarioResolucion() {
		return comentarioResolucion;
	}
	public void setComentarioResolucion(String comentarioResolucion) {
		this.comentarioResolucion = comentarioResolucion;
	}
	
	

}
