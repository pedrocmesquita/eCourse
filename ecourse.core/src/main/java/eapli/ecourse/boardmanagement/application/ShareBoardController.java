package eapli.ecourse.boardmanagement.application;

import eapli.ecourse.boardmanagement.newdomain.*;
import eapli.ecourse.boardmanagement.repositories.BoardPermissionRepository;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usermanagement.domain.BaseRoles;
import eapli.ecourse.usertypemanagement.teacherusermanagement.domain.Acronym;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

import java.util.Optional;

/**
 * The type Share board controller.
 */
@UseCaseController
public class ShareBoardController{
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final BoardRepository repo = PersistenceContext.repositories().boards();
    private final BoardPermissionRepository permissionrepo = PersistenceContext.repositories().boardPermissions();

    /**
     * Find boards by owner iterable.
     *
     * @param user the user
     * @return the iterable
     */
    public Iterable<Board> findBoardsByOwner(SystemUser user) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER, BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.STUDENT);
        return repo.findByOwner(user);
    }

    /**
     * Get user system user.
     *
     * @return the system user
     */
    public SystemUser getUser(){
        Optional<UserSession> session = authz.session();
        if(session.isEmpty())
            throw new IllegalArgumentException("No user authentication");
        SystemUser user = session.get().authenticatedUser();
        return user;
    }

    /**
     * Share board.
     *
     * @param board the board
     * @param user  the user
     * @param write the write
     */
    public void shareBoard(Board board, SystemUser user, boolean write){
        if (write == true) {
            addPermission(board, new BoardPermission(user, AccessLevel.WRITE));
        } else if (write == false){
            addPermission(board, new BoardPermission(user, AccessLevel.READ));
        }
    }

    /**
     * Add permission.
     *
     * @param board            the board
     * @param boardPermissionp the board permissionp
     */
    public void addPermission(Board board, final BoardPermission boardPermissionp) {
        board.addPermission(boardPermissionp);
        permissionrepo.save(boardPermissionp);
        board.addLog(new Log("Permissions added: " + boardPermissionp.toString()));
        repo.save(board);
    }
}
