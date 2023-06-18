package eapli.ecourse.boardmanagement.application;

import eapli.ecourse.boardmanagement.newdomain.*;
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

public class UndoPostItController
{
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final BoardRepository repo = PersistenceContext.repositories().boards();
    private final BoardPermissionRepository repo1 = PersistenceContext.repositories().boardPermissions();

    private final PostItRepository repo2 = PersistenceContext.repositories().postIts();
    Optional<UserSession> session = authz.session();
    SystemUser user = session.get().authenticatedUser();

    public void rollbackPost(Board board, PostIt postIt)
    {

        Preconditions.ensure(postIt.rollbackPost(),
                "Failure undoing changes to post.");
        repo2.save(postIt);
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
    public Iterable<PostIt> findPostItsByOwner(Board selectedBoard, SystemUser user) {
        return repo2.getPostItsByOwner(selectedBoard,user);
    }
}
