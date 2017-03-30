package pe.com.tss.runakuna.view.model;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.tss.runakuna.json.JsonDateSimpleDeserializer;
import pe.com.tss.runakuna.json.JsonDateSimpleSerializer;

public class CompensacionViewModel {

	private Long idCompensacion;
	private String empleado;
	private String mes;
	private BigDecimal horasTardanzaIngreso;
	private BigDecimal horasTardanzaSalida;
	private BigDecimal horasDemoraAlmuerzo;
	private BigDecimal horasTrabajadas;

	private BigDecimal horarioHorasTrabajo;

	private Integer vacaciones;
	private Integer licencias;
	private Integer inasistencias;
	private BigDecimal horasPendientesTotal;
	
	private BigDecimal horasAdicionales;
	private BigDecimal horasCompensadas;
	
	private Long idEmpleadoCompensacion;
	
	private Long idEmpleado;
	
	public Long getIdCompensacion() {
		return idCompensacion;
	}
	public void setIdCompensacion(Long idCompensacion) {
		this.idCompensacion = idCompensacion;
	}
	public String getEmpleado() {
		return empleado;
	}
	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
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
	public BigDecimal getHorarioHorasTrabajo() {
		return horarioHorasTrabajo;
	}
	public void setHorarioHorasTrabajo(BigDecimal horarioHorasTrabajo) {
		this.horarioHorasTrabajo = horarioHorasTrabajo;
	}
	public Integer getVacaciones() {
		return vacaciones;
	}
	public void setVacaciones(Integer vacaciones) {
		this.vacaciones = vacaciones;
	}
	public Integer getLicencias() {
		return licencias;
	}
	public void setLicencias(Integer licencias) {
		this.licencias = licencias;
	}
	public Integer getInasistencias() {
		return inasistencias;
	}
	public void setInasistencias(Integer inasistencias) {
		this.inasistencias = inasistencias;
	}
	public BigDecimal getHorasPendientesTotal() {
		return horasPendientesTotal;
	}
	public void setHorasPendientesTotal(BigDecimal horasPendientesTotal) {
		this.horasPendientesTotal = horasPendientesTotal;
	}
	public Long getIdEmpleadoCompensacion() {
		return idEmpleadoCompensacion;
	}
	public void setIdEmpleadoCompensacion(Long idEmpleadoCompensacion) {
		this.idEmpleadoCompensacion = idEmpleadoCompensacion;
	}
	public Long getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public BigDecimal getHorasAdicionales() {
		return horasAdicionales;
	}
	public void setHorasAdicionales(BigDecimal horasAdicionales) {
		this.horasAdicionales = horasAdicionales;
	}
	public BigDecimal getHorasCompensadas() {
		return horasCompensadas;
	}
	public void setHorasCompensadas(BigDecimal horasCompensadas) {
		this.horasCompensadas = horasCompensadas;
	}
	
}
