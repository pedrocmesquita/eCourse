package eapli.ecourse.boardmanagement.application;

import eapli.ecourse.boardmanagement.domain.*;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

import java.util.List;
import java.util.Optional;

public class ViewBoardLogsController
{
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final BoardRepository repo = new BoardRepository();
    
    Optional<UserSession> session = authz.session();
    SystemUser user = session.get().authenticatedUser();
    
    public List<Log> viewLogs(String boardTitle)
    {
        Board board = repo.getBoardByTitle(boardTitle);
        
        //if board found
        Preconditions.ensure(board != null, "Board with title " + boardTitle + " not found.");
        
        //if user has read permissions
        Preconditions.ensure(board.userHasPermission(user, new AccessLevel(AccessLevelType.READ.toString())),
                "You must have read permissions on the board to view it's update history.");
        
        return board.getLogs();
    }
}
