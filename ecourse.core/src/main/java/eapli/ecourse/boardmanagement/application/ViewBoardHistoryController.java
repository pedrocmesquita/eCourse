package eapli.ecourse.boardmanagement.application;

import eapli.ecourse.boardmanagement.newdomain.Log;
import eapli.ecourse.boardmanagement.newdomain.Board;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.Optional;

public class ViewBoardHistoryController
{
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    
    private final BoardRepository repo = PersistenceContext.repositories().boards();
    
    public ViewBoardHistoryController(){}
    
    public Iterable<Log> listBoardLogs(Board board)
    {
        return board.getLogs();
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
