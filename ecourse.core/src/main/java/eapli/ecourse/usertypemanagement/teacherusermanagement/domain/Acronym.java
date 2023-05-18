package eapli.ecourse.usertypemanagement.teacherusermanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;

@Embeddable
public class Acronym implements ValueObject, Comparable<Acronym> {
    private String acronym;

    protected Acronym() {
        //ORM only
    }

    protected Acronym(String acronym) {
        if (StringPredicates.isNullOrEmpty(acronym)) {
            throw new IllegalArgumentException(
                    "Acronym Number should neither be null nor empty");
        }
        //TODO validate acronym
        //regex
        this.acronym = acronym;
    }

    @Override
    public int hashCode() {
        return this.acronym.hashCode();
    }

    @Override
    public String toString() {
        return this.acronym;
    }

    @Override
    public int compareTo(Acronym o) {
        return acronym.compareTo(o.acronym);
    }
}
