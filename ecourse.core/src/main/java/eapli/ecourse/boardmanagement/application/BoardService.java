package eapli.ecourse.boardmanagement.application;

import eapli.ecourse.boardmanagement.newdomain.*;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.Set;

public class BoardService {

    private final BoardRepository boardRepository;


    public BoardService(final BoardRepository boardRepo) {
        boardRepository = boardRepo;
    }

    public Board createBoard(final String boardTitlep,
                             final int boardNRowp,
                             final int boardNColp,
                             final Set<BoardCell> allBoardEntrys,
                             final SystemUser boardOwner)
    {
        BoardFactory boardFactory = new BoardFactory();

        Board newBoard = boardFactory.create(boardTitlep, boardNRowp,
                boardNColp, allBoardEntrys, boardOwner);

        return boardRepository.save(newBoard);
    }
}

