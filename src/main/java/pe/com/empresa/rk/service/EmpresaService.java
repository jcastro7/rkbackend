package pe.com.empresa.rk.service;

import pe.com.empresa.rk.view.model.EmpresaResultViewModel;
import pe.com.empresa.rk.view.model.EmpresaViewModel;
import pe.com.empresa.rk.view.model.NotificacionViewModel;
import pe.com.empresa.rk.view.model.EmpresaFilterViewModel;

public interface EmpresaService extends MaintenanceService<EmpresaFilterViewModel, EmpresaResultViewModel, EmpresaViewModel, NotificacionViewModel, Long> {

}
