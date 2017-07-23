package pe.com.tss.runakuna.domain.model.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.com.tss.runakuna.domain.model.entities.Marcador;

/**
 * Created by josediaz on 27/03/2017.
 */

@Repository
public interface MarcadorJpaRepository extends CrudRepository<Marcador, Long>, JpaRepository<Marcador, Long>{
	
	@Query("SELECT m FROM Marcador m WHERE  m.codigo = :codigo")
	List<Marcador> getMarcadorByCodigo(@Param("codigo") String codigo);
}
