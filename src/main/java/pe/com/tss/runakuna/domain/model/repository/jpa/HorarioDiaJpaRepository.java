package pe.com.tss.runakuna.domain.model.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.domain.model.entities.HorarioDia;

@Repository
public interface HorarioDiaJpaRepository extends CrudRepository<HorarioDia, Long>, JpaRepository<HorarioDia, Long>{
	
	@Modifying
	@Query("delete from HorarioDia h where h.horario.idHorario = :idHorario")
	Integer deleteByIdHorario(@Param("idHorario") Long idHorario);
	
	@Query("select h from HorarioDia h where h.horario.idHorario = :idHorario")
	List<HorarioDia> findByIdHorario(@Param("idHorario") Long idHorario);

}
