package pe.com.tss.runakuna.service;

import java.math.BigDecimal;
import java.util.List;

import pe.com.tss.runakuna.view.model.LicenciaEmpleadoResultViewModel;
import pe.com.tss.runakuna.view.model.HorasExtraEmpleadoResultViewModel;
import pe.com.tss.runakuna.view.model.IndicadorJefeResultViewModel;
import pe.com.tss.runakuna.view.model.IndicadorRRHHResultViewModel;
import pe.com.tss.runakuna.view.model.IndicadorVacacionesResultViewModel;
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

public interface DashboardService extends MaintenanceService<MarcacionDashboardFilterViewModel, MarcacionDashboardResultViewModel,MarcacionViewModel, NotificacionViewModel, Long>{

	List<PieChartDataResultViewModel> searchPieChartRRHH(MarcacionDashboardFilterViewModel busquedaMarcacionDto);

	List<PermisoEmpleadoResultViewModel> searchPermisoDashboardJefe(MarcacionDashboardFilterViewModel busquedaMarcacionDto);

	List<VacacionResultViewModel> searchVacacionesDashboardJefe(
			MarcacionDashboardFilterViewModel busquedaVacacionesEmpleadoDto);

	List<HorasExtraEmpleadoResultViewModel> searchHorasExtrasDashboardJefe(
			MarcacionDashboardFilterViewModel marcacionDashboardEmpleadoDto);
	
	List<MarcacionResultViewModel> searchMarcacionesDashboardJefe(
			MarcacionDashboardFilterViewModel marcacionDashboardEmpleadoDto);
	
	List<LicenciaEmpleadoResultViewModel> searchLicenciasDashboardJefe(
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
	
	List<LicenciaEmpleadoResultViewModel> searchLicenciasDashboardRRHH(
			MarcacionDashboardFilterViewModel marcacionDashboardEmpleadoDto);

	List<MarcacionesMensualesViewModel> obtenerMarcacionesMensuales(MarcacionesMensualesFilterViewModel dto);

	List<MarcacionDashboardResultViewModel> busquedaMarcacionesDashboardRRHHAll(
			MarcacionDashboardFilterViewModel busquedaMarcacionDto);

	List<MarcacionDashboardResultViewModel> busquedaMarcacionesDashboardJefeAll(
			MarcacionDashboardFilterViewModel busquedaMarcacionDto);

	List<IndicadorRRHHResultViewModel> listMinutosTardanciaxMesRRHH(
			MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto);

	List<IndicadorJefeResultViewModel> buscarIndicadorJefeDashBoard(
			MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto);

	List<IndicadorVacacionesResultViewModel> busquedaIndicadorVacacionesDashboard(
			MarcacionDashboardFilterViewModel marcacionDashboardEmpleadoDto);

}
