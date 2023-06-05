package eapli.ecourse.coursemanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.domain.model.DomainEntity;
import eapli.framework.validations.Preconditions;

import java.time.LocalDateTime;
import java.util.Calendar;

public class SchClass implements AggregateRoot<Name> {
    private String course;
    private int instructor;
    private Calendar startTime;
    private Calendar endTime;

    public SchClass(String course, int instructor, Calendar startTime, Calendar endTime) {
        Preconditions.noneNull(course, startTime, endTime);
        this.course = course.toString();
        this.instructor = instructor;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getCourse() {
        return course.toString();
    }

    public int getInstructor() {
        return instructor;
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

        return course.equals(that.course) && (instructor==that.instructor) &&
                startTime.equals(that.startTime) && endTime.equals(that.endTime);
    }

    @Override
    public Name identity() {
        return null;
    }
}
