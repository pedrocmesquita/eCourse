package eapli.ecourse.classmanagement.application;

import eapli.ecourse.classmanagement.domain.SchClass;
import eapli.ecourse.classmanagement.repositories.ClassRepository;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.Calendar;

public class ScheduleClassController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ClassRepository classRepository = PersistenceContext.repositories().classes();

    public SchClass scheduleClass(String className, Calendar startTime, Calendar endTime) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.TEACHER);

        SchClass classs = new SchClass(className, startTime, endTime);

        return this.classRepository.save(classs);
    }
}