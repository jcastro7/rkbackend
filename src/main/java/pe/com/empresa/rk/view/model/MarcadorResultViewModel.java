package pe.com.empresa.rk.view.model;

/**
 * Created by josediaz on 25/11/2016.
 */
public class MarcadorResultViewModel {

	private Long idMarcador;
    private String codigo;
    private String nombre;
    private String descripcion;
    
	public Long getIdMarcador() {
		return idMarcador;
	}
	public void setIdMarcador(Long idMarcador) {
		this.idMarcador = idMarcador;
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

        
    
}
