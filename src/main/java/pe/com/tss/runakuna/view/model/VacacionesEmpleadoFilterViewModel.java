package pe.com.tss.runakuna.view.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import pe.com.tss.runakuna.json.JsonDateSimpleDeserializer;
import pe.com.tss.runakuna.json.JsonDateSimpleSerializer;

import java.util.Date;

/**
 * Created by josediaz on 8/11/2016.
 */
public class VacacionesEmpleadoFilterViewModel extends PageableFilter {

    private Long idEmpleado;
    


    @JsonSerialize(using=JsonDateSimpleSerializer.class)
    @JsonDeserialize(using=JsonDateSimpleDeserializer.class)
    private Date fechaInicio;

    @JsonSerialize(using=JsonDateSimpleSerializer.class)
    @JsonDeserialize(using=JsonDateSimpleDeserializer.class)
    private Date fechaFin;


    private Long idUnidadNegocio;
    private Long idDepartamentoArea;
    private Long idProyecto;
    private String codigoPermiso;
    private String estado;
    
    private Long idJefeInmediato;

	public Long getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
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

	public Long getIdUnidadNegocio() {
		return idUnidadNegocio;
	}

	public void setIdUnidadNegocio(Long idUnidadNegocio) {
		this.idUnidadNegocio = idUnidadNegocio;
	}

	public Long getIdDepartamentoArea() {
		return idDepartamentoArea;
	}

	public void setIdDepartamentoArea(Long idDepartamentoArea) {
		this.idDepartamentoArea = idDepartamentoArea;
	}

	public Long getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(Long idProyecto) {
		this.idProyecto = idProyecto;
	}

	public String getCodigoPermiso() {
		return codigoPermiso;
	}

	public void setCodigoPermiso(String codigoPermiso) {
		this.codigoPermiso = codigoPermiso;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Long getIdJefeInmediato() {
		return idJefeInmediato;
	}

	public void setIdJefeInmediato(Long idJefeInmediato) {
		this.idJefeInmediato = idJefeInmediato;
	}


    
    
}
