package pe.com.tss.runakuna.view.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import pe.com.tss.runakuna.json.JsonVarbinaryDeserializer;
import pe.com.tss.runakuna.json.JsonVarbinarySerializer;

import java.util.Arrays;
import java.util.List;

public class CurrentUser {
	
	private Long idEmpleado;
	private Long idUsuario;
	private String nombreCompleto;
	private String email;
	private Long idEmpresa;
	private String genero;

	private String cuentaUsuario;
	private String build;
	private String revision;
	private String timestamp;

	@JsonSerialize(using=JsonVarbinarySerializer.class)
	@JsonDeserialize(using=JsonVarbinaryDeserializer.class)
	private byte[] foto;
	private List<AssignedRole> assignedRoles;
	
	public CurrentUser(){}

	public Long getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public List<AssignedRole> getAssignedRoles() {
		return assignedRoles;
	}

	public void setAssignedRoles(List<AssignedRole> assignedRoles) {
		this.assignedRoles = assignedRoles;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getBuild() {
		return build;
	}

	public void setBuild(String build) {
		this.build = build;
	}

	public String getRevision() {
		return revision;
	}

	public void setRevision(String revision) {
		this.revision = revision;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}


	public String getCuentaUsuario() {
		return cuentaUsuario;
	}

	public void setCuentaUsuario(String cuentaUsuario) {
		this.cuentaUsuario = cuentaUsuario;
	}
}
