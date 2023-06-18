package eapli.ecourse.boardmanagement.application;

import eapli.ecourse.boardmanagement.newdomain.AccessLevel;
import eapli.ecourse.boardmanagement.newdomain.BoardPermission;
import eapli.ecourse.boardmanagement.newdomain.Log;
import eapli.ecourse.boardmanagement.newdomain.Board;
import eapli.ecourse.boardmanagement.repositories.BoardPermissionRepository;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ViewBoardHistoryController
{
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    
    private final BoardRepository repo = PersistenceContext.repositories().boards();
    private final BoardPermissionRepository repo1 = PersistenceContext.repositories().boardPermissions();

    
    public ViewBoardHistoryController(){}
    
    public Iterable<Log> listBoardLogs(Board board)
    {
        return board.getLogs();
    }



    public Iterable<Board> findBoards(SystemUser user) {
        AccessLevel read = AccessLevel.READ;
        AccessLevel write = AccessLevel.WRITE;
        Iterable<Board> board1 = findBoardsByOwner(user);
        Iterable<Board> board2 = findBoardsByUserPermission(user, read);
        Iterable<Board> board3 = findBoardsByUserPermission(user, write);
        Set<Board> boards1 = new HashSet<>();
        Set<Board> boards2 = new HashSet<>();
        Set<Board> boards3 = new HashSet<>();
        board1.forEach(boards1::add);
        board2.forEach(boards2::add);
        board3.forEach(boards3::add);
        boards1.addAll(boards2);
        boards1.addAll(boards3);
        return boards1;
    }
    public Iterable<Board> findBoardsByUserPermission(SystemUser user, AccessLevel accessLevel) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER, BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.STUDENT);
        Iterable<BoardPermission> permission1 = repo1.findBySystemUserAccessLevel(user, accessLevel);
        Set<Board> boards = new HashSet<>();

        for(BoardPermission bp : permission1)
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
    
    /**
     * Get user system user.
     *
     * @return the system user
     */
    public SystemUser getUser()
    {
        Optional<UserSession> session = authz.session();
        if (session.isEmpty())
        {
            throw new IllegalArgumentException("No user authentication");
        }
        
        SystemUser user = session.get().authenticatedUser();
        return user;
    }
}
