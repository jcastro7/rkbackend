package pe.com.tss.runakuna.domain.model.entities;

import java.io.Serializable;
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

@Entity
@Table(name = "UnidadDeNegocio")
@NamedQuery(name = "UnidadDeNegocio.findAll", query = "SELECT u FROM UnidadDeNegocio u")
public class UnidadDeNegocio extends AuditingEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long idUnidadDeNegocio;
	private Empresa empresa;
	private String nombre;
	private String estado;

	private List<DepartamentoArea> departamentosArea;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdUnidadDeNegocio")
	public Long getIdUnidadDeNegocio() {
		return idUnidadDeNegocio;
	}

	public void setIdUnidadDeNegocio(Long idUnidadDeNegocio) {
		this.idUnidadDeNegocio = idUnidadDeNegocio;
	}

	@ManyToOne
    @JoinColumn(name = "IdEmpresa")
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},orphanRemoval=true, mappedBy = "unidadDeNegocio")
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.DELETE,
			org.hibernate.annotations.CascadeType.MERGE,
			org.hibernate.annotations.CascadeType.PERSIST
	})
	public List<DepartamentoArea> getDepartamentosArea() {
		return departamentosArea;
	}

	public void setDepartamentosArea(List<DepartamentoArea> departamentosArea) {
		this.departamentosArea = departamentosArea;
	}
	
	
	
}
