package pe.com.tss.runakuna.service;

import java.util.List;

import pe.com.tss.runakuna.view.model.CalendarioFilterViewModel;
import pe.com.tss.runakuna.view.model.CalendarioResultViewModel;
import pe.com.tss.runakuna.view.model.CalendarioViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;

public interface CalendarioService {

	List<CalendarioResultViewModel> searchDiasNoLaborables(CalendarioFilterViewModel dto);

	CalendarioViewModel findOne(Long idCalendario);

	NotificacionViewModel create(CalendarioViewModel calendarioDto);

	NotificacionViewModel update(CalendarioViewModel calendarioDto);

	NotificacionViewModel delete(Long idCalendario);

}
