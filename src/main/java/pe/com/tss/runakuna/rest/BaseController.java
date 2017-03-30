package pe.com.tss.runakuna.rest;


import pe.com.tss.runakuna.view.model.CurrentUser;

/**
 * Created by josediaz on 16/03/2017.
 */

public class BaseController {

    private CurrentUser currentUser;

    public CurrentUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }
}
