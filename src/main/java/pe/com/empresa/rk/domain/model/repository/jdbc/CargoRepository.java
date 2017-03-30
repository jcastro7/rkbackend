package pe.com.empresa.rk.domain.model.repository.jdbc;

import java.util.List;

import pe.com.empresa.rk.view.model.HorarioViewModel;
import pe.com.empresa.rk.view.model.QuickFilterViewModel;
import pe.com.empresa.rk.view.model.CargoFilterViewModel;
import pe.com.empresa.rk.view.model.CargoResultViewModel;
import pe.com.empresa.rk.view.model.CargoViewModel;

public interface CargoRepository {

	List<CargoViewModel> findCargo();
	List<HorarioViewModel> obtenerNombreHorario();
	List<CargoResultViewModel> obtenerCargos(CargoFilterViewModel dto);
	List<CargoResultViewModel> busquedaRapidaCargos(QuickFilterViewModel quickFilter);
}
