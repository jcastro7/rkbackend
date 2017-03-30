package pe.com.empresa.rk.view.model;

/**
 * Created by josediaz on 14/12/2016.
 */
public class AlertaEmpleadoViewModel {

    
	private Long idAlerta;
    private Long idEmpleado;
    private String mensaje;
    private String estado;
    

    public Long getIdAlerta() {
        return idAlerta;
    }

    public void setIdAlerta(Long idAlerta) {
        this.idAlerta = idAlerta;
    }

	public Long getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
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

   
}
