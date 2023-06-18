import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Request extends Thread
{
    //String baseFolder;
    Socket sock;
    DataInputStream inS;
    DataOutputStream outS;
    
    public Request(Socket s)
    {
        sock = s;
    }
    
    public void run()
    {
        try
        {
            outS = new DataOutputStream(sock.getOutputStream());
            inS = new DataInputStream(sock.getInputStream());
        } catch (IOException ex)
        {
            System.out.println("Thread error on data streams creation");
        }
        try
        {
            HttpMessage request = new HttpMessage(inS);
            HttpMessage response = new HttpMessage();
            // System.out.println(request.getURI());
            
            HttpServerAjax ajax = new HttpServerAjax();
            String uri = request.getURI();
            
            //client wants to GET something
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
                //view a list of my boards
                if (uri.equals(Shared.URI_BOARD_LIST))
                {
                    if (response.setContentFromFile(Shared.FILE_BOARD_LIST))
                    {
                        response.setResponseStatus(Shared.RESPONSE_STATUS_OK);
                    }
        
                    else
                    {
                        response.setContentFromString(Shared.RESPONSE_CONTENT_NOTFOUND, Shared.CONTENT_TEXT);
                        response.setResponseStatus(Shared.RESPONSE_STATUS_NOTFOUND);
                    }
                }
                //create a board
                if (uri.equals(Shared.URI_BOARD_CREATE))
                {
                    if (response.setContentFromFile(Shared.FILE_BOARD_CREATE))
                    {
                        response.setResponseStatus(Shared.RESPONSE_STATUS_OK);
                    }
        
                    else
                    {
                        response.setContentFromString(Shared.RESPONSE_CONTENT_NOTFOUND, Shared.CONTENT_TEXT);
                        response.setResponseStatus(Shared.RESPONSE_STATUS_NOTFOUND);
                    }
                }
                //view a specific board's history
                if (uri.startsWith(Shared.URI_BOARD_LOGS))
                {
                    if (response.setContentFromFile(Shared.FILE_BOARD_LOGS))
                    {
                        response.setResponseStatus(Shared.RESPONSE_STATUS_OK);
                    }
        
                    else
                    {
                        response.setContentFromString(Shared.RESPONSE_CONTENT_NOTFOUND, Shared.CONTENT_TEXT);
                        response.setResponseStatus(Shared.RESPONSE_STATUS_NOTFOUND);
                    }
                }
            }
            
            else if (request.getMethod().equals("POST"))
            {
                //user logging in
                if (uri.equals(Shared.URI_AUTH))
                {
                    Auth auth = new Auth();
                    if (!auth.authenticateUser(request.getContentAsString()))
                    {
                        response.setContentFromString(Shared.RESPONSE_CONTENT_UNAUTHORIZED, Shared.CONTENT_TEXT);
                        response.setResponseStatus(Shared.RESPONSE_STATUS_UNAUTHORIZED);
                    }
                    
                    else
                    {
                        //response.setContentFromFile(Shared.FILE_HOME);
                        response.setResponseStatus(Shared.RESPONSE_STATUS_OK);
                    }
                }
            }
            
            else
            {
                response.setContentFromString(Shared.RESPONSE_CONTENT_NOMETHOD, Shared.CONTENT_TEXT);
                response.setResponseStatus(Shared.RESPONSE_STATUS_NOMETHOD);
            }
            
            response.send(outS);
        } catch (IOException ex)
        {
            System.out.println("Thread error when reading request");
        }
        try
        {
            sock.close();
        } catch (IOException ex)
        {
            System.out.println("CLOSE IOException");
        }
    }
}
