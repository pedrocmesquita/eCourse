package eapli.ecourse.coursemanagement.domain;

import eapli.framework.domain.model.ValueObject;

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
        this.minEnroll = minEnroll;
        this.maxEnroll = maxEnroll;
    }

}
