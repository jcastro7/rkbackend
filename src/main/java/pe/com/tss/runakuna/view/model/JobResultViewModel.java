package pe.com.tss.runakuna.view.model;

/**
 * Created by josediaz on 25/11/2016.
 */
public class JobResultViewModel extends AuditingEntityViewModel{

    private Long idJob;
    private String codigo;
    private String descripcion;
    private String estado;
    private String estadoEjecucion;
    private String fechaUltimaEjecucion;
    private String nombreClase;
    private String metodo;
	
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
	public Long getIdJob() {
		return idJob;
	}
	public void setIdJob(Long idJob) {
		this.idJob = idJob;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getEstadoEjecucion() {
		return estadoEjecucion;
	}
	public void setEstadoEjecucion(String estadoEjecucion) {
		this.estadoEjecucion = estadoEjecucion;
	}
	public String getFechaUltimaEjecucion() {
		return fechaUltimaEjecucion;
	}
	public void setFechaUltimaEjecucion(String fechaUltimaEjecucion) {
		this.fechaUltimaEjecucion = fechaUltimaEjecucion;
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

