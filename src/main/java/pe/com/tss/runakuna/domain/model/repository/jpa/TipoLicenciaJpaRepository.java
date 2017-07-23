package pe.com.tss.runakuna.domain.model.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.domain.model.entities.TipoLicencia;

@Repository
public interface TipoLicenciaJpaRepository extends CrudRepository<TipoLicencia, Long>, JpaRepository<TipoLicencia, Long>{

	@Query("SELECT d FROM TipoLicencia d WHERE d.estado ='A'")
	List<TipoLicencia> findTipoLicencia();
	
}
