package eapli.ecourse.boardmanagement.domain;

import eapli.ecourse.Application;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;


import java.util.Objects;

public class BoardNRows implements ValueObject {
    /**
     * Board Title of Entity.
     */

    private final Integer value;

    /**
     * The constant MAX_LENGTH.
     */
    private static final Integer MAX_ROWS = Application.settings().maxRows();


    /**
     * The constant MIN_LENGTH.
     */
    private static final Integer MIN_ROWS = 1;

    /**
     * Instantiates a new BoardTitle.
     */
    protected BoardNRows() {
        this.value = null;
    }

    private BoardNRows(final String valuep) {
        Preconditions.nonEmpty(
                valuep,
                "The number of Rows should neither be null nor empty"
        );
        Preconditions.nonNull(
                valuep,
                "The number of Rows should neither be null nor empty"
        );

        int nRows = Integer.parseInt(valuep);

        Preconditions.ensure(
                nRows >= MIN_ROWS && nRows <= MAX_ROWS,
                "The number of Rows should be between 1 and "+ MAX_ROWS
        );

        this.value = nRows;
    }

    /**
     * Factory method to create a BoardNRow instance.
     * @param valuep The value of the board number of rows.
     * @return BoardNRow instance.
     */
    public static BoardNRows of(final String valuep) {
        return new BoardNRows(valuep);
    }

    /**
     * Value int.
     * @return the int
     */
    public int value() {
        return value;
    }

    /**
     * Compare if BoardNRow is same then another object.
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
        BoardNRows other = (BoardNRows) obj;
        return Objects.equals(value, other.value);
    }

}
