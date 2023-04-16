package eapli.framework.infrastructure.authz.domain.model;

import eapli.framework.validations.Preconditions;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Embeddable
public class BirthDate {

    @Column(name = "birthDate")
    private LocalDate value;

    private static final int YEAR_LIMIT = 150;


    protected BirthDate() {
        value = null;
    }

    private BirthDate(final LocalDate value) {
        Preconditions.ensure(value.isBefore(LocalDate.now()), "You can't be born in the future");
        Preconditions.ensure(value.isAfter(LocalDate.now().minusYears(YEAR_LIMIT)), "Your birthdate can't be more than 150 years ago.");
        this.value = value;
    }

    private BirthDate(final String value) {
        LocalDate date = LocalDate.parse(
                value,
                DateTimeFormatter.ofPattern("dd/MM/yyyy")
        );
        Preconditions.ensure(
                date.isBefore(LocalDate.now()), "You can't be born in the future"
        );
        Preconditions.ensure(
                date.isAfter(LocalDate.now().minusYears(YEAR_LIMIT)), "Your birthdate can't be more than 150 years ago."
        );
        this.value = date;
    }

    public static BirthDate valueOf(final String date) {
        return new BirthDate(date);
    }

    public static BirthDate valueOf(final LocalDate date) {
        return new BirthDate(date);
    }

    LocalDate value() {
        return this.value;
    }
}
