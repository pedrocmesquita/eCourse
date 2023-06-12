package eapli.ecourse.boardmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;


import java.util.Objects;

public class BoardCol implements ValueObject {
    /**
     * Board Column position.
     */

    private final Integer value;

    /**
     * The constant MIN_COLUMNS.
     */
    private static final Integer MIN_COLUMNS = 1;

    /**
     * Instantiates a new BoardCol.
     */
    protected BoardCol() {
        this.value = null;
    }

    private BoardCol(final String valuep,
                     final String boardNColp) {
        Preconditions.nonEmpty(
                valuep,
                "Column position should neither be null nor empty"
        );
        Preconditions.nonNull(
                valuep,
                "Column position should neither be null nor empty"
        );

        int nColPos = Integer.parseInt(valuep);
        int MAX_COLUMNS = Integer.parseInt(boardNColp);

        Preconditions.ensure(
                nColPos >= MIN_COLUMNS && nColPos <= MAX_COLUMNS,
                "Column position should have between 1 and "
                        + MAX_COLUMNS
        );

        this.value = nColPos;
    }

    /**
     * Factory method to create a BoardCol instance.
     * @param valuep The value of the board column position.
     * @param boardNColp
     * @return BoardCol instance.
     */
    public static BoardCol of(final String valuep,
                              final String boardNColp) {
        return new BoardCol(valuep, boardNColp);
    }

    /**
     * Value int.
     * @return the int
     */
    public int value() {
        return value;
    }

    /**
     * Compare two BoardCol.
     * @param obj
     * @return true/false
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BoardCol other = (BoardCol) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString(){
        return String.valueOf(value);
    }
}
