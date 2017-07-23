package pe.com.tss.runakuna.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.tss.runakuna.domain.model.repository.jdbc.LocalStorageJdbcRepository;
import pe.com.tss.runakuna.service.LocalStorageService;
import pe.com.tss.runakuna.view.model.CargoComboViewModel;
import pe.com.tss.runakuna.view.model.DepartamentoAreaViewModel;
import pe.com.tss.runakuna.view.model.LocalStorageViewModel;
import pe.com.tss.runakuna.view.model.MonedaViewModel;
import pe.com.tss.runakuna.view.model.ProyectoComboViewModel;
import pe.com.tss.runakuna.view.model.TablaGeneralViewModel;
import pe.com.tss.runakuna.view.model.TipoLicenciaViewModel;
import pe.com.tss.runakuna.view.model.UnidadDeNegocioViewModel;

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
