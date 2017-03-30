package pe.com.empresa.rk.domain.model.entities;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "Proyecto")
@NamedQuery(name = "Proyecto.findAll", query = "SELECT o FROM Proyecto o")
public class Proyecto extends AuditingEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long idProyecto;
	private DepartamentoArea departamentoArea;
	private Empleado jefe;
	private String codigo;
	private String nombre;
	private String descripcion;
	private String estado;
	private Empleado jefeReemplazo;
	private Integer jefeNoDisponible;
	private String cliente;
	private Date fechaInicio;
	private Date fechaFin;
	
	public Proyecto() { }

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdProyecto")
	public Long getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(Long idProyecto) {
		this.idProyecto = idProyecto;
	}

	@ManyToOne
    @JoinColumn(name = "IdDepartamentoArea")
	public DepartamentoArea getDepartamentoArea() {
		return departamentoArea;
	}

	public void setDepartamentoArea(DepartamentoArea departamentoArea) {
		this.departamentoArea = departamentoArea;
	}
	
	@Column(name="Nombre")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name="Descripcion")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name="Codigo")
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Column(name="Estado")
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@ManyToOne
    @JoinColumn(name = "IdJefe")
	public Empleado getJefe() {
		return jefe;
	}

	public void setJefe(Empleado jefe) {
		this.jefe = jefe;
	}

	@ManyToOne
    @JoinColumn(name = "IdJefeReemplazo")
	public Empleado getJefeReemplazo() {
		return jefeReemplazo;
	}

	public void setJefeReemplazo(Empleado jefeReemplazo) {
		this.jefeReemplazo = jefeReemplazo;
	}

	@Column(name="JefeNoDisponible")
	public Integer getJefeNoDisponible() {
		return jefeNoDisponible;
	}

	public void setJefeNoDisponible(Integer jefeNoDisponible) {
		this.jefeNoDisponible = jefeNoDisponible;
	}

	@Column(name="Cliente")
	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	@Column(name="FechaInicio")
	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	@Column(name="FechaFin")
	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
}
