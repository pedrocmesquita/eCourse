package eapli.ecourse.usertypemanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;
import java.util.Objects;

@Embeddable
public class BirthDate implements ValueObject {

    @Temporal(TemporalType.DATE)
    Calendar birthDate;

    protected BirthDate() {
        //ORM only
    }

    public BirthDate(Calendar birthDate) {
        setBirthDate(birthDate);
    }

    public void setBirthDate(Calendar birthDate) {
        if (birthDate == null) {
            throw new IllegalArgumentException("birthdate cannot be null");
        }
        if (birthDate.compareTo(Calendar.getInstance()) > 0)
            throw new IllegalArgumentException("birthdate cannot be a future date");
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BirthDate birthDate1 = (BirthDate) o;
        return Objects.equals(birthDate, birthDate1.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(birthDate);
    }

    @Override
    public String toString() {
        return birthDate.toString();
    }
}
