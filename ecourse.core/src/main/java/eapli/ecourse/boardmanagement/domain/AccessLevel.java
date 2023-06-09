package eapli.ecourse.boardmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

public class AccessLevel implements ValueObject {
    /**
     * AccessLevel type for users.
     */
    private final String accessLevel;

    private AccessLevel(final String accessLevelp) {
        Preconditions.nonEmpty(accessLevelp);
        this.accessLevel = accessLevelp;
    }

    /**
     * Instantiates a new AccessLevel.
     */
    protected AccessLevel() {
        this.accessLevel = null;
    }

    /**
     * @return String of accessLevel
     */
    public String toString() {
        return this.accessLevel;
    }

    /**
     * Parse string to AccessLevel.
     * @param accessLevelp
     * @return AccessLevel
     */
    public static AccessLevel valueOf(final String accessLevelp) {
        return new AccessLevel(accessLevelp);
    }

    /**
     * Check if two objects are equal.
     * @param o object
     * @return true/false
     */
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof AccessLevel)) {
            return false;
        } else {
            AccessLevel other = (AccessLevel) o;

            if (!other.canEqual(this)) {
                return false;
            } else {
                Object thisAccessLevel = this.accessLevel;
                Object otherAccessLevel = other.accessLevel;

                if (thisAccessLevel == null) {
                    if (otherAccessLevel != null) {
                        return false;
                    }
                } else if (!thisAccessLevel.equals(otherAccessLevel)) {
                    return false;
                }

                return true;
            }
        }
    }

    /**
     * Can be equals.
     * @param other
     * @return true/false
     */
    protected boolean canEqual(final Object other) {
        return other instanceof AccessLevel;
    }
}