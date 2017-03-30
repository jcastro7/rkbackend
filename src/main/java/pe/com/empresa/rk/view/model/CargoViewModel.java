package pe.com.empresa.rk.view.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CargoViewModel extends AuditingEntityViewModel {
	
	private Long idCargo;
	private Long idEmpresa;
	private Long idUnidadDeNegocio;
	private Long idDepartamentoArea;
	private Long idProyecto;
	private String nombre;
	private String descripcion;
	private String horasSemanal;
	private Date fechaInicio;
	private Date fechaFin;
	private BigDecimal salario;
	private Long idSuperior;
	private List<BandaSalarialViewModel> bandasSalariales;
	private String nombreDepartamento;
	private String nombreUnidadNegocio;
	private String nombreCargoSuperior;
	private String estado;
	private String nombreEstado;
	private String nombreProyecto;
	
	public Long getIdCargo() {
		return idCargo;
	}
	public void setIdCargo(Long idCargo) {
		this.idCargo = idCargo;
	}
	public Long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public Long getIdUnidadDeNegocio() {
		return idUnidadDeNegocio;
	}
	public void setIdUnidadDeNegocio(Long idUnidadDeNegocio) {
		this.idUnidadDeNegocio = idUnidadDeNegocio;
	}
	public Long getIdDepartamentoArea() {
		return idDepartamentoArea;
	}
	public void setIdDepartamentoArea(Long idDepartamentoArea) {
		this.idDepartamentoArea = idDepartamentoArea;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getHorasSemanal() {
		return horasSemanal;
	}
	public void setHorasSemanal(String horasSemanal) {
		this.horasSemanal = horasSemanal;
	}
	public Long getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(Long idProyecto) {
		this.idProyecto = idProyecto;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public BigDecimal getSalario() {
		return salario;
	}
	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}
	public Long getIdSuperior() {
		return idSuperior;
	}
	public void setIdSuperior(Long idSuperior) {
		this.idSuperior = idSuperior;
	}
	public List<BandaSalarialViewModel> getBandasSalariales() {
		return bandasSalariales;
	}
	public void setBandasSalariales(List<BandaSalarialViewModel> bandasSalariales) {
		this.bandasSalariales = bandasSalariales;
	}
	public String getNombreDepartamento() {
		return nombreDepartamento;
	}
	public void setNombreDepartamento(String nombreDepartamento) {
		this.nombreDepartamento = nombreDepartamento;
	}
	public String getNombreUnidadNegocio() {
		return nombreUnidadNegocio;
	}
	public void setNombreUnidadNegocio(String nombreUnidadNegocio) {
		this.nombreUnidadNegocio = nombreUnidadNegocio;
	}
	public String getNombreCargoSuperior() {
		return nombreCargoSuperior;
	}
	public void setNombreCargoSuperior(String nombreCargoSuperior) {
		this.nombreCargoSuperior = nombreCargoSuperior;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getNombreEstado() {
		return nombreEstado;
	}
	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}
	public String getNombreProyecto() {
		return nombreProyecto;
	}
	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}
	
}
