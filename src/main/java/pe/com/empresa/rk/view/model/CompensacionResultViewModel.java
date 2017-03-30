package pe.com.empresa.rk.view.model;

import java.math.BigDecimal;

public class CompensacionResultViewModel {

	private Long idEmpleadoCompensacion;
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
	
	public Long getIdEmpleadoCompensacion() {
		return idEmpleadoCompensacion;
	}
	public void setIdEmpleadoCompensacion(Long idEmpleadoCompensacion) {
		this.idEmpleadoCompensacion = idEmpleadoCompensacion;
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
		
}
