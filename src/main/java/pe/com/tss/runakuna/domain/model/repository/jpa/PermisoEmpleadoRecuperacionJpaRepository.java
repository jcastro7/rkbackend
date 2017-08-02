package pe.com.tss.runakuna.domain.model.repository.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.com.tss.runakuna.domain.model.entities.PermisoEmpleado;
import pe.com.tss.runakuna.domain.model.entities.PermisoEmpleadoRecuperacion;

@Repository
public interface PermisoEmpleadoRecuperacionJpaRepository extends CrudRepository<PermisoEmpleadoRecuperacion, Long>, JpaRepository<PermisoEmpleadoRecuperacion,Long>{

	@Query("SELECT p FROM PermisoEmpleadoRecuperacion p WHERE p.fechaRecuperacion = :fecha AND p.permisoEmpleado.periodoEmpleado.empleado.idEmpleado =:idEmpleado AND p.permisoEmpleado.estado = 'A'")
	List<PermisoEmpleadoRecuperacion> obtenerPermisosRecuperacionPorIdPeriodoEmpleadoYFecha(@Param("idEmpleado") Long idEmpleado, @Param("fecha") Date fecha);
	
	@Query("SELECT p FROM PermisoEmpleadoRecuperacion p WHERE p.permisoEmpleado.idPermisoEmpleado =:idPermisoEmpleado")
	List<PermisoEmpleadoRecuperacion> obtenerPermisosRecuperacionPorIdPermiso(@Param("idPermisoEmpleado") Long idPermisoEmpleado);
	
	
}
