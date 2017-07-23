package pe.com.tss.runakuna.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.tss.runakuna.service.ReporteMarcacionService;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;
import pe.com.tss.runakuna.view.model.ReporteMarcacionFilterViewModel;
import pe.com.tss.runakuna.view.model.ReporteMarcacionResultViewModel;
import pe.com.tss.runakuna.view.model.ReporteMarcacionViewModel;

/**
 * Created by josediaz on 14/12/2016.
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/api/reporteMarcacion")
public class ReporteMarcacionController extends BaseController{

    private final Logger LOG = LoggerFactory.getLogger(ReporteMarcacionController.class);

    @Autowired
    ReporteMarcacionService reporteMarcacionService;



    @RequestMapping(value = "/registrarReporteMarcacion", method = RequestMethod.POST)
    public @ResponseBody
    NotificacionViewModel registrarReporteMarcacion(@RequestBody ReporteMarcacionViewModel reporteMarcacionDto) {
        NotificacionViewModel dto;
        if(reporteMarcacionDto.getIdReporteMarcacion()==null){
            dto = reporteMarcacionService.create(reporteMarcacionDto);
        } else {
            dto = reporteMarcacionService.update(reporteMarcacionDto);
        }
        return dto;
    }

    @RequestMapping(value = "/obtenerReportesMarcacion", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<List<ReporteMarcacionResultViewModel>> obtenerReportes(@RequestBody ReporteMarcacionFilterViewModel dto){
        List<ReporteMarcacionResultViewModel> result;
        result = reporteMarcacionService.search(dto);
        if(result == null)
            result = new ArrayList<>();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "/obtenerReporteMarcacionDetalle", method = RequestMethod.POST)
    public @ResponseBody
    ReporteMarcacionViewModel obtenerReporteMarcacionDetalle(@RequestBody Long idReporteMarcacion){
        ReporteMarcacionViewModel result;
        result = reporteMarcacionService.obtenerReporteMarcacionDetalle(idReporteMarcacion);
        if(result == null)
            result = new ReporteMarcacionViewModel();
        return result;
    }

    @RequestMapping(value = "/eliminarReporteMarcacion", method = RequestMethod.POST)
    public NotificacionViewModel eliminarReporteMarcacion(@RequestBody ReporteMarcacionViewModel dto) {
        NotificacionViewModel notificacionViewModel;
        notificacionViewModel= reporteMarcacionService.eliminarReporteMarcacion(dto.getIdReporteMarcacion());
        return notificacionViewModel;
    }


}
