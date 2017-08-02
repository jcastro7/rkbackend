package pe.com.tss.runakuna.service.impl;

import pe.com.tss.runakuna.view.model.ValidationErrorViewModel;

import java.util.List;

/**
 * Created by josediaz on 28/10/2016.
 */
public interface ValidationErrors {
    int size();
    void addError(ValidationErrorViewModel valErr);
    List<ValidationErrorViewModel> getErrors();

}
