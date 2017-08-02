package pe.com.tss.runakuna.service.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pe.com.tss.runakuna.RunakunaBackendApplication;
import pe.com.tss.runakuna.security.config.WebSecurityConfig;
import pe.com.tss.runakuna.service.ContratoService;
import pe.com.tss.runakuna.view.model.ContratoFilterViewModel;
import pe.com.tss.runakuna.view.model.ContratoResultViewModel;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes= {RunakunaBackendApplication.class, WebSecurityConfig.class})
public class ContratoServiceImplTest {
	
	@Autowired
	ContratoService contratoService;

	@Test
	public void searchContract() throws Exception {
		ContratoFilterViewModel filterViewModel = new ContratoFilterViewModel();
		
		List<ContratoResultViewModel> result = contratoService.search(filterViewModel);
		assertTrue(result.size()>0);
	}

}
