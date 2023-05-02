package eapli.ecourse.persistence.impl.jpa;

import eapli.ecourse.Application;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.Name;
import eapli.ecourse.coursemanagement.domain.State;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;

public class JpaCourseRepository extends JpaAutoTxRepository<Course, Long, Name> implements CourseRepository {

    public JpaCourseRepository(final TransactionalContext autoTx) {
        super(autoTx, "courseId");
    }

    public JpaCourseRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "courseId");
    }

    @Override
    public Iterable<Course> findAllCoursesWithState(State state) {
        final TypedQuery<Course> query = entityManager().createQuery(
                "SELECT c FROM Course c WHERE c.state = '" + state + "'", Course.class);

        return query.getResultList();
    }

    @Override
    public Iterable<Course> findAllCoursesWithOtherState(State state) {
        final TypedQuery<Course> query = entityManager().createQuery(
                "SELECT c FROM Course c WHERE NOT c.state = '" + state + "'", Course.class);

        return query.getResultList();
    }
}
