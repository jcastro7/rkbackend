package pe.com.empresa.rk.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pe.com.empresa.rk.service.LdapService;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NameNotFoundException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.SizeLimitExceededException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import java.util.Hashtable;

/**
 * Created by josediaz on 16/02/2017.
 */
@Service
public class LdapServiceImpl implements LdapService {

    final Logger log = LoggerFactory.getLogger(getClass());

    @Value("${spring.security.ldap.url}")
    private String url;

    @Value("${spring.security.ldap.authentication}")
    private String authentication;

    @Value("${spring.security.ldap.admin}")
    private String usuarioBase;

    @Value("${spring.security.ldap.password}")
    private String passwordUsuarioBase;

    @Value("${spring.security.ldap.baseDN}")
    private String baseDN;

    @Override
    public boolean authenticate(String usuario,String password){

        Hashtable<String,String> env=new Hashtable<String,String>(11);
        env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL,url);
        env.put(Context.SECURITY_AUTHENTICATION,authentication);
        env.put(Context.SECURITY_PRINCIPAL,usuarioBase);
        env.put(Context.SECURITY_CREDENTIALS,passwordUsuarioBase);

        DirContext ctx=null;
        try{
            ctx=new InitialDirContext(env);
        }
        catch(AuthenticationException e){
            log.warn("Usuario o Contrase\u00f1a de Administrador Incorrecta.");
            return false;
        }
        catch(NamingException e){
            log.error("No se pudo conectar con el servidor",e);
            return false;
        }
        catch(Exception e){
            log.error("Excepcion",e);
            return false;
        }

        NamingEnumeration<SearchResult> results=null;

        try{
            SearchControls controls=new SearchControls();
            controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            controls.setCountLimit(1);
            controls.setTimeLimit(5000);

            String searchString="(&(objectCategory=user)(sAMAccountName=" + usuario + "))";

            results=ctx.search(baseDN,searchString,controls);

            if(results.hasMore()){

                SearchResult result=results.next();
                Attributes attrs=result.getAttributes();
                Attribute dnAttr=attrs.get("distinguishedName");
                String dn=(String) dnAttr.get();

                // Valida el Password y Usuario encontrado

                env.put(Context.SECURITY_PRINCIPAL,dn);
                env.put(Context.SECURITY_CREDENTIALS,password);

                new InitialDirContext(env);
                return true;
            }

        }
        catch(AuthenticationException e){
            log.error("El password del usuario es incorrecto",e);
            return false;
        }
        catch(NameNotFoundException e){
            log.error("No se encontro el baseDN",e);
            return false;
        }
        catch(SizeLimitExceededException e){
            log.error("La query obtuvo demasiados resultados",e);
        }
        catch(NamingException e){
            log.error("Error de nombramiento",e);
        }
        catch(Exception e){
            log.error("Excepcion",e);
        }
        return false;
    }
}
