package pe.com.empresa.rk.service.impl;

import pe.com.empresa.rk.view.model.ValidationErrorViewModel;

import java.util.List;

/**
 * Created by josediaz on 28/10/2016.
 */
public interface ValidationErrors {
    int size();
    void addError(ValidationErrorViewModel valErr);
    List<ValidationErrorViewModel> getErrors();

}
