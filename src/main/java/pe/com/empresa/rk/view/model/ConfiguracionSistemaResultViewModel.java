package pe.com.empresa.rk.view.model;

import java.io.Serializable;

public class ConfiguracionSistemaResultViewModel extends ResultViewModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long idConfiguracionSistema;
	private String codigo;
	private String valor;
	private String descripcion;
	
	public ConfiguracionSistemaResultViewModel() { }

	public Long getIdConfiguracionSistema() {
		return idConfiguracionSistema;
	}

	public void setIdConfiguracionSistema(Long idConfiguracionSistema) {
		this.idConfiguracionSistema = idConfiguracionSistema;
	}

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


	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	
}
