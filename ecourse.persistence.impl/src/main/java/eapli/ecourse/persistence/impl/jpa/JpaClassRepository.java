package eapli.ecourse.persistence.impl.jpa;

import eapli.ecourse.Application;
import eapli.ecourse.classmanagement.domain.SchClass;
import eapli.ecourse.classmanagement.repositories.ClassRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;
import java.util.Calendar;

public class JpaClassRepository extends JpaAutoTxRepository<SchClass, Long, Calendar> implements ClassRepository {

    public JpaClassRepository(final TransactionalContext autoTx) {
        super(autoTx, "courseId");
    }

    public JpaClassRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "courseId");
    }

}