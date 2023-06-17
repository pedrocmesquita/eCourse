package eapli.ecourse.app.board.server.domain;

public class Controller
{
    private Auth auth;
    
    public byte authFromMsg(Message msg)
    {
        String data = new String(msg.getData());
        int split = data.indexOf('\n');  //expecting format "username\npassword"
        
        return auth.authenticateUser(data.substring(0, split), data.substring(split));
    }
}