package eapli.ecourse.boardmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;


import java.util.Objects;

public class BoardTitle implements ValueObject, Comparable<BoardTitle> {
    /**
     * Board Title of Entity.
     */

    private final String value;

    /**
     * The constant MAX_LENGTH.
     */
    private static final Integer MAX_LENGTH = 50;


    /**
     * The constant MIN_LENGTH.
     */
    private static final Integer MIN_LENGTH = 5;

    /**
     * Instantiates a new BoardTitle.
     */
    protected BoardTitle() {
        this.value = null;
    }

    private BoardTitle(final String valuep) {
        Preconditions.nonEmpty(
                valuep,
                "Board Title should neither be null nor empty"
        );
        Preconditions.nonNull(
                valuep,
                "Board Title should neither be null nor empty"
        );
        Preconditions.ensure(
                valuep.length() <= MAX_LENGTH && valuep.length() >= MIN_LENGTH,
                "Board Title should have between "
                        + MIN_LENGTH + " and "
                        + MAX_LENGTH + " characters"
        );

        this.value = valuep;
    }

    /**
     * Factory method to create a BoardTitle instance.
     *
     * @param valuep The value of the board title.
     * @return BoardTitle instance.
     */
    public static BoardTitle of(final String valuep) {
        return new BoardTitle(valuep);
    }

    /**
     * Value string.
     *
     * @return the string
     */
    public String value() {
        return value;
    }

    /**
     * Compare if BoardTitle is equals to another object.
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
        BoardTitle other = (BoardTitle) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public int compareTo(BoardTitle o) {
        return 0;
    }
}
