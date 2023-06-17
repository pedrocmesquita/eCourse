package eapli.ecourse.coursemanagement.domain;

import eapli.ecourse.usertypemanagement.studentusermanagement.domain.MecanographicNumber;
import eapli.ecourse.usertypemanagement.teacherusermanagement.domain.Acronym;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class StudentsInCourseKey implements Serializable, Comparable<StudentsInCourseKey> {

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "student_id")
    private MecanographicNumber studentId;

    protected StudentsInCourseKey() {
        //ORM only
    }

    protected StudentsInCourseKey(Long courseId, MecanographicNumber studentId) {
        this.courseId = courseId;
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentsInCourseKey that = (StudentsInCourseKey) o;
        return Objects.equals(studentId, that.studentId) && Objects.equals(courseId, that.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId);
    }

    @Override
    public int compareTo(StudentsInCourseKey o) {
        return courseId.compareTo(o.courseId) & studentId.compareTo(o.studentId);
    }
}
