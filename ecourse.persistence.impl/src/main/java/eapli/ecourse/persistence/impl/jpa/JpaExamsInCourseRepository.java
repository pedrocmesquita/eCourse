package eapli.ecourse.persistence.impl.jpa;

import eapli.ecourse.Application;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.Name;
import eapli.ecourse.exammanagement.domain.*;
import eapli.ecourse.exammanagement.repositories.ExamsInCourseRepository;
import eapli.ecourse.usertypemanagement.studentusermanagement.domain.MecanographicNumber;
import eapli.ecourse.usertypemanagement.studentusermanagement.repositories.ClientInExamRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;

public class JpaExamsInCourseRepository
        extends JpaAutoTxRepository<ExamsInCourse, ExamsInCourseKey, ExamsInCourseKey>
        implements ExamsInCourseRepository
{

    public JpaExamsInCourseRepository(final TransactionalContext autoTx)
    {
        super(autoTx, "id");
    }

    public JpaExamsInCourseRepository(final String puname)
    {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "id");
    }

    @Override
    public Iterable<Exam> findAllExamsInCourse(Name name) {
        String jpql = "SELECT e FROM Exam e " +
                "JOIN ExamsInCourse x ON e.examId = x.exam.id " +
                "WHERE x.course.id = :courseId";

        TypedQuery<Exam> query = entityManager().createQuery(jpql, Exam.class);
        query.setParameter("examId", name);

        return query.getResultList();
    }
}
