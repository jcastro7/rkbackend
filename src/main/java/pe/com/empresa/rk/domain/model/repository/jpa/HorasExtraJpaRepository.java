package pe.com.empresa.rk.domain.model.repository.jpa;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.empresa.rk.domain.model.entities.HorasExtra;

@Repository
public interface HorasExtraJpaRepository extends CrudRepository<HorasExtra, Long>, JpaRepository<HorasExtra, Long>{
	
	@Query("SELECT h FROM HorasExtra h WHERE h.fecha = :fecha AND h.empleado.idEmpleado = :idEmpleado AND h.estado = 'A'")
	HorasExtra findOneByIdEmpleadoAndFecha(@Param("fecha") Date fecha, @Param("idEmpleado") Long idEmpleado);
}
