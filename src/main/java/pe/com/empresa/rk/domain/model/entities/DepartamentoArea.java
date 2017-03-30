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
@Table(name = "DepartamentoArea")
@NamedQuery(name = "DepartamentoArea.findAll", query = "SELECT d FROM DepartamentoArea d")
public class DepartamentoArea extends AuditingEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long idDepartamentoArea;
	private UnidadDeNegocio unidadDeNegocio;
	private String nombre;
	private String estado;
		
	public DepartamentoArea(){}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdDepartamentoArea")
	public Long getIdDepartamentoArea() {
		return idDepartamentoArea;
	}

	public void setIdDepartamentoArea(Long idDepartamentoArea) {
		this.idDepartamentoArea = idDepartamentoArea;
	}

	@ManyToOne()
    @JoinColumn(name = "IdUnidadDeNegocio")
	public UnidadDeNegocio getUnidadDeNegocio() {
		return this.unidadDeNegocio;
	}

	public void setUnidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
		this.unidadDeNegocio = unidadDeNegocio;
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
	
	
}
