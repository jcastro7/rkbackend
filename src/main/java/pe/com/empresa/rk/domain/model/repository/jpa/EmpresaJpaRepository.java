package pe.com.empresa.rk.domain.model.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.empresa.rk.domain.model.entities.Empresa;

@Repository
public interface EmpresaJpaRepository extends CrudRepository<Empresa, Long>, JpaRepository<Empresa, Long>{

	@Query("SELECT e FROM Empresa e WHERE e.codigo = :codigo")
	Empresa findEmpresaByCode(@Param("codigo") String codigo);
	
}
