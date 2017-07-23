package pe.com.tss.runakuna.view.model;

import java.util.List;

public class VacacionEmpleadoPlanillaViewModel {
	
	private Integer mes;
	private Integer anio;
	private List<VacacionResultViewModel> vacacionesEnPlanilla;
	
	public Integer getMes() {
		return mes;
	}
	public void setMes(Integer mes) {
		this.mes = mes;
	}
	public Integer getAnio() {
		return anio;
	}
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	public List<VacacionResultViewModel> getVacacionesEnPlanilla() {
		return vacacionesEnPlanilla;
	}
	public void setVacacionesEnPlanilla(List<VacacionResultViewModel> vacacionesEnPlanilla) {
		this.vacacionesEnPlanilla = vacacionesEnPlanilla;
	}
	
}
