package pe.com.empresa.rk.service;

import java.util.List;

import pe.com.empresa.rk.view.model.CargoViewModel;
import pe.com.empresa.rk.view.model.UndNegocioViewModel;

public interface UndNegocioService {
	
	List<UndNegocioViewModel> getUndNegocio();
	
	List<CargoViewModel> getListCargos();

}
