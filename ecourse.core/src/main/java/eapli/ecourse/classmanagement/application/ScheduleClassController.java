package eapli.ecourse.classmanagement.application;

import eapli.ecourse.coursemanagement.domain.ClassBuilder;
import eapli.ecourse.coursemanagement.domain.SchClass;
import eapli.ecourse.coursemanagement.repositories.ClassRepository;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usermanagement.domain.BaseRoles;
import eapli.ecourse.usertypemanagement.teacherusermanagement.repositories.TeacherUserRepository;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.Calendar;

@UseCaseController
public class ScheduleClassController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ClassRepository classRepository = PersistenceContext.repositories().classes();
    private final TeacherUserRepository teacherUserRepository = PersistenceContext.repositories().teacherUsers();

    public SchClass scheduleClass(String className, int instructor, Calendar startTime, Calendar endTime) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN,BaseRoles.POWER_USER,BaseRoles.TEACHER);

        final ClassBuilder classbuilder = new ClassBuilder();
        classbuilder.withCourse(className).withInstructor(instructor).withDates(startTime, endTime);

        SchClass classs = classbuilder.build();
        return this.classRepository.save(classs);
    }
}
