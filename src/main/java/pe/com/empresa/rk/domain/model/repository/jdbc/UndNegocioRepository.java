package pe.com.empresa.rk.domain.model.repository.jdbc;

import java.util.List;

import pe.com.empresa.rk.view.model.CargoViewModel;
import pe.com.empresa.rk.view.model.UndNegocioViewModel;

public interface UndNegocioRepository {

	List<UndNegocioViewModel> findUndNegocio();
	
	List<CargoViewModel> findListCargos();
}
