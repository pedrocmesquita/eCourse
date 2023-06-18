import eapli.framework.infrastructure.authz.application.AuthenticationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;




public class Auth
{
    private final AuthenticationService authService = AuthzRegistry.authenticationService();
    
    //expecting format "username\npassword"
    public byte authenticateUser(Message msg)
    {
        String data = new String(msg.getData());
        int split = data.indexOf('\n');
        
        //ACK if exists, ERR otherwise
        return (authService.authenticate(data.substring(0, split), data.substring(split)).isPresent()) ?
                MessageCodes.ACK : MessageCodes.ERR;
    }
    
    public boolean authenticateUser(String data)
    {
        int split = data.indexOf('\n');
        return authService.authenticate(data.substring(0, split), data.substring(split)).isPresent();
    }
    
    public Auth()
    {
    }
}

