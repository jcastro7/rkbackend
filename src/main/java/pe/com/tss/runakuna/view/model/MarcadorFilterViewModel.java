package pe.com.tss.runakuna.view.model;

/**
 * Created by ocastillo on 28/03/2017.
 */
public class MarcadorFilterViewModel extends FileViewModel{

	private String codigo;
    private String nombre;
    private String descripcion;
    
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
   
    
}
