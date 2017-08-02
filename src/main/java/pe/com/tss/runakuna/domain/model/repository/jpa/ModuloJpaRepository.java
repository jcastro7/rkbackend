package pe.com.tss.runakuna.domain.model.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.domain.model.entities.Modulo;
import pe.com.tss.runakuna.domain.model.entities.Moneda;

@Repository
public interface ModuloJpaRepository extends CrudRepository<Modulo, Long>, JpaRepository<Modulo, Long>{

}
