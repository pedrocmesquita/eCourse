package eapli.ecourse.persistence.impl.inmemory;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.Name;
import eapli.ecourse.coursemanagement.domain.State;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryCourseRepository extends InMemoryDomainRepository<Course, Name> implements CourseRepository {
    static {
        InMemoryInitializer.init();
    }

    //todo
    @Override
    public Iterable<Course> findAllCoursesWithState(State state) {
        return null;
    }

    @Override
    public Iterable<Course> findAllCoursesWithOtherState(State state) {
        return null;
    }

}
