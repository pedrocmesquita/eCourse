package eapli.ecourse.coursemanagement.domain;

import eapli.ecourse.usertypemanagement.teacherusermanagement.domain.Acronym;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

/**
 * Composite primary key of TeacherInCourse
 */
public class TeachersInCourseKey implements Serializable, Comparable<TeachersInCourseKey> {

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "teacher_id")
    private Acronym teacherId;

    protected TeachersInCourseKey() {
        //ORM only
    }

    protected TeachersInCourseKey(Long courseId, Acronym teacherId) {
        this.courseId = courseId;
        this.teacherId = teacherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeachersInCourseKey that = (TeachersInCourseKey) o;
        return Objects.equals(teacherId, that.teacherId) && Objects.equals(courseId, that.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, courseId);
    }

    @Override
    public int compareTo(TeachersInCourseKey o) {
        return courseId.compareTo(o.courseId) & teacherId.compareTo(o.teacherId);
    }
}
