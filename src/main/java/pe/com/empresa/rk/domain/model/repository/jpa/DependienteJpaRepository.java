package pe.com.empresa.rk.domain.model.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pe.com.empresa.rk.domain.model.entities.Dependiente;

@Repository
public interface DependienteJpaRepository extends CrudRepository<Dependiente, Long>, JpaRepository<Dependiente, Long>{
	
}
