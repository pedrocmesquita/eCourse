package eapli.ecourse.app.board.server.domain;

public final class Shared
{
    public static final byte PORT_TCP = (byte) 9999;
    public static final byte PORT_HTTP = (byte) 8000;
    
    public static final byte CURR_VERSION = (byte) 1;
    
    private static final String HTML_FOLDER = "ecourse.app.board.server/src/main/java/" +
            "eapli.ecourse.app.board.server/www";
    
    private Shared() {}
}