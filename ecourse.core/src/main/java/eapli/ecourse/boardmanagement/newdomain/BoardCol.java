package eapli.ecourse.boardmanagement.newdomain;

import eapli.framework.validations.Preconditions;

public class BoardCol {
    private int valuec;
    private static final Integer MIN_COLS = 1;
    protected BoardCol() {
    }
    public BoardCol(final String valuep,
                     final String boardNRowp) {
        Preconditions.nonEmpty(
                valuep,
                "Row position should neither be null nor empty"
        );
        Preconditions.nonNull(
                valuep,
                "Row position should neither be null nor empty"
        );

        int nRows = Integer.parseInt(valuep);
        int MAX_COLS = Integer.parseInt(boardNRowp);

        Preconditions.ensure(
                nRows >= MIN_COLS && nRows <= MAX_COLS,
                "Row position should have between 1 and "
                        + MAX_COLS
        );

        this.valuec = nRows;
    }
    public int value() {
        return valuec;
    }
}