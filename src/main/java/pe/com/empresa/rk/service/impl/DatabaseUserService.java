package pe.com.empresa.rk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.empresa.rk.domain.model.repository.jpa.UsuarioRepository;
import pe.com.empresa.rk.domain.model.entities.Usuario;
import pe.com.empresa.rk.security.UserService;

import java.util.Optional;

/**
 * Created by josediaz on 23/11/2016.
 */
@Service
public class DatabaseUserService implements UserService {
    private final UsuarioRepository userRepository;

    @Autowired
    public DatabaseUserService(UsuarioRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UsuarioRepository getUserRepository() {
        return userRepository;
    }

    @Override
    public Optional<Usuario> getByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }
    

    public Optional<Usuario> getByUserId(Long userId) {
        return this.userRepository.findByUserId(userId);
    }
}
