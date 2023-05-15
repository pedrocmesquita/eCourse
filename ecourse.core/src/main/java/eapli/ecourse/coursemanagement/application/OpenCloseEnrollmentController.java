package eapli.ecourse.coursemanagement.application;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class OpenCloseEnrollmentController {
    private final ListCourseService service = new ListCourseService();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CourseRepository courseRepository = PersistenceContext.repositories().courses();

    public Iterable<Course> allCoursesEnrollOrProgress() {
        return service.allCoursesEnrollOrProgress();
    }

    public Course toggleOpenCloseEnroll(Course course) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN);
        if (course == null) throw new IllegalArgumentException();
        course.toggleOpenCloseEnroll();
        return courseRepository.save(course);
    }
}