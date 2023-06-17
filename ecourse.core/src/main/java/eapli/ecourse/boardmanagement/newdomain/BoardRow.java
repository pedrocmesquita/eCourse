package eapli.ecourse.boardmanagement.newdomain;

import eapli.framework.validations.Preconditions;

public class BoardRow {
    private int valuer;
    private static final Integer MIN_ROWS = 1;
    protected BoardRow() {
    }
    public BoardRow(final String valuep,
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
        int MAX_ROWS = Integer.parseInt(boardNRowp);

        Preconditions.ensure(
                nRows >= MIN_ROWS && nRows <= MAX_ROWS,
                "Row position should have between 1 and "
                        + MAX_ROWS
        );

        this.valuer = nRows;
    }
    public int value() {
        return valuer;
    }
}
