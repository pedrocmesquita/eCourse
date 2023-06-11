package eapli.ecourse.enrollmanagement;

import eapli.ecourse.Application;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.State;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.enrollmentmanagement.application.RequestEnrollmentController;
import eapli.ecourse.enrollmentmanagement.domain.EnrollmentRequest;
import eapli.ecourse.enrollmentmanagement.domain.RequestState;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class RequestEnrollmentTest {
    @Test
    public void returnsRequestStateString() {
        assertEquals("PENDING", RequestState.PENDING.name());
        assertEquals("ACCEPTED", RequestState.ACCEPTED.name());
        assertEquals("REJECTED", RequestState.REJECTED.name());
    }
    /*
    @Test
    public void enrollmentRequestCreateWithClosedCourse() {
        RequestEnrollmentController controller = new RequestEnrollmentController();
        EnrollmentRequest test = controller.attemptEnroll("Java-2-23");
        assertEquals("\"This course is not available for enrollment\"", test.toString());
    }

    @Test
    public void enrollmentRequestCreatedWithValidCours(){
        RequestEnrollmentController controller = new RequestEnrollmentController();
        EnrollmentRequest test = controller.attemptEnroll("Java-3-23");
        assertEquals("Request for course Java-3-23 created successfully. Please wait for your response.", test.toString());
    }

     */
}

