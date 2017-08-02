package pe.com.tss.runakuna.service;

import java.util.List;

import pe.com.tss.runakuna.view.model.CentroCostoFilterViewModel;
import pe.com.tss.runakuna.view.model.CentroCostoResultViewModel;
import pe.com.tss.runakuna.view.model.CentroCostoViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;

public interface CentroCostoService extends MaintenanceService<CentroCostoFilterViewModel, CentroCostoResultViewModel, CentroCostoViewModel, NotificacionViewModel, Long>{

	List<CentroCostoViewModel> obtenerCentrosCosto();
}
