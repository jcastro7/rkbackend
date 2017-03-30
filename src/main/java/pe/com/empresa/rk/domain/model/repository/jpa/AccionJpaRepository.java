package pe.com.empresa.rk.domain.model.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pe.com.empresa.rk.domain.model.entities.Accion;

@Repository
public interface AccionJpaRepository extends CrudRepository<Accion, Long>, JpaRepository<Accion, Long> {

}
