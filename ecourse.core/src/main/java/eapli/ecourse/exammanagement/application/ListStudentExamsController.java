package eapli.ecourse.exammanagement.application;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.exammanagement.domain.Exam;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usertypemanagement.studentusermanagement.domain.StudentUser;
import eapli.ecourse.usertypemanagement.studentusermanagement.repositories.ClientUserRepository;
import eapli.ecourse.usertypemanagement.studentusermanagement.repositories.StudentsInCourseRepository;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.*;

@UseCaseController
public class ListStudentExamsController
{
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ClientUserRepository clientUserRepository = PersistenceContext.repositories().clientUsers();
    private final StudentsInCourseRepository studentsInCourseRepository = PersistenceContext.repositories().studentsInCourse();

    public Iterable<Course> userCourses(){
        StudentUser studentUser = clientUserRepository.findByUsername(authz.session().get().authenticatedUser().username()).get();
        Iterable<Course> studentCourses = studentsInCourseRepository.findAllCoursesStudentIsAssign(studentUser.mecanographicNumber());
        return studentCourses;
    }

    public Iterable<Exam> listExams(Iterable<Course> courses) {
        Set<Exam> commonExams = null;
        boolean firstCourse = true;

        for (Course course : courses) {
            Set<Exam> exams = course.getExams();

            if (firstCourse) {
                commonExams = new HashSet<>(exams);
                firstCourse = false;
            } else {
                commonExams.retainAll(exams);
            }
        }

        return commonExams;
    }
}
