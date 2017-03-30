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
@Table(name = "Cargo")
@NamedQuery(name = "Cargo.findAll", query = "SELECT o FROM Cargo o")
public class Cargo extends AuditingEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long idCargo;
	private Empresa empresa;
	private UnidadDeNegocio unidadDeNegocio;
	private DepartamentoArea departamentoArea;
	private Proyecto proyecto;
	private Cargo superior;
	private String nombre;
	private String descripcion;
	private String estado;
	
	private List<BandaSalarial> bandasSalariales;
	
	public Cargo() { }

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCargo")
	public Long getIdCargo() {
		return idCargo;
	}

	public void setIdCargo(Long idCargo) {
		this.idCargo = idCargo;
	}

	@ManyToOne
    @JoinColumn(name = "IdEmpresa")
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@ManyToOne
    @JoinColumn(name = "IdUnidadDeNegocio")
	public UnidadDeNegocio getUnidadDeNegocio() {
		return unidadDeNegocio;
	}

	public void setUnidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
		this.unidadDeNegocio = unidadDeNegocio;
	}

	@ManyToOne
    @JoinColumn(name = "IdDepartamentoArea")
	public DepartamentoArea getDepartamentoArea() {
		return departamentoArea;
	}

	public void setDepartamentoArea(DepartamentoArea departamentoArea) {
		this.departamentoArea = departamentoArea;
	}

	@Column(name = "Nombre")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "Descripcion")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@ManyToOne
    @JoinColumn(name = "IdSuperior")
	public Cargo getSuperior() {
		return superior;
	}

	public void setSuperior(Cargo superior) {
		this.superior = superior;
	}

	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},orphanRemoval=true, mappedBy = "cargo")
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.DELETE,
			org.hibernate.annotations.CascadeType.MERGE,
			org.hibernate.annotations.CascadeType.PERSIST})
	public List<BandaSalarial> getBandasSalariales() {
		return bandasSalariales;
	}

	public void setBandasSalariales(List<BandaSalarial> bandasSalariales) {
		this.bandasSalariales = bandasSalariales;
	}
	
	public BandaSalarial removeBandaSalarial(BandaSalarial bandaSalarial) {
    	getBandasSalariales().remove(bandaSalarial);
    	bandaSalarial.setCargo(null);

        return bandaSalarial;
    }
	
	@ManyToOne
    @JoinColumn(name = "IdProyecto")
	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
