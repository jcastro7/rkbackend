package pe.com.empresa.rk.domain.model.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.empresa.rk.domain.model.entities.EmpleadoCompensacion;

@Repository
public interface CompensacionJpaRepository extends CrudRepository<EmpleadoCompensacion, Long>, JpaRepository<EmpleadoCompensacion, Long>{

	
	@Query("SELECT m FROM EmpleadoCompensacion m WHERE m.empleado.idEmpleado =:idEmpleado AND m.mes =:mes")
	EmpleadoCompensacion findByMonthAndIdEmpleado(@Param("idEmpleado") Long idEmpleado, @Param("mes") String mes);
	
}
