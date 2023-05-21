package eapli.ecourse.usertypemanagement.teacherusermanagement.application;

import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usermanagement.domain.BaseRoles;
import eapli.ecourse.usertypemanagement.teacherusermanagement.domain.TeacherUser;
import eapli.ecourse.usertypemanagement.teacherusermanagement.repositories.TeacherUserRepository;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.List;

/**
 * List teachers service
 */
@ApplicationService
public class ListTeachersService {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final TeacherUserRepository teacherUserRepository = PersistenceContext.repositories().teacherUsers();

    public Iterable<TeacherUser> allTeacherUsers() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.POWER_USER);
        return teacherUserRepository.findAll();
    }

    public Iterable<TeacherUser> allTeachersJoinWithSystemUser() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.POWER_USER);
        return teacherUserRepository.joinAllTeachersWithSystemUser();
    }

}
