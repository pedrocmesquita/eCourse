package eapli.ecourse.exammanagement.domain;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.usertypemanagement.studentusermanagement.domain.StudentUser;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.general.domain.model.Designation;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Grade implements AggregateRoot<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long gradeId;

    private Double grade;

    protected Grade() {
        //ORM
    }

    public Grade(Double grade){
        this.grade = grade;
    }

    @Override
    public Long identity() {
        return gradeId;
    }

    @Override
    public String toString() {
        return "ID: " + gradeId +
                "Grade: " + grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade1 = (Grade) o;
        return Objects.equals(gradeId, grade1.gradeId) && Objects.equals(grade, grade1.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gradeId, grade);
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public int compareTo(Long other) {
        return AggregateRoot.super.compareTo(other);
    }
}
