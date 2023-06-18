package eapli.ecourse.persistence.impl.jpa;

import eapli.ecourse.Application;
import eapli.ecourse.boardmanagement.newdomain.Board;
import eapli.ecourse.boardmanagement.newdomain.BoardCell;
import eapli.ecourse.boardmanagement.newdomain.Log;
import eapli.ecourse.boardmanagement.newdomain.PostIt;
import eapli.ecourse.boardmanagement.repositories.LogRepository;
import eapli.ecourse.boardmanagement.repositories.PostItRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;

public class JpaPostItRepository extends JpaAutoTxRepository<PostIt, Long, Long> implements PostItRepository {
    public JpaPostItRepository(final TransactionalContext autoTx) {
        super(autoTx, "logId");
    }

    public JpaPostItRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "logId");
    }

    @Override
    public PostIt getPostItByBoardCell(BoardCell cell) {
        final TypedQuery<PostIt> query = entityManager().createQuery(
                "SELECT b FROM PostIt b WHERE b.cell = :boardCellParam", PostIt.class);
        query.setParameter("boardCellParam", cell);
        return query.getSingleResult();
    }

    @Override
    public Iterable<PostIt> getPostItsByBoard(Board selectedBoard) {
        final TypedQuery<PostIt> query = entityManager().createQuery(
                "SELECT b FROM PostIt b WHERE b.board = :boardParam", PostIt.class);
        query.setParameter("boardParam", selectedBoard);
        return query.getResultList();
    }

    @Override
    public Iterable<PostIt> getPostItsByOwner(Board selectedBoard, SystemUser user) {
        final TypedQuery<PostIt> query = entityManager().createQuery(
                "SELECT b FROM PostIt b WHERE b.board = :boardParam AND b.owner = :userParam", PostIt.class);
        query.setParameter("boardParam", selectedBoard);
        query.setParameter("userParam", user);
        return query.getResultList();
    }
}