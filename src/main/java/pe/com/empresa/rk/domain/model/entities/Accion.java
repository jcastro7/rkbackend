package pe.com.empresa.rk.domain.model.entities;

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
@Table(name = "Accion")
@NamedQuery(name = "Accion.findAll", query = "SELECT e FROM Accion e")
public class Accion extends AuditingEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Long idAccion;
	private Modulo modulo;
	private String nombre;
	private String etiqueta;
	private String tipoAccion;
	private Integer autorizacionDefecto;
	private Integer editable;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAccion")
	public Long getIdAccion() {
		return idAccion;
	}
	public void setIdAccion(Long idAccion) {
		this.idAccion = idAccion;
	}
	
	@ManyToOne
    @JoinColumn(name = "IdModulo")
	public Modulo getModulo() {
		return modulo;
	}
	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}
	
	@Column(name="Nombre")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Column(name="Etiqueta")
	public String getEtiqueta() {
		return etiqueta;
	}
	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}
	
	@Column(name="TipoAccion")
	public String getTipoAccion() {
		return tipoAccion;
	}
	public void setTipoAccion(String tipoAccion) {
		this.tipoAccion = tipoAccion;
	}
	
	@Column(name="AutorizacionDefecto")
	public Integer getAutorizacionDefecto() {
		return autorizacionDefecto;
	}
	public void setAutorizacionDefecto(Integer autorizacionDefecto) {
		this.autorizacionDefecto = autorizacionDefecto;
	}
	
	@Column(name="Editable")
	public Integer getEditable() {
		return editable;
	}
	public void setEditable(Integer editable) {
		this.editable = editable;
	}
	
	
	

	
	
	
	
}
