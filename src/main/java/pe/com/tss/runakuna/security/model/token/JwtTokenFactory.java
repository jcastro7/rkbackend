package pe.com.tss.runakuna.security.model.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.com.tss.runakuna.view.model.EmpleadoViewModel;
import pe.com.tss.runakuna.view.model.UsuarioViewModel;
import pe.com.tss.runakuna.security.config.JwtSettings;
import pe.com.tss.runakuna.security.model.Scopes;
import pe.com.tss.runakuna.security.model.UserContext;
import pe.com.tss.runakuna.service.EmpleadoService;
import pe.com.tss.runakuna.service.UsuarioService;
import pe.com.tss.runakuna.util.StringUtil;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by josediaz on 23/11/2016.
 */
@Component
public class JwtTokenFactory {
    private final JwtSettings settings;


    @Autowired
    private EmpleadoService empleadoService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    public JwtTokenFactory(JwtSettings settings) {
        this.settings = settings;
    }

    /**
     * Factory method for issuing new JWT Tokens.
     *
     * @param username
     * @param roles
     * @return
     */
    public AccessJwtToken createAccessJwtToken(UserContext userContext) {
        if (userContext.getUserId()==null)
            throw new IllegalArgumentException("No se puede crear token JWT sin usuario");

        if (userContext.getAuthorities() == null || userContext.getAuthorities().isEmpty())
            throw new IllegalArgumentException("El Usuario no tiene ning\u00fan privilegio");

        Claims claims = Jwts.claims().setSubject(String.valueOf(userContext.getUserId()));
        //claims.put("scopes", userContext.getAuthorities().stream().map(s -> s.toString()).collect(Collectors.toList()));

         EmpleadoViewModel empleadoDto = new EmpleadoViewModel();

        UsuarioViewModel usuarioDto = usuarioService.cargarUsuarioPorcuentaUsuario(userContext.getUserId());
        DateTime currentTime = new DateTime();
        if(usuarioDto.getIdEmpleado()==null || usuarioDto.getCuentaUsuario()=="admin"){
            claims.put("cuentaUsuario", usuarioDto.getCuentaUsuario());
            claims.put("empleado", "");
        	claims.put("administrador", "Administrador");
        	String token = Jwts.builder()
                    .setClaims(claims)
                    .setIssuer(settings.getTokenIssuer())
                    .setIssuedAt(currentTime.toDate())
                    .setExpiration(currentTime.plusMinutes(settings.getTokenExpirationTime()).toDate())
                    .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
                    .compact();
        	return new AccessJwtToken(token, claims);
        }
        empleadoDto.setIdEmpleado(usuarioDto.getIdEmpleado());
        EmpleadoViewModel respuesta = empleadoService.findOneAccessJwtToken(empleadoDto.getIdEmpleado());

        claims.put("cuentaUsuario", usuarioDto.getCuentaUsuario());
        claims.put("empleado", StringUtil.toCamelCase(respuesta.getNombre()) + " " + StringUtil.toCamelCase(respuesta.getApellidoPaterno()) + " " + StringUtil.toCamelCase(respuesta.getApellidoMaterno()) );

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(settings.getTokenIssuer())
                .setIssuedAt(currentTime.toDate())
                .setExpiration(currentTime.plusMinutes(settings.getTokenExpirationTime()).toDate())
                .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
                .compact();
//        HS512
        return new AccessJwtToken(token, claims);
    }

    public JwtToken createRefreshToken(UserContext userContext) {
        if (userContext.getUserId()==null) {
            throw new IllegalArgumentException("No se puede crear token JWT sin usuario");
        }
        DateTime currentTime = new DateTime();

        Claims claims = Jwts.claims().setSubject(String.valueOf(userContext.getUserId()));
        //claims.put("scopes", Arrays.asList(Scopes.REFRESH_TOKEN.authority()));
        EmpleadoViewModel empleadoDto = new EmpleadoViewModel();

        UsuarioViewModel usuarioDto = usuarioService.cargarUsuarioPorcuentaUsuario(userContext.getUserId());
        if(usuarioDto.getIdEmpleado()==null || usuarioDto.getCuentaUsuario()=="admin"){
            claims.put("cuentaUsuario", usuarioDto.getCuentaUsuario());
        	claims.put("administrador", "Administrador");
        	String token = Jwts.builder()
                    .setClaims(claims)
                    .setIssuer(settings.getTokenIssuer())
                    .setId(UUID.randomUUID().toString())
                    .setIssuedAt(currentTime.toDate())
                    .setExpiration(currentTime.plusMinutes(settings.getRefreshTokenExpTime()).toDate())
                    .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
                    .compact();
        	return new AccessJwtToken(token, claims);
        }

        empleadoDto.setIdEmpleado(usuarioDto.getIdEmpleado());
        EmpleadoViewModel respuesta = empleadoService.findOne(empleadoDto.getIdEmpleado());


        claims.put("cuentaUsuario", usuarioDto.getCuentaUsuario());
        claims.put("empleado", StringUtil.toCamelCase(respuesta.getNombre()) + " " + StringUtil.toCamelCase(respuesta.getApellidoPaterno()) + " " + StringUtil.toCamelCase(respuesta.getApellidoMaterno()) );


        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(settings.getTokenIssuer())
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(currentTime.toDate())
                .setExpiration(currentTime.plusMinutes(settings.getRefreshTokenExpTime()).toDate())
                .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
                .compact();

        return new AccessJwtToken(token, claims);
    }
}
