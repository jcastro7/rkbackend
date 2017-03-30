package pe.com.empresa.rk.service;

import pe.com.empresa.rk.view.model.CompensacionDetalleViewModel;
import pe.com.empresa.rk.view.model.CompensacionFilterViewModel;
import pe.com.empresa.rk.view.model.CompensacionResultViewModel;
import pe.com.empresa.rk.view.model.CompensacionViewModel;
import pe.com.empresa.rk.view.model.NotificacionViewModel;
import pe.com.empresa.rk.view.model.QuickFilterViewModel;

public interface CompensacionService extends MaintenanceService<CompensacionFilterViewModel, CompensacionResultViewModel, CompensacionViewModel, NotificacionViewModel, Long> ,
												QuickSearchService<CompensacionResultViewModel, QuickFilterViewModel>{

	CompensacionDetalleViewModel findDetalle(Long idEmpleadoCompensacion);
	
}
