package eapli.ecourse.app.board.server.domain;

import java.io.*;
import java.net.*;

public class TcpClient
{
    private static InetAddress serverIP;
    private static Socket sock;
    
    private static final int fullByte = 256;
    
    private static DataOutputStream sOut;
    private static DataInputStream sIn;
    
    /*
    public TcpClient(Socket sock)
    {
        this.sock = sock;
        
        try
        {
            sOut = new DataOutputStream(sock.getOutputStream());
            sIn = new DataInputStream(sock.getInputStream());
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    */
    
    public static void main(String args[]) throws Exception
    {
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
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream sOut = new DataOutputStream(sock.getOutputStream());
        DataInputStream sIn = new DataInputStream(sock.getInputStream());
    }
    
    public void send(byte version, byte code, String text)
    {
        byte data[] = text.getBytes();
        int size = data.length;
        
        int d_length_1 = size % fullByte;
        int d_length_2 = size / fullByte;
        
        try
        {
            sOut.writeByte(version);
            sOut.writeByte(code);
            sOut.writeByte(d_length_1);
            sOut.writeByte(d_length_2);
            sOut.write(data, 0, size);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    
    public Message recieve()
    {
        try
        {
            byte version = (byte) sIn.readUnsignedByte();
            byte code = (byte) sIn.readUnsignedByte();
            byte d_length_1 = (byte) sIn.readUnsignedByte();
            byte d_length_2 = (byte) sIn.readUnsignedByte();
            
            byte data[] = new byte[d_length_1 + (d_length_2 * fullByte)];
            
            if (data.length > 0)
            {
                sIn.readFully(data);
            }
            
            return new Message(version, code, d_length_1, d_length_2, data);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}