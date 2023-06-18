public final class Shared
{
    public static final byte PORT_TCP = (byte) 9999;
    public static final byte PORT_HTTP = (byte) 8000;
    
    public static final byte CURR_VERSION = (byte) 1;
    
    public static final String HTML_FOLDER = "ecourse.app.board.server/src/main/java/" +
            "eapli.ecourse.app.board.server/www";
    
    //TODO: create postit, change postit, delete postit
    public static final String FILE_AUTH =          "/auth.html";
    public static final String FILE_BOARD_VIEW =    "/board.html";
    public static final String FILE_BOARD_LIST =    "/board-list.html";
    public static final String FILE_BOARD_CREATE =  "/board-create.html";
    public static final String FILE_BOARD_SHARE =   "/board-share.html";
    public static final String FILE_BOARD_LOGS =    "/logs.html";
    public static final String FILE_HOME =          FILE_BOARD_LIST;
    
    //only leave a trailing / on non board specific ones
    public static final String URI_AUTH =          "/auth";
    public static final String URI_BOARD_VIEW =    "/board-view/";
    public static final String URI_BOARD_LIST =    "/board-list";
    public static final String URI_BOARD_CREATE =  "/board-create";
    public static final String URI_BOARD_SHARE =   "/board-share/";
    public static final String URI_BOARD_LOGS =    "/board-history/";
    
    public static final String CONTENT_TEXT =       "text/html";
    
    public static final String RESPONSE_STATUS_OK =             "200 Ok";
    
    public static final String RESPONSE_STATUS_UNAUTHORIZED =   "401 Unauthorized";
    public static final String RESPONSE_CONTENT_UNAUTHORIZED =  "<html><body><h1>401 Access Unauthorized</h1></body></html>";
    
    public static final String RESPONSE_STATUS_FORBIDDEN =      "403 Forbidden";
    public static final String RESPONSE_CONTENT_FORBIDDEN =     "<html><body><h1>403 Access Forbidden</h1></body></html>";
    
    public static final String RESPONSE_STATUS_NOTFOUND =       "404 Not Found";
    public static final String RESPONSE_CONTENT_NOTFOUND =      "<html><body><h1>404 File not found</h1></body></html>";
    
    public static final String RESPONSE_STATUS_NOMETHOD =       "405 Invalid Method";
    public static final String RESPONSE_CONTENT_NOMETHOD =      "<html><body><h1>ERROR: 405 Method Not Allowed</h1></body></html>";
    
    private Shared() {}
}