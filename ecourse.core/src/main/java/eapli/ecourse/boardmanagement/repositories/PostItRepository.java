package eapli.ecourse.boardmanagement.repositories;

import eapli.ecourse.boardmanagement.newdomain.BoardCell;
import eapli.ecourse.boardmanagement.newdomain.Log;
import eapli.ecourse.boardmanagement.newdomain.PostIt;
import eapli.framework.domain.repositories.DomainRepository;

public interface PostItRepository extends DomainRepository<Long, PostIt> {
    PostIt getPostItByBoardCell(BoardCell cell);
}
