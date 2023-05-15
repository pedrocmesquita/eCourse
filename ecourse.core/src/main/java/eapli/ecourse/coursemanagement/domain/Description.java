package eapli.ecourse.coursemanagement.domain;

import eapli.ecourse.Application;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Description implements ValueObject {

    private String description;

    protected Description() {
        //ORM only
    }

    /**
     * Constructor
     *
     * @param description
     */
    protected Description(String description) {
        setDescription(description);
    }

    /**
     * Validates if description doesnt excess value defined in config file, then sets description
     */
    private void setDescription(String description) {
        if (StringPredicates.isNullOrEmpty(description)) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        if (description.length() > Application.settings().getCourseDescriptionCharacterLimit())
            throw new IllegalArgumentException("Description excess character limit");
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Description that = (Description) o;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    @Override
    public String toString() {
        return this.description;
    }

}
