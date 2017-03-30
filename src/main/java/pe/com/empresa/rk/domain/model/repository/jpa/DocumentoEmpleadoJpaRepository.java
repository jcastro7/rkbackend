package pe.com.empresa.rk.domain.model.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.empresa.rk.domain.model.entities.DocumentoEmpleado;

@Repository
public interface DocumentoEmpleadoJpaRepository extends CrudRepository<DocumentoEmpleado, Long>, JpaRepository<DocumentoEmpleado, Long>{

	@Query("SELECT d FROM DocumentoEmpleado d WHERE d.empleado.idEmpleado =:idEmpleado")
	List<DocumentoEmpleado> buscarDocumentosPorEmpleado(@Param("idEmpleado") Long idEmpleado);
	
	
	@Query("SELECT d FROM DocumentoEmpleado d WHERE d.empleado.idEmpleado =:idEmpleado AND d.tipoDocumento = 'PE'")
	List<DocumentoEmpleado> findAllOnlyPersonal(@Param("idEmpleado") Long idEmpleado);
	
	@Query("SELECT d FROM DocumentoEmpleado d WHERE d.empleado.idEmpleado =:idEmpleado AND d.tipoDocumento = 'FO'")
	DocumentoEmpleado findOneOnlyFotoPerfil(@Param("idEmpleado") Long idEmpleado);
	
	@Query("SELECT d FROM DocumentoEmpleado d WHERE d.empleado.idEmpleado =:idEmpleado AND d.licencia.idLicencia =:idLicencia AND d.tipoDocumento = 'LI'")
	List<DocumentoEmpleado> findAllOnlyLicencias(@Param("idEmpleado") Long idEmpleado, @Param("idLicencia") Long idLicencia);
	
}
