package pe.com.tss.runakuna.view.model;

import java.io.Serializable;

/**
 * Created by ocastillo on 29/03/2017.
 */
public class MarcacionesMensualesFilterViewModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idEmpleado;
    private String anio;
    private String mes;
	public Long getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
    
	
    
    
}
