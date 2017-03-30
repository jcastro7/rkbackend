package pe.com.tss.runakuna.view.model;

public class TipoLicenciaResultViewModel extends ResultViewModel{
	
	private Long idTipoLicencia;
	private String codigo;
	private String nombre;
	private Integer limiteMensual;
	private Integer limiteAnual;
	private String estado;
	
	public Long getIdTipoLicencia() {
		return idTipoLicencia;
	}
	public void setIdTipoLicencia(Long idTipoLicencia) {
		this.idTipoLicencia = idTipoLicencia;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getLimiteMensual() {
		return limiteMensual;
	}
	public void setLimiteMensual(Integer limiteMensual) {
		this.limiteMensual = limiteMensual;
	}
	public Integer getLimiteAnual() {
		return limiteAnual;
	}
	public void setLimiteAnual(Integer limiteAnual) {
		this.limiteAnual = limiteAnual;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
}
