package pe.com.tss.runakuna.service;

import java.util.List;

import pe.com.tss.runakuna.view.model.EmpleadoViewModel;
import pe.com.tss.runakuna.view.model.HorasExtraEmpleadoFilterViewModel;
import pe.com.tss.runakuna.view.model.HorasExtraEmpleadoResultViewModel;
import pe.com.tss.runakuna.view.model.HorasExtraViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;
import pe.com.tss.runakuna.view.model.PeriodoEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.QuickFilterViewModel;

public interface HoraExtraService extends MaintenanceService<HorasExtraEmpleadoFilterViewModel, HorasExtraEmpleadoResultViewModel, HorasExtraViewModel, NotificacionViewModel, Long> ,
QuickSearchService<HorasExtraEmpleadoResultViewModel, QuickFilterViewModel>{

	NotificacionViewModel aprobarHorasExtra(HorasExtraViewModel horasExtraDto);

	NotificacionViewModel rechazarHorasExtra(HorasExtraViewModel horasExtraDto);

	HorasExtraViewModel informacionAdicionalHorasExtras(EmpleadoViewModel empleado);
	
	NotificacionViewModel registrarRecuperarHoras(HorasExtraViewModel manteinanceViewModel);

	List<HorasExtraViewModel> verHorasExtras(PeriodoEmpleadoViewModel periodoEmpleado);

	HorasExtraViewModel obtenerHorasSemanalesPendientes(Long idEmpleado);
	
	HorasExtraViewModel obtenerHorasSemanalPendiente(HorasExtraViewModel filter);
	
}
