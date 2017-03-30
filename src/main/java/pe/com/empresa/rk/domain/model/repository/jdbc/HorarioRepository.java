package pe.com.empresa.rk.domain.model.repository.jdbc;

import pe.com.empresa.rk.view.model.HorarioViewModel;
import pe.com.empresa.rk.view.model.HorarioFilterViewModel;
import pe.com.empresa.rk.view.model.HorarioResultViewModel;
import pe.com.empresa.rk.view.model.HorarioDiaViewModel;

import java.util.List;

public interface HorarioRepository {
     
     List<HorarioResultViewModel> busquedaHorario(HorarioFilterViewModel busquedaHorarioDto);
     List<HorarioViewModel> obtenerHorariosPorTipoHorario(HorarioViewModel horarioDto);
     HorarioViewModel obtenerHorariosPorTipoHorarioyPorDefecto(HorarioViewModel horarioDto);
     List<HorarioDiaViewModel> obtenerHorarioDiaPorHorario(Long idHorario);
     
}
