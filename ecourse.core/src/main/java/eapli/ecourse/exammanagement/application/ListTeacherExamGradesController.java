package eapli.ecourse.exammanagement.application;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.exammanagement.domain.Exam;
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

import java.util.Iterator;
import java.util.Optional;

@UseCaseController
public class ListTeacherExamGradesController
{
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final TeachersInCourseRepository teachersInCourseRepository = PersistenceContext.repositories().teachersInCourse();
    private final ExamsInCourseRepository examsInCourseRepository = PersistenceContext.repositories().examsInCourse();
    private final TeacherUserRepository teacherUserRepository = PersistenceContext.repositories().teacherUsers();
    
    public Iterable<Exam> findAllTeacherExams()
    {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.POWER_USER, BaseRoles.TEACHER);
        
        for (Course x : teachersInCourseRepository.findAllCoursesTeacherIsAssign(getUserAcronym().acronym()))
        {
            for (Exam e : examsInCourseRepository.findAllExamsInCourse(x.name()))
            {
                //return iterable of exam grades
            }
        }
        
        return null;
    }
    
    public TeacherUser getUserAcronym()
    {
        Optional<UserSession> session = authz.session();
        SystemUser user = session.get().authenticatedUser();
        return teacherUserRepository.getTeacherUserFromSystemUser(user);
    }
}
