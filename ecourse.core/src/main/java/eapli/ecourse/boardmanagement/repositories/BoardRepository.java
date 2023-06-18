package eapli.ecourse.boardmanagement.repositories;

import eapli.ecourse.boardmanagement.newdomain.Board;
import eapli.ecourse.boardmanagement.newdomain.BoardPermission;
import eapli.ecourse.boardmanagement.newdomain.BoardTitle;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.State;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public interface BoardRepository extends DomainRepository<BoardTitle, Board> {
    Board getBoardByTitle(BoardTitle title);
    Iterable<Board> findByOwner(SystemUser boardOwner);
    Board findByBoardPermission(BoardPermission permission);
}
