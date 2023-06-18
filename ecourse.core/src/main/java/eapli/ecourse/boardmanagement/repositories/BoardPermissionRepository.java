package eapli.ecourse.boardmanagement.repositories;

import eapli.ecourse.boardmanagement.newdomain.AccessLevel;
import eapli.ecourse.boardmanagement.newdomain.BoardCell;
import eapli.ecourse.boardmanagement.newdomain.BoardPermission;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public interface BoardPermissionRepository extends DomainRepository<Long, BoardPermission> {
    Iterable<BoardPermission> findBySystemUserAccessLevel(SystemUser user, AccessLevel accessLevel);
}
