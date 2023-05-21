package eapli.ecourse.coursemanagement.domain;

import eapli.ecourse.usertypemanagement.teacherusermanagement.domain.TeacherUser;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;

@Entity
public class TeachersInCourse implements AggregateRoot<TeachersInCourseKey> {

    @EmbeddedId
    TeachersInCourseKey id;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @MapsId("teacherId")
    @JoinColumn(name = "teacher_id")
    private TeacherUser teacher;

    private boolean isInCharge;

    protected TeachersInCourse() {
        //ORM only
    }

    public TeachersInCourse(Course course, TeacherUser teacher) {
        Preconditions.noneNull(course, teacher);
        if(course.state().equals(State.CLOSED)) {
            throw new IllegalStateException("To assign teachers, course cannot be closed");
        }
        this.course = course;
        this.teacher = teacher;
        id = new TeachersInCourseKey(course.courseId(), teacher.acronym());
        this.isInCharge = false;
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
        if (!(other instanceof TeachersInCourse)) {
            return false;
        }

        final TeachersInCourse that = (TeachersInCourse) other;
        if (this == that) {
            return true;
        }

        return course.equals(that.course) && teacher.equals(that.teacher);
    }

    @Override
    public TeachersInCourseKey identity() {
        return id;
    }

    public Course course() {
        return course;
    }

    public TeacherUser teacher() {
        return teacher;
    }

    public boolean isInCharge() {
        return isInCharge;
    }
}
