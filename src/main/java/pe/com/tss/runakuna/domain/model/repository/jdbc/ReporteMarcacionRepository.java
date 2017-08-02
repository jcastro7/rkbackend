package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.List;

import pe.com.tss.runakuna.view.model.ReporteMarcacionFilterViewModel;
import pe.com.tss.runakuna.view.model.ReporteMarcacionResultViewModel;
import pe.com.tss.runakuna.view.model.ReporteMarcacionViewModel;

/**
 * Created by josediaz on 14/12/2016.
 */
public interface ReporteMarcacionRepository {

    List<ReporteMarcacionResultViewModel> obtenerReportes(ReporteMarcacionFilterViewModel dto);
    
    List<ReporteMarcacionViewModel> obtenerReportesJob(ReporteMarcacionFilterViewModel reporteMarcacionDto);
    
}
