package eapli.ecourse.teacherusermanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity
public class TeacherUser implements AggregateRoot<Acronym> {
    @Version
    private Long version;

    @EmbeddedId
    private Acronym acronym;

    /**
     * cascade = CascadeType.NONE as the systemUser is part of another aggregate
     */
    @OneToOne()
    private SystemUser systemUser;

    public TeacherUser(SystemUser systemUser, Acronym acronym) {
        this.acronym = acronym;
        this.systemUser = systemUser;
    }

    protected TeacherUser() {
        //ORM only
    }

    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    public Acronym acronym() {
        return identity();
    }

    public SystemUser user() {
        return this.systemUser;
    }

    @Override
    public Acronym identity() {
        return this.acronym;
    }
}
