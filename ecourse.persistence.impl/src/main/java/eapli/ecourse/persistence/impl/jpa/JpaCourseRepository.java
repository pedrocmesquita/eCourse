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
                "SELECT c FROM Course c WHERE c.state = :stateParam", Course.class);
        query.setParameter("stateParam", state);
        return query.getResultList();
    }

    @Override
    public Iterable<Course> findAllCoursesWithoutState(State state) {
        final TypedQuery<Course> query = entityManager().createQuery(
                "SELECT c FROM Course c WHERE c.state != :stateParam", Course.class);
        query.setParameter("stateParam", state);
        return query.getResultList();
    }

    @Override
    public Iterable<Course> findAllCoursesOpenOrEnrollState() {
        final TypedQuery<Course> query = entityManager().createQuery(
                "SELECT c FROM Course c WHERE c.state = :stateParam1 OR c.state = :stateParam2", Course.class);
        query.setParameter("stateParam1", State.OPEN);
        query.setParameter("stateParam2", State.ENROLL);
        return query.getResultList();
    }

    @Override
    public Iterable<Course> findCoursesTeacherIsAssigned(String acronym) {
        final TypedQuery<Course> query = entityManager().createQuery(
                "SELECT c FROM Course c JOIN c.teachersInCourses t WHERE t.id = :teacherId", Course.class);
        query.setParameter("teacherId", acronym);
        return query.getResultList();
    }

}
