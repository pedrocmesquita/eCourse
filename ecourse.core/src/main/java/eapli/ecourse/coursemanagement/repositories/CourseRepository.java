package eapli.ecourse.coursemanagement.repositories;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.Name;
import eapli.ecourse.coursemanagement.domain.State;
import eapli.framework.domain.repositories.DomainRepository;

public interface CourseRepository extends DomainRepository<Name, Course> {

    /**
     * Finds all courses with state passed as parameter
     *
     * @param state
     * @return
     */
    Iterable<Course> findAllCoursesWithState(State state);

    /**
     * Finds all courses that don't have state passed as parameter
     *
     * @param state
     * @return
     */
    Iterable<Course> findAllCoursesWithOtherState(State state);
}
