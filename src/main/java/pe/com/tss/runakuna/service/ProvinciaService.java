package pe.com.tss.runakuna.service;

import java.util.List;

import pe.com.tss.runakuna.view.model.ProvinciaViewModel;

public interface ProvinciaService {

	List<ProvinciaViewModel> obtenerProvinciasPorDepartamento(String codigoDepartamento);
}
