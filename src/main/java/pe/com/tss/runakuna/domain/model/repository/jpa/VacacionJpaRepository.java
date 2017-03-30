package pe.com.tss.runakuna.domain.model.repository.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.domain.model.entities.Vacacion;
import pe.com.tss.runakuna.view.model.VacacionesEmpleadoFilterViewModel;
import pe.com.tss.runakuna.view.model.VacacionesEmpleadoResultViewModel;

@Repository
public interface VacacionJpaRepository extends CrudRepository<Vacacion, Long>, JpaRepository<Vacacion, Long>{

	
	@Query("SELECT v FROM Vacacion v WHERE  (v.fechaInicio <= :fecha AND v.fechaFin >= :fecha) AND v.periodoEmpleado.idPeriodoEmpleado = :idPeriodoEmpleado")
	Vacacion obtenerVacaciones(@Param("fecha") Date fecha, @Param("idPeriodoEmpleado") Long idPeriodoEmpleado);

	@Query("SELECT v FROM Vacacion v WHERE v.estado != 'R' AND v.periodoEmpleado.idPeriodoEmpleado = :idPeriodoEmpleado AND v.idVacacion != :idVacacion")
	List<Vacacion> allListVacacionExceptionItself(@Param("idPeriodoEmpleado") Long idPeriodoEmpleado, @Param("idVacacion") Long idVacacion);

	
}
