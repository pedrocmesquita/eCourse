package eapli.ecourse.classmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import java.util.Calendar;

public class SchClass implements AggregateRoot<Name> {
    private String course;
    private Calendar startTime;
    private Calendar endTime;

    public SchClass(String course, Calendar startTime, Calendar endTime) {
        Preconditions.noneNull(course, startTime, endTime);
        this.course = course.toString();
        this.startTime = startTime;
        this.endTime = endTime;
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
    public Name identity() {
        return null;
    }
}
