package pe.com.tss.runakuna.view.model;

public class CargoResultViewModel extends ResultViewModel {
	
	private Long idCargo;
	private String nombre;
	private String nombreDepartamento;
	private String nombreUnidadNegocio;
	private String nombreCargoSuperior;
	private String estado;
	private String nombreProyecto;
	public Long getIdCargo() {
		return idCargo;
	}
	public void setIdCargo(Long idCargo) {
		this.idCargo = idCargo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	public String getNombreCargoSuperior() {
		return nombreCargoSuperior;
	}
	public void setNombreCargoSuperior(String nombreCargoSuperior) {
		this.nombreCargoSuperior = nombreCargoSuperior;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getNombreProyecto() {
		return nombreProyecto;
	}
	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}
	
	
	
}
