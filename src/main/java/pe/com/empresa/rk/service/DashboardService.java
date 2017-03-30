package pe.com.empresa.rk.service;

import java.util.List;

import pe.com.empresa.rk.view.model.MarcacionViewModel;
import pe.com.empresa.rk.view.model.HorasExtraEmpleadoResultViewModel;
import pe.com.empresa.rk.view.model.IndicadorRRHHResultViewModel;
import pe.com.empresa.rk.view.model.MarcacionDashboardEmpleadoFilterViewModel;
import pe.com.empresa.rk.view.model.MarcacionDashboardFilterViewModel;
import pe.com.empresa.rk.view.model.MarcacionDashboardResultViewModel;
import pe.com.empresa.rk.view.model.NotificacionViewModel;
import pe.com.empresa.rk.view.model.PermisoEmpleadoResultViewModel;
import pe.com.empresa.rk.view.model.PieChartDataResultViewModel;
import pe.com.empresa.rk.view.model.VacacionResultViewModel;

public interface DashboardService extends MaintenanceService<MarcacionDashboardFilterViewModel, MarcacionDashboardResultViewModel,MarcacionViewModel, NotificacionViewModel, Long>{

	List<PieChartDataResultViewModel> searchPieChartRRHH(MarcacionDashboardFilterViewModel busquedaMarcacionDto);

	List<PermisoEmpleadoResultViewModel> searchPermisoDashboardJefe(MarcacionDashboardFilterViewModel busquedaMarcacionDto);

	List<VacacionResultViewModel> searchVacacionesDashboardJefe(
			MarcacionDashboardFilterViewModel busquedaVacacionesEmpleadoDto);

	List<HorasExtraEmpleadoResultViewModel> searchHorasExtrasDashboardJefe(
			MarcacionDashboardFilterViewModel marcacionDashboardEmpleadoDto);

	List<MarcacionDashboardResultViewModel> searchByEmpleado(
			MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto);

	List<PermisoEmpleadoResultViewModel> searchPermisoDashboardEmpleado(
			MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto);

	List<VacacionResultViewModel> searchVacacionesDashboardEmpleado(
			MarcacionDashboardEmpleadoFilterViewModel marcacionDashboardEmpleadoDto);

	List<HorasExtraEmpleadoResultViewModel> busquedaHorasExtrasDashboardEmpledo(
			MarcacionDashboardEmpleadoFilterViewModel marcacionDashboardEmpleadoDto);

	List<PieChartDataResultViewModel> searchPieChartEmpleado(
			MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto);

	List<MarcacionDashboardResultViewModel> searchByJefe(MarcacionDashboardFilterViewModel busquedaMarcacionDto);

	List<PieChartDataResultViewModel> searchPieChartJefe(MarcacionDashboardFilterViewModel busquedaMarcacionDto);

	List<PermisoEmpleadoResultViewModel> searchPermisoDashboardRRHH(
			MarcacionDashboardFilterViewModel busquedaMarcacionDto);

	List<PieChartDataResultViewModel> buscarEmpleadoIndicadorDashBoardXmes(
			MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto);

	Long minutosTardanciaxDia(MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto);

	Long minutosTardanciaxMes(MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto);

	List<PieChartDataResultViewModel> listMinutosTardanciaxMes(MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto);

	List<IndicadorRRHHResultViewModel> buscarIndicadorRRHHDashBoard(
			MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto);

	List<VacacionResultViewModel> busquedaVacacionesDashboardRRHH(
			MarcacionDashboardFilterViewModel marcacionDashboardEmpleadoDto);

	List<HorasExtraEmpleadoResultViewModel> searchHorasExtrasDashboardRRHH(
			MarcacionDashboardFilterViewModel marcacionDashboardEmpleadoDto);

}
