package pe.com.tss.runakuna.domain.model.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.domain.model.entities.DepartamentoArea;

@Repository
public interface DepartamentoAreaJpaRepository extends CrudRepository<DepartamentoArea, Long>, JpaRepository<DepartamentoArea, Long>{
	
	@Query("SELECT d FROM DepartamentoArea d WHERE d.unidadDeNegocio.idUnidadDeNegocio =:idUnidadDeNegocio")
	List<DepartamentoArea> findByUndNegocio(@Param("idUnidadDeNegocio") Long idUnidadDeNegocio);
	
	@Query("SELECT d FROM DepartamentoArea d WHERE d.idDepartamentoArea =:idDepartamentoArea")
	DepartamentoArea findByCodigo(@Param("idDepartamentoArea") Long idDepartamentoArea);
	
	@Query("SELECT d FROM DepartamentoArea d WHERE d.estado ='A'")
	List<DepartamentoArea> getAllDepartamentosActivos();

}
