package pe.com.tss.runakuna.view.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import pe.com.tss.runakuna.json.JsonDateSimpleDeserializer;
import pe.com.tss.runakuna.json.JsonDateSimpleSerializer;

import java.util.Date;

public class HorasExtraEmpleadoFilterViewModel extends PageableFilter {

    private Long idEmpleado;
    private String nombreEmpleado;
    private Long idHorasExtra;


    @JsonSerialize(using=JsonDateSimpleSerializer.class)
    @JsonDeserialize(using=JsonDateSimpleDeserializer.class)
    private Date fechaInicio;

    @JsonSerialize(using=JsonDateSimpleSerializer.class)
    @JsonDeserialize(using=JsonDateSimpleDeserializer.class)
    private Date fechaFin;


    private Long unidadNegocio;
    private Long departamento;
    private Long proyecto;

    private String codigoPermiso;
    private String jefeInmediato;

    private String estado;
    private String estadoString;

    private Long idJefeInmediato;

    private Integer mes;
    private Integer anio;

    public String getCodigoPermiso() {
        return codigoPermiso;
    }

    public void setCodigoPermiso(String codigoPermiso) {
        this.codigoPermiso = codigoPermiso;
    }

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
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

	public Long getUnidadNegocio() {
        return unidadNegocio;
    }

    public void setUnidadNegocio(Long unidadNegocio) {
        this.unidadNegocio = unidadNegocio;
    }

    public Long getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Long departamento) {
        this.departamento = departamento;
    }

    public Long getProyecto() {
        return proyecto;
    }

    public void setProyecto(Long proyecto) {
        this.proyecto = proyecto;
    }

    public String getJefeInmediato() {
        return jefeInmediato;
    }

    public void setJefeInmediato(String jefeInmediato) {
        this.jefeInmediato = jefeInmediato;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstadoString() {
        return estadoString;
    }

    public void setEstadoString(String estadoString) {
        this.estadoString = estadoString;
    }

	public Long getIdJefeInmediato() {
		return idJefeInmediato;
	}

	public void setIdJefeInmediato(Long idJefeInmediato) {
		this.idJefeInmediato = idJefeInmediato;
	}

	public Long getIdHorasExtra() {
		return idHorasExtra;
	}

	public void setIdHorasExtra(Long idHorasExtra) {
		this.idHorasExtra = idHorasExtra;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}
    
}
