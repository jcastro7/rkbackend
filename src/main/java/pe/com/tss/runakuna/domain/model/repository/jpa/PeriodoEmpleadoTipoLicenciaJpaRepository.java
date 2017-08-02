package pe.com.tss.runakuna.domain.model.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.domain.model.entities.PeriodoEmpleadoTipoLicencia;

@Repository
public interface PeriodoEmpleadoTipoLicenciaJpaRepository extends CrudRepository<PeriodoEmpleadoTipoLicencia, Long>,JpaRepository<PeriodoEmpleadoTipoLicencia,Long> {
	
	@Query("SELECT d FROM PeriodoEmpleadoTipoLicencia d WHERE d.periodoEmpleado.idPeriodoEmpleado =:idPeriodoEmpleado and d.tipoLicencia.idTipoLicencia= :idTipoLicencia ")
	PeriodoEmpleadoTipoLicencia findOneByIdPeriodoAndIdTipoLicencia (@Param("idPeriodoEmpleado") Long idPeriodoEmpleado, @Param("idTipoLicencia") Long idTipoLicencia );
	
	@Query("SELECT d FROM PeriodoEmpleadoTipoLicencia d WHERE d.periodoEmpleado.idPeriodoEmpleado =:idPeriodoEmpleado ")
	List<PeriodoEmpleadoTipoLicencia> findOneByIdPeriodo(@Param("idPeriodoEmpleado") Long idPeriodoEmpleado );
	
	
}
