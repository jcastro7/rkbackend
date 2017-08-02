package pe.com.tss.runakuna.service;

import java.util.List;

import pe.com.tss.runakuna.view.model.DistritoViewModel;

public interface DistritoService {

	List<DistritoViewModel> obtenerDistritosPorProvincia(Long idProvincia);
}
