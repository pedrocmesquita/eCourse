package eapli.base.teacherusermanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;

@Embeddable
public class Acronym implements ValueObject, Comparable<Acronym> {
    private String acronym;

    protected Acronym() {
        //ORM only
    }

    @Override
    public int compareTo(Acronym o) {
        return acronym.compareTo(o.acronym);
    }

    public Acronym(String acronym) {
        if (StringPredicates.isNullOrEmpty(acronym)) {
            throw new IllegalArgumentException(
                    "Acronym Number should neither be null nor empty");
        }
        //TODO regex
        this.acronym = acronym;
    }
}
