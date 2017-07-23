package pe.com.tss.runakuna.view.model;

public class CentroCostoViewModel extends AuditingEntityViewModel {
	
	private Long idCentroCosto;
	private String nombre;
	private String descripcion;
	private String estado;
	private Long idEmpresa;
	private Long idUnidadDeNegocio;
	private Long idDepartamentoArea;
	private Long idProyecto;
	
	
	
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
	public Long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
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
	public Long getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(Long idProyecto) {
		this.idProyecto = idProyecto;
	}
	
}
