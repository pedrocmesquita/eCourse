package eapli.ecourse.coursemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.math.util.NumberPredicates;

import javax.persistence.Embeddable;

@Embeddable
public class EnrollLimit implements ValueObject {

    private static final long serialVersionUID = 1L;

    private Integer minEnroll;
    private Integer maxEnroll;

    public EnrollLimit() {
        //ORM only
    }

    public EnrollLimit(Integer minEnroll, Integer maxEnroll) {
        if(NumberPredicates.isNegative(minEnroll) || NumberPredicates.isNonNegative(maxEnroll)) {
            throw new IllegalArgumentException("Limits cannot be negative");
        }
        if(minEnroll > maxEnroll) {
            throw new IllegalArgumentException("Maximum limit must be higher than minimum");
        }
        this.minEnroll = minEnroll;
        this.maxEnroll = maxEnroll;
    }

}
