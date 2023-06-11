package eapli.ecourse.enrollmentmanagement.application;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.enrollmentmanagement.domain.EnrollmentRequest;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

@UseCaseController
public class RequestEnrollmentController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    public EnrollmentRequest attemptEnroll(final String courseCodeString) {
        CourseRepository courseRepository = PersistenceContext.repositories().courses();
        Iterable<Course> courses = courseRepository.findAllCoursesOpenOrEnrollState();

        Course foundCourse = null;
        for (Course course : courses) {
            if (course.name().toString().equals(courseCodeString)) {
                foundCourse = course;
                break;
            }
        }

        if (foundCourse == null) {
            throw new IllegalArgumentException("Course not found.");
        }

       authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.STUDENT);

        return EnrollmentRequest.create(
                authz.session().orElseThrow(
                        () -> new IllegalArgumentException("There is no user logged in.")
                ).authenticatedUser(),
                foundCourse
        );
    }

}
