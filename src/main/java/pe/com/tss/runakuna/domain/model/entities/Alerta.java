package pe.com.tss.runakuna.domain.model.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonIgnore;

import pe.com.tss.runakuna.domain.model.base.AuditingEntity;

@Entity
@Table(name = "Alerta")
@NamedQuery(name = "Alerta.findAll", query = "SELECT e FROM Alerta e")
public class Alerta extends AuditingEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long idAlerta;
	private Empresa empresa;
	private String tipoAlerta;
	private String codigo;
	private String tipoNotificacion;
	
	private String descripcion;
	private String asunto;
	private String cuerpo;
	private String alerta;
	
	private Integer jefeProyecto;
	private Integer jefeDepartamento;
	private Integer jefeUnidad;
	private Integer jefeEmpresa;
	private String agrupamiento;
	private String estado;
	private Integer alertaDashboard;
	private Integer correoElectronico;
	private Integer umbralAdvertencia;
    private String 	colorAdvertencia;
    private Integer umbralCritico;
    private String 	colorCritico;
	private List<AlertaSubscriptor> subscriptores;
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdAlerta")
	public Long getIdAlerta() {
		return idAlerta;
	}
	public void setIdAlerta(Long idAlerta) {
		this.idAlerta = idAlerta;
	}
	
	@ManyToOne()
    @JoinColumn(name = "IdEmpresa")
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	@Column(name = "TipoAlerta")
	public String getTipoAlerta() {
		return tipoAlerta;
	}
	public void setTipoAlerta(String tipoAlerta) {
		this.tipoAlerta = tipoAlerta;
	}
	
	@Column(name = "Codigo")
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
	@Column(name = "Estado")
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},orphanRemoval=true, mappedBy = "alerta",fetch=FetchType.EAGER)
	//@OneToMany(mappedBy = "alerta", cascade=CascadeType.ALL)
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.DELETE,
			org.hibernate.annotations.CascadeType.MERGE,
			org.hibernate.annotations.CascadeType.PERSIST})
	//@JsonIgnore
	public List<AlertaSubscriptor> getSubscriptores() {
		return subscriptores;
	}
	public void setSubscriptores(List<AlertaSubscriptor> subscriptores) {
		this.subscriptores = subscriptores;
	}
	
	
	public AlertaSubscriptor removeAlertaSubscriptor(AlertaSubscriptor alertaSubscriptor) {
    	getSubscriptores().remove(alertaSubscriptor);
    	alertaSubscriptor.setAlerta(null);

        return alertaSubscriptor;
    }
	
	@Column(name = "TipoNotificacion")
	public String getTipoNotificacion() {
		return tipoNotificacion;
	}
	public void setTipoNotificacion(String tipoNotificacion) {
		this.tipoNotificacion = tipoNotificacion;
	}
	
	@Column(name = "Asunto")
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	
	@Column(name = "Cuerpo")
	public String getCuerpo() {
		return cuerpo;
	}
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}
	
	@Column(name = "Alerta")
	public String getAlerta() {
		return alerta;
	}
	public void setAlerta(String alerta) {
		this.alerta = alerta;
	}
	
	@Column(name = "JefeProyecto")
	public Integer getJefeProyecto() {
		return jefeProyecto;
	}
	public void setJefeProyecto(Integer jefeProyecto) {
		this.jefeProyecto = jefeProyecto;
	}
	
	@Column(name = "JefeDepartamento")
	public Integer getJefeDepartamento() {
		return jefeDepartamento;
	}
	public void setJefeDepartamento(Integer jefeDepartamento) {
		this.jefeDepartamento = jefeDepartamento;
	}
	
	@Column(name = "JefeUnidad")
	public Integer getJefeUnidad() {
		return jefeUnidad;
	}
	public void setJefeUnidad(Integer jefeUnidad) {
		this.jefeUnidad = jefeUnidad;
	}
	
	@Column(name = "JefeEmpresa")
	public Integer getJefeEmpresa() {
		return jefeEmpresa;
	}
	public void setJefeEmpresa(Integer jefeEmpresa) {
		this.jefeEmpresa = jefeEmpresa;
	}
	
	@Column(name = "Agrupamiento")
	public String getAgrupamiento() {
		return agrupamiento;
	}
	public void setAgrupamiento(String agrupamiento) {
		this.agrupamiento = agrupamiento;
	}
	
	@Column(name = "Descripcion")
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Column(name = "AlertaDashboard")
	public Integer getAlertaDashboard() {
		return alertaDashboard;
	}
	public void setAlertaDashboard(Integer alertaDashboard) {
		this.alertaDashboard = alertaDashboard;
	}
	@Column(name = "CorreoElectronico")
	public Integer getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(Integer correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	public Integer getUmbralAdvertencia() {
		return umbralAdvertencia;
	}
	public void setUmbralAdvertencia(Integer umbralAdvertencia) {
		this.umbralAdvertencia = umbralAdvertencia;
	}
	public String getColorAdvertencia() {
		return colorAdvertencia;
	}
	public void setColorAdvertencia(String colorAdvertencia) {
		this.colorAdvertencia = colorAdvertencia;
	}
	public Integer getUmbralCritico() {
		return umbralCritico;
	}
	public void setUmbralCritico(Integer umbralCritico) {
		this.umbralCritico = umbralCritico;
	}
	public String getColorCritico() {
		return colorCritico;
	}
	public void setColorCritico(String colorCritico) {
		this.colorCritico = colorCritico;
	}
	
	
		      

}
