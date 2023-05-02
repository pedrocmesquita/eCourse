package eapli.ecourse.coursemanagement.application;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.State;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usermanagement.domain.BaseRoles;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

/**
 * Application service to avoid code duplication. Lists all courses or in a state
 */
@ApplicationService
public class ListCourseService {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CourseRepository courseRepository = PersistenceContext.repositories().courses();

    public Iterable<Course> allClosedCourses() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.POWER_USER);
        return courseRepository.findAllCoursesWithState(State.CLOSED);
    }

    public Iterable<Course> allOpenCourses() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.POWER_USER, BaseRoles.TEACHER);
        return courseRepository.findAllCoursesWithOtherState(State.CLOSED);
    }

    public Iterable<Course> allEnrollCourses() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.POWER_USER, BaseRoles.STUDENT);
        return courseRepository.findAllCoursesWithOtherState(State.ENROLL);
    }

    public Iterable<Course> allProgressCourses() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.POWER_USER);
        return courseRepository.findAllCoursesWithOtherState(State.PROGRESS);
    }

    public Iterable<Course> allCourses() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.POWER_USER);
        return courseRepository.findAll();
    }
}
