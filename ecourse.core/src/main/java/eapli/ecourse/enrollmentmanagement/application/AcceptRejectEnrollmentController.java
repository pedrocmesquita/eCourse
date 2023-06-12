package eapli.ecourse.enrollmentmanagement.application;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.enrollmentmanagement.domain.EnrollmentRequest;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.enrollmentmanagement.repositories.EnrollmentRequestRepository;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;

import java.util.List;

@UseCaseController
public class AcceptRejectEnrollmentController {
    private final CourseRepository coursesRepository;
    private final EnrollmentRequestRepository enrollmentRequestRepository;
    public AcceptRejectEnrollmentController(final CourseRepository coursesRepositoryaux) {
        this.coursesRepository = coursesRepositoryaux;
        this.enrollmentRequestRepository = new EnrollmentRequestRepository();
    }
    public EnrollmentRequest accept(final EnrollmentRequest request) {
        request.accept();

        EnrollmentRequest aux = this.enrollmentRequestRepository.add(request);
        this.coursesRepository.save(request.course());

        return aux;
    }

    public EnrollmentRequest reject(final EnrollmentRequest request) {
        request.reject();

        return this.enrollmentRequestRepository.add(request);
    }

    public List<EnrollmentRequest> getPendingRequests() {
        return this.enrollmentRequestRepository.findPendingRequests();
    }
}
