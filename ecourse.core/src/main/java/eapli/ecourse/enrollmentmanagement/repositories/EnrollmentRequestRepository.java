package eapli.ecourse.enrollmentmanagement.repositories;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.Name;
import eapli.ecourse.enrollmentmanagement.domain.EnrollmentRequest;
import eapli.ecourse.enrollmentmanagement.domain.RequestState;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface EnrollmentRequestRepository extends DomainRepository<Long, EnrollmentRequest> {
    Iterable<EnrollmentRequest> findPendingRequests();
}