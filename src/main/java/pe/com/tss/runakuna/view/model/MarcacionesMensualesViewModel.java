package pe.com.tss.runakuna.view.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by ocastillo on 29/03/2017.
 */
public class MarcacionesMensualesViewModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fecha;
    private BigDecimal entradaHorario;
    private BigDecimal entradaHorarioReal;
    private BigDecimal horasTrabajoHorario;
    private BigDecimal horasTrabajoReal;
    
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public BigDecimal getEntradaHorario() {
		return entradaHorario;
	}
	public void setEntradaHorario(BigDecimal entradaHorario) {
		this.entradaHorario = entradaHorario;
	}
	public BigDecimal getEntradaHorarioReal() {
		return entradaHorarioReal;
	}
	public void setEntradaHorarioReal(BigDecimal entradaHorarioReal) {
		this.entradaHorarioReal = entradaHorarioReal;
	}
	public BigDecimal getHorasTrabajoHorario() {
		return horasTrabajoHorario;
	}
	public void setHorasTrabajoHorario(BigDecimal horasTrabajoHorario) {
		this.horasTrabajoHorario = horasTrabajoHorario;
	}
	public BigDecimal getHorasTrabajoReal() {
		return horasTrabajoReal;
	}
	public void setHorasTrabajoReal(BigDecimal horasTrabajoReal) {
		this.horasTrabajoReal = horasTrabajoReal;
	}
	
    
    
}
