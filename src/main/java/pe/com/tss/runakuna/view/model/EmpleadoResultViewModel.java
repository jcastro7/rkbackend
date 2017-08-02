package pe.com.tss.runakuna.view.model;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.tss.runakuna.json.JsonDateSimpleDeserializer;
import pe.com.tss.runakuna.json.JsonDateSimpleSerializer;
import pe.com.tss.runakuna.util.DateUtil;
import pe.com.tss.runakuna.util.StringUtil;

public class EmpleadoResultViewModel extends PageableFilter {

	private Long idEmpleado;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String tipoDocumento;
	private String numeroDocumento;
	private String codigo;
	private String emailInterno;
	private String estado;
	private String cargo;
	
	private String genero;
	private String estadoCivil;
	private String grupoSangre;
	
	private String discapacitado;
	private Date fechaNacimiento;
	private String paisNacimiento;
	
	private String telefonoInterno;
	private String telefonoCasa;
	private String telefonoCelular;
	private String anexoInterno;

	private int antiguedad;
	
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date fechaIngreso;
	
	private String contratoTrabajo;
	
	private String centroCosto;
	
	private String categoriaEmpleado;
	
	public Long getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getEmailInterno() {
		return emailInterno;
	}
	public void setEmailInterno(String emailInterno) {
		this.emailInterno = emailInterno;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public String getGrupoSangre() {
		return grupoSangre;
	}
	public void setGrupoSangre(String grupoSangre) {
		this.grupoSangre = grupoSangre;
	}
	public String getDiscapacitado() {
		return discapacitado;
	}
	public void setDiscapacitado(String discapacitado) {
		this.discapacitado = discapacitado;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getPaisNacimiento() {
		return paisNacimiento;
	}
	public void setPaisNacimiento(String paisNacimiento) {
		this.paisNacimiento = paisNacimiento;
	}
	public String getTelefonoInterno() {
		return telefonoInterno;
	}
	public void setTelefonoInterno(String telefonoInterno) {
		this.telefonoInterno = telefonoInterno;
	}
	public String getAnexoInterno() {
		return anexoInterno;
	}
	public void setAnexoInterno(String anexoInterno) {
		this.anexoInterno = anexoInterno;
	}
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public String getTelefonoCasa() {
		return telefonoCasa;
	}
	public void setTelefonoCasa(String telefonoCasa) {
		this.telefonoCasa = telefonoCasa;
	}
	public String getTelefonoCelular() {
		return telefonoCelular;
	}
	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}

	public int getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getContratoTrabajo() {
		return contratoTrabajo;
	}
	public void setContratoTrabajo(String contratoTrabajo) {
		this.contratoTrabajo = contratoTrabajo;
	}
	
	public String getCentroCosto() {
		return centroCosto;
	}
	public void setCentroCosto(String centroCosto) {
		this.centroCosto = centroCosto;
	}
	public String getCategoriaEmpleado() {
		return categoriaEmpleado;
	}
	public void setCategoriaEmpleado(String categoriaEmpleado) {
		this.categoriaEmpleado = categoriaEmpleado;
	}
	public String getFechaCumpleanio() {
		if(getFechaNacimiento() == null){
			return null;
		}
		
		return StringUtil.autocompleteZeroLeft(String.valueOf(DateUtil.getDayOfMonth(getFechaNacimiento()))) +
				" de "+StringUtil.toCamelCase(StringUtil.retornarMesNombre(DateUtil.getNumberMonth(getFechaNacimiento())));
	}
	
	
}