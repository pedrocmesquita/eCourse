package eapli.ecourse.exammanagement.application;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.exammanagement.domain.Exam;
import eapli.ecourse.exammanagement.domain.Grade;
import eapli.ecourse.exammanagement.repositories.ExamsInCourseRepository;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usermanagement.domain.BaseRoles;
import eapli.ecourse.usertypemanagement.teacherusermanagement.domain.TeacherUser;
import eapli.ecourse.usertypemanagement.teacherusermanagement.repositories.TeacherUserRepository;
import eapli.ecourse.usertypemanagement.teacherusermanagement.repositories.TeachersInCourseRepository;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.*;

@UseCaseController
public class ListTeacherExamGradesController
{
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final TeachersInCourseRepository teachersInCourseRepository = PersistenceContext.repositories().teachersInCourse();
    private final ExamsInCourseRepository examsInCourseRepository = PersistenceContext.repositories().examsInCourse();
    private final TeacherUserRepository teacherUserRepository = PersistenceContext.repositories().teacherUsers();
    
    public Iterable<Exam> findAllTeacherExams() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.POWER_USER, BaseRoles.TEACHER);
        List<Exam> teacherExams = new ArrayList<>();

        for (Course x : teachersInCourseRepository.findAllCoursesTeacherIsAssign(getUserAcronym().acronym())) {
            System.out.println(x.name().toString());
            for (Exam e : x.getExams()) {
                System.out.println(e.toString());
                for (Grade g : e.getGrades()) {
                    System.out.println(g.identity().toString());
                }
                teacherExams.add(e);
            }
        }
        return teacherExams;
    }
    
    public TeacherUser getUserAcronym() {
        Optional<UserSession> session = authz.session();
        SystemUser user = session.get().authenticatedUser();
        return teacherUserRepository.getTeacherUserFromSystemUser(user);
    }
}
