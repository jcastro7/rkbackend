package pe.com.empresa.rk.domain.model.repository.jdbc;

import pe.com.empresa.rk.view.model.HorarioEmpleadoDiaViewModel;
import pe.com.empresa.rk.view.model.HorarioEmpleadoViewModel;

import java.util.List;

public interface HorarioEmpleadoRepository {
     HorarioEmpleadoViewModel ObtenerHorarioEmpleado(Long idEmpleado);
     
     HorarioEmpleadoViewModel findOneById(Long idHorarioEmpleado);
     
     List<HorarioEmpleadoDiaViewModel> findHorarioEmpleadoDiaByIdHorarioEmpleado(Long idHorarioEmpleado);
     

}
