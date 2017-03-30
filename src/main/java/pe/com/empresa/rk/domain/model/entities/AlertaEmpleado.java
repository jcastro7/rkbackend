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
@Table(name = "AlertaEmpleado")
@NamedQuery(name = "AlertaEmpleado.findAll", query = "SELECT e FROM AlertaEmpleado e")
public class AlertaEmpleado extends AuditingEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long idAlertaEmpleado;
	private Alerta alerta;
	private Empleado empleado;
	private String mensaje;
	private String estado;
	
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdAlertaEmpleado")
	public Long getIdAlertaEmpleado() {
		return idAlertaEmpleado;
	}
	public void setIdAlertaEmpleado(Long idAlertaEmpleado) {
		this.idAlertaEmpleado = idAlertaEmpleado;
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
    @JoinColumn(name = "IdAlerta")
	public Alerta getAlerta() {
		return alerta;
	}
	public void setAlerta(Alerta alerta) {
		this.alerta = alerta;
	}
	
	@Column(name = "Mensaje")
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	@Column(name = "Estado")
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
	
	
	
		      

}
