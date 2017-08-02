package pe.com.tss.runakuna.service;

import java.util.List;

import pe.com.tss.runakuna.view.model.CargoViewModel;
import pe.com.tss.runakuna.view.model.UndNegocioViewModel;

public interface UndNegocioService {
	
	List<UndNegocioViewModel> getUndNegocio();
	
	List<CargoViewModel> getListCargos();

}
