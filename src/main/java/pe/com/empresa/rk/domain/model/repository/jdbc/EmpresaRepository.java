package pe.com.empresa.rk.domain.model.repository.jdbc;

import java.util.List;

import pe.com.empresa.rk.view.model.DepartamentoAreaViewModel;
import pe.com.empresa.rk.view.model.EmpresaFilterViewModel;
import pe.com.empresa.rk.view.model.EmpresaResultViewModel;
import pe.com.empresa.rk.view.model.EmpresaViewModel;
import pe.com.empresa.rk.view.model.UnidadDeNegocioViewModel;

public interface EmpresaRepository {
	
     EmpresaViewModel findEmpresaById(Long idEmpresa);
     List<UnidadDeNegocioViewModel> findAllUnidadDeNegocioByEmpresa(Long idEmpresa);
     List<DepartamentoAreaViewModel> findAllDepartamentoAreaByUnidadNegocio(Long idUnidadDeNegocio);
     
     List<EmpresaResultViewModel> finAllEmpresaByFilterSearch(EmpresaFilterViewModel empresaFilter);
 
}
