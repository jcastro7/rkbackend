package pe.com.tss.runakuna.domain.model.repository.jdbc;

import pe.com.tss.runakuna.view.model.HorarioFilterViewModel;
import pe.com.tss.runakuna.view.model.HorarioResultViewModel;
import pe.com.tss.runakuna.view.model.HorarioDiaViewModel;
import pe.com.tss.runakuna.view.model.HorarioViewModel;

import java.util.List;

public interface HorarioRepository {
     
     List<HorarioResultViewModel> busquedaHorario(HorarioFilterViewModel busquedaHorarioDto);
     List<HorarioViewModel> obtenerHorariosPorTipoHorario(HorarioViewModel horarioDto);
     HorarioViewModel obtenerHorariosPorTipoHorarioyPorDefecto(HorarioViewModel horarioDto);
     List<HorarioDiaViewModel> obtenerHorarioDiaPorHorario(Long idHorario);
     
}
