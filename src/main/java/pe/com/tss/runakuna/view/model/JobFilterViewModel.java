package pe.com.tss.runakuna.view.model;

/**
 * Created by josediaz on 25/11/2016.
 */
public class JobFilterViewModel extends AuditingEntityViewModel{

    
    private String codigo;
    private String descripcion;
    
	
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
	
        
}

