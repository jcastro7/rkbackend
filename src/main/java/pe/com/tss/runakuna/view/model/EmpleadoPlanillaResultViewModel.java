package pe.com.tss.runakuna.view.model;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoPlanillaResultViewModel {

	private Long idEmpleado;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String nombreCompletoEmpleado;
	
	private List<VacacionEmpleadoViewModel> vacacionesEmpleado = new ArrayList<>();

	public Long getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getNombreCompletoEmpleado() {
		return nombreCompletoEmpleado;
	}

	public void setNombreCompletoEmpleado(String nombreCompletoEmpleado) {
		this.nombreCompletoEmpleado = nombreCompletoEmpleado;
	}

	public List<VacacionEmpleadoViewModel> getVacacionesEmpleado() {
		return vacacionesEmpleado;
	}

	public void setVacacionesEmpleado(List<VacacionEmpleadoViewModel> vacacionesEmpleado) {
		this.vacacionesEmpleado = vacacionesEmpleado;
	} 
	
}