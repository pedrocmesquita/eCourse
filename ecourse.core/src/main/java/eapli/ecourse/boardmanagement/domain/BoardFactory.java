package eapli.ecourse.boardmanagement.domain;

import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.List;

public class BoardFactory {
    /**
     * BoardFactory constructor.
     */
    public BoardFactory() {

    }

    /**
     * Create BoardFactory.
     * @param boardTitlep
     * @param boardNRowp
     * @param boardNColp
     * @param allBoardEntrys
     * @param boardOwnerp
     * @return Board
     */
    public Board create(final String boardTitlep,
                        final String boardNRowp,
                        final String boardNColp,
                        final List<BoardEntry> allBoardEntrys,
                        final SystemUser boardOwnerp) {
        return new Board(
                BoardTitle.of(boardTitlep),
                BoardNRows.of(boardNRowp),
                BoardNCols.of(boardNColp),
                allBoardEntrys,
                boardOwnerp
        );
    }
}

