package pe.com.empresa.rk.view.model;

/**
 * Created by josediaz on 28/10/2016.
 */
public class ValidationErrorViewModel {
    private String property;
    private String message;
    private String code;
    private Object[] arguments;


    public ValidationErrorViewModel(String property, String message) {
        this.property = property;
        this.message = message;
    }

    public ValidationErrorViewModel(String code, Object[] arguments) {
        this.code = code;
        this.arguments = arguments;
    }

    public String getProperty() {
        return property;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public void setArguments(Object[] arguments) {
        this.arguments = arguments;
    }
}
