package eapli.ecourse.usertypemanagement.teacherusermanagement.domain;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseBuilder;
import eapli.ecourse.coursemanagement.domain.TeachersInCourse;
import eapli.ecourse.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.domain.model.*;
import org.junit.Test;

import java.util.Calendar;

public class TeachersInCourseTest {

    public static SystemUser dummyUser(final String username, final Role... roles) {
        // should we load from spring context?
        final SystemUserBuilder userBuilder = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder());
        return userBuilder.with(username, "duMMy1", "dummy", "dummy", "a@b.ro").withRoles(roles).build();
    }

    private SystemUser getNewDummyUser() {
        return dummyUser("dummy", BaseRoles.ADMIN);
    }

    private SystemUser getNewDummyUserTwo() {
        return dummyUser("dummy-two", BaseRoles.ADMIN);
    }

    @Test(expected = IllegalStateException.class)
    public void ensureTeacherInCourseFailsClosedCourse() {
        Calendar birthdate = Calendar.getInstance();
        birthdate.add(Calendar.DATE, -1);
        final Course course = new CourseBuilder().withName("Java-1").withDescription("Java intro 22").build();
        final TeacherUser teacherUser = new TeacherUserBuilder().withAcronym("AB").withBirthDate(birthdate)
                .withTaxPayerNumber("123456789").withSystemUser(getNewDummyUser()).build();
        final TeachersInCourse teachersInCourse = new TeachersInCourse(course, teacherUser);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureTeacherInCourseCourseNotNull() {
        Calendar birthdate = Calendar.getInstance();
        birthdate.add(Calendar.DATE, -1);
        final Course course = new CourseBuilder().withName("Java-1").withDescription("Java intro 22").build();
        course.toggleOpenClose();
        final TeacherUser teacherUser = new TeacherUserBuilder().withAcronym("AB").withBirthDate(birthdate)
                .withTaxPayerNumber("123456789").withSystemUser(getNewDummyUser()).build();
        final TeachersInCourse teachersInCourse = new TeachersInCourse(null, teacherUser);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureTeacherInCourseTeacherNotNull() {
        Calendar birthdate = Calendar.getInstance();
        birthdate.add(Calendar.DATE, -1);
        final Course course = new CourseBuilder().withName("Java-1").withDescription("Java intro 22").build();
        course.toggleOpenClose();
        final TeacherUser teacherUser = new TeacherUserBuilder().withAcronym("AB").withBirthDate(birthdate)
                .withTaxPayerNumber("123456789").withSystemUser(getNewDummyUser()).build();
        final TeachersInCourse teachersInCourse = new TeachersInCourse(course, null);
    }
}