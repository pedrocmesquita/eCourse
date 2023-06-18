package eapli.ecourse.boardmanagement.application;

import eapli.ecourse.boardmanagement.newdomain.Board;
import eapli.ecourse.boardmanagement.newdomain.BoardCell;
import eapli.ecourse.boardmanagement.newdomain.BoardTitle;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.Optional;
import java.util.Set;

public class ViewBoardInServerController {
    private final AuthorizationService authz;
    private final BoardRepository repo = PersistenceContext.repositories().boards();
    private final BoardService boardSvc = new BoardService(PersistenceContext.repositories().boards());
    private final PostItService postitSvc = new PostItService(PersistenceContext.repositories().postIts());
    public ViewBoardInServerController() {
        authz = AuthzRegistry.authorizationService();
        Optional<UserSession> session = authz.session();
        SystemUser user = session.get().authenticatedUser();
    }
    public String getBoardtoStringByTitle(String title)
    {
        //String output = "";
        BoardTitle title1 = new BoardTitle(title);
        Board board = boardSvc.getBoardByTitle(title1);
        
        return board.toStringStatic();
    }
    
    public String getPostsToStringByTitle(String title)
    {
        StringBuilder output = new StringBuilder();
        Board board = boardSvc.getBoardByTitle(new BoardTitle(title));
        Set<BoardCell> cells = board.getBoardCells();
        
        for (BoardCell i : cells)
        {
            output.append(postitSvc.getPostItByBoardCell(i).toString());
        }
        
        return output.toString();
    }
    public Board getBoardByTitle(String title){
        BoardTitle title1 = new BoardTitle(title);
        Board board = boardSvc.getBoardByTitle(title1);
        return board;
    }



}
