package pe.com.tss.runakuna.domain.model.repository.jdbc;

import pe.com.tss.runakuna.view.model.HorarioEmpleadoDiaViewModel;
import pe.com.tss.runakuna.view.model.HorarioEmpleadoViewModel;

import java.util.List;

public interface HorarioEmpleadoRepository {
     HorarioEmpleadoViewModel ObtenerHorarioEmpleado(Long idEmpleado);
     
     HorarioEmpleadoViewModel findOneById(Long idHorarioEmpleado);
     
     List<HorarioEmpleadoDiaViewModel> findHorarioEmpleadoDiaByIdHorarioEmpleado(Long idHorarioEmpleado);
     

}
