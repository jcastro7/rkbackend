package pe.com.tss.runakuna;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.catalina.connector.RequestFacade;
import org.apache.catalina.connector.ResponseFacade;
import pe.com.tss.runakuna.support.LogContext;
import pe.com.tss.runakuna.util.StringUtil;

@SpringBootApplication
@EnableConfigurationProperties
@EnableScheduling
@EnableCaching
public class RunakunaBackendApplication {


    public static void main(String[] args) {
    	
    	SpringApplication.run(RunakunaBackendApplication.class, args);

    }



}
