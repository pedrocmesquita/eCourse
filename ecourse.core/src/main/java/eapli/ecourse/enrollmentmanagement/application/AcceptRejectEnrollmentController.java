package eapli.ecourse.enrollmentmanagement.application;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.StudentsInCourse;
import eapli.ecourse.coursemanagement.domain.TeachersInCourse;
import eapli.ecourse.enrollmentmanagement.domain.EnrollmentRequest;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.enrollmentmanagement.repositories.EnrollmentRequestRepository;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usermanagement.domain.BaseRoles;
import eapli.ecourse.usertypemanagement.studentusermanagement.domain.StudentUser;
import eapli.ecourse.usertypemanagement.studentusermanagement.repositories.ClientUserRepository;
import eapli.ecourse.usertypemanagement.studentusermanagement.repositories.StudentsInCourseRepository;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;

import java.util.List;

@UseCaseController
public class AcceptRejectEnrollmentController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final EnrollmentRequestRepository enrollmentRequestRepository = PersistenceContext.repositories().requests();
    private final StudentsInCourseRepository studentsInCourseRepository = PersistenceContext.repositories().studentsInCourse();
    private final ClientUserRepository clientUserRepository = PersistenceContext.repositories().clientUsers();

    public EnrollmentRequest accept(final EnrollmentRequest request) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN);
        request.accept();
        enrollmentRequestRepository.save(request);
        StudentUser studentUser = clientUserRepository.findByUsername(request.student().username()).get();
        StudentsInCourse studentInCourse = new StudentsInCourse(request.course(), studentUser);
        studentsInCourseRepository.save(studentInCourse);
        return request;
    }

    public EnrollmentRequest reject(final EnrollmentRequest request) {
        request.reject();
        enrollmentRequestRepository.save(request);

        return request;
    }

    public Iterable<EnrollmentRequest> getPendingRequests() {
        return this.enrollmentRequestRepository.findPendingRequests();
    }
}
