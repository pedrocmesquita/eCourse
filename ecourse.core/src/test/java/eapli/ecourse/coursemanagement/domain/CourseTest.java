package eapli.ecourse.coursemanagement.domain;

import eapli.ecourse.Application;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CourseTest {

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
        EnrollLimit enrollLimit = new EnrollLimit(1, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureDescriptionLimit() {
        final Integer LIMIT = Application.settings().getCourseDescriptionCharacterLimit();
        Description description = new Description("a".repeat(Math.max(0, LIMIT + 1)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureDescriptionNotNullOrEmpty() {
        final Integer LIMIT = Application.settings().getCourseDescriptionCharacterLimit();
        Description description = new Description("a".repeat(Math.max(0, LIMIT + 1)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNameNotNullOrEmpty() {
        final Integer LIMIT = Application.settings().getCourseDescriptionCharacterLimit();
        Description description = new Description("a".repeat(Math.max(0, LIMIT + 1)));
    }

    @Test
    public void ensureCourseEqualsSameAttributes() throws Exception {
        Course course1 = new CourseBuilder().withName("Java-1").withDescription("Java intro 22").withEnrollLimit(80, 120).build();
        Course course2 = new CourseBuilder().withName("Java-1").withDescription("Java intro 22").withEnrollLimit(80, 120).build();
        assertEquals(course1, course2);
    }

    @Test
    public void ensureCourseNotEqualsDifferentName() throws Exception {
        Course course1 = new CourseBuilder().withName("Java-2").withDescription("Java intro 22").withEnrollLimit(80, 120).build();
        Course course2 = new CourseBuilder().withName("Java-1").withDescription("Java intro 22").withEnrollLimit(80, 120).build();
        assertNotEquals(course1, course2);
    }

//    @Test
//    public void ensureCourseNotEqualsDifferentDescription() throws Exception {
//        Course course1 = new CourseBuilder().withName("Java-1").withDescription("Java intro 22").build();
//        Course course2 = new CourseBuilder().withName("Java-1").withDescription("Java intro 23").build();
//        assertNotEquals(course1, course2);
//    }
//
//    @Test
//    public void ensureCourseNotEqualsDifferentLimits() throws Exception {
//        Course course1 = new CourseBuilder().withName("Java-1").withDescription("Java intro 22").withEnrollLimit(80, 120).build();
//        Course course2 = new CourseBuilder().withName("Java-1").withDescription("Java intro 22").withEnrollLimit(80, 140).build();
//        assertNotEquals(course1, course2);
//    }
}