package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.List;

import pe.com.tss.runakuna.view.model.AlertaEmpleadoViewModel;
import pe.com.tss.runakuna.view.model.AlertaFilterViewModel;
import pe.com.tss.runakuna.view.model.AlertaResultViewModel;
import pe.com.tss.runakuna.view.model.AlertaViewModel;

/**
 * Created by josediaz on 14/12/2016.
 */
public interface AlertaRepository {

    public AlertaViewModel obtenerAlerta(String codigo);
    List<AlertaEmpleadoViewModel> obtenerAlertaEmpleado(Long idEmpleado);
	List<AlertaResultViewModel> obtenerAlertas(AlertaFilterViewModel dto);
    
    
}
