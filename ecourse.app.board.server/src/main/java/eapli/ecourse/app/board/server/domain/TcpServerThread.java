package eapli.ecourse.app.board.server.domain;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

class TcpServerThread implements Runnable
{
    private Socket sock;
    private Auth auth = new Auth();
    
    public TcpServerThread(Socket sockClient)
    {
        sock = sockClient;
    }
    
    public void run()
    {
        InetAddress clientIP;
        clientIP = sock.getInetAddress();
        System.out.println("New client connection from " + clientIP.getHostAddress() +
                ", port number " + sock.getPort());
        
        try
        {
            TcpClient tcpCli = new TcpClient(sock);
            Message msg;
    
            do
            {
                msg = tcpCli.recieve();
                
                switch (msg.getCode())
                {
                    case MessageCodes.COMMTEST:
                        tcpCli.send(Shared.CURR_VERSION, MessageCodes.ACK, "");
                        break;
    
                    //case MessageCodes.DISCONN:    //handled by exiting while
                    //    break;
                    //
                    //case MessageCodes.ACK:
                    //    break;
                    //
                    //case MessageCodes.ERR:
                    //    break;
    
                    case MessageCodes.AUTH:
                        try
                        {
                            tcpCli.send(Shared.CURR_VERSION, auth.authenticateUser(msg), "");
                        } catch (IllegalArgumentException e)
                        {
                            tcpCli.send(Shared.CURR_VERSION, MessageCodes.ERR,
                                    e.getMessage());
                        }
                        break;
    
                    //default:
                    //    System.out.println("Invalid message code recieved.");
                    //    break;
                }
            } while (msg.getCode() != MessageCodes.DISCONN);
            
            System.out.println("Client " + clientIP.getHostAddress() +
                    ", port number: " + sock.getPort() + " disconnected");
            sock.close();
        } catch (IOException ex)
        {
            System.out.println("IOException");
        }
    }
}