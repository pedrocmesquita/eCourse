package eapli.ecourse.enrollmentmanagement.application;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.enrollmentmanagement.domain.EnrollmentRequest;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.enrollmentmanagement.repositories.EnrollmentRequestRepository;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

@UseCaseController
public class RequestEnrollmentController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final EnrollmentRequestRepository enrollmentRequestRepository = PersistenceContext.repositories().requests();
    public EnrollmentRequest attemptEnroll(final String coursename) {
        CourseRepository courseRepository = PersistenceContext.repositories().courses();
        Iterable<Course> courses = courseRepository.findAllCoursesOpenOrEnrollState();

        Course foundCourse = null;
        for (Course course : courses) {
            if (course.name().toString().equals(coursename)) {
                foundCourse = course;
                break;
            }
        }

        if (foundCourse == null) {
            throw new IllegalArgumentException("Course not found.");
        }

       authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.STUDENT);

        EnrollmentRequest enrollmentRequest = EnrollmentRequest.create(authz.session().orElseThrow(
                () -> new IllegalArgumentException("There is no user logged in.")
        ).authenticatedUser(), foundCourse);

        enrollmentRequestRepository.save(enrollmentRequest);

        return enrollmentRequest;
    }

}
