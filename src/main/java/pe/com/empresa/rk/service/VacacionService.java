package pe.com.empresa.rk.service;

import pe.com.empresa.rk.view.model.QuickFilterViewModel;
import pe.com.empresa.rk.view.model.VacacionResultViewModel;
import pe.com.empresa.rk.view.model.NotificacionViewModel;
import pe.com.empresa.rk.view.model.VacacionFilterViewModel;
import pe.com.empresa.rk.view.model.VacacionViewModel;

public interface VacacionService extends MaintenanceService<VacacionFilterViewModel, VacacionResultViewModel, VacacionViewModel, NotificacionViewModel, Long>,
QuickSearchService<VacacionResultViewModel, QuickFilterViewModel>{
	
	

}
