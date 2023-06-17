package eapli.ecourse.persistence.impl.jpa;

import eapli.ecourse.Application;
import eapli.ecourse.boardmanagement.newdomain.BoardCell;
import eapli.ecourse.boardmanagement.newdomain.Log;
import eapli.ecourse.boardmanagement.repositories.BoardCellRepository;
import eapli.ecourse.boardmanagement.repositories.LogRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaBoardCellRepository extends JpaAutoTxRepository<BoardCell, Long, Long> implements BoardCellRepository {

    public JpaBoardCellRepository(final TransactionalContext autoTx) {
        super(autoTx, "cellId");
    }

    public JpaBoardCellRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "cellId");
    }
}