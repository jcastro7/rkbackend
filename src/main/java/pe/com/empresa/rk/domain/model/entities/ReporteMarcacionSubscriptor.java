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
@Table(name = "ReporteMarcacionSubscriptor")
@NamedQuery(name = "ReporteMarcacionSubscriptor.findAll", query = "SELECT e FROM ReporteMarcacionSubscriptor e")
public class ReporteMarcacionSubscriptor extends AuditingEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long idReporteMarcacionSubscriptor;
	private ReporteMarcacion reporteMarcacion;
	private Empleado empleado;
	
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdReporteMarcacionSubscriptor")
	public Long getIdReporteMarcacionSubscriptor() {
		return idReporteMarcacionSubscriptor;
	}
	public void setIdReporteMarcacionSubscriptor(Long idReporteMarcacionSubscriptor) {
		this.idReporteMarcacionSubscriptor = idReporteMarcacionSubscriptor;
	}
	
	@ManyToOne()
    @JoinColumn(name = "IdEmpleado")
	public Empleado getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	
	@ManyToOne()
    @JoinColumn(name = "IdReporteMarcacion")
	public ReporteMarcacion getReporteMarcacion() {
		return reporteMarcacion;
	}
	public void setReporteMarcacion(ReporteMarcacion reporteMarcacion) {
		this.reporteMarcacion = reporteMarcacion;
	}
	
	
	
	
	
	
		      

}
