package pe.com.empresa.rk.service;

import java.util.List;

import pe.com.empresa.rk.view.model.DistritoViewModel;

public interface DistritoService {

	List<DistritoViewModel> obtenerDistritosPorProvincia(String codigoProvincia);
}
