package pe.com.empresa.rk.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pe.com.empresa.rk.domain.model.entities.Empleado;
import pe.com.empresa.rk.domain.model.entities.Marcador;
import pe.com.empresa.rk.domain.model.repository.jdbc.MarcacionJdbcRepository;
import pe.com.empresa.rk.domain.model.repository.jpa.EmpleadoJpaRepository;
import pe.com.empresa.rk.domain.model.repository.jpa.MarcadorJpaRepository;
import pe.com.empresa.rk.service.AlertaService;
import pe.com.empresa.rk.service.MailService;
import pe.com.empresa.rk.service.VerificarMarcadores;
import pe.com.empresa.rk.view.model.AlertaSubscriptorViewModel;
import pe.com.empresa.rk.view.model.AlertaViewModel;
import pe.com.empresa.rk.view.model.GeneraMsjeAlertaEmpleadoViewModel;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by josediaz on 24/03/2017.
 */

@Service
public class VerificarMarcadoresImpl implements VerificarMarcadores {



    private static final Logger LOGGER  = LoggerFactory.getLogger(VerificarMarcadoresImpl.class);

    @Autowired
    DataSource dataSource;

    @Autowired
    MarcadorJpaRepository marcadorJpaRepository;


    @Autowired
    MarcacionJdbcRepository marcacionJdbcRepository;

    @Autowired
    MailService mailService;

    @Autowired
    AlertaService alertaService;

    @Autowired
    EmpleadoJpaRepository empleadoJpaRepository;


    //@Scheduled(cron = "0 0/1 * * * *")
    @Scheduled(cron = "0 0/50 * * * *")
    @Override
    public void verificar() {


        List<Marcador> marcadores = marcadorJpaRepository.findAll();
        AlertaViewModel alertaDto=alertaService.obtenerAlerta("Alerta17");
        GeneraMsjeAlertaEmpleadoViewModel msjeAlertaDto;


        for (Marcador marcador : marcadores) {

            int numeroMarcaciones = marcacionJdbcRepository.countMarcacionesPorCodigo(marcador.getCodigo());

            if (numeroMarcaciones == 0) {

                msjeAlertaDto=new GeneraMsjeAlertaEmpleadoViewModel();
                msjeAlertaDto.setIdAlerta(alertaDto.getIdAlerta());

                Map<String,String> parametrosMensaje;

                for(AlertaSubscriptorViewModel alertaSubscriptorDto:alertaDto.getSubscriptores()) {
                    Empleado empl=empleadoJpaRepository.findOne(alertaSubscriptorDto.getIdEmpleado());

                    msjeAlertaDto.setIdEmpleado(empl.getIdEmpleado());
                    parametrosMensaje=new HashMap<String,String>();
                    parametrosMensaje.put("Codigo", marcador.getCodigo());

                    msjeAlertaDto.setParametrosMsje(parametrosMensaje);
                    alertaService.generarMensajeAlerta(msjeAlertaDto);

                    try {
                        String[] correos = new String[alertaDto.getSubscriptores().size()];
                        sendAlertaToSubscriptores(marcador, correos);
                    }catch(Exception ex){
                        LOGGER.info(ex.getMessage(), ex);
                    }

                }
            }

        }


    }

    private void sendAlertaToSubscriptores(Marcador marcador, String[] correos) {
        StringBuilder sb=new StringBuilder();
        sb.append("<p>");
        sb.append("<table>");
        sb.append("<tr>");
        sb.append("<td>El dispositivo [").append(marcador.getCodigo()).append("] no esta funcionando correctamente. Las marcaciones no se estan registrando. </td>");
        sb.append("<td></td>");
        sb.append("</tr>");
        sb.append("</table>");
        mailService.sendEmail("Estado de Marcadores", sb.toString(), correos);
    }
}

