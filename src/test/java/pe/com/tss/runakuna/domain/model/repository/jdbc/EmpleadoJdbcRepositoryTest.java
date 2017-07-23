package pe.com.tss.runakuna.domain.model.repository.jdbc;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import pe.com.tss.runakuna.RunakunaBackendApplication;
import pe.com.tss.runakuna.security.config.WebSecurityConfig;
import pe.com.tss.runakuna.view.model.EmpleadoPlanillaResultViewModel;
import pe.com.tss.runakuna.view.model.EmpleadoViewModel;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes= {RunakunaBackendApplication.class, WebSecurityConfig.class})
@Transactional
public class EmpleadoJdbcRepositoryTest {

	@Autowired
	EmpleadoRepository empleadoJdbcRepository;
	
	@Autowired
	VacacionEmpleadoRepository vacacionEmpleadoJdbcRepository;
	
	@Test
    @Rollback(false)
    public void findOne(){
		//arrange
    	Long idEmpleado = 104L;
    	
    	//act
    	EmpleadoViewModel empleado = empleadoJdbcRepository.verEmpleado(idEmpleado);
    	
    	//assert
    	assertTrue(empleado != null);
        assertTrue(empleado.getPublicarTelefonoPersonal().intValue() == 0);
    	
	}
	
	/*@Test
    public void busquedaEmpleadoPlanilla(){
		//arrange
    	Long idEmpleado = 104L;
    	
    	//act
    	//List<EmpleadoPlanillaResultViewModel> empleado = vacacionEmpleadoJdbcRepository.obtenerBusquedaEmpeladoPlanilla();
    	
    	//assert
    	assertTrue(empleado != null);
        assertTrue(empleado.size() > 0);
    	
	}*/
	
	
}
