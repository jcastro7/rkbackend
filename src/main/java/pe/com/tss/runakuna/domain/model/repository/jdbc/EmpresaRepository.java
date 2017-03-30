package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.List;

import pe.com.tss.runakuna.view.model.DepartamentoAreaViewModel;
import pe.com.tss.runakuna.view.model.EmpresaFilterViewModel;
import pe.com.tss.runakuna.view.model.EmpresaResultViewModel;
import pe.com.tss.runakuna.view.model.EmpresaViewModel;
import pe.com.tss.runakuna.view.model.UnidadDeNegocioViewModel;

public interface EmpresaRepository {
	
     EmpresaViewModel findEmpresaById(Long idEmpresa);
     List<UnidadDeNegocioViewModel> findAllUnidadDeNegocioByEmpresa(Long idEmpresa);
     List<DepartamentoAreaViewModel> findAllDepartamentoAreaByUnidadNegocio(Long idUnidadDeNegocio);
     
     List<EmpresaResultViewModel> finAllEmpresaByFilterSearch(EmpresaFilterViewModel empresaFilter);
 
}
