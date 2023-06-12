package eapli.ecourse.boardmanagement.repositories;

import eapli.ecourse.boardmanagement.domain.Board;
import eapli.ecourse.boardmanagement.domain.BoardTitle;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*public interface BoardRepository extends DomainRepository<Long, Board> {
    /**
     * Persist board.
     * @param board
     * @return Board
     */
/*
    Board save(Board board);

    /**
     * Get Board with Identity (board id).
     * @param id Board id
     * @return Board
     */
/*
    Optional<Board> ofIdentity(Long id);

    /**
     * Get boards that user have permission.
     * @param user user
     * @return List<Board>
     */
/*
    Iterable<Board> getBoardsByUser(SystemUser user);
}*/

/* Repositorio temporario, persistencia por fazer */

public class BoardRepository {

    private final List<Board> board = new ArrayList<>();

    public Board add(Board board) {
        this.board.add(board);
        return board;
    }

    public List<Board> getSchClassList() {
        return this.board;
    }
    
    //temp, should find by sql
    public Board getBoardByTitle(String title)
    {
        for (Board b : board)
        {
            if (b.boardTitle().value().equals(title))
            {
                return b;
            }
        }
        
        return null;
    }
}