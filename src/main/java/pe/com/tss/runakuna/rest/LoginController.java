package pe.com.tss.runakuna.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.com.tss.runakuna.service.LoginService;
import pe.com.tss.runakuna.view.model.AssignedRole;
import pe.com.tss.runakuna.view.model.ChangeRolViewModel;
import pe.com.tss.runakuna.view.model.CurrentUser;
import pe.com.tss.runakuna.view.model.ModuloViewModel;

@RestController
@RequestMapping(value = "/login")
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value="/authenticate", method= RequestMethod.GET, produces="application/json")
    public CurrentUser authenticate(@RequestParam("cuentaUsuario") String cuentaUsuario) {
        return loginService.authenticate(cuentaUsuario);
    }
	
	@RequestMapping(value="/validateModuleActionName", method= RequestMethod.GET, produces="application/json")
    public Boolean validateModuleActionName(@RequestParam("idUsuario") Long idUsuario, @RequestParam("moduleCode") String moduleCode,
    		@RequestParam("actionName") String actionName) {
        return loginService.validateModuleActionName(idUsuario,moduleCode,actionName);
    }
	
	@RequestMapping(value="/updateDefaultRole", method= RequestMethod.GET, produces="application/json")
    public ChangeRolViewModel updateDefaultRole(@RequestParam("cuentaUsuario") String cuentaUsuario, @RequestParam("roleCode") String rolCode) {
        
		return loginService.updateDefaultRole(cuentaUsuario,rolCode);
		
    }

}
