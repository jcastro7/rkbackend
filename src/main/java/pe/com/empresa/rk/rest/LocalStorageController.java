package pe.com.empresa.rk.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.empresa.rk.view.model.LocalStorageViewModel;
import pe.com.empresa.rk.service.LocalStorageService;
import pe.com.empresa.rk.view.model.AutorizacionFilterViewModel;

@CrossOrigin
@RestController
@RequestMapping(value = "/localStorage")
public class LocalStorageController {
	
	@Autowired
	LocalStorageService localStorageService;
	
	private final Logger LOG = LoggerFactory.getLogger(LocalStorageController.class);
	
	@RequestMapping(value = "/obtenerLocalStorage", method = RequestMethod.POST)
	public @ResponseBody
    LocalStorageViewModel obtenerLocalStorage(@RequestBody AutorizacionFilterViewModel autorizacion){
		LocalStorageViewModel result = new LocalStorageViewModel();
		result = localStorageService.findAllLocalStorageValues();
		if(result == null)
			result = new LocalStorageViewModel();
		LOG.info("Msg obtenerAlerta: " + result);
		return result;
	}

}
