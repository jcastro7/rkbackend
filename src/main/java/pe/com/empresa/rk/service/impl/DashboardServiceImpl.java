package pe.com.empresa.rk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.empresa.rk.service.DashboardService;
import pe.com.empresa.rk.view.model.MarcacionDashboardResultViewModel;
import pe.com.empresa.rk.view.model.MarcacionViewModel;
import pe.com.empresa.rk.domain.model.repository.jdbc.DashboardJdbcRepository;
import pe.com.empresa.rk.view.model.HorasExtraEmpleadoResultViewModel;
import pe.com.empresa.rk.view.model.IndicadorRRHHResultViewModel;
import pe.com.empresa.rk.view.model.MarcacionDashboardEmpleadoFilterViewModel;
import pe.com.empresa.rk.view.model.MarcacionDashboardFilterViewModel;
import pe.com.empresa.rk.view.model.NotificacionViewModel;
import pe.com.empresa.rk.view.model.PermisoEmpleadoResultViewModel;
import pe.com.empresa.rk.view.model.PieChartDataResultViewModel;
import pe.com.empresa.rk.view.model.VacacionResultViewModel;

@Service
public class DashboardServiceImpl implements DashboardService {
	
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return dashboardJdbcRepository.searchVacacionesEmpleadoDashboardEmpleado(marcacionDashboardEmpleadoDto);
	}

	@Override
	public List<HorasExtraEmpleadoResultViewModel> busquedaHorasExtrasDashboardEmpledo(
			MarcacionDashboardEmpleadoFilterViewModel marcacionDashboardEmpleadoDto) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return dashboardJdbcRepository.minutosTardanciaxDia(busquedaMarcacionDto);
	}

	@Override
	public Long minutosTardanciaxMes(MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto) {
		// TODO Auto-generated method stub
		return dashboardJdbcRepository.minutosTardanciaxMes(busquedaMarcacionDto);
	}

	@Override
	public List<PieChartDataResultViewModel> listMinutosTardanciaxMes(MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto) {
		// TODO Auto-generated method stub
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

}
