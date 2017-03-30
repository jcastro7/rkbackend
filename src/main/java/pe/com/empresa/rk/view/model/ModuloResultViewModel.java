package pe.com.empresa.rk.view.model;

import java.math.BigDecimal;

/**
 * Created by josediaz on 25/11/2016.
 */
public class ModuloResultViewModel {

    protected Long idModulo;

    private String codigo;
    
    private String codigoPadre;

    private String nombre;

    private BigDecimal orden;
    
    private String visible; 
    
    private String etiquetaMenu;

    public ModuloResultViewModel(){}


	public Long getIdModulo() {
		return idModulo;
	}


	public void setIdModulo(Long idModulo) {
		this.idModulo = idModulo;
	}


	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public String getCodigoPadre() {
		return codigoPadre;
	}


	public void setCodigoPadre(String codigoPadre) {
		this.codigoPadre = codigoPadre;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public BigDecimal getOrden() {
		return orden;
	}


	public void setOrden(BigDecimal orden) {
		this.orden = orden;
	}


	public String getVisible() {
		return visible;
	}


	public void setVisible(String visible) {
		this.visible = visible;
	}


	public String getEtiquetaMenu() {
		return etiquetaMenu;
	}


	public void setEtiquetaMenu(String etiquetaMenu) {
		this.etiquetaMenu = etiquetaMenu;
	}


	

    
    
}
