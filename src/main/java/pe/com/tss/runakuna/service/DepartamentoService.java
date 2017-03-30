package pe.com.tss.runakuna.service;

import java.util.List;

import pe.com.tss.runakuna.view.model.CargoViewModel;
import pe.com.tss.runakuna.view.model.DepartamentoAreaViewModel;
import pe.com.tss.runakuna.view.model.DepartamentoViewModel;
import pe.com.tss.runakuna.view.model.ProyectoViewModel;

public interface DepartamentoService {

	List<DepartamentoViewModel> obtenerDepartamentosPorPais(String codigoPais);
	
	List<DepartamentoAreaViewModel> obtenerDepaAreaPorUndNegocio(Long idUnidadDeNegocio);
	
	List<ProyectoViewModel> obtenerProyPorDepaArea(Long idDepartamentoArea);
	
	List<CargoViewModel> obtenerCargoPorProy(Long idProyecto);
	
	List<CargoViewModel> getListCargos();
	
	List<ProyectoViewModel> obtenerProyectos();
	
}
