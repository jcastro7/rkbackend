package pe.com.tss.runakuna.domain.model.repository.jpa;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.domain.model.entities.Calendario;

@Repository
public interface CalendarioJpaRepository extends CrudRepository<Calendario, Long>, JpaRepository<Calendario, Long>{

	@Query("SELECT c FROM Calendario c WHERE c.fecha =:fecha")
	Calendario getByDate(@Param("fecha") Date fecha);
	
}
