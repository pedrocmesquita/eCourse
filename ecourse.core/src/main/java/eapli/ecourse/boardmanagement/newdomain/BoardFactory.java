package eapli.ecourse.boardmanagement.newdomain;

import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.Set;

public class BoardFactory {
    /**
     * BoardFactory constructor.
     */
    public BoardFactory() {

    }

    public Board create(final String boardTitlep,
                        final int boardNRowp,
                        final int boardNColp,
                        final Set<BoardCell> allBoardEntrys,
                        final SystemUser boardOwner) {
        return new Board(BoardTitle.of(boardTitlep), boardNRowp, boardNColp, boardOwner);
    }
}

