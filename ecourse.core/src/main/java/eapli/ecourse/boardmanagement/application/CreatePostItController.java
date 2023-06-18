package eapli.ecourse.boardmanagement.application;

import eapli.ecourse.boardmanagement.newdomain.*;
import eapli.ecourse.boardmanagement.repositories.BoardCellRepository;
import eapli.ecourse.boardmanagement.repositories.BoardPermissionRepository;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
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

public class CreatePostItController
{
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final BoardRepository repo = PersistenceContext.repositories().boards();

    private final BoardPermissionRepository repo1 = PersistenceContext.repositories().boardPermissions();

    private final PostItService postSvc = new PostItService(PersistenceContext.repositories().postIts());

    private final BoardCellRepository cellRepo = PersistenceContext.repositories().cells();

    public PostIt attemptCreatePost(Board board, int row, int column, String text, String image)
    {
        Optional<UserSession> session = authz.session();
        SystemUser user = session.get().authenticatedUser();
        //if cell exists
        //Preconditions.ensure(board.getCellByRowColumn(row, column) != null, "Specified cell in row" + row + "column"+column +" doesn't exist.");
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
        return postSvc.createPostIt(board, board.getCellByRowColumn(row, column), user, cont);
    }
    public Iterable<Board> findBoards(SystemUser user, AccessLevel accessLevel) {
        Iterable<Board> board1 = findBoardsByOwner(user);
        Iterable<Board> board2 = findBoardsByUserPermission(user, accessLevel);
        Set<Board> boards1 = new HashSet<>();
        Set<Board> boards2 = new HashSet<>();
        board1.forEach(boards1::add);
        board2.forEach(boards2::add);
        boards1.addAll(boards2);
        return boards1;
    }


    public Iterable<Board> findBoardsByOwner(SystemUser user) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER, BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.STUDENT);
        return repo.findByOwner(user);
    }
    public Iterable<Board> findBoardsByUserPermission(SystemUser user, AccessLevel accessLevel) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER, BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.STUDENT);
        Iterable<BoardPermission> permission = repo1.findBySystemUserAccessLevel(user, accessLevel);
         Set<Board> boards = new HashSet<>();

        for(BoardPermission bp : permission)
            boards.add(repo.findByBoardPermission(bp));
        return boards;
    }
    public SystemUser getUser(){
        Optional<UserSession> session = authz.session();
        if(session.isEmpty())
            throw new IllegalArgumentException("No user authentication");
        SystemUser user = session.get().authenticatedUser();
        return user;
    }



    }

