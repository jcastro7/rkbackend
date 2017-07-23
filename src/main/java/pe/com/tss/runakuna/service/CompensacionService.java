package pe.com.tss.runakuna.service;

import pe.com.tss.runakuna.view.model.CompensacionDetalleViewModel;
import pe.com.tss.runakuna.view.model.CompensacionFilterViewModel;
import pe.com.tss.runakuna.view.model.CompensacionResultViewModel;
import pe.com.tss.runakuna.view.model.CompensacionViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;
import pe.com.tss.runakuna.view.model.QuickFilterViewModel;

public interface CompensacionService extends MaintenanceService<CompensacionFilterViewModel, CompensacionResultViewModel, CompensacionViewModel, NotificacionViewModel, Long> , 
												QuickSearchService<CompensacionResultViewModel, QuickFilterViewModel>{

	CompensacionDetalleViewModel findDetalle(Long idEmpleadoCompensacion);
	
}
