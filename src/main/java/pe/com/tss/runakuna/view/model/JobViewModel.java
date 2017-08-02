package pe.com.tss.runakuna.view.model;

/**
 * Created by josediaz on 25/11/2016.
 */
public class JobViewModel extends AuditingEntityViewModel{

    private Long idJob;
    private String codigo;
    private String descripcion;
    private String ultimaEjecucion;
    private String estado;
    private String nombreClase;
    private String metodo;
    
	public Long getIdJob() {
		return idJob;
	}
	public void setIdJob(Long idJob) {
		this.idJob = idJob;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getUltimaEjecucion() {
		return ultimaEjecucion;
	}
	public void setUltimaEjecucion(String ultimaEjecucion) {
		this.ultimaEjecucion = ultimaEjecucion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getNombreClase() {
		return nombreClase;
	}
	public void setNombreClase(String nombreClase) {
		this.nombreClase = nombreClase;
	}
	public String getMetodo() {
		return metodo;
	}
	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}
    
        
}

