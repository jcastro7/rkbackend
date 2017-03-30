package pe.com.empresa.rk.service;

import java.util.List;

import pe.com.empresa.rk.view.model.ContratoFilterViewModel;
import pe.com.empresa.rk.view.model.ContratoResultViewModel;
import pe.com.empresa.rk.view.model.ContratoViewModel;
import pe.com.empresa.rk.view.model.HistoriaLaboralViewModel;
import pe.com.empresa.rk.view.model.NotificacionViewModel;

public interface ContratoService extends MaintenanceService<ContratoFilterViewModel, ContratoResultViewModel, ContratoViewModel, NotificacionViewModel, Long> {
	
	
	List<ContratoViewModel> obtenerContratosPorEmpleado(Long idEmpleado);
	
	List<ContratoResultViewModel> busquedaContratosPorEmpleado(Long idEmpleado);
	
	NotificacionViewModel aprobar(ContratoViewModel dto);
	
	HistoriaLaboralViewModel obtenerHistorialLaboralActualPorEmpleado(Long idEmpleado);
	
}
