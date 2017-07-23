package pe.com.tss.runakuna.view.model;

public class CentroCostoFilterViewModel extends FileViewModel {
	
	
	private String nombre;
	private Long idUnidadDeNegocio;
	private Long idDepartamentoArea;
	private String estado;
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Long getIdUnidadDeNegocio() {
		return idUnidadDeNegocio;
	}
	public void setIdUnidadDeNegocio(Long idUnidadDeNegocio) {
		this.idUnidadDeNegocio = idUnidadDeNegocio;
	}
	public Long getIdDepartamentoArea() {
		return idDepartamentoArea;
	}
	public void setIdDepartamentoArea(Long idDepartamentoArea) {
		this.idDepartamentoArea = idDepartamentoArea;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
