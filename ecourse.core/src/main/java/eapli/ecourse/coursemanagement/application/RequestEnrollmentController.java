package eapli.ecourse.coursemanagement.application;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseBuilder;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import javax.naming.Name;
import java.util.Objects;

@UseCaseController
public class RequestEnrollmentController
{
    private final ListCourseService service = new ListCourseService();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CourseRepository courseRepository = PersistenceContext.repositories().courses();
   // final CourseBuilder courseBuilder = new CourseBuilder();
    
    public void attemptEnroll(String name)
    {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.STUDENT);
        for (Course x : courseRepository.findAllCoursesOpenOrEnrollState())
        {
            if (Objects.equals(x.name().toString(), name))
            {
                /*
                if (x.enrollLimit().getMaxEnroll())
                {
                }
                */
                return;
            }
        }
        
        //no course with matching name found
        throw new IllegalArgumentException();
    }
}
