package pe.com.tss.runakuna.service;

import pe.com.tss.runakuna.view.model.NotificacionViewModel;
import pe.com.tss.runakuna.view.model.QuickFilterViewModel;
import pe.com.tss.runakuna.view.model.VacacionFilterViewModel;
import pe.com.tss.runakuna.view.model.VacacionResultViewModel;
import pe.com.tss.runakuna.view.model.VacacionViewModel;

public interface VacacionService extends MaintenanceService<VacacionFilterViewModel, VacacionResultViewModel, VacacionViewModel, NotificacionViewModel, Long>, 
QuickSearchService<VacacionResultViewModel, QuickFilterViewModel>{
	
	

}
