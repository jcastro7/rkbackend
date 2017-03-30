package pe.com.empresa.rk.view.model;

public class NotificacionEmpleadoViewModel extends NotificacionViewModel {

	
	private EmpleadoViewModel empleadoDto;
	private PeriodoEmpleadoViewModel periodoEmpleadoDto;
	
	
	public PeriodoEmpleadoViewModel getPeriodoEmpleadoDto() {
		return periodoEmpleadoDto;
	}
	public void setPeriodoEmpleadoDto(PeriodoEmpleadoViewModel periodoEmpleadoDto) {
		this.periodoEmpleadoDto = periodoEmpleadoDto;
	}
	public EmpleadoViewModel getEmpleadoDto() {
		return empleadoDto;
	}
	public void setEmpleadoDto(EmpleadoViewModel empleadoDto) {
		this.empleadoDto = empleadoDto;
	}
	
}
