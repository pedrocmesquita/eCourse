package eapli.ecourse.coursemanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Description implements ValueObject {

    private static final long serialVersionUID = 1L;

    private String description;

    public Description() {
        //ORM only
    }

    public Description(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    @Override
    public String toString() {
        return description;
    }
}
