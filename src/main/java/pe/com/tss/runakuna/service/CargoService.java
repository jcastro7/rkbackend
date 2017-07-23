package pe.com.tss.runakuna.service;

import java.util.List;

import pe.com.tss.runakuna.view.model.CargoComboViewModel;
import pe.com.tss.runakuna.view.model.CargoFilterViewModel;
import pe.com.tss.runakuna.view.model.CargoResultViewModel;
import pe.com.tss.runakuna.view.model.CargoViewModel;
import pe.com.tss.runakuna.view.model.HistoriaLaboralViewModel;
import pe.com.tss.runakuna.view.model.HistorialLaboralViewModel;
import pe.com.tss.runakuna.view.model.HorarioViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;
import pe.com.tss.runakuna.view.model.ProyectoFilterViewModel;
import pe.com.tss.runakuna.view.model.QuickFilterViewModel;

public interface CargoService extends MaintenanceService<CargoFilterViewModel, CargoResultViewModel, CargoViewModel, NotificacionViewModel, Long>,
QuickSearchService<CargoResultViewModel, QuickFilterViewModel>{
	
	String save(CargoViewModel dto);
	
	List<CargoViewModel> getCargo();
	
	List<HorarioViewModel> getObtenerNombreHorario();
	
	NotificacionViewModel actualizarCargo(HistorialLaboralViewModel historiaLaboralDto);
	
	NotificacionViewModel crearCargo(HistoriaLaboralViewModel historiaLaboralDto);
	
	NotificacionViewModel eliminarCargo(Long idHistorialLaboral);

	List<CargoComboViewModel> obtenerCargoComboHistorialLaboral(ProyectoFilterViewModel filter);
	
}
