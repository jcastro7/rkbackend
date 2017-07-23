package pe.com.tss.runakuna.security.auth.ajax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import pe.com.tss.runakuna.domain.model.entities.Usuario;
import pe.com.tss.runakuna.security.model.UserContext;
import pe.com.tss.runakuna.service.LdapService;
import pe.com.tss.runakuna.service.impl.DatabaseUserService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by josediaz on 23/11/2016.
 */
@Component
public class AjaxAuthenticationProvider implements AuthenticationProvider {
    private final BCryptPasswordEncoder encoder;
    private final DatabaseUserService userService;

    @Value("${spring.security.ldap.active}")
    private boolean ldapActive;

    @Autowired
    private LdapService ldapService;

    @Autowired
    public AjaxAuthenticationProvider(final DatabaseUserService userService, final BCryptPasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.notNull(authentication, "Datos de autenticaci\u00f3n no proveidos");


        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        Usuario user = null;



        if(ldapActive){
            if(!ldapService.authenticate(username, password)){
                //throw new BadCredentialsException("Fallo la autenticación. Usuario o contraseña no validos.");
            	throw new BadCredentialsException("Usuario o contrase\u00f1a no v\u00e1lidos.");
            }
        }

        user = userService.getByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
        
        if(!ldapActive && !"A".equalsIgnoreCase(user.getEstado())){
        	throw new BadCredentialsException("Usuario inactivo.");
        }

        if (!ldapActive && !encoder.matches(password, user.getPassword())) {
                throw new BadCredentialsException("Usuario o contrase\u00f1a no v\u00e1lidos.");
        }






        if (user.getRoles() == null) throw new InsufficientAuthenticationException("Usuario no tiene roles asignados");

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRol().authority()))
                .collect(Collectors.toList());

        UserContext userContext = UserContext.create(user.getIdUsuario(), authorities);

        return new UsernamePasswordAuthenticationToken(userContext, null, userContext.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}