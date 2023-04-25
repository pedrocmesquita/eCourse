package eapli.ecourse.teacherusermanagement.domain;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class TeacherUserBuilder implements DomainFactory<TeacherUser> {

    private SystemUser systemUser;
    private Acronym acronym;

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

    @Override
    public TeacherUser build() {
        return new TeacherUser(this.systemUser, this.acronym);
    }
}
