package pe.com.tss.runakuna.service;

import java.util.List;

import pe.com.tss.runakuna.view.model.ContratoFilterViewModel;
import pe.com.tss.runakuna.view.model.ContratoResultViewModel;
import pe.com.tss.runakuna.view.model.ContratoViewModel;
import pe.com.tss.runakuna.view.model.HistoriaLaboralViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;

public interface ContratoService extends MaintenanceService<ContratoFilterViewModel, ContratoResultViewModel, ContratoViewModel, NotificacionViewModel, Long> {
	
	
	List<ContratoViewModel> obtenerContratosPorEmpleado(Long idEmpleado);
	
	List<ContratoResultViewModel> busquedaContratosPorEmpleado(Long idEmpleado);
	
	NotificacionViewModel aprobar(ContratoViewModel dto);
	
	HistoriaLaboralViewModel obtenerHistorialLaboralActualPorEmpleado(Long idEmpleado);
	
}
