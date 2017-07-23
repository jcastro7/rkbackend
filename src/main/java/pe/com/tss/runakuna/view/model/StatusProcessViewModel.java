package pe.com.tss.runakuna.view.model;

/**
 * Created by josediaz on 7/11/2016.
 */
public class StatusProcessViewModel {

    private String status;
    private String message;

    public StatusProcessViewModel(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public StatusProcessViewModel() {
        //Mapper
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
