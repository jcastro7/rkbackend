package pe.com.tss.runakuna.domain.model.repository.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.domain.model.entities.HorasExtra;

@Repository
public interface HorasExtraJpaRepository extends CrudRepository<HorasExtra, Long>, JpaRepository<HorasExtra, Long>{
	
	@Query("SELECT h FROM HorasExtra h WHERE h.fecha = :fecha AND h.empleado.idEmpleado = :idEmpleado AND h.estado = 'A' AND h.tipo = 'T'")
	HorasExtra findOneByIdEmpleadoAndFecha(@Param("fecha") Date fecha, @Param("idEmpleado") Long idEmpleado);

	@Query("SELECT h FROM HorasExtra h WHERE h.fecha = :fecha AND h.empleado.idEmpleado = :idEmpleado AND h.estado = 'A' AND h.tipo = 'T'")
	List<HorasExtra> findAllByIdEmpleadoAndFecha(@Param("fecha") Date fecha, @Param("idEmpleado") Long idEmpleado);
	
	@Query("SELECT h FROM HorasExtra h WHERE h.fecha >= :fechaInicio AND h.fecha <= :fechaFin AND h.empleado.idEmpleado = :idEmpleado AND h.estado = 'P' AND h.tipo = 'T' ORDER BY h.fecha DESC")
	List<HorasExtra> findAllByIdEmpleadoAndRangeFecha(@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin, @Param("idEmpleado") Long idEmpleado);
}



