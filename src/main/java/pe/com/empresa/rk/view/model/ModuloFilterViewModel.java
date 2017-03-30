package pe.com.empresa.rk.view.model;

/**
 * Created by josediaz on 25/11/2016.
 */
public class ModuloFilterViewModel extends FileViewModel{



    private String codigo;

    private String nombre;

    private String etiqueta;
    
    private String visible;
    

    public ModuloFilterViewModel(){}

    

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

	



	public String getEtiqueta() {
		return etiqueta;
	}



	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}



	public String getVisible() {
		return visible;
	}



	public void setVisible(String visible) {
		this.visible = visible;
	}

   
    
	
    
}
