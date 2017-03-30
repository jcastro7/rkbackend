package pe.com.empresa.rk.domain.model.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pe.com.empresa.rk.domain.model.entities.Contrato;

@Repository
public interface ContratoJpaRepository extends CrudRepository<Contrato, Long>, JpaRepository<Contrato, Long>{

}
