package pe.com.tss.runakuna.view.model;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.tss.runakuna.json.JsonDateSimpleDeserializer;
import pe.com.tss.runakuna.json.JsonDateSimpleSerializer;

/**
 * Created by josediaz on 25/11/2016.
 */
public class JobEjecucionFilterViewModel extends AuditingEntityViewModel{

    private Long idJobEjecucion;
    private Long idJob;
    @JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
    private Date fechaInicio;
    @JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
    private Date fechaFin;
    private String estado;
    private String resultadoMensaje;
    
	public Long getIdJobEjecucion() {
		return idJobEjecucion;
	}
	public void setIdJobEjecucion(Long idJobEjecucion) {
		this.idJobEjecucion = idJobEjecucion;
	}
	public Long getIdJob() {
		return idJob;
	}
	public void setIdJob(Long idJob) {
		this.idJob = idJob;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getResultadoMensaje() {
		return resultadoMensaje;
	}
	public void setResultadoMensaje(String resultadoMensaje) {
		this.resultadoMensaje = resultadoMensaje;
	}
    
	
	
        
}

