package pe.com.empresa.rk.view.model;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.empresa.rk.json.JsonDateSimpleDeserializer;
import pe.com.empresa.rk.json.JsonDateSimpleSerializer;

public class GenerarReporteMarcacionAcumuladaViewModel {

	private Long idMarcacion;
	private Long idEmpleado;
	
	private String nombreEmpleado;
	private String apelPaternoEmpleado;
	private String apelMaternoEmpleado;
	private String nombreCompletoEmpleado;	
	
	
	private String unidadNegocio;
	private String departamentoArea;
	private String proyecto;
	
	
	private BigDecimal horasPendientesTotal;
	private BigDecimal horasPendientesMesActual;
	private BigDecimal horasPendientesHastaMesAnterior;
	
	private BigDecimal horasTardanzaIngreso;
	private BigDecimal horasTardanzaSalida;
	private BigDecimal horasDemoraAlmuerzo;
	private BigDecimal horasTrabajadas;
	private String tardanzas;
	private BigDecimal horarioHorasTrabajo;
	private String vacaciones;
	private String licencias;
	private String inasistencias;
	
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date fecha;
	
	public Long getIdMarcacion() {
		return idMarcacion;
	}

	public void setIdMarcacion(Long idMarcacion) {
		this.idMarcacion = idMarcacion;
	}

	public Long getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	public String getApelPaternoEmpleado() {
		return apelPaternoEmpleado;
	}

	public void setApelPaternoEmpleado(String apelPaternoEmpleado) {
		this.apelPaternoEmpleado = apelPaternoEmpleado;
	}

	public String getApelMaternoEmpleado() {
		return apelMaternoEmpleado;
	}

	public void setApelMaternoEmpleado(String apelMaternoEmpleado) {
		this.apelMaternoEmpleado = apelMaternoEmpleado;
	}

	public String getNombreCompletoEmpleado() {
		return nombreCompletoEmpleado;
	}

	public void setNombreCompletoEmpleado(String nombreCompletoEmpleado) {
		this.nombreCompletoEmpleado = nombreCompletoEmpleado;
	}

	public String getUnidadNegocio() {
		return unidadNegocio;
	}

	public void setUnidadNegocio(String unidadNegocio) {
		this.unidadNegocio = unidadNegocio;
	}

	public String getDepartamentoArea() {
		return departamentoArea;
	}

	public void setDepartamentoArea(String departamentoArea) {
		this.departamentoArea = departamentoArea;
	}

	public String getProyecto() {
		return proyecto;
	}

	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}


	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	

	public BigDecimal getHorasPendientesTotal() {
		return horasPendientesTotal;
	}

	public void setHorasPendientesTotal(BigDecimal horasPendientesTotal) {
		this.horasPendientesTotal = horasPendientesTotal;
	}

	public BigDecimal getHorasPendientesMesActual() {
		return horasPendientesMesActual;
	}

	public void setHorasPendientesMesActual(BigDecimal horasPendientesMesActual) {
		this.horasPendientesMesActual = horasPendientesMesActual;
	}

	public BigDecimal getHorasPendientesHastaMesAnterior() {
		return horasPendientesHastaMesAnterior;
	}

	public void setHorasPendientesHastaMesAnterior(BigDecimal horasPendientesHastaMesAnterior) {
		this.horasPendientesHastaMesAnterior = horasPendientesHastaMesAnterior;
	}

	public BigDecimal getHorasTardanzaIngreso() {
		return horasTardanzaIngreso;
	}

	public void setHorasTardanzaIngreso(BigDecimal horasTardanzaIngreso) {
		this.horasTardanzaIngreso = horasTardanzaIngreso;
	}

	public BigDecimal getHorasTardanzaSalida() {
		return horasTardanzaSalida;
	}

	public void setHorasTardanzaSalida(BigDecimal horasTardanzaSalida) {
		this.horasTardanzaSalida = horasTardanzaSalida;
	}

	public BigDecimal getHorasDemoraAlmuerzo() {
		return horasDemoraAlmuerzo;
	}

	public void setHorasDemoraAlmuerzo(BigDecimal horasDemoraAlmuerzo) {
		this.horasDemoraAlmuerzo = horasDemoraAlmuerzo;
	}

	public BigDecimal getHorasTrabajadas() {
		return horasTrabajadas;
	}

	public void setHorasTrabajadas(BigDecimal horasTrabajadas) {
		this.horasTrabajadas = horasTrabajadas;
	}

	public String getTardanzas() {
		return tardanzas;
	}

	public void setTardanzas(String tardanzas) {
		this.tardanzas = tardanzas;
	}

	public BigDecimal getHorarioHorasTrabajo() {
		return horarioHorasTrabajo;
	}

	public void setHorarioHorasTrabajo(BigDecimal horarioHorasTrabajo) {
		this.horarioHorasTrabajo = horarioHorasTrabajo;
	}

	public String getVacaciones() {
		return vacaciones;
	}

	public void setVacaciones(String vacaciones) {
		this.vacaciones = vacaciones;
	}

	public String getLicencias() {
		return licencias;
	}

	public void setLicencias(String licencias) {
		this.licencias = licencias;
	}

	public String getInasistencias() {
		return inasistencias;
	}

	public void setInasistencias(String inasistencias) {
		this.inasistencias = inasistencias;
	}

	
	
	
	
	

	
	
	
}
