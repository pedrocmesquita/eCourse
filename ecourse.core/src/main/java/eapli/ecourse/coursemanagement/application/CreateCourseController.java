package eapli.ecourse.coursemanagement.application;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseBuilder;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import org.springframework.transaction.annotation.Transactional;

@UseCaseController
public class CreateCourseController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CourseRepository courseRepository = PersistenceContext.repositories().courses();

    @Transactional
    public Course createCourse(String name, String description) {
        return createCourse(name, description,null, null);
    }

    @Transactional
    public Course createCourse(String name, String description, Integer minEnroll, Integer maxEnroll) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN);

        final CourseBuilder courseBuilder = new CourseBuilder();
        courseBuilder.withName(name).withDescription(description).withEnrollLimit(minEnroll, maxEnroll);
        Course course = courseBuilder.build();

        return this.courseRepository.save(course);
    }
}
