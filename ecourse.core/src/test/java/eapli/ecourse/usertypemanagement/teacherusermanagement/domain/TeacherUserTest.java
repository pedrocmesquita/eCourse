/*
 * Copyright (c) 2013-2022 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.ecourse.usertypemanagement.teacherusermanagement.domain;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseBuilder;
import eapli.ecourse.coursemanagement.domain.TeachersInCourse;
import eapli.ecourse.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.domain.model.*;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Nuno Bettencourt [NMB] on 03/04/16.
 */
public class TeacherUserTest {

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

    @Test
    public void ensureTeacherUserEqualsPassesForSameAcronym() throws Exception {
        Calendar birthdate = Calendar.getInstance();
        birthdate.add(Calendar.DATE, -1);

        final TeacherUser teacherUser1 = new TeacherUserBuilder().withAcronym("AB").withBirthDate(birthdate)
                .withTaxPayerNumber("123456789").withSystemUser(getNewDummyUser()).build();

        final TeacherUser teacherUser2 = new TeacherUserBuilder().withAcronym("AB").withBirthDate(birthdate)
                .withTaxPayerNumber("123456789").withSystemUser(getNewDummyUser()).build();

        final boolean expected = teacherUser1.equals(teacherUser2);

        assertTrue(expected);
    }

    @Test
    public void ensureTeacherUserEqualsFailsForDifferentAcronym() throws Exception {
        Calendar birthdate = Calendar.getInstance();
        birthdate.add(Calendar.DATE, -1);
        final TeacherUser teacherUser1 = new TeacherUserBuilder().withAcronym("AB").withBirthDate(birthdate)
                .withTaxPayerNumber("123456789").withSystemUser(getNewDummyUser()).build();

        final TeacherUser teacherUser2 = new TeacherUserBuilder().withAcronym("AC").withBirthDate(birthdate)
                .withTaxPayerNumber("123456789").withSystemUser(getNewDummyUser()).build();

        final boolean expected = teacherUser1.equals(teacherUser2);

        assertFalse(expected);
    }

    @Test
    public void ensureTeacherUserEqualsAreTheSameForTheSameInstance() throws Exception {
        final TeacherUser teacherUser = new TeacherUser();

        final boolean expected = teacherUser.equals(teacherUser);

        assertTrue(expected);
    }

    @Test
    public void ensureTeacherUserEqualsFailsDifferentObjectTypes() throws Exception {
        Calendar birthdate = Calendar.getInstance();
        birthdate.add(Calendar.DATE, -1);
        final TeacherUser teacherUser1 = new TeacherUserBuilder().withAcronym("AB").withBirthDate(birthdate)
                .withTaxPayerNumber("123456789").withSystemUser(getNewDummyUser()).build();

        final boolean expected = teacherUser1.equals(getNewDummyUser());

        assertFalse(expected);
    }

    @Test
    public void ensureTeacherUserIsSameAsItsInstance() throws Exception {
        Calendar birthdate = Calendar.getInstance();
        birthdate.add(Calendar.DATE, -1);
        final TeacherUser teacherUser1 = new TeacherUserBuilder().withAcronym("AB").withBirthDate(birthdate)
                .withTaxPayerNumber("123456789").withSystemUser(getNewDummyUser()).build();

        final boolean expected = teacherUser1.sameAs(teacherUser1);

        assertTrue(expected);
    }

    @Test
    public void ensureTwoTeacherUserWithDifferentAcronymAreNotTheSame() throws Exception {
        Calendar birthdate = Calendar.getInstance();
        birthdate.add(Calendar.DATE, -1);
        final TeacherUser teacherUser1 = new TeacherUserBuilder().withAcronym("AB").withBirthDate(birthdate)
                .withTaxPayerNumber("123456789").withSystemUser(getNewDummyUser()).build();

        final TeacherUser teacherUser2 = new TeacherUserBuilder().withAcronym("AC").withBirthDate(birthdate)
                .withTaxPayerNumber("123456789").withSystemUser(getNewDummyUser()).build();

        final boolean expected = teacherUser1.sameAs(teacherUser2);

        assertFalse(expected);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureAcronymNotNullOrEmpty() {
        Calendar birthdate = Calendar.getInstance();
        birthdate.add(Calendar.DATE, -1);
        final TeacherUser teacherUser1 = new TeacherUserBuilder().withAcronym((Acronym) null).withBirthDate(birthdate)
                .withTaxPayerNumber("123456789").withSystemUser(getNewDummyUser()).build();
        final TeacherUser teacherUser2 = new TeacherUserBuilder().withAcronym("").withBirthDate(birthdate)
                .withTaxPayerNumber("123456789").withSystemUser(getNewDummyUser()).build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureAcronymOnlyLetters() {
        Calendar birthdate = Calendar.getInstance();
        birthdate.add(Calendar.DATE, -1);
        final TeacherUser teacherUser1 = new TeacherUserBuilder().withAcronym("1").withBirthDate(birthdate)
                .withTaxPayerNumber("123456789").withSystemUser(getNewDummyUser()).build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureAcronymOnlyCapitalLetters() {
        Calendar birthdate = Calendar.getInstance();
        birthdate.add(Calendar.DATE, -1);
        final TeacherUser teacherUser1 = new TeacherUserBuilder().withAcronym("a").withBirthDate(birthdate)
                .withTaxPayerNumber("123456789").withSystemUser(getNewDummyUser()).build();
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

    @Test(expected = IllegalStateException.class)
    public void ensureTeacherInCourseCourseNotNull() {
        Calendar birthdate = Calendar.getInstance();
        birthdate.add(Calendar.DATE, -1);
        final Course course = new CourseBuilder().withName("Java-1").withDescription("Java intro 22").build();
        final TeacherUser teacherUser = new TeacherUserBuilder().withAcronym("AB").withBirthDate(birthdate)
                .withTaxPayerNumber("123456789").withSystemUser(getNewDummyUser()).build();
        final TeachersInCourse teachersInCourse = new TeachersInCourse(course, teacherUser);
    }

    @Test(expected = IllegalStateException.class)
    public void ensureTeacherInCourseTeacherNotNull() {
        Calendar birthdate = Calendar.getInstance();
        birthdate.add(Calendar.DATE, -1);
        final Course course = new CourseBuilder().withName("Java-1").withDescription("Java intro 22").build();
        final TeacherUser teacherUser = new TeacherUserBuilder().withAcronym("AB").withBirthDate(birthdate)
                .withTaxPayerNumber("123456789").withSystemUser(getNewDummyUser()).build();
        final TeachersInCourse teachersInCourse = new TeachersInCourse(course, teacherUser);
    }

}
