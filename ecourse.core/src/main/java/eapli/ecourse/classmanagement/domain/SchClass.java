package eapli.ecourse.classmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.Calendar;

@Entity
public class SchClass implements AggregateRoot<Calendar> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long courseId;
    @Column(nullable = false)
    private String course;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Calendar startTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Calendar endTime;


    public SchClass(String course, Calendar startTime, Calendar endTime) {
        Preconditions.noneNull(course, startTime, endTime);
        this.course = course.toString();
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public SchClass() {

    }

    public String getCourse() {
        return course.toString();
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    @Override
    public boolean equals(final Object o)
    {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode()
    {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(final Object other) {
        if (!(other instanceof SchClass)) {
            return false;
        }

        final SchClass that = (SchClass) other;
        if (this == that) {
            return true;
        }

        return course.equals(that.course) &&
                startTime.equals(that.startTime) && endTime.equals(that.endTime);
    }

    @Override
    public Calendar identity() {
        return this.startTime;
    }
}
