package eapli.ecourse.persistence.impl.jpa;

import eapli.ecourse.Application;
import eapli.ecourse.coursemanagement.domain.*;
import eapli.ecourse.usertypemanagement.studentusermanagement.domain.MecanographicNumber;
import eapli.ecourse.usertypemanagement.studentusermanagement.repositories.StudentsInCourseRepository;
import eapli.ecourse.usertypemanagement.teacherusermanagement.domain.Acronym;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;

public class JpaStudentsInCourseRepository
        extends JpaAutoTxRepository<StudentsInCourse, StudentsInCourseKey, StudentsInCourseKey>
        implements StudentsInCourseRepository {

    public JpaStudentsInCourseRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    public JpaStudentsInCourseRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "id");
    }

    @Override
    public Iterable<Course> findAllCoursesStudentIsAssign(MecanographicNumber mecanographicNumber) {
        String jpql = "SELECT c FROM Course c " +
                "JOIN StudentsInCourse tic ON c.courseId = tic.course.id " +
                "WHERE tic.student.id = :studentId";

        TypedQuery<Course> query = entityManager().createQuery(jpql, Course.class);
        query.setParameter("studentId", mecanographicNumber);

        return query.getResultList();
    }

}
