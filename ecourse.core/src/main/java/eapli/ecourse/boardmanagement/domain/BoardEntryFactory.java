package eapli.ecourse.boardmanagement.domain;

public class BoardEntryFactory {


    /**
     * BoardEntryFactory constructor.
     */
    public BoardEntryFactory() {

    }

    /**
     * Create BoardEntry.
     * @param entryNumberp
     * @param boardRowp
     * @param boardColp
     * @param entryTitlep
     * @param boardNRowp
     * @param boardNColp
     * @return BoardEntry
     */
    public BoardEntry create(final String entryNumberp,
                             final String boardNRowp,
                             final String boardNColp,
                             final String entryTitlep,
                             final String boardRowp,
                             final String boardColp) {
        BoardNRows boardNRows = BoardNRows.of(boardNRowp);
        BoardNCols boardNCols = BoardNCols.of(boardNColp);

        BoardRow boardRow = BoardRow.of(boardNRowp, boardRowp);

        BoardCol boardCol = BoardCol.of(boardNColp, boardColp);


        return new BoardEntry(
                EntryNumber.of(entryNumberp, boardRow,
                        boardCol, boardNRows, boardNCols),
                boardRow,
                boardCol,
                EntryTitle.of(entryTitlep)
        );
    }
}
