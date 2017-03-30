package pe.com.empresa.rk.domain.model.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pe.com.empresa.rk.domain.model.entities.Marcador;

/**
 * Created by josediaz on 27/03/2017.
 */

@Repository

public interface MarcadorJpaRepository extends CrudRepository<Marcador, Long>, JpaRepository<Marcador, Long>{
}
