package eapli.ecourse.enrollmanagement;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.enrollmentmanagement.domain.EnrollmentRequest;
import eapli.ecourse.enrollmentmanagement.domain.RequestState;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AcceptRejectEnrollmentTest {

    private CourseRepository courseRepository;
    @BeforeEach
    public void init() {
        CourseRepository courseRepository = PersistenceContext.repositories().courses();
    }

    @Test
    public void returnsRequestStateString() {
        assertEquals("PENDING", RequestState.PENDING.name());
        assertEquals("ACCEPTED", RequestState.ACCEPTED.name());
        assertEquals("REJECTED", RequestState.REJECTED.name());
    }

    /*
    @Test
    public void acceptRequest() {
        Iterable<Course> courses = courseRepository.findAllCoursesOpenOrEnrollState();

        Course foundCourse = null;
        for (Course course : courses) {
            if (course.name().toString().equals("Java-3-23")) {
                foundCourse = course;
                break;
            }
        }
        System.out.println(foundCourse.name().toString());
        EnrollmentRequest test = EnrollmentRequest.create(AuthzRegistry.authorizationService().session().get().authenticatedUser(), foundCourse);
        test.accept();
        assertEquals("ACCEPTED", test.getState().name());
    }

    @Test
    public void rejectRequest() {
        CourseRepository courseRepository = PersistenceContext.repositories().courses();
        Iterable<Course> courses = courseRepository.findAllCoursesOpenOrEnrollState();

        Course foundCourse = null;
        for (Course course : courses) {
            if (course.name().toString().equals("Java-3-23")) {
                foundCourse = course;
                break;
            }
        }
        System.out.println(foundCourse.name().toString());
        EnrollmentRequest test = EnrollmentRequest.create(AuthzRegistry.authorizationService().session().get().authenticatedUser(), foundCourse);
        test.reject();
        assertEquals("REJECTED", test.getState().name());
    }

     */
}
