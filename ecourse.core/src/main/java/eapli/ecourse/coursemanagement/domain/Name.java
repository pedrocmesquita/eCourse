package eapli.ecourse.coursemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Name implements ValueObject, Comparable<Name> {

    /**
     * Course business ID
     */
    @Column(unique = true)
    private String name;

    public Name() {
        //ORM only
    }

    /**
     * Constructor
     *
     * @param name
     */
    public Name(String name) {
        setName(name);
    }

    /**
     * Validates if name is not null or empty, then sets name
     */
    private void setName(String name) {
        if (StringPredicates.isNullOrEmpty(name)) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int compareTo(Name o) {
        return name.compareTo(o.name);
    }
}
