package pe.com.empresa.rk.view.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ocastillo on 21/12/2016.
 */
public class GeneraMsjeAlertaEmpleadoViewModel {

    
	private Long idAlerta;
    private Long idEmpleado;
    private Map<String,String> parametrosMsje= new HashMap<String, String>();
    

    public Long getIdAlerta() {
        return idAlerta;
    }

    public void setIdAlerta(Long idAlerta) {
        this.idAlerta = idAlerta;
    }

	public Long getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public Map<String, String> getParametrosMsje() {
		return parametrosMsje;
	}

	public void setParametrosMsje(Map<String, String> parametrosMsje) {
		this.parametrosMsje = parametrosMsje;
	}

	
   
}
