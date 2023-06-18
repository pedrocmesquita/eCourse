package eapli.ecourse.persistence.impl.jpa;

import eapli.ecourse.Application;
import eapli.ecourse.boardmanagement.newdomain.Log;
import eapli.ecourse.boardmanagement.repositories.LogRepository;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.State;
import eapli.ecourse.exammanagement.domain.Grade;
import eapli.ecourse.exammanagement.repositories.GradeRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;

public class JpaGradeRepository extends JpaAutoTxRepository<Grade, Double, Long> implements GradeRepository {
    public JpaGradeRepository(final TransactionalContext autoTx) {
        super(autoTx, "gradeId");
    }

    public JpaGradeRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "gradeId");
    }

    /*@Override
    public Iterable<Grade> findGradeByCourse(Course course) {
        final TypedQuery<Grade> query = entityManager().createQuery(
                "SELECT c FROM Grade c WHERE c. = :stateParam", .class);
        query.setParameter("stateParam", state);
        return query.getResultList();
    }

     */

}
