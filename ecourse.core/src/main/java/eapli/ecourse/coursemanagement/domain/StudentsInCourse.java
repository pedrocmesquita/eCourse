package eapli.ecourse.coursemanagement.domain;

import eapli.ecourse.usertypemanagement.studentusermanagement.domain.StudentUser;
import eapli.ecourse.usertypemanagement.teacherusermanagement.domain.TeacherUser;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;

@Entity
public class StudentsInCourse implements AggregateRoot<StudentsInCourseKey> {

    @EmbeddedId
    StudentsInCourseKey id;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private StudentUser student;

    protected StudentsInCourse() {
        //ORM only
    }

    public StudentsInCourse(Course course, StudentUser student) {
        Preconditions.noneNull(course, student);
        if(course.state().equals(State.CLOSED)) {
            throw new IllegalStateException("To assign teachers, course cannot be closed");
        }
        this.course = course;
        this.student = student;
        id = new StudentsInCourseKey(course.courseId(), student.mecanographicNumber());
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
        if (!(other instanceof StudentsInCourse)) {
            return false;
        }

        final StudentsInCourse that = (StudentsInCourse) other;
        if (this == that) {
            return true;
        }

        return course.equals(that.course) && student.equals(that.student);
    }

    @Override
    public StudentsInCourseKey identity() {
        return id;
    }

    public Course course() {
        return course;
    }

    public StudentUser student() {
        return student;
    }
}
