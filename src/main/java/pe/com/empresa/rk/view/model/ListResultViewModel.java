package pe.com.empresa.rk.view.model;

import java.util.List;

/**
 * Created by josediaz on 7/11/2016.
 */
public class ListResultViewModel {

    private List<EmpleadoFilterViewModel> resultList;

    public List<EmpleadoFilterViewModel> getResultList() {
        return resultList;
    }

    public void setResultList(List<EmpleadoFilterViewModel> resultList) {
        this.resultList = resultList;
    }
}
