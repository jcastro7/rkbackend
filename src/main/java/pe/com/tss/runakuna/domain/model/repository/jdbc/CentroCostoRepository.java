package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.List;

import pe.com.tss.runakuna.view.model.CentroCostoFilterViewModel;
import pe.com.tss.runakuna.view.model.CentroCostoResultViewModel;

public interface CentroCostoRepository {

	List<CentroCostoResultViewModel> search(CentroCostoFilterViewModel dto);
}
