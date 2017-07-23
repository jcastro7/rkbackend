package pe.com.tss.runakuna.view.model;

import java.io.Serializable;

public class ConfiguracionSistemaFilterViewModel extends FilterViewModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String codigo;
	
	private String descripcion;
	
	public ConfiguracionSistemaFilterViewModel() { }

	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}