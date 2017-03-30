package pe.com.empresa.rk.domain.model.repository.jdbc;

import java.util.List;

import pe.com.empresa.rk.view.model.AlertaEmpleadoViewModel;
import pe.com.empresa.rk.view.model.AlertaFilterViewModel;
import pe.com.empresa.rk.view.model.AlertaResultViewModel;
import pe.com.empresa.rk.view.model.AlertaViewModel;

/**
 * Created by josediaz on 14/12/2016.
 */
public interface AlertaRepository {

    public AlertaViewModel obtenerAlerta(String codigo);
    List<AlertaEmpleadoViewModel> obtenerAlertaEmpleado(Long idEmpleado);
	List<AlertaResultViewModel> obtenerAlertas(AlertaFilterViewModel dto);
    
    
}
