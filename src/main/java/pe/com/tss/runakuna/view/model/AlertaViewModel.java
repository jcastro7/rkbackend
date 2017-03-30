package pe.com.tss.runakuna.view.model;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.tss.runakuna.json.JsonFlagIntegerDeserializer;
import pe.com.tss.runakuna.json.JsonFlagIntegerSerializer;

/**
 * Created by josediaz on 14/12/2016.
 */
public class AlertaViewModel extends AuditingEntityViewModel {

    private Long idAlerta;
    private Long idEmpresa;
    
    private String codigoTipoAlerta;
    private String tipoAlerta;
	private String codigo;
	private String codigoTipoNotificacion;
	private String tipoNotificacion;
	
	private String asunto;
	private String cuerpo;
	private String alerta;
	
	@JsonSerialize(using=JsonFlagIntegerSerializer.class)
	@JsonDeserialize(using=JsonFlagIntegerDeserializer.class)
	private Integer jefeProyecto;
	@JsonSerialize(using=JsonFlagIntegerSerializer.class)
	@JsonDeserialize(using=JsonFlagIntegerDeserializer.class)
	private Integer jefeDepartamento;
	@JsonSerialize(using=JsonFlagIntegerSerializer.class)
	@JsonDeserialize(using=JsonFlagIntegerDeserializer.class)
	private Integer jefeUnidad;
	@JsonSerialize(using=JsonFlagIntegerSerializer.class)
	@JsonDeserialize(using=JsonFlagIntegerDeserializer.class)
	private Integer jefeEmpresa;
	private String agrupamiento;
	private String estado;
	private String codigoEstado;
	
	private Long idEmpleado;
	private String nombreEmpleado;
	
    private List<AlertaSubscriptorViewModel> subscriptores;
    private Long idAlertaSubscriptor;    

    public Long getIdAlerta() {
        return idAlerta;
    }

    public void setIdAlerta(Long idAlerta) {
        this.idAlerta = idAlerta;
    }

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getTipoAlerta() {
        return tipoAlerta;
    }

    public void setTipoAlerta(String tipoAlerta) {
        this.tipoAlerta = tipoAlerta;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

	

	public List<AlertaSubscriptorViewModel> getSubscriptores() {
		return subscriptores;
	}

	public void setSubscriptores(List<AlertaSubscriptorViewModel> subscriptores) {
		this.subscriptores = subscriptores;
	}

	public String getTipoNotificacion() {
		return tipoNotificacion;
	}

	public void setTipoNotificacion(String tipoNotificacion) {
		this.tipoNotificacion = tipoNotificacion;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public String getAlerta() {
		return alerta;
	}

	public void setAlerta(String alerta) {
		this.alerta = alerta;
	}

	public Integer getJefeProyecto() {
		return jefeProyecto;
	}

	public void setJefeProyecto(Integer jefeProyecto) {
		this.jefeProyecto = jefeProyecto;
	}

	public Integer getJefeDepartamento() {
		return jefeDepartamento;
	}

	public void setJefeDepartamento(Integer jefeDepartamento) {
		this.jefeDepartamento = jefeDepartamento;
	}

	public Integer getJefeUnidad() {
		return jefeUnidad;
	}

	public void setJefeUnidad(Integer jefeUnidad) {
		this.jefeUnidad = jefeUnidad;
	}

	public Integer getJefeEmpresa() {
		return jefeEmpresa;
	}

	public void setJefeEmpresa(Integer jefeEmpresa) {
		this.jefeEmpresa = jefeEmpresa;
	}

	public String getAgrupamiento() {
		return agrupamiento;
	}

	public void setAgrupamiento(String agrupamiento) {
		this.agrupamiento = agrupamiento;
	}

	public String getCodigoEstado() {
		return codigoEstado;
	}

	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	public String getCodigoTipoAlerta() {
		return codigoTipoAlerta;
	}

	public void setCodigoTipoAlerta(String codigoTipoAlerta) {
		this.codigoTipoAlerta = codigoTipoAlerta;
	}

	public String getCodigoTipoNotificacion() {
		return codigoTipoNotificacion;
	}

	public void setCodigoTipoNotificacion(String codigoTipoNotificacion) {
		this.codigoTipoNotificacion = codigoTipoNotificacion;
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

	public Long getIdAlertaSubscriptor() {
		return idAlertaSubscriptor;
	}

	public void setIdAlertaSubscriptor(Long idAlertaSubscriptor) {
		this.idAlertaSubscriptor = idAlertaSubscriptor;
	}
	
}
