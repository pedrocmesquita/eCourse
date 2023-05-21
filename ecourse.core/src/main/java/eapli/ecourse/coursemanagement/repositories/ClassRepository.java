package eapli.ecourse.coursemanagement.repositories;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.Name;
import eapli.ecourse.coursemanagement.domain.SchClass;
import eapli.ecourse.coursemanagement.domain.State;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.ArrayList;
import java.util.List;

public interface ClassRepository extends DomainRepository<Name, SchClass> {

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
    Iterable<Course> findAllCoursesWithoutState(State state);

    /**
     * Finds all course in enroll or progress state
     *
     * @return
     */
    Iterable<Course> findAllCoursesOpenOrEnrollState();
}