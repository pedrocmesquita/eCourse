package eapli.ecourse.usertypemanagement.teacherusermanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Embeddable
public class Acronym implements ValueObject, Comparable<Acronym> {
    private String acronym;

    protected Acronym() {
        //ORM only
    }

    protected Acronym(String acronym) {
        //TODO validate acronym
        //regex
        setAcronym(acronym);
    }

    public void setAcronym(String acronym) {
        if (StringPredicates.isNullOrEmpty(acronym))
            throw new IllegalArgumentException("Acronym should neither be null nor empty");
        String regex = "^[A-Z]+$";
        if(!acronym.matches(regex)) {
            throw new IllegalArgumentException("Acronym should only have capital letters");
        }
        this.acronym = acronym;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Acronym acronym1 = (Acronym) o;
        return Objects.equals(acronym, acronym1.acronym);
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
