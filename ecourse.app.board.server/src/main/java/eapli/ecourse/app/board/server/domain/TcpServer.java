package eapli.ecourse.app.board.server.domain;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class TcpServer
{
    private static ServerSocket sock;
    
    public static void main(String args[]) throws Exception
    {
        Socket cliSock;
        open(Shared.PORT_TCP);
        open(Shared.PORT_HTTP);
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
}