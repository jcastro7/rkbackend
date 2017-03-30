package pe.com.empresa.rk.view.model;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.empresa.rk.json.JsonDateSimpleDeserializer;
import pe.com.empresa.rk.json.JsonDateSimpleSerializer;

public class MarcacionDashboardResultViewModel extends ResultViewModel{
	
	private String horaIngreso;
	private String horaInicioAlmuerzo;
	private String horaFinAlmuerzo;
	private String horaSalida;
	private String nombreCompletoEmpleado;
	
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date fecha;
	
	private String horaIngresoHorario;
	private String horaSalidaHorario;
	
	private String codigoEmpleado;
	
	private BigDecimal demoraEntrada;
	private BigDecimal demoraAlmuerzo;
	private BigDecimal demoraSalida;
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
	public String getNombreCompletoEmpleado() {
		return nombreCompletoEmpleado;
	}
	public void setNombreCompletoEmpleado(String nombreCompletoEmpleado) {
		this.nombreCompletoEmpleado = nombreCompletoEmpleado;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getHoraIngresoHorario() {
		return horaIngresoHorario;
	}
	public void setHoraIngresoHorario(String horaIngresoHorario) {
		this.horaIngresoHorario = horaIngresoHorario;
	}
	public String getHoraSalidaHorario() {
		return horaSalidaHorario;
	}
	public void setHoraSalidaHorario(String horaSalidaHorario) {
		this.horaSalidaHorario = horaSalidaHorario;
	}
	public String getCodigoEmpleado() {
		return codigoEmpleado;
	}
	public void setCodigoEmpleado(String codigoEmpleado) {
		this.codigoEmpleado = codigoEmpleado;
	}
	public BigDecimal getDemoraEntrada() {
		return demoraEntrada;
	}
	public void setDemoraEntrada(BigDecimal demoraEntrada) {
		this.demoraEntrada = demoraEntrada;
	}
	public BigDecimal getDemoraAlmuerzo() {
		return demoraAlmuerzo;
	}
	public void setDemoraAlmuerzo(BigDecimal demoraAlmuerzo) {
		this.demoraAlmuerzo = demoraAlmuerzo;
	}
	public BigDecimal getDemoraSalida() {
		return demoraSalida;
	}
	public void setDemoraSalida(BigDecimal demoraSalida) {
		this.demoraSalida = demoraSalida;
	}
	
	

}
