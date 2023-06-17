package eapli.ecourse.persistence.impl.jpa;

import eapli.ecourse.Application;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.Name;
import eapli.ecourse.enrollmentmanagement.domain.RequestState;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.enrollmentmanagement.domain.EnrollmentRequest;
import eapli.ecourse.enrollmentmanagement.repositories.EnrollmentRequestRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;

public class JpaEnrollmentRequestRepository extends JpaAutoTxRepository<EnrollmentRequest, Long, Long> implements EnrollmentRequestRepository {
    public JpaEnrollmentRequestRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    public JpaEnrollmentRequestRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "id");
    }

    @Override
    public Iterable<EnrollmentRequest> findPendingRequests() {
        final TypedQuery<EnrollmentRequest> query = entityManager().createQuery(
                "SELECT c FROM EnrollmentRequest c WHERE c.state = :stateParam", EnrollmentRequest.class);
        query.setParameter("stateParam", RequestState.PENDING);
        return query.getResultList();
    }
}
