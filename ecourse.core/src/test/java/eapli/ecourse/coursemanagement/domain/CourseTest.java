package eapli.ecourse.coursemanagement.domain;

import eapli.ecourse.Application;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        System.out.println(course1.getDescription());
        System.out.println(course2.getDescription());
        assertFalse(course1.sameAs(course2));
    }

    @Test
    public void ensureCourseNotSameDifferentLimits() throws Exception {
        Course course1 = new CourseBuilder().withName("Java-1").withDescription("Java intro 22").withEnrollLimit(80, 120).build();
        Course course2 = new CourseBuilder().withName("Java-1").withDescription("Java intro 22").withEnrollLimit(80, 140).build();
        assertFalse(course1.sameAs(course2));
    }
}