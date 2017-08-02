package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.List;

import pe.com.tss.runakuna.view.model.CargoViewModel;
import pe.com.tss.runakuna.view.model.UndNegocioViewModel;

public interface UndNegocioRepository {

	List<UndNegocioViewModel> findUndNegocio();
	
	List<CargoViewModel> findListCargos();
}
