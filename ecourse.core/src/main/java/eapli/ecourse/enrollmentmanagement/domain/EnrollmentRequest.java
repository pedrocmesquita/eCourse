package eapli.ecourse.enrollmentmanagement.domain;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.State;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class EnrollmentRequest implements AggregateRoot<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "student_email")
    private SystemUser student;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "course_code")
    private Course course;

    private LocalDate DateOfRequest;

    private LocalDate ReplyDate; // só criada depois de aceitar ou rejeitar o request
    @Enumerated(EnumType.STRING)
    private RequestState state;
    protected EnrollmentRequest() {
    }
    private EnrollmentRequest(final SystemUser student, final Course course) {
        this.student = student;
        this.course = course;
        this.DateOfRequest = LocalDate.now();
        this.state = RequestState.PENDING;
        this.ReplyDate = null;
    }
    public static EnrollmentRequest create(final SystemUser student, final Course course) {
        Preconditions.noneNull(student, course);
        Preconditions.ensure(course.state().equals(State.ENROLL), "This course is not available for enrollment");
        /*
        Preconditions.ensure(
                course.hasStudent(student),
                "This student is already enrolled in this course"
        );
        Preconditions.ensure(
                course.limitOfStudents() == course.students().size(),
                "This course is already full"
        );
        */

        return new EnrollmentRequest(student, course);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EnrollmentRequest that = (EnrollmentRequest) o;

        if (!Objects.equals(student, that.student)) {
            return false;
        }
        return Objects.equals(course, that.course);
    }

    @Override
    public boolean sameAs(final Object other) {
        EnrollmentRequest that = (EnrollmentRequest) other;

        if (!Objects.equals(student, that.student)) {
            return false;
        }
        return Objects.equals(course, that.course);
    }

    public void accept() {
        Preconditions.ensure(!this.state.equals(RequestState.ACCEPTED), "This request has already been accepted.");
        this.state = RequestState.ACCEPTED;
        this.ReplyDate = LocalDate.now();
        //this.course.addStudent(this.student);
    }

    public void reject() {
        Preconditions.ensure(!this.state.equals(RequestState.REJECTED), "This request has already been rejected.");
        this.state = RequestState.REJECTED;
        this.ReplyDate = LocalDate.now();
    }
    @Override
    public Long identity() {
        return id;
    }
    public SystemUser student() {
        return student;
    }

    public Course course() {
        return course;
    }

    @Override
    public String toString() {
        return "Request " + this.id + "\n"
                + "Student: " + this.student.email() + "\n"
                + "Course: " + this.course.courseId() + "\n";
    }
    @Override
    public int hashCode() {
        final int hashConstant = 31;
        int result = student.hashCode();
        result = hashConstant * result + course.hashCode();
        return result;
    }

    public RequestState state() {
        return state;
    }
}