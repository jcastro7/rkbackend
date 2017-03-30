package pe.com.tss.runakuna.domain.model.repository.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.domain.model.entities.Marcacion;

@Repository
public interface MarcacionJpaRepository extends CrudRepository<Marcacion, Long>, JpaRepository<Marcacion, Long>{

	@Query("SELECT m FROM Marcacion m WHERE m.empleado.idEmpleado =:idEmpleado AND m.fecha =:fecha")
	Marcacion obtenerMarcacionPorIdEmpleadoyDate(@Param("idEmpleado") Long idEmpleado, @Param("fecha") Date fecha);
	
	@Query("SELECT m FROM Marcacion m WHERE (m.horaIngreso is null OR m.horaInicioAlmuerzo is null OR m.horaFinAlmuerzo is null OR m.horaSalida is null) AND m.esPersonalDeConfianza=0 AND m.fecha =:fecha")
	List<Marcacion> obtenerMarcacionIncorrectasYNoEsPersonalDeConfianza(@Param("fecha") Date fecha);
	
	@Query("SELECT m FROM Marcacion m WHERE m.horaIngreso is null AND m.esPersonalDeConfianza=1 AND m.fecha =:fecha")
	List<Marcacion> obtenerMarcacionIncorrectasYEsPersonalDeConfianza(@Param("fecha") Date fecha);
	
	@Query("SELECT m FROM Marcacion m WHERE m.fecha =:fecha")
	List<Marcacion> obtenerMarcacionesDelDia(@Param("fecha") Date fecha);
	
	@Query("SELECT m FROM Marcacion m WHERE m.empleado.idEmpleado =:idEmpleado")
	List<Marcacion> obtenerMarcacionesByIdXmes(@Param("idEmpleado") Long idEmpleado);
	
}
