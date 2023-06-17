package eapli.ecourse.boardmanagement.repositories;

import eapli.ecourse.boardmanagement.newdomain.Board;
import eapli.ecourse.boardmanagement.newdomain.BoardTitle;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.State;
import eapli.framework.domain.repositories.DomainRepository;

public interface BoardRepository extends DomainRepository<BoardTitle, Board> {
    Board getBoardByTitle(BoardTitle title);
}
