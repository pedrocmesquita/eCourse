package eapli.ecourse.persistence.impl.jpa;

import eapli.ecourse.Application;
import eapli.ecourse.boardmanagement.newdomain.Board;
import eapli.ecourse.boardmanagement.newdomain.BoardTitle;
import eapli.ecourse.boardmanagement.newdomain.Log;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.boardmanagement.repositories.LogRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;

public class JpaLogRepository extends JpaAutoTxRepository<Log, Long, Long> implements LogRepository {

    public JpaLogRepository(final TransactionalContext autoTx) {
        super(autoTx, "logId");
    }

    public JpaLogRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "logId");
    }
    public Iterable<Log> getLogByBoard(Board board) {
        final TypedQuery<Log> query = entityManager().createQuery(
                "SELECT l FROM Log l WHERE l.id = :boardParam", Log.class);
        query.setParameter("boardParam", board);
        return query.getResultList();
    }
}