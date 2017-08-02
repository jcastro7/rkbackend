package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.List;

import pe.com.tss.runakuna.view.model.CargoComboViewModel;
import pe.com.tss.runakuna.view.model.CargoFilterViewModel;
import pe.com.tss.runakuna.view.model.CargoResultViewModel;
import pe.com.tss.runakuna.view.model.CargoViewModel;
import pe.com.tss.runakuna.view.model.HorarioViewModel;
import pe.com.tss.runakuna.view.model.ProyectoFilterViewModel;
import pe.com.tss.runakuna.view.model.QuickFilterViewModel;

public interface CargoRepository {

	List<CargoViewModel> findCargo();
	List<HorarioViewModel> obtenerNombreHorario();
	List<CargoResultViewModel> obtenerCargos(CargoFilterViewModel dto);
	List<CargoResultViewModel> busquedaRapidaCargos(QuickFilterViewModel quickFilter);
	
	List<CargoComboViewModel> obtenerCargoComboHistorialLaboral(ProyectoFilterViewModel filter);
}
