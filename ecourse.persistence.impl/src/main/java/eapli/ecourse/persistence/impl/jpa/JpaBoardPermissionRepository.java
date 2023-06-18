package eapli.ecourse.persistence.impl.jpa;

import eapli.ecourse.Application;
import eapli.ecourse.boardmanagement.newdomain.AccessLevel;
import eapli.ecourse.boardmanagement.newdomain.BoardCell;
import eapli.ecourse.boardmanagement.newdomain.BoardPermission;
import eapli.ecourse.boardmanagement.repositories.BoardCellRepository;
import eapli.ecourse.boardmanagement.repositories.BoardPermissionRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;

public class JpaBoardPermissionRepository extends JpaAutoTxRepository<BoardPermission, SystemUser, Long> implements BoardPermissionRepository {

    public JpaBoardPermissionRepository(final TransactionalContext autoTx) {
        super(autoTx, "cellId");
    }

    public JpaBoardPermissionRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "cellId");
    }

    @Override
    public Iterable<BoardPermission> findBySystemUserAccessLevel(SystemUser user, AccessLevel accessLevel) {
        final TypedQuery<BoardPermission> query = entityManager().createQuery(
                "SELECT bp FROM BoardPermission bp WHERE bp.SystemUser = :systemUserParam AND bp.accessLevel = :accessLevelParam", BoardPermission.class);
        query.setParameter("systemUserParam", user);
        query.setParameter("accessLevelParam", accessLevel);
        return query.getResultList();
    }
}