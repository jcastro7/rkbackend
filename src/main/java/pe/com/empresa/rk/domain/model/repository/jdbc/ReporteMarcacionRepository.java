package pe.com.empresa.rk.domain.model.repository.jdbc;

import java.util.List;

import pe.com.empresa.rk.view.model.ReporteMarcacionFilterViewModel;
import pe.com.empresa.rk.view.model.ReporteMarcacionResultViewModel;
import pe.com.empresa.rk.view.model.ReporteMarcacionViewModel;

/**
 * Created by josediaz on 14/12/2016.
 */
public interface ReporteMarcacionRepository {

    List<ReporteMarcacionResultViewModel> obtenerReportes(ReporteMarcacionFilterViewModel dto);
    
    List<ReporteMarcacionViewModel> obtenerReportesJob(ReporteMarcacionFilterViewModel reporteMarcacionDto);
    
}
