package eapli.ecourse.persistence.impl.inmemory;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryCourseRepository extends InMemoryDomainRepository<Course, Long> implements CourseRepository {
    static {
        InMemoryInitializer.init();
    }
}
