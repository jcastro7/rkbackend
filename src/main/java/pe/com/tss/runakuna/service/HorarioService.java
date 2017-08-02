package pe.com.tss.runakuna.service;

import java.util.List;

import pe.com.tss.runakuna.view.model.HorarioDiaViewModel;
import pe.com.tss.runakuna.view.model.HorarioFilterViewModel;
import pe.com.tss.runakuna.view.model.HorarioResultViewModel;
import pe.com.tss.runakuna.view.model.HorarioViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;

public interface HorarioService extends MaintenanceService<HorarioFilterViewModel,HorarioResultViewModel,HorarioViewModel,NotificacionViewModel, Long>{

	List<HorarioViewModel> obtenerHorariosPorTipoHorario(HorarioViewModel horarioDto);
	HorarioViewModel obtenerHorarioPorTipoHorarioPorDefecto(HorarioViewModel horarioDto);
	List<HorarioDiaViewModel> obtenerHorarioDiaPorHorario(Long idHorario);
	List<HorarioViewModel> obtenerHorarios();
	HorarioViewModel obtenerHorarioPorIdHorario(HorarioViewModel horarioDto);
}
