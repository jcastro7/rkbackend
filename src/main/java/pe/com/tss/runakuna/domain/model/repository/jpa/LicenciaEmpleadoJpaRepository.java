package pe.com.tss.runakuna.domain.model.repository.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.domain.model.entities.Licencia;

@Repository
public interface LicenciaEmpleadoJpaRepository extends CrudRepository<Licencia, Long>, JpaRepository<Licencia, Long>{

	@Query("SELECT l FROM Licencia l WHERE (l.fechaInicio <= :fecha AND l.fechaFin >= :fecha) AND l.empleado.idEmpleado = :idEmpleado AND l.estado = 'A'")
	Licencia obtenerLicencia(@Param("fecha") Date fecha, @Param("idEmpleado") Long idEmpleado);
	
	@Query("SELECT l FROM Licencia l LEFT JOIN l.tipoLicencia tl WHERE "
			+ "(tl.considerarLicenciaAlEnviar = 0 "
			+ "AND (l.fechaInicio <= :fecha AND l.fechaFin >=:fecha) "
			+ "AND l.empleado.idEmpleado = :idEmpleado "
			+ "AND l.estado = 'A') "
			+ "OR "
			+ "(tl.considerarLicenciaAlEnviar = 1 "
			+ "AND (l.fechaInicio <= :fecha AND l.fechaFin >=:fecha) "
			+ "AND l.empleado.idEmpleado = :idEmpleado "
			+ "AND l.estado IN ('E','A'))")
	List<Licencia> obtenerLicenciasPorIdEmpleadoYFecha(@Param("fecha") Date fecha, @Param("idEmpleado") Long idEmpleado);
}
