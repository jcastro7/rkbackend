package pe.com.tss.runakuna.view.model;

import java.util.Date;

public class RegistroMarcacionViewModel {

	private Long idRegistroMarcacionEmpleado;
	private String codigoEmpleado;
	private String dni;
	private Date fecha;
	private String hora;
	private String tipo;
	
	private String creador;

	private Date fechaCreacion;
		
	private Long idEmpleado;
	
	private String procesado;
	
	public Long getIdRegistroMarcacionEmpleado() {
		return idRegistroMarcacionEmpleado;
	}

	public void setIdRegistroMarcacionEmpleado(Long idRegistroMarcacionEmpleado) {
		this.idRegistroMarcacionEmpleado = idRegistroMarcacionEmpleado;
	}

	public String getCodigoEmpleado() {
		return codigoEmpleado;
	}

	public void setCodigoEmpleado(String codigoEmpleado) {
		this.codigoEmpleado = codigoEmpleado;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCreador() {
		return creador;
	}

	public void setCreador(String creador) {
		this.creador = creador;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Long getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getProcesado() {
		return procesado;
	}

	public void setProcesado(String procesado) {
		this.procesado = procesado;
	}
	
}
