package pe.com.empresa.rk.service;

import java.util.List;

import pe.com.empresa.rk.view.model.CargoViewModel;
import pe.com.empresa.rk.view.model.DepartamentoAreaViewModel;
import pe.com.empresa.rk.view.model.DepartamentoViewModel;
import pe.com.empresa.rk.view.model.ProyectoViewModel;

public interface DepartamentoService {

	List<DepartamentoViewModel> obtenerDepartamentosPorPais(String codigoPais);
	
	List<DepartamentoAreaViewModel> obtenerDepaAreaPorUndNegocio(Long idUnidadDeNegocio);
	
	List<ProyectoViewModel> obtenerProyPorDepaArea(Long idDepartamentoArea);
	
	List<CargoViewModel> obtenerCargoPorProy(Long idProyecto);
	
	List<CargoViewModel> getListCargos();
	
	List<ProyectoViewModel> obtenerProyectos();
	
}
