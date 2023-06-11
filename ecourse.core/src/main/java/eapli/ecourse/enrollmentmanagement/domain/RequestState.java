package eapli.ecourse.enrollmentmanagement.domain;

public enum RequestState {
    /**
     * Pending request state.
     */
    PENDING,
    /**
     * Accepted request state.
     */
    ACCEPTED,
    /**
     * Rejected request state.
     */
    REJECTED;

    /**
     * The Value.
     */
    private String value;

    RequestState() {
        this.value = this.name();
    }

    /**
     * Value string.
     *
     * @return the string
     */
    String value() {
        return this.value;
    }
}

