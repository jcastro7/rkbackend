package pe.com.tss.runakuna.view.model;

public class AlertaResultViewModel extends ResultViewModel{

	private Long idAlerta;
	private String codigo;
	private String tipoAlerta;
	private String tipoNotificacion;
	private  String mensaje;
	private String estado;
	
	
	public Long getIdAlerta() {
		return idAlerta;
	}
	public void setIdAlerta(Long idAlerta) {
		this.idAlerta = idAlerta;
	}
	
	public String getTipoAlerta() {
		return tipoAlerta;
	}
	public void setTipoAlerta(String tipoAlerta) {
		this.tipoAlerta = tipoAlerta;
	}
	public String getTipoNotificacion() {
		return tipoNotificacion;
	}
	public void setTipoNotificacion(String tipoNotificacion) {
		this.tipoNotificacion = tipoNotificacion;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
