package pe.com.tss.runakuna.enums;

import java.util.ArrayList;
import java.util.List;

public enum SeverityStatusEnum {
    WARN("warn","Warning"),
    ERROR("error","Error"),
    INFO("info","Information"),
    SUCCESS("success","Success")
    ;

    private String code;
    private String label;

    SeverityStatusEnum(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public static List<SeverityStatusEnum> findAll(){
        List<SeverityStatusEnum> list= new ArrayList<SeverityStatusEnum>();

        for(SeverityStatusEnum status: SeverityStatusEnum.values()){
            list.add(status);
        }

        return list;
    }

    public static SeverityStatusEnum findByCode(String value){
        SeverityStatusEnum[] array= SeverityStatusEnum.values();
        SeverityStatusEnum result=null;
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
