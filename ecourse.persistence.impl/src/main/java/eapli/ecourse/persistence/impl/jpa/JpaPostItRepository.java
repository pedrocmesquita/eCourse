package eapli.ecourse.persistence.impl.jpa;

import eapli.ecourse.Application;
import eapli.ecourse.boardmanagement.newdomain.Board;
import eapli.ecourse.boardmanagement.newdomain.Log;
import eapli.ecourse.boardmanagement.newdomain.PostIt;
import eapli.ecourse.boardmanagement.repositories.LogRepository;
import eapli.ecourse.boardmanagement.repositories.PostItRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaPostItRepository extends JpaAutoTxRepository<PostIt, Long, Long> implements PostItRepository {
    public JpaPostItRepository(final TransactionalContext autoTx) {
        super(autoTx, "logId");
    }

    public JpaPostItRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "logId");
    }
}