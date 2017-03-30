package pe.com.tss.runakuna.view.model;

public class CentroCostoResultViewModel extends ResultViewModel{
	
	private Long idCentroCosto;
	private String nombreUnidadDeNegocio;
	private String nombreDepartamentoArea;
	private String nombre;
	private String descripcion;
	private String estado;
	
	public Long getIdCentroCosto() {
		return idCentroCosto;
	}
	public void setIdCentroCosto(Long idCentroCosto) {
		this.idCentroCosto = idCentroCosto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombreUnidadDeNegocio() {
		return nombreUnidadDeNegocio;
	}
	public void setNombreUnidadDeNegocio(String nombreUnidadDeNegocio) {
		this.nombreUnidadDeNegocio = nombreUnidadDeNegocio;
	}
	public String getNombreDepartamentoArea() {
		return nombreDepartamentoArea;
	}
	public void setNombreDepartamentoArea(String nombreDepartamentoArea) {
		this.nombreDepartamentoArea = nombreDepartamentoArea;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
