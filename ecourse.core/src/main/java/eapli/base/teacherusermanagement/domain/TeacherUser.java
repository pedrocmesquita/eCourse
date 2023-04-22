package eapli.base.teacherusermanagement.domain;

import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class TeacherUser {
    @EmbeddedId
    private Acronym acronym;

    @OneToOne()
    private SystemUser systemUser;

    public TeacherUser() {
        //ORM only
    }

    public TeacherUser(Acronym acronym, SystemUser systemUser) {
        this.acronym = acronym;
        this.systemUser = systemUser;
    }

}
