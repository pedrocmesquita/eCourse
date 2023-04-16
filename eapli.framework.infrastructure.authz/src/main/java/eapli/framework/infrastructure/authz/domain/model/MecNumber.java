package eapli.framework.infrastructure.authz.domain.model;

import eapli.framework.validations.Preconditions;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class MecNumber {

    @Column(name = "mecNumber")
    private final String value;

    private MecNumber(String value) {
        this.value = value;
    }

    protected MecNumber(){
        this.value = null;
    }

    public static MecNumber valueOf(String value){
        return new MecNumber(value);
    }

    String value(){
        return this.value;
    }

    private MecNumber(Integer year, Integer number){
        Preconditions.ensure(year <= LocalDate.now().getYear(), "Student cannot be enrolled in the future");
        Preconditions.ensure(number > 0 && number < 100000);

        this.value = year + buildNumberString(number);
    }

    static String buildNumberString(Integer number) {
        StringBuilder sb = new StringBuilder();
        int length = number.toString().length();

        for (int i = length; i < 5; i++) {
            sb.append("0");
        }

        sb.append(number);

        return sb.toString();
    }
}

