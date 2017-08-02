package pe.com.tss.runakuna.domain.model.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import pe.com.tss.runakuna.domain.model.entities.Autorizacion;

public interface AutorizacionJpaRepository extends CrudRepository<Autorizacion, Long>, JpaRepository<Autorizacion, Long>{

}
