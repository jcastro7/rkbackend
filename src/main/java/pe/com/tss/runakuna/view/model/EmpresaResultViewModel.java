package pe.com.tss.runakuna.view.model;

public class EmpresaResultViewModel extends ResultViewModel{
	
	private Long idEmpresa;	
	private String codigo;
	private String razonSocial;
    private String ruc;
    private String estado;
    private String jefe;
    private String jefeReemplazo;
    private Long idJefe;
    private Long idJefeReemplazo;
    
	public Long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getRuc() {
		return ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getJefe() {
		return jefe;
	}
	public void setJefe(String jefe) {
		this.jefe = jefe;
	}
	public String getJefeReemplazo() {
		return jefeReemplazo;
	}
	public void setJefeReemplazo(String jefeReemplazo) {
		this.jefeReemplazo = jefeReemplazo;
	}
	public Long getIdJefe() {
		return idJefe;
	}
	public void setIdJefe(Long idJefe) {
		this.idJefe = idJefe;
	}
	public Long getIdJefeReemplazo() {
		return idJefeReemplazo;
	}
	public void setIdJefeReemplazo(Long idJefeReemplazo) {
		this.idJefeReemplazo = idJefeReemplazo;
	}
    
}
