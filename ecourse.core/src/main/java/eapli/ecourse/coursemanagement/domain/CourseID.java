package eapli.ecourse.coursemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import org.apache.commons.lang3.builder.EqualsBuilder;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Embeddable
public class CourseID implements ValueObject, Comparable<CourseID> {

    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    public CourseID() {
        //ORM only
    }

    public CourseID(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CourseID courseID = (CourseID) o;

        return new EqualsBuilder().append(id, courseID.id).isEquals();
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public String toString() {
        return this.id.toString();
    }

    @Override
    public int compareTo(CourseID o) {
        return id.compareTo(o.id);
    }
}
