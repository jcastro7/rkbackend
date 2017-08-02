package pe.com.tss.runakuna.view.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by josediaz on 9/11/2016.
 */
public class ListEmpleadosNuevosYAntiguosViewModel {

    private List<EmpleadoViewModel> empleadosNuevos;
    private List<EmpleadoViewModel> empleadosAntiguos;

    public ListEmpleadosNuevosYAntiguosViewModel(){
        this.empleadosNuevos = new ArrayList<>();
        this.empleadosAntiguos = new ArrayList<>();
    }

    public List<EmpleadoViewModel> getEmpleadosNuevos() {
        return empleadosNuevos;
    }

    public void setEmpleadosNuevos(List<EmpleadoViewModel> empleadosNuevos) {
        this.empleadosNuevos = empleadosNuevos;
    }

    public List<EmpleadoViewModel> getEmpleadosAntiguos() {
        return empleadosAntiguos;
    }

    public void setEmpleadosAntiguos(List<EmpleadoViewModel> empleadosAntiguos) {
        this.empleadosAntiguos = empleadosAntiguos;
    }
}
