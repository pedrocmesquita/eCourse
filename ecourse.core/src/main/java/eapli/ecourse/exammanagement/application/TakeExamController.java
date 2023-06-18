package eapli.ecourse.exammanagement.application;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.StudentsInCourse;
import eapli.ecourse.exammanagement.domain.Exam;
import eapli.ecourse.exammanagement.domain.Grade;
import eapli.ecourse.exammanagement.repositories.ExamRepository;
import eapli.ecourse.exammanagement.repositories.GradeRepository;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usermanagement.domain.BaseRoles;
import eapli.ecourse.usertypemanagement.studentusermanagement.domain.MecanographicNumber;
import eapli.ecourse.usertypemanagement.studentusermanagement.domain.StudentUser;
import eapli.ecourse.usertypemanagement.studentusermanagement.repositories.StudentsInCourseRepository;
import eapli.ecourse.usertypemanagement.teacherusermanagement.domain.Acronym;
import eapli.ecourse.usertypemanagement.teacherusermanagement.repositories.TeachersInCourseRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.Optional;

public class TakeExamController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final StudentsInCourseRepository studentsInCourseRepository = PersistenceContext.repositories().studentsInCourse();
    private final ExamRepository examRepository = PersistenceContext.repositories().exams();
    private final GradeRepository gradeRepository = PersistenceContext.repositories().grades();

    public void takeExam(Exam exam, Grade grade) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.STUDENT);
        System.out.println("Exam: " + exam);
        System.out.println("Grade: " + grade);
        exam.addGrade(grade);
        examRepository.save(exam);
        gradeRepository.save(grade);
    }
    public Iterable<Course> allCoursesStudentIsAssigned(MecanographicNumber mecanographicNumber){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.STUDENT);
        return studentsInCourseRepository.findAllCoursesStudentIsAssign(mecanographicNumber);
    }

    public MecanographicNumber getUser(){
        Optional<UserSession> session = authz.session();
        if(session.isEmpty())
            throw new IllegalArgumentException("No user authentication.");
        SystemUser user = session.get().authenticatedUser();
        if(!user.roleTypes().contains(BaseRoles.STUDENT))
            throw new IllegalArgumentException("User must be a student!");
        return studentsInCourseRepository.getStudentUserFromSystemUser(user).mecanographicNumber();
    }
    public StudentUser getUser2(){
        Optional<UserSession> session = authz.session();
        if(session.isEmpty())
            throw new IllegalArgumentException("No user authentication.");
        SystemUser user = session.get().authenticatedUser();
        if(!user.roleTypes().contains(BaseRoles.STUDENT))
            throw new IllegalArgumentException("User must be a student!");
        return studentsInCourseRepository.getStudentUserFromSystemUser(user);
    }

    public Iterable<Exam> listCourseExams(Course course) {
        return course.getExams();
    }

}
