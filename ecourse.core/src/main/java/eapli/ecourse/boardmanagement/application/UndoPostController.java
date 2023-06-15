package eapli.ecourse.boardmanagement.application;

import eapli.ecourse.boardmanagement.domain.AccessLevel;
import eapli.ecourse.boardmanagement.domain.Board;
import eapli.ecourse.boardmanagement.domain.Content;
import eapli.ecourse.boardmanagement.domain.PostIt;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.CheckIf;
import eapli.framework.validations.Preconditions;

import java.util.Optional;

public class UndoPostController
{
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final BoardRepository repo = new BoardRepository();
    
    Optional<UserSession> session = authz.session();
    SystemUser user = session.get().authenticatedUser();
    
    /**
     * Attempts to restore the backup of a post
     * @param boardTitle
     * @param row
     * @param column
     */
    public void rollbackPost(String boardTitle, int row, int column)
    {
        Board board = repo.getBoardByTitle(boardTitle);
        
        //if board found
        Preconditions.ensure(board != null, "Board with title " + boardTitle + " not found.");
        
        //if user has write permission on board
        Preconditions.ensure(board.userHasPermission(user, new AccessLevel("WRITE")),
                "You do not have write permissions on this board.");
        
        //if user is post maker
        Preconditions.ensure(board.getCellByRowColumn(row, column).getPost().getOwner() == user,
                "You can only change a post you made.");
    
        Preconditions.ensure(board.getCellByRowColumn(row, column).getPost().rollbackPost(),
                "Failure undoing changes to post.");
    }
}