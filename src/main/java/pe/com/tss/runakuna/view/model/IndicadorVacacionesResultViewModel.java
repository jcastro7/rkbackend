package pe.com.tss.runakuna.view.model;

public class IndicadorVacacionesResultViewModel extends ResultViewModel {
	
	private String nombreEmpleado;
	private String periodo;
	private Integer diasHabiles;
	private String semaforo;
	
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}
	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public Integer getDiasHabiles() {
		return diasHabiles;
	}
	public void setDiasHabiles(Integer diasHabiles) {
		this.diasHabiles = diasHabiles;
	}
	public String getSemaforo() {
		return semaforo;
	}
	public void setSemaforo(String semaforo) {
		this.semaforo = semaforo;
	}

}
