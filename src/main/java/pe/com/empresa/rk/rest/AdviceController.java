package pe.com.empresa.rk.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;
import pe.com.empresa.rk.gateway.common.GatewayValidationException;
import pe.com.empresa.rk.dto.JsonResult;
import pe.com.empresa.rk.exception.BusinessException;
import pe.com.empresa.rk.exception.GenericRestException;

/**
 * Created by josediaz on 22/02/2017.
 */
@ControllerAdvice
public class AdviceController {
    public static final String ERR_MSG_PREFIX = "errors.";

    final Logger log = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ResponseEntity<JsonResult>  handleGenericExcepcion(Exception ex) {

        log.error(ex.getMessage(),ex);
        JsonResult result = new JsonResult();


        if(ex instanceof EmptyResultDataAccessException){
            result.setSeverity("error");
            result.setSummary("Error Base de Datos");
            result.setDetail("Se ha producido un error en la base de datos. Vuelva a intentarlo");

        }else if (ex instanceof ObjectOptimisticLockingFailureException) {
            result.setSeverity("warning");
            result.setSummary("Error Base de Datos");
            result.setDetail("El registro ha sido cambiado por otro usuario. Vuelva a intentarlo");

        } else if (ex instanceof GatewayValidationException) {
            result.setSeverity("error");
            result.setSummary("Error Base de Datos");
            result.setDetail("El registro ha sido cambiado por otro usuario. Vuelva a intentarlo");

        }else{
            result.setSeverity("error");
            result.setSummary("Error Generico");
            result.setDetail("Se ha producido un error general. " + ex.getMessage());
        }


        return new ResponseEntity<>(result,HttpStatus.INTERNAL_SERVER_ERROR);



    }

    @ResponseBody
    @ExceptionHandler(MultipartException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ResponseEntity<JsonResult> handleMultipartException(Exception ex) {

        log.error(ex.getMessage(),ex);

        JsonResult result = new JsonResult();

        result.setStatus("MAX_FILE_SIZE");
        result.setSeverity("error");  //info, warn
        result.setSummary("Upload File");
        result.setDetail("The file exceeds the maximum size allowed.");

        return new ResponseEntity<>(result,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String getCurrentSessionUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth!=null &&  auth.getPrincipal()!=null && auth.getPrincipal() instanceof UserDetails){
            return ((UserDetails) auth.getPrincipal()).getUsername();
        }
        return "";
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({ BusinessException.class })
    public ResponseEntity<JsonResult> handleBusinessException(
            BusinessException ex, WebRequest request) {

        log.error(ex.getMessage(),ex);

        JsonResult result = new JsonResult();

        result.setSeverity(ex.getSeverity());
        result.setSummary(ex.getSummary());
        result.setDetail(ex.getMessage());


        return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ GenericRestException.class })
    public ResponseEntity<JsonResult> handleMethodArgumentTypeMismatch(
            GenericRestException ex, WebRequest request) {

        log.error(ex.getMessage(),ex);

        JsonResult result = new JsonResult();

        result.setSeverity("error");
        result.setSummary("Error Sistema");
        result.setDetail("Se ha producido un error al procesar su request. Vuelva a intentarlo." + ex.getMessage());


        return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}