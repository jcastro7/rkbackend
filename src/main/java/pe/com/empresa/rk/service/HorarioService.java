package pe.com.empresa.rk.service;

import java.util.List;

import pe.com.empresa.rk.view.model.HorarioDiaViewModel;
import pe.com.empresa.rk.view.model.HorarioResultViewModel;
import pe.com.empresa.rk.view.model.HorarioViewModel;
import pe.com.empresa.rk.view.model.NotificacionViewModel;
import pe.com.empresa.rk.view.model.HorarioFilterViewModel;

public interface HorarioService extends MaintenanceService<HorarioFilterViewModel,HorarioResultViewModel,HorarioViewModel,NotificacionViewModel, Long>{

	List<HorarioViewModel> obtenerHorariosPorTipoHorario(HorarioViewModel horarioDto);
	HorarioViewModel obtenerHorarioPorTipoHorarioPorDefecto(HorarioViewModel horarioDto);
	List<HorarioDiaViewModel> obtenerHorarioDiaPorHorario(Long idHorario);
	List<HorarioViewModel> obtenerHorarios();
	HorarioViewModel obtenerHorarioPorIdHorario(HorarioViewModel horarioDto);
}
