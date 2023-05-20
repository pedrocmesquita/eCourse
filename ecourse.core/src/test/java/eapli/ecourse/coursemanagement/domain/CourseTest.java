package eapli.ecourse.coursemanagement.domain;

import eapli.ecourse.Application;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {

    private static final Integer LIMIT = Application.settings().getCourseDescriptionCharacterLimit();

    @Test(expected = IllegalArgumentException.class)
    public void ensureEnrollmentLimitsMinimumNonNegative() {
        EnrollLimit enrollLimit = new EnrollLimit(-1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureEnrollmentLimitsMaximumNonNegative() {
        EnrollLimit enrollLimit = new EnrollLimit(1, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureEnrollmentLimitsMaximumHigherThanMinimum() {
        EnrollLimit enrollLimit = new EnrollLimit(11, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureDescriptionLimit() {
        Description description = new Description("a".repeat(Math.max(0, LIMIT + 1)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureDescriptionNotNullOrEmpty() {
        Description description1 = new Description("");
        Description description2 = new Description(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNameNotNullOrEmpty() {
        Name name1 = new Name("");
        Name name2 = new Name(null);
    }

    @Test
    public void ensureCourseSameEqualAttributes() throws Exception {
        Course course1 = new CourseBuilder().withName("Java-1").withDescription("Java intro 22").withEnrollLimit(80, 120).build();
        Course course2 = new CourseBuilder().withName("Java-1").withDescription("Java intro 22").withEnrollLimit(80, 120).build();
        assertTrue(course1.sameAs(course2));
    }

    @Test
    public void ensureCourseNotSameDifferentName() throws Exception {
        Course course1 = new CourseBuilder().withName("Java-2").withDescription("Java intro 22").withEnrollLimit(80, 120).build();
        Course course2 = new CourseBuilder().withName("Java-1").withDescription("Java intro 22").withEnrollLimit(80, 120).build();
        assertFalse(course1.sameAs(course2));
    }

    @Test
    public void ensureCourseNotSameDifferentDescription() throws Exception {
        Course course1 = new CourseBuilder().withName("Java-1").withDescription("Java intro 22").build();
        Course course2 = new CourseBuilder().withName("Java-1").withDescription("Java intro 23").build();
        assertFalse(course1.sameAs(course2));
    }

    @Test
    public void ensureCourseNotSameDifferentLimits() throws Exception {
        Course course1 = new CourseBuilder().withName("Java-1").withDescription("Java intro 22").withEnrollLimit(80, 120).build();
        Course course2 = new CourseBuilder().withName("Java-1").withDescription("Java intro 22").withEnrollLimit(80, 140).build();
        assertFalse(course1.sameAs(course2));
    }

    @Test
    public void ensureStatusToggleOpenToEnroll() {
        Course course1 = new CourseBuilder().withName("Java-1").withDescription("Java intro 22").withEnrollLimit(80, 120).build();
        course1.setState(State.OPEN);
        course1.toggleOpenCloseEnroll();
        assertEquals(course1.getState(), State.ENROLL);
    }

    @Test
    public void ensureToggleEnrollmentOpenToEnroll() {
        Course course1 = new CourseBuilder().withName("Java-1").withDescription("Java intro 22").withEnrollLimit(80, 120).build();
        course1.setState(State.ENROLL);
        course1.toggleOpenCloseEnroll();
        assertEquals(course1.getState(), State.OPEN);
    }

    @Test
    public void ensureToggleEnrollmentEnrollToOpen() {
        Course course1 = new CourseBuilder().withName("Java-1").withDescription("Java intro 22").withEnrollLimit(80, 120).build();
        course1.setState(State.ENROLL);
        course1.toggleOpenCloseEnroll();
        assertEquals(course1.getState(), State.OPEN);
    }

    @Test(expected = IllegalStateException.class)
    public void ensureToggleEnrollmentCannotToggleClosed() {
        Course course1 = new CourseBuilder().withName("Java-1").withDescription("Java intro 22").withEnrollLimit(80, 120).build();
        course1.setState(State.CLOSED);
        course1.toggleOpenCloseEnroll();
    }

    @Test(expected = IllegalStateException.class)
    public void ensureToggleEnrollmentCannotToggleProgress() {
        Course course1 = new CourseBuilder().withName("Java-1").withDescription("Java intro 22").withEnrollLimit(80, 120).build();
        course1.setState(State.PROGRESS);
        course1.toggleOpenCloseEnroll();
    }

    @Test
    public void ensureToggleOpenToClose() {
        Course course1 = new CourseBuilder().withName("Java-1").withDescription("Java intro 22").withEnrollLimit(80, 120).build();
        course1.setState(State.OPEN);
        course1.toggleOpenClose();
        assertEquals(course1.getState(), State.CLOSED);
    }

    @Test
    public void ensureToggleCloseToOpen() {
        Course course1 = new CourseBuilder().withName("Java-1").withDescription("Java intro 22").withEnrollLimit(80, 120).build();
        course1.setState(State.CLOSED);
        course1.toggleOpenClose();
        assertEquals(course1.getState(), State.OPEN);
    }

    @Test(expected = IllegalStateException.class)
    public void ensureToggleCannotToggleEnroll() {
        Course course1 = new CourseBuilder().withName("Java-1").withDescription("Java intro 22").withEnrollLimit(80, 120).build();
        course1.setState(State.ENROLL);
        course1.toggleOpenClose();
    }

    @Test//(expected = IllegalStateException.class)
    public void ensureToggleCannotToggleToClosedWithActivity() {
        //TODO, not implemented yet
    }



}