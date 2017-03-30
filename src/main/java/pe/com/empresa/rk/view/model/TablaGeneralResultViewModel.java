package pe.com.empresa.rk.view.model;

public class TablaGeneralResultViewModel extends AuditingEntityViewModel{

	private Long idTablaGeneral;
	private String codigo;
	private String nombre;
	private String grupo;
	private String estado;

	public Long getIdTablaGeneral() {
		return idTablaGeneral;
	}
	public void setIdTablaGeneral(Long idTablaGeneral) {
		this.idTablaGeneral = idTablaGeneral;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}



}
