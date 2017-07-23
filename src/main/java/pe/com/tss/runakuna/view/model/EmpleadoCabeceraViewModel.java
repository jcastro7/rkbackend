package pe.com.tss.runakuna.view.model;

public class EmpleadoCabeceraViewModel {

	private Long idEmpleado;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	

	private DocumentoEmpleadoViewModel fotoPerfil;
	
	
	private String nombreCompletoEmpleado;
	
	public EmpleadoCabeceraViewModel(){
		
	}

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
	
	public String getNombreCompletoEmpleado() {
		return nombreCompletoEmpleado;
	}

	public void setNombreCompletoEmpleado(String nombreCompletoEmpleado) {
		this.nombreCompletoEmpleado = nombreCompletoEmpleado;
	}

	public DocumentoEmpleadoViewModel getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(DocumentoEmpleadoViewModel fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}
	
}