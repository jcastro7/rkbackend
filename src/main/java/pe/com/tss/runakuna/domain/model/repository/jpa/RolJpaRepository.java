package pe.com.tss.runakuna.domain.model.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import pe.com.tss.runakuna.domain.model.entities.Rol;

/**
 * Created by josediaz on 23/11/2016.
 */
public interface RolJpaRepository extends JpaRepository<Rol, Long> {

	@Modifying
	@Transactional
	@Query("Delete from Rol r where r.idRol =:idRol and r.rolSistema = :rolSistema ")
    void deleteByIdRol(@Param("idRol") Long idRol,@Param("rolSistema") Integer rolSistema);
}
