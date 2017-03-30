package pe.com.tss.runakuna.domain.model.repository.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.domain.model.entities.HorarioEmpleado;

@Repository
public interface HorarioEmpleadoJpaRepository extends CrudRepository<HorarioEmpleado, Long>, JpaRepository<HorarioEmpleado, Long>{

	@Query("SELECT e FROM HorarioEmpleado e WHERE e.empleado.idEmpleado = :idEmpleado")
	HorarioEmpleado findByIdEmpleado(@Param("idEmpleado") Long idEmpleado);
	
	@Query("SELECT e FROM HorarioEmpleado e WHERE e.finVigencia = :finVigencia")
	HorarioEmpleado findByFechaFin(@Param("finVigencia") Date finVigencia);
	
	@Query("SELECT e FROM HorarioEmpleado e WHERE e.inicioVigencia = :inicioVigencia AND e.empleado.idEmpleado = :idEmpleado")
	HorarioEmpleado findByFechaInicio(@Param("inicioVigencia") Date inicioVigencia,@Param("idEmpleado") Long idEmpleado);
	
	
	@Query("SELECT e FROM HorarioEmpleado e WHERE ((e.inicioVigencia<=:fechaVigente AND e.finVigencia IS NULL) OR (e.inicioVigencia<=:fechaVigente AND e.finVigencia IS NOT NULL AND e.finVigencia>=:fechaVigente)) AND e.empleado.idEmpleado = :idEmpleado")
	HorarioEmpleado obtenerHorarioPorFechaVigente(@Param("fechaVigente") Date fechaVigente, @Param("idEmpleado") Long idEmpleado);
	
	@Query("SELECT e FROM HorarioEmpleado e WHERE e.horario.idHorario = :idHorario")
	List<HorarioEmpleado> findByIdHorario(@Param("idHorario") Long idHorario);
	
	@Query("SELECT e FROM HorarioEmpleado e WHERE e.empleado.idEmpleado = :idEmpleado AND e.finVigencia IS NULL AND e.inicioVigencia<=:fecha")
	HorarioEmpleado obtenerUltimoHorarioEmpleado(@Param("fecha") Date fecha, @Param("idEmpleado") Long idEmpleado);

	@Query("SELECT e FROM HorarioEmpleado e WHERE e.empleado.idEmpleado = :idEmpleado")
	List<HorarioEmpleado> obtenerHorariosEmpleado(@Param("idEmpleado") Long idEmpleado);
	
	@Query("SELECT e FROM HorarioEmpleado e WHERE e.empleado.idEmpleado = :idEmpleado AND e.inicioVigencia <= :inicioVigencia ORDER BY e.inicioVigencia")
	List<HorarioEmpleado> findPrevHorarioEmpleado(@Param("idEmpleado") Long idEmpleado, @Param("inicioVigencia") Date inicioVigencia);
}
