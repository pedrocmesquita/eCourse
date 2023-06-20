import java.io.*;
import java.net.*;

public class TcpClient
{
    private static InetAddress serverIP;
    private static Socket sock;
    
    public static void main(String args1[])
    {
        String args[] = {"localhost"};
        if (args.length != 1)
        {
            System.out.println("Server IPv4/IPv6 address or DNS name is required as argument");
            System.exit(1);
        }
        
        try
        {
            serverIP = InetAddress.getByName(args[0]);
        } catch (UnknownHostException ex)
        {
            System.out.println("Invalid server specified: " + args[0]);
            System.exit(1);
        }
        
        try
        {
            sock = new Socket(serverIP, Shared.PORT_TCP);
        } catch (IOException ex)
        {
            System.out.println("Failed to establish TCP connection");
            System.exit(1);
        }
        
        //TcpShared tcp = new TcpShared(sock);
        //tcp.send(Shared.CURR_VERSION, MessageCodes.COMMTEST, "hello");
    }
}