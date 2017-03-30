package pe.com.empresa.rk.domain.model.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.empresa.rk.domain.model.entities.UsuarioRol;

/**
 * Created by josediaz on 23/11/2016.
 */
public interface UsuarioRolJpaRepository extends JpaRepository<UsuarioRol, Long> {
    /*@Query("select u from Usuario u left join fetch u.roles r where u.cuentaUsuario=:username")
    public Optional<Usuario> findByUsername(@Param("username") String username);*/
}
