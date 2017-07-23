package pe.com.tss.runakuna.view.model;

import java.util.List;

public class LocalStorageViewModel {
	
	private List<UnidadDeNegocioViewModel> unidadDeNegocio;
	private List<DepartamentoAreaViewModel> departamentoArea;
	private List<ProyectoComboViewModel> proyecto;
	private List<TablaGeneralViewModel> tablaGeneral;
	private List<TipoLicenciaViewModel> tipoLicencia;
	private List<MonedaViewModel> moneda;
	private List<CargoComboViewModel> cargo;

	public List<UnidadDeNegocioViewModel> getUnidadDeNegocio() {
		return unidadDeNegocio;
	}

	public void setUnidadDeNegocio(List<UnidadDeNegocioViewModel> unidadDeNegocio) {
		this.unidadDeNegocio = unidadDeNegocio;
	}

	public List<DepartamentoAreaViewModel> getDepartamentoArea() {
		return departamentoArea;
	}

	public void setDepartamentoArea(List<DepartamentoAreaViewModel> departamentoArea) {
		this.departamentoArea = departamentoArea;
	}

	public List<ProyectoComboViewModel> getProyecto() {
		return proyecto;
	}

	public void setProyecto(List<ProyectoComboViewModel> proyecto) {
		this.proyecto = proyecto;
	}

	public List<TablaGeneralViewModel> getTablaGeneral() {
		return tablaGeneral;
	}

	public void setTablaGeneral(List<TablaGeneralViewModel> tablaGeneral) {
		this.tablaGeneral = tablaGeneral;
	}

	public List<TipoLicenciaViewModel> getTipoLicencia() {
		return tipoLicencia;
	}

	public void setTipoLicencia(List<TipoLicenciaViewModel> tipoLicencia) {
		this.tipoLicencia = tipoLicencia;
	}

	public List<MonedaViewModel> getMoneda() {
		return moneda;
	}

	public void setMoneda(List<MonedaViewModel> moneda) {
		this.moneda = moneda;
	}

	public List<CargoComboViewModel> getCargo() {
		return cargo;
	}

	public void setCargo(List<CargoComboViewModel> cargo) {
		this.cargo = cargo;
	}
	
	
	

}
