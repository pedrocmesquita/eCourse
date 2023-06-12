package eapli.ecourse.exammanagement.domain;

import eapli.ecourse.coursemanagement.domain.Name;
import eapli.ecourse.usertypemanagement.studentusermanagement.domain.MecanographicNumber;
import eapli.framework.general.domain.model.Designation;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

/**
 * Composite primary key of studentInexam
 */
public class ExamsInCourseKey implements Serializable, Comparable<ExamsInCourseKey>
{
    
    @Column(name = "exam_id")
    private Designation examId;
    
    @Column(name = "student_id")
    private Name courseId;
    
    protected ExamsInCourseKey()
    {
        //ORM only
    }
    
    protected ExamsInCourseKey(Designation examId, Name courseId)
    {
        this.examId = examId;
        this.courseId = courseId;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        ExamsInCourseKey that = (ExamsInCourseKey) o;
        return Objects.equals(courseId, that.courseId) && Objects.equals(examId, that.examId);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(courseId, examId);
    }
    
    @Override
    public int compareTo(ExamsInCourseKey o)
    {
        return examId.compareTo(o.examId) & courseId.compareTo(o.courseId);
    }
}
