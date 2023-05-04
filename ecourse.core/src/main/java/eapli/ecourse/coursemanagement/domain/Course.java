package eapli.ecourse.coursemanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;

@Entity
public class Course implements AggregateRoot<Name> {

    /**
     * ORM primary key. Autogenerated value
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long courseId;

    /**
     * Business ID
     */
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

    /**
     * Constructor
     *
     * @param name        Mandatory, Unique
     * @param description Mandatory
     * @param enrollLimit Optional
     */
    public Course(Name name, Description description, EnrollLimit enrollLimit) {
        Preconditions.noneNull(name, description);
        this.name = name;
        this.description = description;
        this.enrollLimit = enrollLimit;
        this.state = State.CLOSED;
    }

    public Name getName() {
        return identity();
    }

    public Description getDescription() {
        return description;
    }

    public EnrollLimit getEnrollLimit() {
        return enrollLimit;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void toggleOpenClose() {
        if (this.state.equals(State.CLOSED)) {
            open();
        } else if (this.state.equals(State.OPEN) || this.state.equals(State.PROGRESS)) {
            close();
        } else {
            throw new IllegalStateException("Cannot open/close course that is in enrollment state");
        }
    }

    private void open() {
        this.setState(State.OPEN);
    }

    private void close() {
        //todo check activity
        this.setState(State.CLOSED);
    }

    public void toggleOpenCloseEnroll() {
        if (this.state.equals(State.OPEN)) {
            this.setState(State.ENROLL);
        } else if (this.state.equals(State.ENROLL)) {
            //todo check teacher leader assigned
            this.setState(State.PROGRESS);
        } else {
            throw new IllegalStateException("Cannot open/close enrollment of a course that is closed");
        }
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
        if (!(other instanceof Course)) {
            return false;
        }

        final Course that = (Course) other;
        if (this == that) {
            return true;
        }

        return name.equals(that.name) && description.equals(that.description)
                && enrollLimit.equals(that.enrollLimit) && state.equals(that.state);
    }

    @Override
    public Name identity() {
        return this.name;
    }

    @Override
    public String toString() {
        return name.toString();
    }
}
