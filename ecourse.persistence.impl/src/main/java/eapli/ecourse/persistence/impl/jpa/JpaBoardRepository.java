package eapli.ecourse.persistence.impl.jpa;

import eapli.ecourse.Application;
import eapli.ecourse.boardmanagement.newdomain.Board;
import eapli.ecourse.boardmanagement.newdomain.BoardPermission;
import eapli.ecourse.boardmanagement.newdomain.BoardTitle;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;

public class JpaBoardRepository extends JpaAutoTxRepository<Board, Long, BoardTitle> implements BoardRepository {

    public JpaBoardRepository(final TransactionalContext autoTx) {
        super(autoTx, "boardId");
    }

    public JpaBoardRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "boardId");
    }
    @Override
    public Board findByBoardPermission(BoardPermission permission) {
        final TypedQuery<Board> query = entityManager().createQuery(
                "SELECT b FROM Board b WHERE b.boardPermissions = :boardPermissionParam", Board.class);
        query.setParameter("boardPermissionParam", permission);
        return query.getSingleResult();
    }
    @Override
    public Board getBoardByTitle(BoardTitle title) {
        final TypedQuery<Board> query = entityManager().createQuery(
                "SELECT b FROM Board b WHERE b.boardTitle = :boardTitleParam", Board.class);
        query.setParameter("boardTitleParam", title);
        return query.getSingleResult();
    }

    @Override
    public Iterable<Board> findByOwner(SystemUser boardOwner) {
        final TypedQuery<Board> query = entityManager().createQuery(
                "SELECT b FROM Board b WHERE b.boardOwner = :boardOwnerParam", Board.class);
        query.setParameter("boardOwnerParam", boardOwner);
        return query.getResultList();
    }

}