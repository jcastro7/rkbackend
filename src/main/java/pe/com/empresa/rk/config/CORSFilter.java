package pe.com.empresa.rk.config;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORSFilter implements Filter {

    @Value("${frontend.server}")
    private String frontendServer;

    @Value("${frontend.port}")
    private String frontendPort;


    // This is to be replaced with a list of domains allowed to access the server
    private List<String> allowedOrigins = null;
    private String mode = "DENY";

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        String port = (frontendPort!=null && !frontendPort.equals("")) ?   ":" + frontendPort : "";

        String frontendUrl = "http://" + frontendServer + port;

        this.allowedOrigins = Arrays.asList( frontendUrl,   "http://localhost:4200");

        // Lets make sure that we are working with HTTP (that is, against HttpServletRequest and HttpServletResponse objects)
        if (req instanceof HttpServletRequest && res instanceof HttpServletResponse) {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) res;


            response.setHeader("Access-Control-Allow-Origin", frontendUrl);

            
            response.setHeader("Vary", "Origin");
            response.setHeader("X-FRAME-OPTIONS", mode );
            //response.setHeader(" X-Frame-Options", mode );
           
            // Access-Control-Max-Age
            response.setHeader("Access-Control-Max-Age", "3600");

            // Access-Control-Allow-Credentials
            response.setHeader("Access-Control-Allow-Credentials", "true");

            // Access-Control-Allow-Methods
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");

            // Access-Control-Allow-Headers
            response.setHeader("Access-Control-Allow-Headers",
                    "Origin, Authorization, X-Requested-With, Content-Type, Accept");

            if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                chain.doFilter(req, res);
            }
        }


    }

    public void init(FilterConfig filterConfig) {
    }
}
