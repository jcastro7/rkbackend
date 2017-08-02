package pe.com.tss.runakuna.view.model;

/**
 * Created by josediaz on 25/11/2016.
 */
public class MarcadorViewModel extends AuditingEntityViewModel{

    private Long idMarcador;
    private String codigo;
    private String nombre;
    private String descripcion;
    private Long idEmpresa;
    
	public Long getIdMarcador() {
		return idMarcador;
	}
	public void setIdMarcador(Long idMarcador) {
		this.idMarcador = idMarcador;
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
        
}

