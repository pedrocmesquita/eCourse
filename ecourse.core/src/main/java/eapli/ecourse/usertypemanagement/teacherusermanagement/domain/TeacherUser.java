package eapli.ecourse.usertypemanagement.teacherusermanagement.domain;

import eapli.ecourse.usertypemanagement.domain.BirthDate;
import eapli.ecourse.usertypemanagement.domain.TaxPayerNumber;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import javax.persistence.*;

@Entity
public class TeacherUser implements AggregateRoot<Acronym> {

    @Version
    private Long version;

    @EmbeddedId
    private Acronym acronym;

    @Embedded
    private TaxPayerNumber taxPayerNumber;

    @Embedded
    private BirthDate birthDate;


    /**
     * cascade = CascadeType.NONE as the systemUser is part of another aggregate
     */
    @OneToOne()
    private SystemUser systemUser;

    protected TeacherUser(SystemUser systemUser, Acronym acronym,
                          final TaxPayerNumber taxPayerNumber, final BirthDate birthDate) {
        this.acronym = acronym;
        this.systemUser = systemUser;
        this.taxPayerNumber = taxPayerNumber;
        this.birthDate = birthDate;
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
