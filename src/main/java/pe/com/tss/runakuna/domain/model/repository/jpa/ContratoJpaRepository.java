package pe.com.tss.runakuna.domain.model.repository.jpa;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.domain.model.entities.Contrato;
import pe.com.tss.runakuna.domain.model.entities.PeriodoEmpleadoTipoLicencia;

@Repository
public interface ContratoJpaRepository extends CrudRepository<Contrato, Long>, JpaRepository<Contrato, Long>{

	@Query("SELECT d FROM Contrato d WHERE d.fechaInicio <=:fecha and (d.fechaFin is null or d.fechaFin>=:fecha) and d.empleado.idEmpleado= :idEmpleado ")
	Contrato buscarContratoVigentePorEmpleado (@Param("fecha") Date fecha, @Param("idEmpleado") Long idEmpleado );
}
