package eapli.ecourse.coursemanagement.domain;

import eapli.ecourse.Application;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}