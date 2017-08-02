package pe.com.tss.runakuna.domain.model.repository.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.domain.model.entities.RegistroMarcacionEmpleado;

@Repository
public interface RegistroMarcacionEmpleadoJpaRepository extends CrudRepository<RegistroMarcacionEmpleado, Long>, JpaRepository<RegistroMarcacionEmpleado, Long>{

	@Query("SELECT m FROM RegistroMarcacionEmpleado m WHERE m.empleado.idEmpleado =:idEmpleado AND m.fecha =:fecha AND m.tipo =:tipo ORDER BY m.fecha, m.fechaCreacion")
	List<RegistroMarcacionEmpleado> findByIdEmpleadoAndFecha(@Param("idEmpleado") Long idEmpleado, @Param("fecha") Date fecha, @Param("tipo") String tipo);
	
	@Query("SELECT m FROM RegistroMarcacionEmpleado m WHERE m.empleado.idEmpleado =:idEmpleado AND m.fecha =:fecha ORDER BY m.fecha, m.hora")
	List<RegistroMarcacionEmpleado> findAllByIdEmpleadoAndFecha(@Param("idEmpleado") Long idEmpleado, @Param("fecha") Date fecha);
	
}
