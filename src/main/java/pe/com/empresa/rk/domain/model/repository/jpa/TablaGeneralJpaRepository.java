package pe.com.empresa.rk.domain.model.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.empresa.rk.domain.model.entities.TablaGeneral;

@Repository
public interface TablaGeneralJpaRepository extends CrudRepository<TablaGeneral, Long>,JpaRepository<TablaGeneral,Long> {
	
	@Query("SELECT t FROM TablaGeneral t WHERE t.grupo LIKE :grupo ORDER BY t.idTablaGeneral ASC")
    List<TablaGeneral> findByGrupo(@Param("grupo") String grupo);

    @Query("SELECT t FROM TablaGeneral t WHERE t.grupo = :grupo and  LOWER(t.nombre) = LOWER(:nombre)")
    TablaGeneral findByGrupoAndNombre(@Param("grupo") String grupo,  @Param("nombre") String nombre);
    
    @Query("SELECT t FROM TablaGeneral t WHERE t.grupo = :grupo and  LOWER(t.codigo) = LOWER(:codigo)")
    TablaGeneral findByGrupoAndCodigo(@Param("grupo") String grupo,  @Param("codigo") String codigo);

}
