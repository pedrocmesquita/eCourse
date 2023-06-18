package eapli.ecourse.boardmanagement.repositories;

import eapli.ecourse.boardmanagement.newdomain.BoardCell;
import eapli.ecourse.boardmanagement.newdomain.BoardCol;
import eapli.ecourse.boardmanagement.newdomain.BoardRow;
import eapli.ecourse.boardmanagement.newdomain.PostIt;
import eapli.framework.domain.repositories.DomainRepository;

public interface BoardCellRepository extends DomainRepository<Long, BoardCell> {
    BoardCell getBoardCellByRowAndCol(BoardRow row, BoardCol col, PostIt post);

}
