package eapli.ecourse.coursemanagement.domain;

import eapli.ecourse.usertypemanagement.studentusermanagement.domain.StudentUser;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;

@Entity
public class StudentsInExam implements AggregateRoot<StudentsInExamKey>
{
    
    @EmbeddedId
    StudentsInExamKey id;
    
    @ManyToOne
    @MapsId("examId")
    @JoinColumn(name = "exam_id")
    private Exam exam;
    
    @ManyToOne
    @MapsId("teacherId")
    @JoinColumn(name = "teacher_id")
    private StudentUser student;
    
    protected StudentsInExam()
    {
        //ORM only
    }
    
    public StudentsInExam(Exam exam, StudentUser student)
    {
        Preconditions.noneNull(exam, student);
        if (exam.getState().equals(State.CLOSED))
        {
            throw new IllegalStateException("To assign students, the exam cannot be closed.");
        }
        this.exam = exam;
        this.student = student;
        id = new StudentsInExamKey(exam.getTitle(), student.mecanographicNumber());
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
    public boolean sameAs(Object other)
    {
        if (! (other instanceof StudentsInExam))
        {
            return false;
        }
        
        final StudentsInExam that = (StudentsInExam) other;
        if (this == that)
        {
            return true;
        }
        
        return exam.equals(that.exam) && student.equals(that.student);
    }
    
    @Override
    public StudentsInExamKey identity()
    {
        return id;
    }
    
    public Exam exam()
    {
        return exam;
    }
    
    public StudentUser student()
    {
        return student;
    }
}
