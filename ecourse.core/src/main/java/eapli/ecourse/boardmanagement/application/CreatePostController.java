package eapli.ecourse.boardmanagement.application;

import eapli.ecourse.boardmanagement.domain.*;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

import java.util.List;
import java.util.Optional;

public class CreatePostController
{
    /*
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final BoardRepository repo = PersistenceContext.repositories().boards();

    public void attemptCreatePost(BoardTitle boardTitle, int row, int column, String text, String image)
    {
        Optional<UserSession> session = authz.session();
        SystemUser user = session.get().authenticatedUser();
        
        Board board = repo.getBoardByTitle(boardTitle);
        
        //if board not found
        Preconditions.ensure(board != null, "Board with title " + boardTitle + " not found.");
        
        //if user doesn't have write permission on board
        Preconditions.ensure(board.userHasPermission(user, new AccessLevel(AccessLevelType.WRITE.toString())),
                "User doesn't have write permissions on this board.");
        
        Content cont = new Content();
        
        //create content with text, if it exists
        if (text != null)
        {
            cont.createContentText(text);
        }
        
        //otherwise, create content with image
        else if (image != null)
        {
            cont.createContentText(image);
        }
        
        new PostIt(board, board.getCellByRowColumn(row, column), user, cont);
    }
    */
}
