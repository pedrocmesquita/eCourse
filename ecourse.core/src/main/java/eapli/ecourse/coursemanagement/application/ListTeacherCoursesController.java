package eapli.ecourse.coursemanagement.application;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usermanagement.domain.BaseRoles;
import eapli.ecourse.usertypemanagement.teacherusermanagement.domain.TeacherUser;
import eapli.ecourse.usertypemanagement.teacherusermanagement.repositories.TeacherUserRepository;
import eapli.ecourse.usertypemanagement.teacherusermanagement.repositories.TeachersInCourseRepository;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.Optional;

@UseCaseController
public class ListTeacherCoursesController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final TeachersInCourseRepository teachersInCourseRepository = PersistenceContext.repositories().teachersInCourse();
    private final TeacherUserRepository teacherUserRepository = PersistenceContext.repositories().teacherUsers();

    public Iterable<Course> findAllCoursesTeacherIsAssign() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.POWER_USER, BaseRoles.TEACHER);
        return teachersInCourseRepository.findAllCoursesTeacherIsAssign(getUserAcronym().acronym());
    }

    public TeacherUser getUserAcronym(){
        Optional<UserSession> session = authz.session();
        SystemUser user = session.get().authenticatedUser();
        return teacherUserRepository.getTeacherUserFromSystemUser(user);
    }
}
