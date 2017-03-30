package pe.com.empresa.rk.service;

import java.util.List;

import pe.com.empresa.rk.view.model.ProvinciaViewModel;

public interface ProvinciaService {

	List<ProvinciaViewModel> obtenerProvinciasPorDepartamento(String codigoDepartamento);
}
