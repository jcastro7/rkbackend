package pe.com.tss.runakuna.service;

/**
 * Created by josediaz on 16/02/2017.
 */
public interface LdapService {


    boolean authenticate(String usuario, String password);
}
