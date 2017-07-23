package pe.com.tss.runakuna.domain.model.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import pe.com.tss.runakuna.domain.model.base.AuditingEntity;

/**
 * Created by ocastillo on 12/01/2017.
 */
@Entity
@Table(name = "UsuarioReset")
@NamedQuery(name = "UsuarioReset.findAll", query = "SELECT u FROM UsuarioReset u")
public class UsuarioReset extends AuditingEntity implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idUsuarioReset;
	private Usuario usuario;
    private String estado;
    private Date fechaInicio;
    private Date fechaFin;
    private String enlace;

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdUsuarioReset")
    public Long getIdUsuarioReset() {
        return idUsuarioReset;
    }

    public void setIdUsuarioReset(Long idUsuarioReset) {
        this.idUsuarioReset = idUsuarioReset;
    }

    @ManyToOne()
    @JoinColumn(name = "IdUsuario")
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Column(name = "Estado")
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Column(name = "FechaInicio")
	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	@Column(name = "FechaFin")
	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Column(name = "Enlace")
	public String getEnlace() {
		return enlace;
	}

	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}

	
	
}
