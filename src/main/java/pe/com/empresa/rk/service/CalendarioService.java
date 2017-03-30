package pe.com.empresa.rk.service;

import java.util.List;

import pe.com.empresa.rk.view.model.CalendarioViewModel;
import pe.com.empresa.rk.view.model.CalendarioFilterViewModel;
import pe.com.empresa.rk.view.model.CalendarioResultViewModel;
import pe.com.empresa.rk.view.model.NotificacionViewModel;

public interface CalendarioService {

	List<CalendarioResultViewModel> searchDiasNoLaborables(CalendarioFilterViewModel dto);

	CalendarioViewModel findOne(Long idCalendario);

	NotificacionViewModel create(CalendarioViewModel calendarioDto);

	NotificacionViewModel update(CalendarioViewModel calendarioDto);

	NotificacionViewModel delete(Long idCalendario);

}
