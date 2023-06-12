package eapli.ecourse.coursemanagement.domain;

import eapli.ecourse.exammanagement.domain.Exam;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.Set;

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

    @OneToMany(mappedBy = "course")
    private Set<TeachersInCourse> teachersInCourses;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Exam> exams;

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
    protected Course(Name name, Description description, EnrollLimit enrollLimit) {
        Preconditions.noneNull(name, description);
        this.name = name;
        this.description = description;
        this.enrollLimit = enrollLimit;
        this.state = State.CLOSED;
        //teachersInCourses = new HashSet<TeachersInCourse>();
    }

    public Long courseId() {
        return courseId;
    }

    public Name name() {
        return identity();
    }

    public Description description() {
        return description;
    }

    public EnrollLimit enrollLimit() {
        return enrollLimit;
    }

    public State state() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Set<TeachersInCourse> getTeachersInCourses() {
        return teachersInCourses;
    }

    /**
     * Toggles course state between open and Close. Can only toggle to open if course has no activity
     */
    public void toggleOpenClose() {
        if (this.state.equals(State.CLOSED)) {
            this.setState(State.OPEN);
        } else if (this.state.equals(State.OPEN) || this.state.equals(State.PROGRESS)) {
            checkActivity();
            this.setState(State.CLOSED);
        } else {
            throw new IllegalStateException("Cannot open/close course that is in enrollment or progress");
        }
    }

    /**
     * checks if course as activity(schelude exams or extra classes).
     * @throws IllegalStateException case course as activity
     */
    private void checkActivity() throws IllegalStateException{
        //todo check activity
    }

    /**
     * Toggles course state between open and enroll.
     */
    public void toggleOpenCloseEnroll() {
        if (this.state.equals(State.OPEN)) {
            this.setState(State.ENROLL);
        } else if (this.state.equals(State.ENROLL)) {
            this.setState(State.OPEN);
        } else {
            throw new IllegalStateException("Cannot open/close enrollment of a course that is closed or in progress");
        }
    }

    public boolean addExam(Exam exam) {
        return this.exams.add(exam);
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

}
