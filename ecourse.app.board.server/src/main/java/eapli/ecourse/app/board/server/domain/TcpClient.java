package eapli.ecourse.app.board.server.domain;

import java.io.*;
import java.net.*;

public class TcpClient
{
    private InetAddress serverIP;
    private Socket sock;
    
    private static final int fullByte = 256;
    
    private DataOutputStream sOut;
    private DataInputStream sIn;
    
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}