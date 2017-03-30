package pe.com.empresa.rk.view.model;

import java.util.List;

public class CompensacionDetalleViewModel {

	private CompensacionViewModel compensacion;
	
	private List<PermisoEmpleadoViewModel> permisosEmpleado;
	
	private List<HorasExtraViewModel> horasExtras;

	public CompensacionViewModel getCompensacion() {
		return compensacion;
	}

	public void setCompensacion(CompensacionViewModel compensacion) {
		this.compensacion = compensacion;
	}

	public List<PermisoEmpleadoViewModel> getPermisosEmpleado() {
		return permisosEmpleado;
	}

	public void setPermisosEmpleado(List<PermisoEmpleadoViewModel> permisosEmpleado) {
		this.permisosEmpleado = permisosEmpleado;
	}

	public List<HorasExtraViewModel> getHorasExtras() {
		return horasExtras;
	}

	public void setHorasExtras(List<HorasExtraViewModel> horasExtras) {
		this.horasExtras = horasExtras;
	}

}
