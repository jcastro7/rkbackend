package pe.com.empresa.rk.service;

import java.util.List;

import pe.com.empresa.rk.view.model.CentroCostoFilterViewModel;
import pe.com.empresa.rk.view.model.CentroCostoResultViewModel;
import pe.com.empresa.rk.view.model.CentroCostoViewModel;
import pe.com.empresa.rk.view.model.NotificacionViewModel;

public interface CentroCostoService extends MaintenanceService<CentroCostoFilterViewModel, CentroCostoResultViewModel, CentroCostoViewModel, NotificacionViewModel, Long>{

	List<CentroCostoViewModel> obtenerCentrosCosto();
}
