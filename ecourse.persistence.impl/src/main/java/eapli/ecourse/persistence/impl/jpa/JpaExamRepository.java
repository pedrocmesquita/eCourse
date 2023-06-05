package eapli.ecourse.persistence.impl.jpa;

import eapli.ecourse.Application;
import eapli.ecourse.exammanagement.domain.Exam;
import eapli.ecourse.exammanagement.repositories.ExamRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaExamRepository
        extends JpaAutoTxRepository<Exam, Designation, Designation>
        implements ExamRepository {

    public JpaExamRepository(final TransactionalContext autoTx) {
        super(autoTx, "title");
    }

    public JpaExamRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "courseId");
    }
}
