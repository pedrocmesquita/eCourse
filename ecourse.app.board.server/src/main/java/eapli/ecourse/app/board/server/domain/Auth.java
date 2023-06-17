package eapli.ecourse.app.board.server.domain;

import eapli.framework.infrastructure.authz.application.AuthenticationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class Auth
{
    private final AuthenticationService authService = AuthzRegistry.authenticationService();
    
    //expecting format "username\npassword"
    public byte authenticateUser(String username, String password)
    {
        if(authService.authenticate(username, password).isPresent())
        {
            return MessageCodes.ACK;
        }
        
        //invalid username/password
        return MessageCodes.ERR;
    }
    
    public boolean authenticateUser(String data)
    {
        int split = data.indexOf('\n');
        return authService.authenticate(data.substring(0, split), data.substring(split)).isPresent();
    }
}

