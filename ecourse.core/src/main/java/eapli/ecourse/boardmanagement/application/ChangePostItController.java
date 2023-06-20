package eapli.ecourse.boardmanagement.application;

import eapli.ecourse.boardmanagement.newdomain.*;
import eapli.ecourse.boardmanagement.repositories.BoardCellRepository;
import eapli.ecourse.boardmanagement.repositories.BoardPermissionRepository;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.boardmanagement.repositories.PostItRepository;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ChangePostItController
{

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final BoardRepository repo = PersistenceContext.repositories().boards();

    private final BoardPermissionRepository repo1 = PersistenceContext.repositories().boardPermissions();

    private final PostItRepository repo2 = PersistenceContext.repositories().postIts();

    private final BoardCellRepository cellRepo = PersistenceContext.repositories().cells();


    private final PostItService postSvc = new PostItService(PersistenceContext.repositories().postIts());
    public PostIt changePostContent(SystemUser user, BoardTitle boardTitle, int row, int column, Content newContent, PostIt post)
    {

        BoardCell cell = cellRepo.getBoardCellByRowAndCol(new BoardRow(Integer.toString(row),"50"), new BoardCol(Integer.toString(column), "50"),post);
        //cell.getPost().setBackup(cell.getPost());
        Preconditions.ensure(cell.getPost().setContent(newContent),"Failure in changing post content.");
        cellRepo.save(cell);
        repo2.save(cell.getPost());
        return cell.getPost();
    }
    
    public void changePostPosition(SystemUser user,BoardTitle boardTitle, int row, int column, int rownew, int columnnew, PostIt post1)
    {
        Board board = repo.getBoardByTitle(boardTitle);
        
        //cell and post of location of relocation
        BoardCell cellnew = board.getCellByRowColumn(rownew, columnnew);
        PostIt postnew = cellnew.getPost();
    
        //if new cell location is empty
        Preconditions.ensure(postnew == null, "You can only relocate a post to an empty cell.");

        //cell and post of current location
        BoardCell cell = board.getCellByRowColumn(row, column);
        PostIt post = cell.getPost();
        //New post it info
        board.getCellByRowColumn(rownew, columnnew).setPost(post);
        board.getCellByRowColumn(rownew, columnnew).getPost().setBackup(new PostIt(board,cell,post.getOwner(),post.getContent()));
        //cell.setPost(null);
        //post.setCell(cellnew);
        //cellnew.setPost(post);
    
        Preconditions.ensure(post.relocate(cellnew), "Failure relocating the post.");
        repo2.save(post);
    }
    public Iterable<Board> findBoards(SystemUser user) {
        AccessLevel write = AccessLevel.WRITE;
        Iterable<Board> board1 = findBoardsByOwner(user);
        Iterable<Board> board2 = findBoardsByUserPermission(user, write);
        Set<Board> boards1 = new HashSet<>();
        Set<Board> boards2 = new HashSet<>();
        board1.forEach(boards1::add);
        board2.forEach(boards2::add);
        boards1.addAll(boards2);
        return boards1;
    }
    public Iterable<Board> findBoardsByUserPermission(SystemUser user, AccessLevel accessLevel) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER, BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.STUDENT);
        Iterable<BoardPermission> permission = repo1.findBySystemUserAccessLevel(user, accessLevel);
        Set<Board> boards = new HashSet<>();

        for(BoardPermission bp : permission)
            boards.add(repo.findByBoardPermission(bp));
        return boards;
    }
    /**
     * Find boards by owner iterable.
     *
     * @param user the user
     * @return the iterable
     */
    public Iterable<Board> findBoardsByOwner(SystemUser user)
    {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER, BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.STUDENT);
        return repo.findByOwner(user);
    }
    public SystemUser getUser(){
        Optional<UserSession> session = authz.session();
        if(session.isEmpty())
            throw new IllegalArgumentException("No user authentication");
        SystemUser user = session.get().authenticatedUser();
        return user;
    }


    public Iterable<PostIt> findPostIts(Board selectedBoard) {
        return repo2.getPostItsByBoard(selectedBoard);
    }
}
