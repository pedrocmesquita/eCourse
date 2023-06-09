package eapli.ecourse.exammanagement.domain;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.usertypemanagement.studentusermanagement.domain.StudentUser;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;

@Entity
public class ExamsInCourse implements AggregateRoot<ExamsInCourseKey>
{
    @EmbeddedId
    ExamsInCourseKey id;

    @ManyToOne
    @MapsId("examId")
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @ManyToOne
    @MapsId("teacherId")
    @JoinColumn(name = "teacher_id")
    private Course course;

    protected ExamsInCourse()
    {
        //ORM only
    }

    public ExamsInCourse(Exam exam, Course course)
    {
        Preconditions.noneNull(exam, course);
        
        this.exam = exam;
        this.course = course;
        id = new ExamsInCourseKey(exam.getTitle(), course.name());
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
        if (! (other instanceof ExamsInCourse))
        {
            return false;
        }

        final ExamsInCourse that = (ExamsInCourse) other;
        if (this == that)
        {
            return true;
        }

        return exam.equals(that.exam) && course.equals(that.course);
    }

    @Override
    public ExamsInCourseKey identity()
    {
        return id;
    }

    public Exam exam()
    {
        return exam;
    }

    public Course course()
    {
        return course;
    }
}
