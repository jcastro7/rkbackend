package pe.com.empresa.rk.security;

import pe.com.empresa.rk.domain.model.entities.Usuario;

import java.util.Optional;

/**
 * Created by josediaz on 23/11/2016.
 */
public interface UserService {
    public Optional<Usuario> getByUsername(String username);
    
    public Optional<Usuario> getByUserId(Long userId);
}
