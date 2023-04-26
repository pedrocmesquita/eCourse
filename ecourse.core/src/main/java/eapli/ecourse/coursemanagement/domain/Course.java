package eapli.ecourse.coursemanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;

@Entity
public class Course implements AggregateRoot<CourseID> {

    @Id
    private CourseID id;

    @Embedded
    private Name name;

    @Embedded
    private Description description;

    @Embedded
    private EnrollLimit enrollLimit;

    @Enumerated(EnumType.STRING)
    private State state;

    protected Course() {
        //ORM only
    }

    public Course(Name name, Description description, EnrollLimit enrollLimit) {
        if (name == null || description == null)
            throw new IllegalArgumentException();
        this.name = name;
        this.description = description;
        this.enrollLimit = enrollLimit;
        this.state = State.CLOSED;
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
    public boolean sameAs(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

    public CourseID id() {
        return identity();
    }

    @Override
    public CourseID identity() {
        return this.id;
    }

}