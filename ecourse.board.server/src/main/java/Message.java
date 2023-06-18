public class Message
{
    private byte version;
    private byte code;
    private byte d_length_1;
    private byte d_length_2;
    private byte data[];
    
    public Message(byte version, byte code, byte d_length_1, byte d_length_2, byte data[])
    {
        this.version = version;
        this.code = code;
        this.d_length_1 = d_length_1;
        this.d_length_2 = d_length_2;
        this.data = data;
    }
    
    public byte getCode()
    {
        return code;
    }
    
    public byte[] getData()
    {
        return data;
    }
}
