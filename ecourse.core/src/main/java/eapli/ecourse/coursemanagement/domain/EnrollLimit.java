package eapli.ecourse.coursemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class EnrollLimit implements ValueObject {

    private Integer minEnroll;
    private Integer maxEnroll;

    public EnrollLimit() {
        //ORM only
    }

    /**
     * Constructor
     *
     * @param minEnroll Mandatory (0, INTEGER.MAX)
     * @param maxEnroll Mandatory (0, INTEGER.MAX)
     */
    public EnrollLimit(Integer minEnroll, Integer maxEnroll) {
        setEnrollLimit(minEnroll, maxEnroll);
    }

    /**
     * Validates if values are not negative nor maximum value smaller than minimum, then sets values
     */
    private void setEnrollLimit(Integer minEnroll, Integer maxEnroll) {
        Preconditions.nonNegative(minEnroll, "Minimum enrollment cannot be negative");
        Preconditions.nonNegative(maxEnroll, "Maximum enrollment cannot be negative");
        if (minEnroll > maxEnroll) {
            throw new IllegalArgumentException("Maximum limit must be higher than minimum");
        }
        this.minEnroll = minEnroll;
        this.maxEnroll = maxEnroll;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnrollLimit that = (EnrollLimit) o;
        return Objects.equals(minEnroll, that.minEnroll) && Objects.equals(maxEnroll, that.maxEnroll);
    }

    @Override
    public int hashCode() {
        return Objects.hash(minEnroll, maxEnroll);
    }

    @Override
    public String toString() {
        return "Minimum Enrollment: " + (minEnroll == null ? "0" : minEnroll)
                + "\nMaximum Enrollment: " + (maxEnroll == null ? "0" : maxEnroll);
    }
}
