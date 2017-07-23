package pe.com.tss.runakuna.view.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.tss.runakuna.json.JsonDateSimpleDeserializer;
import pe.com.tss.runakuna.json.JsonDateSimpleSerializer;

public class PeriodoEmpleadoResultViewModel extends ResultViewModel{
	
	private Long idPeriodoEmpleado;
	private String nombreEmpleado;
	private String codigoEmpleado;
	
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date fechaInicio;
	
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date fechaFin;
	private Long idEmpleado;
	private Integer maxDiasVacaciones;
	private Integer diasVacacionesDisponibles;
	private Integer diasVacacionesPorVencer;
	private Integer maxPermisos;
	private Integer permisosDisponibles;
	
	private Integer diasVacacionesAcumulado;
	
	private Integer diasCalendariosDisponibles;
	
	private String periodo;
	
	private List<TipoLicenciaViewModel> tipoLicencia;
	
	public Long getIdPeriodoEmpleado() {
		return idPeriodoEmpleado;
	}
	public void setIdPeriodoEmpleado(Long idPeriodoEmpleado) {
		this.idPeriodoEmpleado = idPeriodoEmpleado;
	}
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}
	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
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
	public Integer getMaxDiasVacaciones() {
		return maxDiasVacaciones;
	}
	public void setMaxDiasVacaciones(Integer maxDiasVacaciones) {
		this.maxDiasVacaciones = maxDiasVacaciones;
	}
	public Integer getDiasVacacionesDisponibles() {
		return diasVacacionesDisponibles;
	}
	public void setDiasVacacionesDisponibles(Integer diasVacacionesDisponibles) {
		this.diasVacacionesDisponibles = diasVacacionesDisponibles;
	}
	public Integer getMaxPermisos() {
		return maxPermisos;
	}
	public void setMaxPermisos(Integer maxPermisos) {
		this.maxPermisos = maxPermisos;
	}
	public Integer getPermisosDisponibles() {
		return permisosDisponibles;
	}
	public void setPermisosDisponibles(Integer permisosDisponibles) {
		this.permisosDisponibles = permisosDisponibles;
	}
	public Integer getDiasVacacionesAcumulado() {
		return diasVacacionesAcumulado;
	}
	public void setDiasVacacionesAcumulado(Integer diasVacacionesAcumulado) {
		this.diasVacacionesAcumulado = diasVacacionesAcumulado;
	}
	public String getCodigoEmpleado() {
		return codigoEmpleado;
	}
	public void setCodigoEmpleado(String codigoEmpleado) {
		this.codigoEmpleado = codigoEmpleado;
	}
	public Integer getDiasVacacionesPorVencer() {
		return diasVacacionesPorVencer;
	}
	public void setDiasVacacionesPorVencer(Integer diasVacacionesPorVencer) {
		this.diasVacacionesPorVencer = diasVacacionesPorVencer;
	}
	public Long getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public Integer getDiasCalendariosDisponibles() {
		return diasCalendariosDisponibles;
	}
	public void setDiasCalendariosDisponibles(Integer diasCalendariosDisponibles) {
		this.diasCalendariosDisponibles = diasCalendariosDisponibles;
	}
	public List<TipoLicenciaViewModel> getTipoLicencia() {
		return tipoLicencia;
	}
	public void setTipoLicencia(List<TipoLicenciaViewModel> tipoLicencia) {
		this.tipoLicencia = tipoLicencia;
	}
	
	
	
}
