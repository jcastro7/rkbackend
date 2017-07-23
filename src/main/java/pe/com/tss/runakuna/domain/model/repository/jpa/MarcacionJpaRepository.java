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
	
	@Query("SELECT m FROM Marcacion m WHERE (m.horaIngreso is null OR m.horaInicioAlmuerzo is null OR m.horaFinAlmuerzo is null OR m.horaSalida is null) AND m.empleado.categoriaEmpleado='E' AND m.fecha =:fecha")
	List<Marcacion> obtenerMarcacionIncorrectasYNoEsPersonalDeConfianza(@Param("fecha") Date fecha);
	
	@Query("SELECT m FROM Marcacion m WHERE m.horaIngreso is null AND m.empleado.categoriaEmpleado='C' AND m.fecha =:fecha")
	List<Marcacion> obtenerMarcacionIncorrectasYEsPersonalDeConfianza(@Param("fecha") Date fecha);
	
	@Query("SELECT m FROM Marcacion m WHERE m.fecha =:fecha")
	List<Marcacion> obtenerMarcacionesDelDia(@Param("fecha") Date fecha);
	
	@Query("SELECT m FROM Marcacion m WHERE m.empleado.idEmpleado =:idEmpleado")
	List<Marcacion> obtenerMarcacionesByIdXmes(@Param("idEmpleado") Long idEmpleado);
	
	@Query("SELECT m FROM Marcacion m WHERE  m.empleado.idEmpleado = :idEmpleado AND DATEPART(dw, m.fecha)=:dayNumber AND m.fecha >=:fechaInicio")
	List<Marcacion> getMarcacionRecalcular(@Param("idEmpleado") Long idEmpleado, @Param("fechaInicio") Date fechaInicio,  @Param("dayNumber") Integer dayNumber);
	
	@Query("SELECT m FROM Marcacion m WHERE  m.empleado.idEmpleado = :idEmpleado AND DATEPART(dw, m.fecha)=:dayNumber AND m.fecha >=:fechaInicio AND m.fecha <=:fechaFin")
	List<Marcacion> getMarcacionRecalcularWithinRangeDate(@Param("idEmpleado") Long idEmpleado, @Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin,  @Param("dayNumber") Integer dayNumber);
	
	
	@Query("SELECT m FROM Marcacion m WHERE  m.recalcular = :estadoRecalcular ORDER BY m.empleado.idEmpleado, m.fecha")
	List<Marcacion> getMarcacionesByEstadoRecalcular(@Param("estadoRecalcular") String estadoRecalcular);
	
	@Query("SELECT m FROM Marcacion m WHERE m.empleado.idEmpleado =:idEmpleado AND YEAR(m.fecha)=:anio AND MONTH(m.fecha)=:mes and m.fecha<>:fecha")
	List<Marcacion> getAllByIdEmpleadoAndMes(@Param("idEmpleado") Long idEmpleado, @Param("anio") Integer anio, @Param("mes") Integer mes,@Param("fecha") Date fecha);
	
	@Query("SELECT m FROM Marcacion m WHERE m.empleado.idEmpleado =:idEmpleado AND m.fecha >=:fechaInicio AND m.fecha<=:fechaFin")
	List<Marcacion> getMarcacionByIdEmpleadoAndDateRange(@Param("idEmpleado") Long idEmpleado, @Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);
	
	@Query("SELECT m FROM Marcacion m WHERE m.fecha =:fecha AND m.estado='IN'")
	List<Marcacion> getMarcacionInasistentesByDate(@Param("fecha") Date fecha);

	@Query("SELECT m FROM Marcacion m WHERE m.fechaActualizacion >:fecha")
	List<Marcacion> obtenerMarcacionesActualizadasDelDia(@Param("fecha") Date fecha);
	
	@Query("SELECT m FROM Marcacion m WHERE m.empleado.idEmpleado =:idEmpleado AND m.fecha >:fechaCese")
	List<Marcacion> getAllIdEmpleadoAndDateCese(@Param("idEmpleado") Long idEmpleado, @Param("fechaCese") Date fechaCese);
	
	
}
