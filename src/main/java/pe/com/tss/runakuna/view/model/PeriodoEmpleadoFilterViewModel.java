package pe.com.tss.runakuna.view.model;

public class PeriodoEmpleadoFilterViewModel extends FilterViewModel {

	private Long idEmpleado;
    private String nombreEmpleado;
	
	private Long unidadNegocio;
    private Long departamento;
    private Long proyecto;
    
    private Long idJefeInmediato;
    private String jefeInmediato;
    private boolean vigente;
	public Long getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}
	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}
	public Long getUnidadNegocio() {
		return unidadNegocio;
	}
	public void setUnidadNegocio(Long unidadNegocio) {
		this.unidadNegocio = unidadNegocio;
	}
	public Long getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Long departamento) {
		this.departamento = departamento;
	}
	public Long getProyecto() {
		return proyecto;
	}
	public void setProyecto(Long proyecto) {
		this.proyecto = proyecto;
	}
	public Long getIdJefeInmediato() {
		return idJefeInmediato;
	}
	public void setIdJefeInmediato(Long idJefeInmediato) {
		this.idJefeInmediato = idJefeInmediato;
	}
	public String getJefeInmediato() {
		return jefeInmediato;
	}
	public void setJefeInmediato(String jefeInmediato) {
		this.jefeInmediato = jefeInmediato;
	}
	public boolean isVigente() {
		return vigente;
	}
	public void setVigente(boolean vigente) {
		this.vigente = vigente;
	}
	
}
