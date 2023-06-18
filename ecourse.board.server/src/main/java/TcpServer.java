import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class TcpServer
{
    private static ServerSocket sock;
    
    public static void main(String args[]) throws Exception
    {
        new Thread(() ->
        {
            try
            {
                System.out.println("TCP Server is ON");
                open(Shared.PORT_TCP);
            } catch (Exception e)
            {
                System.exit(0);
            }
        }).start();
        
        System.out.println("HTTP Server is ON");
        openHTTP(Shared.PORT_HTTP);
    }
    
    public static void open(byte port) throws Exception
    {
        Socket sockClient;
        
        try
        {
            sock = new ServerSocket(port);
        } catch (IOException ex)
        {
            System.out.println("Failed to open server socket");
            System.exit(1);
        }
        
        while (true)
        {
            sockClient = sock.accept();
            new Thread(new TcpServerThread(sockClient)).start();
        }
    }
    
    public static void openHTTP(byte port) throws Exception
    {
        Socket sockClient;
        
        try
        {
            sock = new ServerSocket(port);
        } catch (IOException ex)
        {
            System.out.println("Failed to open server socket");
            System.exit(1);
        }
        
        HttpServerAjax ajax = new HttpServerAjax();
        
        while (true)
        {
            sockClient = sock.accept();
            
            new Thread(new HttpRequest(sockClient, ajax)).start();
        }
        
    }
}