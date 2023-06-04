package eapli.ecourse.coursemanagement.domain;

import eapli.ecourse.usertypemanagement.studentusermanagement.domain.MecanographicNumber;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

/**
 * Composite primary key of studentInexam
 */
public class StudentsInExamKey implements Serializable, Comparable<StudentsInExamKey>
{
    
    @Column(name = "exam_id")
    private Name examId;
    
    @Column(name = "student_id")
    private MecanographicNumber studentId;
    
    protected StudentsInExamKey()
    {
        //ORM only
    }
    
    protected StudentsInExamKey(Name examId, MecanographicNumber studentId)
    {
        this.examId = examId;
        this.studentId = studentId;
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
        StudentsInExamKey that = (StudentsInExamKey) o;
        return Objects.equals(studentId, that.studentId) && Objects.equals(examId, that.examId);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(studentId, examId);
    }
    
    @Override
    public int compareTo(StudentsInExamKey o)
    {
        return examId.compareTo(o.examId) & studentId.compareTo(o.studentId);
    }
}
