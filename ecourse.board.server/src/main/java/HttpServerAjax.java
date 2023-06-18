

import eapli.ecourse.boardmanagement.application.ViewBoardInServerController;
import eapli.ecourse.boardmanagement.newdomain.Board;

import java.util.ArrayList;
import java.util.List;


public class HttpServerAjax
{
    
    public HttpServerAjax() {}
    
    public String ViewBoard(String title)
    {
        String boardinfo;
        ViewBoardInServerController ctrl = new ViewBoardInServerController();
        boardinfo = ctrl.getBoardtoStringByTitle(title);
        return boardinfo;
    }
    
    public String ViewPosts(String title)
    {
        String boardinfo;
        ViewBoardInServerController ctrl = new ViewBoardInServerController();
        boardinfo = ctrl.getPostsToStringByTitle(title);
        return boardinfo;
    }
    
    private List<Object> locks = new ArrayList<>();
    
    /**
     * Unique paramater to stop multiple simultaneous accesses to same resource in board
     * @param title title of board
     * @param row row number of post
     * @param column column number of post
     * @return
     */
    public Object lock(String title, int row, int column)
    {
        Object n = (row + title + column);
        
        for (Object o : locks)
        {
            if (o.equals(n))
            {
                return o;
            }
        }
        
        return n;
    }
}