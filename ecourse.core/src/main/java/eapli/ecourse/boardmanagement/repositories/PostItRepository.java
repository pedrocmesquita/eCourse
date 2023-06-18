package eapli.ecourse.boardmanagement.repositories;

import eapli.ecourse.boardmanagement.newdomain.Board;
import eapli.ecourse.boardmanagement.newdomain.BoardCell;
import eapli.ecourse.boardmanagement.newdomain.Log;
import eapli.ecourse.boardmanagement.newdomain.PostIt;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public interface PostItRepository extends DomainRepository<Long, PostIt> {
    PostIt getPostItByBoardCell(BoardCell cell);

    Iterable<PostIt> getPostItsByBoard(Board selectedBoard);

    Iterable<PostIt> getPostItsByOwner(Board selectedBoard, SystemUser user);
}
