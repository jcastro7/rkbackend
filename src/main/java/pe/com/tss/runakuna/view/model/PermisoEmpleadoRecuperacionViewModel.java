package pe.com.tss.runakuna.view.model;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.tss.runakuna.json.JsonDateSimpleDeserializer;
import pe.com.tss.runakuna.json.JsonDateSimpleSerializer;

public class PermisoEmpleadoRecuperacionViewModel extends AuditingEntityViewModel{
	
	private Long idPermisoEmpleadoRecuperacion;
	
	private String horaInicio;
	private String horaFin;
	private BigDecimal horas;	
    
	public Long getIdPermisoEmpleadoRecuperacion() {
		return idPermisoEmpleadoRecuperacion;
	}

	public void setIdPermisoEmpleadoRecuperacion(Long idPermisoEmpleadoRecuperacion) {
		this.idPermisoEmpleadoRecuperacion = idPermisoEmpleadoRecuperacion;
	}

	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
    private Date fechaRecuperacion;

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

	public BigDecimal getHoras() {
		return horas;
	}

	public void setHoras(BigDecimal horas) {
		this.horas = horas;
	}

	public Date getFechaRecuperacion() {
		return fechaRecuperacion;
	}

	public void setFechaRecuperacion(Date fechaRecuperacion) {
		this.fechaRecuperacion = fechaRecuperacion;
	}
	
	

}
