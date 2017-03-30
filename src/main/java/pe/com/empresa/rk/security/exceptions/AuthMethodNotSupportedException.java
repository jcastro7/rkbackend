package pe.com.empresa.rk.security.exceptions;

import org.springframework.security.authentication.AuthenticationServiceException;

/**
 * Created by josediaz on 23/11/2016.
 */
public class AuthMethodNotSupportedException extends AuthenticationServiceException {
    private static final long serialVersionUID = 3705043083010304496L;

    public AuthMethodNotSupportedException(String msg) {
        super(msg);
    }
}
