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

public class ChangePostController
{
    /*
    public void changePostContent(BoardTitle boardTitle, int row, int column, Content newContent)
    {
        Board board = repo.getBoardByTitle(boardTitle);
        
        //if board not found
        Preconditions.ensure(board != null, "Board with title " + boardTitle + " not found.");
    
        //if user doesn't have write permission on board
        Preconditions.ensure(board.userHasPermission(user, new AccessLevel(AccessLevelType.WRITE.toString())),
                "You do not have write permissions on this board.");
        
        //if user is post maker
        Preconditions.ensure(board.getCellByRowColumn(row, column).getPost().getOwner() == user,
                "You can only change a post you made.");
        
        board.getCellByRowColumn(row, column).getPost().setContent(newContent);
    }
    
    public void changePostPosition(BoardTitle boardTitle, int row, int column, int rownew, int columnnew)
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
        
        //cell and post of location of relocation
        BoardEntry cellnew = board.getCellByRowColumn(rownew, columnnew);
        PostIt postnew = cellnew.getPost();
    
        //if new cell location is empty
        Preconditions.ensure(postnew == null, "You can only relocate a post to an empty cell.");
    
        //cell and post of current location
        BoardEntry cell = board.getCellByRowColumn(row, column);
        PostIt post = cell.getPost();
        
        //cell.setPost(null);
        //post.setCell(cellnew);
        //cellnew.setPost(post);
    
        Preconditions.ensure(post.relocate(cellnew), "Failure relocating the post.");
    }
    */
}
