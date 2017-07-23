package pe.com.tss.runakuna.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.tss.runakuna.domain.model.repository.jdbc.DashboardJdbcRepository;
import pe.com.tss.runakuna.service.DashboardService;
import pe.com.tss.runakuna.view.model.HorasExtraEmpleadoResultViewModel;
import pe.com.tss.runakuna.view.model.IndicadorJefeResultViewModel;
import pe.com.tss.runakuna.view.model.IndicadorRRHHResultViewModel;
import pe.com.tss.runakuna.view.model.IndicadorVacacionesResultViewModel;
import pe.com.tss.runakuna.view.model.LicenciaEmpleadoResultViewModel;
import pe.com.tss.runakuna.view.model.MarcacionDashboardEmpleadoFilterViewModel;
import pe.com.tss.runakuna.view.model.MarcacionDashboardFilterViewModel;
import pe.com.tss.runakuna.view.model.MarcacionDashboardResultViewModel;
import pe.com.tss.runakuna.view.model.MarcacionResultViewModel;
import pe.com.tss.runakuna.view.model.MarcacionViewModel;
import pe.com.tss.runakuna.view.model.MarcacionesMensualesFilterViewModel;
import pe.com.tss.runakuna.view.model.MarcacionesMensualesViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;
import pe.com.tss.runakuna.view.model.PermisoEmpleadoResultViewModel;
import pe.com.tss.runakuna.view.model.PieChartDataResultViewModel;
import pe.com.tss.runakuna.view.model.VacacionResultViewModel;

@Service
public class DashboardServiceImpl implements DashboardService{
	
	@Autowired
	DashboardJdbcRepository dashboardJdbcRepository;

	@Override
	public List<MarcacionDashboardResultViewModel> search(MarcacionDashboardFilterViewModel filterViewModel) {
		return dashboardJdbcRepository.busquedaMarcacionesDashboardRRHH(filterViewModel);
	}

	@Override
	public MarcacionViewModel findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NotificacionViewModel create(MarcacionViewModel manteinanceViewModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NotificacionViewModel update(MarcacionViewModel manteinanceViewModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NotificacionViewModel delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PieChartDataResultViewModel> searchPieChartRRHH(MarcacionDashboardFilterViewModel busquedaMarcacionDto) {
		return dashboardJdbcRepository.busquedaPieChartDashboardRHHH(busquedaMarcacionDto);
	}

	@Override
	public List<PermisoEmpleadoResultViewModel> searchPermisoDashboardJefe(MarcacionDashboardFilterViewModel busquedaMarcacionDto) {
		return dashboardJdbcRepository.searchPermisoDashboardJefe(busquedaMarcacionDto);
	}

	@Override
	public List<VacacionResultViewModel> searchVacacionesDashboardJefe(
			MarcacionDashboardFilterViewModel marcacionDashboardEmpleadoDto) {
		return dashboardJdbcRepository.searchVacacionesDashboardJefe(marcacionDashboardEmpleadoDto);
	}

	@Override
	public List<HorasExtraEmpleadoResultViewModel> searchHorasExtrasDashboardJefe(
			MarcacionDashboardFilterViewModel marcacionDashboardEmpleadoDto) {
		return dashboardJdbcRepository.searchHorasExtrasDashboardJefe(marcacionDashboardEmpleadoDto);
	}
	
	@Override
	public List<MarcacionResultViewModel> searchMarcacionesDashboardJefe(
			MarcacionDashboardFilterViewModel marcacionDashboardEmpleadoDto) {
		return dashboardJdbcRepository.searchMarcacionesDashboardJefe(marcacionDashboardEmpleadoDto);
	}
	
	@Override
	public List<LicenciaEmpleadoResultViewModel> searchLicenciasDashboardJefe(
			MarcacionDashboardFilterViewModel marcacionDashboardEmpleadoDto) {
		return dashboardJdbcRepository.searchLicenciasDashboardJefe(marcacionDashboardEmpleadoDto);
	}

	@Override
	public List<MarcacionDashboardResultViewModel> searchByEmpleado(
			MarcacionDashboardEmpleadoFilterViewModel filterViewModel) {
		return dashboardJdbcRepository.busquedaMarcacionesDashboardEmpleado(filterViewModel);
	}

	@Override
	public List<PermisoEmpleadoResultViewModel> searchPermisoDashboardEmpleado(
			MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto) {
		
		return dashboardJdbcRepository.searchPermisoDashboardEmpleado(busquedaMarcacionDto);
	}

	@Override
	public List<VacacionResultViewModel> searchVacacionesDashboardEmpleado(
			MarcacionDashboardEmpleadoFilterViewModel marcacionDashboardEmpleadoDto) {
		return dashboardJdbcRepository.searchVacacionesEmpleadoDashboardEmpleado(marcacionDashboardEmpleadoDto);
	}

	@Override
	public List<HorasExtraEmpleadoResultViewModel> busquedaHorasExtrasDashboardEmpledo(
			MarcacionDashboardEmpleadoFilterViewModel marcacionDashboardEmpleadoDto) {
		return dashboardJdbcRepository.busquedaHorasExtrasDashboardEmpledo(marcacionDashboardEmpleadoDto);
	}

	@Override
	public List<PieChartDataResultViewModel> searchPieChartEmpleado(
			MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto) {
		return dashboardJdbcRepository.busquedaPieChartDashboardEmpleado(busquedaMarcacionDto);
	}

	@Override
	public List<MarcacionDashboardResultViewModel> searchByJefe(
			MarcacionDashboardFilterViewModel busquedaMarcacionDto) {
		return dashboardJdbcRepository.busquedaMarcacionesDashboardJefe(busquedaMarcacionDto);
	}

	@Override
	public List<PieChartDataResultViewModel> searchPieChartJefe(
			MarcacionDashboardFilterViewModel busquedaMarcacionDto) {
		return dashboardJdbcRepository.busquedaPieChartDashboardJefe(busquedaMarcacionDto);
	}

	@Override
	public List<PermisoEmpleadoResultViewModel> searchPermisoDashboardRRHH(
			MarcacionDashboardFilterViewModel busquedaMarcacionDto) {
		return dashboardJdbcRepository.searchPermisoDashboardRRHH(busquedaMarcacionDto);
	}

	@Override
	public List<PieChartDataResultViewModel> buscarEmpleadoIndicadorDashBoardXmes(
			MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto) {
		return dashboardJdbcRepository.buscarEmpleadoIndicadorDashBoardXmes(busquedaMarcacionDto);
	}

	@Override
	public Long minutosTardanciaxDia(MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto) {
		return dashboardJdbcRepository.minutosTardanciaxDia(busquedaMarcacionDto);
	}

	@Override
	public Long minutosTardanciaxMes(MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto) {
		return dashboardJdbcRepository.minutosTardanciaxMes(busquedaMarcacionDto);
	}

	@Override
	public List<PieChartDataResultViewModel> listMinutosTardanciaxMes(MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto) {
		return dashboardJdbcRepository.listMinutosTardanciaxMes(busquedaMarcacionDto);
	}

	@Override
	public List<IndicadorRRHHResultViewModel> buscarIndicadorRRHHDashBoard(
			MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto) {
		return dashboardJdbcRepository.buscarIndicadorRRHHDashBoard(busquedaMarcacionDto);
	}

	@Override
	public List<VacacionResultViewModel> busquedaVacacionesDashboardRRHH(MarcacionDashboardFilterViewModel marcacionDashboardEmpleadoDto) {
		return dashboardJdbcRepository.searchVacacionesDashboardRRHH(marcacionDashboardEmpleadoDto);
	}

	@Override
	public List<HorasExtraEmpleadoResultViewModel> searchHorasExtrasDashboardRRHH(MarcacionDashboardFilterViewModel marcacionDashboardEmpleadoDto) {
		return dashboardJdbcRepository.searchHorasExtrasDashboardRRHH(marcacionDashboardEmpleadoDto);
	}
	
	@Override
	public List<LicenciaEmpleadoResultViewModel> searchLicenciasDashboardRRHH(MarcacionDashboardFilterViewModel marcacionDashboardEmpleadoDto) {
		return dashboardJdbcRepository.searchLicenciasDashboardRRHH(marcacionDashboardEmpleadoDto);
	}

	@Override
	public List<MarcacionesMensualesViewModel> obtenerMarcacionesMensuales(MarcacionesMensualesFilterViewModel dto) {
		return dashboardJdbcRepository.obtenerMarcacionesMensuales(dto);
	}

	@Override
	public List<MarcacionDashboardResultViewModel> busquedaMarcacionesDashboardRRHHAll(
			MarcacionDashboardFilterViewModel filterViewModel) {
		return dashboardJdbcRepository.busquedaMarcacionesDashboardRRHHAll(filterViewModel);
	}

	@Override
	public List<MarcacionDashboardResultViewModel> busquedaMarcacionesDashboardJefeAll(
			MarcacionDashboardFilterViewModel busquedaMarcacionDto) {
		return dashboardJdbcRepository.busquedaMarcacionesDashboardJefeAll(busquedaMarcacionDto);
	}

	@Override
	public List<IndicadorRRHHResultViewModel> listMinutosTardanciaxMesRRHH(
			MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto) {
		return dashboardJdbcRepository.listMinutosTardanciaxMesRRHH(busquedaMarcacionDto);
	}

	@Override
	public List<IndicadorJefeResultViewModel> buscarIndicadorJefeDashBoard(
			MarcacionDashboardEmpleadoFilterViewModel filterViewModel) {
		return dashboardJdbcRepository.buscarIndicadorJefeDashBoard(filterViewModel);
	}

	@Override
	public List<IndicadorVacacionesResultViewModel> busquedaIndicadorVacacionesDashboard(
			MarcacionDashboardFilterViewModel filterViewModel) {
		return dashboardJdbcRepository.busquedaIndicadorVacacionesDashboard(filterViewModel);
	}

}
