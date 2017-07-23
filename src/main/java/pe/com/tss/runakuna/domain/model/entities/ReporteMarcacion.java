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
@Table(name = "ReporteMarcacion")
@NamedQuery(name = "ReporteMarcacion.findAll", query = "SELECT e FROM ReporteMarcacion e")
public class ReporteMarcacion extends AuditingEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long idReporteMarcacion;
	private Empresa empresa;
	private UnidadDeNegocio unidadDeNegocio;
	private DepartamentoArea departamentoArea;
	private Proyecto proyecto;
	private Empleado jefeProyecto;
	private Integer reporteDiario;
	private Integer reporteAcumulado;
	private String estado;
	
	private List<ReporteMarcacionSubscriptor> subscriptores;
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdReporteMarcacion")
	public Long getIdReporteMarcacion() {
		return idReporteMarcacion;
	}
	public void setIdReporteMarcacion(Long idReporteMarcacion) {
		this.idReporteMarcacion = idReporteMarcacion;
	}
	
	@ManyToOne()
    @JoinColumn(name = "IdEmpresa")
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	
	
	@Column(name = "Estado")
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},orphanRemoval=true, mappedBy = "reporteMarcacion")
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.DELETE,
			org.hibernate.annotations.CascadeType.MERGE,
			org.hibernate.annotations.CascadeType.PERSIST})
	public List<ReporteMarcacionSubscriptor> getSubscriptores() {
		return subscriptores;
	}
	public void setSubscriptores(List<ReporteMarcacionSubscriptor> subscriptores) {
		this.subscriptores = subscriptores;
	}
	
	
	public ReporteMarcacionSubscriptor removeReporteMarcacionSubscriptor(ReporteMarcacionSubscriptor reporteMarcacionSubscriptor) {
    	getSubscriptores().remove(reporteMarcacionSubscriptor);
    	reporteMarcacionSubscriptor.setReporteMarcacion(null);

        return reporteMarcacionSubscriptor;
    }
	
	@ManyToOne()
    @JoinColumn(name = "IdUnidadDeNegocio")
	public UnidadDeNegocio getUnidadDeNegocio() {
		return unidadDeNegocio;
	}
	public void setUnidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
		this.unidadDeNegocio = unidadDeNegocio;
	}
	
	@ManyToOne()
    @JoinColumn(name = "IdDepartamentoArea")
	public DepartamentoArea getDepartamentoArea() {
		return departamentoArea;
	}
	public void setDepartamentoArea(DepartamentoArea departamentoArea) {
		this.departamentoArea = departamentoArea;
	}
	
	@ManyToOne()
    @JoinColumn(name = "IdProyecto")
	public Proyecto getProyecto() {
		return proyecto;
	}
	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}
	
	@ManyToOne()
    @JoinColumn(name = "IdJefe")
	public Empleado getJefeProyecto() {
		return jefeProyecto;
	}
	public void setJefeProyecto(Empleado jefeProyecto) {
		this.jefeProyecto = jefeProyecto;
	}
	
	@Column(name = "ReporteDiario")
	public Integer getReporteDiario() {
		return reporteDiario;
	}
	public void setReporteDiario(Integer reporteDiario) {
		this.reporteDiario = reporteDiario;
	}
	
	@Column(name = "ReporteAcumulado")
	public Integer getReporteAcumulado() {
		return reporteAcumulado;
	}
	public void setReporteAcumulado(Integer reporteAcumulado) {
		this.reporteAcumulado = reporteAcumulado;
	}
	
	
	
	
		      

}
