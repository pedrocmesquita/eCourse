package eapli.ecourse.boardmanagement.application;

import eapli.ecourse.boardmanagement.domain.*;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

import java.util.Optional;

public class ShareBoardController
{
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final BoardRepository repo = PersistenceContext.repositories().boards();
    
    Optional<UserSession> session = authz.session();
    SystemUser user = session.get().authenticatedUser();
    
    /**
     * Attempt to share a board with another user
     * @param boardTitle
     * @param username
     * @param write boolean to determine if user should be able to create new posts
     */
    public void shareBoard(BoardTitle boardTitle, String username, boolean write)
    {
        Board board = repo.getBoardByTitle(boardTitle);
        
        //if board found
        Preconditions.ensure(board != null, "Board with title " + boardTitle + " not found.");
        
        //if user is board owner
        Preconditions.ensure(board.boardOwner().equals(user),
                "You must own the board to share it with another user.");
        
        //when sharing a board, assume READ permissions by default
        AccessLevel al = write ? new AccessLevel(AccessLevelType.WRITE.toString()) :
                new AccessLevel(AccessLevelType.READ.toString());
        
        board.addPermission(new BoardPermission(user, al));
    }
}
