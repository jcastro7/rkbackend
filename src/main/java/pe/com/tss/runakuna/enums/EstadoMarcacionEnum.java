package pe.com.tss.runakuna.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by josediaz on 10/10/2016.
 */
public enum EstadoMarcacionEnum {
    SIN_MARCAR("SM","No Marca"),
    TARDANZA("TA","Tardanza"),
    PUNTUAL("PU","Puntual"),
    VACACIONES("VA","Vacaciones"),
    LICENCIA("LI","Licencia"),
    INASISTENCIA("IN","Inasistencia"),
    PERMISO_COMPENSACION("PC","Permiso por Compensacion")
    ;

    private String code;
    private String label;

    EstadoMarcacionEnum(String code, String label) {
        this.code = code;
        this.label = label;
    }
   
    public static List<EstadoMarcacionEnum> findAll(){
        List<EstadoMarcacionEnum> list= new ArrayList<EstadoMarcacionEnum>();

        for(EstadoMarcacionEnum status: EstadoMarcacionEnum.values()){
            list.add(status);
        }

        return list;
    }

    public static EstadoMarcacionEnum findByCode(String value){
        EstadoMarcacionEnum[] array= EstadoMarcacionEnum.values();
        EstadoMarcacionEnum result=null;
        for (int i=0;i<array.length;i++){
            if(array[i].getCode().equals(value)){
                result=array[i];
                break;
            }
        }
        return result;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
