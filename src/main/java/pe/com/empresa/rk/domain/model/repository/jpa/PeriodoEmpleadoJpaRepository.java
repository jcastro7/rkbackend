package pe.com.empresa.rk.domain.model.repository.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.empresa.rk.domain.model.entities.PeriodoEmpleado;

@Repository
public interface PeriodoEmpleadoJpaRepository extends CrudRepository<PeriodoEmpleado, Long>,JpaRepository<PeriodoEmpleado,Long> {
	
	@Query("SELECT p FROM PeriodoEmpleado p WHERE (p.fechaInicio <= :fecha AND p.fechaFin >= :fecha) AND p.empleado.idEmpleado = :idEmpleado")
	PeriodoEmpleado obtenerPeriodoEmpleadoPorFechayEmpleado(@Param("fecha") Date fecha, @Param("idEmpleado") Long idEmpleado);
	
	@Query("SELECT p FROM PeriodoEmpleado p WHERE p.empleado.idEmpleado = :idEmpleado order by p.fechaInicio desc ")
	List<PeriodoEmpleado> obtenerUltimoPeriodoEmpleadoVigente(@Param("idEmpleado") Long idEmpleado);
	
}
