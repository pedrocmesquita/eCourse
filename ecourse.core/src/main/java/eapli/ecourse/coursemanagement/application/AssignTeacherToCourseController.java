package eapli.ecourse.coursemanagement.application;

import eapli.ecourse.coursemanagement.application.ListCourseService;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.TeachersInCourse;
import eapli.ecourse.usertypemanagement.teacherusermanagement.application.ListTeachersService;
import eapli.ecourse.usertypemanagement.teacherusermanagement.repositories.TeachersInCourseRepository;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usermanagement.domain.BaseRoles;
import eapli.ecourse.usertypemanagement.teacherusermanagement.domain.TeacherUser;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

@UseCaseController
public class AssignTeacherToCourseController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final TeachersInCourseRepository teachersInCourseRepository = PersistenceContext.repositories().teachersInCourse();
    private final ListCourseService courseService = new ListCourseService();
    private final ListTeachersService teachersService = new ListTeachersService();


    public TeachersInCourse assignTeacherToCourse(TeacherUser teacher, Course course) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN);
        TeachersInCourse teacherInCourse = new TeachersInCourse(course, teacher);
        teachersInCourseRepository.save(teacherInCourse);
        return teacherInCourse;
    }

    public Iterable<Course> allNotClosedCourses() {
        return courseService.allNotClosedCourses();
    }

    public Iterable<TeacherUser> allTeachersJoinWithSystemUser() {
        return teachersService.allTeachersJoinWithSystemUser();
    }

}
