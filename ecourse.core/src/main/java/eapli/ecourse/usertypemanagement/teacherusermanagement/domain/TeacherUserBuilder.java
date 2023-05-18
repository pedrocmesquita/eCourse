package eapli.ecourse.usertypemanagement.teacherusermanagement.domain;

import eapli.ecourse.usertypemanagement.domain.BirthDate;
import eapli.ecourse.usertypemanagement.domain.TaxPayerNumber;
import eapli.ecourse.usertypemanagement.studentusermanagement.domain.StudentUserBuilder;
import eapli.framework.domain.model.DomainFactory;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.time.LocalDate;
import java.util.Calendar;

public class TeacherUserBuilder implements DomainFactory<TeacherUser> {

    private SystemUser systemUser;
    private Acronym acronym;
    private TaxPayerNumber taxPayerNumber;
    private BirthDate birthDate;

    public TeacherUserBuilder withSystemUser(final SystemUser systemUser) {
        this.systemUser = systemUser;
        return this;
    }

    public TeacherUserBuilder withAcronym(final Acronym acronym) {
        this.acronym = acronym;
        return this;
    }

    public TeacherUserBuilder withAcronym(final String acronym) {
        this.acronym = new Acronym(acronym);
        return this;
    }

    public TeacherUserBuilder withTaxPayerNumber(final String taxPayerNumber) {
        this.taxPayerNumber = new TaxPayerNumber(taxPayerNumber);
        return this;
    }

    public TeacherUserBuilder withBirthDate(final Calendar birthDate) {
        this.birthDate = new BirthDate(birthDate);
        return this;
    }

    @Override
    public TeacherUser build() {
        return new TeacherUser(this.systemUser, this.acronym, this.taxPayerNumber, this.birthDate);
    }
}
