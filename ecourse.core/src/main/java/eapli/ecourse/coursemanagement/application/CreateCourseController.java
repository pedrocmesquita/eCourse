package eapli.ecourse.coursemanagement.application;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseBuilder;
import eapli.ecourse.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class CreateCourseController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    public Course createCourse(String name, String description) {
        return createCourse(name, description,null, null);
    }

    public Course createCourse(String name, String description, Integer minEnroll, Integer maxEnroll) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN);
        final CourseBuilder courseBuilder = new CourseBuilder();
        courseBuilder.withName(name).withDescription(description).withEnrollLimit(minEnroll, maxEnroll);
        //TODO repo
        return courseBuilder.build();
    }
}
