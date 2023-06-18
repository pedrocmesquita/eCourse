package eapli.ecourse.boardmanagement.repositories;

import eapli.ecourse.boardmanagement.newdomain.BoardCell;
import eapli.framework.domain.repositories.DomainRepository;

public interface BoardCellRepository extends DomainRepository<Long, BoardCell> {
    BoardCell getBoardCellByRowAndCol(int row, int col);

}
