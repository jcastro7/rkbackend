package pe.com.empresa.rk.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.empresa.rk.domain.model.repository.jdbc.LocalStorageJdbcRepository;
import pe.com.empresa.rk.service.LocalStorageService;
import pe.com.empresa.rk.view.model.CargoComboViewModel;
import pe.com.empresa.rk.view.model.DepartamentoAreaViewModel;
import pe.com.empresa.rk.view.model.LocalStorageViewModel;
import pe.com.empresa.rk.view.model.MonedaViewModel;
import pe.com.empresa.rk.view.model.ProyectoComboViewModel;
import pe.com.empresa.rk.view.model.TablaGeneralViewModel;
import pe.com.empresa.rk.view.model.TipoLicenciaViewModel;
import pe.com.empresa.rk.view.model.UnidadDeNegocioViewModel;

@Service
public class LocalStorageServiceImpl implements LocalStorageService{

	@Autowired
	LocalStorageJdbcRepository localStorageJdbcRepository;
	
	@Override
	public LocalStorageViewModel findAllLocalStorageValues() {
		LocalStorageViewModel dto = new LocalStorageViewModel();
		dto.setUnidadDeNegocio(new ArrayList<>());
		List<UnidadDeNegocioViewModel> unidadDeNegocio = localStorageJdbcRepository.searchUnidadDeNegocio();
		List<DepartamentoAreaViewModel> departamentoArea = localStorageJdbcRepository.searchDepartamentoArea();
		List<ProyectoComboViewModel> proyecto = localStorageJdbcRepository.searchProyecto();
		List<TablaGeneralViewModel> tablaGeneral = localStorageJdbcRepository.searchTablaGeneral();
		List<TipoLicenciaViewModel> tipoLicencia = localStorageJdbcRepository.searchTipoLicencia();
		List<MonedaViewModel> moneda = localStorageJdbcRepository.searchMoneda();
		List<CargoComboViewModel> cargo = localStorageJdbcRepository.searchCargo();
		dto.setUnidadDeNegocio(unidadDeNegocio);
		dto.setDepartamentoArea(departamentoArea);
		dto.setTablaGeneral(tablaGeneral);
		dto.setProyecto(proyecto);
		dto.setTipoLicencia(tipoLicencia);
		dto.setMoneda(moneda);
		dto.setCargo(cargo);
		
		return dto;
	}

}
