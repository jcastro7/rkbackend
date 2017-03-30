package pe.com.tss.runakuna.rest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import pe.com.tss.runakuna.domain.model.entities.Usuario;
import pe.com.tss.runakuna.security.UserService;
import pe.com.tss.runakuna.security.auth.jwt.extractor.TokenExtractor;
import pe.com.tss.runakuna.security.auth.jwt.verifier.TokenVerifier;
import pe.com.tss.runakuna.security.config.JwtSettings;
import pe.com.tss.runakuna.security.config.WebSecurityConfig;
import pe.com.tss.runakuna.security.model.token.RawAccessJwtToken;
import pe.com.tss.runakuna.service.impl.VerificarMarcadoresImpl;
import pe.com.tss.runakuna.support.LogContext;
import pe.com.tss.runakuna.view.model.AuditingEntityViewModel;
import pe.com.tss.runakuna.view.model.CurrentUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by josediaz on 16/03/2017.
 */
@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {



    private static final Logger LOGGER  = LoggerFactory.getLogger(TokenInterceptor.class);

    @Autowired
    @Qualifier("jwtHeaderTokenExtractor") private TokenExtractor tokenExtractor;
    @Autowired private JwtSettings jwtSettings;
    @Autowired private TokenVerifier tokenVerifier;

    @Autowired
    private UserService userService;

    @Autowired
    private Mapper mapper;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LogContext.cleanup();
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        try {

            String tokenPayload = request.getHeader(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM);

            if (tokenPayload != null && !"".equals(tokenPayload)) {

                CurrentUser currentUser = getCurrentUser(tokenPayload);

                ((BaseController) ((HandlerMethod) handler).getBean()).setCurrentUser(currentUser);

                request.setAttribute("currentUser", currentUser);

                LogContext.setUserId(currentUser.getCuentaUsuario());
            }

        }catch(Exception ex) {
            LOGGER.info(ex.getMessage(), ex);
            LogContext.cleanup();
            ex.printStackTrace();
        }


        return true;
    }

    private CurrentUser getCurrentUser(String tokenPayload) {
        CurrentUser currentUser = new CurrentUser();
        RawAccessJwtToken rawAccessToken = new RawAccessJwtToken(tokenExtractor.extract(tokenPayload));

        Jws<Claims> jwsClaims = rawAccessToken.parseClaims(jwtSettings.getTokenSigningKey());
        Long idUsuario = Long.valueOf(jwsClaims.getBody().getSubject());
        String cuentaUsuario = jwsClaims.getBody().get("cuentaUsuario").toString();
        String nombreCompleto = jwsClaims.getBody().get("empleado").toString();

        Usuario user = userService.getByUserId(idUsuario).orElseThrow(() -> new UsernameNotFoundException("User Id no encontrado: " + idUsuario));



        this.mapper.map(user, currentUser);

        currentUser.setNombreCompleto(nombreCompleto);
        currentUser.setCuentaUsuario(cuentaUsuario);

        return currentUser;
    }


}
