package eapli.framework.infrastructure.authz.domain.model;

import eapli.framework.validations.Preconditions;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Nif {

    private static final String REGEX_PT = "[0-9]{9}";


    @Column(name = "nif", unique = true)
    private final String value;

    private Nif(String value) {
        Preconditions.ensure(value.matches(REGEX_PT), "Nif must be 9 digits long");
        this.value = value;
    }

    protected Nif(){
        this.value = null;
    }

    public static Nif valueOf(String value){
        return new Nif(value);
    }

    String value(){
        return this.value;
    }
}
