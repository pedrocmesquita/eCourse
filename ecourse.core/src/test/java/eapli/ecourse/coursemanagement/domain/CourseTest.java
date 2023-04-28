package eapli.ecourse.coursemanagement.domain;

import eapli.ecourse.studentusermanagement.domain.StudentUser;
import eapli.ecourse.studentusermanagement.domain.StudentUserBuilder;
import eapli.ecourse.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.domain.model.Role;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {

    @Test
    public void ensureDescriptionLimits() {
    }

    @Test
    public void ensureEnrollmentLimits() {
    }

    @Test
    public void ensureCourseEqualsAreTheSameForTheSameInstance() throws Exception {
        final Course course = new Course();

        final boolean expected = course.equals(course);

        assertTrue(expected);
    }

//    @Test
//    public void ensureCourseEqualsFailsForDifferentObjectTypes() throws Exception {
//
//        final Course course = new CourseBuilder().withMecanographicNumber("DUMMY")
//                .withSystemUser(getNewDummyUser()).build();
//
//        final boolean expected = aStudentUser.equals(getNewDummyUser());
//
//        assertFalse(expected);
//    }
//
//    @Test
//    public void ensureCourseIsTheSameAsItsInstance() throws Exception {
//        final StudentUser aStudentUser = new StudentUserBuilder().withMecanographicNumber("DUMMY")
//                .withSystemUser(getNewDummyUser()).build();
//
//        final boolean expected = aStudentUser.sameAs(aStudentUser);
//
//        assertTrue(expected);
//    }

    @Test
    public void ensureCourseEqualsSameAttributes() throws Exception{
        Course course1 = new CourseBuilder().withName("Java-1").withDescription("Java intro 22").build();
        Course course2 = new CourseBuilder().withName("Java-1").withDescription("Java intro 22").build();
        assertEquals(course1, course2);
    }

    @Test
    public void ensureCourseNotEqualsDifferentName() throws Exception{
        Course course1 = new CourseBuilder().withName("Java-2").withDescription("Java intro 22").build();
        Course course2 = new CourseBuilder().withName("Java-1").withDescription("Java intro 22").build();
        assertNotNull(course1);
        //assertNotEquals(course1, course2);
    }

    @Test
    public void ensureCourseNotEqualsDifferentDescription() throws Exception{
        Course course1 = new CourseBuilder().withName("Java-2").withDescription("Java intro 22").build();
        Course course2 = new CourseBuilder().withName("Java-1").withDescription("Java intro 22").build();
        assertNotNull(course1);
        //assertNotEquals(course1, course2);
    }

    @Test
    public void ensureCourseNotEqualsDifferentLimits() throws Exception{
        Course course1 = new CourseBuilder().withName("Java-2").withDescription("Java intro 22").build();
        Course course2 = new CourseBuilder().withName("Java-1").withDescription("Java intro 22").build();
        assertNotNull(course1);
        //assertNotEquals(course1, course2);
    }
}