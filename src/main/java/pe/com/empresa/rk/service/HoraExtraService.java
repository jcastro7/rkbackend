package pe.com.empresa.rk.service;

import java.util.List;

import pe.com.empresa.rk.view.model.EmpleadoViewModel;
import pe.com.empresa.rk.view.model.HorasExtraEmpleadoFilterViewModel;
import pe.com.empresa.rk.view.model.HorasExtraEmpleadoResultViewModel;
import pe.com.empresa.rk.view.model.HorasExtraViewModel;
import pe.com.empresa.rk.view.model.NotificacionViewModel;
import pe.com.empresa.rk.view.model.PeriodoEmpleadoViewModel;
import pe.com.empresa.rk.view.model.QuickFilterViewModel;

public interface HoraExtraService extends MaintenanceService<HorasExtraEmpleadoFilterViewModel, HorasExtraEmpleadoResultViewModel, HorasExtraViewModel, NotificacionViewModel, Long> ,
QuickSearchService<HorasExtraEmpleadoResultViewModel, QuickFilterViewModel>{

	NotificacionViewModel aprobarHorasExtra(HorasExtraViewModel horasExtraDto);

	NotificacionViewModel rechazarHorasExtra(HorasExtraViewModel horasExtraDto);

	HorasExtraViewModel informacionAdicionalHorasExtras(EmpleadoViewModel empleado);
	
	NotificacionViewModel registrarRecuperarHoras(HorasExtraViewModel manteinanceViewModel);

	List<HorasExtraViewModel> verHorasExtras(PeriodoEmpleadoViewModel periodoEmpleado);

	HorasExtraViewModel obtenerHorasSemanalesPendientes(Long idEmpleado);
	
}
