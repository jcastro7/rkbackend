package pe.com.tss.runakuna.security.auth.jwt;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import pe.com.tss.runakuna.domain.model.entities.Usuario;
import pe.com.tss.runakuna.security.UserService;
import pe.com.tss.runakuna.security.auth.JwtAuthenticationToken;
import pe.com.tss.runakuna.security.config.JwtSettings;
import pe.com.tss.runakuna.security.model.UserContext;
import pe.com.tss.runakuna.security.model.token.RawAccessJwtToken;

/**
 * Created by josediaz on 23/11/2016.
 */
@Component
@SuppressWarnings("unchecked")
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final JwtSettings jwtSettings;



    @Autowired
    private UserService userService;

    
    @Autowired
    public JwtAuthenticationProvider(JwtSettings jwtSettings) {
        this.jwtSettings = jwtSettings;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        RawAccessJwtToken rawAccessToken = (RawAccessJwtToken) authentication.getCredentials();

        Jws<Claims> jwsClaims = rawAccessToken.parseClaims(jwtSettings.getTokenSigningKey());
        Long subject = Long.valueOf(jwsClaims.getBody().getSubject());



        Usuario user = userService.getByUserId(subject).orElseThrow(() -> new UsernameNotFoundException("User Id no encontrado: " + subject));


        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRol().authority()))
                .collect(Collectors.toList());

        UserContext context = UserContext.create(subject, authorities);

        return new JwtAuthenticationToken(context, context.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }
}