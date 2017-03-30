package pe.com.empresa.rk.view.model;

import java.util.List;

import pe.com.empresa.rk.domain.model.entities.Empleado;

public class UsuarioViewModel extends AuditingEntityViewModel{

	private Long idUsuario;
	private Empleado empleado;
	private Long idEmpleado;
	private String cuentaUsuario;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String email;
	private String estado;
    private String password;
    private String nombreEmpleado;
	
	private List<UsuarioRolViewModel> usuarioRol;
	private List<RolResultViewModel> allRol;
	
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Empleado getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	public String getCuentaUsuario() {
		return cuentaUsuario;
	}
	public void setCuentaUsuario(String cuentaUsuario) {
		this.cuentaUsuario = cuentaUsuario;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Long getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public List<UsuarioRolViewModel> getUsuarioRol() {
		return usuarioRol;
	}
	public void setUsuarioRol(List<UsuarioRolViewModel> usuarioRol) {
		this.usuarioRol = usuarioRol;
	}
	public List<RolResultViewModel> getAllRol() {
		return allRol;
	}
	public void setAllRol(List<RolResultViewModel> allRol) {
		this.allRol = allRol;
	}
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}
	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}
	
	
	
}
