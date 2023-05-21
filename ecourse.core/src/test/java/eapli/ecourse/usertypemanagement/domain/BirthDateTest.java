package eapli.ecourse.usertypemanagement.domain;

import eapli.ecourse.usermanagement.domain.BaseRoles;
import eapli.ecourse.usertypemanagement.teacherusermanagement.domain.TeacherUser;
import eapli.ecourse.usertypemanagement.teacherusermanagement.domain.TeacherUserBuilder;
import eapli.framework.infrastructure.authz.domain.model.*;
import org.junit.Test;

import java.util.Calendar;

public class BirthDateTest {
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

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveBirthdate() {
        final TeacherUser teacherUser1 = new TeacherUserBuilder().withAcronym("AB").withBirthDate(null)
                .withTaxPayerNumber("123456789").withSystemUser(getNewDummyUser()).build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureBirthdateMustNotHaveBeInFuture() {
        Calendar birthdate = Calendar.getInstance();
        birthdate.add(Calendar.DATE, 1);
        final TeacherUser teacherUser2 = new TeacherUserBuilder().withAcronym("AB").withBirthDate(birthdate)
                .withTaxPayerNumber("123456789").withSystemUser(getNewDummyUser()).build();
    }
}
