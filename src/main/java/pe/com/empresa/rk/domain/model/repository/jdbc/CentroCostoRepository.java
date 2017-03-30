package pe.com.empresa.rk.domain.model.repository.jdbc;

import java.util.List;

import pe.com.empresa.rk.view.model.CentroCostoFilterViewModel;
import pe.com.empresa.rk.view.model.CentroCostoResultViewModel;

public interface CentroCostoRepository {

	List<CentroCostoResultViewModel> search(CentroCostoFilterViewModel dto);
}
