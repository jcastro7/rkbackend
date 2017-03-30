package pe.com.empresa.rk.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.empresa.rk.service.DashboardService;
import pe.com.empresa.rk.view.model.HorasExtraEmpleadoResultViewModel;
import pe.com.empresa.rk.view.model.IndicadorRRHHResultViewModel;
import pe.com.empresa.rk.view.model.MarcacionDashboardEmpleadoFilterViewModel;
import pe.com.empresa.rk.view.model.MarcacionDashboardFilterViewModel;
import pe.com.empresa.rk.view.model.MarcacionDashboardResultViewModel;
import pe.com.empresa.rk.view.model.PermisoEmpleadoResultViewModel;
import pe.com.empresa.rk.view.model.PieChartDataResultViewModel;
import pe.com.empresa.rk.view.model.VacacionResultViewModel;

@RestController
@RequestMapping(value = "/api/dashboard")
public class DashBoardController extends BaseController {

    @Autowired
    DashboardService dashboardService;

    @RequestMapping(value = "/busquedaMarcacionesDashboardRRHH", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<List<MarcacionDashboardResultViewModel>> busquedaMarcacionesDashboardRRHH(@RequestBody MarcacionDashboardFilterViewModel busquedaMarcacionDto) {

        List<MarcacionDashboardResultViewModel> result;
        result = dashboardService.search(busquedaMarcacionDto);
        if (result == null)
            result = new ArrayList<>();

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @RequestMapping(value = "/busquedaMarcacionesDashboardJefe", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<List<MarcacionDashboardResultViewModel>> busquedaMarcacionesDashboardJefe(@RequestBody MarcacionDashboardFilterViewModel busquedaMarcacionDto) {

        List<MarcacionDashboardResultViewModel> result;
        result = dashboardService.searchByJefe(busquedaMarcacionDto);
        if (result == null)
            result = new ArrayList<>();

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @RequestMapping(value = "/buscarMarcacionesDashboardEmpleado", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<List<MarcacionDashboardResultViewModel>>
    buscarMarcacionesDashboardEmpleado(@RequestBody MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto) {

        List<MarcacionDashboardResultViewModel> result;
        result = dashboardService.searchByEmpleado(busquedaMarcacionDto);
        if (result == null)
            result = new ArrayList<>();

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @RequestMapping(value = "/buscarMarcacionEmpleadoPieChart", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<List<PieChartDataResultViewModel>> buscarMarcacionPieChartRRHH(@RequestBody MarcacionDashboardFilterViewModel busquedaMarcacionDto) {

        List<PieChartDataResultViewModel> result;
        result = dashboardService.searchPieChartRRHH(busquedaMarcacionDto);
        if (result == null)
            result = new ArrayList<>();

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @RequestMapping(value = "/buscarMarcacionPieChartJefe", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<List<PieChartDataResultViewModel>> buscarMarcacionPieChartJefe(@RequestBody MarcacionDashboardFilterViewModel busquedaMarcacionDto) {

        List<PieChartDataResultViewModel> result;
        result = dashboardService.searchPieChartJefe(busquedaMarcacionDto);
        if (result == null)
            result = new ArrayList<>();

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @RequestMapping(value = "/buscarMarcacionEmpleadoPieChartEmpleado", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<List<PieChartDataResultViewModel>> buscarMarcacionEmpleadoPieChartEmpleado(@RequestBody MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto) {

        List<PieChartDataResultViewModel> result;
        result = dashboardService.searchPieChartEmpleado(busquedaMarcacionDto);
        if (result == null)
            result = new ArrayList<>();

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @RequestMapping(value = "/buscarEmpleadoIndicadorDashBoardXmes", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<List<PieChartDataResultViewModel>> buscarEmpleadoIndicadorDashBoardXmes(@RequestBody MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto) {

        List<PieChartDataResultViewModel> result ;
        result = dashboardService.buscarEmpleadoIndicadorDashBoardXmes(busquedaMarcacionDto);
        result.stream().forEach(a -> a.setMinutosTardanzaXdia(dashboardService.minutosTardanciaxDia(busquedaMarcacionDto)));
        List<PieChartDataResultViewModel> listIdMinutosTardanzasXmes = dashboardService.listMinutosTardanciaxMes(busquedaMarcacionDto);
        Long sumMinutosTardanzasXmes = listIdMinutosTardanzasXmes.stream().mapToLong(a -> a.getMinutosTardanzaXmes()).sum();

        result.stream().forEach(a -> a.setMinutosTardanzaXmes(sumMinutosTardanzasXmes));

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @RequestMapping(value = "/buscarIndicadorRRHHDashBoard", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<List<IndicadorRRHHResultViewModel>> buscarIndicadorRRHHDashBoard(@RequestBody MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto) {

        List<IndicadorRRHHResultViewModel> result ;
        result = dashboardService.buscarIndicadorRRHHDashBoard(busquedaMarcacionDto);
        if (result == null)
            result = new ArrayList<>();

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @RequestMapping(value = "/busquedaPermisoDashboardRRHH", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<List<PermisoEmpleadoResultViewModel>> busquedaPermisoDashboardRRHH(@RequestBody MarcacionDashboardFilterViewModel busquedaMarcacionDto) {

        List<PermisoEmpleadoResultViewModel> result ;
        result = dashboardService.searchPermisoDashboardRRHH(busquedaMarcacionDto);
        if (result == null)
            result = new ArrayList<>();
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @RequestMapping(value = "/busquedaPermisoDashboardJefe", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<List<PermisoEmpleadoResultViewModel>> busquedaPermisoDashboardJefe(@RequestBody MarcacionDashboardFilterViewModel busquedaMarcacionDto) {

        List<PermisoEmpleadoResultViewModel> result ;
        result = dashboardService.searchPermisoDashboardJefe(busquedaMarcacionDto);
        if (result == null)
            result = new ArrayList<>();
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @RequestMapping(value = "/busquedaPermisoDashboardEmpleado", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<List<PermisoEmpleadoResultViewModel>> busquedaPermisoDashboardEmpleado(@RequestBody MarcacionDashboardEmpleadoFilterViewModel busquedaMarcacionDto) {

        List<PermisoEmpleadoResultViewModel> result;
        result = dashboardService.searchPermisoDashboardEmpleado(busquedaMarcacionDto);
        if (result == null)
            result = new ArrayList<>();
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @RequestMapping(value = "/busquedaVacacionesDashboardRRHH", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<List<VacacionResultViewModel>> busquedaVacacionesDashboardRRHH(@RequestBody MarcacionDashboardFilterViewModel marcacionDashboardEmpleadoDto) {

        List<VacacionResultViewModel> result;
        result = dashboardService.busquedaVacacionesDashboardRRHH(marcacionDashboardEmpleadoDto);
        if (result == null)
            result = new ArrayList<>();

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @RequestMapping(value = "/busquedaVacacionesDashboardJefe", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<List<VacacionResultViewModel>> busquedaVacacionesDashboardJefe(@RequestBody MarcacionDashboardFilterViewModel marcacionDashboardEmpleadoDto) {

        List<VacacionResultViewModel> result;
        result = dashboardService.searchVacacionesDashboardJefe(marcacionDashboardEmpleadoDto);
        if (result == null)
            result = new ArrayList<>();

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    //Empleado
    @RequestMapping(value = "/busquedaVacacioneDashboardEmpleado", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<List<VacacionResultViewModel>> busquedaVacacioneDashboardEmpleado(@RequestBody MarcacionDashboardEmpleadoFilterViewModel marcacionDashboardEmpleadoDto) {

        List<VacacionResultViewModel> result ;
        result = dashboardService.searchVacacionesDashboardEmpleado(marcacionDashboardEmpleadoDto);
        if (result == null)
            result = new ArrayList<>();

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    //rrhh
    @RequestMapping(value = "/busquedaHorasExtrasDashboardRRHH", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<List<HorasExtraEmpleadoResultViewModel>> busquedaHorasExtrasDashboardRRHH(@RequestBody MarcacionDashboardFilterViewModel marcacionDashboardEmpleadoDto) {

        List<HorasExtraEmpleadoResultViewModel> result ;
        result = dashboardService.searchHorasExtrasDashboardRRHH(marcacionDashboardEmpleadoDto);
        if (result == null)
            result = new ArrayList<>();

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @RequestMapping(value = "/buscarHorasExtrasDashboardJefe", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<List<HorasExtraEmpleadoResultViewModel>> buscarHorasExtrasDashboardJefe(@RequestBody MarcacionDashboardFilterViewModel marcacionDashboardEmpleadoDto) {

        List<HorasExtraEmpleadoResultViewModel> result ;
        result = dashboardService.searchHorasExtrasDashboardJefe(marcacionDashboardEmpleadoDto);
        if (result == null)
            result = new ArrayList<>();

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    //Empleado
    @RequestMapping(value = "/busquedaHorasExtrasDashboardEmpledo", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<List<HorasExtraEmpleadoResultViewModel>> busquedaHorasExtrasDashboardEmpledo(@RequestBody MarcacionDashboardEmpleadoFilterViewModel marcacionDashboardEmpleadoDto) {

        List<HorasExtraEmpleadoResultViewModel> result ;
        result = dashboardService.busquedaHorasExtrasDashboardEmpledo(marcacionDashboardEmpleadoDto);
        if (result == null)
            result = new ArrayList<>();

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

}
