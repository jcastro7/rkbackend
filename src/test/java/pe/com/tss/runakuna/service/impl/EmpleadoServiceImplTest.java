package pe.com.tss.runakuna.service.impl;

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
import pe.com.tss.runakuna.service.EmpleadoService;
import pe.com.tss.runakuna.service.LoginService;
import pe.com.tss.runakuna.util.DateUtil;
import pe.com.tss.runakuna.view.model.CurrentUser;
import pe.com.tss.runakuna.view.model.EmpleadoDirectorioResultViewModel;
import pe.com.tss.runakuna.view.model.EmpleadoFilterViewModel;
import pe.com.tss.runakuna.view.model.EmpleadoQuickFilterViewModel;
import pe.com.tss.runakuna.view.model.EmpleadoResultViewModel;
import pe.com.tss.runakuna.view.model.EmpleadoViewModel;
import pe.com.tss.runakuna.view.model.NotificacionViewModel;

/**
 * Created by josediaz on 10/05/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes= {RunakunaBackendApplication.class, WebSecurityConfig.class})
@Transactional
public class EmpleadoServiceImplTest {

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private LoginService loginService;

    @Test
    public void deberiaBuscarPorAntiguedad(){
        //arrange
    	int desde = 3;
    	int hasta= 20;
        CurrentUser user = loginService.authenticate("dante.rebagliati");

   
        EmpleadoFilterViewModel filterViewModel = new EmpleadoFilterViewModel();
        filterViewModel.setIdEmpleado(user.getIdEmpleado());
        filterViewModel.setAntiguedadDesde(desde);
        filterViewModel.setAntiguedadHasta(hasta);

        //act
        List<EmpleadoResultViewModel> result = empleadoService.search(filterViewModel);

        //assert
        assertTrue(result.size()>0);
        assertTrue("Bien",result.get(0).getAntiguedad()>=desde && result.get(0).getAntiguedad()<=hasta );
    }
    
    @Test
    public void deberiaBuscarEmpleadoDirectorio(){
    	//arrange
    	EmpleadoQuickFilterViewModel quickFilterViewModel=new EmpleadoQuickFilterViewModel();
    	quickFilterViewModel.setValue("oscar");
    	//act
    	List<EmpleadoDirectorioResultViewModel> result= empleadoService.busquedaDirectorioEmpleado(quickFilterViewModel);
    	//assert
        assertTrue(result.size()>0);
    	
    }
    
    @Test
    public void findOne(){
    	Long idEmpleado = 104L;
    	
    	EmpleadoViewModel empleado = empleadoService.findOne(idEmpleado);
    	
    	assertTrue(empleado != null);
        assertTrue(empleado.getPublicarTelefonoPersonal().intValue() == 0);
    	
    }
    
    @Test
    @Rollback(true)
    public void createEmpleado(){
		//arrange
    	EmpleadoViewModel dto = new EmpleadoViewModel();
    	dto.setNombre("LUCAS");
    	dto.setAfp("HA");
    	dto.setApellidoMaterno("SOLANO");
    	dto.setApellidoPaterno("RIVEROS");
    	dto.setCodigo("5003");
    	dto.setContratoTrabajo("DE");
    	dto.setCts("AR");
    	dto.setDireccionDomicilio("Comas");
    	dto.setDiscapacitado(0);
    	dto.setEmailInterno("lucas.riveros@tss.com.pe");
    	dto.setEntidadBancariaSueldo("CO");
    	dto.setEps("RI");
    	dto.setEsPersonalDeConfianza(0);
    	dto.setEstadoCivil("S");
    	dto.setFechaIngreso(DateUtil.formatoFechaArchivoMarcacion("01/01/2017"));
    	dto.setFechaNacimiento(DateUtil.formatoFechaArchivoMarcacion("12/12/1995"));
    	dto.setGenero("M");
    	dto.setGrupoSangre("A");
    	dto.setIdCentroCosto(33L);
    	dto.setIdEmpresa(1L);
    	dto.setIdPaisDomicilio(1l);
    	dto.setIdPaisNacimiento(1l);
    	dto.setNumeroDocumento("44603056");
    	dto.setRegimenHorario("PG");
    	dto.setRegimenLaboral("TC");
    	dto.setTelefonoCelular("986470029");
    	dto.setTieneEps(1);
    	dto.setTipoDocumento("DI");
    	dto.setTipoTrabajador("EM");
    	dto.setEstado("A");
    	
    	dto.setPublicarEmailPersonal(0);
    	dto.setPublicarTelefonoAdicional(0);
    	dto.setPublicarTelefonoCasa(0);
    	dto.setPublicarTelefonoPersonal(0);
    	
    	dto.setCreador("TEST");
    	dto.setFechaCreacion(DateUtil.formatoFechaArchivoMarcacion("11/05/2017"));
    	
    	//act
    	NotificacionViewModel result = empleadoService.create(dto);
    	
    	//assert
    	assertTrue(result != null);
        assertTrue(result.getCodigo().intValue() == 1);
    	
	}
    
    @Test
    @Rollback(true)
    public void updateEmpleado(){
		//arrange
    	EmpleadoViewModel dto = new EmpleadoViewModel();
    	
    	dto.setIdEmpleado(163L);
    	dto.setNombre("LUCAS ANGEL");
    	dto.setAfp("HA");
    	dto.setApellidoMaterno("SOLANO");
    	dto.setApellidoPaterno("RIVEROS");
    	dto.setCodigo("5003");
    	dto.setContratoTrabajo("DE");
    	dto.setCts("AR");
    	dto.setDireccionDomicilio("Comas");
    	dto.setDiscapacitado(0);
    	dto.setEmailInterno("lucas.riveros@tss.com.pe");
    	dto.setEntidadBancariaSueldo("CO");
    	dto.setEps("RI");
    	dto.setEsPersonalDeConfianza(0);
    	dto.setEstadoCivil("S");
    	dto.setFechaIngreso(DateUtil.formatoFechaArchivoMarcacion("01/01/2017"));
    	dto.setFechaNacimiento(DateUtil.formatoFechaArchivoMarcacion("12/12/1995"));
    	dto.setGenero("M");
    	dto.setGrupoSangre("A");
    	dto.setIdCentroCosto(33L);
    	dto.setIdEmpresa(1L);
    	dto.setIdPaisDomicilio(1l);
    	dto.setIdPaisNacimiento(1l);
    	dto.setNumeroDocumento("44603056");
    	dto.setRegimenHorario("PG");
    	dto.setRegimenLaboral("TC");
    	dto.setTelefonoCelular("986470029");
    	dto.setTieneEps(1);
    	dto.setTipoDocumento("DI");
    	dto.setTipoTrabajador("EM");
    	dto.setEstado("A");
    	dto.setPublicarEmailPersonal(0);
    	dto.setPublicarTelefonoAdicional(0);
    	dto.setPublicarTelefonoCasa(0);
    	dto.setPublicarTelefonoPersonal(0);
    	
    	dto.setCreador("TEST");
    	dto.setFechaCreacion(DateUtil.formatoFechaArchivoMarcacion("11/05/2017"));
    	
    	dto.setActualizador("TEST");
    	dto.setFechaActualizacion(DateUtil.formatoFechaArchivoMarcacion("11/05/2017"));
    	
    	//ver el row version actual en la bd
    	dto.setRowVersion(0L);
    	
    	//act
    	NotificacionViewModel result = empleadoService.update(dto);
    	
    	//assert
    	assertTrue(result != null);
        assertTrue(result.getCodigo().intValue() == 1);
    	
	}
}
