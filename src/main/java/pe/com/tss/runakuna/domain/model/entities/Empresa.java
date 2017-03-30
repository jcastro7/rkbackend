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
import javax.persistence.JoinTable;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.WhereJoinTable;

import pe.com.tss.runakuna.domain.model.base.AuditingEntity;

@Entity
@Table(name = "Empresa")
@NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e")
public class Empresa extends AuditingEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long idEmpresa;
	private String codigo;
	private String razonSocial;
	private String ruc;
	private String logo;
	private String estado;
	
	private TablaGeneral nombreEstado;
	
	private List<UnidadDeNegocio> unidadesDeNegocio;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdEmpresa")
	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	@Column(name = "Codigo")
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Column(name = "RazonSocial")
	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	@Column(name = "RUC")
	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	@Column(name = "Logo")
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Column(name = "Estado")
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},orphanRemoval=true, mappedBy = "empresa")
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.DELETE,
			org.hibernate.annotations.CascadeType.MERGE,
			org.hibernate.annotations.CascadeType.PERSIST
	})
	public List<UnidadDeNegocio> getUnidadesDeNegocio() {
		return unidadesDeNegocio;
	}

	public void setUnidadesDeNegocio(List<UnidadDeNegocio> unidadesDeNegocio) {
		this.unidadesDeNegocio = unidadesDeNegocio;
	} 
	
}