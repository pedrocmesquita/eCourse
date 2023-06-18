import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HttpRequest extends Thread
{
    Socket sock;
    DataInputStream inS;
    DataOutputStream outS;
    HttpServerAjax httpServerAjax;
    
    public HttpRequest(Socket httpCliSock, HttpServerAjax httpServerAjax)
    {
        this.sock = httpCliSock;
        this.httpServerAjax = httpServerAjax;
    }
    
    @Override
    public void run()
    {
        try
        {
            outS = new DataOutputStream(sock.getOutputStream());
            inS = new DataInputStream(sock.getInputStream());
        } catch (IOException ex)
        {
        }
        
        try
        {
            HttpMessage request = new HttpMessage(inS);
            HttpMessage response = new HttpMessage();
            
            HttpServerAjax ajax = new HttpServerAjax();
            String uri = request.getURI();
            
            if (request.getMethod().equals("GET"))
            {
                //view a specific board, static elements
                if (uri.startsWith(Shared.URI_BOARD_VIEW))
                {
                    String temp = uri.replace(Shared.URI_BOARD_VIEW, "");
                    response.setContentFromString(ajax.ViewBoard(temp), Shared.CONTENT_TEXT);
                    response.setResponseStatus(Shared.RESPONSE_STATUS_OK);
                }
                //view a specific board, posts
                if (uri.startsWith(Shared.URI_BOARD_POSTS))
                {
                    String temp = uri.replace(Shared.URI_BOARD_POSTS, "");
                    response.setContentFromString(ajax.ViewPosts(temp), Shared.CONTENT_TEXT);
                    response.setResponseStatus(Shared.RESPONSE_STATUS_OK);
                }
                
            }
            
            if (request.getMethod().equals("POST"))
            {
                //user logging in
                if (uri.equals(Shared.URI_AUTH))
                {
                    Auth auth = new Auth();
                    if (! auth.authenticateUser(request.getContentAsString()))
                    {
                        response.setContentFromString(Shared.RESPONSE_CONTENT_UNAUTHORIZED, Shared.CONTENT_TEXT);
                        response.setResponseStatus(Shared.RESPONSE_STATUS_UNAUTHORIZED);
                    }
                    
                    else
                    {
                        response.setContentFromFile(Shared.HTML_FOLDER + Shared.FILE_HOME);
                        response.setResponseStatus(Shared.RESPONSE_STATUS_OK);
                    }
                }
            }
            
            if (request.getMethod().equals("PUT"))
            {
            
            }
            
            if (request.getMethod().equals("DELETE"))
            {
            
            }
    
            response.send(outS);
        } catch (Exception ex)
        {
    
            System.out.println("Error reading request.");
        }
        
        try
        {
            sock.close();
        } catch (Exception ex)
        {
            System.out.println("IOException error.");
        }
    }
}