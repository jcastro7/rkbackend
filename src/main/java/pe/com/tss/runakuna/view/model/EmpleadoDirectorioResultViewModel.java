package pe.com.tss.runakuna.view.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.tss.runakuna.json.JsonFlagIntegerDeserializer;
import pe.com.tss.runakuna.json.JsonFlagIntegerSerializer;
import pe.com.tss.runakuna.json.JsonVarbinaryDeserializer;
import pe.com.tss.runakuna.json.JsonVarbinarySerializer;

public class EmpleadoDirectorioResultViewModel extends PageableFilter {

	@JsonSerialize(using=JsonVarbinarySerializer.class)
	@JsonDeserialize(using=JsonVarbinaryDeserializer.class)
	private byte[] foto;
	private String nombreCompleto;
	private String proyecto;
	private String cargo;
	private String anexoInterno;
	private String emailInterno;
	
	private String telefonoCasa;
	private String telefonoCelular;
	private String telefonoAdicional;
	private String emailPersonal;
	
	private Long idEmpleado;
	
	@JsonSerialize(using=JsonFlagIntegerSerializer.class)
	@JsonDeserialize(using=JsonFlagIntegerDeserializer.class)
	private Integer publicarTelefonoCasa;
	
	@JsonSerialize(using=JsonFlagIntegerSerializer.class)
	@JsonDeserialize(using=JsonFlagIntegerDeserializer.class)
	private Integer publicarTelefonoPersonal;
	
	@JsonSerialize(using=JsonFlagIntegerSerializer.class)
	@JsonDeserialize(using=JsonFlagIntegerDeserializer.class)
	private Integer publicarTelefonoAdicional;
	
	@JsonSerialize(using=JsonFlagIntegerSerializer.class)
	@JsonDeserialize(using=JsonFlagIntegerDeserializer.class)
	private Integer publicarEmailPersonal;
	
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getProyecto() {
		return proyecto;
	}
	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getAnexoInterno() {
		return anexoInterno;
	}
	public void setAnexoInterno(String anexoInterno) {
		this.anexoInterno = anexoInterno;
	}
	public String getEmailInterno() {
		return emailInterno;
	}
	public void setEmailInterno(String emailInterno) {
		this.emailInterno = emailInterno;
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
	public String getTelefonoAdicional() {
		return telefonoAdicional;
	}
	public void setTelefonoAdicional(String telefonoAdicional) {
		this.telefonoAdicional = telefonoAdicional;
	}
	public String getEmailPersonal() {
		return emailPersonal;
	}
	public void setEmailPersonal(String emailPersonal) {
		this.emailPersonal = emailPersonal;
	}
	public Integer getPublicarTelefonoCasa() {
		return publicarTelefonoCasa;
	}
	public void setPublicarTelefonoCasa(Integer publicarTelefonoCasa) {
		this.publicarTelefonoCasa = publicarTelefonoCasa;
	}
	public Integer getPublicarTelefonoPersonal() {
		return publicarTelefonoPersonal;
	}
	public void setPublicarTelefonoPersonal(Integer publicarTelefonoPersonal) {
		this.publicarTelefonoPersonal = publicarTelefonoPersonal;
	}
	public Integer getPublicarTelefonoAdicional() {
		return publicarTelefonoAdicional;
	}
	public void setPublicarTelefonoAdicional(Integer publicarTelefonoAdicional) {
		this.publicarTelefonoAdicional = publicarTelefonoAdicional;
	}
	public Integer getPublicarEmailPersonal() {
		return publicarEmailPersonal;
	}
	public void setPublicarEmailPersonal(Integer publicarEmailPersonal) {
		this.publicarEmailPersonal = publicarEmailPersonal;
	}
	public Long getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	
}