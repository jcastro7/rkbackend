package pe.com.tss.runakuna.domain.model.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import pe.com.tss.runakuna.domain.model.base.AuditingEntity;
import pe.com.tss.runakuna.view.model.AuditingEntityViewModel;

@Entity
@Table(name = "Modulo")
@NamedQuery(name = "Modulo.findAll", query = "SELECT e FROM Modulo e")
public class Modulo extends AuditingEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Long idModulo;
	private Modulo parent;
	private String nombre;
	private String etiquetaMenu;
	private String url;
	private String helpUrl;
	private Integer orden;
	private Integer visible;
	private String codigo;
	private Integer tieneSubModulo;
	private List<Accion> acciones;
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idModulo")
	public Long getIdModulo() {
		return idModulo;
	}
	public void setIdModulo(Long idModulo) {
		this.idModulo = idModulo;
	}
	
	@ManyToOne
    @JoinColumn(name = "IdParent")
	public Modulo getParent() {
		return parent;
	}
	public void setParent(Modulo parent) {
		this.parent = parent;
	}
	
	@Column(name="Nombre")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Column(name="EtiquetaMenu")
	public String getEtiquetaMenu() {
		return etiquetaMenu;
	}
	public void setEtiquetaMenu(String etiquetaMenu) {
		this.etiquetaMenu = etiquetaMenu;
	}
	@Column(name="Url")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Column(name="HelpUrl")
	public String getHelpUrl() {
		return helpUrl;
	}
	public void setHelpUrl(String helpUrl) {
		this.helpUrl = helpUrl;
	}
	@Column(name="Orden")
	public Integer getOrden() {
		return orden;
	}
	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	
	@Column(name="Visible")
	public Integer getVisible() {
		return visible;
	}
	public void setVisible(Integer visible) {
		this.visible = visible;
	}
	
	@Column(name="Codigo")
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	@Column(name="TieneSubModulo")
	public Integer getTieneSubModulo() {
		return tieneSubModulo;
	}
	public void setTieneSubModulo(Integer tieneSubModulo) {
		this.tieneSubModulo = tieneSubModulo;
	}
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},orphanRemoval=true, mappedBy = "modulo")
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.DELETE,
			org.hibernate.annotations.CascadeType.MERGE,
			org.hibernate.annotations.CascadeType.PERSIST})
	public List<Accion> getAcciones() {
		return acciones;
	}
	public void setAcciones(List<Accion> acciones) {
		this.acciones = acciones;
	}
	
}
