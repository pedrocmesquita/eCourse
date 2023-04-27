package eapli.ecourse.coursemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import org.apache.commons.lang3.builder.EqualsBuilder;

import javax.persistence.*;

public class CourseId implements ValueObject, Comparable<CourseId> {

    private static final long serialVersionUID = 1L;

    @GeneratedValue
    private Long courseId;

    public CourseId() {
        //ORM only
    }

    public CourseId(Long courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CourseId courseID = (CourseId) o;

        return new EqualsBuilder().append(courseId, courseID.courseId).isEquals();
    }

    @Override
    public int hashCode() {
        return this.courseId.hashCode();
    }

    @Override
    public String toString() {
        return this.courseId.toString();
    }

    @Override
    public int compareTo(CourseId o) {
        return courseId.compareTo(o.courseId);
    }
}
