package eapli.ecourse.usertypemanagement.domain;

import eapli.ecourse.usermanagement.domain.BaseRoles;
import eapli.ecourse.usertypemanagement.teacherusermanagement.domain.TeacherUser;
import eapli.ecourse.usertypemanagement.teacherusermanagement.domain.TeacherUserBuilder;
import eapli.framework.infrastructure.authz.domain.model.*;
import org.junit.Test;

import java.util.Calendar;

public class TaxPayerNumberTest {

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
    public void ensureTaxpayerNumberNotNullOrEmpty() {
        Calendar birthdate = Calendar.getInstance();
        birthdate.add(Calendar.DATE, -1);
        final TeacherUser teacherUser1 = new TeacherUserBuilder().withAcronym("AB").withBirthDate(birthdate)
                .withTaxPayerNumber("").withSystemUser(getNewDummyUser()).build();
        final TeacherUser teacherUser2 = new TeacherUserBuilder().withAcronym("AB").withBirthDate(birthdate)
                .withTaxPayerNumber(null).withSystemUser(getNewDummyUser()).build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureTaxpayerNumberOnlyNumber() {
        Calendar birthdate = Calendar.getInstance();
        birthdate.add(Calendar.DATE, -1);
        final TeacherUser teacherUser1 = new TeacherUserBuilder().withAcronym("AB").withBirthDate(birthdate)
                .withTaxPayerNumber("a23456789").withSystemUser(getNewDummyUser()).build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureTaxpayerNumberMustHave9Digits() {
        Calendar birthdate = Calendar.getInstance();
        birthdate.add(Calendar.DATE, -1);
        final TeacherUser teacherUser1 = new TeacherUserBuilder().withAcronym("AB").withBirthDate(birthdate)
                .withTaxPayerNumber("12345678").withSystemUser(getNewDummyUser()).build();
        final TeacherUser teacherUser2 = new TeacherUserBuilder().withAcronym("AB").withBirthDate(birthdate)
                .withTaxPayerNumber("1234567890").withSystemUser(getNewDummyUser()).build();
    }
}
