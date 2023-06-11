package eapli.ecourse.enrollmentmanagement.repositories;

import eapli.ecourse.enrollmentmanagement.domain.EnrollmentRequest;
import eapli.ecourse.enrollmentmanagement.domain.RequestState;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EnrollmentRequestRepository {
    private final List<EnrollmentRequest> requests = new ArrayList<>();

    public EnrollmentRequest add(EnrollmentRequest classs) {
        this.requests.add(classs);
        return classs;
    }

    public List<EnrollmentRequest> getEnrollmentRequestList() {
        return this.requests;
    }

    public List<EnrollmentRequest> findPendingRequests() {
        return requests.stream()
                .filter(request -> request.getState() == RequestState.PENDING)
                .collect(Collectors.toList());
    }
}