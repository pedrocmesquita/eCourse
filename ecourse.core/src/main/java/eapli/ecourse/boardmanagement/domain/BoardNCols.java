package eapli.ecourse.boardmanagement.domain;

import eapli.ecourse.Application;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;


import java.util.Objects;

public class BoardNCols implements ValueObject {
    /**
     * Board Title of Entity.
     */

    private final Integer value;

    /**
     * The constant MAX_LENGTH.
     */
    private static final Integer MAX_COLUMNS = Application.settings().maxColumns();



    /**
     * The constant MIN_LENGTH.
     */
    private static final Integer MIN_COLUMNS = 1;

    /**
     * Instantiates a new BoardNCol.
     */
    protected BoardNCols() {
        this.value = null;
    }

    private BoardNCols(final String valuep) {
        Preconditions.nonEmpty(
                valuep,
                "The number of Columns should neither be null nor empty"
        );
        Preconditions.nonNull(
                valuep,
                "The number of Columns should neither be null nor empty"
        );

        int nColumns = Integer.parseInt(valuep);

        Preconditions.ensure(
                nColumns >= MIN_COLUMNS && nColumns <= MAX_COLUMNS,
                "The number of Columns should be between 1 and " + MAX_COLUMNS
        );

        this.value = nColumns;
    }

    /**
     * Factory method to create a BoardNCol instance.
     * @param valuep The value of the board number of columns.
     * @return BoardNCol instance.
     */
    public static BoardNCols of(final String valuep) {
        return new BoardNCols(valuep);
    }

    /**
     * Value int.
     * @return the int
     */
    public int value() {
        return value;
    }

    /**
     * Compare if BoardNCol is same then another object.
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
        BoardNCols other = (BoardNCols) obj;
        return Objects.equals(value, other.value);
    }

}
