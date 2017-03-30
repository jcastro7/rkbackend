package pe.com.empresa.rk.service;

import java.util.List;

import pe.com.empresa.rk.view.model.CargoViewModel;
import pe.com.empresa.rk.view.model.HorarioViewModel;
import pe.com.empresa.rk.view.model.QuickFilterViewModel;
import pe.com.empresa.rk.view.model.CargoFilterViewModel;
import pe.com.empresa.rk.view.model.CargoResultViewModel;
import pe.com.empresa.rk.view.model.HistoriaLaboralViewModel;
import pe.com.empresa.rk.view.model.HistorialLaboralViewModel;
import pe.com.empresa.rk.view.model.NotificacionViewModel;

public interface CargoService extends MaintenanceService<CargoFilterViewModel, CargoResultViewModel, CargoViewModel, NotificacionViewModel, Long>,
QuickSearchService<CargoResultViewModel, QuickFilterViewModel>{
	
	String save(CargoViewModel dto);
	
	List<CargoViewModel> getCargo();
	
	List<HorarioViewModel> getObtenerNombreHorario();
	
	NotificacionViewModel actualizarCargo(HistorialLaboralViewModel historiaLaboralDto);
	
//	Long crearCargo(HistoriaLaboralDto historiaLaboralDto);
	NotificacionViewModel crearCargo(HistoriaLaboralViewModel historiaLaboralDto);
	
	NotificacionViewModel eliminarCargo(Long idHistorialLaboral);
	/*
	NotificacionViewModel actualizarCargo(CargoViewModel cargoDto);
	NotificacionViewModel registrarCargo(CargoViewModel cargoDto);

	CargoViewModel obtenerCargoDetalle(Long idCargo);

	List<CargoViewModel> obtenerCargos(CargoFilterViewModel dto);

	Long eliminarCargoBanda(Long idCargo);
	*/

}
