package pe.com.tss.runakuna.service.impl;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pe.com.tss.runakuna.RunakunaBackendApplication;
import pe.com.tss.runakuna.security.config.WebSecurityConfig;
import pe.com.tss.runakuna.service.DashboardService;
import pe.com.tss.runakuna.view.model.IndicadorJefeResultViewModel;
import pe.com.tss.runakuna.view.model.MarcacionDashboardEmpleadoFilterViewModel;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes= {RunakunaBackendApplication.class, WebSecurityConfig.class})
public class DashboardServiceImplTest {
	
	@Autowired
	DashboardService dashboardService;
	
	@Test
	public void mostrarIndicarParaDashboardJefe(){
		MarcacionDashboardEmpleadoFilterViewModel filter = new MarcacionDashboardEmpleadoFilterViewModel();
		filter.setIdEmpleado(11L);
		
		List<IndicadorJefeResultViewModel> result ;
        result = dashboardService.buscarIndicadorJefeDashBoard(filter);
        
        assertTrue(result.size()>0);
        
	}

}
