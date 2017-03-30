package pe.com.empresa.rk.domain.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import pe.com.empresa.rk.domain.model.base.AuditingEntity;

@Entity
@Table(name = "Autorizacion")
@NamedQuery(name = "Autorizacion.findAll", query = "SELECT e FROM Autorizacion e")
public class Autorizacion extends AuditingEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long idAutorizacion;
	private Rol rol;
	private Accion accion;
	private Integer autorizado;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdAutorizacion")
	public Long getIdAutorizacion() {
		return idAutorizacion;
	}
	public void setIdAutorizacion(Long idAutorizacion) {
		this.idAutorizacion = idAutorizacion;
	}
	
	@ManyToOne()
    @JoinColumn(name = "IdRol")
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}	
	
	@Column(name = "Autorizado")
	public Integer getAutorizado() {
		return autorizado;
	}
	public void setAutorizado(Integer autorizado) {
		this.autorizado = autorizado;
	}
	
	@ManyToOne()
    @JoinColumn(name = "IdAccion")
	public Accion getAccion() {
		return accion;
	}
	public void setAccion(Accion accion) {
		this.accion = accion;
	}	

}
